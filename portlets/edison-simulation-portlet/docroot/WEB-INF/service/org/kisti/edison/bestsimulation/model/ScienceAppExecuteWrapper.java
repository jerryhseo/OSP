/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScienceAppExecute}.
 * </p>
 *
 * @author EDISON
 * @see ScienceAppExecute
 * @generated
 */
public class ScienceAppExecuteWrapper implements ScienceAppExecute,
	ModelWrapper<ScienceAppExecute> {
	public ScienceAppExecuteWrapper(ScienceAppExecute scienceAppExecute) {
		_scienceAppExecute = scienceAppExecute;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppExecute.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppExecute.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("executeDate", getExecuteDate());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("userCnt", getUserCnt());
		attributes.put("avgExeTime", getAvgExeTime());
		attributes.put("exeCnt", getExeCnt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String executeDate = (String)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long userCnt = (Long)attributes.get("userCnt");

		if (userCnt != null) {
			setUserCnt(userCnt);
		}

		Long avgExeTime = (Long)attributes.get("avgExeTime");

		if (avgExeTime != null) {
			setAvgExeTime(avgExeTime);
		}

		Long exeCnt = (Long)attributes.get("exeCnt");

		if (exeCnt != null) {
			setExeCnt(exeCnt);
		}
	}

	/**
	* Returns the primary key of this science app execute.
	*
	* @return the primary key of this science app execute
	*/
	@Override
	public org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK getPrimaryKey() {
		return _scienceAppExecute.getPrimaryKey();
	}

	/**
	* Sets the primary key of this science app execute.
	*
	* @param primaryKey the primary key of this science app execute
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK primaryKey) {
		_scienceAppExecute.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the execute date of this science app execute.
	*
	* @return the execute date of this science app execute
	*/
	@Override
	public java.lang.String getExecuteDate() {
		return _scienceAppExecute.getExecuteDate();
	}

	/**
	* Sets the execute date of this science app execute.
	*
	* @param executeDate the execute date of this science app execute
	*/
	@Override
	public void setExecuteDate(java.lang.String executeDate) {
		_scienceAppExecute.setExecuteDate(executeDate);
	}

	/**
	* Returns the science app ID of this science app execute.
	*
	* @return the science app ID of this science app execute
	*/
	@Override
	public long getScienceAppId() {
		return _scienceAppExecute.getScienceAppId();
	}

	/**
	* Sets the science app ID of this science app execute.
	*
	* @param scienceAppId the science app ID of this science app execute
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppExecute.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the user cnt of this science app execute.
	*
	* @return the user cnt of this science app execute
	*/
	@Override
	public long getUserCnt() {
		return _scienceAppExecute.getUserCnt();
	}

	/**
	* Sets the user cnt of this science app execute.
	*
	* @param userCnt the user cnt of this science app execute
	*/
	@Override
	public void setUserCnt(long userCnt) {
		_scienceAppExecute.setUserCnt(userCnt);
	}

	/**
	* Returns the avg exe time of this science app execute.
	*
	* @return the avg exe time of this science app execute
	*/
	@Override
	public long getAvgExeTime() {
		return _scienceAppExecute.getAvgExeTime();
	}

	/**
	* Sets the avg exe time of this science app execute.
	*
	* @param avgExeTime the avg exe time of this science app execute
	*/
	@Override
	public void setAvgExeTime(long avgExeTime) {
		_scienceAppExecute.setAvgExeTime(avgExeTime);
	}

	/**
	* Returns the exe cnt of this science app execute.
	*
	* @return the exe cnt of this science app execute
	*/
	@Override
	public long getExeCnt() {
		return _scienceAppExecute.getExeCnt();
	}

	/**
	* Sets the exe cnt of this science app execute.
	*
	* @param exeCnt the exe cnt of this science app execute
	*/
	@Override
	public void setExeCnt(long exeCnt) {
		_scienceAppExecute.setExeCnt(exeCnt);
	}

	@Override
	public boolean isNew() {
		return _scienceAppExecute.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_scienceAppExecute.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _scienceAppExecute.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceAppExecute.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceAppExecute.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _scienceAppExecute.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_scienceAppExecute.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _scienceAppExecute.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_scienceAppExecute.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_scienceAppExecute.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_scienceAppExecute.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ScienceAppExecuteWrapper((ScienceAppExecute)_scienceAppExecute.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute) {
		return _scienceAppExecute.compareTo(scienceAppExecute);
	}

	@Override
	public int hashCode() {
		return _scienceAppExecute.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.bestsimulation.model.ScienceAppExecute> toCacheModel() {
		return _scienceAppExecute.toCacheModel();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute toEscapedModel() {
		return new ScienceAppExecuteWrapper(_scienceAppExecute.toEscapedModel());
	}

	@Override
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute toUnescapedModel() {
		return new ScienceAppExecuteWrapper(_scienceAppExecute.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _scienceAppExecute.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scienceAppExecute.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppExecute.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppExecuteWrapper)) {
			return false;
		}

		ScienceAppExecuteWrapper scienceAppExecuteWrapper = (ScienceAppExecuteWrapper)obj;

		if (Validator.equals(_scienceAppExecute,
					scienceAppExecuteWrapper._scienceAppExecute)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ScienceAppExecute getWrappedScienceAppExecute() {
		return _scienceAppExecute;
	}

	@Override
	public ScienceAppExecute getWrappedModel() {
		return _scienceAppExecute;
	}

	@Override
	public void resetOriginalValues() {
		_scienceAppExecute.resetOriginalValues();
	}

	private ScienceAppExecute _scienceAppExecute;
}