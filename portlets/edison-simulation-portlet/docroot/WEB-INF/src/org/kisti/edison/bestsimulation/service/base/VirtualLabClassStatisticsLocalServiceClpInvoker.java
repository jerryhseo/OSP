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

package org.kisti.edison.bestsimulation.service.base;

import org.kisti.edison.bestsimulation.service.VirtualLabClassStatisticsLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class VirtualLabClassStatisticsLocalServiceClpInvoker {
	public VirtualLabClassStatisticsLocalServiceClpInvoker() {
		_methodName0 = "addVirtualLabClassStatistics";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics"
			};

		_methodName1 = "createVirtualLabClassStatistics";

		_methodParameterTypes1 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK"
			};

		_methodName2 = "deleteVirtualLabClassStatistics";

		_methodParameterTypes2 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK"
			};

		_methodName3 = "deleteVirtualLabClassStatistics";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics"
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

		_methodName10 = "fetchVirtualLabClassStatistics";

		_methodParameterTypes10 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK"
			};

		_methodName11 = "getVirtualLabClassStatistics";

		_methodParameterTypes11 = new String[] {
				"org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getVirtualLabClassStatisticses";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getVirtualLabClassStatisticsesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateVirtualLabClassStatistics";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics"
			};

		_methodName90 = "getBeanIdentifier";

		_methodParameterTypes90 = new String[] {  };

		_methodName91 = "setBeanIdentifier";

		_methodParameterTypes91 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.addVirtualLabClassStatistics((org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.createVirtualLabClassStatistics((org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.deleteVirtualLabClassStatistics((org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.deleteVirtualLabClassStatistics((org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.fetchVirtualLabClassStatistics((org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.getVirtualLabClassStatistics((org.kisti.edison.bestsimulation.service.persistence.VirtualLabClassStatisticsPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.getVirtualLabClassStatisticses(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.getVirtualLabClassStatisticsesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.updateVirtualLabClassStatistics((org.kisti.edison.bestsimulation.model.VirtualLabClassStatistics)arguments[0]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return VirtualLabClassStatisticsLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			VirtualLabClassStatisticsLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

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
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
}