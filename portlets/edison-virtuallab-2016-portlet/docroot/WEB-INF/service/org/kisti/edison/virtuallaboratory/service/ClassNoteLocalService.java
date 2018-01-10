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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for ClassNote. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author EDISON
 * @see ClassNoteLocalServiceUtil
 * @see org.kisti.edison.virtuallaboratory.service.base.ClassNoteLocalServiceBaseImpl
 * @see org.kisti.edison.virtuallaboratory.service.impl.ClassNoteLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ClassNoteLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ClassNoteLocalServiceUtil} to access the class note local service. Add custom service methods to {@link org.kisti.edison.virtuallaboratory.service.impl.ClassNoteLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the class note to the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.virtuallaboratory.model.ClassNote addClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new class note with the primary key. Does not add the class note to the database.
	*
	* @param classNoteSeq the primary key for the new class note
	* @return the new class note
	*/
	public org.kisti.edison.virtuallaboratory.model.ClassNote createClassNote(
		long classNoteSeq);

	/**
	* Deletes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note that was removed
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the class note from the database. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.virtuallaboratory.model.ClassNote deleteClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.virtuallaboratory.model.ClassNote fetchClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the class note with the primary key.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note
	* @throws PortalException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.virtuallaboratory.model.ClassNote getClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> getClassNotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of class notes.
	*
	* @return the number of class notes
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getClassNotesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the class note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param classNote the class note
	* @return the class note that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.virtuallaboratory.model.ClassNote updateClassNote(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getVirtualLabClassNoteList(
		long classId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @param locale
	* @param searchText
	* @param start
	* @param end
	* @param groupId
	* @param companyGroupId
	* @return
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrievetListClassNote(
		java.util.Locale locale, java.lang.String searchText, int start,
		int end, long groupId, long companyGroupId) throws java.lang.Exception;

	/**
	* @param companyGroupId
	* @param groupId
	* @param locale
	* @param searchText
	* @return
	* @throws SystemException
	* @throws PortalException
	*/
	public int retrieveCountClassNote(long companyGroupId, long groupId,
		java.util.Locale locale, java.lang.String searchText)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* @param classId
	* @param isContent
	* @param locale
	* @return
	* @throws SystemException
	*/
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveListRelatedClassNote(
		long classId, boolean isContent, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	public org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException;

	/**
	* @param classNoteSeq
	* @return
	* @throws SystemException
	* @throws NoSuchClassNoteException
	*/
	public org.kisti.edison.virtuallaboratory.model.ClassNote retrieveClassNote(
		long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException;

	/**
	* �뚯씪 �깅줉
	*
	* @param upload
	* @param request
	* @param groupId
	* @param userId
	* @param param(classNoteSeq,
	classId, description)
	* @param locale
	*/
	public org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		com.liferay.portal.kernel.upload.UploadPortletRequest upload,
		javax.portlet.PortletRequest request, long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.String> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException;

	public org.kisti.edison.virtuallaboratory.model.ClassNote addFileClassNote(
		javax.portlet.PortletRequest request, long contentSeq, long classId,
		long groupId, long userId,
		java.util.Map<java.lang.String, java.lang.Object> param,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException, java.sql.SQLException;

	/**
	* 移댄뀒怨좊━ 諛곗뿴 �앹꽦 �듯빀寃�깋���ъ슜 �덊븿. 肄섑뀗痢좊뒗 �ы깉�먯꽌��移댄뀒怨좊━蹂꾨줈 議고쉶�섏� �딆쑝誘�줈 parentCategory 媛�0��     * �ъ씠���ы깉)����빐��null��諛섑솚��
	*
	* @param companyGroupId
	* @param groupId
	* @return long[]
	* @throws PortalException
	* @throws SystemException
	*/
	public long[] makeCategoryEntryList(long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long uploadDLfileByContentFile(
		javax.portlet.PortletRequest request, long classId, long contentSeq,
		long groupId, long userId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;
}