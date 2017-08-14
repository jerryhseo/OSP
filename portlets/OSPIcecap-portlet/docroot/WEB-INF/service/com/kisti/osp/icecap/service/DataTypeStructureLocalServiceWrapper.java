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
 * Provides a wrapper for {@link DataTypeStructureLocalService}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructureLocalService
 * @generated
 */
public class DataTypeStructureLocalServiceWrapper
	implements DataTypeStructureLocalService,
		ServiceWrapper<DataTypeStructureLocalService> {
	public DataTypeStructureLocalServiceWrapper(
		DataTypeStructureLocalService dataTypeStructureLocalService) {
		_dataTypeStructureLocalService = dataTypeStructureLocalService;
	}

	/**
	* Adds the data type structure to the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeStructure the data type structure
	* @return the data type structure that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure addDataTypeStructure(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.addDataTypeStructure(dataTypeStructure);
	}

	/**
	* Creates a new data type structure with the primary key. Does not add the data type structure to the database.
	*
	* @param typeId the primary key for the new data type structure
	* @return the new data type structure
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure createDataTypeStructure(
		long typeId) {
		return _dataTypeStructureLocalService.createDataTypeStructure(typeId);
	}

	/**
	* Deletes the data type structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure that was removed
	* @throws PortalException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure deleteDataTypeStructure(
		long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.deleteDataTypeStructure(typeId);
	}

	/**
	* Deletes the data type structure from the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeStructure the data type structure
	* @return the data type structure that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure deleteDataTypeStructure(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.deleteDataTypeStructure(dataTypeStructure);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dataTypeStructureLocalService.dynamicQuery();
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
		return _dataTypeStructureLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dataTypeStructureLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dataTypeStructureLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _dataTypeStructureLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dataTypeStructureLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure fetchDataTypeStructure(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.fetchDataTypeStructure(typeId);
	}

	/**
	* Returns the data type structure with the primary key.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure
	* @throws PortalException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure getDataTypeStructure(
		long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.getDataTypeStructure(typeId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the data type structures.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type structures
	* @param end the upper bound of the range of data type structures (not inclusive)
	* @return the range of data type structures
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> getDataTypeStructures(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.getDataTypeStructures(start, end);
	}

	/**
	* Returns the number of data type structures.
	*
	* @return the number of data type structures
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDataTypeStructuresCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.getDataTypeStructuresCount();
	}

	/**
	* Updates the data type structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataTypeStructure the data type structure
	* @return the data type structure that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataTypeStructure updateDataTypeStructure(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.updateDataTypeStructure(dataTypeStructure);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dataTypeStructureLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dataTypeStructureLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dataTypeStructureLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Create a data type structure object
	*/
	@Override
	public boolean createDataTypeStructureObject(java.lang.Long dataTypeId,
		java.lang.String dataTypeStructureJSON)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.createDataTypeStructureObject(dataTypeId,
			dataTypeStructureJSON);
	}

	/**
	* Retrieve a DataTypeStructure object
	*
	* @param typeId DataType typeId
	* @return Map<String, Object>
	* @throws SystemException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> retrieveDataTypeStructurePK(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataTypeStructureLocalService.retrieveDataTypeStructurePK(typeId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DataTypeStructureLocalService getWrappedDataTypeStructureLocalService() {
		return _dataTypeStructureLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDataTypeStructureLocalService(
		DataTypeStructureLocalService dataTypeStructureLocalService) {
		_dataTypeStructureLocalService = dataTypeStructureLocalService;
	}

	@Override
	public DataTypeStructureLocalService getWrappedService() {
		return _dataTypeStructureLocalService;
	}

	@Override
	public void setWrappedService(
		DataTypeStructureLocalService dataTypeStructureLocalService) {
		_dataTypeStructureLocalService = dataTypeStructureLocalService;
	}

	private DataTypeStructureLocalService _dataTypeStructureLocalService;
}