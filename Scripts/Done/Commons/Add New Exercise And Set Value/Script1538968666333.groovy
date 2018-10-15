import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


CustomKeywords.'com.bridgeathletic.block.expandBlockFromName'(var_blockName)

CustomKeywords.'com.bridgeathletic.exercise.addNewExerciseFromBlockName'(var_blockName, var_exerciseName)

CustomKeywords.'com.bridgeathletic.exercise.selectExerciseFromName'(var_blockName, var_exerciseName)

if (var_paramList == [] && var_paramNumbers > 0){
	
	var_paramList = CustomKeywords.'com.bridgeathletic.parameter.getRandomParameterList'(var_paramNumbers)
	
}

CustomKeywords.'com.bridgeathletic.exercise.editParameterNumbers'(var_paramList)

CustomKeywords.'com.bridgeathletic.exercise.editSetNumbers'(var_setNumbers)

int tmp = 1

while (tmp <= var_setNumbers){

	List<Integer> valueList = CustomKeywords.'com.bridgeathletic.random.getRandomListNumber'(1, 100, var_paramList.size())

	CustomKeywords.'com.bridgeathletic.set.setValueForSet'(tmp, valueList)
	
	tmp = tmp + 1

}