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

package edison.challenge.service.builder.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Member}.
 * </p>
 *
 * @author kyj
 * @see Member
 * @generated
 */
public class MemberWrapper implements Member, ModelWrapper<Member> {
	public MemberWrapper(Member member) {
		_member = member;
	}

	@Override
	public Class<?> getModelClass() {
		return Member.class;
	}

	@Override
	public String getModelClassName() {
		return Member.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("memberid", getMemberid());
		attributes.put("name", getName());
		attributes.put("leader", getLeader());
		attributes.put("falculty", getFalculty());
		attributes.put("major", getMajor());
		attributes.put("grade", getGrade());
		attributes.put("teamid", getTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String memberid = (String)attributes.get("memberid");

		if (memberid != null) {
			setMemberid(memberid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean leader = (Boolean)attributes.get("leader");

		if (leader != null) {
			setLeader(leader);
		}

		String falculty = (String)attributes.get("falculty");

		if (falculty != null) {
			setFalculty(falculty);
		}

		String major = (String)attributes.get("major");

		if (major != null) {
			setMajor(major);
		}

		String grade = (String)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		Long teamid = (Long)attributes.get("teamid");

		if (teamid != null) {
			setTeamid(teamid);
		}
	}

	/**
	* Returns the primary key of this member.
	*
	* @return the primary key of this member
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _member.getPrimaryKey();
	}

	/**
	* Sets the primary key of this member.
	*
	* @param primaryKey the primary key of this member
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_member.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the memberid of this member.
	*
	* @return the memberid of this member
	*/
	@Override
	public java.lang.String getMemberid() {
		return _member.getMemberid();
	}

	/**
	* Sets the memberid of this member.
	*
	* @param memberid the memberid of this member
	*/
	@Override
	public void setMemberid(java.lang.String memberid) {
		_member.setMemberid(memberid);
	}

	/**
	* Returns the name of this member.
	*
	* @return the name of this member
	*/
	@Override
	public java.lang.String getName() {
		return _member.getName();
	}

	/**
	* Sets the name of this member.
	*
	* @param name the name of this member
	*/
	@Override
	public void setName(java.lang.String name) {
		_member.setName(name);
	}

	/**
	* Returns the leader of this member.
	*
	* @return the leader of this member
	*/
	@Override
	public boolean getLeader() {
		return _member.getLeader();
	}

	/**
	* Returns <code>true</code> if this member is leader.
	*
	* @return <code>true</code> if this member is leader; <code>false</code> otherwise
	*/
	@Override
	public boolean isLeader() {
		return _member.isLeader();
	}

	/**
	* Sets whether this member is leader.
	*
	* @param leader the leader of this member
	*/
	@Override
	public void setLeader(boolean leader) {
		_member.setLeader(leader);
	}

	/**
	* Returns the falculty of this member.
	*
	* @return the falculty of this member
	*/
	@Override
	public java.lang.String getFalculty() {
		return _member.getFalculty();
	}

	/**
	* Sets the falculty of this member.
	*
	* @param falculty the falculty of this member
	*/
	@Override
	public void setFalculty(java.lang.String falculty) {
		_member.setFalculty(falculty);
	}

	/**
	* Returns the major of this member.
	*
	* @return the major of this member
	*/
	@Override
	public java.lang.String getMajor() {
		return _member.getMajor();
	}

	/**
	* Sets the major of this member.
	*
	* @param major the major of this member
	*/
	@Override
	public void setMajor(java.lang.String major) {
		_member.setMajor(major);
	}

	/**
	* Returns the grade of this member.
	*
	* @return the grade of this member
	*/
	@Override
	public java.lang.String getGrade() {
		return _member.getGrade();
	}

	/**
	* Sets the grade of this member.
	*
	* @param grade the grade of this member
	*/
	@Override
	public void setGrade(java.lang.String grade) {
		_member.setGrade(grade);
	}

	/**
	* Returns the teamid of this member.
	*
	* @return the teamid of this member
	*/
	@Override
	public long getTeamid() {
		return _member.getTeamid();
	}

	/**
	* Sets the teamid of this member.
	*
	* @param teamid the teamid of this member
	*/
	@Override
	public void setTeamid(long teamid) {
		_member.setTeamid(teamid);
	}

	@Override
	public boolean isNew() {
		return _member.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_member.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _member.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_member.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _member.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _member.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_member.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _member.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_member.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_member.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_member.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MemberWrapper((Member)_member.clone());
	}

	@Override
	public int compareTo(edison.challenge.service.builder.model.Member member) {
		return _member.compareTo(member);
	}

	@Override
	public int hashCode() {
		return _member.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<edison.challenge.service.builder.model.Member> toCacheModel() {
		return _member.toCacheModel();
	}

	@Override
	public edison.challenge.service.builder.model.Member toEscapedModel() {
		return new MemberWrapper(_member.toEscapedModel());
	}

	@Override
	public edison.challenge.service.builder.model.Member toUnescapedModel() {
		return new MemberWrapper(_member.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _member.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _member.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_member.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MemberWrapper)) {
			return false;
		}

		MemberWrapper memberWrapper = (MemberWrapper)obj;

		if (Validator.equals(_member, memberWrapper._member)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Member getWrappedMember() {
		return _member;
	}

	@Override
	public Member getWrappedModel() {
		return _member;
	}

	@Override
	public void resetOriginalValues() {
		_member.resetOriginalValues();
	}

	private Member _member;
}