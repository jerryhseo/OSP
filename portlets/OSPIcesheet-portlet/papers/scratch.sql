SELECT t4.screenName,
       t0.scienceAppId,
       t1.simulationuuid, 
       t2.jobUuid,
       t3.jobData
FROM User_ t4,
     EDAPP_ScienceApp t0, 
     EDSIM_Simulation t1, 
     EDSIM_SimulationJob t2, 
     EDSIM_SimulationJobData t3 
WHERE t0.name = 'rollerCoaster' 
AND t0.version = '1.0.0' 
AND t0.scienceAppId = t1.scienceAppId 
AND t1.simulationUuid = t2.simulationUuid 
AND t2.jobUuid = t3.jobUuid
AND t1.userId = t4.userId  
AND t2.jobStatus = 1701011
order by simulationUuid asc

SELECT t4.screenName, t0.scienceAppId, t1.simulationuuid, t1.simulationCreateDt, t2.jobUuid, t3.jobData
FROM User_ t4, EDAPP_ScienceApp t0, EDSIM_Simulation t1, EDSIM_SimulationJob t2, EDSIM_SimulationJobData t3
WHERE t0.name = 'gamess'
AND t0.version = '1.0.0'
AND t0.scienceAppId = t1.scienceAppId
AND t1.simulationUuid = t2.simulationUuid
AND t2.jobUuid = t3.jobUuid
AND t1.userId = t4.userId
ORDER BY t1.simulationCreateDt DESC 
LIMIT 0 , 30

SELECT t4.screenName,
       t0.scienceAppId,
       t1.simulationuuid, 
	   t1.simulationCreateDt,
       t2.jobUuid,
       t3.jobData
FROM User_ t4,
     EDAPP_ScienceApp t0, 
     EDSIM_Simulation t1, 
     EDSIM_SimulationJob t2, 
     EDSIM_SimulationJobData t3 
WHERE t0.name = 'uChem' 
AND t0.scienceAppId = t1.scienceAppId 
AND t1.simulationUuid = t2.simulationUuid 
AND t2.jobUuid = t3.jobUuid
AND t1.userId = t4.userId  
order by t1.simulationCreateDt desc, (t2.jobEndDt-t2.jobStartDt) desc

SELECT t4.screenName, t0.scienceAppId, t1.simulationuuid, t2.jobUuid, t3.jobData
FROM User_ t4, EDAPP_ScienceApp t0, EDSIM_Simulation t1, EDSIM_SimulationJob t2, EDSIM_SimulationJobData t3
WHERE t0.name = 'roundSTMtip'
AND t0.version = '1.0.0'
AND t0.scienceAppId = t1.scienceAppId
AND t1.simulationUuid = t2.simulationUuid
AND t2.jobUuid = t3.jobUuid
AND t1.userId = t4.userId

INSERT INTO EDAPP_33901  (a,b,c,d,frequency) VALUES  (0.5,1,2,3,1)

00102191-8708-44ac-af0a-ef715963db48

a = 5 ;\nb = 1.0 ;\nc = 1.0 ;\nd = 0.01 ;\ne = 0.0 ;\nf = 100 ;\ng = 0.0 


drop table EDAPP_33901;
create table EDAPP_33901 
 (paramValSetId BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  a DOUBLE NOT NULL,
  b DOUBLE NOT NULL,
  c DOUBLE NOT NULL,
  d DOUBLE NOT NULL,
  e DOUBLE NOT NULL,
  frequency DECIMAL NOT NULL,
  CONSTRAINT param_val_set_cnt UNIQUE (a,b,c,d,e)
);
insert into EDAPP_33901 (a,b,c,d,e,frequency) values (1,2,3,4,5,6);

drop table EDAPP_33901_Detailed;
create table EDAPP_33901_Detailed (
   paramValSetId BIGINT(20) NOT NULL, 
   snapshotTime datetime NOT NULL,
   elapsedTime BIGINT(20),
   status DECIMAL,
   PRIMARY KEY (paramValSetId, snapshotTime)
);

insert into EDAPP_33901_Detailed (paramValSetId,snapshotTime,elapsedTime,status) values (1,'2017-06-22 19:22:20',1000,1701011);

select frequency 
from EDAPP_33901 
where paramValSetId = 1;

update EDAPP_33901 
set frequency = 2 
where paramValSetId = 1;

insert into EDAPP_33901_Detailed (paramValSetId,snapshotTime,elapsedTime,status) values (1,'2017-06-22 19:22:21',2000,1701011);
