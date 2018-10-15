package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DateFormat
import java.text.SimpleDateFormat

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
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class calendar {

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def selectDateInCalendar (String date){

		List<String> expectDateList = date.replaceAll(',', '').split(' ')

		String expectMonthText = expectDateList[0]

		SimpleDateFormat monthFormat = new SimpleDateFormat('MMM')

		Date m  = monthFormat.parse(expectMonthText)

		int expectMonth = m.format('MM') as int

		String expectDate = expectDateList[1]

		int expectYear = Integer.parseInt(expectDateList[2])

		List<String> actualDateList = driver.findElement(By.xpath('id("calendar-bk")//span[@class="fc-header-title"]/h2[1]')).getText().split(' ')

		int actualYear = Integer.parseInt(actualDateList[1])

		while (actualYear != expectYear){

			if (actualYear < expectYear){

				driver.findElement(By.xpath('//span[contains(@class,"fc-button-next")]')).click()
			}
			else{

				driver.findElement(By.xpath('//span[contains(@class,"fc-button-prev")]')).click()
			}

			actualDateList = driver.findElement(By.xpath('id("calendar-bk")//span[@class="fc-header-title"]/h2[1]')).getText().split(' ')

			actualYear = Integer.parseInt(actualDateList[1])
		}

		m = monthFormat.parse(actualDateList[0])

		int actualMonth = m.format('MM') as int

		while (actualMonth != expectMonth){

			if (actualMonth < expectMonth){

				driver.findElement(By.xpath('//span[contains(@class,"fc-button-next")]')).click()
			}
			else{

				driver.findElement(By.xpath('//span[contains(@class,"fc-button-prev")]')).click()
			}

			actualDateList = driver.findElement(By.xpath('id("calendar-bk")//span[@class="fc-header-title"]/h2[1]')).getText().split(' ')

			m = monthFormat.parse(actualDateList[0])

			actualMonth = m.format('MM') as int
		}
	}
}
