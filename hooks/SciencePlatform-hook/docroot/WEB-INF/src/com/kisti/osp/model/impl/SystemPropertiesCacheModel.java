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

package com.kisti.osp.model.impl;

import com.kisti.osp.model.SystemProperties;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SystemProperties in entity cache.
 *
 * @author Jerry h. Seo
 * @see SystemProperties
 * @generated
 */
public class SystemPropertiesCacheModel implements CacheModel<SystemProperties>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{propertyName=");
		sb.append(propertyName);
		sb.append(", propertyValue=");
		sb.append(propertyValue);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SystemProperties toEntityModel() {
		SystemPropertiesImpl systemPropertiesImpl = new SystemPropertiesImpl();

		if (propertyName == null) {
			systemPropertiesImpl.setPropertyName(StringPool.BLANK);
		}
		else {
			systemPropertiesImpl.setPropertyName(propertyName);
		}

		if (propertyValue == null) {
			systemPropertiesImpl.setPropertyValue(StringPool.BLANK);
		}
		else {
			systemPropertiesImpl.setPropertyValue(propertyValue);
		}

		systemPropertiesImpl.setActive(active);

		systemPropertiesImpl.resetOriginalValues();

		return systemPropertiesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		propertyName = objectInput.readUTF();
		propertyValue = objectInput.readUTF();
		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (propertyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyName);
		}

		if (propertyValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyValue);
		}

		objectOutput.writeBoolean(active);
	}

	public String propertyName;
	public String propertyValue;
	public boolean active;
}