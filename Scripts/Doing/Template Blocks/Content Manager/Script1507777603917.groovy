import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.concurrent.locks.Condition as Condition
import org.junit.After as After
import org.openqa.selenium.By as By
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

WebUI.click(findTestObject('Template Blocks/Content Manager/a_Login'))

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
WebUI.setText(findTestObject('Template Blocks/Content Manager/input_email'), GlobalVariable.gl_adminEmail)

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_password'), GlobalVariable.gl_adminPassword)

WebUI.click(findTestObject('Template Blocks/Content Manager/button_LOGIN'))

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reload'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Template Blocks/Content Manager/span_Reload'))
}

'Navigate to a random organization'
WebUI.click(findTestObject('Template Blocks/Content Manager/input_typeahead ng-pristine ng'))

WebUI.click(findTestObject('Template Blocks/Content Manager/a_Organization 19'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_coaches'))

'Find a coach in that organization and record their userId (if no coaches in org, repeat step 1)'
if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_coach 1 test 1'), 30, FailureHandling.OPTIONAL) == 
true) {
    WebUI.click(findTestObject('Template Blocks/Content Manager/span_coach 1 test 1'))
}

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

Coach = WebUI.getText(findTestObject('Template Blocks/Content Manager/div_coach_email'))

System.out.println(Coach)

WebUI.click(findTestObject('Template Blocks/Content Manager/a_glyphicon glyphicon-home'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-button md-default-theme'))

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-sign-out'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/a_Login'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/a_Login'))

'Login as the coach from previous step'
WebUI.setText(findTestObject('Template Blocks/Content Manager/input_email'), Coach)

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_password'), '123456789')

WebUI.click(findTestObject('Template Blocks/Content Manager/button_LOGIN'))

'You will land on the organization homepage'
WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/header_md-toolbar-tools'), 30)

WebUI.delay(3)

'Click on the "Content manager"'
WebUI.click(findTestObject('Template Blocks/Content Manager/header_md-toolbar-tools'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

'Click "Blocks" >> My Blocks'
WebUI.click(findTestObject('Template Blocks/Content Manager/strong_Blocks'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_My Blocks'), 30)

'Using the "+new" button, create a new template bock, name it \'template-1\''
WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-plus ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

Calendar cal = Calendar.getInstance()

rd = cal.getTime().toString()

block_name1 = ('template1 ' + rd)

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_block-name'), block_name1)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-check ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

'Open the template block for editing'
WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

'Add 3 exercises to the block, each with 5 sets'
WebUI.click(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'))

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'), 'Barbell Bench')

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Barbell Bench Press'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'))

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'), 'Barbell Bench')

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Barbell Bench Press (Floo'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'))

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_block-to-add-8165472'), 'Barbell Bench')

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Incline Barbell Bench Pre'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_first'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_param'))

while (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'), 1, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'))

    WebUI.delay(2)
}

WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb'))

WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebDriver driver = DriverFactory.getWebDriver()

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

while (set_number != 5) {
    if (set_number < 5) {
        WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

        set_number = (set_number + 1)
    } else {
        WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-trash ng-scope'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

        WebUI.delay(1)

        set_number = (set_number - 1)
    }
}

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reps'), 2, FailureHandling.OPTIONAL) == 
true) {
    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-1'), '15')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-2'), '12')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-3'), '10')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-4'), '8')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-5'), '6')
}

WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_second'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_param'))

while (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'), 1, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'))

    WebUI.delay(2)
}

WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb'))

WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

while (set_number != 5) {
    if (set_number < 5) {
        WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

        set_number = (set_number + 1)
    } else {
        WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-trash ng-scope'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

        WebUI.delay(1)

        set_number = (set_number - 1)
    }
}

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reps'), 2, FailureHandling.OPTIONAL) == 
true) {
    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-1'), '15')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-2'), '12')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-3'), '10')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-4'), '8')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-5'), '6')
}

WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_third'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_param'))

while (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'), 1, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb_checked'))

    WebUI.delay(2)
}

WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb'))

WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

while (set_number != 5) {
    if (set_number < 5) {
        WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

        set_number = (set_number + 1)
    } else {
        WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-trash ng-scope'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

        WebUI.delay(1)

        set_number = (set_number - 1)
    }
}

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reps'), 2, FailureHandling.OPTIONAL) == 
true) {
    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-1'), '15')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-2'), '12')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-3'), '10')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-4'), '8')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-5'), '6')
}

'Change the exercise parameters and the set prescriptions'
WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_first'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_param'))

WebUI.click(findTestObject('Template Blocks/Content Manager/div_md-thumb'))

WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

while (set_number != 5) {
    if (set_number < 5) {
        WebUI.click(findTestObject('Template Blocks/Content Manager/button_md-button type Workset'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

        set_number = (set_number + 1)
    } else {
        WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-trash ng-scope'))

        WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

        WebUI.delay(1)

        set_number = (set_number - 1)
    }
}

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Reps'), 2, FailureHandling.OPTIONAL) == 
true) {
    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-1'), '15')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-2'), '12')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-3'), '10')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-4'), '8')

    WebUI.setText(findTestObject('Template Blocks/Content Manager/input_unselector new-positive-number reps-5'), '6')
}

if (WebUI.verifyElementPresent(findTestObject('Template Blocks/Content Manager/span_Time'), 2, FailureHandling.OPTIONAL) == 
true) {
    WebUI.sendKeys(findTestObject('Template Blocks/Content Manager/input_text-right unselector time-1'), '10')

    WebUI.sendKeys(findTestObject('Template Blocks/Content Manager/input_text-right unselector time-2'), '15')

    WebUI.sendKeys(findTestObject('Template Blocks/Content Manager/input_text-right unselector time-3'), '20')

    WebUI.sendKeys(findTestObject('Template Blocks/Content Manager/input_text-right unselector time-4'), '25')

    WebUI.sendKeys(findTestObject('Template Blocks/Content Manager/input_text-right unselector time-5'), '30')
}

WebUI.delay(2)

'Navigate back to the content manager >> blocks'
WebUI.click(findTestObject('Template Blocks/Content Manager/span_Content Manager'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/strong_Blocks'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.delay(1)

template_1 = findTestObject('Template Blocks/Content Manager/strong_template-1')

template_1.addProperty('text', ConditionType.CONTAINS, block_name1, true)

WebUI.click(template_1)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

'Clone \'template-1\' and name the new copy \'template-2\''
WebUI.click(findTestObject('Template Blocks/Content Manager/i_custom-icon icon-three-dots'))

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Clone block'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

'Open template-2 for editing'
WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_name'))

block_name2 = ('template2 ' + rd)

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_form-control'), block_name2)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_icon-check'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

WebUI.delay(2)

'Make changes to the content of the template'
WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_first'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

WebUI.verifyEqual(set_number, 5)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_second'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

WebUI.verifyEqual(set_number, 5)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_exerecise_third'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

set_number = driver.findElements(By.xpath('//div[@class="single-set"]')).size()

WebUI.verifyEqual(set_number, 5)

WebUI.delay(2)

'Navigate back to the content manager >> blocks'
WebUI.click(findTestObject('Template Blocks/Content Manager/span_Content Manager'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/strong_Blocks'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.delay(1)

template_2 = findTestObject('Template Blocks/Content Manager/strong_template-2')

template_2.addProperty('text', ConditionType.CONTAINS, block_name2, true)

'Clone \'template-2\' and name the new copy \'template-3\''
WebUI.click(template_2)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_custom-icon icon-three-dots'))

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Clone block'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_fa fa-pencil ng-scope_name'))

block_name3 = ('template3 ' + rd)

WebUI.setText(findTestObject('Template Blocks/Content Manager/input_form-control'), block_name3)

WebUI.click(findTestObject('Template Blocks/Content Manager/i_icon-check'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Content Manager'))

WebUI.waitForElementPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Processing...'), 30)

WebUI.click(findTestObject('Template Blocks/Content Manager/strong_Blocks'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

WebUI.delay(1)

template_3 = findTestObject('Template Blocks/Content Manager/strong_template-3')

template_3.addProperty('text', ConditionType.CONTAINS, block_name3, true)

'Make sure that the 3 templates appear under the list of "My templates"'
WebUI.verifyElementPresent(template_3, 30)

WebUI.click(template_3)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

'Delete \'template-3\''
WebUI.click(findTestObject('Template Blocks/Content Manager/i_custom-icon icon-three-dots'))

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Remove block'))

WebUI.click(findTestObject('Template Blocks/Content Manager/span_Yes'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Saving...'), 30)

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading...'), 30)

'Make sure that template-3 does not appear under My templates'
WebUI.waitForElementNotPresent(template_3, 30)

WebUI.verifyElementNotPresent(template_3, 3)

