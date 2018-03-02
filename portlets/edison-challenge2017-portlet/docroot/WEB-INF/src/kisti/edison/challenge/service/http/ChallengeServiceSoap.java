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

import kisti.edison.challenge.service.ChallengeServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChallengeServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChallengeSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.Challenge}, that is translated to a
 * {@link kisti.edison.challenge.model.ChallengeSoap}. Methods that SOAP cannot
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
 * @see ChallengeServiceHttp
 * @see kisti.edison.challenge.model.ChallengeSoap
 * @see kisti.edison.challenge.service.ChallengeServiceUtil
 * @generated
 */
public class ChallengeServiceSoap {
	public static kisti.edison.challenge.model.ChallengeSoap addChallenge(
		long userId, java.lang.String[] nameLanguageIds,
		java.lang.String[] nameValues, java.lang.String[] fieldLanguageIds,
		java.lang.String[] fieldValues,
		java.lang.String[] descriptionLanguageIds,
		java.lang.String[] descriptionValues,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> name = LocalizationUtil.getLocalizationMap(nameLanguageIds,
					nameValues);
			Map<Locale, String> field = LocalizationUtil.getLocalizationMap(fieldLanguageIds,
					fieldValues);
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(descriptionLanguageIds,
					descriptionValues);

			kisti.edison.challenge.model.Challenge returnValue = ChallengeServiceUtil.addChallenge(userId,
					name, field, description, serviceContext);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeSoap deleteChallenge(
		long challengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.Challenge returnValue = ChallengeServiceUtil.deleteChallenge(challengeId,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeSoap[] getChallenges(
		long groupId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.Challenge> returnValue = ChallengeServiceUtil.getChallenges(groupId);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeSoap[] getChallenges(
		long groupId, int start, int end) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.Challenge> returnValue = ChallengeServiceUtil.getChallenges(groupId,
					start, end);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeSoap getChallengeGroupAndField(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.Challenge returnValue = ChallengeServiceUtil.getChallengeGroupAndField(groupId,
					field, orderByComparator);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeSoap updateChallenge(
		long userId, long challengeId, java.lang.String[] nameLanguageIds,
		java.lang.String[] nameValues, java.lang.String[] fieldLanguageIds,
		java.lang.String[] fieldValues,
		java.lang.String[] descriptionLanguageIds,
		java.lang.String[] descriptionValues,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> name = LocalizationUtil.getLocalizationMap(nameLanguageIds,
					nameValues);
			Map<Locale, String> field = LocalizationUtil.getLocalizationMap(fieldLanguageIds,
					fieldValues);
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(descriptionLanguageIds,
					descriptionValues);

			kisti.edison.challenge.model.Challenge returnValue = ChallengeServiceUtil.updateChallenge(userId,
					challengeId, name, field, description, serviceContext);

			return kisti.edison.challenge.model.ChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChallengeServiceSoap.class);
}