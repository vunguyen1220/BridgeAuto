import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

import com.bridgeathletic.phase
import com.bridgeathletic.program
import com.bridgeathletic.user
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

CustomKeywords.'bridgeathletic.program.selectProgramType'(programType)

int totalProgramNumbers = CustomKeywords.'bridgeathletic.program.getTotalProgramNumbers'()

int memberNumbers = 0

boolean flag = true

int tmp = 1

Object selectedProgramInfo = new program()

while (memberNumbers < 20 || flag == false) {
	
	flag = true
	
	Object tmpProgramInfoInList = new program()
	
	tmpProgramInfoInList = CustomKeywords.'bridgeathletic.program.getProgramInfoInLibrary'(tmp)
	
	TestObject programSelectObject = new TestObject()
	
	programSelectObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]['+ tmp +']', true)
	
	WebUI.click(programSelectObject)
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
	Object tmpAssignedProgramInfo = new program()
	
	tmpAssignedProgramInfo = CustomKeywords.'bridgeathletic.program.getAssignedProgramInfoInDetail'()
	
	if (tmpAssignedProgramInfo.memberNumbers > 20){
		
		for (int i = 1; i <= tmpAssignedProgramInfo.phaseNumbers; i = i + 1){
			
			tmpAssignedProgramInfo.phaseList.add(CustomKeywords.'bridgeathletic.phase.getPhaseInfoInDetail'(i))
			
		}
		
	}
	
	selectedProgramInfo = CustomKeywords.'bridgeathletic.program.updateProgramInfo'(tmpAssignedProgramInfo, tmpProgramInfoInList)
	
	WebUI.click(findTestObject('Breadcrumbs/button_Programs'))
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	
	if (selectedProgramInfo.currentPhase == null){
		
		flag = false
		
	}
	
	memberNumbers = selectedProgramInfo.memberNumbers
	
	tmp = tmp + 1
	
}

CustomKeywords.'bridgeathletic.program.printProgramInfo'(selectedProgramInfo)

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

int totalMembers = CustomKeywords.'bridgeathletic.total.getItemSize'('//li[contains(@class,"team-roster-item ng-scope")]')

List<user> userList = []

for (int i = 1; i <= totalMembers; i = i + 1){
	
	userList.add(CustomKeywords.'bridgeathletic.user.getUserInfo'(i))
	
}

int rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(userList.size() - 1)

WebUI.callTestCase(findTestCase('Done/Commons/Logout'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : userList[rd].email, ('password') : '23456789'], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

CustomKeywords.'bridgeathletic.select.selectProgramType'(programType)

TestObject programSelectObject = new TestObject()

programSelectObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]/div[@class="items name ng-scope"]/span[text()="'+ selectedProgramInfo.programName +'"]', true)

WebUI.click(programSelectObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject treeDotCurrentPhaseObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.currentPhase)

WebUI.click(treeDotCurrentPhaseObject)

WebUI.click(findTestObject('A Program Page/li_Clone Phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

if (WebUI.verifyElementPresent(findTestObject('A Program Page/md-dialog_first-change-dialog'), 3, FailureHandling.OPTIONAL)){
	
	WebUI.click(findTestObject('A Program Page/md-checkbox_first-change-dialog'))
	
	WebUI.click(findTestObject('A Program Page/button_OK_first-change-dialog'))
	
}

int totalPhaseNumbers = CustomKeywords.'bridgeathletic.phase.getTotalPhaseNumbersInDetail'()

if (totalPhaseNumbers != selectedProgramInfo.phaseNumbers){
	
	List<phase> phaseList = []
	
	for (int i = 1; i <= totalPhaseNumbers; i = i + 1){
		
		phaseList.add(CustomKeywords.'bridgeathletic.phase.getPhaseInfoInDetail'(i))
		
	}
	
	selectedProgramInfo.phaseNumbers = totalPhaseNumbers
	
	selectedProgramInfo.phaseList = phaseList
	
}

TestObject treeDotLastPhaseObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.phaseList[selectedProgramInfo.phaseNumbers - 1].phaseName)

WebUI.click(treeDotLastPhaseObject)

WebUI.click(findTestObject('A Program Page/li_Edit NameNote'))

String randomText = CustomKeywords.'bridgeathletic.random.getRandomName'('Next Phase ')

WebUI.setText(findTestObject('A Program Page/input_phase-name'), randomText)

WebUI.click(findTestObject('A Program Page/i_icon-update-phase'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

selectedProgramInfo.phaseList[selectedProgramInfo.phaseNumbers - 1].phaseName = randomText

TestObject sourceObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByIndex'(selectedProgramInfo.phaseNumbers)

int currentPhaseIndex = CustomKeywords.'bridgeathletic.phase.getPhaseIndexByName'(selectedProgramInfo.currentPhase)

TestObject destinationObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByIndex'(currentPhaseIndex + 1)

WebUI.dragAndDropToObject(sourceObject, destinationObject)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Breadcrumbs/button_DELIVER TO ATHLETES'))

WebUI.waitForElementPresent(findTestObject('Commons/Deliver Dialog/dialog_Deliver'), 30)

WebUI.click(findTestObject('Commons/Deliver Dialog/button_Close'))

selectedProgramInfo.phaseList = CustomKeywords.'bridgeathletic.phase.getPhaseList'(totalPhaseNumbers)

int nextPhaseIndex = CustomKeywords.'bridgeathletic.phase.getPhaseIndexByName'(randomText)

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

rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(selectedProgramInfo.memberNumbers)

Object randomAthlete  = new TestObject()

randomAthlete.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewMemberProfile(athlete)"]//span[contains(text(),"'+ selectedProgramInfo.memberList[rd - 1].split(" ")[1] +'")]', true)

WebUI.click(randomAthlete)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Athlete Profile Page/div_fc-content-calendar'), 30)

CustomKeywords.'bridgeathletic.calendar.selectDateInCalendar'(selectedProgramInfo.phaseList[nextPhaseIndex - 1].startDate)

String expectStartPhaseName = selectedProgramInfo.phaseList[nextPhaseIndex - 1].phaseName + ' week 1/' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers

WebUI.verifyTextPresent(expectStartPhaseName, false)

CustomKeywords.'bridgeathletic.calendar.selectDateInCalendar'(selectedProgramInfo.phaseList[nextPhaseIndex - 1].endDate)

String expectEndPhaseName = selectedProgramInfo.phaseList[nextPhaseIndex - 1].phaseName + ' week ' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers + '/' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers

WebUI.verifyTextPresent(expectEndPhaseName, false)

WebUI.callTestCase(findTestCase('Done/Commons/Logout'), [:])

WebUI.closeBrowser()