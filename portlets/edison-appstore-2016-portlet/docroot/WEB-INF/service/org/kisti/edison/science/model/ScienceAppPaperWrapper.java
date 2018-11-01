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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScienceAppPaper}.
 * </p>
 *
 * @author EDISON
 * @see ScienceAppPaper
 * @generated
 */
public class ScienceAppPaperWrapper implements ScienceAppPaper,
	ModelWrapper<ScienceAppPaper> {
	public ScienceAppPaperWrapper(ScienceAppPaper scienceAppPaper) {
		_scienceAppPaper = scienceAppPaper;
	}

	@Override
	public Class<?> getModelClass() {
		return ScienceAppPaper.class;
	}

	@Override
	public String getModelClassName() {
		return ScienceAppPaper.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("paperSeq", getPaperSeq());
		attributes.put("paperType", getPaperType());
		attributes.put("paperValue", getPaperValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long paperSeq = (Long)attributes.get("paperSeq");

		if (paperSeq != null) {
			setPaperSeq(paperSeq);
		}

		String paperType = (String)attributes.get("paperType");

		if (paperType != null) {
			setPaperType(paperType);
		}

		String paperValue = (String)attributes.get("paperValue");

		if (paperValue != null) {
			setPaperValue(paperValue);
		}
	}

	/**
	* Returns the primary key of this science app paper.
	*
	* @return the primary key of this science app paper
	*/
	@Override
	public org.kisti.edison.science.service.persistence.ScienceAppPaperPK getPrimaryKey() {
		return _scienceAppPaper.getPrimaryKey();
	}

	/**
	* Sets the primary key of this science app paper.
	*
	* @param primaryKey the primary key of this science app paper
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK primaryKey) {
		_scienceAppPaper.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the science app ID of this science app paper.
	*
	* @return the science app ID of this science app paper
	*/
	@Override
	public long getScienceAppId() {
		return _scienceAppPaper.getScienceAppId();
	}

	/**
	* Sets the science app ID of this science app paper.
	*
	* @param scienceAppId the science app ID of this science app paper
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppPaper.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the paper seq of this science app paper.
	*
	* @return the paper seq of this science app paper
	*/
	@Override
	public long getPaperSeq() {
		return _scienceAppPaper.getPaperSeq();
	}

	/**
	* Sets the paper seq of this science app paper.
	*
	* @param paperSeq the paper seq of this science app paper
	*/
	@Override
	public void setPaperSeq(long paperSeq) {
		_scienceAppPaper.setPaperSeq(paperSeq);
	}

	/**
	* Returns the paper type of this science app paper.
	*
	* @return the paper type of this science app paper
	*/
	@Override
	public java.lang.String getPaperType() {
		return _scienceAppPaper.getPaperType();
	}

	/**
	* Sets the paper type of this science app paper.
	*
	* @param paperType the paper type of this science app paper
	*/
	@Override
	public void setPaperType(java.lang.String paperType) {
		_scienceAppPaper.setPaperType(paperType);
	}

	/**
	* Returns the paper value of this science app paper.
	*
	* @return the paper value of this science app paper
	*/
	@Override
	public java.lang.String getPaperValue() {
		return _scienceAppPaper.getPaperValue();
	}

	/**
	* Sets the paper value of this science app paper.
	*
	* @param paperValue the paper value of this science app paper
	*/
	@Override
	public void setPaperValue(java.lang.String paperValue) {
		_scienceAppPaper.setPaperValue(paperValue);
	}

	@Override
	public boolean isNew() {
		return _scienceAppPaper.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_scienceAppPaper.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _scienceAppPaper.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_scienceAppPaper.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _scienceAppPaper.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _scienceAppPaper.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_scienceAppPaper.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _scienceAppPaper.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_scienceAppPaper.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_scienceAppPaper.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_scienceAppPaper.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ScienceAppPaperWrapper((ScienceAppPaper)_scienceAppPaper.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper) {
		return _scienceAppPaper.compareTo(scienceAppPaper);
	}

	@Override
	public int hashCode() {
		return _scienceAppPaper.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.science.model.ScienceAppPaper> toCacheModel() {
		return _scienceAppPaper.toCacheModel();
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppPaper toEscapedModel() {
		return new ScienceAppPaperWrapper(_scienceAppPaper.toEscapedModel());
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppPaper toUnescapedModel() {
		return new ScienceAppPaperWrapper(_scienceAppPaper.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _scienceAppPaper.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _scienceAppPaper.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_scienceAppPaper.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScienceAppPaperWrapper)) {
			return false;
		}

		ScienceAppPaperWrapper scienceAppPaperWrapper = (ScienceAppPaperWrapper)obj;

		if (Validator.equals(_scienceAppPaper,
					scienceAppPaperWrapper._scienceAppPaper)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ScienceAppPaper getWrappedScienceAppPaper() {
		return _scienceAppPaper;
	}

	@Override
	public ScienceAppPaper getWrappedModel() {
		return _scienceAppPaper;
	}

	@Override
	public void resetOriginalValues() {
		_scienceAppPaper.resetOriginalValues();
	}

	private ScienceAppPaper _scienceAppPaper;
}