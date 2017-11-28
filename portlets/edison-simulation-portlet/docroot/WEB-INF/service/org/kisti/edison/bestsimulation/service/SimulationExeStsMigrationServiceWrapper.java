/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SimulationExeStsMigrationService}.
 *
 * @author EDISON
 * @see SimulationExeStsMigrationService
 * @generated
 */
public class SimulationExeStsMigrationServiceWrapper
	implements SimulationExeStsMigrationService,
		ServiceWrapper<SimulationExeStsMigrationService> {
	public SimulationExeStsMigrationServiceWrapper(
		SimulationExeStsMigrationService simulationExeStsMigrationService) {
		_simulationExeStsMigrationService = simulationExeStsMigrationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationExeStsMigrationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationExeStsMigrationService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationExeStsMigrationService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationExeStsMigrationService getWrappedSimulationExeStsMigrationService() {
		return _simulationExeStsMigrationService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationExeStsMigrationService(
		SimulationExeStsMigrationService simulationExeStsMigrationService) {
		_simulationExeStsMigrationService = simulationExeStsMigrationService;
	}

	@Override
	public SimulationExeStsMigrationService getWrappedService() {
		return _simulationExeStsMigrationService;
	}

	@Override
	public void setWrappedService(
		SimulationExeStsMigrationService simulationExeStsMigrationService) {
		_simulationExeStsMigrationService = simulationExeStsMigrationService;
	}

	private SimulationExeStsMigrationService _simulationExeStsMigrationService;
}