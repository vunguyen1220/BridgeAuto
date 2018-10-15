package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class total {

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def public getTotalBlockNumbers(){

		int totalBlockNumbers = driver.findElements(By.xpath('//*[@class = "block-name draggable"]')).size()

		return totalBlockNumbers
	}

	@Keyword
	def getItemSize(String xpath){

		int size = driver.findElements(By.xpath(xpath)).size()

		System.out.println('Item size = ' + size)

		return size
	}

	@Keyword
	def getAllProgram(){

		ArrayList<String> AllProgram = new ArrayList<String>()

		int i = 1

		int ProgramNumbers = Integer.parseInt(WebUI.getText(findTestObject('Commons/span_ProgramNumbers')))

		while(AllProgram.size < ProgramNumbers){

			WebElement element = driver.findElement(By.xpath('(//div[contains(@class,"ng-isolate-scope visible")]/div[@class="item-container"]/ul[@class="ng-scope"]/li[contains(@class,"ng-scope is-single")]/a[@class="ng-scope"]/strong[@class="ng-binding"])['+ i +']'))

			((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

			String programName = (driver.findElement(By.xpath('(//div[contains(@class,"ng-isolate-scope visible")]/div[@class="item-container"]/ul[@class="ng-scope"]/li[contains(@class,"ng-scope is-single")]/a[@class="ng-scope"]/strong[@class="ng-binding"])['+ i +']')).getText()).trim()

			AllProgram.add(programName)

			i = i + 1

			if (i > 50){

				WebUI.click(findTestObject('Commons/div_sub-title_right'))

				WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Builder-Add Template Block/span_Processing...'), 30)

				WebUI.delay(2)

				i = 1
			}
		}

		System.out.println('Program size: ' + AllProgram.size + '\nProgram Name List: ' + AllProgram)

		return AllProgram
	}

	@Keyword
	def getTotalAthleteMembers(){

		int totalAthleteMembers

		if (driver.findElements(By.xpath('(//div[@class="athlete-select"]/h3[@class="ng-binding"])[1]')).size() == 0){

			totalAthleteMembers = 0
		}

		else{

			totalAthleteMembers = Integer.parseInt((driver.findElement(By.xpath('(//div[@class="athlete-select"]/h3[@class="ng-binding"])[1]')).getText()).substring(0, 1))
		}

		System.out.println('Total Athlete Members: ' + totalAthleteMembers)

		return totalAthleteMembers
	}
}
