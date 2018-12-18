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

import org.kisti.edison.model.WorkflowSimulation;

import java.util.List;

/**
 * The persistence utility for the workflow simulation service. This utility wraps {@link WorkflowSimulationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationPersistence
 * @see WorkflowSimulationPersistenceImpl
 * @generated
 */
public class WorkflowSimulationUtil {
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
	public static void clearCache(WorkflowSimulation workflowSimulation) {
		getPersistence().clearCache(workflowSimulation);
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
	public static List<WorkflowSimulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkflowSimulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkflowSimulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static WorkflowSimulation update(
		WorkflowSimulation workflowSimulation) throws SystemException {
		return getPersistence().update(workflowSimulation);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static WorkflowSimulation update(
		WorkflowSimulation workflowSimulation, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(workflowSimulation, serviceContext);
	}

	/**
	* Returns all the workflow simulations where title LIKE &#63;.
	*
	* @param title the title
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title);
	}

	/**
	* Returns a range of all the workflow simulations where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title, start, end);
	}

	/**
	* Returns an ordered range of all the workflow simulations where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTitle(title, start, end, orderByComparator);
	}

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation findByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().findByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation fetchByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation findByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().findByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation fetchByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the workflow simulations before and after the current workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param simulationId the primary key of the current workflow simulation
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation[] findByTitle_PrevAndNext(
		long simulationId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence()
				   .findByTitle_PrevAndNext(simulationId, title,
			orderByComparator);
	}

	/**
	* Removes all the workflow simulations where title LIKE &#63; from the database.
	*
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTitle(title);
	}

	/**
	* Returns the number of workflow simulations where title LIKE &#63;.
	*
	* @param title the title
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTitle(title);
	}

	/**
	* Returns all the workflow simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the workflow simulations where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the workflow simulations where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the workflow simulations before and after the current workflow simulation in the ordered set where userId = &#63;.
	*
	* @param simulationId the primary key of the current workflow simulation
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation[] findByUserId_PrevAndNext(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence()
				   .findByUserId_PrevAndNext(simulationId, userId,
			orderByComparator);
	}

	/**
	* Removes all the workflow simulations where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of workflow simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Caches the workflow simulation in the entity cache if it is enabled.
	*
	* @param workflowSimulation the workflow simulation
	*/
	public static void cacheResult(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation) {
		getPersistence().cacheResult(workflowSimulation);
	}

	/**
	* Caches the workflow simulations in the entity cache if it is enabled.
	*
	* @param workflowSimulations the workflow simulations
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations) {
		getPersistence().cacheResult(workflowSimulations);
	}

	/**
	* Creates a new workflow simulation with the primary key. Does not add the workflow simulation to the database.
	*
	* @param simulationId the primary key for the new workflow simulation
	* @return the new workflow simulation
	*/
	public static org.kisti.edison.model.WorkflowSimulation create(
		long simulationId) {
		return getPersistence().create(simulationId);
	}

	/**
	* Removes the workflow simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation that was removed
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation remove(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().remove(simulationId);
	}

	public static org.kisti.edison.model.WorkflowSimulation updateImpl(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(workflowSimulation);
	}

	/**
	* Returns the workflow simulation with the primary key or throws a {@link org.kisti.edison.NoSuchWorkflowSimulationException} if it could not be found.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation findByPrimaryKey(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException {
		return getPersistence().findByPrimaryKey(simulationId);
	}

	/**
	* Returns the workflow simulation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation, or <code>null</code> if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.WorkflowSimulation fetchByPrimaryKey(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(simulationId);
	}

	/**
	* Returns all the workflow simulations.
	*
	* @return the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the workflow simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the workflow simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the workflow simulations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workflow simulations.
	*
	* @return the number of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the workflow simulation jobs associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @return the workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getWorkflowSimulationJobs(pk);
	}

	/**
	* Returns a range of all the workflow simulation jobs associated with the workflow simulation.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getWorkflowSimulationJobs(pk, start, end);
	}

	/**
	* Returns an ordered range of all the workflow simulation jobs associated with the workflow simulation.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getWorkflowSimulationJobs(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of workflow simulation jobs associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @return the number of workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static int getWorkflowSimulationJobsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getWorkflowSimulationJobsSize(pk);
	}

	/**
	* Returns <code>true</code> if the workflow simulation job is associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @return <code>true</code> if the workflow simulation job is associated with the workflow simulation; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .containsWorkflowSimulationJob(pk, workflowSimulationJobPK);
	}

	/**
	* Returns <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it.
	*
	* @param pk the primary key of the workflow simulation to check for associations with workflow simulation jobs
	* @return <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsWorkflowSimulationJobs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsWorkflowSimulationJobs(pk);
	}

	/**
	* Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addWorkflowSimulationJob(pk, workflowSimulationJobPK);
	}

	/**
	* Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJob the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addWorkflowSimulationJob(pk, workflowSimulationJob);
	}

	/**
	* Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addWorkflowSimulationJobs(pk, workflowSimulationJobPKs);
	}

	/**
	* Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static void addWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addWorkflowSimulationJobs(pk, workflowSimulationJobs);
	}

	/**
	* Clears all associations between the workflow simulation and its workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation to clear the associated workflow simulation jobs from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearWorkflowSimulationJobs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearWorkflowSimulationJobs(pk);
	}

	/**
	* Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public static void removeWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeWorkflowSimulationJob(pk, workflowSimulationJobPK);
	}

	/**
	* Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJob the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public static void removeWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeWorkflowSimulationJob(pk, workflowSimulationJob);
	}

	/**
	* Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static void removeWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeWorkflowSimulationJobs(pk, workflowSimulationJobPKs);
	}

	/**
	* Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static void removeWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeWorkflowSimulationJobs(pk, workflowSimulationJobs);
	}

	/**
	* Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs to be associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static void setWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setWorkflowSimulationJobs(pk, workflowSimulationJobPKs);
	}

	/**
	* Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs to be associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public static void setWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setWorkflowSimulationJobs(pk, workflowSimulationJobs);
	}

	public static WorkflowSimulationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WorkflowSimulationPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					WorkflowSimulationPersistence.class.getName());

			ReferenceRegistry.registerReference(WorkflowSimulationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(WorkflowSimulationPersistence persistence) {
	}

	private static WorkflowSimulationPersistence _persistence;
}