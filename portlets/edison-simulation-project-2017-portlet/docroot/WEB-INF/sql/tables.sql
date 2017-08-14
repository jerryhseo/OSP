create table EDSIMPRO_RequestMember (
	requestSeq LONG not null,
	simulationProjectId LONG not null,
	userId LONG,
	requestDate DATE null,
	processDate DATE null,
	requestState VARCHAR(75) null,
	primary key (requestSeq, simulationProjectId)
);

create table EDSIMPRO_SimProScienceAppLink (
	simProScienceAppLinkId LONG not null,
	simulationProjectId LONG not null,
	scienceAppId LONG,
	primary key (simProScienceAppLinkId, simulationProjectId)
);

create table EDSIMPRO_SimulationProject (
	simulationProjectId LONG not null primary key,
	title STRING null,
	serviceLanguage VARCHAR(75) null,
	projectOpenYn BOOLEAN,
	explain_ STRING null,
	iconId LONG,
	imageFolderId LONG,
	ownerId LONG,
	insertId LONG,
	insertDate DATE null,
	updateId LONG,
	updateDate DATE null,
	templetId LONG
);