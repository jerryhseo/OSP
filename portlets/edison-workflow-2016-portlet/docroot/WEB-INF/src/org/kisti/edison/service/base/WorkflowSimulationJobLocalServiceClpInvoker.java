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

import org.kisti.edison.service.WorkflowSimulationJobLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class WorkflowSimulationJobLocalServiceClpInvoker {
	public WorkflowSimulationJobLocalServiceClpInvoker() {
		_methodName0 = "addWorkflowSimulationJob";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName1 = "createWorkflowSimulationJob";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteWorkflowSimulationJob";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteWorkflowSimulationJob";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
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

		_methodName10 = "fetchWorkflowSimulationJob";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getWorkflowSimulationJob";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getWorkflowSimulationJobs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getWorkflowSimulationJobsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateWorkflowSimulationJob";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName16 = "addWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes17 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName18 = "addWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes22 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName23 = "deleteWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getWorkflowSimulationWorkflowSimulationJobsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

<<<<<<< HEAD
		_methodName58 = "getBeanIdentifier";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "setBeanIdentifier";

		_methodParameterTypes59 = new String[] { "java.lang.String" };

		_methodName64 = "getWorkflowSimulationJobs";

		_methodParameterTypes64 = new String[] {
				"long", "java.lang.String", "long", "int", "int"
			};

		_methodName65 = "updateWorkflowSimulationJob";

		_methodParameterTypes65 = new String[] { "long", "java.util.Map" };

		_methodName66 = "createWorkflowSimulationJob";

		_methodParameterTypes66 = new String[] {
				"long", "java.util.Map", "javax.servlet.http.HttpServletRequest"
			};

		_methodName68 = "createSimulationJob";

		_methodParameterTypes68 = new String[] {
				"org.kisti.edison.model.WorkflowSimulation",
				"org.kisti.edison.model.Workflow", "java.lang.String"
			};

		_methodName69 = "createWorkflowSimulationJob";

		_methodParameterTypes69 = new String[] {  };

		_methodName70 = "createWorkflowSimulationJob";

		_methodParameterTypes70 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName72 = "startWorkflowSimulationJob";

		_methodParameterTypes72 = new String[] { "long" };

		_methodName73 = "startWorkflowSimulationJob";

		_methodParameterTypes73 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName74 = "askForWorkflowStart";

		_methodParameterTypes74 = new String[] { "java.lang.String" };

		_methodName75 = "getWorkflowStatus";

		_methodParameterTypes75 = new String[] { "long" };

		_methodName76 = "deleteSimulationAndJobs";

		_methodParameterTypes76 = new String[] { "long" };

		_methodName77 = "deleteWorkflowSimulationJobWitEngine";

		_methodParameterTypes77 = new String[] { "long" };

		_methodName78 = "deleteWorkflowSimulationJobWitEngine";

		_methodParameterTypes78 = new String[] {
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName79 = "pauseWorkflowSimulationJob";

		_methodParameterTypes79 = new String[] { "long" };

		_methodName80 = "resumeWorkflowSimulationJob";

		_methodParameterTypes80 = new String[] { "long" };

		_methodName81 = "updateWorkflowSimulationJob";

		_methodParameterTypes81 = new String[] {
				"org.codehaus.jackson.JsonNode",
				"org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName82 = "askForWorkflowStatus";

		_methodParameterTypes82 = new String[] { "java.lang.String" };

		_methodName84 = "getWorkflowSimulationLog";

		_methodParameterTypes84 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName85 = "getWorkflowSimulationErrorLog";

		_methodParameterTypes85 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName87 = "getWorkflowJobIntermediateResult";

		_methodParameterTypes87 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName92 = "downloadFileApi";

		_methodParameterTypes92 = new String[] {
				"com.liferay.portal.model.User", "long",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName95 = "uploadFileToIcebreaker";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};

		_methodName96 = "uploadFileToIcebreaker";

		_methodParameterTypes96 = new String[] {
				"long", "java.lang.String", "java.io.InputStream"
			};

		_methodName97 = "uploadFileToIcebreaker";

		_methodParameterTypes97 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName98 = "getCountWorkflowSimulationJobByUserId";

		_methodParameterTypes98 = new String[] {
				"com.liferay.portal.model.User", "java.util.Map"
			};

		_methodName99 = "getWorkflowSimulationJobByUserId";

		_methodParameterTypes99 = new String[] {
				"com.liferay.portal.model.User", "java.util.Map",
				"java.util.Locale"
			};

		_methodName100 = "getDataTypeEditors";

		_methodParameterTypes100 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName101 = "getDataTypeDefaultEditor";

		_methodParameterTypes101 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName102 = "getDataTypeDefaultAnalyzer";

		_methodParameterTypes102 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName103 = "getDataTypeAnalyzers";

		_methodParameterTypes103 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName104 = "getRootSiteAssetCategries";

		_methodParameterTypes104 = new String[] { "long", "long" };

		_methodName105 = "getSiteAssetCategoriesByParentId";

		_methodParameterTypes105 = new String[] { "long", "long", "long" };

		_methodName108 = "getLv1Categories";

		_methodParameterTypes108 = new String[] {
				"long", "long", "java.util.Locale"
			};
=======
		_methodName32 = "addWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes32 = new String[] { "long", "long" };

		_methodName33 = "addWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes33 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName34 = "addWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes34 = new String[] { "long", "long[][]" };

		_methodName35 = "addWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes35 = new String[] { "long", "java.util.List" };

		_methodName36 = "clearWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "deleteWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "deleteWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes38 = new String[] {
				"long", "org.kisti.edison.model.WorkflowSimulationJob"
			};

		_methodName39 = "deleteWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "deleteWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes42 = new String[] { "long", "int", "int" };

		_methodName43 = "getWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes43 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName44 = "getWorkflowSimulationWorkflowSimulationJobsCount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "hasWorkflowSimulationWorkflowSimulationJob";

		_methodParameterTypes45 = new String[] { "long", "long" };

		_methodName46 = "hasWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "setWorkflowSimulationWorkflowSimulationJobs";

		_methodParameterTypes47 = new String[] { "long", "long[][]" };

		_methodName78 = "getBeanIdentifier";

		_methodParameterTypes78 = new String[] {  };

		_methodName79 = "setBeanIdentifier";

		_methodParameterTypes79 = new String[] { "java.lang.String" };
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationJob((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.createWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationJob((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.fetchWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulationJob)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulationJob>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.clearWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulationJob)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulationJob>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.hasWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.hasWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.setWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

<<<<<<< HEAD
		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
=======
		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulationJob)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.addWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulationJob>)arguments[1]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.clearWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(org.kisti.edison.model.WorkflowSimulationJob)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.model.WorkflowSimulationJob>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationWorkflowSimulationJobsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.hasWorkflowSimulationWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.hasWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			WorkflowSimulationJobLocalServiceUtil.setWorkflowSimulationWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
			WorkflowSimulationJobLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobs(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.createWorkflowSimulationJob(((Long)arguments[0]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(javax.servlet.http.HttpServletRequest)arguments[2]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.createSimulationJob((org.kisti.edison.model.WorkflowSimulation)arguments[0],
				(org.kisti.edison.model.Workflow)arguments[1],
				(java.lang.String)arguments[2]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.createWorkflowSimulationJob();
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.createWorkflowSimulationJob((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.startWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.startWorkflowSimulationJob((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.askForWorkflowStart((java.lang.String)arguments[0]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowStatus(((Long)arguments[0]).longValue());
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.deleteSimulationAndJobs(((Long)arguments[0]).longValue());
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationJobWitEngine(((Long)arguments[0]).longValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.deleteWorkflowSimulationJobWitEngine((org.kisti.edison.model.WorkflowSimulationJob)arguments[0]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.pauseWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.resumeWorkflowSimulationJob(((Long)arguments[0]).longValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.updateWorkflowSimulationJob((org.codehaus.jackson.JsonNode)arguments[0],
				(org.kisti.edison.model.WorkflowSimulationJob)arguments[1]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.askForWorkflowStatus((java.lang.String)arguments[0]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationLog((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationErrorLog((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowJobIntermediateResult((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.downloadFileApi((com.liferay.portal.model.User)arguments[0],
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.kernel.json.JSONObject)arguments[2]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.io.InputStream)arguments[2]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.uploadFileToIcebreaker(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getCountWorkflowSimulationJobByUserId((com.liferay.portal.model.User)arguments[0],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getWorkflowSimulationJobByUserId((com.liferay.portal.model.User)arguments[0],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[1],
				(java.util.Locale)arguments[2]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getDataTypeEditors((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getDataTypeDefaultEditor((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getDataTypeDefaultAnalyzer((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getDataTypeAnalyzers((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getRootSiteAssetCategries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getSiteAssetCategoriesByParentId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return WorkflowSimulationJobLocalServiceUtil.getLv1Categories(((Long)arguments[0]).longValue(),
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
<<<<<<< HEAD
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
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
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
=======
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
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
<<<<<<< HEAD
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
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
	private String _methodName108;
	private String[] _methodParameterTypes108;
=======
>>>>>>> 7b21a178c4fc74feebf3336ab3a4473abb54bf29
}