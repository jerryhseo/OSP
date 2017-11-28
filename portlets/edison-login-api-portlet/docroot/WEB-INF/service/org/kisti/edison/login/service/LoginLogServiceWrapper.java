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
 * Provides a wrapper for {@link LoginLogService}.
 *
 * @author yjpark
 * @see LoginLogService
 * @generated
 */
public class LoginLogServiceWrapper implements LoginLogService,
	ServiceWrapper<LoginLogService> {
	public LoginLogServiceWrapper(LoginLogService loginLogService) {
		_loginLogService = loginLogService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _loginLogService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_loginLogService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _loginLogService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject loginInfo(
		java.lang.String screenName, java.lang.String password) {
		return _loginLogService.loginInfo(screenName, password);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LoginLogService getWrappedLoginLogService() {
		return _loginLogService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLoginLogService(LoginLogService loginLogService) {
		_loginLogService = loginLogService;
	}

	@Override
	public LoginLogService getWrappedService() {
		return _loginLogService;
	}

	@Override
	public void setWrappedService(LoginLogService loginLogService) {
		_loginLogService = loginLogService;
	}

	private LoginLogService _loginLogService;
}