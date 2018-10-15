import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.List as List
import java.lang.reflect.Array as Array
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

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.waitForElementVisible(findTestObject('Content Manager Page/strong_Programs'), 30)

WebUI.delay(4)

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

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.waitForElementVisible(findTestObject('Content Manager Page/strong_Programs'), 30)

WebUI.delay(4)

'Click the "Programs" option on the left side'
WebUI.click(findTestObject('Content Manager Page/strong_Programs'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(1)

'Click "+ New" from the left panel'
WebUI.click(findTestObject('Content Manager Page/span_New'))

'Input a name and description (do not select a team), press green checkmark to save'
WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-untouched'), Program_Name)

WebUI.setText(findTestObject('Content Manager Page/textarea_ng-pristine ng-untouc'), Program_Description)

WebUI.click(findTestObject('Content Manager Page/i_fa fa-check ng-scope'))

WebUI.waitForElementNotPresent(findTestObject('Template Blocks/Content Manager/span_Loading content'), 30)

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(3)

'Check that the left panel shows the "No Teams" list of programs'
WebUI.verifyElementPresent(findTestObject('Content Manager Page/span_No Team'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/strong_Test Program 01'), 30)

'Click the edit icon and change the name and description'
WebUI.click(findTestObject('Content Manager Page/i_fa fa-pencil ng-scope'))

WebUI.setText(findTestObject('Content Manager Page/input_ng-pristine ng-untouched (1)'), 'Test Program 01 edited')

WebUI.setText(findTestObject('Content Manager Page/textarea_ng-pristine ng-untouc (1)'), 'Test Description 01 edited')

WebUI.click(findTestObject('Content Manager Page/span_SAVE'))

'Click the three dots and find the clone option'
WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots'))

'Clone the program from the dropdown menu'
WebUI.click(findTestObject('Content Manager Page/button_md-button md-default-th'))

WebUI.verifyElementNotPresent(findTestObject('Icon/span_Saving...'), 30)

WebUI.verifyElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.click(findTestObject('Content Manager Page/strong_No Team'))

WebUI.verifyElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

'Check that the name is the same as the original program + "Copy" at the end '
WebUI.verifyElementPresent(findTestObject('Content Manager Page/strong_Test Program 01 edited'), 30)

'Find the original program in the list on the left panel & Select the original program so the details show up on the right side'
WebUI.click(findTestObject('Content Manager Page/strong_Test Program 01 edited (1)'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

'Delete the original program with the icon in the top right corner'
WebUI.click(findTestObject('Content Manager Page/i_fa fa-trash ng-scope'))

WebUI.delay(1)

WebUI.click(findTestObject('Content Manager Page/i_ Yes'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

'Check that the left panel stays on the "No Team" list and the deleted program is removed'
WebUI.click(findTestObject('Content Manager Page/strong_No Team'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.verifyElementNotPresent(findTestObject('Content Manager Page/strong_Test Program 01 edited (1)'), 30)

WebUI.delay(1)

'Find the program cloned from organization 45'
WebUI.click(findTestObject('Content Manager Page/span_All Programs (1)'))

WebUI.verifyElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Content Manager Page/strong_No Team'))

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Processing...'), 30)

WebUI.delay(1)

'Select it so the details appear on the right side'
WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.linkText(selected_program)).click()

WebUI.waitForElementNotPresent(findTestObject('Icon/span_Loading...'), 30)

'Click the three dots and select the "Analyze" option'
WebUI.click(findTestObject('Content Manager Page/i_custom-icon icon-three-dots'))

WebUI.click(findTestObject('Content Manager Page/span_Analyze'))

WebUI.waitForElementNotPresent(findTestObject('Content Manager Page/span_Please wait processing an'), 30)

'Make sure the graphs and charts render (will check content of graphs / charts in another flow)'
WebUI.verifyElementPresent(findTestObject('Content Manager Page/div_phase-statistic'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/div_line-chart'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/div'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/div (1)'), 30)

WebUI.verifyElementPresent(findTestObject('Content Manager Page/div (2)'), 30)

WebUI.clickOffset(findTestObject('Content Manager Page/div_md-dialog-container ng-sco'), 1, 1)

WebUI.closeBrowser()

