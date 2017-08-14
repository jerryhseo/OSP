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

package org.kisti.edison.virtuallaboratory.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.kisti.edison.virtuallaboratory.service.ClassNoteLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.ClassNoteServiceUtil;
import org.kisti.edison.virtuallaboratory.service.ClpSerializer;
import org.kisti.edison.virtuallaboratory.service.ProfessorLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.ProfessorServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyAnswerLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyAnswerServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyQuestionLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyQuestionServiceUtil;
import org.kisti.edison.virtuallaboratory.service.SurveyServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassScienceAppLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassScienceAppServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassStsMigrationLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabClassStsMigrationServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkClassLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkClassServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabScienceAppLinkServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserTempLocalServiceUtil;
import org.kisti.edison.virtuallaboratory.service.VirtualLabUserTempServiceUtil;

/**
 * @author EDISON
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
			ClassNoteLocalServiceUtil.clearService();

			ClassNoteServiceUtil.clearService();
			ProfessorLocalServiceUtil.clearService();

			ProfessorServiceUtil.clearService();
			SurveyLocalServiceUtil.clearService();

			SurveyServiceUtil.clearService();
			SurveyAnswerLocalServiceUtil.clearService();

			SurveyAnswerServiceUtil.clearService();
			SurveyQuestionLocalServiceUtil.clearService();

			SurveyQuestionServiceUtil.clearService();
			VirtualLabLocalServiceUtil.clearService();

			VirtualLabServiceUtil.clearService();
			VirtualLabClassLocalServiceUtil.clearService();

			VirtualLabClassServiceUtil.clearService();
			VirtualLabClassScienceAppLocalServiceUtil.clearService();

			VirtualLabClassScienceAppServiceUtil.clearService();
			VirtualLabClassStsMigrationLocalServiceUtil.clearService();

			VirtualLabClassStsMigrationServiceUtil.clearService();
			VirtualLabScienceAppLinkLocalServiceUtil.clearService();

			VirtualLabScienceAppLinkServiceUtil.clearService();
			VirtualLabScienceAppLinkClassLocalServiceUtil.clearService();

			VirtualLabScienceAppLinkClassServiceUtil.clearService();
			VirtualLabUserLocalServiceUtil.clearService();

			VirtualLabUserServiceUtil.clearService();
			VirtualLabUserTempLocalServiceUtil.clearService();

			VirtualLabUserTempServiceUtil.clearService();
		}
	}
}