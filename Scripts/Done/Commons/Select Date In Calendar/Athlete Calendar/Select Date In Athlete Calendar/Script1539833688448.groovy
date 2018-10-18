import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


if (var_year != 0){

	WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Athlete Calendar/Move To Expect Year In Athlete Calendar'), [var_year:var_year])

}

if (var_month != 0){

	WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Athlete Calendar/Move To Expect Month In Athlete Calendar'), [var_month:var_month])

}

String day

if (var_month < 10){

	day = var_year + '-0' + var_month

}

else {

	day = var_year + '-' + var_month

}

if (var_day < 10){

	day = day + '-0' + var_day

}

else {

	day = day + '-' + var_day

}

TestObject selectDayObject = new TestObject()

selectDayObject.addProperty('xpath', ConditionType.EQUALS, '//td[@data-date = "'+ day +'"]//div[@class="fc-day-content"]', true)




