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

import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public class DataEntryLocalServiceClpInvoker {
	public DataEntryLocalServiceClpInvoker() {
		_methodName0 = "addDataEntry";

		_methodParameterTypes0 = new String[] {
				"com.kisti.osp.icecap.model.DataEntry"
			};

		_methodName1 = "createDataEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteDataEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteDataEntry";

		_methodParameterTypes3 = new String[] {
				"com.kisti.osp.icecap.model.DataEntry"
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

		_methodName10 = "fetchDataEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getDataEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getDataEntries";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getDataEntriesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateDataEntry";

		_methodParameterTypes15 = new String[] {
				"com.kisti.osp.icecap.model.DataEntry"
			};

		_methodName80 = "getBeanIdentifier";

		_methodParameterTypes80 = new String[] {  };

		_methodName81 = "setBeanIdentifier";

		_methodParameterTypes81 = new String[] { "java.lang.String" };

		_methodName86 = "createDataEntryObject";

		_methodParameterTypes86 = new String[] {
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.Map", "java.lang.String",
				"java.lang.Long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName87 = "updateDataEntryObject";

		_methodParameterTypes87 = new String[] {
				"java.lang.Long", "java.lang.Long", "java.util.Map",
				"java.lang.Long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName90 = "checkExistSubjectIdAndJobData";

		_methodParameterTypes90 = new String[] {
				"java.lang.Long", "java.lang.String"
			};

		_methodName91 = "findDataEntryPerApp";

		_methodParameterTypes91 = new String[] { "java.lang.Long" };

		_methodName92 = "findDataEntryObject";

		_methodParameterTypes92 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.String"
			};

		_methodName93 = "retrieveDataEntryOnlyUsingMapByCollectionId";

		_methodParameterTypes93 = new String[] { "long" };

		_methodName94 = "retrieveDataEntryOnlyByCollectionId";

		_methodParameterTypes94 = new String[] { "long" };

		_methodName95 = "addPROVStructure";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "long", "long", "long",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "long",
				"java.lang.String", "long", "long", "long", "java.lang.String"
			};

		_methodName96 = "searchPROVStructure";

		_methodParameterTypes96 = new String[] { "java.lang.String" };

		_methodName98 = "retrieveDataEntryByCollectionId";

		_methodParameterTypes98 = new String[] { "long" };

		_methodName99 = "retrieveDataEntryByEntryId";

		_methodParameterTypes99 = new String[] { "long" };

		_methodName100 = "incrementDownloadCnt";

		_methodParameterTypes100 = new String[] { "long" };

		_methodName101 = "incrementViewCnt";

		_methodParameterTypes101 = new String[] { "long" };

		_methodName102 = "removeDataEntryByEntryId";

		_methodParameterTypes102 = new String[] { "long" };

		_methodName104 = "retrieveListDataEntry";

		_methodParameterTypes104 = new String[] {
				"long", "long", "java.lang.String", "long", "int", "int",
				"java.util.Locale"
			};

		_methodName105 = "copyFilesFromPortal";

		_methodParameterTypes105 = new String[] {
				"java.lang.String", "java.lang.String", "long", "java.util.Map"
			};

		_methodName106 = "retrieveCountDataCollection";

		_methodParameterTypes106 = new String[] {
				"long", "long", "java.lang.String", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DataEntryLocalServiceUtil.addDataEntry((com.kisti.osp.icecap.model.DataEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DataEntryLocalServiceUtil.createDataEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DataEntryLocalServiceUtil.deleteDataEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DataEntryLocalServiceUtil.deleteDataEntry((com.kisti.osp.icecap.model.DataEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DataEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DataEntryLocalServiceUtil.fetchDataEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DataEntryLocalServiceUtil.getDataEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DataEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DataEntryLocalServiceUtil.getDataEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DataEntryLocalServiceUtil.getDataEntriesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DataEntryLocalServiceUtil.updateDataEntry((com.kisti.osp.icecap.model.DataEntry)arguments[0]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return DataEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			DataEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return DataEntryLocalServiceUtil.createDataEntryObject((java.lang.Long)arguments[0],
				(java.lang.Long)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5], (java.lang.Long)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return DataEntryLocalServiceUtil.updateDataEntryObject((java.lang.Long)arguments[0],
				(java.lang.Long)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.Long)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return DataEntryLocalServiceUtil.checkExistSubjectIdAndJobData((java.lang.Long)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return DataEntryLocalServiceUtil.findDataEntryPerApp((java.lang.Long)arguments[0]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return DataEntryLocalServiceUtil.findDataEntryObject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.Long)arguments[4],
				(java.lang.String)arguments[5]);
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveDataEntryOnlyUsingMapByCollectionId(((Long)arguments[0]).longValue());
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveDataEntryOnlyByCollectionId(((Long)arguments[0]).longValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			DataEntryLocalServiceUtil.addPROVStructure(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				(java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				(java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				((Long)arguments[13]).longValue(),
				((Long)arguments[14]).longValue(),
				((Long)arguments[15]).longValue(),
				(java.lang.String)arguments[16]);

			return null;
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return DataEntryLocalServiceUtil.searchPROVStructure((java.lang.String)arguments[0]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveDataEntryByCollectionId(((Long)arguments[0]).longValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveDataEntryByEntryId(((Long)arguments[0]).longValue());
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			DataEntryLocalServiceUtil.incrementDownloadCnt(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			DataEntryLocalServiceUtil.incrementViewCnt(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			DataEntryLocalServiceUtil.removeDataEntryByEntryId(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveListDataEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(java.util.Locale)arguments[6]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return DataEntryLocalServiceUtil.copyFilesFromPortal((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.util.Map<java.lang.String, java.lang.String>)arguments[3]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return DataEntryLocalServiceUtil.retrieveCountDataCollection(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], ((Long)arguments[3]).longValue());
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
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
}