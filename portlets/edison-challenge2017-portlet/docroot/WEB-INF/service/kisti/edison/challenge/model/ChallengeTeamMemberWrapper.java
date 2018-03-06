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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChallengeTeamMember}.
 * </p>
 *
 * @author KYJ
 * @see ChallengeTeamMember
 * @generated
 */
public class ChallengeTeamMemberWrapper implements ChallengeTeamMember,
	ModelWrapper<ChallengeTeamMember> {
	public ChallengeTeamMemberWrapper(ChallengeTeamMember challengeTeamMember) {
		_challengeTeamMember = challengeTeamMember;
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

	/**
	* Returns the primary key of this challenge team member.
	*
	* @return the primary key of this challenge team member
	*/
	@Override
	public long getPrimaryKey() {
		return _challengeTeamMember.getPrimaryKey();
	}

	/**
	* Sets the primary key of this challenge team member.
	*
	* @param primaryKey the primary key of this challenge team member
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_challengeTeamMember.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this challenge team member.
	*
	* @return the uuid of this challenge team member
	*/
	@Override
	public java.lang.String getUuid() {
		return _challengeTeamMember.getUuid();
	}

	/**
	* Sets the uuid of this challenge team member.
	*
	* @param uuid the uuid of this challenge team member
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_challengeTeamMember.setUuid(uuid);
	}

	/**
	* Returns the challenge team member ID of this challenge team member.
	*
	* @return the challenge team member ID of this challenge team member
	*/
	@Override
	public long getChallengeTeamMemberId() {
		return _challengeTeamMember.getChallengeTeamMemberId();
	}

	/**
	* Sets the challenge team member ID of this challenge team member.
	*
	* @param challengeTeamMemberId the challenge team member ID of this challenge team member
	*/
	@Override
	public void setChallengeTeamMemberId(long challengeTeamMemberId) {
		_challengeTeamMember.setChallengeTeamMemberId(challengeTeamMemberId);
	}

	/**
	* Returns the group ID of this challenge team member.
	*
	* @return the group ID of this challenge team member
	*/
	@Override
	public long getGroupId() {
		return _challengeTeamMember.getGroupId();
	}

	/**
	* Sets the group ID of this challenge team member.
	*
	* @param groupId the group ID of this challenge team member
	*/
	@Override
	public void setGroupId(long groupId) {
		_challengeTeamMember.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this challenge team member.
	*
	* @return the company ID of this challenge team member
	*/
	@Override
	public long getCompanyId() {
		return _challengeTeamMember.getCompanyId();
	}

	/**
	* Sets the company ID of this challenge team member.
	*
	* @param companyId the company ID of this challenge team member
	*/
	@Override
	public void setCompanyId(long companyId) {
		_challengeTeamMember.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this challenge team member.
	*
	* @return the user ID of this challenge team member
	*/
	@Override
	public long getUserId() {
		return _challengeTeamMember.getUserId();
	}

	/**
	* Sets the user ID of this challenge team member.
	*
	* @param userId the user ID of this challenge team member
	*/
	@Override
	public void setUserId(long userId) {
		_challengeTeamMember.setUserId(userId);
	}

	/**
	* Returns the user uuid of this challenge team member.
	*
	* @return the user uuid of this challenge team member
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamMember.getUserUuid();
	}

	/**
	* Sets the user uuid of this challenge team member.
	*
	* @param userUuid the user uuid of this challenge team member
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_challengeTeamMember.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this challenge team member.
	*
	* @return the user name of this challenge team member
	*/
	@Override
	public java.lang.String getUserName() {
		return _challengeTeamMember.getUserName();
	}

	/**
	* Sets the user name of this challenge team member.
	*
	* @param userName the user name of this challenge team member
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_challengeTeamMember.setUserName(userName);
	}

	/**
	* Returns the create date of this challenge team member.
	*
	* @return the create date of this challenge team member
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _challengeTeamMember.getCreateDate();
	}

	/**
	* Sets the create date of this challenge team member.
	*
	* @param createDate the create date of this challenge team member
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_challengeTeamMember.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this challenge team member.
	*
	* @return the modified date of this challenge team member
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _challengeTeamMember.getModifiedDate();
	}

	/**
	* Sets the modified date of this challenge team member.
	*
	* @param modifiedDate the modified date of this challenge team member
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_challengeTeamMember.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the status of this challenge team member.
	*
	* @return the status of this challenge team member
	*/
	@Override
	public int getStatus() {
		return _challengeTeamMember.getStatus();
	}

	/**
	* Sets the status of this challenge team member.
	*
	* @param status the status of this challenge team member
	*/
	@Override
	public void setStatus(int status) {
		_challengeTeamMember.setStatus(status);
	}

	/**
	* Returns the status by user ID of this challenge team member.
	*
	* @return the status by user ID of this challenge team member
	*/
	@Override
	public long getStatusByUserId() {
		return _challengeTeamMember.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this challenge team member.
	*
	* @param statusByUserId the status by user ID of this challenge team member
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_challengeTeamMember.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this challenge team member.
	*
	* @return the status by user uuid of this challenge team member
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamMember.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this challenge team member.
	*
	* @param statusByUserUuid the status by user uuid of this challenge team member
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_challengeTeamMember.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this challenge team member.
	*
	* @return the status by user name of this challenge team member
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _challengeTeamMember.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this challenge team member.
	*
	* @param statusByUserName the status by user name of this challenge team member
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_challengeTeamMember.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this challenge team member.
	*
	* @return the status date of this challenge team member
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _challengeTeamMember.getStatusDate();
	}

	/**
	* Sets the status date of this challenge team member.
	*
	* @param statusDate the status date of this challenge team member
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_challengeTeamMember.setStatusDate(statusDate);
	}

	/**
	* Returns the apply user ID of this challenge team member.
	*
	* @return the apply user ID of this challenge team member
	*/
	@Override
	public long getApplyUserId() {
		return _challengeTeamMember.getApplyUserId();
	}

	/**
	* Sets the apply user ID of this challenge team member.
	*
	* @param applyUserId the apply user ID of this challenge team member
	*/
	@Override
	public void setApplyUserId(long applyUserId) {
		_challengeTeamMember.setApplyUserId(applyUserId);
	}

	/**
	* Returns the apply user uuid of this challenge team member.
	*
	* @return the apply user uuid of this challenge team member
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getApplyUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamMember.getApplyUserUuid();
	}

	/**
	* Sets the apply user uuid of this challenge team member.
	*
	* @param applyUserUuid the apply user uuid of this challenge team member
	*/
	@Override
	public void setApplyUserUuid(java.lang.String applyUserUuid) {
		_challengeTeamMember.setApplyUserUuid(applyUserUuid);
	}

	/**
	* Returns the apply user name of this challenge team member.
	*
	* @return the apply user name of this challenge team member
	*/
	@Override
	public java.lang.String getApplyUserName() {
		return _challengeTeamMember.getApplyUserName();
	}

	/**
	* Sets the apply user name of this challenge team member.
	*
	* @param applyUserName the apply user name of this challenge team member
	*/
	@Override
	public void setApplyUserName(java.lang.String applyUserName) {
		_challengeTeamMember.setApplyUserName(applyUserName);
	}

	/**
	* Returns the email of this challenge team member.
	*
	* @return the email of this challenge team member
	*/
	@Override
	public java.lang.String getEmail() {
		return _challengeTeamMember.getEmail();
	}

	/**
	* Sets the email of this challenge team member.
	*
	* @param email the email of this challenge team member
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_challengeTeamMember.setEmail(email);
	}

	/**
	* Returns the institute of this challenge team member.
	*
	* @return the institute of this challenge team member
	*/
	@Override
	public java.lang.String getInstitute() {
		return _challengeTeamMember.getInstitute();
	}

	/**
	* Returns the localized institute of this challenge team member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized institute of this challenge team member
	*/
	@Override
	public java.lang.String getInstitute(java.util.Locale locale) {
		return _challengeTeamMember.getInstitute(locale);
	}

	/**
	* Returns the localized institute of this challenge team member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized institute of this challenge team member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getInstitute(java.util.Locale locale,
		boolean useDefault) {
		return _challengeTeamMember.getInstitute(locale, useDefault);
	}

	/**
	* Returns the localized institute of this challenge team member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized institute of this challenge team member
	*/
	@Override
	public java.lang.String getInstitute(java.lang.String languageId) {
		return _challengeTeamMember.getInstitute(languageId);
	}

	/**
	* Returns the localized institute of this challenge team member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized institute of this challenge team member
	*/
	@Override
	public java.lang.String getInstitute(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeamMember.getInstitute(languageId, useDefault);
	}

	@Override
	public java.lang.String getInstituteCurrentLanguageId() {
		return _challengeTeamMember.getInstituteCurrentLanguageId();
	}

	@Override
	public java.lang.String getInstituteCurrentValue() {
		return _challengeTeamMember.getInstituteCurrentValue();
	}

	/**
	* Returns a map of the locales and localized institutes of this challenge team member.
	*
	* @return the locales and localized institutes of this challenge team member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getInstituteMap() {
		return _challengeTeamMember.getInstituteMap();
	}

	/**
	* Sets the institute of this challenge team member.
	*
	* @param institute the institute of this challenge team member
	*/
	@Override
	public void setInstitute(java.lang.String institute) {
		_challengeTeamMember.setInstitute(institute);
	}

	/**
	* Sets the localized institute of this challenge team member in the language.
	*
	* @param institute the localized institute of this challenge team member
	* @param locale the locale of the language
	*/
	@Override
	public void setInstitute(java.lang.String institute, java.util.Locale locale) {
		_challengeTeamMember.setInstitute(institute, locale);
	}

	/**
	* Sets the localized institute of this challenge team member in the language, and sets the default locale.
	*
	* @param institute the localized institute of this challenge team member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setInstitute(java.lang.String institute,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_challengeTeamMember.setInstitute(institute, locale, defaultLocale);
	}

	@Override
	public void setInstituteCurrentLanguageId(java.lang.String languageId) {
		_challengeTeamMember.setInstituteCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized institutes of this challenge team member from the map of locales and localized institutes.
	*
	* @param instituteMap the locales and localized institutes of this challenge team member
	*/
	@Override
	public void setInstituteMap(
		java.util.Map<java.util.Locale, java.lang.String> instituteMap) {
		_challengeTeamMember.setInstituteMap(instituteMap);
	}

	/**
	* Sets the localized institutes of this challenge team member from the map of locales and localized institutes, and sets the default locale.
	*
	* @param instituteMap the locales and localized institutes of this challenge team member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setInstituteMap(
		java.util.Map<java.util.Locale, java.lang.String> instituteMap,
		java.util.Locale defaultLocale) {
		_challengeTeamMember.setInstituteMap(instituteMap, defaultLocale);
	}

	/**
	* Returns the major of this challenge team member.
	*
	* @return the major of this challenge team member
	*/
	@Override
	public java.lang.String getMajor() {
		return _challengeTeamMember.getMajor();
	}

	/**
	* Returns the localized major of this challenge team member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized major of this challenge team member
	*/
	@Override
	public java.lang.String getMajor(java.util.Locale locale) {
		return _challengeTeamMember.getMajor(locale);
	}

	/**
	* Returns the localized major of this challenge team member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized major of this challenge team member. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getMajor(java.util.Locale locale, boolean useDefault) {
		return _challengeTeamMember.getMajor(locale, useDefault);
	}

	/**
	* Returns the localized major of this challenge team member in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized major of this challenge team member
	*/
	@Override
	public java.lang.String getMajor(java.lang.String languageId) {
		return _challengeTeamMember.getMajor(languageId);
	}

	/**
	* Returns the localized major of this challenge team member in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized major of this challenge team member
	*/
	@Override
	public java.lang.String getMajor(java.lang.String languageId,
		boolean useDefault) {
		return _challengeTeamMember.getMajor(languageId, useDefault);
	}

	@Override
	public java.lang.String getMajorCurrentLanguageId() {
		return _challengeTeamMember.getMajorCurrentLanguageId();
	}

	@Override
	public java.lang.String getMajorCurrentValue() {
		return _challengeTeamMember.getMajorCurrentValue();
	}

	/**
	* Returns a map of the locales and localized majors of this challenge team member.
	*
	* @return the locales and localized majors of this challenge team member
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getMajorMap() {
		return _challengeTeamMember.getMajorMap();
	}

	/**
	* Sets the major of this challenge team member.
	*
	* @param major the major of this challenge team member
	*/
	@Override
	public void setMajor(java.lang.String major) {
		_challengeTeamMember.setMajor(major);
	}

	/**
	* Sets the localized major of this challenge team member in the language.
	*
	* @param major the localized major of this challenge team member
	* @param locale the locale of the language
	*/
	@Override
	public void setMajor(java.lang.String major, java.util.Locale locale) {
		_challengeTeamMember.setMajor(major, locale);
	}

	/**
	* Sets the localized major of this challenge team member in the language, and sets the default locale.
	*
	* @param major the localized major of this challenge team member
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMajor(java.lang.String major, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_challengeTeamMember.setMajor(major, locale, defaultLocale);
	}

	@Override
	public void setMajorCurrentLanguageId(java.lang.String languageId) {
		_challengeTeamMember.setMajorCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized majors of this challenge team member from the map of locales and localized majors.
	*
	* @param majorMap the locales and localized majors of this challenge team member
	*/
	@Override
	public void setMajorMap(
		java.util.Map<java.util.Locale, java.lang.String> majorMap) {
		_challengeTeamMember.setMajorMap(majorMap);
	}

	/**
	* Sets the localized majors of this challenge team member from the map of locales and localized majors, and sets the default locale.
	*
	* @param majorMap the locales and localized majors of this challenge team member
	* @param defaultLocale the default locale
	*/
	@Override
	public void setMajorMap(
		java.util.Map<java.util.Locale, java.lang.String> majorMap,
		java.util.Locale defaultLocale) {
		_challengeTeamMember.setMajorMap(majorMap, defaultLocale);
	}

	/**
	* Returns the grade of this challenge team member.
	*
	* @return the grade of this challenge team member
	*/
	@Override
	public java.lang.String getGrade() {
		return _challengeTeamMember.getGrade();
	}

	/**
	* Sets the grade of this challenge team member.
	*
	* @param grade the grade of this challenge team member
	*/
	@Override
	public void setGrade(java.lang.String grade) {
		_challengeTeamMember.setGrade(grade);
	}

	/**
	* Returns the phone of this challenge team member.
	*
	* @return the phone of this challenge team member
	*/
	@Override
	public java.lang.String getPhone() {
		return _challengeTeamMember.getPhone();
	}

	/**
	* Sets the phone of this challenge team member.
	*
	* @param phone the phone of this challenge team member
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_challengeTeamMember.setPhone(phone);
	}

	/**
	* Returns the leader of this challenge team member.
	*
	* @return the leader of this challenge team member
	*/
	@Override
	public boolean getLeader() {
		return _challengeTeamMember.getLeader();
	}

	/**
	* Returns <code>true</code> if this challenge team member is leader.
	*
	* @return <code>true</code> if this challenge team member is leader; <code>false</code> otherwise
	*/
	@Override
	public boolean isLeader() {
		return _challengeTeamMember.isLeader();
	}

	/**
	* Sets whether this challenge team member is leader.
	*
	* @param leader the leader of this challenge team member
	*/
	@Override
	public void setLeader(boolean leader) {
		_challengeTeamMember.setLeader(leader);
	}

	/**
	* Returns the challenge team ID of this challenge team member.
	*
	* @return the challenge team ID of this challenge team member
	*/
	@Override
	public long getChallengeTeamId() {
		return _challengeTeamMember.getChallengeTeamId();
	}

	/**
	* Sets the challenge team ID of this challenge team member.
	*
	* @param challengeTeamId the challenge team ID of this challenge team member
	*/
	@Override
	public void setChallengeTeamId(long challengeTeamId) {
		_challengeTeamMember.setChallengeTeamId(challengeTeamId);
	}

	/**
	* @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	*/
	@Override
	public boolean getApproved() {
		return _challengeTeamMember.getApproved();
	}

	/**
	* Returns <code>true</code> if this challenge team member is approved.
	*
	* @return <code>true</code> if this challenge team member is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _challengeTeamMember.isApproved();
	}

	/**
	* Returns <code>true</code> if this challenge team member is denied.
	*
	* @return <code>true</code> if this challenge team member is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _challengeTeamMember.isDenied();
	}

	/**
	* Returns <code>true</code> if this challenge team member is a draft.
	*
	* @return <code>true</code> if this challenge team member is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _challengeTeamMember.isDraft();
	}

	/**
	* Returns <code>true</code> if this challenge team member is expired.
	*
	* @return <code>true</code> if this challenge team member is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _challengeTeamMember.isExpired();
	}

	/**
	* Returns <code>true</code> if this challenge team member is inactive.
	*
	* @return <code>true</code> if this challenge team member is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _challengeTeamMember.isInactive();
	}

	/**
	* Returns <code>true</code> if this challenge team member is incomplete.
	*
	* @return <code>true</code> if this challenge team member is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _challengeTeamMember.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this challenge team member is pending.
	*
	* @return <code>true</code> if this challenge team member is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _challengeTeamMember.isPending();
	}

	/**
	* Returns <code>true</code> if this challenge team member is scheduled.
	*
	* @return <code>true</code> if this challenge team member is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _challengeTeamMember.isScheduled();
	}

	@Override
	public boolean isNew() {
		return _challengeTeamMember.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_challengeTeamMember.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _challengeTeamMember.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_challengeTeamMember.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _challengeTeamMember.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _challengeTeamMember.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_challengeTeamMember.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _challengeTeamMember.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_challengeTeamMember.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_challengeTeamMember.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_challengeTeamMember.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _challengeTeamMember.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _challengeTeamMember.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_challengeTeamMember.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_challengeTeamMember.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new ChallengeTeamMemberWrapper((ChallengeTeamMember)_challengeTeamMember.clone());
	}

	@Override
	public int compareTo(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember) {
		return _challengeTeamMember.compareTo(challengeTeamMember);
	}

	@Override
	public int hashCode() {
		return _challengeTeamMember.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<kisti.edison.challenge.model.ChallengeTeamMember> toCacheModel() {
		return _challengeTeamMember.toCacheModel();
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeamMember toEscapedModel() {
		return new ChallengeTeamMemberWrapper(_challengeTeamMember.toEscapedModel());
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeamMember toUnescapedModel() {
		return new ChallengeTeamMemberWrapper(_challengeTeamMember.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _challengeTeamMember.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _challengeTeamMember.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeTeamMember.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChallengeTeamMemberWrapper)) {
			return false;
		}

		ChallengeTeamMemberWrapper challengeTeamMemberWrapper = (ChallengeTeamMemberWrapper)obj;

		if (Validator.equals(_challengeTeamMember,
					challengeTeamMemberWrapper._challengeTeamMember)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _challengeTeamMember.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ChallengeTeamMember getWrappedChallengeTeamMember() {
		return _challengeTeamMember;
	}

	@Override
	public ChallengeTeamMember getWrappedModel() {
		return _challengeTeamMember;
	}

	@Override
	public void resetOriginalValues() {
		_challengeTeamMember.resetOriginalValues();
	}

	private ChallengeTeamMember _challengeTeamMember;
}