import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.concurrent.ThreadLocalRandom as ThreadLocalRandom

import bridgeathletic.block
import bridgeathletic.exercise
import bridgeathletic.program
import bridgeathletic.workout
import bridgeathletic.phase
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
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
import org.apache.commons.lang.WordUtils as WordUtils
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByCssSelector as ByCssSelector
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Done/Commons/Open App'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [var_email:GlobalVariable.gl_adminEmail, var_password:GlobalVariable.gl_adminPassword])

WebUI.callTestCase(findTestCase('Done/Commons/Select Organization'), [var_orgNumber:45])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Filter Programs'), [var_byTeam:'Swimming', var_byType:['Assigned']])

int totalProgramNumbers = CustomKeywords.'bridgeathletic.program.getTotalProgramNumbers'()

int rd

boolean flag = false

Object selectProgramInfo = new program()

while (flag == false){

	rd = CustomKeywords.'bridgeathletic.random.getRandomNumberIntoRange'(1, totalProgramNumbers)

	CustomKeywords.'bridgeathletic.program.selectProgramInLibraryByIndex'(rd)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	selectProgramInfo = CustomKeywords.'bridgeathletic.program.getAssignedProgramInfoInDetail'()

	if (selectProgramInfo.currentPhase == ''){

		WebUI.click(findTestObject('Breadcrumbs/button_Programs'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	}

	else {

		flag = true

	}

}

WebUI.callTestCase(findTestCase('Done/Commons/Open App In New Tab'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Select Organization'), [var_orgNumber:45])

WebUI.callTestCase(findTestCase('Done/Commons/Navigate To User Detail'), [var_userName:selectProgramInfo.memberList[0].split('. ')[1]])

WebUI.switchToWindowIndex(0)

Object currentPhase = new phase()

int currentPhaseIndex = CustomKeywords.'bridgeathletic.phase.getPhaseIndexByName'(selectProgramInfo.currentPhase)

currentPhase = CustomKeywords.'bridgeathletic.phase.getPhaseInfoInDetail'(currentPhaseIndex)

TestObject editCurrentPhaseObject = CustomKeywords.'bridgeathletic.phase.getEditPhaseObjectByName'(selectProgramInfo.currentPhase)

WebUI.click(editCurrentPhaseObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.delay(2)

String blockName = CustomKeywords.'bridgeathletic.random.getRandomName'('Warmup--New ')

CustomKeywords.'bridgeathletic.block.addNewBlock'(1, 1, blockName)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Close First Change Dialog'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Add New Exercise And Set Value'), [var_exerciseName:'Barbell Bench Press', var_blockName:blockName, var_paramNumbers:2, var_setNumbers:3])

List<exercise> exerciseList = []

exerciseList.add(CustomKeywords.'bridgeathletic.exercise.getExerciseInfo'())

WebUI.callTestCase(findTestCase('Done/Commons/Add New Exercise And Set Value'), [var_exerciseName:'Barbell Back Squat', var_blockName:blockName, var_paramNumbers:3, var_setNumbers:4])

exerciseList.add(CustomKeywords.'bridgeathletic.exercise.getExerciseInfo'())

WebUI.callTestCase(findTestCase('Done/Commons/Add New Exercise And Set Value'), [var_exerciseName:CustomKeywords.'bridgeathletic.random.getRandomName'('Barbell '), var_blockName:blockName, var_paramNumbers:2, var_setNumbers:3])

WebUI.setText(findTestObject('Phase Builder Page/Left Panel/input_Exercise Note'), 'This is my note for my exercise')

exerciseList.add(CustomKeywords.'bridgeathletic.exercise.getExerciseInfo'())

WebUI.callTestCase(findTestCase('Done/Commons/Deliver To Athletes'), [:])

WebUI.switchToWindowIndex(1)

WebUI.refresh()

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Commons/Program Calendar Popup/header_Current Date'), 30)

List<Integer> dateList = []

dateList = CustomKeywords.'bridgeathletic.convert.convertFromDateStringToDateList'(currentPhase.startDate, 'MMM dd, yyyy')

WebUI.callTestCase(findTestCase('Done/Commons/Select Date In Calendar/Athlete Calendar/Select Date In Athlete Calendar'), [var_day:dateList[0], var_month:dateList[1], var_year:dateList[2]])





