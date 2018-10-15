import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Date as Date
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import org.apache.commons.validator.Var as Var
import org.apache.poi.ss.formula.functions.Today as Today
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://stg.bridgeathletic.com/')

WebUI.click(findTestObject('Demo Page/a_Login'))

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.setText(findTestObject('Home Page/input_email'), 'test+assay2@bridgeathletic.com')

WebUI.setText(findTestObject('Home Page/input_password'), '23456789')

WebUI.click(findTestObject('Login Page/button_LOGIN'))

if (WebUI.verifyElementPresent(findTestObject('Org Home Page/span_Reload'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Org Home Page/span_Reload'))
}

'Navigate to a random organization'
WebUI.navigateToUrl('http://stg.bridgeathletic.com/user/2/organizations/19')

WebUI.delay(5)

WebUI.click(findTestObject('Org Home Page/span_coaches (5)'))

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
WebUI.verifyElementPresent(findTestObject('Org Home Page/span_coach 1 test 1'), 30)

WebUI.click(findTestObject('Org Home Page/span_coach 1 test 1'))

Coach = WebUI.getText(findTestObject('Coach Home Page/div_bridgetest1102coach1gmail.'))

WebUI.click(findTestObject('Template Blocks/Content Manager/a_glyphicon glyphicon-home'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Logout of the super admin account'
WebUI.click(findTestObject('Org Home Page/div_md-button md-default-theme'))

WebUI.delay(2)

WebUI.click(findTestObject('Org Home Page/i_fa fa-sign-out'))

WebUI.waitForElementPresent(findTestObject('Demo Page/a_Login'), 30)

WebUI.click(findTestObject('Demo Page/a_Login'))

'Login as the coach from Step 3'
WebUI.setText(findTestObject('Home Page/input_email'), Coach)

WebUI.setText(findTestObject('Home Page/input_password'), '123456789')

WebUI.click(findTestObject('Login Page/button_LOGIN'))

'You will land on the organization homepage'
WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.delay(3)

WebUI.waitForElementPresent(findTestObject('Org Home Page/header_md-toolbar-tools'), 30)

'Find the "Programs, Phases, Exercises" module, click the header'
WebUI.click(findTestObject('Org Home Page/header_md-toolbar-tools'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.waitForElementVisible(findTestObject('Content Manager Page/strong_Programs'), 30)

WebUI.delay(4)

'Click the "Programs" option on the left side'
WebUI.click(findTestObject('Content Manager Page/strong_Programs'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(2)

'Click "+ New" from the left panel'
WebUI.click(findTestObject('Content Manager Page/span_New'))

'Input a name and description (do not select a team), press green checkmark to save'
Date date = new Date()

day = date.getDate()

WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-untouched'), 'Regression Testing Edit Blocks Day View - ' + 
    day)

WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(3)

'Check that the left panel shows the "No Teams" list of programs'
WebUI.verifyElementPresent(findTestObject('Content Manager Page/span_No Team'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/strong_new program'), 30)

WebUI.mouseOver(findTestObject('Content Manager Page/span_Add Phase'))

WebUI.click(findTestObject('Content Manager Page/a_Add New'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-untouched (2)'), 'Phase 1')

WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope (1)'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(3)

WebDriver driver = DriverFactory.getWebDriver()

week_number = driver.findElements(By.xpath('//div[@class="week-contain"]')).size()

day_number = driver.findElements(By.xpath('//div[starts-with(@class, "day ng-scope")]')).size()

WebUI.verifyEqual(week_number, 1)

WebUI.verifyEqual(day_number, 1)

WebUI.mouseOver(findTestObject('Phase Builder Page/div_add-block-btns'))

WebUI.click(findTestObject('Phase Builder Page/label_New'))

WebUI.setText(findTestObject('Phase Builder Page/input_new-block-0-1'), 'Block 1')

WebUI.sendKeys(findTestObject('Phase Builder Page/input_new-block-0-1'), Keys.chord(Keys.ENTER))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(1)

WebUI.setText(findTestObject('Phase Builder Page/input_block-to-add-6842443'), 'Barbell Back Squat')

WebUI.waitForElementNotPresent(findTestObject('Phase Builder Page/div_loading-exercise ng-scope'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Phase Builder Page/span_Barbell Back Squat'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Phase Builder Page/header_block-name draggable'))

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-files-o ng-scope_block'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Phase Builder Page/header_block-name draggable _second'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-pencil (1)'))

WebUI.setText(findTestObject('Phase Builder Page/input_form-control ng-pristine'), 'Block 2')

WebUI.click(findTestObject('Phase Builder Page/i_icon-check check-btn ng-scop'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

not_run: WebUI.verifyElementText(findTestObject('Phase Builder Page/span_Block_selected'), 'Block 2')

WebUI.mouseOver(findTestObject('Phase Builder Page/header_text-center day-number'))

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-files-o ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Phase Builder Page/header_block-name draggable _second (day selected)'), 30)

WebUI.dragAndDropToObject(findTestObject('Phase Builder Page/header_block-name draggable'), findTestObject('Phase Builder Page/header_block-name draggable _second (day selected)'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-pencil (1)'))

WebUI.setText(findTestObject('Phase Builder Page/input_form-control ng-pristine'), 'Block 3')

WebUI.click(findTestObject('Phase Builder Page/i_icon-check check-btn ng-scop'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

WebUI.mouseOver(findTestObject('Phase Builder Page/header_block-name draggable_selected'))

WebUI.click(findTestObject('Phase Builder Page/i_custom-icon icon-three-dots_block selected'))

WebUI.waitForElementPresent(findTestObject('Phase Builder Page/span_Create superset'), 30)

WebUI.click(findTestObject('Phase Builder Page/span_Create superset'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

'Go to the "Load Progression View"'
WebUI.click(findTestObject('Phase Builder Page/img_ng-scope_week-view'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Clone the week '
WebUI.mouseOver(findTestObject('Phase Builder Page/div_md-button md-default-theme_selected'))

WebUI.click(findTestObject('Phase Builder Page/button_fa fa-files-o md-button'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Go to the "Weekly View"'
WebUI.click(findTestObject('Phase Builder Page/img_ng-scope_day-view'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/w2_button_expand-btn md-button'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.dragAndDropToObject(findTestObject('Phase Builder Page/header_block-name draggable_w1d2b3'), findTestObject('Phase Builder Page/header_block-name draggable_w2d1b1'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-pencil (1)'))

WebUI.setText(findTestObject('Phase Builder Page/input_form-control ng-pristine'), 'Block 1')

WebUI.click(findTestObject('Phase Builder Page/i_icon-check check-btn ng-scop'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

WebUI.mouseOver(findTestObject('Phase Builder Page/header_block-name draggable'))

WebUI.click(findTestObject('Phase Builder Page/i_custom-icon icon-three-dots (1)'))

WebUI.waitForElementPresent(findTestObject('Phase Builder Page/span_Delete Block'), 30)

WebUI.click(findTestObject('Phase Builder Page/span_Delete Block'))

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/span_Delete'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

WebUI.mouseOver(findTestObject('Phase Builder Page/header_block-name draggable_w2d2b3'))

WebUI.click(findTestObject('Phase Builder Page/i_custom-icon icon-three-dots_block_w2d2b3'))

WebUI.waitForElementPresent(findTestObject('Phase Builder Page/span_Delete Block'), 30)

WebUI.click(findTestObject('Phase Builder Page/span_Delete Block'))

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/span_Delete'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

WebUI.mouseOver(findTestObject('Phase Builder Page/a_add-phase-builder_w2'))

WebUI.click(findTestObject('Phase Builder Page/a_New Workout_w2'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.dragAndDropToObject(findTestObject('Phase Builder Page/header_block-name draggable_w2d2b1'), findTestObject('Phase Builder Page/div_add-block-btns_selected'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

