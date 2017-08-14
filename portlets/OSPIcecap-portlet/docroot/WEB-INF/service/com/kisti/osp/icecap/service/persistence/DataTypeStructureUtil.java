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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the data type structure service. This utility wraps {@link DataTypeStructurePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeStructurePersistence
 * @see DataTypeStructurePersistenceImpl
 * @generated
 */
public class DataTypeStructureUtil {
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
	public static void clearCache(DataTypeStructure dataTypeStructure) {
		getPersistence().clearCache(dataTypeStructure);
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
	public static List<DataTypeStructure> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataTypeStructure> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataTypeStructure> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataTypeStructure update(DataTypeStructure dataTypeStructure)
		throws SystemException {
		return getPersistence().update(dataTypeStructure);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataTypeStructure update(
		DataTypeStructure dataTypeStructure, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(dataTypeStructure, serviceContext);
	}

	/**
	* Returns the data type structure where typeId = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param typeId the type ID
	* @return the matching data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure findByTypeID(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTypeID(typeId);
	}

	/**
	* Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeId the type ID
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure fetchByTypeID(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID(typeId);
	}

	/**
	* Returns the data type structure where typeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeId the type ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure fetchByTypeID(
		long typeId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypeID(typeId, retrieveFromCache);
	}

	/**
	* Removes the data type structure where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	* @return the data type structure that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure removeByTypeID(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByTypeID(typeId);
	}

	/**
	* Returns the number of data type structures where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching data type structures
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTypeID(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTypeID(typeId);
	}

	/**
	* Returns the data type structure where structure = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param structure the structure
	* @return the matching data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure findByStructure(
		java.lang.String structure)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStructure(structure);
	}

	/**
	* Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param structure the structure
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure fetchByStructure(
		java.lang.String structure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStructure(structure);
	}

	/**
	* Returns the data type structure where structure = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param structure the structure
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data type structure, or <code>null</code> if a matching data type structure could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure fetchByStructure(
		java.lang.String structure, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStructure(structure, retrieveFromCache);
	}

	/**
	* Removes the data type structure where structure = &#63; from the database.
	*
	* @param structure the structure
	* @return the data type structure that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure removeByStructure(
		java.lang.String structure)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByStructure(structure);
	}

	/**
	* Returns the number of data type structures where structure = &#63;.
	*
	* @param structure the structure
	* @return the number of matching data type structures
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStructure(java.lang.String structure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStructure(structure);
	}

	/**
	* Caches the data type structure in the entity cache if it is enabled.
	*
	* @param dataTypeStructure the data type structure
	*/
	public static void cacheResult(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure) {
		getPersistence().cacheResult(dataTypeStructure);
	}

	/**
	* Caches the data type structures in the entity cache if it is enabled.
	*
	* @param dataTypeStructures the data type structures
	*/
	public static void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> dataTypeStructures) {
		getPersistence().cacheResult(dataTypeStructures);
	}

	/**
	* Creates a new data type structure with the primary key. Does not add the data type structure to the database.
	*
	* @param typeId the primary key for the new data type structure
	* @return the new data type structure
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure create(
		long typeId) {
		return getPersistence().create(typeId);
	}

	/**
	* Removes the data type structure with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure remove(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(typeId);
	}

	public static com.kisti.osp.icecap.model.DataTypeStructure updateImpl(
		com.kisti.osp.icecap.model.DataTypeStructure dataTypeStructure)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataTypeStructure);
	}

	/**
	* Returns the data type structure with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataTypeStructureException} if it could not be found.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure
	* @throws com.kisti.osp.icecap.NoSuchDataTypeStructureException if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure findByPrimaryKey(
		long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeStructureException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(typeId);
	}

	/**
	* Returns the data type structure with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param typeId the primary key of the data type structure
	* @return the data type structure, or <code>null</code> if a data type structure with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeStructure fetchByPrimaryKey(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(typeId);
	}

	/**
	* Returns all the data type structures.
	*
	* @return the data type structures
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeStructure> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data type structures from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data type structures.
	*
	* @return the number of data type structures
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataTypeStructurePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataTypeStructurePersistence)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataTypeStructurePersistence.class.getName());

			ReferenceRegistry.registerReference(DataTypeStructureUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataTypeStructurePersistence persistence) {
	}

	private static DataTypeStructurePersistence _persistence;
}