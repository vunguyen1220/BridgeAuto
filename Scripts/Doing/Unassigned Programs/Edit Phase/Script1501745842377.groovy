import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import jdk.nashorn.internal.runtime.FindProperty as FindProperty
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.remote.server.handler.FindElement as FindElement
import org.openqa.selenium.remote.server.handler.FindElements as FindElements
import java.lang.invoke.MethodHandleNatives.Constants as Constants
import java.sql.Driver as Driver
import java.text.NumberFormat as NumberFormat
import java.util.concurrent.ConcurrentSkipListMap.Values as Values
import java.util.concurrent.ThreadLocalRandom as ThreadLocalRandom
import javax.swing.text.MaskFormatter.UpperCaseCharacter as UpperCaseCharacter
import javax.xml.xpath.XPath as XPath
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.apache.commons.lang.WordUtils as WordUtils
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ById as ById
import org.openqa.selenium.By.ByLinkText as ByLinkText
import org.openqa.selenium.By.ByName as ByName
import org.openqa.selenium.By.ByTagName as ByTagName
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

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

String phase_number = WebUI.getText(findTestObject('Content Manager Page/h2_7')).substring(0, 1)

int number = Integer.parseInt(phase_number)

WebUI.verifyGreaterThan(number, 0, FailureHandling.STOP_ON_FAILURE)

phase_name = WebUI.getText(findTestObject('Content Manager Page/h3_first phase'))

WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots (1)'))

WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/li_Clone Phase'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

number = (number + 1)

WebUI.delay(2)

//phase_name_ed = WordUtils.capitalizeFully(phase_name)
//System.out.println(phase_name_ed)
String phase_name_copy = phase_name + ' COPY'

System.out.println(phase_name_copy)

//WebDriver driver1 = DriverFactory.getWebDriver()
//driver1.findElement(By.xpath('//*[text()=\'' + phase_name_copy + '\']')).click()
WebDriver driver2 = DriverFactory.getWebDriver()

driver2.findElement(By.xpath(('id("phases-container")/div[@class="ng-scope"][' + number) + ']/div[@class="row ng-scope"]/div[@class="col-md-2 phase-bt-icon"]/icon[2]/i[@class="fa fa-pencil"]')).click()

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.verifyElementText(findTestObject('Content Manager Page/span_General Preparation Copy'), phase_name_copy)

WebUI.doubleClick(findTestObject('Content Manager Page/span_General Preparation Copy (1)'))

WebUI.setText(findTestObject('Content Manager Page/input_form-control ng-pristine'), phase_name_copy + ' edited')

WebUI.click(findTestObject('Content Manager Page/i_icon-check check-btn'))

WebUI.delay(2)

phase_name_copy = WebUI.getText(findTestObject('Content Manager Page/span_General Preparation Copy'))

WebUI.click(findTestObject('Content Manager Page/span_SwimmingGym_L3_In_Distanc'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(2)

WebDriver driver3 = DriverFactory.getWebDriver()

new_phase = driver3.findElement(By.xpath(('id("phases-container")/div[@class="ng-scope"][' + number) + ']/div[@class="row ng-scope"]/div[@class="col-md-5"]/h3[contains(@class,"ng-binding")]')).getText()

WebUI.verifyMatch(new_phase, phase_name_copy, false)

last_phase = findTestObject('Content Manager Page/h3_GENERAL PREPARATION COPY ed')

last_phase.findProperty('xpath').setValue(('id("phases-container")/div[@class="ng-scope"][' + number) + ']/div[@class="row ng-scope"]/div[@class="col-md-5"]/h3[contains(@class,"ng-binding")]')

last_phase.findProperty('xpath').setActive(true)

WebUI.scrollToElement(last_phase, 30)

WebUI.dragAndDropToObject(last_phase, findTestObject('Content Manager Page/h3_first phase'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(1)

name_first_phase = WebUI.getText(findTestObject('Content Manager Page/h3_first phase'))

if (WebUI.verifyMatch(name_first_phase, phase_name_copy, false, FailureHandling.OPTIONAL) == false) {
    WebUI.dragAndDropToObject(findTestObject('Content Manager Page/h3_first phase'), findTestObject('Content Manager Page/h3_second phase'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.delay(1)
}

WebUI.mouseOver(findTestObject('Content Manager Page/span_Add Phase'))

WebUI.click(findTestObject('Content Manager Page/a_Add Existing'))

if (WebUI.verifyElementPresent(findTestObject('Content Manager Page/strong_POWER COPY edited'), 3, FailureHandling.OPTIONAL) == 
false) {
    WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots (1)'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Content Manager Page/li_Save as Template'))

    WebUI.delay(1)

    WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope (2)'))

    WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

    WebUI.delay(2)

    WebUI.click(findTestObject('Content Manager Page/i_fa fa-close ng-scope'))

    WebUI.mouseOver(findTestObject('Content Manager Page/span_Add Phase'))

    WebUI.click(findTestObject('Content Manager Page/a_Add Existing'))
}

template_phase = WebUI.getText(findTestObject('Content Manager Page/strong_POWER COPY edited'))

template_phase = template_phase.substring(0, template_phase.length() - 2).toUpperCase()

WebUI.click(findTestObject('Content Manager Page/strong_POWER COPY edited'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(4)

number = (number + 1)

last_phase.findProperty('xpath').setValue(('id("phases-container")/div[@class="ng-scope"][' + number) + ']/div[@class="row ng-scope"]/div[@class="col-md-5"]/h3[contains(@class,"ng-binding")]')

last_phase.findProperty('xpath').setActive(true)

name_last_phase = WebUI.getText(last_phase)

WebUI.verifyMatch(name_last_phase.toUpperCase(), template_phase + ' copy'.toUpperCase(), false)

WebUI.mouseOver(findTestObject('Content Manager Page/span_Add Phase'))

WebUI.click(findTestObject('Content Manager Page/a_Add New'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-untouched (2)'), 'Test Phase 01')

WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope (1)'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(3)

name_new_phase = WebUI.getText(findTestObject('Content Manager Page/span_General Preparation Copy'))

WebUI.click(findTestObject('Content Manager Page/span_SwimmingGym_L3_In_Distanc'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.delay(1)

number = (number + 1)

last_phase.findProperty('xpath').setValue(('id("phases-container")/div[@class="ng-scope"][' + number) + ']/div[@class="row ng-scope"]/div[@class="col-md-5"]/h3[contains(@class,"ng-binding")]')

last_phase.findProperty('xpath').setActive(true)

name_last_phase = WebUI.getText(last_phase)

WebUI.verifyMatch(name_last_phase, name_new_phase, false)

WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots_second phase'))

WebUI.delay(1)

WebUI.click(findTestObject('Content Manager Page/li_Delete Phase'))

WebUI.delay(1)

WebUI.click(findTestObject('Content Manager Page/span_Yes'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(5)

WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots (1)'))

WebUI.delay(1)

WebUI.click(findTestObject('Content Manager Page/li_Save as Template'))

WebUI.delay(1)

WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-valid ng-'), name_template_phase)

WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope_edit name'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.delay(2)

not_run: WebUI.click(findTestObject('Content Manager Page/i_fa fa-chevron-left back-icon'))

not_run: WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

not_run: WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/strong_Phases'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

WebUI.click(findTestObject('Content Manager Page/span_All Phases'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/span_All Phases_active'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/span_Templates'), 30)

template_phase = findTestObject('Content Manager Page/strong_Test Template Phase 01')

template_phase.findProperty('text').setValue(name_template_phase)

template_phase.findProperty('text').setActive(true)

//template_phase.findProperty('xpath').setActive(false)
WebUI.verifyElementPresent(template_phase, 30)

WebUI.closeBrowser()

