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

import org.kisti.edison.service.SimProScienceAppLinkLocalServiceUtil;

import java.util.Arrays;

/**
 * @author edison
 * @generated
 */
public class SimProScienceAppLinkLocalServiceClpInvoker {
	public SimProScienceAppLinkLocalServiceClpInvoker() {
		_methodName0 = "addSimProScienceAppLink";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.model.SimProScienceAppLink"
			};

		_methodName1 = "createSimProScienceAppLink";

		_methodParameterTypes1 = new String[] {
				"org.kisti.edison.service.persistence.SimProScienceAppLinkPK"
			};

		_methodName2 = "deleteSimProScienceAppLink";

		_methodParameterTypes2 = new String[] {
				"org.kisti.edison.service.persistence.SimProScienceAppLinkPK"
			};

		_methodName3 = "deleteSimProScienceAppLink";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.model.SimProScienceAppLink"
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

		_methodName10 = "fetchSimProScienceAppLink";

		_methodParameterTypes10 = new String[] {
				"org.kisti.edison.service.persistence.SimProScienceAppLinkPK"
			};

		_methodName11 = "getSimProScienceAppLink";

		_methodParameterTypes11 = new String[] {
				"org.kisti.edison.service.persistence.SimProScienceAppLinkPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSimProScienceAppLinks";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSimProScienceAppLinksCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSimProScienceAppLink";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.model.SimProScienceAppLink"
			};

		_methodName52 = "getBeanIdentifier";

		_methodParameterTypes52 = new String[] {  };

		_methodName53 = "setBeanIdentifier";

		_methodParameterTypes53 = new String[] { "java.lang.String" };

		_methodName58 = "getScienceAppList";

		_methodParameterTypes58 = new String[] {
				"java.lang.String", "java.util.Locale", "int", "int"
			};

		_methodName59 = "getScienceAppListCount";

		_methodParameterTypes59 = new String[] {
				"java.lang.String", "java.util.Locale"
			};

		_methodName60 = "getScienceAppList";

		_methodParameterTypes60 = new String[] {
				"java.lang.String[][]", "java.util.Locale"
			};

		_methodName62 = "getScienceAppList";

		_methodParameterTypes62 = new String[] { "long", "java.util.Locale" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.addSimProScienceAppLink((org.kisti.edison.model.SimProScienceAppLink)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.createSimProScienceAppLink((org.kisti.edison.service.persistence.SimProScienceAppLinkPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.deleteSimProScienceAppLink((org.kisti.edison.service.persistence.SimProScienceAppLinkPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.deleteSimProScienceAppLink((org.kisti.edison.model.SimProScienceAppLink)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.fetchSimProScienceAppLink((org.kisti.edison.service.persistence.SimProScienceAppLinkPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getSimProScienceAppLink((org.kisti.edison.service.persistence.SimProScienceAppLinkPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getSimProScienceAppLinks(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getSimProScienceAppLinksCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.updateSimProScienceAppLink((org.kisti.edison.model.SimProScienceAppLink)arguments[0]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			SimProScienceAppLinkLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getScienceAppList((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getScienceAppListCount((java.lang.String)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getScienceAppList((java.lang.String[])arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SimProScienceAppLinkLocalServiceUtil.getScienceAppList(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
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
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName62;
	private String[] _methodParameterTypes62;
}