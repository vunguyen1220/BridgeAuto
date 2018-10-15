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

if (WebUI.verifyElementPresent(findTestObject('Program Assignment Popup/header_Select Members'), 2, FailureHandling.OPTIONAL)){
	
	if (var_MemberList.size() > 0){
		
		for (int i = 0; i < var_MemberList.size(); i = i + 1){
			
			TestObject userNameObject = new TestObject()
			
			userNameObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class = "item team-name" and . = "'+ var_MemberList[i] +'"]', true)
			
			WebUI.click(userNameObject)
			
			TestObject userNameSelectedObject = new TestObject()
			
			userNameSelectedObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class = "user-name-selected ng-binding" and . = "'+ var_MemberList[i] +'"]', true)
			
			WebUI.waitForElementPresent(userNameSelectedObject, 30)
			
		}
		
	}
	
	else {
		
		WebUI.click(findTestObject('Program Assignment Popup/checkbox_Select All Athletes'))
		
		WebUI.waitForElementPresent(findTestObject('Program Assignment Popup/div_User Name Selected'), 30)
		
	}
	
	WebUI.click(findTestObject('Program Assignment Popup/button_Next'))
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
}