import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())


RunConfiguration.setExecutionSettingFile('C:\\Users\\VuNA\\AppData\\Local\\Temp\\Katalon\\Test Cases\\Doing\\Assigned Program -- Edits\\Clone Phase, Move Phase\\20190108_155130\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCaseRawScript(
'''import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import bridgeathletic.program as program
import internal.GlobalVariable as GlobalVariable

not_run: WebUI.callTestCase(findTestCase('Done/Commons/Open App'), [:])

'Login as test+assay2@bridgeathletic.com (this is a super admin account)'
not_run: WebUI.callTestCase(findTestCase('Done/Commons/Login'), [('var_email') : GlobalVariable.gl_adminEmail, ('var_password') : GlobalVariable.gl_adminPassword])

'Navigate to a random organization (org 2)'
not_run: WebUI.callTestCase(findTestCase('Done/Commons/Select Organization'), [('var_orgNumber') : 2])

WebUI.click(findTestObject('Org Home Page/span_LIBRARY'))

WebUI.callTestCase(findTestCase('Done/Commons/Waiting'), [:])

WebUI.callTestCase(findTestCase('Done/Commons/Filter Programs'), [('var_byType') : ['Assigned']])

int totalProgramNumbers = CustomKeywords.'bridgeathletic.program.getTotalProgramNumbers'()

boolean flag = false

int tmp = 1

Object selectedProgramInfo = new program()

''', 'Test Cases/Doing/Assigned Program -- Edits/Clone Phase, Move Phase', new TestCaseBinding('Test Cases/Doing/Assigned Program -- Edits/Clone Phase, Move Phase',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
