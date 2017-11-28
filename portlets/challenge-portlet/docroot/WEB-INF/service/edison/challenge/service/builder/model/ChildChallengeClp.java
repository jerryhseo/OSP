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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import edison.challenge.service.builder.service.ChildChallengeLocalServiceUtil;
import edison.challenge.service.builder.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kyj
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
		return _childid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChildid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _childid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("childid", getChildid());
		attributes.put("number", getNumber());
		attributes.put("presentationDay", getPresentationDay());
		attributes.put("paperStartDay", getPaperStartDay());
		attributes.put("paperEndDay", getPaperEndDay());
		attributes.put("evaluationStartDay", getEvaluationStartDay());
		attributes.put("evaluationEndDay", getEvaluationEndDay());
		attributes.put("challengeStartDay", getChallengeStartDay());
		attributes.put("challengeEndDay", getChallengeEndDay());
		attributes.put("status", getStatus());
		attributes.put("challengeid", getChallengeid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long childid = (Long)attributes.get("childid");

		if (childid != null) {
			setChildid(childid);
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

		Date evaluationStartDay = (Date)attributes.get("evaluationStartDay");

		if (evaluationStartDay != null) {
			setEvaluationStartDay(evaluationStartDay);
		}

		Date evaluationEndDay = (Date)attributes.get("evaluationEndDay");

		if (evaluationEndDay != null) {
			setEvaluationEndDay(evaluationEndDay);
		}

		Date challengeStartDay = (Date)attributes.get("challengeStartDay");

		if (challengeStartDay != null) {
			setChallengeStartDay(challengeStartDay);
		}

		Date challengeEndDay = (Date)attributes.get("challengeEndDay");

		if (challengeEndDay != null) {
			setChallengeEndDay(challengeEndDay);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long challengeid = (Long)attributes.get("challengeid");

		if (challengeid != null) {
			setChallengeid(challengeid);
		}
	}

	@Override
	public long getChildid() {
		return _childid;
	}

	@Override
	public void setChildid(long childid) {
		_childid = childid;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChildid", long.class);

				method.invoke(_childChallengeRemoteModel, childid);
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
	public Date getEvaluationStartDay() {
		return _evaluationStartDay;
	}

	@Override
	public void setEvaluationStartDay(Date evaluationStartDay) {
		_evaluationStartDay = evaluationStartDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setEvaluationStartDay",
						Date.class);

				method.invoke(_childChallengeRemoteModel, evaluationStartDay);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEvaluationEndDay() {
		return _evaluationEndDay;
	}

	@Override
	public void setEvaluationEndDay(Date evaluationEndDay) {
		_evaluationEndDay = evaluationEndDay;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setEvaluationEndDay",
						Date.class);

				method.invoke(_childChallengeRemoteModel, evaluationEndDay);
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
	public String getStatus() {
		return _status;
	}

	@Override
	public void setStatus(String status) {
		_status = status;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", String.class);

				method.invoke(_childChallengeRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getChallengeid() {
		return _challengeid;
	}

	@Override
	public void setChallengeid(long challengeid) {
		_challengeid = challengeid;

		if (_childChallengeRemoteModel != null) {
			try {
				Class<?> clazz = _childChallengeRemoteModel.getClass();

				Method method = clazz.getMethod("setChallengeid", long.class);

				method.invoke(_childChallengeRemoteModel, challengeid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
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

		clone.setChildid(getChildid());
		clone.setNumber(getNumber());
		clone.setPresentationDay(getPresentationDay());
		clone.setPaperStartDay(getPaperStartDay());
		clone.setPaperEndDay(getPaperEndDay());
		clone.setEvaluationStartDay(getEvaluationStartDay());
		clone.setEvaluationEndDay(getEvaluationEndDay());
		clone.setChallengeStartDay(getChallengeStartDay());
		clone.setChallengeEndDay(getChallengeEndDay());
		clone.setStatus(getStatus());
		clone.setChallengeid(getChallengeid());

		return clone;
	}

	@Override
	public int compareTo(ChildChallenge childChallenge) {
		int value = 0;

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
		StringBundler sb = new StringBundler(23);

		sb.append("{childid=");
		sb.append(getChildid());
		sb.append(", number=");
		sb.append(getNumber());
		sb.append(", presentationDay=");
		sb.append(getPresentationDay());
		sb.append(", paperStartDay=");
		sb.append(getPaperStartDay());
		sb.append(", paperEndDay=");
		sb.append(getPaperEndDay());
		sb.append(", evaluationStartDay=");
		sb.append(getEvaluationStartDay());
		sb.append(", evaluationEndDay=");
		sb.append(getEvaluationEndDay());
		sb.append(", challengeStartDay=");
		sb.append(getChallengeStartDay());
		sb.append(", challengeEndDay=");
		sb.append(getChallengeEndDay());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", challengeid=");
		sb.append(getChallengeid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.ChildChallenge");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>childid</column-name><column-value><![CDATA[");
		sb.append(getChildid());
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
			"<column><column-name>evaluationStartDay</column-name><column-value><![CDATA[");
		sb.append(getEvaluationStartDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>evaluationEndDay</column-name><column-value><![CDATA[");
		sb.append(getEvaluationEndDay());
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
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>challengeid</column-name><column-value><![CDATA[");
		sb.append(getChallengeid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _childid;
	private int _number;
	private Date _presentationDay;
	private Date _paperStartDay;
	private Date _paperEndDay;
	private Date _evaluationStartDay;
	private Date _evaluationEndDay;
	private Date _challengeStartDay;
	private Date _challengeEndDay;
	private String _status;
	private long _challengeid;
	private BaseModel<?> _childChallengeRemoteModel;
	private Class<?> _clpSerializerClass = edison.challenge.service.builder.service.ClpSerializer.class;
}