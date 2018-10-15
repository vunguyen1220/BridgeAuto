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

WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/header_Review'), 30)

if (var_programName != ''){
	
	WebUI.click(findTestObject('Program Assignment Popup/button_Edit Name'))
	
	WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/input_Program Name'), 30)
	
	WebUI.setText(findTestObject('Program Assignment Popup/input_Program Name'), var_programName)
	
	WebUI.click(findTestObject('Program Assignment Popup/button_Save'))
	
	WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/button_Edit Name'), 30)
	
}

if (var_removeUserList.size() > 0){
	
	for (int i = 0; i < var_removeUserList.size(); i = i + 1){
		
		TestObject removeUserSelectedObject = new TestObject()
		
		removeUserSelectedObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class = "user-name-selected ng-binding" and . = "'+ var_removeUserList[i] +'"]/i[@class = "fa fa-close"]', true)
		
		WebUI.click(removeUserSelectedObject)
		
		Thread.sleep(500)
		
	}
	
}

WebUI.click(findTestObject('Program Assignment Popup/button_Assign Training'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])