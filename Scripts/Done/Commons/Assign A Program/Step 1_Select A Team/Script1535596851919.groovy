import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


if (WebUI.verifyElementPresent(findTestObject('Program Assignment Popup/header_Select A Team'), 2, FailureHandling.OPTIONAL)){

	if (var_TeamName == ''){

		int tmp = 0

		while (tmp < var_MemberNumbers){

			int rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[@class="item team-name"]'))

			TestObject randomTeamObject = new TestObject()

			randomTeamObject.addProperty('xpath', ConditionType.EQUALS, '(//div[@class="item team-name"])['+ rd +']', true)

			WebUI.click(randomTeamObject)
			
			WebUI.delay(1)

			tmp = Integer.parseInt(WebUI.getText(findTestObject('Program Assignment Popup/text_Athlete Numbers Selected')).split(' ')[0])

		}

	}

	else {
		
		TestObject selectTeamObject = new TestObject()
		
		selectTeamObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class="item team-name" and . = ' + '"' + var_TeamName + '"' +']', true)
		
		WebUI.click(selectTeamObject)
		
	}
	
	WebUI.click(findTestObject('Program Assignment Popup/button_Next'))
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

}