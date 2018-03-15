-- total
SELECT sum( frequency ) as b FROM EDAPP_27901

-- unique
select t0.a+t1.b
from (SELECT count(frequency) as a FROM EDAPP_27901 WHERE frequency >1) t0,
     (SELECT sum( frequency ) as b FROM EDAPP_27901 WHERE frequency = 1) t1

-- duplicate
SELECT sum(frequency)-count(frequency) FROM EDAPP_27901 WHERE frequency >1

-- total elapsed time
SELECT sum(elapsedTime), sum(queuingTime) FROM EDAPP_27901_Detailed 

-- duplicate elapsed time
SELECT sum( elapsedTime ), avg(elapsedTime), max(elapsedTime), sum( queuingTime ), avg(queuingTime), max(queuingTime)
FROM EDAPP_27901_Detailed t1, 
        (SELECT paramValSetId, min( snapshotTime ) AS snapshotTime
        FROM EDAPP_27901_Detailed
        GROUP BY paramValSetId) t2
WHERE t1.paramValSetId = t2.paramValSetId
AND t1.snapshotTime <> t2.snapshotTime

---
SELECT * 
FROM EDSIM_SimulationProv
WHERE simulationCreateDt >= SUBDATE( NOW( ) , 60 ) 
LIMIT 0 , 30

--
 SELECT simulationUuid, simulationTitle, simulationCreateDt, provLoc  FROM EDSIM_SimulationProv WHERE subjectId = 27901  and simulationCreateDt >= SUBDATE(NOW(), 60) order by simulationCreateDt desc LIMIT 0 , 10



