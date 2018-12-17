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

package org.kisti.edison.content.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.content.model.Content;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Content in entity cache.
 *
 * @author EDISON
 * @see Content
 * @generated
 */
public class ContentCacheModel implements CacheModel<Content>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentSeq=");
		sb.append(contentSeq);
		sb.append(", contentDiv=");
		sb.append(contentDiv);
		sb.append(", title=");
		sb.append(title);
		sb.append(", resume=");
		sb.append(resume);
		sb.append(", contentFileNm=");
		sb.append(contentFileNm);
		sb.append(", advancedStartFileNm=");
		sb.append(advancedStartFileNm);
		sb.append(", serviceLanguage=");
		sb.append(serviceLanguage);
		sb.append(", projectYn=");
		sb.append(projectYn);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", viewCnt=");
		sb.append(viewCnt);
		sb.append(", insertId=");
		sb.append(insertId);
		sb.append(", insertDate=");
		sb.append(insertDate);
		sb.append(", updateId=");
		sb.append(updateId);
		sb.append(", updateDate=");
		sb.append(updateDate);
		sb.append(", version=");
		sb.append(version);
		sb.append(", openYn=");
		sb.append(openYn);
		sb.append(", coverImageFileEntryId=");
		sb.append(coverImageFileEntryId);
		sb.append(", contentUrl=");
		sb.append(contentUrl);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Content toEntityModel() {
		ContentImpl contentImpl = new ContentImpl();

		if (uuid == null) {
			contentImpl.setUuid(StringPool.BLANK);
		}
		else {
			contentImpl.setUuid(uuid);
		}

		contentImpl.setContentSeq(contentSeq);
		contentImpl.setContentDiv(contentDiv);

		if (title == null) {
			contentImpl.setTitle(StringPool.BLANK);
		}
		else {
			contentImpl.setTitle(title);
		}

		if (resume == null) {
			contentImpl.setResume(StringPool.BLANK);
		}
		else {
			contentImpl.setResume(resume);
		}

		if (contentFileNm == null) {
			contentImpl.setContentFileNm(StringPool.BLANK);
		}
		else {
			contentImpl.setContentFileNm(contentFileNm);
		}

		if (advancedStartFileNm == null) {
			contentImpl.setAdvancedStartFileNm(StringPool.BLANK);
		}
		else {
			contentImpl.setAdvancedStartFileNm(advancedStartFileNm);
		}

		if (serviceLanguage == null) {
			contentImpl.setServiceLanguage(StringPool.BLANK);
		}
		else {
			contentImpl.setServiceLanguage(serviceLanguage);
		}

		if (projectYn == null) {
			contentImpl.setProjectYn(StringPool.BLANK);
		}
		else {
			contentImpl.setProjectYn(projectYn);
		}

		contentImpl.setProjectId(projectId);
		contentImpl.setViewCnt(viewCnt);
		contentImpl.setInsertId(insertId);

		if (insertDate == Long.MIN_VALUE) {
			contentImpl.setInsertDate(null);
		}
		else {
			contentImpl.setInsertDate(new Date(insertDate));
		}

		contentImpl.setUpdateId(updateId);

		if (updateDate == Long.MIN_VALUE) {
			contentImpl.setUpdateDate(null);
		}
		else {
			contentImpl.setUpdateDate(new Date(updateDate));
		}

		if (version == null) {
			contentImpl.setVersion(StringPool.BLANK);
		}
		else {
			contentImpl.setVersion(version);
		}

		if (openYn == null) {
			contentImpl.setOpenYn(StringPool.BLANK);
		}
		else {
			contentImpl.setOpenYn(openYn);
		}

		contentImpl.setCoverImageFileEntryId(coverImageFileEntryId);

		if (contentUrl == null) {
			contentImpl.setContentUrl(StringPool.BLANK);
		}
		else {
			contentImpl.setContentUrl(contentUrl);
		}

		contentImpl.resetOriginalValues();

		return contentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		contentSeq = objectInput.readLong();
		contentDiv = objectInput.readLong();
		title = objectInput.readUTF();
		resume = objectInput.readUTF();
		contentFileNm = objectInput.readUTF();
		advancedStartFileNm = objectInput.readUTF();
		serviceLanguage = objectInput.readUTF();
		projectYn = objectInput.readUTF();
		projectId = objectInput.readLong();
		viewCnt = objectInput.readLong();
		insertId = objectInput.readLong();
		insertDate = objectInput.readLong();
		updateId = objectInput.readLong();
		updateDate = objectInput.readLong();
		version = objectInput.readUTF();
		openYn = objectInput.readUTF();
		coverImageFileEntryId = objectInput.readLong();
		contentUrl = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(contentSeq);
		objectOutput.writeLong(contentDiv);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (resume == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(resume);
		}

		if (contentFileNm == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contentFileNm);
		}

		if (advancedStartFileNm == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(advancedStartFileNm);
		}

		if (serviceLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serviceLanguage);
		}

		if (projectYn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectYn);
		}

		objectOutput.writeLong(projectId);
		objectOutput.writeLong(viewCnt);
		objectOutput.writeLong(insertId);
		objectOutput.writeLong(insertDate);
		objectOutput.writeLong(updateId);
		objectOutput.writeLong(updateDate);

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (openYn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(openYn);
		}

		objectOutput.writeLong(coverImageFileEntryId);

		if (contentUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contentUrl);
		}
	}

	public String uuid;
	public long contentSeq;
	public long contentDiv;
	public String title;
	public String resume;
	public String contentFileNm;
	public String advancedStartFileNm;
	public String serviceLanguage;
	public String projectYn;
	public long projectId;
	public long viewCnt;
	public long insertId;
	public long insertDate;
	public long updateId;
	public long updateDate;
	public String version;
	public String openYn;
	public long coverImageFileEntryId;
	public String contentUrl;
}