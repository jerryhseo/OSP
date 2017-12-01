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

package org.kisti.edison.simulation.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.simulation.model.BatchMonitoring;

/**
 * The persistence interface for the batch monitoring service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see BatchMonitoringPersistenceImpl
 * @see BatchMonitoringUtil
 * @generated
 */
public interface BatchMonitoringPersistence extends BasePersistence<BatchMonitoring> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BatchMonitoringUtil} to access the batch monitoring persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the batch monitoring in the entity cache if it is enabled.
	*
	* @param batchMonitoring the batch monitoring
	*/
	public void cacheResult(
		org.kisti.edison.simulation.model.BatchMonitoring batchMonitoring);

	/**
	* Caches the batch monitorings in the entity cache if it is enabled.
	*
	* @param batchMonitorings the batch monitorings
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.simulation.model.BatchMonitoring> batchMonitorings);

	/**
	* Creates a new batch monitoring with the primary key. Does not add the batch monitoring to the database.
	*
	* @param batSeqNo the primary key for the new batch monitoring
	* @return the new batch monitoring
	*/
	public org.kisti.edison.simulation.model.BatchMonitoring create(
		long batSeqNo);

	/**
	* Removes the batch monitoring with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param batSeqNo the primary key of the batch monitoring
	* @return the batch monitoring that was removed
	* @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.simulation.model.BatchMonitoring remove(
		long batSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.simulation.NoSuchBatchMonitoringException;

	public org.kisti.edison.simulation.model.BatchMonitoring updateImpl(
		org.kisti.edison.simulation.model.BatchMonitoring batchMonitoring)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the batch monitoring with the primary key or throws a {@link org.kisti.edison.simulation.NoSuchBatchMonitoringException} if it could not be found.
	*
	* @param batSeqNo the primary key of the batch monitoring
	* @return the batch monitoring
	* @throws org.kisti.edison.simulation.NoSuchBatchMonitoringException if a batch monitoring with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.simulation.model.BatchMonitoring findByPrimaryKey(
		long batSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.simulation.NoSuchBatchMonitoringException;

	/**
	* Returns the batch monitoring with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param batSeqNo the primary key of the batch monitoring
	* @return the batch monitoring, or <code>null</code> if a batch monitoring with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.simulation.model.BatchMonitoring fetchByPrimaryKey(
		long batSeqNo)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the batch monitorings.
	*
	* @return the batch monitorings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.simulation.model.BatchMonitoring> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the batch monitorings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.simulation.model.impl.BatchMonitoringModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of batch monitorings
	* @param end the upper bound of the range of batch monitorings (not inclusive)
	* @return the range of batch monitorings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.simulation.model.BatchMonitoring> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the batch monitorings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.simulation.model.impl.BatchMonitoringModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of batch monitorings
	* @param end the upper bound of the range of batch monitorings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of batch monitorings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.simulation.model.BatchMonitoring> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the batch monitorings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of batch monitorings.
	*
	* @return the number of batch monitorings
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}