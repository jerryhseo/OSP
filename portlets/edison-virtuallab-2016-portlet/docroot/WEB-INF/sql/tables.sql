create table EDVIR_ClassNote (
	classNoteSeq LONG not null primary key,
	classId LONG,
	contentSeq LONG,
	isContent VARCHAR(75) null,
	fileEntryId LONG,
	description STRING null,
	insertDate DATE null,
	updateDate DATE null,
	insertId LONG,
	updateId LONG
);

create table EDVIR_Professor (
	professorSeq LONG not null primary key,
	userId LONG,
	record STRING null,
	paper STRING null,
	homepage VARCHAR(75) null,
	introduce STRING null,
	phone VARCHAR(75) null
);

create table EDVIR_Survey (
	surveySeqNo LONG not null primary key,
	surveyUseYn VARCHAR(75) null,
	surveyTitle STRING null,
	surveyStartDate VARCHAR(75) null,
	surveyEndDate VARCHAR(75) null,
	surveyStatus VARCHAR(75) null,
	surveyCreateDate DATE null
);

create table EDVIR_SurveyAnswer (
	SurveyAnswerId LONG not null primary key,
	userId LONG,
	virtualLabId LONG,
	classId LONG,
	subjectivityAnswer STRING null,
	objecttivityAnswer VARCHAR(75) null,
	answerDate DATE null
);

create table EDVIR_SurveyQuestion (
	questionSeqNo LONG not null primary key,
	questionDivCd VARCHAR(75) null,
	questionTitle STRING null,
	question1 STRING null,
	question2 STRING null,
	question3 STRING null,
	question4 STRING null,
	question5 STRING null,
	question6 STRING null,
	question7 STRING null,
	question8 STRING null,
	question9 STRING null,
	question10 STRING null
);

create table EDVIR_SurveyQuestions_SurveyAnswers (
	SurveyAnswerId LONG not null,
	questionSeqNo LONG not null,
	primary key (SurveyAnswerId, questionSeqNo)
);

create table EDVIR_Surveys_SurveyQuestions (
	surveySeqNo LONG not null,
	questionSeqNo LONG not null,
	primary key (surveySeqNo, questionSeqNo)
);

create table EDVIR_VirtualLab (
	virtualLabId LONG not null primary key,
	groupId LONG,
	userId LONG,
	virtualLabRequestDt DATE null,
	virtualLabConfirmDt DATE null,
	virtualLabConfirmDescription VARCHAR(75) null,
	virtualLabStatus VARCHAR(75) null,
	virtualLabTitle STRING null,
	virtualLabDescription STRING null,
	virtualLabUseYn VARCHAR(75) null,
	virtualLabUniversityField VARCHAR(75) null,
	iconId LONG,
	professorSeq LONG
);

create table EDVIR_VirtualLabClass (
	classId LONG not null primary key,
	classTitle STRING null,
	classStartDt VARCHAR(75) null,
	classEndDt VARCHAR(75) null,
	classUseYn VARCHAR(75) null,
	classDescription STRING null,
	classPersonnel INTEGER,
	classCreateDt DATE null,
	classUpdateDt DATE null,
	virtualClassCd VARCHAR(75) null,
	classCurriculumUrl VARCHAR(75) null,
	classExternalPersonnel INTEGER
);

create table EDVIR_VirtualLabClassScienceApp (
	scienceAppSeqNo LONG not null primary key,
	scienceAppId LONG,
	createDate DATE null
);

create table EDVIR_VirtualLabClassStsMigration (
	groupId LONG not null,
	virtualLabId LONG not null,
	classId LONG not null,
	universityField VARCHAR(75) null,
	virtualLabTitle STRING null,
	virtualLabPersonName STRING null,
	classTitle STRING null,
	virtualClassCd VARCHAR(75) null,
	classCreateDt DATE null,
	scienceAppId VARCHAR(75) null,
	scienceAppName VARCHAR(75) null,
	registerStudentCnt LONG,
	executeCount LONG,
	executeStudentcount LONG,
	avgerageRuntime LONG,
	cputime LONG,
	primary key (groupId, virtualLabId, classId)
);

create table EDVIR_VirtualLabClasses_VirtualLabClassScienceApps (
	classId LONG not null,
	scienceAppSeqNo LONG not null,
	primary key (classId, scienceAppSeqNo)
);

create table EDVIR_VirtualLabClasses_VirtualLabUsers (
	classId LONG not null,
	virtualLabUserId LONG not null,
	primary key (classId, virtualLabUserId)
);

create table EDVIR_VirtualLabScienceAppLink (
	scienceAppSeqNo LONG not null primary key,
	scienceAppId LONG,
	virtualLabId LONG
);

create table EDVIR_VirtualLabScienceAppLinkClass (
	scienceAppClassSeqNo LONG not null primary key,
	classId LONG,
	scienceAppSeqNo LONG
);

create table EDVIR_VirtualLabUser (
	virtualLabUserId LONG not null primary key,
	userId LONG,
	userStudentNumber VARCHAR(75) null,
	authRole VARCHAR(75) null,
	userUseYn VARCHAR(75) null,
	requestSort VARCHAR(75) null,
	processNote VARCHAR(75) null,
	processDate DATE null,
	createDt DATE null,
	updateDt DATE null
);

create table EDVIR_VirtualLabUserTemp (
	seqNo LONG not null,
	virtualLabId LONG not null,
	classId LONG not null,
	userStudentNumber VARCHAR(75) null,
	userName VARCHAR(75) null,
	status BOOLEAN,
	primary key (seqNo, virtualLabId, classId)
);

create table EDVIR_VirtualLabs_Surveys (
	surveySeqNo LONG not null,
	virtualLabId LONG not null,
	primary key (surveySeqNo, virtualLabId)
);

create table EDVIR_VirtualLabs_VirtualLabClasses (
	virtualLabId LONG not null,
	classId LONG not null,
	primary key (virtualLabId, classId)
);