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

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DataType service. Represents a row in the &quot;icecap_DataType&quot; database table, with each column mapped to a property of this class.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeModel
 * @see com.kisti.osp.icecap.model.impl.DataTypeImpl
 * @see com.kisti.osp.icecap.model.impl.DataTypeModelImpl
 * @generated
 */
public interface DataType extends DataTypeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.kisti.osp.icecap.model.impl.DataTypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DataType, String> UUID_ACCESSOR = new Accessor<DataType, String>() {
			@Override
			public String get(DataType dataType) {
				return dataType.getUuid();
			}
		};
}