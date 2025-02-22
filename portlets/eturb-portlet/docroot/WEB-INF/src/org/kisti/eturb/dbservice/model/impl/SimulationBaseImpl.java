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

package org.kisti.eturb.dbservice.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import org.kisti.eturb.dbservice.model.Simulation;
import org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil;

/**
 * The extended model base implementation for the Simulation service. Represents a row in the &quot;ETURB_Simulation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SimulationImpl}.
 * </p>
 *
 * @author EDISON
 * @see SimulationImpl
 * @see org.kisti.eturb.dbservice.model.Simulation
 * @generated
 */
public abstract class SimulationBaseImpl extends SimulationModelImpl
	implements Simulation {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a simulation model instance should use the {@link Simulation} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SimulationLocalServiceUtil.addSimulation(this);
		}
		else {
			SimulationLocalServiceUtil.updateSimulation(this);
		}
	}
}