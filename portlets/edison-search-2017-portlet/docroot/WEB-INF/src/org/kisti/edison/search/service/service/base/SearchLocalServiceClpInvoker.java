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

package org.kisti.edison.search.service.service.base;

import org.kisti.edison.search.service.service.SearchLocalServiceUtil;

import java.util.Arrays;

/**
 * @author yjpark
 * @generated
 */
public class SearchLocalServiceClpInvoker {
	public SearchLocalServiceClpInvoker() {
		_methodName0 = "addSearch";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName1 = "createSearch";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSearch";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSearch";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.search.service.model.Search"
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

		_methodName10 = "fetchSearch";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSearch";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSearchs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSearchsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSearch";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName42 = "getRootSiteAssetCategries";

		_methodParameterTypes42 = new String[] { "long", "long" };

		_methodName43 = "getSiteAssetCategoriesByParentId";

		_methodParameterTypes43 = new String[] { "long", "long", "long" };

		_methodName45 = "getLv1Categories";

		_methodParameterTypes45 = new String[] {
				"long", "long", "java.util.Locale"
			};

		_methodName46 = "getCategoriesJsonString";

		_methodParameterTypes46 = new String[] {
				"long", "long", "java.util.Locale"
			};

		_methodName47 = "getCategories";

		_methodParameterTypes47 = new String[] {
				"long", "long", "java.util.Locale"
			};

		_methodName49 = "assetCategoryToJstreeModel";

		_methodParameterTypes49 = new String[] {
				"java.util.List", "java.util.Locale"
			};

		_methodName50 = "assetCategoryToJstreeModel";

		_methodParameterTypes50 = new String[] {
				"com.liferay.portlet.asset.model.AssetCategory",
				"java.util.Locale"
			};

		_methodName51 = "appSearch";

		_methodParameterTypes51 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName52 = "appSearch";

		_methodParameterTypes52 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition",
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName53 = "contentSearch";

		_methodParameterTypes53 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName54 = "contentSearch";

		_methodParameterTypes54 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition",
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName55 = "projectSearch";

		_methodParameterTypes55 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName56 = "projectSearch";

		_methodParameterTypes56 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition",
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName57 = "dataSearch";

		_methodParameterTypes57 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName58 = "dataSearch";

		_methodParameterTypes58 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition",
				"org.kisti.edison.search.service.model.Search"
			};

		_methodName59 = "totalSearch";

		_methodParameterTypes59 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName60 = "getCategoryIdArrays";

		_methodParameterTypes60 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};

		_methodName61 = "getCategoryIds";

		_methodParameterTypes61 = new String[] {
				"org.kisti.edison.search.service.model.SearchCondition"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SearchLocalServiceUtil.addSearch((org.kisti.edison.search.service.model.Search)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SearchLocalServiceUtil.createSearch(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SearchLocalServiceUtil.deleteSearch(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SearchLocalServiceUtil.deleteSearch((org.kisti.edison.search.service.model.Search)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SearchLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SearchLocalServiceUtil.fetchSearch(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SearchLocalServiceUtil.getSearch(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SearchLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SearchLocalServiceUtil.getSearchs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SearchLocalServiceUtil.getSearchsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SearchLocalServiceUtil.updateSearch((org.kisti.edison.search.service.model.Search)arguments[0]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SearchLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SearchLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SearchLocalServiceUtil.getRootSiteAssetCategries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SearchLocalServiceUtil.getSiteAssetCategoriesByParentId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return SearchLocalServiceUtil.getLv1Categories(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SearchLocalServiceUtil.getCategoriesJsonString(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return SearchLocalServiceUtil.getCategories(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return SearchLocalServiceUtil.assetCategoryToJstreeModel((java.util.List<com.liferay.portlet.asset.model.AssetCategory>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return SearchLocalServiceUtil.assetCategoryToJstreeModel((com.liferay.portlet.asset.model.AssetCategory)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return SearchLocalServiceUtil.appSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SearchLocalServiceUtil.appSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0],
				(org.kisti.edison.search.service.model.Search)arguments[1]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return SearchLocalServiceUtil.contentSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return SearchLocalServiceUtil.contentSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0],
				(org.kisti.edison.search.service.model.Search)arguments[1]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return SearchLocalServiceUtil.projectSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return SearchLocalServiceUtil.projectSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0],
				(org.kisti.edison.search.service.model.Search)arguments[1]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return SearchLocalServiceUtil.dataSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return SearchLocalServiceUtil.dataSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0],
				(org.kisti.edison.search.service.model.Search)arguments[1]);
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return SearchLocalServiceUtil.totalSearch((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return SearchLocalServiceUtil.getCategoryIdArrays((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return SearchLocalServiceUtil.getCategoryIds((org.kisti.edison.search.service.model.SearchCondition)arguments[0]);
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
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
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
}