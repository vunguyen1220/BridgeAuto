<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>div_day ng-scope</name>
   <tag></tag>
   <elementGuidId>c7089ccd-74bd-40fb-a4e2-dee11fb49cc3</elementGuidId>
   <useRalativeImagePath>false</useRalativeImagePath>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>div</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>//div[@class=&quot;days clearfix unselector&quot;]/div[@class=&quot;day ng-scope&quot;]</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>ng-style</name>
      <type>Main</type>
      <value>{'width': globalParams.eachCellWidth+'px'}</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>ng-class</name>
      <type>Main</type>
      <value>{selected: workoutId == selectedWorkoutId &amp;&amp; !selectedBlockId &amp;&amp; !selectedExerciseId, 'hidden-day': $index&lt;phaseCurrentPage.weekCurrentPage[weekId]-1 || $index>phaseCurrentPage.weekCurrentPage[weekId]+1, 'disabled-day': dayIsDisabled}</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>ng-init</name>
      <type>Main</type>
      <value>dayNumber = $index+1; dayIsDisabled = data.linked.workoutHistories[workoutId].dayIsDisabled</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>ng-repeat</name>
      <type>Main</type>
      <value>workoutId in data.linked.phaseWeeks[weekId].links.workoutHistories</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>day ng-scope</value>
   </webElementProperties>
</WebElementEntity>
