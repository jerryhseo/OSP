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

import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public class DataTypeEditorLocalServiceClpInvoker {
	public DataTypeEditorLocalServiceClpInvoker() {
		_methodName0 = "addDataTypeEditor";

		_methodParameterTypes0 = new String[] {
				"com.kisti.osp.icecap.model.DataTypeEditor"
			};

		_methodName1 = "createDataTypeEditor";

		_methodParameterTypes1 = new String[] {
				"com.kisti.osp.icecap.service.persistence.DataTypeEditorPK"
			};

		_methodName2 = "deleteDataTypeEditor";

		_methodParameterTypes2 = new String[] {
				"com.kisti.osp.icecap.service.persistence.DataTypeEditorPK"
			};

		_methodName3 = "deleteDataTypeEditor";

		_methodParameterTypes3 = new String[] {
				"com.kisti.osp.icecap.model.DataTypeEditor"
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

		_methodName10 = "fetchDataTypeEditor";

		_methodParameterTypes10 = new String[] {
				"com.kisti.osp.icecap.service.persistence.DataTypeEditorPK"
			};

		_methodName11 = "getDataTypeEditor";

		_methodParameterTypes11 = new String[] {
				"com.kisti.osp.icecap.service.persistence.DataTypeEditorPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getDataTypeEditors";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getDataTypeEditorsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateDataTypeEditor";

		_methodParameterTypes15 = new String[] {
				"com.kisti.osp.icecap.model.DataTypeEditor"
			};

		_methodName80 = "getBeanIdentifier";

		_methodParameterTypes80 = new String[] {  };

		_methodName81 = "setBeanIdentifier";

		_methodParameterTypes81 = new String[] { "java.lang.String" };

		_methodName86 = "createDataTypeEditorObject";

		_methodParameterTypes86 = new String[] {
				"java.lang.Long", "boolean", "java.lang.Long"
			};

		_methodName87 = "retrieveDataTypeEditorPK";

		_methodParameterTypes87 = new String[] { "long", "long" };

		_methodName88 = "retrieveDataTypeEditorList";

		_methodParameterTypes88 = new String[] { "long" };

		_methodName89 = "removeDataTypeEditorByPK";

		_methodParameterTypes89 = new String[] { "long", "long" };

		_methodName90 = "removeDataTypeEditorByTypeId";

		_methodParameterTypes90 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.addDataTypeEditor((com.kisti.osp.icecap.model.DataTypeEditor)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.createDataTypeEditor((com.kisti.osp.icecap.service.persistence.DataTypeEditorPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.deleteDataTypeEditor((com.kisti.osp.icecap.service.persistence.DataTypeEditorPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.deleteDataTypeEditor((com.kisti.osp.icecap.model.DataTypeEditor)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.fetchDataTypeEditor((com.kisti.osp.icecap.service.persistence.DataTypeEditorPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.getDataTypeEditor((com.kisti.osp.icecap.service.persistence.DataTypeEditorPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.getDataTypeEditors(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.getDataTypeEditorsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.updateDataTypeEditor((com.kisti.osp.icecap.model.DataTypeEditor)arguments[0]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			DataTypeEditorLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.createDataTypeEditorObject((java.lang.Long)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				(java.lang.Long)arguments[2]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorPK(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return DataTypeEditorLocalServiceUtil.retrieveDataTypeEditorList(((Long)arguments[0]).longValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			DataTypeEditorLocalServiceUtil.removeDataTypeEditorByPK(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			DataTypeEditorLocalServiceUtil.removeDataTypeEditorByTypeId(((Long)arguments[0]).longValue());

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
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
}