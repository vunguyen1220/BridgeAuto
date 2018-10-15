import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//span[@ui-sref = "user.organizations.members.home"]') == 0){
	
	WebUI.click(findTestObject('Breadcrumbs/button_Home'))
	
}

WebUI.click(findTestObject('Org Home Page/header_Members'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.setText(findTestObject('Members Page/input_Search Member'), var_userName)

WebUI.delay(1)

WebUI.waitForElementNotPresent(findTestObject('Members Page/icon_Wait Search'), 30)

WebUI.waitForElementPresent(findTestObject('Members Page/icon_Clear Search'), 30)

TestObject userNameObject = new TestObject()

userNameObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="member-item ng-scope"]/div[contains(., "'+ var_userName +'")]', true)

WebUI.click(userNameObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])





