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

package org.kisti.edison.virtuallaboratory.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.virtuallaboratory.model.ClassNote;

import java.util.List;

/**
 * The persistence utility for the class note service. This utility wraps {@link ClassNotePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ClassNotePersistence
 * @see ClassNotePersistenceImpl
 * @generated
 */
public class ClassNoteUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ClassNote classNote) {
		getPersistence().clearCache(classNote);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ClassNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ClassNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ClassNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ClassNote update(ClassNote classNote)
		throws SystemException {
		return getPersistence().update(classNote);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ClassNote update(ClassNote classNote,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(classNote, serviceContext);
	}

	/**
	* Returns all the class notes where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @return the matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContent(
		long classId, java.lang.String isContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByclassIdIsContent(classId, isContent);
	}

	/**
	* Returns a range of all the class notes where classId = &#63; and isContent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @return the range of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContent(
		long classId, java.lang.String isContent, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByclassIdIsContent(classId, isContent, start, end);
	}

	/**
	* Returns an ordered range of all the class notes where classId = &#63; and isContent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContent(
		long classId, java.lang.String isContent, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByclassIdIsContent(classId, isContent, start, end,
			orderByComparator);
	}

	/**
	* Returns the first class note in the ordered set where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote findByclassIdIsContent_First(
		long classId, java.lang.String isContent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContent_First(classId, isContent,
			orderByComparator);
	}

	/**
	* Returns the first class note in the ordered set where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching class note, or <code>null</code> if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchByclassIdIsContent_First(
		long classId, java.lang.String isContent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByclassIdIsContent_First(classId, isContent,
			orderByComparator);
	}

	/**
	* Returns the last class note in the ordered set where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote findByclassIdIsContent_Last(
		long classId, java.lang.String isContent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContent_Last(classId, isContent,
			orderByComparator);
	}

	/**
	* Returns the last class note in the ordered set where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching class note, or <code>null</code> if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchByclassIdIsContent_Last(
		long classId, java.lang.String isContent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByclassIdIsContent_Last(classId, isContent,
			orderByComparator);
	}

	/**
	* Returns the class notes before and after the current class note in the ordered set where classId = &#63; and isContent = &#63;.
	*
	* @param classNoteSeq the primary key of the current class note
	* @param classId the class ID
	* @param isContent the is content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote[] findByclassIdIsContent_PrevAndNext(
		long classNoteSeq, long classId, java.lang.String isContent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContent_PrevAndNext(classNoteSeq, classId,
			isContent, orderByComparator);
	}

	/**
	* Removes all the class notes where classId = &#63; and isContent = &#63; from the database.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByclassIdIsContent(long classId,
		java.lang.String isContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByclassIdIsContent(classId, isContent);
	}

	/**
	* Returns the number of class notes where classId = &#63; and isContent = &#63;.
	*
	* @param classId the class ID
	* @param isContent the is content
	* @return the number of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByclassIdIsContent(long classId,
		java.lang.String isContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByclassIdIsContent(classId, isContent);
	}

	/**
	* Returns all the class notes where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @return the matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContentSeq(
		long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByclassIdIsContentSeq(classId, contentSeq);
	}

	/**
	* Returns a range of all the class notes where classId = &#63; and contentSeq = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @return the range of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContentSeq(
		long classId, long contentSeq, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByclassIdIsContentSeq(classId, contentSeq, start, end);
	}

	/**
	* Returns an ordered range of all the class notes where classId = &#63; and contentSeq = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findByclassIdIsContentSeq(
		long classId, long contentSeq, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByclassIdIsContentSeq(classId, contentSeq, start, end,
			orderByComparator);
	}

	/**
	* Returns the first class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote findByclassIdIsContentSeq_First(
		long classId, long contentSeq,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContentSeq_First(classId, contentSeq,
			orderByComparator);
	}

	/**
	* Returns the first class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching class note, or <code>null</code> if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchByclassIdIsContentSeq_First(
		long classId, long contentSeq,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByclassIdIsContentSeq_First(classId, contentSeq,
			orderByComparator);
	}

	/**
	* Returns the last class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote findByclassIdIsContentSeq_Last(
		long classId, long contentSeq,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContentSeq_Last(classId, contentSeq,
			orderByComparator);
	}

	/**
	* Returns the last class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching class note, or <code>null</code> if a matching class note could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchByclassIdIsContentSeq_Last(
		long classId, long contentSeq,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByclassIdIsContentSeq_Last(classId, contentSeq,
			orderByComparator);
	}

	/**
	* Returns the class notes before and after the current class note in the ordered set where classId = &#63; and contentSeq = &#63;.
	*
	* @param classNoteSeq the primary key of the current class note
	* @param classId the class ID
	* @param contentSeq the content seq
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote[] findByclassIdIsContentSeq_PrevAndNext(
		long classNoteSeq, long classId, long contentSeq,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence()
				   .findByclassIdIsContentSeq_PrevAndNext(classNoteSeq,
			classId, contentSeq, orderByComparator);
	}

	/**
	* Removes all the class notes where classId = &#63; and contentSeq = &#63; from the database.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByclassIdIsContentSeq(long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByclassIdIsContentSeq(classId, contentSeq);
	}

	/**
	* Returns the number of class notes where classId = &#63; and contentSeq = &#63;.
	*
	* @param classId the class ID
	* @param contentSeq the content seq
	* @return the number of matching class notes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByclassIdIsContentSeq(long classId, long contentSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByclassIdIsContentSeq(classId, contentSeq);
	}

	/**
	* Caches the class note in the entity cache if it is enabled.
	*
	* @param classNote the class note
	*/
	public static void cacheResult(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote) {
		getPersistence().cacheResult(classNote);
	}

	/**
	* Caches the class notes in the entity cache if it is enabled.
	*
	* @param classNotes the class notes
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> classNotes) {
		getPersistence().cacheResult(classNotes);
	}

	/**
	* Creates a new class note with the primary key. Does not add the class note to the database.
	*
	* @param classNoteSeq the primary key for the new class note
	* @return the new class note
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote create(
		long classNoteSeq) {
		return getPersistence().create(classNoteSeq);
	}

	/**
	* Removes the class note with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note that was removed
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote remove(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence().remove(classNoteSeq);
	}

	public static org.kisti.edison.virtuallaboratory.model.ClassNote updateImpl(
		org.kisti.edison.virtuallaboratory.model.ClassNote classNote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(classNote);
	}

	/**
	* Returns the class note with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchClassNoteException} if it could not be found.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note
	* @throws org.kisti.edison.virtuallaboratory.NoSuchClassNoteException if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote findByPrimaryKey(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchClassNoteException {
		return getPersistence().findByPrimaryKey(classNoteSeq);
	}

	/**
	* Returns the class note with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param classNoteSeq the primary key of the class note
	* @return the class note, or <code>null</code> if a class note with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.ClassNote fetchByPrimaryKey(
		long classNoteSeq)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(classNoteSeq);
	}

	/**
	* Returns all the class notes.
	*
	* @return the class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the class notes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.ClassNoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of class notes
	* @param end the upper bound of the range of class notes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of class notes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.ClassNote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the class notes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of class notes.
	*
	* @return the number of class notes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ClassNotePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ClassNotePersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					ClassNotePersistence.class.getName());

			ReferenceRegistry.registerReference(ClassNoteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ClassNotePersistence persistence) {
	}

	private static ClassNotePersistence _persistence;
}