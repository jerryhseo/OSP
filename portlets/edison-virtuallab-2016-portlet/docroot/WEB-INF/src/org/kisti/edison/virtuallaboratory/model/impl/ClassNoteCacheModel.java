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

import org.kisti.edison.virtuallaboratory.model.ClassNote;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ClassNote in entity cache.
 *
 * @author EDISON
 * @see ClassNote
 * @generated
 */
public class ClassNoteCacheModel implements CacheModel<ClassNote>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{classNoteSeq=");
		sb.append(classNoteSeq);
		sb.append(", classId=");
		sb.append(classId);
		sb.append(", contentSeq=");
		sb.append(contentSeq);
		sb.append(", isContent=");
		sb.append(isContent);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", insertDate=");
		sb.append(insertDate);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", insertId=");
		sb.append(insertId);
		sb.append(", updateId=");
		sb.append(updateId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ClassNote toEntityModel() {
		ClassNoteImpl classNoteImpl = new ClassNoteImpl();

		classNoteImpl.setClassNoteSeq(classNoteSeq);
		classNoteImpl.setClassId(classId);
		classNoteImpl.setContentSeq(contentSeq);

		if (isContent == null) {
			classNoteImpl.setIsContent(StringPool.BLANK);
		}
		else {
			classNoteImpl.setIsContent(isContent);
		}

		classNoteImpl.setFileEntryId(fileEntryId);

		if (description == null) {
			classNoteImpl.setDescription(StringPool.BLANK);
		}
		else {
			classNoteImpl.setDescription(description);
		}

		if (insertDate == Long.MIN_VALUE) {
			classNoteImpl.setInsertDate(null);
		}
		else {
			classNoteImpl.setInsertDate(new Date(insertDate));
		}

		if (updateDate == Long.MIN_VALUE) {
			classNoteImpl.setUpdateDate(null);
		}
		else {
			classNoteImpl.setUpdateDate(new Date(updateDate));
		}

		classNoteImpl.setInsertId(insertId);
		classNoteImpl.setUpdateId(updateId);

		classNoteImpl.resetOriginalValues();

		return classNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		classNoteSeq = objectInput.readLong();
		classId = objectInput.readLong();
		contentSeq = objectInput.readLong();
		isContent = objectInput.readUTF();
		fileEntryId = objectInput.readLong();
		description = objectInput.readUTF();
		insertDate = objectInput.readLong();
		updateDate = objectInput.readLong();
		insertId = objectInput.readLong();
		updateId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(classNoteSeq);
		objectOutput.writeLong(classId);
		objectOutput.writeLong(contentSeq);

		if (isContent == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(isContent);
		}

		objectOutput.writeLong(fileEntryId);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(insertDate);
		objectOutput.writeLong(updateDate);
		objectOutput.writeLong(insertId);
		objectOutput.writeLong(updateId);
	}

	public long classNoteSeq;
	public long classId;
	public long contentSeq;
	public String isContent;
	public long fileEntryId;
	public String description;
	public long insertDate;
	public long updateDate;
	public long insertId;
	public long updateId;
}