package bridgeathletic

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class exercise {

	String exerciseName

	String exerciseNote

	List<parameter> parameterList = []

	List<set> setList = []

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def getExerciseInfo (){

		Object exerciseInfo = new exercise()

		String exerciseNameText = WebUI.getText(findTestObject('Phase Builder Page/Left Panel/text_Exercise Name'))

		exerciseInfo.exerciseName = exerciseNameText

		String exerciseNoteText = WebUI.getAttribute(findTestObject('Phase Builder Page/Left Panel/input_Exercise Note'), 'value')

		exerciseInfo.exerciseNote = exerciseNoteText

		List<parameter> paramList = (new bridgeathletic.parameter()).getAllParametersInfo()

		exerciseInfo.parameterList = paramList

		List<set> setList = (new bridgeathletic.set()).getAllSetsInfo()

		exerciseInfo.setList = setList

		return exerciseInfo
	}

	@Keyword
	def getExerciseInfoInCalendar (int blockIndex, int exerciseIndex){

		Object selectExercise = new exercise()

		String exerciseText = '//md-dialog[@aria-label = "Workout Dialog"]//div[contains(@ng-repeat, "block in workout.data")]['+ blockIndex +']/div[@class="block-exercise ng-scope"]['+ exerciseIndex +']'

		String exerciseNameText = exerciseText + '/div[@class="exercise-name"]/span[@class="ng-binding"]'

		TestObject exerciseNameObject = new TestObject('Exercise Name')

		exerciseNameObject.addProperty('xpath', ConditionType.EQUALS, exerciseNameText, true)

		selectExercise.setExerciseName(WebUI.getText(exerciseNameObject))

		String exerciseNoteText = exerciseText + '/div[@class="exercise-desc ng-binding"]'

		if (driver.findElements(By.xpath(exerciseNoteText)).size() > 0){

			TestObject exerciseNoteObject = new TestObject()

			exerciseNoteObject.addProperty('xpath', ConditionType.EQUALS, exerciseNoteText, true)

			selectExercise.setExerciseNote(WebUI.getText(exerciseNoteObject))
		}

		int blocksetNumbers = driver.findElements(By.xpath(exerciseText + '/div[@ng-repeat="blockSet in exercise.orderSets track by $index"]')).size()

		for (int i = 1; i <= blocksetNumbers; i = i + 1){

			String blockSetText = exerciseText + '/div[@ng-repeat="blockSet in exercise.orderSets track by $index"]['+ i +']'
		}
	}

	@Keyword
	def addNewExerciseFromBlockName (String blockName, String exerciseName){

		String addNewExerciseText = '//header[@class = "block-name draggable"]/span[. = "'+ blockName +'"]//ancestor::div[contains(@class, "block ng-scope")]//div[@class = "exercise-search-box ng-scope"]'

		TestObject addNewExerciseButtonObject = new TestObject()

		addNewExerciseButtonObject.addProperty('xpath', ConditionType.EQUALS, addNewExerciseText, true)

		WebUI.click(addNewExerciseButtonObject)

		WebUI.waitForElementPresent(findTestObject('Phase Builder Page/Choose Exercise Popup/popup_Choose Exercise'), 30)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		WebUI.setText(findTestObject('Phase Builder Page/Choose Exercise Popup/input_Search For Exercise'), exerciseName)

		WebUI.sendKeys(findTestObject('Phase Builder Page/Choose Exercise Popup/input_Search For Exercise'), Keys.chord(Keys.ENTER))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

		WebUI.waitForElementNotPresent(findTestObject('Phase Builder Page/Choose Exercise Popup/icon_Loading'), 30)

		TestObject selectExcerciseObject = new TestObject()

		selectExcerciseObject.addProperty('xpath', ConditionType.EQUALS, '//div[@class = "exercise-item-content"]/h5[. = "'+ exerciseName +'"]', true)

		if (WebUI.verifyElementPresent(selectExcerciseObject, 2, FailureHandling.OPTIONAL)){

			WebUI.click(selectExcerciseObject)

			WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
		}

		else {

			WebUI.click(findTestObject('Phase Builder Page/Choose Exercise Popup/button_Create New Exercise'))

			WebUI.click(findTestObject('Phase Builder Page/Choose Exercise Popup/button_Yes'))
		}

		WebUI.click(findTestObject('Phase Builder Page/Choose Exercise Popup/button_Insert'))

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	@Keyword
	def selectExerciseFromName (String blockName, String exerciseName){

		String exerciseNameText = '//header[@class = "block-name draggable"]/span[. = "'+ blockName +'"]//ancestor::div[contains(@class, "block ng-scope")]//header[contains(@class, "exercise ng-scope")]//span[. = "'+ exerciseName +'"]'

		TestObject exerciseNameObject = new TestObject()

		exerciseNameObject.addProperty('xpath', ConditionType.EQUALS, exerciseNameText, true)

		WebUI.click(exerciseNameObject)

		WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
	}

	@Keyword
	def editParameterNumbers (List<String> paramName){

		if (driver.findElements(By.xpath('//section[@id="exercise-content"]//div[@class="params"]')).size() == 0){

			WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Edit Parameters'))

			WebUI.waitForElementPresent(findTestObject('Phase Builder Page/Left Panel/list_Parameters Name'), 30)
		}

		while (driver.findElements(By.xpath('id("exercise-content")//div[@class="params"]//md-checkbox[contains(@class, "md-checked")]')).size() > 0) {

			TestObject checkedParamObject = new TestObject()

			checkedParamObject.addProperty('xpath', ConditionType.EQUALS, 'id("exercise-content")//div[@class="params"]//md-checkbox[contains(@class, "md-checked")]', true)

			WebUI.click(checkedParamObject)

			WebUI.delay(1)
		}

		for (int i = 0; i < paramName.size(); i = i + 1){

			TestObject selectParamObject = new TestObject()

			selectParamObject.addProperty('xpath', ConditionType.EQUALS, '//md-checkbox[@aria-label = "'+ paramName[i] +'"]', true)

			WebUI.click(selectParamObject)

			WebUI.delay(1)
		}

		WebUI.click(findTestObject('Phase Builder Page/Left Panel/text_Exercise Name'))
	}

	@Keyword
	def editSetNumbers (int setNumbers){

		int totalSetNumbers = driver.findElements(By.xpath('id("exercise-content")//div[@class="single-row ng-scope"]')).size()

		while (totalSetNumbers != setNumbers){

			if (totalSetNumbers < setNumbers){

				WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Add New Set'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			if (totalSetNumbers > setNumbers){

				WebUI.click(findTestObject('Phase Builder Page/Left Panel/button_Remove Set'))

				WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])
			}

			totalSetNumbers = driver.findElements(By.xpath('id("exercise-content")//div[@class="single-row ng-scope"]')).size()
		}
	}
}
