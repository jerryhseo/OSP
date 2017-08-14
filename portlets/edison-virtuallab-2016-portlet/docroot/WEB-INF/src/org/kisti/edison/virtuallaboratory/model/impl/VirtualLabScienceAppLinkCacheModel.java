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

package org.kisti.edison.virtuallaboratory.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VirtualLabScienceAppLink in entity cache.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLink
 * @generated
 */
public class VirtualLabScienceAppLinkCacheModel implements CacheModel<VirtualLabScienceAppLink>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{scienceAppSeqNo=");
		sb.append(scienceAppSeqNo);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", virtualLabId=");
		sb.append(virtualLabId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VirtualLabScienceAppLink toEntityModel() {
		VirtualLabScienceAppLinkImpl virtualLabScienceAppLinkImpl = new VirtualLabScienceAppLinkImpl();

		virtualLabScienceAppLinkImpl.setScienceAppSeqNo(scienceAppSeqNo);
		virtualLabScienceAppLinkImpl.setScienceAppId(scienceAppId);
		virtualLabScienceAppLinkImpl.setVirtualLabId(virtualLabId);

		virtualLabScienceAppLinkImpl.resetOriginalValues();

		return virtualLabScienceAppLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppSeqNo = objectInput.readLong();
		scienceAppId = objectInput.readLong();
		virtualLabId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppSeqNo);
		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(virtualLabId);
	}

	public long scienceAppSeqNo;
	public long scienceAppId;
	public long virtualLabId;
}