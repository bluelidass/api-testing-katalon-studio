import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

'Check user with valid id'
response = WS.sendRequest(findTestObject('ReqresInAPI/Single User', [('userId') : userId]))

def jsonSlurper = new JsonSlurper()

def object = jsonSlurper.parseText(response.getResponseBodyContent())

WS.verifyResponseStatusCode(response, 200)

WS.verifyElementPropertyValue(response, 'data.id', userId)

WS.verifyElementPropertyValue(response, 'data.first_name', firstName)

WS.verifyElementPropertyValue(response, 'data.last_name', lastName)

WS.verifyElementPropertyValue(response, 'data.email', email)

WS.verifyElementPropertyValue(response, 'data.avatar', avatar)