import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.concurrent.ThreadLocalRandom as ThreadLocalRandom
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ById as ById
import org.openqa.selenium.By.ByLinkText as ByLinkText
import org.openqa.selenium.By.ByName as ByName
import org.openqa.selenium.By.ByTagName as ByTagName
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
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

'Navigate to organization 45'
WebUI.click(findTestObject('Org Home Page/input_typeahead ng-pristine ng'))

WebUI.click(findTestObject('Org Home Page/a_Organization 45'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.delay(3)

WebUI.waitForElementPresent(findTestObject('Org Home Page/header_md-toolbar-tools'), 30)

'Find the "Programs, Phases, Exercises" module, click the header'
WebUI.click(findTestObject('Org Home Page/header_md-toolbar-tools'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.waitForElementVisible(findTestObject('Content Manager Page/strong_Programs'), 30)

WebUI.delay(3)

'Click the "Programs" option on the left side'
WebUI.click(findTestObject('Content Manager Page/strong_Programs'))

WebUI.delay(2)

'Click "All Programs" then click "Unassigned"'
WebUI.click(findTestObject('Content Manager Page/span_All Programs'))

WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/span_Unassigned'))

WebUI.delay(2)

'Select the team named "Swimming"'
WebUI.click(findTestObject('Content Manager Page/strong_Swimming'))

WebUI.delay(2)

all_program = WebUI.getText(findTestObject('Content Manager Page/a_ng-scope'))

String[] array = all_program.split('\\n')

number = 0

while (number == 0) {
    int ran = ThreadLocalRandom.current().nextInt(0, array.size() - 1)

    selected_program = (array[ran])

    WebDriver driver = DriverFactory.getWebDriver()

    driver.findElement(By.linkText(selected_program)).click()

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    phase_number = WebUI.getText(findTestObject('Content Manager Page/h2_7')).substring(0, 1)

    number = Integer.parseInt(phase_number)
}

WebUI.delay(2)

'Click the three dots and find the "Clone to org" option'
WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots'))

'Clone the program to the org that the coach from Step 3 is a coach'
WebUI.mouseOver(findTestObject('Content Manager Page/span_Clone to org'))

WebUI.click(findTestObject('Content Manager Page/a_Organization 19'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

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

WebUI.delay(2)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.waitForElementVisible(findTestObject('Content Manager Page/strong_Programs'), 30)

'Click the "Programs" option on the left side'
WebUI.click(findTestObject('Content Manager Page/strong_Programs'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(1)

'Find the program cloned from organization 45'
WebUI.click(findTestObject('Content Manager Page/span_All Programs'))

WebUI.verifyElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Content Manager Page/span_Unassigned'))

WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/strong_No Team'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(1)

'Select it so the details appear on the right side'
WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.linkText(selected_program)).click()

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Make sure this program has at least one phase.'
String phase_number = WebUI.getText(findTestObject('Content Manager Page/h2_7')).substring(0, 1)

int number = Integer.parseInt(phase_number)

WebUI.verifyGreaterThan(number, 0, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

'Click the edit button for the first phase'
WebUI.click(findTestObject('Phase Builder Page/i_fa fa-pencil'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(4)

'Make sure there is at least one week, if there is more than one week, delete the other weeks.'
WebDriver driver1 = DriverFactory.getWebDriver()

week_number = driver1.findElements(By.xpath('//div[@class="week-contain"]')).size()

while (week_number > 1) {
    WebUI.click(findTestObject('Phase Builder Page/i_white-icon fa fa-trash'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Phase Builder Page/span_Delete'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.delay(3)

    week_number = (week_number - 1)
}

'Expand the only remaining week (from the day view)'
WebUI.click(findTestObject('Phase Builder Page/button_expand-btn md-button'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Clone or delete days so there are 3 days in the week'
WebDriver driver3 = DriverFactory.getWebDriver()

day_number = driver3.findElements(By.xpath('//div[starts-with(@class, "day ng-scope")]')).size()

System.out.println(day_number)

while (day_number > 3) {
    WebUI.mouseOver(findTestObject('Phase Builder Page/header_text-center day-number'))

    WebUI.click(findTestObject('Phase Builder Page/i_custom-icon icon-three-dots'))

    WebUI.click(findTestObject('Phase Builder Page/li_Delete Day'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Phase Builder Page/span_Delete'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    day_number = (day_number - 1)
}

while (day_number < 3) {
    WebUI.mouseOver(findTestObject('Phase Builder Page/header_text-center day-number'))

    WebUI.click(findTestObject('Phase Builder Page/i_fa fa-files-o ng-scope'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    day_number = (day_number + 1)
}

WebUI.delay(2)

'Go to the "Load Progression View"'
WebUI.click(findTestObject('Phase Builder Page/img_ng-scope_week-view'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Clone the week '
WebUI.mouseOver(findTestObject('Phase Builder Page/div_md-button md-default-theme_selected'))

WebUI.click(findTestObject('Phase Builder Page/button_fa fa-files-o md-button'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Drag Week 2 to be Week 1'
WebUI.dragAndDropToObject(findTestObject('Phase Builder Page/div_md-button md-default-theme_unselect'), findTestObject('Phase Builder Page/div_md-button md-default-theme_selected'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Click "+Add Week"'
WebUI.click(findTestObject('Phase Builder Page/span_Add Week'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(3)

'Move Week 3 to be Week 2'
WebUI.dragAndDropToObject(findTestObject('Phase Builder Page/div_md-button md-default-theme_unselect_2'), findTestObject(
        'Phase Builder Page/div_md-button md-default-theme_unselect'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

'Click Day 3/3 in the dropdown '
WebUI.click(findTestObject('Phase Builder Page/i_fa fa-chevron-down'))

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/span_Day 3'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Make sure you only see 2 weeks when looking at Day 3'
WebUI.verifyElementNotPresent(findTestObject('Phase Builder Page/div_md-button md-default-theme_selected'), 5)

'Minimze the left panel'
WebUI.click(findTestObject('Phase Builder Page/i_fa fa-chevron-left ng-scope_left panel'))

WebUI.waitForElementPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Make sure the weeks readjust the width so it looks like 4 weeks would fit'
week_width = WebUI.getElementWidth(findTestObject('Phase Builder Page/div_md-button md-default-theme_unselect'))

header_width = WebUI.getElementWidth(findTestObject('Phase Builder Page/div_day-controll'))

WebUI.verifyGreaterThanOrEqual(header_width, week_width * 6)

'Expand the left panel'
WebUI.click(findTestObject('Phase Builder Page/i_fa fa-chevron-right ng-scope_panel'))

WebUI.waitForElementPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Make sure the weeks readjust the width so 4 weeks can fit on the right side'
week_width = WebUI.getElementWidth(findTestObject('Phase Builder Page/div_md-button md-default-theme_unselect'))

header_width = WebUI.getElementWidth(findTestObject('Phase Builder Page/div_day-controll'))

WebUI.verifyGreaterThanOrEqual(header_width, week_width * 4)

'Click the analyze button and make sure the graphs render.'
WebUI.click(findTestObject('Phase Builder Page/i_fa fa-bar-chart'))

WebUI.verifyElementPresent(findTestObject('Phase Builder Page/div_Total Volume and Average I'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/i_fa fa-chevron-down'))

WebUI.delay(1)

WebUI.click(findTestObject('Phase Builder Page/span_Day 1'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

'Delete Week 2\\r\\nDelete the Week 2 again (week 3 should have moved to be week 2 after step 28)\\r\\nMake sure you cannot delete the only remaining week - should see a pop up.'
while (WebUI.verifyElementPresent(findTestObject('Phase Builder Page/md-dialog'), 3, FailureHandling.OPTIONAL) == false) {
    WebUI.mouseOver(findTestObject('Phase Builder Page/div_md-button md-default-theme_selected'))

    WebUI.click(findTestObject('Phase Builder Page/button_fa fa-trash md-button m'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Phase Builder Page/span_Delete'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    WebUI.delay(2)
}

WebUI.click(findTestObject('Phase Builder Page/span_Close'))

