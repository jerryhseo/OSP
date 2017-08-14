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

import org.kisti.edison.model.SimulationProject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SimulationProject in entity cache.
 *
 * @author edison
 * @see SimulationProject
 * @generated
 */
public class SimulationProjectCacheModel implements CacheModel<SimulationProject>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{simulationProjectId=");
		sb.append(simulationProjectId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", serviceLanguage=");
		sb.append(serviceLanguage);
		sb.append(", projectOpenYn=");
		sb.append(projectOpenYn);
		sb.append(", explain=");
		sb.append(explain);
		sb.append(", iconId=");
		sb.append(iconId);
		sb.append(", imageFolderId=");
		sb.append(imageFolderId);
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", insertId=");
		sb.append(insertId);
		sb.append(", insertDate=");
		sb.append(insertDate);
		sb.append(", updateId=");
		sb.append(updateId);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", templetId=");
		sb.append(templetId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SimulationProject toEntityModel() {
		SimulationProjectImpl simulationProjectImpl = new SimulationProjectImpl();

		simulationProjectImpl.setSimulationProjectId(simulationProjectId);

		if (title == null) {
			simulationProjectImpl.setTitle(StringPool.BLANK);
		}
		else {
			simulationProjectImpl.setTitle(title);
		}

		if (serviceLanguage == null) {
			simulationProjectImpl.setServiceLanguage(StringPool.BLANK);
		}
		else {
			simulationProjectImpl.setServiceLanguage(serviceLanguage);
		}

		simulationProjectImpl.setProjectOpenYn(projectOpenYn);

		if (explain == null) {
			simulationProjectImpl.setExplain(StringPool.BLANK);
		}
		else {
			simulationProjectImpl.setExplain(explain);
		}

		simulationProjectImpl.setIconId(iconId);
		simulationProjectImpl.setImageFolderId(imageFolderId);
		simulationProjectImpl.setOwnerId(ownerId);
		simulationProjectImpl.setInsertId(insertId);

		if (insertDate == Long.MIN_VALUE) {
			simulationProjectImpl.setInsertDate(null);
		}
		else {
			simulationProjectImpl.setInsertDate(new Date(insertDate));
		}

		simulationProjectImpl.setUpdateId(updateId);

		if (updateDate == Long.MIN_VALUE) {
			simulationProjectImpl.setUpdateDate(null);
		}
		else {
			simulationProjectImpl.setUpdateDate(new Date(updateDate));
		}

		simulationProjectImpl.setTempletId(templetId);

		simulationProjectImpl.resetOriginalValues();

		return simulationProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		simulationProjectId = objectInput.readLong();
		title = objectInput.readUTF();
		serviceLanguage = objectInput.readUTF();
		projectOpenYn = objectInput.readBoolean();
		explain = objectInput.readUTF();
		iconId = objectInput.readLong();
		imageFolderId = objectInput.readLong();
		ownerId = objectInput.readLong();
		insertId = objectInput.readLong();
		insertDate = objectInput.readLong();
		updateId = objectInput.readLong();
		updateDate = objectInput.readLong();
		templetId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(simulationProjectId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (serviceLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceLanguage);
		}

		objectOutput.writeBoolean(projectOpenYn);

		if (explain == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(explain);
		}

		objectOutput.writeLong(iconId);
		objectOutput.writeLong(imageFolderId);
		objectOutput.writeLong(ownerId);
		objectOutput.writeLong(insertId);
		objectOutput.writeLong(insertDate);
		objectOutput.writeLong(updateId);
		objectOutput.writeLong(updateDate);
		objectOutput.writeLong(templetId);
	}

	public long simulationProjectId;
	public String title;
	public String serviceLanguage;
	public boolean projectOpenYn;
	public String explain;
	public long iconId;
	public long imageFolderId;
	public long ownerId;
	public long insertId;
	public long insertDate;
	public long updateId;
	public long updateDate;
	public long templetId;
}