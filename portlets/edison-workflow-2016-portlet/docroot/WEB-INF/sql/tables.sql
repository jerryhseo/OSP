create table EDWF_Workflow (
	workflowId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	description STRING null,
	parentWorkflowId LONG,
	screenLogic TEXT null
);

create table EDWF_WorkflowInstance (
	workflowInstanceId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	status VARCHAR(75) null,
	statusResponse TEXT null,
	startTime DATE null,
	endTime DATE null,
	workflowId LONG,
	workflowUUID VARCHAR(75) null,
	reuseWorkflowUUID VARCHAR(75) null,
	screenLogic TEXT null
);

create table EDWF_WorkflowSimulation (
	simulationId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classId LONG,
	customId LONG,
	title VARCHAR(75) null,
	testYn BOOLEAN,
	workflowId LONG
);

create table EDWF_WorkflowSimulationJob (
	simulationJobId LONG not null primary key,
	simulationId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	status VARCHAR(75) null,
	statusResponse TEXT null,
	startTime DATE null,
	endTime DATE null,
	workflowId LONG,
	workflowUUID VARCHAR(75) null,
	reuseWorkflowUUID VARCHAR(75) null,
	screenLogic TEXT null
);

create table EDWF_WorkflowSimulation_WorkflowSimulationJob (
	simulationId LONG not null,
	simulationJobId LONG not null,
	primary key (simulationId, simulationJobId)
);

create table EDWF_Workflow_WorkflowInstance (
	workflowId LONG not null,
	workflowInstanceId LONG not null,
	primary key (workflowId, workflowInstanceId)
);