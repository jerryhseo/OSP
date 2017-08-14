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

package org.kisti.edison.virtuallaboratory.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VirtualLabScienceAppLinkService}.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkService
 * @generated
 */
public class VirtualLabScienceAppLinkServiceWrapper
	implements VirtualLabScienceAppLinkService,
		ServiceWrapper<VirtualLabScienceAppLinkService> {
	public VirtualLabScienceAppLinkServiceWrapper(
		VirtualLabScienceAppLinkService virtualLabScienceAppLinkService) {
		_virtualLabScienceAppLinkService = virtualLabScienceAppLinkService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabScienceAppLinkService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabScienceAppLinkService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabScienceAppLinkService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabScienceAppLinkService getWrappedVirtualLabScienceAppLinkService() {
		return _virtualLabScienceAppLinkService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabScienceAppLinkService(
		VirtualLabScienceAppLinkService virtualLabScienceAppLinkService) {
		_virtualLabScienceAppLinkService = virtualLabScienceAppLinkService;
	}

	@Override
	public VirtualLabScienceAppLinkService getWrappedService() {
		return _virtualLabScienceAppLinkService;
	}

	@Override
	public void setWrappedService(
		VirtualLabScienceAppLinkService virtualLabScienceAppLinkService) {
		_virtualLabScienceAppLinkService = virtualLabScienceAppLinkService;
	}

	private VirtualLabScienceAppLinkService _virtualLabScienceAppLinkService;
}