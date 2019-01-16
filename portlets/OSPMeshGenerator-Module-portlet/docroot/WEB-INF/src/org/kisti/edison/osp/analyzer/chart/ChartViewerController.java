package org.kisti.edison.osp.analyzer.chart;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class ChartViewerController {
	private static Log log = LogFactory.getLog(ChartViewerController.class);
	
	@RequestMapping
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		return "wrapper-view";
	}
	
	@ResourceMapping(value = "loadFile")
	public void loadFile(
		@RequestParam(required=true, value = "filePath") String targetFilePath,
		ResourceRequest request, ResourceResponse response, ModelMap model) throws SystemException{
		log.info("loadFile path : " + targetFilePath);
		
		try{
			Path filePath = Paths.get(targetFilePath);
			String result = OSPFileLocalServiceUtil.readTextFile(filePath);
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		}catch (Exception e) {
			String msg = ChartViewerController.class.getName() + ".loadFile error";
			handleRuntimeException(e, PortalUtil.getHttpServletResponse(response), msg);
		}
	}
	
	private static void handleRuntimeException(Exception ex, HttpServletResponse response, String message)
	        throws SystemException{
	        try{
	            log.error(message, ex);
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write(message);
	            response.flushBuffer();
	        }catch (IOException e){
	            throw new SystemException(e);
	        }
	    }
	
}
