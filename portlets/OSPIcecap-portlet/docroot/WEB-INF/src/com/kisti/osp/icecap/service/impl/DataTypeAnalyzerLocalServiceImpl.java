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
import com.kisti.osp.icecap.service.base.DataTypeAnalyzerLocalServiceBaseImpl;
import com.kisti.osp.icecap.service.persistence.DataTypeAnalyzerPK;
import com.kisti.osp.icecap.service.persistence.DataTypeEditorPK;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the data type analyzer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.osp.icecap.service.DataTypeAnalyzerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see com.kisti.osp.icecap.service.base.DataTypeAnalyzerLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil
 */
public class DataTypeAnalyzerLocalServiceImpl
	extends DataTypeAnalyzerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.osp.icecap.service.DataTypeAnalyzerLocalServiceUtil} to access the data type analyzer local service.
	 */
	public boolean createDataTypeAnalyzerObject(
			Long dataTypeId, 		 // data type id
			boolean isDefault, 		// default?
			Long dataTypeAnalyzerId // data type analyzer id
			) throws SystemException{
		DataTypeAnalyzer dataTypeAnalyzer = null;
		List<DataTypeAnalyzer> dataTypeAnalyzerList = super.dataTypeAnalyzerPersistence.findByAnalyzer(dataTypeAnalyzerId);
		for(int i=0;i<dataTypeAnalyzerList.size();i++){
			DataTypeAnalyzer dtaItem = dataTypeAnalyzerList.get(i);
			if(dtaItem.getTypeId() == dataTypeId && dtaItem.getAnalyzerId() == dataTypeAnalyzerId){
				System.out.println("Found a matching data analyzer id!");
				// hit!
				dataTypeAnalyzer = dtaItem;
				break;
			}
		}
		
		DataTypeAnalyzerPK dataTypeAnalyzerPK = null;
		if(dataTypeAnalyzer == null){
			System.out.println("Creating a new data type analyzer object...");
			
			dataTypeAnalyzerPK = new DataTypeAnalyzerPK();
			dataTypeAnalyzerPK.setTypeId(dataTypeId);
			dataTypeAnalyzerPK.setAnalyzerId(dataTypeAnalyzerId);
			dataTypeAnalyzer = super.dataTypeAnalyzerPersistence.create(dataTypeAnalyzerPK);
		}else{
			// do nothing so far
			dataTypeAnalyzerPK = dataTypeAnalyzer.getPrimaryKey();
		}
		dataTypeAnalyzer.setIsDefault(isDefault);
		
		/* Let's update this data collection row. */
		super.dataTypeAnalyzerPersistence.update(dataTypeAnalyzer);
		return true;
	}
	
	/****
	 * Retrieve a matching DataTypeAnalyzer with typeId and servicePK
	 * @param typeId a given DataType typeId 
	 * @param servicePK analyzerId
	 * @return A DataTypeAnalyzer object
	 * @throws SystemException
	 */
	public Map<String, Object> retrieveDataTypeAnalyzerPK(long typeId,
			long servicePK) throws SystemException{
		// let's prepare for a container for a DataTypeAnalyzer object
		Map<String, Object> resMap = new HashMap<String, Object>();
		// gather all DataTypeAnalyzer objects 
		List<DataTypeAnalyzer> dataTypeAnalyzerList = super.dataTypeAnalyzerPersistence.findByTypeID(typeId);
		if(dataTypeAnalyzerList != null){
			for(int i=0;i<dataTypeAnalyzerList.size();i++){
				DataTypeAnalyzer dtaObj = dataTypeAnalyzerList.get(i);
				DataTypeAnalyzerPK dtaPKObj = dtaObj.getPrimaryKey();
				// how to compare it to servicePK?
				if(dtaPKObj.getAnalyzerId() == servicePK){
					resMap = dtaObj.getModelAttributes();
					break;	
				}
			}
		}
		return resMap;
	}
	
	/****
	 * Retrieve a list of DataTypeAnalyzer objects
	 * @param typeId typeid
	 * @return a list of DataTypeAnalyzer objects
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveDataTypeAnalyzerList(long typeId) throws SystemException{
		List<Map<String, Object>> resMap = new ArrayList<Map<String, Object>>();
		List<DataTypeAnalyzer> dataTypeAnalyzerList = super.dataTypeAnalyzerPersistence.findByTypeID(typeId);
		if(dataTypeAnalyzerList != null){
			for(int i=0;i<dataTypeAnalyzerList.size();i++){
				resMap.add(dataTypeAnalyzerList.get(i).getModelAttributes());
			}
		}
		return resMap;
	}
	
	/***
	 * Remove a DataTypeAnalyzer object associated with a given typeId and a given servicePK
	 * @param typeId typeId
	 * @param servicePK analyerId
	 * @throws SystemException
	 * @throws NoSuchDataTypeAnalyzerException 
	 */
	public void removeDataTypeAnalyzerByPK(long typeId, 
			long servicePK) throws SystemException, NoSuchDataTypeAnalyzerException{
		DataTypeAnalyzerPK dataTypeAnalyzerPK = new DataTypeAnalyzerPK();
		// set type id
		dataTypeAnalyzerPK.setTypeId(typeId);
		// set analyzer id = servicePK
		dataTypeAnalyzerPK.setAnalyzerId(servicePK);
		// remove DataTypeAnalyzer objects
		super.dataTypeAnalyzerPersistence.remove(dataTypeAnalyzerPK);
		return;
	}
	
	/****
	 * Remove DataTypeAnalyzer by typeId
	 * @param typeId typeId
	 * @throws SystemException
	 * @throws NoSuchDataTypeAnalyzerException
	 */
	public void removeDataTypeAnalyzerByTypeId(long typeId) throws SystemException, NoSuchDataTypeAnalyzerException{
		super.dataTypeAnalyzerPersistence.removeByTypeID(typeId);
		return;
	}
}