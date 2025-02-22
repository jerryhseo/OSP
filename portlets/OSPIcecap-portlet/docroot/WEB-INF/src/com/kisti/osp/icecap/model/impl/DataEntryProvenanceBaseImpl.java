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

package com.kisti.osp.icecap.model.impl;

import com.kisti.osp.icecap.model.DataEntryProvenance;
import com.kisti.osp.icecap.service.DataEntryProvenanceLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the DataEntryProvenance service. Represents a row in the &quot;icecap_DataEntryProvenance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DataEntryProvenanceImpl}.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryProvenanceImpl
 * @see com.kisti.osp.icecap.model.DataEntryProvenance
 * @generated
 */
public abstract class DataEntryProvenanceBaseImpl
	extends DataEntryProvenanceModelImpl implements DataEntryProvenance {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a data entry provenance model instance should use the {@link DataEntryProvenance} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DataEntryProvenanceLocalServiceUtil.addDataEntryProvenance(this);
		}
		else {
			DataEntryProvenanceLocalServiceUtil.updateDataEntryProvenance(this);
		}
	}
}