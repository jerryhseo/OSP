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

package org.kisti.edison.osp.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.osp.model.Project;

/**
 * The persistence interface for the project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ProjectPersistenceImpl
 * @see ProjectUtil
 * @generated
 */
public interface ProjectPersistence extends BasePersistence<Project> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectUtil} to access the project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the projects where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findBysimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first project in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws org.kisti.edison.osp.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project findBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project fetchBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last project in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws org.kisti.edison.osp.NoSuchProjectException if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project findBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project fetchBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects before and after the current project in the ordered set where simulationUuid = &#63;.
	*
	* @param projectPK the primary key of the current project
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project[] findBysimulationUuid_PrevAndNext(
		org.kisti.edison.osp.service.persistence.ProjectPK projectPK,
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchProjectException;

	/**
	* Removes all the projects where simulationUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the number of matching projects
	* @throws SystemException if a system exception occurred
	*/
	public int countBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the project in the entity cache if it is enabled.
	*
	* @param project the project
	*/
	public void cacheResult(org.kisti.edison.osp.model.Project project);

	/**
	* Caches the projects in the entity cache if it is enabled.
	*
	* @param projects the projects
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.osp.model.Project> projects);

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectPK the primary key for the new project
	* @return the new project
	*/
	public org.kisti.edison.osp.model.Project create(
		org.kisti.edison.osp.service.persistence.ProjectPK projectPK);

	/**
	* Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectPK the primary key of the project
	* @return the project that was removed
	* @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project remove(
		org.kisti.edison.osp.service.persistence.ProjectPK projectPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchProjectException;

	public org.kisti.edison.osp.model.Project updateImpl(
		org.kisti.edison.osp.model.Project project)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the project with the primary key or throws a {@link org.kisti.edison.osp.NoSuchProjectException} if it could not be found.
	*
	* @param projectPK the primary key of the project
	* @return the project
	* @throws org.kisti.edison.osp.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project findByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ProjectPK projectPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchProjectException;

	/**
	* Returns the project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectPK the primary key of the project
	* @return the project, or <code>null</code> if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Project fetchByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ProjectPK projectPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects.
	*
	* @return the projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Project> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}