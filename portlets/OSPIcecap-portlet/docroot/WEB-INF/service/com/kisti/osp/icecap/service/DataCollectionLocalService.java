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
 * Provides the local service interface for DataCollection. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLocalServiceUtil
 * @see com.kisti.osp.icecap.service.base.DataCollectionLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.impl.DataCollectionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DataCollectionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataCollectionLocalServiceUtil} to access the data collection local service. Add custom service methods to {@link com.kisti.osp.icecap.service.impl.DataCollectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the data collection to the database. Also notifies the appropriate model listeners.
	*
	* @param dataCollection the data collection
	* @return the data collection that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.icecap.model.DataCollection addDataCollection(
		com.kisti.osp.icecap.model.DataCollection dataCollection)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new data collection with the primary key. Does not add the data collection to the database.
	*
	* @param collectionId the primary key for the new data collection
	* @return the new data collection
	*/
	public com.kisti.osp.icecap.model.DataCollection createDataCollection(
		long collectionId);

	/**
	* Deletes the data collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the data collection
	* @return the data collection that was removed
	* @throws PortalException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.icecap.model.DataCollection deleteDataCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the data collection from the database. Also notifies the appropriate model listeners.
	*
	* @param dataCollection the data collection
	* @return the data collection that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.icecap.model.DataCollection deleteDataCollection(
		com.kisti.osp.icecap.model.DataCollection dataCollection)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public com.kisti.osp.icecap.model.DataCollection fetchDataCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection with the matching UUID and company.
	*
	* @param uuid the data collection's UUID
	* @param companyId the primary key of the company
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataCollection fetchDataCollectionByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection matching the UUID and group.
	*
	* @param uuid the data collection's UUID
	* @param groupId the primary key of the group
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataCollection fetchDataCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection with the primary key.
	*
	* @param collectionId the primary key of the data collection
	* @return the data collection
	* @throws PortalException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataCollection getDataCollection(
		long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection with the matching UUID and company.
	*
	* @param uuid the data collection's UUID
	* @param companyId the primary key of the company
	* @return the matching data collection
	* @throws PortalException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataCollection getDataCollectionByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection matching the UUID and group.
	*
	* @param uuid the data collection's UUID
	* @param groupId the primary key of the group
	* @return the matching data collection
	* @throws PortalException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.icecap.model.DataCollection getDataCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of data collections
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.kisti.osp.icecap.model.DataCollection> getDataCollections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data collections.
	*
	* @return the number of data collections
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDataCollectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the data collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataCollection the data collection
	* @return the data collection that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.icecap.model.DataCollection updateDataCollection(
		com.kisti.osp.icecap.model.DataCollection dataCollection)
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

	public boolean createDataCollectionObject(java.lang.Long dtId,
		java.lang.String dcName, java.lang.String dcVersion,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String dcAccessMethod, java.lang.String dcStatus,
		java.lang.String dcTargetLanguage, long[] categoryIds,
		long companyGroupId, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Find an id of a DataType object matching name+version
	*
	* @param dtId
	data type id
	* @param dcName
	data collection name
	* @param dcVersion
	data collection version
	* @return data collection object
	* @throws SystemException
	*/
	public java.util.Map<java.lang.String, java.lang.Object> findDataCollectionObject(
		java.lang.Long dtId, java.lang.String dcName, java.lang.String dcVersion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Validate a DataCollection object matching a given name and a given
	* version
	*
	* @param name
	a given DataCollection name
	* @param version
	a given DataCollection version
	* @return true or false
	* @throws SystemException
	*/
	public boolean checkDataCollectionValidity(java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve a set of DataCollection objects whose title partially contains
	* or fully matching searchText
	*
	* @param searchText
	search text
	* @param start
	start
	* @param end
	end
	* @return a set of matching DataCollection objects
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataCollectionObjects(
		java.lang.String searchText, java.util.Locale locale, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve a set of DataCollection objects whose title partially contains
	* or fully matching searchText
	*
	* @param searchText
	search text
	* @param start
	start
	* @param end
	end
	* @return a set of matching DataCollection objects
	* @throws SystemException
	*/
	public int countDataCollectionObjects(java.lang.String searchText,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve a set of DataCollection objects by a given array of
	* CollectionIds
	*
	* @param collectionIdArray
	an array of CollectionId
	* @param start
	start
	* @param end
	end
	* @return a set of matching DataCollection objects
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataCollectionObjectsByCollectId(
		java.lang.Object[] collectionIdArray, int start, int end,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Retrieve a set of DataCollection objects by a given array of
	* CollectionIds
	*
	* @param collectionIdArray
	an array of CollectionId
	* @return a set of matching DataCollection objects
	* @throws SystemException
	* @throws PortalException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataCollectionObjectsByCollectId(
		java.lang.Object[] collectionIdArray, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Count a set of DataCollection objects by a given array of CollectionIds
	*
	* @param collectionIdArray
	an array of CollectionId
	* @return a set of matching DataCollection objects
	* @throws SystemException
	* @throws PortalException
	*/
	public int countDataCollectionObjectsByCollectId(
		java.lang.Object[] collectionIdArray, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Remove DataCollection objects whose collection Id matches a given collectionId
	*/
	public void removeDataCollectionByCollectionId(long collectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @param dcName
	* @return
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataCollectionHistory(
		java.lang.String dcName, java.lang.String dcVersion,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in unified search of specific user- GPLUS Search by Text,
	* Category
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	* @param start
	* @param end
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataCollection(
		long companyGroupId, long groupId, long userId,
		java.lang.String searchText, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in unified search - GPLUS Search by Text, Category
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	* @param start
	* @param end
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataCollection(
		long companyGroupId, long groupId, java.lang.String searchText,
		int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in Unified Search - GPLUS Search by Text, Category
	*
	* @param categoryIds
	* @param searchText
	* @param start
	* @param end
	* @param locale
	* @return
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListDataCollection(
		long[] categoryIds, java.lang.String searchText, int start, int end,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in Unified Search of specific user - GPLUS Search by Text,
	* Category
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public int retrieveCountDataCollection(long companyGroupId, long groupId,
		long userId, java.lang.String searchText, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in Unified Search - GPLUS Search by Text, Category
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	* @param locale
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public int retrieveCountDataCollection(long companyGroupId, long groupId,
		java.lang.String searchText, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* using method in Unified Search - GPLUS Search by Text, Category
	*
	* @param categoryIds
	* @param searchText
	* @param locale
	* @return
	* @throws SystemException
	*/
	public int retrieveCountDataCollection(long[] categoryIds,
		java.lang.String searchText, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* related asset count method
	*
	* @param classPKList
	* @param locale
	* @return
	* @throws SystemException
	*/
	public int retrieveCountRelatedAssetDataCollection(
		java.util.List<java.lang.Long> classPKList, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataCollectionObjects(
		java.lang.String searchText, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countDataCollectionObjects(java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.SystemException;
}