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

if (WebUI.verifyElementPresent(findTestObject('Program Assignment Popup/header_Select A Program'), 2, FailureHandling.OPTIONAL)){

	if (var_ProgramName == ''){
		
		int tmp = 0
		
		while (tmp <= 0) {
			
			WebUI.waitForElementNotPresent(findTestObject('Program Assignment Popup/img_spin'), 30)
			
			int rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(CustomKeywords.'bridgeathletic.total.getItemSize'('//div[@class = "items name ng-scope"]'))
			
			TestObject randomProgramObject = new TestObject()
			
			randomProgramObject.addProperty('xpath', ConditionType.EQUALS, '(//div[@class = "items name ng-scope"])['+ rd +']', true)
			
			WebUI.click(randomProgramObject)
			
			tmp = Integer.parseInt(WebUI.getText(findTestObject('Program Assignment Popup/text_Week Numbers Selected')).split(' ')[0])
			
		}
		
	}
	
	else {
		
		WebUI.waitForElementNotPresent(findTestObject('Program Assignment Popup/img_spin'), 30)
		
		WebUI.click(findTestObject('Program Assignment Popup/icon_Search'))
		
		WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/input_Search For Programs'), 30)
		
		WebUI.setText(findTestObject('Program Assignment Popup/input_Search For Programs'), var_ProgramName)
		
		WebUI.waitForElementNotPresent(findTestObject('Program Assignment Popup/img_spin'), 30)
		
		WebUI.delay(1)
		
		TestObject resultProgramObject = new TestObject()
		
		resultProgramObject.addProperty('xpath', ConditionType.EQUALS, '(//div[@class = "items name ng-scope"])[1]', true)
		
		WebUI.click(resultProgramObject)
		
	}
	
	WebUI.click(findTestObject('Program Assignment Popup/button_Next'))
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
}