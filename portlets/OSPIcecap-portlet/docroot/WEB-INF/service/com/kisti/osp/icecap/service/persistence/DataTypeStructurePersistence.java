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

import com.kisti.osp.icecap.model.DataTypeStructure;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data type structure service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructurePersistenceImpl
 * @see DataTypeStructureUtil
 * @generated
 */
public interface DataTypeStructurePersistence extends BasePersistence<DataTypeStructure> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataTypeStructureUtil} to access the data type structure persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the data type structure where typeId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param typeId the type ID
	* @return the matching data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure findByTypeID(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeId the type ID
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure fetchByTypeID(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeId the type ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure fetchByTypeID(
		long typeId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data type structure where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @return the data type structure that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure removeByTypeID(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type structures where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data type structures
	* @throws SystemException if a system exception occurred
	*/
	public int countByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure where structure = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param structure the structure
	* @return the matching data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure findByStructure(
		java.lang.String structure)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param structure the structure
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure fetchByStructure(
		java.lang.String structure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param structure the structure
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure fetchByStructure(
		java.lang.String structure, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data type structure where structure = &#63; from the database.
	*
	* @param structure the structure
	* @return the data type structure that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure removeByStructure(
		java.lang.String structure)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type structures where structure = &#63;.
	*
	* @param structure the structure
	* @return the number of matching data type structures
	* @throws SystemException if a system exception occurred
	*/
	public int countByStructure(java.lang.String structure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data type structure in the entity cache if it is enabled.
	*
	* @param dataTypeStructure the data type structure
	*/
	public void cacheResult(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure);

	/**
	* Caches the data type structures in the entity cache if it is enabled.
	*
	* @param dataTypeStructures the data type structures
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> dataTypeStructures);

	/**
	* Creates a new data type structure with the primary key. Does not add the data type structure to the database.
	*
	* @param typeId the primary key for the new data type structure
	* @return the new data type structure
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure create(long typeId);

	/**
	* Removes the data type structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure remove(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataTypeStructure updateImpl(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure findByPrimaryKey(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data type structure with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure, or <code>null</code> if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataTypeStructure fetchByPrimaryKey(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data type structures.
	*
	* @return the data type structures
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the data type structures.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type structures
	* @param end the upper bound of the range of data type structures (not inclusive)
	* @return the range of data type structures
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the data type structures.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type structures
	* @param end the upper bound of the range of data type structures (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data type structures
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data type structures from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data type structures.
	*
	* @return the number of data type structures
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}