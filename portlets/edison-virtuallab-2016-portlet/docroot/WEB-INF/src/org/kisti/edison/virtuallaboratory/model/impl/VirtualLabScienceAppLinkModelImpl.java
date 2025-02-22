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

package org.kisti.edison.virtuallaboratory.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkModel;
import org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the VirtualLabScienceAppLink service. Represents a row in the &quot;EDVIR_VirtualLabScienceAppLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VirtualLabScienceAppLinkImpl}.
 * </p>
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkImpl
 * @see org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink
 * @see org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLinkModel
 * @generated
 */
@JSON(strict = true)
public class VirtualLabScienceAppLinkModelImpl extends BaseModelImpl<VirtualLabScienceAppLink>
	implements VirtualLabScienceAppLinkModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a virtual lab science app link model instance should use the {@link org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink} interface instead.
	 */
	public static final String TABLE_NAME = "EDVIR_VirtualLabScienceAppLink";
	public static final Object[][] TABLE_COLUMNS = {
			{ "scienceAppSeqNo", Types.BIGINT },
			{ "scienceAppId", Types.BIGINT },
			{ "virtualLabId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table EDVIR_VirtualLabScienceAppLink (scienceAppSeqNo LONG not null primary key,scienceAppId LONG,virtualLabId LONG)";
	public static final String TABLE_SQL_DROP = "drop table EDVIR_VirtualLabScienceAppLink";
	public static final String ORDER_BY_JPQL = " ORDER BY virtualLabScienceAppLink.scienceAppSeqNo ASC";
	public static final String ORDER_BY_SQL = " ORDER BY EDVIR_VirtualLabScienceAppLink.scienceAppSeqNo ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink"),
			true);
	public static long VIRTUALLABID_COLUMN_BITMASK = 1L;
	public static long SCIENCEAPPSEQNO_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static VirtualLabScienceAppLink toModel(
		VirtualLabScienceAppLinkSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		VirtualLabScienceAppLink model = new VirtualLabScienceAppLinkImpl();

		model.setScienceAppSeqNo(soapModel.getScienceAppSeqNo());
		model.setScienceAppId(soapModel.getScienceAppId());
		model.setVirtualLabId(soapModel.getVirtualLabId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<VirtualLabScienceAppLink> toModels(
		VirtualLabScienceAppLinkSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<VirtualLabScienceAppLink> models = new ArrayList<VirtualLabScienceAppLink>(soapModels.length);

		for (VirtualLabScienceAppLinkSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink"));

	public VirtualLabScienceAppLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScienceAppSeqNo(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualLabScienceAppLink.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualLabScienceAppLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scienceAppSeqNo", getScienceAppSeqNo());
		attributes.put("scienceAppId", getScienceAppId());
		attributes.put("virtualLabId", getVirtualLabId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scienceAppSeqNo = (Long)attributes.get("scienceAppSeqNo");

		if (scienceAppSeqNo != null) {
			setScienceAppSeqNo(scienceAppSeqNo);
		}

		Long scienceAppId = (Long)attributes.get("scienceAppId");

		if (scienceAppId != null) {
			setScienceAppId(scienceAppId);
		}

		Long virtualLabId = (Long)attributes.get("virtualLabId");

		if (virtualLabId != null) {
			setVirtualLabId(virtualLabId);
		}
	}

	@JSON
	@Override
	public long getScienceAppSeqNo() {
		return _scienceAppSeqNo;
	}

	@Override
	public void setScienceAppSeqNo(long scienceAppSeqNo) {
		_scienceAppSeqNo = scienceAppSeqNo;
	}

	@JSON
	@Override
	public long getScienceAppId() {
		return _scienceAppId;
	}

	@Override
	public void setScienceAppId(long scienceAppId) {
		_scienceAppId = scienceAppId;
	}

	@JSON
	@Override
	public long getVirtualLabId() {
		return _virtualLabId;
	}

	@Override
	public void setVirtualLabId(long virtualLabId) {
		_columnBitmask |= VIRTUALLABID_COLUMN_BITMASK;

		if (!_setOriginalVirtualLabId) {
			_setOriginalVirtualLabId = true;

			_originalVirtualLabId = _virtualLabId;
		}

		_virtualLabId = virtualLabId;
	}

	public long getOriginalVirtualLabId() {
		return _originalVirtualLabId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			VirtualLabScienceAppLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public VirtualLabScienceAppLink toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (VirtualLabScienceAppLink)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		VirtualLabScienceAppLinkImpl virtualLabScienceAppLinkImpl = new VirtualLabScienceAppLinkImpl();

		virtualLabScienceAppLinkImpl.setScienceAppSeqNo(getScienceAppSeqNo());
		virtualLabScienceAppLinkImpl.setScienceAppId(getScienceAppId());
		virtualLabScienceAppLinkImpl.setVirtualLabId(getVirtualLabId());

		virtualLabScienceAppLinkImpl.resetOriginalValues();

		return virtualLabScienceAppLinkImpl;
	}

	@Override
	public int compareTo(VirtualLabScienceAppLink virtualLabScienceAppLink) {
		long primaryKey = virtualLabScienceAppLink.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VirtualLabScienceAppLink)) {
			return false;
		}

		VirtualLabScienceAppLink virtualLabScienceAppLink = (VirtualLabScienceAppLink)obj;

		long primaryKey = virtualLabScienceAppLink.getPrimaryKey();

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
		VirtualLabScienceAppLinkModelImpl virtualLabScienceAppLinkModelImpl = this;

		virtualLabScienceAppLinkModelImpl._originalVirtualLabId = virtualLabScienceAppLinkModelImpl._virtualLabId;

		virtualLabScienceAppLinkModelImpl._setOriginalVirtualLabId = false;

		virtualLabScienceAppLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<VirtualLabScienceAppLink> toCacheModel() {
		VirtualLabScienceAppLinkCacheModel virtualLabScienceAppLinkCacheModel = new VirtualLabScienceAppLinkCacheModel();

		virtualLabScienceAppLinkCacheModel.scienceAppSeqNo = getScienceAppSeqNo();

		virtualLabScienceAppLinkCacheModel.scienceAppId = getScienceAppId();

		virtualLabScienceAppLinkCacheModel.virtualLabId = getVirtualLabId();

		return virtualLabScienceAppLinkCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{scienceAppSeqNo=");
		sb.append(getScienceAppSeqNo());
		sb.append(", scienceAppId=");
		sb.append(getScienceAppId());
		sb.append(", virtualLabId=");
		sb.append(getVirtualLabId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"org.kisti.edison.virtuallaboratory.model.VirtualLabScienceAppLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scienceAppSeqNo</column-name><column-value><![CDATA[");
		sb.append(getScienceAppSeqNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scienceAppId</column-name><column-value><![CDATA[");
		sb.append(getScienceAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualLabId</column-name><column-value><![CDATA[");
		sb.append(getVirtualLabId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = VirtualLabScienceAppLink.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			VirtualLabScienceAppLink.class
		};
	private long _scienceAppSeqNo;
	private long _scienceAppId;
	private long _virtualLabId;
	private long _originalVirtualLabId;
	private boolean _setOriginalVirtualLabId;
	private long _columnBitmask;
	private VirtualLabScienceAppLink _escapedModel;
}