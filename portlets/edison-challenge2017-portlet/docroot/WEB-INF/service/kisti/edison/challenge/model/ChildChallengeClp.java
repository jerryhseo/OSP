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

package kisti.edison.challenge.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KYJ
 */
public class ChildChallengeClp extends BaseModelImpl<ChildChallenge>
	implements ChildChallenge {
	public ChildChallengeClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ChildChallenge.class;
	}

	@Override
	public String getModelClassName() {
		return ChildChallenge.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _childChallengeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChildChallengeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _childChallengeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("childChallengeId", getChildChallengeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("number", getNumber());
		attributes.put("presentationDay", getPresentationDay());
		attributes.put("paperStartDay", getPaperStartDay());
		attributes.put("paperEndDay", getPaperEndDay());
		attributes.put("evaluationDay", getEvaluationDay());
		attributes.put("challengeStartDay", getChallengeStartDay());
		attributes.put("challengeEndDay", getChallengeEndDay());
		attributes.put("challengeStatus", getChallengeStatus());
		attributes.put("challengeId", getChallengeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long childChallengeId = (Long)attributes.get("childChallengeId");

		if (childChallengeId != null) {
			setChildChallengeId(childChallengeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Integer number = (Integer)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Date presentationDay = (Date)attributes.get("presentationDay");

		if (presentationDay != null) {
			setPresentationDay(presentationDay);
		}

		Date paperStartDay = (Date)attributes.get("paperStartDay");

		if (paperStartDay != null) {
			setPaperStartDay(paperStartDay);
		}

		Date paperEndDay = (Date)attributes.get("paperEndDay");

		if (paperEndDay != null) {
			setPaperEndDay(paperEndDay);
		}

		Date evaluationDay = (Date)attributes.get("evaluationDay");

		if (evaluationDay != null) {
			setEvaluationDay(evaluationDay);
		}

		Date challengeStartDay = (Date)attributes.get("challengeStartDay");

		if (challengeStartDay != null) {
			setChallengeStartDay(challengeStartDay);
		}

		Date challengeEndDay = (Date)attributes.get("challengeEndDay");

		if (challengeEndDay != null) {
			setChallengeEndDay(challengeEndDay);
		}

		String challengeStatus = (String)attributes.get("challengeStatus");

		if (challengeStatus != null) {
			setChallengeStatus(challengeStatus);
		}

		Long challengeId = (Long)attributes.get("challengeId");

		if (challengeId != null) {
			setChallengeId(challengeId);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_childChallengeRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChildChallengeId() {
		return _childChallengeId;
	}

	@Override
	public void setChildChallengeId(long childChallengeId) {
		_childChallengeId = childChallengeId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChildChallengeId",
						long.class);

				method.invoke(_childChallengeRemoteModel, childChallengeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_childChallengeRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_childChallengeRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_childChallengeRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_childChallengeRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_childChallengeRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_childChallengeRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_childChallengeRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_childChallengeRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	@Override
	public String getStatusByUserName() {
		return _statusByUserName;
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_childChallengeRemoteModel, statusByUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_childChallengeRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getNumber() {
		return _number;
	}

	@Override
	public void setNumber(int number) {
		_number = number;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setNumber", int.class);

				method.invoke(_childChallengeRemoteModel, number);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPresentationDay() {
		return _presentationDay;
	}

	@Override
	public void setPresentationDay(Date presentationDay) {
		_presentationDay = presentationDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setPresentationDay", Date.class);

				method.invoke(_childChallengeRemoteModel, presentationDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperStartDay() {
		return _paperStartDay;
	}

	@Override
	public void setPaperStartDay(Date paperStartDay) {
		_paperStartDay = paperStartDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperStartDay", Date.class);

				method.invoke(_childChallengeRemoteModel, paperStartDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getPaperEndDay() {
		return _paperEndDay;
	}

	@Override
	public void setPaperEndDay(Date paperEndDay) {
		_paperEndDay = paperEndDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setPaperEndDay", Date.class);

				method.invoke(_childChallengeRemoteModel, paperEndDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEvaluationDay() {
		return _evaluationDay;
	}

	@Override
	public void setEvaluationDay(Date evaluationDay) {
		_evaluationDay = evaluationDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setEvaluationDay", Date.class);

				method.invoke(_childChallengeRemoteModel, evaluationDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getChallengeStartDay() {
		return _challengeStartDay;
	}

	@Override
	public void setChallengeStartDay(Date challengeStartDay) {
		_challengeStartDay = challengeStartDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeStartDay",
						Date.class);

				method.invoke(_childChallengeRemoteModel, challengeStartDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getChallengeEndDay() {
		return _challengeEndDay;
	}

	@Override
	public void setChallengeEndDay(Date challengeEndDay) {
		_challengeEndDay = challengeEndDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeEndDay", Date.class);

				method.invoke(_childChallengeRemoteModel, challengeEndDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getChallengeStatus() {
		return _challengeStatus;
	}

	@Override
	public void setChallengeStatus(String challengeStatus) {
		_challengeStatus = challengeStatus;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeStatus",
						String.class);

				method.invoke(_childChallengeRemoteModel, challengeStatus);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeId() {
		return _challengeId;
	}

	@Override
	public void setChallengeId(long challengeId) {
		_challengeId = challengeId;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeId", long.class);

				method.invoke(_childChallengeRemoteModel, challengeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ChildChallenge.class.getName()));
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved}
	 */
	@Override
	public boolean getApproved() {
		return isApproved();
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public BaseModel<?> getChildChallengeRemoteModel() {
		return _childChallengeRemoteModel;
	}

	public void setChildChallengeRemoteModel(
		BaseModel<?> childChallengeRemoteModel) {
		_childChallengeRemoteModel = childChallengeRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _childChallengeRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_childChallengeRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChildChallengeLocalServiceUtil.addChildChallenge(this);
		}
		else {
			ChildChallengeLocalServiceUtil.updateChildChallenge(this);
		}
	}

	@Override
	public ChildChallenge toEscapedModel() {
		return (ChildChallenge)ProxyUtil.newProxyInstance(ChildChallenge.class.getClassLoader(),
			new Class[] { ChildChallenge.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ChildChallengeClp clone = new ChildChallengeClp();

		clone.setUuid(getUuid());
		clone.setChildChallengeId(getChildChallengeId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUserName(getStatusByUserName());
		clone.setStatusDate(getStatusDate());
		clone.setNumber(getNumber());
		clone.setPresentationDay(getPresentationDay());
		clone.setPaperStartDay(getPaperStartDay());
		clone.setPaperEndDay(getPaperEndDay());
		clone.setEvaluationDay(getEvaluationDay());
		clone.setChallengeStartDay(getChallengeStartDay());
		clone.setChallengeEndDay(getChallengeEndDay());
		clone.setChallengeStatus(getChallengeStatus());
		clone.setChallengeId(getChallengeId());

		return clone;
	}

	@Override
	public int compareTo(ChildChallenge childChallenge) {
		int value = 0;

		if (getChildChallengeId() < childChallenge.getChildChallengeId()) {
			value = -1;
		}
		else if (getChildChallengeId() > childChallenge.getChildChallengeId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getChallengeEndDay(),
				childChallenge.getChallengeEndDay());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChildChallengeClp)) {
			return false;
		}

		ChildChallengeClp childChallenge = (ChildChallengeClp)obj;

		long primaryKey = childChallenge.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", childChallengeId=");
		sb.append(getChildChallengeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", number=");
		sb.append(getNumber());
		sb.append(", presentationDay=");
		sb.append(getPresentationDay());
		sb.append(", paperStartDay=");
		sb.append(getPaperStartDay());
		sb.append(", paperEndDay=");
		sb.append(getPaperEndDay());
		sb.append(", evaluationDay=");
		sb.append(getEvaluationDay());
		sb.append(", challengeStartDay=");
		sb.append(getChallengeStartDay());
		sb.append(", challengeEndDay=");
		sb.append(getChallengeEndDay());
		sb.append(", challengeStatus=");
		sb.append(getChallengeStatus());
		sb.append(", challengeId=");
		sb.append(getChallengeId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(67);

		sb.append("<model><model-name>");
		sb.append("kisti.edison.challenge.model.ChildChallenge");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>childChallengeId</column-name><column-value><![CDATA[");
		sb.append(getChildChallengeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>number</column-name><column-value><![CDATA[");
		sb.append(getNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>presentationDay</column-name><column-value><![CDATA[");
		sb.append(getPresentationDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperStartDay</column-name><column-value><![CDATA[");
		sb.append(getPaperStartDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paperEndDay</column-name><column-value><![CDATA[");
		sb.append(getPaperEndDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>evaluationDay</column-name><column-value><![CDATA[");
		sb.append(getEvaluationDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeStartDay</column-name><column-value><![CDATA[");
		sb.append(getChallengeStartDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeEndDay</column-name><column-value><![CDATA[");
		sb.append(getChallengeEndDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeStatus</column-name><column-value><![CDATA[");
		sb.append(getChallengeStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeId</column-name><column-value><![CDATA[");
		sb.append(getChallengeId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _childChallengeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUserName;
	private Date _statusDate;
	private int _number;
	private Date _presentationDay;
	private Date _paperStartDay;
	private Date _paperEndDay;
	private Date _evaluationDay;
	private Date _challengeStartDay;
	private Date _challengeEndDay;
	private String _challengeStatus;
	private long _challengeId;
	private BaseModel<?> _childChallengeRemoteModel;
	private Class<?> _clpSerializerClass = kisti.edison.challenge.service.ClpSerializer.class;
}