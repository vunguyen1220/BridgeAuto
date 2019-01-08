import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


if (WebUI.verifyElementPresent(findTestObject('Commons/First Change Dialog/dialog_First Change'), 2, FailureHandling.OPTIONAL) == true){

	WebUI.click(findTestObject('Commons/First Change Dialog/checkbox_Not Show Again'))

	WebUI.click(findTestObject('Commons/First Change Dialog/button_OK'))

	WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

}
