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

		_methodName90 = "getBeanIdentifier";

		_methodParameterTypes90 = new String[] {  };

		_methodName91 = "setBeanIdentifier";

		_methodParameterTypes91 = new String[] { "java.lang.String" };

		_methodName96 = "getSimulationJobWithJobUuid";

		_methodParameterTypes96 = new String[] { "java.lang.String" };

		_methodName97 = "getJobsWithSimulationUuid";

		_methodParameterTypes97 = new String[] { "java.lang.String", "int", "int" };

		_methodName98 = "getJobsCountWithSimulationUuid";

		_methodParameterTypes98 = new String[] { "java.lang.String" };

		_methodName100 = "createSimulationJob";

		_methodParameterTypes100 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName101 = "createSimulationJobWithJobData";

		_methodParameterTypes101 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String"
			};

		_methodName102 = "deleteSimulationJob";

		_methodParameterTypes102 = new String[] {
				"java.lang.String", "long", "long"
			};

		_methodName103 = "deleteSimulationJobByJobUuid";

		_methodParameterTypes103 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName104 = "submitSimulationJob";

		_methodParameterTypes104 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName105 = "getMaxJobSeqNoSimulationJob";

		_methodParameterTypes105 = new String[] { "java.lang.String" };

		_methodName106 = "deleteSimulationJob";

		_methodParameterTypes106 = new String[] {
				"long", "java.lang.String", "long", "long"
			};

		_methodName107 = "deleteSimulationParameter";

		_methodParameterTypes107 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName108 = "deleteSimulationCommandOption";

		_methodParameterTypes108 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName109 = "getListSimulationJob";

		_methodParameterTypes109 = new String[] {
				"long", "java.lang.String", "long", "long", "java.util.Locale"
			};

		_methodName110 = "getMonitoringList";

		_methodParameterTypes110 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "int"
			};

		_methodName111 = "getMonitoringJobList";

		_methodParameterTypes111 = new String[] { "long", "java.lang.String" };

		_methodName112 = "getMonitoringJob";

		_methodParameterTypes112 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName114 = "getMonitoringCount";

		_methodParameterTypes114 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long"
			};

		_methodName115 = "deleteMonitoring";

		_methodParameterTypes115 = new String[] {
				"java.lang.String", "long", "long",
				"com.liferay.portal.model.User"
			};

		_methodName117 = "getMaxStatusSeqSimulationJobStatus";

		_methodParameterTypes117 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName118 = "getStatusSimulationJobStatus";

		_methodParameterTypes118 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName119 = "getMigrationSimulationJobStatus";

		_methodParameterTypes119 = new String[] { "long", "long", "boolean" };

		_methodName120 = "getSimulationMonitoringJobList";

		_methodParameterTypes120 = new String[] { "long", "long", "int", "int" };

		_methodName121 = "getSimulationMonitoringJobCount";

		_methodParameterTypes121 = new String[] { "long", "long" };

		_methodName122 = "getListVirtualLabSearchUni";

		_methodParameterTypes122 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName123 = "getListVirtualClassSearchLab";

		_methodParameterTypes123 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName124 = "getSiteCategoryIdList";

		_methodParameterTypes124 = new String[] { "long", "long" };

		_methodName126 = "getStatisticsSwTableOrganigation";

		_methodParameterTypes126 = new String[] {
				"long", "long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName127 = "getStatisticsSwBarChartDate";

		_methodParameterTypes127 = new String[] {
				"long", "long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName128 = "getStatisticsUserTableOrganigation";

		_methodParameterTypes128 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String",
				"java.lang.String"
			};

		_methodName129 = "getStatisticsUserBarChartDate";

		_methodParameterTypes129 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String"
			};

		_methodName130 = "getMajorAchievementsListForProjectStatistics";

		_methodParameterTypes130 = new String[] {
				"java.lang.String", "java.lang.String", "long"
			};

		_methodName131 = "getScienceAppCenterListForProjectStatistics";

		_methodParameterTypes131 = new String[] {
				"java.lang.String", "java.util.Locale"
			};

		_methodName132 = "getAppDetailListForProjectStatistics";

		_methodParameterTypes132 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Locale"
			};

		_methodName133 = "getVirtualClassStatisticsSimulation";

		_methodParameterTypes133 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName134 = "getVirtualClassStatisticsList";

		_methodParameterTypes134 = new String[] {
				"java.util.Map", "java.util.Locale", "boolean"
			};

		_methodName135 = "getCountVirtualClassStatistics";

		_methodParameterTypes135 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName136 = "getJobsBySimulationUuidWithAdditionalCondition";

		_methodParameterTypes136 = new String[] { "java.lang.String" };

		_methodName137 = "addJob";

		_methodParameterTypes137 = new String[] { "java.lang.String", "long" };

		_methodName138 = "getJob";

		_methodParameterTypes138 = new String[] { "java.lang.String" };

		_methodName139 = "getJobsByAppUuid";

		_methodParameterTypes139 = new String[] { "java.lang.String" };

		_methodName140 = "getJobsByAppUuid";

		_methodParameterTypes140 = new String[] { "java.lang.String", "int", "int" };

		_methodName141 = "countJobsByAppUuid";

		_methodParameterTypes141 = new String[] { "java.lang.String" };

		_methodName142 = "getJobsBySimulationUuid";

		_methodParameterTypes142 = new String[] { "java.lang.String" };

		_methodName143 = "getJobsBySimulationUuid";

		_methodParameterTypes143 = new String[] { "java.lang.String", "int", "int" };

		_methodName144 = "countJobsBySimulationUuid";

		_methodParameterTypes144 = new String[] { "java.lang.String" };

		_methodName145 = "getJobInputData";

		_methodParameterTypes145 = new String[] { "java.lang.String" };

		_methodName146 = "deleteJobsBySimulationUuid";

		_methodParameterTypes146 = new String[] { "java.lang.String" };

		_methodName147 = "getJobWorkingDirPath";

		_methodParameterTypes147 = new String[] { "java.lang.String" };

		_methodName148 = "deleteJob";

		_methodParameterTypes148 = new String[] { "java.lang.String" };

		_methodName152 = "executeSchedulerOfClassStatistics";

		_methodParameterTypes152 = new String[] { "java.util.Map" };

		_methodName153 = "getVirtualLabClassStatisticsList";

		_methodParameterTypes153 = new String[] {
				"java.util.Map", "java.util.Locale", "boolean"
			};

		_methodName154 = "getClassStatisticsManagementList";

		_methodParameterTypes154 = new String[] {
				"java.util.Map", "java.util.Locale", "boolean"
			};
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

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			SimulationJobLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationJobWithJobUuid((java.lang.String)arguments[0]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsWithSimulationUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsCountWithSimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return SimulationJobLocalServiceUtil.createSimulationJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return SimulationJobLocalServiceUtil.createSimulationJobWithJobData((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteSimulationJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteSimulationJobByJobUuid((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return SimulationJobLocalServiceUtil.submitSimulationJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMaxJobSeqNoSimulationJob((java.lang.String)arguments[0]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationParameter(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteSimulationCommandOption(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListSimulationJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.util.Locale)arguments[4]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringJobList(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMonitoringCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue());
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return SimulationJobLocalServiceUtil.deleteMonitoring((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.model.User)arguments[3]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMaxStatusSeqSimulationJobStatus(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatusSimulationJobStatus(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMigrationSimulationJobStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationMonitoringJobList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSimulationMonitoringJobCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListVirtualLabSearchUni(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getListVirtualClassSearchLab(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getSiteCategoryIdList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsSwTableOrganigation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName127.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes127, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsSwBarChartDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsUserTableOrganigation(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getStatisticsUserBarChartDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getMajorAchievementsListForProjectStatistics((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName131.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes131, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getScienceAppCenterListForProjectStatistics((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getAppDetailListForProjectStatistics(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4]);
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getVirtualClassStatisticsSimulation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName134.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes134, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getVirtualClassStatisticsList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName135.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes135, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getCountVirtualClassStatistics((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName136.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes136, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuidWithAdditionalCondition((java.lang.String)arguments[0]);
		}

		if (_methodName137.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes137, parameterTypes)) {
			return SimulationJobLocalServiceUtil.addJob((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName138.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes138, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJob((java.lang.String)arguments[0]);
		}

		if (_methodName139.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes139, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsByAppUuid((java.lang.String)arguments[0]);
		}

		if (_methodName140.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes140, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsByAppUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName141.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes141, parameterTypes)) {
			return SimulationJobLocalServiceUtil.countJobsByAppUuid((java.lang.String)arguments[0]);
		}

		if (_methodName142.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes142, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName143.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes143, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobsBySimulationUuid((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName144.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes144, parameterTypes)) {
			return SimulationJobLocalServiceUtil.countJobsBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName145.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes145, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobInputData((java.lang.String)arguments[0]);
		}

		if (_methodName146.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes146, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteJobsBySimulationUuid((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName147.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes147, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getJobWorkingDirPath((java.lang.String)arguments[0]);
		}

		if (_methodName148.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes148, parameterTypes)) {
			SimulationJobLocalServiceUtil.deleteJob((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName152.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes152, parameterTypes)) {
			SimulationJobLocalServiceUtil.executeSchedulerOfClassStatistics((java.util.Map)arguments[0]);

			return null;
		}

		if (_methodName153.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes153, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getVirtualLabClassStatisticsList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName154.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes154, parameterTypes)) {
			return SimulationJobLocalServiceUtil.getClassStatisticsManagementList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1],
				((Boolean)arguments[2]).booleanValue());
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
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
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
	private String _methodName102;
	private String[] _methodParameterTypes102;
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
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
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
	private String _methodName134;
	private String[] _methodParameterTypes134;
	private String _methodName135;
	private String[] _methodParameterTypes135;
	private String _methodName136;
	private String[] _methodParameterTypes136;
	private String _methodName137;
	private String[] _methodParameterTypes137;
	private String _methodName138;
	private String[] _methodParameterTypes138;
	private String _methodName139;
	private String[] _methodParameterTypes139;
	private String _methodName140;
	private String[] _methodParameterTypes140;
	private String _methodName141;
	private String[] _methodParameterTypes141;
	private String _methodName142;
	private String[] _methodParameterTypes142;
	private String _methodName143;
	private String[] _methodParameterTypes143;
	private String _methodName144;
	private String[] _methodParameterTypes144;
	private String _methodName145;
	private String[] _methodParameterTypes145;
	private String _methodName146;
	private String[] _methodParameterTypes146;
	private String _methodName147;
	private String[] _methodParameterTypes147;
	private String _methodName148;
	private String[] _methodParameterTypes148;
	private String _methodName152;
	private String[] _methodParameterTypes152;
	private String _methodName153;
	private String[] _methodParameterTypes153;
	private String _methodName154;
	private String[] _methodParameterTypes154;
}