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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException;
import com.kisti.osp.icecap.NoSuchDataTypeEditorException;
import com.kisti.osp.icecap.model.DataTypeAnalyzer;
import com.kisti.osp.icecap.model.DataTypeEditor;
import com.kisti.osp.icecap.service.base.DataTypeEditorLocalServiceBaseImpl;
import com.kisti.osp.icecap.service.persistence.DataTypeEditorPK;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the data type editor local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icecap.service.DataTypeEditorLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataTypeEditorLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil
 */
public class DataTypeEditorLocalServiceImpl
	extends DataTypeEditorLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.osp.icecap.service.DataTypeEditorLocalServiceUtil} to access the data type editor local service.
	 */
	public boolean createDataTypeEditorObject(
			Long dataTypeId, 		 // data type id
			boolean isDefault, 		// is this default?
			Long dataTypeEditorId // data type editor id
			) throws SystemException{
		DataTypeEditor dataTypeEditor = null;
		List<DataTypeEditor> dataTypeEditorList = super.dataTypeEditorPersistence.findByEditor(dataTypeEditorId);
		for(int i=0;i<dataTypeEditorList.size();i++){
			DataTypeEditor dteItem = dataTypeEditorList.get(i);
			if(dteItem.getTypeId() == dataTypeId && dteItem.getEditorId() == dataTypeEditorId){
				System.out.println("Found a matching DataTypeEditor editorId!");
				// hit!
				dataTypeEditor = dteItem;
				break;
			}
		}
		
		/**
		 * Declare dataTypeEditor PK object
		 */
		DataTypeEditorPK dataTypeEditorPK = null;
		if(dataTypeEditor == null){
			System.out.println("Creating a new data type editor object...");
			
			dataTypeEditorPK = new DataTypeEditorPK();
			dataTypeEditorPK.setTypeId(dataTypeId);
			dataTypeEditorPK.setEditorId(dataTypeEditorId);
			dataTypeEditor = super.dataTypeEditorPersistence.create(dataTypeEditorPK);
		}else{
			// do nothing so far
			dataTypeEditorPK = dataTypeEditor.getPrimaryKey();
		}
		dataTypeEditor.setIsDefault(isDefault);
		
		/* Let's update this data collection row. */
		super.dataTypeEditorPersistence.update(dataTypeEditor);
		return true;
	}
	
	/****
	 * Retrieve a matching DataTypeEditor with a given typeId and a given servicePK
	 * @param typeId a given DataType typeId 
	 * @param servicePK a given editorId 
	 * @return Map<String, Object> object representing a matching DataTypeEditor object
	 * @throws SystemException
	 */
	public Map<String, Object> retrieveDataTypeEditorPK(long typeId,
			long servicePK) throws SystemException{
		// declare a Map object to return
		Map<String, Object> resMap = new HashMap<String, Object>();
		// get every DataTypeObject matching a given typeId
		List<DataTypeEditor> dataTypeEditorList = super.dataTypeEditorPersistence.findByTypeID(typeId);
		if(dataTypeEditorList != null){
			for(int i=0;i<dataTypeEditorList.size();i++){
				// now locate a matching DataTypeEditor having the same editorId as servicePK
				DataTypeEditor dteObj = dataTypeEditorList.get(i);
				DataTypeEditorPK dtePKObj = dteObj.getPrimaryKey();
				// is it the same as servicePK?
				if(dtePKObj.getEditorId() == servicePK){
					// now we found it!
					resMap = dteObj.getModelAttributes();
					break;	
				}
			}
		}
		return resMap;
	}
	
	/****
	 * Retrieve a list of DataTypeEditor objects
	 * @param typeId typeid
	 * @return a list of DataTypeEditor objects
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveDataTypeEditorList(long typeId) throws SystemException{
		// an Map object to return
		List<Map<String, Object>> resMap = new ArrayList<Map<String, Object>>();
		List<DataTypeEditor> dataTypeEditorList = super.dataTypeEditorPersistence.findByTypeID(typeId);
		if(dataTypeEditorList != null){
			for(int i=0;i<dataTypeEditorList.size();i++){
				// gather DataTypeEditor objects
				DataTypeEditor dteObject = dataTypeEditorList.get(i);
				if(dteObject != null) // if this is non-null, then let's add this object
					resMap.add(dteObject.getModelAttributes());
			}
		}
		return resMap;
	}
	
	
	/***
	 * Remove a DataTypeEditor object's PK associated with a given typeId and a given servicePK
	 * @param typeId typeId
	 * @param servicePK editorId
	 * @throws SystemException
	 * @throws NoSuchDataTypeEditorException 
	 */
	public void removeDataTypeEditorByPK(long typeId, 
			long servicePK) throws SystemException, NoSuchDataTypeEditorException{
		// prepare for a PK object
		DataTypeEditorPK dataTypeEditorPK = new DataTypeEditorPK();
		// set type id
		dataTypeEditorPK.setTypeId(typeId);
		// set editorId = servicePK
		dataTypeEditorPK.setEditorId(servicePK);
		// now let's remove an object having this PK
		super.dataTypeEditorPersistence.remove(dataTypeEditorPK);
		return;
	}
	
	/****
	 * Remove DataTypeEditor by typeId
	 * @param typeId typeId
	 * @throws SystemException
	 * @throws NoSuchDataTypeEditorException
	 */
	public void removeDataTypeEditorByTypeId(long typeId) throws SystemException, NoSuchDataTypeAnalyzerException{
		// remove all DataTypeEditor objects having a given typeId
		super.dataTypeEditorPersistence.removeByTypeID(typeId);
		return;
	}
}