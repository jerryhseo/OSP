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

package org.kisti.edison.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RequestMember}.
 * </p>
 *
 * @author edison
 * @see RequestMember
 * @generated
 */
public class RequestMemberWrapper implements RequestMember,
	ModelWrapper<RequestMember> {
	public RequestMemberWrapper(RequestMember requestMember) {
		_requestMember = requestMember;
	}

	@Override
	public Class<?> getModelClass() {
		return RequestMember.class;
	}

	@Override
	public String getModelClassName() {
		return RequestMember.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("requestSeq", getRequestSeq());
		attributes.put("simulationProjectId", getSimulationProjectId());
		attributes.put("userId", getUserId());
		attributes.put("requestDate", getRequestDate());
		attributes.put("processDate", getProcessDate());
		attributes.put("requestState", getRequestState());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long requestSeq = (Long)attributes.get("requestSeq");

		if (requestSeq != null) {
			setRequestSeq(requestSeq);
		}

		Long simulationProjectId = (Long)attributes.get("simulationProjectId");

		if (simulationProjectId != null) {
			setSimulationProjectId(simulationProjectId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date requestDate = (Date)attributes.get("requestDate");

		if (requestDate != null) {
			setRequestDate(requestDate);
		}

		Date processDate = (Date)attributes.get("processDate");

		if (processDate != null) {
			setProcessDate(processDate);
		}

		String requestState = (String)attributes.get("requestState");

		if (requestState != null) {
			setRequestState(requestState);
		}
	}

	/**
	* Returns the primary key of this request member.
	*
	* @return the primary key of this request member
	*/
	@Override
	public org.kisti.edison.service.persistence.RequestMemberPK getPrimaryKey() {
		return _requestMember.getPrimaryKey();
	}

	/**
	* Sets the primary key of this request member.
	*
	* @param primaryKey the primary key of this request member
	*/
	@Override
	public void setPrimaryKey(
		org.kisti.edison.service.persistence.RequestMemberPK primaryKey) {
		_requestMember.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the request seq of this request member.
	*
	* @return the request seq of this request member
	*/
	@Override
	public long getRequestSeq() {
		return _requestMember.getRequestSeq();
	}

	/**
	* Sets the request seq of this request member.
	*
	* @param requestSeq the request seq of this request member
	*/
	@Override
	public void setRequestSeq(long requestSeq) {
		_requestMember.setRequestSeq(requestSeq);
	}

	/**
	* Returns the simulation project ID of this request member.
	*
	* @return the simulation project ID of this request member
	*/
	@Override
	public long getSimulationProjectId() {
		return _requestMember.getSimulationProjectId();
	}

	/**
	* Sets the simulation project ID of this request member.
	*
	* @param simulationProjectId the simulation project ID of this request member
	*/
	@Override
	public void setSimulationProjectId(long simulationProjectId) {
		_requestMember.setSimulationProjectId(simulationProjectId);
	}

	/**
	* Returns the user ID of this request member.
	*
	* @return the user ID of this request member
	*/
	@Override
	public java.lang.Long getUserId() {
		return _requestMember.getUserId();
	}

	/**
	* Sets the user ID of this request member.
	*
	* @param userId the user ID of this request member
	*/
	@Override
	public void setUserId(java.lang.Long userId) {
		_requestMember.setUserId(userId);
	}

	/**
	* Returns the request date of this request member.
	*
	* @return the request date of this request member
	*/
	@Override
	public java.util.Date getRequestDate() {
		return _requestMember.getRequestDate();
	}

	/**
	* Sets the request date of this request member.
	*
	* @param requestDate the request date of this request member
	*/
	@Override
	public void setRequestDate(java.util.Date requestDate) {
		_requestMember.setRequestDate(requestDate);
	}

	/**
	* Returns the process date of this request member.
	*
	* @return the process date of this request member
	*/
	@Override
	public java.util.Date getProcessDate() {
		return _requestMember.getProcessDate();
	}

	/**
	* Sets the process date of this request member.
	*
	* @param processDate the process date of this request member
	*/
	@Override
	public void setProcessDate(java.util.Date processDate) {
		_requestMember.setProcessDate(processDate);
	}

	/**
	* Returns the request state of this request member.
	*
	* @return the request state of this request member
	*/
	@Override
	public java.lang.String getRequestState() {
		return _requestMember.getRequestState();
	}

	/**
	* Sets the request state of this request member.
	*
	* @param requestState the request state of this request member
	*/
	@Override
	public void setRequestState(java.lang.String requestState) {
		_requestMember.setRequestState(requestState);
	}

	@Override
	public boolean isNew() {
		return _requestMember.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_requestMember.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _requestMember.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_requestMember.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _requestMember.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _requestMember.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_requestMember.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _requestMember.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_requestMember.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_requestMember.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_requestMember.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RequestMemberWrapper((RequestMember)_requestMember.clone());
	}

	@Override
	public int compareTo(org.kisti.edison.model.RequestMember requestMember) {
		return _requestMember.compareTo(requestMember);
	}

	@Override
	public int hashCode() {
		return _requestMember.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<org.kisti.edison.model.RequestMember> toCacheModel() {
		return _requestMember.toCacheModel();
	}

	@Override
	public org.kisti.edison.model.RequestMember toEscapedModel() {
		return new RequestMemberWrapper(_requestMember.toEscapedModel());
	}

	@Override
	public org.kisti.edison.model.RequestMember toUnescapedModel() {
		return new RequestMemberWrapper(_requestMember.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _requestMember.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _requestMember.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_requestMember.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RequestMemberWrapper)) {
			return false;
		}

		RequestMemberWrapper requestMemberWrapper = (RequestMemberWrapper)obj;

		if (Validator.equals(_requestMember, requestMemberWrapper._requestMember)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public RequestMember getWrappedRequestMember() {
		return _requestMember;
	}

	@Override
	public RequestMember getWrappedModel() {
		return _requestMember;
	}

	@Override
	public void resetOriginalValues() {
		_requestMember.resetOriginalValues();
	}

	private RequestMember _requestMember;
}