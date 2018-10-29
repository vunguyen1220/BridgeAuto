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
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://stg.bridgeathletic.com/')

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword],
	FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-19'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
GlobalVariable.Coach_Username = WebUI.callTestCase(findTestCase('Done/Commons/GetCoachEmail'), [:], FailureHandling.STOP_ON_FAILURE)

System.out.println('Coach Email: ' + GlobalVariable.Coach_Username)

WebUI.click(findTestObject('Header/div_User Details'))

WebUI.delay(1)

WebUI.click(findTestObject('Header/i_sign-out'))

'Login as the coach from previous step'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.Coach_Username, ('password') : '123456789'],
	FailureHandling.STOP_ON_FAILURE)

'You will land on the organization homepage'
WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Content Manager Page/Left Panel/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'bridgeathletic.select.selectProgramGroup'('All Programs', 'Unassigned')

WebUI.verifyElementPresent(findTestObject('Content Manager Page/Left Panel/strong_Team'), 2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Content Manager Page/Left Panel/strong_Team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<String> allPrograms = CustomKeywords.'bridgeathletic.total.getAllProgram'()

int phaseNumbers = 0

while (phaseNumbers == 0) {
	TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(allPrograms)

	while (WebUI.waitForElementVisible(randomProgram, 2, FailureHandling.OPTIONAL) == false) {
		WebUI.click(findTestObject('Commons/div_sub-title_left'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	WebUI.click(randomProgram)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
	phaseNumbers = Integer.parseInt(WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/h2_phase_number')).substring(0, 1))

	System.out.println('Phase Numbers of Program: ' + phaseNumbers)
}

Object programInfo = CustomKeywords.'bridgeathletic.info.getProgramInfo'()

