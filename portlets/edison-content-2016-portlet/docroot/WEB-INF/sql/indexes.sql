create index IX_315F4C3F on EDMED_AdvancedContent (groupId);
create index IX_87E8CCBE on EDMED_AdvancedContent (groupId, serviceLanguage);

create index IX_950D1367 on EDMED_Content (contentSeq);
create index IX_6E414C47 on EDMED_Content (uuid_);

create index IX_C6A86403 on EDMED_GeneralContent (contentSeq);
create index IX_D2701361 on EDMED_GeneralContent (groupId);
create index IX_716C1FAD on EDMED_GeneralContent (groupId, contentDiv);