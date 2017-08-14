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

package com.kisti.osp.icecap.service.base;

import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public class DataCollectionLocalServiceClpInvoker {
	public DataCollectionLocalServiceClpInvoker() {
		_methodName0 = "addDataCollection";

		_methodParameterTypes0 = new String[] {
				"com.kisti.osp.icecap.model.DataCollection"
			};

		_methodName1 = "createDataCollection";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteDataCollection";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteDataCollection";

		_methodParameterTypes3 = new String[] {
				"com.kisti.osp.icecap.model.DataCollection"
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

		_methodName10 = "fetchDataCollection";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchDataCollectionByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchDataCollectionByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getDataCollection";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getDataCollectionByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getDataCollectionByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getDataCollections";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getDataCollectionsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateDataCollection";

		_methodParameterTypes19 = new String[] {
				"com.kisti.osp.icecap.model.DataCollection"
			};

		_methodName94 = "getBeanIdentifier";

		_methodParameterTypes94 = new String[] {  };

		_methodName95 = "setBeanIdentifier";

		_methodParameterTypes95 = new String[] { "java.lang.String" };

		_methodName100 = "createDataCollectionObject";

		_methodParameterTypes100 = new String[] {
				"java.lang.Long", "java.lang.String", "java.lang.String",
				"java.util.Map", "java.util.Map", "java.lang.String",
				"java.lang.String", "java.lang.String", "long[][]", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName101 = "findDataCollectionObject";

		_methodParameterTypes101 = new String[] {
				"java.lang.Long", "java.lang.String", "java.lang.String"
			};

		_methodName102 = "checkDataCollectionValidity";

		_methodParameterTypes102 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName103 = "retrieveDataCollectionObjects";

		_methodParameterTypes103 = new String[] {
				"java.lang.String", "java.util.Locale", "int", "int"
			};

		_methodName104 = "countDataCollectionObjects";

		_methodParameterTypes104 = new String[] {
				"java.lang.String", "java.util.Locale"
			};

		_methodName105 = "retrieveDataCollectionObjectsByCollectId";

		_methodParameterTypes105 = new String[] {
				"java.lang.Object[][]", "int", "int", "java.util.Locale"
			};

		_methodName106 = "retrieveDataCollectionObjectsByCollectId";

		_methodParameterTypes106 = new String[] {
				"java.lang.Object[][]", "java.util.Locale"
			};

		_methodName107 = "countDataCollectionObjectsByCollectId";

		_methodParameterTypes107 = new String[] {
				"java.lang.Object[][]", "java.util.Locale"
			};

		_methodName108 = "removeDataCollectionByCollectionId";

		_methodParameterTypes108 = new String[] { "long" };

		_methodName112 = "retrieveListDataCollectionHistory";

		_methodParameterTypes112 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Locale"
			};

		_methodName113 = "retrieveListDataCollection";

		_methodParameterTypes113 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"java.util.Locale"
			};

		_methodName114 = "retrieveListDataCollection";

		_methodParameterTypes114 = new String[] {
				"long", "long", "java.lang.String", "int", "int",
				"java.util.Locale"
			};

		_methodName115 = "retrieveListDataCollection";

		_methodParameterTypes115 = new String[] {
				"long[][]", "java.lang.String", "int", "int", "java.util.Locale"
			};

		_methodName116 = "retrieveCountDataCollection";

		_methodParameterTypes116 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Locale"
			};

		_methodName117 = "retrieveCountDataCollection";

		_methodParameterTypes117 = new String[] {
				"long", "long", "java.lang.String", "java.util.Locale"
			};

		_methodName118 = "retrieveCountDataCollection";

		_methodParameterTypes118 = new String[] {
				"long[][]", "java.lang.String", "java.util.Locale"
			};

		_methodName119 = "retrieveCountRelatedAssetDataCollection";

		_methodParameterTypes119 = new String[] {
				"java.util.List", "java.util.Locale"
			};

		_methodName121 = "retrieveDataCollectionObjects";

		_methodParameterTypes121 = new String[] { "java.lang.String", "int", "int" };

		_methodName122 = "countDataCollectionObjects";

		_methodParameterTypes122 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DataCollectionLocalServiceUtil.addDataCollection((com.kisti.osp.icecap.model.DataCollection)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DataCollectionLocalServiceUtil.createDataCollection(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DataCollectionLocalServiceUtil.deleteDataCollection(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DataCollectionLocalServiceUtil.deleteDataCollection((com.kisti.osp.icecap.model.DataCollection)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DataCollectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DataCollectionLocalServiceUtil.fetchDataCollection(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DataCollectionLocalServiceUtil.fetchDataCollectionByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DataCollectionLocalServiceUtil.fetchDataCollectionByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getDataCollection(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getDataCollectionByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getDataCollectionByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getDataCollections(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getDataCollectionsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return DataCollectionLocalServiceUtil.updateDataCollection((com.kisti.osp.icecap.model.DataCollection)arguments[0]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return DataCollectionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			DataCollectionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return DataCollectionLocalServiceUtil.createDataCollectionObject((java.lang.Long)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (long[])arguments[8],
				((Long)arguments[9]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return DataCollectionLocalServiceUtil.findDataCollectionObject((java.lang.Long)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return DataCollectionLocalServiceUtil.checkDataCollectionValidity((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveDataCollectionObjects((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return DataCollectionLocalServiceUtil.countDataCollectionObjects((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveDataCollectionObjectsByCollectId((java.lang.Object[])arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(java.util.Locale)arguments[3]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveDataCollectionObjectsByCollectId((java.lang.Object[])arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return DataCollectionLocalServiceUtil.countDataCollectionObjectsByCollectId((java.lang.Object[])arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			DataCollectionLocalServiceUtil.removeDataCollectionByCollectionId(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveListDataCollectionHistory((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Locale)arguments[2]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveListDataCollection(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(java.util.Locale)arguments[6]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveListDataCollection(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(java.util.Locale)arguments[5]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveListDataCollection((long[])arguments[0],
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(java.util.Locale)arguments[4]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveCountDataCollection(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.util.Locale)arguments[4]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveCountDataCollection(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveCountDataCollection((long[])arguments[0],
				(java.lang.String)arguments[1], (java.util.Locale)arguments[2]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveCountRelatedAssetDataCollection((java.util.List<java.lang.Long>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return DataCollectionLocalServiceUtil.retrieveDataCollectionObjects((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return DataCollectionLocalServiceUtil.countDataCollectionObjects((java.lang.String)arguments[0]);
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
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
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
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
	private String _methodName116;
	private String[] _methodParameterTypes116;
	private String _methodName117;
	private String[] _methodParameterTypes117;
	private String _methodName118;
	private String[] _methodParameterTypes118;
	private String _methodName119;
	private String[] _methodParameterTypes119;
	private String _methodName121;
	private String[] _methodParameterTypes121;
	private String _methodName122;
	private String[] _methodParameterTypes122;
}