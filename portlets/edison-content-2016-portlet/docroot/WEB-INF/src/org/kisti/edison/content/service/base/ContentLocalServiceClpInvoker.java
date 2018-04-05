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

package org.kisti.edison.content.service.base;

import org.kisti.edison.content.service.ContentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class ContentLocalServiceClpInvoker {
	public ContentLocalServiceClpInvoker() {
		_methodName0 = "addContent";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.content.model.Content"
			};

		_methodName1 = "createContent";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteContent";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteContent";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.content.model.Content"
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

		_methodName10 = "fetchContent";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getContent";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getContents";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getContentsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateContent";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.content.model.Content"
			};

		_methodName58 = "getBeanIdentifier";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "setBeanIdentifier";

		_methodParameterTypes59 = new String[] { "java.lang.String" };

		_methodName64 = "retrieveMapContent";

		_methodParameterTypes64 = new String[] {
				"long", "java.util.Locale", "long"
			};

		_methodName65 = "retrieveListContent";

		_methodParameterTypes65 = new String[] {
				"long", "long", "java.lang.String", "long[][]", "int", "int",
				"java.util.Locale", "boolean"
			};

		_methodName66 = "retrievetListClassNote";

		_methodParameterTypes66 = new String[] {
				"long[][]", "java.lang.String", "int", "int", "java.util.Locale"
			};

		_methodName67 = "retrieveCountClassNote";

		_methodParameterTypes67 = new String[] {
				"long[][]", "java.lang.String", "java.util.Locale"
			};

		_methodName68 = "retrieveListRelatedClassNote";

		_methodParameterTypes68 = new String[] {
				"java.util.List", "java.util.Locale"
			};

		_methodName69 = "retrieveListContent";

		_methodParameterTypes69 = new String[] {
				"long[][]", "java.lang.String", "long[][]", "int", "int",
				"java.util.Locale", "boolean", "boolean"
			};

		_methodName70 = "retrieveListContent";

		_methodParameterTypes70 = new String[] {
				"long[][]", "java.lang.String", "long[][]", "int", "int",
				"java.util.Locale", "boolean", "boolean", "java.lang.String",
				"java.lang.String"
			};

		_methodName71 = "retrieveCountContent";

		_methodParameterTypes71 = new String[] {
				"long", "long", "java.lang.String", "long[][]",
				"java.lang.String", "boolean"
			};

		_methodName72 = "retrieveCountContent";

		_methodParameterTypes72 = new String[] {
				"long[][]", "java.lang.String", "long[][]", "java.lang.String",
				"boolean", "boolean"
			};

		_methodName73 = "retrieveListUserContent";

		_methodParameterTypes73 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"java.util.Locale", "long", "long"
			};

		_methodName74 = "retrieveCountUserContent";

		_methodParameterTypes74 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"long", "long"
			};

		_methodName75 = "createContent";

		_methodParameterTypes75 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long", "long",
				"java.util.Map"
			};

		_methodName76 = "updateContent";

		_methodParameterTypes76 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long", "long",
				"java.util.Map"
			};

		_methodName77 = "deleteContent";

		_methodParameterTypes77 = new String[] { "long", "long", "java.util.Map" };

		_methodName78 = "relatedAssetLinkedEntryContent";

		_methodParameterTypes78 = new String[] { "java.util.Map" };

		_methodName79 = "searchAssetEntryModelContent";

		_methodParameterTypes79 = new String[] { "java.util.Map" };

		_methodName80 = "searchAssetEntryModelContentCount";

		_methodParameterTypes80 = new String[] { "java.util.Map" };

		_methodName81 = "contentAddAssetEntry";

		_methodParameterTypes81 = new String[] {
				"long", "long", "org.kisti.edison.content.model.Content"
			};

		_methodName82 = "updateContentInsertId";

		_methodParameterTypes82 = new String[] {
				"long", "long", "long", "java.lang.String", "long"
			};

		_methodName83 = "makeCategoryEntryList";

		_methodParameterTypes83 = new String[] { "long", "long" };

		_methodName84 = "getContentDatailListForProjectStatistics";

		_methodParameterTypes84 = new String[] {
				"java.lang.String", "long", "long", "java.lang.String",
				"java.util.Locale"
			};

		_methodName85 = "getContentForProjectList";

		_methodParameterTypes85 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "java.util.Locale"
			};

		_methodName86 = "getContentCenterListForProjectStatistics";

		_methodParameterTypes86 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Locale"
			};

		_methodName87 = "getGeneralContentCountByGroupIdForProjectList";

		_methodParameterTypes87 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName88 = "migrationGeneralContentTable";

		_methodParameterTypes88 = new String[] { "long", "long" };

		_methodName90 = "migrationAdvancedContentTable";

		_methodParameterTypes90 = new String[] { "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return ContentLocalServiceUtil.addContent((org.kisti.edison.content.model.Content)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return ContentLocalServiceUtil.createContent(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return ContentLocalServiceUtil.deleteContent(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return ContentLocalServiceUtil.deleteContent((org.kisti.edison.content.model.Content)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return ContentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return ContentLocalServiceUtil.fetchContent(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return ContentLocalServiceUtil.getContent(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return ContentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return ContentLocalServiceUtil.getContents(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return ContentLocalServiceUtil.getContentsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return ContentLocalServiceUtil.updateContent((org.kisti.edison.content.model.Content)arguments[0]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return ContentLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			ContentLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveMapContent(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveListContent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (long[])arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(java.util.Locale)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return ContentLocalServiceUtil.retrievetListClassNote((long[])arguments[0],
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(java.util.Locale)arguments[4]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveCountClassNote((long[])arguments[0],
				(java.lang.String)arguments[1], (java.util.Locale)arguments[2]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveListRelatedClassNote((java.util.List<java.lang.Long>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveListContent((long[])arguments[0],
				(java.lang.String)arguments[1], (long[])arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(java.util.Locale)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveListContent((long[])arguments[0],
				(java.lang.String)arguments[1], (long[])arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(java.util.Locale)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue(),
				(java.lang.String)arguments[8], (java.lang.String)arguments[9]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveCountContent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (long[])arguments[3],
				(java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveCountContent((long[])arguments[0],
				(java.lang.String)arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveListUserContent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(java.util.Locale)arguments[6],
				((Long)arguments[7]).longValue(),
				((Long)arguments[8]).longValue());
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return ContentLocalServiceUtil.retrieveCountUserContent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue());
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return ContentLocalServiceUtil.createContent((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[5]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return ContentLocalServiceUtil.updateContent((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[5]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			ContentLocalServiceUtil.deleteContent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.lang.String, java.lang.Object>)arguments[2]);

			return null;
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return ContentLocalServiceUtil.relatedAssetLinkedEntryContent((java.util.Map)arguments[0]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return ContentLocalServiceUtil.searchAssetEntryModelContent((java.util.Map)arguments[0]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return ContentLocalServiceUtil.searchAssetEntryModelContentCount((java.util.Map)arguments[0]);
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return ContentLocalServiceUtil.contentAddAssetEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(org.kisti.edison.content.model.Content)arguments[2]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			ContentLocalServiceUtil.updateContentInsertId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], ((Long)arguments[4]).longValue());

			return null;
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return ContentLocalServiceUtil.makeCategoryEntryList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return ContentLocalServiceUtil.getContentDatailListForProjectStatistics((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return ContentLocalServiceUtil.getContentForProjectList(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(java.util.Locale)arguments[6]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return ContentLocalServiceUtil.getContentCenterListForProjectStatistics((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Locale)arguments[2]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return ContentLocalServiceUtil.getGeneralContentCountByGroupIdForProjectList(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			ContentLocalServiceUtil.migrationGeneralContentTable(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			ContentLocalServiceUtil.migrationAdvancedContentTable(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
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
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName90;
	private String[] _methodParameterTypes90;
}