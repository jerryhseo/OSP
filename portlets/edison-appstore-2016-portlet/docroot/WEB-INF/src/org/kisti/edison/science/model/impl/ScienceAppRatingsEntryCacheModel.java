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

import org.kisti.edison.science.model.ScienceAppRatingsEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScienceAppRatingsEntry in entity cache.
 *
 * @author EDISON
 * @see ScienceAppRatingsEntry
 * @generated
 */
public class ScienceAppRatingsEntryCacheModel implements CacheModel<ScienceAppRatingsEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", scienceAppId=");
		sb.append(scienceAppId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", score=");
		sb.append(score);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScienceAppRatingsEntry toEntityModel() {
		ScienceAppRatingsEntryImpl scienceAppRatingsEntryImpl = new ScienceAppRatingsEntryImpl();

		scienceAppRatingsEntryImpl.setUserId(userId);
		scienceAppRatingsEntryImpl.setScienceAppId(scienceAppId);
		scienceAppRatingsEntryImpl.setCompanyId(companyId);

		if (userName == null) {
			scienceAppRatingsEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			scienceAppRatingsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scienceAppRatingsEntryImpl.setCreateDate(null);
		}
		else {
			scienceAppRatingsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scienceAppRatingsEntryImpl.setModifiedDate(null);
		}
		else {
			scienceAppRatingsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		scienceAppRatingsEntryImpl.setClassNameId(classNameId);
		scienceAppRatingsEntryImpl.setScore(score);

		scienceAppRatingsEntryImpl.resetOriginalValues();

		return scienceAppRatingsEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();
		scienceAppId = objectInput.readLong();
		companyId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		score = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);
		objectOutput.writeLong(scienceAppId);
		objectOutput.writeLong(companyId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(score);
	}

	public long userId;
	public long scienceAppId;
	public long companyId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long score;
}