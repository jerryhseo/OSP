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

package com.kisti.osp.icecap.service.messaging;

import com.kisti.osp.icecap.service.ClpSerializer;
import com.kisti.osp.icecap.service.DataCollectionLayoutLocalServiceUtil;
import com.kisti.osp.icecap.service.DataCollectionLayoutServiceUtil;
import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.kisti.osp.icecap.service.DataCollectionServiceUtil;
import com.kisti.osp.icecap.service.DataEntryLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryProvenanceLocalServiceUtil;
import com.kisti.osp.icecap.service.DataEntryProvenanceServiceUtil;
import com.kisti.osp.icecap.service.DataEntryServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeAnalyzerServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeEditorServiceUtil;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Young-K. Suh and Jerry H. Seo
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
			DataCollectionLocalServiceUtil.clearService();

			DataCollectionServiceUtil.clearService();
			DataCollectionLayoutLocalServiceUtil.clearService();

			DataCollectionLayoutServiceUtil.clearService();
			DataEntryLocalServiceUtil.clearService();

			DataEntryServiceUtil.clearService();
			DataEntryProvenanceLocalServiceUtil.clearService();

			DataEntryProvenanceServiceUtil.clearService();
			DataTypeLocalServiceUtil.clearService();

			DataTypeServiceUtil.clearService();
			DataTypeAnalyzerLocalServiceUtil.clearService();

			DataTypeAnalyzerServiceUtil.clearService();
			DataTypeEditorLocalServiceUtil.clearService();

			DataTypeEditorServiceUtil.clearService();
			DataTypeStructureLocalServiceUtil.clearService();

			DataTypeStructureServiceUtil.clearService();
		}
	}
}