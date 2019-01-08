package bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.ThreadLocalRandom

import org.apache.commons.lang.WordUtils
import org.junit.After
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor
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

public class random {

	static WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def getRandomNumber(int maxNumber){

		int randomNumber

		if(maxNumber == 0){

			randomNumber = 0
		}

		else {

			randomNumber = ThreadLocalRandom.current().nextInt(1, maxNumber + 1)
		}

		System.out.println(randomNumber)

		return randomNumber
	}

	@Keyword
	def getRandomNumberIntoRange(int minNumber, int maxNumber){

		int randomNumber

		if(minNumber == maxNumber){

			randomNumber = maxNumber
		}

		else {

			randomNumber = ThreadLocalRandom.current().nextInt(minNumber, maxNumber + 1)
		}

		return randomNumber
	}

	@Keyword
	def getRandomListNumber (int minNumber, int maxNumber, int listSize){

		List<Integer> listNumber = []

		while (listNumber.size() < listSize){

			listNumber.add(getRandomNumberIntoRange(minNumber, maxNumber))
		}

		return listNumber
	}

	@Keyword
	def public getRandomProgram(ArrayList<String> allProgram){

		int randomNumber = ThreadLocalRandom.current().nextInt(0, allProgram.size)

		System.out.println('Random Number: ' + randomNumber)

		String randomProgramName = allProgram[randomNumber]

		System.out.println('Random Program Name: ' + randomProgramName)

		TestObject randomProgram = new TestObject()

		randomProgram.addProperty('text', ConditionType.CONTAINS, randomProgramName, true)

		randomProgram.addProperty('tag', ConditionType.EQUALS, 'strong', true)

		return randomProgram
	}

	@Keyword
	def public getRandomBlock (int totalBlockNumbers){

		int randomNumber = ThreadLocalRandom.current().nextInt(1, totalBlockNumbers + 1)

		System.out.println('Random Block Number: ' + randomNumber)

		TestObject randomBlock = new TestObject()

		randomBlock.addProperty('xpath', ConditionType.CONTAINS, '(//*[@class = "block-name draggable"])['+ randomNumber +']', true)

		return randomBlock
	}

	@Keyword
	def public getRandomName(String startName){

		Calendar calendar = Calendar.getInstance()

		String calendarRandom = calendar.getTime().toString()

		String randomName = startName + calendarRandom

		return randomName
	}

	@Keyword
	def public selectRandomTeam(){

		int teamNumber = driver.findElements(By.xpath('id("assign-athletes")/div[@class="assign-atheletes"]/div[@class="team-select"]/select[contains(@class,"ng-valid")]/option[@class="ng-binding ng-scope"]')).size()

		int randomNumber = ThreadLocalRandom.current().nextInt(1, teamNumber + 1)

		System.out.println(randomNumber)

		TestObject randomTeam = new TestObject()

		randomTeam.addProperty('xpath', ConditionType.EQUALS, 'id("assign-athletes")/div[@class="assign-atheletes"]/div[@class="team-select"]/select[contains(@class,"ng-valid")]/option[@class="ng-binding ng-scope"][' + randomNumber + ']', true)

		return randomTeam
	}

	@Keyword
	def public selectRandomMember(int totalMembers){

		int randomMemberNumberSelect = ThreadLocalRandom.current().nextInt(1, totalMembers + 1)

		System.out.println('Member Random Numbers: ' + randomMemberNumberSelect)

		ArrayList<String> a = new ArrayList<String>()

		for (int i = 1; i <= totalMembers; i++){

			a.add(i)
		}

		Collections.shuffle(a)

		System.out.println(a)

		for (int i = 0; i < randomMemberNumberSelect; i++){

			TestObject randomMemberSelect = new TestObject()

			randomMemberSelect.addProperty('xpath', ConditionType.EQUALS, '(//span[text() = "Add"])['+ a[i] +']', true)

			WebUI.click(randomMemberSelect)
		}
	}

	@Keyword
	def public selectRandomDay(int dayNumber){

		List<Integer> w = [1, 2, 3, 4, 5, 6, 7]

		Collections.shuffle(w)

		for (int i = 0; i < dayNumber; i++){

			TestObject selectDay = new TestObject()

			selectDay.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-repeat="day in current.daysInWeek"]['+ w[i] +']', true)

			WebUI.click(selectDay)

			WebUI.delay(1)
		}
	}

	@Keyword
	def public selectRandomWeek(){

		int weekNumber = driver.findElements(By.xpath('//input[@name="day-select"]')).size()

		int randomNumber = ThreadLocalRandom.current().nextInt(1, weekNumber + 1)

		System.out.println(randomNumber)

		driver.findElement(By.xpath('//input[@name="day-select"]')).click()
	}

	@Keyword
	def public selectRandomNewBlock(){

		int newBlockNumber = driver.findElements(By.xpath('//*[@class = "add-block-btns"]')).size()

		System.out.println(newBlockNumber)

		int randomNumber = 3

		System.out.println(randomNumber)

		TestObject randomNewBlock = new TestObject()

		TestObject Week1 = new TestObject()

		randomNewBlock.addProperty('xpath', ConditionType.EQUALS, '(//*[@class = "add-block-btns"])['+ randomNumber +']', true)

		Week1.addProperty('xpath', ConditionType.EQUALS, "(//*[@class = 'week-name'])[1]", true)

		int WeekX = driver.findElement(By.xpath("(//*[@class = 'week-name'])[1]")).getLocation().getX()

		int WeekY = driver.findElement(By.xpath("(//*[@class = 'week-name'])[1]")).getLocation().getY()

		WebUI.scrollToElement(Week1, 2)

		System.out.println(WeekX+'  '+WeekY)

		WebUI.delay(1)

		WebUI.click(randomNewBlock)
	}
}
