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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.login.model.LoginLog;

import java.util.List;

/**
 * The persistence utility for the login log service. This utility wraps {@link LoginLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yjpark
 * @see LoginLogPersistence
 * @see LoginLogPersistenceImpl
 * @generated
 */
public class LoginLogUtil {
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
	public static void clearCache(LoginLog loginLog) {
		getPersistence().clearCache(loginLog);
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
	public static List<LoginLog> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoginLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoginLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static LoginLog update(LoginLog loginLog) throws SystemException {
		return getPersistence().update(loginLog);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static LoginLog update(LoginLog loginLog,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(loginLog, serviceContext);
	}

	/**
	* Caches the login log in the entity cache if it is enabled.
	*
	* @param loginLog the login log
	*/
	public static void cacheResult(
		org.kisti.edison.login.model.LoginLog loginLog) {
		getPersistence().cacheResult(loginLog);
	}

	/**
	* Caches the login logs in the entity cache if it is enabled.
	*
	* @param loginLogs the login logs
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.login.model.LoginLog> loginLogs) {
		getPersistence().cacheResult(loginLogs);
	}

	/**
	* Creates a new login log with the primary key. Does not add the login log to the database.
	*
	* @param id the primary key for the new login log
	* @return the new login log
	*/
	public static org.kisti.edison.login.model.LoginLog create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the login log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the login log
	* @return the login log that was removed
	* @throws org.kisti.edison.login.NoSuchLoginLogException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.login.model.LoginLog remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.login.NoSuchLoginLogException {
		return getPersistence().remove(id);
	}

	public static org.kisti.edison.login.model.LoginLog updateImpl(
		org.kisti.edison.login.model.LoginLog loginLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(loginLog);
	}

	/**
	* Returns the login log with the primary key or throws a {@link org.kisti.edison.login.NoSuchLoginLogException} if it could not be found.
	*
	* @param id the primary key of the login log
	* @return the login log
	* @throws org.kisti.edison.login.NoSuchLoginLogException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.login.model.LoginLog findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.login.NoSuchLoginLogException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the login log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the login log
	* @return the login log, or <code>null</code> if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.login.model.LoginLog fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the login logs.
	*
	* @return the login logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.login.model.LoginLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<org.kisti.edison.login.model.LoginLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<org.kisti.edison.login.model.LoginLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the login logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of login logs.
	*
	* @return the number of login logs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static LoginLogPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LoginLogPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.login.service.ClpSerializer.getServletContextName(),
					LoginLogPersistence.class.getName());

			ReferenceRegistry.registerReference(LoginLogUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(LoginLogPersistence persistence) {
	}

	private static LoginLogPersistence _persistence;
}