import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

String current_url = WebUI.getUrl()

if (WebUI.verifyMatch(current_url, var_url, false, FailureHandling.OPTIONAL) == false) {

	WebUI.navigateToUrl(var_url)

}

WebUI.click(findTestObject('Home Page/button_Login'))

current_url = WebUI.getUrl()

WebUI.verifyMatch(current_url, var_url + '/login', false)

WebUI.setText(findTestObject('Login Page/input_email'), var_email)

WebUI.setText(findTestObject('Login Page/input_password'), var_password)

WebUI.click(findTestObject('Login Page/button_LOGIN'))

WebUI.waitForElementPresent(findTestObject('Header/div_User Details'), 30)

if (CustomKeywords.'bridgeathletic.total.getItemSize'('//button[@class="md-raised md-button md-default-theme"]') > 0){

	TestObject reloadObject = new TestObject()

	reloadObject.addProperty('xpath', ConditionType.EQUALS, '//button[@class="md-raised md-button md-default-theme"]', true)

	WebUI.click(reloadObject)

}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])