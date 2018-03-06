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

import kisti.edison.challenge.service.ChallengeTeamServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChallengeTeamServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChallengeTeamSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.ChallengeTeam}, that is translated to a
 * {@link kisti.edison.challenge.model.ChallengeTeamSoap}. Methods that SOAP cannot
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
 * @see ChallengeTeamServiceHttp
 * @see kisti.edison.challenge.model.ChallengeTeamSoap
 * @see kisti.edison.challenge.service.ChallengeTeamServiceUtil
 * @generated
 */
public class ChallengeTeamServiceSoap {
	public static kisti.edison.challenge.model.ChallengeTeamSoap addChallengeTeam(
		long userId, java.lang.String teamName,
		java.lang.String[] subjectLanguageIds,
		java.lang.String[] subjectValues,
		java.lang.String[] paperNameLanguageIds,
		java.lang.String[] paperNameValues,
		java.lang.String[] paperAbstractLanguageIds,
		java.lang.String[] paperAbstractValues,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone, long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> subject = LocalizationUtil.getLocalizationMap(subjectLanguageIds,
					subjectValues);
			Map<Locale, String> paperName = LocalizationUtil.getLocalizationMap(paperNameLanguageIds,
					paperNameValues);
			Map<Locale, String> paperAbstract = LocalizationUtil.getLocalizationMap(paperAbstractLanguageIds,
					paperAbstractValues);

			kisti.edison.challenge.model.ChallengeTeam returnValue = ChallengeTeamServiceUtil.addChallengeTeam(userId,
					teamName, subject, paperName, paperAbstract, uploadRequest,
					grade, phone, childChallengeId, serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamSoap deleteChallengeTeam(
		long challengeTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeTeam returnValue = ChallengeTeamServiceUtil.deleteChallengeTeam(challengeTeamId,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamSoap[] getChallengeTeames(
		long groupId, long childChallengeId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeTeam> returnValue =
				ChallengeTeamServiceUtil.getChallengeTeames(groupId,
					childChallengeId);

			return kisti.edison.challenge.model.ChallengeTeamSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamSoap[] getChallengeTeames(
		long groupId, long childChallengeId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChallengeTeam> returnValue =
				ChallengeTeamServiceUtil.getChallengeTeames(groupId,
					childChallengeId, start, end);

			return kisti.edison.challenge.model.ChallengeTeamSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getChallengeTeamesCount(long groupId,
		long childChallengeId) throws RemoteException {
		try {
			int returnValue = ChallengeTeamServiceUtil.getChallengeTeamesCount(groupId,
					childChallengeId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamSoap updateChallengeTeam(
		long userId, long childChallengeId, long challengeTeamId,
		java.lang.String teamName, java.lang.String[] subjectLanguageIds,
		java.lang.String[] subjectValues,
		java.lang.String[] paperNameLanguageIds,
		java.lang.String[] paperNameValues,
		java.lang.String[] paperAbstractLanguageIds,
		java.lang.String[] paperAbstractValues,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> subject = LocalizationUtil.getLocalizationMap(subjectLanguageIds,
					subjectValues);
			Map<Locale, String> paperName = LocalizationUtil.getLocalizationMap(paperNameLanguageIds,
					paperNameValues);
			Map<Locale, String> paperAbstract = LocalizationUtil.getLocalizationMap(paperAbstractLanguageIds,
					paperAbstractValues);

			kisti.edison.challenge.model.ChallengeTeam returnValue = ChallengeTeamServiceUtil.updateChallengeTeam(userId,
					childChallengeId, challengeTeamId, teamName, subject,
					paperName, paperAbstract, uploadRequest, grade, phone,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChallengeTeamServiceSoap.class);
}