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

package org.kisti.edison.model.impl;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The extended model implementation for the WorkflowSimulationJob service. Represents a row in the &quot;EDWF_WorkflowSimulationJob&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.kisti.edison.model.WorkflowSimulationJob} interface.
 * </p>
 *
 * @author EDISON
 */
@JsonIgnoreProperties(ignoreUnknown = true, 
value = {"expandoBridge", "expandoBridgeAttributes", "cachedModel",
    "escapedModel", "modelAttributes", "modelClass", "modelClassName", "new", "primaryKey", "primaryKeyObj"})
public class WorkflowSimulationJobImpl extends WorkflowSimulationJobBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a workflow simulation job model instance should use the {@link org.kisti.edison.model.WorkflowSimulationJob} interface instead.
	 */
	public WorkflowSimulationJobImpl() {
	}
}