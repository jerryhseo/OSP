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

package org.kisti.edison.simulation.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.simulation.model.BatchMonitoring;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BatchMonitoring in entity cache.
 *
 * @author edison
 * @see BatchMonitoring
 * @generated
 */
public class BatchMonitoringCacheModel implements CacheModel<BatchMonitoring>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{batSeqNo=");
		sb.append(batSeqNo);
		sb.append(", batDiv=");
		sb.append(batDiv);
		sb.append(", manualYN=");
		sb.append(manualYN);
		sb.append(", status=");
		sb.append(status);
		sb.append(", message=");
		sb.append(message);
		sb.append(", insertId=");
		sb.append(insertId);
		sb.append(", exeDate=");
		sb.append(exeDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BatchMonitoring toEntityModel() {
		BatchMonitoringImpl batchMonitoringImpl = new BatchMonitoringImpl();

		batchMonitoringImpl.setBatSeqNo(batSeqNo);

		if (batDiv == null) {
			batchMonitoringImpl.setBatDiv(StringPool.BLANK);
		}
		else {
			batchMonitoringImpl.setBatDiv(batDiv);
		}

		if (manualYN == null) {
			batchMonitoringImpl.setManualYN(StringPool.BLANK);
		}
		else {
			batchMonitoringImpl.setManualYN(manualYN);
		}

		if (status == null) {
			batchMonitoringImpl.setStatus(StringPool.BLANK);
		}
		else {
			batchMonitoringImpl.setStatus(status);
		}

		if (message == null) {
			batchMonitoringImpl.setMessage(StringPool.BLANK);
		}
		else {
			batchMonitoringImpl.setMessage(message);
		}

		batchMonitoringImpl.setInsertId(insertId);

		if (exeDate == Long.MIN_VALUE) {
			batchMonitoringImpl.setExeDate(null);
		}
		else {
			batchMonitoringImpl.setExeDate(new Date(exeDate));
		}

		batchMonitoringImpl.resetOriginalValues();

		return batchMonitoringImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		batSeqNo = objectInput.readLong();
		batDiv = objectInput.readUTF();
		manualYN = objectInput.readUTF();
		status = objectInput.readUTF();
		message = objectInput.readUTF();
		insertId = objectInput.readLong();
		exeDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(batSeqNo);

		if (batDiv == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batDiv);
		}

		if (manualYN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manualYN);
		}

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (message == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(message);
		}

		objectOutput.writeLong(insertId);
		objectOutput.writeLong(exeDate);
	}

	public long batSeqNo;
	public String batDiv;
	public String manualYN;
	public String status;
	public String message;
	public long insertId;
	public long exeDate;
}