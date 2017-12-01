create index IX_6887E5D6 on EDSIM_Simulation (groupId);
create index IX_43C50810 on EDSIM_Simulation (groupId, userId);
create index IX_BA44160 on EDSIM_Simulation (scienceAppId);
create index IX_B0A4BF4A on EDSIM_Simulation (scienceAppId, groupId);
create index IX_4B3A399A on EDSIM_Simulation (scienceAppId, userId);
create index IX_A6E96850 on EDSIM_Simulation (scienceAppId, userId, groupId);
create index IX_BC16EA10 on EDSIM_Simulation (scienceAppName);
create index IX_C41188AA on EDSIM_Simulation (simulationUuid);
create index IX_F652118E on EDSIM_Simulation (userId);

create index IX_3AB915B0 on EDSIM_SimulationJob (jobSubmit);
create index IX_8756B24F on EDSIM_SimulationJob (jobSubmit, jobStatus);
create index IX_498BCE13 on EDSIM_SimulationJob (jobUuid);
create index IX_972712B on EDSIM_SimulationJob (simulationUuid);
create index IX_19D08725 on EDSIM_SimulationJob (simulationUuid, jobSubmit, jobStatus);
create index IX_2BCAD57D on EDSIM_SimulationJob (simulationUuid, jobUuid);

create index IX_101568AF on EDSIM_SimulationJobStatus (groupId, simulationUuid, jobUuid);