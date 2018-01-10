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

package org.kisti.edison.bestsimulation.service.base;

import org.kisti.edison.bestsimulation.service.SimulationJobServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class SimulationJobServiceClpInvoker {
	public SimulationJobServiceClpInvoker() {
		_methodName68 = "getBeanIdentifier";

		_methodParameterTypes68 = new String[] {  };

		_methodName69 = "setBeanIdentifier";

		_methodParameterTypes69 = new String[] { "java.lang.String" };

		_methodName74 = "updateSimulationJob";

		_methodParameterTypes74 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SimulationJobServiceUtil.getBeanIdentifier();
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			SimulationJobServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return SimulationJobServiceUtil.updateSimulationJob(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName74;
	private String[] _methodParameterTypes74;
}