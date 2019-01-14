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

import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public class DataTypeLocalServiceClpInvoker {
	public DataTypeLocalServiceClpInvoker() {
		_methodName0 = "addDataType";

		_methodParameterTypes0 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName1 = "createDataType";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteDataType";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteDataType";

		_methodParameterTypes3 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
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

		_methodName10 = "fetchDataType";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchDataTypeByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchDataTypeByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getDataType";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getDataTypeByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getDataTypeByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getDataTypes";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getDataTypesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateDataType";

		_methodParameterTypes19 = new String[] {
				"com.kisti.osp.icecap.model.DataType"
			};

		_methodName84 = "getBeanIdentifier";

		_methodParameterTypes84 = new String[] {  };

		_methodName85 = "setBeanIdentifier";

		_methodParameterTypes85 = new String[] { "java.lang.String" };

		_methodName90 = "addFavoriteToDataTypeObject";

		_methodParameterTypes90 = new String[] {
				"java.lang.String", "java.lang.String", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName91 = "addOwnerIdToDataTypeObject";

		_methodParameterTypes91 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.Long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName92 = "createDataTypeObject";

		_methodParameterTypes92 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName93 = "findDataTypeObject";

		_methodParameterTypes93 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName94 = "addDataTypeAnalyzer";

		_methodParameterTypes94 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.Long"
			};

		_methodName95 = "findDataTypeObject";

		_methodParameterTypes95 = new String[] { "java.lang.Long" };

		_methodName96 = "retrieveDataTypeObject";

		_methodParameterTypes96 = new String[] { "java.lang.Long" };

		_methodName97 = "findDataTypeObjectByFavorite";

		_methodParameterTypes97 = new String[] { "long", "boolean" };

		_methodName98 = "findDataTypeObjectByOwner";

		_methodParameterTypes98 = new String[] { "long", "long" };

		_methodName99 = "checkDataTypeObjectValiation";

		_methodParameterTypes99 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName100 = "modifyDataTypeObject";

		_methodParameterTypes100 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.Integer", "java.lang.String",
				"java.lang.Long", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName101 = "modifyDataTypeObjectForEditorAnalyzer";

		_methodParameterTypes101 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Map",
				"java.util.List", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName102 = "retrieveDataTypeObjects";

		_methodParameterTypes102 = new String[] {
				"java.lang.String", "java.lang.Object", "long", "int", "int"
			};

		_methodName103 = "retrieveDataTypeCount";

		_methodParameterTypes103 = new String[] {
				"java.lang.String", "java.lang.Object", "long"
			};

		_methodName104 = "removeDataTypeObject";

		_methodParameterTypes104 = new String[] { "long" };

		_methodName105 = "retrieveViewCount";

		_methodParameterTypes105 = new String[] { "long" };

		_methodName106 = "copyDataType";

		_methodParameterTypes106 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName107 = "addSampleFilePath";

		_methodParameterTypes107 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext",
				"com.liferay.portal.kernel.upload.UploadPortletRequest"
			};

		_methodName108 = "getDataTypeStructure";

		_methodParameterTypes108 = new String[] {
				"java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DataTypeLocalServiceUtil.addDataType((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DataTypeLocalServiceUtil.createDataType(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DataTypeLocalServiceUtil.deleteDataType(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DataTypeLocalServiceUtil.deleteDataType((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DataTypeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DataTypeLocalServiceUtil.fetchDataType(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DataTypeLocalServiceUtil.fetchDataTypeByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DataTypeLocalServiceUtil.fetchDataTypeByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataType(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DataTypeLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataTypeByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataTypeByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataTypes(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataTypesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return DataTypeLocalServiceUtil.updateDataType((com.kisti.osp.icecap.model.DataType)arguments[0]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return DataTypeLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			DataTypeLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return DataTypeLocalServiceUtil.addFavoriteToDataTypeObject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return DataTypeLocalServiceUtil.addOwnerIdToDataTypeObject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.Long)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return DataTypeLocalServiceUtil.createDataTypeObject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.Long)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return DataTypeLocalServiceUtil.findDataTypeObject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			DataTypeLocalServiceUtil.addDataTypeAnalyzer((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.Long)arguments[2]);

			return null;
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return DataTypeLocalServiceUtil.findDataTypeObject((java.lang.Long)arguments[0]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return DataTypeLocalServiceUtil.retrieveDataTypeObject((java.lang.Long)arguments[0]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return DataTypeLocalServiceUtil.findDataTypeObjectByFavorite(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return DataTypeLocalServiceUtil.findDataTypeObjectByOwner(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return DataTypeLocalServiceUtil.checkDataTypeObjectValiation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return DataTypeLocalServiceUtil.modifyDataTypeObject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4],
				(java.lang.Integer)arguments[5],
				(java.lang.String)arguments[6], (java.lang.Long)arguments[7],
				(java.lang.Boolean)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return DataTypeLocalServiceUtil.modifyDataTypeObjectForEditorAnalyzer((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.List<java.util.Map<java.lang.String, java.lang.Object>>)arguments[3],
				(java.util.List<java.util.Map<java.lang.String, java.lang.Object>>)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return DataTypeLocalServiceUtil.retrieveDataTypeObjects((java.lang.String)arguments[0],
				(java.lang.Object)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return DataTypeLocalServiceUtil.retrieveDataTypeCount((java.lang.String)arguments[0],
				(java.lang.Object)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			DataTypeLocalServiceUtil.removeDataTypeObject(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return DataTypeLocalServiceUtil.retrieveViewCount(((Long)arguments[0]).longValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return DataTypeLocalServiceUtil.copyDataType(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return DataTypeLocalServiceUtil.addSampleFilePath(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[2]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return DataTypeLocalServiceUtil.getDataTypeStructure((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
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
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
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
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
}