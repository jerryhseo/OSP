create index IX_C1385D6C on EDVIR_ClassNote (classId, contentSeq);
create index IX_D7CA664D on EDVIR_ClassNote (classId, isContent);

create index IX_D7276DD0 on EDVIR_Professor (userId);

create index IX_AC7EB9DD on EDVIR_SurveyQuestions_SurveyAnswers (SurveyAnswerId);
create index IX_C002B394 on EDVIR_SurveyQuestions_SurveyAnswers (questionSeqNo);

create index IX_F6A972F6 on EDVIR_Surveys_SurveyQuestions (questionSeqNo);
create index IX_2B93F782 on EDVIR_Surveys_SurveyQuestions (surveySeqNo);

create index IX_D04F97E3 on EDVIR_VirtualLabClasses_VirtualLabClassScienceApps (classId);
create index IX_11FC2093 on EDVIR_VirtualLabClasses_VirtualLabClassScienceApps (scienceAppSeqNo);

create index IX_8E4576B1 on EDVIR_VirtualLabClasses_VirtualLabUsers (classId);
create index IX_1B8A068E on EDVIR_VirtualLabClasses_VirtualLabUsers (virtualLabUserId);

create index IX_CA084D8F on EDVIR_VirtualLabScienceAppLink (virtualLabId);

create index IX_9AD1101D on EDVIR_VirtualLabScienceAppLinkClass (classId);

create index IX_C2CD7F2C on EDVIR_VirtualLabUserTemp (virtualLabId, classId);

create index IX_51F94624 on EDVIR_VirtualLabs_Surveys (surveySeqNo);
create index IX_79454B83 on EDVIR_VirtualLabs_Surveys (virtualLabId);

create index IX_6D813E46 on EDVIR_VirtualLabs_VirtualLabClasses (classId);
create index IX_BE86ECAE on EDVIR_VirtualLabs_VirtualLabClasses (virtualLabId);