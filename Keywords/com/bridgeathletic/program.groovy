package com.bridgeathletic

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
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
import com.sun.jna.Library

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class program {

	String programName

	String programDescription

	String teamName

	String folderName

	int memberNumbers

	List<String> memberList = []

	String assignStatus

	String editedBy

	String editedDate

	String startDate

	String endDate

	String currentPhase

	int durationNumbers

	int dayPerWeekNumbers

	int phaseNumbers

	List<phase> phaseList = []

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def printProgramInfo (program pr){

		println 'Program Name: ' + pr.programName

		println 'Program Description: ' + pr.programDescription

		println 'Team Name: ' + pr.teamName

		println 'Folder Name: ' + pr.folderName

		println 'Member Numbers: ' + pr.memberNumbers

		println 'Member List: ' + pr.memberList

		println 'Assign Status: ' + pr.assignStatus

		println 'Edited By: ' + pr.editedBy

		println 'Edited Date: ' + pr.editedDate

		println 'Start Date: ' + pr.startDate

		println 'End Date: ' + pr.endDate

		println 'Current Phase: ' + pr.currentPhase

		println 'Duration Numbers: ' + pr.durationNumbers

		println 'Day/Week: ' + pr.dayPerWeekNumbers

		println 'Phase Numbers: ' + pr.phaseNumbers

		println 'Phase List Size: ' + pr.phaseList.size()
	}

	@Keyword
	def getTotalProgramNumbers (){

		String headerText = driver.findElement(By.xpath('//div[@class="gird-view-all"]//span[@class="ng-binding"]')).getText()

		List<String> textList = (headerText.replace('  ', '')).split(' of ')

		int count = Integer.parseInt(textList[1])

		return count
	}

	@Keyword
	def selectProgramInLibraryByIndex (int index){

		int startPaging = Integer.parseInt(WebUI.getText(findTestObject('Library/div_Paging')).split(' of ')[0].split('-')[0].trim())

		int endPaging = Integer.parseInt(WebUI.getText(findTestObject('Library/div_Paging')).split(' of ')[0].split('-')[1].trim())

		while (startPaging > index || index > endPaging){

			if (startPaging > index){

				WebUI.click(findTestObject('Library/button_Previous Paging'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			if (index > endPaging) {

				WebUI.click(findTestObject('Library/button_Next Paging'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			startPaging = Integer.parseInt(WebUI.getText(findTestObject('Library/div_Paging')).split(' of ')[0].split('-')[0].trim())

			endPaging = Integer.parseInt(WebUI.getText(findTestObject('Library/div_Paging')).split(' of ')[0].split('-')[1].trim())
		}

		int temp = index%50

		if (temp == 0){

			temp = 50
		}

		TestObject selectProgram = new TestObject()

		selectProgram.addProperty('xpath', ConditionType.EQUALS, '//div[@ng-click="viewProgram(item)"]['+ temp +']', true)

		WebUI.click(selectProgram)
	}

	@Keyword
	def getProgramInfoInLibrary (int index){

		Object programInfo = new program()

		String programName = driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']/div[@class="items name ng-scope"]/span')).getText()

		programInfo.setProgramName(programName)

		List<String> dateText = (driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']//div[@class="date ng-scope"]/span[1]')).getText()).split(' ')

		programInfo.setDurationNumbers(0)

		if (dateText[0].length() > 0 && dateText[0] != '0'){

			programInfo.setDurationNumbers(Integer.parseInt(dateText[0]))
		}

		dateText = (driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']//div[@class="date ng-scope"]/span[2]')).getText()).split(' ')

		programInfo.setDayPerWeekNumbers(0)

		if (dateText.size > 1){

			programInfo.setDayPerWeekNumbers(Integer.parseInt(dateText[0]))
		}

		String editedBy = driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']/div[@class="items edited-by ng-scope"]/span')).getText()

		if (editedBy.empty == false){

			programInfo.setEditedBy(editedBy)
		}

		String editedDate = driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']/div[@class="items edited-on ng-binding ng-scope"]')).getText()

		programInfo.setEditedDate(editedDate)

		String assignStatus = driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']/div[@class="items text-center program-status ng-scope"]//span[1]')).getText()

		programInfo.setAssignStatus(assignStatus)

		String teamName = driver.findElement(By.xpath('//div[@ng-click = "viewProgram(item)"]['+ index +']/div[@class="items text-center program-status ng-scope"]//div')).getText()

		if (teamName.empty == false){

			programInfo.setTeamName(teamName)
		}

		return programInfo
	}

	@Keyword
	def getUnassignedProgramInfoInDetail() {

		Object programInfo = new program()

		WebElement element = driver.findElement(By.xpath('id("program-detail")//h1[@class="program-name"]'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

		String programName = driver.findElement(By.xpath('id("program-detail")//h1[@class="program-name"]')).getText()

		programInfo.setProgramName(programName)

		if (driver.findElements(By.xpath('id("program-detail")//p[@class="program-description"]/span[@id="check-readmore"]')).size() > 0){

			String programDescription = driver.findElement(By.xpath('id("program-detail")//p[@class="program-description"]/span[@id="check-readmore"]')).getText()

			programInfo.setProgramDescription(programDescription)
		}

		String teamName = 'No Team'

		if (driver.findElements(By.xpath('//select[@ng-model="data.programs.teamId"]/option[@selected="selected"]')).size() > 0){

			teamName = driver.findElement(By.xpath('//select[@ng-model="data.programs.teamId"]/option[@selected="selected"]')).getText()
		}

		programInfo.setTeamName(teamName)

		if (driver.findElements(By.xpath('//select[@ng-model="data.programs.programFolderId"]/option[@selected="selected"]')).size() > 0){

			String folderName = driver.findElement(By.xpath('//select[@ng-model="data.programs.programFolderId"]/option[@selected="selected"]')).getText()

			programInfo.setFolderName(folderName)
		}

		if (driver.findElement(By.xpath('//md-card[@class="clone ng-scope md-default-theme"]/md-card-content')).getText() != 'Assign Program'){

			return false
		}

		programInfo.setAssignStatus('Unassigned')

		String editedBy = (driver.findElement(By.xpath('//md-card-content[@ng-if="data.programs.updatedAt"]/strong')).getText()).trim()

		programInfo.setEditedBy(editedBy)

		String editedDate = driver.findElement(By.xpath('//md-card-content[@ng-if="data.programs.updatedAt"]/div')).getText()

		programInfo.setEditedDate(editedDate)

		int durationNumbers = 0

		if ((driver.findElement(By.xpath('//md-card[*="Duration"]/md-card-content/strong')).getText()).length() > 0){

			durationNumbers = Integer.parseInt(driver.findElement(By.xpath('//md-card[*="Duration"]/md-card-content/strong')).getText())
		}

		programInfo.setDurationNumbers(durationNumbers)

		int dayPerWeekNumbers = 0

		if ((driver.findElement(By.xpath('//md-card[*="Days"]/md-card-content/strong')).getText()).length() > 0){

			dayPerWeekNumbers = Integer.parseInt(driver.findElement(By.xpath('//md-card[*="Days"]/md-card-content/strong')).getText())
		}

		programInfo.setDayPerWeekNumbers(dayPerWeekNumbers)

		List<String> textPhase = driver.findElement(By.xpath('id("phase-in-program")/h2')).getText().split(" \n")

		int phaseNumbers = Integer.parseInt(textPhase[0])

		programInfo.setPhaseNumbers(phaseNumbers)

		return programInfo
	}

	@Keyword
	def getAssignedProgramInfoInDetail(){

		Object programInfo = new program()

		WebElement element = driver.findElement(By.xpath('id("program-detail")//h1[@class="program-name"]'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

		String programName = driver.findElement(By.xpath('id("program-detail")//h1[@class="program-name"]')).getText()

		programInfo.setProgramName(programName)

		if (driver.findElements(By.xpath('id("program-detail")//p[@class="program-description program-assigned-description"]/span[@id="check-readmore"]')).size() > 0){

			String programDescription = driver.findElement(By.xpath('id("program-detail")//p[@class="program-description program-assigned-description"]/span[@id="check-readmore"]')).getText()

			programInfo.setProgramDescription(programDescription)
		}

		element = driver.findElement(By.xpath('id("program-detail")//div[@ng-click="goTeam(team)"]'))

		((JavascriptExecutor)driver).executeScript("arguments[0].style='text-transform: unset;'", element)

		String teamName = driver.findElement(By.xpath('id("program-detail")//div[@ng-click="goTeam(team)"]')).getText()

		programInfo.setTeamName(teamName)

		if (driver.findElements(By.xpath('id("program-detail")//p[@class="edited-name ng-scope"]')).size() > 0){

			String folderName = driver.findElement(By.xpath('id("program-detail")//p[@class="edited-name ng-scope"]//span')).getText()

			programInfo.setFolderName(folderName)
		}

		int athleteNumbers = Integer.parseInt((driver.findElement(By.xpath('(id("program-detail")//div[@class="user-list"]//h4)[1]')).getText().replace('(', '').replace(')', '').split(' '))[1])

		int coachNumbers = Integer.parseInt((driver.findElement(By.xpath('(id("program-detail")//div[@class="user-list"]//h4)[2]')).getText().replace('(', '').replace(')', '').split(' '))[1])

		programInfo.setMemberNumbers(athleteNumbers + coachNumbers)

		if (programInfo.memberNumbers > 0){

			String userName

			for (int i = 1; i <= athleteNumbers; i = i + 1){

				userName = driver.findElement(By.xpath('(id("program-detail")//div[@class="user-list"]//div[@ng-show="showUser"]/p[@ng-click="viewUser(athlete)"])['+ i +']')).getAttribute('innerHTML')

				programInfo.memberList.add(userName)
			}

			for (int i = 1; i <= coachNumbers; i = i + 1){

				userName = driver.findElement(By.xpath('(id("program-detail")//div[@class="user-list"]//p[@ng-click="viewUser(coach)"])['+ i +']')).getAttribute('innerHTML')

				programInfo.memberList.add(userName)
			}
		}

		programInfo.setAssignStatus('Assigned')

		List<String> editedList = driver.findElement(By.xpath('id("program-detail")//p[@class="edited-date ng-binding"][2]')).getText().replace(')', '').split(' \\(')

		programInfo.setEditedBy(editedList[0])

		programInfo.setEditedDate(editedList[1])

		List<String> durationList = driver.findElement(By.xpath('id("program-detail")//p[@class="edited-date ng-binding"][1]')).getText().split(' - ')

		programInfo.setStartDate(durationList[0])

		programInfo.setEndDate(durationList[1])

		if (driver.findElements(By.xpath('id("program-detail")//p[@ng-if="currentPhase"][2]')).size() > 0){

			String currentPhase = driver.findElement(By.xpath('id("program-detail")//p[@ng-if="currentPhase"][2]')).getText()

			programInfo.setCurrentPhase(currentPhase)
		}

		List<String> textPhase = driver.findElement(By.xpath('id("phase-in-program")/h2')).getText().split(" \n")

		int phaseNumbers = Integer.parseInt(textPhase[0])

		programInfo.setPhaseNumbers(phaseNumbers)

		return programInfo
	}

	@Keyword
	def updateProgramInfo (program pr1, program pr2){

		if (pr1.programName == pr2.programName){

			if (pr1.programDescription == null && pr2.programDescription != null){

				pr1.programDescription = pr2.programDescription
			}

			if (pr1.teamName == null && pr2.teamName != null){

				pr1.teamName = pr2.teamName
			}

			if (pr1.folderName == null && pr2.folderName != null){

				pr1.folderName = pr2.folderName
			}

			if (pr1.memberNumbers == 0 && pr2.memberNumbers > 0){

				pr1.memberNumbers = pr2.memberNumbers
			}

			if (pr1.memberList.size() == 0 && pr2.memberList.size() > 0){

				pr1.memberList = pr2.memberList
			}

			if (pr1.assignStatus == null && pr2.assignStatus != null){

				pr1.assignStatus = pr2.assignStatus
			}

			if (pr1.editedBy == null && pr2.editedBy != null){

				pr1.editedBy = pr2.editedBy
			}

			if (pr1.editedDate == null && pr2.editedDate != null){

				pr1.editedDate = pr2.editedDate
			}

			if (pr1.startDate == null && pr2.startDate != null){

				pr1.startDate = pr2.startDate
			}

			if (pr1.endDate == null && pr2.endDate != null){

				pr1.endDate = pr2.endDate
			}

			if (pr1.currentPhase == null && pr2.currentPhase != null){

				pr1.currentPhase = pr2.currentPhase
			}

			if (pr1.durationNumbers == 0 && pr2.durationNumbers > 0){

				pr1.durationNumbers = pr2.durationNumbers
			}

			if (pr1.dayPerWeekNumbers == 0 && pr2.dayPerWeekNumbers > 0){

				pr1.dayPerWeekNumbers = pr2.dayPerWeekNumbers
			}

			if (pr1.phaseNumbers == 0 && pr2.phaseNumbers > 0){

				pr1.phaseNumbers = pr2.phaseNumbers
			}

			if (pr1.phaseList.size() == 0 && pr2.phaseList.size() > 0){

				pr1.phaseList = pr2.phaseList
			}
		}

		return pr1
	}

	@Keyword
	def filterProgramsByCreator(String filterByCreator){

		if (filterByCreator == 'All Programs'){

			WebUI.click(findTestObject('Library/button_All Programs'))
		}

		else if (filterByCreator == 'My Programs'){

			WebUI.click(findTestObject('Library/button_My Programs'))
		}

		else if (filterByCreator == 'EXOS'){

			WebUI.click(findTestObject('Library/button_EXOS'))
		}

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	@Keyword
	def filterProgramsByTeam(String filterByTeam){

		WebUI.click(findTestObject('Library/select_Filter Current Team'))

		Thread.sleep(500)

		TestObject filterTeamNameObject = new TestObject()

		filterTeamNameObject.addProperty('xpath', ConditionType.EQUALS, '//select[@ng-model = "options.teamId"]/option[@ng-selected = "team.id == options.teamId" and . = "'+ filterByTeam +'"]', true)

		WebUI.click(filterTeamNameObject)

		WebUI.click(findTestObject('Library/select_Filter Current Team'))

		WebUI.delay(1)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	@Keyword
	def filterProgramsByType(List<String> selectList){

		List<String> totalList = ['Unassigned', 'Assigned', 'Template', 'Completed']

		List<String> unSelectList = totalList - selectList

		for (int i = 0; i < unSelectList.size(); i = i + 1){

			if (driver.findElements(By.xpath('//md-checkbox[. = "'+ unSelectList[i] +'"]')).size() > 0){

				TestObject typeObject = new TestObject()

				typeObject.addProperty('xpath', ConditionType.EQUALS, '//md-checkbox[. = "'+ unSelectList[i] +'"]', true)

				boolean flag = (WebUI.getAttribute(typeObject, 'class')).contains('md-checked')

				if (flag) {

					WebUI.click(typeObject)

					WebUI.delay(1)

					WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
				}
			}
		}

		for (int i = 0; i < selectList.size(); i = i + 1){

			if (driver.findElements(By.xpath('//md-checkbox[. = "'+ selectList[i] +'"]')).size() > 0){

				TestObject typeObject = new TestObject()

				typeObject.addProperty('xpath', ConditionType.EQUALS, '//md-checkbox[. = "'+ selectList[i] +'"]', true)

				boolean flag = (WebUI.getAttribute(typeObject, 'class')).contains('md-checked')

				if (flag == false) {

					WebUI.click(typeObject)

					WebUI.delay(1)

					WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
				}
			}
		}
	}
}
