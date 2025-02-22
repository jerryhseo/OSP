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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the data collection layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataCollectionLayoutPersistenceImpl
 * @see DataCollectionLayoutUtil
 * @generated
 */
public interface DataCollectionLayoutPersistence extends BasePersistence<DataCollectionLayout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataCollectionLayoutUtil} to access the data collection layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the data collection layout where layoutStr = &#63; or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	*
	* @param layoutStr the layout str
	* @return the matching data collection layout
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout findByLayout(
		java.lang.String layoutStr)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param layoutStr the layout str
	* @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout fetchByLayout(
		java.lang.String layoutStr)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection layout where layoutStr = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param layoutStr the layout str
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data collection layout, or <code>null</code> if a matching data collection layout could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout fetchByLayout(
		java.lang.String layoutStr, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data collection layout where layoutStr = &#63; from the database.
	*
	* @param layoutStr the layout str
	* @return the data collection layout that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout removeByLayout(
		java.lang.String layoutStr)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data collection layouts where layoutStr = &#63;.
	*
	* @param layoutStr the layout str
	* @return the number of matching data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public int countByLayout(java.lang.String layoutStr)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data collection layout in the entity cache if it is enabled.
	*
	* @param dataCollectionLayout the data collection layout
	*/
	public void cacheResult(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout);

	/**
	* Caches the data collection layouts in the entity cache if it is enabled.
	*
	* @param dataCollectionLayouts the data collection layouts
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> dataCollectionLayouts);

	/**
	* Creates a new data collection layout with the primary key. Does not add the data collection layout to the database.
	*
	* @param collectionId the primary key for the new data collection layout
	* @return the new data collection layout
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout create(
		long collectionId);

	/**
	* Removes the data collection layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout that was removed
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout remove(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.icecap.model.DataCollectionLayout updateImpl(
		com.kisti.osp.icecap.model.DataCollectionLayout dataCollectionLayout)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection layout with the primary key or throws a {@link com.kisti.osp.icecap.NoSuchDataCollectionLayoutException} if it could not be found.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout
	* @throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout findByPrimaryKey(
		long collectionId)
		throws com.kisti.osp.icecap.NoSuchDataCollectionLayoutException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data collection layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collectionId the primary key of the data collection layout
	* @return the data collection layout, or <code>null</code> if a data collection layout with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.icecap.model.DataCollectionLayout fetchByPrimaryKey(
		long collectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data collection layouts.
	*
	* @return the data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.kisti.osp.icecap.model.DataCollectionLayout> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data collection layouts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data collection layouts.
	*
	* @return the number of data collection layouts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}