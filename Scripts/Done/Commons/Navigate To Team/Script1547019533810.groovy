import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

if (CustomKeywords.'bridgeathletic.total.getItemSize'('//span[@ui-sref = "user.organizations.members.home"]') == 0){
	
	WebUI.click(findTestObject('Breadcrumbs/button_Home'))
	
}

WebUI.click(findTestObject('Org Home Page/header_Teams'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject selectedTeamObject = new TestObject()

selectedTeamObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="list teams details"]//md-item[@class="team-row ng-scope"]//input[@value="'+ var_teamName +'"]/preceding-sibling::span', true)

WebUI.click(selectedTeamObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])