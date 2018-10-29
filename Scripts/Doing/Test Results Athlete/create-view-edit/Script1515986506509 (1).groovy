import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.bridgeathletic.teamInfo as teamInfo
import com.bridgeathletic.testResultInfo
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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
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

WebUI.click(findTestObject('Header/a_Organization-13'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

'You will land on the organization homepage'
WebUI.click(findTestObject('Org Home Page/span_TEAMS'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

int totalTeamNumbers = CustomKeywords.'bridgeathletic.total.getItemSize'('//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content')

List<teamInfo> teamList = []

for (int i = 1; i <= totalTeamNumbers; i = (i + 1)) {
	
	Object teamInfo = teamInfo
	
	teamInfo = CustomKeywords.'bridgeathletic.info.getTeamInfo'(i)

	if ((teamInfo.athleteNumbers >= 2) && (teamInfo.athleteNumbers <= 5)) {
		
		teamList.add(teamInfo)
		
	}
	
}

int rd = CustomKeywords.'bridgeathletic.random.getRandomNumber'(teamList.size() - 1)

TestObject teamName = new TestObject()

teamName.addProperty('xpath', ConditionType.EQUALS, '//span[@class = "ng-binding" and text() = "' + teamList[rd].teamName +
		'"]', true)

WebUI.click(teamName)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.click(findTestObject('Team Home Page/button_Enter-test-results'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<testResultInfo> testResultList = []

List<Integer> testDateList = [-7, -6, -3, -2, 0]

for (int i = 0; i < testDateList.size(); i = i + 1){

	WebUI.click(findTestObject('New Test Page/button_select-exercise'))

	WebUI.waitForElementPresent(findTestObject('New Test Page/md-dialog_Choose-exercise'), 30)

	WebUI.setText(findTestObject('New Test Page/input_search-text'), 'Squat')

	String exerciseName = 'Squat'

	WebUI.delay(2)

	boolean flag = false

	int tmp = 1

	while (flag == false) {
		TestObject exerciseObj = new TestObject('exerciseObj')

		exerciseObj.addProperty('xpath', ConditionType.EQUALS, ('(id("exercise-switch-new")//div[@ng-repeat="ex in exerciseCollection.organizationExercise track by $index"]/span[@class="ng-binding"])[' +
				tmp) + ']', true)

		String tmpExerciseName = WebUI.getText(exerciseObj).trim()

		if (tmpExerciseName == 'Squat') {
			WebUI.click(exerciseObj)

			flag = true
		}

		flag = flag

		tmp = (tmp + 1)
	}

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	WebUI.click(findTestObject('New Test Page/span_test-for_Weight'))

	String testForName = WebUI.getText(findTestObject('New Test Page/span_test-for_Weight'))
	
	String optionValue = WebUI.getAttribute(findTestObject('New Test Page/select_test-for-unit'), 'value')
	
	TestObject optionSelected = new TestObject('Option Selected')
	
	optionSelected.addProperty('xpath', ConditionType.EQUALS, '//select[@ng-model = "testForItem.unitSel"]/option[@value="'+ optionValue +'"]', true)
	
	String testForUnit = WebUI.getText(optionSelected)
	
	if (testForUnit == ''){
		
		testForUnit = testForName.toLowerCase()
		
	}

	WebUI.click(findTestObject('New Test Page/span_param_Reps'))

	String parameterName = WebUI.getText(findTestObject('New Test Page/span_param_Reps'))
	
	optionValue = WebUI.getAttribute(findTestObject('New Test Page/select_parameter-unit'), 'value')
	
	WebUI.removeObjectProperty(optionSelected, 'xpath')
	
	optionSelected.addProperty('xpath', ConditionType.EQUALS, '//select[@ng-model = "selectedParam.unitSel"]/option[@value="'+ optionValue +'"]', true)

	String parameterUnit = WebUI.getText(optionSelected)
	
	if (parameterUnit == ''){
		
		parameterUnit = parameterName.toLowerCase()
		
	}
	
	WebUI.setText(findTestObject('New Test Page/input_Param-value'), '5')

	float parameterValue = 5

	String testDate = CustomKeywords.'bridgeathletic.convert.convertFromDate'(null, null, 'MMddyyyy', testDateList[i], 'Month')

	WebUI.sendKeys(findTestObject('New Test Page/input_testDate'), testDate)

	testDate = CustomKeywords.'bridgeathletic.convert.convertFormatDate'(testDate, 'MMddyyyy', 'MM/dd/yy')
	
	WebUI.click(findTestObject('New Test Page/button_Continue'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

	tmp = 1

	while (tmp <= teamList[rd].athleteNumbers) {

		TestObject testValueObj = new TestObject('testValue')

		testValueObj.addProperty('xpath', ConditionType.EQUALS, '(//input[@ng-model = "member.testValueDisplay"])['+ tmp +']', true)

		TestObject athleteNameObj = new TestObject('athleteName')

		athleteNameObj.addProperty('xpath', ConditionType.EQUALS, '(//md-list-item[contains(@ng-repeat,"member in newtest.athletes")]//span[@class="ng-binding"])['+ tmp +']', true)

		String athleteName = WebUI.getText(athleteNameObj)
		
		float testValue = CustomKeywords.'bridgeathletic.random.getRandomNumberIntoRange'(75, 100)

		WebUI.setText(testValueObj, Float.toString(testValue))
		
		TestObject RMEObj = new TestObject('athleteName')
		
		RMEObj.addProperty('xpath', ConditionType.EQUALS, '(//md-list-item[contains(@ng-repeat,"member in newtest.athletes")]//div[@ng-show="show1RME"])['+ tmp +']', true)

		float RME
		
		if (WebUI.getText(RMEObj) == ''){
			
			RME = 0
			
		}
		else {
			
			RME = Float.parseFloat(WebUI.getText(RMEObj))
			
		}
		
		Object testResultInfo = new testResultInfo()
		
		testResultInfo.setTestResultInfo(exerciseName, testForUnit, parameterUnit, parameterValue, testDate, athleteName, testValue, RME)

		testResultList.add(testResultInfo)

		System.out.println('Exercise Name: ' + testResultInfo.exerciseName + '\nTest For Unit: ' + testResultInfo.testForUnit + '\nParameter Unit: ' + testResultInfo.parameterUnit + '\nParameter Value: ' + testResultInfo.parameterValue + '\nTest Date: ' + testResultInfo.testDate + '\nAthlete Result Name: ' + testResultInfo.athleteName + '\nAthlete Result Value: ' + testResultInfo.testValue + '\nAthlete RME Value: ' + testResultInfo.RME)
		
		tmp = tmp + 1

	}

	WebUI.click(findTestObject('New Test Page/button_submit'))

	if (i == 4){

		WebUI.waitForElementPresent(findTestObject('New Test Page/button_team-overview'), 30)

		WebUI.click(findTestObject('New Test Page/button_team-overview'))

	}

	else{

		WebUI.waitForElementPresent(findTestObject('New Test Page/button_create-another-test'), 30)

		WebUI.click(findTestObject('New Test Page/button_create-another-test'))

	}
	
	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

}

println testResultList.size()

WebUI.click(findTestObject('Team Home Page/custom-select_team-leader-search'))

WebUI.delay(1)

WebUI.click(findTestObject('Team Home Page/span_Squat'))

WebUI.delay(1)

List<testResultInfo> testResultInfoInTeamLeaderList = []

testResultInfoInTeamLeaderList = CustomKeywords.'bridgeathletic.info.getTestResultInfoInTeamLeaderModule'()

for (int i = 0; i < testResultInfoInTeamLeaderList.size(); i = i + 1){
	
	boolean flag = false
	
	int j = 0
	
	while (flag == false){
		
		if (testResultInfoInTeamLeaderList[i].exerciseName == testResultList[j].exerciseName && testResultInfoInTeamLeaderList[i].testForUnit == testResultList[j].testForUnit && testResultInfoInTeamLeaderList[i].parameterUnit == testResultList[j].parameterUnit && testResultInfoInTeamLeaderList[i].parameterValue == testResultList[j].parameterValue && testResultInfoInTeamLeaderList[i].testDate == testResultList[j].testDate && testResultInfoInTeamLeaderList[i].athleteName == testResultList[j].athleteName && testResultInfoInTeamLeaderList[i].testValue == testResultList[j].testValue){
			
			flag = true
			
			println 'List Number: ' + j
			
		}
		
		j = j + 1
		
	}
	
}

rd = CustomKeywords.'bridgeathletic.random.getRandomNumberIntoRange'(0, testResultList.size() - 1)

List<testResultInfo> testResultInfoAthlete = []

for (int i = 0; i < testResultList.size(); i = i + 1){
	
	if (testResultList[i].athleteName == testResultList[rd].athleteName){
		
		testResultInfoAthlete.add(testResultList[i])
		
	}
	
}

println 'Test Result Info Athlete List: ' + testResultInfoAthlete.size()

TestObject randomAthleteObj = new TestObject('Random Athlete Object')

randomAthleteObj.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-show="test.showRow"]/div[@ng-attr-flex="{{flexs[1]}}" and text()="'+ testResultList[rd].athleteName +'"]', true)

WebUI.click(randomAthleteObj)

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

List<testResultInfo> testResultInfoTable = CustomKeywords.'bridgeathletic.info.getTestResultInfoInTestHistoryTable'()

List<testResultInfo> testResultInfoChart = CustomKeywords.'bridgeathletic.info.getTestResultInfoInHighCharts'()

WebUI.verifyEqual(CustomKeywords.'bridgeathletic.testResultInfo.verifyTestResultInfoListContains'(testResultInfoAthlete, testResultInfoTable), true, FailureHandling.STOP_ON_FAILURE)


