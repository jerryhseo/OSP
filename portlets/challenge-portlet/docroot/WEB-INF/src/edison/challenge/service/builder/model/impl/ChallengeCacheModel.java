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

import edison.challenge.service.builder.model.Challenge;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Challenge in entity cache.
 *
 * @author kyj
 * @see Challenge
 * @generated
 */
public class ChallengeCacheModel implements CacheModel<Challenge>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{challengeid=");
		sb.append(challengeid);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Challenge toEntityModel() {
		ChallengeImpl challengeImpl = new ChallengeImpl();

		challengeImpl.setChallengeid(challengeid);

		if (name == null) {
			challengeImpl.setName(StringPool.BLANK);
		}
		else {
			challengeImpl.setName(name);
		}

		if (description == null) {
			challengeImpl.setDescription(StringPool.BLANK);
		}
		else {
			challengeImpl.setDescription(description);
		}

		challengeImpl.resetOriginalValues();

		return challengeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		challengeid = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(challengeid);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long challengeid;
	public String name;
	public String description;
}