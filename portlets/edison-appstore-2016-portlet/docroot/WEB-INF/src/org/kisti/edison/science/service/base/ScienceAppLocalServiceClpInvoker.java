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

package org.kisti.edison.science.service.base;

import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class ScienceAppLocalServiceClpInvoker {
	public ScienceAppLocalServiceClpInvoker() {
		_methodName0 = "addScienceApp";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName1 = "createScienceApp";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteScienceApp";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteScienceApp";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
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

		_methodName10 = "fetchScienceApp";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchScienceAppByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchScienceAppByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getScienceApp";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getScienceAppByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getScienceAppByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getScienceApps";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getScienceAppsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateScienceApp";

		_methodParameterTypes19 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName190 = "getBeanIdentifier";

		_methodParameterTypes190 = new String[] {  };

		_methodName191 = "setBeanIdentifier";

		_methodParameterTypes191 = new String[] { "java.lang.String" };

		_methodName196 = "createScienceApp";

		_methodParameterTypes196 = new String[] {
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName197 = "getTextEditorScienceApp";

		_methodParameterTypes197 = new String[] {  };

		_methodName198 = "getFileEditorScienceApp";

		_methodParameterTypes198 = new String[] {  };

		_methodName199 = "getStructuredEditorScienceApp";

		_methodParameterTypes199 = new String[] {  };

		_methodName200 = "getEditorScienceApp";

		_methodParameterTypes200 = new String[] { "java.lang.String" };

		_methodName201 = "addScienceApp";

		_methodParameterTypes201 = new String[] {
				"org.kisti.edison.science.model.ScienceApp",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName202 = "setScienceAppInputPorts";

		_methodParameterTypes202 = new String[] { "long", "java.lang.String" };

		_methodName203 = "getScienceAppInputPorts";

		_methodParameterTypes203 = new String[] { "long" };

		_methodName204 = "setScienceAppLogPorts";

		_methodParameterTypes204 = new String[] { "long", "java.lang.String" };

		_methodName205 = "getScienceAppLogPorts";

		_methodParameterTypes205 = new String[] { "long" };

		_methodName206 = "setScienceAppOutputPorts";

		_methodParameterTypes206 = new String[] { "long", "java.lang.String" };

		_methodName207 = "getScienceAppOutputPorts";

		_methodParameterTypes207 = new String[] { "long" };

		_methodName208 = "verifyScienceAppName";

		_methodParameterTypes208 = new String[] { "java.lang.String" };

		_methodName209 = "existAppName";

		_methodParameterTypes209 = new String[] { "java.lang.String" };

		_methodName210 = "existApp";

		_methodParameterTypes210 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName211 = "getLatestVersion";

		_methodParameterTypes211 = new String[] { "java.lang.String" };

		_methodName212 = "verifyVersionNumber";

		_methodParameterTypes212 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName213 = "deleteScienceApp";

		_methodParameterTypes213 = new String[] { "long" };

		_methodName214 = "deleteScienceApp";

		_methodParameterTypes214 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName215 = "deleteAllScienceApps";

		_methodParameterTypes215 = new String[] {  };

		_methodName216 = "getAll";

		_methodParameterTypes216 = new String[] {  };

		_methodName217 = "updateScienceApp";

		_methodParameterTypes217 = new String[] {
				"org.kisti.edison.science.model.ScienceApp",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName218 = "getScienceAppListByStatus";

		_methodParameterTypes218 = new String[] { "int" };

		_methodName219 = "getScienceAppListByStage";

		_methodParameterTypes219 = new String[] { "java.lang.String" };

		_methodName220 = "getScienceAppListByAuthorIdStatus";

		_methodParameterTypes220 = new String[] { "long", "int" };

		_methodName221 = "getScienceAppListByAuthorIdStatus";

		_methodParameterTypes221 = new String[] { "long", "int", "int", "int" };

		_methodName222 = "getScienceAppListByAuthorIdAppType";

		_methodParameterTypes222 = new String[] { "long", "java.lang.String" };

		_methodName223 = "getScienceAppListByAuthorIdAppType";

		_methodParameterTypes223 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName224 = "getScienceAppListByAuthorId";

		_methodParameterTypes224 = new String[] { "long" };

		_methodName225 = "getScienceAppListByAuthorId";

		_methodParameterTypes225 = new String[] { "long", "int", "int" };

		_methodName226 = "countScienceAppsByAuthorId";

		_methodParameterTypes226 = new String[] { "long" };

		_methodName227 = "getScienceAppListByOpenLevel";

		_methodParameterTypes227 = new String[] { "java.lang.String" };

		_methodName228 = "getScienceAppListByOpenLevel";

		_methodParameterTypes228 = new String[] { "java.lang.String", "int", "int" };

		_methodName229 = "getScienceAppListByManagerId";

		_methodParameterTypes229 = new String[] { "long" };

		_methodName230 = "getScienceAppListByManagerId";

		_methodParameterTypes230 = new String[] { "long", "int", "int" };

		_methodName231 = "countScienceAppsByManagerId";

		_methodParameterTypes231 = new String[] { "long" };

		_methodName232 = "getScienceAppManagerIds";

		_methodParameterTypes232 = new String[] { "long" };

		_methodName233 = "getScienceAppManagerIds";

		_methodParameterTypes233 = new String[] { "long", "int", "int" };

		_methodName234 = "countScienceAppManagers";

		_methodParameterTypes234 = new String[] { "long" };

		_methodName235 = "getScienceAppListByCategoryId";

		_methodParameterTypes235 = new String[] { "long" };

		_methodName236 = "getScienceAppListByCategoryId";

		_methodParameterTypes236 = new String[] { "long", "int", "int" };

		_methodName237 = "countScienceAppsByCategoryId";

		_methodParameterTypes237 = new String[] { "long" };

		_methodName238 = "getScienceAppCategoryIds";

		_methodParameterTypes238 = new String[] { "long" };

		_methodName239 = "getScienceAppCategoryIds";

		_methodParameterTypes239 = new String[] { "long", "int", "int" };

		_methodName240 = "countScienceAppCategories";

		_methodParameterTypes240 = new String[] { "long" };

		_methodName241 = "assignScienceAppToCategories";

		_methodParameterTypes241 = new String[] { "long", "long[][]" };

		_methodName242 = "assignScienceAppToCategory";

		_methodParameterTypes242 = new String[] { "long", "long" };

		_methodName243 = "assignManagersToScienceApp";

		_methodParameterTypes243 = new String[] { "long", "long[][]" };

		_methodName244 = "assignManagerToScienceApp";

		_methodParameterTypes244 = new String[] { "long", "long" };

		_methodName245 = "getScienceAppBinPath";

		_methodParameterTypes245 = new String[] { "long" };

		_methodName246 = "getScienceAppSrcPath";

		_methodParameterTypes246 = new String[] { "long" };

		_methodName247 = "countAllScienceApps";

		_methodParameterTypes247 = new String[] {  };

		_methodName250 = "existScienceAppPath";

		_methodParameterTypes250 = new String[] { "java.lang.String" };

		_methodName251 = "deleteScienceAppDir";

		_methodParameterTypes251 = new String[] { "java.lang.String" };

		_methodName252 = "makeScienceAppDir";

		_methodParameterTypes252 = new String[] { "java.lang.String" };

		_methodName253 = "saveToScienceAppStorage";

		_methodParameterTypes253 = new String[] {
				"java.lang.String", "java.lang.String", "java.io.InputStream"
			};

		_methodName254 = "unzipScienceAppZipFile";

		_methodParameterTypes254 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName256 = "cleanScienceAppDir";

		_methodParameterTypes256 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName257 = "getScienceAppListByCategoryId2";

		_methodParameterTypes257 = new String[] { "long", "java.util.Locale" };

		_methodName258 = "getScienceAppListByCategoryId";

		_methodParameterTypes258 = new String[] { "long", "java.util.Locale" };

		_methodName259 = "getScienceAppListByCategoryId";

		_methodParameterTypes259 = new String[] {
				"long", "java.util.Locale", "int", "int"
			};

		_methodName260 = "getScienceAppListByScienceAppIdsAndLocale";

		_methodParameterTypes260 = new String[] { "long[][]", "java.util.Locale" };

		_methodName261 = "getLanguageSpecificAssetCategories";

		_methodParameterTypes261 = new String[] {
				"java.util.List", "long", "java.util.Locale"
			};

		_methodName262 = "getScienceAppListByScienceAppIdsAndLocale";

		_methodParameterTypes262 = new String[] {
				"long[][]", "java.util.Locale", "int", "int"
			};

		_methodName263 = "createScienceApp";

		_methodParameterTypes263 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map"
			};

		_methodName264 = "updateScienceApp";

		_methodParameterTypes264 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map",
				"long"
			};

		_methodName265 = "retrieveListScienceAppFromExplore";

		_methodParameterTypes265 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String", "int", "int"
			};

		_methodName266 = "retrieveListScienceAppFromExplore";

		_methodParameterTypes266 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String"
			};

		_methodName267 = "countScienceAppFromExplore";

		_methodParameterTypes267 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String"
			};

		_methodName268 = "countScienceAppFromExplore";

		_methodParameterTypes268 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String", "java.lang.String"
			};

		_methodName269 = "retrieveListScienceAppAsManager";

		_methodParameterTypes269 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean", "int", "int"
			};

		_methodName270 = "countScienceAppAsManager";

		_methodParameterTypes270 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean"
			};

		_methodName271 = "retrieveListScienceAppAsCategory";

		_methodParameterTypes271 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "int", "int", "boolean"
			};

		_methodName272 = "countListScienceAppAsCategory";

		_methodParameterTypes272 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean"
			};

		_methodName273 = "retrieveListScienceAppByName";

		_methodParameterTypes273 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String",
				"boolean"
			};

		_methodName274 = "retrieveListScienceApp";

		_methodParameterTypes274 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Map", "java.lang.String",
				"int", "int", "boolean"
			};

		_methodName275 = "countListScienceApp";

		_methodParameterTypes275 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Map", "java.lang.String",
				"boolean"
			};

		_methodName280 = "getScienceAppReturnObject";

		_methodParameterTypes280 = new String[] { "long", "java.util.Locale" };

		_methodName281 = "deleteScienceAppRelation";

		_methodParameterTypes281 = new String[] { "long" };

		_methodName282 = "updateExeInfomaionScienceApp";

		_methodParameterTypes282 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map",
				"long"
			};

		_methodName283 = "copyScienceApp";

		_methodParameterTypes283 = new String[] {
				"com.liferay.portal.service.ServiceContext", "long",
				"java.lang.String"
			};

		_methodName284 = "addScienceAppFile";

		_methodParameterTypes284 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.InputStream"
			};

		_methodName285 = "retrieveListAppTest";

		_methodParameterTypes285 = new String[] { "java.util.Map" };

		_methodName286 = "countAppTest";

		_methodParameterTypes286 = new String[] { "long" };

		_methodName287 = "getMyAppListWithQna";

		_methodParameterTypes287 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName288 = "getListMyAppQna";

		_methodParameterTypes288 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName289 = "searchAssetEntryModelAPP";

		_methodParameterTypes289 = new String[] { "java.util.Map" };

		_methodName290 = "searchAssetEntryModelAPPCount";

		_methodParameterTypes290 = new String[] { "java.util.Map" };

		_methodName291 = "relatedAssetLinkedEntryScienceAPP";

		_methodParameterTypes291 = new String[] { "java.util.Map" };

		_methodName293 = "updateScienceApp";

		_methodParameterTypes293 = new String[] {
				"org.kisti.edison.science.model.ScienceApp", "int"
			};

		_methodName295 = "migrationScienceApp";

		_methodParameterTypes295 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName296 = "getMyAppListForProject";

		_methodParameterTypes296 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName297 = "getMyAppListForProjectCount";

		_methodParameterTypes297 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName298 = "getScienceApp";

		_methodParameterTypes298 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName299 = "countScienceApp";

		_methodParameterTypes299 = new String[] {
				"long", "long", "long", "java.util.Locale", "java.util.Map"
			};

		_methodName300 = "retrieveListScienceApp";

		_methodParameterTypes300 = new String[] {
				"long", "long", "long", "java.util.Locale", "java.util.Map",
				"int", "int", "boolean"
			};

		_methodName302 = "retrieveListByTemplateId";

		_methodParameterTypes302 = new String[] { "java.lang.String" };

		_methodName304 = "getScienceAppRatingsStats";

		_methodParameterTypes304 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName305 = "deleteScienceAppRatingsStats";

		_methodParameterTypes305 = new String[] { "long", "java.lang.String" };

		_methodName306 = "getScienceAppMyRatingsEntry";

		_methodParameterTypes306 = new String[] {
				"long", "java.lang.String", "com.liferay.portal.model.User"
			};

		_methodName307 = "setScienceAppMyRatingsEntry";

		_methodParameterTypes307 = new String[] {
				"long", "java.lang.String", "com.liferay.portal.model.User",
				"long"
			};

		_methodName308 = "deleteScienceAppMyRatingsEntry";

		_methodParameterTypes308 = new String[] {
				"long", "com.liferay.portal.model.User"
			};

		_methodName309 = "deleteScienceAppPaperItem";

		_methodParameterTypes309 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName310 = "getScienceAppPaperList";

		_methodParameterTypes310 = new String[] { "long" };

		_methodName311 = "getScienceAppExecuteStatistics";

		_methodParameterTypes311 = new String[] { "long" };

		_methodName312 = "getScienceAppHistoryList";

		_methodParameterTypes312 = new String[] { "java.util.Map" };

		_methodName313 = "getScienceAppReviewList";

		_methodParameterTypes313 = new String[] { "java.util.Map" };

		_methodName314 = "getSimulationUsersOfScienceApp";

		_methodParameterTypes314 = new String[] { "long" };

		_methodName315 = "countScienceAppByWorkflowId";

		_methodParameterTypes315 = new String[] { "long" };

		_methodName316 = "getScienceAppByWorkflowId";

		_methodParameterTypes316 = new String[] { "long" };

		_methodName317 = "getOrganizationRegisteredWithApp";

		_methodParameterTypes317 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return ScienceAppLocalServiceUtil.addScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return ScienceAppLocalServiceUtil.createScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return ScienceAppLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return ScienceAppLocalServiceUtil.fetchScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return ScienceAppLocalServiceUtil.fetchScienceAppByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return ScienceAppLocalServiceUtil.fetchScienceAppByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceApps(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName190.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes190, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName191.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes191, parameterTypes)) {
			ScienceAppLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName196.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes196, parameterTypes)) {
			return ScienceAppLocalServiceUtil.createScienceApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName197.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes197, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getTextEditorScienceApp();
		}

		if (_methodName198.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes198, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getFileEditorScienceApp();
		}

		if (_methodName199.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes199, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getStructuredEditorScienceApp();
		}

		if (_methodName200.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes200, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getEditorScienceApp((java.lang.String)arguments[0]);
		}

		if (_methodName201.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes201, parameterTypes)) {
			return ScienceAppLocalServiceUtil.addScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName202.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes202, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppInputPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName203.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes203, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppInputPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName204.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes204, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppLogPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName205.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes205, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppLogPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName206.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes206, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppOutputPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName207.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes207, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppOutputPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return ScienceAppLocalServiceUtil.verifyScienceAppName((java.lang.String)arguments[0]);
		}

		if (_methodName209.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes209, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existAppName((java.lang.String)arguments[0]);
		}

		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName211.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes211, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getLatestVersion((java.lang.String)arguments[0]);
		}

		if (_methodName212.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes212, parameterTypes)) {
			return ScienceAppLocalServiceUtil.verifyVersionNumber((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteAllScienceApps();

			return null;
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getAll();
		}

		if (_methodName217.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes217, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName218.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes218, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByStatus(((Integer)arguments[0]).intValue());
		}

		if (_methodName219.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes219, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByStage((java.lang.String)arguments[0]);
		}

		if (_methodName220.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes220, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName221.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes221, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName222.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes222, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdAppType(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName223.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes223, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdAppType(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName224.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes224, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorId(((Long)arguments[0]).longValue());
		}

		if (_methodName225.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes225, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName226.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes226, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByAuthorId(((Long)arguments[0]).longValue());
		}

		if (_methodName227.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes227, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByOpenLevel((java.lang.String)arguments[0]);
		}

		if (_methodName228.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes228, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByOpenLevel((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName229.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes229, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByManagerId(((Long)arguments[0]).longValue());
		}

		if (_methodName230.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes230, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByManagerId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName231.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes231, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByManagerId(((Long)arguments[0]).longValue());
		}

		if (_methodName232.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes232, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppManagerIds(((Long)arguments[0]).longValue());
		}

		if (_methodName233.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes233, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppManagerIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName234.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes234, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppManagers(((Long)arguments[0]).longValue());
		}

		if (_methodName235.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes235, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue());
		}

		if (_methodName236.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes236, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName237.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes237, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByCategoryId(((Long)arguments[0]).longValue());
		}

		if (_methodName238.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes238, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppCategoryIds(((Long)arguments[0]).longValue());
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppCategoryIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppCategories(((Long)arguments[0]).longValue());
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignScienceAppToCategories(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignScienceAppToCategory(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName243.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes243, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignManagersToScienceApp(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName244.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes244, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignManagerToScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName245.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes245, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppBinPath(((Long)arguments[0]).longValue());
		}

		if (_methodName246.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes246, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppSrcPath(((Long)arguments[0]).longValue());
		}

		if (_methodName247.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes247, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countAllScienceApps();
		}

		if (_methodName250.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes250, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existScienceAppPath((java.lang.String)arguments[0]);
		}

		if (_methodName251.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes251, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteScienceAppDir((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName252.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes252, parameterTypes)) {
			ScienceAppLocalServiceUtil.makeScienceAppDir((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName253.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes253, parameterTypes)) {
			return ScienceAppLocalServiceUtil.saveToScienceAppStorage((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(java.io.InputStream)arguments[2]);
		}

		if (_methodName254.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes254, parameterTypes)) {
			ScienceAppLocalServiceUtil.unzipScienceAppZipFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName256.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes256, parameterTypes)) {
			ScienceAppLocalServiceUtil.cleanScienceAppDir(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName257.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes257, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId2(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName258.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes258, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName259.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes259, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName260.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes260, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByScienceAppIdsAndLocale((long[])arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName261.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes261, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getLanguageSpecificAssetCategories((java.util.List<com.liferay.portlet.asset.model.AssetCategory>)arguments[0],
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName262.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes262, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByScienceAppIdsAndLocale((long[])arguments[0],
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName263.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes263, parameterTypes)) {
			return ScienceAppLocalServiceUtil.createScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1]);
		}

		if (_methodName264.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes264, parameterTypes)) {
			ScienceAppLocalServiceUtil.updateScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName265.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes265, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName266.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes266, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.lang.String)arguments[9], (java.lang.String)arguments[10]);
		}

		if (_methodName267.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes267, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5]);
		}

		if (_methodName268.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6]);
		}

		if (_methodName269.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppAsManager(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[6],
				(java.lang.String)arguments[7],
				((Boolean)arguments[8]).booleanValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue());
		}

		if (_methodName270.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes270, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppAsManager(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[6],
				(java.lang.String)arguments[7],
				((Boolean)arguments[8]).booleanValue());
		}

		if (_methodName271.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes271, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppAsCategory(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[6],
				(java.lang.String)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Boolean)arguments[10]).booleanValue());
		}

		if (_methodName272.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes272, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countListScienceAppAsCategory(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[6],
				(java.lang.String)arguments[7],
				((Boolean)arguments[8]).booleanValue());
		}

		if (_methodName273.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes273, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppByName(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceApp(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String[])arguments[3],
				(java.lang.String[])arguments[4],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[5],
				(java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Boolean)arguments[9]).booleanValue());
		}

		if (_methodName275.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes275, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countListScienceApp(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String[])arguments[3],
				(java.lang.String[])arguments[4],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[5],
				(java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName280.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes280, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppReturnObject(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName281.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes281, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteScienceAppRelation(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateExeInfomaionScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return ScienceAppLocalServiceUtil.copyScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			ScienceAppLocalServiceUtil.addScienceAppFile(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				(java.io.InputStream)arguments[4]);

			return null;
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListAppTest((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countAppTest(((Long)arguments[0]).longValue());
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListWithQna((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getListMyAppQna((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return ScienceAppLocalServiceUtil.searchAssetEntryModelAPP((java.util.Map)arguments[0]);
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return ScienceAppLocalServiceUtil.searchAssetEntryModelAPPCount((java.util.Map)arguments[0]);
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return ScienceAppLocalServiceUtil.relatedAssetLinkedEntryScienceAPP((java.util.Map)arguments[0]);
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				((Integer)arguments[1]).intValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return ScienceAppLocalServiceUtil.migrationScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListForProject((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListForProjectCount((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName298.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes298, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Locale)arguments[3],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[4]);
		}

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Locale)arguments[3],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListByTemplateId((java.lang.String)arguments[0]);
		}

		if (_methodName304.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes304, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppRatingsStats(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName305.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes305, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceAppRatingsStats(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName306.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes306, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppMyRatingsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.model.User)arguments[2]);
		}

		if (_methodName307.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes307, parameterTypes)) {
			return ScienceAppLocalServiceUtil.setScienceAppMyRatingsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.model.User)arguments[2],
				((Long)arguments[3]).longValue());
		}

		if (_methodName308.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes308, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceAppMyRatingsEntry(((Long)arguments[0]).longValue(),
				(com.liferay.portal.model.User)arguments[1]);
		}

		if (_methodName309.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes309, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceAppPaperItem(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName310.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes310, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppPaperList(((Long)arguments[0]).longValue());
		}

		if (_methodName311.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes311, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppExecuteStatistics(((Long)arguments[0]).longValue());
		}

		if (_methodName312.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes312, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppHistoryList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName313.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes313, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppReviewList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName314.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes314, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getSimulationUsersOfScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName315.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes315, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppByWorkflowId(((Long)arguments[0]).longValue());
		}

		if (_methodName316.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes316, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppByWorkflowId(((Long)arguments[0]).longValue());
		}

		if (_methodName317.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes317, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getOrganizationRegisteredWithApp();
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
	private String _methodName190;
	private String[] _methodParameterTypes190;
	private String _methodName191;
	private String[] _methodParameterTypes191;
	private String _methodName196;
	private String[] _methodParameterTypes196;
	private String _methodName197;
	private String[] _methodParameterTypes197;
	private String _methodName198;
	private String[] _methodParameterTypes198;
	private String _methodName199;
	private String[] _methodParameterTypes199;
	private String _methodName200;
	private String[] _methodParameterTypes200;
	private String _methodName201;
	private String[] _methodParameterTypes201;
	private String _methodName202;
	private String[] _methodParameterTypes202;
	private String _methodName203;
	private String[] _methodParameterTypes203;
	private String _methodName204;
	private String[] _methodParameterTypes204;
	private String _methodName205;
	private String[] _methodParameterTypes205;
	private String _methodName206;
	private String[] _methodParameterTypes206;
	private String _methodName207;
	private String[] _methodParameterTypes207;
	private String _methodName208;
	private String[] _methodParameterTypes208;
	private String _methodName209;
	private String[] _methodParameterTypes209;
	private String _methodName210;
	private String[] _methodParameterTypes210;
	private String _methodName211;
	private String[] _methodParameterTypes211;
	private String _methodName212;
	private String[] _methodParameterTypes212;
	private String _methodName213;
	private String[] _methodParameterTypes213;
	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
	private String _methodName217;
	private String[] _methodParameterTypes217;
	private String _methodName218;
	private String[] _methodParameterTypes218;
	private String _methodName219;
	private String[] _methodParameterTypes219;
	private String _methodName220;
	private String[] _methodParameterTypes220;
	private String _methodName221;
	private String[] _methodParameterTypes221;
	private String _methodName222;
	private String[] _methodParameterTypes222;
	private String _methodName223;
	private String[] _methodParameterTypes223;
	private String _methodName224;
	private String[] _methodParameterTypes224;
	private String _methodName225;
	private String[] _methodParameterTypes225;
	private String _methodName226;
	private String[] _methodParameterTypes226;
	private String _methodName227;
	private String[] _methodParameterTypes227;
	private String _methodName228;
	private String[] _methodParameterTypes228;
	private String _methodName229;
	private String[] _methodParameterTypes229;
	private String _methodName230;
	private String[] _methodParameterTypes230;
	private String _methodName231;
	private String[] _methodParameterTypes231;
	private String _methodName232;
	private String[] _methodParameterTypes232;
	private String _methodName233;
	private String[] _methodParameterTypes233;
	private String _methodName234;
	private String[] _methodParameterTypes234;
	private String _methodName235;
	private String[] _methodParameterTypes235;
	private String _methodName236;
	private String[] _methodParameterTypes236;
	private String _methodName237;
	private String[] _methodParameterTypes237;
	private String _methodName238;
	private String[] _methodParameterTypes238;
	private String _methodName239;
	private String[] _methodParameterTypes239;
	private String _methodName240;
	private String[] _methodParameterTypes240;
	private String _methodName241;
	private String[] _methodParameterTypes241;
	private String _methodName242;
	private String[] _methodParameterTypes242;
	private String _methodName243;
	private String[] _methodParameterTypes243;
	private String _methodName244;
	private String[] _methodParameterTypes244;
	private String _methodName245;
	private String[] _methodParameterTypes245;
	private String _methodName246;
	private String[] _methodParameterTypes246;
	private String _methodName247;
	private String[] _methodParameterTypes247;
	private String _methodName250;
	private String[] _methodParameterTypes250;
	private String _methodName251;
	private String[] _methodParameterTypes251;
	private String _methodName252;
	private String[] _methodParameterTypes252;
	private String _methodName253;
	private String[] _methodParameterTypes253;
	private String _methodName254;
	private String[] _methodParameterTypes254;
	private String _methodName256;
	private String[] _methodParameterTypes256;
	private String _methodName257;
	private String[] _methodParameterTypes257;
	private String _methodName258;
	private String[] _methodParameterTypes258;
	private String _methodName259;
	private String[] _methodParameterTypes259;
	private String _methodName260;
	private String[] _methodParameterTypes260;
	private String _methodName261;
	private String[] _methodParameterTypes261;
	private String _methodName262;
	private String[] _methodParameterTypes262;
	private String _methodName263;
	private String[] _methodParameterTypes263;
	private String _methodName264;
	private String[] _methodParameterTypes264;
	private String _methodName265;
	private String[] _methodParameterTypes265;
	private String _methodName266;
	private String[] _methodParameterTypes266;
	private String _methodName267;
	private String[] _methodParameterTypes267;
	private String _methodName268;
	private String[] _methodParameterTypes268;
	private String _methodName269;
	private String[] _methodParameterTypes269;
	private String _methodName270;
	private String[] _methodParameterTypes270;
	private String _methodName271;
	private String[] _methodParameterTypes271;
	private String _methodName272;
	private String[] _methodParameterTypes272;
	private String _methodName273;
	private String[] _methodParameterTypes273;
	private String _methodName274;
	private String[] _methodParameterTypes274;
	private String _methodName275;
	private String[] _methodParameterTypes275;
	private String _methodName280;
	private String[] _methodParameterTypes280;
	private String _methodName281;
	private String[] _methodParameterTypes281;
	private String _methodName282;
	private String[] _methodParameterTypes282;
	private String _methodName283;
	private String[] _methodParameterTypes283;
	private String _methodName284;
	private String[] _methodParameterTypes284;
	private String _methodName285;
	private String[] _methodParameterTypes285;
	private String _methodName286;
	private String[] _methodParameterTypes286;
	private String _methodName287;
	private String[] _methodParameterTypes287;
	private String _methodName288;
	private String[] _methodParameterTypes288;
	private String _methodName289;
	private String[] _methodParameterTypes289;
	private String _methodName290;
	private String[] _methodParameterTypes290;
	private String _methodName291;
	private String[] _methodParameterTypes291;
	private String _methodName293;
	private String[] _methodParameterTypes293;
	private String _methodName295;
	private String[] _methodParameterTypes295;
	private String _methodName296;
	private String[] _methodParameterTypes296;
	private String _methodName297;
	private String[] _methodParameterTypes297;
	private String _methodName298;
	private String[] _methodParameterTypes298;
	private String _methodName299;
	private String[] _methodParameterTypes299;
	private String _methodName300;
	private String[] _methodParameterTypes300;
	private String _methodName302;
	private String[] _methodParameterTypes302;
	private String _methodName304;
	private String[] _methodParameterTypes304;
	private String _methodName305;
	private String[] _methodParameterTypes305;
	private String _methodName306;
	private String[] _methodParameterTypes306;
	private String _methodName307;
	private String[] _methodParameterTypes307;
	private String _methodName308;
	private String[] _methodParameterTypes308;
	private String _methodName309;
	private String[] _methodParameterTypes309;
	private String _methodName310;
	private String[] _methodParameterTypes310;
	private String _methodName311;
	private String[] _methodParameterTypes311;
	private String _methodName312;
	private String[] _methodParameterTypes312;
	private String _methodName313;
	private String[] _methodParameterTypes313;
	private String _methodName314;
	private String[] _methodParameterTypes314;
	private String _methodName315;
	private String[] _methodParameterTypes315;
	private String _methodName316;
	private String[] _methodParameterTypes316;
	private String _methodName317;
	private String[] _methodParameterTypes317;
}