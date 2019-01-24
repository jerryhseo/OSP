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

		_methodName90 = "getBeanIdentifier";

		_methodParameterTypes90 = new String[] {  };

		_methodName91 = "setBeanIdentifier";

		_methodParameterTypes91 = new String[] { "java.lang.String" };

		_methodName96 = "getSimulationsWithScienceAppId";

		_methodParameterTypes96 = new String[] {
				"long", "long", "boolean", "long", "long", "int", "int"
			};

		_methodName97 = "getSimulationsCountWithScienceAppId";

		_methodParameterTypes97 = new String[] {
				"long", "long", "boolean", "long", "long"
			};

		_methodName98 = "getSimulationsWithJobUuid";

		_methodParameterTypes98 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "int", "int"
			};

		_methodName99 = "getSimulationsCountWithJobUuid";

		_methodParameterTypes99 = new String[] {
				"long", "long", "java.lang.String", "long", "long"
			};

		_methodName104 = "getSimulationBySimulationUuid";

		_methodParameterTypes104 = new String[] { "java.lang.String" };

		_methodName105 = "getSimulationByUUID";

		_methodParameterTypes105 = new String[] { "java.lang.String" };

		_methodName106 = "createSimulationWithJob";

		_methodParameterTypes106 = new String[] {
				"com.liferay.portal.model.User", "long", "java.lang.String",
				"long", "java.lang.String", "long", "long", "boolean"
			};

		_methodName107 = "createSimulationWithJob";

		_methodParameterTypes107 = new String[] {
				"com.liferay.portal.model.User", "long", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName108 = "createSimulationWithJob";

		_methodParameterTypes108 = new String[] {
				"com.liferay.portal.model.User",
				"com.liferay.portal.model.Group", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName114 = "getOrCreateToken";

		_methodParameterTypes114 = new String[] {
				"long", "com.liferay.portal.model.User"
			};

		_methodName117 = "executeJob";

		_methodParameterTypes117 = new String[] {
				"java.lang.String", "java.util.Map"
			};

		_methodName118 = "statusJob";

		_methodParameterTypes118 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName119 = "updateSimulation";

		_methodParameterTypes119 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName120 = "uploadFile";

		_methodParameterTypes120 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File"
			};

		_methodName121 = "deleteFile";

		_methodParameterTypes121 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName122 = "getServerFileList";

		_methodParameterTypes122 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName123 = "downloadFileJob";

		_methodParameterTypes123 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName124 = "errorView";

		_methodParameterTypes124 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName125 = "retrieveFileId";

		_methodParameterTypes125 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName126 = "retrievePostProcessor";

		_methodParameterTypes126 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName127 = "retrieveRemoteDir";

		_methodParameterTypes127 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName129 = "retrieveAllPostProcessor";

		_methodParameterTypes129 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName130 = "retrieveAllRemoteDir";

		_methodParameterTypes130 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName132 = "cancleJob";

		_methodParameterTypes132 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName133 = "retrieveCluster";

		_methodParameterTypes133 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName134 = "getResultRead";

		_methodParameterTypes134 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName135 = "getFileRead";

		_methodParameterTypes135 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName136 = "getResultFile";

		_methodParameterTypes136 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName137 = "getCurrentAssignedCoresByUser";

		_methodParameterTypes137 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName138 = "getUserRepositorySize";

		_methodParameterTypes138 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName139 = "deleteSimulationJob";

		_methodParameterTypes139 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName140 = "findByUserIdAndGroupId";

		_methodParameterTypes140 = new String[] { "long", "long" };

		_methodName141 = "findByGroupId";

		_methodParameterTypes141 = new String[] { "long", "int", "int" };

		_methodName142 = "findByUserIdAndGroupId";

		_methodParameterTypes142 = new String[] { "long", "long", "int", "int" };

		_methodName143 = "countByUserIdAndGroupId";

		_methodParameterTypes143 = new String[] { "long" };

		_methodName144 = "countByUserIdAndGroupId";

		_methodParameterTypes144 = new String[] { "long", "long" };

		_methodName145 = "findByUserIdAndTitle";

		_methodParameterTypes145 = new String[] {
				"long", "long", "java.lang.String", "int", "int"
			};

		_methodName146 = "countByUserIdAndTitle";

		_methodParameterTypes146 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName147 = "findByTitle";

		_methodParameterTypes147 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName148 = "countByTitle";

		_methodParameterTypes148 = new String[] { "long", "java.lang.String" };

		_methodName149 = "findByUserIdAndGroupIdAndTitle";

		_methodParameterTypes149 = new String[] {
				"long", "long", "java.lang.String", "int", "int"
			};

		_methodName150 = "countByUserIdAndGroupIdAndTitle";

		_methodParameterTypes150 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName151 = "findByGroupIdAndTitle";

		_methodParameterTypes151 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName152 = "countByGroupIdAndTitle";

		_methodParameterTypes152 = new String[] { "long", "java.lang.String" };

		_methodName157 = "addSimulation";

		_methodParameterTypes157 = new String[] {
				"java.lang.String", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "long", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName158 = "deleteSimulation";

		_methodParameterTypes158 = new String[] { "java.lang.String" };

		_methodName159 = "addJob";

		_methodParameterTypes159 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName160 = "addJob";

		_methodParameterTypes160 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName161 = "deleteJob";

		_methodParameterTypes161 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName162 = "deleteAllJobs";

		_methodParameterTypes162 = new String[] { "java.lang.String" };

		_methodName163 = "getJobData";

		_methodParameterTypes163 = new String[] { "java.lang.String" };

		_methodName164 = "getSimulationsByAppId";

		_methodParameterTypes164 = new String[] { "long" };

		_methodName165 = "getSimulationsByAppId";

		_methodParameterTypes165 = new String[] { "long", "int", "int" };

		_methodName166 = "countSimulationsByAppId";

		_methodParameterTypes166 = new String[] { "long" };
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

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SimulationLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			SimulationLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsWithScienceAppId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsCountWithScienceAppId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsWithJobUuid(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsCountWithJobUuid(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationBySimulationUuid((java.lang.String)arguments[0]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationByUUID((java.lang.String)arguments[0]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return SimulationLocalServiceUtil.createSimulationWithJob((com.liferay.portal.model.User)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return SimulationLocalServiceUtil.createSimulationWithJob((com.liferay.portal.model.User)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return SimulationLocalServiceUtil.createSimulationWithJob((com.liferay.portal.model.User)arguments[0],
				(com.liferay.portal.model.Group)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return SimulationLocalServiceUtil.getOrCreateToken(((Long)arguments[0]).longValue(),
				(com.liferay.portal.model.User)arguments[1]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return SimulationLocalServiceUtil.executeJob((java.lang.String)arguments[0],
				(java.util.Map)arguments[1]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return SimulationLocalServiceUtil.statusJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return SimulationLocalServiceUtil.updateSimulation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return SimulationLocalServiceUtil.uploadFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.io.File)arguments[3]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			SimulationLocalServiceUtil.deleteFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return SimulationLocalServiceUtil.getServerFileList((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return SimulationLocalServiceUtil.downloadFileJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return SimulationLocalServiceUtil.errorView((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveFileId((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return SimulationLocalServiceUtil.retrievePostProcessor((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName127.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes127, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveRemoteDir((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveAllPostProcessor((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveAllRemoteDir((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			return SimulationLocalServiceUtil.cancleJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			return SimulationLocalServiceUtil.retrieveCluster((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName134.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes134, parameterTypes)) {
			return SimulationLocalServiceUtil.getResultRead((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName135.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes135, parameterTypes)) {
			return SimulationLocalServiceUtil.getFileRead((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName136.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes136, parameterTypes)) {
			return SimulationLocalServiceUtil.getResultFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName137.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes137, parameterTypes)) {
			return SimulationLocalServiceUtil.getCurrentAssignedCoresByUser((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName138.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes138, parameterTypes)) {
			return SimulationLocalServiceUtil.getUserRepositorySize((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName139.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes139, parameterTypes)) {
			return SimulationLocalServiceUtil.deleteSimulationJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName140.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes140, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName141.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes141, parameterTypes)) {
			return SimulationLocalServiceUtil.findByGroupId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName142.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes142, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName143.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes143, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupId(((Long)arguments[0]).longValue());
		}

		if (_methodName144.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes144, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName145.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes145, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName146.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes146, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName147.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes147, parameterTypes)) {
			return SimulationLocalServiceUtil.findByTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName148.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes148, parameterTypes)) {
			return SimulationLocalServiceUtil.countByTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName149.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes149, parameterTypes)) {
			return SimulationLocalServiceUtil.findByUserIdAndGroupIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName150.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes150, parameterTypes)) {
			return SimulationLocalServiceUtil.countByUserIdAndGroupIdAndTitle(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName151.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes151, parameterTypes)) {
			return SimulationLocalServiceUtil.findByGroupIdAndTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName152.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes152, parameterTypes)) {
			return SimulationLocalServiceUtil.countByGroupIdAndTitle(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName157.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes157, parameterTypes)) {
			return SimulationLocalServiceUtil.addSimulation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName158.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes158, parameterTypes)) {
			SimulationLocalServiceUtil.deleteSimulation((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName159.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes159, parameterTypes)) {
			return SimulationLocalServiceUtil.addJob((java.lang.String)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName160.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes160, parameterTypes)) {
			return SimulationLocalServiceUtil.addJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName161.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes161, parameterTypes)) {
			SimulationLocalServiceUtil.deleteJob((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			SimulationLocalServiceUtil.deleteAllJobs((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName163.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes163, parameterTypes)) {
			return SimulationLocalServiceUtil.getJobData((java.lang.String)arguments[0]);
		}

		if (_methodName164.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes164, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsByAppId(((Long)arguments[0]).longValue());
		}

		if (_methodName165.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes165, parameterTypes)) {
			return SimulationLocalServiceUtil.getSimulationsByAppId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName166.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes166, parameterTypes)) {
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
	private String _methodName99;
	private String[] _methodParameterTypes99;
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
	private String _methodName114;
	private String[] _methodParameterTypes114;
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
	private String _methodName129;
	private String[] _methodParameterTypes129;
	private String _methodName130;
	private String[] _methodParameterTypes130;
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
	private String _methodName149;
	private String[] _methodParameterTypes149;
	private String _methodName150;
	private String[] _methodParameterTypes150;
	private String _methodName151;
	private String[] _methodParameterTypes151;
	private String _methodName152;
	private String[] _methodParameterTypes152;
	private String _methodName157;
	private String[] _methodParameterTypes157;
	private String _methodName158;
	private String[] _methodParameterTypes158;
	private String _methodName159;
	private String[] _methodParameterTypes159;
	private String _methodName160;
	private String[] _methodParameterTypes160;
	private String _methodName161;
	private String[] _methodParameterTypes161;
	private String _methodName162;
	private String[] _methodParameterTypes162;
	private String _methodName163;
	private String[] _methodParameterTypes163;
	private String _methodName164;
	private String[] _methodParameterTypes164;
	private String _methodName165;
	private String[] _methodParameterTypes165;
	private String _methodName166;
	private String[] _methodParameterTypes166;
}