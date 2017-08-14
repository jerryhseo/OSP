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

import org.kisti.edison.service.SimulationProjectLocalServiceUtil;

import java.util.Arrays;

/**
 * @author edison
 * @generated
 */
public class SimulationProjectLocalServiceClpInvoker {
	public SimulationProjectLocalServiceClpInvoker() {
		_methodName0 = "addSimulationProject";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.model.SimulationProject"
			};

		_methodName1 = "createSimulationProject";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSimulationProject";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSimulationProject";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.model.SimulationProject"
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

		_methodName10 = "fetchSimulationProject";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSimulationProject";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSimulationProjects";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSimulationProjectsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSimulationProject";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.model.SimulationProject"
			};

		_methodName62 = "getBeanIdentifier";

		_methodParameterTypes62 = new String[] {  };

		_methodName63 = "setBeanIdentifier";

		_methodParameterTypes63 = new String[] { "java.lang.String" };

		_methodName68 = "getSiteCategoryIdList";

		_methodParameterTypes68 = new String[] { "long", "long" };

		_methodName69 = "getCustomMySimulationProjectList";

		_methodParameterTypes69 = new String[] {
				"int", "int", "java.lang.String", "java.util.Locale"
			};

		_methodName70 = "getCustomMySimulationProjectList";

		_methodParameterTypes70 = new String[] {
				"long", "int", "int", "java.lang.String", "java.util.Locale"
			};

		_methodName71 = "getCustomMySimulationProjectList";

		_methodParameterTypes71 = new String[] {
				"int", "int", "java.lang.String", "java.util.Locale",
				"java.util.List"
			};

		_methodName72 = "getCustomMySimulationProjectList";

		_methodParameterTypes72 = new String[] {
				"long", "int", "int", "java.lang.String", "java.util.Locale",
				"java.util.List"
			};

		_methodName73 = "getCustomMySimulationProjectCount";

		_methodParameterTypes73 = new String[] {
				"java.lang.String", "java.util.Locale"
			};

		_methodName74 = "getCustomMySimulationProjectCount";

		_methodParameterTypes74 = new String[] {
				"long", "java.lang.String", "java.util.Locale"
			};

		_methodName75 = "getCustomMySimulationProjectCount";

		_methodParameterTypes75 = new String[] {
				"java.lang.String", "java.util.List", "java.util.Locale"
			};

		_methodName76 = "getCustomMySimulationProjectCount";

		_methodParameterTypes76 = new String[] {
				"long", "java.lang.String", "java.util.List", "java.util.Locale"
			};

		_methodName77 = "getCustomIntegratedSearchSimulationProjectList";

		_methodParameterTypes77 = new String[] {
				"int", "int", "java.lang.String", "java.util.Locale",
				"java.util.List"
			};

		_methodName78 = "getCustomIntegratedSearchSimulationProjectCount";

		_methodParameterTypes78 = new String[] {
				"java.lang.String", "java.util.List", "java.util.Locale"
			};

		_methodName79 = "getCustomLinkSimulationProjectList";

		_methodParameterTypes79 = new String[] {
				"int", "int", "java.lang.String", "java.util.List",
				"java.util.Locale"
			};

		_methodName80 = "getCustomLinkSimulationProjectList";

		_methodParameterTypes80 = new String[] {
				"java.lang.String", "java.util.List", "java.util.Locale"
			};

		_methodName81 = "getCustomLinkSimulationProjectCount";

		_methodParameterTypes81 = new String[] {
				"java.lang.String", "java.util.List", "java.util.Locale"
			};

		_methodName82 = "searchAssetEntryModelAppCount";

		_methodParameterTypes82 = new String[] { "java.util.Map" };

		_methodName83 = "insertCustomSimulationProject";

		_methodParameterTypes83 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long",
				"com.liferay.portal.model.User", "java.util.Map"
			};

		_methodName84 = "updateCustomSimulationProject";

		_methodParameterTypes84 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long",
				"com.liferay.portal.model.User", "java.util.Map"
			};

		_methodName85 = "deleteCustomSimulationProject";

		_methodParameterTypes85 = new String[] { "long" };

		_methodName86 = "getCustomDefaultInfoSimulationProject";

		_methodParameterTypes86 = new String[] {
				"long", "long", "long", "java.util.Locale"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.addSimulationProject((org.kisti.edison.model.SimulationProject)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.createSimulationProject(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.deleteSimulationProject(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.deleteSimulationProject((org.kisti.edison.model.SimulationProject)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.fetchSimulationProject(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getSimulationProject(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getSimulationProjects(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getSimulationProjectsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.updateSimulationProject((org.kisti.edison.model.SimulationProject)arguments[0]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			SimulationProjectLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getSiteCategoryIdList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3],
				(java.util.List<java.lang.Long>)arguments[4]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectList(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4],
				(java.util.List<java.lang.Long>)arguments[5]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.util.Locale)arguments[2]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount((java.lang.String)arguments[0],
				(java.util.List<java.lang.Long>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomMySimulationProjectCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.Long>)arguments[2],
				(java.util.Locale)arguments[3]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectList(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3],
				(java.util.List<java.lang.Long>)arguments[4]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectCount((java.lang.String)arguments[0],
				(java.util.List<java.lang.Long>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectList(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2],
				(java.util.List<java.lang.Long>)arguments[3],
				(java.util.Locale)arguments[4]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectList((java.lang.String)arguments[0],
				(java.util.List<java.lang.Long>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectCount((java.lang.String)arguments[0],
				(java.util.List<java.lang.Long>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.searchAssetEntryModelAppCount((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.insertCustomSimulationProject((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.model.User)arguments[4],
				(java.util.Map)arguments[5]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.updateCustomSimulationProject((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.model.User)arguments[4],
				(java.util.Map)arguments[5]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			SimulationProjectLocalServiceUtil.deleteCustomSimulationProject(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return SimulationProjectLocalServiceUtil.getCustomDefaultInfoSimulationProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(), (java.util.Locale)arguments[3]);
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
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
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
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
}