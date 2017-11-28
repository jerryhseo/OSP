/**
 * Copyright (c) 2016-present EDISON, KISTI. All rights reserved.
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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.bestsimulation.model.ScienceAppExecute;

import java.util.List;

/**
 * The persistence utility for the science app execute service. This utility wraps {@link ScienceAppExecutePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppExecutePersistence
 * @see ScienceAppExecutePersistenceImpl
 * @generated
 */
public class ScienceAppExecuteUtil {
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
	public static void clearCache(ScienceAppExecute scienceAppExecute) {
		getPersistence().clearCache(scienceAppExecute);
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
	public static List<ScienceAppExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ScienceAppExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ScienceAppExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ScienceAppExecute update(ScienceAppExecute scienceAppExecute)
		throws SystemException {
		return getPersistence().update(scienceAppExecute);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ScienceAppExecute update(
		ScienceAppExecute scienceAppExecute, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(scienceAppExecute, serviceContext);
	}

	/**
	* Caches the science app execute in the entity cache if it is enabled.
	*
	* @param scienceAppExecute the science app execute
	*/
	public static void cacheResult(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute) {
		getPersistence().cacheResult(scienceAppExecute);
	}

	/**
	* Caches the science app executes in the entity cache if it is enabled.
	*
	* @param scienceAppExecutes the science app executes
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> scienceAppExecutes) {
		getPersistence().cacheResult(scienceAppExecutes);
	}

	/**
	* Creates a new science app execute with the primary key. Does not add the science app execute to the database.
	*
	* @param scienceAppExecutePK the primary key for the new science app execute
	* @return the new science app execute
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute create(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK) {
		return getPersistence().create(scienceAppExecutePK);
	}

	/**
	* Removes the science app execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute remove(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException {
		return getPersistence().remove(scienceAppExecutePK);
	}

	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute updateImpl(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(scienceAppExecute);
	}

	/**
	* Returns the science app execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException} if it could not be found.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute
	* @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException {
		return getPersistence().findByPrimaryKey(scienceAppExecutePK);
	}

	/**
	* Returns the science app execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute, or <code>null</code> if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.ScienceAppExecute fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(scienceAppExecutePK);
	}

	/**
	* Returns all the science app executes.
	*
	* @return the science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the science app executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app executes
	* @param end the upper bound of the range of science app executes (not inclusive)
	* @return the range of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the science app executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app executes
	* @param end the upper bound of the range of science app executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the science app executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of science app executes.
	*
	* @return the number of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ScienceAppExecutePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ScienceAppExecutePersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					ScienceAppExecutePersistence.class.getName());

			ReferenceRegistry.registerReference(ScienceAppExecuteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ScienceAppExecutePersistence persistence) {
	}

	private static ScienceAppExecutePersistence _persistence;
}