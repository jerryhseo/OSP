create table OSP_OSPFile (
	propertyName VARCHAR(75) not null primary key,
	propertyValue VARCHAR(75) null
);

create table OSP_SystemProperties (
	propertyName VARCHAR(75) not null primary key,
	propertyValue VARCHAR(75) null,
	active_ BOOLEAN
);