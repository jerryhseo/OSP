/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.content.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringUtils;
import org.kisti.edison.content.NoSuchContentException;
import org.kisti.edison.content.model.AdvancedContent;
import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.model.GeneralContent;
import org.kisti.edison.content.portlet.util.AdvancedFileUtil;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.content.service.base.ContentLocalServiceBaseImpl;
import org.kisti.edison.content.service.persistence.ContentFinderImpl;
import org.kisti.edison.content.service.persistence.ContentFinderUtil;
import org.kisti.edison.customauthmanager.service.UserGroupRoleCustomLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonExpndoUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.util.EdisonUserUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.util.ContentUtil;

/**
 * The implementation of the content local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.content.service.ContentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.content.service.base.ContentLocalServiceBaseImpl
 * @see org.kisti.edison.content.service.ContentLocalServiceUtil
 */
public class ContentLocalServiceImpl extends ContentLocalServiceBaseImpl{
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * org.kisti.edison.content.service.ContentLocalServiceUtil} to access the
	 * content local service.
	 */
	private static Log log = LogFactoryUtil.getLog(ContentLocalServiceImpl.class);

	private String contentFilePreFix = EdisonFileConstants.INFORMATION;

	/**
	 * 콘텐츠 조회
	 * 
	 * @param locale
	 * @param contentSeq
	 * @return
	 * @throws PortalException
	 * @throws NumberFormatException
	 * @throws SystemException
	 */
	public Map<String, Object> retrieveMapContent(long companyId, Locale locale, long contentSeq) throws NumberFormatException,
		PortalException, SystemException{
		Map<String, Object> contentMap = null;
		contentMap = new HashMap<String, Object>();

		Content content = contentPersistence.findByPrimaryKey(contentSeq);

		if(content != null){
			contentMap.put("contentSeq", content.getContentSeq());
			contentMap.put("contentDiv", content.getContentDiv());
			contentMap.put("title", content.getTitle());
			contentMap.put("resume", content.getResume());

			String localeTitle = content.getTitle(locale);
			if(localeTitle.length() > 39){
				localeTitle = localeTitle.substring(0, 40) + "..." ;
			}
			
			contentMap.put("title_" + locale.toString(), localeTitle);
			contentMap.put("resume_" + locale.toString(), StringUtil.split(content.getResume(locale), StringPool.NEW_LINE));
			

			if(content.getContentDiv() == 2001002){
				for(Locale aLocale : LanguageUtil.getAvailableLocales()){

					String languageId = LocaleUtil.toLanguageId(aLocale);
					contentMap.put("contentFileNm_manual_" + languageId, content.getContentFileNm(aLocale));
				}
			}else{
				contentMap.put("contentFileNm", content.getContentFileNm(locale));
				
				if(content.getContentDiv() == 2001004){
					String contentFileNm = content.getContentFileNm(locale);
					String contentFolderNm = contentFileNm.substring(0, contentFileNm.lastIndexOf("."));
					
					String advanceBasicFolder = File.separator + "content" + File.separator +String.valueOf(contentSeq) + 
						File.separator + String.valueOf(contentSeq);// + File.separator;// + contentFolderNm;
	  			
	  			advanceBasicFolder = advanceBasicFolder.replace(File.separator, "/");

					contentMap.put("advancedContentFolderPath", advanceBasicFolder);
//					contentMap.put("advancedContentFolderName", content.getAdvancedStartFileNm());
				}
			}

			String contentDivNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(content.getContentDiv(),
				EdisonExpando.CDNM, locale);
			contentMap.put("contentDivNm", contentDivNm);

			contentMap.put("serviceLanguage", content.getServiceLanguage());
			contentMap.put("projectYn", content.getProjectYn());
			contentMap.put("projectId", content.getProjectId());
			contentMap.put("openYn", content.getOpenYn());
			contentMap.put("advancedStartFileNm", content.getAdvancedStartFileNm());

			contentMap.put("insertId", content.getInsertId());

			User contentUser = UserLocalServiceUtil.getUserById(Long.parseLong(String.valueOf(content
				.getInsertId())));

			contentMap.put("insertNm", contentUser.getScreenName());
			contentMap.put("viewCnt", content.getViewCnt());
		}

		return contentMap;
	}

	/**
	 * 콘텐츠 리스트 조회 사이트 카테고리 생성하여 조회
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 *          콘텐츠 검색어
	 * @param contentDiv
	 *          콘텐츠 유형검색
	 * @param start
	 * @param end
	 * @param locale
	 * @param categoryJoin
	 *          카테고리 테이블과 조인 여부
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> retrieveListContent(long companyGroupId, long groupId, String searchText,
		long[] contentDiv, int start, int end, Locale locale, boolean categoryJoin) throws Exception{
		// 관리자/사이트 관리자이고, 현재 사이트가 하위사이트일때( 카테고리가 있음 )
		long[] categoryIds = makeCategoryEntryList(companyGroupId, groupId);
		return retrieveListContent(categoryIds, searchText, contentDiv, start, end, locale, categoryJoin, true);
	}

	
	/**
	 * 강의노트 조회 서비스 - 강의실에서 사용
	 * @param categoryIds
	 * @param searchText
	 * @param start
	 * @param end
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> retrievetListClassNote(long[] categoryIds,String searchText,int start,int end,Locale locale) throws Exception{
		long[] contentDiv = {(long) 2001001};
		return retrieveListContent(categoryIds, searchText, contentDiv, start, end, locale, true, true);
	}
	
	/**
	 * 강의노트 카운트 조회 서비스 - 강의실에서 사용
	 * @param categoryIds
	 * @param searchText
	 * @param languageId
	 * @return
	 */
	public int retrieveCountClassNote(long[] categoryIds, String searchText, Locale locale){ // 포탈
		// isSite --> 통합검색에서는 category가 있어서 조인을 해야하고, 콘텐츠에서 포탈인 경우에는 category가
		// 없어도되니까 조인이 필요없음.
		long[] contentDiv = {(long) 2001001};
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Content.class.getName());
		return contentFinder.getContentCount(categoryIds, searchText, contentDiv, locale.toString(), classNameId, true, true);
	}
	
	/**
	 * 강의노트의 콘텐츠 목록 조회 서비스 - 강의실에서 사용
	 * @param contentIds
	 * @param locale
	 * @return
	 */
	public List<Map<String, Object>> retrieveListRelatedClassNote(List<Long> contentIds, Locale locale ){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contentDiv", 2001001);
		params.put("languageId", locale.toString());
		String modelSeqList = StringUtils.join(contentIds, ",");
		params.put("modelSeqList", modelSeqList);
		
		try{
			List<Object[]> contentLinkEntryList = contentFinder.searchTextEntryContentList(params);

			Map<String, Object> map = null;
			for(Object[] content : contentLinkEntryList){
				map = new HashMap<String, Object>();
				map.put("contentSeq", content[0]);
				map.put("contentDiv", content[1]);
				map.put("title", content[2]);
				map.put("description", content[3]);

				returnList.add(map);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return returnList;
	}
	
	/**
     * 콘텐츠 리스트 조회 콘텐츠 목록 : 포털 -> categoryIds null
     * 
     * @param categoryIds
     * @param searchText
     *          콘텐츠 검색어
     * @param contentDiv
     *          콘텐츠 유형검색
     * @param start
     * @param end
     * @param locale
     * @param categoryJoin
     *          카테고리 테이블과 조인 여부
     * @param isTotalSearch
     *          통합검색에서 조회하면 true - openYn, serviceLanguage 추가
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> retrieveListContent(long[] categoryIds, String searchText,
        long[] contentDiv, int start, int end, Locale locale, boolean categoryJoin, boolean isTotalSearch)
        throws Exception{
        return retrieveListContent(categoryIds, searchText, contentDiv, start, end, locale, 
            categoryJoin, isTotalSearch, null, null);
    }
	
    public List<Map<String, Object>> retrieveListContent(long[] categoryIds, String searchText, long[] contentDiv,
        int start, int end, Locale locale, boolean categoryJoin, boolean isTotalSearch,
        String sortField, String sortOrder) throws Exception{

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Content.class.getName());
		List<Object[]> contentList = contentFinder.getContentList(categoryIds, searchText, contentDiv, start, end,
			locale.toString(), classNameId, categoryJoin, isTotalSearch, sortField, sortOrder);

		// nullpointer exception prevention
		if(contentList == null || contentList.isEmpty()){
			return new ArrayList<Map<String, Object>>();
		}

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultRow = null;
		for(int i = 0; i < contentList.size(); i++){
			Content gContent = (Content) contentList.get(i)[0];
			resultRow = new HashMap<String, Object>();

			long contentSeq = gContent.getContentSeq();

			resultRow.put("contentSeq", String.valueOf(contentSeq));
			resultRow.put("contentDiv", String.valueOf(gContent.getContentDiv()));

			String contentDivNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(gContent.getContentDiv(),
				EdisonExpando.CDNM, locale);
			resultRow.put("contentDivNm", contentDivNm);

			resultRow.put("title", gContent.getTitle(locale, false));
			resultRow.put("resume", gContent.getResume(locale, false));
			resultRow.put("serviceLanguage", gContent.getServiceLanguage());
			resultRow.put("projectYn", gContent.getProjectYn());
			resultRow.put("projectId", gContent.getProjectId());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String insertDate = simpleDateFormat.format(gContent.getInsertDate());
			resultRow.put("insertDate", insertDate);

			resultRow.put("openYn", gContent.getOpenYn().equals("Y") ? LanguageUtil.get(locale,
				"edison-appstore-status-service") : LanguageUtil.get(locale, "edison-appstore-status-private"));

			resultRow.put("screenName", contentList.get(i)[1]);
			resultRow.put("viewCnt", contentList.get(i)[2]);

			returnList.add(resultRow);
		}
		return returnList;
	}
	
	
	/**
	 * 콘텐츠 카운트 조회 카테고리 배열 생성
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @param searchText
	 *          콘텐츠 검색어
	 * @param contentDiv
	 *          콘텐츠 유형검색
	 * @param languageId
	 * @param categoryJoin
	 *          카테고리 테이블과 조인 여부
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public int retrieveCountContent(long companyGroupId, long groupId, String searchText, long[] contentDiv,
		String languageId, boolean categoryJoin) throws PortalException, SystemException{ // 사이트
		// 사이트인 경우 카테고리 아이디가 있으므로, isSite true
		long categoryIds[] = makeCategoryEntryList(companyGroupId, groupId);
		return retrieveCountContent(categoryIds, searchText, contentDiv, languageId, categoryJoin, false);

	}

	/**
	 * 콘텐츠 카운트 조회 카테고리 배열 파라미터로 받음
	 * 
	 * @param categoryIds
	 * @param searchText
	 *          콘텐츠 검색어
	 * @param contentDiv
	 *          콘텐츠 유형검색
	 * @param languageId
	 * @param categoryJoin
	 *          카테고리 테이블과 조인 여부
	 * @param isTotalSearch
	 *          통합검색에서 조회하면 true
	 * @return
	 */
	public int retrieveCountContent(long[] categoryIds, String searchText, long[] contentDiv, String languageId,
		boolean categoryJoin, boolean isTotalSearch){ // 포탈
		// isSite --> 통합검색에서는 category가 있어서 조인을 해야하고, 콘텐츠에서 포탈인 경우에는 category가
		// 없어도되니까 조인이 필요없음.
	    long classNameId = ClassNameLocalServiceUtil.getClassNameId(Content.class.getName());
        return contentFinder.getContentCount(categoryIds, searchText, contentDiv, languageId, classNameId,
            categoryJoin, isTotalSearch);
	}
	
	/**
	 * 유저의 콘텐츠 리스트 조회 콘텐츠 중 현재 사용자가 OWNER/MANAGER인 항목조회
	 * 
	 * @param companyGroupId
	 * @param parentGroupId
	 * @param groupId
	 * @param searchText
	 * @param start
	 * @param end
	 * @param locale
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListUserContent(long companyGroupId, long parentGroupId,
		long groupId, String searchText, int start, int end, Locale locale, long userId, long roleId)
		throws PortalException, SystemException{
		// 로그인한 사람이 user인 경우의 목록

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultRow = null;

		long[] categoryIds = makeCategoryEntryList(companyGroupId, groupId);

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Content.class.getName());
		List<Object[]> contentList = contentFinder.getContentUserList(categoryIds, searchText, start, end, locale
			.toString(), classNameId, userId, roleId);
		for(int i = 0; i < contentList.size(); i++){
			Content gContent = (Content) contentList.get(i)[0];
			resultRow = new HashMap<String, Object>();

			long contentSeq = gContent.getContentSeq();

			resultRow.put("contentSeq", String.valueOf(contentSeq));
			resultRow.put("contentDiv", String.valueOf(gContent.getContentDiv()));

			String contentDivNm = EdisonExpndoUtil.getCommonCdSearchFieldValue(gContent.getContentDiv(),
				EdisonExpando.CDNM, locale);
			resultRow.put("contentDivNm", contentDivNm);

			resultRow.put("title", gContent.getTitle(locale, false));
			resultRow.put("serviceLanguage", gContent.getServiceLanguage());
			resultRow.put("projectYn", gContent.getProjectYn());
			resultRow.put("projectId", gContent.getProjectId());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String insertDate = simpleDateFormat.format(gContent.getInsertDate());
			resultRow.put("insertDate", insertDate);
			resultRow.put("downloadCnt", String.valueOf(gContent.getViewCnt()));

			resultRow.put("screenName", contentList.get(i)[1]);

			returnList.add(resultRow);
		}
		return returnList;
	}

	/**
	 * 유저의 콘텐츠 카운트 조회 콘텐츠 중 현재 사용자가 OWNER/MANAGER인 항목 카운트
	 * 
	 * @param companyGroupId
	 * @param parentGroupId
	 * @param groupId
	 * @param searchText
	 * @param languageId
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public int retrieveCountUserContent(long companyGroupId, long parentGroupId, long groupId,
		String searchText, String languageId, long userId, long roleId) throws PortalException, SystemException{

		long categoryIds[] = makeCategoryEntryList(companyGroupId, groupId);

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Content.class.getName());

		return contentFinder.getContentUserCount(categoryIds, searchText, languageId, classNameId, userId,
			roleId);
	}

	/**
	 * 콘텐츠 등록
	 * 
	 * @param upload
	 * @param request
	 * @param companyId
	 * @param groupId
	 * @param userId
	 * @param param
	 * @return Content
	 * @throws Exception
	 */
	public Content createContent(UploadPortletRequest upload, PortletRequest request, long companyId,
		long groupId, long userId, Map<String, Object> param) throws Exception{
		Role contentOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_OWNER);

		long contentSeq = CounterLocalServiceUtil.increment(Content.class.getName());

		Content content = ContentLocalServiceUtil.createContent(contentSeq);

		String contentDivStr = CustomUtil.strNull(param.get("contentDiv"));
		if(contentDivStr.equals("")){
			contentDivStr = String.valueOf(param.get("contentDivSelect"));
		}

		long contentDiv = Long.parseLong(contentDivStr);
		content.setContentDiv(contentDiv);

		String serviceLanguage = CustomUtil.strNull(param.get("serviceLanguage"));

		if(serviceLanguage.equals("")){
			String fullLanuage = "";
			for(Locale locale : LanguageUtil.getAvailableLocales()){
				String languageId = LocaleUtil.toLanguageId(locale);

				if(fullLanuage.equals("")){
					fullLanuage += languageId;
				}else{
					fullLanuage += "," + languageId;
				}
			}
			serviceLanguage = fullLanuage;
		}

		content.setTitleMap(CustomUtil.getLocalizationNotSetLocaleMap(param, "title"));
		content.setResumeMap(CustomUtil.getLocalizationNotSetLocaleMap(param, "resume"));

		content.setServiceLanguage(serviceLanguage);

		content.setProjectYn("N");
		content.setProjectId(0);
		content.setOpenYn(String.valueOf(param.get("openYn")));
		content.setInsertId(userId);
		content.setInsertDate(new Date());

		/**************************************************************************************************************************************/
		// 파일등록 (일반/고급) zip으로 파일 묶어서 한개업로드
		String basicContentFilePath = File.separator + contentSeq;// +"/"+advancedContentPK.getContentSeq();

		Locale[] locales = LanguageUtil.getAvailableLocales();

		// 콘텐츠 파일 등록
		for(Locale locale : locales){
			String languageId = LocaleUtil.toLanguageId(locale);
			String fileName = "";
			if(contentDiv == 2001002){// 메뉴얼
				String fileParamsNm = "manual" + languageId;

				String manualFilePath = basicContentFilePath + File.separator + locale.toString() + File.separator;
				fileName = AdvancedFileUtil.createFile(upload, companyId, manualFilePath, String.valueOf(content.getContentSeq()), fileParamsNm);
			}else{
				fileName = AdvancedFileUtil.createFile(upload, companyId, basicContentFilePath + File.separator,
					String.valueOf(content.getContentSeq()), "contentFile");

				String advancedStartFileNm = String.valueOf(param.get("advancedStartFileNm"));
				if(contentDiv == 2001004 && !"".equals(advancedStartFileNm)){// advanced content이고 HTML  파일이면  압축해제
					AdvancedFileUtil.unzipFile(upload, companyId, basicContentFilePath, String.valueOf(content.getContentSeq()), "contentFile");
					content.setAdvancedStartFileNm(advancedStartFileNm);
				}
			}
			content.setContentFileNm(fileName, locale);
		}

		// dl 대표이미지 등록
		EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", String.valueOf(contentSeq),
			"mainImage", contentFilePreFix);

		/**************************************************************************************************************************************/

		contentPersistence.update(content);

		// asset 등록
		long entryId = contentAddAssetEntry(companyId, groupId, content);

		// 카테고리 등록
		List<String[]> categoryList = new ArrayList<String[]>();
		if(param.get("childrenCategoryCheckbox") instanceof String[]){
			String[] childrenCategoryArray = (String[]) param.get("childrenCategoryCheckbox");
			for(String childrenCategory : childrenCategoryArray){
				String[] childrenCategoryValue = childrenCategory.split("_");
				categoryList.add(childrenCategoryValue);
			}
		}else if(param.get("childrenCategoryCheckbox") instanceof String){
			String[] childrenCategoryValue = CustomUtil.strNull(param.get("childrenCategoryCheckbox")).split("_");
			categoryList.add(childrenCategoryValue);
		}

		if(entryId != 0){
			for(String[] categoryArray : categoryList){
				long categoryId = GetterUtil.getLong(categoryArray[1], 0l);

				AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
				// AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(categoryId,
				// entryId);
			}
		}

		// 권한등록
		// CUSTOM 권한 테이블 등록
		UserGroupRoleCustomLocalServiceUtil.addUserGroupRoleCustom(userId, groupId, contentOwnerRole.getRoleId(),
			contentSeq); // OWNER CUSTOM ROLE 추가

		// SiteRole 추가
		User requestUser = UserLocalServiceUtil.fetchUser(userId);
		EdisonUserUtil.addSiteRole(requestUser, groupId, EdisonRoleConstants.CONTENT_OWNER);

		return content;
	}

	/**
	 * 콘텐츠 업데이트
	 * 
	 * @param upload
	 * @param request
	 * @param companyId
	 * @param groupId
	 * @param userId
	 * @param param
	 * @return Content
	 */
	public Content updateContent(UploadPortletRequest upload, PortletRequest request, long companyId,
		long groupId, long userId, Map<String, Object> param) throws Exception{
		long contentDiv = Long.parseLong(String.valueOf(param.get("contentDiv")));
		long entryId = Long.parseLong(String.valueOf(param.get("entryId")));
		long contentSeq = Long.parseLong(String.valueOf(param.get("contentSeq")));

		Content content = ContentLocalServiceUtil.getContent(contentSeq);
		content.setContentDiv(contentDiv);

		String serviceLanguage = CustomUtil.strNull(param.get("serviceLanguage"));
		if(serviceLanguage.equals("")){
			String fullLanuage = "";
			for(Locale locale : LanguageUtil.getAvailableLocales()){
				String languageId = LocaleUtil.toLanguageId(locale);

				if(fullLanuage.equals("")){
					fullLanuage += languageId;
				}else{
					fullLanuage += "," + languageId;
				}
			}
			serviceLanguage = fullLanuage;
		}

		content.setTitleMap(CustomUtil.getLocalizationNotSetLocaleMap(param, "title"));
		content.setResumeMap(CustomUtil.getLocalizationNotSetLocaleMap(param, "resume"));

		content.setServiceLanguage(serviceLanguage);

		content.setProjectYn("N");
		content.setProjectId(0);
		content.setOpenYn(String.valueOf(param.get("openYn")));

		content.setAdvancedStartFileNm(String.valueOf(param.get("advancedStartFileNm")));
		content.setUpdateId(userId);
		content.setUpdateDate(new Date());

		// ********************************************************************************************************************************
		// 파일등록 (일반/고급) zip으로 파일 묶어서 한개업로드
		String basicContentFilePath = File.separator + contentSeq;

		// File 삭제 - 콘텐츠 파일
		if(!CustomUtil.strNull(param.get("deleteAdvancedFile")).equals("")){
			Locale[] locales = LanguageUtil.getAvailableLocales();
			for(Locale locale : locales){
				String languageId = LocaleUtil.toLanguageId(locale);
				String fileName = "";

				if(content.getContentDiv() == 2001002){// 메뉴얼

					String fileParamsNm = "manual" + languageId;

					String manualFilePath = basicContentFilePath + File.separator + locale.toString() + File.separator;
					if(upload.getFileName(fileParamsNm) != null){
						// 삭제
						AdvancedFileUtil.deleteAllFile(companyId, manualFilePath);
						// 등록
						fileName = AdvancedFileUtil.createFile(upload, companyId, manualFilePath, String.valueOf(content.getContentSeq()) , fileParamsNm);

						content.setContentFileNm(fileName, locale);
					}
				}else{
					if(upload.getFileName("contentFile") != null){
						AdvancedFileUtil.deleteAllFile(companyId, basicContentFilePath);
						// 콘텐츠 파일 등록
						fileName = AdvancedFileUtil.createFile(upload, companyId, basicContentFilePath, String.valueOf(content.getContentSeq()), "contentFile");

						if(content.getContentDiv() == 2001004){
							AdvancedFileUtil.unzipFile(upload, companyId, basicContentFilePath, String.valueOf(content.getContentSeq()), "contentFile");
						}
					}
					content.setContentFileNm(fileName);
				}

			}
		}

		// ********************************************************************************************************************************

		// 파일등록
		if(upload.getFileNames("mainImage") != null){
			EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", String.valueOf(contentSeq),
				"mainImage", contentFilePreFix);
		}

		ContentLocalServiceUtil.updateContent(content);

		// 카테고리 clean
		AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);

		// 카테고리 등록
		List<String[]> categoryList = new ArrayList<String[]>();
		if(param.get("childrenCategoryCheckbox") instanceof String[]){
			String[] childrenCategoryArray = (String[]) param.get("childrenCategoryCheckbox");
			for(String childrenCategory : childrenCategoryArray){
				String[] childrenCategoryValue = childrenCategory.split("_");
				categoryList.add(childrenCategoryValue);
			}
		}else if(param.get("childrenCategoryCheckbox") instanceof String){
			String[] childrenCategoryValue = CustomUtil.strNull(param.get("childrenCategoryCheckbox")).split("_");
			categoryList.add(childrenCategoryValue);
		}

		if(entryId != 0){
			for(String[] categoryArray : categoryList){
				long categoryId = GetterUtil.getLong(categoryArray[1], 0l);

				AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(entryId, categoryId);
				// AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(categoryId,
				// entryId);
			}
		}
		return content;
	}

	/**
	 * 콘텐츠 삭제
	 * 
	 * @param companyId
	 * @param groupId
	 * @param param
	 */
	public void deleteContent(long companyId, long groupId, Map<String, Object> param) throws Exception{

		Role contentOwnerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_OWNER);
		Role contentManagerRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.CONTENT_MANAGER);

		long entryId = Long.parseLong(String.valueOf(param.get("entryId")));
		long contentSeq = Long.parseLong(String.valueOf(param.get("contentSeq")));

		Content content = ContentLocalServiceUtil.getContent(contentSeq);

		// 권한 테이블 삭제
		// Administrator, Site Administrator 가 아닐경우
		// 솔버권한 확인 후 GROUP, SiteRole 삭제
		// owner
		User owner = UserLocalServiceUtil.fetchUser(content.getInsertId());

		UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(content.getInsertId(), groupId,
			contentOwnerRole.getRoleId(), contentSeq);
		if(!EdisonUserUtil.isRegularRole(owner, EdisonRoleConstants.ADMINISTRATOR) && !EdisonUserUtil.isSiteRole(
			owner, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
			List<Long> contentOwnerCustomRoleList = UserGroupRoleCustomLocalServiceUtil.getCustomIdList(owner
				.getUserId(), groupId, contentOwnerRole.getRoleId());

			// 솔버 OWNER 권한이 없을 경우 사이트 CONTENT_OWNER 권한 삭제
			if(contentOwnerCustomRoleList == null){
				EdisonUserUtil.deleteSiteRole(owner, groupId, EdisonRoleConstants.CONTENT_OWNER);
			}
		}
		// manager
		long managerId = Long.parseLong(CustomUtil.strNull(param.get("nowMgrId"), "0"));
		if(managerId != 0){
			User manager = UserLocalServiceUtil.fetchUser(managerId);
			UserGroupRoleCustomLocalServiceUtil.removeUserGroupRoleCustom(managerId, groupId, contentManagerRole
				.getRoleId(), contentSeq);
			// manager
			if(!EdisonUserUtil.isRegularRole(manager, EdisonRoleConstants.ADMINISTRATOR) && !EdisonUserUtil
				.isSiteRole(manager, groupId, EdisonRoleConstants.SITE_ADMINISTRATOR)){
				List<Long> contentManagerCustomRoleList = UserGroupRoleCustomLocalServiceUtil.getCustomIdList(manager
					.getUserId(), groupId, contentManagerRole.getRoleId());

				// 솔버 MANAGER 권한이 없을 경우 사이트 CONTENT_MANAGER 권한 삭제
				if(contentManagerCustomRoleList == null){
					EdisonUserUtil.deleteSiteRole(manager, groupId, EdisonRoleConstants.CONTENT_MANAGER);
				}
			}
		}//


		// asset link 테이블 삭제
		List<AssetLink> entryLinkList = AssetLinkLocalServiceUtil.getLinks(entryId);
		for(AssetLink link : entryLinkList){
			AssetLinkLocalServiceUtil.deleteAssetLink(link);
		}

		// 카테고리 삭제
		AssetCategoryLocalServiceUtil.clearAssetEntryAssetCategories(entryId);
		
		// asset Entry 테이블 삭제
		AssetEntryLocalServiceUtil.deleteAssetEntry(entryId);

		// dl file/folder 삭제
		EdisonFileUtil.deleteGroupEdisonFile(groupId, contentFilePreFix, groupId + "_" + contentSeq);

		// 실행파일 삭제
		String contentFilePath = "/" + contentSeq;// +"/"+advancedContentPK.getContentSeq();
		AdvancedFileUtil.deleteAllFile(companyId, contentFilePath);

		// content 삭제
		ContentLocalServiceUtil.deleteContent(content);
	}

	/**
	 * entryId와 관련된 콘텐츠 Entry 조회
	 * 
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> relatedAssetLinkedEntryContent(Map params){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		try{
			List<Object[]> contentLinkEntryList = contentFinder.searchTextEntryContentList(params);

			Map<String, Object> map = null;
			for(Object[] content : contentLinkEntryList){
				map = new HashMap<String, Object>();
				map.put("modelSeq", content[0]);
				map.put("modelDiv", content[1]);
				map.put("title", content[2]);
				map.put("description", content[3]);
				map.put("version", content[4]);

				returnList.add(map);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return returnList;
	}

	/**
	 * 관련정보에서 검색어를 이용해 검색한 콘텐츠 리스트
	 * 
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> searchAssetEntryModelContent(Map params){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = null;

		List<Object[]> contentLinkEntryList = contentFinder.searchTextEntryContentList(params);

		for(Object[] content : contentLinkEntryList){
			map = new HashMap<String, Object>();

			map.put("modelSeq", content[0]);
			map.put("modelDiv", content[1]);
			map.put("title", content[2]);
			map.put("description", content[3]);

			returnList.add(map);
		}
		return returnList;
	}

	/**
	 * 관련정보에서 검색어를 이용해 검색한 콘텐츠 카운트
	 * 
	 * @param params
	 * @return int
	 */
	public int searchAssetEntryModelContentCount(Map params){
		return contentFinder.searchTextEntryContentCount(params);
	}

	/**
	 * 콘텐츠를 ENTRY에 등록
	 * 
	 * @param companyId
	 * @param groupId
	 * @param content
	 * @return long
	 * @throws PortalException
	 * @throws SystemException
	 */
	public long contentAddAssetEntry(long companyId, long groupId, Content content) throws PortalException,
		SystemException{
		try{

			// entry 등록
			AssetEntry assetEntry = assetEntryLocalService.updateEntry(content.getInsertId(), groupId, content
				.getInsertDate(), content.getUpdateDate(), Content.class.getName(), content.getContentSeq(), content
					.getUuid(), 0, null, null, true, null, null, null, ContentTypes.TEXT_PLAIN, content.getTitle(),
				null, null, null, null, 0, 0, null, false);

			return assetEntry.getEntryId();
		}catch (Exception e){
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 콘텐츠 INSERTID 업데이트 (OWNER 변경)
	 * 
	 * @param groupId
	 * @param contentSeq
	 * @param userId
	 * @param projectYn
	 * @param projectId
	 * @throws Exception
	 */
	public void updateContentInsertId(long groupId, long contentSeq, long userId, String projectYn,
		long projectId) throws Exception{

		if(contentSeq != 0){
			Content content = contentPersistence.findByPrimaryKey(contentSeq);

			if(content != null){
				content.setInsertId(userId);
				content.setProjectYn(projectYn);
				content.setProjectId(projectId);

				contentPersistence.update(content);
			}
		}
	}

	/**
	 * 카테고리 배열 생성 통합검색은 사용 안함. 콘텐츠는 포탈에서는 카테고리별로 조회하지 않으므로 parentCategory 가 0인
	 * 사이트(포탈)에 대해서 null을 반환함.
	 * 
	 * @param companyGroupId
	 * @param groupId
	 * @return long[]
	 * @throws PortalException
	 * @throws SystemException
	 */
	public long[] makeCategoryEntryList(long companyGroupId, long groupId) throws PortalException,
		SystemException{

		//
		// custom qurey에서 조회할 customId 값 long 배열을 만듬
		long parentGroupId = GroupLocalServiceUtil.getGroup(groupId).getParentGroupId();

		String categoryStr = "";
		if(parentGroupId != 0){ // 포탈이 아닐때 사이트의 카테고리를 long배열로 생성
			AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
				EdisonAssetCategory.GLOBAL_DOMAIN);

			AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
			List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry
				.getEntryId()); // 사이트에서 사용중인 category

			for(AssetCategory aCategory : aCategoryList){
				// 글로벌 도메인이면서 parentCategory값
				if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory
					.getParentCategoryId() != 0){
					// parent 카테고리 아이디를 만듬.
					if(categoryStr.equals("")){

						categoryStr += aCategory.getCategoryId();
					}else{
						categoryStr += "," + aCategory.getCategoryId();
					}
				}
			}
		}
		return StringUtil.split(categoryStr, 0l);
	}

	public List<Map<String, Object>> getContentDatailListForProjectStatistics(String projectYn, long columnId,
		long categoryId, String languageId, Locale locale) throws Exception{

		List<Object[]> contentDetailList = contentFinder.getContentDatailList(projectYn, columnId, categoryId,
			languageId);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultRow = null;

		for(int i = 0; i < contentDetailList.size(); i++){
			Object[] contentDetailObj = (Object[]) contentDetailList.get(i);
			resultRow = new HashMap<String, Object>();

			if(contentDetailObj != null){
				resultRow.put("projectCategoryId", contentDetailObj[0]);
				resultRow.put("projectCategoryNm", contentDetailObj[1]);
				resultRow.put("prpertyKey", contentDetailObj[2]);
				resultRow.put("propertyValue", contentDetailObj[3]);
				resultRow.put("title", contentDetailObj[4]);
				resultRow.put("screenName", contentDetailObj[5]);
				resultRow.put("firstName", contentDetailObj[6]);
				resultRow.put("insertId", contentDetailObj[7]);
				resultRow.put("insertDate", contentDetailObj[8]);

				String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(String.valueOf(contentDetailObj[9]),
					EdisonExpando.CDNM, locale);
				resultRow.put("affiliation", affiliation);

				returnList.add(resultRow);
			}

		}
		return returnList;
	}

	public List<Map<String, Object>> getContentForProjectList(long userId, String searchText,
		String projectCategoryId, String languageId, int start, int end, Locale locale) throws Exception{
		List<Object[]> generalContentList = contentFinder.getContentForProjectList(userId, searchText,
			projectCategoryId, languageId, start, end);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultRow = null;

		// for(GeneralContent gContent:generalContentList){
		for(int i = 0; i < generalContentList.size(); i++){
			Content gContent = (Content) generalContentList.get(i)[0];
			resultRow = new HashMap<String, Object>();

			long contentSeq = gContent.getContentSeq();

			resultRow.put("contentSeq", String.valueOf(contentSeq));
			resultRow.put("contentDiv", String.valueOf(gContent.getContentDiv()));
			resultRow.put("title", gContent.getTitle(locale, false));
			resultRow.put("serviceLanguage", gContent.getServiceLanguage());
			resultRow.put("projectYn", gContent.getProjectYn());
			resultRow.put("projectId", gContent.getProjectId());

			User contentUser = UserLocalServiceUtil.getUser(gContent.getInsertId());
			long classPK = GetterUtil.getLong(contentUser.getExpandoBridge().getAttribute(
				EdisonExpando.USER_UNIVERSITY), 0);
			String affiliation = EdisonExpndoUtil.getCommonCdSearchFieldValue(classPK, EdisonExpando.CDNM, locale);
			resultRow.put("affiliation", affiliation);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String insertDate = simpleDateFormat.format(gContent.getInsertDate());
			resultRow.put("insertDate", insertDate);
			resultRow.put("screenName", generalContentList.get(i)[1]);
			resultRow.put("firstName", generalContentList.get(i)[2]);

			returnList.add(resultRow);

		}
		return returnList;
	}

	/* 에디슨 프로젝트 통계 */
	public List<Map<String, Object>> getContentCenterListForProjectStatistics(String projectYn,
		String propertyKey, Locale locale) throws Exception{

		List<Object[]> contentCenterList = contentFinder.getContentCenterList(projectYn, propertyKey);

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultRow = null;

		for(int i = 0; i < contentCenterList.size(); i++){
			Object[] contentCenterObj = (Object[]) contentCenterList.get(i);
			resultRow = new HashMap<String, Object>();

			if(contentCenterObj != null){
				int projectCnt = (Integer) contentCenterObj[4];
				if(projectCnt > 0){
					resultRow.put("projectCategoryId", contentCenterObj[0]);
					resultRow.put("projectCategoryNm", contentCenterObj[1]);
					resultRow.put("prpertyKey", contentCenterObj[2]);
					resultRow.put("propertyValue", LanguageUtil.get(locale, CustomUtil.strNull(contentCenterObj[3])));
					resultRow.put("projectCnt", projectCnt);

					returnList.add(resultRow);
				}
			}

		}
		return returnList;
	}

	public int getGeneralContentCountByGroupIdForProjectList(long userId, String searchText,
		String projectCategoryId, String languageId){
		return contentFinder.getGeneralContentForProjectListCount(userId, searchText, projectCategoryId,
			languageId);
	}

	/* asset 마이그레이션 서비스 */
	public void migrationGeneralContentTable(long companyGroupId, long companyId) throws SystemException{
//		log.info("===================== 일반콘텐츠 마이그레이션 시작 =====================");
		try{
			 List<GeneralContent> generalContentList =   generalContentPersistence.findAll();
//			 List<GeneralContent> generalContentList =   generalContentPersistence.findAll(1, 10);

			String contentBasicPath = PropsUtil.get(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR) + File.separator + "content";

			Locale[] availableLanguage = LanguageUtil.getAvailableLocales();
			for(GeneralContent general : generalContentList){
				long groupId = general.getGroupId();

				long contentSeq = general.getContentSeq();// CounterLocalServiceUtil.increment(Content.class.getName());

				Content content = null;
				try {
					content = contentPersistence.findByPrimaryKey(general.getContentSeq());
				} catch (NoSuchContentException e) {
					content = contentPersistence.create(contentSeq);

					content.setTitleMap(general.getTitleMap());
	
					Map<Locale, String> resumeMap = new HashMap<Locale, String>();
					String lang = "";
	
					List fileList = EdisonFileUtil.getListEdisonFile(general.getGroupId(), "", String.valueOf(general.getContentSeq()), "DEFAULT_CONTENTS");
					for(int i = 0; i < availableLanguage.length; i++){
						resumeMap.put(availableLanguage[i], general.getTitle(availableLanguage[i]));
	
						if(lang.equals("")){
							lang += availableLanguage[i];
						}else{
							lang += "," + availableLanguage[i];
						}
	
						String fileName = "";
						if(fileList != null){
							if(fileList.size() == 1){
								Map fileMap = (Map) fileList.get(0);
								fileName = String.valueOf(fileMap.get("fileTitle"));
							}else if(fileList.size() > 1){
								fileName = "content.zip";
							}
						}
						content.setContentFileNm(fileName, availableLanguage[i]);
					}
	
					content.setResumeMap(resumeMap);
					content.setContentDiv(general.getContentDiv());
					content.setServiceLanguage(lang);
					content.setProjectYn(general.getProjectYn());
					content.setProjectId(general.getProjectId());
					content.setViewCnt(general.getDownloadCnt());
					content.setInsertId(general.getInsertId());
					content.setInsertDate(general.getInsertDate());
					content.setUpdateId(general.getUpdateId());
					content.setUpdateDate(general.getUpdateDate());
					content.setOpenYn("Y");
					content.setVersion("1.0.0");
	
					// 모델 등록
					 ContentLocalServiceUtil.updateContent(content);
	
					// 카테고리
					long categoryIds[] = new long[1];
					AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
						EdisonAssetCategory.GLOBAL_DOMAIN);
	
					AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
					List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry
						.getEntryId()); // 사이트에서 사용중인 category
	
					for(AssetCategory aCategory : aCategoryList){
						// 글로벌 도메인이면서 parentCategory값
						if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory
							.getParentCategoryId() != 0){
							/*
							 * if(aCategory.getName().equals("기타")){ categoryIds[0] =
							 * aCategory.getCategoryId(); }
							 */
							if(aCategory.getTitle("en_US").equals("Etc")){
								categoryIds[0] = aCategory.getCategoryId();
							}
						}
					}
	
					AssetEntry assetEntry =
						  assetEntryLocalService.updateEntry(content.getInsertId(), groupId,
						  content .getInsertDate(), content.getUpdateDate(),
						  Content.class.getName(), content.getContentSeq(), content .getUuid(),
						  0, categoryIds, null, true, null, null, null,
						  ContentTypes.TEXT_PLAIN, content.getTitle(), null, null, null, null,
						  0, 0, null, false);
				}
				
				
				//파일 다운로드
				if(content != null){
					List file = EdisonFileUtil.getListEdisonFile(groupId, "", String.valueOf(contentSeq), "DEFAULT_CONTENTS");
					for(int i=0;i<file.size();i++){
						Map fileMap = (Map)file.get(i);

						String fileAddress = "http://150.183.247.221:8080/documents/" + fileMap.get("fileRepositoryId") + "/" + fileMap.get("fileUuid");
						if(content.getContentDiv() == 2001002){
							for(int j=0; j<availableLanguage.length;j++){
								String langDownloadDir = contentBasicPath + File.separator  + content.getContentSeq() + File.separator + availableLanguage[j];
								String fileName = String.valueOf(fileMap.get("fileTitle"));
								String fileExt = "."+fileName.substring(fileName.lastIndexOf(".")+1);
								
								String exportFileName = content.getContentSeq() + fileExt;
								if(fileExt.equals(".zip")){
									exportFileName = "content.zip";
								}
								generalContentfileUrlReadAndDownload(fileAddress,exportFileName  , langDownloadDir);
							}
						}else{
							String langDownloadDir = contentBasicPath + File.separator  + content.getContentSeq();
							String fileName = String.valueOf(fileMap.get("fileTitle"));
							String fileExt = "."+fileName.substring(fileName.lastIndexOf(".")+1);
							
							String exportFileName = content.getContentSeq() + fileExt;
							if(fileExt.equals(".zip")){
								exportFileName = "content.zip";
							}
							
							generalContentfileUrlReadAndDownload(fileAddress, exportFileName, langDownloadDir);
						}
						
					}
					log.info(content.getContentSeq() + " ---> " + file.size());
					if(file.size() > 1){
						AdvancedFileUtil.compressFolderToZip(companyId, content.getContentSeq(), content.getContentDiv(), String.valueOf(content.getContentSeq()));							
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private void generalContentfileUrlReadAndDownload(String fileAddress, String localFileName, String downloadDir){
		OutputStream outStream = null;
		URLConnection uCon = null;
		InputStream is = null;
		try{
			// System.out.println("-------Download Start------");
			URL Url;
			byte[] buf;
			int byteRead;
			int byteWritten = 0;

			Url = new URL(fileAddress);

			File existFolder = new File(downloadDir);
			if(!existFolder.exists()){
				existFolder.mkdirs();
			}else{
				existFolder.delete();
				existFolder.mkdirs();
			}

			outStream = new BufferedOutputStream(new FileOutputStream(downloadDir + File.separator + localFileName));
			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[1024];
			while((byteRead = is.read(buf)) != -1){
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(is != null) is.close();
				if(outStream != null)outStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	
	/* asset 마이그레이션 서비스 */
	public void migrationAdvancedContentTable(long companyGroupId, long companyId) throws SystemException{
//		log.info("===================== 고급콘텐츠 마이그레이션 시작 =====================");
		try{
			List<AdvancedContent> advancedContentList = advancedContentPersistence.findAll();

			String contentTomcatPath = PropsUtil.get(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR) + File.separator;
			
			Locale[] availableLanguage = LanguageUtil.getAvailableLocales();
			
			for(AdvancedContent advanced : advancedContentList){
				Content content = null;
				try {
					content = contentPersistence.findByPrimaryKey(advanced.getContentSeq());
//					content = ContentLocalServiceUtil.getContent(advanced.getContentSeq());
				} catch (NoSuchContentException e) {
					long contentSeq = advanced.getContentSeq();// CounterLocalServiceUtil.increment(Content.class.getName());
	
					content = contentPersistence.create(contentSeq);
	
					Map<Locale, String> titleMap = new HashMap<Locale, String>();
					Map<Locale, String> resumeMap = new HashMap<Locale, String>();
	
					Locale locale = LocaleUtil.fromLanguageId(advanced.getServiceLanguage());
					titleMap.put(locale, advanced.getTitle());
					resumeMap.put(locale, advanced.getResume());
	
					/*
					 * content.setTitleMap(titleMap); content.setResumeMap(resumeMap);
					 */
					content.setTitle(advanced.getTitle(), locale);
					content.setResume(advanced.getResume(), locale);
	
					content.setContentDiv(2001004);
					content.setServiceLanguage(advanced.getServiceLanguage());
					
					for(int i = 0; i < availableLanguage.length; i++){
						content.setContentFileNm(advanced.getContentFileNm(), availableLanguage[i]);
					}
					
					content.setAdvancedStartFileNm(advanced.getContentStartFileNm());
					content.setViewCnt(advanced.getViewCnt());
					content.setInsertId(advanced.getInsertId());
					content.setInsertDate(advanced.getInsertDate());
					content.setUpdateId(advanced.getUpdateId());
					content.setUpdateDate(advanced.getUpdateDate());
	
					long groupId = advanced.getGroupId();
					// 모델 등록
					ContentLocalServiceUtil.updateContent(content);
	
					// resourceLocalService.addResources(companyId, groupId,
					// content.getInsertId(), Content.class.getName(),
					// content.getContentSeq(), false, true, true);
	
					// 카테고리
					long categoryIds[] = new long[1];
					AssetVocabulary aVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId,
						EdisonAssetCategory.GLOBAL_DOMAIN);
	
					AssetEntry aEntry = AssetEntryLocalServiceUtil.fetchEntry(Group.class.getName(), groupId);
					List<AssetCategory> aCategoryList = AssetCategoryLocalServiceUtil.getAssetEntryAssetCategories(aEntry
						.getEntryId()); // 사이트에서 사용중인 category
	
					for(AssetCategory aCategory : aCategoryList){
						// 글로벌 도메인이면서 parentCategory값
						if(aCategory.getVocabularyId() == aVocabulary.getVocabularyId() && aCategory
							.getParentCategoryId() != 0){
							if(aCategory.getTitle("en_US").equals("Etc")){
								System.out.println("category Etc");
								categoryIds[0] = aCategory.getCategoryId();
							}
							else if(aCategory.getTitle("en_US").equals("etc")){
								System.out.println("category etc");
								categoryIds[0] = aCategory.getCategoryId();
							}
						}
					}
	
					AssetEntry assetEntry = assetEntryLocalService.updateEntry(content.getInsertId(), groupId, content
						.getInsertDate(), content.getUpdateDate(), Content.class.getName(), content.getContentSeq(), content
							.getUuid(), 0, categoryIds, null, true, null, null, null, ContentTypes.TEXT_PLAIN, content
								.getTitle(), null, null, null, null, 0, 0, null, false);
	
					/*
					 * Indexer indexer =
					 * IndexerRegistryUtil.nullSafeGetIndexer(Content.class);
					 * indexer.reindex(content);
					 */
	
					//파일다운로드
					String contentFileNm = advanced.getContentFileNm();
					if(!contentFileNm.equals("")){
						String fileExt = "."+contentFileNm.substring(contentFileNm.lastIndexOf(".")+1);
						
						String advancedPrePath = contentTomcatPath + "content" + advanced.getContentFilePath();
						String advancedNewPath = contentTomcatPath + "content" + File.separator + contentSeq;
						
						String advancedPreFile = advancedPrePath + File.separator + advanced.getContentFileNm();
						
						File advancedPrePathFile = new File(advancedPreFile);
						if(advancedPrePathFile.exists()){
							
							InputStream is = new FileInputStream(advancedPreFile);
							
							File tempFile = FileUtil.createTempFile(is); // tempFile 생성
		//						tempFileIs = new FileInputStream(tempFile);
							
							File advancedNewPathFolder = new File(advancedNewPath);
							if(!advancedNewPathFolder.exists()){
								advancedNewPathFolder.mkdirs();
							}
							
							FileOutputStream output = new FileOutputStream(advancedNewPath + File.separator + contentSeq+fileExt); //contentSeq로 파일명생성
							FileInputStream inputStream = new FileInputStream(tempFile);
							byte[] buffer = new byte[1024 * 8];
							int readcount = 0;
							while((readcount=inputStream.read(buffer)) != -1) {
								output.write(buffer, 0, readcount);
							}
							inputStream.close();
							output.close();
							FileUtil.delete(tempFile);
							
							
							//파일압축해제 시작
							ZipInputStream zis = new ZipInputStream(new FileInputStream(advancedNewPath + File.separator + contentSeq+fileExt),Charset.forName("euc-kr"));
					         
					        ZipEntry ze = zis.getNextEntry();
					        while(ze!=null){
					            String entryName = ze.getName();
					            File f = new File(advancedNewPath + File.separator + contentSeq +File.separator +  entryName);
					            f.getParentFile().mkdirs();
					            FileOutputStream fos = new FileOutputStream(f);
					            int len;
					            while ((len = zis.read(buffer)) > 0) {
					                fos.write(buffer, 0, len);
					            }
					            
					            fos.close();   
	
					            ze = zis.getNextEntry();
					        }
					        zis.closeEntry();
					        zis.close();
					        //파일압축해제 시작
					        
					        /*File removeTargetFolder = new File(advancedPrePath);
					        if(removeTargetFolder.exists()){
					        	removeTargetFolder.delete();
					        }*/
						}
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	
	public List<Content> findByContentDiv(long contentDiv) throws SystemException{
		return contentPersistence.findBycontentDiv(contentDiv);
	}
}
