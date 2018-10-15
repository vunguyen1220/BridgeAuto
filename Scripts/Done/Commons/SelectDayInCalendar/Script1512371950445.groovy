import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

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

String startDate = startDate

int expectYear = expectYear

String expectMonth = startDate.substring(0, 3)

String selectDay = startDate.substring(4)

if (selectDay[0] == '0'){
	selectDay = selectDay.substring(1)
}

System.out.println(selectDay)

def m = Date.parse('MMM',expectMonth)

int expectMonthNumber = m.format('MM') as int

System.out.println(expectMonthNumber)

System.out.println(expectYear)

String actualCal = WebUI.getText(findTestObject('Commons/h2_title'))

System.out.println('Actual Month: ' + actualCal)

int actualYear = Integer.parseInt(actualCal.substring(actualCal.length() - 4))

System.out.println('Actual Year: ' + actualYear)

while (actualYear != expectYear) {

	if (actualYear < expectYear){

		WebUI.click(findTestObject('Commons/span_fc-button fc-button-next'))

	}
	else{

		WebUI.click(findTestObject('Commons/span_fc-button fc-button-prev'))

	}
	
	actualCal = WebUI.getText(findTestObject('Commons/h2_title'))

	actualYear = Integer.parseInt(actualCal.substring(actualCal.length() - 4))

}

actualCal = WebUI.getText(findTestObject('Commons/h2_title'))

String actualMonth = actualCal.substring(0, 3)

m = Date.parse('MMM',actualMonth)

int actualMonthNumber = m.format('MM') as int

while (actualMonthNumber != expectMonthNumber) {
	
		if (actualMonthNumber < expectMonthNumber){
	
			WebUI.click(findTestObject('Commons/span_fc-button fc-button-next'))
	
		}
		else{
	
			WebUI.click(findTestObject('Commons/span_fc-button fc-button-prev'))
	
		}
		
		actualCal = WebUI.getText(findTestObject('Commons/h2_title'))
	
		actualMonth = actualCal.substring(0, 3)

		m = Date.parse('MMM',actualMonth)

		actualMonthNumber = m.format('MM') as int
		
		System.out.println('Actual Month: ' + actualMonthNumber)
	
	}

TestObject selectDayObject = new TestObject()

selectDayObject.addProperty('xpath', ConditionType.EQUALS, '//span[@class="fc-event-title" and text()="'+ selectDay +'"]', true)

if (WebUI.verifyElementPresent(selectDayObject, 1, FailureHandling.OPTIONAL) == false){
	
	selectDayObject = WebUI.removeObjectProperty(selectDayObject, 'xpath')
	
	selectDayObject.addProperty('xpath', ConditionType.EQUALS, '//span[@class="fc-event-title" and text()="0'+ selectDay +'"]', true)
	
}

return selectDayObject