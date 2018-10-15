package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

public class exercise {

	String exerciseName

	String exerciseNote

	List<parameter> parameterList = []

	List<set> setList = []

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def getExerciseInfo (){

		Object exerciseInfo = new exercise()

		String exerciseNameText = WebUI.getText(findTestObject('Phase Builder Page/Left Panel/text_Exercise Name'))

		exerciseInfo.exerciseName = exerciseNameText

		String exerciseNoteText = WebUI.getAttribute(findTestObject('Phase Builder Page/Left Panel/input_Exercise Note'), 'value')

		exerciseInfo.exerciseNote = exerciseNoteText

		List<parameter> paramList = (new com.bridgeathletic.parameter()).getAllParametersInfo()

		exerciseInfo.parameterList = paramList

		List<set> setList = (new com.bridgeathletic.set()).getAllSetsInfo()

		exerciseInfo.setList = setList

		return exerciseInfo
	}

	@Keyword
	def addNewExerciseFromBlockName (String blockName, String exerciseName){

		String addNewExerciseText = '//header[@class = "block-name draggable"]/span[. = "'+ blockName +'"]//ancestor::div[contains(@class, "block ng-scope")]//div[@class = "exercise-search-box ng-scope"]'

		TestObject addNewExerciseButtonObject = new TestObject()

		addNewExerciseButtonObject.addProperty('xpath', ConditionType.EQUALS, addNewExerciseText, true)

		WebUI.click(addNewExerciseButtonObject)

		WebUI.waitForElementPresent(findTestObject('Phase Builder Page/Choose Exercise Popup/popup_Choose Exercise'), 30)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		WebUI.setText(findTestObject('Phase Builder Page/Choose Exercise Popup/input_Search For Exercise'), exerciseName)

		WebUI.sendKeys(findTestObject('Phase Builder Page/Choose Exercise Popup/input_Search For Exercise'), Keys.chord(Keys.ENTER))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		TestObject selectExcerciseObject = new TestObject()

		selectExcerciseObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class = "exercise-item-content"]/h5[. = "'+ exerciseName +'"]', true)

		if (WebUI.verifyElementPresent(selectExcerciseObject, 2, FailureHandling.OPTIONAL)){

			WebUI.click(selectExcerciseObject)

			WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
		}

		else {

			WebUI.click(findTestObject('Phase Builder Page/Choose Exercise Popup/button_Create New Exercise'))

			WebUI.click(findTestObject('Phase Builder Page/Choose Exercise Popup/button_Yes'))

			WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
		}
	}

	@Keyword
	def editParameterNumbers (List<String> paramName){

		if (driver.findElements(By.xpath('//section[@id="exercise-content"]//div[@class="params"]')).size() == 0){

			WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Edit Parameters'))

			WebUI.waitForElementPresent(findTestObject('Phase Builder Page/Left Panel/list_Parameters Name'), 30)
		}

		while (driver.findElements(By.xpath('id("exercise-content")//div[@class="params"]//md-checkbox[contains(@class, "md-checked")]')).size() > 0) {

			TestObject checkedParamObject = new TestObject()

			checkedParamObject.addProperty('xpath', ConditionType.EQUALS, 'id("exercise-content")//div[@class="params"]//md-checkbox[contains(@class, "md-checked")]', true)

			WebUI.click(checkedParamObject)

			WebUI.delay(1)
		}

		for (int i = 0; i < paramName.size(); i = i + 1){

			TestObject selectParamObject = new TestObject()

			selectParamObject.addProperty('xpath', ConditionType.EQUALS, '//md-checkbox[@aria-label = "'+ paramName[i] +'"]', true)

			WebUI.click(selectParamObject)

			WebUI.delay(1)
		}

		WebUI.click(findTestObject('Phase Builder Page/Left Panel/text_Exercise Name'))
	}

	@Keyword
	def editSetNumbers (int setNumbers){

		int totalSetNumbers = driver.findElements(By.xpath('id("exercise-content")//div[@class="single-row ng-scope"]')).size()

		while (totalSetNumbers != setNumbers){

			if (totalSetNumbers < setNumbers){

				WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Add New Set'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			if (totalSetNumbers > setNumbers){

				WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Remove Set'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			totalSetNumbers = driver.findElements(By.xpath('id("exercise-content")//div[@class="single-row ng-scope"]')).size()
		}
	}
}
