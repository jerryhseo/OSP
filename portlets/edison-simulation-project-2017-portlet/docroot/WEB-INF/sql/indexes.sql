create index IX_E53FC4D8 on EDSIMPRO_RequestMember (simulationProjectId);
create index IX_66CF69DE on EDSIMPRO_RequestMember (userId, simulationProjectId);

create index IX_7A7297E0 on EDSIMPRO_SimProScienceAppLink (simulationProjectId);

create index IX_B8B5ABD0 on EDSIMPRO_SimulationProject (ownerId);