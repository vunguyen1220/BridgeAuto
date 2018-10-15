import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

String selectOption = option

List<String> currentData = []

if (selectOption == 'Week') {

	currentData = getCurrentWeek()

}

else if (selectOption == 'Month') {

	currentData = getCurrentMonth()

}

else if (selectOption == 'event1') {

	currentData = getEvent1()

}

else if (selectOption == 'event2') {

	currentData = getEvent2()

}

return currentData

def getCurrentWeek() {

	Calendar cal = Calendar.getInstance()

	SimpleDateFormat longFormat = new SimpleDateFormat('MMM dd yyyy')

	SimpleDateFormat shortFormat = new SimpleDateFormat('MMM dd')

	cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)) + 1)

	String longStartDate = longFormat.format(cal.getTime())

	String shortStartDate = shortFormat.format(cal.getTime())

	cal = Calendar.getInstance()

	cal.add(Calendar.DATE, Calendar.DAY_OF_WEEK - cal.get(Calendar.DAY_OF_WEEK))

	String longEndDate = longFormat.format(cal.getTime())

	String shortEndDate = shortFormat.format(cal.getTime())

	List<String> currentWeek = []

	currentWeek.add((shortStartDate + ' - ') + shortEndDate)

	currentWeek.add((longStartDate + ' - ') + longEndDate)

	System.out.println(currentWeek)

	return currentWeek
}

def getCurrentMonth() {

	Calendar cal = Calendar.getInstance()

	SimpleDateFormat longFormat = new SimpleDateFormat('MMM dd yyyy')

	SimpleDateFormat shortFormat = new SimpleDateFormat('MMM dd')

	cal.add(Calendar.DATE, - cal.get(Calendar.DAY_OF_MONTH) + 1)
	
	String longStartDate = longFormat.format(cal.getTime())

	String shortStartDate = shortFormat.format(cal.getTime())

	cal = Calendar.getInstance()

	cal.add(Calendar.DATE, - cal.get(Calendar.DAY_OF_MONTH) + cal.getActualMaximum(Calendar.DAY_OF_MONTH))

	String longEndDate = longFormat.format(cal.getTime())

	String shortEndDate = shortFormat.format(cal.getTime())

	List<String> currentMonth = []

	currentMonth.add((shortStartDate + ' - ') + shortEndDate)

	currentMonth.add((longStartDate + ' - ') + longEndDate)

	System.out.println(currentMonth)

	return currentMonth

}

def getEvent1() {

	Calendar cal = Calendar.getInstance()

	SimpleDateFormat format = new SimpleDateFormat('MM/dd/yyyy')

	cal.add(Calendar.DATE, 1)

	String startDate = format.format(cal.getTime())

	cal = Calendar.getInstance()

	cal.add(Calendar.DATE, Calendar.DAY_OF_WEEK - cal.get(Calendar.DAY_OF_WEEK) + 7)

	String endDate = format.format(cal.getTime())

	List<String> eventDate = []

	eventDate.add(startDate)

	eventDate.add(endDate)

	System.out.println(eventDate)

	return eventDate

}

def getEvent2() {

	int randomNumber = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(14)

	Calendar cal = Calendar.getInstance()

	SimpleDateFormat format = new SimpleDateFormat('MM/dd/yyyy')

	cal.add(Calendar.DATE, randomNumber)

	String startDate = format.format(cal.getTime())

	String endDate = format.format(cal.getTime())

	List<String> eventDate = []

	eventDate.add(startDate)

	eventDate.add(endDate)

	System.out.println(eventDate)

	return eventDate

}