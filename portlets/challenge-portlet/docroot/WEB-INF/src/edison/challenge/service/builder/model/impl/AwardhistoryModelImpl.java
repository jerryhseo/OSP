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

package edison.challenge.service.builder.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import edison.challenge.service.builder.model.Awardhistory;
import edison.challenge.service.builder.model.AwardhistoryModel;
import edison.challenge.service.builder.model.AwardhistorySoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Awardhistory service. Represents a row in the &quot;edison_Awardhistory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link edison.challenge.service.builder.model.AwardhistoryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AwardhistoryImpl}.
 * </p>
 *
 * @author kyj
 * @see AwardhistoryImpl
 * @see edison.challenge.service.builder.model.Awardhistory
 * @see edison.challenge.service.builder.model.AwardhistoryModel
 * @generated
 */
@JSON(strict = true)
public class AwardhistoryModelImpl extends BaseModelImpl<Awardhistory>
	implements AwardhistoryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a awardhistory model instance should use the {@link edison.challenge.service.builder.model.Awardhistory} interface instead.
	 */
	public static final String TABLE_NAME = "edison_Awardhistory";
	public static final Object[][] TABLE_COLUMNS = {
			{ "awardhistoryid", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "prize", Types.VARCHAR },
			{ "chTeamid", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table edison_Awardhistory (awardhistoryid LONG not null primary key,name VARCHAR(75) null,prize VARCHAR(75) null,chTeamid LONG)";
	public static final String TABLE_SQL_DROP = "drop table edison_Awardhistory";
	public static final String ORDER_BY_JPQL = " ORDER BY awardhistory.chTeamid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY edison_Awardhistory.chTeamid ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.edison.challenge.service.builder.model.Awardhistory"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.edison.challenge.service.builder.model.Awardhistory"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.edison.challenge.service.builder.model.Awardhistory"),
			true);
	public static long CHTEAMID_COLUMN_BITMASK = 1L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Awardhistory toModel(AwardhistorySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Awardhistory model = new AwardhistoryImpl();

		model.setAwardhistoryid(soapModel.getAwardhistoryid());
		model.setName(soapModel.getName());
		model.setPrize(soapModel.getPrize());
		model.setChTeamid(soapModel.getChTeamid());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Awardhistory> toModels(AwardhistorySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Awardhistory> models = new ArrayList<Awardhistory>(soapModels.length);

		for (AwardhistorySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.edison.challenge.service.builder.model.Awardhistory"));

	public AwardhistoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _awardhistoryid;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAwardhistoryid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _awardhistoryid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Awardhistory.class;
	}

	@Override
	public String getModelClassName() {
		return Awardhistory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("awardhistoryid", getAwardhistoryid());
		attributes.put("name", getName());
		attributes.put("prize", getPrize());
		attributes.put("chTeamid", getChTeamid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long awardhistoryid = (Long)attributes.get("awardhistoryid");

		if (awardhistoryid != null) {
			setAwardhistoryid(awardhistoryid);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String prize = (String)attributes.get("prize");

		if (prize != null) {
			setPrize(prize);
		}

		Long chTeamid = (Long)attributes.get("chTeamid");

		if (chTeamid != null) {
			setChTeamid(chTeamid);
		}
	}

	@JSON
	@Override
	public long getAwardhistoryid() {
		return _awardhistoryid;
	}

	@Override
	public void setAwardhistoryid(long awardhistoryid) {
		_awardhistoryid = awardhistoryid;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getPrize() {
		if (_prize == null) {
			return StringPool.BLANK;
		}
		else {
			return _prize;
		}
	}

	@Override
	public void setPrize(String prize) {
		_prize = prize;
	}

	@JSON
	@Override
	public long getChTeamid() {
		return _chTeamid;
	}

	@Override
	public void setChTeamid(long chTeamid) {
		_columnBitmask = -1L;

		if (!_setOriginalChTeamid) {
			_setOriginalChTeamid = true;

			_originalChTeamid = _chTeamid;
		}

		_chTeamid = chTeamid;
	}

	public long getOriginalChTeamid() {
		return _originalChTeamid;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Awardhistory.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Awardhistory toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Awardhistory)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AwardhistoryImpl awardhistoryImpl = new AwardhistoryImpl();

		awardhistoryImpl.setAwardhistoryid(getAwardhistoryid());
		awardhistoryImpl.setName(getName());
		awardhistoryImpl.setPrize(getPrize());
		awardhistoryImpl.setChTeamid(getChTeamid());

		awardhistoryImpl.resetOriginalValues();

		return awardhistoryImpl;
	}

	@Override
	public int compareTo(Awardhistory awardhistory) {
		int value = 0;

		if (getChTeamid() < awardhistory.getChTeamid()) {
			value = -1;
		}
		else if (getChTeamid() > awardhistory.getChTeamid()) {
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

		if (!(obj instanceof Awardhistory)) {
			return false;
		}

		Awardhistory awardhistory = (Awardhistory)obj;

		long primaryKey = awardhistory.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		AwardhistoryModelImpl awardhistoryModelImpl = this;

		awardhistoryModelImpl._originalChTeamid = awardhistoryModelImpl._chTeamid;

		awardhistoryModelImpl._setOriginalChTeamid = false;

		awardhistoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Awardhistory> toCacheModel() {
		AwardhistoryCacheModel awardhistoryCacheModel = new AwardhistoryCacheModel();

		awardhistoryCacheModel.awardhistoryid = getAwardhistoryid();

		awardhistoryCacheModel.name = getName();

		String name = awardhistoryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			awardhistoryCacheModel.name = null;
		}

		awardhistoryCacheModel.prize = getPrize();

		String prize = awardhistoryCacheModel.prize;

		if ((prize != null) && (prize.length() == 0)) {
			awardhistoryCacheModel.prize = null;
		}

		awardhistoryCacheModel.chTeamid = getChTeamid();

		return awardhistoryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{awardhistoryid=");
		sb.append(getAwardhistoryid());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", prize=");
		sb.append(getPrize());
		sb.append(", chTeamid=");
		sb.append(getChTeamid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("edison.challenge.service.builder.model.Awardhistory");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>awardhistoryid</column-name><column-value><![CDATA[");
		sb.append(getAwardhistoryid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>prize</column-name><column-value><![CDATA[");
		sb.append(getPrize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>chTeamid</column-name><column-value><![CDATA[");
		sb.append(getChTeamid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Awardhistory.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Awardhistory.class
		};
	private long _awardhistoryid;
	private String _name;
	private String _prize;
	private long _chTeamid;
	private long _originalChTeamid;
	private boolean _setOriginalChTeamid;
	private long _columnBitmask;
	private Awardhistory _escapedModel;
}