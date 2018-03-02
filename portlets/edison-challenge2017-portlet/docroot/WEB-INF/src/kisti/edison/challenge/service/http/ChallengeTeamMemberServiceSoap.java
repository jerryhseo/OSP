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

import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChallengeTeamMemberSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.ChallengeTeamMember}, that is translated to a
 * {@link kisti.edison.challenge.model.ChallengeTeamMemberSoap}. Methods that SOAP cannot
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
 * @see ChallengeTeamMemberServiceHttp
 * @see kisti.edison.challenge.model.ChallengeTeamMemberSoap
 * @see kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil
 * @generated
 */
public class ChallengeTeamMemberServiceSoap {
	public static kisti.edison.challenge.model.ChallengeTeamMemberSoap addChallengeTeamMember(
		long userId, long challengeTeamId, long applyUserId,
		java.lang.String grade, java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeTeamMember returnValue = ChallengeTeamMemberServiceUtil.addChallengeTeamMember(userId,
					challengeTeamId, applyUserId, grade, phone, isLeader,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamMemberSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamMemberSoap deleteChallengeTeamMember(
		long challengeTeamMemberId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeTeamMember returnValue = ChallengeTeamMemberServiceUtil.deleteChallengeTeamMember(challengeTeamMemberId,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamMemberSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChallengeTeamMemberSoap updateChallengeTeamMember(
		long userId, long challengeTeamMemberId, java.lang.String grade,
		java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChallengeTeamMember returnValue = ChallengeTeamMemberServiceUtil.updateChallengeTeamMember(userId,
					challengeTeamMemberId, grade, phone, isLeader,
					serviceContext);

			return kisti.edison.challenge.model.ChallengeTeamMemberSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChallengeTeamMemberServiceSoap.class);
}