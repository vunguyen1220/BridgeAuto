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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

List<String> currentDate = WebUI.getText(findTestObject('Commons/Program Calendar Popup/header_Current Date')).split(' ')

int currentMonth = CustomKeywords.'com.bridgeathletic.convert.convertMonthFromStringToInteger'(currentDate[0], 'MMMM')

while (currentMonth != var_month){
	
	if (currentMonth < var_month){
		
		WebUI.click(findTestObject('Commons/Athlete Calendar/button_Next'))
		
		WebUI.delay(1)
		
	}
	
	else {
		
		WebUI.click(findTestObject('Commons/Athlete Calendar/button_Previous'))
		
		WebUI.delay(1)
		
	}
	
	currentMonth = CustomKeywords.'com.bridgeathletic.convert.convertMonthFromStringToInteger'(WebUI.getText(findTestObject('Commons/Program Calendar Popup/header_Current Date')).split(' ')[0], 'MMMM')
	
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])