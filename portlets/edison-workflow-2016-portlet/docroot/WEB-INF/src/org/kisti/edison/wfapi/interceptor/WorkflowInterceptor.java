package org.kisti.edison.wfapi.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kisti.edison.wfapi.custom.exception.EdisonWorkflowError;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class WorkflowInterceptor extends HandlerInterceptorAdapter{
    
    private Log log = LogFactoryUtil.getLog(WorkflowInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
        throws Exception{
        log.info("prehandle");
        User user = PortalUtil.getUser(request);
        if(user == null && !request.getRequestURI().endsWith("error")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delegate/services/workflows/error");
            request.setAttribute("error", EdisonWorkflowError.REQ_LOGIN);
            requestDispatcher.forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
