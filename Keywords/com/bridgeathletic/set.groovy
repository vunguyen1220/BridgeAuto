package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class set {

	int setIndex

	String setType

	List<Integer> setValue = []

	boolean hasRest

	int restValue

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def getAllSetsInfo (){

		List<set> setList = []

		int setNumbers = driver.findElements(By.xpath('id("exercise-content")//div[@class="single-row ng-scope"]')).size()

		for (int i = 1; i <= setNumbers; i = i + 1){

			setList.add(getSetInfo(i))
		}

		return setList
	}

	@Keyword
	def getSetInfo (int index){

		Object setInfo = new set()

		String setText = 'id("exercise-content")//div[@class="single-row ng-scope"]['+ index +']'

		setInfo.setIndex = index

		TestObject setTypeObject = new TestObject()

		setTypeObject.addProperty('xpath', ConditionType.EQUALS, setText + '//button[contains(@class, "md-button type")]', true)

		String setTypeText = WebUI.getText(setTypeObject)

		setInfo.setType = setTypeText

		TestObject setRestObject = new TestObject()

		setRestObject.addProperty('xpath', ConditionType.EQUALS, setText + '//md-switch[@ng-model="blockSet.hasRestBoolean"]', true)

		String restText = WebUI.getAttribute(setRestObject, 'class')

		setInfo.hasRest = restText.contains('md-checked')

		if (setInfo.hasRest == true){

			String restValueText = setText + '//duration-input[@model="blockSet.restTime"]/input'

			TestObject restValueObject = new TestObject()

			restValueObject.addProperty('xpath', ConditionType.EQUALS, restValueText, true)

			String valueText = WebUI.getAttribute(restValueObject, 'value')

			if (valueText == ''){

				setInfo.restValue = 0
			}

			else {

				setInfo.restValue = Integer.parseInt(valueText.split(':')[0])*60 + Integer.parseInt(valueText.split(':')[1])
			}
		}

		int paramNumbers = driver.findElements(By.xpath(setText + '//div[contains(@class,"single-value relative ng-scope")]/input')).size()

		for (int i = 1; i <= paramNumbers; i = i + 1){

			if (driver.findElements(By.xpath(setText +  '//div[contains(@class,"single-value relative ng-scope")]['+ i +']/duration-input[@aria-hidden="false"]')).size() > 0){

				String valueText = setText +  '//div[contains(@class,"single-value relative ng-scope")]['+ i +']/duration-input[@aria-hidden="false"]/input'

				TestObject valueObject = new TestObject()

				valueObject.addProperty('xpath', ConditionType.EQUALS, valueText, true)

				valueText = WebUI.getAttribute(valueObject, 'value')

				int value = Integer.parseInt(valueText.split(':')[0])*60 + Integer.parseInt(valueText.split(':')[1])

				setInfo.setValue.add(value)
			}

			else {

				String valueText = setText +  '//div[contains(@class,"single-value relative ng-scope")]['+ i +']/input'

				TestObject valueObject = new TestObject()

				valueObject.addProperty('xpath', ConditionType.EQUALS, valueText, true)

				int value = Integer.parseInt(WebUI.getAttribute(valueObject, 'value'))

				setInfo.setValue.add(value)
			}
		}

		return setInfo
	}

	@Keyword
	def setValueForSet(int setIndex, List<Integer> setValue){

		String setText = 'id("exercise-content")//div[@class="single-row ng-scope"]['+ setIndex +']'

		int paramNumbers = driver.findElements(By.xpath(setText + '//div[contains(@class,"single-value relative ng-scope")]/input')).size()

		for (int i = 1; i <= paramNumbers; i = i + 1){

			String valueText = setText +  '//div[contains(@class,"single-value relative ng-scope")]['+ i +']/input'

			TestObject valueObject = new TestObject()

			valueObject.addProperty('xpath', ConditionType.EQUALS, valueText, true)

			String isTime = WebUI.getAttribute(valueObject, 'aria-hidden')

			if (isTime == 'false'){

				WebUI.setText(valueObject, Integer.toString(setValue[i-1]))
			}

			else {

				WebUI.removeObjectProperty(valueObject, 'xpath')

				valueObject.addProperty('xpath', ConditionType.EQUALS, setText + '//div[contains(@class,"single-value relative ng-scope")]['+ i +']/duration-input/input', true)

				WebUI.sendKeys(valueObject, Integer.toString(setValue[i-1]))
			}
		}
	}
}
