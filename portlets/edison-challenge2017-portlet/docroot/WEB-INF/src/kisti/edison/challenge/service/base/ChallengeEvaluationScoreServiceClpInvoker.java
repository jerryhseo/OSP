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

import kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil;

import java.util.Arrays;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeEvaluationScoreServiceClpInvoker {
	public ChallengeEvaluationScoreServiceClpInvoker() {
		_methodName50 = "getBeanIdentifier";

		_methodParameterTypes50 = new String[] {  };

		_methodName51 = "setBeanIdentifier";

		_methodParameterTypes51 = new String[] { "java.lang.String" };

		_methodName56 = "getChallengeEvaluationScores";

		_methodParameterTypes56 = new String[] { "long" };

		_methodName57 = "getChallengeEvaluationScores";

		_methodParameterTypes57 = new String[] { "long", "long" };

		_methodName58 = "getChallengeEvaluationScores2";

		_methodParameterTypes58 = new String[] { "long", "long" };

		_methodName59 = "getChallengeEvaluationCount2";

		_methodParameterTypes59 = new String[] { "long", "long" };

		_methodName60 = "getChallengeEvaluationCount";

		_methodParameterTypes60 = new String[] { "long", "long" };

		_methodName61 = "getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId";

		_methodParameterTypes61 = new String[] { "long", "long", "long" };

		_methodName62 = "addChallengeEvaluationScore";

		_methodParameterTypes62 = new String[] {
				"long", "long", "long", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName63 = "updateChallengeEvaluationScore";

		_methodParameterTypes63 = new String[] {
				"long", "long", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName64 = "deleteChallengeEvaluationScore";

		_methodParameterTypes64 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getBeanIdentifier();
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			ChallengeEvaluationScoreServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores(((Long)arguments[0]).longValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores2(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationCount2(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.addChallengeEvaluationScore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Double)arguments[3]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.updateChallengeEvaluationScore(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Double)arguments[2]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return ChallengeEvaluationScoreServiceUtil.deleteChallengeEvaluationScore(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
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
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
}