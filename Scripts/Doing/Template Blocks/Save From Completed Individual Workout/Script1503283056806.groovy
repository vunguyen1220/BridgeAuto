import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.supercsv.cellprocessor.ParseInt as ParseInt
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
import com.sun.beans.decoder.FalseElementHandler as FalseElementHandler
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

WebUI.click(findTestObject('Save From Completed Individual Workout/span_TEAMS (4)'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebDriver driver = DriverFactory.getWebDriver()

int team_number = driver.findElements(By.xpath('id("teams-content")/md-content[@class="user-organization ng-scope md-default-theme"]/div[@class="list teams details"]/md-card[@class="md-default-theme"]/md-content[@class="md-default-theme"]/md-list[1]/md-item[@class="ng-scope"]/md-item-content[1]/div[1]/span[@class="center ng-binding"][3]')).size()

for (int i = 1; i <= team_number; i = (i + 1)) {
    active_program = driver.findElement(By.xpath(('id("teams-content")/md-content[@class="user-organization ng-scope md-default-theme"]/div[@class="list teams details"]/md-card[@class="md-default-theme"]/md-content[@class="md-default-theme"]/md-list[1]/md-item[contains(@class,"ng-scope")][' + 
            i) + ']/md-item-content[1]/div[1]/span[@class="center ng-binding"][3]')).getText()

    System.out.println(active_program)

    active_number = Integer.parseInt(active_program)

    if ((WebUI.verifyGreaterThan(active_number, 0, FailureHandling.OPTIONAL) == true) && (driver.findElements(By.xpath(('id("teams-content")/md-content[@class="user-organization ng-scope md-default-theme"]/div[@class="list teams details"]/md-card[@class="md-default-theme"]/md-content[@class="md-default-theme"]/md-list[1]/md-item[@class="ng-scope"][' + 
            i) + ']/md-item-content[1]/div[1]/div[@aria-hidden="false"]/i[@class="fa fa-lock"]')).size() == 0)) {
        driver.findElement(By.xpath(('id("teams-content")/md-content[@class="user-organization ng-scope md-default-theme"]/div[@class="list teams details"]/md-card[@class="md-default-theme"]/md-content[@class="md-default-theme"]/md-list[1]/md-item[contains(@class,"ng-scope")][' + 
                i) + ']/md-item-content[1]/div[1]/div[1]/span[@class="ng-binding"]')).click()

        break
    }
}

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Team Home Page/span_See All'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.mouseOver(findTestObject('Save From Completed Individual Workout/div_Program for exercise modif'))

WebUI.click(findTestObject('Save From Completed Individual Workout/span_athlete 2 test 2'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.waitForElementPresent(findTestObject('Save From Completed Individual Workout/div_fc-content'), 30)

WebUI.click(findTestObject('Save From Completed Individual Workout/i_fa fa-pencil ng-scope'))

WebUI.click(findTestObject('Save From Completed Individual Workout/i_fa fa-pencil ng-scope (1)'))

if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/md-dialog_confirm-popup'), 3, FailureHandling.OPTIONAL) == 
true) {
    WebUI.click(findTestObject('Save From Completed Individual Workout/div_md-container'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Save From Completed Individual Workout/span_OK'))

    WebUI.waitForElementPresent(findTestObject('Icon/span_Loading...'), 30)
}

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebDriver driver1 = DriverFactory.getWebDriver()

week_number = driver1.findElements(By.xpath('id("phase-day-view")/div[@class="weeks"]/div[contains(@class,"week week")]')).size()

for (i = 1; i <= week_number; i = (i + 1)) {
    driver1.findElement(By.xpath(('id("phase-day-view")/div[@class="weeks"]/div[' + i) + ']/header[contains(@class,"text-center")]/button[@class="collapse-btn md-button md-default-theme active"]')).click()

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    if ((driver1.findElements(By.xpath('//div[@class="days clearfix unselector"]/div[@class="day ng-scope"]')).size() + 
    driver1.findElements(By.xpath('//div[@class="days clearfix unselector"]/div[@class="day ng-scope hidden-day"]')).size()) == 
    0) {
        WebUI.click(findTestObject('Save From Completed Individual Workout/button_collapse-btn md-button'))
    } else {
        break
    }
}

while (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/input_block-to-add-19791549'), 
    5, FailureHandling.OPTIONAL) == false) {
    WebUI.click(findTestObject('Save From Completed Individual Workout/i_fa fa-chevron-right ng-scope'))

    WebUI.delay(2)
}

WebUI.click(findTestObject('Save From Completed Individual Workout/input_block-to-add-19791549'))

WebUI.setText(findTestObject('Save From Completed Individual Workout/input_block-to-add-19791549'), 'Barbell Back Squat')

WebUI.delay(2)

WebUI.click(findTestObject('Save From Completed Individual Workout/span_Barbell Back Squat'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Save From Completed Individual Workout/span_1RME'))

WebUI.delay(1)

WebUI.click(findTestObject('Save From Completed Individual Workout/span_Absolute'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Reps_1'), '10')

WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Weight_1'), '50')

if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/div_md-thumb'), 3, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Save From Completed Individual Workout/div_md-thumb'))
}

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Save From Completed Individual Workout/input_text-right unselector'))

WebUI.sendKeys(findTestObject('Save From Completed Individual Workout/input_text-right unselector'), '30')

WebUI.click(findTestObject('Save From Completed Individual Workout/button_md-button type Workset'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Rep_2'), '12')

WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Weight_2'), '65')

WebUI.delay(1)

WebUI.click(findTestObject('Save From Completed Individual Workout/header_exercise ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Reps_1'), 
    1, FailureHandling.OPTIONAL) == true) {
    WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Reps_1'), '50')
} else if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Weight_1'), 
    1, FailureHandling.OPTIONAL) == true) {
    WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Weight_1'), '50')
} else if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Distance_1'), 
    1, FailureHandling.OPTIONAL) == true) {
    WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Distance_1'), '50')
} else if (WebUI.verifyElementPresent(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Height_1'), 
    1, FailureHandling.OPTIONAL) == true) {
    WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive_Height_1'), '50')
} else {
    WebUI.click(findTestObject('Save From Completed Individual Workout/i_fa fa-pencil ng-scope (2)'))

    WebUI.delay(2)

    WebUI.click(findTestObject('Save From Completed Individual Workout/div_md-thumb'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Reps_1'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

    WebUI.setText(findTestObject('Save From Completed Individual Workout/input_unselector new-positive-Reps_1'), '50')
}

WebUI.delay(2)

WebUI.waitForElementPresent(findTestObject('Save From Completed Individual Workout/span_Warmup'), 30)

block_template_name = WebUI.getText(findTestObject('Save From Completed Individual Workout/span_Warmup'))

WebUI.click(findTestObject('Save From Completed Individual Workout/span_Warmup'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.click(findTestObject('Save From Completed Individual Workout/i_custom-icon icon-three-dots'))

WebUI.delay(1)

WebUI.click(findTestObject('Save From Completed Individual Workout/span_Save As Template'))

WebUI.sendKeys(findTestObject('Save From Completed Individual Workout/input_ng-pristine ng-untouched'), '-123')

block_template_name = (block_template_name + '-123')

WebUI.click(findTestObject('Save From Completed Individual Workout/i_fa fa-check ng-scope'))

WebUI.waitForElementPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Save From Completed Individual Workout/a_glyphicon glyphicon-home'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.waitForElementPresent(findTestObject('Org Home Page/header_md-toolbar-tools'), 30)

'Find the "Programs, Phases, Exercises" module, click the header'
WebUI.click(findTestObject('Org Home Page/header_md-toolbar-tools'))

WebUI.delay(2)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.click(findTestObject('Save From Completed Individual Workout/strong_Blocks'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

WebUI.verifyTextPresent(block_template_name, false)

WebDriver driver2 = DriverFactory.getWebDriver()

driver2.findElement(By.linkText(block_template_name + '  ')).click()

