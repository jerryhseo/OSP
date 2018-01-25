package com.kisti.osp.workbench.portlet.simulation.layout;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.kisti.osp.workbench.Exception.SimulationWorkbenchException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import freemarker.template.Configuration;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("VIEW")
public class LayoutController {
	protected static Log  _log = LogFactoryUtil.getLog(LayoutController.class);
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		String workbenchType = ParamUtil.getString(request, "workbenchType", "SIMULATION_WITH_APP");
		
		try{
			model = this.evaluateScienceAppLayout(model, 84702);
			model.addAttribute("workbenchType", workbenchType);
			return "view";
		}catch(Exception e){
			if(e instanceof SimulationWorkbenchException){
				SessionErrors.add(request, e.getClass(), e);
			}else{
				_log.error(e);
				e.printStackTrace();
				SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
			}
			return "error";
		}
	}
	
	
	@ResourceMapping(value="serveResource")
	public void serveResource(ResourceRequest request, ResourceResponse response) throws Exception{
		String command = ParamUtil.getString(request, "command");
		if( command.equalsIgnoreCase("RESOLVE_TEMPLATE")){
			this.resolveLayoutTemplate(request, response);
		}
	}
	
	protected void resolveLayoutTemplate(  ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException{
		String templateDir = ParamUtil.getString(resourceRequest, "templateDir");
		String templateFile = ParamUtil.getString(resourceRequest, "templateFile");
		String namespace = ParamUtil.getString(resourceRequest, "namespace");
		
		String realTemplateDir = resourceRequest.getPortletSession().getPortletContext().getRealPath(templateDir);
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(realTemplateDir));
		cfg.setDefaultEncoding("UTF-8");
		
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		
		Map<String, Object> params = new HashMap<>();
		params.put("namespace", namespace);
		JSONArray columns = null;
		try {
			columns = JSONFactoryUtil.createJSONArray(ParamUtil.getString(resourceRequest, "columns"));
		} catch (JSONException e1) {
			_log.error("Getting columns while resolving layout template");
			throw new PortletException();
		}
		try {
			params.put("columns", this.layoutJSONObject2Map(columns));
		} catch (JSONException e1) {
			_log.error("Getting columns while creating freemarker parameters");
			throw new PortletException();
		}
		
		Template template = cfg.getTemplate(templateFile);
		PrintWriter writer = resourceResponse.getWriter();
		try {
			template.process(params, writer);
		} catch (TemplateException e) {
			_log.error("[ERROR] template.process()");
			throw new PortletException();
		} finally{
			writer.close();
		}
		
	}
	
	protected SimpleSequence layoutJSONObject2Map( JSONArray jsonColumns ) throws JSONException{
		SimpleSequence columns = new SimpleSequence();
		
		for( int i=0; i<jsonColumns.length(); i++){
			JSONObject jsonColumn = jsonColumns.getJSONObject(i);
			Map<String, String> column = new HashMap<>();
			column.put("id", jsonColumn.getString("id"));
			column.put("portletId", jsonColumn.getString("portletId"));
			columns.add( column );
		}
		_log.info(columns.toString());
		
		return columns;
	}

	protected ModelMap evaluateScienceAppLayout(ModelMap model,long scienceAppId ) throws SimulationWorkbenchException{
		ScienceApp scienceApp = null;
		try{
			scienceApp = ScienceAppLocalServiceUtil.getScienceApp(scienceAppId);
		}catch (Exception e) {
			throw new SimulationWorkbenchException(SimulationWorkbenchException.NO_SCIENCEAPP_ID);
		}
		
		try{
			JSONObject workbenchLayout = JSONFactoryUtil.createJSONObject(scienceApp.getLayout());
			model.addAttribute("workbenchLayout", workbenchLayout);
		}catch (Exception e) {
			throw new SimulationWorkbenchException(SimulationWorkbenchException.NO_SCIENCEAPP_LAYOUT_EXIST);
		}
		
		return model;
	}
}
