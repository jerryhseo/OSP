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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import edison.challenge.service.builder.service.ClpSerializer;
import edison.challenge.service.builder.service.MemberLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kyj
 */
public class MemberClp extends BaseModelImpl<Member> implements Member {
	public MemberClp() {
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
	public String getPrimaryKey() {
		return _memberid;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setMemberid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _memberid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
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

	@Override
	public String getMemberid() {
		return _memberid;
	}

	@Override
	public void setMemberid(String memberid) {
		_memberid = memberid;

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setMemberid", String.class);

				method.invoke(_memberRemoteModel, memberid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_memberRemoteModel, name);
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

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setLeader", boolean.class);

				method.invoke(_memberRemoteModel, leader);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFalculty() {
		return _falculty;
	}

	@Override
	public void setFalculty(String falculty) {
		_falculty = falculty;

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setFalculty", String.class);

				method.invoke(_memberRemoteModel, falculty);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMajor() {
		return _major;
	}

	@Override
	public void setMajor(String major) {
		_major = major;

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setMajor", String.class);

				method.invoke(_memberRemoteModel, major);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
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

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setGrade", String.class);

				method.invoke(_memberRemoteModel, grade);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTeamid() {
		return _teamid;
	}

	@Override
	public void setTeamid(long teamid) {
		_teamid = teamid;

		if (_memberRemoteModel != null) {
			try {
				Class<?> clazz = _memberRemoteModel.getClass();

				Method method = clazz.getMethod("setTeamid", long.class);

				method.invoke(_memberRemoteModel, teamid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMemberRemoteModel() {
		return _memberRemoteModel;
	}

	public void setMemberRemoteModel(BaseModel<?> memberRemoteModel) {
		_memberRemoteModel = memberRemoteModel;
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

		Class<?> remoteModelClass = _memberRemoteModel.getClass();

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

		Object returnValue = method.invoke(_memberRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			MemberLocalServiceUtil.addMember(this);
		}
		else {
			MemberLocalServiceUtil.updateMember(this);
		}
	}

	@Override
	public Member toEscapedModel() {
		return (Member)ProxyUtil.newProxyInstance(Member.class.getClassLoader(),
			new Class[] { Member.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MemberClp clone = new MemberClp();

		clone.setMemberid(getMemberid());
		clone.setName(getName());
		clone.setLeader(getLeader());
		clone.setFalculty(getFalculty());
		clone.setMajor(getMajor());
		clone.setGrade(getGrade());
		clone.setTeamid(getTeamid());

		return clone;
	}

	@Override
	public int compareTo(Member member) {
		int value = 0;

		if (getTeamid() < member.getTeamid()) {
			value = -1;
		}
		else if (getTeamid() > member.getTeamid()) {
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

		if (!(obj instanceof MemberClp)) {
			return false;
		}

		MemberClp member = (MemberClp)obj;

		String primaryKey = member.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
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
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{memberid=");
		sb.append(getMemberid());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", leader=");
		sb.append(getLeader());
		sb.append(", falculty=");
		sb.append(getFalculty());
		sb.append(", major=");
		sb.append(getMajor());
		sb.append(", grade=");
		sb.append(getGrade());
		sb.append(", teamid=");
		sb.append(getTeamid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.Member");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>memberid</column-name><column-value><![CDATA[");
		sb.append(getMemberid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>leader</column-name><column-value><![CDATA[");
		sb.append(getLeader());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>falculty</column-name><column-value><![CDATA[");
		sb.append(getFalculty());
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
			"<column><column-name>teamid</column-name><column-value><![CDATA[");
		sb.append(getTeamid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _memberid;
	private String _name;
	private boolean _leader;
	private String _falculty;
	private String _major;
	private String _grade;
	private long _teamid;
	private BaseModel<?> _memberRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}