package org.kisti.edison.portlet.login.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpSession;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.util.PortalUtil;

public class CreateAccountAction extends BaseStrutsPortletAction{
	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		originalStrutsPortletAction.processAction(portletConfig, actionRequest, actionResponse);
	}

	
	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {
	    
	    HttpSession session = PortalUtil.getHttpServletRequest(renderRequest).getSession();
	    
	    renderRequest.setAttribute("saml_email", CustomUtil.strNull(session.getAttribute("saml:email")));
	    renderRequest.setAttribute("saml_eppn", CustomUtil.strNull(session.getAttribute("saml:eppn")));
	    renderRequest.setAttribute("saml_firstName", CustomUtil.strNull(session.getAttribute("saml:firstName")));
	    
		renderRequest.setAttribute("EdisonExpando_USER_UNIVERSITY", CustomUtil.strNull(EdisonExpando.USER_UNIVERSITY));
		renderRequest.setAttribute("EdisonExpando_USER_MAJOR", CustomUtil.strNull(EdisonExpando.USER_MAJOR));
		
		return originalStrutsPortletAction.render(portletConfig, renderRequest, renderResponse);
	}
	
	
}
