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
 * Provides a wrapper for {@link VirtualLabScienceAppLinkClassService}.
 *
 * @author EDISON
 * @see VirtualLabScienceAppLinkClassService
 * @generated
 */
public class VirtualLabScienceAppLinkClassServiceWrapper
	implements VirtualLabScienceAppLinkClassService,
		ServiceWrapper<VirtualLabScienceAppLinkClassService> {
	public VirtualLabScienceAppLinkClassServiceWrapper(
		VirtualLabScienceAppLinkClassService virtualLabScienceAppLinkClassService) {
		_virtualLabScienceAppLinkClassService = virtualLabScienceAppLinkClassService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _virtualLabScienceAppLinkClassService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_virtualLabScienceAppLinkClassService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _virtualLabScienceAppLinkClassService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public VirtualLabScienceAppLinkClassService getWrappedVirtualLabScienceAppLinkClassService() {
		return _virtualLabScienceAppLinkClassService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedVirtualLabScienceAppLinkClassService(
		VirtualLabScienceAppLinkClassService virtualLabScienceAppLinkClassService) {
		_virtualLabScienceAppLinkClassService = virtualLabScienceAppLinkClassService;
	}

	@Override
	public VirtualLabScienceAppLinkClassService getWrappedService() {
		return _virtualLabScienceAppLinkClassService;
	}

	@Override
	public void setWrappedService(
		VirtualLabScienceAppLinkClassService virtualLabScienceAppLinkClassService) {
		_virtualLabScienceAppLinkClassService = virtualLabScienceAppLinkClassService;
	}

	private VirtualLabScienceAppLinkClassService _virtualLabScienceAppLinkClassService;
}