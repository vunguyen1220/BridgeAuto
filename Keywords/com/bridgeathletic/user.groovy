package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.ArrayList

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

public class user {

	WebDriver driver = DriverFactory.getWebDriver()

	String userName

	String status

	float weightValue

	String weightUnit

	float heightValue

	String heightUnit

	String email

	boolean registration

	@Keyword
	def getUserInfo (int index){

		Object userInfo = new user()

		String userName = driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ index +']//span[@class = "team-roster-name ng-binding"]')).getText()

		userInfo.setUserName(userName)

		List<String> weightText = (driver.findElement(By.xpath('(//li[contains(@class,"team-roster-item ng-scope")]['+ index +']//div[@aria-hidden="false"]/div[@class="team-roster-text ng-binding ng-scope"])[1]')).getText()).split(' ')

		float weightValue = 0

		if (weightText[0].isEmpty() == false){

			weightValue = Float.parseFloat(weightText[0])
		}

		userInfo.setWeightValue(weightValue)

		String weightUnit = weightText[1]

		userInfo.setWeightUnit(weightUnit)

		String heightText = driver.findElement(By.xpath('(//li[contains(@class,"team-roster-item ng-scope")]['+ index +']//div[@aria-hidden="false"]/div[@class="team-roster-text ng-binding ng-scope"])[2]')).getText()

		String heightUnit = ''

		float heightValue = 0

		if (weightUnit == 'lbs' && heightText.isEmpty() == false){

			heightUnit = 'ft'

			heightValue = Float.parseFloat((heightText.replace("' ", ".")).replace('"', ''))
		}

		else if (weightUnit == 'kg' && heightText.isEmpty() == false){

			heightUnit = 'cm'

			heightValue = Float.parseFloat(heightText.replace(' cm', ''))
		}

		userInfo.setHeightUnit(heightUnit)

		userInfo.setHeightValue(heightValue)

		String email = driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ index +']//div[@aria-hidden="false"]//input[@type="email"]')).getAttribute("value")

		userInfo.setEmail(email)

		int registerText = driver.findElements(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ index +']//div[@aria-hidden="false"]//span[@ng-if="athlete.isRegistered"]')).size()

		boolean registration = false

		if (registerText > 0){

			registration = true
		}

		userInfo.setRegistration(registration)

		println 'User Name: ' + userInfo.userName + '\nWeight Unit: ' + userInfo.weightUnit + '\nWeight Value: ' + userInfo.weightValue + '\nHeight Unit: ' + userInfo.heightUnit + '\nHeight Value: ' + userInfo.heightValue + '\nEmail: ' + userInfo.email + '\nRegistration Status: ' + userInfo.registration

		return userInfo
	}
}
