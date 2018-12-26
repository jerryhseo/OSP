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

import org.kisti.edison.model.WorkflowSimulationJob;

/**
 * The persistence interface for the workflow simulation job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see WorkflowSimulationJobPersistenceImpl
 * @see WorkflowSimulationJobUtil
 * @generated
 */
public interface WorkflowSimulationJobPersistence extends BasePersistence<WorkflowSimulationJob> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkflowSimulationJobUtil} to access the workflow simulation job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @return the matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, java.lang.String title, long userId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_Title_UserId(
		long simulationId, java.lang.String title, long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findBySimulationId_Title_UserId_First(
		long simulationId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the first workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchBySimulationId_Title_UserId_First(
		long simulationId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findBySimulationId_Title_UserId_Last(
		long simulationId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the last workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchBySimulationId_Title_UserId_Last(
		long simulationId, java.lang.String title, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationJobId the primary key of the current workflow simulation job
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob[] findBySimulationId_Title_UserId_PrevAndNext(
		long simulationJobId, long simulationId, java.lang.String title,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Removes all the workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63; from the database.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationId_Title_UserId(long simulationId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs where simulationId = &#63; and title LIKE &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param title the title
	* @param userId the user ID
	* @return the number of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationId_Title_UserId(long simulationId,
		java.lang.String title, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @return the matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findBySimulationId_UserId(
		long simulationId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findBySimulationId_UserId_First(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the first workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchBySimulationId_UserId_First(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findBySimulationId_UserId_Last(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the last workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchBySimulationId_UserId_Last(
		long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationJobId the primary key of the current workflow simulation job
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob[] findBySimulationId_UserId_PrevAndNext(
		long simulationJobId, long simulationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Removes all the workflow simulation jobs where simulationId = &#63; and userId = &#63; from the database.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationId_UserId(long simulationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs where simulationId = &#63; and userId = &#63;.
	*
	* @param simulationId the simulation ID
	* @param userId the user ID
	* @return the number of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationId_UserId(long simulationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulation jobs where title LIKE &#63;.
	*
	* @param title the title
	* @return the matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByTitle(
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulation jobs where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByTitle(
		java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulation jobs where title LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByTitle(
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation job in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the first workflow simulation job in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchByTitle_First(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation job in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the last workflow simulation job in the ordered set where title LIKE &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchByTitle_Last(
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where title LIKE &#63;.
	*
	* @param simulationJobId the primary key of the current workflow simulation job
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob[] findByTitle_PrevAndNext(
		long simulationJobId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Removes all the workflow simulation jobs where title LIKE &#63; from the database.
	*
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs where title LIKE &#63;.
	*
	* @param title the title
	* @return the number of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByTitle(java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulation jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulation jobs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulation jobs where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first workflow simulation job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the first workflow simulation job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last workflow simulation job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the last workflow simulation job in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workflow simulation job, or <code>null</code> if a matching workflow simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation jobs before and after the current workflow simulation job in the ordered set where userId = &#63;.
	*
	* @param simulationJobId the primary key of the current workflow simulation job
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob[] findByUserId_PrevAndNext(
		long simulationJobId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Removes all the workflow simulation jobs where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the workflow simulation job in the entity cache if it is enabled.
	*
	* @param workflowSimulationJob the workflow simulation job
	*/
	public void cacheResult(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob);

	/**
	* Caches the workflow simulation jobs in the entity cache if it is enabled.
	*
	* @param workflowSimulationJobs the workflow simulation jobs
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.WorkflowSimulationJob> workflowSimulationJobs);

	/**
	* Creates a new workflow simulation job with the primary key. Does not add the workflow simulation job to the database.
	*
	* @param simulationJobId the primary key for the new workflow simulation job
	* @return the new workflow simulation job
	*/
	public org.kisti.edison.model.WorkflowSimulationJob create(
		long simulationJobId);

	/**
	* Removes the workflow simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job that was removed
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob remove(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	public org.kisti.edison.model.WorkflowSimulationJob updateImpl(
		org.kisti.edison.model.WorkflowSimulationJob workflowSimulationJob)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the workflow simulation job with the primary key or throws a {@link org.kisti.edison.NoSuchWorkflowSimulationJobException} if it could not be found.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job
	* @throws org.kisti.edison.NoSuchWorkflowSimulationJobException if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob findByPrimaryKey(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchWorkflowSimulationJobException;

	/**
	* Returns the workflow simulation job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationJobId the primary key of the workflow simulation job
	* @return the workflow simulation job, or <code>null</code> if a workflow simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.WorkflowSimulationJob fetchByPrimaryKey(
		long simulationJobId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulation jobs.
	*
	* @return the workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulationJob> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the workflow simulation jobs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulation jobs.
	*
	* @return the number of workflow simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @return the workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulations associated with the workflow simulation job.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation job
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulations associated with the workflow simulation job.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation job
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @return the number of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public int getWorkflowSimulationsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation is associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @return <code>true</code> if the workflow simulation is associated with the workflow simulation job; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation job has any workflow simulations associated with it.
	*
	* @param pk the primary key of the workflow simulation job to check for associations with workflow simulations
	* @return <code>true</code> if the workflow simulation job has any workflow simulations associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulation the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the workflow simulation job and its workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job to clear the associated workflow simulations from
	* @throws SystemException if a system exception occurred
	*/
	public void clearWorkflowSimulations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulation the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations to be associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations to be associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the workflow simulations associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @return the workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the workflow simulations associated with the workflow simulation job.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation job
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @return the range of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the workflow simulations associated with the workflow simulation job.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.WorkflowSimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the workflow simulation job
	* @param start the lower bound of the range of workflow simulation jobs
	* @param end the upper bound of the range of workflow simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.WorkflowSimulation> getWorkflowSimulations(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of workflow simulations associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @return the number of workflow simulations associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public int getWorkflowSimulationsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation is associated with the workflow simulation job.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @return <code>true</code> if the workflow simulation is associated with the workflow simulation job; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the workflow simulation job has any workflow simulations associated with it.
	*
	* @param pk the primary key of the workflow simulation job to check for associations with workflow simulations
	* @return <code>true</code> if the workflow simulation job has any workflow simulations associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsWorkflowSimulations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulation the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void addWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the workflow simulation job and its workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job to clear the associated workflow simulations from
	* @throws SystemException if a system exception occurred
	*/
	public void clearWorkflowSimulations(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPK the primary key of the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulation(long pk, long workflowSimulationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulation the workflow simulation
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulation(long pk,
		org.kisti.edison.model.WorkflowSimulation workflowSimulation)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the workflow simulation job and the workflow simulations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations
	* @throws SystemException if a system exception occurred
	*/
	public void removeWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulationPKs the primary keys of the workflow simulations to be associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulations(long pk, long[] workflowSimulationPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the workflow simulations associated with the workflow simulation job, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the workflow simulation job
	* @param workflowSimulations the workflow simulations to be associated with the workflow simulation job
	* @throws SystemException if a system exception occurred
	*/
	public void setWorkflowSimulations(long pk,
		java.util.List<org.kisti.edison.model.WorkflowSimulation> workflowSimulations)
		throws com.liferay.portal.kernel.exception.SystemException;
}