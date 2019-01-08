import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import bridgeathletic.attendanceInfo as attendanceInfo
import bridgeathletic.teamInfo as teamInfo

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

'Login as Super Admin'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword], 
    FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-2'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
GlobalVariable.Coach_Username = WebUI.callTestCase(findTestCase('Done/Commons/GetCoachEmail'), [:], FailureHandling.STOP_ON_FAILURE)

System.out.println('Coach Email: ' + GlobalVariable.Coach_Username)

WebUI.click(findTestObject('Header/div_User Details'))

WebUI.delay(1)

WebUI.click(findTestObject('Header/i_sign-out'))

'Login as the coach from previous step'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.Coach_Username, ('password') : '23456789'], 
    FailureHandling.STOP_ON_FAILURE)

'Click on "See All" at the bottom of the Members module'
WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Get all teams'
int totalTeamNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content')

'Locate your team (a team with an active program)'
Object randomTeam = teamInfo

for (int i = 1; i <= totalTeamNumbers; i = (i + 1)) {
    randomTeam = CustomKeywords.'bridgeathletic.info.getTeamInfo'(i)

    if ((randomTeam.athleteNumbers > 0) && (randomTeam.activeProgramNumbers > 0)) {
        break
    }
}

TestObject teamName = new TestObject()

teamName.addProperty('xpath', ConditionType.EQUALS, ('//span[@class = "ng-binding" and text() = "' + randomTeam.teamName) + 
    '"]', true)

'Click on your team'
WebUI.click(teamName)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'You should now see the Team Page'

'Locate the Roster Module'
String headerText = WebUI.getText(findTestObject('Team Home Page/div_team-roster-content-header')).replace('\n', ' ')

'You should see the titles: NAME     STATUS     CURRENT PROGRAM   ATTENDANCE  |  "Phase Name"'

'Look at: ATTENDANCE  |  "Phase Name"   ---  The phase name is the default time frame.'
WebUI.verifyMatch(headerText, 'NAME STATUS CURRENT PROGRAM ATTENDANCE | Phase', false, FailureHandling.STOP_ON_FAILURE)

List<Integer> expectAttendanceOption = ['Program', 'Phase', 'Month', 'Week', 'Custom']

List<Integer> expectHeader = []

/*Click "See All" at the bottom of the roster module
You should now be on the Roster Page (Roster is visible in the Breadcrumbs)
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Program: You should see the name of the program
In the Breadcrumbs, click on your team name to navigate back to the team page
Check that the program name is listed next to ATTENDANCE
Click "See All" at the bottom of the roster module
You should now be on the Roster Page (Roster is visible in the Breadcrumbs)
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Phase: You should see the name of the Phase
In the Breadcrumbs, click on your team name to navigate back to the team page
Check that the phase name is listed next to attendance
Click "See All" at the bottom of the roster module
You should now be on the Roster Page (Roster is visible in the Breadcrumbs)
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Month: You should see the dates for the current Month
Double check the number of workouts correspond with the workouts assigned for the current month.
In the Breadcrumbs, click on your team name to navigate back to the team page
Check that the dates of the current month are listed next to attendance
Click "See All" at the bottom of the roster module
You should now be on the Roster Page (Roster is visible in the Breadcrumbs)
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Week: You should see the dates for the current week
Double check the number of workouts correspond with the workouts assigned for the current week.
In the Breadcrumbs, click on your team name to navigate back to the team page
Check that the dates of the current week are listed next to attendance*/
for (int i = 0; i <= 3; i = (i + 1)) {
    WebUI.click(findTestObject('Team Home Page/button_see-all-team-roster'))

    WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

    WebUI.verifyMatch(WebUI.getText(findTestObject('Breadcrumbs/span_last-item')), 'ROSTER', false, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Roster Page/span_ATTENDANCE'))

    List<Integer> actualAttendanceOption = WebUI.getText(findTestObject('Roster Page/ul_attendances open')).split('\n')

    WebUI.verifyEqual(actualAttendanceOption, expectAttendanceOption, FailureHandling.STOP_ON_FAILURE)

    TestObject selectOption = WebUI.modifyObjectProperty(findTestObject('Roster Page/li_item in attendances_Program'), 'text', 
        'equals', expectAttendanceOption[i], true)

    WebUI.click(selectOption)

    WebUI.delay(1)

    if ((expectAttendanceOption[i]) == 'Month') {
        expectHeader.clear()

        expectHeader = WebUI.callTestCase(findTestCase('Done/Commons/Calendar'), [('option') : expectAttendanceOption[i]], 
            FailureHandling.STOP_ON_FAILURE)
    } else if ((expectAttendanceOption[i]) == 'Week') {
        expectHeader.clear()

        expectHeader = WebUI.callTestCase(findTestCase('Done/Commons/Calendar'), [('option') : expectAttendanceOption[i]], 
            FailureHandling.STOP_ON_FAILURE)
    } else {
        expectHeader.clear()

        expectHeader.add(expectAttendanceOption[i])
    }
    
    System.out.println(expectHeader)

    List<Integer> teamRosterAttendanceInfo = []

    for (int j = 1; j <= randomTeam.athleteNumbers; j = (j + 1)) {
        teamRosterAttendanceInfo.add(CustomKeywords.'bridgeathletic.info.getAttendanceInfo'(j))

        if (j == 5) {
            break
        }
    }
    
    WebUI.click(findTestObject('Breadcrumbs/span_select-team'))

    WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

    headerText = WebUI.getText(findTestObject('Team Home Page/div_team-roster-content-header')).replace('\n', ' ')

    List<Integer> teamAttendanceInfo = []

    for (int j = 1; j <= randomTeam.athleteNumbers; j = (j + 1)) {
        teamAttendanceInfo.add(CustomKeywords.'bridgeathletic.info.getAttendanceInfo'(j))

        if (j == 5) {
            break
        }
    }
    
    WebUI.verifyMatch(headerText, 'NAME STATUS CURRENT PROGRAM ATTENDANCE | ' + (expectHeader[0]), false, FailureHandling.STOP_ON_FAILURE)

    for (int j = 0; j < teamAttendanceInfo.size(); j = (j + 1)) {
        WebUI.verifyEqual(teamAttendanceInfo[j].athleteName, teamRosterAttendanceInfo[j].athleteName, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(teamAttendanceInfo[j].currentProgram, teamRosterAttendanceInfo[j].currentProgram, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(teamAttendanceInfo[j].completedWorkout, teamRosterAttendanceInfo[j].completedWorkout, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(teamAttendanceInfo[j].incompleteWorkout, teamRosterAttendanceInfo[j].incompleteWorkout, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(teamAttendanceInfo[j].scheduledWorkout, teamRosterAttendanceInfo[j].scheduledWorkout, FailureHandling.STOP_ON_FAILURE)
    }
}

'Click on an athletes name to view their profile page'
WebUI.click(findTestObject('Team Home Page/div_athlete-has-current-program-name'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

String expectHeaderText = (WebUI.getText(findTestObject('Athlete Profile Page/b_ATTENDANCE')) + WebUI.getText(findTestObject(
        'Athlete Profile Page/span_'))) + (expectHeader[1])

headerText = ((WebUI.getText(findTestObject('Athlete Profile Page/b_ATTENDANCE')) + WebUI.getText(findTestObject('Athlete Profile Page/span_'))) + 
WebUI.getText(findTestObject('Athlete Profile Page/span_header-text')))

WebUI.verifyMatch(headerText, expectHeaderText, false, FailureHandling.STOP_ON_FAILURE)

/*The most recent time frame you selected should be located next to "ATTENDANCE"
If you are following this flow, you should see "ATTENDANCE  |  "dates of current week"
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Program: You should see the name of the program
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Phase: You should see the name of the Phase
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Month: You should see the dates of the current month
Click on the word "ATTENDANCE" 
A dropdown menu appears with "Program, Phase, Month, Week and Custom" 
Select Week: You should see dates of the current week*/
for (int i = 0; i <= 3; i = (i + 1)) {
    WebUI.click(findTestObject('Athlete Profile Page/b_ATTENDANCE'))

    List<Integer> actualAttendanceOption = WebUI.getText(findTestObject('Athlete Profile Page/ul_attendances open')).split(
        '\n')

    WebUI.verifyEqual(actualAttendanceOption, expectAttendanceOption, FailureHandling.STOP_ON_FAILURE)

    TestObject selectOption = WebUI.modifyObjectProperty(findTestObject('Athlete Profile Page/li_item in attendances'), 
        'text', 'equals', expectAttendanceOption[i], true)

    WebUI.click(selectOption)

    WebUI.delay(1)

    if ((expectAttendanceOption[i]) == 'Month') {
        expectHeader.clear()

        expectHeader = WebUI.callTestCase(findTestCase('Done/Commons/Calendar'), [('option') : expectAttendanceOption[i]], 
            FailureHandling.STOP_ON_FAILURE)
    } else if ((expectAttendanceOption[i]) == 'Week') {
        expectHeader.clear()

        expectHeader = WebUI.callTestCase(findTestCase('Done/Commons/Calendar'), [('option') : expectAttendanceOption[i]], 
            FailureHandling.STOP_ON_FAILURE)
    } else {
        expectHeader.clear()

        expectHeader.add(expectAttendanceOption[i])

        expectHeader.add(expectAttendanceOption[i])
    }
    
    expectHeaderText = ((WebUI.getText(findTestObject('Athlete Profile Page/b_ATTENDANCE')) + WebUI.getText(findTestObject(
            'Athlete Profile Page/span_'))) + (expectHeader[1]))

    headerText = ((WebUI.getText(findTestObject('Athlete Profile Page/b_ATTENDANCE')) + WebUI.getText(findTestObject('Athlete Profile Page/span_'))) + 
    WebUI.getText(findTestObject('Athlete Profile Page/span_header-text')))

    WebUI.verifyMatch(headerText, expectHeaderText, false, FailureHandling.STOP_ON_FAILURE)
}

WebUI.closeBrowser()