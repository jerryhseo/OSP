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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.bestsimulation.model.SimulationShare;

/**
 * The persistence interface for the simulation share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationSharePersistenceImpl
 * @see SimulationShareUtil
 * @generated
 */
public interface SimulationSharePersistence extends BasePersistence<SimulationShare> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationShareUtil} to access the simulation share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the simulation share in the entity cache if it is enabled.
	*
	* @param simulationShare the simulation share
	*/
	public void cacheResult(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare);

	/**
	* Caches the simulation shares in the entity cache if it is enabled.
	*
	* @param simulationShares the simulation shares
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> simulationShares);

	/**
	* Creates a new simulation share with the primary key. Does not add the simulation share to the database.
	*
	* @param simulationSharePK the primary key for the new simulation share
	* @return the new simulation share
	*/
	public org.kisti.edison.bestsimulation.model.SimulationShare create(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK);

	/**
	* Removes the simulation share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationShare remove(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationShareException;

	public org.kisti.edison.bestsimulation.model.SimulationShare updateImpl(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation share with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationShareException} if it could not be found.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationShareException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationShare findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationShareException;

	/**
	* Returns the simulation share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share, or <code>null</code> if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.SimulationShare fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the simulation shares.
	*
	* @return the simulation shares
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the simulation shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation shares
	* @param end the upper bound of the range of simulation shares (not inclusive)
	* @return the range of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the simulation shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation shares
	* @param end the upper bound of the range of simulation shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the simulation shares from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation shares.
	*
	* @return the number of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}