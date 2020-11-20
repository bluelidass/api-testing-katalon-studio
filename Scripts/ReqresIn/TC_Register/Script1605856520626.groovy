import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

response = WS.sendRequest(findTestObject('ReqresInAPI/Register', [('email') : email, ('password') : password]))

def jsonSlurper = new JsonSlurper()

def object = jsonSlurper.parseText(response.getResponseBodyContent())

if ((email != '') && (password == '')) {
    'Verify if an user cannot register with a valid email and an empty password.'
    assert errorMessage == 'Missing password'

    WS.verifyResponseStatusCode(response, 400)
} else if ((email == '') && (password == '')) {
    'Verify if an user cannot register with an empty email and a password.'
    assert errorMessage == 'Missing email or username'

    WS.verifyResponseStatusCode(response, 400)
} else if ((email == '') && (password != '')) {
    'Verify the register for both, when the input is empty.'
    assert errorMessage == 'Missing email or username'

    WS.verifyResponseStatusCode(response, 400)
} else {
    'Register - Successful.'
    assert errorMessage == null

    WS.verifyResponseStatusCode(response, 200)
}

