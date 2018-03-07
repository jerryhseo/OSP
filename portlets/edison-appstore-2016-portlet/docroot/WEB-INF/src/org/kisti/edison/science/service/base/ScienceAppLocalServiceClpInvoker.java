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

		_methodName178 = "getBeanIdentifier";

		_methodParameterTypes178 = new String[] {  };

		_methodName179 = "setBeanIdentifier";

		_methodParameterTypes179 = new String[] { "java.lang.String" };

		_methodName184 = "createScienceApp";

		_methodParameterTypes184 = new String[] {
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName185 = "getTextEditorScienceApp";

		_methodParameterTypes185 = new String[] {  };

		_methodName186 = "getFileEditorScienceApp";

		_methodParameterTypes186 = new String[] {  };

		_methodName187 = "getStructuredEditorScienceApp";

		_methodParameterTypes187 = new String[] {  };

		_methodName188 = "getEditorScienceApp";

		_methodParameterTypes188 = new String[] { "java.lang.String" };

		_methodName189 = "addScienceApp";

		_methodParameterTypes189 = new String[] {
				"org.kisti.edison.science.model.ScienceApp",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName190 = "setScienceAppInputPorts";

		_methodParameterTypes190 = new String[] { "long", "java.lang.String" };

		_methodName191 = "getScienceAppInputPorts";

		_methodParameterTypes191 = new String[] { "long" };

		_methodName192 = "setScienceAppLogPorts";

		_methodParameterTypes192 = new String[] { "long", "java.lang.String" };

		_methodName193 = "getScienceAppLogPorts";

		_methodParameterTypes193 = new String[] { "long" };

		_methodName194 = "setScienceAppOutputPorts";

		_methodParameterTypes194 = new String[] { "long", "java.lang.String" };

		_methodName195 = "getScienceAppOutputPorts";

		_methodParameterTypes195 = new String[] { "long" };

		_methodName196 = "verifyScienceAppName";

		_methodParameterTypes196 = new String[] { "java.lang.String" };

		_methodName197 = "existAppName";

		_methodParameterTypes197 = new String[] { "java.lang.String" };

		_methodName198 = "existApp";

		_methodParameterTypes198 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName199 = "getLatestVersion";

		_methodParameterTypes199 = new String[] { "java.lang.String" };

		_methodName200 = "verifyVersionNumber";

		_methodParameterTypes200 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName201 = "deleteScienceApp";

		_methodParameterTypes201 = new String[] { "long" };

		_methodName202 = "deleteScienceApp";

		_methodParameterTypes202 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName203 = "deleteAllScienceApps";

		_methodParameterTypes203 = new String[] {  };

		_methodName204 = "getAll";

		_methodParameterTypes204 = new String[] {  };

		_methodName205 = "updateScienceApp";

		_methodParameterTypes205 = new String[] {
				"org.kisti.edison.science.model.ScienceApp",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName206 = "getScienceAppListByStatus";

		_methodParameterTypes206 = new String[] { "int" };

		_methodName207 = "getScienceAppListByStage";

		_methodParameterTypes207 = new String[] { "java.lang.String" };

		_methodName208 = "getScienceAppListByAuthorIdStatus";

		_methodParameterTypes208 = new String[] { "long", "int" };

		_methodName209 = "getScienceAppListByAuthorIdStatus";

		_methodParameterTypes209 = new String[] { "long", "int", "int", "int" };

		_methodName210 = "getScienceAppListByAuthorIdAppType";

		_methodParameterTypes210 = new String[] { "long", "java.lang.String" };

		_methodName211 = "getScienceAppListByAuthorIdAppType";

		_methodParameterTypes211 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName212 = "getScienceAppListByAuthorId";

		_methodParameterTypes212 = new String[] { "long" };

		_methodName213 = "getScienceAppListByAuthorId";

		_methodParameterTypes213 = new String[] { "long", "int", "int" };

		_methodName214 = "countScienceAppsByAuthorId";

		_methodParameterTypes214 = new String[] { "long" };

		_methodName215 = "getScienceAppListByOpenLevel";

		_methodParameterTypes215 = new String[] { "java.lang.String" };

		_methodName216 = "getScienceAppListByOpenLevel";

		_methodParameterTypes216 = new String[] { "java.lang.String", "int", "int" };

		_methodName217 = "getScienceAppListByManagerId";

		_methodParameterTypes217 = new String[] { "long" };

		_methodName218 = "getScienceAppListByManagerId";

		_methodParameterTypes218 = new String[] { "long", "int", "int" };

		_methodName219 = "countScienceAppsByManagerId";

		_methodParameterTypes219 = new String[] { "long" };

		_methodName220 = "getScienceAppManagerIds";

		_methodParameterTypes220 = new String[] { "long" };

		_methodName221 = "getScienceAppManagerIds";

		_methodParameterTypes221 = new String[] { "long", "int", "int" };

		_methodName222 = "countScienceAppManagers";

		_methodParameterTypes222 = new String[] { "long" };

		_methodName223 = "getScienceAppListByCategoryId";

		_methodParameterTypes223 = new String[] { "long" };

		_methodName224 = "getScienceAppListByCategoryId";

		_methodParameterTypes224 = new String[] { "long", "int", "int" };

		_methodName225 = "countScienceAppsByCategoryId";

		_methodParameterTypes225 = new String[] { "long" };

		_methodName226 = "getScienceAppCategoryIds";

		_methodParameterTypes226 = new String[] { "long" };

		_methodName227 = "getScienceAppCategoryIds";

		_methodParameterTypes227 = new String[] { "long", "int", "int" };

		_methodName228 = "countScienceAppCategories";

		_methodParameterTypes228 = new String[] { "long" };

		_methodName229 = "assignScienceAppToCategories";

		_methodParameterTypes229 = new String[] { "long", "long[][]" };

		_methodName230 = "assignScienceAppToCategory";

		_methodParameterTypes230 = new String[] { "long", "long" };

		_methodName231 = "assignManagersToScienceApp";

		_methodParameterTypes231 = new String[] { "long", "long[][]" };

		_methodName232 = "assignManagerToScienceApp";

		_methodParameterTypes232 = new String[] { "long", "long" };

		_methodName233 = "getScienceAppBinPath";

		_methodParameterTypes233 = new String[] { "long" };

		_methodName234 = "getScienceAppSrcPath";

		_methodParameterTypes234 = new String[] { "long" };

		_methodName235 = "countAllScienceApps";

		_methodParameterTypes235 = new String[] {  };

		_methodName238 = "existScienceAppPath";

		_methodParameterTypes238 = new String[] { "java.lang.String" };

		_methodName239 = "deleteScienceAppDir";

		_methodParameterTypes239 = new String[] { "java.lang.String" };

		_methodName240 = "makeScienceAppDir";

		_methodParameterTypes240 = new String[] { "java.lang.String" };

		_methodName241 = "saveToScienceAppStorage";

		_methodParameterTypes241 = new String[] {
				"java.lang.String", "java.lang.String", "java.io.InputStream"
			};

		_methodName242 = "unzipScienceAppZipFile";

		_methodParameterTypes242 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName244 = "cleanScienceAppDir";

		_methodParameterTypes244 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName245 = "getScienceAppListByCategoryId2";

		_methodParameterTypes245 = new String[] { "long", "java.util.Locale" };

		_methodName246 = "getScienceAppListByCategoryId";

		_methodParameterTypes246 = new String[] { "long", "java.util.Locale" };

		_methodName247 = "getScienceAppListByCategoryId";

		_methodParameterTypes247 = new String[] {
				"long", "java.util.Locale", "int", "int"
			};

		_methodName248 = "getScienceAppListByScienceAppIdsAndLocale";

		_methodParameterTypes248 = new String[] { "long[][]", "java.util.Locale" };

		_methodName249 = "getLanguageSpecificAssetCategories";

		_methodParameterTypes249 = new String[] {
				"java.util.List", "long", "java.util.Locale"
			};

		_methodName250 = "getScienceAppListByScienceAppIdsAndLocale";

		_methodParameterTypes250 = new String[] {
				"long[][]", "java.util.Locale", "int", "int"
			};

		_methodName251 = "createScienceApp";

		_methodParameterTypes251 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map"
			};

		_methodName252 = "updateScienceApp";

		_methodParameterTypes252 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map",
				"long"
			};

		_methodName253 = "retrieveListScienceAppFromExplore";

		_methodParameterTypes253 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String", "int", "int"
			};

		_methodName254 = "countScienceAppFromExplore";

		_methodParameterTypes254 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String[][]",
				"long[][]", "java.lang.String"
			};

		_methodName255 = "retrieveListScienceAppAsManager";

		_methodParameterTypes255 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean", "int", "int"
			};

		_methodName256 = "countScienceAppAsManager";

		_methodParameterTypes256 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean"
			};

		_methodName257 = "retrieveListScienceAppAsCategory";

		_methodParameterTypes257 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "int", "int", "boolean"
			};

		_methodName258 = "countListScienceAppAsCategory";

		_methodParameterTypes258 = new String[] {
				"long", "long", "java.util.Locale", "long",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Map",
				"java.lang.String", "boolean"
			};

		_methodName259 = "retrieveListScienceAppByName";

		_methodParameterTypes259 = new String[] {
				"long", "long", "java.util.Locale", "java.lang.String",
				"boolean"
			};

		_methodName260 = "retrieveListScienceApp";

		_methodParameterTypes260 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Map", "java.lang.String",
				"int", "int", "boolean"
			};

		_methodName261 = "countListScienceApp";

		_methodParameterTypes261 = new String[] {
				"long", "java.util.Locale", "long", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Map", "java.lang.String",
				"boolean"
			};

		_methodName266 = "getScienceAppReturnObject";

		_methodParameterTypes266 = new String[] { "long", "java.util.Locale" };

		_methodName267 = "deleteScienceAppRelation";

		_methodParameterTypes267 = new String[] { "long" };

		_methodName268 = "updateExeInfomaionScienceApp";

		_methodParameterTypes268 = new String[] {
				"com.liferay.portal.service.ServiceContext", "java.util.Map",
				"long"
			};

		_methodName269 = "copyScienceApp";

		_methodParameterTypes269 = new String[] {
				"com.liferay.portal.service.ServiceContext", "long"
			};

		_methodName270 = "addScienceAppFile";

		_methodParameterTypes270 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.InputStream"
			};

		_methodName271 = "retrieveListAppTest";

		_methodParameterTypes271 = new String[] { "java.util.Map" };

		_methodName272 = "countAppTest";

		_methodParameterTypes272 = new String[] { "long" };

		_methodName273 = "getMyAppListWithQna";

		_methodParameterTypes273 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName274 = "getListMyAppQna";

		_methodParameterTypes274 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName275 = "searchAssetEntryModelAPP";

		_methodParameterTypes275 = new String[] { "java.util.Map" };

		_methodName276 = "searchAssetEntryModelAPPCount";

		_methodParameterTypes276 = new String[] { "java.util.Map" };

		_methodName277 = "relatedAssetLinkedEntryScienceAPP";

		_methodParameterTypes277 = new String[] { "java.util.Map" };

		_methodName279 = "updateScienceApp";

		_methodParameterTypes279 = new String[] {
				"org.kisti.edison.science.model.ScienceApp", "int"
			};

		_methodName281 = "migrationScienceApp";

		_methodParameterTypes281 = new String[] {
				"org.kisti.edison.science.model.ScienceApp"
			};

		_methodName282 = "getMyAppListForProject";

		_methodParameterTypes282 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName283 = "getMyAppListForProjectCount";

		_methodParameterTypes283 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName284 = "getScienceApp";

		_methodParameterTypes284 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName285 = "countScienceApp";

		_methodParameterTypes285 = new String[] {
				"long", "long", "long", "java.util.Locale", "java.util.Map"
			};

		_methodName286 = "retrieveListScienceApp";

		_methodParameterTypes286 = new String[] {
				"long", "long", "long", "java.util.Locale", "java.util.Map",
				"int", "int", "boolean"
			};
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

		if (_methodName178.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes178, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName179.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes179, parameterTypes)) {
			ScienceAppLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName184.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes184, parameterTypes)) {
			return ScienceAppLocalServiceUtil.createScienceApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName185.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes185, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getTextEditorScienceApp();
		}

		if (_methodName186.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes186, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getFileEditorScienceApp();
		}

		if (_methodName187.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes187, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getStructuredEditorScienceApp();
		}

		if (_methodName188.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes188, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getEditorScienceApp((java.lang.String)arguments[0]);
		}

		if (_methodName189.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes189, parameterTypes)) {
			return ScienceAppLocalServiceUtil.addScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName190.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes190, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppInputPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName191.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes191, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppInputPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName192.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes192, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppLogPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName193.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes193, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppLogPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName194.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes194, parameterTypes)) {
			ScienceAppLocalServiceUtil.setScienceAppOutputPorts(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName195.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes195, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppOutputPorts(((Long)arguments[0]).longValue());
		}

		if (_methodName196.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes196, parameterTypes)) {
			return ScienceAppLocalServiceUtil.verifyScienceAppName((java.lang.String)arguments[0]);
		}

		if (_methodName197.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes197, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existAppName((java.lang.String)arguments[0]);
		}

		if (_methodName198.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes198, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName199.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes199, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getLatestVersion((java.lang.String)arguments[0]);
		}

		if (_methodName200.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes200, parameterTypes)) {
			return ScienceAppLocalServiceUtil.verifyVersionNumber((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName201.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes201, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp(((Long)arguments[0]).longValue());
		}

		if (_methodName202.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes202, parameterTypes)) {
			return ScienceAppLocalServiceUtil.deleteScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName203.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes203, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteAllScienceApps();

			return null;
		}

		if (_methodName204.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes204, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getAll();
		}

		if (_methodName205.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes205, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName206.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes206, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByStatus(((Integer)arguments[0]).intValue());
		}

		if (_methodName207.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes207, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByStage((java.lang.String)arguments[0]);
		}

		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName209.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes209, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdAppType(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName211.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes211, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorIdAppType(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName212.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes212, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorId(((Long)arguments[0]).longValue());
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByAuthorId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByAuthorId(((Long)arguments[0]).longValue());
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByOpenLevel((java.lang.String)arguments[0]);
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByOpenLevel((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName217.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes217, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByManagerId(((Long)arguments[0]).longValue());
		}

		if (_methodName218.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes218, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByManagerId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName219.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes219, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByManagerId(((Long)arguments[0]).longValue());
		}

		if (_methodName220.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes220, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppManagerIds(((Long)arguments[0]).longValue());
		}

		if (_methodName221.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes221, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppManagerIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName222.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes222, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppManagers(((Long)arguments[0]).longValue());
		}

		if (_methodName223.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes223, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue());
		}

		if (_methodName224.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes224, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName225.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes225, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppsByCategoryId(((Long)arguments[0]).longValue());
		}

		if (_methodName226.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes226, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppCategoryIds(((Long)arguments[0]).longValue());
		}

		if (_methodName227.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes227, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppCategoryIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName228.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes228, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppCategories(((Long)arguments[0]).longValue());
		}

		if (_methodName229.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes229, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignScienceAppToCategories(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName230.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes230, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignScienceAppToCategory(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName231.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes231, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignManagersToScienceApp(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName232.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes232, parameterTypes)) {
			ScienceAppLocalServiceUtil.assignManagerToScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName233.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes233, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppBinPath(((Long)arguments[0]).longValue());
		}

		if (_methodName234.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes234, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppSrcPath(((Long)arguments[0]).longValue());
		}

		if (_methodName235.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes235, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countAllScienceApps();
		}

		if (_methodName238.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes238, parameterTypes)) {
			return ScienceAppLocalServiceUtil.existScienceAppPath((java.lang.String)arguments[0]);
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteScienceAppDir((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
			ScienceAppLocalServiceUtil.makeScienceAppDir((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			return ScienceAppLocalServiceUtil.saveToScienceAppStorage((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(java.io.InputStream)arguments[2]);
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			ScienceAppLocalServiceUtil.unzipScienceAppZipFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName244.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes244, parameterTypes)) {
			ScienceAppLocalServiceUtil.cleanScienceAppDir(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName245.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes245, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId2(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName246.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes246, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName247.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes247, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByCategoryId(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName248.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes248, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByScienceAppIdsAndLocale((long[])arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName249.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes249, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getLanguageSpecificAssetCategories((java.util.List<com.liferay.portlet.asset.model.AssetCategory>)arguments[0],
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName250.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes250, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppListByScienceAppIdsAndLocale((long[])arguments[0],
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName251.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes251, parameterTypes)) {
			return ScienceAppLocalServiceUtil.createScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1]);
		}

		if (_methodName252.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes252, parameterTypes)) {
			ScienceAppLocalServiceUtil.updateScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName253.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes253, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName254.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes254, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceAppFromExplore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				(java.lang.String[])arguments[3], (long[])arguments[4],
				(java.lang.String)arguments[5]);
		}

		if (_methodName255.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes255, parameterTypes)) {
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

		if (_methodName256.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes256, parameterTypes)) {
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

		if (_methodName257.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes257, parameterTypes)) {
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

		if (_methodName258.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes258, parameterTypes)) {
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

		if (_methodName259.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes259, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceAppByName(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName260.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes260, parameterTypes)) {
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

		if (_methodName261.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes261, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countListScienceApp(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String[])arguments[3],
				(java.lang.String[])arguments[4],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[5],
				(java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName266.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes266, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceAppReturnObject(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName267.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes267, parameterTypes)) {
			ScienceAppLocalServiceUtil.deleteScienceAppRelation(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName268.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateExeInfomaionScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				(java.util.Map)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName269.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
			return ScienceAppLocalServiceUtil.copyScienceApp((com.liferay.portal.service.ServiceContext)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName270.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes270, parameterTypes)) {
			ScienceAppLocalServiceUtil.addScienceAppFile(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				(java.io.InputStream)arguments[4]);

			return null;
		}

		if (_methodName271.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes271, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListAppTest((java.util.Map<java.lang.String, java.lang.Object>)arguments[0]);
		}

		if (_methodName272.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes272, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countAppTest(((Long)arguments[0]).longValue());
		}

		if (_methodName273.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes273, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListWithQna((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getListMyAppQna((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName275.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes275, parameterTypes)) {
			return ScienceAppLocalServiceUtil.searchAssetEntryModelAPP((java.util.Map)arguments[0]);
		}

		if (_methodName276.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes276, parameterTypes)) {
			return ScienceAppLocalServiceUtil.searchAssetEntryModelAPPCount((java.util.Map)arguments[0]);
		}

		if (_methodName277.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes277, parameterTypes)) {
			return ScienceAppLocalServiceUtil.relatedAssetLinkedEntryScienceAPP((java.util.Map)arguments[0]);
		}

		if (_methodName279.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes279, parameterTypes)) {
			return ScienceAppLocalServiceUtil.updateScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0],
				((Integer)arguments[1]).intValue());
		}

		if (_methodName281.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes281, parameterTypes)) {
			return ScienceAppLocalServiceUtil.migrationScienceApp((org.kisti.edison.science.model.ScienceApp)arguments[0]);
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListForProject((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getMyAppListForProjectCount((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return ScienceAppLocalServiceUtil.getScienceApp((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return ScienceAppLocalServiceUtil.countScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Locale)arguments[3],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[4]);
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return ScienceAppLocalServiceUtil.retrieveListScienceApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Locale)arguments[3],
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Boolean)arguments[7]).booleanValue());
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
	private String _methodName178;
	private String[] _methodParameterTypes178;
	private String _methodName179;
	private String[] _methodParameterTypes179;
	private String _methodName184;
	private String[] _methodParameterTypes184;
	private String _methodName185;
	private String[] _methodParameterTypes185;
	private String _methodName186;
	private String[] _methodParameterTypes186;
	private String _methodName187;
	private String[] _methodParameterTypes187;
	private String _methodName188;
	private String[] _methodParameterTypes188;
	private String _methodName189;
	private String[] _methodParameterTypes189;
	private String _methodName190;
	private String[] _methodParameterTypes190;
	private String _methodName191;
	private String[] _methodParameterTypes191;
	private String _methodName192;
	private String[] _methodParameterTypes192;
	private String _methodName193;
	private String[] _methodParameterTypes193;
	private String _methodName194;
	private String[] _methodParameterTypes194;
	private String _methodName195;
	private String[] _methodParameterTypes195;
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
	private String _methodName244;
	private String[] _methodParameterTypes244;
	private String _methodName245;
	private String[] _methodParameterTypes245;
	private String _methodName246;
	private String[] _methodParameterTypes246;
	private String _methodName247;
	private String[] _methodParameterTypes247;
	private String _methodName248;
	private String[] _methodParameterTypes248;
	private String _methodName249;
	private String[] _methodParameterTypes249;
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
	private String _methodName255;
	private String[] _methodParameterTypes255;
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
	private String _methodName276;
	private String[] _methodParameterTypes276;
	private String _methodName277;
	private String[] _methodParameterTypes277;
	private String _methodName279;
	private String[] _methodParameterTypes279;
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
}