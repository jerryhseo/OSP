create table EDSIM_ScienceAppExecute (
	executeDate VARCHAR(75) not null,
	scienceAppId LONG not null,
	userCnt LONG,
	avgExeTime LONG,
	exeCnt LONG,
	primary key (executeDate, scienceAppId)
);

create table EDSIM_Simulation (
	simulationUuid VARCHAR(75) not null,
	groupId LONG not null,
	userId LONG,
	simulationTitle STRING null,
	scienceAppId VARCHAR(75) null,
	scienceAppName STRING null,
	scienceAppVersion VARCHAR(75) null,
	simulationCreateDt DATE null,
	cluster VARCHAR(75) null,
	classId LONG,
	customId LONG,
	testYn BOOLEAN,
	primary key (simulationUuid, groupId)
);

create table EDSIM_SimulationExeStsMigration (
	scienceAppId LONG not null,
	groupId LONG not null,
	submitDate VARCHAR(75) not null,
	userCnt LONG,
	jobCnt LONG,
	runtime LONG,
	primary key (scienceAppId, groupId, submitDate)
);

create table EDSIM_SimulationJob (
	jobSeqNo LONG not null,
	simulationUuid VARCHAR(75) not null,
	groupId LONG not null,
	jobUuid VARCHAR(75) null,
	jobStatus LONG,
	jobStartDt DATE null,
	jobEndDt DATE null,
	jobTitle STRING null,
	jobExecPath STRING null,
	jobPhase LONG,
	jobSubmitDt DATE null,
	jobUniversityField LONG,
	jobInputDeckYn BOOLEAN,
	jobInputDeckName VARCHAR(75) null,
	jobSubmit BOOLEAN,
	primary key (jobSeqNo, simulationUuid, groupId)
);

create table EDSIM_SimulationJobData (
	jobUuid VARCHAR(75) not null primary key,
	jobData STRING null
);

create table EDSIM_SimulationJobStatus (
	statusSeq LONG not null,
	groupId LONG not null,
	simulationUuid VARCHAR(75) not null,
	jobUuid VARCHAR(75) not null,
	jobStatus LONG,
	jobStartDt DATE null,
	jobEndDt DATE null,
	writeDt DATE null,
	primary key (statusSeq, groupId, simulationUuid, jobUuid)
);

create table EDSIM_SimulationShare (
	shareSeqno LONG not null,
	jobSeqNo LONG not null,
	jobUuid VARCHAR(75) not null,
	simulationUuid VARCHAR(75) not null,
	classId LONG,
	customId LONG,
	simulationShareDt DATE null,
	primary key (shareSeqno, jobSeqNo, jobUuid, simulationUuid)
);

create table EDSIM_UniversityExecute (
	executeDate VARCHAR(75) not null,
	universityField LONG not null,
	userCnt LONG,
	avgExeTime LONG,
	exeCnt LONG,
	cpuTime LONG,
	primary key (executeDate, universityField)
);

create table EDSIM_VirtualLabClassStatistics (
	virtualLabId LONG not null,
	classId VARCHAR(75) not null,
	virtualLabTitle STRING null,
	classTitle STRING null,
	virtualLabPersonName VARCHAR(75) null,
	registerStudentCnt LONG,
	virtualLabUsersId VARCHAR(75) null,
	scienceAppId VARCHAR(75) null,
	executeUserCnt LONG,
	executeCnt LONG,
	cputime VARCHAR(75) null,
	groupId LONG,
	university LONG,
	classCreateDt DATE null,
	virtualLabUseYn VARCHAR(75) null,
	classUseYn VARCHAR(75) null,
	lastModifiedDt DATE null,
	primary key (virtualLabId, classId)
);