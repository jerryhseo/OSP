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

package org.kisti.edison.virtuallaboratory.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ClassNoteLocalService}.
 *
 * @author EDISON
 * @see ClassNoteLocalService
 * @generated
 */
public class ClassNoteLocalServiceWrapper implements ClassNoteLocalService,
	ServiceWrapper<ClassNoteLocalService> {
	public ClassNoteLocalServiceWrapper(
		ClassNoteLocalService classNoteLocalService) {
		_classNoteLocalService = classNoteLocalService;
	}

	/**
	* Adds the class note to the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote addClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.addClassNote(classNote);
	}

	/**
	* Creates a new class note with the primary key. Does not add the class note to the database.
	*
	* @param classNoteSeq the primary key for the new class note
	* @return the new class note
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote createClassNote(
		long classNoteSeq) {
		return _classNoteLocalService.createClassNote(classNoteSeq);
	}

	/**
	* Deletes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note that was removed
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.deleteClassNote(classNoteSeq);
	}

	/**
	* Deletes the class note from the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.deleteClassNote(classNote);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _classNoteLocalService.dynamicQuery();
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
		return _classNoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _classNoteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _classNoteLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _classNoteLocalService.dynamicQueryCount(dynamicQuery);
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
		return _classNoteLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote fetchClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.fetchClassNote(classNoteSeq);
	}

	/**
	* Returns the class note with the primary key.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote getClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.getClassNote(classNoteSeq);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the class notes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @return the range of class notes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> getClassNotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.getClassNotes(start, end);
	}

	/**
	* Returns the number of class notes.
	*
	* @return the number of class notes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getClassNotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.getClassNotesCount();
	}

	/**
	* Updates the class note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote updateClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.updateClassNote(classNote);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _classNoteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_classNoteLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _classNoteLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualLabClassNoteList(
		long classId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.getVirtualLabClassNoteList(classId, locale);
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
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrievetListClassNote(
		java.util.Locale locale, java.lang.String searchText, int start,
		int end, long groupId, long companyGroupId) throws java.lang.Exception {
		return _classNoteLocalService.retrievetListClassNote(locale,
			searchText, start, end, groupId, companyGroupId);
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
	@Override
	public int retrieveCountClassNote(long companyGroupId, long groupId,
		java.util.Locale locale, java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.retrieveCountClassNote(companyGroupId,
			groupId, locale, searchText);
	}

	/**
	* @param classId
	* @param isContent
	* @param locale
	* @return
	* @throws SystemException
	*/
	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListRelatedClassNote(
		long classId, boolean isContent, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _classNoteLocalService.retrieveListRelatedClassNote(classId,
			isContent, locale);
	}

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return _classNoteLocalService.retrieveClassNote(classNoteSeq);
	}

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return _classNoteLocalService.retrieveClassNote(classId, contentSeq);
	}

	/**
	* 占쎈슣��占쎄퉭以�     *
	* @param upload
	* @param request
	* @param groupId
	* @param userId
	* @param param(classNoteSeq,
	classId, description)
	* @param locale
	*/
	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.String> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return _classNoteLocalService.addFileClassNote(upload, request,
			groupId, userId, param, locale);
	}

	@Override
	public org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		javax.portlet.PortletRequest request, long contentSeq, long classId,
		long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.Object> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return _classNoteLocalService.addFileClassNote(request, contentSeq,
			classId, groupId, userId, param, locale);
	}

	/**
	* 燁삳똾�믤�醫듼봺 獄쏄퀣肉�占쎌빘苑�占쎈벏鍮�칰占쎄퉳占쏙옙占싼딆뒠 占쎈뜇釉� �꾩꼹�쀯㎘醫딅뮉 占싼뗪퉱占쎈Ŋ苑뚳옙占썹㎉�꾨��⑥쥓�곮퉪袁⑥쨮 鈺곌퀬�띰옙�륅옙 占쎈봿�앲첋占쎌쨮 parentCategory 揶쏉옙0占쏙옙     * 占싼딆뵠占쏙옙占싼뗪퉱)占쏙옙占쏙옙鍮먲옙占퐊ull占쏙옙獄쏆꼹�싷옙占�     *
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
		return _classNoteLocalService.makeCategoryEntryList(companyGroupId,
			groupId);
	}

	@Override
	public long uploadDLfileByContentFile(
		javax.portlet.PortletRequest request, long classId, long contentSeq,
		long groupId, long userId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _classNoteLocalService.uploadDLfileByContentFile(request,
			classId, contentSeq, groupId, userId, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ClassNoteLocalService getWrappedClassNoteLocalService() {
		return _classNoteLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedClassNoteLocalService(
		ClassNoteLocalService classNoteLocalService) {
		_classNoteLocalService = classNoteLocalService;
	}

	@Override
	public ClassNoteLocalService getWrappedService() {
		return _classNoteLocalService;
	}

	@Override
	public void setWrappedService(ClassNoteLocalService classNoteLocalService) {
		_classNoteLocalService = classNoteLocalService;
	}

	private ClassNoteLocalService _classNoteLocalService;
}