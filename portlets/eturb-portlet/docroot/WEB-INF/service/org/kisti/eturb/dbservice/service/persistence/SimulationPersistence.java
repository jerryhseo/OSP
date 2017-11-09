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

package org.kisti.eturb.dbservice.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.eturb.dbservice.model.Simulation;

/**
 * The persistence interface for the simulation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationPersistenceImpl
 * @see SimulationUtil
 * @generated
 */
public interface SimulationPersistence extends BasePersistence<Simulation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationUtil} to access the simulation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the simulations where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulations where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulations where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first simulation in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.eturb.dbservice.NoSuchSimulationException;

	/**
	* Returns the first simulation in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation fetchByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last simulation in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.eturb.dbservice.NoSuchSimulationException;

	/**
	* Returns the last simulation in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation fetchByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulations before and after the current simulation in the ordered set where projectId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation[] findByProjectId_PrevAndNext(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK simulationPK,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.eturb.dbservice.NoSuchSimulationException;

	/**
	* Removes all the simulations where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulations where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the simulation in the entity cache if it is enabled.
	*
	* @param simulation the simulation
	*/
	public void cacheResult(
		org.kisti.eturb.dbservice.model.Simulation simulation);

	/**
	* Caches the simulations in the entity cache if it is enabled.
	*
	* @param simulations the simulations
	*/
	public void cacheResult(
		java.util.List<org.kisti.eturb.dbservice.model.Simulation> simulations);

	/**
	* Creates a new simulation with the primary key. Does not add the simulation to the database.
	*
	* @param simulationPK the primary key for the new simulation
	* @return the new simulation
	*/
	public org.kisti.eturb.dbservice.model.Simulation create(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK simulationPK);

	/**
	* Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation that was removed
	* @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation remove(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.eturb.dbservice.NoSuchSimulationException;

	public org.kisti.eturb.dbservice.model.Simulation updateImpl(
		org.kisti.eturb.dbservice.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation with the primary key or throws a {@link org.kisti.eturb.dbservice.NoSuchSimulationException} if it could not be found.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation
	* @throws org.kisti.eturb.dbservice.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation findByPrimaryKey(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.eturb.dbservice.NoSuchSimulationException;

	/**
	* Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.eturb.dbservice.model.Simulation fetchByPrimaryKey(
		org.kisti.eturb.dbservice.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the simulations.
	*
	* @return the simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.eturb.dbservice.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.eturb.dbservice.model.Simulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the simulations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulations.
	*
	* @return the number of simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}