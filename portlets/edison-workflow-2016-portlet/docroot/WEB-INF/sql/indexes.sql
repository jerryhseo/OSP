create index IX_50F953FD on EDWF_Workflow (isPublic);
create index IX_FF0D8532 on EDWF_Workflow (title);

create index IX_D5FCD81D on EDWF_WorkflowInstance (title);
create index IX_492DA5C5 on EDWF_WorkflowInstance (userId);

create index IX_870BE2EB on EDWF_WorkflowSimulation (title);
create index IX_B9FFF4B7 on EDWF_WorkflowSimulation (userId);

create index IX_75855E7C on EDWF_WorkflowSimulationJob (title);
create index IX_9AB5EB46 on EDWF_WorkflowSimulationJob (userId);

create index IX_51D5D60B on EDWF_WorkflowSimulation_WorkflowSimulationJob (simulationId);
create index IX_8E2E09AC on EDWF_WorkflowSimulation_WorkflowSimulationJob (simulationJobId);

create index IX_4701B357 on EDWF_Workflow_WorkflowInstance (workflowId);
create index IX_2A27FF8C on EDWF_Workflow_WorkflowInstance (workflowInstanceId);