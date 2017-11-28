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

import edison.challenge.service.builder.model.ChildChallenge;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChildChallenge in entity cache.
 *
 * @author kyj
 * @see ChildChallenge
 * @generated
 */
public class ChildChallengeCacheModel implements CacheModel<ChildChallenge>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{childid=");
		sb.append(childid);
		sb.append(", number=");
		sb.append(number);
		sb.append(", presentationDay=");
		sb.append(presentationDay);
		sb.append(", paperStartDay=");
		sb.append(paperStartDay);
		sb.append(", paperEndDay=");
		sb.append(paperEndDay);
		sb.append(", evaluationStartDay=");
		sb.append(evaluationStartDay);
		sb.append(", evaluationEndDay=");
		sb.append(evaluationEndDay);
		sb.append(", challengeStartDay=");
		sb.append(challengeStartDay);
		sb.append(", challengeEndDay=");
		sb.append(challengeEndDay);
		sb.append(", status=");
		sb.append(status);
		sb.append(", challengeid=");
		sb.append(challengeid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChildChallenge toEntityModel() {
		ChildChallengeImpl childChallengeImpl = new ChildChallengeImpl();

		childChallengeImpl.setChildid(childid);
		childChallengeImpl.setNumber(number);

		if (presentationDay == Long.MIN_VALUE) {
			childChallengeImpl.setPresentationDay(null);
		}
		else {
			childChallengeImpl.setPresentationDay(new Date(presentationDay));
		}

		if (paperStartDay == Long.MIN_VALUE) {
			childChallengeImpl.setPaperStartDay(null);
		}
		else {
			childChallengeImpl.setPaperStartDay(new Date(paperStartDay));
		}

		if (paperEndDay == Long.MIN_VALUE) {
			childChallengeImpl.setPaperEndDay(null);
		}
		else {
			childChallengeImpl.setPaperEndDay(new Date(paperEndDay));
		}

		if (evaluationStartDay == Long.MIN_VALUE) {
			childChallengeImpl.setEvaluationStartDay(null);
		}
		else {
			childChallengeImpl.setEvaluationStartDay(new Date(
					evaluationStartDay));
		}

		if (evaluationEndDay == Long.MIN_VALUE) {
			childChallengeImpl.setEvaluationEndDay(null);
		}
		else {
			childChallengeImpl.setEvaluationEndDay(new Date(evaluationEndDay));
		}

		if (challengeStartDay == Long.MIN_VALUE) {
			childChallengeImpl.setChallengeStartDay(null);
		}
		else {
			childChallengeImpl.setChallengeStartDay(new Date(challengeStartDay));
		}

		if (challengeEndDay == Long.MIN_VALUE) {
			childChallengeImpl.setChallengeEndDay(null);
		}
		else {
			childChallengeImpl.setChallengeEndDay(new Date(challengeEndDay));
		}

		if (status == null) {
			childChallengeImpl.setStatus(StringPool.BLANK);
		}
		else {
			childChallengeImpl.setStatus(status);
		}

		childChallengeImpl.setChallengeid(challengeid);

		childChallengeImpl.resetOriginalValues();

		return childChallengeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		childid = objectInput.readLong();
		number = objectInput.readInt();
		presentationDay = objectInput.readLong();
		paperStartDay = objectInput.readLong();
		paperEndDay = objectInput.readLong();
		evaluationStartDay = objectInput.readLong();
		evaluationEndDay = objectInput.readLong();
		challengeStartDay = objectInput.readLong();
		challengeEndDay = objectInput.readLong();
		status = objectInput.readUTF();
		challengeid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(childid);
		objectOutput.writeInt(number);
		objectOutput.writeLong(presentationDay);
		objectOutput.writeLong(paperStartDay);
		objectOutput.writeLong(paperEndDay);
		objectOutput.writeLong(evaluationStartDay);
		objectOutput.writeLong(evaluationEndDay);
		objectOutput.writeLong(challengeStartDay);
		objectOutput.writeLong(challengeEndDay);

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(challengeid);
	}

	public long childid;
	public int number;
	public long presentationDay;
	public long paperStartDay;
	public long paperEndDay;
	public long evaluationStartDay;
	public long evaluationEndDay;
	public long challengeStartDay;
	public long challengeEndDay;
	public String status;
	public long challengeid;
}