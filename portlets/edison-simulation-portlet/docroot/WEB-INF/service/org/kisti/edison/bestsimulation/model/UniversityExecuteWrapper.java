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

package org.kisti.edison.bestsimulation.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UniversityExecute}.
 * </p>
 *
 * @author EDISON
 * @see UniversityExecute
 * @generated
 */
public class UniversityExecuteWrapper implements UniversityExecute,
	ModelWrapper<UniversityExecute> {
	public UniversityExecuteWrapper(UniversityExecute universityExecute) {
		_universityExecute = universityExecute;
	}

	@Override
	public Class<?> getModelClass() {
		return UniversityExecute.class;
	}

	@Override
	public String getModelClassName() {
		return UniversityExecute.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("executeDate", getExecuteDate());
		attributes.put("universityField", getUniversityField());
		attributes.put("userCnt", getUserCnt());
		attributes.put("avgExeTime", getAvgExeTime());
		attributes.put("exeCnt", getExeCnt());
		attributes.put("cpuTime", getCpuTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String executeDate = (String)attributes.get("executeDate");

		if (executeDate != null) {
			setExecuteDate(executeDate);
		}

		Long universityField = (Long)attributes.get("universityField");

		if (universityField != null) {
			setUniversityField(universityField);
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

		Long cpuTime = (Long)attributes.get("cpuTime");

		if (cpuTime != null) {
			setCpuTime(cpuTime);
		}
	}

	/**
	* Returns the primary key of this university execute.
	*
	* @return the primary key of this university execute
	*/
	@Override
	public org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK getPrimaryKey() {
		return _universityExecute.getPrimaryKey();
	}

	/**
	* Sets the primary key of this university execute.
	*
	* @param primaryKey the primary key of this university execute
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK primaryKey) {
		_universityExecute.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the execute date of this university execute.
	*
	* @return the execute date of this university execute
	*/
	@Override
	public java.lang.String getExecuteDate() {
		return _universityExecute.getExecuteDate();
	}

	/**
	* Sets the execute date of this university execute.
	*
	* @param executeDate the execute date of this university execute
	*/
	@Override
	public void setExecuteDate(java.lang.String executeDate) {
		_universityExecute.setExecuteDate(executeDate);
	}

	/**
	* Returns the university field of this university execute.
	*
	* @return the university field of this university execute
	*/
	@Override
	public long getUniversityField() {
		return _universityExecute.getUniversityField();
	}

	/**
	* Sets the university field of this university execute.
	*
	* @param universityField the university field of this university execute
	*/
	@Override
	public void setUniversityField(long universityField) {
		_universityExecute.setUniversityField(universityField);
	}

	/**
	* Returns the user cnt of this university execute.
	*
	* @return the user cnt of this university execute
	*/
	@Override
	public long getUserCnt() {
		return _universityExecute.getUserCnt();
	}

	/**
	* Sets the user cnt of this university execute.
	*
	* @param userCnt the user cnt of this university execute
	*/
	@Override
	public void setUserCnt(long userCnt) {
		_universityExecute.setUserCnt(userCnt);
	}

	/**
	* Returns the avg exe time of this university execute.
	*
	* @return the avg exe time of this university execute
	*/
	@Override
	public long getAvgExeTime() {
		return _universityExecute.getAvgExeTime();
	}

	/**
	* Sets the avg exe time of this university execute.
	*
	* @param avgExeTime the avg exe time of this university execute
	*/
	@Override
	public void setAvgExeTime(long avgExeTime) {
		_universityExecute.setAvgExeTime(avgExeTime);
	}

	/**
	* Returns the exe cnt of this university execute.
	*
	* @return the exe cnt of this university execute
	*/
	@Override
	public long getExeCnt() {
		return _universityExecute.getExeCnt();
	}

	/**
	* Sets the exe cnt of this university execute.
	*
	* @param exeCnt the exe cnt of this university execute
	*/
	@Override
	public void setExeCnt(long exeCnt) {
		_universityExecute.setExeCnt(exeCnt);
	}

	/**
	* Returns the cpu time of this university execute.
	*
	* @return the cpu time of this university execute
	*/
	@Override
	public long getCpuTime() {
		return _universityExecute.getCpuTime();
	}

	/**
	* Sets the cpu time of this university execute.
	*
	* @param cpuTime the cpu time of this university execute
	*/
	@Override
	public void setCpuTime(long cpuTime) {
		_universityExecute.setCpuTime(cpuTime);
	}

	@Override
	public boolean isNew() {
		return _universityExecute.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_universityExecute.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _universityExecute.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_universityExecute.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _universityExecute.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _universityExecute.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_universityExecute.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _universityExecute.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_universityExecute.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_universityExecute.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_universityExecute.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UniversityExecuteWrapper((UniversityExecute)_universityExecute.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute) {
		return _universityExecute.compareTo(universityExecute);
	}

	@Override
	public int hashCode() {
		return _universityExecute.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.bestsimulation.model.UniversityExecute> toCacheModel() {
		return _universityExecute.toCacheModel();
	}

	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute toEscapedModel() {
		return new UniversityExecuteWrapper(_universityExecute.toEscapedModel());
	}

	@Override
	public org.kisti.edison.bestsimulation.model.UniversityExecute toUnescapedModel() {
		return new UniversityExecuteWrapper(_universityExecute.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _universityExecute.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _universityExecute.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_universityExecute.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UniversityExecuteWrapper)) {
			return false;
		}

		UniversityExecuteWrapper universityExecuteWrapper = (UniversityExecuteWrapper)obj;

		if (Validator.equals(_universityExecute,
					universityExecuteWrapper._universityExecute)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public UniversityExecute getWrappedUniversityExecute() {
		return _universityExecute;
	}

	@Override
	public UniversityExecute getWrappedModel() {
		return _universityExecute;
	}

	@Override
	public void resetOriginalValues() {
		_universityExecute.resetOriginalValues();
	}

	private UniversityExecute _universityExecute;
}