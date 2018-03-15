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

import kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChallengeEvaluationScoreSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.ChallengeEvaluationScore}, that is translated to a
 * {@link kisti.edison.challenge.model.ChallengeEvaluationScoreSoap}. Methods that SOAP cannot
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
 * @see ChallengeEvaluationScoreServiceHttp
 * @see kisti.edison.challenge.model.ChallengeEvaluationScoreSoap
 * @see kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil
 * @generated
 */
public class ChallengeEvaluationScoreServiceSoap {
	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap[] getChallengeEvaluationScores(
		long groupId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> returnValue =
				ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores(groupId);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap[] getChallengeEvaluationScores(
		long groupId, long challengeTeamId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> returnValue =
				ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores(groupId,
					challengeTeamId);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap[] getChallengeEvaluationScores2(
		long groupId, long childChallengeId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> returnValue =
				ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScores2(groupId,
					childChallengeId);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getChallengeEvaluationCount2(long groupId,
		long challengeTeamId) throws RemoteException {
		try {
			int returnValue = ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationCount2(groupId,
					challengeTeamId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getChallengeEvaluationCount(long groupId,
		long childChallengeId) throws RemoteException {
		try {
			int returnValue = ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationCount(groupId,
					childChallengeId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap[] getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> returnValue =
				ChallengeEvaluationScoreServiceUtil.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
					challengeTeamId, userId);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeEvaluationScore returnValue = ChallengeEvaluationScoreServiceUtil.addChallengeEvaluationScore(userId,
					challengeTeamId, challengeEvaluationManagementId, score,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap updateChallengeEvaluationScore(
		long userId, long challengeEvaluationScoreId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeEvaluationScore returnValue = ChallengeEvaluationScoreServiceUtil.updateChallengeEvaluationScore(userId,
					challengeEvaluationScoreId, score, serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScoreSoap deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeEvaluationScore returnValue = ChallengeEvaluationScoreServiceUtil.deleteChallengeEvaluationScore(challengeEvaluationScoreId,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeEvaluationScoreSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChallengeEvaluationScoreServiceSoap.class);
}