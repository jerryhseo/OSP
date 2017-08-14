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

package org.kisti.edison.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.model.SimulationProject;

/**
 * The persistence interface for the simulation project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimulationProjectPersistenceImpl
 * @see SimulationProjectUtil
 * @generated
 */
public interface SimulationProjectPersistence extends BasePersistence<SimulationProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationProjectUtil} to access the simulation project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the simulation projects where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation projects where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of simulation projects
	* @param end the upper bound of the range of simulation projects (not inclusive)
	* @return the range of matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation projects where ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ownerId the owner ID
	* @param start the lower bound of the range of simulation projects
	* @param end the upper bound of the range of simulation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject findByOwnerId_First(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException;

	/**
	* Returns the first simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation project, or <code>null</code> if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject fetchByOwnerId_First(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject findByOwnerId_Last(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException;

	/**
	* Returns the last simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation project, or <code>null</code> if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject fetchByOwnerId_Last(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation projects before and after the current simulation project in the ordered set where ownerId = &#63;.
	*
	* @param simulationProjectId the primary key of the current simulation project
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject[] findByOwnerId_PrevAndNext(
		long simulationProjectId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException;

	/**
	* Removes all the simulation projects where ownerId = &#63; from the database.
	*
	* @param ownerId the owner ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation projects where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the number of matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the simulation project in the entity cache if it is enabled.
	*
	* @param simulationProject the simulation project
	*/
	public void cacheResult(
		org.kisti.edison.model.SimulationProject simulationProject);

	/**
	* Caches the simulation projects in the entity cache if it is enabled.
	*
	* @param simulationProjects the simulation projects
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.SimulationProject> simulationProjects);

	/**
	* Creates a new simulation project with the primary key. Does not add the simulation project to the database.
	*
	* @param simulationProjectId the primary key for the new simulation project
	* @return the new simulation project
	*/
	public org.kisti.edison.model.SimulationProject create(
		long simulationProjectId);

	/**
	* Removes the simulation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project that was removed
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject remove(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException;

	public org.kisti.edison.model.SimulationProject updateImpl(
		org.kisti.edison.model.SimulationProject simulationProject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation project with the primary key or throws a {@link org.kisti.edison.NoSuchSimulationProjectException} if it could not be found.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject findByPrimaryKey(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException;

	/**
	* Returns the simulation project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project, or <code>null</code> if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimulationProject fetchByPrimaryKey(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the simulation projects.
	*
	* @return the simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation projects
	* @param end the upper bound of the range of simulation projects (not inclusive)
	* @return the range of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimulationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation projects
	* @param end the upper bound of the range of simulation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimulationProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the simulation projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation projects.
	*
	* @return the number of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}