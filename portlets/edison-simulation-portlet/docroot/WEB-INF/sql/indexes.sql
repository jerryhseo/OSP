create index IX_6887E5D6 on EDSIM_Simulation (groupId);
create index IX_43C50810 on EDSIM_Simulation (groupId, userId);
create index IX_BA44160 on EDSIM_Simulation (scienceAppId);
create index IX_C41188AA on EDSIM_Simulation (simulationUuid);
create index IX_F652118E on EDSIM_Simulation (userId);

create index IX_972712B on EDSIM_SimulationJob (simulationUuid);
create index IX_2BCAD57D on EDSIM_SimulationJob (simulationUuid, jobUuid);

create index IX_101568AF on EDSIM_SimulationJobStatus (groupId, simulationUuid, jobUuid);