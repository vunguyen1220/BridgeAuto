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
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

List<String> currentDate = WebUI.getText(findTestObject('Commons/Calendar Popup/header_Current Date')).split(' ')

int currentMonth = CustomKeywords.'com.bridgeathletic.convert.convertMonthFromStringToInteger'(currentDate[0], 'MMMM')

while (currentMonth != var_month){
	
	if (currentMonth < var_month){
		
		WebUI.click(findTestObject('Commons/Calendar Popup/button_Next'))
		
		Thread.sleep(500)
		
		currentDate = WebUI.getText(findTestObject('Commons/Calendar Popup/header_Current Date')).split(' ')
		
		currentMonth = CustomKeywords.'com.bridgeathletic.convert.convertMonthFromStringToInteger'(currentDate[0], 'MMMM')
		
	}
	
	else {
		
		WebUI.click(findTestObject('Commons/Calendar Popup/button_Previous'))
		
		Thread.sleep(500)
		
		currentDate = WebUI.getText(findTestObject('Commons/Calendar Popup/header_Current Date')).split(' ')
		
		currentMonth = CustomKeywords.'com.bridgeathletic.convert.convertMonthFromStringToInteger'(currentDate[0], 'MMMM')
		
	}
	
}