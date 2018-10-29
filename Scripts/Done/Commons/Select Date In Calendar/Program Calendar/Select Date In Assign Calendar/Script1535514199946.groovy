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

if (var_year != 0){

	WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Program Calendar/Move To Expect Year In Program Calendar'), [var_year:var_year])

}

if (var_month != 0){

	WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Program Calendar/Move To Expect Month In Program Calendar'), [var_month:var_month])

}

if (var_day != 0){

	List<String> currentDate = WebUI.getText(findTestObject('Commons/Program Calendar Popup/header_Current Date')).split(' ')

	int currentMonth = CustomKeywords.'bridgeathletic.convert.convertMonthFromStringToInteger'(currentDate[0], 'MMMM')

	int currentYear = Integer.parseInt(currentDate[1])

	String day

	if (currentMonth < 10){

		day = currentYear + '-0' + currentMonth

	}

	else {

		day = currentYear + '-' + currentMonth

	}

	if (var_day < 10){

		day = day + '-0' + var_day

	}

	else {

		day = day + '-' + var_day

	}

	TestObject selectDayObject = new TestObject()

	selectDayObject.addProperty('xpath', ConditionType.EQUALS, '//td[@data-date = "'+ day +'"]', true)

	WebUI.click(selectDayObject)

}

else {

	WebUI.click(findTestObject('Commons/Program Calendar Popup/button_Today'))

}