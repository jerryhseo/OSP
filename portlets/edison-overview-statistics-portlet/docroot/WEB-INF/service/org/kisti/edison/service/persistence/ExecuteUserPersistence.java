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

import org.kisti.edison.model.ExecuteUser;

/**
 * The persistence interface for the execute user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ExecuteUserPersistenceImpl
 * @see ExecuteUserUtil
 * @generated
 */
public interface ExecuteUserPersistence extends BasePersistence<ExecuteUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExecuteUserUtil} to access the execute user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the execute user in the entity cache if it is enabled.
	*
	* @param executeUser the execute user
	*/
	public void cacheResult(org.kisti.edison.model.ExecuteUser executeUser);

	/**
	* Caches the execute users in the entity cache if it is enabled.
	*
	* @param executeUsers the execute users
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.ExecuteUser> executeUsers);

	/**
	* Creates a new execute user with the primary key. Does not add the execute user to the database.
	*
	* @param createDate the primary key for the new execute user
	* @return the new execute user
	*/
	public org.kisti.edison.model.ExecuteUser create(
		java.lang.String createDate);

	/**
	* Removes the execute user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param createDate the primary key of the execute user
	* @return the execute user that was removed
	* @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.ExecuteUser remove(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchExecuteUserException;

	public org.kisti.edison.model.ExecuteUser updateImpl(
		org.kisti.edison.model.ExecuteUser executeUser)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the execute user with the primary key or throws a {@link org.kisti.edison.NoSuchExecuteUserException} if it could not be found.
	*
	* @param createDate the primary key of the execute user
	* @return the execute user
	* @throws org.kisti.edison.NoSuchExecuteUserException if a execute user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.ExecuteUser findByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchExecuteUserException;

	/**
	* Returns the execute user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param createDate the primary key of the execute user
	* @return the execute user, or <code>null</code> if a execute user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.ExecuteUser fetchByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the execute users.
	*
	* @return the execute users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.ExecuteUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the execute users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ExecuteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of execute users
	* @param end the upper bound of the range of execute users (not inclusive)
	* @return the range of execute users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.ExecuteUser> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the execute users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ExecuteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of execute users
	* @param end the upper bound of the range of execute users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of execute users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.ExecuteUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the execute users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of execute users.
	*
	* @return the number of execute users
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}