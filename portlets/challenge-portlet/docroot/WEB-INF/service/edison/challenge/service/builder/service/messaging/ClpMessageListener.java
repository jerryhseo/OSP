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

package edison.challenge.service.builder.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import edison.challenge.service.builder.service.AgencyLocalServiceUtil;
import edison.challenge.service.builder.service.AgencyServiceUtil;
import edison.challenge.service.builder.service.AwardLocalServiceUtil;
import edison.challenge.service.builder.service.AwardServiceUtil;
import edison.challenge.service.builder.service.AwardhistoryLocalServiceUtil;
import edison.challenge.service.builder.service.AwardhistoryServiceUtil;
import edison.challenge.service.builder.service.ChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeMemberLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeMemberServiceUtil;
import edison.challenge.service.builder.service.ChallengeServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamLocalServiceUtil;
import edison.challenge.service.builder.service.ChallengeTeamServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ChildChallengeServiceUtil;
import edison.challenge.service.builder.service.ClpSerializer;

/**
 * @author kyj
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
			AgencyLocalServiceUtil.clearService();

			AgencyServiceUtil.clearService();
			AwardLocalServiceUtil.clearService();

			AwardServiceUtil.clearService();
			AwardhistoryLocalServiceUtil.clearService();

			AwardhistoryServiceUtil.clearService();
			ChallengeLocalServiceUtil.clearService();

			ChallengeServiceUtil.clearService();
			ChallengeMemberLocalServiceUtil.clearService();

			ChallengeMemberServiceUtil.clearService();
			ChallengeTeamLocalServiceUtil.clearService();

			ChallengeTeamServiceUtil.clearService();
			ChildChallengeLocalServiceUtil.clearService();

			ChildChallengeServiceUtil.clearService();
		}
	}
}