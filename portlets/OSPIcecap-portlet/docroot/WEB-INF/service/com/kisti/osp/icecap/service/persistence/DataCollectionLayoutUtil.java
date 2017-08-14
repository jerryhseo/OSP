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

import com.kisti.osp.icecap.model.DataCollectionLayout;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the data collection layout service. This utility wraps {@link DataCollectionLayoutPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLayoutPersistence
 * @see DataCollectionLayoutPersistenceImpl
 * @generated
 */
public class DataCollectionLayoutUtil {
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
	public static void clearCache(DataCollectionLayout dataCollectionLayout) {
		getPersistence().clearCache(dataCollectionLayout);
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
	public static List<DataCollectionLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataCollectionLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataCollectionLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataCollectionLayout update(
		DataCollectionLayout dataCollectionLayout) throws SystemException {
		return getPersistence().update(dataCollectionLayout);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataCollectionLayout update(
		DataCollectionLayout dataCollectionLayout, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(dataCollectionLayout, serviceContext);
	}

	/**
	* Returns the data collection layout where layoutStr = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	*
	* @param layoutStr the layout str
	* @return the matching data collection layout
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout findByLayout(
		java.lang.String layoutStr)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLayout(layoutStr);
	}

	/**
	* Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param layoutStr the layout str
	* @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout fetchByLayout(
		java.lang.String layoutStr)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLayout(layoutStr);
	}

	/**
	* Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param layoutStr the layout str
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout fetchByLayout(
		java.lang.String layoutStr, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLayout(layoutStr, retrieveFromCache);
	}

	/**
	* Removes the data collection layout where layoutStr = &#63; from the database.
	*
	* @param layoutStr the layout str
	* @return the data collection layout that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout removeByLayout(
		java.lang.String layoutStr)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByLayout(layoutStr);
	}

	/**
	* Returns the number of data collection layouts where layoutStr = &#63;.
	*
	* @param layoutStr the layout str
	* @return the number of matching data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLayout(java.lang.String layoutStr)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLayout(layoutStr);
	}

	/**
	* Caches the data collection layout in the entity cache if it is enabled.
	*
	* @param dataCollectionLayout the data collection layout
	*/
	public static void cacheResult(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout) {
		getPersistence().cacheResult(dataCollectionLayout);
	}

	/**
	* Caches the data collection layouts in the entity cache if it is enabled.
	*
	* @param dataCollectionLayouts the data collection layouts
	*/
	public static void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> dataCollectionLayouts) {
		getPersistence().cacheResult(dataCollectionLayouts);
	}

	/**
	* Creates a new data collection layout with the primary key. Does not add the data collection layout to the database.
	*
	* @param collectionId the primary key for the new data collection layout
	* @return the new data collection layout
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout create(
		long collectionId) {
		return getPersistence().create(collectionId);
	}

	/**
	* Removes the data collection layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout remove(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(collectionId);
	}

	public static com.kisti.osp.icecap.model.DataCollectionLayout updateImpl(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataCollectionLayout);
	}

	/**
	* Returns the data collection layout with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout findByPrimaryKey(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(collectionId);
	}

	/**
	* Returns the data collection layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout, or <code>null</code> if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataCollectionLayout fetchByPrimaryKey(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(collectionId);
	}

	/**
	* Returns all the data collection layouts.
	*
	* @return the data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the data collection layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data collection layouts
	* @param end the upper bound of the range of data collection layouts (not inclusive)
	* @return the range of data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the data collection layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataCollectionLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data collection layouts
	* @param end the upper bound of the range of data collection layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data collection layouts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data collection layouts.
	*
	* @return the number of data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataCollectionLayoutPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataCollectionLayoutPersistence)PortletBeanLocatorUtil.locate(com.kisti.osp.icecap.service.ClpSerializer.getServletContextName(),
					DataCollectionLayoutPersistence.class.getName());

			ReferenceRegistry.registerReference(DataCollectionLayoutUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataCollectionLayoutPersistence persistence) {
	}

	private static DataCollectionLayoutPersistence _persistence;
}