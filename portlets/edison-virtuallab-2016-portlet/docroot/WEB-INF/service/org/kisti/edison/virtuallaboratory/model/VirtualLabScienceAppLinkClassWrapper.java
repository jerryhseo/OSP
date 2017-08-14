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

package org.kisti.edison.virtuallaboratory.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VirtualLabScienceAppLinkClass}.
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClass
 * @generated
 */
public class VirtualLabScienceAppLinkClassWrapper
	implements VirtualLabScienceAppLinkClass,
		ModelWrapper<VirtualLabScienceAppLinkClass> {
	public VirtualLabScienceAppLinkClassWrapper(
		VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		_virtualLabScienceAppLinkClass = virtualLabScienceAppLinkClass;
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabScienceAppLinkClass.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabScienceAppLinkClass.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppClassSeqNo", getScienceAppClassSeqNo());
		attributes.put("classId", getClassId());
		attributes.put("scienceAppSeqNo", getScienceAppSeqNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppClassSeqNo = (Long)attributes.get("scienceAppClassSeqNo");

		if (scienceAppClassSeqNo != null) {
			setScienceAppClassSeqNo(scienceAppClassSeqNo);
		}

		Long classId = (Long)attributes.get("classId");

		if (classId != null) {
			setClassId(classId);
		}

		Long scienceAppSeqNo = (Long)attributes.get("scienceAppSeqNo");

		if (scienceAppSeqNo != null) {
			setScienceAppSeqNo(scienceAppSeqNo);
		}
	}

	/**
	* Returns the primary key of this virtual lab science app link class.
	*
	* @return the primary key of this virtual lab science app link class
	*/
	@Override
	public long getPrimaryKey() {
		return _virtualLabScienceAppLinkClass.getPrimaryKey();
	}

	/**
	* Sets the primary key of this virtual lab science app link class.
	*
	* @param primaryKey the primary key of this virtual lab science app link class
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_virtualLabScienceAppLinkClass.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the science app class seq no of this virtual lab science app link class.
	*
	* @return the science app class seq no of this virtual lab science app link class
	*/
	@Override
	public long getScienceAppClassSeqNo() {
		return _virtualLabScienceAppLinkClass.getScienceAppClassSeqNo();
	}

	/**
	* Sets the science app class seq no of this virtual lab science app link class.
	*
	* @param scienceAppClassSeqNo the science app class seq no of this virtual lab science app link class
	*/
	@Override
	public void setScienceAppClassSeqNo(long scienceAppClassSeqNo) {
		_virtualLabScienceAppLinkClass.setScienceAppClassSeqNo(scienceAppClassSeqNo);
	}

	/**
	* Returns the class ID of this virtual lab science app link class.
	*
	* @return the class ID of this virtual lab science app link class
	*/
	@Override
	public long getClassId() {
		return _virtualLabScienceAppLinkClass.getClassId();
	}

	/**
	* Sets the class ID of this virtual lab science app link class.
	*
	* @param classId the class ID of this virtual lab science app link class
	*/
	@Override
	public void setClassId(long classId) {
		_virtualLabScienceAppLinkClass.setClassId(classId);
	}

	/**
	* Returns the science app seq no of this virtual lab science app link class.
	*
	* @return the science app seq no of this virtual lab science app link class
	*/
	@Override
	public long getScienceAppSeqNo() {
		return _virtualLabScienceAppLinkClass.getScienceAppSeqNo();
	}

	/**
	* Sets the science app seq no of this virtual lab science app link class.
	*
	* @param scienceAppSeqNo the science app seq no of this virtual lab science app link class
	*/
	@Override
	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_virtualLabScienceAppLinkClass.setScienceAppSeqNo(scienceAppSeqNo);
	}

	@Override
	public boolean isNew() {
		return _virtualLabScienceAppLinkClass.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_virtualLabScienceAppLinkClass.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _virtualLabScienceAppLinkClass.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_virtualLabScienceAppLinkClass.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _virtualLabScienceAppLinkClass.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _virtualLabScienceAppLinkClass.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_virtualLabScienceAppLinkClass.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _virtualLabScienceAppLinkClass.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_virtualLabScienceAppLinkClass.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_virtualLabScienceAppLinkClass.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_virtualLabScienceAppLinkClass.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new VirtualLabScienceAppLinkClassWrapper((VirtualLabScienceAppLinkClass)_virtualLabScienceAppLinkClass.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass virtualLabScienceAppLinkClass) {
		return _virtualLabScienceAppLinkClass.compareTo(virtualLabScienceAppLinkClass);
	}

	@Override
	public int hashCode() {
		return _virtualLabScienceAppLinkClass.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass> toCacheModel() {
		return _virtualLabScienceAppLinkClass.toCacheModel();
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass toEscapedModel() {
		return new VirtualLabScienceAppLinkClassWrapper(_virtualLabScienceAppLinkClass.toEscapedModel());
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkClass toUnescapedModel() {
		return new VirtualLabScienceAppLinkClassWrapper(_virtualLabScienceAppLinkClass.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _virtualLabScienceAppLinkClass.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _virtualLabScienceAppLinkClass.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_virtualLabScienceAppLinkClass.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabScienceAppLinkClassWrapper)) {
			return false;
		}

		VirtualLabScienceAppLinkClassWrapper virtualLabScienceAppLinkClassWrapper =
			(VirtualLabScienceAppLinkClassWrapper)obj;

		if (Validator.equals(_virtualLabScienceAppLinkClass,
					virtualLabScienceAppLinkClassWrapper._virtualLabScienceAppLinkClass)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public VirtualLabScienceAppLinkClass getWrappedVirtualLabScienceAppLinkClass() {
		return _virtualLabScienceAppLinkClass;
	}

	@Override
	public VirtualLabScienceAppLinkClass getWrappedModel() {
		return _virtualLabScienceAppLinkClass;
	}

	@Override
	public void resetOriginalValues() {
		_virtualLabScienceAppLinkClass.resetOriginalValues();
	}

	private VirtualLabScienceAppLinkClass _virtualLabScienceAppLinkClass;
}