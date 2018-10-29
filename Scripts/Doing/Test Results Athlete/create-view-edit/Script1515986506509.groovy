import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.bridgeathletic.teamInfo as teamInfo
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
import com.sun.org.apache.xalan.internal.xsltc.compiler.If as If
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://stg.bridgeathletic.com/')

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword], 
    FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-13'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'You will land on the organization homepage'
WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

int totalTeamNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content')

Object randomTeam = teamInfo

for (int i = 1; i <= totalTeamNumbers; i = (i + 1)) {
    randomTeam = CustomKeywords.'bridgeathletic.info.getTeamInfo'(i)

    if ((randomTeam.athleteNumbers > 1) && (randomTeam.athleteNumbers <= 5)) {
        break
    }
}

TestObject teamName = new TestObject()

teamName.addProperty('xpath', ConditionType.EQUALS, ('//span[@class = "ng-binding" and text() = "' + randomTeam.teamName) + 
    '"]', true)

'Navigate to the team page'
WebUI.click(teamName)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Team Home Page/button_Enter-test-results'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('New Test Page/button_select-exercise'))

WebUI.waitForElementPresent(findTestObject('New Test Page/md-dialog_Choose-exercise'), 30)

WebUI.setText(findTestObject('New Test Page/input_search-text'), 'Squat')

WebUI.delay(2)

int exerciseNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('id("exercise-switch-new")//div[@ng-repeat="ex in exerciseCollection.organizationExercise track by $index"]')

for (int i = 1; i <= exerciseNumbers; i = (i + 1)) {
    TestObject exerciseItem = new TestObject()

    exerciseItem.addProperty('xpath', ConditionType.EQUALS, ('(id("exercise-switch-new")//div[@ng-repeat="ex in exerciseCollection.organizationExercise track by $index"]/span)[' + 
        i) + ']', true)

    String exerciseName = WebUI.getText(exerciseItem).trim()

    if (exerciseName == 'Squat') {
        WebUI.click(exerciseItem)

        break
    }
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('New Test Page/span_test-for_Weight'))

WebUI.click(findTestObject('New Test Page/span_param_Reps'))

WebUI.setText(findTestObject('New Test Page/input_Param-value'), '5')

