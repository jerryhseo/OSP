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

package org.kisti.edison.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.model.Contents;

import java.util.List;

/**
 * The persistence utility for the contents service. This utility wraps {@link ContentsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ContentsPersistence
 * @see ContentsPersistenceImpl
 * @generated
 */
public class ContentsUtil {
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
	public static void clearCache(Contents contents) {
		getPersistence().clearCache(contents);
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
	public static List<Contents> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Contents> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Contents> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Contents update(Contents contents) throws SystemException {
		return getPersistence().update(contents);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Contents update(Contents contents,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(contents, serviceContext);
	}

	/**
	* Caches the contents in the entity cache if it is enabled.
	*
	* @param contents the contents
	*/
	public static void cacheResult(org.kisti.edison.model.Contents contents) {
		getPersistence().cacheResult(contents);
	}

	/**
	* Caches the contentses in the entity cache if it is enabled.
	*
	* @param contentses the contentses
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.model.Contents> contentses) {
		getPersistence().cacheResult(contentses);
	}

	/**
	* Creates a new contents with the primary key. Does not add the contents to the database.
	*
	* @param createDate the primary key for the new contents
	* @return the new contents
	*/
	public static org.kisti.edison.model.Contents create(
		java.lang.String createDate) {
		return getPersistence().create(createDate);
	}

	/**
	* Removes the contents with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param createDate the primary key of the contents
	* @return the contents that was removed
	* @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.Contents remove(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchContentsException {
		return getPersistence().remove(createDate);
	}

	public static org.kisti.edison.model.Contents updateImpl(
		org.kisti.edison.model.Contents contents)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(contents);
	}

	/**
	* Returns the contents with the primary key or throws a {@link org.kisti.edison.NoSuchContentsException} if it could not be found.
	*
	* @param createDate the primary key of the contents
	* @return the contents
	* @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.Contents findByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchContentsException {
		return getPersistence().findByPrimaryKey(createDate);
	}

	/**
	* Returns the contents with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param createDate the primary key of the contents
	* @return the contents, or <code>null</code> if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.Contents fetchByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(createDate);
	}

	/**
	* Returns all the contentses.
	*
	* @return the contentses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.Contents> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the contentses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ContentsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contentses
	* @param end the upper bound of the range of contentses (not inclusive)
	* @return the range of contentses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.Contents> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the contentses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.ContentsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contentses
	* @param end the upper bound of the range of contentses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contentses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.Contents> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the contentses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contentses.
	*
	* @return the number of contentses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ContentsPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ContentsPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.service.ClpSerializer.getServletContextName(),
					ContentsPersistence.class.getName());

			ReferenceRegistry.registerReference(ContentsUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ContentsPersistence persistence) {
	}

	private static ContentsPersistence _persistence;
}