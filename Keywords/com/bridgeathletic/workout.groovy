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

public class workout {

	String workoutName

	String workoutNote

	int workoutIndex

	String startWeek

	String endWeek

	int workoutDuration

	int blockNumbers

	List<block> blockList = []

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def printWorkout (workout wk){

		println 'Workout Name: ' + wk.workoutName

		println 'Workout Note: ' + wk.workoutNote

		println 'Workout Duration: ' + wk.workoutDuration

		println 'Workout Index: ' + wk.workoutIndex

		println 'Block Numbers: ' + wk.blockNumbers

		println 'Block List Size: ' + wk.blockList.size()
	}

	@Keyword
	def scrollWorkoutToPresent(int weekIndex, int workoutIndex){

		String selectedWorkoutText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']'

		boolean flag = false

		while (flag == false){

			flag = false

			String attributeValue = driver.findElement(By.xpath(selectedWorkoutText)).getAttribute('class')

			if (attributeValue.contains('hidden-day') == false){

				flag = true
			}

			else {

				if (attributeValue.contains('the-day-after') == true){

					driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//button[contains(@class,"next-btn")]')).click()

					WebUI.delay(1)
				}

				else {

					driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//button[contains(@class,"prev-btn")]')).click()

					WebUI.delay(1)
				}
			}
		}
	}

	@Keyword
	def expandWeek (int weekIndex){

		String headerWeekText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']/header/button[@class="collapse-btn md-button md-default-theme active"]'

		if (driver.findElements(By.xpath(headerWeekText)).size() > 0){

			driver.findElement(By.xpath(headerWeekText)).click()

			TestObject loading = new TestObject()

			loading.addProperty('xpath', ConditionType.EQUALS, '//div[@class="block-ui ng-binding"]/div[@class="md-dialog-container ng-scope"]', true)

			WebUI.waitForElementNotPresent(loading, 30)
		}
	}

	@Keyword
	def getWorkoutListInWeek (int weekIndex){

		String startWeekText

		String endWeekText

		if (driver.findElements(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//span[@ng-if = "!isKitOfPart && data.linked.phaseWeeks[weekId].duration"]')).size() > 0){

			WebElement element = driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//span[@ng-if = "!isKitOfPart && data.linked.phaseWeeks[weekId].duration"]'))

			((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: none;'", element)

			TestObject weekTextObject = new TestObject()

			weekTextObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//span[@ng-if = "!isKitOfPart && data.linked.phaseWeeks[weekId].duration"]', true)

			String weekDurationText = WebUI.getText(weekTextObject).trim()

			startWeekText = weekDurationText.split('- ')[0]

			endWeekText = weekDurationText.split('- ')[1]
		}

		List<workout> workoutList = []

		String weekText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']'

		if (driver.findElements(By.xpath(weekText)).size() <= 0){

			return false
		}

		if (driver.findElements(By.xpath(weekText + '/header/button[@class="collapse-btn md-button md-default-theme active"]')).size() > 0){

			driver.findElement(By.xpath(weekText + '/header/button[@class="collapse-btn md-button md-default-theme active"]')).click()

			TestObject loading = new TestObject()

			loading.addProperty('xpath', ConditionType.EQUALS, '//div[@class="block-ui ng-binding"]/div[@class="md-dialog-container ng-scope"]', true)

			WebUI.waitForElementNotPresent(loading, 30)
		}

		int workoutNumbers = driver.findElements(By.xpath(weekText + '//div[contains(@class,"day ng-scope")]')).size()

		for (int i = 1; i <= workoutNumbers; i = i + 1){

			scrollWorkoutToPresent(weekIndex, i)

			Object selectedWorkout = new workout()

			selectedWorkout.setStartWeek(startWeekText.substring(1))

			selectedWorkout.setEndWeek(endWeekText.substring(0, endWeekText.length() - 1))

			WebElement element = driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ i +']/header'))

			((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

			String workoutName = driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ i +']/header/span')).getText()

			selectedWorkout.setWorkoutName(workoutName)

			driver.findElement(By.xpath('//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ i +']/header/span')).click()

			WebUI.delay(1)

			String workoutNote = driver.findElement(By.xpath('//div[@class="current-phase ng-scope"]//div[@class="notes ng-scope"]/p')).getText()

			selectedWorkout.setWorkoutNote(workoutNote)

			selectedWorkout.setWorkoutIndex(i)

			int workoutDuration = Integer.parseInt(driver.findElement(By.xpath('//div[@class="current-phase ng-scope"]//span[contains(@class,"duration-color")]')).getText().trim())

			selectedWorkout.setWorkoutDuration(workoutDuration)

			int blockNumbers = driver.findElements(By.xpath('//div[@id="workout-content"]//div[@class="workout-header ng-scope"]')).size()

			selectedWorkout.setBlockNumbers(blockNumbers)

			workoutList.add(selectedWorkout)
		}

		return workoutList
	}

	@Keyword
	def clickCloneWorkoutButtonByIndex (int weekIndex, int workoutIndex){

		String headerText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']/header'

		String cloneText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']/header//i[@class="fa fa-files-o ng-scope"]'

		TestObject header = new TestObject()

		header.addProperty('xpath', ConditionType.EQUALS, headerText, true)

		WebUI.mouseOver(header)

		TestObject clone = new TestObject()

		clone.addProperty('xpath', ConditionType.EQUALS, cloneText, true)

		WebUI.click(clone)
	}

	@Keyword
	def clickTreeDotButtonWorkoutByIndex (int weekIndex, int workoutIndex){

		String headerText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']/header'

		String cloneText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']/header//i[@class="custom-icon icon-three-dots"]'

		TestObject header = new TestObject()

		header.addProperty('xpath', ConditionType.EQUALS, headerText, true)

		WebUI.mouseOver(header)

		TestObject treeDot = new TestObject()

		treeDot.addProperty('xpath', ConditionType.EQUALS, cloneText, true)

		WebUI.click(treeDot)
	}

	@Keyword
	def moveWorkout (int fromWeekIndex, int fromWorkoutIndex, int toWeekIndex, int toWorkoutIndex){

		expandWeek(fromWeekIndex)

		expandWeek(toWeekIndex)

		String fromHeaderText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ fromWeekIndex +']//div[contains(@class,"day ng-scope")]['+ fromWorkoutIndex +']/header'

		TestObject fromHeaderObject = new TestObject()

		fromHeaderObject.addProperty('xpath', ConditionType.EQUALS, fromHeaderText, true)

		String toHeaderText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ toWeekIndex +']//div[contains(@class,"day ng-scope")]['+ toWorkoutIndex +']/header'

		TestObject toHeaderObject = new TestObject()

		toHeaderObject.addProperty('xpath', ConditionType.EQUALS, toHeaderText, true)

		WebUI.dragAndDropToObject(fromHeaderObject, toHeaderObject)
	}
}
