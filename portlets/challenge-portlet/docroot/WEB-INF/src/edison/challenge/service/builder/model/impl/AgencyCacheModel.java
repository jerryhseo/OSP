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

package edison.challenge.service.builder.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import edison.challenge.service.builder.model.Agency;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Agency in entity cache.
 *
 * @author kyj
 * @see Agency
 * @generated
 */
public class AgencyCacheModel implements CacheModel<Agency>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{agencyid=");
		sb.append(agencyid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", level=");
		sb.append(level);
		sb.append(", url=");
		sb.append(url);
		sb.append(", childid=");
		sb.append(childid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Agency toEntityModel() {
		AgencyImpl agencyImpl = new AgencyImpl();

		agencyImpl.setAgencyid(agencyid);

		if (name == null) {
			agencyImpl.setName(StringPool.BLANK);
		}
		else {
			agencyImpl.setName(name);
		}

		if (level == null) {
			agencyImpl.setLevel(StringPool.BLANK);
		}
		else {
			agencyImpl.setLevel(level);
		}

		if (url == null) {
			agencyImpl.setUrl(StringPool.BLANK);
		}
		else {
			agencyImpl.setUrl(url);
		}

		agencyImpl.setChildid(childid);

		agencyImpl.resetOriginalValues();

		return agencyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		agencyid = objectInput.readLong();
		name = objectInput.readUTF();
		level = objectInput.readUTF();
		url = objectInput.readUTF();
		childid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(agencyid);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (level == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(level);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(childid);
	}

	public long agencyid;
	public String name;
	public String level;
	public String url;
	public long childid;
}