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

package com.kisti.osp.icecap.service.persistence;

import com.kisti.osp.icecap.model.DataTypeEditor;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the data type editor service. This utility wraps {@link DataTypeEditorPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditorPersistence
 * @see DataTypeEditorPersistenceImpl
 * @generated
 */
public class DataTypeEditorUtil {
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
	public static void clearCache(DataTypeEditor dataTypeEditor) {
		getPersistence().clearCache(dataTypeEditor);
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
	public static List<DataTypeEditor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataTypeEditor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataTypeEditor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataTypeEditor update(DataTypeEditor dataTypeEditor)
		throws SystemException {
		return getPersistence().update(dataTypeEditor);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataTypeEditor update(DataTypeEditor dataTypeEditor,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dataTypeEditor, serviceContext);
	}

	/**
	* Returns all the data type editors where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @return the matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEditor(editorId);
	}

	/**
	* Returns a range of all the data type editors where editorId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param editorId the editor ID
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @return the range of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEditor(editorId, start, end);
	}

	/**
	* Returns an ordered range of all the data type editors where editorId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param editorId the editor ID
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEditor(editorId, start, end, orderByComparator);
	}

	/**
	* Returns the first data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor findByEditor_First(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEditor_First(editorId, orderByComparator);
	}

	/**
	* Returns the first data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor fetchByEditor_First(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEditor_First(editorId, orderByComparator);
	}

	/**
	* Returns the last data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor findByEditor_Last(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEditor_Last(editorId, orderByComparator);
	}

	/**
	* Returns the last data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor fetchByEditor_Last(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEditor_Last(editorId, orderByComparator);
	}

	/**
	* Returns the data type editors before and after the current data type editor in the ordered set where editorId = &#63;.
	*
	* @param dataTypeEditorPK the primary key of the current data type editor
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor[] findByEditor_PrevAndNext(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK,
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEditor_PrevAndNext(dataTypeEditorPK, editorId,
			orderByComparator);
	}

	/**
	* Removes all the data type editors where editorId = &#63; from the database.
	*
	* @param editorId the editor ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEditor(long editorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEditor(editorId);
	}

	/**
	* Returns the number of data type editors where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @return the number of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEditor(long editorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEditor(editorId);
	}

	/**
	* Returns all the data type editors where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID(typeId);
	}

	/**
	* Returns a range of all the data type editors where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @return the range of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID(typeId, start, end);
	}

	/**
	* Returns an ordered range of all the data type editors where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTypeID(typeId, start, end, orderByComparator);
	}

	/**
	* Returns the first data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor findByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID_First(typeId, orderByComparator);
	}

	/**
	* Returns the first data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor fetchByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID_First(typeId, orderByComparator);
	}

	/**
	* Returns the last data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor findByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID_Last(typeId, orderByComparator);
	}

	/**
	* Returns the last data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor fetchByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID_Last(typeId, orderByComparator);
	}

	/**
	* Returns the data type editors before and after the current data type editor in the ordered set where typeId = &#63;.
	*
	* @param dataTypeEditorPK the primary key of the current data type editor
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor[] findByTypeID_PrevAndNext(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK,
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTypeID_PrevAndNext(dataTypeEditorPK, typeId,
			orderByComparator);
	}

	/**
	* Removes all the data type editors where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTypeID(typeId);
	}

	/**
	* Returns the number of data type editors where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTypeID(typeId);
	}

	/**
	* Caches the data type editor in the entity cache if it is enabled.
	*
	* @param dataTypeEditor the data type editor
	*/
	public static void cacheResult(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor) {
		getPersistence().cacheResult(dataTypeEditor);
	}

	/**
	* Caches the data type editors in the entity cache if it is enabled.
	*
	* @param dataTypeEditors the data type editors
	*/
	public static void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> dataTypeEditors) {
		getPersistence().cacheResult(dataTypeEditors);
	}

	/**
	* Creates a new data type editor with the primary key. Does not add the data type editor to the database.
	*
	* @param dataTypeEditorPK the primary key for the new data type editor
	* @return the new data type editor
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor create(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK) {
		return getPersistence().create(dataTypeEditorPK);
	}

	/**
	* Removes the data type editor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor remove(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(dataTypeEditorPK);
	}

	public static com.kisti.osp.icecap.model.DataTypeEditor updateImpl(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataTypeEditor);
	}

	/**
	* Returns the data type editor with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeEditorException} if it could not be found.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor findByPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(dataTypeEditorPK);
	}

	/**
	* Returns the data type editor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor, or <code>null</code> if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor fetchByPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(dataTypeEditorPK);
	}

	/**
	* Returns all the data type editors.
	*
	* @return the data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the data type editors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @return the range of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the data type editors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data type editors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data type editors.
	*
	* @return the number of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataTypeEditorPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataTypeEditorPersistence)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataTypeEditorPersistence.class.getName());

			ReferenceRegistry.registerReference(DataTypeEditorUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataTypeEditorPersistence persistence) {
	}

	private static DataTypeEditorPersistence _persistence;
}