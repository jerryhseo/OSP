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

package com.kisti.osp.icecap.service.persistence;

import com.kisti.osp.icecap.model.DataCollection;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the data collection service. This utility wraps {@link DataCollectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionPersistence
 * @see DataCollectionPersistenceImpl
 * @generated
 */
public class DataCollectionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(DataCollection dataCollection) {
		getPersistence().clearCache(dataCollection);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DataCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataCollection update(DataCollection dataCollection)
		throws SystemException {
		return getPersistence().update(dataCollection);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataCollection update(DataCollection dataCollection,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dataCollection, serviceContext);
	}

	/**
	* Returns all the data collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the data collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where uuid = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByUuid_PrevAndNext(
		long collectionId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(collectionId, uuid, orderByComparator);
	}

	/**
	* Removes all the data collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of data collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the data collection where uuid = &#63; and groupId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the data collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the data collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the data collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the data collection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of data collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the data collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the data collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByUuid_C_PrevAndNext(
		long collectionId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(collectionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the data collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of data collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the data collections where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTargetLanguage(
		java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTargetLanguage(targetLanguage);
	}

	/**
	* Returns a range of all the data collections where targetLanguage = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param targetLanguage the target language
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTargetLanguage(
		java.lang.String targetLanguage, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTargetLanguage(targetLanguage, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where targetLanguage = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param targetLanguage the target language
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTargetLanguage(
		java.lang.String targetLanguage, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTargetLanguage(targetLanguage, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTargetLanguage_First(
		java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTargetLanguage_First(targetLanguage, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTargetLanguage_First(
		java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTargetLanguage_First(targetLanguage,
			orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTargetLanguage_Last(
		java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTargetLanguage_Last(targetLanguage, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTargetLanguage_Last(
		java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTargetLanguage_Last(targetLanguage, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where targetLanguage = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByTargetLanguage_PrevAndNext(
		long collectionId, java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTargetLanguage_PrevAndNext(collectionId,
			targetLanguage, orderByComparator);
	}

	/**
	* Removes all the data collections where targetLanguage = &#63; from the database.
	*
	* @param targetLanguage the target language
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTargetLanguage(java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTargetLanguage(targetLanguage);
	}

	/**
	* Returns the number of data collections where targetLanguage = &#63;.
	*
	* @param targetLanguage the target language
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTargetLanguage(java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTargetLanguage(targetLanguage);
	}

	/**
	* Returns all the data collections where status = &#63;.
	*
	* @param status the status
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the data collections where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where status = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByStatus_PrevAndNext(
		long collectionId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus_PrevAndNext(collectionId, status,
			orderByComparator);
	}

	/**
	* Removes all the data collections where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of data collections where status = &#63;.
	*
	* @param status the status
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the data collections where name = &#63;.
	*
	* @param name the name
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the data collections where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where name = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByName_PrevAndNext(
		long collectionId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(collectionId, name, orderByComparator);
	}

	/**
	* Removes all the data collections where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of data collections where name = &#63;.
	*
	* @param name the name
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns all the data collections where title LIKE &#63;.
	*
	* @param title the title
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title);
	}

	/**
	* Returns a range of all the data collections where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle(
		java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle(
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where title LIKE &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByTitle_PrevAndNext(
		long collectionId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTitle_PrevAndNext(collectionId, title,
			orderByComparator);
	}

	/**
	* Removes all the data collections where title LIKE &#63; from the database.
	*
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTitle(title);
	}

	/**
	* Returns the number of data collections where title LIKE &#63;.
	*
	* @param title the title
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTitle(title);
	}

	/**
	* Returns all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle_TL(
		java.lang.String title, java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle_TL(title, targetLanguage);
	}

	/**
	* Returns a range of all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle_TL(
		java.lang.String title, java.lang.String targetLanguage, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle_TL(title, targetLanguage, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where title LIKE &#63; and targetLanguage = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTitle_TL(
		java.lang.String title, java.lang.String targetLanguage, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTitle_TL(title, targetLanguage, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTitle_TL_First(
		java.lang.String title, java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTitle_TL_First(title, targetLanguage,
			orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTitle_TL_First(
		java.lang.String title, java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTitle_TL_First(title, targetLanguage,
			orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTitle_TL_Last(
		java.lang.String title, java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTitle_TL_Last(title, targetLanguage, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTitle_TL_Last(
		java.lang.String title, java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTitle_TL_Last(title, targetLanguage,
			orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param title the title
	* @param targetLanguage the target language
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByTitle_TL_PrevAndNext(
		long collectionId, java.lang.String title,
		java.lang.String targetLanguage,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTitle_TL_PrevAndNext(collectionId, title,
			targetLanguage, orderByComparator);
	}

	/**
	* Removes all the data collections where title LIKE &#63; and targetLanguage = &#63; from the database.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTitle_TL(java.lang.String title,
		java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTitle_TL(title, targetLanguage);
	}

	/**
	* Returns the number of data collections where title LIKE &#63; and targetLanguage = &#63;.
	*
	* @param title the title
	* @param targetLanguage the target language
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTitle_TL(java.lang.String title,
		java.lang.String targetLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTitle_TL(title, targetLanguage);
	}

	/**
	* Returns all the data collections where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByNameVersion(
		java.lang.String name, java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByNameVersion(name, version);
	}

	/**
	* Returns a range of all the data collections where name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByNameVersion(
		java.lang.String name, java.lang.String version, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByNameVersion(name, version, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where name = &#63; and version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param version the version
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByNameVersion(
		java.lang.String name, java.lang.String version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNameVersion(name, version, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByNameVersion_First(
		java.lang.String name, java.lang.String version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNameVersion_First(name, version, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByNameVersion_First(
		java.lang.String name, java.lang.String version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNameVersion_First(name, version, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByNameVersion_Last(
		java.lang.String name, java.lang.String version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNameVersion_Last(name, version, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByNameVersion_Last(
		java.lang.String name, java.lang.String version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNameVersion_Last(name, version, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where name = &#63; and version = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param name the name
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByNameVersion_PrevAndNext(
		long collectionId, java.lang.String name, java.lang.String version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNameVersion_PrevAndNext(collectionId, name, version,
			orderByComparator);
	}

	/**
	* Removes all the data collections where name = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param version the version
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByNameVersion(java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByNameVersion(name, version);
	}

	/**
	* Returns the number of data collections where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByNameVersion(java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByNameVersion(name, version);
	}

	/**
	* Returns all the data collections where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTypeID(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID(typeId);
	}

	/**
	* Returns a range of all the data collections where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTypeID(
		long typeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID(typeId, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByTypeID(
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTypeID(typeId, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID_First(typeId, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID_First(typeId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID_Last(typeId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID_Last(typeId, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where typeId = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByTypeID_PrevAndNext(
		long collectionId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTypeID_PrevAndNext(collectionId, typeId,
			orderByComparator);
	}

	/**
	* Removes all the data collections where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTypeID(typeId);
	}

	/**
	* Returns the number of data collections where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTypeID(typeId);
	}

	/**
	* Returns all the data collections where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Name(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_Name(groupId, name);
	}

	/**
	* Returns a range of all the data collections where groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Name(
		long groupId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_Name(groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Name(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Name(groupId, name, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByG_Name_First(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Name_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByG_Name_First(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_Name_First(groupId, name, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByG_Name_Last(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Name_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByG_Name_Last(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_Name_Last(groupId, name, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByG_Name_PrevAndNext(
		long collectionId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Name_PrevAndNext(collectionId, groupId, name,
			orderByComparator);
	}

	/**
	* Removes all the data collections where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_Name(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_Name(groupId, name);
	}

	/**
	* Returns the number of data collections where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_Name(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_Name(groupId, name);
	}

	/**
	* Returns all the data collections where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Status(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_Status(groupId, status);
	}

	/**
	* Returns a range of all the data collections where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Status(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_Status(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByG_Status(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Status(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByG_Status_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Status_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByG_Status_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_Status_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByG_Status_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Status_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByG_Status_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_Status_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByG_Status_PrevAndNext(
		long collectionId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_Status_PrevAndNext(collectionId, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the data collections where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_Status(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_Status(groupId, status);
	}

	/**
	* Returns the number of data collections where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_Status(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_Status(groupId, status);
	}

	/**
	* Returns all the data collections where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the data collections where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @return the range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the data collections where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first data collection in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last data collection in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the data collections before and after the current data collection in the ordered set where userId = &#63;.
	*
	* @param collectionId the primary key of the current data collection
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection[] findByUserId_PrevAndNext(
		long collectionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(collectionId, userId,
			orderByComparator);
	}

	/**
	* Removes all the data collections where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of data collections where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	*
	* @param typeId the type ID
	* @param name the name
	* @param version the version
	* @return the matching data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByT_NameVersion(
		long typeId, java.lang.String name, java.lang.String version)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_NameVersion(typeId, name, version);
	}

	/**
	* Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeId the type ID
	* @param name the name
	* @param version the version
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByT_NameVersion(
		long typeId, java.lang.String name, java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByT_NameVersion(typeId, name, version);
	}

	/**
	* Returns the data collection where typeId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeId the type ID
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data collection, or <code>null</code> if a matching data collection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByT_NameVersion(
		long typeId, java.lang.String name, java.lang.String version,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_NameVersion(typeId, name, version,
			retrieveFromCache);
	}

	/**
	* Removes the data collection where typeId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param typeId the type ID
	* @param name the name
	* @param version the version
	* @return the data collection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection removeByT_NameVersion(
		long typeId, java.lang.String name, java.lang.String version)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByT_NameVersion(typeId, name, version);
	}

	/**
	* Returns the number of data collections where typeId = &#63; and name = &#63; and version = &#63;.
	*
	* @param typeId the type ID
	* @param name the name
	* @param version the version
	* @return the number of matching data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_NameVersion(long typeId, java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_NameVersion(typeId, name, version);
	}

	/**
	* Caches the data collection in the entity cache if it is enabled.
	*
	* @param dataCollection the data collection
	*/
	public static void cacheResult(
		com.kisti.osp.icecap.model.DataCollection dataCollection) {
		getPersistence().cacheResult(dataCollection);
	}

	/**
	* Caches the data collections in the entity cache if it is enabled.
	*
	* @param dataCollections the data collections
	*/
	public static void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataCollection> dataCollections) {
		getPersistence().cacheResult(dataCollections);
	}

	/**
	* Creates a new data collection with the primary key. Does not add the data collection to the database.
	*
	* @param collectionId the primary key for the new data collection
	* @return the new data collection
	*/
	public static com.kisti.osp.icecap.model.DataCollection create(
		long collectionId) {
		return getPersistence().create(collectionId);
	}

	/**
	* Removes the data collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the data collection
	* @return the data collection that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection remove(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(collectionId);
	}

	public static com.kisti.osp.icecap.model.DataCollection updateImpl(
		com.kisti.osp.icecap.model.DataCollection dataCollection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataCollection);
	}

	/**
	* Returns the data collection with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionException} if it could not be found.
	*
	* @param collectionId the primary key of the data collection
	* @return the data collection
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionException if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection findByPrimaryKey(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(collectionId);
	}

	/**
	* Returns the data collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collectionId the primary key of the data collection
	* @return the data collection, or <code>null</code> if a data collection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollection fetchByPrimaryKey(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(collectionId);
	}

	/**
	* Returns all the data collections.
	*
	* @return the data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the data collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data collections
	* @param end the upper bound of the range of data collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data collections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data collections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data collections.
	*
	* @return the number of data collections
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataCollectionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataCollectionPersistence)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataCollectionPersistence.class.getName());

			ReferenceRegistry.registerReference(DataCollectionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataCollectionPersistence persistence) {
	}

	private static DataCollectionPersistence _persistence;
}