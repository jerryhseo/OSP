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

import org.kisti.edison.osp.model.Execute;

/**
 * The persistence interface for the execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ExecutePersistenceImpl
 * @see ExecuteUtil
 * @generated
 */
public interface ExecutePersistence extends BasePersistence<Execute> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExecuteUtil} to access the execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the executes where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the executes where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @return the range of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the executes where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException;

	/**
	* Returns the first execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching execute, or <code>null</code> if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute fetchByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException;

	/**
	* Returns the last execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching execute, or <code>null</code> if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute fetchByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the executes before and after the current execute in the ordered set where projectId = &#63;.
	*
	* @param executePK the primary key of the current execute
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute[] findByProjectId_PrevAndNext(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException;

	/**
	* Removes all the executes where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of executes where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the execute in the entity cache if it is enabled.
	*
	* @param execute the execute
	*/
	public void cacheResult(org.kisti.edison.osp.model.Execute execute);

	/**
	* Caches the executes in the entity cache if it is enabled.
	*
	* @param executes the executes
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.osp.model.Execute> executes);

	/**
	* Creates a new execute with the primary key. Does not add the execute to the database.
	*
	* @param executePK the primary key for the new execute
	* @return the new execute
	*/
	public org.kisti.edison.osp.model.Execute create(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK);

	/**
	* Removes the execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param executePK the primary key of the execute
	* @return the execute that was removed
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute remove(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException;

	public org.kisti.edison.osp.model.Execute updateImpl(
		org.kisti.edison.osp.model.Execute execute)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the execute with the primary key or throws a {@link org.kisti.edison.osp.NoSuchExecuteException} if it could not be found.
	*
	* @param executePK the primary key of the execute
	* @return the execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute findByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException;

	/**
	* Returns the execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param executePK the primary key of the execute
	* @return the execute, or <code>null</code> if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.osp.model.Execute fetchByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the executes.
	*
	* @return the executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @return the range of executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.osp.model.Execute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of executes.
	*
	* @return the number of executes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}