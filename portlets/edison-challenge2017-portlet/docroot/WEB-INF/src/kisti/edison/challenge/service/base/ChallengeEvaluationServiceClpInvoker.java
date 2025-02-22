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

import kisti.edison.challenge.service.ChallengeEvaluationServiceUtil;

import java.util.Arrays;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeEvaluationServiceClpInvoker {
	public ChallengeEvaluationServiceClpInvoker() {
		_methodName44 = "getBeanIdentifier";

		_methodParameterTypes44 = new String[] {  };

		_methodName45 = "setBeanIdentifier";

		_methodParameterTypes45 = new String[] { "java.lang.String" };

		_methodName50 = "getChallengeEvaluations";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "getChallengeEvaluations";

		_methodParameterTypes51 = new String[] { "long", "long" };

		_methodName52 = "getChallengeEvaluations";

		_methodParameterTypes52 = new String[] { "long", "long", "int", "int" };

		_methodName53 = "getChallengeEvaluationCount";

		_methodParameterTypes53 = new String[] { "long", "long" };

		_methodName54 = "addChallengeEvaluation";

		_methodParameterTypes54 = new String[] {
				"long", "long", "java.util.Map", "double", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName55 = "updateChallengeEvaluation";

		_methodParameterTypes55 = new String[] {
				"long", "long", "long", "java.util.Map", "double", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName56 = "deleteChallengeEvaluation";

		_methodParameterTypes56 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.getBeanIdentifier();
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			ChallengeEvaluationServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.getChallengeEvaluations(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.getChallengeEvaluations(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.getChallengeEvaluations(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.getChallengeEvaluationCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.addChallengeEvaluation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Double)arguments[3]).doubleValue(),
				((Double)arguments[4]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.updateChallengeEvaluation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				((Double)arguments[4]).doubleValue(),
				((Double)arguments[5]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return ChallengeEvaluationServiceUtil.deleteChallengeEvaluation(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
}