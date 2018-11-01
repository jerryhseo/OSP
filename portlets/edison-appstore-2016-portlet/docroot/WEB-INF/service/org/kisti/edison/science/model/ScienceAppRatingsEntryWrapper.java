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

package org.kisti.edison.science.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScienceAppRatingsEntry}.
 * </p>
 *
 * @author EDISON
 * @see ScienceAppRatingsEntry
 * @generated
 */
public class ScienceAppRatingsEntryWrapper implements ScienceAppRatingsEntry,
	ModelWrapper<ScienceAppRatingsEntry> {
	public ScienceAppRatingsEntryWrapper(
		ScienceAppRatingsEntry scienceAppRatingsEntry) {
		_scienceAppRatingsEntry = scienceAppRatingsEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppRatingsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppRatingsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("score", getScore());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long score = (Long)attributes.get("score");

		if (score != null) {
			setScore(score);
		}
	}

	/**
	* Returns the primary key of this science app ratings entry.
	*
	* @return the primary key of this science app ratings entry
	*/
	@Override
	public org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK getPrimaryKey() {
		return _scienceAppRatingsEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this science app ratings entry.
	*
	* @param primaryKey the primary key of this science app ratings entry
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppRatingsEntryPK primaryKey) {
		_scienceAppRatingsEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user ID of this science app ratings entry.
	*
	* @return the user ID of this science app ratings entry
	*/
	@Override
	public long getUserId() {
		return _scienceAppRatingsEntry.getUserId();
	}

	/**
	* Sets the user ID of this science app ratings entry.
	*
	* @param userId the user ID of this science app ratings entry
	*/
	@Override
	public void setUserId(long userId) {
		_scienceAppRatingsEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this science app ratings entry.
	*
	* @return the user uuid of this science app ratings entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppRatingsEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this science app ratings entry.
	*
	* @param userUuid the user uuid of this science app ratings entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_scienceAppRatingsEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the science app ID of this science app ratings entry.
	*
	* @return the science app ID of this science app ratings entry
	*/
	@Override
	public long getScienceAppId() {
		return _scienceAppRatingsEntry.getScienceAppId();
	}

	/**
	* Sets the science app ID of this science app ratings entry.
	*
	* @param scienceAppId the science app ID of this science app ratings entry
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppRatingsEntry.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the company ID of this science app ratings entry.
	*
	* @return the company ID of this science app ratings entry
	*/
	@Override
	public long getCompanyId() {
		return _scienceAppRatingsEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this science app ratings entry.
	*
	* @param companyId the company ID of this science app ratings entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_scienceAppRatingsEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user name of this science app ratings entry.
	*
	* @return the user name of this science app ratings entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _scienceAppRatingsEntry.getUserName();
	}

	/**
	* Sets the user name of this science app ratings entry.
	*
	* @param userName the user name of this science app ratings entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_scienceAppRatingsEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this science app ratings entry.
	*
	* @return the create date of this science app ratings entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _scienceAppRatingsEntry.getCreateDate();
	}

	/**
	* Sets the create date of this science app ratings entry.
	*
	* @param createDate the create date of this science app ratings entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_scienceAppRatingsEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this science app ratings entry.
	*
	* @return the modified date of this science app ratings entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _scienceAppRatingsEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this science app ratings entry.
	*
	* @param modifiedDate the modified date of this science app ratings entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_scienceAppRatingsEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this science app ratings entry.
	*
	* @return the fully qualified class name of this science app ratings entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _scienceAppRatingsEntry.getClassName();
	}

	@Override
	public void setClassName(java.lang.String className) {
		_scienceAppRatingsEntry.setClassName(className);
	}

	/**
	* Returns the class name ID of this science app ratings entry.
	*
	* @return the class name ID of this science app ratings entry
	*/
	@Override
	public long getClassNameId() {
		return _scienceAppRatingsEntry.getClassNameId();
	}

	/**
	* Sets the class name ID of this science app ratings entry.
	*
	* @param classNameId the class name ID of this science app ratings entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_scienceAppRatingsEntry.setClassNameId(classNameId);
	}

	/**
	* Returns the score of this science app ratings entry.
	*
	* @return the score of this science app ratings entry
	*/
	@Override
	public long getScore() {
		return _scienceAppRatingsEntry.getScore();
	}

	/**
	* Sets the score of this science app ratings entry.
	*
	* @param score the score of this science app ratings entry
	*/
	@Override
	public void setScore(long score) {
		_scienceAppRatingsEntry.setScore(score);
	}

	@Override
	public boolean isNew() {
		return _scienceAppRatingsEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_scienceAppRatingsEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _scienceAppRatingsEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceAppRatingsEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceAppRatingsEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _scienceAppRatingsEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_scienceAppRatingsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _scienceAppRatingsEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_scienceAppRatingsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_scienceAppRatingsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_scienceAppRatingsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ScienceAppRatingsEntryWrapper((ScienceAppRatingsEntry)_scienceAppRatingsEntry.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.science.model.ScienceAppRatingsEntry scienceAppRatingsEntry) {
		return _scienceAppRatingsEntry.compareTo(scienceAppRatingsEntry);
	}

	@Override
	public int hashCode() {
		return _scienceAppRatingsEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.science.model.ScienceAppRatingsEntry> toCacheModel() {
		return _scienceAppRatingsEntry.toCacheModel();
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry toEscapedModel() {
		return new ScienceAppRatingsEntryWrapper(_scienceAppRatingsEntry.toEscapedModel());
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppRatingsEntry toUnescapedModel() {
		return new ScienceAppRatingsEntryWrapper(_scienceAppRatingsEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _scienceAppRatingsEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scienceAppRatingsEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppRatingsEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppRatingsEntryWrapper)) {
			return false;
		}

		ScienceAppRatingsEntryWrapper scienceAppRatingsEntryWrapper = (ScienceAppRatingsEntryWrapper)obj;

		if (Validator.equals(_scienceAppRatingsEntry,
					scienceAppRatingsEntryWrapper._scienceAppRatingsEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ScienceAppRatingsEntry getWrappedScienceAppRatingsEntry() {
		return _scienceAppRatingsEntry;
	}

	@Override
	public ScienceAppRatingsEntry getWrappedModel() {
		return _scienceAppRatingsEntry;
	}

	@Override
	public void resetOriginalValues() {
		_scienceAppRatingsEntry.resetOriginalValues();
	}

	private ScienceAppRatingsEntry _scienceAppRatingsEntry;
}