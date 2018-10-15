package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DateFormat
import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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

public class phase {

	int phaseIndex

	String phaseName

	String phaseDescription

	int weekNumbers

	int dayPerWeek

	String startDate

	String endDate

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def printPhaseInfo (phase ph){

		println 'Phase Index:' + ph.phaseIndex

		println 'Phase Name: ' + ph.phaseName

		println 'Phase Description: ' + ph.phaseDescription

		println 'Week Numbers: ' + ph.weekNumbers

		println 'Day/Week: ' + ph.dayPerWeek

		println 'Start Date: ' + ph.startDate

		println 'End Date: ' + ph.endDate
	}

	@Keyword
	def getTotalPhaseNumbersInDetail(){

		List<String> textPhase = driver.findElement(By.xpath('id("phase-in-program")/h2')).getText().split(" \n")

		int phaseNumbers = Integer.parseInt(textPhase[0])

		return phaseNumbers
	}

	@Keyword
	def getPhaseInfoInDetail (int phaseNumber){

		Object phaseInfo = new phase()

		phaseInfo.setPhaseIndex(phaseNumber)

		WebElement element = driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[1]/h3'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

		String phaseName = driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[1]/h3')).getText()

		phaseInfo.setPhaseName(phaseName)

		String phaseDescription = driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[4]/b')).getText()

		phaseInfo.setPhaseDescription(phaseDescription)

		int weekNumber = Integer.parseInt(((driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[1]')).getText()).split("  "))[0])

		phaseInfo.setWeekNumbers(weekNumber)

		int dayPerWeek = Integer.parseInt(((driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[2]')).getText()).split("  "))[0])

		phaseInfo.setDayPerWeek(dayPerWeek)

		if (driver.findElements(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[3]')).size() > 0 && (driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[3]')).getText()).length() > 2) {

			element = driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[3]'))

			((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

			String dateText = driver.findElement(By.xpath('id("phases-container")/div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ phaseNumber +']/div/div[2]/div/span[3]')).getText()

			String startDate = dateText.substring(0, 6)

			String endDate = dateText.substring(9, 15)

			int startYear = Integer.parseInt(dateText.substring(17, 21))

			int endYear = startYear

			def m = Date.parse('MMM', startDate.substring(0, 3))

			int startMonth = m.format('MM') as int

			m = Date.parse('MMM', endDate.substring(0, 3))

			int endMonth = m.format('MM') as int

			if (startMonth > endMonth){

				startYear = endYear - 1
			}

			phaseInfo.setStartDate(startDate + ', ' + startYear)

			phaseInfo.setEndDate(endDate + ', ' + endYear)
		}

		return phaseInfo
	}

	@Keyword
	def getPhaseList (int totalPhaseNumbers){

		List<phase> phaseList = []

		for (int i = 1; i <= totalPhaseNumbers; i = i + 1){

			phaseList.add(getPhaseInfoInDetail(i))
		}

		return phaseList
	}

	@Keyword
	def getEditPhaseObjectByName (String phaseName){

		TestObject editPhaseObject = new TestObject()

		editPhaseObject.addProperty('xpath', ConditionType.EQUALS, '//h3[text()="'+ phaseName +'"]/ancestor::div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]//i[@class="fa fa-pencil"]', true)

		return editPhaseObject
	}

	@Keyword
	def getEditPhaseObjectByIndex (int index){

		TestObject editPhaseObject = new TestObject()

		editPhaseObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ index +']//i[@class="fa fa-pencil"]', true)

		return editPhaseObject
	}

	@Keyword
	def getAnalyzePhaseObjectByName (String phaseName){

		TestObject analyzePhaseObject = new TestObject()

		analyzePhaseObject.addProperty('xpath', ConditionType.EQUALS, '//h3[text()="'+ phaseName +'"]/ancestor::div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]//i[@class="fa fa-bar-chart"]', true)

		return analyzePhaseObject
	}

	@Keyword
	def getAnalyzePhaseObjectByIndex (int index){

		TestObject editPhaseObject = new TestObject()

		editPhaseObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ index +']//i[@class="fa fa-bar-chart"]', true)

		return editPhaseObject
	}

	@Keyword
	def getTreeDotPhaseObjectByName (String phaseName){

		TestObject treeDotPhaseObject = new TestObject()

		treeDotPhaseObject.addProperty('xpath', ConditionType.EQUALS, '//h3[text()="'+ phaseName +'"]/ancestor::div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]//i[@class="custom-icon icon-three-dots"]', true)

		return treeDotPhaseObject
	}

	@Keyword
	def getTreeDotPhaseObjectByIndex (int index){

		TestObject editPhaseObject = new TestObject()

		editPhaseObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-repeat="phaseId in data.programs.links.phases track by $index"]['+ index +']//i[@class="custom-icon icon-three-dots"]', true)

		return editPhaseObject
	}

	@Keyword
	def getPhaseIndexByName (String phaseName){

		List<phase> phaseList = getPhaseList(getTotalPhaseNumbersInDetail())

		int i = 0

		while (phaseName != phaseList[i].phaseName){

			i = i + 1
		}

		return i + 1
	}
}
