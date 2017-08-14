package org.kisti.edison.customService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kisti.edison.constants.SimulationProjectConstants;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.service.SimulationProjectLocalServiceUtil;

import com.kisti.osp.icecap.service.DataCollectionLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

public class RelateAssetStatisticCustomService{

	/**
	 * 관련정보 카운트
	 * @param locale
	 * @param className - 카운트 하고자 하는 관련정보의 className
	 * @param modelId - entryId ex)  시뮬레이션 프로젝트의 entry id
	 * @return int
	 */
	public static int retrieveCountLinkedAssetEntry(Locale locale, String className, long modelId){
		try{
			
			long entryId = modelId;
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setLinkedAssetEntryId(entryId);
			assetEntryQuery.setClassName(className); 

			List<AssetEntry> linkEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);

			List<Long> classPKList = new ArrayList<Long>();

			for(AssetEntry entry : linkEntryList){
				classPKList.add(entry.getClassPK()); // query용 classPK
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("className", className);
			params.put("languageId", locale.toString());

			String modelSeqList = StringUtils.join(classPKList, ",");
			params.put("modelSeqList", modelSeqList);

			int totalCnt = 0;

			if(!"".equals(modelSeqList)){
				if(className.equals(SimulationProjectConstants.SCIENCE_APP_CLASS_NAME)){
					totalCnt = SimulationProjectLocalServiceUtil.searchAssetEntryModelAppCount(params);
				}else if(className.equals(SimulationProjectConstants.SCIENCE_INFORMATION_CLASS_NAME)){
					totalCnt = ContentLocalServiceUtil.searchAssetEntryModelContentCount(params);
				}else if(className.equals(SimulationProjectConstants.SCIENCE_DATA_CLASS_NAME)){
					totalCnt = DataCollectionLocalServiceUtil.retrieveCountRelatedAssetDataCollection(classPKList, locale);
				}else if(className.equals(SimulationProjectConstants.SIMULATION_PROJECT_CLASS_NAME)){
					totalCnt = SimulationProjectLocalServiceUtil.getCustomLinkSimulationProjectCount(null, classPKList, locale);
				}else if(className.equals(SimulationProjectConstants.COMMUNITY_CLASS_NAME)){
					totalCnt = linkEntryList.size();
				}
			}
			return totalCnt;

		}catch (Exception e){
			e.printStackTrace();
		}

		return 0;

	}
}
