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
 * This class is a wrapper for {@link VirtualLabScienceAppLink}.
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLink
 * @generated
 */
public class VirtualLabScienceAppLinkWrapper implements VirtualLabScienceAppLink,
	ModelWrapper<VirtualLabScienceAppLink> {
	public VirtualLabScienceAppLinkWrapper(
		VirtualLabScienceAppLink virtualLabScienceAppLink) {
		_virtualLabScienceAppLink = virtualLabScienceAppLink;
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabScienceAppLink.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabScienceAppLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppSeqNo", getScienceAppSeqNo());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("virtualLabId", getVirtualLabId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppSeqNo = (Long)attributes.get("scienceAppSeqNo");

		if (scienceAppSeqNo != null) {
			setScienceAppSeqNo(scienceAppSeqNo);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}
	}

	/**
	* Returns the primary key of this virtual lab science app link.
	*
	* @return the primary key of this virtual lab science app link
	*/
	@Override
	public long getPrimaryKey() {
		return _virtualLabScienceAppLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this virtual lab science app link.
	*
	* @param primaryKey the primary key of this virtual lab science app link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_virtualLabScienceAppLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the science app seq no of this virtual lab science app link.
	*
	* @return the science app seq no of this virtual lab science app link
	*/
	@Override
	public long getScienceAppSeqNo() {
		return _virtualLabScienceAppLink.getScienceAppSeqNo();
	}

	/**
	* Sets the science app seq no of this virtual lab science app link.
	*
	* @param scienceAppSeqNo the science app seq no of this virtual lab science app link
	*/
	@Override
	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_virtualLabScienceAppLink.setScienceAppSeqNo(scienceAppSeqNo);
	}

	/**
	* Returns the science app ID of this virtual lab science app link.
	*
	* @return the science app ID of this virtual lab science app link
	*/
	@Override
	public long getScienceAppId() {
		return _virtualLabScienceAppLink.getScienceAppId();
	}

	/**
	* Sets the science app ID of this virtual lab science app link.
	*
	* @param scienceAppId the science app ID of this virtual lab science app link
	*/
	@Override
	public void setScienceAppId(long scienceAppId) {
		_virtualLabScienceAppLink.setScienceAppId(scienceAppId);
	}

	/**
	* Returns the virtual lab ID of this virtual lab science app link.
	*
	* @return the virtual lab ID of this virtual lab science app link
	*/
	@Override
	public long getVirtualLabId() {
		return _virtualLabScienceAppLink.getVirtualLabId();
	}

	/**
	* Sets the virtual lab ID of this virtual lab science app link.
	*
	* @param virtualLabId the virtual lab ID of this virtual lab science app link
	*/
	@Override
	public void setVirtualLabId(long virtualLabId) {
		_virtualLabScienceAppLink.setVirtualLabId(virtualLabId);
	}

	@Override
	public boolean isNew() {
		return _virtualLabScienceAppLink.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_virtualLabScienceAppLink.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _virtualLabScienceAppLink.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_virtualLabScienceAppLink.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _virtualLabScienceAppLink.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _virtualLabScienceAppLink.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_virtualLabScienceAppLink.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _virtualLabScienceAppLink.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_virtualLabScienceAppLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_virtualLabScienceAppLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_virtualLabScienceAppLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new VirtualLabScienceAppLinkWrapper((VirtualLabScienceAppLink)_virtualLabScienceAppLink.clone());
	}

	@Override
	public int compareTo(
		org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink virtualLabScienceAppLink) {
		return _virtualLabScienceAppLink.compareTo(virtualLabScienceAppLink);
	}

	@Override
	public int hashCode() {
		return _virtualLabScienceAppLink.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink> toCacheModel() {
		return _virtualLabScienceAppLink.toCacheModel();
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink toEscapedModel() {
		return new VirtualLabScienceAppLinkWrapper(_virtualLabScienceAppLink.toEscapedModel());
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink toUnescapedModel() {
		return new VirtualLabScienceAppLinkWrapper(_virtualLabScienceAppLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _virtualLabScienceAppLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _virtualLabScienceAppLink.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_virtualLabScienceAppLink.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabScienceAppLinkWrapper)) {
			return false;
		}

		VirtualLabScienceAppLinkWrapper virtualLabScienceAppLinkWrapper = (VirtualLabScienceAppLinkWrapper)obj;

		if (Validator.equals(_virtualLabScienceAppLink,
					virtualLabScienceAppLinkWrapper._virtualLabScienceAppLink)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public VirtualLabScienceAppLink getWrappedVirtualLabScienceAppLink() {
		return _virtualLabScienceAppLink;
	}

	@Override
	public VirtualLabScienceAppLink getWrappedModel() {
		return _virtualLabScienceAppLink;
	}

	@Override
	public void resetOriginalValues() {
		_virtualLabScienceAppLink.resetOriginalValues();
	}

	private VirtualLabScienceAppLink _virtualLabScienceAppLink;
}