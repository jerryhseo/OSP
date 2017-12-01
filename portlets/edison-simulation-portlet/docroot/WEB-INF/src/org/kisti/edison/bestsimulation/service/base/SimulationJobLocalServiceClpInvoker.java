/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.bestsimulation.service.base;

import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class SimulationJobLocalServiceClpInvoker {
	public SimulationJobLocalServiceClpInvoker() {
		_methodName0 = "addSimulationJob";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.bestsimulation.model.SimulationJob"
			};

		_methodName1 = "createSimulationJob";

		_methodParameterTypes1 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK"
			};

		_methodName2 = "deleteSimulationJob";

		_methodParameterTypes2 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK"
			};

		_methodName3 = "deleteSimulationJob";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.bestsimulation.model.SimulationJob"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchSimulationJob";

		_methodParameterTypes10 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK"
			};

		_methodName11 = "getSimulationJob";

		_methodParameterTypes11 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSimulationJobs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSimulationJobsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSimulationJob";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.bestsimulation.model.SimulationJob"
			};

		_methodName76 = "getBeanIdentifier";

		_methodParameterTypes76 = new String[] {  };

		_methodName77 = "setBeanIdentifier";

		_methodParameterTypes77 = new String[] { "java.lang.String" };

		_methodName82 = "getSimulationJobWithJobUuid";

		_methodParameterTypes82 = new String[] { "java.lang.String" };

		_methodName83 = "getJobsWithSimulationUuid";

		_methodParameterTypes83 = new String[] { "java.lang.String", "int", "int" };

		_methodName84 = "getJobsCountWithSimulationUuid";

		_methodParameterTypes84 = new String[] { "java.lang.String" };

		_methodName86 = "createSimulationJob";

		_methodParameterTypes86 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName87 = "createSimulationJobWithJobData";

		_methodParameterTypes87 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String"
			};

		_methodName88 = "deleteSimulationJob";

		_methodParameterTypes88 = new String[] {
				"java.lang.String", "long", "long"
			};

		_methodName89 = "deleteSimulationJobByJobUuid";

		_methodParameterTypes89 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName90 = "submitSimulationJob";

		_methodParameterTypes90 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName91 = "getMaxJobSeqNoSimulationJob";

		_methodParameterTypes91 = new String[] { "java.lang.String" };

		_methodName92 = "deleteSimulationJob";

		_methodParameterTypes92 = new String[] {
				"long", "java.lang.String", "long", "long"
			};

		_methodName93 = "deleteSimulationParameter";

		_methodParameterTypes93 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName94 = "deleteSimulationCommandOption";

		_methodParameterTypes94 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName95 = "getListSimulationJob";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "long", "long", "java.util.Locale"
			};

		_methodName96 = "getMonitoringList";

		_methodParameterTypes96 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "int"
			};

		_methodName97 = "getMonitoringJobList";

		_methodParameterTypes97 = new String[] { "long", "java.lang.String" };

		_methodName98 = "getMonitoringJob";

		_methodParameterTypes98 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName100 = "getMonitoringCount";

		_methodParameterTypes100 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long"
			};

		_methodName101 = "deleteMonitoring";

		_methodParameterTypes101 = new String[] {
				"java.lang.String", "long", "long",
				"com.liferay.portal.model.User"
			};

		_methodName103 = "getMaxStatusSeqSimulationJobStatus";

		_methodParameterTypes103 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName104 = "getStatusSimulationJobStatus";

		_methodParameterTypes104 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName105 = "getMigrationSimulationJobStatus";

		_methodParameterTypes105 = new String[] { "long", "long", "boolean" };

		_methodName106 = "getSimulationMonitoringJobList";

		_methodParameterTypes106 = new String[] { "long", "long", "int", "int" };

		_methodName107 = "getSimulationMonitoringJobCount";

		_methodParameterTypes107 = new String[] { "long", "long" };

		_methodName108 = "getListVirtualLabSearchUni";

		_methodParameterTypes108 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName109 = "getListVirtualClassSearchLab";

		_methodParameterTypes109 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName110 = "getSiteCategoryIdList";

		_methodParameterTypes110 = new String[] { "long", "long" };

		_methodName112 = "getStatisticsSwTableOrganigation";

		_methodParameterTypes112 = new String[] {
				"long", "long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName113 = "getStatisticsSwBarChartDate";

		_methodParameterTypes113 = new String[] {
				"long", "long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName114 = "getStatisticsUserTableOrganigation";

		_methodParameterTypes114 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String"
			};

		_methodName115 = "getStatisticsUserBarChartDate";

		_methodParameterTypes115 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String"
			};

		_methodName116 = "getMajorAchievementsListForProjectStatistics";

		_methodParameterTypes116 = new String[] {
				"java.lang.String", "java.lang.String", "long"
			};

		_methodName117 = "getScienceAppCenterListForProjectStatistics";

		_methodParameterTypes117 = new String[] {
				"java.lang.String", "java.util.Locale"
			};

		_methodName118 = "getAppDetailListForProjectStatistics";

		_methodParameterTypes118 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Locale"
			};

		_methodName119 = "getVirtualClassStatisticsList";

		_methodParameterTypes119 = new String[] {
				"java.util.Map", "java.util.Locale", "boolean"
			};

		_methodName120 = "getCountVirtualClassStatistics";

		_methodParameterTypes120 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName121 = "getJobsBySimulationUuidWithAdditionalCondition";

		_methodParameterTypes121 = new String[] { "java.lang.String" };

		_methodName122 = "addJob";

		_methodParameterTypes122 = new String[] { "java.lang.String", "long" };

		_methodName123 = "getJob";

		_methodParameterTypes123 = new String[] { "java.lang.String" };

		_methodName124 = "getJobsByAppUuid";

		_methodParameterTypes124 = new String[] { "java.lang.String" };

		_methodName125 = "getJobsByAppUuid";

		_methodParameterTypes125 = new String[] { "java.lang.String", "int", "int" };

		_methodName126 = "countJobsByAppUuid";

		_methodParameterTypes126 = new String[] { "java.lang.String" };

		_methodName127 = "getJobsBySimulationUuid";

		_methodParameterTypes127 = new String[] { "java.lang.String" };

		_methodName128 = "getJobsBySimulationUuid";

		_methodParameterTypes128 = new String[] { "java.lang.String", "int", "int" };

		_methodName129 = "countJobsBySimulationUuid";

		_methodParameterTypes129 = new String[] { "java.lang.String" };

		_methodName130 = "getJobInputData";

		_methodParameterTypes130 = new String[] { "java.lang.String" };

		_methodName131 = "deleteJobsBySimulationUuid";

		_methodParameterTypes131 = new String[] { "java.lang.String" };

		_methodName132 = "getJobWorkingDirPath";

		_methodParameterTypes132 = new String[] { "java.lang.String" };

		_methodName133 = "deleteJob";

		_methodParameterTypes133 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SimulationJobLocalServiceUtil.addSimulationJob((org.kisti.edison.bestsimulation.model.SimulationJob)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SimulationJobLocalServiceUtil.createSimulationJob((org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationJob((org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationJob((org.kisti.edison.bestsimulation.model.SimulationJob)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SimulationJobLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SimulationJobLocalServiceUtil.fetchSimulationJob((org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationJob((org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationJobs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationJobsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SimulationJobLocalServiceUtil.updateSimulationJob((org.kisti.edison.bestsimulation.model.SimulationJob)arguments[0]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			SimulationJobLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid((java.lang.String)arguments[0]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsWithSimulationUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsCountWithSimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return SimulationJobLocalServiceUtil.createSimulationJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return SimulationJobLocalServiceUtil.createSimulationJobWithJobData((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteSimulationJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteSimulationJobByJobUuid((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SimulationJobLocalServiceUtil.submitSimulationJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMaxJobSeqNoSimulationJob((java.lang.String)arguments[0]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationParameter(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationCommandOption(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListSimulationJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.util.Locale)arguments[4]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringJobList(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteMonitoring((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.model.User)arguments[3]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMaxStatusSeqSimulationJobStatus(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatusSimulationJobStatus(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationMonitoringJobList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationMonitoringJobCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListVirtualLabSearchUni(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListVirtualClassSearchLab(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSiteCategoryIdList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsSwTableOrganigation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsSwBarChartDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsUserTableOrganigation(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsUserBarChartDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMajorAchievementsListForProjectStatistics((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getScienceAppCenterListForProjectStatistics((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getAppDetailListForProjectStatistics(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getVirtualClassStatisticsList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getCountVirtualClassStatistics((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuidWithAdditionalCondition((java.lang.String)arguments[0]);
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return SimulationJobLocalServiceUtil.addJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJob((java.lang.String)arguments[0]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsByAppUuid((java.lang.String)arguments[0]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsByAppUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return SimulationJobLocalServiceUtil.countJobsByAppUuid((java.lang.String)arguments[0]);
		}

		if (_methodName127.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes127, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			return SimulationJobLocalServiceUtil.countJobsBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobInputData((java.lang.String)arguments[0]);
		}

		if (_methodName131.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes131, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteJobsBySimulationUuid((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobWorkingDirPath((java.lang.String)arguments[0]);
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteJob((java.lang.String)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
	private String _methodName116;
	private String[] _methodParameterTypes116;
	private String _methodName117;
	private String[] _methodParameterTypes117;
	private String _methodName118;
	private String[] _methodParameterTypes118;
	private String _methodName119;
	private String[] _methodParameterTypes119;
	private String _methodName120;
	private String[] _methodParameterTypes120;
	private String _methodName121;
	private String[] _methodParameterTypes121;
	private String _methodName122;
	private String[] _methodParameterTypes122;
	private String _methodName123;
	private String[] _methodParameterTypes123;
	private String _methodName124;
	private String[] _methodParameterTypes124;
	private String _methodName125;
	private String[] _methodParameterTypes125;
	private String _methodName126;
	private String[] _methodParameterTypes126;
	private String _methodName127;
	private String[] _methodParameterTypes127;
	private String _methodName128;
	private String[] _methodParameterTypes128;
	private String _methodName129;
	private String[] _methodParameterTypes129;
	private String _methodName130;
	private String[] _methodParameterTypes130;
	private String _methodName131;
	private String[] _methodParameterTypes131;
	private String _methodName132;
	private String[] _methodParameterTypes132;
	private String _methodName133;
	private String[] _methodParameterTypes133;
}