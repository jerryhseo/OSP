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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for DataEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryLocalServiceUtil
 * @see com.kisti.osp.icecap.service.base.DataEntryLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.impl.DataEntryLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DataEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataEntryLocalServiceUtil} to access the data entry local service. Add custom service methods to {@link com.kisti.osp.icecap.service.impl.DataEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the data entry to the database. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.icecap.model.DataEntry addDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new data entry with the primary key. Does not add the data entry to the database.
	*
	* @param entryId the primary key for the new data entry
	* @return the new data entry
	*/
	public com.kisti.osp.icecap.model.DataEntry createDataEntry(long entryId);

	/**
	* Deletes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry that was removed
	* @throws PortalException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.icecap.model.DataEntry deleteDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the data entry from the database. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.icecap.model.DataEntry deleteDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataEntry fetchDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry with the primary key.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry
	* @throws PortalException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataEntry getDataEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> getDataEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entries.
	*
	* @return the number of data entries
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDataEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the data entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataEntry the data entry
	* @return the data entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.icecap.model.DataEntry updateDataEntry(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.kisti.osp.icecap.model.DataEntry createDataEntryObject(
		java.lang.Long dcId, java.lang.Long simulationSubjectId,
		java.lang.String inputData, java.lang.String path,
		java.util.Map<java.util.Locale, java.lang.String> commentsMap,
		java.lang.String productionTime, java.lang.Long dlFolderId,
		java.lang.String resultFileId, java.lang.String resultFileNm,
		com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException;

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
	public com.kisti.osp.icecap.model.DataEntry updateDataEntryObject(
		java.lang.Long deId, java.lang.Long dcId,
		java.util.Map<java.util.Locale, java.lang.String> commentsMap,
		java.lang.Long dlFolderId, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException;

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
	public java.util.Map<java.lang.String, java.lang.Object> checkExistSubjectIdAndJobData(
		java.lang.Long simulationSubjectId, java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve a list of DataEntry objects matching a given subjectId
	*
	* @param subjectId
	* @return a list of data entries
	* @throws SystemException
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findDataEntryPerApp(
		java.lang.Long subjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.kisti.osp.icecap.model.DataEntry findDataEntryObject(
		java.lang.String dtTypeName, java.lang.String dtTypeVersion,
		java.lang.String dcCollectionName,
		java.lang.String dcCollectionVersion, java.lang.Long subjectId,
		java.lang.String inputData)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve DataEntry objects whose collectionId matches a given collectionId
	*
	* @param collectionId DataCollectionID
	* @return a list of map objects
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataEntryOnlyUsingMapByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve DataEntry objects whose collectionId matches a given collectionId
	*
	* @param collectionId DataCollectionID
	* @return a list of map objects
	* @throws SystemException
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> retrieveDataEntryOnlyByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Add PROV structure assoacited with this data entry
	*
	* @param entryId data entry Id
	* @param strPROV PROV spec
	* @throws SystemException
	*/
	public void addPROVStructure(long entryId, java.lang.String simulationUuid,
		long groupId, long userId, long scienceAppId,
		java.lang.String scienceAppName, long jobSeqNo,
		java.lang.String jobUuid, long jobStatusVal,
		java.lang.String startDateStr, java.lang.String endDateStr,
		long jobCompletion, java.lang.String jobExecPath,
		long jobUniversityField, long jobInputDeckYnVal,
		long jobInputDeckNameVal, java.lang.String jobDataStr)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Search for matching DataEntry objects on a given target
	*
	* @param entryId data entry Id
	* @param strPROV PROV spec
	* @throws SystemException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> searchPROVStructure(
		java.lang.String searchTerm)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve DataEntry objects by CollectionId
	*
	* @param collectionId
	* @return List<Map<String, Object>>
	* @throws SystemException
	* @throws PortalException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataEntryByCollectionId(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve DataEntry objects by EntryId
	*
	* @param collectionId
	* @return List<Map<String, Object>>
	* @throws SystemException
	* @throws PortalException
	*/
	public java.util.Map<java.lang.String, java.lang.Object> retrieveDataEntryByEntryId(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Increment download count by 1
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	*/
	public void incrementDownloadCnt(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Increment view count by 1
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	*/
	public void incrementViewCnt(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Remove DataEntry
	*
	* @param entryId a given DataEntry
	* @throws SystemException
	* @throws PortalException
	*/
	public void removeDataEntryByEntryId(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataEntry(
		long companyGroupId, long groupId, java.lang.String searchText,
		long userId, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Copy result files of a completed job (simulation) from portal to IceCap server
	*
	* @param entryId
	* @return server path
	*/
	public java.lang.String copyFilesFromPortal(
		java.lang.String collectionName, java.lang.String collectionVersion,
		long entryId,
		java.util.Map<java.lang.String, java.lang.String> fileLocMap);

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
	public int retrieveCountDataCollection(long companyGroupId, long groupId,
		java.lang.String searchText, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}