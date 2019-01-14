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

package com.kisti.osp.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SystemPropertiesService}.
 *
 * @author Jerry h. Seo
 * @see SystemPropertiesService
 * @generated
 */
public class SystemPropertiesServiceWrapper implements SystemPropertiesService,
	ServiceWrapper<SystemPropertiesService> {
	public SystemPropertiesServiceWrapper(
		SystemPropertiesService systemPropertiesService) {
		_systemPropertiesService = systemPropertiesService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _systemPropertiesService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_systemPropertiesService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _systemPropertiesService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SystemPropertiesService getWrappedSystemPropertiesService() {
		return _systemPropertiesService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSystemPropertiesService(
		SystemPropertiesService systemPropertiesService) {
		_systemPropertiesService = systemPropertiesService;
	}

	@Override
	public SystemPropertiesService getWrappedService() {
		return _systemPropertiesService;
	}

	@Override
	public void setWrappedService(
		SystemPropertiesService systemPropertiesService) {
		_systemPropertiesService = systemPropertiesService;
	}

	private SystemPropertiesService _systemPropertiesService;
}