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

package org.kisti.edison.search.service.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.kisti.edison.search.service.model.SearchCondition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SearchCondition in entity cache.
 *
 * @author yjpark
 * @see SearchCondition
 * @generated
 */
public class SearchConditionCacheModel implements CacheModel<SearchCondition>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{id=");
		sb.append(id);
		sb.append(", searchKeyword=");
		sb.append(searchKeyword);
		sb.append(", areaScienceApp=");
		sb.append(areaScienceApp);
		sb.append(", areaContents=");
		sb.append(areaContents);
		sb.append(", areaSimulationProject=");
		sb.append(areaSimulationProject);
		sb.append(", areaScienceData=");
		sb.append(areaScienceData);
		sb.append(", parentCategory=");
		sb.append(parentCategory);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", companyGroupId=");
		sb.append(companyGroupId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", currentPage=");
		sb.append(currentPage);
		sb.append(", listSize=");
		sb.append(listSize);
		sb.append(", blockSize=");
		sb.append(blockSize);
		sb.append(", classnote=");
		sb.append(classnote);
		sb.append(", manual=");
		sb.append(manual);
		sb.append(", reference=");
		sb.append(reference);
		sb.append(", advanced=");
		sb.append(advanced);
		sb.append(", Solver=");
		sb.append(Solver);
		sb.append(", Converter=");
		sb.append(Converter);
		sb.append(", Editor=");
		sb.append(Editor);
		sb.append(", Analyzer=");
		sb.append(Analyzer);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SearchCondition toEntityModel() {
		SearchConditionImpl searchConditionImpl = new SearchConditionImpl();

		searchConditionImpl.setId(id);

		if (searchKeyword == null) {
			searchConditionImpl.setSearchKeyword(StringPool.BLANK);
		}
		else {
			searchConditionImpl.setSearchKeyword(searchKeyword);
		}

		searchConditionImpl.setAreaScienceApp(areaScienceApp);
		searchConditionImpl.setAreaContents(areaContents);
		searchConditionImpl.setAreaSimulationProject(areaSimulationProject);
		searchConditionImpl.setAreaScienceData(areaScienceData);
		searchConditionImpl.setParentCategory(parentCategory);
		searchConditionImpl.setCategoryId(categoryId);
		searchConditionImpl.setCompanyGroupId(companyGroupId);
		searchConditionImpl.setGroupId(groupId);
		searchConditionImpl.setCurrentPage(currentPage);
		searchConditionImpl.setListSize(listSize);
		searchConditionImpl.setBlockSize(blockSize);
		searchConditionImpl.setClassnote(classnote);
		searchConditionImpl.setManual(manual);
		searchConditionImpl.setReference(reference);
		searchConditionImpl.setAdvanced(advanced);
		searchConditionImpl.setSolver(Solver);
		searchConditionImpl.setConverter(Converter);
		searchConditionImpl.setEditor(Editor);
		searchConditionImpl.setAnalyzer(Analyzer);

		searchConditionImpl.resetOriginalValues();

		return searchConditionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		searchKeyword = objectInput.readUTF();
		areaScienceApp = objectInput.readBoolean();
		areaContents = objectInput.readBoolean();
		areaSimulationProject = objectInput.readBoolean();
		areaScienceData = objectInput.readBoolean();
		parentCategory = objectInput.readBoolean();
		categoryId = objectInput.readLong();
		companyGroupId = objectInput.readLong();
		groupId = objectInput.readLong();
		currentPage = objectInput.readInt();
		listSize = objectInput.readInt();
		blockSize = objectInput.readInt();
		classnote = objectInput.readBoolean();
		manual = objectInput.readBoolean();
		reference = objectInput.readBoolean();
		advanced = objectInput.readBoolean();
		Solver = objectInput.readBoolean();
		Converter = objectInput.readBoolean();
		Editor = objectInput.readBoolean();
		Analyzer = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);

		if (searchKeyword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(searchKeyword);
		}

		objectOutput.writeBoolean(areaScienceApp);
		objectOutput.writeBoolean(areaContents);
		objectOutput.writeBoolean(areaSimulationProject);
		objectOutput.writeBoolean(areaScienceData);
		objectOutput.writeBoolean(parentCategory);
		objectOutput.writeLong(categoryId);
		objectOutput.writeLong(companyGroupId);
		objectOutput.writeLong(groupId);
		objectOutput.writeInt(currentPage);
		objectOutput.writeInt(listSize);
		objectOutput.writeInt(blockSize);
		objectOutput.writeBoolean(classnote);
		objectOutput.writeBoolean(manual);
		objectOutput.writeBoolean(reference);
		objectOutput.writeBoolean(advanced);
		objectOutput.writeBoolean(Solver);
		objectOutput.writeBoolean(Converter);
		objectOutput.writeBoolean(Editor);
		objectOutput.writeBoolean(Analyzer);
	}

	public long id;
	public String searchKeyword;
	public boolean areaScienceApp;
	public boolean areaContents;
	public boolean areaSimulationProject;
	public boolean areaScienceData;
	public boolean parentCategory;
	public long categoryId;
	public long companyGroupId;
	public long groupId;
	public int currentPage;
	public int listSize;
	public int blockSize;
	public boolean classnote;
	public boolean manual;
	public boolean reference;
	public boolean advanced;
	public boolean Solver;
	public boolean Converter;
	public boolean Editor;
	public boolean Analyzer;
}