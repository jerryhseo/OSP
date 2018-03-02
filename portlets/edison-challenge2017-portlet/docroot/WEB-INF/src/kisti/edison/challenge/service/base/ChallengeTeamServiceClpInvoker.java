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

import kisti.edison.challenge.service.ChallengeTeamServiceUtil;

import java.util.Arrays;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeTeamServiceClpInvoker {
	public ChallengeTeamServiceClpInvoker() {
		_methodName60 = "getBeanIdentifier";

		_methodParameterTypes60 = new String[] {  };

		_methodName61 = "setBeanIdentifier";

		_methodParameterTypes61 = new String[] { "java.lang.String" };

		_methodName66 = "addChallengeTeam";

		_methodParameterTypes66 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.util.Map",
				"java.util.Map",
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"java.lang.String", "java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName67 = "deleteChallengeTeam";

		_methodParameterTypes67 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName68 = "getChallengeTeames";

		_methodParameterTypes68 = new String[] { "long", "long" };

		_methodName69 = "getChallengeTeames";

		_methodParameterTypes69 = new String[] { "long", "long", "int", "int" };

		_methodName70 = "getChallengeTeamesCount";

		_methodParameterTypes70 = new String[] { "long", "long" };

		_methodName71 = "updateChallengeTeam";

		_methodParameterTypes71 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Map",
				"java.util.Map", "java.util.Map",
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return ChallengeTeamServiceUtil.getBeanIdentifier();
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			ChallengeTeamServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return ChallengeTeamServiceUtil.addChallengeTeam(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return ChallengeTeamServiceUtil.deleteChallengeTeam(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return ChallengeTeamServiceUtil.getChallengeTeames(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return ChallengeTeamServiceUtil.getChallengeTeames(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return ChallengeTeamServiceUtil.getChallengeTeamesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return ChallengeTeamServiceUtil.updateChallengeTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[5],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[6],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
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
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
}