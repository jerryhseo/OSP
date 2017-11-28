/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.bestsimulation.model.SimulationJob;

/**
 * The persistence interface for the simulation job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationJobPersistenceImpl
 * @see SimulationJobUtil
 * @generated
 */
public interface SimulationJobPersistence extends BasePersistence<SimulationJob> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationJobUtil} to access the simulation job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the simulation jobs where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation jobs where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation jobs where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob findBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob findBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob[] findBysimulationUuid_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Removes all the simulation jobs where simulationUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation jobs where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob findByjobUuid_First(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchByjobUuid_First(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob findByjobUuid_Last(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchByjobUuid_Last(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob[] findByjobUuid_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Removes all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByjobUuid(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByjobUuid(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the simulation job in the entity cache if it is enabled.
	*
	* @param simulationJob the simulation job
	*/
	public void cacheResult(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob);

	/**
	* Caches the simulation jobs in the entity cache if it is enabled.
	*
	* @param simulationJobs the simulation jobs
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> simulationJobs);

	/**
	* Creates a new simulation job with the primary key. Does not add the simulation job to the database.
	*
	* @param simulationJobPK the primary key for the new simulation job
	* @return the new simulation job
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob create(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK);

	/**
	* Removes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob remove(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	public org.kisti.edison.bestsimulation.model.SimulationJob updateImpl(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation job with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationJobException} if it could not be found.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException;

	/**
	* Returns the simulation job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job, or <code>null</code> if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationJob fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the simulation jobs.
	*
	* @return the simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the simulation jobs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation jobs.
	*
	* @return the number of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}