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

import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VirtualLabScienceAppLinkClass in entity cache.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClass
 * @generated
 */
public class VirtualLabScienceAppLinkClassCacheModel implements CacheModel<VirtualLabScienceAppLinkClass>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{scienceAppClassSeqNo=");
		sb.append(scienceAppClassSeqNo);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", scienceAppSeqNo=");
		sb.append(scienceAppSeqNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VirtualLabScienceAppLinkClass toEntityModel() {
		VirtualLabScienceAppLinkClassImpl virtualLabScienceAppLinkClassImpl = new VirtualLabScienceAppLinkClassImpl();

		virtualLabScienceAppLinkClassImpl.setScienceAppClassSeqNo(scienceAppClassSeqNo);
		virtualLabScienceAppLinkClassImpl.setClassId(classId);
		virtualLabScienceAppLinkClassImpl.setScienceAppSeqNo(scienceAppSeqNo);

		virtualLabScienceAppLinkClassImpl.resetOriginalValues();

		return virtualLabScienceAppLinkClassImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scienceAppClassSeqNo = objectInput.readLong();
		classId = objectInput.readLong();
		scienceAppSeqNo = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(scienceAppClassSeqNo);
		objectOutput.writeLong(classId);
		objectOutput.writeLong(scienceAppSeqNo);
	}

	public long scienceAppClassSeqNo;
	public long classId;
	public long scienceAppSeqNo;
}