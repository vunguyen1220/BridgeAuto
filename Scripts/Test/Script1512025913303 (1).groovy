import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.junit.Assert.*

import java.lang.reflect.Array as Array
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.LocalDate as LocalDate
import java.util.concurrent.ThreadLocalRandom as ThreadLocalRandom
import java.util.Date as Date
import java.text.ParseException as ParseException

import org.junit.After as After
import org.junit.Test
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import bridgeathletic.attendanceInfo as attendanceInfo
import bridgeathletic.blockInfo as blockInfo
import bridgeathletic.eventInfo as eventInfo
import bridgeathletic.phase as phase
import bridgeathletic.program as program
import bridgeathletic.exercise as exercise
import bridgeathletic.testResultInfo as testResultInfo
import bridgeathletic.user as user
import bridgeathletic.workout as workout
import bridgeathletic.workoutInfo as workoutInfo

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword as CallTestCaseKeyword
import com.kms.katalon.core.main.TestResult
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.switchToWindowIndex(1)

TestObject testObj = new TestObject()

testObj.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@aria-label = "Workout Dialog"]//div[contains(@ng-repeat, "block in workout.data")][10]/div[@class="block-exercise ng-scope"][2]/div[@ng-repeat="blockSet in exercise.orderSets track by $index"][1]', true)

String test = WebUI.getText(testObj)

List<String> testList = test.split('\n')

List<Integer> resultList = []

for (int i = 0; i < testList.size(); i = i + 1){
	
	if (testList[i].contains(':')){
		
		
		
	}
	
}

println testList[2]
