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

import kisti.edison.challenge.service.ChildChallengeServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link kisti.edison.challenge.service.ChildChallengeServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link kisti.edison.challenge.model.ChildChallengeSoap}.
 * If the method in the service utility returns a
 * {@link kisti.edison.challenge.model.ChildChallenge}, that is translated to a
 * {@link kisti.edison.challenge.model.ChildChallengeSoap}. Methods that SOAP cannot
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
 * @see ChildChallengeServiceHttp
 * @see kisti.edison.challenge.model.ChildChallengeSoap
 * @see kisti.edison.challenge.service.ChildChallengeServiceUtil
 * @generated
 */
public class ChildChallengeServiceSoap {
	public static kisti.edison.challenge.model.ChildChallengeSoap addChildChallenge(
		long userId, long challengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChildChallenge returnValue = ChildChallengeServiceUtil.addChildChallenge(userId,
					challengeId, number, presentationDay, paperStartDay,
					paperEndDay, evaluationDay, challengeStartDay,
					challengeEndDay, challengeStatus, serviceContext);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChildChallengeSoap deleteChildChallenge(
		long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChildChallenge returnValue = ChildChallengeServiceUtil.deleteChildChallenge(childChallengeId,
					serviceContext);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChildChallengeSoap[] getChildChallenges(
		long groupId, long challengeId) throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChildChallenge> returnValue =
				ChildChallengeServiceUtil.getChildChallenges(groupId,
					challengeId);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChildChallengeSoap[] getChildChallenges(
		long groupId, long challengeId, int start, int end)
		throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChildChallenge> returnValue =
				ChildChallengeServiceUtil.getChildChallenges(groupId,
					challengeId, start, end);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getChildChallengesCount(long groupId, long challengeId)
		throws RemoteException {
		try {
			int returnValue = ChildChallengeServiceUtil.getChildChallengesCount(groupId,
					challengeId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChildChallengeSoap[] getChildChallengesByStatus(
		long groupId, java.lang.String challengeStatus)
		throws RemoteException {
		try {
			java.util.List<kisti.edison.challenge.model.ChildChallenge> returnValue =
				ChildChallengeServiceUtil.getChildChallengesByStatus(groupId,
					challengeStatus);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static kisti.edison.challenge.model.ChildChallengeSoap updateChildChallenge(
		long userId, long challengeId, long childChallengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			kisti.edison.challenge.model.ChildChallenge returnValue = ChildChallengeServiceUtil.updateChildChallenge(userId,
					challengeId, childChallengeId, number, presentationDay,
					paperStartDay, paperEndDay, evaluationDay,
					challengeStartDay, challengeEndDay, challengeStatus,
					serviceContext);

			return kisti.edison.challenge.model.ChildChallengeSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ChildChallengeServiceSoap.class);
}