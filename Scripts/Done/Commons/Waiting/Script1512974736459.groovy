import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


WebDriver driver = DriverFactory.getWebDriver()

boolean flag = true

while (flag == true){

	WebUI.delay(1)

	flag = false
	
	if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[@id="messageAlert"]/span[@aria-hidden="false"]') > 0){

		TestObject headerLoadingObject = new TestObject()

		headerLoadingObject.addProperty('xpath', ConditionType.EQUALS, '//div[@id="messageAlert"]/span[@aria-hidden="false"]', true)

		WebUI.waitForElementNotPresent(headerLoadingObject, 30)

		flag = true

	}

	if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[@class="block-ui ng-binding"]/div[@class="md-dialog-container ng-scope"]') > 0){

		TestObject loadingObject = new TestObject()

		loadingObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="block-ui ng-binding"]/div[@class="md-dialog-container ng-scope"]', true)

		WebUI.waitForElementNotPresent(loadingObject, 30)

		flag = true

	}

	if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[@class="loading-overlay"]') > 0){

		TestObject loadingContentObject = new TestObject()

		loadingContentObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="loading-overlay"]', true)

		WebUI.waitForElementNotPresent(loadingContentObject, 30)

		flag = true

	}

	if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[contains(@id,"wm-shoutout")]') > 0){

		TestObject xButtonWalkMeObject = new TestObject()

		xButtonWalkMeObject.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@id,"wm-shoutout")]/div[@class="wm-close-button walkme-x-button"]', true)

		WebUI.click(xButtonWalkMeObject)

		flag = true

	}

	if (CustomKeywords.'com.bridgeathletic.total.getItemSize'('//md-dialog[@id="accept-term-pop"]') > 0){

		TestObject viewObject = new TestObject()

		viewObject.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@id="accept-term-pop"]//md-checkbox[@ng-model="term.view"]', true)

		WebUI.click(viewObject)

		TestObject useDataToUpdateObject = new TestObject()

		useDataToUpdateObject.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@id="accept-term-pop"]//md-checkbox[@ng-model="term.useDataToUpdate"]', true)

		WebUI.click(useDataToUpdateObject)

		TestObject useDataToCommunicateObject = new TestObject()

		useDataToCommunicateObject.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@id="accept-term-pop"]//md-checkbox[@ng-model="term.useDataToCommunicate"]', true)

		WebUI.click(useDataToCommunicateObject)

		TestObject useDataToInformObj = new TestObject()

		useDataToInformObj.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@id="accept-term-pop"]//md-checkbox[@ng-model="term.useDataToInform"]', true)

		WebUI.click(useDataToInformObj)

		TestObject acceptButtonObject = new TestObject()

		acceptButtonObject.addProperty('xpath', ConditionType.EQUALS, '//md-dialog[@id="accept-term-pop"]//button[@ng-click="accept()"]', true)

		WebUI.click(acceptButtonObject)

		flag = true

	}

	if (driver.findElements(By.xpath('//div[@class="cookie-navigation ng-scope"]')).size() > 0){

		TestObject xButton = new TestObject()

		xButton.addProperty('xpath', ConditionType.EQUALS, '//div[@class="cookie-navigation ng-scope"]/i[@class="icon-close"]', true)

		WebUI.click(xButton)

		flag = true

	}

}

