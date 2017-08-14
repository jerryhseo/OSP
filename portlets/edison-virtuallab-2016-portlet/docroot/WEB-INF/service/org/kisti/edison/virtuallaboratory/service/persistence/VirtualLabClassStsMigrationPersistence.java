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

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration;

/**
 * The persistence interface for the virtual lab class sts migration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see VirtualLabClassStsMigrationPersistenceImpl
 * @see VirtualLabClassStsMigrationUtil
 * @generated
 */
public interface VirtualLabClassStsMigrationPersistence extends BasePersistence<VirtualLabClassStsMigration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VirtualLabClassStsMigrationUtil} to access the virtual lab class sts migration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the virtual lab class sts migration in the entity cache if it is enabled.
	*
	* @param virtualLabClassStsMigration the virtual lab class sts migration
	*/
	public void cacheResult(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration);

	/**
	* Caches the virtual lab class sts migrations in the entity cache if it is enabled.
	*
	* @param virtualLabClassStsMigrations the virtual lab class sts migrations
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> virtualLabClassStsMigrations);

	/**
	* Creates a new virtual lab class sts migration with the primary key. Does not add the virtual lab class sts migration to the database.
	*
	* @param virtualLabClassStsMigrationPK the primary key for the new virtual lab class sts migration
	* @return the new virtual lab class sts migration
	*/
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration create(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK);

	/**
	* Removes the virtual lab class sts migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration that was removed
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration remove(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException;

	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration updateImpl(
		org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration virtualLabClassStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the virtual lab class sts migration with the primary key or throws a {@link org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException} if it could not be found.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration
	* @throws org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration findByPrimaryKey(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.virtuallaboratory.NoSuchVirtualLabClassStsMigrationException;

	/**
	* Returns the virtual lab class sts migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param virtualLabClassStsMigrationPK the primary key of the virtual lab class sts migration
	* @return the virtual lab class sts migration, or <code>null</code> if a virtual lab class sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration fetchByPrimaryKey(
		org.kisti.edison.virtuallaboratory.service.persistence.VirtualLabClassStsMigrationPK virtualLabClassStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the virtual lab class sts migrations.
	*
	* @return the virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLabClassStsMigration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the virtual lab class sts migrations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of virtual lab class sts migrations.
	*
	* @return the number of virtual lab class sts migrations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}