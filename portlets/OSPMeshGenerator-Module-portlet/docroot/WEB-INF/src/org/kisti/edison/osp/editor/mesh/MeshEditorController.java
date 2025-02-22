package org.kisti.edison.osp.editor.mesh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.model.EdisonMessageConstants;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.model.DataTypeStructure;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.kisti.osp.icecap.service.DataTypeStructureLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("VIEW")
public class MeshEditorController {
	private static Log log = LogFactory.getLog(MeshEditorController.class);
	
	
	@RequestMapping//default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try{
			List<String[]> dataStructurAppList = new ArrayList<String[]>();;
			dataStructurAppList.add(new String[]{"KFOIL_AirFoil_Para_parin", "1.0.1", "parametric"});
			dataStructurAppList.add(new String[]{"KGRID", "1.0.0", "meshparametric"});
			
			for(String[] appList : dataStructurAppList){
				String appName = appList[0];
				String appVersion = appList[1];
				String parameterName = appList[2];
				
				ScienceApp scienceAppParam = ScienceAppLocalServiceUtil.getScienceApp(appName, appVersion);
				String paramInputPorts = ScienceAppLocalServiceUtil.getScienceAppInputPorts(scienceAppParam.getScienceAppId());
				
				long paramTypeId = 0;
				JSONObject paramJSON = (JSONObject) JSONSerializer.toJSON(paramInputPorts);
				Map paramJsonMap = paramJSON;
				Iterator paramItr = paramJsonMap.keySet().iterator();
				while(paramItr.hasNext()) {
					String key = (String) paramItr.next();
					Map paramMap = (Map)paramJsonMap.get(key);
					Map dataTypeMap = (Map) paramMap.get("dataType_");
					String dataTypeName = (String) dataTypeMap.get("name");
					String dataTypeVersion = (String) dataTypeMap.get("version");
					DataType paramDataType = DataTypeLocalServiceUtil.findDataTypeObject(dataTypeName, dataTypeVersion);
					paramTypeId = paramDataType.getTypeId();
					break;
				}
				DataTypeStructure paramStructure = DataTypeStructureLocalServiceUtil.getDataTypeStructure(paramTypeId);
				model.addAttribute(parameterName, paramStructure.getStructure());
			}
			model.addAttribute("currentUserName", themeDisplay.getUser().getScreenName());
		}catch(Exception e){
			e.printStackTrace();
			SessionErrors.add(request, EdisonMessageConstants.SEARCH_ERROR);
		}
		
		model.addAttribute("type", "MESH");
		return "view";
	}
}
