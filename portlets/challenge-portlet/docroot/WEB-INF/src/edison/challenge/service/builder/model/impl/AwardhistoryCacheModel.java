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

import edison.challenge.service.builder.model.Awardhistory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Awardhistory in entity cache.
 *
 * @author kyj
 * @see Awardhistory
 * @generated
 */
public class AwardhistoryCacheModel implements CacheModel<Awardhistory>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{awardhistoryid=");
		sb.append(awardhistoryid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", prize=");
		sb.append(prize);
		sb.append(", chTeamid=");
		sb.append(chTeamid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Awardhistory toEntityModel() {
		AwardhistoryImpl awardhistoryImpl = new AwardhistoryImpl();

		awardhistoryImpl.setAwardhistoryid(awardhistoryid);

		if (name == null) {
			awardhistoryImpl.setName(StringPool.BLANK);
		}
		else {
			awardhistoryImpl.setName(name);
		}

		if (prize == null) {
			awardhistoryImpl.setPrize(StringPool.BLANK);
		}
		else {
			awardhistoryImpl.setPrize(prize);
		}

		awardhistoryImpl.setChTeamid(chTeamid);

		awardhistoryImpl.resetOriginalValues();

		return awardhistoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		awardhistoryid = objectInput.readLong();
		name = objectInput.readUTF();
		prize = objectInput.readUTF();
		chTeamid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(awardhistoryid);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (prize == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(prize);
		}

		objectOutput.writeLong(chTeamid);
	}

	public long awardhistoryid;
	public String name;
	public String prize;
	public long chTeamid;
}