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

package kisti.edison.challenge.service.base;

import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;

import java.util.Arrays;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeTeamMemberServiceClpInvoker {
	public ChallengeTeamMemberServiceClpInvoker() {
		_methodName60 = "getBeanIdentifier";

		_methodParameterTypes60 = new String[] {  };

		_methodName61 = "setBeanIdentifier";

		_methodParameterTypes61 = new String[] { "java.lang.String" };

		_methodName66 = "addChallengeTeamMember";

		_methodParameterTypes66 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"boolean", "com.liferay.portal.service.ServiceContext"
			};

		_methodName67 = "deleteChallengeTeamMember";

		_methodParameterTypes67 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName68 = "updateChallengeTeamMember";

		_methodParameterTypes68 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"boolean", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return ChallengeTeamMemberServiceUtil.getBeanIdentifier();
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			ChallengeTeamMemberServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return ChallengeTeamMemberServiceUtil.addChallengeTeamMember(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return ChallengeTeamMemberServiceUtil.deleteChallengeTeamMember(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return ChallengeTeamMemberServiceUtil.updateChallengeTeamMember(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
}