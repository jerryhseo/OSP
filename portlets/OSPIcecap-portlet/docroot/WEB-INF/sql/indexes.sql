create index IX_27141C83 on icecap_DataCollection (groupId, name);
create index IX_9377EAAA on icecap_DataCollection (groupId, status);
create index IX_B2697AE5 on icecap_DataCollection (name);
create index IX_20626CC3 on icecap_DataCollection (name, version);
create index IX_9EEF388C on icecap_DataCollection (status);
create index IX_A18E9EE3 on icecap_DataCollection (targetLanguage);
create index IX_E638BCA2 on icecap_DataCollection (title);
create index IX_C745E3DF on icecap_DataCollection (title, targetLanguage);
create index IX_2800A5CF on icecap_DataCollection (typeId);
create index IX_FD5D8F7A on icecap_DataCollection (typeId, name, version);
create index IX_406E51E0 on icecap_DataCollection (userId);
create index IX_2EFA634E on icecap_DataCollection (uuid_);
create index IX_7954241A on icecap_DataCollection (uuid_, companyId);
create unique index IX_81EAE91C on icecap_DataCollection (uuid_, groupId);

create index IX_E5072227 on icecap_DataCollectionLayout (layoutStr);

create index IX_78F7D343 on icecap_DataEntry (collectionId);
create index IX_CCD2735A on icecap_DataEntry (simulationSubjectId);

create index IX_E22B21A3 on icecap_DataEntryProvenance (PROVStructure);
create index IX_B90A812D on icecap_DataEntryProvenance (inputData);

create index IX_A0EB0C28 on icecap_DataType (groupId);
create index IX_1DBA9202 on icecap_DataType (groupId, isFavorite);
create index IX_78059DE7 on icecap_DataType (groupId, name);
create index IX_D03357B6 on icecap_DataType (groupId, ownerId);
create index IX_6E0EA30E on icecap_DataType (groupId, status);
create index IX_D2521901 on icecap_DataType (name);
create index IX_7153EE27 on icecap_DataType (name, version);
create index IX_6728BFA8 on icecap_DataType (status);
create index IX_F03A2CEB on icecap_DataType (typeId);
create index IX_8A7D8FC on icecap_DataType (userId);
create index IX_C2588B2 on icecap_DataType (uuid_);
create index IX_F1947836 on icecap_DataType (uuid_, companyId);
create unique index IX_4F299438 on icecap_DataType (uuid_, groupId);

create index IX_82B4549D on icecap_DataTypeAnalyzer (analyzerId);
create index IX_C8E66231 on icecap_DataTypeAnalyzer (typeId);

create index IX_43E1F1AB on icecap_DataTypeEditor (editorId);
create index IX_76564138 on icecap_DataTypeEditor (typeId);

create index IX_244F8366 on icecap_DataTypeStructure (structure);
create index IX_F6D58406 on icecap_DataTypeStructure (typeId);