import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())


RunConfiguration.setExecutionSettingFile('C:\\Users\\VuNA\\AppData\\Local\\Temp\\Katalon\\Test Cases\\Test\\20181019_162828\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCaseRawScript(
'''import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
import org.junit.Test as Test
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.bridgeathletic.attendanceInfo as attendanceInfo
import com.bridgeathletic.blockInfo as blockInfo
import com.bridgeathletic.eventInfo as eventInfo
import com.bridgeathletic.phase as phase
import com.bridgeathletic.program as program
import com.bridgeathletic.exercise as exercise
import com.bridgeathletic.testResultInfo as testResultInfo
import com.bridgeathletic.user as user
import com.bridgeathletic.workout as workout
import com.bridgeathletic.workoutInfo as workoutInfo
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword as CallTestCaseKeyword
import com.kms.katalon.core.main.TestResult as TestResult
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

WebUI.switchToWindowIndex(0)

List<Integer> testList = []

testList = CustomKeywords.'com.bridgeathletic.convert.convertFromDateStringToDateList'('aug 21, 2018', 'MMM dd, yyyy')

println(testList)

WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Athlete Calendar/Select Date In Athlete Calendar'), 
    [('var_day') : testList[0], ('var_month') : testList[1], ('var_year') : testList[2]])

''', 'Test Cases/Test', new TestCaseBinding('Test Cases/Test',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
