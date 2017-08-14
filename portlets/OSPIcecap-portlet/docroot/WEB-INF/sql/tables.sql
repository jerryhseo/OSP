create table icecap_DataCollection (
	uuid_ VARCHAR(75) null,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	title STRING null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	description STRING null,
	targetLanguage VARCHAR(75) null,
	status INTEGER,
	accessMethod VARCHAR(75) null,
	collectionId LONG not null primary key,
	typeId LONG,
	folderId LONG
);

create table icecap_DataCollectionLayout (
	collectionId LONG not null primary key,
	layoutStr VARCHAR(75) null
);

create table icecap_DataEntry (
	entryId LONG not null primary key,
	collectionId LONG,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	comments STRING null,
	path_ VARCHAR(75) null,
	simulationSubjectId LONG,
	productionTime LONG,
	viewCount LONG,
	downloadCount LONG,
	lastAccessedDate DATE null,
	fileEntryId LONG
);

create table icecap_DataEntryProvenance (
	entryId LONG not null primary key,
	inputData VARCHAR(75) null,
	PROVStructure VARCHAR(75) null
);

create table icecap_DataType (
	uuid_ VARCHAR(75) null,
	typeId LONG not null primary key,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	samplePath VARCHAR(75) null,
	status INTEGER,
	description STRING null,
	groupId LONG,
	isFavorite BOOLEAN,
	ownerId LONG
);

create table icecap_DataTypeAnalyzer (
	typeId LONG not null,
	analyzerId LONG not null,
	isDefault BOOLEAN,
	primary key (typeId, analyzerId)
);

create table icecap_DataTypeEditor (
	typeId LONG not null,
	editorId LONG not null,
	isDefault BOOLEAN,
	primary key (typeId, editorId)
);

create table icecap_DataTypeStructure (
	typeId LONG not null primary key,
	structure TEXT null
);