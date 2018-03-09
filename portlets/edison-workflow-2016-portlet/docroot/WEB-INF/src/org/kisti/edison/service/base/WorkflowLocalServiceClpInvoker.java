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

import org.kisti.edison.service.WorkflowLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class WorkflowLocalServiceClpInvoker {
	public WorkflowLocalServiceClpInvoker() {
		_methodName0 = "addWorkflow";

		_methodParameterTypes0 = new String[] { "org.kisti.edison.model.Workflow" };

		_methodName1 = "createWorkflow";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteWorkflow";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteWorkflow";

		_methodParameterTypes3 = new String[] { "org.kisti.edison.model.Workflow" };

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

		_methodName10 = "fetchWorkflow";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getWorkflow";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getWorkflows";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getWorkflowsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateWorkflow";

		_methodParameterTypes15 = new String[] { "org.kisti.edison.model.Workflow" };

		_methodName16 = "addWorkflowInstanceWorkflow";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addWorkflowInstanceWorkflow";

		_methodParameterTypes17 = new String[] {
				"long", "org.kisti.edison.model.Workflow"
			};

		_methodName18 = "addWorkflowInstanceWorkflows";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addWorkflowInstanceWorkflows";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearWorkflowInstanceWorkflows";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteWorkflowInstanceWorkflow";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteWorkflowInstanceWorkflow";

		_methodParameterTypes22 = new String[] {
				"long", "org.kisti.edison.model.Workflow"
			};

		_methodName23 = "deleteWorkflowInstanceWorkflows";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteWorkflowInstanceWorkflows";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getWorkflowInstanceWorkflows";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getWorkflowInstanceWorkflows";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getWorkflowInstanceWorkflows";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getWorkflowInstanceWorkflowsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasWorkflowInstanceWorkflow";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasWorkflowInstanceWorkflows";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setWorkflowInstanceWorkflows";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName54 = "getBeanIdentifier";

		_methodParameterTypes54 = new String[] {  };

		_methodName55 = "setBeanIdentifier";

		_methodParameterTypes55 = new String[] { "java.lang.String" };

		_methodName60 = "createWorkflow";

		_methodParameterTypes60 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"javax.servlet.http.HttpServletRequest"
			};

		_methodName61 = "createWorkflow";

		_methodParameterTypes61 = new String[] {  };

		_methodName62 = "copyWorkflow";

		_methodParameterTypes62 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"javax.servlet.http.HttpServletRequest"
			};

		_methodName63 = "copyWorkflow";

		_methodParameterTypes63 = new String[] {
				"long", "java.lang.String",
				"javax.servlet.http.HttpServletRequest"
			};

		_methodName64 = "updateWorkflow";

		_methodParameterTypes64 = new String[] {
				"long", "java.util.Map", "java.util.Locale"
			};

		_methodName65 = "updateWorkflowTutorial";

		_methodParameterTypes65 = new String[] { "long", "long" };

		_methodName66 = "updateWorkflow";

		_methodParameterTypes66 = new String[] { "java.util.Map" };

		_methodName67 = "retrieveWorkflows";

		_methodParameterTypes67 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName68 = "pauseWorkflowInstance";

		_methodParameterTypes68 = new String[] { "long" };

		_methodName69 = "resumeWorkflowInstance";

		_methodParameterTypes69 = new String[] { "long" };

		_methodName70 = "deleteWorkflowInstance";

		_methodParameterTypes70 = new String[] { "long" };

		_methodName71 = "deleteWorkflowAndInstances";

		_methodParameterTypes71 = new String[] { "long" };

		_methodName72 = "runWorkflow";

		_methodParameterTypes72 = new String[] {
				"long", "java.util.Map", "javax.servlet.http.HttpServletRequest"
			};

		_methodName73 = "runWorkflowInstance";

		_methodParameterTypes73 = new String[] {
				"long", "java.util.Map", "javax.servlet.http.HttpServletRequest"
			};

		_methodName78 = "startWorkflowInstance";

		_methodParameterTypes78 = new String[] { "long" };

		_methodName79 = "createWorkflowInstance";

		_methodParameterTypes79 = new String[] {
				"java.lang.String", "org.kisti.edison.model.Workflow",
				"javax.servlet.http.HttpServletRequest"
			};

		_methodName80 = "getWorkflowStatus";

		_methodParameterTypes80 = new String[] { "long" };

		_methodName81 = "updateWorkflowInstance";

		_methodParameterTypes81 = new String[] {
				"org.codehaus.jackson.JsonNode",
				"org.kisti.edison.model.WorkflowInstance"
			};

		_methodName82 = "askForWorkflowStart";

		_methodParameterTypes82 = new String[] { "java.lang.String" };

		_methodName83 = "askForWorkflowStatus";

		_methodParameterTypes83 = new String[] { "java.lang.String" };

		_methodName85 = "getWorkflowSimulationLog";

		_methodParameterTypes85 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName86 = "getWorkflowSimulationErrorLog";

		_methodParameterTypes86 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName88 = "getWorkflowJobIntermediateResult";

		_methodParameterTypes88 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName96 = "downloadFileApi";

		_methodParameterTypes96 = new String[] {
				"com.liferay.portal.model.User", "long",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName99 = "uploadFileToIcebreaker";

		_methodParameterTypes99 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};

		_methodName100 = "uploadFileToIcebreaker";

		_methodParameterTypes100 = new String[] {
				"long", "java.lang.String", "java.io.InputStream"
			};

		_methodName101 = "uploadFileToIcebreaker";

		_methodParameterTypes101 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName102 = "getWorkflowsByLikeSearch";

		_methodParameterTypes102 = new String[] { "java.util.Map" };

		_methodName103 = "getCountWorkflowsByLikeSearch";

		_methodParameterTypes103 = new String[] { "java.util.Map" };

		_methodName105 = "getCountWorkflowInstanceByUserId";

		_methodParameterTypes105 = new String[] {
				"com.liferay.portal.model.User", "java.util.Map"
			};

		_methodName106 = "getWorkflowInstanceByUserId";

		_methodParameterTypes106 = new String[] {
				"com.liferay.portal.model.User", "java.util.Map",
				"java.util.Locale"
			};

		_methodName107 = "getDataTypeEditors";

		_methodParameterTypes107 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName108 = "getDataTypeDefaultEditor";

		_methodParameterTypes108 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName109 = "getDataTypeDefaultAnalyzer";

		_methodParameterTypes109 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName110 = "getDataTypeAnalyzers";

		_methodParameterTypes110 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName111 = "getRootSiteAssetCategries";

		_methodParameterTypes111 = new String[] { "long", "long" };

		_methodName112 = "getSiteAssetCategoriesByParentId";

		_methodParameterTypes112 = new String[] { "long", "long", "long" };

		_methodName114 = "getLv1Categories";

		_methodParameterTypes114 = new String[] {
				"long", "long", "java.util.Locale"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return WorkflowLocalServiceUtil.addWorkflow((org.kisti.edison.model.Workflow)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return WorkflowLocalServiceUtil.createWorkflow(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return WorkflowLocalServiceUtil.deleteWorkflow(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return WorkflowLocalServiceUtil.deleteWorkflow((org.kisti.edison.model.Workflow)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return WorkflowLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return WorkflowLocalServiceUtil.fetchWorkflow(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflow(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return WorkflowLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflows(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return WorkflowLocalServiceUtil.updateWorkflow((org.kisti.edison.model.Workflow)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			WorkflowLocalServiceUtil.addWorkflowInstanceWorkflow(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			WorkflowLocalServiceUtil.addWorkflowInstanceWorkflow(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.Workflow)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			WorkflowLocalServiceUtil.addWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			WorkflowLocalServiceUtil.addWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.Workflow>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			WorkflowLocalServiceUtil.clearWorkflowInstanceWorkflows(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			WorkflowLocalServiceUtil.deleteWorkflowInstanceWorkflow(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			WorkflowLocalServiceUtil.deleteWorkflowInstanceWorkflow(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.Workflow)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			WorkflowLocalServiceUtil.deleteWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			WorkflowLocalServiceUtil.deleteWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.Workflow>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowInstanceWorkflows(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowInstanceWorkflowsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return WorkflowLocalServiceUtil.hasWorkflowInstanceWorkflow(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return WorkflowLocalServiceUtil.hasWorkflowInstanceWorkflows(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			WorkflowLocalServiceUtil.setWorkflowInstanceWorkflows(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return WorkflowLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			WorkflowLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return WorkflowLocalServiceUtil.createWorkflow((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(javax.servlet.http.HttpServletRequest)arguments[3]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return WorkflowLocalServiceUtil.createWorkflow();
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return WorkflowLocalServiceUtil.copyWorkflow(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(javax.servlet.http.HttpServletRequest)arguments[3]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return WorkflowLocalServiceUtil.copyWorkflow(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(javax.servlet.http.HttpServletRequest)arguments[2]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return WorkflowLocalServiceUtil.updateWorkflow(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return WorkflowLocalServiceUtil.updateWorkflowTutorial(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return WorkflowLocalServiceUtil.updateWorkflow((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return WorkflowLocalServiceUtil.retrieveWorkflows((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return WorkflowLocalServiceUtil.pauseWorkflowInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return WorkflowLocalServiceUtil.resumeWorkflowInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return WorkflowLocalServiceUtil.deleteWorkflowInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return WorkflowLocalServiceUtil.deleteWorkflowAndInstances(((Long)arguments[0]).longValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return WorkflowLocalServiceUtil.runWorkflow(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(javax.servlet.http.HttpServletRequest)arguments[2]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return WorkflowLocalServiceUtil.runWorkflowInstance(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(javax.servlet.http.HttpServletRequest)arguments[2]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return WorkflowLocalServiceUtil.startWorkflowInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return WorkflowLocalServiceUtil.createWorkflowInstance((java.lang.String)arguments[0],
				(org.kisti.edison.model.Workflow)arguments[1],
				(javax.servlet.http.HttpServletRequest)arguments[2]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowStatus(((Long)arguments[0]).longValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return WorkflowLocalServiceUtil.updateWorkflowInstance((org.codehaus.jackson.JsonNode)arguments[0],
				(org.kisti.edison.model.WorkflowInstance)arguments[1]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return WorkflowLocalServiceUtil.askForWorkflowStart((java.lang.String)arguments[0]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return WorkflowLocalServiceUtil.askForWorkflowStatus((java.lang.String)arguments[0]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowSimulationLog((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowSimulationErrorLog((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowJobIntermediateResult((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return WorkflowLocalServiceUtil.downloadFileApi((com.liferay.portal.model.User)arguments[0],
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.kernel.json.JSONObject)arguments[2]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return WorkflowLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return WorkflowLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.io.InputStream)arguments[2]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return WorkflowLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowsByLikeSearch((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return WorkflowLocalServiceUtil.getCountWorkflowsByLikeSearch((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return WorkflowLocalServiceUtil.getCountWorkflowInstanceByUserId((com.liferay.portal.model.User)arguments[0],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return WorkflowLocalServiceUtil.getWorkflowInstanceByUserId((com.liferay.portal.model.User)arguments[0],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return WorkflowLocalServiceUtil.getDataTypeEditors((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return WorkflowLocalServiceUtil.getDataTypeDefaultEditor((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return WorkflowLocalServiceUtil.getDataTypeDefaultAnalyzer((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return WorkflowLocalServiceUtil.getDataTypeAnalyzers((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return WorkflowLocalServiceUtil.getRootSiteAssetCategries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return WorkflowLocalServiceUtil.getSiteAssetCategoriesByParentId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return WorkflowLocalServiceUtil.getLv1Categories(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
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
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
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
	private String _methodName73;
	private String[] _methodParameterTypes73;
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
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName96;
	private String[] _methodParameterTypes96;
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
}