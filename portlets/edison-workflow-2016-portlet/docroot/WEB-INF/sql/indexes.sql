create index IX_50F953FD on EDWF_Workflow (isPublic);
create index IX_FF0D8532 on EDWF_Workflow (title);

create index IX_D5FCD81D on EDWF_WorkflowInstance (title);
create index IX_492DA5C5 on EDWF_WorkflowInstance (userId);

create index IX_870BE2EB on EDWF_WorkflowSimulation (title);
create index IX_FA992025 on EDWF_WorkflowSimulation (title, userId);
create index IX_B9FFF4B7 on EDWF_WorkflowSimulation (userId);
create index IX_173C4F2B on EDWF_WorkflowSimulation (workflowId);
create index IX_F90C68B7 on EDWF_WorkflowSimulation (workflowId, title, userId);
create index IX_4AB94C65 on EDWF_WorkflowSimulation (workflowId, userId);

create index IX_9CCD7A00 on EDWF_WorkflowSimulationJob (simulationId, title, userId);
create index IX_B65F2B3C on EDWF_WorkflowSimulationJob (simulationId, userId);
create index IX_75855E7C on EDWF_WorkflowSimulationJob (title);
create index IX_9D2BF6AC on EDWF_WorkflowSimulationJob (title, simulationId, userId);
create index IX_3C2A7AB6 on EDWF_WorkflowSimulationJob (title, userId);
create index IX_9AB5EB46 on EDWF_WorkflowSimulationJob (userId);

create index IX_51D5D60B on EDWF_WorkflowSimulation_WorkflowSimulationJob (simulationId);
create index IX_8E2E09AC on EDWF_WorkflowSimulation_WorkflowSimulationJob (simulationJobId);

create index IX_4701B357 on EDWF_Workflow_WorkflowInstance (workflowId);
create index IX_2A27FF8C on EDWF_Workflow_WorkflowInstance (workflowInstanceId);