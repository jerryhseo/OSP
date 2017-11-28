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

package org.kisti.edison.login.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.login.model.LoginLog;

/**
 * The persistence interface for the login log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yjpark
 * @see LoginLogPersistenceImpl
 * @see LoginLogUtil
 * @generated
 */
public interface LoginLogPersistence extends BasePersistence<LoginLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoginLogUtil} to access the login log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the login log in the entity cache if it is enabled.
	*
	* @param loginLog the login log
	*/
	public void cacheResult(org.kisti.edison.login.model.LoginLog loginLog);

	/**
	* Caches the login logs in the entity cache if it is enabled.
	*
	* @param loginLogs the login logs
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.login.model.LoginLog> loginLogs);

	/**
	* Creates a new login log with the primary key. Does not add the login log to the database.
	*
	* @param id the primary key for the new login log
	* @return the new login log
	*/
	public org.kisti.edison.login.model.LoginLog create(long id);

	/**
	* Removes the login log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the login log
	* @return the login log that was removed
	* @throws org.kisti.edison.login.NoSuchLoginLogException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.login.model.LoginLog remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.login.NoSuchLoginLogException;

	public org.kisti.edison.login.model.LoginLog updateImpl(
		org.kisti.edison.login.model.LoginLog loginLog)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the login log with the primary key or throws a {@link org.kisti.edison.login.NoSuchLoginLogException} if it could not be found.
	*
	* @param id the primary key of the login log
	* @return the login log
	* @throws org.kisti.edison.login.NoSuchLoginLogException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.login.model.LoginLog findByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.login.NoSuchLoginLogException;

	/**
	* Returns the login log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the login log
	* @return the login log, or <code>null</code> if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.login.model.LoginLog fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the login logs.
	*
	* @return the login logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.login.model.LoginLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the login logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.login.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of login logs
	* @param end the upper bound of the range of login logs (not inclusive)
	* @return the range of login logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.login.model.LoginLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the login logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.login.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of login logs
	* @param end the upper bound of the range of login logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of login logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.login.model.LoginLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the login logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of login logs.
	*
	* @return the number of login logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}