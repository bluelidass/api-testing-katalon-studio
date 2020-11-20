import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper

response = WS.sendRequest(findTestObject('ReqresInAPI/Login', [('email') : email, ('password') : password]))

def jsonSlurper = new JsonSlurper()

def object = jsonSlurper.parseText(response.getResponseBodyContent())

errorMessage = object.error

if ((email != '') && (password == '')) {
	'Verify if an user cannot login with a valid email and an empty password.'
	assert errorMessage == 'Missing password'

	WS.verifyResponseStatusCode(response, 400)
} else if ((email == '') && (password == '')) {
	'Verify if an user cannot login with an empty email and a password.'
	assert errorMessage == 'Missing email or username'

	WS.verifyResponseStatusCode(response, 400)
} else if ((email == '') && (password != '')) {
	'Verify the login for both, when the input is empty.'
	assert errorMessage == 'Missing email or username'

	WS.verifyResponseStatusCode(response, 400)
} else {
	'Login - Successful.'
	assert errorMessage == null

	WS.verifyResponseStatusCode(response, 200)
}
