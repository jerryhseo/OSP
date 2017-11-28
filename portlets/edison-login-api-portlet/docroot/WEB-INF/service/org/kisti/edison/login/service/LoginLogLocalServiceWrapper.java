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

package org.kisti.edison.login.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoginLogLocalService}.
 *
 * @author yjpark
 * @see LoginLogLocalService
 * @generated
 */
public class LoginLogLocalServiceWrapper implements LoginLogLocalService,
	ServiceWrapper<LoginLogLocalService> {
	public LoginLogLocalServiceWrapper(
		LoginLogLocalService loginLogLocalService) {
		_loginLogLocalService = loginLogLocalService;
	}

	/**
	* Adds the login log to the database. Also notifies the appropriate model listeners.
	*
	* @param loginLog the login log
	* @return the login log that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog addLoginLog(
		org.kisti.edison.login.model.LoginLog loginLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.addLoginLog(loginLog);
	}

	/**
	* Creates a new login log with the primary key. Does not add the login log to the database.
	*
	* @param id the primary key for the new login log
	* @return the new login log
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog createLoginLog(long id) {
		return _loginLogLocalService.createLoginLog(id);
	}

	/**
	* Deletes the login log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the login log
	* @return the login log that was removed
	* @throws PortalException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog deleteLoginLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.deleteLoginLog(id);
	}

	/**
	* Deletes the login log from the database. Also notifies the appropriate model listeners.
	*
	* @param loginLog the login log
	* @return the login log that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog deleteLoginLog(
		org.kisti.edison.login.model.LoginLog loginLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.deleteLoginLog(loginLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loginLogLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.login.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.login.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.login.model.LoginLog fetchLoginLog(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.fetchLoginLog(id);
	}

	/**
	* Returns the login log with the primary key.
	*
	* @param id the primary key of the login log
	* @return the login log
	* @throws PortalException if a login log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog getLoginLog(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.getLoginLog(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<org.kisti.edison.login.model.LoginLog> getLoginLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.getLoginLogs(start, end);
	}

	/**
	* Returns the number of login logs.
	*
	* @return the number of login logs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLoginLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.getLoginLogsCount();
	}

	/**
	* Updates the login log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loginLog the login log
	* @return the login log that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.login.model.LoginLog updateLoginLog(
		org.kisti.edison.login.model.LoginLog loginLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _loginLogLocalService.updateLoginLog(loginLog);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _loginLogLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_loginLogLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _loginLogLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LoginLogLocalService getWrappedLoginLogLocalService() {
		return _loginLogLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLoginLogLocalService(
		LoginLogLocalService loginLogLocalService) {
		_loginLogLocalService = loginLogLocalService;
	}

	@Override
	public LoginLogLocalService getWrappedService() {
		return _loginLogLocalService;
	}

	@Override
	public void setWrappedService(LoginLogLocalService loginLogLocalService) {
		_loginLogLocalService = loginLogLocalService;
	}

	private LoginLogLocalService _loginLogLocalService;
}