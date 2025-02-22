/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.kisti.osp.workbench.Exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class SimulationWorkbenchException extends PortalException {
	
	public static final int NO_SCIENCEAPP_ID = 1;
	
	public static final int NO_SCIENCEAPP_LAYOUT_EXIST = 2;
	
	public static final int SCIENCEAPP_PORT_SEARCH_EXCEPTION = 3;
	
	public SimulationWorkbenchException() {
		super();
	}

	public SimulationWorkbenchException(int type) {
		_type = type;
	}
	
	public int getType() {
		return _type;
	}

	private int _type;

	public SimulationWorkbenchException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SimulationWorkbenchException(Throwable cause) {
		super(cause);
	}

}