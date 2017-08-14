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

package com.kisti.osp.icecap.service.persistence;

import com.kisti.osp.icecap.model.DataTypeEditor;
import com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Young-K. Suh and Jerry H. Seo
 * @generated
 */
public abstract class DataTypeEditorActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public DataTypeEditorActionableDynamicQuery() throws SystemException {
		setBaseLocalService(DataTypeEditorLocalServiceUtil.getService());
		setClass(DataTypeEditor.class);

		setClassLoader(com.kisti.osp.icecap.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("primaryKey.typeId");
	}
}