package bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver


public class block {

	String blockName

	String blockNote

	int exerciseNumbers

	List<exercise> exerciseList = []

	WebDriver driver = DriverFactory.getWebDriver()
	
	@Keyword
	def getBlockInfoInCalendar (int blockIndex){
		
		
		
	}

	@Keyword
	def expandBlockFromName (String blockName){

		String blockHeaderText = '//header[@class = "block-name draggable"]/span[. = "'+ blockName +'"]'

		if (driver.findElements(By.xpath(blockHeaderText + '//preceding-sibling::button/i[@class = "fa ng-scope fa-plus"]')).size() > 0){

			TestObject expandButtonObject = new TestObject()

			expandButtonObject.addProperty('xpath', ConditionType.EQUALS, blockHeaderText + '//preceding-sibling::button/i[@class = "fa ng-scope fa-plus"]', true)

			WebUI.click(expandButtonObject)
		}
	}

	@Keyword
	def addNewBlock(int weekIndex, int workoutIndex, String blockName){

		String workoutFooterText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']//div[@class="add-block-btns"]'

		String newBlockText = workoutFooterText + '//label[(text() = "+New" or . = "+New")]'

		TestObject footerObject = new TestObject()

		footerObject.addProperty('xpath', ConditionType.EQUALS, workoutFooterText, true)

		WebUI.mouseOver(footerObject)

		TestObject newBlockButtonObject = new TestObject()

		newBlockButtonObject.addProperty('xpath', ConditionType.EQUALS, newBlockText, true)

		WebUI.click(newBlockButtonObject)

		WebUI.setText(findTestObject('Phase Builder Page/Right Panel/Day View/input_Add New Block'), blockName)

		WebUI.sendKeys(findTestObject('Phase Builder Page/Right Panel/Day View/input_Add New Block'), Keys.chord(Keys.ENTER))
	}

	@Keyword
	def addTemplateBlock(int weekIndex, int workoutIndex, String blockName){

		String workoutFooterText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']//div[@class="add-block-btns"]'

		String templateBlockText = workoutFooterText + '//div[(text() = "+Template" or . = "+Template")]'

		TestObject footerObject = new TestObject()

		footerObject.addProperty('xpath', ConditionType.EQUALS, workoutFooterText, true)

		WebUI.mouseOver(footerObject)

		TestObject templateBlockButtonObject = new TestObject()

		templateBlockButtonObject.addProperty('xpath', ConditionType.EQUALS, templateBlockText, true)

		WebUI.click(templateBlockButtonObject)

		WebUI.waitForElementPresent(findTestObject('Phase Builder Page/Template Blocks Popup/popup_Template Blocks'), 30)

		WebUI.click(findTestObject('Phase Builder Page/Template Blocks Popup/button_All Template Blocks'))

		WebUI.setText(findTestObject('Phase Builder Page/Template Blocks Popup/input_Search'), blockName)

		WebUI.delay(1)

		TestObject selectTemplateBlockObject = new TestObject()

		selectTemplateBlockObject.addProperty('xpath', ConditionType.EQUALS, 'id("template-work-popup")//button[. = "'+ blockName +'"]', true)

		WebUI.waitForElementPresent(selectTemplateBlockObject, 10)

		WebUI.click(selectTemplateBlockObject)
	}

	@Keyword
	def addWarmupPersonalizedBlock(int weekIndex, int workoutIndex){

		String workoutFooterText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']//div[@class="add-block-btns"]'

		String personalizedBlockText = workoutFooterText + '//div[(text() = "+Personalized" or . = "+Personalized")]'

		TestObject footerObject = new TestObject()

		footerObject.addProperty('xpath', ConditionType.EQUALS, workoutFooterText, true)

		WebUI.mouseOver(footerObject)

		TestObject personalizedBlockButtonObject = new TestObject()

		personalizedBlockButtonObject.addProperty('xpath', ConditionType.EQUALS, personalizedBlockText, true)

		WebUI.click(personalizedBlockButtonObject)

		WebUI.click(findTestObject('Phase Builder Page/Right Panel/Day View/button_Warmup'))
	}

	@Keyword
	def addRecoveryPersonalizedBlock(int weekIndex, int workoutIndex){

		String workoutFooterText = '//div[@class="weeks"]/div[contains(@class,"week week")]['+ weekIndex +']//div[contains(@class,"day ng-scope")]['+ workoutIndex +']//div[@class="add-block-btns"]'

		String personalizedBlockText = workoutFooterText + '//div[(text() = "+Personalized" or . = "+Personalized")]'

		TestObject footerObject = new TestObject()

		footerObject.addProperty('xpath', ConditionType.EQUALS, workoutFooterText, true)

		WebUI.mouseOver(footerObject)

		TestObject personalizedBlockButtonObject = new TestObject()

		personalizedBlockButtonObject.addProperty('xpath', ConditionType.EQUALS, personalizedBlockText, true)

		WebUI.click(personalizedBlockButtonObject)

		WebUI.click(findTestObject('Phase Builder Page/Right Panel/Day View/button_Recovery'))
	}
}
