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
 * The extended model interface for the DataCollection service. Represents a row in the &quot;icecap_DataCollection&quot; database table, with each column mapped to a property of this class.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionModel
 * @see com.kisti.osp.icecap.model.impl.DataCollectionImpl
 * @see com.kisti.osp.icecap.model.impl.DataCollectionModelImpl
 * @generated
 */
public interface DataCollection extends DataCollectionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.kisti.osp.icecap.model.impl.DataCollectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DataCollection, String> UUID_ACCESSOR = new Accessor<DataCollection, String>() {
			@Override
			public String get(DataCollection dataCollection) {
				return dataCollection.getUuid();
			}
		};
}