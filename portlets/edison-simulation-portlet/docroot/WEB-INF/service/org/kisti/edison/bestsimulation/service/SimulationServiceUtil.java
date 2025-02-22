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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for Simulation. This utility wraps
 * {@link org.kisti.edison.bestsimulation.service.impl.SimulationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author EDISON
 * @see SimulationService
 * @see org.kisti.edison.bestsimulation.service.base.SimulationServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.impl.SimulationServiceImpl
 * @generated
 */
public class SimulationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.bestsimulation.service.impl.SimulationServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.portal.kernel.json.JSONObject addSimulationWithJob(
		long userId, java.lang.String appName, java.lang.String appVersion,
		java.lang.String simulationTitle, java.lang.String jobData) {
		return getService()
				   .addSimulationWithJob(userId, appName, appVersion,
			simulationTitle, jobData);
	}

	public static com.liferay.portal.kernel.json.JSONObject getSimulationJob(
		long userId, java.lang.String appName, java.lang.String appVersion,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		java.lang.String jobData) {
		return getService()
				   .getSimulationJob(userId, appName, appVersion,
			simulationUuid, jobUuid, jobData);
	}

	public static void clearService() {
		_service = null;
	}

	public static SimulationService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SimulationService.class.getName());

			if (invokableService instanceof SimulationService) {
				_service = (SimulationService)invokableService;
			}
			else {
				_service = new SimulationServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SimulationServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SimulationService service) {
	}

	private static SimulationService _service;
}