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

package org.kisti.edison.login.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.login.model.LoginLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoginLog in entity cache.
 *
 * @author yjpark
 * @see LoginLog
 * @generated
 */
public class LoginLogCacheModel implements CacheModel<LoginLog>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{id=");
		sb.append(id);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoginLog toEntityModel() {
		LoginLogImpl loginLogImpl = new LoginLogImpl();

		loginLogImpl.setId(id);

		if (createDate == Long.MIN_VALUE) {
			loginLogImpl.setCreateDate(null);
		}
		else {
			loginLogImpl.setCreateDate(new Date(createDate));
		}

		loginLogImpl.setUserId(userId);

		loginLogImpl.resetOriginalValues();

		return loginLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		createDate = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(userId);
	}

	public long id;
	public long createDate;
	public long userId;
}