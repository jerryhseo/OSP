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

package org.kisti.edison.bestsimulation.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.bestsimulation.model.UniversityExecute;

import java.util.List;

/**
 * The persistence utility for the university execute service. This utility wraps {@link UniversityExecutePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see UniversityExecutePersistence
 * @see UniversityExecutePersistenceImpl
 * @generated
 */
public class UniversityExecuteUtil {
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
	public static void clearCache(UniversityExecute universityExecute) {
		getPersistence().clearCache(universityExecute);
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
	public static List<UniversityExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UniversityExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UniversityExecute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static UniversityExecute update(UniversityExecute universityExecute)
		throws SystemException {
		return getPersistence().update(universityExecute);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static UniversityExecute update(
		UniversityExecute universityExecute, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(universityExecute, serviceContext);
	}

	/**
	* Caches the university execute in the entity cache if it is enabled.
	*
	* @param universityExecute the university execute
	*/
	public static void cacheResult(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute) {
		getPersistence().cacheResult(universityExecute);
	}

	/**
	* Caches the university executes in the entity cache if it is enabled.
	*
	* @param universityExecutes the university executes
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> universityExecutes) {
		getPersistence().cacheResult(universityExecutes);
	}

	/**
	* Creates a new university execute with the primary key. Does not add the university execute to the database.
	*
	* @param universityExecutePK the primary key for the new university execute
	* @return the new university execute
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute create(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK) {
		return getPersistence().create(universityExecutePK);
	}

	/**
	* Removes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute remove(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException {
		return getPersistence().remove(universityExecutePK);
	}

	public static org.kisti.edison.bestsimulation.model.UniversityExecute updateImpl(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(universityExecute);
	}

	/**
	* Returns the university execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException} if it could not be found.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute
	* @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException {
		return getPersistence().findByPrimaryKey(universityExecutePK);
	}

	/**
	* Returns the university execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute, or <code>null</code> if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.UniversityExecute fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(universityExecutePK);
	}

	/**
	* Returns all the university executes.
	*
	* @return the university executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the university executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of university executes
	* @param end the upper bound of the range of university executes (not inclusive)
	* @return the range of university executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the university executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.UniversityExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of university executes
	* @param end the upper bound of the range of university executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of university executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the university executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of university executes.
	*
	* @return the number of university executes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UniversityExecutePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UniversityExecutePersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					UniversityExecutePersistence.class.getName());

			ReferenceRegistry.registerReference(UniversityExecuteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(UniversityExecutePersistence persistence) {
	}

	private static UniversityExecutePersistence _persistence;
}