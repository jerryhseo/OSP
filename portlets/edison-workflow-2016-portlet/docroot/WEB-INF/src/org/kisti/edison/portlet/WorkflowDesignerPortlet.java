package org.kisti.edison.portlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("VIEW")
public class WorkflowDesignerPortlet{

	private static Log log = LogFactory.getLog(WorkflowDesignerPortlet.class);

  @RequestMapping
  public String view(RenderRequest request, RenderResponse response, ModelMap model)
      throws SystemException, IOException, PortalException, SQLException, ParseException{
    log.debug("view rendering");
    return "designer/view";
  }

}
