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

package org.kisti.edison.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.kisti.edison.model.WorkflowSimulation;
import org.kisti.edison.model.WorkflowSimulationJob;
import org.kisti.edison.service.base.WorkflowSimulationLocalServiceBaseImpl;
import org.kisti.edison.util.CustomUtil;
import org.springframework.util.StringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;

/**
 * The implementation of the workflow simulation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.service.WorkflowSimulationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.service.base.WorkflowSimulationLocalServiceBaseImpl
 * @see org.kisti.edison.service.WorkflowSimulationLocalServiceUtil
 */
public class WorkflowSimulationLocalServiceImpl extends WorkflowSimulationLocalServiceBaseImpl{
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link
     * org.kisti.edison.service.WorkflowSimulationLocalServiceUtil} to access
     * the workflow simulation local service.
     */

    public List<WorkflowSimulation> getWorkflowSimulations(String title, long userId, int begin, int end) throws SystemException {
        if(StringUtils.hasText(title)) {
            return workflowSimulationPersistence.findByTitle_UserId("%" + title + "%", userId, begin, end);    
        }
        return workflowSimulationPersistence.findByUserId(userId, begin, end);
    }
    
    public WorkflowSimulation createWorkflowSimulation() throws SystemException{
        long simulationId = super.counterLocalService.increment();
        return workflowSimulationLocalService.createWorkflowSimulation(simulationId);
    }
    
    public WorkflowSimulation createWorkflowSimulation(Map<String, Object> params, User user) throws SystemException{
        String tetsYnStr = CustomUtil.strNull(params.get("testYn"), "true");
        boolean testYn = tetsYnStr.equals("true") || tetsYnStr.equals("y") || tetsYnStr.equals("Y"); 
        WorkflowSimulation simulation = createWorkflowSimulation();
        simulation.setTitle(CustomUtil.strNull(params.get("title"), "workflow simulation #" + simulation.getSimulationId()));
        simulation.setWorkflowId(GetterUtil.getLong(params.get("workflowId")));
        simulation.setUserId(user.getUserId());
        simulation.setTestYn(testYn);
        simulation.setCreateDate(new Date());
        return workflowSimulationLocalService.addWorkflowSimulation(simulation);
    }
    
    public WorkflowSimulation updateWorkflowSimulation(long simulationId, Map<String, Object> params, User user) 
        throws SystemException, PortalException{
        WorkflowSimulation simulation = this.getWorkflowSimulation(simulationId);  
        String tetsYnStr = CustomUtil.strNull(params.get("testYn"), "true");
        boolean testYn = tetsYnStr.equals("true") || tetsYnStr.equals("y") || tetsYnStr.equals("Y"); 
        simulation.setTitle(CustomUtil.strNull(params.get("title"), "workflow simulation #" + simulation.getSimulationId()));
        simulation.setTestYn(testYn);
        simulation.setModifiedDate(new Date());
        return workflowSimulationLocalService.updateWorkflowSimulation(simulation);
    }
}