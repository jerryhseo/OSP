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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author EDISON
 * @generated
 */
public class SimulationLocalServiceClp implements SimulationLocalService {
	public SimulationLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

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

		_methodName16 = "getBeanIdentifier";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "setBeanIdentifier";

		_methodParameterTypes17 = new String[] { "java.lang.String" };

		_methodName19 = "getSimulationsWithScienceAppId";

		_methodParameterTypes19 = new String[] {
				"long", "long", "boolean", "long", "long", "int", "int"
			};

		_methodName20 = "getSimulationsCountWithScienceAppId";

		_methodParameterTypes20 = new String[] {
				"long", "long", "boolean", "long", "long"
			};

		_methodName21 = "getSimulationsWithJobUuid";

		_methodParameterTypes21 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "int", "int"
			};

		_methodName22 = "getSimulationsCountWithJobUuid";

		_methodParameterTypes22 = new String[] {
				"long", "long", "java.lang.String", "long", "long"
			};

		_methodName23 = "getSimulationBySimulationUuid";

		_methodParameterTypes23 = new String[] { "java.lang.String" };

		_methodName24 = "getSimulationByUUID";

		_methodParameterTypes24 = new String[] { "java.lang.String" };

		_methodName25 = "createSimulationWithJob";

		_methodParameterTypes25 = new String[] {
				"com.liferay.portal.model.User", "long", "java.lang.String",
				"long", "java.lang.String", "long", "long", "boolean"
			};

		_methodName26 = "createSimulationWithJob";

		_methodParameterTypes26 = new String[] {
				"com.liferay.portal.model.User", "long", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName27 = "createSimulationWithJob";

		_methodParameterTypes27 = new String[] {
				"com.liferay.portal.model.User",
				"com.liferay.portal.model.Group", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName28 = "getOrCreateToken";

		_methodParameterTypes28 = new String[] {
				"long", "com.liferay.portal.model.User"
			};

		_methodName29 = "executeJob";

		_methodParameterTypes29 = new String[] {
				"java.lang.String", "java.util.Map"
			};

		_methodName30 = "statusJob";

		_methodParameterTypes30 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName31 = "updateSimulation";

		_methodParameterTypes31 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName32 = "uploadFile";

		_methodParameterTypes32 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File"
			};

		_methodName33 = "deleteFile";

		_methodParameterTypes33 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName34 = "getServerFileList";

		_methodParameterTypes34 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName35 = "downloadFileJob";

		_methodParameterTypes35 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName36 = "errorView";

		_methodParameterTypes36 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName37 = "retrieveFileId";

		_methodParameterTypes37 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName38 = "retrievePostProcessor";

		_methodParameterTypes38 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName39 = "retrieveRemoteDir";

		_methodParameterTypes39 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName40 = "retrieveAllPostProcessor";

		_methodParameterTypes40 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName41 = "retrieveAllRemoteDir";

		_methodParameterTypes41 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName42 = "cancleJob";

		_methodParameterTypes42 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName43 = "retrieveCluster";

		_methodParameterTypes43 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName44 = "getResultRead";

		_methodParameterTypes44 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName45 = "getFileRead";

		_methodParameterTypes45 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName46 = "getResultFile";

		_methodParameterTypes46 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName47 = "getCurrentAssignedCoresByUser";

		_methodParameterTypes47 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName48 = "getUserRepositorySize";

		_methodParameterTypes48 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName49 = "deleteSimulationJob";

		_methodParameterTypes49 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName50 = "findByUserIdAndGroupId";

		_methodParameterTypes50 = new String[] { "long", "long" };

		_methodName51 = "findByGroupId";

		_methodParameterTypes51 = new String[] { "long", "int", "int" };

		_methodName52 = "findByUserIdAndGroupId";

		_methodParameterTypes52 = new String[] { "long", "long", "int", "int" };

		_methodName53 = "countByUserIdAndGroupId";

		_methodParameterTypes53 = new String[] { "long" };

		_methodName54 = "countByUserIdAndGroupId";

		_methodParameterTypes54 = new String[] { "long", "long" };

		_methodName55 = "findByUserIdAndTitle";

		_methodParameterTypes55 = new String[] {
				"long", "long", "java.lang.String", "int", "int"
			};

		_methodName56 = "countByUserIdAndTitle";

		_methodParameterTypes56 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName57 = "findByTitle";

		_methodParameterTypes57 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName58 = "countByTitle";

		_methodParameterTypes58 = new String[] { "long", "java.lang.String" };

		_methodName59 = "findByUserIdAndGroupIdAndTitle";

		_methodParameterTypes59 = new String[] {
				"long", "long", "java.lang.String", "int", "int"
			};

		_methodName60 = "countByUserIdAndGroupIdAndTitle";

		_methodParameterTypes60 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName61 = "findByGroupIdAndTitle";

		_methodParameterTypes61 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName62 = "countByGroupIdAndTitle";

		_methodParameterTypes62 = new String[] { "long", "java.lang.String" };

		_methodName63 = "addSimulation";

		_methodParameterTypes63 = new String[] {
				"java.lang.String", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "long", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName64 = "deleteSimulation";

		_methodParameterTypes64 = new String[] { "java.lang.String" };

		_methodName65 = "addJob";

		_methodParameterTypes65 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName66 = "addJob";

		_methodParameterTypes66 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName67 = "deleteJob";

		_methodParameterTypes67 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName68 = "deleteAllJobs";

		_methodParameterTypes68 = new String[] { "java.lang.String" };

		_methodName69 = "getJobData";

		_methodParameterTypes69 = new String[] { "java.lang.String" };

		_methodName70 = "getSimulationsByAppId";

		_methodParameterTypes70 = new String[] { "long" };

		_methodName71 = "getSimulationsByAppId";

		_methodParameterTypes71 = new String[] { "long", "int", "int" };

		_methodName72 = "countSimulationsByAppId";

		_methodParameterTypes72 = new String[] { "long" };
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation addSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { ClpSerializer.translateInput(simulation) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation createSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { ClpSerializer.translateInput(simulationPK) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation deleteSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] { ClpSerializer.translateInput(simulationPK) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation deleteSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] { ClpSerializer.translateInput(simulation) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.DynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					ClpSerializer.translateInput(projection)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation fetchSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] { ClpSerializer.translateInput(simulationPK) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation getSimulation(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] { ClpSerializer.translateInput(simulationPK) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13, new Object[] { start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getSimulationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation updateSimulation(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] { ClpSerializer.translateInput(simulation) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableLocalService.invokeMethod(_methodName17,
				_methodParameterTypes17,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsWithScienceAppId(
		long scienceAppId, long userId, boolean isTest, long classId,
		long customId, int begin, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] {
						scienceAppId,
						
					userId,
						
					isTest,
						
					classId,
						
					customId,
						
					begin,
						
					size
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long getSimulationsCountWithScienceAppId(long scienceAppId,
		long userId, boolean isTest, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName20,
					_methodParameterTypes20,
					new Object[] { scienceAppId, userId, isTest, classId, customId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsWithJobUuid(
		long scienceAppId, long userId, java.lang.String jobUuid, long classId,
		long customId, int begin, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						scienceAppId,
						
					userId,
						
					ClpSerializer.translateInput(jobUuid),
						
					classId,
						
					customId,
						
					begin,
						
					size
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long getSimulationsCountWithJobUuid(long scienceAppId, long userId,
		java.lang.String jobUuid, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22,
					new Object[] {
						scienceAppId,
						
					userId,
						
					ClpSerializer.translateInput(jobUuid),
						
					classId,
						
					customId
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getSimulationBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
					new Object[] { ClpSerializer.translateInput(simulationUuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof org.kisti.edison.bestsimulation.NoSuchSimulationException) {
				throw (org.kisti.edison.bestsimulation.NoSuchSimulationException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map<java.lang.String, java.lang.Object>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation getSimulationByUUID(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24,
					new Object[] { ClpSerializer.translateInput(uuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof org.kisti.edison.bestsimulation.NoSuchSimulationException) {
				throw (org.kisti.edison.bestsimulation.NoSuchSimulationException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user, long groupId,
		java.lang.String simulationTitle, long scienceAppId,
		java.lang.String scienceAppName, long classId, long customId,
		boolean testYn)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25,
					new Object[] {
						ClpSerializer.translateInput(user),
						
					groupId,
						
					ClpSerializer.translateInput(simulationTitle),
						
					scienceAppId,
						
					ClpSerializer.translateInput(scienceAppName),
						
					classId,
						
					customId,
						
					testYn
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map<java.lang.String, java.lang.Object>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user, long groupId,
		java.lang.String appName, java.lang.String appVersion,
		java.lang.String simulationTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26,
					new Object[] {
						ClpSerializer.translateInput(user),
						
					groupId,
						
					ClpSerializer.translateInput(appName),
						
					ClpSerializer.translateInput(appVersion),
						
					ClpSerializer.translateInput(simulationTitle)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map<java.lang.String, java.lang.Object>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> createSimulationWithJob(
		com.liferay.portal.model.User user,
		com.liferay.portal.model.Group group, java.lang.String appName,
		java.lang.String appVersion, java.lang.String simulationUuid,
		java.lang.String jobUuid, java.lang.String jobData,
		boolean hasSimulation)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						ClpSerializer.translateInput(user),
						
					ClpSerializer.translateInput(group),
						
					ClpSerializer.translateInput(appName),
						
					ClpSerializer.translateInput(appVersion),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(jobUuid),
						
					ClpSerializer.translateInput(jobData),
						
					hasSimulation
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map<java.lang.String, java.lang.Object>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.model.IcebreakerVcToken getOrCreateToken(
		long thisGroupId, com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.net.MalformedURLException,
			java.text.ParseException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] { thisGroupId, ClpSerializer.translateInput(
							user) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof java.text.ParseException) {
				throw (java.text.ParseException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.model.IcebreakerVcToken)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map executeJob(java.lang.String icebreakerUrl,
		java.util.Map params)
		throws java.io.IOException, java.net.MalformedURLException,
			javax.xml.parsers.ParserConfigurationException,
			org.xml.sax.SAXException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(params)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof javax.xml.parsers.ParserConfigurationException) {
				throw (javax.xml.parsers.ParserConfigurationException)t;
			}

			if (t instanceof org.xml.sax.SAXException) {
				throw (org.xml.sax.SAXException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map statusJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String job_uuid)
		throws java.io.IOException, java.net.MalformedURLException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName30,
					_methodParameterTypes30,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(job_uuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int updateSimulation(java.lang.String icebreakerUrl,
		java.lang.String simulationUuid, java.lang.String vcToken,
		java.lang.String title, java.lang.String description)
		throws java.io.IOException, java.net.MalformedURLException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName31,
					_methodParameterTypes31,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(title),
						
					ClpSerializer.translateInput(description)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public java.util.Map uploadFile(java.lang.String cluster,
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.io.File file)
		throws java.io.IOException, java.lang.InterruptedException,
			java.net.MalformedURLException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
					new Object[] {
						ClpSerializer.translateInput(cluster),
						
					ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(file)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.lang.InterruptedException) {
				throw (java.lang.InterruptedException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void deleteFile(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName33,
				_methodParameterTypes33,
				new Object[] {
					ClpSerializer.translateInput(icebreakerUrl),
					
				ClpSerializer.translateInput(vcToken),
					
				ClpSerializer.translateInput(fileId)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.String getServerFileList(java.lang.String icebreakerUrl,
		java.lang.String cmd_directory, java.lang.String cluster,
		java.lang.String vcToken)
		throws java.io.IOException, java.net.MalformedURLException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName34,
					_methodParameterTypes34,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(cmd_directory),
						
					ClpSerializer.translateInput(cluster),
						
					ClpSerializer.translateInput(vcToken)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.io.InputStream downloadFileJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid)
		throws java.io.IOException, java.net.MalformedURLException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(job_uuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof java.net.MalformedURLException) {
				throw (java.net.MalformedURLException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.io.InputStream)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String errorView(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName36,
					_methodParameterTypes36,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(job_uuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String retrieveFileId(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String job_uuid,
		java.lang.String fileName) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName37,
					_methodParameterTypes37,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(job_uuid),
						
					ClpSerializer.translateInput(fileName)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String retrievePostProcessor(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName38,
					_methodParameterTypes38,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(jobUuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String retrieveRemoteDir(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String jobUuid,
		java.lang.String dirPath) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName39,
					_methodParameterTypes39,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(jobUuid),
						
					ClpSerializer.translateInput(dirPath)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String retrieveAllPostProcessor(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName40,
					_methodParameterTypes40,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(jobUuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String retrieveAllRemoteDir(
		java.lang.String icebreakerUrl, java.lang.String vcToken,
		java.lang.String jobUuid, java.lang.String dirPath)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName41,
					_methodParameterTypes41,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(jobUuid),
						
					ClpSerializer.translateInput(dirPath)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int cancleJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String job_uuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName42,
					_methodParameterTypes42,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(job_uuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public net.sf.json.JSONObject retrieveCluster(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName43,
					_methodParameterTypes43,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.sf.json.JSONObject)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getResultRead(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName44,
					_methodParameterTypes44,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(fileId)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.io.InputStream getFileRead(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String fileId)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName45,
					_methodParameterTypes45,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(fileId)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.io.InputStream)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getResultFile(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String fileName) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName46,
					_methodParameterTypes46,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(fileName)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getCurrentAssignedCoresByUser(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName47,
					_methodParameterTypes47,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getUserRepositorySize(
		java.lang.String icebreakerUrl, java.lang.String vcToken)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName48,
					_methodParameterTypes48,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int deleteSimulationJob(java.lang.String icebreakerUrl,
		java.lang.String vcToken, java.lang.String simulationUuid,
		java.lang.String jobUuid) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName49,
					_methodParameterTypes49,
					new Object[] {
						ClpSerializer.translateInput(icebreakerUrl),
						
					ClpSerializer.translateInput(vcToken),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(jobUuid)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupId(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName50,
					_methodParameterTypes50, new Object[] { groupId, userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName51,
					_methodParameterTypes51,
					new Object[] { groupId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupId(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName52,
					_methodParameterTypes52,
					new Object[] { groupId, userId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int countByUserIdAndGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName53,
					_methodParameterTypes53, new Object[] { groupId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int countByUserIdAndGroupId(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName54,
					_methodParameterTypes54, new Object[] { groupId, userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndTitle(
		long groupId, long userId, java.lang.String tittleKeyword, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName55,
					_methodParameterTypes55,
					new Object[] {
						groupId,
						
					userId,
						
					ClpSerializer.translateInput(tittleKeyword),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long countByUserIdAndTitle(long groupId, long userId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName56,
					_methodParameterTypes56,
					new Object[] {
						groupId,
						
					userId,
						
					ClpSerializer.translateInput(tittleKeyword)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByTitle(
		long groupId, java.lang.String tittleKeyword, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName57,
					_methodParameterTypes57,
					new Object[] {
						groupId,
						
					ClpSerializer.translateInput(tittleKeyword),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long countByTitle(long groupId, java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName58,
					_methodParameterTypes58,
					new Object[] {
						groupId,
						
					ClpSerializer.translateInput(tittleKeyword)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserIdAndGroupIdAndTitle(
		long groupId, long userId, java.lang.String tittleKeyword, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName59,
					_methodParameterTypes59,
					new Object[] {
						groupId,
						
					userId,
						
					ClpSerializer.translateInput(tittleKeyword),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long countByUserIdAndGroupIdAndTitle(long groupId, long userId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName60,
					_methodParameterTypes60,
					new Object[] {
						groupId,
						
					userId,
						
					ClpSerializer.translateInput(tittleKeyword)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupIdAndTitle(
		long groupId, java.lang.String tittleKeyword, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName61,
					_methodParameterTypes61,
					new Object[] {
						groupId,
						
					ClpSerializer.translateInput(tittleKeyword),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long countByGroupIdAndTitle(long groupId,
		java.lang.String tittleKeyword)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName62,
					_methodParameterTypes62,
					new Object[] {
						groupId,
						
					ClpSerializer.translateInput(tittleKeyword)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.Simulation addSimulation(
		java.lang.String uuid, java.lang.String title, long scienceAppId,
		java.lang.String scienceAppName, java.lang.String scienceAppVersion,
		long srcClassCode, long srcClassId,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName63,
					_methodParameterTypes63,
					new Object[] {
						ClpSerializer.translateInput(uuid),
						
					ClpSerializer.translateInput(title),
						
					scienceAppId,
						
					ClpSerializer.translateInput(scienceAppName),
						
					ClpSerializer.translateInput(scienceAppVersion),
						
					srcClassCode,
						
					srcClassId,
						
					ClpSerializer.translateInput(sc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.Simulation)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void deleteSimulation(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		try {
			_invokableLocalService.invokeMethod(_methodName64,
				_methodParameterTypes64,
				new Object[] { ClpSerializer.translateInput(simulationUuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof org.kisti.edison.bestsimulation.NoSuchSimulationException) {
				throw (org.kisti.edison.bestsimulation.NoSuchSimulationException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUUID,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName65,
					_methodParameterTypes65,
					new Object[] {
						ClpSerializer.translateInput(simulationUUID),
						
					ClpSerializer.translateInput(sc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.SimulationJob)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationJob addJob(
		java.lang.String simulationUUID, java.lang.String scienceAppName,
		java.lang.String scienceAppVersion,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName66,
					_methodParameterTypes66,
					new Object[] {
						ClpSerializer.translateInput(simulationUUID),
						
					ClpSerializer.translateInput(scienceAppName),
						
					ClpSerializer.translateInput(scienceAppVersion),
						
					ClpSerializer.translateInput(sc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (org.kisti.edison.bestsimulation.model.SimulationJob)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void deleteJob(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName67,
				_methodParameterTypes67,
				new Object[] {
					ClpSerializer.translateInput(simulationUuid),
					
				ClpSerializer.translateInput(jobUuid)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void deleteAllJobs(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName68,
				_methodParameterTypes68,
				new Object[] { ClpSerializer.translateInput(simulationUuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.String getJobData(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName69,
					_methodParameterTypes69,
					new Object[] { ClpSerializer.translateInput(jobUuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsByAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName70,
					_methodParameterTypes70, new Object[] { scienceAppId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.Simulation> getSimulationsByAppId(
		long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName71,
					_methodParameterTypes71,
					new Object[] { scienceAppId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<org.kisti.edison.bestsimulation.model.Simulation>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int countSimulationsByAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName72,
					_methodParameterTypes72, new Object[] { scienceAppId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	private InvokableLocalService _invokableLocalService;
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
}