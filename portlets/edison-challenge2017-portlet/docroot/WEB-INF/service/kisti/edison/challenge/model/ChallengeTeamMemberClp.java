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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;
import kisti.edison.challenge.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author KYJ
 */
public class ChallengeTeamMemberClp extends BaseModelImpl<ChallengeTeamMember>
	implements ChallengeTeamMember {
	public ChallengeTeamMemberClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return ChallengeTeamMember.class;
	}

	@Override
	public String getModelClassName() {
		return ChallengeTeamMember.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _challengeTeamMemberId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChallengeTeamMemberId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _challengeTeamMemberId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("challengeTeamMemberId", getChallengeTeamMemberId());
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
		attributes.put("applyUserId", getApplyUserId());
		attributes.put("applyUserName", getApplyUserName());
		attributes.put("email", getEmail());
		attributes.put("institute", getInstitute());
		attributes.put("major", getMajor());
		attributes.put("grade", getGrade());
		attributes.put("phone", getPhone());
		attributes.put("leader", getLeader());
		attributes.put("challengeTeamId", getChallengeTeamId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long challengeTeamMemberId = (Long)attributes.get(
				"challengeTeamMemberId");

		if (challengeTeamMemberId != null) {
			setChallengeTeamMemberId(challengeTeamMemberId);
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

		Long applyUserId = (Long)attributes.get("applyUserId");

		if (applyUserId != null) {
			setApplyUserId(applyUserId);
		}

		String applyUserName = (String)attributes.get("applyUserName");

		if (applyUserName != null) {
			setApplyUserName(applyUserName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String institute = (String)attributes.get("institute");

		if (institute != null) {
			setInstitute(institute);
		}

		String major = (String)attributes.get("major");

		if (major != null) {
			setMajor(major);
		}

		String grade = (String)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		Boolean leader = (Boolean)attributes.get("leader");

		if (leader != null) {
			setLeader(leader);
		}

		Long challengeTeamId = (Long)attributes.get("challengeTeamId");

		if (challengeTeamId != null) {
			setChallengeTeamId(challengeTeamId);
		}
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeTeamMemberId() {
		return _challengeTeamMemberId;
	}

	@Override
	public void setChallengeTeamMemberId(long challengeTeamMemberId) {
		_challengeTeamMemberId = challengeTeamMemberId;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeTeamMemberId",
						long.class);

				method.invoke(_challengeTeamMemberRemoteModel,
					challengeTeamMemberId);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, groupId);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, companyId);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, userId);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, userName);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_challengeTeamMemberRemoteModel, createDate);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_challengeTeamMemberRemoteModel, modifiedDate);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_challengeTeamMemberRemoteModel, status);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, statusByUserId);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserName",
						String.class);

				method.invoke(_challengeTeamMemberRemoteModel, statusByUserName);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_challengeTeamMemberRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getApplyUserId() {
		return _applyUserId;
	}

	@Override
	public void setApplyUserId(long applyUserId) {
		_applyUserId = applyUserId;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setApplyUserId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, applyUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getApplyUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getApplyUserId(), "uuid", _applyUserUuid);
	}

	@Override
	public void setApplyUserUuid(String applyUserUuid) {
		_applyUserUuid = applyUserUuid;
	}

	@Override
	public String getApplyUserName() {
		return _applyUserName;
	}

	@Override
	public void setApplyUserName(String applyUserName) {
		_applyUserName = applyUserName;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setApplyUserName", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, applyUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmail() {
		return _email;
	}

	@Override
	public void setEmail(String email) {
		_email = email;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setEmail", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, email);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInstitute() {
		return _institute;
	}

	@Override
	public String getInstitute(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getInstitute(languageId);
	}

	@Override
	public String getInstitute(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getInstitute(languageId, useDefault);
	}

	@Override
	public String getInstitute(String languageId) {
		return LocalizationUtil.getLocalization(getInstitute(), languageId);
	}

	@Override
	public String getInstitute(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getInstitute(), languageId,
			useDefault);
	}

	@Override
	public String getInstituteCurrentLanguageId() {
		return _instituteCurrentLanguageId;
	}

	@Override
	public String getInstituteCurrentValue() {
		Locale locale = getLocale(_instituteCurrentLanguageId);

		return getInstitute(locale);
	}

	@Override
	public Map<Locale, String> getInstituteMap() {
		return LocalizationUtil.getLocalizationMap(getInstitute());
	}

	@Override
	public void setInstitute(String institute) {
		_institute = institute;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setInstitute", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, institute);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setInstitute(String institute, Locale locale) {
		setInstitute(institute, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setInstitute(String institute, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(institute)) {
			setInstitute(LocalizationUtil.updateLocalization(getInstitute(),
					"Institute", institute, languageId, defaultLanguageId));
		}
		else {
			setInstitute(LocalizationUtil.removeLocalization(getInstitute(),
					"Institute", languageId));
		}
	}

	@Override
	public void setInstituteCurrentLanguageId(String languageId) {
		_instituteCurrentLanguageId = languageId;
	}

	@Override
	public void setInstituteMap(Map<Locale, String> instituteMap) {
		setInstituteMap(instituteMap, LocaleUtil.getDefault());
	}

	@Override
	public void setInstituteMap(Map<Locale, String> instituteMap,
		Locale defaultLocale) {
		if (instituteMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setInstitute(LocalizationUtil.updateLocalization(instituteMap,
					getInstitute(), "Institute",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getMajor() {
		return _major;
	}

	@Override
	public String getMajor(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMajor(languageId);
	}

	@Override
	public String getMajor(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getMajor(languageId, useDefault);
	}

	@Override
	public String getMajor(String languageId) {
		return LocalizationUtil.getLocalization(getMajor(), languageId);
	}

	@Override
	public String getMajor(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getMajor(), languageId,
			useDefault);
	}

	@Override
	public String getMajorCurrentLanguageId() {
		return _majorCurrentLanguageId;
	}

	@Override
	public String getMajorCurrentValue() {
		Locale locale = getLocale(_majorCurrentLanguageId);

		return getMajor(locale);
	}

	@Override
	public Map<Locale, String> getMajorMap() {
		return LocalizationUtil.getLocalizationMap(getMajor());
	}

	@Override
	public void setMajor(String major) {
		_major = major;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setMajor", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, major);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setMajor(String major, Locale locale) {
		setMajor(major, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setMajor(String major, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(major)) {
			setMajor(LocalizationUtil.updateLocalization(getMajor(), "Major",
					major, languageId, defaultLanguageId));
		}
		else {
			setMajor(LocalizationUtil.removeLocalization(getMajor(), "Major",
					languageId));
		}
	}

	@Override
	public void setMajorCurrentLanguageId(String languageId) {
		_majorCurrentLanguageId = languageId;
	}

	@Override
	public void setMajorMap(Map<Locale, String> majorMap) {
		setMajorMap(majorMap, LocaleUtil.getDefault());
	}

	@Override
	public void setMajorMap(Map<Locale, String> majorMap, Locale defaultLocale) {
		if (majorMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setMajor(LocalizationUtil.updateLocalization(majorMap, getMajor(),
					"Major", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getGrade() {
		return _grade;
	}

	@Override
	public void setGrade(String grade) {
		_grade = grade;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setGrade", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, grade);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPhone() {
		return _phone;
	}

	@Override
	public void setPhone(String phone) {
		_phone = phone;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setPhone", String.class);

				method.invoke(_challengeTeamMemberRemoteModel, phone);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getLeader() {
		return _leader;
	}

	@Override
	public boolean isLeader() {
		return _leader;
	}

	@Override
	public void setLeader(boolean leader) {
		_leader = leader;

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setLeader", boolean.class);

				method.invoke(_challengeTeamMemberRemoteModel, leader);
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

		if (_challengeTeamMemberRemoteModel != null) {
			try {
				Class<?> clazz = _challengeTeamMemberRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeTeamId", long.class);

				method.invoke(_challengeTeamMemberRemoteModel, challengeTeamId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ChallengeTeamMember.class.getName()));
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

	public BaseModel<?> getChallengeTeamMemberRemoteModel() {
		return _challengeTeamMemberRemoteModel;
	}

	public void setChallengeTeamMemberRemoteModel(
		BaseModel<?> challengeTeamMemberRemoteModel) {
		_challengeTeamMemberRemoteModel = challengeTeamMemberRemoteModel;
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

		Class<?> remoteModelClass = _challengeTeamMemberRemoteModel.getClass();

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

		Object returnValue = method.invoke(_challengeTeamMemberRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ChallengeTeamMemberLocalServiceUtil.addChallengeTeamMember(this);
		}
		else {
			ChallengeTeamMemberLocalServiceUtil.updateChallengeTeamMember(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> instituteMap = getInstituteMap();

		for (Map.Entry<Locale, String> entry : instituteMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> majorMap = getMajorMap();

		for (Map.Entry<Locale, String> entry : majorMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getInstitute();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String institute = getInstitute(defaultLocale);

		if (Validator.isNull(institute)) {
			setInstitute(getInstitute(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setInstitute(getInstitute(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String major = getMajor(defaultLocale);

		if (Validator.isNull(major)) {
			setMajor(getMajor(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setMajor(getMajor(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public ChallengeTeamMember toEscapedModel() {
		return (ChallengeTeamMember)ProxyUtil.newProxyInstance(ChallengeTeamMember.class.getClassLoader(),
			new Class[] { ChallengeTeamMember.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ChallengeTeamMemberClp clone = new ChallengeTeamMemberClp();

		clone.setUuid(getUuid());
		clone.setChallengeTeamMemberId(getChallengeTeamMemberId());
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
		clone.setApplyUserId(getApplyUserId());
		clone.setApplyUserName(getApplyUserName());
		clone.setEmail(getEmail());
		clone.setInstitute(getInstitute());
		clone.setMajor(getMajor());
		clone.setGrade(getGrade());
		clone.setPhone(getPhone());
		clone.setLeader(getLeader());
		clone.setChallengeTeamId(getChallengeTeamId());

		return clone;
	}

	@Override
	public int compareTo(ChallengeTeamMember challengeTeamMember) {
		int value = 0;

		if (getChallengeTeamId() < challengeTeamMember.getChallengeTeamId()) {
			value = -1;
		}
		else if (getChallengeTeamId() > challengeTeamMember.getChallengeTeamId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getChallengeTeamMemberId() < challengeTeamMember.getChallengeTeamMemberId()) {
			value = -1;
		}
		else if (getChallengeTeamMemberId() > challengeTeamMember.getChallengeTeamMemberId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof ChallengeTeamMemberClp)) {
			return false;
		}

		ChallengeTeamMemberClp challengeTeamMember = (ChallengeTeamMemberClp)obj;

		long primaryKey = challengeTeamMember.getPrimaryKey();

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
		sb.append(", challengeTeamMemberId=");
		sb.append(getChallengeTeamMemberId());
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
		sb.append(", applyUserId=");
		sb.append(getApplyUserId());
		sb.append(", applyUserName=");
		sb.append(getApplyUserName());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", institute=");
		sb.append(getInstitute());
		sb.append(", major=");
		sb.append(getMajor());
		sb.append(", grade=");
		sb.append(getGrade());
		sb.append(", phone=");
		sb.append(getPhone());
		sb.append(", leader=");
		sb.append(getLeader());
		sb.append(", challengeTeamId=");
		sb.append(getChallengeTeamId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(67);

		sb.append("<model><model-name>");
		sb.append("kisti.edison.challenge.model.ChallengeTeamMember");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeTeamMemberId</column-name><column-value><![CDATA[");
		sb.append(getChallengeTeamMemberId());
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
			"<column><column-name>applyUserId</column-name><column-value><![CDATA[");
		sb.append(getApplyUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applyUserName</column-name><column-value><![CDATA[");
		sb.append(getApplyUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>institute</column-name><column-value><![CDATA[");
		sb.append(getInstitute());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>major</column-name><column-value><![CDATA[");
		sb.append(getMajor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grade</column-name><column-value><![CDATA[");
		sb.append(getGrade());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phone</column-name><column-value><![CDATA[");
		sb.append(getPhone());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>leader</column-name><column-value><![CDATA[");
		sb.append(getLeader());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeTeamId</column-name><column-value><![CDATA[");
		sb.append(getChallengeTeamId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _challengeTeamMemberId;
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
	private long _applyUserId;
	private String _applyUserUuid;
	private String _applyUserName;
	private String _email;
	private String _institute;
	private String _instituteCurrentLanguageId;
	private String _major;
	private String _majorCurrentLanguageId;
	private String _grade;
	private String _phone;
	private boolean _leader;
	private long _challengeTeamId;
	private BaseModel<?> _challengeTeamMemberRemoteModel;
	private Class<?> _clpSerializerClass = kisti.edison.challenge.service.ClpSerializer.class;
}