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

package com.kisti.osp.icecap.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DataTypeLocalService}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeLocalService
 * @generated
 */
public class DataTypeLocalServiceWrapper implements DataTypeLocalService,
	ServiceWrapper<DataTypeLocalService> {
	public DataTypeLocalServiceWrapper(
		DataTypeLocalService dataTypeLocalService) {
		_dataTypeLocalService = dataTypeLocalService;
	}

	/**
	* Adds the data type to the database. Also notifies the appropriate model listeners.
	*
	* @param dataType the data type
	* @return the data type that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType addDataType(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.addDataType(dataType);
	}

	/**
	* Creates a new data type with the primary key. Does not add the data type to the database.
	*
	* @param typeId the primary key for the new data type
	* @return the new data type
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType createDataType(long typeId) {
		return _dataTypeLocalService.createDataType(typeId);
	}

	/**
	* Deletes the data type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the data type
	* @return the data type that was removed
	* @throws PortalException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType deleteDataType(long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.deleteDataType(typeId);
	}

	/**
	* Deletes the data type from the database. Also notifies the appropriate model listeners.
	*
	* @param dataType the data type
	* @return the data type that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType deleteDataType(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.deleteDataType(dataType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dataTypeLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.kisti.osp.icecap.model.DataType fetchDataType(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.fetchDataType(typeId);
	}

	/**
	* Returns the data type with the matching UUID and company.
	*
	* @param uuid the data type's UUID
	* @param companyId the primary key of the company
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType fetchDataTypeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.fetchDataTypeByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the data type matching the UUID and group.
	*
	* @param uuid the data type's UUID
	* @param groupId the primary key of the group
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType fetchDataTypeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.fetchDataTypeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the data type with the primary key.
	*
	* @param typeId the primary key of the data type
	* @return the data type
	* @throws PortalException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType getDataType(long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataType(typeId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the data type with the matching UUID and company.
	*
	* @param uuid the data type's UUID
	* @param companyId the primary key of the company
	* @return the matching data type
	* @throws PortalException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType getDataTypeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataTypeByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the data type matching the UUID and group.
	*
	* @param uuid the data type's UUID
	* @param groupId the primary key of the group
	* @return the matching data type
	* @throws PortalException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType getDataTypeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataTypeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the data types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of data types
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataType> getDataTypes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataTypes(start, end);
	}

	/**
	* Returns the number of data types.
	*
	* @return the number of data types
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDataTypesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataTypesCount();
	}

	/**
	* Updates the data type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataType the data type
	* @return the data type that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType updateDataType(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.updateDataType(dataType);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dataTypeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dataTypeLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dataTypeLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public boolean addFavoriteToDataTypeObject(java.lang.String dtName,
		java.lang.String dtVersion, boolean isFavorite,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.addFavoriteToDataTypeObject(dtName,
			dtVersion, isFavorite, sc);
	}

	@Override
	public boolean addOwnerIdToDataTypeObject(java.lang.String dtName,
		java.lang.String dtVersion, java.lang.Long ownerId,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.addOwnerIdToDataTypeObject(dtName,
			dtVersion, ownerId, sc);
	}

	/**
	* Create a new data type object
	*
	* @param dtName
	* @param dtVersion
	* @param dtDescription
	* @param dtSampleFilePath
	* @param dtStatus
	* @param dtStructure
	* @param ownerId
	* @param sc Service Context
	* @return
	* @throws SystemException
	*/
	@Override
	public boolean createDataTypeObject(java.lang.String dtName,
		java.lang.String dtVersion,
		java.util.Map<java.util.Locale, java.lang.String> dtDescription,
		java.lang.String dtSampleFilePath, java.lang.String dtStatus,
		java.lang.String dtStructure, java.lang.Long ownerId,
		boolean isFavorite, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.createDataTypeObject(dtName, dtVersion,
			dtDescription, dtSampleFilePath, dtStatus, dtStructure, ownerId,
			isFavorite, sc);
	}

	/**
	* Find a data type object matching name+version
	*
	* @param dtName data type name
	* @param dtVersion data type version
	* @return data type object
	* @throws SystemException
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType findDataTypeObject(
		java.lang.String dtName, java.lang.String dtVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.findDataTypeObject(dtName, dtVersion);
	}

	@Override
	public void addDataTypeAnalyzer(java.lang.String dtName,
		java.lang.String dtVersion, java.lang.Long dataTypeAnalyzerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataTypeLocalService.addDataTypeAnalyzer(dtName, dtVersion,
			dataTypeAnalyzerId);
	}

	/**
	* Find a data type object matching name+version
	*
	* @param dataTypeId data type Id
	* @return data type object
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public com.kisti.osp.icecap.model.DataType findDataTypeObject(
		java.lang.Long dataTypeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.findDataTypeObject(dataTypeId);
	}

	/**
	* Find a data type object associated with dataTypeId
	*
	* @param dataTypeId data type Id
	* @return data type object
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> retrieveDataTypeObject(
		java.lang.Long dataTypeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.retrieveDataTypeObject(dataTypeId);
	}

	/**
	* Find a data type object associated with favorite value
	*
	* @param dtName data type name
	* @param dtVersion data type version
	* @return data type object
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataType> findDataTypeObjectByFavorite(
		long groupId, boolean isFavorite)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.findDataTypeObjectByFavorite(groupId,
			isFavorite);
	}

	/**
	* @param groupId
	* @param ownerId
	* @return
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataType> findDataTypeObjectByOwner(
		long groupId, long ownerId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.findDataTypeObjectByOwner(groupId, ownerId);
	}

	/**
	* Perform data type validation - GPLUS
	*
	* @param typeId
	* @param version
	* @return true or false
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public boolean checkDataTypeObjectValiation(java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.checkDataTypeObjectValiation(name, version);
	}

	/**
	* @param typeId
	* @param description
	* @param name
	* @param version
	* @param ownerId
	* @param isFavorite
	* @param samplePath
	* @param status
	* @param sc
	* @return
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> modifyDataTypeObject(
		long typeId, java.lang.String name, java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> description,
		java.lang.String samplePath, java.lang.Integer status,
		java.lang.String structure, java.lang.Long ownerId,
		java.lang.Boolean isFavorite,
		com.liferay.portal.service.ServiceContext sc)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.modifyDataTypeObject(typeId, name,
			version, description, samplePath, status, structure, ownerId,
			isFavorite, sc);
	}

	/**
	* Modify a DataType object to add or remove DataTypeEditor and/or DataTypeAnalyzer
	*
	* @param dtName DataType name
	* @param dtVersion DataType version
	* @param editorList a list of DataTypeEditor objects
	* @param analyzerList a list of DataTypeAnalyzer objects
	* @param sc ServiceContext
	* @return typeId
	* @throws SystemException
	*/
	@Override
	public java.lang.Long modifyDataTypeObjectForEditorAnalyzer(
		java.lang.String dtName, java.lang.String dtVersion,
		java.util.Map<java.util.Locale, java.lang.String> description,
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> editorList,
		java.util.List<java.util.Map<java.lang.String, java.lang.Object>> analyzerList,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.modifyDataTypeObjectForEditorAnalyzer(dtName,
			dtVersion, description, editorList, analyzerList, sc);
	}

	/**
	* Retrieve matching a list of DataType objects - GPLUS
	*
	* @param name data type name
	* @return a list of Map<String, Object>
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataTypeObjects(
		java.lang.String name, java.lang.Object isFavorite, long ownerId,
		int begin, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.retrieveDataTypeObjects(name, isFavorite,
			ownerId, begin, end);
	}

	/**
	* Retrieve matching a count of DataType objects - GPLUS
	*
	* @param name data type name
	* @return Integer
	* @throws SystemException
	*/
	@Override
	public int retrieveDataTypeCount(java.lang.String name,
		java.lang.Object isFavorite, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.retrieveDataTypeCount(name, isFavorite,
			ownerId);
	}

	/**
	* Remove a DataType object matching typeId
	*
	* @param typeId
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public void removeDataTypeObject(long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dataTypeLocalService.removeDataTypeObject(typeId);
	}

	/**
	* Retrieve view counts associated with this DataType object
	*
	* @param typeId a given typeId
	* @return
	* @throws SystemException
	* @throws NoSuchDataTypeException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> retrieveViewCount(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.retrieveViewCount(typeId);
	}

	/**
	* Copy DataTypeEntry  - GPLUS
	*
	* @param typeId
	* @param userId
	* @param sc
	* @return new DataTypeEntry Object
	* @throws SystemException
	* @throws PortalException
	* @throws IOException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> copyDataType(
		long typeId, long userId, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.copyDataType(typeId, userId, sc);
	}

	/**
	* SampleFile Upload & Update DataType - GPLUS
	*
	* @param typeId
	* @param sc
	* @param upload
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> addSampleFilePath(
		long typeId, com.liferay.portal.service.ServiceContext sc,
		com.liferay.portal.kernel.upload.UploadPortletRequest upload)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.addSampleFilePath(typeId, sc, upload);
	}

	@Override
	public java.lang.String getDataTypeStructure(
		java.lang.String dataTypeName, java.lang.String dataTypeVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeLocalService.getDataTypeStructure(dataTypeName,
			dataTypeVersion);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DataTypeLocalService getWrappedDataTypeLocalService() {
		return _dataTypeLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDataTypeLocalService(
		DataTypeLocalService dataTypeLocalService) {
		_dataTypeLocalService = dataTypeLocalService;
	}

	@Override
	public DataTypeLocalService getWrappedService() {
		return _dataTypeLocalService;
	}

	@Override
	public void setWrappedService(DataTypeLocalService dataTypeLocalService) {
		_dataTypeLocalService = dataTypeLocalService;
	}

	private DataTypeLocalService _dataTypeLocalService;
}