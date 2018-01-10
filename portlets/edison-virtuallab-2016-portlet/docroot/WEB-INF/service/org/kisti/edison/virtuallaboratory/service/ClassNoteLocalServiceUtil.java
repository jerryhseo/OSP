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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ClassNote. This utility wraps
 * {@link org.kisti.edison.virtuallaboratory.service.impl.ClassNoteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author EDISON
 * @see ClassNoteLocalService
 * @see org.kisti.edison.virtuallaboratory.service.base.ClassNoteLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.impl.ClassNoteLocalServiceImpl
 * @generated
 */
public class ClassNoteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.virtuallaboratory.service.impl.ClassNoteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the class note to the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote addClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addClassNote(classNote);
	}

	/**
	* Creates a new class note with the primary key. Does not add the class note to the database.
	*
	* @param classNoteSeq the primary key for the new class note
	* @return the new class note
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote createClassNote(
		long classNoteSeq) {
		return getService().createClassNote(classNoteSeq);
	}

	/**
	* Deletes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note that was removed
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteClassNote(classNoteSeq);
	}

	/**
	* Deletes the class note from the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteClassNote(classNote);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchClassNote(classNoteSeq);
	}

	/**
	* Returns the class note with the primary key.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote getClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getClassNote(classNoteSeq);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> getClassNotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getClassNotes(start, end);
	}

	/**
	* Returns the number of class notes.
	*
	* @return the number of class notes
	* @throws SystemException if a system exception occurred
	*/
	public static int getClassNotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getClassNotesCount();
	}

	/**
	* Updates the class note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote updateClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateClassNote(classNote);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualLabClassNoteList(
		long classId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getVirtualLabClassNoteList(classId, locale);
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
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrievetListClassNote(
		java.util.Locale locale, java.lang.String searchText, int start,
		int end, long groupId, long companyGroupId) throws java.lang.Exception {
		return getService()
				   .retrievetListClassNote(locale, searchText, start, end,
			groupId, companyGroupId);
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
	public static int retrieveCountClassNote(long companyGroupId, long groupId,
		java.util.Locale locale, java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveCountClassNote(companyGroupId, groupId, locale,
			searchText);
	}

	/**
	* @param classId
	* @param isContent
	* @param locale
	* @return
	* @throws SystemException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListRelatedClassNote(
		long classId, boolean isContent, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .retrieveListRelatedClassNote(classId, isContent, locale);
	}

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getService().retrieveClassNote(classNoteSeq);
	}

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getService().retrieveClassNote(classId, contentSeq);
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
	public static org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.String> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return getService()
				   .addFileClassNote(upload, request, groupId, userId, param,
			locale);
	}

	public static org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		javax.portlet.PortletRequest request, long contentSeq, long classId,
		long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.Object> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException {
		return getService()
				   .addFileClassNote(request, contentSeq, classId, groupId,
			userId, param, locale);
	}

	/**
	* 燁삳똾�믤�醫듼봺 獄쏄퀣肉�占쎌빘苑�占쎈벏鍮�칰占쎄퉳占쏙옙占싼딆뒠 占쎈뜇釉� �꾩꼹�쀯㎘醫딅뮉 占싼뗪퉱占쎈Ŋ苑뚳옙占썹㎉�꾨��⑥쥓�곮퉪袁⑥쨮 鈺곌퀬�띰옙�륅옙 占쎈봿�앲첋占쎌쨮 parentCategory 揶쏉옙0占쏙옙     * 占싼딆뵠占쏙옙占싼뗪퉱)占쏙옙占쏙옙鍮먲옙占퐊ull占쏙옙獄쏆꼹�싷옙占�     *
	* @param companyGroupId
	* @param groupId
	* @return long[]
	* @throws PortalException
	* @throws SystemException
	*/
	public static long[] makeCategoryEntryList(long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().makeCategoryEntryList(companyGroupId, groupId);
	}

	public static long uploadDLfileByContentFile(
		javax.portlet.PortletRequest request, long classId, long contentSeq,
		long groupId, long userId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .uploadDLfileByContentFile(request, classId, contentSeq,
			groupId, userId, locale);
	}

	public static void clearService() {
		_service = null;
	}

	public static ClassNoteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ClassNoteLocalService.class.getName());

			if (invokableLocalService instanceof ClassNoteLocalService) {
				_service = (ClassNoteLocalService)invokableLocalService;
			}
			else {
				_service = new ClassNoteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ClassNoteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ClassNoteLocalService service) {
	}

	private static ClassNoteLocalService _service;
}