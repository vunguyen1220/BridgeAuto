package bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Point as Point

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class click {

	@Keyword
	def clickDateObjectFromXpath (String xp){
		
		WebDriver driver = DriverFactory.getWebDriver()

		int dateInCurrentWeek = driver.findElements(By.xpath(xp + '/preceding-sibling::td')).size() + 1
		
		int beforeWeekNumbers = driver.findElements(By.xpath(xp + '/parent::tr/preceding-sibling::tr')).size()
		
		int dateNumber = beforeWeekNumbers*7 + dateInCurrentWeek
		
		TestObject dateObject = new TestObject()
		
		dateObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="fc-content"]//div[@class="fc-event-container"]/div[contains(@class, "fc-event")]['+ dateNumber +']', true)
		
		WebUI.click(dateObject)
		
		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
		
	}
}
