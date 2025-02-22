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

package org.kisti.edison.science.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.science.model.ScienceAppLogPorts;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ScienceAppLogPorts in entity cache.
 *
 * @author EDISON
 * @see ScienceAppLogPorts
 * @generated
 */
public class ScienceAppLogPortsCacheModel implements CacheModel<ScienceAppLogPorts>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", logPorts=");
		sb.append(logPorts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceAppLogPorts toEntityModel() {
		ScienceAppLogPortsImpl scienceAppLogPortsImpl = new ScienceAppLogPortsImpl();

		scienceAppLogPortsImpl.setScienceAppId(scienceAppId);

		if (logPorts == null) {
			scienceAppLogPortsImpl.setLogPorts(StringPool.BLANK);
		}
		else {
			scienceAppLogPortsImpl.setLogPorts(logPorts);
		}

		scienceAppLogPortsImpl.resetOriginalValues();

		return scienceAppLogPortsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppId = objectInput.readLong();
		logPorts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppId);

		if (logPorts == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(logPorts);
		}
	}

	public long scienceAppId;
	public String logPorts;
}