import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.List as List
import java.lang.reflect.Array as Array
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.keyword.builtin.VerifyMatchKeyword as VerifyMatchKeyword
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.sun.jna.StringArray as StringArray
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ById as ById
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

WebUI.openBrowser('')

WebUI.navigateToUrl('http://stg.bridgeathletic.com/')

'start 1'
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

WebUI.verifyElementPresent(findTestObject('Org Home Page/span_coach 1 test 1'), 0)

WebUI.click(findTestObject('Org Home Page/span_coach 1 test 1'))

Coach = WebUI.getText(findTestObject('Coach Home Page/div_bridgetest1102coach1gmail.'))

WebUI.click(findTestObject('Template Blocks/Content Manager/a_glyphicon glyphicon-home'))

WebUI.click(findTestObject('Org Home Page/div_md-button md-default-theme'))

WebUI.waitForPageLoad(10)

WebUI.waitForElementPresent(findTestObject('Org Home Page/i_fa fa-sign-out'), 10)

WebUI.waitForElementClickable(findTestObject('Org Home Page/i_fa fa-sign-out'), 10)

WebUI.click(findTestObject('Org Home Page/i_fa fa-sign-out'))

'end 1'
WebUI.waitForElementPresent(findTestObject('Demo Page/a_Login'), 10)

WebUI.click(findTestObject('Demo Page/a_Login'))

'Login as the coach from previous step'
WebUI.setText(findTestObject('Home Page/input_email'), Coach)

WebUI.setText(findTestObject('Home Page/input_password'), '123456789')

WebUI.click(findTestObject('Login Page/button_LOGIN'))

if (WebUI.verifyElementPresent(findTestObject('Org Home Page/span_Reload'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Org Home Page/span_Reload'))
}

WebUI.waitForPageLoad(10)

WebUI.delay(5, FailureHandling.STOP_ON_FAILURE)

'You will land on the organization homepage'
WebUI.verifyElementPresent(findTestObject('Org Home Page/span_Home'), 2)

WebUI.click(findTestObject('Org Home Page/span_SEE ALL'))

'start 2'
WebUI.waitForElementClickable(findTestObject('All Team Page/span_New Team'), 10)

'Find team module on organization page, click "Add Team", save name and sport for team'
WebUI.click(findTestObject('All Team Page/span_New Team'))

WebUI.setText(findTestObject('New Team Page/input_input-content ng-pristin'), 'team 01')

WebUI.selectOptionByValue(findTestObject('New Team Page/select_input-content ng-pristi'), '6', false)

WebUI.click(findTestObject('New Team Page/i_fa fa-check ng-scope'))

WebUI.delay(2)

if (WebUI.verifyElementPresent(findTestObject('New Team Page/i_ Close'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('New Team Page/i_ Close'))
}

WebUI.mouseOver(findTestObject('All Team Page/span_TEAM 01'))

if (WebUI.verifyElementPresent(findTestObject('All Team Page/span_Edit'), 5, FailureHandling.STOP_ON_FAILURE)) {
    WebUI.click(findTestObject('All Team Page/span_Edit'))
}

WebUI.click(findTestObject('All Team Page/input_uppercase teams-name-inp'))

'Change the name of the team'
WebUI.setText(findTestObject('All Team Page/input_uppercase teams-name-inp'), 'team 1')

'Change the sport of the team'
WebUI.selectOptionByValue(findTestObject('All Team Page/select_teams-sports ng-pristin'), '2', false)

'end 2'
WebUI.click(findTestObject('All Team Page/i_fa fa-check'))

WebUI.mouseOver(findTestObject('All Team Page/span_TEAM 1'))

if (WebUI.verifyElementPresent(findTestObject('All Team Page/span_TEAM 1 hover'), 5, FailureHandling.STOP_ON_FAILURE)) {
    WebUI.click(findTestObject('All Team Page/span_TEAM 1 hover'))
}

'Find roster module on the team page'
WebUI.verifyElementPresent(findTestObject('Test/div_team-roster-header-bar'), 2)

'Go to See All, roster module'
WebUI.click(findTestObject('Team Home Page/span_See All'))

'Select "Add coach"'
WebUI.click(findTestObject('Roster Page/span_Add Coach'))

'Enter data for 2 coaches (new users, not in the system) with email addresses that you can access'
WebUI.setText(findTestObject('New Coach Page/input_firstName'), 'coach 2')

WebUI.setText(findTestObject('New Coach Page/input_lastName'), 'test 2')

WebUI.setText(findTestObject('New Coach Page/input_email'), 'bridgetest1102+coach2@gmail.com')

WebUI.click(findTestObject('New Coach Page/button_single-button'))

WebUI.click(findTestObject('New Coach Page/span_Male'))

WebUI.click(findTestObject('New Coach Page/i_fa fa-check ng-scope'))

WebUI.click(findTestObject('New Coach Page/strong_coach 2 test 2'))

WebUI.click(findTestObject('New Coach Page/span_More Options'))

'Upload image for 1 coach'
WebUI.uploadFile(findTestObject('New Coach Page/label_UPLOAD PICTURE'), 'C:\\Users\\SP001\\Downloads\\Compressed\\image_test.jpg')

WebUI.delay(3)

WebUI.selectOptionByValue(findTestObject('New Coach Page/select_ng-pristine ng-valid ng month'), '1', false)

WebUI.selectOptionByValue(findTestObject('New Coach Page/select_ng-pristine ng-valid ng day'), '2', false)

WebUI.selectOptionByValue(findTestObject('New Coach Page/select_last ng-pristine ng-val year'), '1992', false)

WebUI.click(findTestObject('New Coach Page/i_fa fa-check ng-scope'))

WebUI.delay(2)

WebUI.setText(findTestObject('New Coach Page/input_firstName (1)'), 'coach 3')

WebUI.setText(findTestObject('New Coach Page/input_lastName (1)'), 'test 3')

WebUI.setText(findTestObject('New Coach Page/input_email (1)'), 'bridgetest1102+coach3@gmail.com')

WebUI.click(findTestObject('New Coach Page/button_single-button'))

WebUI.click(findTestObject('New Coach Page/span_Female'))

'Send invitations, with email notification / registration'
WebUI.click(findTestObject('New Coach Page/i_fa fa-check ng-scope'))

WebUI.click(findTestObject('New Coach Page/i_fa fa-check ng-scope (1)'))

WebUI.delay(3)

WebUI.click(findTestObject('Roster Page/span_Athletes'))

WebUI.click(findTestObject('Roster Page/span_Coaches'))

WebUI.mouseOver(findTestObject('Roster Page/span_coach 2 test 2'), FailureHandling.STOP_ON_FAILURE)

'Check that Registered = No for both coaches'
WebUI.verifyElementPresent(findTestObject('Roster Page/div_No'), 1)

'For first coach, resend invitation'
WebUI.click(findTestObject('Roster Page/span_Resend'))

WebUI.delay(1)

WebUI.click(findTestObject('Roster Page/span_Close'))

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Roster Page/span_coach 3 test 3'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Roster Page/div_No'), 1)

WebUI.click(findTestObject('Org Home Page/div_md-button md-default-theme'))

WebUI.delay(1)

'Logout'
WebUI.click(findTestObject('Org Home Page/i_fa fa-sign-out'))

WebUI.navigateToUrl('gmail.com')

WebUI.setText(findTestObject('Test/input_identifier'), 'bridgetest1102')

WebUI.click(findTestObject('Test/span_Next'))

WebUI.setText(findTestObject('Test/input_password'), '123456789Asd')

WebUI.click(findTestObject('Test/span_Next (1)'))

WebUI.click(findTestObject('Test/b_coach 1 test 1 has added you'))

WebUI.verifyElementPresent(findTestObject('Test/p_Hi coach 2'), 2)

WebDriver driver = DriverFactory.getWebDriver()

elecount = driver.findElements(By.xpath('//*[text()=\'Hi coach 2,\']')).size()

if (elecount > 2) {
    WebUI.scrollToElement(findTestObject('Test/p_Hi coach 2'), 2)
}

for (int i = 1; i < 5; i++) {
    link = WebUI.getAttribute(findTestObject('Test/a_httpstg.bridgeathletic.com o'), 'text')

    System.out.println(link)
}

WebUI.delay(2)

WebUI.verifyElementVisible(findTestObject('null'))

WebUI.click(findTestObject('null'))

'Exit page'
WebUI.closeBrowser()

