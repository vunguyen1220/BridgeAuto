import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat

import bridgeathletic.eventInfo as eventInfo
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

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.gl_adminEmail, ('password') : GlobalVariable.gl_adminPassword], 
    FailureHandling.STOP_ON_FAILURE)

'Navigate to a random organization'
WebUI.click(findTestObject('Header/input_currentOrg'))

WebUI.click(findTestObject('Header/a_Organization-2'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
GlobalVariable.Coach_Username = WebUI.callTestCase(findTestCase('Done/Commons/Get Coach Email From Team'), [:], FailureHandling.STOP_ON_FAILURE)

System.out.println('Coach Email: ' + GlobalVariable.Coach_Username)

WebUI.click(findTestObject('Header/div_User Details'))

WebUI.delay(1)

WebUI.click(findTestObject('Header/i_sign-out'))

'Login as the coach from previous step'
WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('email') : GlobalVariable.Coach_Username, ('password') : '23456789'], 
    FailureHandling.STOP_ON_FAILURE)

'You will land on the organization homepage'
WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'Find a team with athletes'
int totalTeamNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content')

Object randomTeam = teamInfo

for (int i = 1; i <= totalTeamNumbers; i = (i + 1)) {
    randomTeam = CustomKeywords.'bridgeathletic.info.getTeamInfo'(i)

    if (randomTeam.athleteNumbers >= 2) {
        break
    }
}

TestObject teamName = new TestObject()

teamName.addProperty('xpath', ConditionType.EQUALS, ('//span[@class = "ng-binding" and text() = "' + randomTeam.teamName) + 
    '"]', true)

'Navigate to the team page'
WebUI.click(teamName)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'On the team page, the events module should display info for upcoming events'
WebUI.verifyElementPresent(findTestObject('Team Home Page/md-card_team-competitions'), 2, FailureHandling.STOP_ON_FAILURE)

List<Integer> eventDate = []

List<Integer> expectEventName = []

/*Create a new event: Test Event
Event should start tomorrow and end next week
Add other optional information to the event
Assign a few athletes to the event
Using the apply all functionality, select and use the same importance for all the athletes
Create another event: Test Event 2
The event is a one day event, scheduled in 2 weeks
Add other optional information to the event
Assign all the athletes to the event
Using the apply all functionality, select and use the same importance for all the athletes
Change the importance for some athletes
Save the event*/
for (int tmp = 1; tmp <= 2; tmp = (tmp + 1)) {
    String randomName = CustomKeywords.'bridgeathletic.random.getRandomName'(' ')

    WebUI.click(findTestObject('Team Home Page/button_team-competitions-add-new'))

    WebUI.waitForElementPresent(findTestObject('Team Home Page/md-dialog_add-conpetition'), 30, FailureHandling.STOP_ON_FAILURE)

    WebUI.setText(findTestObject('Team Home Page/input_new-competition-name'), ('Test Event ' + tmp) + randomName)

    expectEventName.add(('Test Event ' + tmp) + randomName)

    eventDate.addAll(WebUI.callTestCase(findTestCase('Done/Commons/Calendar'), [('option') : 'event' + tmp], FailureHandling.STOP_ON_FAILURE))

    WebUI.setText(findTestObject('Team Home Page/input_new-competition-startDate'), eventDate[((tmp - 1) * tmp)])

    WebUI.setText(findTestObject('Team Home Page/input_new-competition-endDate'), eventDate[(((tmp - 1) * tmp) + 1)])

    WebUI.click(findTestObject('Team Home Page/a_new-competition-addAllAthletes'))

    WebUI.waitForElementPresent(findTestObject('Team Home Page/custom-select_new-competition-importance'), 30, FailureHandling.STOP_ON_FAILURE)

    int randomImportanceLevel = CustomKeywords.'bridgeathletic.random.getRandomNumber'(5)

    for (int i = 1; i <= randomTeam.athleteNumbers; i = (i + 1)) {
        if (tmp == 2) {
            randomImportanceLevel = CustomKeywords.'bridgeathletic.random.getRandomNumber'(5)
        }
        
        TestObject importanceLevel = new TestObject()

        importanceLevel.addProperty('xpath', ConditionType.EQUALS, ('(//li[@ng-repeat="item in sortedItems"])[' + randomImportanceLevel) + 
            ']', true)

        TestObject athleteImportance = new TestObject()

        athleteImportance.addProperty('xpath', ConditionType.EQUALS, ('(//custom-select[@model = "addingAthlete.importance"])[' + 
            i) + ']', true)

        WebUI.click(athleteImportance)

        WebUI.delay(1)

        WebUI.click(importanceLevel)
    }
    
    WebUI.click(findTestObject('Team Home Page/button_Save'))

    WebUI.delay(2)
}

System.out.println(eventDate)

WebUI.click(findTestObject('Team Home Page/span_See All_Events-module'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<Integer> eventList = []

int totalEventNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//div[@class = "competition-item ng-scope"]')

for (int i = 1; i <= totalEventNumbers; i = (i + 1)) {
    TestObject toggleButton = new TestObject()

    toggleButton.addProperty('xpath', ConditionType.EQUALS, ('(//i[@ng-click = "toggle()"])[' + i) + ']', true)

    WebUI.click(toggleButton)

    Object eventItemInfo = eventInfo

    eventItemInfo = CustomKeywords.'bridgeathletic.info.getEventInfo'(i)

    if (expectEventName.contains(eventItemInfo.eventName)) {
        eventList.add(eventItemInfo)
    }
}

System.out.println('Event List size: ' + eventList.size())

/*Navigate to the profile page of two athletes and confirm that the events were created successfully
Make sure the events are displayed on the profile page >> events module
Make sure the events are displayed on the profile page >> calendar module
Hover of the event on the calendar >> the name of the event should be displayed*/
for (int i = 0; i < eventList.size(); i = (i + 1)) {
    int randomNumber = CustomKeywords.'bridgeathletic.random.getRandomNumber'(eventList[i].athleteNumbers) - 1

    WebUI.click(findTestObject('Breadcrumbs/span_select-team'))

    WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

    WebUI.click(findTestObject('Team Home Page/button_see-all-team-roster'))

    WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

    TestObject randomAthleteMember = new TestObject()

    randomAthleteMember.addProperty('xpath', ConditionType.EQUALS, ('//div[@ng-click="viewMemberProfile(athlete)"]/span[text()="' + 
        eventList[i].importanceList[randomNumber].athleteName) + '"]', true)

    WebUI.click(randomAthleteMember)

    WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

    WebUI.waitForElementPresent(findTestObject('Athlete Profile Page/div_fc-content-calendar'), 30)

    int tmp = 1

    int matchNumber = 1

    boolean flag = false

    while (matchNumber <= 2) {
        TestObject eventNameObject = new TestObject()

        eventNameObject.addProperty('xpath', ConditionType.EQUALS, ('(//strong[@class="name ng-binding"])[' + tmp) + ']', 
            true)

        String eventName = WebUI.getText(eventNameObject)

        if (expectEventName.contains(eventName)) {
            matchNumber = (matchNumber + 1)
        }
        
        tmp = (tmp + 1)
    }
    
    tmp = 0

    while (tmp < 4) {
        DateFormat input = new SimpleDateFormat('MM/dd/yyyy')

        DateFormat output = new SimpleDateFormat('MMM dd yyyy')

        String selectDate = output.format(input.parse(eventDate[tmp]))

        TestObject selectDayObject = WebUI.callTestCase(findTestCase('Done/Commons/SelectDayInCalendar'), [('startDate') : selectDate.substring(
                    0, 6), ('expectYear') : Integer.parseInt(selectDate.substring(7, 11))], FailureHandling.STOP_ON_FAILURE)

        WebUI.mouseOver(selectDayObject)

        List<Integer> tooltipEventName = WebUI.getText(findTestObject('Athlete Profile Page/div_event-tooltip')).split('\n')

        tooltipEventName.retainAll(expectEventName)

        System.out.println(expectEventName)

        System.out.println(tooltipEventName)

        System.out.println(flag)

        WebUI.verifyGreaterThanOrEqual(tooltipEventName.size(), 1, FailureHandling.STOP_ON_FAILURE)

        WebUI.delay(1)

        tmp = (tmp + 1)
    }
    
    WebUI.delay(1)
}

WebUI.delay(1)

'From the athlete page (Athlete A), open Test Event 2 and change the importance for the athlete'
WebUI.click(findTestObject('Athlete Profile Page/button_see-all-event'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(eventList[1].editButton)

WebUI.click(findTestObject('Events Page/button_importances_edit'))

int randomLevel = CustomKeywords.'bridgeathletic.random.getRandomNumber'(4)

TestObject randomLevelObject = new TestObject()

randomLevelObject.addProperty('xpath', ConditionType.EQUALS, ('(//i[@class="fa fa-check ng-hide"]/parent::a)[' + randomLevel) + 
    ']', true)

WebUI.click(randomLevelObject)

WebUI.delay(1)

String athleteName = WebUI.getText(findTestObject('Events Page/div_athlete-name_edit'))

boolean flag = false

int tmp = 0

while (flag == false) {
    if (WebUI.verifyMatch(eventList[1].importanceList[tmp].athleteName, athleteName, false, FailureHandling.OPTIONAL)) {
        System.out.println('Old level: ' + eventList[1].importanceList[tmp].eventImportance)

        eventList[1].importanceList[tmp].eventImportance = WebUI.getText(findTestObject('Events Page/button_importances_edit'))

        flag = true

        System.out.println('Update level: ' + eventList[1].importanceList[tmp].eventImportance)
    }
    
    tmp = (tmp + 1)
}

WebUI.click(findTestObject('Events Page/button_Save_edit'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.refresh()

'Navigate back to the team page'
WebUI.click(findTestObject('Breadcrumbs/span_select-team'))

WebUI.click(findTestObject('Team Home Page/span_See All_Events-module'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

for (int i = 1; i <= totalEventNumbers; i = (i + 1)) {
    TestObject toggleButton = new TestObject()

    toggleButton.addProperty('xpath', ConditionType.EQUALS, ('(//i[@ng-click = "toggle()"])[' + i) + ']', true)

    WebUI.click(toggleButton)

    Object eventItemInfo = eventInfo

    eventItemInfo = CustomKeywords.'bridgeathletic.info.getEventInfo'(i)

    'Open the events module, select on Test Event 2'

    'Confirm that the importance level for Athlete A was updated'
    if (eventItemInfo.eventName == eventList[1].eventName) {
        WebUI.verifyEqual(eventItemInfo.eventName, eventList[1].eventName, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.city, eventList[1].city, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.streetAddress, eventList[1].streetAddress, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.state, eventList[1].state, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.zipCode, eventList[1].zipCode, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.startDate, eventList[1].startDate, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.endDate, eventList[1].endDate, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyEqual(eventItemInfo.athleteNumbers, eventList[1].athleteNumbers, FailureHandling.STOP_ON_FAILURE)

        for (tmp = 0; tmp < eventItemInfo.importanceList.size(); tmp = (tmp + 1)) {
            WebUI.verifyEqual(eventItemInfo.importanceList[tmp].athleteName, eventList[1].importanceList[tmp].athleteName, 
                FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyEqual(eventItemInfo.importanceList[tmp].eventImportance, eventList[1].importanceList[tmp].eventImportance, 
                FailureHandling.STOP_ON_FAILURE)
        }
        
        break
    }
}

'Edit the event information, remove Athlete A and a couple other athletes from Test Event 2'
WebUI.click(eventList[1].editButton)

TestObject delAthleteButton = new TestObject()

delAthleteButton.addProperty('xpath', ConditionType.EQUALS, ('//div[@class="competition-item ng-scope competition-highlight"]//div[text()="' + 
    athleteName) + '"]/i[@aria-hidden="false"]', true)

WebUI.click(delAthleteButton)

WebUI.waitForElementNotPresent(delAthleteButton, 30)

WebUI.click(findTestObject('Events Page/button_Save_edit'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Breadcrumbs/span_select-team'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Team Home Page/button_see-all-team-roster'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

TestObject AthleteMember = new TestObject()

AthleteMember.addProperty('xpath', ConditionType.EQUALS, ('//div[@ng-click="viewMemberProfile(athlete)"]/span[text()="' + 
    athleteName) + '"]', true)

'Navigate to the profile page of Athlete A and confirm that the event was removed'
WebUI.click(AthleteMember)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.waitForElementPresent(findTestObject('Athlete Profile Page/div_fc-content-calendar'), 30)

totalEventNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//strong[@class="name ng-binding"]')

tmp = 1

while (tmp <= totalEventNumbers) {
    TestObject eventNameObject = new TestObject()

    eventNameObject.addProperty('xpath', ConditionType.EQUALS, ('(//strong[@class="name ng-binding"])[' + tmp) + ']', true)

    String eventName = WebUI.getText(eventNameObject)

    WebUI.verifyNotMatch(eventName, eventList[1].eventName, false, FailureHandling.STOP_ON_FAILURE)

    tmp = (tmp + 1)
}

'Logout'
WebUI.click(findTestObject('Header/div_User Details'))

WebUI.delay(1)

WebUI.click(findTestObject('Header/i_sign-out'))

WebUI.closeBrowser()

