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

package com.kisti.osp.icecap.service.impl;

import java.util.Map;

import com.kisti.osp.icecap.NoSuchDataTypeException;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.base.DataTypeStructureLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the data type structure local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icecap.service.DataTypeStructureLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataTypeStructureLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil
 */
public class DataTypeStructureLocalServiceImpl
	extends DataTypeStructureLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil} to access the data type structure local service.
	 */
	/****
	 * Create a data type structure object
	 */
	public boolean createDataTypeStructureObject(
			Long dataTypeId, 			  // data type id
			String dataTypeStructureJSON // data type structure
			) throws SystemException{
		DataType dataTypeObject = null;
		try {
			dataTypeObject = dataTypePersistence.findByTypeId(dataTypeId);
		} catch (NoSuchDataTypeException e) {
			dataTypeObject = dataTypePersistence.create(dataTypeId);
		} 

		// check again if a matching data type object
		// this must not happen...
		if(dataTypeObject == null) throw new SystemException("No such data type");

		// let's now create a data type structure object
		DataTypeStructure dataTypeStructureObject = null;
		dataTypeStructureObject = super.dataTypeStructurePersistence.fetchByTypeID(dataTypeId);
		
		// check if there's no data type structure created yet
		if(dataTypeStructureObject == null){
			dataTypeStructureObject = super.dataTypeStructurePersistence.create(dataTypeId);
		}
		dataTypeStructureObject.setStructure(dataTypeStructureJSON);
		
//		/* Let's update this data type structure row. */
		super.dataTypeStructurePersistence.update(dataTypeStructureObject);
		return true;
	}
	
	/****
	 * Retrieve a DataTypeStructure object
	 * @param typeId DataType typeId
	 * @return Map<String, Object>
	 * @throws SystemException
	 */
	public Map<String, Object> retrieveDataTypeStructurePK(long typeId) throws SystemException{
		// let's now find a DataTypeStructure object
		DataTypeStructure dataTypeStructureObject = super.dataTypeStructurePersistence.fetchByTypeID(typeId);
		// return dataTypeStructureObject contents
		return dataTypeStructureObject.getModelAttributes();
	}
}