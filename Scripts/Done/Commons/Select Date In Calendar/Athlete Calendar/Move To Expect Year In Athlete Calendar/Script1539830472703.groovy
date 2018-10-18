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

int currentYear = Integer.parseInt(WebUI.getText(findTestObject('Commons/Athlete Calendar/text_Current Date')).split(' ')[1])

while (currentYear != var_year){
	
	if (currentYear < var_year){
		
		WebUI.click(findTestObject('Commons/Athlete Calendar/button_Next'))
		
		Thread.sleep(500)
		
	}
	
	else {
		
		WebUI.click(findTestObject('Commons/Athlete Calendar/button_Previous'))
		
		Thread.sleep(500)
		
	}
	
	currentYear = Integer.parseInt(WebUI.getText(findTestObject('Commons/Athlete Calendar/text_Current Date')).split(' ')[1])
	
}


