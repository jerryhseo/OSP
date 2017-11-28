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

import edison.challenge.service.builder.model.Award;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Award in entity cache.
 *
 * @author kyj
 * @see Award
 * @generated
 */
public class AwardCacheModel implements CacheModel<Award>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{awardid=");
		sb.append(awardid);
		sb.append(", awardGradeName=");
		sb.append(awardGradeName);
		sb.append(", awardName=");
		sb.append(awardName);
		sb.append(", prize=");
		sb.append(prize);
		sb.append(", number=");
		sb.append(number);
		sb.append(", childid=");
		sb.append(childid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Award toEntityModel() {
		AwardImpl awardImpl = new AwardImpl();

		awardImpl.setAwardid(awardid);

		if (awardGradeName == null) {
			awardImpl.setAwardGradeName(StringPool.BLANK);
		}
		else {
			awardImpl.setAwardGradeName(awardGradeName);
		}

		if (awardName == null) {
			awardImpl.setAwardName(StringPool.BLANK);
		}
		else {
			awardImpl.setAwardName(awardName);
		}

		if (prize == null) {
			awardImpl.setPrize(StringPool.BLANK);
		}
		else {
			awardImpl.setPrize(prize);
		}

		if (number == null) {
			awardImpl.setNumber(StringPool.BLANK);
		}
		else {
			awardImpl.setNumber(number);
		}

		awardImpl.setChildid(childid);

		awardImpl.resetOriginalValues();

		return awardImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		awardid = objectInput.readLong();
		awardGradeName = objectInput.readUTF();
		awardName = objectInput.readUTF();
		prize = objectInput.readUTF();
		number = objectInput.readUTF();
		childid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(awardid);

		if (awardGradeName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(awardGradeName);
		}

		if (awardName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(awardName);
		}

		if (prize == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(prize);
		}

		if (number == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(number);
		}

		objectOutput.writeLong(childid);
	}

	public long awardid;
	public String awardGradeName;
	public String awardName;
	public String prize;
	public String number;
	public long childid;
}