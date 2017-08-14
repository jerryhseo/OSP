package org.kisti.edison.asset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kisti.edison.asset.portlet.AssetCustomModelConstants;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;
import org.kisti.edison.util.CustomUtil;

import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

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
			if(className.equals(AssetCustomModelConstants.SCIENCE_APP)){
				params.put("status", 1901004);
				searchTextList = ScienceAppLocalServiceUtil.searchAssetEntryModelAPP(params);
			}else if(className.equals(AssetCustomModelConstants.SCIENCE_INFORMATION)){
				params.put("openYn", "Y");
				searchTextList = ContentLocalServiceUtil.searchAssetEntryModelContent(params);
			}else if(className.equals(AssetCustomModelConstants.OPEN_DATA)){
				// 모델생성 후 작성
				List<Map<String, Object>> collectionSearchList = new ArrayList<Map<String, Object>>();
				collectionSearchList = DataCollectionLocalServiceUtil.retrieveDataCollectionObjects(searchText, locale, start, start+end);
				
				for(Map<String, Object> collectionMap : collectionSearchList){
					Map<String, Object> collectionNewMap = new HashMap<String, Object>();
					collectionNewMap.put("modelSeq", collectionMap.get("collectionId"));
					
					String title = CustomUtil.strNull(collectionMap.get("title")); 
					String description = CustomUtil.strNull(collectionMap.get("description"));
					
					if(!title.equals("")){
						title = LocalizationUtil.getLocalization(title, locale.toString());
					}
					collectionNewMap.put("title", title);
					if(!description.equals("")){
						description = LocalizationUtil.getLocalization(title, locale.toString());
					}
					collectionNewMap.put("description", description);
					collectionNewMap.put("version", collectionMap.get("version"));
					
					searchTextList.add(collectionNewMap);
				}
			}else if(className.equals(AssetCustomModelConstants.SIMULATION_PROJECT)){
				
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

			className = CustomUtil.strNull(className, AssetCustomModelConstants.SCIENCE_APP);

			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setLinkedAssetEntryId(entryId);
			assetEntryQuery.setClassName(className); // entryId의 className
			
			List<AssetEntry> linkEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
			
			List<Long> classPKList = new ArrayList<Long>();
			Object[] collectionIdArray = new Object[linkEntryList.size()];
			int index = 0;
			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK // 리스트 작성
				collectionIdArray[index++] = entry.getClassPK();
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
				
				if(className.equals(AssetCustomModelConstants.SCIENCE_APP)){
					returnList = ScienceAppLocalServiceUtil.relatedAssetLinkedEntryScienceAPP(params);
				}else if(className.equals(AssetCustomModelConstants.SCIENCE_INFORMATION)){
					returnList = ContentLocalServiceUtil.relatedAssetLinkedEntryContent(params);
				}else if(className.equals(AssetCustomModelConstants.OPEN_DATA)){					
					List<Map<String, Object>> collectionLinkList = DataCollectionLocalServiceUtil.retrieveDataCollectionObjectsByCollectId(collectionIdArray, start, end ,locale);

					for(Map<String, Object> collectionMap : collectionLinkList){
						collectionMap.put("modelSeq", collectionMap.get("collectionId"));
						collectionMap.put("title",LocalizationUtil.getLocalization(String.valueOf(collectionMap.get("title")), locale.toString()));
						collectionMap.put("description", LocalizationUtil.getLocalization(String.valueOf(collectionMap.get("description")), locale.toString()));
						
						returnList.add(collectionMap);
					}
				}else if(className.equals(AssetCustomModelConstants.SIMULATION_PROJECT)){
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
				}else if(className.equals(AssetCustomModelConstants.COMMUNITY)){
					Map<String, Object> communityMap = null;
					for(int i = 0; i < classPKList.size(); i++){
						communityMap = new HashMap<String, Object>();

						Group community = GroupLocalServiceUtil.getGroup(classPKList.get(i));
						communityMap.put("modelSeq", community.getGroupId());
						communityMap.put("name", LanguageUtil.get(LocaleUtil.fromLanguageId(locale.toString()), community
							.getName()));
						
						communityMap.put("friendlyURL", community.getFriendlyURL());

						returnList.add(communityMap);
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
			// 목록조회
			if(className.equals(AssetCustomModelConstants.SCIENCE_APP)){
				params.put("status", 1901004);
				entryListCount = ScienceAppLocalServiceUtil.searchAssetEntryModelAPPCount(params);
			}else if(className.equals(AssetCustomModelConstants.SCIENCE_INFORMATION)){
				params.put("openYn", "Y");
				entryListCount = ContentLocalServiceUtil.searchAssetEntryModelContentCount(params);
			}else if(className.equals(AssetCustomModelConstants.OPEN_DATA)){
				entryListCount = DataCollectionLocalServiceUtil.countDataCollectionObjects(searchText, locale);
			}else if(className.equals(AssetCustomModelConstants.SIMULATION_PROJECT)){
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

			Object[] collectionIdArray = new Object[linkEntryList.size()];
			int index = 0;
			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK  리스트 작성
				collectionIdArray[index++] = entry.getClassPK();
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("className", className);
			params.put("languageId", locale.toString());

			String modelSeqList = StringUtils.join(classPKList, ",");
			params.put("modelSeqList", modelSeqList);

			int totalCnt = 0;
			

			if(!"".equals(modelSeqList)){
				if(className.equals(AssetCustomModelConstants.SCIENCE_APP)){
					totalCnt = ScienceAppLocalServiceUtil.searchAssetEntryModelAPPCount(params);
				}else if(className.equals(AssetCustomModelConstants.SCIENCE_INFORMATION)){
					totalCnt = ContentLocalServiceUtil.searchAssetEntryModelContentCount(params);
				}else if(className.equals(AssetCustomModelConstants.OPEN_DATA)){
					totalCnt = DataCollectionLocalServiceUtil.countDataCollectionObjectsByCollectId(collectionIdArray, locale);
				}else if(className.equals(AssetCustomModelConstants.SIMULATION_PROJECT)){
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

}
