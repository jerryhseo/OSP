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

import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class SimulationLocalServiceClpInvoker {
	public SimulationLocalServiceClpInvoker() {
		_methodName0 = "addSimulation";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.bestsimulation.model.Simulation"
			};

		_methodName1 = "createSimulation";

		_methodParameterTypes1 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationPK"
			};

		_methodName2 = "deleteSimulation";

		_methodParameterTypes2 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationPK"
			};

		_methodName3 = "deleteSimulation";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.bestsimulation.model.Simulation"
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

		_methodName10 = "fetchSimulation";

		_methodParameterTypes10 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationPK"
			};

		_methodName11 = "getSimulation";

		_methodParameterTypes11 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.SimulationPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSimulations";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSimulationsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSimulation";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.bestsimulation.model.Simulation"
			};

		_methodName76 = "getBeanIdentifier";

		_methodParameterTypes76 = new String[] {  };

		_methodName77 = "setBeanIdentifier";

		_methodParameterTypes77 = new String[] { "java.lang.String" };

		_methodName82 = "getSimulationsWithScienceAppId";

		_methodParameterTypes82 = new String[] {
				"long", "long", "boolean", "long", "long", "int", "int"
			};

		_methodName83 = "getSimulationsCountWithScienceAppId";

		_methodParameterTypes83 = new String[] {
				"long", "long", "boolean", "long", "long"
			};

		_methodName84 = "getSimulationsWithJobUuid";

		_methodParameterTypes84 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "int", "int"
			};

		_methodName85 = "getSimulationsCountWithJobUuid";

		_methodParameterTypes85 = new String[] {
				"long", "long", "java.lang.String", "long", "long"
			};

		_methodName90 = "getSimulationBySimulationUuid";

		_methodParameterTypes90 = new String[] { "java.lang.String" };

		_methodName91 = "getSimulationByUUID";

		_methodParameterTypes91 = new String[] { "java.lang.String" };

		_methodName92 = "createSimulationWithJob";

		_methodParameterTypes92 = new String[] {
				"com.liferay.portal.model.User", "long", "java.lang.String",
				"long", "java.lang.String", "long", "long", "boolean"
			};

		_methodName94 = "getOrCreateToken";

		_methodParameterTypes94 = new String[] {
				"long", "com.liferay.portal.model.User"
			};

		_methodName97 = "executeJob";

		_methodParameterTypes97 = new String[] {
				"java.lang.String", "java.util.Map"
			};

		_methodName98 = "statusJob";

		_methodParameterTypes98 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName99 = "updateSimulation";

		_methodParameterTypes99 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName100 = "uploadFile";

		_methodParameterTypes100 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File"
			};

		_methodName101 = "deleteFile";

		_methodParameterTypes101 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName102 = "getServerFileList";

		_methodParameterTypes102 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName103 = "downloadFileJob";

		_methodParameterTypes103 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName104 = "errorView";

		_methodParameterTypes104 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName105 = "retrieveFileId";

		_methodParameterTypes105 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName106 = "retrievePostProcessor";

		_methodParameterTypes106 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName107 = "retrieveRemoteDir";

		_methodParameterTypes107 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName109 = "cancleJob";

		_methodParameterTypes109 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName110 = "retrieveCluster";

		_methodParameterTypes110 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName111 = "getResultRead";

		_methodParameterTypes111 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName112 = "getFileRead";

		_methodParameterTypes112 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName113 = "getResultFile";

		_methodParameterTypes113 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName114 = "getCurrentAssignedCoresByUser";

		_methodParameterTypes114 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName115 = "getUserRepositorySize";

		_methodParameterTypes115 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName116 = "deleteSimulationJob";

		_methodParameterTypes116 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName117 = "findByUserIdAndGroupId";

		_methodParameterTypes117 = new String[] { "long", "long" };

		_methodName118 = "findByGroupId";

		_methodParameterTypes118 = new String[] { "long", "int", "int" };

		_methodName119 = "findByUserIdAndGroupId";

		_methodParameterTypes119 = new String[] { "long", "long", "int", "int" };

		_methodName120 = "countByUserIdAndGroupId";

		_methodParameterTypes120 = new String[] { "long" };

		_methodName121 = "countByUserIdAndGroupId";

		_methodParameterTypes121 = new String[] { "long", "long" };

		_methodName122 = "findByUserIdAndGroupIdAndTitle";

		_methodParameterTypes122 = new String[] {
				"long", "long", "java.lang.String", "int", "int"
			};

		_methodName123 = "countByUserIdAndGroupIdAndTitle";

		_methodParameterTypes123 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName124 = "findByGroupIdAndTitle";

		_methodParameterTypes124 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName125 = "countByGroupIdAndTitle";

		_methodParameterTypes125 = new String[] { "long", "java.lang.String" };

		_methodName128 = "addSimulation";

		_methodParameterTypes128 = new String[] {
				"java.lang.String", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "long", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName129 = "deleteSimulation";

		_methodParameterTypes129 = new String[] { "java.lang.String" };

		_methodName130 = "addJob";

		_methodParameterTypes130 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName131 = "addJob";

		_methodParameterTypes131 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName132 = "deleteJob";

		_methodParameterTypes132 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName133 = "deleteAllJobs";

		_methodParameterTypes133 = new String[] { "java.lang.String" };

		_methodName134 = "getJobData";

		_methodParameterTypes134 = new String[] { "java.lang.String" };

		_methodName135 = "getSimulationsByAppId";

		_methodParameterTypes135 = new String[] { "long" };

		_methodName136 = "getSimulationsByAppId";

		_methodParameterTypes136 = new String[] { "long", "int", "int" };

		_methodName137 = "countSimulationsByAppId";

		_methodParameterTypes137 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SimulationLocalServiceUtil.addSimulation((org.kisti.edison.bestsimulation.model.Simulation)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SimulationLocalServiceUtil.createSimulation((org.kisti.edison.bestsimulation.service.persistence.SimulationPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SimulationLocalServiceUtil.deleteSimulation((org.kisti.edison.bestsimulation.service.persistence.SimulationPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SimulationLocalServiceUtil.deleteSimulation((org.kisti.edison.bestsimulation.model.Simulation)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SimulationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SimulationLocalServiceUtil.fetchSimulation((org.kisti.edison.bestsimulation.service.persistence.SimulationPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulation((org.kisti.edison.bestsimulation.service.persistence.SimulationPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SimulationLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulations(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SimulationLocalServiceUtil.updateSimulation((org.kisti.edison.bestsimulation.model.Simulation)arguments[0]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SimulationLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			SimulationLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsWithScienceAppId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsWithJobUuid(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsCountWithJobUuid(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationByUUID((java.lang.String)arguments[0]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return SimulationLocalServiceUtil.createSimulationWithJob((com.liferay.portal.model.User)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return SimulationLocalServiceUtil.getOrCreateToken(((Long)arguments[0]).longValue(),
				(com.liferay.portal.model.User)arguments[1]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return SimulationLocalServiceUtil.executeJob((java.lang.String)arguments[0],
				(java.util.Map)arguments[1]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return SimulationLocalServiceUtil.statusJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return SimulationLocalServiceUtil.updateSimulation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return SimulationLocalServiceUtil.uploadFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.io.File)arguments[3]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			SimulationLocalServiceUtil.deleteFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return SimulationLocalServiceUtil.getServerFileList((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return SimulationLocalServiceUtil.downloadFileJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return SimulationLocalServiceUtil.errorView((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveFileId((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return SimulationLocalServiceUtil.retrievePostProcessor((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveRemoteDir((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return SimulationLocalServiceUtil.cancleJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveCluster((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return SimulationLocalServiceUtil.getResultRead((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return SimulationLocalServiceUtil.getFileRead((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return SimulationLocalServiceUtil.getResultFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return SimulationLocalServiceUtil.getCurrentAssignedCoresByUser((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return SimulationLocalServiceUtil.getUserRepositorySize((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			return SimulationLocalServiceUtil.deleteSimulationJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return SimulationLocalServiceUtil.findByGroupId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupId(((Long)arguments[0]).longValue());
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return SimulationLocalServiceUtil.findByGroupIdAndTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return SimulationLocalServiceUtil.countByGroupIdAndTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			return SimulationLocalServiceUtil.addSimulation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			SimulationLocalServiceUtil.deleteSimulation((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			return SimulationLocalServiceUtil.addJob((java.lang.String)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName131.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes131, parameterTypes)) {
			return SimulationLocalServiceUtil.addJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			SimulationLocalServiceUtil.deleteJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			SimulationLocalServiceUtil.deleteAllJobs((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName134.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes134, parameterTypes)) {
			return SimulationLocalServiceUtil.getJobData((java.lang.String)arguments[0]);
		}

		if (_methodName135.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes135, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsByAppId(((Long)arguments[0]).longValue());
		}

		if (_methodName136.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes136, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsByAppId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName137.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes137, parameterTypes)) {
			return SimulationLocalServiceUtil.countSimulationsByAppId(((Long)arguments[0]).longValue());
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
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
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
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName111;
	private String[] _methodParameterTypes111;
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
}