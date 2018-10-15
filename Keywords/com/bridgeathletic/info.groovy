package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat
import java.util.ArrayList

import org.junit.After
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.bridgeathletic.eventInfo.eventImportanceForAthlete
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.thoughtworks.selenium.condition.Condition

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI




class workoutInfo {

	String workoutName

	String workoutNote

	int blockNumber

	ArrayList<String> blockName

	String day

	String date

	int year

	def workoutInfo (workoutName, workoutNote, blockNumber, blockName, day, date, year) {

		this.workoutName = workoutName

		this.workoutNote = workoutNote

		this.blockNumber = blockNumber

		this.blockName = blockName

		this.day = day

		this.date = date

		this.year = year
	}
}

class blockInfo {

	String blockName

	int exerciseNumber

	ArrayList<String> exerciseName

	blockInfo (blockName, exerciseNumber, exerciseName){

		this.blockName = blockName

		this.exerciseName = exerciseName

		this.exerciseNumber = exerciseNumber
	}
}

class teamInfo {

	String teamName

	String teamSport

	int athleteNumbers

	int coachNumbers

	int activeProgramNumbers

	teamInfo (teamName, teamSport, athleteNumbers, coachNumbers, activeProgramNumbers) {

		this.teamName = teamName

		this.teamSport = teamSport

		this.athleteNumbers = athleteNumbers

		this.coachNumbers = coachNumbers

		this.activeProgramNumbers = activeProgramNumbers
	}
}


class attendanceInfo {

	String athleteName

	String currentProgram

	int completedWorkout

	int incompleteWorkout

	int scheduledWorkout

	attendanceInfo (athleteName, currentProgram, completedWorkout, incompleteWorkout, scheduledWorkout) {

		this.athleteName = athleteName

		this.currentProgram = currentProgram

		this.completedWorkout = completedWorkout

		this.incompleteWorkout = incompleteWorkout

		this.scheduledWorkout = scheduledWorkout
	}
}


class eventInfo {

	String eventName

	String streetAddress

	String city

	String state

	String zipCode

	String startDate

	String endDate

	int athleteNumbers

	List<eventImportanceForAthlete> importanceList = []

	TestObject editButton

	TestObject delButton

	eventInfo (eventName, streetAddress, city, state, zipCode, startDate, endDate, athleteNumbers, importanceList, editButton, delButton) {

		this.eventName = eventName

		this.streetAddress = streetAddress

		this.city = city

		this.state = state

		this.zipCode = zipCode

		this.startDate = startDate

		this.endDate = endDate

		this.athleteNumbers = athleteNumbers

		this.importanceList = importanceList

		this.editButton = editButton

		this.delButton = delButton
	}

	public static class eventImportanceForAthlete {

		String athleteName

		String eventImportance

		eventImportanceForAthlete (athleteName, eventImportance) {

			this.athleteName = athleteName

			this.eventImportance = eventImportance
		}
	}
}

class testResultInfo {

	String exerciseName

	String testForUnit

	String parameterUnit

	float parameterValue

	String testDate

	String athleteName

	float testValue

	float RME

	@Keyword
	public void setTestResultInfo(exerciseName, testForUnit, parameterUnit, parameterValue, testDate, athleteName, testValue, RME){

		this.exerciseName = exerciseName

		this.testForUnit = testForUnit

		this.parameterUnit = parameterUnit

		this.parameterValue = parameterValue

		this.testDate = testDate

		this.athleteName = athleteName

		this.testValue = testValue

		this.RME = RME
	}

	@Keyword
	def verifyTestResultInfoListContains(List<testResultInfo> actualList, List<testResultInfo> expectList){

		int match = 0

		for (int i = 0; i < actualList.size(); i = i + 1){

			for (int j = 0; j < expectList.size(); j = j + 1){

				if (actualList[i].exerciseName == expectList[j].exerciseName
				&& actualList[i].testForUnit == expectList[j].testForUnit
				&& actualList[i].parameterUnit == expectList[j].parameterUnit
				&& actualList[i].parameterValue == expectList[j].parameterValue
				&& actualList[i].testDate == expectList[j].testDate
				&& actualList[i].athleteName == expectList[j].athleteName
				&& actualList[i].testValue == expectList[j].testValue
				&& actualList[i].RME == expectList[j].RME){

					match = match + 1

					break
				}
			}
		}

		if (match == actualList.size()){

			return true
		}
		else{

			return false
		}
	}
}

public class info {

	WebDriver driver = DriverFactory.getWebDriver()



	@Keyword
	def getBlockInfo() {

		String randomBlockName = WebUI.getText(findTestObject('Commons/span_blockName'))

		int exerciseNumber = driver.findElements(By.xpath('//*[@class = "editor-block ng-scope"]')).size()

		ArrayList<String> exerciseName = new ArrayList<String>()

		for (int i = 1; i <= exerciseNumber; i++){

			TestObject name = WebUI.modifyObjectProperty(findTestObject('Commons/span_exerciseName'), 'xpath', 'equals', '//div[@class="editor-block ng-scope"]['+ i +']/span[@class="name ng-binding"]', true)

			exerciseName[i - 1] = WebUI.getText(name)
		}

		System.out.println('Random Block Name: ' + randomBlockName)

		System.out.println('Random Block Exercise Numbers: ' + exerciseNumber)

		System.out.println('Random Block Exercise Name: ' + exerciseName)

		return new blockInfo(randomBlockName, exerciseNumber, exerciseName)
	}

	@Keyword
	def getTemplateBlockInfo() {

		String templateBlockName = WebUI.getText(findTestObject('Commons/span_templateBlockName')).trim()

		int templateExerciseNumber = driver.findElements(By.xpath('id("template-block")/div[@class="exercise-list scrollable"]/div[@class="single-exercise ng-scope"]')).size()

		ArrayList<String> templateExerciseName = new ArrayList<String>()

		for (int i = 1; i <= templateExerciseNumber; i++){

			TestObject name = WebUI.modifyObjectProperty(findTestObject('Commons/span_exerciseNameList'), 'xpath', 'equals', 'id("template-block")/div[@class="exercise-list scrollable"]/div[@class="single-exercise ng-scope"]['+ i +']/span[@class="ng-binding"]', true)

			templateExerciseName[i - 1] = (WebUI.getText(name)).trim()
		}

		System.out.println('Template Block Name: ' + templateBlockName)

		System.out.println('Template Block Exercise Numbers: ' + templateExerciseNumber)

		System.out.println('Template Block Exercise Name: ' + templateExerciseName)

		return new blockInfo(templateBlockName, templateExerciseNumber, templateExerciseName)
	}

	@Keyword
	def getBlockInfoInCal(int blockNumber){

		String blockName = driver.findElement(By.xpath('//div[@class="ng-scope"]['+ blockNumber +']/div[@class="block-name"]/div[@class="text-center"]/strong[@class="ng-binding"]')).getText()

		int exerciseNumber = driver.findElements(By.xpath('//div[@class="ng-scope"]['+ blockNumber +']/div[@class="block-exercise ng-scope"]')).size()

		ArrayList<String> exerciseName = new ArrayList<String>()

		for (int i = 1; i <= exerciseNumber; i++){

			exerciseName[i - 1] = driver.findElement(By.xpath('//div[@class="ng-scope"]['+ blockNumber +']/div[@class="block-exercise ng-scope"]['+ i +']/div[@class="exercise-name"]/span[@class="ng-binding"]')).getText()
		}

		System.out.println('Block name: ' + blockName)

		System.out.println('Exercise number: ' + exerciseNumber)

		System.out.println('Exercise name list: ' + exerciseName)

		return new blockInfo(blockName, exerciseNumber, exerciseName)
	}

	@Keyword
	def getWorkoutInfo(int workoutNumber){

		driver.findElement(By.xpath('(//div[not(contains(@class,"disabled-day"))]/header[@class="text-center day-number"]/span[@class="ng-binding ng-scope"])['+ workoutNumber +']')).click()

		String day

		String date

		int workoutYear

		if (driver.findElements(By.xpath('(//div[not(contains(@class,"disabled-day"))]/header[@class="text-center day-number"]/span[contains(@class,"sub-title")])['+ workoutNumber +']')).size() > 0) {

			String phaseCal = driver.findElement(By.xpath('(id("breadcrumb")/a[@class="ng-scope disabled"]/span[@class="ng-binding ng-scope"])[2]')).getText()

			int phaseEndYear = Integer.parseInt(phaseCal.substring(phaseCal.length() - 5, phaseCal.length() - 1))

			String phaseEndMonth = phaseCal.substring(phaseCal.length() - 18, phaseCal.length() - 15)

			def m = Date.parse('MMM', phaseEndMonth)

			int phaseMonth = m.format('MM') as int

			String workoutCal = (driver.findElement(By.xpath('(//div[not(contains(@class,"disabled-day"))]/header[@class="text-center day-number"]/span[contains(@class,"sub-title")])['+ workoutNumber +']')).getText()).trim()

			workoutCal = workoutCal.substring(1, workoutCal.length() - 1)

			String[] workoutCalInfo

			workoutCalInfo = workoutCal.split(' ')

			day = workoutCalInfo[0]

			date = workoutCalInfo[1] + ' ' + workoutCalInfo[2]

			m = Date.parse('MMM', workoutCalInfo[1])

			int workoutMonth = m.format('MM') as int

			if (workoutMonth > phaseMonth) {

				workoutYear = phaseEndYear - 1
			} else {

				workoutYear = phaseEndYear
			}
		}

		String workoutName = driver.findElement(By.xpath("//*[@class = 'phase-name2 ng-binding ng-scope']")).getText()

		String workoutNote = null

		if (driver.findElements(By.xpath('//div[@class="notes ng-scope"]/p[@class="ng-binding"]')).size() > 0) {

			workoutNote = driver.findElement(By.xpath('//div[@class="notes ng-scope"]/p[@class="ng-binding"]')).getText()
		}

		int blockNumber = driver.findElements(By.xpath('//div[@class="full text-left editor-workout"]/span[@class="name ng-binding"]')).size()

		ArrayList<String> blockName = new ArrayList<String>()

		for (int i = 1; i <= blockNumber; i = i + 1){

			blockName.add(driver.findElement(By.xpath('(//div[@class="full text-left editor-workout"]/span[@class="name ng-binding"])['+ i +']')).getText())
		}

		Object workoutInfo = new workoutInfo(workoutName, workoutNote, blockNumber, blockName, day, date, workoutYear)

		System.out.println('WorkoutName: ' + workoutInfo.workoutName + '\nWorkout Note: ' + workoutInfo.workoutNote + '\nBlock Number: ' + workoutInfo.blockNumber + '\nBlock Name: ' + workoutInfo.blockName + '\nWorkout Day: ' + workoutInfo.day + '\nWorkout Date: ' + workoutInfo.date + '\nWorkout Year: ' + workoutInfo.year)

		return workoutInfo
	}

	@Keyword
	def getTeamInfo(int teamNumber) {

		String teamName = driver.findElement(By.xpath('((//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content)['+ teamNumber +']//span[contains(@class,"ng-binding")])[1]')).getText()

		String teamSport = driver.findElement(By.xpath('((//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content)['+ teamNumber +']//span[contains(@class,"ng-binding")])[2]')).getText()

		int athleteNumbers = Integer.parseInt(driver.findElement(By.xpath('((//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content)['+ teamNumber +']//span[contains(@class,"ng-binding")])[3]')).getText())

		int coachNumbers = Integer.parseInt(driver.findElement(By.xpath('((//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content)['+ teamNumber +']//span[contains(@class,"ng-binding")])[4]')).getText())

		int activeProgramNumbers = Integer.parseInt(driver.findElement(By.xpath('((//md-list[1]/md-item[contains(@class,"ng-scope")]/md-item-content)['+ teamNumber +']//span[contains(@class,"ng-binding")])[5]')).getText())

		System.out.println('Team Name: ' + teamName + '\nTeam Sport: ' + teamSport + '\nAthlete Numbers: ' + athleteNumbers + '\nCoach Numbers: ' + coachNumbers + '\nActive Program Numbers: ' + activeProgramNumbers)

		Object teamInfo = new teamInfo(teamName, teamSport, athleteNumbers, coachNumbers, activeProgramNumbers)

		return teamInfo
	}

	@Keyword
	def getAttendanceInfo (int athleteNumber) {

		String athleteName = driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//span[@class = "team-roster-name ng-binding"]')).getText()

		String currentProgram = ""

		if (driver.findElements(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "team-roster-text program-name truncate ng-binding ng-scope"]')).size() > 0) {

			currentProgram = driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "team-roster-text program-name truncate ng-binding ng-scope"]')).getText()
		}

		int completedWorkout = 0

		if (driver.findElements(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar completed"]')).size() > 0) {

			completedWorkout = Integer.parseInt(driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar completed"]')).getText())
		}

		int incompleteWorkout = 0

		if (driver.findElements(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar incomplete"]')).size() > 0) {

			incompleteWorkout = Integer.parseInt(driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar incomplete"]')).getText())
		}

		int scheduledWorkout = 0

		if (driver.findElements(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar scheduled"]')).size() > 0) {

			scheduledWorkout = Integer.parseInt(driver.findElement(By.xpath('//li[contains(@class,"team-roster-item ng-scope")]['+ athleteNumber +']//div[@class = "pull-left bar scheduled"]')).getText())
		}

		Object attendanceInfo = new attendanceInfo(athleteName, currentProgram, completedWorkout, incompleteWorkout, scheduledWorkout)

		System.out.println('Athlete Name: ' + attendanceInfo.athleteName + '\nCurrent Program: ' + attendanceInfo.currentProgram + '\nCompleted Workout: ' + attendanceInfo.completedWorkout + '\nIncomplete Workout: ' + attendanceInfo.incompleteWorkout + '\nScheduled Workout: ' + attendanceInfo.scheduledWorkout)

		return attendanceInfo
	}

	@Keyword
	def getEventInfo(int eventNumber) {

		String eventName = (driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//div[@class="title"]/span[@ng-click = "toggle()"]')).getText()).trim()

		String streetAddress = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//input[@ng-model="competition.address"]')).getAttribute("value")

		String city = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//input[@ng-model="competition.city"]')).getAttribute("value")

		String state = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//span[3]/span[@class="ng-binding"]')).getText()

		String zipCode = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//input[@ng-model="competition.zipcode"]')).getAttribute("value")

		String startDate = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//input[@ng-model="competition.startDateDisplay"]')).getAttribute("value")

		String endDate = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//input[@ng-model="competition.endDateDisplay"]')).getAttribute("value")

		int athleteNumbers = driver.findElements(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//div[@class="athlete-name ng-binding"]')).size()

		List<eventImportanceForAthlete> importanceList = []

		for (int i = 1; i <= athleteNumbers; i = i + 1) {

			String athleteName = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//div[@ng-repeat="athlete in competition.details"]['+ i +']/div[@class="athlete-name ng-binding"]')).getText()

			String eventImportance = driver.findElement(By.xpath('//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//div[@ng-repeat="athlete in competition.details"]['+ i +']//span')).getText()

			Object eventImportanceForAthlete = new eventInfo.eventImportanceForAthlete(athleteName, eventImportance)

			importanceList.add(eventImportanceForAthlete)
		}

		TestObject editButton = new TestObject()

		editButton.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//i[@class ="fa fa-pencil"]', true)

		TestObject delButton = new TestObject()

		delButton.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"competition-item ng-scope")]['+ eventNumber +']//i[@class ="fa fa-trash"]', true)

		Object eventInfo = new eventInfo(eventName, streetAddress, city, state, zipCode, startDate, endDate, athleteNumbers, importanceList, editButton, delButton)

		System.out.println('Event Name: ' + eventInfo.eventName + '\nStreet Address: ' + eventInfo.streetAddress + '\nCity: ' + eventInfo.city + '\nState: ' + eventInfo.state + '\nZip Code: ' + eventInfo.zipCode + '\nStart Date: ' + eventInfo.startDate + '\nEnd Date: ' + eventInfo.endDate + '\nAthlete Numbers: ' + eventInfo.athleteNumbers)

		return eventInfo
	}



	@Keyword
	def getTestResultInfoInTeamLeaderModule(){

		int athleteNumbers = driver.findElements(By.xpath('//div[@class="team-test-item ng-scope"]')).size()

		List<testResultInfo> testResultList = []

		for (int i = 1; i <= athleteNumbers; i = i + 1){

			Object testResultInfo = new testResultInfo()

			String exerciseName = driver.findElement(By.xpath('//custom-select[@items="team.search"]//span')).getText()

			List<String> resultText = ((driver.findElement(By.xpath('(//div[@ng-show="test.showRow"]/div[@ng-attr-flex="{{flexs[3]}}"]/span)['+ i +']')).getText()).trim()).split(' ')

			String testForUnit = resultText[1]

			//int testValue = Integer.parseInt(resultText[0])

			float testValue = Float.parseFloat(resultText[0])

			List<String> paramText = ((driver.findElement(By.xpath('(//div[@ng-show="test.showRow"]/div[@ng-attr-flex="{{flexs[2]}}"]/span)['+ i +']')).getText()).trim()).split(' ')

			String parameterUnit = paramText[1]

			//int parameterValue = Integer.parseInt(paramText[0])

			float parameterValue = Float.parseFloat(paramText[0])

			String testDate = driver.findElement(By.xpath('(//div[@ng-show="test.showRow"]/div[@ng-attr-flex="{{flexs[0]}}"])['+ i +']')).getText()

			String athleteName = driver.findElement(By.xpath('(//div[@ng-show="test.showRow"]/div[@ng-attr-flex="{{flexs[1]}}"])['+ i +']')).getText()

			float RME = 0

			testResultInfo.setTestResultInfo(exerciseName, testForUnit, parameterUnit, parameterValue, testDate, athleteName, testValue, RME)

			System.out.println('Exercise Name: ' + testResultInfo.exerciseName + '\nTest For Value: ' + testResultInfo.testForUnit + '\nParameter Unit: ' + testResultInfo.parameterUnit + '\nParameter Value: ' + testResultInfo.parameterValue + '\nTest Date: ' + testResultInfo.testDate + '\nAthlete Result Name: ' + testResultInfo.athleteName + '\nAthlete Result Value: ' + testResultInfo.testValue + '\nAthlete RME Value: ' + testResultInfo.RME)

			testResultList.add(testResultInfo)

		}

		println testResultList.size()

		return testResultList
	}

	@Keyword
	def getTestResultInfoInTestHistoryTable(){

		List<testResultInfo> testResultList = []

		String exerciseName = driver.findElement(By.xpath('id("test-history-popup")/md-toolbar')).getText()

		WebElement element = driver.findElement(By.xpath('//span[@ui-sref="user.organizations.teams.team.roster.member"]'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: none;'", element)

		String athleteName = driver.findElement(By.xpath('//span[@ui-sref="`.organizations.teams.team.roster.member"]')).getText()

		int testHistoryNumbers = driver.findElements(By.xpath('//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"]/span[1][text() != ""]')).size()

		for (int i = 1; i <= testHistoryNumbers; i = i + 1){

			Object testResultInfo = new testResultInfo()

			String testDate = driver.findElement(By.xpath('(//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"]/span[1])['+ i +']')).getText()

			List<String> paramText = (driver.findElement(By.xpath('(//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"]/span[2]/span)['+ i +']')).getText()).split(' ')

			float parameterValue = Float.parseFloat(paramText[0])

			String parameterUnit = paramText[1]

			List<String> testForText = (driver.findElement(By.xpath('(//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"]/span[3]/span)['+ i +']')).getText()).split(' ')

			float testValue = Float.parseFloat(testForText[0])

			String testForUnit = testForText[1]

			List<String> RMEText = (driver.findElement(By.xpath('(//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"]/strong)['+ i +']')).getText()).split(' ')

			float RME = Float.parseFloat(RMEText[0])

			testResultInfo.setTestResultInfo(exerciseName, testForUnit, parameterUnit, parameterValue, testDate, athleteName, testValue, RME)

			System.out.println('Exercise Name: ' + testResultInfo.exerciseName + '\nTest For Unit: ' + testResultInfo.testForUnit + '\nParameter Unit: ' + testResultInfo.parameterUnit + '\nParameter Value: ' + testResultInfo.parameterValue + '\nTest Date: ' + testResultInfo.testDate + '\nAthlete Result Name: ' + testResultInfo.athleteName + '\nAthlete Result Value: ' + testResultInfo.testValue + '\nAthlete RME Value: ' + testResultInfo.RME + '\n')

			testResultList.add(testResultInfo)
		}

		println 'Test Result Info In Test History Table Size: ' + testResultList.size()

		return testResultList
	}

	@Keyword
	def getTestResultInfoInHighCharts(){

		List<testResultInfo> testResultList = []

		String exerciseName = driver.findElement(By.xpath('id("test-history-popup")/md-toolbar')).getText()

		WebElement element = driver.findElement(By.xpath('//span[@ui-sref="user.organizations.teams.team.roster.member"]'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: none;'", element)

		String athleteName = driver.findElement(By.xpath('//span[@ui-sref="user.organizations.teams.team.roster.member"]')).getText()

		int dotNumbers = driver.findElements(By.xpath('//*[@class="highcharts-markers highcharts-series-0 highcharts-tracker"]/*[@fill="#ffd800"]')).size()

		for (int i = dotNumbers; i >= 1; i = i - 1){

			Object testResultInfo = new testResultInfo()

			TestObject dotObj = new TestObject('dotObj')

			dotObj.addProperty('xpath', ConditionType.EQUALS, '(//div[@ng-repeat="exercise in exerciseHistoryToShow track by $index"])['+ i +']', true)

			WebUI.mouseOver(dotObj)

			String testDate = driver.findElement(By.xpath('//div[@class="highcharts-tooltip"]//div[@class="tool-tips"]/span')).getText()

			List<String> textList = (driver.findElement(By.xpath('//div[@class="highcharts-tooltip"]//div[@class="tool-tips"]/p')).getText()).split('\n')

			List<String> infoList = (textList[0].replace(',', '')).split(' ')

			float parameterValue = Float.parseFloat(infoList[0])

			String parameterUnit = infoList[1]

			float testValue = Float.parseFloat(infoList[2])

			String testForUnit = infoList[3]

			float RME

			if (textList[1] != null){

				RME = Float.parseFloat((textList[1].split(' '))[1])
			}

			else {

				RME = 0
			}

			testResultInfo.setTestResultInfo(exerciseName, testForUnit, parameterUnit, parameterValue, testDate, athleteName, testValue, RME)

			System.out.println('Exercise Name: ' + testResultInfo.exerciseName + '\nTest For Unit: ' + testResultInfo.testForUnit + '\nParameter Unit: ' + testResultInfo.parameterUnit + '\nParameter Value: ' + testResultInfo.parameterValue + '\nTest Date: ' + testResultInfo.testDate + '\nAthlete Result Name: ' + testResultInfo.athleteName + '\nAthlete Result Value: ' + testResultInfo.testValue + '\nAthlete RME Value: ' + testResultInfo.RME + '\n')

			testResultList.add(testResultInfo)
		}

		println 'Test Result Info In Highcharts Size: ' + testResultList.size()

		return testResultList
	}
}
