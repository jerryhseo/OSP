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

package org.kisti.edison.service.base;

import org.kisti.edison.service.WorkflowSimulationLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class WorkflowSimulationLocalServiceClpInvoker {
	public WorkflowSimulationLocalServiceClpInvoker() {
		_methodName0 = "addWorkflowSimulation";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.model.WorkflowSimulation"
			};

		_methodName1 = "createWorkflowSimulation";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteWorkflowSimulation";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteWorkflowSimulation";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.model.WorkflowSimulation"
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

		_methodName10 = "fetchWorkflowSimulation";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getWorkflowSimulation";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getWorkflowSimulations";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getWorkflowSimulationsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateWorkflowSimulation";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.model.WorkflowSimulation"
			};

		_methodName16 = "addWorkflowSimulationJobWorkflowSimulation";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addWorkflowSimulationJobWorkflowSimulation";

		_methodParameterTypes17 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulation"
			};

		_methodName18 = "addWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteWorkflowSimulationJobWorkflowSimulation";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteWorkflowSimulationJobWorkflowSimulation";

		_methodParameterTypes22 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulation"
			};

		_methodName23 = "deleteWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getWorkflowSimulationJobWorkflowSimulationsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasWorkflowSimulationJobWorkflowSimulation";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setWorkflowSimulationJobWorkflowSimulations";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName58 = "getBeanIdentifier";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "setBeanIdentifier";

		_methodParameterTypes59 = new String[] { "java.lang.String" };

		_methodName64 = "getWorkflowSimulations";

		_methodParameterTypes64 = new String[] {
				"java.lang.String", "long", "int", "int"
			};

		_methodName65 = "getCountWorkflowSimulations";

		_methodParameterTypes65 = new String[] { "java.lang.String", "long" };

		_methodName66 = "getWorkflowSimulations";

		_methodParameterTypes66 = new String[] {
				"long", "java.lang.String", "long", "int", "int"
			};

		_methodName67 = "getCountWorkflowSimulations";

		_methodParameterTypes67 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName68 = "createWorkflowSimulation";

		_methodParameterTypes68 = new String[] {  };

		_methodName69 = "createWorkflowSimulation";

		_methodParameterTypes69 = new String[] {
				"java.util.Map", "com.liferay.portal.model.User"
			};

		_methodName70 = "updateWorkflowSimulation";

		_methodParameterTypes70 = new String[] {
				"long", "java.util.Map", "com.liferay.portal.model.User"
			};

		_methodName71 = "getWorkflowMonitoringJobList";

		_methodParameterTypes71 = new String[] { "long", "java.util.Locale" };

		_methodName72 = "getWorkflowMonitoringList";

		_methodParameterTypes72 = new String[] {
				"long", "java.lang.String", "long", "long", "long", "int", "int",
				"java.util.Locale"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.addWorkflowSimulation((org.kisti.edison.model.WorkflowSimulation)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.createWorkflowSimulation(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulation(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulation((org.kisti.edison.model.WorkflowSimulation)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.fetchWorkflowSimulation(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulation(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulations(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulationsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.updateWorkflowSimulation((org.kisti.edison.model.WorkflowSimulation)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.addWorkflowSimulationJobWorkflowSimulation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.addWorkflowSimulationJobWorkflowSimulation(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulation)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.addWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.addWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulation>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.clearWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulationJobWorkflowSimulation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulationJobWorkflowSimulation(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulation)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.deleteWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulation>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulationJobWorkflowSimulationsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.hasWorkflowSimulationJobWorkflowSimulation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.hasWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.setWorkflowSimulationJobWorkflowSimulations(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			WorkflowSimulationLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulations((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getCountWorkflowSimulations((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowSimulations(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getCountWorkflowSimulations(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.createWorkflowSimulation();
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.createWorkflowSimulation((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(com.liferay.portal.model.User)arguments[1]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.updateWorkflowSimulation(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(com.liferay.portal.model.User)arguments[2]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowMonitoringJobList(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return WorkflowSimulationLocalServiceUtil.getWorkflowMonitoringList(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.util.Locale)arguments[7]);
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
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
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
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