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

import com.kisti.osp.icecap.model.DataEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the data entry service. This utility wraps {@link DataEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryPersistence
 * @see DataEntryPersistenceImpl
 * @generated
 */
public class DataEntryUtil {
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
	public static void clearCache(DataEntry dataEntry) {
		getPersistence().clearCache(dataEntry);
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
	public static List<DataEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataEntry update(DataEntry dataEntry)
		throws SystemException {
		return getPersistence().update(dataEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataEntry update(DataEntry dataEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dataEntry, serviceContext);
	}

	/**
	* Returns all the data entries where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @return the matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySimulationSubject(simulationSubjectId);
	}

	/**
	* Returns a range of all the data entries where simulationSubjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationSubjectId the simulation subject ID
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @return the range of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationSubject(simulationSubjectId, start, end);
	}

	/**
	* Returns an ordered range of all the data entries where simulationSubjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationSubjectId the simulation subject ID
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationSubject(simulationSubjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry findBySimulationSubject_First(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationSubject_First(simulationSubjectId,
			orderByComparator);
	}

	/**
	* Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry fetchBySimulationSubject_First(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationSubject_First(simulationSubjectId,
			orderByComparator);
	}

	/**
	* Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry findBySimulationSubject_Last(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationSubject_Last(simulationSubjectId,
			orderByComparator);
	}

	/**
	* Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry fetchBySimulationSubject_Last(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationSubject_Last(simulationSubjectId,
			orderByComparator);
	}

	/**
	* Returns the data entries before and after the current data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param entryId the primary key of the current data entry
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry[] findBySimulationSubject_PrevAndNext(
		long entryId, long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationSubject_PrevAndNext(entryId,
			simulationSubjectId, orderByComparator);
	}

	/**
	* Removes all the data entries where simulationSubjectId = &#63; from the database.
	*
	* @param simulationSubjectId the simulation subject ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySimulationSubject(long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySimulationSubject(simulationSubjectId);
	}

	/**
	* Returns the number of data entries where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @return the number of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySimulationSubject(long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySimulationSubject(simulationSubjectId);
	}

	/**
	* Returns all the data entries where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @return the matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCollectionID(collectionId);
	}

	/**
	* Returns a range of all the data entries where collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param collectionId the collection ID
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @return the range of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCollectionID(collectionId, start, end);
	}

	/**
	* Returns an ordered range of all the data entries where collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param collectionId the collection ID
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCollectionID(collectionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry findByCollectionID_First(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCollectionID_First(collectionId, orderByComparator);
	}

	/**
	* Returns the first data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry fetchByCollectionID_First(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCollectionID_First(collectionId, orderByComparator);
	}

	/**
	* Returns the last data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry findByCollectionID_Last(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCollectionID_Last(collectionId, orderByComparator);
	}

	/**
	* Returns the last data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry fetchByCollectionID_Last(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCollectionID_Last(collectionId, orderByComparator);
	}

	/**
	* Returns the data entries before and after the current data entry in the ordered set where collectionId = &#63;.
	*
	* @param entryId the primary key of the current data entry
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry[] findByCollectionID_PrevAndNext(
		long entryId, long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCollectionID_PrevAndNext(entryId, collectionId,
			orderByComparator);
	}

	/**
	* Removes all the data entries where collectionId = &#63; from the database.
	*
	* @param collectionId the collection ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCollectionID(long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCollectionID(collectionId);
	}

	/**
	* Returns the number of data entries where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @return the number of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCollectionID(long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCollectionID(collectionId);
	}

	/**
	* Caches the data entry in the entity cache if it is enabled.
	*
	* @param dataEntry the data entry
	*/
	public static void cacheResult(
		com.kisti.osp.icecap.model.DataEntry dataEntry) {
		getPersistence().cacheResult(dataEntry);
	}

	/**
	* Caches the data entries in the entity cache if it is enabled.
	*
	* @param dataEntries the data entries
	*/
	public static void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataEntry> dataEntries) {
		getPersistence().cacheResult(dataEntries);
	}

	/**
	* Creates a new data entry with the primary key. Does not add the data entry to the database.
	*
	* @param entryId the primary key for the new data entry
	* @return the new data entry
	*/
	public static com.kisti.osp.icecap.model.DataEntry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry remove(long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.kisti.osp.icecap.model.DataEntry updateImpl(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataEntry);
	}

	/**
	* Returns the data entry with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryException} if it could not be found.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry findByPrimaryKey(
		long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the data entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry, or <code>null</code> if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataEntry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the data entries.
	*
	* @return the data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the data entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data entries
	* @param end the upper bound of the range of data entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data entries.
	*
	* @return the number of data entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataEntryPersistence)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(DataEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataEntryPersistence persistence) {
	}

	private static DataEntryPersistence _persistence;
}