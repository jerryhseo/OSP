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

package org.kisti.edison.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import org.kisti.edison.model.SimulationJob;
import org.kisti.edison.model.SimulationJobModel;
import org.kisti.edison.service.persistence.SimulationJobPK;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SimulationJob service. Represents a row in the &quot;EDOV_SimulationJob&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.kisti.edison.model.SimulationJobModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SimulationJobImpl}.
 * </p>
 *
 * @author edison
 * @see SimulationJobImpl
 * @see org.kisti.edison.model.SimulationJob
 * @see org.kisti.edison.model.SimulationJobModel
 * @generated
 */
public class SimulationJobModelImpl extends BaseModelImpl<SimulationJob>
	implements SimulationJobModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a simulation job model instance should use the {@link org.kisti.edison.model.SimulationJob} interface instead.
	 */
	public static final String TABLE_NAME = "EDOV_SimulationJob";
	public static final Object[][] TABLE_COLUMNS = {
			{ "createDate", Types.VARCHAR },
			{ "groupId", Types.BIGINT },
			{ "cnt", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table EDOV_SimulationJob (createDate VARCHAR(75) not null,groupId LONG not null,cnt LONG,primary key (createDate, groupId))";
	public static final String TABLE_SQL_DROP = "drop table EDOV_SimulationJob";
	public static final String ORDER_BY_JPQL = " ORDER BY simulationJob.id.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY EDOV_SimulationJob.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "true";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.kisti.edison.model.SimulationJob"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.kisti.edison.model.SimulationJob"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.kisti.edison.model.SimulationJob"),
			true);
	public static long GROUPID_COLUMN_BITMASK = 1L;
	public static long CREATEDATE_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.kisti.edison.model.SimulationJob"));

	public SimulationJobModelImpl() {
	}

	@Override
	public SimulationJobPK getPrimaryKey() {
		return new SimulationJobPK(_createDate, _groupId);
	}

	@Override
	public void setPrimaryKey(SimulationJobPK primaryKey) {
		setCreateDate(primaryKey.createDate);
		setGroupId(primaryKey.groupId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new SimulationJobPK(_createDate, _groupId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((SimulationJobPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return SimulationJob.class;
	}

	@Override
	public String getModelClassName() {
		return SimulationJob.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createDate", getCreateDate());
		attributes.put("groupId", getGroupId());
		attributes.put("cnt", getCnt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String createDate = (String)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long cnt = (Long)attributes.get("cnt");

		if (cnt != null) {
			setCnt(cnt);
		}
	}

	@Override
	public String getCreateDate() {
		if (_createDate == null) {
			return StringPool.BLANK;
		}
		else {
			return _createDate;
		}
	}

	@Override
	public void setCreateDate(String createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public Long getCnt() {
		return _cnt;
	}

	@Override
	public void setCnt(Long cnt) {
		_cnt = cnt;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public SimulationJob toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SimulationJob)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SimulationJobImpl simulationJobImpl = new SimulationJobImpl();

		simulationJobImpl.setCreateDate(getCreateDate());
		simulationJobImpl.setGroupId(getGroupId());
		simulationJobImpl.setCnt(getCnt());

		simulationJobImpl.resetOriginalValues();

		return simulationJobImpl;
	}

	@Override
	public int compareTo(SimulationJob simulationJob) {
		int value = 0;

		value = getCreateDate().compareTo(simulationJob.getCreateDate());

		value = value * -1;

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

		if (!(obj instanceof SimulationJob)) {
			return false;
		}

		SimulationJob simulationJob = (SimulationJob)obj;

		SimulationJobPK primaryKey = simulationJob.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public void resetOriginalValues() {
		SimulationJobModelImpl simulationJobModelImpl = this;

		simulationJobModelImpl._originalGroupId = simulationJobModelImpl._groupId;

		simulationJobModelImpl._setOriginalGroupId = false;

		simulationJobModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SimulationJob> toCacheModel() {
		SimulationJobCacheModel simulationJobCacheModel = new SimulationJobCacheModel();

		simulationJobCacheModel.createDate = getCreateDate();

		String createDate = simulationJobCacheModel.createDate;

		if ((createDate != null) && (createDate.length() == 0)) {
			simulationJobCacheModel.createDate = null;
		}

		simulationJobCacheModel.groupId = getGroupId();

		simulationJobCacheModel.cnt = getCnt();

		return simulationJobCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{createDate=");
		sb.append(getCreateDate());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", cnt=");
		sb.append(getCnt());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("org.kisti.edison.model.SimulationJob");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cnt</column-name><column-value><![CDATA[");
		sb.append(getCnt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = SimulationJob.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			SimulationJob.class
		};
	private String _createDate;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private Long _cnt;
	private long _columnBitmask;
	private SimulationJob _escapedModel;
}