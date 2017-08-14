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

import com.kisti.osp.icecap.model.DataType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypePersistenceImpl
 * @see DataTypeUtil
 * @generated
 */
public interface DataTypePersistence extends BasePersistence<DataType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataTypeUtil} to access the data type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the data types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where uuid = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByUuid_PrevAndNext(
		long typeId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where uuid = &#63; and groupId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the data type that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByUuid_C_PrevAndNext(
		long typeId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where name = &#63;.
	*
	* @param name the name
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where name = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByName_PrevAndNext(
		long typeId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where name = &#63;.
	*
	* @param name the name
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where status = &#63;.
	*
	* @param status the status
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where status = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByStatus_PrevAndNext(
		long typeId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where status = &#63;.
	*
	* @param status the status
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where name = &#63; and version = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeException} if it could not be found.
	*
	* @param name the name
	* @param version the version
	* @return the matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByNameVersion(
		java.lang.String name, java.lang.String version)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @param version the version
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByNameVersion(
		java.lang.String name, java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByNameVersion(
		java.lang.String name, java.lang.String version,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data type where name = &#63; and version = &#63; from the database.
	*
	* @param name the name
	* @param version the version
	* @return the data type that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType removeByNameVersion(
		java.lang.String name, java.lang.String version)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where name = &#63; and version = &#63;.
	*
	* @param name the name
	* @param version the version
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByNameVersion(java.lang.String name,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where groupId = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByGroupId_PrevAndNext(
		long typeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Name(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Name(
		long groupId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Name(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Name_First(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Name_First(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Name_Last(long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Name_Last(
		long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where groupId = &#63; and name = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByG_Name_PrevAndNext(
		long typeId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_Name(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_Name(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Status(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Status(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Status(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Status_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Status_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Status_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Status_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByG_Status_PrevAndNext(
		long typeId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_Status(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_Status(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where userId = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByUserId_PrevAndNext(
		long typeId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where typeId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeException} if it could not be found.
	*
	* @param typeId the type ID
	* @return the matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByTypeId(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where typeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeId the type ID
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByTypeId(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type where typeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeId the type ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByTypeId(long typeId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data type where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @return the data type that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType removeByTypeId(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByTypeId(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_OwnerId(
		long groupId, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where groupId = &#63; and ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_OwnerId(
		long groupId, long ownerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where groupId = &#63; and ownerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_OwnerId(
		long groupId, long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_OwnerId_First(
		long groupId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_OwnerId_First(
		long groupId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_OwnerId_Last(
		long groupId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_OwnerId_Last(
		long groupId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where groupId = &#63; and ownerId = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByG_OwnerId_PrevAndNext(
		long typeId, long groupId, long ownerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where groupId = &#63; and ownerId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_OwnerId(long groupId, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where groupId = &#63; and ownerId = &#63;.
	*
	* @param groupId the group ID
	* @param ownerId the owner ID
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_OwnerId(long groupId, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @return the matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Favorite(
		long groupId, boolean isFavorite)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types where groupId = &#63; and isFavorite = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Favorite(
		long groupId, boolean isFavorite, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types where groupId = &#63; and isFavorite = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findByG_Favorite(
		long groupId, boolean isFavorite, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Favorite_First(
		long groupId, boolean isFavorite,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data type in the ordered set where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Favorite_First(
		long groupId, boolean isFavorite,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByG_Favorite_Last(
		long groupId, boolean isFavorite,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data type in the ordered set where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data type, or <code>null</code> if a matching data type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByG_Favorite_Last(
		long groupId, boolean isFavorite,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data types before and after the current data type in the ordered set where groupId = &#63; and isFavorite = &#63;.
	*
	* @param typeId the primary key of the current data type
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType[] findByG_Favorite_PrevAndNext(
		long typeId, long groupId, boolean isFavorite,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types where groupId = &#63; and isFavorite = &#63; from the database.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_Favorite(long groupId, boolean isFavorite)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types where groupId = &#63; and isFavorite = &#63;.
	*
	* @param groupId the group ID
	* @param isFavorite the is favorite
	* @return the number of matching data types
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_Favorite(long groupId, boolean isFavorite)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data type in the entity cache if it is enabled.
	*
	* @param dataType the data type
	*/
	public void cacheResult(com.kisti.osp.icecap.model.DataType dataType);

	/**
	* Caches the data types in the entity cache if it is enabled.
	*
	* @param dataTypes the data types
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataType> dataTypes);

	/**
	* Creates a new data type with the primary key. Does not add the data type to the database.
	*
	* @param typeId the primary key for the new data type
	* @return the new data type
	*/
	public com.kisti.osp.icecap.model.DataType create(long typeId);

	/**
	* Removes the data type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the data type
	* @return the data type that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType remove(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataType updateImpl(
		com.kisti.osp.icecap.model.DataType dataType)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeException} if it could not be found.
	*
	* @param typeId the primary key of the data type
	* @return the data type
	* @throws com.kisti.osp.icecap.NoSuchDataTypeException if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType findByPrimaryKey(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param typeId the primary key of the data type
	* @return the data type, or <code>null</code> if a data type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataType fetchByPrimaryKey(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data types.
	*
	* @return the data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @return the range of data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data types
	* @param end the upper bound of the range of data types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data types
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data types.
	*
	* @return the number of data types
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}