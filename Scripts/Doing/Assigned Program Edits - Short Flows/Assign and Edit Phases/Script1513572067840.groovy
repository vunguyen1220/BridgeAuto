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

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('Password') : GlobalVariable.gl_adminPassword, ('Email') : GlobalVariable.gl_adminEmail],
FailureHandling.STOP_ON_FAILURE)

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reload'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Reload'))
}

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/input_typeahead ng-pristine ng'))

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/a_Organization 19'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

String Coach = WebUI.callTestCase(findTestCase('Done/Commons/Get Coach Email From Team'), [:], FailureHandling.STOP_ON_FAILURE)

System.out.println('Coach Email: ' + Coach)

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/input_typeahead ng-pristine ng'))

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/a_Organization 45'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Commons/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'bridgeathletic.select.selectProgramGroup'('All Programs', 'Unassigned')

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/strong_Swimming'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

ArrayList<String> allProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(allProgram)

while (WebUI.waitForElementVisible(randomProgram, 2, FailureHandling.OPTIONAL) == false) {
	WebUI.click(findTestObject('Commons/div_sub-title_left'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.click(randomProgram)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/button_treedot md-button md-de'))

WebUI.mouseOver(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/span_Clone to org'))

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/a_clone to Organization 19'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/div_md-button md-default-theme'))

WebUI.delay(2)

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/i_fa fa-sign-out'))

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('Email') : Coach, ('Password') : '123456789'], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Commons/span_LIBRARY'), 30)

if (WebUI.verifyElementPresent(findTestObject('Commons/span_Close'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Commons/span_Close'))
}

WebUI.delay(2)

WebUI.click(findTestObject('Commons/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'bridgeathletic.select.selectProgramGroup'('All Programs', 'Unassigned')

WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/strong_No Team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

allProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

int phaseNumbers = 0

while (phaseNumbers <= 2) {
	randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(allProgram)

	while (WebUI.waitForElementVisible(randomProgram, 2, FailureHandling.OPTIONAL) == false) {
		WebUI.click(findTestObject('Commons/div_sub-title_left'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	WebUI.click(randomProgram)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	phaseNumbers = Integer.parseInt(WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/h2_phase_number')).substring(
			0, 1))

	System.out.println('Phase Numbers of Program: ' + phaseNumbers)
}

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Assign A Program'), [:])

for (int i = 1; i <= 2; i = i + 1) {

	phaseNumbers = Integer.parseInt(WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/h2_phase_number')).substring(
			0, 1))

	int randomPhase = CustomKeywords.'bridgeathletic.random.getRandomNumber'(phaseNumbers)

	Object phaseInfo = phaseInfo

	phaseInfo = CustomKeywords.'bridgeathletic.info.getPhaseInfo'(randomPhase)

	WebUI.click(phaseInfo.dotPhase)

	WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/li_Delete Phase'))

	WebUI.waitForElementPresent(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/md-dialog_confirm-popup'),
			30)

	WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/button_Yes'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	if (WebUI.verifyElementPresent(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/md-dialog_app-dialog first-change-dialog'),
	2, FailureHandling.OPTIONAL)) {
		WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/md-checkbox_primary-checkbox'))

		WebUI.click(findTestObject('Assigned Program Edits - Short Flows/Assign and Edit Phases/button_OK'))

		WebUI.delay(1)
	}

}