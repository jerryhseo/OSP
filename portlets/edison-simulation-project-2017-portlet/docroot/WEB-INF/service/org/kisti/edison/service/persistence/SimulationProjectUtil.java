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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.model.SimulationProject;

import java.util.List;

/**
 * The persistence utility for the simulation project service. This utility wraps {@link SimulationProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimulationProjectPersistence
 * @see SimulationProjectPersistenceImpl
 * @generated
 */
public class SimulationProjectUtil {
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
	public static void clearCache(SimulationProject simulationProject) {
		getPersistence().clearCache(simulationProject);
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
	public static List<SimulationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SimulationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SimulationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SimulationProject update(SimulationProject simulationProject)
		throws SystemException {
		return getPersistence().update(simulationProject);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SimulationProject update(
		SimulationProject simulationProject, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(simulationProject, serviceContext);
	}

	/**
	* Returns all the simulation projects where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOwnerId(ownerId);
	}

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
	public static java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOwnerId(ownerId, start, end);
	}

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
	public static java.util.List<org.kisti.edison.model.SimulationProject> findByOwnerId(
		long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOwnerId(ownerId, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject findByOwnerId_First(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException {
		return getPersistence().findByOwnerId_First(ownerId, orderByComparator);
	}

	/**
	* Returns the first simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation project, or <code>null</code> if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject fetchByOwnerId_First(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByOwnerId_First(ownerId, orderByComparator);
	}

	/**
	* Returns the last simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject findByOwnerId_Last(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException {
		return getPersistence().findByOwnerId_Last(ownerId, orderByComparator);
	}

	/**
	* Returns the last simulation project in the ordered set where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation project, or <code>null</code> if a matching simulation project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject fetchByOwnerId_Last(
		long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByOwnerId_Last(ownerId, orderByComparator);
	}

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
	public static org.kisti.edison.model.SimulationProject[] findByOwnerId_PrevAndNext(
		long simulationProjectId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException {
		return getPersistence()
				   .findByOwnerId_PrevAndNext(simulationProjectId, ownerId,
			orderByComparator);
	}

	/**
	* Removes all the simulation projects where ownerId = &#63; from the database.
	*
	* @param ownerId the owner ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOwnerId(ownerId);
	}

	/**
	* Returns the number of simulation projects where ownerId = &#63;.
	*
	* @param ownerId the owner ID
	* @return the number of matching simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOwnerId(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOwnerId(ownerId);
	}

	/**
	* Caches the simulation project in the entity cache if it is enabled.
	*
	* @param simulationProject the simulation project
	*/
	public static void cacheResult(
		org.kisti.edison.model.SimulationProject simulationProject) {
		getPersistence().cacheResult(simulationProject);
	}

	/**
	* Caches the simulation projects in the entity cache if it is enabled.
	*
	* @param simulationProjects the simulation projects
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.model.SimulationProject> simulationProjects) {
		getPersistence().cacheResult(simulationProjects);
	}

	/**
	* Creates a new simulation project with the primary key. Does not add the simulation project to the database.
	*
	* @param simulationProjectId the primary key for the new simulation project
	* @return the new simulation project
	*/
	public static org.kisti.edison.model.SimulationProject create(
		long simulationProjectId) {
		return getPersistence().create(simulationProjectId);
	}

	/**
	* Removes the simulation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project that was removed
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject remove(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException {
		return getPersistence().remove(simulationProjectId);
	}

	public static org.kisti.edison.model.SimulationProject updateImpl(
		org.kisti.edison.model.SimulationProject simulationProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(simulationProject);
	}

	/**
	* Returns the simulation project with the primary key or throws a {@link org.kisti.edison.NoSuchSimulationProjectException} if it could not be found.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project
	* @throws org.kisti.edison.NoSuchSimulationProjectException if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject findByPrimaryKey(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimulationProjectException {
		return getPersistence().findByPrimaryKey(simulationProjectId);
	}

	/**
	* Returns the simulation project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationProjectId the primary key of the simulation project
	* @return the simulation project, or <code>null</code> if a simulation project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.SimulationProject fetchByPrimaryKey(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(simulationProjectId);
	}

	/**
	* Returns all the simulation projects.
	*
	* @return the simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.SimulationProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<org.kisti.edison.model.SimulationProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.kisti.edison.model.SimulationProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the simulation projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of simulation projects.
	*
	* @return the number of simulation projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SimulationProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SimulationProjectPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					SimulationProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(SimulationProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SimulationProjectPersistence persistence) {
	}

	private static SimulationProjectPersistence _persistence;
}