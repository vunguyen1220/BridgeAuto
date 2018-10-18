package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.WebDriver

import com.gargoylesoftware.htmlunit.Cache
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

public class convert {

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def convertFromDate(String currentDate, String formatCurrentDate, String formatExpectDate, int convertDate, String dateType){

		SimpleDateFormat expectDateFormat = new SimpleDateFormat(formatExpectDate)

		Calendar cal = Calendar.getInstance()

		if (currentDate != null){

			SimpleDateFormat currentDateFormat = new SimpleDateFormat(formatCurrentDate)

			cal.setTime(currentDateFormat.parse(currentDate))
		}

		if (dateType == 'Date'){

			cal.add(Calendar.DATE, convertDate)
		}
		else if (dateType == 'Month'){

			cal.add(Calendar.MONTH, convertDate)
		}
		else if (dateType == 'Year'){

			cal.add(Calendar.YEAR, convertDate)
		}

		String expectDate = expectDateFormat.format(cal.getTime())

		println 'Expect Date: ' + expectDate

		return expectDate
	}

	@Keyword
	public def convertFormatDate(String currentDate, String formatCurrentDate, String formatExpectDate){

		Calendar cal = Calendar.getInstance()

		SimpleDateFormat currentDateFormat = new SimpleDateFormat(formatCurrentDate)

		SimpleDateFormat expectDateFormat = new SimpleDateFormat(formatExpectDate)

		cal.setTime(currentDateFormat.parse(currentDate))

		String expectDate = expectDateFormat.format(cal.getTime())

		println 'Expect Date: ' + expectDate

		return expectDate
	}

	@Keyword
	public def convertMonthFromStringToInteger(String month, String format){

		Date date = Date.parse(format, month)

		int newMonth = date.format('M') as int

		return newMonth
	}

	@Keyword
	public def convertMonthFromIntToString (int month, String format){

		String monthText = Integer.toString(month)

		Date date = Date.parse('M', monthText)

		String newMonth = date.format(format)

		return newMonth
	}
	
	@Keyword
	def convertFromDateStringToDateList (String date, String format){
		
		List<Integer> dateList = []
		
		String dateString = convertFormatDate(date, format, 'dd/MM/yyyy')
		
		List<String> dateStringList = dateString.split('/')
		
		println dateStringList
		
		for (int i = 0; i < dateStringList.size(); i = i + 1) {
			
			println i
			
			dateList.add(Integer.parseInt(dateStringList[i]))
			
			println dateList
			
		}
		
		return dateList
		
	}
}
