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

package kisti.edison.challenge.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import kisti.edison.challenge.service.ChallengeEvaluationServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChallengeEvaluationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChallengeEvaluationSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.ChallengeEvaluation}, that is translated to a
 * {@link kisti.edison.challenge.model.ChallengeEvaluationSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author KYJ
 * @see ChallengeEvaluationServiceHttp
 * @see kisti.edison.challenge.model.ChallengeEvaluationSoap
 * @see kisti.edison.challenge.service.ChallengeEvaluationServiceUtil
 * @generated
 */
public class ChallengeEvaluationServiceSoap {
	public static kisti.edison.challenge.model.ChallengeEvaluationSoap[] getChallengeEvaluations(
		long groupId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> returnValue =
				ChallengeEvaluationServiceUtil.getChallengeEvaluations(groupId);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationSoap[] getChallengeEvaluations(
		long groupId, long challengeTeamId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> returnValue =
				ChallengeEvaluationServiceUtil.getChallengeEvaluations(groupId,
					challengeTeamId);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationSoap[] getChallengeEvaluations(
		long groupId, long challengeTeamId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> returnValue =
				ChallengeEvaluationServiceUtil.getChallengeEvaluations(groupId,
					challengeTeamId, start, end);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getChallengeEvaluationCount(long groupId,
		long challengeTeamId) throws RemoteException {
		try {
			int returnValue = ChallengeEvaluationServiceUtil.getChallengeEvaluationCount(groupId,
					challengeTeamId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationSoap addChallengeEvaluation(
		long userId, long challengeTeamId,
		java.lang.String[] assessmentItemLanguageIds,
		java.lang.String[] assessmentItemValues, double distribution,
		double score, com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> assessmentItem = LocalizationUtil.getLocalizationMap(assessmentItemLanguageIds,
					assessmentItemValues);

			kisti.edison.challenge.model.ChallengeEvaluation returnValue = ChallengeEvaluationServiceUtil.addChallengeEvaluation(userId,
					challengeTeamId, assessmentItem, distribution, score,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationSoap updateChallengeEvaluation(
		long userId, long challengeTeamId, long challengeEvaluationId,
		java.lang.String[] assessmentItemLanguageIds,
		java.lang.String[] assessmentItemValues, double distribution,
		double score, com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> assessmentItem = LocalizationUtil.getLocalizationMap(assessmentItemLanguageIds,
					assessmentItemValues);

			kisti.edison.challenge.model.ChallengeEvaluation returnValue = ChallengeEvaluationServiceUtil.updateChallengeEvaluation(userId,
					challengeTeamId, challengeEvaluationId, assessmentItem,
					distribution, score, serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationSoap deleteChallengeEvaluation(
		long challengeEvaluationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeEvaluation returnValue = ChallengeEvaluationServiceUtil.deleteChallengeEvaluation(challengeEvaluationId,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChallengeEvaluationServiceSoap.class);
}