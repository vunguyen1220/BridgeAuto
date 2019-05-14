import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import bridgeathletic.program as program
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Done/Commons/Open App'), [:])

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [var_email:GlobalVariable.gl_adminEmail, var_password:GlobalVariable.gl_adminPassword])

'Navigate to a random organization (org 2)'
WebUI.callTestCase(findTestCase('Done/Commons/Select Organization'), [var_orgNumber:2])

WebUI.click(findTestObject('Org Home Page/header_Library'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Filter Programs'), [var_byType:['Assigned']])

int totalProgramNumbers = CustomKeywords.'bridgeathletic.program.getTotalProgramNumbers'()

boolean flag = false

int tmp = 1

Object selectedProgramInfo = new program()

while (flag == false){
	
	selectedProgramInfo = CustomKeywords.'bridgeathletic.program.getProgramInfoInLibrary'(tmp)
	
	if (selectedProgramInfo.memberNumbers >= 20){
		
		CustomKeywords.'bridgeathletic.program.selectProgramInLibraryByIndex'(tmp)
		
		flag = true
		
	}
	
	tmp = tmp + 1
	
}

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

selectedProgramInfo = CustomKeywords.'bridgeathletic.program.getAssignedProgramInfoInDetail'()

WebUI.callTestCase(findTestCase('Done/Commons/Navigate To Team'), [var_teamName:selectedProgramInfo.teamName])

//WebUI.click(findTestObject('Breadcrumbs/button_Home'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Org Home Page/span_TEAMS'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//TestObject selectedTeamObject = new TestObject()
//
//selectedTeamObject.addProperty('xpath', ConditionType.EQUALS, '//span[@ng-click="!isEditing&&goToTeam(team.id)" and text()="'+ (selectedProgramInfo.teamName).toUpperCase() +'"]', true)
//
//WebUI.click(selectedTeamObject)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Team Home Page/span_See All_Roster-module'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Roster Page/button_role'))
//
//WebUI.click(findTestObject('Roster Page/a_Coaches'))
//
//WebUI.delay(2)
//
//int totalMembers = CustomKeywords.'bridgeathletic.total.getItemSize'('//li[contains(@class,"team-roster-item ng-scope")]')
//
//List<user> userList = []
//
//for (int i = 1; i <= totalMembers; i = i + 1){
//	
//	userList.add(CustomKeywords.'bridgeathletic.user.getUserInfo'(i))
//	
//}
//
//int rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(userList.size() - 1)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Logout'), [:])
//
//WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : userList[rd].email, ('password') : '23456789'], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//CustomKeywords.'bridgeathletic.select.selectProgramType'(programType)
//
//TestObject programSelectObject = new TestObject()
//
//programSelectObject.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]/div[@class="items name ng-scope"]/span[text()="'+ selectedProgramInfo.programName +'"]', true)
//
//WebUI.click(programSelectObject)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//TestObject treeDotCurrentPhaseObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.currentPhase)
//
//WebUI.click(treeDotCurrentPhaseObject)
//
//WebUI.click(findTestObject('A Program Page/li_Clone Phase'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//if (WebUI.verifyElementPresent(findTestObject('A Program Page/md-dialog_first-change-dialog'), 3, FailureHandling.OPTIONAL)){
//	
//	WebUI.click(findTestObject('A Program Page/md-checkbox_first-change-dialog'))
//	
//	WebUI.click(findTestObject('A Program Page/button_OK_first-change-dialog'))
//	
//}
//
//int totalPhaseNumbers = CustomKeywords.'bridgeathletic.phase.getTotalPhaseNumbersInDetail'()
//
//if (totalPhaseNumbers != selectedProgramInfo.phaseNumbers){
//	
//	List<phase> phaseList = []
//	
//	for (int i = 1; i <= totalPhaseNumbers; i = i + 1){
//		
//		phaseList.add(CustomKeywords.'bridgeathletic.phase.getPhaseInfoInDetail'(i))
//		
//	}
//	
//	selectedProgramInfo.phaseNumbers = totalPhaseNumbers
//	
//	selectedProgramInfo.phaseList = phaseList
//	
//}
//
//TestObject treeDotLastPhaseObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByName'(selectedProgramInfo.phaseList[selectedProgramInfo.phaseNumbers - 1].phaseName)
//
//WebUI.click(treeDotLastPhaseObject)
//
//WebUI.click(findTestObject('A Program Page/li_Edit NameNote'))
//
//String randomText = CustomKeywords.'bridgeathletic.random.getRandomName'('Next Phase ')
//
//WebUI.setText(findTestObject('A Program Page/input_phase-name'), randomText)
//
//WebUI.click(findTestObject('A Program Page/i_icon-update-phase'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//selectedProgramInfo.phaseList[selectedProgramInfo.phaseNumbers - 1].phaseName = randomText
//
//TestObject sourceObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByIndex'(selectedProgramInfo.phaseNumbers)
//
//int currentPhaseIndex = CustomKeywords.'bridgeathletic.phase.getPhaseIndexByName'(selectedProgramInfo.currentPhase)
//
//TestObject destinationObject = CustomKeywords.'bridgeathletic.phase.getTreeDotPhaseObjectByIndex'(currentPhaseIndex + 1)
//
//WebUI.dragAndDropToObject(sourceObject, destinationObject)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Breadcrumbs/button_DELIVER TO ATHLETES'))
//
//WebUI.waitForElementPresent(findTestObject('Commons/Deliver Dialog/dialog_Deliver'), 30)
//
//WebUI.click(findTestObject('Commons/Deliver Dialog/button_Close'))
//
//selectedProgramInfo.phaseList = CustomKeywords.'bridgeathletic.phase.getPhaseList'(totalPhaseNumbers)
//
//int nextPhaseIndex = CustomKeywords.'bridgeathletic.phase.getPhaseIndexByName'(randomText)
//
//String a = "window.open('http://stg.bridgeathletic.com', '');"
//
//WebDriver driver = DriverFactory.getWebDriver()
//
//((JavascriptExecutor)driver).executeScript(a)
//
//WebUI.switchToWindowIndex(1)
//
//WebUI.waitForElementPresent(findTestObject('Header/div_User Details'), 30)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Org Home Page/span_TEAMS'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(selectedTeamObject)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.click(findTestObject('Team Home Page/span_See All_Roster-module'))
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(selectedProgramInfo.memberNumbers)
//
//Object randomAthlete  = new TestObject()
//
//randomAthlete.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewMemberProfile(athlete)"]//span[contains(text(),"'+ selectedProgramInfo.memberList[rd - 1].split(" ")[1] +'")]', true)
//
//WebUI.click(randomAthlete)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
//
//WebUI.waitForElementPresent(findTestObject('Athlete Profile Page/div_fc-content-calendar'), 30)
//
//CustomKeywords.'bridgeathletic.calendar.selectDateInCalendar'(selectedProgramInfo.phaseList[nextPhaseIndex - 1].startDate)
//
//String expectStartPhaseName = selectedProgramInfo.phaseList[nextPhaseIndex - 1].phaseName + ' week 1/' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers
//
//WebUI.verifyTextPresent(expectStartPhaseName, false)
//
//CustomKeywords.'bridgeathletic.calendar.selectDateInCalendar'(selectedProgramInfo.phaseList[nextPhaseIndex - 1].endDate)
//
//String expectEndPhaseName = selectedProgramInfo.phaseList[nextPhaseIndex - 1].phaseName + ' week ' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers + '/' + selectedProgramInfo.phaseList[nextPhaseIndex - 1].weekNumbers
//
//WebUI.verifyTextPresent(expectEndPhaseName, false)
//
//WebUI.callTestCase(findTestCase('Done/Commons/Logout'), [:])
//
//WebUI.closeBrowser()