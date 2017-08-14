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

package org.kisti.edison.virtuallaboratory.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration;

import java.util.List;

/**
 * The persistence utility for the virtual lab class sts migration service. This utility wraps {@link VirtualLabClassStsMigrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStsMigrationPersistence
 * @see VirtualLabClassStsMigrationPersistenceImpl
 * @generated
 */
public class VirtualLabClassStsMigrationUtil {
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
	public static void clearCache(
		VirtualLabClassStsMigration virtualLabClassStsMigration) {
		getPersistence().clearCache(virtualLabClassStsMigration);
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
	public static List<VirtualLabClassStsMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VirtualLabClassStsMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VirtualLabClassStsMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static VirtualLabClassStsMigration update(
		VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws SystemException {
		return getPersistence().update(virtualLabClassStsMigration);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static VirtualLabClassStsMigration update(
		VirtualLabClassStsMigration virtualLabClassStsMigration,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(virtualLabClassStsMigration, serviceContext);
	}

	/**
	* Caches the virtual lab class sts migration in the entity cache if it is enabled.
	*
	* @param virtualLabClassStsMigration the virtual lab class sts migration
	*/
	public static void cacheResult(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration) {
		getPersistence().cacheResult(virtualLabClassStsMigration);
	}

	/**
	* Caches the virtual lab class sts migrations in the entity cache if it is enabled.
	*
	* @param virtualLabClassStsMigrations the virtual lab class sts migrations
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> virtualLabClassStsMigrations) {
		getPersistence().cacheResult(virtualLabClassStsMigrations);
	}

	/**
	* Creates a new virtual lab class sts migration with the primary key. Does not add the virtual lab class sts migration to the database.
	*
	* @param virtualLabClassStsMigrationPK the primary key for the new virtual lab class sts migration
	* @return the new virtual lab class sts migration
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration create(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK) {
		return getPersistence().create(virtualLabClassStsMigrationPK);
	}

	/**
	* Removes the virtual lab class sts migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration that was removed
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration remove(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException {
		return getPersistence().remove(virtualLabClassStsMigrationPK);
	}

	public static org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration updateImpl(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(virtualLabClassStsMigration);
	}

	/**
	* Returns the virtual lab class sts migration with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException} if it could not be found.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration findByPrimaryKey(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException {
		return getPersistence().findByPrimaryKey(virtualLabClassStsMigrationPK);
	}

	/**
	* Returns the virtual lab class sts migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration, or <code>null</code> if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration fetchByPrimaryKey(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(virtualLabClassStsMigrationPK);
	}

	/**
	* Returns all the virtual lab class sts migrations.
	*
	* @return the virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the virtual lab class sts migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class sts migrations
	* @param end the upper bound of the range of virtual lab class sts migrations (not inclusive)
	* @return the range of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the virtual lab class sts migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.virtuallaboratory.model.impl.VirtualLabClassStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of virtual lab class sts migrations
	* @param end the upper bound of the range of virtual lab class sts migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the virtual lab class sts migrations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of virtual lab class sts migrations.
	*
	* @return the number of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static VirtualLabClassStsMigrationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (VirtualLabClassStsMigrationPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.virtuallaboratory.service.ClpSerializer.getServletContextName(),
					VirtualLabClassStsMigrationPersistence.class.getName());

			ReferenceRegistry.registerReference(VirtualLabClassStsMigrationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(
		VirtualLabClassStsMigrationPersistence persistence) {
	}

	private static VirtualLabClassStsMigrationPersistence _persistence;
}