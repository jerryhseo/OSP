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

package org.kisti.edison.virtuallaboratory.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.virtuallaboratory.model.Professor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Professor in entity cache.
 *
 * @author EDISON
 * @see Professor
 * @generated
 */
public class ProfessorCacheModel implements CacheModel<Professor>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{professorSeq=");
		sb.append(professorSeq);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", record=");
		sb.append(record);
		sb.append(", paper=");
		sb.append(paper);
		sb.append(", homepage=");
		sb.append(homepage);
		sb.append(", introduce=");
		sb.append(introduce);
		sb.append(", phone=");
		sb.append(phone);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Professor toEntityModel() {
		ProfessorImpl professorImpl = new ProfessorImpl();

		professorImpl.setProfessorSeq(professorSeq);
		professorImpl.setUserId(userId);

		if (record == null) {
			professorImpl.setRecord(StringPool.BLANK);
		}
		else {
			professorImpl.setRecord(record);
		}

		if (paper == null) {
			professorImpl.setPaper(StringPool.BLANK);
		}
		else {
			professorImpl.setPaper(paper);
		}

		if (homepage == null) {
			professorImpl.setHomepage(StringPool.BLANK);
		}
		else {
			professorImpl.setHomepage(homepage);
		}

		if (introduce == null) {
			professorImpl.setIntroduce(StringPool.BLANK);
		}
		else {
			professorImpl.setIntroduce(introduce);
		}

		if (phone == null) {
			professorImpl.setPhone(StringPool.BLANK);
		}
		else {
			professorImpl.setPhone(phone);
		}

		professorImpl.resetOriginalValues();

		return professorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		professorSeq = objectInput.readLong();
		userId = objectInput.readLong();
		record = objectInput.readUTF();
		paper = objectInput.readUTF();
		homepage = objectInput.readUTF();
		introduce = objectInput.readUTF();
		phone = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(professorSeq);
		objectOutput.writeLong(userId);

		if (record == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(record);
		}

		if (paper == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paper);
		}

		if (homepage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(homepage);
		}

		if (introduce == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(introduce);
		}

		if (phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phone);
		}
	}

	public long professorSeq;
	public long userId;
	public String record;
	public String paper;
	public String homepage;
	public String introduce;
	public String phone;
}