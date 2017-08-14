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

package org.kisti.edison.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.model.SimProScienceAppLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SimProScienceAppLink in entity cache.
 *
 * @author edison
 * @see SimProScienceAppLink
 * @generated
 */
public class SimProScienceAppLinkCacheModel implements CacheModel<SimProScienceAppLink>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{simProScienceAppLinkId=");
		sb.append(simProScienceAppLinkId);
		sb.append(", simulationProjectId=");
		sb.append(simulationProjectId);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SimProScienceAppLink toEntityModel() {
		SimProScienceAppLinkImpl simProScienceAppLinkImpl = new SimProScienceAppLinkImpl();

		simProScienceAppLinkImpl.setSimProScienceAppLinkId(simProScienceAppLinkId);
		simProScienceAppLinkImpl.setSimulationProjectId(simulationProjectId);
		simProScienceAppLinkImpl.setScienceAppId(scienceAppId);

		simProScienceAppLinkImpl.resetOriginalValues();

		return simProScienceAppLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simProScienceAppLinkId = objectInput.readLong();
		simulationProjectId = objectInput.readLong();
		scienceAppId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(simProScienceAppLinkId);
		objectOutput.writeLong(simulationProjectId);
		objectOutput.writeLong(scienceAppId);
	}

	public long simProScienceAppLinkId;
	public long simulationProjectId;
	public Long scienceAppId;
}