import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.lang.reflect.Array as Array
import java.util.concurrent.ThreadLocalRandom as ThreadLocalRandom
import org.junit.After as After
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.bridgeathletic.blockInfo as blockInfo
import com.bridgeathletic.phaseInfo as phaseInfo
import com.bridgeathletic.workoutInfo as workoutInfo
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.keyword.builtin.CallTestCaseKeyword as CallTestCaseKeyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://stg.bridgeathletic.com/')

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('Password') : GlobalVariable.gl_adminPassword, ('Email') : GlobalVariable.gl_adminEmail],
FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/input_typeahead ng-pristine ng'))

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/a_Organization 19'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
String Coach = WebUI.callTestCase(findTestCase('Done/Commons/GetCoachEmail'), [:], FailureHandling.STOP_ON_FAILURE)

System.out.println(Coach)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/a_glyphicon glyphicon-home'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Logout of the super admin account'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-button md-default-theme'))

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_fa fa-sign-out'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/a_Login'), 30)

'Login as the coach from previous step'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('Email') : Coach, ('Password') : '123456789'], FailureHandling.STOP_ON_FAILURE)

'You will land on the organization homepage'
WebUI.waitForElementPresent(findTestObject('Commons/span_LIBRARY'), 30)

if (WebUI.verifyElementPresent(findTestObject('Commons/span_Close'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Commons/span_Close'))
}

WebUI.delay(3)

'Click on the "Content manager"'
WebUI.click(findTestObject('Commons/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementVisible(findTestObject('Template Blocks/Builder-Add Template Block/strong_Programs'), 30)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 2, FailureHandling.OPTIONAL) ==
false) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_All Programs'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 2, FailureHandling.OPTIONAL) ==
	false) {
		WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Unassigned'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}
}

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 5)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

ArrayList<String> AllProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

int phaseNumbers = 0

'Open one of the programs (either assigned or unassigned, chose randomly)'
while (phaseNumbers == 0) {
	TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(AllProgram)

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

int randomPhaseNumber = CustomKeywords.'bridgeathletic.random.getRandomNumber'(phaseNumbers)

Object randomPhase = phaseInfo

randomPhase = CustomKeywords.'bridgeathletic.info.getPhaseInfo'(randomPhaseNumber)

'Open one of the phases in that program (at random)'
WebUI.click(randomPhase.editPhase)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

//if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/button_collapse-btn md-button'),
//    2, FailureHandling.OPTIONAL)) {
//    WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_collapse-btn md-button'))
//}
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
while (WebUI.verifyElementPresent(findTestObject('Commons/button_collapse-btn md-button'), 2, FailureHandling.OPTIONAL) ==
true) {
	WebUI.click(findTestObject('Commons/button_collapse-btn md-button'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.scrollToElement(findTestObject('Object Repository/Commons/span_week-name'), 30)

int totalTemplateBlockNumbers = 1

int totalBlockNumbers = CustomKeywords.'bridgeathletic.total.getTotalBlockNumbers'()

ArrayList<String> blockList = new ArrayList<String>()

ArrayList<String> templateBlockNameList = new ArrayList<String>()

'Select one of the blocks in that phase and chose to "save as template", name it "template-builder1"\r\nSelect another blocks and chose to "save as template", name it "template-builder2"'
while (totalTemplateBlockNumbers < 3) {
	int tmpExNumbers = 0

	Object tmpRandomBlockInfo = blockInfo

	String tmpBlockName

	TestObject tmpRandomBlock

	while (tmpExNumbers == 0) {
		tmpRandomBlock = CustomKeywords.'bridgeathletic.random.getRandomBlock'(totalBlockNumbers)

		WebUI.scrollToElement(findTestObject('Object Repository/Commons/span_week-name'), 30)

		WebUI.delay(1)

		WebUI.click(tmpRandomBlock)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		tmpRandomBlockInfo = CustomKeywords.'bridgeathletic.info.getBlockInfo'()

		tmpExNumbers = tmpRandomBlockInfo.exerciseNumber
	}

	blockList.add(tmpRandomBlockInfo)

	tmpBlockName = CustomKeywords.'bridgeathletic.random.getRandomName'(('template-builder ' + totalTemplateBlockNumbers) +
			' ')

	System.out.println('Template Block Name: ' + tmpBlockName)

	templateBlockNameList.add(tmpBlockName)

	WebUI.mouseOver(tmpRandomBlock)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_custom-icon icon-three-dots'))

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Save As Template'))

	WebUI.setText(findTestObject('Template Blocks/Builder-Add Template Block/input_template-name'), tmpBlockName)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_fa fa-check ng-scope'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	totalTemplateBlockNumbers = (totalTemplateBlockNumbers + 1)
}

System.out.println(blockList.size)

System.out.println(blockList[0].exerciseName)

System.out.println(blockList[1].exerciseName)

System.out.println(templateBlockNameList[0])

System.out.println(templateBlockNameList[1])

'Click on the "Content manager >> Blocks >> My templates"\r\nConfirm that the content of the two blocks match the parent blocks from the phase'
for (int i = 0; i < blockList.size; i = (i + 1)) {
	TestObject templateBlock = new TestObject()

	templateBlock.addProperty('xpath', ConditionType.CONTAINS, ('//strong[contains(.,"' + (templateBlockNameList[i])) +
			'")]', true)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Content Manager'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Blocks'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_All Blocks'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	while ((WebUI.verifyElementPresent(templateBlock, 2, FailureHandling.OPTIONAL) == false) && (WebUI.verifyElementPresent(
	findTestObject('Commons/div_sub-title_right'), 2, FailureHandling.OPTIONAL) == true)) {
		WebUI.click(findTestObject('Commons/div_sub-title_right'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	WebUI.click(templateBlock)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object templateBlockInfo = blockInfo

	templateBlockInfo = CustomKeywords.'bridgeathletic.info.getTemplateBlockInfo'()

	WebUI.verifyEqual(templateBlockInfo.blockName, templateBlockNameList[i].toUpperCase())

	WebUI.verifyEqual(templateBlockInfo.exerciseName, blockList[i].exerciseName)

	WebUI.verifyEqual(templateBlockInfo.exerciseNumber, blockList[i].exerciseNumber)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Content Manager'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_All Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Navigate to an unassigned program'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Unassigned'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 5)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

AllProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

phaseNumbers = 0

while (phaseNumbers == 0) {
	TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(AllProgram)

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

randomPhaseNumber = CustomKeywords.'bridgeathletic.random.getRandomNumber'(phaseNumbers)

randomPhase = CustomKeywords.'bridgeathletic.info.getPhaseInfo'(randomPhaseNumber)

'Open one of the phases in that program (at random)'
WebUI.click(randomPhase.editPhase)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

while (WebUI.verifyElementPresent(findTestObject('Commons/button_collapse-btn md-button'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Commons/button_collapse-btn md-button'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.scrollToElement(findTestObject('Commons/span_week-name'), 2)

'Insert a blocks using a template block (template-builder-1)\r\nInsert a second block using a template (template-builder-2)\r\nConfirm that all looks good in the content manager'
for (int i = 0; i < blockList.size; i = (i + 1)) {
	WebUI.click(findTestObject('Commons/div_add-block-btns'))

	WebUI.click(findTestObject('Commons/div_Template'))

	TestObject tmpBlock = new TestObject()

	tmpBlock.addProperty('xpath', ConditionType.EQUALS, ('//*[@class = "ng-binding ng-scope" and text() = "' + (templateBlockNameList[
				i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.waitForElementNotPresent(findTestObject('Commons/md-dialog_template-work-popup'), 30)

	WebUI.delay(2)

	WebUI.modifyObjectProperty(tmpBlock, 'xpath', 'equals', ('//header[@class="block-name draggable"]/span[@class="ng-binding" and text() = "' +
			(templateBlockNameList[i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object tmpBlockInfo = blockInfo

	tmpBlockInfo = CustomKeywords.'bridgeathletic.info.getBlockInfo'()

	WebUI.verifyEqual(tmpBlockInfo.blockName, templateBlockNameList[i])

	WebUI.verifyEqual(tmpBlockInfo.exerciseName, blockList[i].exerciseName)

	WebUI.verifyEqual(tmpBlockInfo.exerciseNumber, blockList[i].exerciseNumber)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Content Manager'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_All Programs'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Navigate to an assigned program'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Assigned'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 1, FailureHandling.OPTIONAL) ==
false) {

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Unassigned'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/li_ng-scope'), 5)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Team'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	AllProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

	phaseNumbers = 0

	while (phaseNumbers == 0) {
		TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(AllProgram)

		while (WebUI.waitForElementVisible(randomProgram, 2, FailureHandling.OPTIONAL) == false) {
			WebUI.click(findTestObject('Commons/div_sub-title_left'))

			WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
		}

		WebUI.click(randomProgram)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		phaseNumbers = Integer.parseInt(WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/h2_phase_number')).substring(0, 1))

		System.out.println('Phase Numbers of Program: ' + phaseNumbers)
	}

	WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Assign A Program'), [:])
	
	CustomKeywords.'bridgeathletic.select.selectProgramGroup'('All Programs', 'Assigned')
	
}

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

AllProgram = CustomKeywords.'bridgeathletic.total.getAllProgram'()

phaseNumbers = 0

while (phaseNumbers == 0) {
	TestObject randomProgram = CustomKeywords.'bridgeathletic.random.getRandomProgram'(AllProgram)

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

String assignedProgramName = WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/span_programName')).trim()

System.out.println('Assigned Program Name: ' + assignedProgramName)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/md-card_Assign Program'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/div_assigned-member-name'), 30)

String assignedMemberName = WebUI.getText(findTestObject('Template Blocks/Builder-Add Template Block/div_assigned-member-name'))

System.out.println('Assigned Member Name: ' + assignedMemberName)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_md-button md-default-th'))

WebUI.delay(1)

randomPhaseNumber = CustomKeywords.'bridgeathletic.random.getRandomNumber'(phaseNumbers)

randomPhase = CustomKeywords.'bridgeathletic.info.getPhaseInfo'(randomPhaseNumber)

'Open one of the phases in that program (at random)'
WebUI.click(randomPhase.editPhase)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

while (WebUI.verifyElementPresent(findTestObject('Commons/button_collapse-btn md-button'), 2, FailureHandling.OPTIONAL) ==
true) {
	WebUI.click(findTestObject('Commons/button_collapse-btn md-button'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.scrollToElement(findTestObject('Commons/span_week-name'), 2)

'Insert a block using a template (template-builder-1)\r\nInsert a second block using a template (template-builder-2)\r\nConfirm that all looks good in the content manager'
for (int i = 0; i < blockList.size; i = (i + 1)) {
	WebUI.click(findTestObject('Commons/div_add-block-btns'))

	WebUI.click(findTestObject('Commons/div_Template'))

	TestObject tmpBlock = new TestObject()

	tmpBlock.addProperty('xpath', ConditionType.EQUALS, ('//*[@class = "ng-binding ng-scope" and text() = "' + (templateBlockNameList[
				i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.waitForElementNotPresent(findTestObject('Commons/md-dialog_template-work-popup'), 30)

	WebUI.delay(1)

	if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/md-content_md-default-theme'),
	2, FailureHandling.OPTIONAL) == true) {
		WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-container'))

		WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_OK'))

		WebUI.delay(2)
	}

	WebUI.modifyObjectProperty(tmpBlock, 'xpath', 'equals', ('//header[@class="block-name draggable"]/span[@class="ng-binding" and text() = "' +
			(templateBlockNameList[i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object tmpBlockInfo = blockInfo

	tmpBlockInfo = CustomKeywords.'bridgeathletic.info.getBlockInfo'()

	WebUI.verifyEqual(tmpBlockInfo.blockName, templateBlockNameList[i])

	WebUI.verifyEqual(tmpBlockInfo.exerciseName, blockList[i].exerciseName)

	WebUI.verifyEqual(tmpBlockInfo.exerciseNumber, blockList[i].exerciseNumber)
}

'Deliver changes to athletes'
WebUI.verifyElementClickable(findTestObject('Template Blocks/Builder-Add Template Block/button_add-btn save-btn md-but'))

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_add-btn save-btn md-but'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/button_fa fa-close md-button m'),
		30)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_fa fa-close md-button m'))

WebUI.delay(1)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/a_glyphicon glyphicon-home'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_MEMBERS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject assignedMemberObject = WebUI.modifyObjectProperty(findTestObject('Template Blocks/Builder-Add Template Block/span_athlete member'),
		'text', 'equals', assignedMemberName, true)

WebUI.click(assignedMemberObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Save From Completed Individual Workout/div_fc-content'), 30)

if (WebUI.verifyElementText(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'), assignedProgramName,
FailureHandling.OPTIONAL) == false) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'))

	TestObject selectedProgram = new TestObject()

	selectedProgram.addProperty('text', ConditionType.EQUALS, assignedProgramName, true)

	WebUI.click(selectedProgram)

	WebUI.verifyElementText(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'), assignedProgramName,
			FailureHandling.STOP_ON_FAILURE)
}

TestObject selectDayObject = WebUI.callTestCase(findTestCase('Done/Commons/SelectDayInCalendar'), [('startDate') : randomPhase.startDate, ('expectYear') : randomPhase.startYear],
FailureHandling.STOP_ON_FAILURE)

WebUI.click(selectDayObject)

WebUI.verifyElementPresent(findTestObject('Commons/md-dialog_selectedDay'), 30)

int blockSize = CustomKeywords.'bridgeathletic.total.getItemSize'('//div[@class="block-name"]')

'Confirm that the athletes received the changes successfully, from the athlete profile page / athlete calendar.'
for (int i = 0; i < blockList.size; i = (i + 1)) {
	Object blockInfoInCal = blockInfo

	blockInfoInCal = CustomKeywords.'bridgeathletic.info.getBlockInfoInCal'(((blockSize - blockList.size) + i) + 1)

	WebUI.verifyEqual(blockInfoInCal.blockName, templateBlockNameList[i])

	WebUI.verifyEqual(blockInfoInCal.exerciseNumber, blockList[i].exerciseNumber)

	WebUI.verifyEqual(blockInfoInCal.exerciseName, blockList[i].exerciseName)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_close md-button'))

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_md-button ng-scope md-d'))

WebUI.delay(2)

'From the athlete page, open the program for individual edits'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_ng-binding'))

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/md-dialog_confirm-popup'), 3,
FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-container (1)'))

	WebUI.delay(1)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_OK'))
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

while (WebUI.verifyElementPresent(findTestObject('Commons/button_collapse-btn md-button'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Commons/button_collapse-btn md-button'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.scrollToElement(findTestObject('Object Repository/Commons/span_week-name'), 30)

totalBlockNumbers = CustomKeywords.'bridgeathletic.total.getTotalBlockNumbers'()

System.out.println('Total Template Block Numbers: ' + totalTemplateBlockNumbers)

'Select a block and save it as template (template-builder-3)'
while (totalTemplateBlockNumbers < 4) {
	int tmpExNumbers = 0

	Object tmpRandomBlockInfo = blockInfo

	String tmpBlockName

	TestObject tmpRandomBlock

	while (tmpExNumbers == 0) {
		tmpRandomBlock = CustomKeywords.'bridgeathletic.random.getRandomBlock'(totalBlockNumbers)

		WebUI.scrollToElement(findTestObject('Object Repository/Commons/span_week-name'), 2)

		WebUI.delay(1)

		WebUI.click(tmpRandomBlock)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		tmpRandomBlockInfo = CustomKeywords.'bridgeathletic.info.getBlockInfo'()

		tmpExNumbers = tmpRandomBlockInfo.exerciseNumber
	}

	blockList.add(tmpRandomBlockInfo)

	tmpBlockName = CustomKeywords.'bridgeathletic.random.getRandomName'('template-builder 3 ')

	System.out.println('Template Block Name: ' + tmpBlockName)

	templateBlockNameList.add(tmpBlockName)

	WebUI.mouseOver(tmpRandomBlock)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_custom-icon icon-three-dots'))

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Save As Template'))

	WebUI.setText(findTestObject('Template Blocks/Builder-Add Template Block/input_template-name'), tmpBlockName)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_fa fa-check ng-scope'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	totalTemplateBlockNumbers = (totalTemplateBlockNumbers + 1)
}

System.out.println(blockList.size)

System.out.println(blockList[2].exerciseName)

System.out.println(templateBlockNameList[2])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/a_glyphicon glyphicon-home'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Commons/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Confirm that the new template block appears in the content manager'
for (int i = 0; i < blockList.size; i = (i + 1)) {
	TestObject templateBlock = new TestObject()

	templateBlock.addProperty('xpath', ConditionType.CONTAINS, ('//strong[contains(.,"' + (templateBlockNameList[i])) +
			'")]', true)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_Content Manager'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/strong_Blocks'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_All Blocks'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	while ((WebUI.verifyElementPresent(templateBlock, 2, FailureHandling.OPTIONAL) == false) && (WebUI.verifyElementPresent(
	findTestObject('Commons/div_sub-title_right'), 2, FailureHandling.OPTIONAL) == true)) {
		WebUI.click(findTestObject('Commons/div_sub-title_right'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	WebUI.click(templateBlock)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object templateBlockInfo = blockInfo

	templateBlockInfo = CustomKeywords.'bridgeathletic.info.getTemplateBlockInfo'()

	WebUI.verifyEqual(templateBlockInfo.blockName, templateBlockNameList[i].toUpperCase())

	WebUI.verifyEqual(templateBlockInfo.exerciseName, blockList[i].exerciseName)

	WebUI.verifyEqual(templateBlockInfo.exerciseNumber, blockList[i].exerciseNumber)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/a_glyphicon glyphicon-home'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_MEMBERS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(assignedMemberObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Save From Completed Individual Workout/div_fc-content'), 30)

if (WebUI.verifyElementText(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'), assignedProgramName,
FailureHandling.OPTIONAL) == false) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'))

	TestObject selectedProgram = new TestObject()

	selectedProgram.addProperty('text', ConditionType.EQUALS, assignedProgramName, true)

	WebUI.click(selectedProgram)

	WebUI.verifyElementText(findTestObject('Template Blocks/Builder-Add Template Block/span_displayProgram'), assignedProgramName,
			FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_md-button ng-scope md-d'))

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_ng-binding'))

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/md-dialog_confirm-popup'), 3,
FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-container (1)'))

	WebUI.delay(1)

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_OK'))
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

while (WebUI.verifyElementPresent(findTestObject('Commons/button_collapse-btn md-button'), 2, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Commons/button_collapse-btn md-button'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
}

WebUI.scrollToElement(findTestObject('Object Repository/Commons/span_week-name'), 30)

'Using individual edits, add a template block (template-builder-3) to a scheduled workout'
for (int i = 2; i < blockList.size; i = (i + 1)) {
	WebUI.click(findTestObject('Commons/div_add-block-btns'))

	WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_Template'))

	TestObject tmpBlock = new TestObject()

	tmpBlock.addProperty('xpath', ConditionType.EQUALS, ('//*[@class = "ng-binding ng-scope" and text() = "' + (templateBlockNameList[
				i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.waitForElementNotPresent(findTestObject('Commons/md-dialog_template-work-popup'), 30)

	WebUI.delay(1)

	if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Builder-Add Template Block/md-content_md-default-theme'),
	2, FailureHandling.OPTIONAL) == true) {
		WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-container'))

		WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_OK'))

		WebUI.delay(2)
	}

	WebUI.modifyObjectProperty(tmpBlock, 'xpath', 'equals', ('//header[@class="block-name draggable"]/span[@class="ng-binding" and text() = "' +
			(templateBlockNameList[i])) + '"]', true)

	WebUI.click(tmpBlock)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object tmpBlockInfo = blockInfo

	tmpBlockInfo = CustomKeywords.'bridgeathletic.info.getBlockInfo'()

	WebUI.verifyEqual(tmpBlockInfo.blockName, templateBlockNameList[i])

	WebUI.verifyEqual(tmpBlockInfo.exerciseName, blockList[i].exerciseName)

	WebUI.verifyEqual(tmpBlockInfo.exerciseNumber, blockList[i].exerciseNumber)
}

Object workoutInfo = workoutInfo

workoutInfo = CustomKeywords.'bridgeathletic.info.getWorkoutInfo'(1)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/span_member-name'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Save From Completed Individual Workout/div_fc-content'), 30)

selectDayObject = WebUI.callTestCase(findTestCase('Done/Commons/SelectDayInCalendar'), [('startDate') : workoutInfo.date, ('expectYear') : workoutInfo.year],
FailureHandling.STOP_ON_FAILURE)

WebUI.click(selectDayObject)

WebUI.verifyElementPresent(findTestObject('Commons/md-dialog_selectedDay'), 30)

blockSize = CustomKeywords.'bridgeathletic.total.getItemSize'('//div[@class="block-name"]')

'Confirm that the athlete received the changes successfully, from the athlete profile page / athlete calendar.'
for (int i = 2; i < blockList.size; i = (i + 1)) {
	Object blockInfoInCal = blockInfo

	blockInfoInCal = CustomKeywords.'bridgeathletic.info.getBlockInfoInCal'(((blockSize - blockList.size) + i) + 1)

	WebUI.verifyEqual(blockInfoInCal.blockName, templateBlockNameList[i])

	WebUI.verifyEqual(blockInfoInCal.exerciseNumber, blockList[i].exerciseNumber)

	WebUI.verifyEqual(blockInfoInCal.exerciseName, blockList[i].exerciseName)
}

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/button_close md-button'))

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/div_md-button md-default-theme'))

WebUI.delay(1)

'Logout'
WebUI.click(findTestObject('Template Blocks/Builder-Add Template Block/i_fa fa-sign-out'))

