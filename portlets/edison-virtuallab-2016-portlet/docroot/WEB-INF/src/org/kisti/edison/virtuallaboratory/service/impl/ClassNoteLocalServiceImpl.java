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

package org.kisti.edison.virtuallaboratory.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.portlet.PortletRequest;

import org.kisti.edison.content.model.Content;
import org.kisti.edison.content.service.ContentLocalServiceUtil;
import org.kisti.edison.model.EdisonAssetCategory;
import org.kisti.edison.model.EdisonFileConstants;
import org.kisti.edison.util.CustomUtil;
import org.kisti.edison.util.EdisonFileUtil;
import org.kisti.edison.virtuallaboratory.NoSuchClassNoteException;
import org.kisti.edison.virtuallaboratory.model.ClassNote;
import org.kisti.edison.virtuallaboratory.service.base.ClassNoteLocalServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * The implementation of the class note local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.virtuallaboratory.service.ClassNoteLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author EDISON
 * @see org.kisti.edison.virtuallaboratory.service.base.ClassNoteLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.ClassNoteLocalServiceUtil
 */
public class ClassNoteLocalServiceImpl extends ClassNoteLocalServiceBaseImpl{

	public List<Map<String, Object>> getVirtualLabClassNoteList (long classId , Locale locale) throws PortalException, SystemException {
		
		List<Object[]> classNoteList = classNoteFinder.getVirtualLabClassNoteList(classId, locale);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		Map <String, Object> resultRow = null;
		for(int i = 0; i < classNoteList.size(); i++) {
			Object[] resultArray = classNoteList.get(i);
			resultRow = new HashMap<String, Object>();
			String decription = "";
			if (resultArray != null) {
				resultRow.put("classNoteSeq", CustomUtil.strNull(resultArray[0]));
				resultRow.put("classId", CustomUtil.strNull(resultArray[1]));
				resultRow.put("contentSeq", CustomUtil.strNull(resultArray[2]));
				resultRow.put("isContent", CustomUtil.strNull(resultArray[3]));
				resultRow.put("fileEntryId", CustomUtil.strNull(resultArray[4]));
				resultRow.put("insertDate", new SimpleDateFormat("yyyy-MM-dd").format(resultArray[6]));
				resultRow.put("insertId", CustomUtil.strNull(resultArray[7]));
				if(CustomUtil.strNull(resultArray[5]).equals("")){
					decription = CustomUtil.strNull(resultArray[8]);
				}else{
					decription = CustomUtil.strNull(resultArray[5]);
				}
				resultRow.put("description", decription );
			}
			returnList.add(resultRow);
		}
		return returnList;
	}
	
	/**
	 * @param locale
	 * @param searchText
	 * @param start
	 * @param end
	 * @param groupId
	 * @param companyGroupId
	 * @return
	 */
	public List<Map<String, Object>> retrievetListClassNote(Locale locale, String searchText, int start,
		int end, long groupId, long companyGroupId) throws Exception{

		long[] categoryIds = makeCategoryEntryList(companyGroupId, groupId);
		return ContentLocalServiceUtil.retrievetListClassNote(categoryIds, searchText, start, end, locale);
	}

	/**
	 * @param companyGroupId
	 * @param groupId
	 * @param locale
	 * @param searchText
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 */
	public int retrieveCountClassNote(long companyGroupId, long groupId, Locale locale, String searchText)
		throws PortalException, SystemException{

		long[] categoryIds = makeCategoryEntryList(companyGroupId, groupId);

		return ContentLocalServiceUtil.retrieveCountClassNote(categoryIds, searchText, locale);

	}

	/**
	 * @param classId
	 * @param isContent
	 * @param locale
	 * @return
	 * @throws SystemException
	 */
	public List<Map<String, Object>> retrieveListRelatedClassNote(long classId, boolean isContent,
		Locale locale) throws SystemException{

		List<ClassNote> classNoteList = classNotePersistence.findByclassIdIsContent(classId, String.valueOf(
			isContent));

		List<Long> contentIds = new ArrayList<Long>();
		if(classNoteList.size() > 0){
			for(ClassNote classNoteMap : classNoteList){
				contentIds.add(classNoteMap.getContentSeq());
			}
		}
		
		List<Map<String, Object>> relatedContentList = null;
		if(contentIds.size() > 0){
			relatedContentList = ContentLocalServiceUtil.retrieveListRelatedClassNote(contentIds, locale);
		}

		return relatedContentList;
	}
	/**
	 * @param classNoteSeq
	 * @return
	 * @throws SystemException
	 * @throws NoSuchClassNoteException 
	 */
	public ClassNote retrieveClassNote(long classNoteSeq) throws SystemException, NoSuchClassNoteException{
		ClassNote classNote = classNotePersistence.findByPrimaryKey(classNoteSeq);
		return classNote;
	}
	
	/**
	 * @param classNoteSeq
	 * @return
	 * @throws SystemException
	 * @throws NoSuchClassNoteException 
	 */
	public ClassNote retrieveClassNote(long classId, long contentSeq) throws SystemException, NoSuchClassNoteException{
		List<ClassNote> classNotes = classNotePersistence.findByclassIdIsContentSeq(classId, contentSeq);
		ClassNote classNote = null;
		for(int i=0; i<classNotes.size(); i++){
			classNote = classNotes.get(0);
		}
		return classNote;
	}

	/**
	 * 파일 등록
	 * 
	 * @param upload
	 * @param request
	 * @param groupId
	 * @param userId
	 * @param param(classNoteSeq,
	 *          classId, description)
	 * @param locale
	 */
	public ClassNote addFileClassNote(UploadPortletRequest upload, PortletRequest request, long groupId,
		long userId, Map<String, String> param, Locale locale) throws SystemException, PortalException,
		SQLException, IOException{
		ClassNote classNote = null;
		if(!param.get("classNoteSeq").equals("0")){
			String myLocalFileStr = CustomUtil.strNull(upload.getFileName("my_local_file"), "");

			classNote = classNotePersistence.findByPrimaryKey(Long.parseLong(param.get("classNoteSeq")));
			classNote.setDescriptionMap(CustomUtil.getLocalizationMap(param, "description"));
			classNote.setUpdateDate(new Date());
			classNote.setUpdateId(userId);

			// 파일 업데이트
			if(!myLocalFileStr.equals("")){ // 파일이 있는경우
				long fileId = classNote.getFileEntryId();
				if(fileId != 0){
					// 기존 파일 삭제
						DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileId);
				}
				List<FileEntry> courseIcon = EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "",
					param.get("classId"), "my_local_file", EdisonFileConstants.COURSE);
				FileEntry appEntry = courseIcon.get(0);
				
				
				classNote.setFileEntryId(appEntry.getFileEntryId());
			}
			classNotePersistence.update(classNote);
		}else{
			long classNoteSeq = CounterLocalServiceUtil.increment(ClassNote.class.getName());
			classNote = classNotePersistence.create(classNoteSeq);
			classNote.setClassId(Long.parseLong(param.get("classId")));
			classNote.setIsContent("false");
			classNote.setInsertDate(new Date());
			classNote.setInsertId(userId);
			classNote.setDescriptionMap(CustomUtil.getLocalizationMap(param, "fileDescription"));

			// 파일 등록
			String myLocalFileStr = CustomUtil.strNull(upload.getFileName("my_local_file"), "");
			if(!myLocalFileStr.equals("")){ // 파일이 있는경우
				List<FileEntry> myLocalFile = EdisonFileUtil.insertEdisonFile(request, upload, userId, groupId, "", "classId_"+ param.get("classId"), "my_local_file", EdisonFileConstants.COURSE);
				FileEntry fileEntry = myLocalFile.get(0);
				classNote.setFileEntryId(fileEntry.getFileEntryId());
			}
			classNote = classNotePersistence.update(classNote); 
		}
		return classNote;
	}
	
	public ClassNote addFileClassNote(PortletRequest request, long contentSeq, long classId, long groupId, long userId, Map<String, Object> param, Locale locale) 
		throws SystemException, PortalException,
		SQLException, IOException{
		
		Content content = ContentLocalServiceUtil.getContent(contentSeq);
		
		param.put("fileDescription", content.getResume());
		
		long classNoteSeq = CounterLocalServiceUtil.increment(ClassNote.class.getName());
		
		ClassNote classNote = classNotePersistence.create(classNoteSeq);
		classNote.setClassId(classId);
		classNote.setIsContent("true");
		classNote.setInsertDate(new Date());
		classNote.setInsertId(userId);
		classNote.setDescription(content.getResume());
		classNote.setContentSeq(contentSeq);
		
		long fileEntryId = uploadDLfileByContentFile(request, classId, contentSeq, groupId, userId, locale);
		classNote.setFileEntryId(fileEntryId);
		
		classNote = classNotePersistence.update(classNote);
		
		return classNote;
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

		try{
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
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public long uploadDLfileByContentFile(PortletRequest request, long classId, long contentSeq,
		long groupId, long userId, Locale locale) throws IOException, PortalException, SystemException{
		String contentFilePath = PropsUtil.get(PropsKeys.AUTO_DEPLOY_TOMCAT_DEST_DIR) + File.separator + "content"
			+ File.separator + contentSeq;
		File contentDir = new File(contentFilePath);

		FileEntry fileEntry = null;
		if(contentDir.exists()){ // 폴더가 존재할때
			File[] contentFiles = contentDir.listFiles();
			if(contentFiles != null && contentFiles.length > 0){
				File contentFile = contentFiles[0];
				InputStream is = new FileInputStream(contentFile);
				byte[] bytes = FileUtil.getBytes(is);

				File tempFile = null;
				if(ArrayUtil.isNotEmpty(bytes)){
					tempFile = FileUtil.createTempFile(bytes);
				}else{
					try{
						tempFile = FileUtil.createTempFile(is);
						is.close();
					}catch (IllegalArgumentException e){
						// 빈 값의 temp 파일 생성 메소드
						tempFile = FileUtil.createTempFile();
						// temp 파일 물리적 생성
						FileUtil.write(tempFile, "");
					}
				}
				// Guest와 User에게 View 권한을 주기 위한 Setting
				ServiceContext sctx = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), request);
				sctx.setAddGuestPermissions(true);
				sctx.setAddGroupPermissions(true);

				if(tempFile != null){
					Content content = ContentLocalServiceUtil.getContent(contentSeq);
					String fileName = content.getContentFileNm(locale);

					String contentType = MimeTypesUtil.getContentType(fileName);
					
					DLFolder dlFolder = EdisonFileUtil.createEdisonDLFolder(request, userId, groupId, "classId_"+classId, EdisonFileConstants.COURSE);
					
					try{
  					fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, groupId, dlFolder.getFolderId(), null,
  						contentType, fileName, null, null, tempFile, sctx);
					}catch (DuplicateFileException e){
					//중복파일명 입력 시 파일명 변경처리
						Random rand = new Random();
						Integer suffix = new Integer(rand.nextInt(10000));
						
						int index = fileName.lastIndexOf("."); 
						
						String onlyFileName = fileName.substring(0, index);
						String ext = fileName.substring(index);
						
						fileEntry = DLAppLocalServiceUtil.addFileEntry(
							userId,	groupId,	dlFolder.getFolderId(),
							fileName, contentType, onlyFileName+"_"+CustomUtil.getRandomString(3)+ext, 
							fileName, "", FileUtil.getBytes(tempFile), 
							sctx
							);
					}
					
					tempFile.delete();
				}
			}
		}
		long fileEntryId = 0;
		if(fileEntry != null){
			fileEntryId = fileEntry.getFileEntryId();
		}
		return fileEntryId;
	}
}