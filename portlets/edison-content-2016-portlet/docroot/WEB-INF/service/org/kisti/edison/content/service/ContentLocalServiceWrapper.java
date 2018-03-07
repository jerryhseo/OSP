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

package org.kisti.edison.content.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContentLocalService}.
 *
 * @author EDISON
 * @see ContentLocalService
 * @generated
 */
public class ContentLocalServiceWrapper implements ContentLocalService,
	ServiceWrapper<ContentLocalService> {
	public ContentLocalServiceWrapper(ContentLocalService contentLocalService) {
		_contentLocalService = contentLocalService;
	}

	/**
	* Adds the content to the database. Also notifies the appropriate model listeners.
	*
	* @param content the content
	* @return the content that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.content.model.Content addContent(
		org.kisti.edison.content.model.Content content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.addContent(content);
	}

	/**
	* Creates a new content with the primary key. Does not add the content to the database.
	*
	* @param contentSeq the primary key for the new content
	* @return the new content
	*/
	@Override
	public org.kisti.edison.content.model.Content createContent(long contentSeq) {
		return _contentLocalService.createContent(contentSeq);
	}

	/**
	* Deletes the content with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentSeq the primary key of the content
	* @return the content that was removed
	* @throws PortalException if a content with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.content.model.Content deleteContent(long contentSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.deleteContent(contentSeq);
	}

	/**
	* Deletes the content from the database. Also notifies the appropriate model listeners.
	*
	* @param content the content
	* @return the content that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.content.model.Content deleteContent(
		org.kisti.edison.content.model.Content content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.deleteContent(content);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contentLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.content.model.Content fetchContent(long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.fetchContent(contentSeq);
	}

	/**
	* Returns the content with the primary key.
	*
	* @param contentSeq the primary key of the content
	* @return the content
	* @throws PortalException if a content with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.content.model.Content getContent(long contentSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.getContent(contentSeq);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.content.model.impl.ContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contents
	* @param end the upper bound of the range of contents (not inclusive)
	* @return the range of contents
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.content.model.Content> getContents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.getContents(start, end);
	}

	/**
	* Returns the number of contents.
	*
	* @return the number of contents
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getContentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.getContentsCount();
	}

	/**
	* Updates the content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param content the content
	* @return the content that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.content.model.Content updateContent(
		org.kisti.edison.content.model.Content content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.updateContent(content);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _contentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_contentLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _contentLocalService.invokeMethod(name, parameterTypes, arguments);
	}

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
	@Override
	public java.util.Map<java.lang.String, java.lang.Object> retrieveMapContent(
		long companyId, java.util.Locale locale, long contentSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.lang.NumberFormatException {
		return _contentLocalService.retrieveMapContent(companyId, locale,
			contentSeq);
	}

	/**
	* 콘텐츠 리스트 조회 사이트 카테고리 생성하여 조회
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	콘텐츠 검색어
	* @param contentDiv
	콘텐츠 유형검색
	* @param start
	* @param end
	* @param locale
	* @param categoryJoin
	카테고리 테이블과 조인 여부
	* @return
	* @throws Exception
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListContent(
		long companyGroupId, long groupId, java.lang.String searchText,
		long[] contentDiv, int start, int end, java.util.Locale locale,
		boolean categoryJoin) throws java.lang.Exception {
		return _contentLocalService.retrieveListContent(companyGroupId,
			groupId, searchText, contentDiv, start, end, locale, categoryJoin);
	}

	/**
	* 강의노트 조회 서비스 - 강의실에서 사용
	*
	* @param categoryIds
	* @param searchText
	* @param start
	* @param end
	* @param locale
	* @return
	* @throws Exception
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrievetListClassNote(
		long[] categoryIds, java.lang.String searchText, int start, int end,
		java.util.Locale locale) throws java.lang.Exception {
		return _contentLocalService.retrievetListClassNote(categoryIds,
			searchText, start, end, locale);
	}

	/**
	* 강의노트 카운트 조회 서비스 - 강의실에서 사용
	*
	* @param categoryIds
	* @param searchText
	* @param languageId
	* @return
	*/
	@Override
	public int retrieveCountClassNote(long[] categoryIds,
		java.lang.String searchText, java.util.Locale locale) {
		return _contentLocalService.retrieveCountClassNote(categoryIds,
			searchText, locale);
	}

	/**
	* 강의노트의 콘텐츠 목록 조회 서비스 - 강의실에서 사용
	*
	* @param contentIds
	* @param locale
	* @return
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListRelatedClassNote(
		java.util.List<java.lang.Long> contentIds, java.util.Locale locale) {
		return _contentLocalService.retrieveListRelatedClassNote(contentIds,
			locale);
	}

	/**
	* 콘텐츠 리스트 조회 콘텐츠 목록 : 포털 -> categoryIds null
	*
	* @param categoryIds
	* @param searchText
	콘텐츠 검색어
	* @param contentDiv
	콘텐츠 유형검색
	* @param start
	* @param end
	* @param locale
	* @param categoryJoin
	카테고리 테이블과 조인 여부
	* @param isTotalSearch
	통합검색에서 조회하면 true - openYn, serviceLanguage 추가
	* @return
	* @throws Exception
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListContent(
		long[] categoryIds, java.lang.String searchText, long[] contentDiv,
		int start, int end, java.util.Locale locale, boolean categoryJoin,
		boolean isTotalSearch) throws java.lang.Exception {
		return _contentLocalService.retrieveListContent(categoryIds,
			searchText, contentDiv, start, end, locale, categoryJoin,
			isTotalSearch);
	}

	/**
	* 콘텐츠 카운트 조회 카테고리 배열 생성
	*
	* @param companyGroupId
	* @param groupId
	* @param searchText
	콘텐츠 검색어
	* @param contentDiv
	콘텐츠 유형검색
	* @param languageId
	* @param categoryJoin
	카테고리 테이블과 조인 여부
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public int retrieveCountContent(long companyGroupId, long groupId,
		java.lang.String searchText, long[] contentDiv,
		java.lang.String languageId, boolean categoryJoin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.retrieveCountContent(companyGroupId,
			groupId, searchText, contentDiv, languageId, categoryJoin);
	}

	/**
	* 콘텐츠 카운트 조회 카테고리 배열 파라미터로 받음
	*
	* @param categoryIds
	* @param searchText
	콘텐츠 검색어
	* @param contentDiv
	콘텐츠 유형검색
	* @param languageId
	* @param categoryJoin
	카테고리 테이블과 조인 여부
	* @param isTotalSearch
	통합검색에서 조회하면 true
	* @return
	*/
	@Override
	public int retrieveCountContent(long[] categoryIds,
		java.lang.String searchText, long[] contentDiv,
		java.lang.String languageId, boolean categoryJoin, boolean isTotalSearch) {
		return _contentLocalService.retrieveCountContent(categoryIds,
			searchText, contentDiv, languageId, categoryJoin, isTotalSearch);
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
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListUserContent(
		long companyGroupId, long parentGroupId, long groupId,
		java.lang.String searchText, int start, int end,
		java.util.Locale locale, long userId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.retrieveListUserContent(companyGroupId,
			parentGroupId, groupId, searchText, start, end, locale, userId,
			roleId);
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
	@Override
	public int retrieveCountUserContent(long companyGroupId,
		long parentGroupId, long groupId, java.lang.String searchText,
		java.lang.String languageId, long userId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.retrieveCountUserContent(companyGroupId,
			parentGroupId, groupId, searchText, languageId, userId, roleId);
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
	@Override
	public org.kisti.edison.content.model.Content createContent(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long companyId, long groupId,
		long userId, java.util.Map<java.lang.String, java.lang.Object> param)
		throws java.lang.Exception {
		return _contentLocalService.createContent(upload, request, companyId,
			groupId, userId, param);
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
	@Override
	public org.kisti.edison.content.model.Content updateContent(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long companyId, long groupId,
		long userId, java.util.Map<java.lang.String, java.lang.Object> param)
		throws java.lang.Exception {
		return _contentLocalService.updateContent(upload, request, companyId,
			groupId, userId, param);
	}

	/**
	* 콘텐츠 삭제
	*
	* @param companyId
	* @param groupId
	* @param param
	*/
	@Override
	public void deleteContent(long companyId, long groupId,
		java.util.Map<java.lang.String, java.lang.Object> param)
		throws java.lang.Exception {
		_contentLocalService.deleteContent(companyId, groupId, param);
	}

	/**
	* entryId와 관련된 콘텐츠 Entry 조회
	*
	* @param params
	* @return List<Map<String, Object>>
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> relatedAssetLinkedEntryContent(
		java.util.Map params) {
		return _contentLocalService.relatedAssetLinkedEntryContent(params);
	}

	/**
	* 관련정보에서 검색어를 이용해 검색한 콘텐츠 리스트
	*
	* @param params
	* @return List<Map<String, Object>>
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> searchAssetEntryModelContent(
		java.util.Map params) {
		return _contentLocalService.searchAssetEntryModelContent(params);
	}

	/**
	* 관련정보에서 검색어를 이용해 검색한 콘텐츠 카운트
	*
	* @param params
	* @return int
	*/
	@Override
	public int searchAssetEntryModelContentCount(java.util.Map params) {
		return _contentLocalService.searchAssetEntryModelContentCount(params);
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
	@Override
	public long contentAddAssetEntry(long companyId, long groupId,
		org.kisti.edison.content.model.Content content)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.contentAddAssetEntry(companyId, groupId,
			content);
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
	@Override
	public void updateContentInsertId(long groupId, long contentSeq,
		long userId, java.lang.String projectYn, long projectId)
		throws java.lang.Exception {
		_contentLocalService.updateContentInsertId(groupId, contentSeq, userId,
			projectYn, projectId);
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
	@Override
	public long[] makeCategoryEntryList(long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _contentLocalService.makeCategoryEntryList(companyGroupId,
			groupId);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getContentDatailListForProjectStatistics(
		java.lang.String projectYn, long columnId, long categoryId,
		java.lang.String languageId, java.util.Locale locale)
		throws java.lang.Exception {
		return _contentLocalService.getContentDatailListForProjectStatistics(projectYn,
			columnId, categoryId, languageId, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getContentForProjectList(
		long userId, java.lang.String searchText,
		java.lang.String projectCategoryId, java.lang.String languageId,
		int start, int end, java.util.Locale locale) throws java.lang.Exception {
		return _contentLocalService.getContentForProjectList(userId,
			searchText, projectCategoryId, languageId, start, end, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getContentCenterListForProjectStatistics(
		java.lang.String projectYn, java.lang.String propertyKey,
		java.util.Locale locale) throws java.lang.Exception {
		return _contentLocalService.getContentCenterListForProjectStatistics(projectYn,
			propertyKey, locale);
	}

	@Override
	public int getGeneralContentCountByGroupIdForProjectList(long userId,
		java.lang.String searchText, java.lang.String projectCategoryId,
		java.lang.String languageId) {
		return _contentLocalService.getGeneralContentCountByGroupIdForProjectList(userId,
			searchText, projectCategoryId, languageId);
	}

	@Override
	public void migrationGeneralContentTable(long companyGroupId, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_contentLocalService.migrationGeneralContentTable(companyGroupId,
			companyId);
	}

	@Override
	public void migrationAdvancedContentTable(long companyGroupId,
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_contentLocalService.migrationAdvancedContentTable(companyGroupId,
			companyId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ContentLocalService getWrappedContentLocalService() {
		return _contentLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedContentLocalService(
		ContentLocalService contentLocalService) {
		_contentLocalService = contentLocalService;
	}

	@Override
	public ContentLocalService getWrappedService() {
		return _contentLocalService;
	}

	@Override
	public void setWrappedService(ContentLocalService contentLocalService) {
		_contentLocalService = contentLocalService;
	}

	private ContentLocalService _contentLocalService;
}