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

package kisti.edison.challenge.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationManagementServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeEvaluationScoreServiceUtil;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamMemberServiceUtil;
import kisti.edison.challenge.service.ChallengeTeamServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChildChallengeServiceUtil;
import kisti.edison.challenge.service.ClpSerializer;

/**
 * @author KYJ
 */
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			ChallengeLocalServiceUtil.clearService();

			ChallengeServiceUtil.clearService();
			ChallengeEvaluationManagementLocalServiceUtil.clearService();

			ChallengeEvaluationManagementServiceUtil.clearService();
			ChallengeEvaluationScoreLocalServiceUtil.clearService();

			ChallengeEvaluationScoreServiceUtil.clearService();
			ChallengeTeamLocalServiceUtil.clearService();

			ChallengeTeamServiceUtil.clearService();
			ChallengeTeamMemberLocalServiceUtil.clearService();

			ChallengeTeamMemberServiceUtil.clearService();
			ChildChallengeLocalServiceUtil.clearService();

			ChildChallengeServiceUtil.clearService();
		}
	}
}