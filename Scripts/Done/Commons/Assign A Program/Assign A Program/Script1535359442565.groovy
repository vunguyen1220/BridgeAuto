import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/popup_Program Assignment'), 30)

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 1_Select A Team'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 2_Select Members'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 3_Select A Program'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 4_Training Schedule'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 5_Review'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 6_Members With Programs'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 7_Super Admin Settings'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Step 8_Coach Settings'), [:])

WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/header_Successful Assignment'), 30)

WebUI.click(findTestObject('Program Assignment Popup/button_OK'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.delay(2)







