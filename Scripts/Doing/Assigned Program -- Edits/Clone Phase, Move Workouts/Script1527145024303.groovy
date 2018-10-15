import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.bridgeathletic.blockInfo
import com.bridgeathletic.phase
import com.bridgeathletic.program
import com.bridgeathletic.user
import com.bridgeathletic.workout
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


WebUI.callTestCase(findTestCase('Done/Commons/Open App'), [:])

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword],
FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-2'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<Integer> programType = [2]

CustomKeywords.'com.bridgeathletic.select.selectProgramType'(programType)

int totalProgramNumbers = CustomKeywords.'com.bridgeathletic.program.getTotalProgramNumbers'()

int memberNumbers = 0

boolean flag = true

int tmp = 1

Object selectedProgramInfo = new program()

while (memberNumbers < 20 || flag == false) {

	flag = true

	Object tmpProgramInfoInList = new program()

	tmpProgramInfoInList = CustomKeywords.'com.bridgeathletic.program.getProgramInfoInLibrary'(tmp)

	TestObject programSelectObject = new TestObject()

	programSelectObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]['+ tmp +']', true)

	WebUI.click(programSelectObject)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	Object tmpAssignedProgramInfo = new program()

	tmpAssignedProgramInfo = CustomKeywords.'com.bridgeathletic.program.getAssignedProgramInfoInDetail'()

	if (tmpAssignedProgramInfo.memberNumbers > 20){

		for (int i = 1; i <= tmpAssignedProgramInfo.phaseNumbers; i = i + 1){

			tmpAssignedProgramInfo.phaseList.add(CustomKeywords.'com.bridgeathletic.phase.getPhaseInfoInDetail'(i))

		}

	}

	selectedProgramInfo = CustomKeywords.'com.bridgeathletic.program.updateProgramInfo'(tmpAssignedProgramInfo, tmpProgramInfoInList)

	WebUI.click(findTestObject('Breadcrumbs/button_Programs'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	if (selectedProgramInfo.currentPhase == null){

		flag = false

	}

	memberNumbers = selectedProgramInfo.memberNumbers

	tmp = tmp + 1

}

CustomKeywords.'com.bridgeathletic.program.printProgramInfo'(selectedProgramInfo)

WebUI.click(findTestObject('Breadcrumbs/button_Home'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject selectedTeamObject = new TestObject()

selectedTeamObject.addProperty('xpath', ConditionType.EQUALS, '//span[@ng-click="!isEditing&&goToTeam(team.id)" and text()="'+ (selectedProgramInfo.teamName).toUpperCase() +'"]', true)

WebUI.click(selectedTeamObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Team Home Page/span_See All_Roster-module'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Roster Page/button_role'))

WebUI.click(findTestObject('Roster Page/a_Coaches'))

WebUI.delay(2)

int totalMembers = CustomKeywords.'com.bridgeathletic.total.getItemSize'('//li[contains(@class,"team-roster-item ng-scope")]')

List<user> userList = []

for (int i = 1; i <= totalMembers; i = i + 1){

	userList.add(CustomKeywords.'com.bridgeathletic.user.getUserInfo'(i))

}

int rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(userList.size() - 1)

WebUI.callTestCase(findTestCase('Done/Commons/Logout'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : userList[rd].email, ('password') : '23456789'], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'com.bridgeathletic.select.selectProgramType'(programType)

TestObject programSelectObject = new TestObject()

programSelectObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]/div[@class="items name ng-scope"]/span[text()="'+ selectedProgramInfo.programName +'"]', true)

WebUI.click(programSelectObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject treeDotCurrentPhaseObject = CustomKeywords.'com.bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.currentPhase)

WebUI.click(treeDotCurrentPhaseObject)

WebUI.click(findTestObject('A Program Page/li_Edit NameNote'))

String randomText = CustomKeywords.'com.bridgeathletic.random.getRandomName'('Current Phase ')

WebUI.setText(findTestObject('A Program Page/input_phase-name'), randomText)

WebUI.click(findTestObject('A Program Page/i_icon-update-phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

if (WebUI.verifyElementPresent(findTestObject('A Program Page/md-dialog_first-change-dialog'), 3, FailureHandling.OPTIONAL)){

	WebUI.click(findTestObject('A Program Page/md-checkbox_first-change-dialog'))

	WebUI.click(findTestObject('A Program Page/button_OK_first-change-dialog'))

}

selectedProgramInfo.currentPhase = randomText

selectedProgramInfo.phaseList = CustomKeywords.'com.bridgeathletic.phase.getPhaseList'(selectedProgramInfo.phaseNumbers)

treeDotCurrentPhaseObject = CustomKeywords.'com.bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.currentPhase)

WebUI.click(treeDotCurrentPhaseObject)

WebUI.click(findTestObject('A Program Page/li_Clone Phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

selectedProgramInfo.phaseNumbers = selectedProgramInfo.phaseNumbers + 1

selectedProgramInfo.phaseList = CustomKeywords.'com.bridgeathletic.phase.getPhaseList'(selectedProgramInfo.phaseNumbers)

TestObject treeDotLastPhaseObject = CustomKeywords.'com.bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.phaseList[selectedProgramInfo.phaseNumbers - 1].phaseName)

WebUI.click(treeDotLastPhaseObject)

WebUI.click(findTestObject('A Program Page/li_Edit NameNote'))

randomText = CustomKeywords.'com.bridgeathletic.random.getRandomName'('New Phase ')

WebUI.setText(findTestObject('A Program Page/input_phase-name'), randomText)

WebUI.click(findTestObject('A Program Page/i_icon-update-phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

selectedProgramInfo.phaseList = CustomKeywords.'com.bridgeathletic.phase.getPhaseList'(selectedProgramInfo.phaseNumbers)

TestObject editLastPhaseObject = CustomKeywords.'com.bridgeathletic.phase.getEditPhaseObjectByIndex'(selectedProgramInfo.phaseNumbers)

WebUI.click(editLastPhaseObject)

WebUI.waitForElementPresent(findTestObject('A Program Page/md-dialog_Deliver'), 30)

WebUI.click(findTestObject('A Program page/button_Deliver'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<workout> workoutList = CustomKeywords.'com.bridgeathletic.workout.getWorkoutListInWeek'(1)

while(workoutList.size() < 3){

	rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(workoutList.size())

	CustomKeywords.'com.bridgeathletic.workout.clickCloneWorkoutButtonByIndex'(1, rd)

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
	workoutList = CustomKeywords.'com.bridgeathletic.workout.getWorkoutListInWeek'(1)

}

CustomKeywords.'com.bridgeathletic.workout.moveWorkout'(1, 1, 1, 2)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'com.bridgeathletic.workout.moveWorkout'(1, 2, 1, 3)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

workoutList = CustomKeywords.'com.bridgeathletic.workout.getWorkoutListInWeek'(1)

String a = "window.open('http://stg.bridgeathletic.com', '');"

WebDriver driver = DriverFactory.getWebDriver()

((JavascriptExecutor)driver).executeScript(a)

WebUI.switchToWindowIndex(1)

WebUI.waitForElementPresent(findTestObject('Header/div_User Details'), 30)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(selectedTeamObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Team Home Page/span_See All_Roster-module'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

rd = CustomKeywords.'com.bridgeathletic.random.getRandomNumber'(selectedProgramInfo.memberNumbers)

TestObject randomAthlete  = new TestObject()

randomAthlete.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewMemberProfile(athlete)"]//span[contains(text(),"'+ selectedProgramInfo.memberList[rd - 1].split(" ")[1] +'")]', true)

WebUI.click(randomAthlete)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Athlete Profile Page/div_fc-content-calendar'), 30)
