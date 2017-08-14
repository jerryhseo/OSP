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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data type editor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditorPersistenceImpl
 * @see DataTypeEditorUtil
 * @generated
 */
public interface DataTypeEditorPersistence extends BasePersistence<DataTypeEditor> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataTypeEditorUtil} to access the data type editor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the data type editors where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @return the matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByEditor(
		long editorId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor findByEditor_First(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor fetchByEditor_First(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor findByEditor_Last(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type editor in the ordered set where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor fetchByEditor_Last(
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.kisti.osp.icecap.model.DataTypeEditor[] findByEditor_PrevAndNext(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK,
		long editorId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data type editors where editorId = &#63; from the database.
	*
	* @param editorId the editor ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEditor(long editorId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type editors where editorId = &#63;.
	*
	* @param editorId the editor ID
	* @return the number of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public int countByEditor(long editorId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data type editors where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findByTypeID(
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor findByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor fetchByTypeID_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor findByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type editor in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type editor, or <code>null</code> if a matching data type editor could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor fetchByTypeID_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.kisti.osp.icecap.model.DataTypeEditor[] findByTypeID_PrevAndNext(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK,
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data type editors where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type editors where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data type editors
	* @throws SystemException if a system exception occurred
	*/
	public int countByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data type editor in the entity cache if it is enabled.
	*
	* @param dataTypeEditor the data type editor
	*/
	public void cacheResult(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor);

	/**
	* Caches the data type editors in the entity cache if it is enabled.
	*
	* @param dataTypeEditors the data type editors
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> dataTypeEditors);

	/**
	* Creates a new data type editor with the primary key. Does not add the data type editor to the database.
	*
	* @param dataTypeEditorPK the primary key for the new data type editor
	* @return the new data type editor
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor create(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK);

	/**
	* Removes the data type editor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor remove(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataTypeEditor updateImpl(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type editor with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeEditorException} if it could not be found.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor
	* @throws com.kisti.osp.icecap.NoSuchDataTypeEditorException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor findByPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type editor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor, or <code>null</code> if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeEditor fetchByPrimaryKey(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data type editors.
	*
	* @return the data type editors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data type editors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type editors.
	*
	* @return the number of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}