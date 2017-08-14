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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.model.RequestMember;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RequestMember in entity cache.
 *
 * @author edison
 * @see RequestMember
 * @generated
 */
public class RequestMemberCacheModel implements CacheModel<RequestMember>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{requestSeq=");
		sb.append(requestSeq);
		sb.append(", simulationProjectId=");
		sb.append(simulationProjectId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", requestDate=");
		sb.append(requestDate);
		sb.append(", processDate=");
		sb.append(processDate);
		sb.append(", requestState=");
		sb.append(requestState);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RequestMember toEntityModel() {
		RequestMemberImpl requestMemberImpl = new RequestMemberImpl();

		requestMemberImpl.setRequestSeq(requestSeq);
		requestMemberImpl.setSimulationProjectId(simulationProjectId);
		requestMemberImpl.setUserId(userId);

		if (requestDate == Long.MIN_VALUE) {
			requestMemberImpl.setRequestDate(null);
		}
		else {
			requestMemberImpl.setRequestDate(new Date(requestDate));
		}

		if (processDate == Long.MIN_VALUE) {
			requestMemberImpl.setProcessDate(null);
		}
		else {
			requestMemberImpl.setProcessDate(new Date(processDate));
		}

		if (requestState == null) {
			requestMemberImpl.setRequestState(StringPool.BLANK);
		}
		else {
			requestMemberImpl.setRequestState(requestState);
		}

		requestMemberImpl.resetOriginalValues();

		return requestMemberImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		requestSeq = objectInput.readLong();
		simulationProjectId = objectInput.readLong();
		userId = objectInput.readLong();
		requestDate = objectInput.readLong();
		processDate = objectInput.readLong();
		requestState = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(requestSeq);
		objectOutput.writeLong(simulationProjectId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(requestDate);
		objectOutput.writeLong(processDate);

		if (requestState == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(requestState);
		}
	}

	public long requestSeq;
	public long simulationProjectId;
	public Long userId;
	public long requestDate;
	public long processDate;
	public String requestState;
}