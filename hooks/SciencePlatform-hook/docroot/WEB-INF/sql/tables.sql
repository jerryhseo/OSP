create table OSP_FileManagement (
	userId LONG not null primary key,
	assigned INTEGER,
	used INTEGER,
	lastModified DATE null,
	status INTEGER
);

create table OSP_SystemProperties (
	propertyName VARCHAR(75) not null primary key,
	propertyValue VARCHAR(75) null,
	active_ BOOLEAN
);