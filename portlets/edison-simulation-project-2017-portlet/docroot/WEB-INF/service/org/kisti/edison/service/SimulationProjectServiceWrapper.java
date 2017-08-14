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

package org.kisti.edison.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SimulationProjectService}.
 *
 * @author edison
 * @see SimulationProjectService
 * @generated
 */
public class SimulationProjectServiceWrapper implements SimulationProjectService,
	ServiceWrapper<SimulationProjectService> {
	public SimulationProjectServiceWrapper(
		SimulationProjectService simulationProjectService) {
		_simulationProjectService = simulationProjectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationProjectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationProjectService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationProjectService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationProjectService getWrappedSimulationProjectService() {
		return _simulationProjectService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationProjectService(
		SimulationProjectService simulationProjectService) {
		_simulationProjectService = simulationProjectService;
	}

	@Override
	public SimulationProjectService getWrappedService() {
		return _simulationProjectService;
	}

	@Override
	public void setWrappedService(
		SimulationProjectService simulationProjectService) {
		_simulationProjectService = simulationProjectService;
	}

	private SimulationProjectService _simulationProjectService;
}