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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataEntryPersistenceImpl
 * @see DataEntryUtil
 * @generated
 */
public interface DataEntryPersistence extends BasePersistence<DataEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataEntryUtil} to access the data entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the data entries where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @return the matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findBySimulationSubject(
		long simulationSubjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry findBySimulationSubject_First(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry fetchBySimulationSubject_First(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry findBySimulationSubject_Last(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data entry in the ordered set where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry fetchBySimulationSubject_Last(
		long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.kisti.osp.icecap.model.DataEntry[] findBySimulationSubject_PrevAndNext(
		long entryId, long simulationSubjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data entries where simulationSubjectId = &#63; from the database.
	*
	* @param simulationSubjectId the simulation subject ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationSubject(long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entries where simulationSubjectId = &#63;.
	*
	* @param simulationSubjectId the simulation subject ID
	* @return the number of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationSubject(long simulationSubjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data entries where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @return the matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findByCollectionID(
		long collectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry findByCollectionID_First(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry fetchByCollectionID_First(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry findByCollectionID_Last(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data entry in the ordered set where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data entry, or <code>null</code> if a matching data entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry fetchByCollectionID_Last(
		long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.kisti.osp.icecap.model.DataEntry[] findByCollectionID_PrevAndNext(
		long entryId, long collectionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data entries where collectionId = &#63; from the database.
	*
	* @param collectionId the collection ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCollectionID(long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entries where collectionId = &#63;.
	*
	* @param collectionId the collection ID
	* @return the number of matching data entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCollectionID(long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data entry in the entity cache if it is enabled.
	*
	* @param dataEntry the data entry
	*/
	public void cacheResult(com.kisti.osp.icecap.model.DataEntry dataEntry);

	/**
	* Caches the data entries in the entity cache if it is enabled.
	*
	* @param dataEntries the data entries
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataEntry> dataEntries);

	/**
	* Creates a new data entry with the primary key. Does not add the data entry to the database.
	*
	* @param entryId the primary key for the new data entry
	* @return the new data entry
	*/
	public com.kisti.osp.icecap.model.DataEntry create(long entryId);

	/**
	* Removes the data entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry remove(long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataEntry updateImpl(
		com.kisti.osp.icecap.model.DataEntry dataEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataEntryException} if it could not be found.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry
	* @throws com.kisti.osp.icecap.NoSuchDataEntryException if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry findByPrimaryKey(long entryId)
		throws com.kisti.osp.icecap.NoSuchDataEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the data entry
	* @return the data entry, or <code>null</code> if a data entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataEntry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data entries.
	*
	* @return the data entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data entries.
	*
	* @return the number of data entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}