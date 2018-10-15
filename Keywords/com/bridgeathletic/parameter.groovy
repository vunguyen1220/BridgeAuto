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

public class parameter {

	int parameterIndex

	String parameterName

	String parameterUnit

	String weightType

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def getAllParametersInfo(){

		List<parameter> paramList = []

		int paramNumbers = driver.findElements(By.xpath('//div[@class="box-params"]//div[@ng-repeat="name in variables.paramName"]')).size()

		for (int i = 1; i <= paramNumbers; i = i + 1){

			paramList.add(getParameterInfo(i))
		}

		return paramList
	}

	@Keyword
	def getParameterInfo(int index){

		Object paramInfo = new parameter()

		String paramText = '//div[@class="box-params"]//div[@ng-repeat="name in variables.paramName"]['+ index +']'

		paramInfo.parameterIndex = index

		String paramNameText = paramText + '/span[@class="title ng-binding"]'

		TestObject paramNameObject = new TestObject()

		paramNameObject.addProperty('xpath', ConditionType.EQUALS, paramNameText, true)

		paramInfo.parameterName = WebUI.getText(paramNameObject)

		if (paramInfo.parameterName != 'Weight' && paramInfo.parameterName != 'Time' && driver.findElements(By.xpath(paramText + '/span[@class="unit ng-scope"]//span')).size() > 0){

			String paramUnitText = paramText + '/span[@class="unit ng-scope"]//span'

			TestObject paramUnitObject = new TestObject()

			paramUnitObject.addProperty('xpath', ConditionType.EQUALS, paramUnitText, true)

			paramInfo.parameterUnit = WebUI.getText(paramUnitObject)
		}

		else if (paramInfo.parameterName == 'Weight') {

			String paramUnitText = paramText + '/span[@class="title ng-binding"]/span'

			TestObject paramUnitObject = new TestObject()

			paramUnitObject.addProperty('xpath', ConditionType.EQUALS, paramUnitText, true)

			paramInfo.parameterUnit = WebUI.getText(paramUnitObject).trim()

			String weightTypeText = paramText + '/span[@class="unit ng-scope"]//span'

			TestObject weightTypeObject = new TestObject()

			paramUnitObject.addProperty('xpath', ConditionType.EQUALS, weightTypeText, true)

			paramInfo.weightType = WebUI.getText(paramUnitObject)
		}

		return paramInfo
	}

	@Keyword
	def getRandomParameterList(int paramNumbers){

		List<String> paramList = ['Reps', 'Weight', 'Time', 'Distance', 'Height', 'Velocity', 'Power', 'Force', 'Heart Rate', 'Heart Rate Zone', 'Calories', 'RPE']

		Collections.shuffle(paramList)

		List<String> resultParamList = paramList.subList(0, paramNumbers)

		return resultParamList
	}
}
