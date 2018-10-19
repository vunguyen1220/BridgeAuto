
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import java.util.List

import com.bridgeathletic.program

import com.bridgeathletic.workout

import java.util.ArrayList

import com.kms.katalon.core.testobject.TestObject

import com.bridgeathletic.phase


def static "com.bridgeathletic.info.getBlockInfo"() {
    (new com.bridgeathletic.info()).getBlockInfo()
}

def static "com.bridgeathletic.info.getTemplateBlockInfo"() {
    (new com.bridgeathletic.info()).getTemplateBlockInfo()
}

def static "com.bridgeathletic.info.getBlockInfoInCal"(
    	int blockNumber	) {
    (new com.bridgeathletic.info()).getBlockInfoInCal(
        	blockNumber)
}

def static "com.bridgeathletic.info.getWorkoutInfo"(
    	int workoutNumber	) {
    (new com.bridgeathletic.info()).getWorkoutInfo(
        	workoutNumber)
}

def static "com.bridgeathletic.info.getTeamInfo"(
    	int teamNumber	) {
    (new com.bridgeathletic.info()).getTeamInfo(
        	teamNumber)
}

def static "com.bridgeathletic.info.getAttendanceInfo"(
    	int athleteNumber	) {
    (new com.bridgeathletic.info()).getAttendanceInfo(
        	athleteNumber)
}

def static "com.bridgeathletic.info.getEventInfo"(
    	int eventNumber	) {
    (new com.bridgeathletic.info()).getEventInfo(
        	eventNumber)
}

def static "com.bridgeathletic.info.getTestResultInfoInTeamLeaderModule"() {
    (new com.bridgeathletic.info()).getTestResultInfoInTeamLeaderModule()
}

def static "com.bridgeathletic.info.getTestResultInfoInTestHistoryTable"() {
    (new com.bridgeathletic.info()).getTestResultInfoInTestHistoryTable()
}

def static "com.bridgeathletic.info.getTestResultInfoInHighCharts"() {
    (new com.bridgeathletic.info()).getTestResultInfoInHighCharts()
}

def static "com.bridgeathletic.exercise.getExerciseInfo"() {
    (new com.bridgeathletic.exercise()).getExerciseInfo()
}

def static "com.bridgeathletic.exercise.addNewExerciseFromBlockName"(
    	String blockName	
     , 	String exerciseName	) {
    (new com.bridgeathletic.exercise()).addNewExerciseFromBlockName(
        	blockName
         , 	exerciseName)
}

def static "com.bridgeathletic.exercise.selectExerciseFromName"(
    	String blockName	
     , 	String exerciseName	) {
    (new com.bridgeathletic.exercise()).selectExerciseFromName(
        	blockName
         , 	exerciseName)
}

def static "com.bridgeathletic.exercise.editParameterNumbers"(
    	java.util.List<String> paramName	) {
    (new com.bridgeathletic.exercise()).editParameterNumbers(
        	paramName)
}

def static "com.bridgeathletic.exercise.editSetNumbers"(
    	int setNumbers	) {
    (new com.bridgeathletic.exercise()).editSetNumbers(
        	setNumbers)
}

def static "com.bridgeathletic.set.getAllSetsInfo"() {
    (new com.bridgeathletic.set()).getAllSetsInfo()
}

def static "com.bridgeathletic.set.getSetInfo"(
    	int index	) {
    (new com.bridgeathletic.set()).getSetInfo(
        	index)
}

def static "com.bridgeathletic.set.setValueForSet"(
    	int setIndex	
     , 	java.util.List<java.lang.Integer> setValue	) {
    (new com.bridgeathletic.set()).setValueForSet(
        	setIndex
         , 	setValue)
}

def static "com.bridgeathletic.program.printProgramInfo"(
    	program pr	) {
    (new com.bridgeathletic.program()).printProgramInfo(
        	pr)
}

def static "com.bridgeathletic.program.getTotalProgramNumbers"() {
    (new com.bridgeathletic.program()).getTotalProgramNumbers()
}

def static "com.bridgeathletic.program.selectProgramInLibraryByIndex"(
    	int index	) {
    (new com.bridgeathletic.program()).selectProgramInLibraryByIndex(
        	index)
}

def static "com.bridgeathletic.program.getProgramInfoInLibrary"(
    	int index	) {
    (new com.bridgeathletic.program()).getProgramInfoInLibrary(
        	index)
}

def static "com.bridgeathletic.program.getUnassignedProgramInfoInDetail"() {
    (new com.bridgeathletic.program()).getUnassignedProgramInfoInDetail()
}

def static "com.bridgeathletic.program.getAssignedProgramInfoInDetail"() {
    (new com.bridgeathletic.program()).getAssignedProgramInfoInDetail()
}

def static "com.bridgeathletic.program.updateProgramInfo"(
    	program pr1	
     , 	program pr2	) {
    (new com.bridgeathletic.program()).updateProgramInfo(
        	pr1
         , 	pr2)
}

def static "com.bridgeathletic.program.filterProgramsByCreator"(
    	String filterByCreator	) {
    (new com.bridgeathletic.program()).filterProgramsByCreator(
        	filterByCreator)
}

def static "com.bridgeathletic.program.filterProgramsByTeam"(
    	String filterByTeam	) {
    (new com.bridgeathletic.program()).filterProgramsByTeam(
        	filterByTeam)
}

def static "com.bridgeathletic.program.filterProgramsByType"(
    	java.util.List<String> selectList	) {
    (new com.bridgeathletic.program()).filterProgramsByType(
        	selectList)
}

def static "com.bridgeathletic.block.expandBlockFromName"(
    	String blockName	) {
    (new com.bridgeathletic.block()).expandBlockFromName(
        	blockName)
}

def static "com.bridgeathletic.block.addNewBlock"(
    	int weekIndex	
     , 	int workoutIndex	
     , 	String blockName	) {
    (new com.bridgeathletic.block()).addNewBlock(
        	weekIndex
         , 	workoutIndex
         , 	blockName)
}

def static "com.bridgeathletic.block.addTemplateBlock"(
    	int weekIndex	
     , 	int workoutIndex	
     , 	String blockName	) {
    (new com.bridgeathletic.block()).addTemplateBlock(
        	weekIndex
         , 	workoutIndex
         , 	blockName)
}

def static "com.bridgeathletic.block.addWarmupPersonalizedBlock"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new com.bridgeathletic.block()).addWarmupPersonalizedBlock(
        	weekIndex
         , 	workoutIndex)
}

def static "com.bridgeathletic.block.addRecoveryPersonalizedBlock"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new com.bridgeathletic.block()).addRecoveryPersonalizedBlock(
        	weekIndex
         , 	workoutIndex)
}

def static "com.bridgeathletic.testResultInfo.setTestResultInfo"(
    	Object exerciseName	
     , 	Object testForUnit	
     , 	Object parameterUnit	
     , 	Object parameterValue	
     , 	Object testDate	
     , 	Object athleteName	
     , 	Object testValue	
     , 	Object RME	) {
    (new com.bridgeathletic.testResultInfo()).setTestResultInfo(
        	exerciseName
         , 	testForUnit
         , 	parameterUnit
         , 	parameterValue
         , 	testDate
         , 	athleteName
         , 	testValue
         , 	RME)
}

def static "com.bridgeathletic.testResultInfo.verifyTestResultInfoListContains"(
    	java.util.List<com.bridgeathletic.testResultInfo> actualList	
     , 	java.util.List<com.bridgeathletic.testResultInfo> expectList	) {
    (new com.bridgeathletic.testResultInfo()).verifyTestResultInfoListContains(
        	actualList
         , 	expectList)
}

def static "com.bridgeathletic.workout.printWorkout"(
    	workout wk	) {
    (new com.bridgeathletic.workout()).printWorkout(
        	wk)
}

def static "com.bridgeathletic.workout.scrollWorkoutToPresent"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new com.bridgeathletic.workout()).scrollWorkoutToPresent(
        	weekIndex
         , 	workoutIndex)
}

def static "com.bridgeathletic.workout.expandWeek"(
    	int weekIndex	) {
    (new com.bridgeathletic.workout()).expandWeek(
        	weekIndex)
}

def static "com.bridgeathletic.workout.getWorkoutListInWeek"(
    	int weekIndex	) {
    (new com.bridgeathletic.workout()).getWorkoutListInWeek(
        	weekIndex)
}

def static "com.bridgeathletic.workout.clickCloneWorkoutButtonByIndex"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new com.bridgeathletic.workout()).clickCloneWorkoutButtonByIndex(
        	weekIndex
         , 	workoutIndex)
}

def static "com.bridgeathletic.workout.clickTreeDotButtonWorkoutByIndex"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new com.bridgeathletic.workout()).clickTreeDotButtonWorkoutByIndex(
        	weekIndex
         , 	workoutIndex)
}

def static "com.bridgeathletic.workout.moveWorkout"(
    	int fromWeekIndex	
     , 	int fromWorkoutIndex	
     , 	int toWeekIndex	
     , 	int toWorkoutIndex	) {
    (new com.bridgeathletic.workout()).moveWorkout(
        	fromWeekIndex
         , 	fromWorkoutIndex
         , 	toWeekIndex
         , 	toWorkoutIndex)
}

def static "com.bridgeathletic.convert.convertFromDate"(
    	String currentDate	
     , 	String formatCurrentDate	
     , 	String formatExpectDate	
     , 	int convertDate	
     , 	String dateType	) {
    (new com.bridgeathletic.convert()).convertFromDate(
        	currentDate
         , 	formatCurrentDate
         , 	formatExpectDate
         , 	convertDate
         , 	dateType)
}

def static "com.bridgeathletic.convert.convertFormatDate"(
    	String currentDate	
     , 	String formatCurrentDate	
     , 	String formatExpectDate	) {
    (new com.bridgeathletic.convert()).convertFormatDate(
        	currentDate
         , 	formatCurrentDate
         , 	formatExpectDate)
}

def static "com.bridgeathletic.convert.convertMonthFromStringToInteger"(
    	String month	
     , 	String format	) {
    (new com.bridgeathletic.convert()).convertMonthFromStringToInteger(
        	month
         , 	format)
}

def static "com.bridgeathletic.convert.convertMonthFromIntToString"(
    	int month	
     , 	String format	) {
    (new com.bridgeathletic.convert()).convertMonthFromIntToString(
        	month
         , 	format)
}

def static "com.bridgeathletic.convert.convertFromDateStringToDateList"(
    	String date	
     , 	String format	) {
    (new com.bridgeathletic.convert()).convertFromDateStringToDateList(
        	date
         , 	format)
}

def static "com.bridgeathletic.user.getUserInfo"(
    	int index	) {
    (new com.bridgeathletic.user()).getUserInfo(
        	index)
}

def static "com.bridgeathletic.parameter.getAllParametersInfo"() {
    (new com.bridgeathletic.parameter()).getAllParametersInfo()
}

def static "com.bridgeathletic.parameter.getParameterInfo"(
    	int index	) {
    (new com.bridgeathletic.parameter()).getParameterInfo(
        	index)
}

def static "com.bridgeathletic.parameter.getRandomParameterList"(
    	int paramNumbers	) {
    (new com.bridgeathletic.parameter()).getRandomParameterList(
        	paramNumbers)
}

def static "com.bridgeathletic.random.getRandomNumber"(
    	int maxNumber	) {
    (new com.bridgeathletic.random()).getRandomNumber(
        	maxNumber)
}

def static "com.bridgeathletic.random.getRandomNumberIntoRange"(
    	int minNumber	
     , 	int maxNumber	) {
    (new com.bridgeathletic.random()).getRandomNumberIntoRange(
        	minNumber
         , 	maxNumber)
}

def static "com.bridgeathletic.random.getRandomListNumber"(
    	int minNumber	
     , 	int maxNumber	
     , 	int listSize	) {
    (new com.bridgeathletic.random()).getRandomListNumber(
        	minNumber
         , 	maxNumber
         , 	listSize)
}

def static "com.bridgeathletic.random.getRandomProgram"(
    	java.util.ArrayList<String> allProgram	) {
    (new com.bridgeathletic.random()).getRandomProgram(
        	allProgram)
}

def static "com.bridgeathletic.random.getRandomBlock"(
    	int totalBlockNumbers	) {
    (new com.bridgeathletic.random()).getRandomBlock(
        	totalBlockNumbers)
}

def static "com.bridgeathletic.random.getRandomName"(
    	String startName	) {
    (new com.bridgeathletic.random()).getRandomName(
        	startName)
}

def static "com.bridgeathletic.random.selectRandomTeam"() {
    (new com.bridgeathletic.random()).selectRandomTeam()
}

def static "com.bridgeathletic.random.selectRandomMember"(
    	int totalMembers	) {
    (new com.bridgeathletic.random()).selectRandomMember(
        	totalMembers)
}

def static "com.bridgeathletic.random.selectRandomDay"(
    	int dayNumber	) {
    (new com.bridgeathletic.random()).selectRandomDay(
        	dayNumber)
}

def static "com.bridgeathletic.random.selectRandomWeek"() {
    (new com.bridgeathletic.random()).selectRandomWeek()
}

def static "com.bridgeathletic.random.selectRandomNewBlock"() {
    (new com.bridgeathletic.random()).selectRandomNewBlock()
}

def static "com.bridgeathletic.click.clickPositionObject"(
    	TestObject to	) {
    (new com.bridgeathletic.click()).clickPositionObject(
        	to)
}

def static "com.bridgeathletic.phase.printPhaseInfo"(
    	phase ph	) {
    (new com.bridgeathletic.phase()).printPhaseInfo(
        	ph)
}

def static "com.bridgeathletic.phase.getTotalPhaseNumbersInDetail"() {
    (new com.bridgeathletic.phase()).getTotalPhaseNumbersInDetail()
}

def static "com.bridgeathletic.phase.getPhaseInfoInDetail"(
    	int phaseNumber	) {
    (new com.bridgeathletic.phase()).getPhaseInfoInDetail(
        	phaseNumber)
}

def static "com.bridgeathletic.phase.getPhaseList"(
    	int totalPhaseNumbers	) {
    (new com.bridgeathletic.phase()).getPhaseList(
        	totalPhaseNumbers)
}

def static "com.bridgeathletic.phase.getEditPhaseObjectByName"(
    	String phaseName	) {
    (new com.bridgeathletic.phase()).getEditPhaseObjectByName(
        	phaseName)
}

def static "com.bridgeathletic.phase.getEditPhaseObjectByIndex"(
    	int index	) {
    (new com.bridgeathletic.phase()).getEditPhaseObjectByIndex(
        	index)
}

def static "com.bridgeathletic.phase.getAnalyzePhaseObjectByName"(
    	String phaseName	) {
    (new com.bridgeathletic.phase()).getAnalyzePhaseObjectByName(
        	phaseName)
}

def static "com.bridgeathletic.phase.getAnalyzePhaseObjectByIndex"(
    	int index	) {
    (new com.bridgeathletic.phase()).getAnalyzePhaseObjectByIndex(
        	index)
}

def static "com.bridgeathletic.phase.getTreeDotPhaseObjectByName"(
    	String phaseName	) {
    (new com.bridgeathletic.phase()).getTreeDotPhaseObjectByName(
        	phaseName)
}

def static "com.bridgeathletic.phase.getTreeDotPhaseObjectByIndex"(
    	int index	) {
    (new com.bridgeathletic.phase()).getTreeDotPhaseObjectByIndex(
        	index)
}

def static "com.bridgeathletic.phase.getPhaseIndexByName"(
    	String phaseName	) {
    (new com.bridgeathletic.phase()).getPhaseIndexByName(
        	phaseName)
}

def static "com.bridgeathletic.calendar.selectDateInCalendar"(
    	String date	) {
    (new com.bridgeathletic.calendar()).selectDateInCalendar(
        	date)
}

def static "com.bridgeathletic.total.getTotalBlockNumbers"() {
    (new com.bridgeathletic.total()).getTotalBlockNumbers()
}

def static "com.bridgeathletic.total.getItemSize"(
    	String xpath	) {
    (new com.bridgeathletic.total()).getItemSize(
        	xpath)
}

def static "com.bridgeathletic.total.getAllProgram"() {
    (new com.bridgeathletic.total()).getAllProgram()
}

def static "com.bridgeathletic.total.getTotalAthleteMembers"() {
    (new com.bridgeathletic.total()).getTotalAthleteMembers()
}
