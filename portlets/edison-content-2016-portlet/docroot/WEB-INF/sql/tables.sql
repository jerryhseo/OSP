create table EDMED_AdvancedContent (
	contentSeq LONG not null,
	groupId LONG not null,
	title VARCHAR(75) null,
	resume VARCHAR(75) null,
	contentFilePath VARCHAR(75) null,
	contentFileNm VARCHAR(75) null,
	contentStartFileNm VARCHAR(75) null,
	serviceLanguage VARCHAR(75) null,
	viewCnt LONG,
	insertId LONG,
	insertDate DATE null,
	updateId LONG,
	updateDate DATE null,
	primary key (contentSeq, groupId)
);

create table EDMED_Content (
	uuid_ VARCHAR(75) null,
	contentSeq LONG not null primary key,
	contentDiv LONG,
	title STRING null,
	resume STRING null,
	contentFileNm STRING null,
	advancedStartFileNm VARCHAR(75) null,
	serviceLanguage VARCHAR(75) null,
	projectYn VARCHAR(75) null,
	projectId LONG,
	viewCnt LONG,
	insertId LONG,
	insertDate DATE null,
	updateId LONG,
	updateDate DATE null,
	version VARCHAR(75) null,
	openYn VARCHAR(75) null,
	coverImageFileEntryId LONG
);

create table EDMED_GeneralContent (
	contentSeq LONG not null,
	groupId LONG not null,
	contentDiv LONG,
	title STRING null,
	downloadCnt LONG,
	serviceLanguage VARCHAR(75) null,
	projectYn VARCHAR(75) null,
	projectId LONG,
	insertId LONG,
	insertDate DATE null,
	updateId LONG,
	updateDate DATE null,
	primary key (contentSeq, groupId)
);