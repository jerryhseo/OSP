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

package com.kisti.osp.icecap.model;

import com.kisti.osp.icecap.service.persistence.DataTypeEditorPK;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the DataTypeEditor service. Represents a row in the &quot;icecap_DataTypeEditor&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.kisti.osp.icecap.model.impl.DataTypeEditorImpl}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditor
 * @see com.kisti.osp.icecap.model.impl.DataTypeEditorImpl
 * @see com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl
 * @generated
 */
public interface DataTypeEditorModel extends BaseModel<DataTypeEditor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a data type editor model instance should use the {@link DataTypeEditor} interface instead.
	 */

	/**
	 * Returns the primary key of this data type editor.
	 *
	 * @return the primary key of this data type editor
	 */
	public DataTypeEditorPK getPrimaryKey();

	/**
	 * Sets the primary key of this data type editor.
	 *
	 * @param primaryKey the primary key of this data type editor
	 */
	public void setPrimaryKey(DataTypeEditorPK primaryKey);

	/**
	 * Returns the type ID of this data type editor.
	 *
	 * @return the type ID of this data type editor
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this data type editor.
	 *
	 * @param typeId the type ID of this data type editor
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the editor ID of this data type editor.
	 *
	 * @return the editor ID of this data type editor
	 */
	public long getEditorId();

	/**
	 * Sets the editor ID of this data type editor.
	 *
	 * @param editorId the editor ID of this data type editor
	 */
	public void setEditorId(long editorId);

	/**
	 * Returns the is default of this data type editor.
	 *
	 * @return the is default of this data type editor
	 */
	public boolean getIsDefault();

	/**
	 * Returns <code>true</code> if this data type editor is is default.
	 *
	 * @return <code>true</code> if this data type editor is is default; <code>false</code> otherwise
	 */
	public boolean isIsDefault();

	/**
	 * Sets whether this data type editor is is default.
	 *
	 * @param isDefault the is default of this data type editor
	 */
	public void setIsDefault(boolean isDefault);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.kisti.osp.icecap.model.DataTypeEditor> toCacheModel();

	@Override
	public com.kisti.osp.icecap.model.DataTypeEditor toEscapedModel();

	@Override
	public com.kisti.osp.icecap.model.DataTypeEditor toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}