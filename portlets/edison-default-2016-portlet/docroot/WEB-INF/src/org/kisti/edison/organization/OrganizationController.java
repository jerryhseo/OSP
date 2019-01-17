package org.kisti.edison.organization;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.PagingUtil;
import org.kisti.edison.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoRowLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("VIEW")
public class OrganizationController{
	
	private static Log _log = LogFactoryUtil.getLog(OrganizationController.class);

	@RequestMapping // default
	public String view(RenderRequest request, RenderResponse response, ModelMap model){
		return "organManager/organ_list";
	}
	
	@ResourceMapping(value="getOrganizationList")
	public void getOrganizationList(ResourceRequest request, ResourceResponse response){
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
		Map params = RequestUtil.getParameterMap(request);
		
		try {
			User user = themeDisplay.getUser();
			
			long nextCd = 0l;
			String upCode = "1501";		//기관 upCode
			List<Map<String,String>> sendOrganList = new ArrayList<Map<String,String>>(); 
			List<Map<String,String>> organList = EdisonExpndoUtil.getCodeListByUpCode(upCode, themeDisplay.getLocale());


			int curPage = Integer.parseInt(CustomUtil.strNull(params.get("curPage"), "1"));
			int linePerPage = Integer.parseInt(CustomUtil.strNull(params.get("viewCnt"), "10"));
			int pagePerBlock = 5;
			int begin = (curPage - 1) * linePerPage;
			int end = begin+linePerPage;
			end = end < organList.size() ? end : organList.size(); 
			
			String pagingStr = PagingUtil.getPaging(request.getContextPath(), response.getNamespace()+"searchDataTypeList", organList.size(), curPage, linePerPage, pagePerBlock);
			
			for(int i=begin; i<end; i++){
				Map<String, String> organization = organList.get(i);
				
				String region = organization.get("region");
				if(!region.equals("")){
					String regionNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(region, EdisonExpando.CDNM, themeDisplay.getLocale());
					organization.put("regionNm", regionNm);
				}
				long cd = Long.parseLong(organization.get("cd"));
				sendOrganList.add(organization);
			}
			
			for(Map<String, String> organ : organList){
				long organCd = Long.parseLong(organ.get("cd"));
				if(organCd < 1501999){
					nextCd = (long) (nextCd < organCd ? organCd : nextCd);
				}
			}
			
			String upCodeRegion = "1601";		// 지역
			List<Map<String,String>> regionList = EdisonExpndoUtil.getCodeListByUpCode(upCodeRegion, themeDisplay.getLocale());
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("organList", sendOrganList);
			jsonObj.put("regionList", regionList);
			jsonObj.put("pagingStr", pagingStr);
			jsonObj.put("nextCd", nextCd+1);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="getOrganizationDetailInfo")
	public void getOrganizationDetailInfo(ResourceRequest request, ResourceResponse response){
		try {
			
			Long organCd = Long.parseLong(CustomUtil.strNull(request.getParameter("organCd"), "0"));
			
			long cdNmColumnId = ExpandoColumnLocalServiceUtil.getColumn(79401, "cdNm").getColumnId();
			long regionColumnId = ExpandoColumnLocalServiceUtil.getColumn(79401, "region").getColumnId();
			
			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(79401, cdNmColumnId, organCd);
			ExpandoValue expandoRegionValue = ExpandoValueLocalServiceUtil.getValue(79401, regionColumnId, organCd);
			
			Map<String, Object> expandoValueMap = new HashMap<String, Object>();
			expandoValueMap.put("valueId", expandoValue.getValueId());
			expandoValueMap.put("companyId", expandoValue.getCompanyId());
			expandoValueMap.put("tableId", expandoValue.getTableId());
			expandoValueMap.put("columnId", expandoValue.getColumnId());
			expandoValueMap.put("rowId", expandoValue.getRowId());
			expandoValueMap.put("classNameId", expandoValue.getClassNameId());
			expandoValueMap.put("data", expandoValue.getData());
			
			Map<String, Object> expandoRegionValueMap = new HashMap<String, Object>();
			expandoRegionValueMap.put("valueId", expandoRegionValue.getValueId());
			expandoRegionValueMap.put("columnId", expandoRegionValue.getColumnId());
			expandoRegionValueMap.put("data", expandoRegionValue.getData());
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("expandoValueMap", expandoValueMap);
			jsonObj.put("expandoRegionValueMap", expandoRegionValueMap);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="createOrganization")
	public void createOrganization(ResourceRequest request, ResourceResponse response){
		try {
			Map params = RequestUtil.getParameterMap(request);
			
			// tableId = 79401		companyId = 20154		classNameId = 20107
			// columnId = 7653530(기관 명), 7653531(지역), 7653532(option1), 7653533(option2), 7653534(option3)
			// 실서버 columnId = 13812115(기관 명), 13812116(지역), 13812117(option1), 13812118(option2), 13812119(option3)
			
			long tableId = 79401l;
			
			long cdNmColumnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, "cdNm").getColumnId();
			long regionColumnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, "region").getColumnId();
			long option1ColumnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, "option1").getColumnId();
			long option2ColumnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, "option2").getColumnId();
			long option3ColumnId = ExpandoColumnLocalServiceUtil.getColumn(tableId, "option3").getColumnId();
			
			long companyId = 20154l;
			long classNameId = 20107l;
			long classPk = Long.parseLong(CustomUtil.strNull(params.get("nextCd"), "0"));
			
			String organRegion = CustomUtil.strNull(params.get("organRegionNm"));
			String orgnaCdNm_ko_KR = CustomUtil.strNull(params.get("organCdNm_ko_KR"), "");
			String orgnaCdNm_en_US = CustomUtil.strNull(params.get("organCdNm_en_US"), "");
			
			// 기관명 XML 데이터
			String data = "<?xml version='1.0' encoding='UTF-8'?><root available-locales=\"en_US,ko_KR,\" default-locale=\"en_US\"><Data language-id=\"en_US\">"
							+ orgnaCdNm_en_US + "</Data><Data language-id=\"ko_KR\">"
							+ orgnaCdNm_ko_KR + "</Data></root>";
			
			// Add ExpandoValue
			ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, cdNmColumnId, classPk, data);									// 기관명
			ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, regionColumnId, classPk, CustomUtil.strNull(organRegion));		// 지역
			ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, option1ColumnId, classPk, "");									// Option 1
			ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, option2ColumnId, classPk, "");									// Option 2
			ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, option3ColumnId, classPk, "");									// Option 3
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="updateOrganization")
	public void updateOrganization(ResourceRequest request, ResourceResponse response){
		try {
			Map params = RequestUtil.getParameterMap(request);
			
			long tableId = Long.parseLong(CustomUtil.strNull(params.get("tableId"), "0"));
			long columnId = Long.parseLong(CustomUtil.strNull(params.get("columnId"), "0"));
			long rowId = Long.parseLong(CustomUtil.strNull(params.get("rowId"), "0"));
			long companyId = Long.parseLong(CustomUtil.strNull(params.get("companyId"), "0"));
			long valueId = Long.parseLong(CustomUtil.strNull(params.get("valueId"), "0"));
			long classNameId = Long.parseLong(CustomUtil.strNull(params.get("classNameId"), "0"));
			long classPk = Long.parseLong(CustomUtil.strNull(params.get("classPk"), "0"));
			String organRegionNm = CustomUtil.strNull(params.get("organRegionNm"));
			long regionColumnId = Long.parseLong(CustomUtil.strNull(params.get("regionColumnId"), "0"));
			long regionValueId = Long.parseLong(CustomUtil.strNull(params.get("regionValueId")));
			
			String orgnaCdNm_ko_KR = CustomUtil.strNull(params.get("organCdNm_ko_KR"), "");
			String orgnaCdNm_en_US = CustomUtil.strNull(params.get("organCdNm_en_US"), "");
			
			if(orgnaCdNm_ko_KR.equals("")){
				orgnaCdNm_ko_KR = orgnaCdNm_en_US;
			}
			
			// 기관명 XML 데이터
			String data = "<?xml version='1.0' encoding='UTF-8'?><root available-locales=\"en_US,ko_KR,\" default-locale=\"en_US\"><Data language-id=\"en_US\">"
							+ orgnaCdNm_en_US + "</Data><Data language-id=\"ko_KR\">"
							+ orgnaCdNm_ko_KR + "</Data></root>";
			
			// 기관명 수정
			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getExpandoValue(valueId);
			if(expandoValue != null){
				expandoValue.setTableId(tableId);
				expandoValue.setColumnId(columnId);
				expandoValue.setRowId(rowId);
				expandoValue.setCompanyId(companyId);
				expandoValue.setValueId(valueId);
				expandoValue.setClassNameId(classNameId);
				expandoValue.setClassPK(classPk);
				expandoValue.setData(data);
				
				ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);
			}
			
			// 지역 수정
			ExpandoValue expandoRegionValue = ExpandoValueLocalServiceUtil.getExpandoValue(regionValueId);
			if(expandoRegionValue != null){
				expandoRegionValue.setTableId(tableId);
				expandoRegionValue.setColumnId(regionColumnId);
				expandoRegionValue.setRowId(rowId);
				expandoRegionValue.setCompanyId(companyId);
				expandoRegionValue.setValueId(regionValueId);
				expandoRegionValue.setClassNameId(classNameId);
				expandoRegionValue.setClassPK(classPk);
				expandoRegionValue.setData(organRegionNm);
				
				ExpandoValueLocalServiceUtil.updateExpandoValue(expandoRegionValue);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResourceMapping(value="deleteOrganization")
	public void deleteOrganization(ResourceRequest request, ResourceResponse response){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
			Map params = RequestUtil.getParameterMap(request);
			
			long valueId = Long.parseLong(CustomUtil.strNull(params.get("valueId"), "0"));
			long classPk = Long.parseLong(CustomUtil.strNull(params.get("classPk"), "0"));
			
			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getExpandoValue(valueId);
			long rowId = expandoValue.getRowId();
			
			// 기관코드를 사용하는 유저 Count		tableId : 23206, columnId : 50187
			//int useCount = ExpandoValueUtil.countByT_C_D(23206, 50187, classPk+"");
			int useCount = ExpandoValueLocalServiceUtil.getColumnValuesCount(20154, 20005, ExpandoTableLocalServiceUtil.getExpandoTable(23206).getName(), ExpandoColumnLocalServiceUtil.getColumn(50187).getName(), classPk+"");
			
			// 삭제할 기관과 관련된 ExpandoValue 삭제
			boolean deleteSuccess = false;
			if(useCount==0 && expandoValue!=null){
				for(int i=0; i<5; i++){
					expandoValue.setValueId(valueId+i);
					expandoValue.setClassPK(classPk);
					
					// ExpandoColumn 삭제
					ExpandoValueLocalServiceUtil.deleteExpandoValue(expandoValue);
				}
				// ExpandoRow 삭제 
				ExpandoRowLocalServiceUtil.deleteExpandoRow(rowId);
				
				deleteSuccess = true;
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("deleteSuccess", deleteSuccess);
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonObj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
