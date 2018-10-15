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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Done/Commons/Open App'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-2'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Library/button_New Program'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

String randomProgramName = CustomKeywords.'com.bridgeathletic.random.getRandomName'('Performance Program Name ')

WebUI.setText(findTestObject('A Program Page/input_new-program-name'), randomProgramName)

String randomProgramDescription = CustomKeywords.'com.bridgeathletic.random.getRandomName'('Performance Program Description ')

WebUI.setText(findTestObject('A Program Page/textarea_new-program-description'), randomProgramDescription)

WebUI.click(findTestObject('A Program Page/button_add-program'))

WebUI.waitForElementNotPresent(findTestObject('A Program Page/button_add-program'), 30)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('A Program Page/icon_Add Phase'))

WebUI.click(findTestObject('A Program Page/a_Template Phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

int rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(20)

TestObject randomSelectPhaseObject = new TestObject()

randomSelectPhaseObject.addProperty('xpath', ConditionType.EQUALS, ('(//div[@ng-click="selectPhase(item)"])[' + rd) + ']', 
    true)

WebUI.click(randomSelectPhaseObject)

WebUI.delay(1)

WebUI.click(findTestObject('A Program Page/button_Insert'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('A Program Page/icon_Add Phase'))

WebUI.click(findTestObject('A Program Page/a_New Phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

String randomPhaseName = CustomKeywords.'com.bridgeathletic.random.getRandomName'('Performance Phase Name ')

WebUI.setText(findTestObject('Phase Builder Page/input_phase-name'), randomPhaseName)

WebUI.click(findTestObject('Phase Builder Page/button_Accept'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.mouseOver(findTestObject('Phase Builder Page/div_Add Block'))

WebUI.click(findTestObject('Phase Builder Page/div_Template'))

WebUI.waitForElementVisible(findTestObject('Phase Builder Page/md-dialog_template-work-popup'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Phase Builder Page/span_change-type-all'))

WebUI.waitForElementPresent(findTestObject('Phase Builder Page/li_template-phase'), 30)

WebUI.delay(1)

int templatePhaseNumbers = CustomKeywords.'com.bridgeathletic.total.getItemSize'('//div[@class="list-phase-popup"]/ul[1]/li[@class="ng-scope"]')

rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(templatePhaseNumbers)

TestObject randomTemplatePhaseObject = new TestObject()

randomTemplatePhaseObject.addProperty('xpath', ConditionType.EQUALS, ('//div[@class="list-phase-popup"]/ul[1]/li[@class="ng-scope"][' + 
    rd) + ']', true)

WebUI.click(randomTemplatePhaseObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Breadcrumbs/span_selected-program'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('A Program Page/button_program-treedot'))

WebUI.click(findTestObject('A Program Page/button_Clone Program'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.delay(7)

WebUI.callTestCase(findTestCase('Done/Commons/Assign A Program/Assign A Program'), [:])

TestObject editRandomPhaseObject = CustomKeywords.'com.bridgeathletic.phase.getEditPhaseObjectByIndex'(1)

WebUI.click(editRandomPhaseObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('A Program Page/i_clone-week'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

if (WebUI.verifyElementPresent(findTestObject('A Program Page/md-dialog_first-change-dialog'), 3, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('A Program Page/md-checkbox_first-change-dialog'))

    WebUI.click(findTestObject('A Program Page/button_OK_first-change-dialog'))
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Phase Builder Page/button_DELIVER TO ATHLETES'))

WebUI.waitForElementPresent(findTestObject('Commons/Deliver Dialog/dialog_Deliver'), 30)

WebUI.click(findTestObject('Commons/Deliver Dialog/button_Close'))

WS.sendRequest(findTestObject('API/Intercom'))

WebUI.closeBrowser()