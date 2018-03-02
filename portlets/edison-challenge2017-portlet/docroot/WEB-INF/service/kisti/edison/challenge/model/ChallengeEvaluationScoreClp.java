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

import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KYJ
 */
public class ChallengeEvaluationScoreClp extends BaseModelImpl<ChallengeEvaluationScore>
	implements ChallengeEvaluationScore {
	public ChallengeEvaluationScoreClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeEvaluationScore.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeEvaluationScore.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _challengeEvaluationScoreId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChallengeEvaluationScoreId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _challengeEvaluationScoreId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeEvaluationScoreId",
			getChallengeEvaluationScoreId());
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
		attributes.put("score", getScore());
		attributes.put("challengeTeamId", getChallengeTeamId());
		attributes.put("challengeEvaluationManagementId",
			getChallengeEvaluationManagementId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeEvaluationScoreId = (Long)attributes.get(
				"challengeEvaluationScoreId");

		if (challengeEvaluationScoreId != null) {
			setChallengeEvaluationScoreId(challengeEvaluationScoreId);
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

		Double score = (Double)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Long challengeTeamId = (Long)attributes.get("challengeTeamId");

		if (challengeTeamId != null) {
			setChallengeTeamId(challengeTeamId);
		}

		Long challengeEvaluationManagementId = (Long)attributes.get(
				"challengeEvaluationManagementId");

		if (challengeEvaluationManagementId != null) {
			setChallengeEvaluationManagementId(challengeEvaluationManagementId);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeEvaluationScoreId() {
		return _challengeEvaluationScoreId;
	}

	@Override
	public void setChallengeEvaluationScoreId(long challengeEvaluationScoreId) {
		_challengeEvaluationScoreId = challengeEvaluationScoreId;

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeEvaluationScoreId",
						long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel,
					challengeEvaluationScoreId);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, groupId);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, companyId);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, userId);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, userName);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, createDate);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, modifiedDate);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, status);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel,
					statusByUserId);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_challengeEvaluationScoreRemoteModel,
					statusByUserName);
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

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getScore() {
		return _score;
	}

	@Override
	public void setScore(double score) {
		_score = score;

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setScore", double.class);

				method.invoke(_challengeEvaluationScoreRemoteModel, score);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeTeamId() {
		return _challengeTeamId;
	}

	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamId = challengeTeamId;

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeTeamId", long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel,
					challengeTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeEvaluationManagementId() {
		return _challengeEvaluationManagementId;
	}

	@Override
	public void setChallengeEvaluationManagementId(
		long challengeEvaluationManagementId) {
		_challengeEvaluationManagementId = challengeEvaluationManagementId;

		if (_challengeEvaluationScoreRemoteModel != null) {
			try {
				Class<?> clazz = _challengeEvaluationScoreRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeEvaluationManagementId",
						long.class);

				method.invoke(_challengeEvaluationScoreRemoteModel,
					challengeEvaluationManagementId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ChallengeEvaluationScore.class.getName()));
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

	public BaseModel<?> getChallengeEvaluationScoreRemoteModel() {
		return _challengeEvaluationScoreRemoteModel;
	}

	public void setChallengeEvaluationScoreRemoteModel(
		BaseModel<?> challengeEvaluationScoreRemoteModel) {
		_challengeEvaluationScoreRemoteModel = challengeEvaluationScoreRemoteModel;
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

		Class<?> remoteModelClass = _challengeEvaluationScoreRemoteModel.getClass();

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

		Object returnValue = method.invoke(_challengeEvaluationScoreRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChallengeEvaluationScoreLocalServiceUtil.addChallengeEvaluationScore(this);
		}
		else {
			ChallengeEvaluationScoreLocalServiceUtil.updateChallengeEvaluationScore(this);
		}
	}

	@Override
	public ChallengeEvaluationScore toEscapedModel() {
		return (ChallengeEvaluationScore)ProxyUtil.newProxyInstance(ChallengeEvaluationScore.class.getClassLoader(),
			new Class[] { ChallengeEvaluationScore.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ChallengeEvaluationScoreClp clone = new ChallengeEvaluationScoreClp();

		clone.setUuid(getUuid());
		clone.setChallengeEvaluationScoreId(getChallengeEvaluationScoreId());
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
		clone.setScore(getScore());
		clone.setChallengeTeamId(getChallengeTeamId());
		clone.setChallengeEvaluationManagementId(getChallengeEvaluationManagementId());

		return clone;
	}

	@Override
	public int compareTo(ChallengeEvaluationScore challengeEvaluationScore) {
		int value = 0;

		if (getChallengeEvaluationScoreId() < challengeEvaluationScore.getChallengeEvaluationScoreId()) {
			value = -1;
		}
		else if (getChallengeEvaluationScoreId() > challengeEvaluationScore.getChallengeEvaluationScoreId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(getCreateDate(),
				challengeEvaluationScore.getCreateDate());

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

		if (!(obj instanceof ChallengeEvaluationScoreClp)) {
			return false;
		}

		ChallengeEvaluationScoreClp challengeEvaluationScore = (ChallengeEvaluationScoreClp)obj;

		long primaryKey = challengeEvaluationScore.getPrimaryKey();

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
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", challengeEvaluationScoreId=");
		sb.append(getChallengeEvaluationScoreId());
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
		sb.append(", score=");
		sb.append(getScore());
		sb.append(", challengeTeamId=");
		sb.append(getChallengeTeamId());
		sb.append(", challengeEvaluationManagementId=");
		sb.append(getChallengeEvaluationManagementId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("kisti.edison.challenge.model.ChallengeEvaluationScore");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeEvaluationScoreId</column-name><column-value><![CDATA[");
		sb.append(getChallengeEvaluationScoreId());
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
			"<column><column-name>score</column-name><column-value><![CDATA[");
		sb.append(getScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeTeamId</column-name><column-value><![CDATA[");
		sb.append(getChallengeTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeEvaluationManagementId</column-name><column-value><![CDATA[");
		sb.append(getChallengeEvaluationManagementId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _challengeEvaluationScoreId;
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
	private double _score;
	private long _challengeTeamId;
	private long _challengeEvaluationManagementId;
	private BaseModel<?> _challengeEvaluationScoreRemoteModel;
	private Class<?> _clpSerializerClass = kisti.edison.challenge.service.ClpSerializer.class;
}