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
 * Provides a wrapper for {@link DataEntryLocalService}.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryLocalService
 * @generated
 */
public class DataEntryLocalServiceWrapper implements DataEntryLocalService,
	ServiceWrapper<DataEntryLocalService> {
	public DataEntryLocalServiceWrapper(
		DataEntryLocalService dataEntryLocalService) {
		_dataEntryLocalService = dataEntryLocalService;
	}

	/**
	* Adds the data entry to the database. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry addDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.addDataEntry(dataEntry);
	}

	/**
	* Creates a new data entry with the primary key. Does not add the data entry to the database.
	*
	* @param entryId the primary key for the new data entry
	* @return the new data entry
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry createDataEntry(long entryId) {
		return _dataEntryLocalService.createDataEntry(entryId);
	}

	/**
	* Deletes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry that was removed
	* @throws PortalException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry deleteDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.deleteDataEntry(entryId);
	}

	/**
	* Deletes the data entry from the database. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry deleteDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.deleteDataEntry(dataEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dataEntryLocalService.dynamicQuery();
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
		return _dataEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dataEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dataEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dataEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dataEntryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntry fetchDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.fetchDataEntry(entryId);
	}

	/**
	* Returns the data entry with the primary key.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry
	* @throws PortalException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry getDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.getDataEntry(entryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the data entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @return the range of data entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> getDataEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.getDataEntries(start, end);
	}

	/**
	* Returns the number of data entries.
	*
	* @return the number of data entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDataEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.getDataEntriesCount();
	}

	/**
	* Updates the data entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry updateDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.updateDataEntry(dataEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dataEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dataEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dataEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.kisti.osp.icecap.model.DataEntry createDataEntryObject(
		java.lang.Long dcId, java.lang.Long simulationSubjectId,
		java.lang.String inputData, java.lang.String path,
		java.util.Map<java.util.Locale, java.lang.String> commentsMap,
		java.lang.String productionTime, java.lang.Long dlFolderId,
		java.lang.String resultFileId, java.lang.String resultFileNm,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return _dataEntryLocalService.createDataEntryObject(dcId,
			simulationSubjectId, inputData, path, commentsMap, productionTime,
			dlFolderId, resultFileId, resultFileNm, sc);
	}

	/**
	* GPLUS
	* upload dl file mehod
	*
	* @param deId
	* @param dcId
	* @param simulationSubjectId
	* @param inputData
	* @param path
	* @param commentsMap
	* @param productionTime
	* @param dlFolderId
	* @param sc
	* @return
	* @throws SystemException
	* @throws IOException
	* @throws SQLException
	* @throws PortalException
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry updateDataEntryObject(
		java.lang.Long deId, java.lang.Long dcId,
		java.util.Map<java.util.Locale, java.lang.String> commentsMap,
		java.lang.Long dlFolderId, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return _dataEntryLocalService.updateDataEntryObject(deId, dcId,
			commentsMap, dlFolderId, sc);
	}

	/**
	* GPLUS
	* Check Already Exist DataEntry with SimulationSubjectId, And InputData
	* if exist this simulationSubjectId and inputData, return true
	*
	* @param simulationSubjectId
	* @param inputData
	* @return
	* @throws SystemException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> checkExistSubjectIdAndJobData(
		java.lang.Long simulationSubjectId, java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.checkExistSubjectIdAndJobData(simulationSubjectId,
			inputData);
	}

	/**
	* Retrieve a list of DataEntry objects matching a given subjectId
	*
	* @param subjectId
	* @return a list of data entries
	* @throws SystemException
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findDataEntryPerApp(
		java.lang.Long subjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.findDataEntryPerApp(subjectId);
	}

	/**
	* Find a DataEntry object matching given info
	*
	* @param dtTypeName DataType name
	* @param dtTypeVersion DataType version
	* @param dcCollectionName DataCollection name
	* @param dcCollectionVersion DataCollection version
	* @param subjectId subject id: science app or workflow instance
	* @param inputData input data
	* @return a DataEntry object
	* @throws SystemException
	*/
	@Override
	public com.kisti.osp.icecap.model.DataEntry findDataEntryObject(
		java.lang.String dtTypeName, java.lang.String dtTypeVersion,
		java.lang.String dcCollectionName,
		java.lang.String dcCollectionVersion, java.lang.Long subjectId,
		java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.findDataEntryObject(dtTypeName,
			dtTypeVersion, dcCollectionName, dcCollectionVersion, subjectId,
			inputData);
	}

	/**
	* Retrieve DataEntry objects whose collectionId matches a given collectionId
	*
	* @param collectionId DataCollectionID
	* @return a list of map objects
	* @throws SystemException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataEntryOnlyUsingMapByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveDataEntryOnlyUsingMapByCollectionId(collectionId);
	}

	/**
	* Retrieve DataEntry objects whose collectionId matches a given collectionId
	*
	* @param collectionId DataCollectionID
	* @return a list of map objects
	* @throws SystemException
	*/
	@Override
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> retrieveDataEntryOnlyByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveDataEntryOnlyByCollectionId(collectionId);
	}

	/**
	* Add PROV structure assoacited with this data entry
	*
	* @param entryId data entry Id
	* @param strPROV PROV spec
	* @throws SystemException
	*/
	@Override
	public void addPROVStructure(long entryId, java.lang.String simulationUuid,
		long groupId, long userId, long scienceAppId,
		java.lang.String scienceAppName, long jobSeqNo,
		java.lang.String jobUuid, long jobStatusVal,
		java.lang.String startDateStr, java.lang.String endDateStr,
		long jobCompletion, java.lang.String jobExecPath,
		long jobUniversityField, long jobInputDeckYnVal,
		long jobInputDeckNameVal, java.lang.String jobDataStr)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataEntryLocalService.addPROVStructure(entryId, simulationUuid,
			groupId, userId, scienceAppId, scienceAppName, jobSeqNo, jobUuid,
			jobStatusVal, startDateStr, endDateStr, jobCompletion, jobExecPath,
			jobUniversityField, jobInputDeckYnVal, jobInputDeckNameVal,
			jobDataStr);
	}

	/**
	* Search for matching DataEntry objects on a given target
	*
	* @param entryId data entry Id
	* @param strPROV PROV spec
	* @throws SystemException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> searchPROVStructure(
		java.lang.String searchTerm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.searchPROVStructure(searchTerm);
	}

	/**
	* Retrieve DataEntry objects by CollectionId
	*
	* @param collectionId
	* @return List<Map<String, Object>>
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataEntryByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveDataEntryByCollectionId(collectionId);
	}

	/**
	* Retrieve DataEntry objects by EntryId
	*
	* @param collectionId
	* @return List<Map<String, Object>>
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> retrieveDataEntryByEntryId(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveDataEntryByEntryId(entryId);
	}

	/**
	* Increment download count by 1
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	*/
	@Override
	public void incrementDownloadCnt(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataEntryLocalService.incrementDownloadCnt(entryId);
	}

	/**
	* Increment view count by 1
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	*/
	@Override
	public void incrementViewCnt(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataEntryLocalService.incrementViewCnt(entryId);
	}

	/**
	* Remove DataEntry
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public void removeDataEntryByEntryId(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dataEntryLocalService.removeDataEntryByEntryId(entryId);
	}

	/**
	* - GPLUS
	* Retrieve list of dataEntry by users (+by categories in site)
	* admin can retrieve dataEntry of all Users
	*
	* @param companyGroupId
	* @param groupId
	* @param userId
	* @param start
	* @param end
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataEntry(
		long companyGroupId, long groupId, java.lang.String searchText,
		long userId, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveListDataEntry(companyGroupId,
			groupId, searchText, userId, start, end, locale);
	}

	/**
	* Copy result files of a completed job (simulation) from portal to IceCap server
	*
	* @param entryId
	* @return server path
	*/
	@Override
	public java.lang.String copyFilesFromPortal(
		java.lang.String collectionName, java.lang.String collectionVersion,
		long entryId,
		java.util.Map<java.lang.String, java.lang.String> fileLocMap) {
		return _dataEntryLocalService.copyFilesFromPortal(collectionName,
			collectionVersion, entryId, fileLocMap);
	}

	/**
	* - GPLUS
	* Retrieve count of dataEntry by users (+by categories in site)
	* admin can retrieve dataEntry count of all Users
	*
	* @param companyGroupId
	* @param groupId
	* @param userId
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public int retrieveCountDataCollection(long companyGroupId, long groupId,
		java.lang.String searchText, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataEntryLocalService.retrieveCountDataCollection(companyGroupId,
			groupId, searchText, userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DataEntryLocalService getWrappedDataEntryLocalService() {
		return _dataEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDataEntryLocalService(
		DataEntryLocalService dataEntryLocalService) {
		_dataEntryLocalService = dataEntryLocalService;
	}

	@Override
	public DataEntryLocalService getWrappedService() {
		return _dataEntryLocalService;
	}

	@Override
	public void setWrappedService(DataEntryLocalService dataEntryLocalService) {
		_dataEntryLocalService = dataEntryLocalService;
	}

	private DataEntryLocalService _dataEntryLocalService;
}