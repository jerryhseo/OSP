package org.kisti.edison.science.customService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kisti.edison.science.portlet.scienceAppProLink.LinktCustomModelConstants;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

public class AssetEntryCustomService{
	/**
	 * 검색어 입력해서 나온 모델의 리스트 
	 * 공개된 모델에 대해서 리스트 조회
	 * @param className
	 * @param locale
	 * @param searchText
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Map<String, Object>> retrieveListCustomModel(String className, Locale locale,
		String searchText, int start, int end){
		List<Map<String, Object>> searchTextList = new ArrayList<Map<String, Object>>();

		try{

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("languageId", locale.toString());
			params.put("searchText", searchText);
			params.put("begin", start);
			params.put("end", end);

			// 목록조회
			if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
				
				List<Map<String, Object>> projectSearchList = new ArrayList<Map<String, Object>>();
				projectSearchList = SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectList(start, end, searchText, locale, null);

				for(Map<String, Object> projectMap : projectSearchList){
					Map<String, Object> projectNewMap = new HashMap<String, Object>();
					projectNewMap.put("modelSeq", projectMap.get("simulationProjectId"));
					projectNewMap.put("title", projectMap.get("title"));
					projectNewMap.put("description", projectMap.get("explain"));
					
					searchTextList.add(projectNewMap);
				}
			}
			/*else if(className.equals(AssetCustomModelConstants.COMMUNITY)){}*/

		}catch (Exception e){
			e.printStackTrace();
		}

		return searchTextList;
	}

	/**
	 * 사이트에서 조회시엔 페이징있어야하며, 페이지에서 조회시 페이징이 없어야함.
	 * entryId에 대해 링크된 모델리스트 조회
	 * @param className
	 * @param languageId
	 * @param entryId
	 * @param pagingYn
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Map<String, Object>> retrieveListLinkedAssetEntry(String className, Locale locale, long entryId, boolean pagingYn, int start, int end){
		try{

			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

			className = CustomUtil.strNull(className, LinktCustomModelConstants.SIMULATION_PROJECT);

			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setLinkedAssetEntryId(entryId);
			assetEntryQuery.setClassName(className); // entryId의 className
			
			List<AssetEntry> linkEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
			
			List<Long> classPKList = new ArrayList<Long>();
			Object[] object = new Object[linkEntryList.size()];
			int index = 0;
			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK // 리스트 작성
				object[index++] = entry.getClassPK();
			}

			if(linkEntryList != null && classPKList.size() > 0){
				Map params = new HashMap();
				params.put("className", className);
				params.put("languageId", locale.toString());
				String modelSeqList = StringUtils.join(classPKList, ",");
				params.put("modelSeqList", modelSeqList);

				if(pagingYn){
					params.put("begin", start);
					params.put("end", end);
				}
				
				if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
					List<Map<String, Object>> projectLinkList = new ArrayList<Map<String, Object>>();
					
					if(pagingYn){
						projectLinkList = SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectList(start, end, null,classPKList,locale);
					}else{
						projectLinkList = SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectList(null, classPKList,locale);
					}

					for(Map<String, Object> projectMap : projectLinkList){
						Map<String, Object> projectNewMap = new HashMap<String, Object>();
						projectNewMap.put("modelSeq", projectMap.get("simulationProjectId"));
						projectNewMap.put("title", projectMap.get("title"));
						projectNewMap.put("description", projectMap.get("explain"));
						
						returnList.add(projectNewMap);
					}
				}
			}

			return returnList;

		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 팝업창에서 검색어 입력해서 조회한 항목들에 대한 카운트
	 * 페이징
	 * @param languageId
	 * @param className
	 * @param searchText
	 * @return
	 */
	public static int retrieveCountCustomModel(Locale locale, String className, String searchText){

		int entryListCount = 0;
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("languageId", locale.toString());
			params.put("searchText", searchText);
			if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
				entryListCount = SimulationProjectLocalServiceUtil.getCustomIntegratedSearchSimulationProjectCount(searchText, null, locale);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return entryListCount;
	}

	/**
	 * 링크된 Custom Model에 대한 카운트
	 * 
	 * @param languageId
	 * @param className
	 * @param entryId
	 * @return
	 */
	public static int retrieveCountLinkedAssetEntry(Locale locale, String className, long entryId){
		try{

			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setLinkedAssetEntryId(entryId);
			assetEntryQuery.setClassName(className); // entryId의 className

			List<AssetEntry> linkEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
			List<Long> classPKList = new ArrayList<Long>();

			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK  리스트 작성
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("className", className);
			params.put("languageId", locale.toString());

			String modelSeqList = StringUtils.join(classPKList, ",");
			params.put("modelSeqList", modelSeqList);

			int totalCnt = 0;

			if(!"".equals(modelSeqList)){
				if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
					totalCnt = SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectCount(null, classPKList, locale);
				}
			}
			return totalCnt;

		}catch (Exception e){
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * 현재 클릭한 모델의 포틀릿 정보 가져오기
	 * PortletId, PortletPlid 
	 * @param className
	 * @param groupId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static Map<String, Object> getMapClassModelPortletInfo(String className, long groupId) throws PortalException, SystemException{
		Map<String, Object> portletInfo = new HashMap<String, Object>();

		/* 사이트에 있는 관련정보에서 바로가기를 했을때 필요한 포틀릿 네임 및 PLID , 페이지의 관련정보는 이 메서드 사용 안함.
		 * 추후 변경 될 것.
		 * */
		String portletId = "";
		long portletPlid = 0;
		if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
			portletId = "edisonsimulationproject_WAR_edisonsimulationproject2017portlet";
			portletPlid = PortalUtil.getPlidFromPortletId(groupId, "edisonmypage_WAR_edisondefault2016portlet");
		}
		
		portletInfo.put("portletId", portletId);
		portletInfo.put("portletPlid", portletPlid);
		return portletInfo;
	}
	
	/**
	 * 엔트리 선택 및 해제에 따른 링크 서비스
	 * @param userId
	 * @param checkYn
	 * @param entryId
	 * @param targetEntryId
	 */
	public static void updateAssetEntryLink(long userId, boolean checkYn, long entryId, long targetEntryId){
		try{
			
			if(checkYn){
				AssetLinkLocalServiceUtil.addLink(userId, entryId, targetEntryId, AssetLinkConstants.TYPE_RELATED, 0);
			}else{
				AssetLinkLocalServiceUtil.deleteLinks(entryId, targetEntryId);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 시뮬레이션 프로젝트 리스트
	 * @param className - sourceEntryId 에 링크된 target의 className
	 * @param entryId - sourceEntryId 
	 * @return List<Map<String, Object>> - 프로젝트 리스트
	 */
	public static List<Map<String, Object>> retrieveCustomListLinkedAssetEntry(String className, Locale locale, long entryId) throws PortalException, SystemException {
		try{
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
	
			className = CustomUtil.strNull(className, LinktCustomModelConstants.SIMULATION_PROJECT);
			
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setLinkedAssetEntryId(entryId);
			assetEntryQuery.setClassName(className); // entryId의 className
			
			List<AssetEntry> linkEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
			
			List<Long> classPKList = new ArrayList<Long>();
			Object[] object = new Object[linkEntryList.size()];
			int index = 0;
			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK // 리스트 작성
				object[index++] = entry.getClassPK();
			}
	
			if(linkEntryList != null && classPKList.size() > 0){
				Map params = new HashMap();
				String modelSeqList = StringUtils.join(classPKList, ",");
	
				if(className.equals(LinktCustomModelConstants.SIMULATION_PROJECT)){
					List<Map<String, Object>> projectLinkList = new ArrayList<Map<String, Object>>();
					
					projectLinkList = SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectList(null, classPKList,locale);
					
					for(Map<String, Object> projectMap : projectLinkList){
						Map<String, Object> projectNewMap = new HashMap<String, Object>();
						projectNewMap.put("modelSeq", projectMap.get("simulationProjectId"));
						projectNewMap.put("title", projectMap.get("title"));
						projectNewMap.put("description", projectMap.get("explain"));
						long iconId = Long.parseLong(CustomUtil.strNull(projectMap.get("iconId"), "0"));
						projectNewMap.put("iconId", iconId);
						
						if(iconId != 0){
							try{
								DLFileEntry iconDl =  DLFileEntryLocalServiceUtil.getDLFileEntry(iconId);
								projectNewMap.put("iconRepositoryId", iconDl.getRepositoryId());					
								projectNewMap.put("iconUuid", iconDl.getUuid());
								projectNewMap.put("iconTitle", iconDl.getTitle());
							}catch (Exception e) {
								// TODO: handle exception
							}
						}
						
						returnList.add(projectNewMap);
					}
				}
			}
			return returnList;
			
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
}
