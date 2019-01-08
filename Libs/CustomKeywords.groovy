
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.util.ArrayList

import java.lang.String

import bridgeathletic.program

import java.util.List

import bridgeathletic.workout

import bridgeathletic.phase


def static "bridgeathletic.random.getRandomNumber"(
    	int maxNumber	) {
    (new bridgeathletic.random()).getRandomNumber(
        	maxNumber)
}

def static "bridgeathletic.random.getRandomNumberIntoRange"(
    	int minNumber	
     , 	int maxNumber	) {
    (new bridgeathletic.random()).getRandomNumberIntoRange(
        	minNumber
         , 	maxNumber)
}

def static "bridgeathletic.random.getRandomListNumber"(
    	int minNumber	
     , 	int maxNumber	
     , 	int listSize	) {
    (new bridgeathletic.random()).getRandomListNumber(
        	minNumber
         , 	maxNumber
         , 	listSize)
}

def static "bridgeathletic.random.getRandomProgram"(
    	java.util.ArrayList<String> allProgram	) {
    (new bridgeathletic.random()).getRandomProgram(
        	allProgram)
}

def static "bridgeathletic.random.getRandomBlock"(
    	int totalBlockNumbers	) {
    (new bridgeathletic.random()).getRandomBlock(
        	totalBlockNumbers)
}

def static "bridgeathletic.random.getRandomName"(
    	String startName	) {
    (new bridgeathletic.random()).getRandomName(
        	startName)
}

def static "bridgeathletic.random.selectRandomTeam"() {
    (new bridgeathletic.random()).selectRandomTeam()
}

def static "bridgeathletic.random.selectRandomMember"(
    	int totalMembers	) {
    (new bridgeathletic.random()).selectRandomMember(
        	totalMembers)
}

def static "bridgeathletic.random.selectRandomDay"(
    	int dayNumber	) {
    (new bridgeathletic.random()).selectRandomDay(
        	dayNumber)
}

def static "bridgeathletic.random.selectRandomWeek"() {
    (new bridgeathletic.random()).selectRandomWeek()
}

def static "bridgeathletic.random.selectRandomNewBlock"() {
    (new bridgeathletic.random()).selectRandomNewBlock()
}

def static "bridgeathletic.block.getBlockInfoInCalendar"(
    	int blockIndex	) {
    (new bridgeathletic.block()).getBlockInfoInCalendar(
        	blockIndex)
}

def static "bridgeathletic.block.expandBlockFromName"(
    	String blockName	) {
    (new bridgeathletic.block()).expandBlockFromName(
        	blockName)
}

def static "bridgeathletic.block.addNewBlock"(
    	int weekIndex	
     , 	int workoutIndex	
     , 	String blockName	) {
    (new bridgeathletic.block()).addNewBlock(
        	weekIndex
         , 	workoutIndex
         , 	blockName)
}

def static "bridgeathletic.block.addTemplateBlock"(
    	int weekIndex	
     , 	int workoutIndex	
     , 	String blockName	) {
    (new bridgeathletic.block()).addTemplateBlock(
        	weekIndex
         , 	workoutIndex
         , 	blockName)
}

def static "bridgeathletic.block.addWarmupPersonalizedBlock"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new bridgeathletic.block()).addWarmupPersonalizedBlock(
        	weekIndex
         , 	workoutIndex)
}

def static "bridgeathletic.block.addRecoveryPersonalizedBlock"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new bridgeathletic.block()).addRecoveryPersonalizedBlock(
        	weekIndex
         , 	workoutIndex)
}

def static "bridgeathletic.program.printProgramInfo"(
    	program pr	) {
    (new bridgeathletic.program()).printProgramInfo(
        	pr)
}

def static "bridgeathletic.program.getTotalProgramNumbers"() {
    (new bridgeathletic.program()).getTotalProgramNumbers()
}

def static "bridgeathletic.program.selectProgramInLibraryByIndex"(
    	int index	) {
    (new bridgeathletic.program()).selectProgramInLibraryByIndex(
        	index)
}

def static "bridgeathletic.program.getProgramInfoInLibrary"(
    	int index	) {
    (new bridgeathletic.program()).getProgramInfoInLibrary(
        	index)
}

def static "bridgeathletic.program.getUnassignedProgramInfoInDetail"() {
    (new bridgeathletic.program()).getUnassignedProgramInfoInDetail()
}

def static "bridgeathletic.program.getAssignedProgramInfoInDetail"() {
    (new bridgeathletic.program()).getAssignedProgramInfoInDetail()
}

def static "bridgeathletic.program.updateProgramInfo"(
    	program pr1	
     , 	program pr2	) {
    (new bridgeathletic.program()).updateProgramInfo(
        	pr1
         , 	pr2)
}

def static "bridgeathletic.program.filterProgramsByCreator"(
    	String filterByCreator	) {
    (new bridgeathletic.program()).filterProgramsByCreator(
        	filterByCreator)
}

def static "bridgeathletic.program.filterProgramsByTeam"(
    	String filterByTeam	) {
    (new bridgeathletic.program()).filterProgramsByTeam(
        	filterByTeam)
}

def static "bridgeathletic.program.filterProgramsByType"(
    	java.util.List<String> selectList	) {
    (new bridgeathletic.program()).filterProgramsByType(
        	selectList)
}

def static "bridgeathletic.set.getAllSetsInfo"() {
    (new bridgeathletic.set()).getAllSetsInfo()
}

def static "bridgeathletic.set.getSetInfoInCalendar"(
    	int blockIndex	
     , 	int exerciseIndex	
     , 	int setIndex	) {
    (new bridgeathletic.set()).getSetInfoInCalendar(
        	blockIndex
         , 	exerciseIndex
         , 	setIndex)
}

def static "bridgeathletic.set.getSetInfo"(
    	int index	) {
    (new bridgeathletic.set()).getSetInfo(
        	index)
}

def static "bridgeathletic.set.setValueForSet"(
    	int setIndex	
     , 	java.util.List<java.lang.Integer> setValue	) {
    (new bridgeathletic.set()).setValueForSet(
        	setIndex
         , 	setValue)
}

def static "bridgeathletic.parameter.getAllParametersInfo"() {
    (new bridgeathletic.parameter()).getAllParametersInfo()
}

def static "bridgeathletic.parameter.getParameterInfo"(
    	int index	) {
    (new bridgeathletic.parameter()).getParameterInfo(
        	index)
}

def static "bridgeathletic.parameter.getRandomParameterList"(
    	int paramNumbers	) {
    (new bridgeathletic.parameter()).getRandomParameterList(
        	paramNumbers)
}

def static "bridgeathletic.workout.printWorkout"(
    	workout wk	) {
    (new bridgeathletic.workout()).printWorkout(
        	wk)
}

def static "bridgeathletic.workout.getSelectedWorkoutInfoInAthleteCalendar"() {
    (new bridgeathletic.workout()).getSelectedWorkoutInfoInAthleteCalendar()
}

def static "bridgeathletic.workout.scrollWorkoutToPresent"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new bridgeathletic.workout()).scrollWorkoutToPresent(
        	weekIndex
         , 	workoutIndex)
}

def static "bridgeathletic.workout.expandWeek"(
    	int weekIndex	) {
    (new bridgeathletic.workout()).expandWeek(
        	weekIndex)
}

def static "bridgeathletic.workout.getWorkoutListInWeek"(
    	int weekIndex	) {
    (new bridgeathletic.workout()).getWorkoutListInWeek(
        	weekIndex)
}

def static "bridgeathletic.workout.clickCloneWorkoutButtonByIndex"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new bridgeathletic.workout()).clickCloneWorkoutButtonByIndex(
        	weekIndex
         , 	workoutIndex)
}

def static "bridgeathletic.workout.clickTreeDotButtonWorkoutByIndex"(
    	int weekIndex	
     , 	int workoutIndex	) {
    (new bridgeathletic.workout()).clickTreeDotButtonWorkoutByIndex(
        	weekIndex
         , 	workoutIndex)
}

def static "bridgeathletic.workout.moveWorkout"(
    	int fromWeekIndex	
     , 	int fromWorkoutIndex	
     , 	int toWeekIndex	
     , 	int toWorkoutIndex	) {
    (new bridgeathletic.workout()).moveWorkout(
        	fromWeekIndex
         , 	fromWorkoutIndex
         , 	toWeekIndex
         , 	toWorkoutIndex)
}

def static "bridgeathletic.convert.convertFromDate"(
    	String currentDate	
     , 	String formatCurrentDate	
     , 	String formatExpectDate	
     , 	int convertDate	
     , 	String dateType	) {
    (new bridgeathletic.convert()).convertFromDate(
        	currentDate
         , 	formatCurrentDate
         , 	formatExpectDate
         , 	convertDate
         , 	dateType)
}

def static "bridgeathletic.convert.convertFormatDate"(
    	String currentDate	
     , 	String formatCurrentDate	
     , 	String formatExpectDate	) {
    (new bridgeathletic.convert()).convertFormatDate(
        	currentDate
         , 	formatCurrentDate
         , 	formatExpectDate)
}

def static "bridgeathletic.convert.convertMonthFromStringToInteger"(
    	String month	
     , 	String format	) {
    (new bridgeathletic.convert()).convertMonthFromStringToInteger(
        	month
         , 	format)
}

def static "bridgeathletic.convert.convertMonthFromIntToString"(
    	int month	
     , 	String format	) {
    (new bridgeathletic.convert()).convertMonthFromIntToString(
        	month
         , 	format)
}

def static "bridgeathletic.convert.convertFromDateStringToDateList"(
    	String date	
     , 	String format	) {
    (new bridgeathletic.convert()).convertFromDateStringToDateList(
        	date
         , 	format)
}

def static "bridgeathletic.calendar.selectDateInCalendar"(
    	String date	) {
    (new bridgeathletic.calendar()).selectDateInCalendar(
        	date)
}

def static "bridgeathletic.info.getBlockInfo"() {
    (new bridgeathletic.info()).getBlockInfo()
}

def static "bridgeathletic.info.getTemplateBlockInfo"() {
    (new bridgeathletic.info()).getTemplateBlockInfo()
}

def static "bridgeathletic.info.getBlockInfoInCal"(
    	int blockNumber	) {
    (new bridgeathletic.info()).getBlockInfoInCal(
        	blockNumber)
}

def static "bridgeathletic.info.getWorkoutInfo"(
    	int workoutNumber	) {
    (new bridgeathletic.info()).getWorkoutInfo(
        	workoutNumber)
}

def static "bridgeathletic.info.getTeamInfo"(
    	int teamNumber	) {
    (new bridgeathletic.info()).getTeamInfo(
        	teamNumber)
}

def static "bridgeathletic.info.getAttendanceInfo"(
    	int athleteNumber	) {
    (new bridgeathletic.info()).getAttendanceInfo(
        	athleteNumber)
}

def static "bridgeathletic.info.getEventInfo"(
    	int eventNumber	) {
    (new bridgeathletic.info()).getEventInfo(
        	eventNumber)
}

def static "bridgeathletic.info.getTestResultInfoInTeamLeaderModule"() {
    (new bridgeathletic.info()).getTestResultInfoInTeamLeaderModule()
}

def static "bridgeathletic.info.getTestResultInfoInTestHistoryTable"() {
    (new bridgeathletic.info()).getTestResultInfoInTestHistoryTable()
}

def static "bridgeathletic.info.getTestResultInfoInHighCharts"() {
    (new bridgeathletic.info()).getTestResultInfoInHighCharts()
}

def static "bridgeathletic.total.getTotalBlockNumbers"() {
    (new bridgeathletic.total()).getTotalBlockNumbers()
}

def static "bridgeathletic.total.getItemSize"(
    	String xpath	) {
    (new bridgeathletic.total()).getItemSize(
        	xpath)
}

def static "bridgeathletic.total.getAllProgram"() {
    (new bridgeathletic.total()).getAllProgram()
}

def static "bridgeathletic.total.getTotalAthleteMembers"() {
    (new bridgeathletic.total()).getTotalAthleteMembers()
}

def static "bridgeathletic.testResultInfo.setTestResultInfo"(
    	Object exerciseName	
     , 	Object testForUnit	
     , 	Object parameterUnit	
     , 	Object parameterValue	
     , 	Object testDate	
     , 	Object athleteName	
     , 	Object testValue	
     , 	Object RME	) {
    (new bridgeathletic.testResultInfo()).setTestResultInfo(
        	exerciseName
         , 	testForUnit
         , 	parameterUnit
         , 	parameterValue
         , 	testDate
         , 	athleteName
         , 	testValue
         , 	RME)
}

def static "bridgeathletic.testResultInfo.verifyTestResultInfoListContains"(
    	java.util.List<bridgeathletic.testResultInfo> actualList	
     , 	java.util.List<bridgeathletic.testResultInfo> expectList	) {
    (new bridgeathletic.testResultInfo()).verifyTestResultInfoListContains(
        	actualList
         , 	expectList)
}

def static "bridgeathletic.exercise.getExerciseInfo"() {
    (new bridgeathletic.exercise()).getExerciseInfo()
}

def static "bridgeathletic.exercise.getExerciseInfoInCalendar"(
    	int blockIndex	
     , 	int exerciseIndex	) {
    (new bridgeathletic.exercise()).getExerciseInfoInCalendar(
        	blockIndex
         , 	exerciseIndex)
}

def static "bridgeathletic.exercise.addNewExerciseFromBlockName"(
    	String blockName	
     , 	String exerciseName	) {
    (new bridgeathletic.exercise()).addNewExerciseFromBlockName(
        	blockName
         , 	exerciseName)
}

def static "bridgeathletic.exercise.selectExerciseFromName"(
    	String blockName	
     , 	String exerciseName	) {
    (new bridgeathletic.exercise()).selectExerciseFromName(
        	blockName
         , 	exerciseName)
}

def static "bridgeathletic.exercise.editParameterNumbers"(
    	java.util.List<String> paramName	) {
    (new bridgeathletic.exercise()).editParameterNumbers(
        	paramName)
}

def static "bridgeathletic.exercise.editSetNumbers"(
    	int setNumbers	) {
    (new bridgeathletic.exercise()).editSetNumbers(
        	setNumbers)
}

def static "bridgeathletic.phase.printPhaseInfo"(
    	phase ph	) {
    (new bridgeathletic.phase()).printPhaseInfo(
        	ph)
}

def static "bridgeathletic.phase.getTotalPhaseNumbersInDetail"() {
    (new bridgeathletic.phase()).getTotalPhaseNumbersInDetail()
}

def static "bridgeathletic.phase.getPhaseInfoInDetail"(
    	int phaseNumber	) {
    (new bridgeathletic.phase()).getPhaseInfoInDetail(
        	phaseNumber)
}

def static "bridgeathletic.phase.getPhaseList"(
    	int totalPhaseNumbers	) {
    (new bridgeathletic.phase()).getPhaseList(
        	totalPhaseNumbers)
}

def static "bridgeathletic.phase.getEditPhaseObjectByName"(
    	String phaseName	) {
    (new bridgeathletic.phase()).getEditPhaseObjectByName(
        	phaseName)
}

def static "bridgeathletic.phase.getEditPhaseObjectByIndex"(
    	int index	) {
    (new bridgeathletic.phase()).getEditPhaseObjectByIndex(
        	index)
}

def static "bridgeathletic.phase.getAnalyzePhaseObjectByName"(
    	String phaseName	) {
    (new bridgeathletic.phase()).getAnalyzePhaseObjectByName(
        	phaseName)
}

def static "bridgeathletic.phase.getAnalyzePhaseObjectByIndex"(
    	int index	) {
    (new bridgeathletic.phase()).getAnalyzePhaseObjectByIndex(
        	index)
}

def static "bridgeathletic.phase.getTreeDotPhaseObjectByName"(
    	String phaseName	) {
    (new bridgeathletic.phase()).getTreeDotPhaseObjectByName(
        	phaseName)
}

def static "bridgeathletic.phase.getTreeDotPhaseObjectByIndex"(
    	int index	) {
    (new bridgeathletic.phase()).getTreeDotPhaseObjectByIndex(
        	index)
}

def static "bridgeathletic.phase.getPhaseIndexByName"(
    	String phaseName	) {
    (new bridgeathletic.phase()).getPhaseIndexByName(
        	phaseName)
}

def static "bridgeathletic.click.clickDateObjectFromXpath"(
    	String xp	) {
    (new bridgeathletic.click()).clickDateObjectFromXpath(
        	xp)
}

def static "bridgeathletic.user.getUserInfo"(
    	int index	) {
    (new bridgeathletic.user()).getUserInfo(
        	index)
}
