/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.kisti.edison.bestsimulation.service.ClpSerializer;
import org.kisti.edison.bestsimulation.service.ScienceAppExecuteLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.ScienceAppExecuteServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationExeStsMigrationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationExeStsMigrationServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobDataLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobDataServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobStatusLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationJobStatusServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.SimulationServiceUtil;
import org.kisti.edison.bestsimulation.service.UniversityExecuteLocalServiceUtil;
import org.kisti.edison.bestsimulation.service.UniversityExecuteServiceUtil;

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
			ScienceAppExecuteLocalServiceUtil.clearService();

			ScienceAppExecuteServiceUtil.clearService();
			SimulationLocalServiceUtil.clearService();

			SimulationServiceUtil.clearService();
			SimulationExeStsMigrationLocalServiceUtil.clearService();

			SimulationExeStsMigrationServiceUtil.clearService();
			SimulationJobLocalServiceUtil.clearService();

			SimulationJobServiceUtil.clearService();
			SimulationJobDataLocalServiceUtil.clearService();

			SimulationJobDataServiceUtil.clearService();
			SimulationJobStatusLocalServiceUtil.clearService();

			SimulationJobStatusServiceUtil.clearService();
			UniversityExecuteLocalServiceUtil.clearService();

			UniversityExecuteServiceUtil.clearService();
		}
	}
}