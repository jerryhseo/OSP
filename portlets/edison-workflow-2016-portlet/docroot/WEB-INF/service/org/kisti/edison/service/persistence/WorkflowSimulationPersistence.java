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

import org.kisti.edison.model.WorkflowSimulation;

/**
 * The persistence interface for the workflow simulation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationPersistenceImpl
 * @see WorkflowSimulationUtil
 * @generated
 */
public interface WorkflowSimulationPersistence extends BasePersistence<WorkflowSimulation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowSimulationUtil} to access the workflow simulation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the workflow simulations where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle_UserId(
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulations where title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle_UserId(
		java.lang.String title, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulations where title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle_UserId(
		java.lang.String title, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByTitle_UserId_First(
		java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByTitle_UserId_First(
		java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByTitle_UserId_Last(
		java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByTitle_UserId_Last(
		java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulations before and after the current workflow simulation in the ordered set where title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the primary key of the current workflow simulation
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation[] findByTitle_UserId_PrevAndNext(
		long simulationId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Removes all the workflow simulations where title LIKE &#63; and userId = &#63; from the database.
	*
	* @param title the title
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTitle_UserId(java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations where title LIKE &#63; and userId = &#63;.
	*
	* @param title the title
	* @param userId the user ID
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByTitle_UserId(java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations where title LIKE &#63;.
	*
	* @param title the title
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByTitle(
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the first workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the last workflow simulation in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.WorkflowSimulation[] findByTitle_PrevAndNext(
		long simulationId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Removes all the workflow simulations where title LIKE &#63; from the database.
	*
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations where title LIKE &#63;.
	*
	* @param title the title
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the first workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the last workflow simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public org.kisti.edison.model.WorkflowSimulation[] findByUserId_PrevAndNext(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Removes all the workflow simulations where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, java.lang.String title, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_Title_UserId(
		long workflowId, java.lang.String title, long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByWorkflowId_Title_UserId_First(
		long workflowId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the first workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByWorkflowId_Title_UserId_First(
		long workflowId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByWorkflowId_Title_UserId_Last(
		long workflowId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the last workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByWorkflowId_Title_UserId_Last(
		long workflowId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulations before and after the current workflow simulation in the ordered set where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the primary key of the current workflow simulation
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation[] findByWorkflowId_Title_UserId_PrevAndNext(
		long simulationId, long workflowId, java.lang.String title,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Removes all the workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63; from the database.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByWorkflowId_Title_UserId(long workflowId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations where workflowId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param title the title
	* @param userId the user ID
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByWorkflowId_Title_UserId(long workflowId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @return the matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_UserId(
		long workflowId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulations where workflowId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @return the range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_UserId(
		long workflowId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulations where workflowId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulations
	* @param end the upper bound of the range of workflow simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findByWorkflowId_UserId(
		long workflowId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByWorkflowId_UserId_First(
		long workflowId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the first workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByWorkflowId_UserId_First(
		long workflowId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByWorkflowId_UserId_Last(
		long workflowId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the last workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation, or <code>null</code> if a matching workflow simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByWorkflowId_UserId_Last(
		long workflowId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulations before and after the current workflow simulation in the ordered set where workflowId = &#63; and userId = &#63;.
	*
	* @param simulationId the primary key of the current workflow simulation
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation[] findByWorkflowId_UserId_PrevAndNext(
		long simulationId, long workflowId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Removes all the workflow simulations where workflowId = &#63; and userId = &#63; from the database.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByWorkflowId_UserId(long workflowId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations where workflowId = &#63; and userId = &#63;.
	*
	* @param workflowId the workflow ID
	* @param userId the user ID
	* @return the number of matching workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countByWorkflowId_UserId(long workflowId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the workflow simulation in the entity cache if it is enabled.
	*
	* @param workflowSimulation the workflow simulation
	*/
	public void cacheResult(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation);

	/**
	* Caches the workflow simulations in the entity cache if it is enabled.
	*
	* @param workflowSimulations the workflow simulations
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations);

	/**
	* Creates a new workflow simulation with the primary key. Does not add the workflow simulation to the database.
	*
	* @param simulationId the primary key for the new workflow simulation
	* @return the new workflow simulation
	*/
	public org.kisti.edison.model.WorkflowSimulation create(long simulationId);

	/**
	* Removes the workflow simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation that was removed
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation remove(long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	public org.kisti.edison.model.WorkflowSimulation updateImpl(
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation with the primary key or throws a {@link org.kisti.edison.NoSuchWorkflowSimulationException} if it could not be found.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation
	* @throws org.kisti.edison.NoSuchWorkflowSimulationException if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation findByPrimaryKey(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationException;

	/**
	* Returns the workflow simulation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationId the primary key of the workflow simulation
	* @return the workflow simulation, or <code>null</code> if a workflow simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulation fetchByPrimaryKey(
		long simulationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations.
	*
	* @return the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the workflow simulations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations.
	*
	* @return the number of workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulation jobs associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @return the workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> getWorkflowSimulationJobs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @return the number of workflow simulation jobs associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public int getWorkflowSimulationJobsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation job is associated with the workflow simulation.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @return <code>true</code> if the workflow simulation job is associated with the workflow simulation; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it.
	*
	* @param pk the primary key of the workflow simulation to check for associations with workflow simulation jobs
	* @return <code>true</code> if the workflow simulation has any workflow simulation jobs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulationJobs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationJob(long pk, long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJob the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the workflow simulation and its workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation to clear the associated workflow simulation jobs from
	* @throws SystemException if a system exception occurred
	*/
	public void clearWorkflowSimulationJobs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPK the primary key of the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulationJob(long pk,
		long workflowSimulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation and the workflow simulation job. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJob the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulationJob(long pk,
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation and the workflow simulation jobs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobPKs the primary keys of the workflow simulation jobs to be associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulationJobs(long pk,
		long[] workflowSimulationJobPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulation jobs associated with the workflow simulation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation
	* @param workflowSimulationJobs the workflow simulation jobs to be associated with the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulationJobs(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs)
		throws com.liferay.portal.kernel.exception.SystemException;
}