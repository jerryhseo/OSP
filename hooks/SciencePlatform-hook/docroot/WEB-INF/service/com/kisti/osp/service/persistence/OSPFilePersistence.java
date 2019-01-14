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

package com.kisti.osp.service.persistence;

import com.kisti.osp.model.OSPFile;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o s p file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry h. Seo
 * @see OSPFilePersistenceImpl
 * @see OSPFileUtil
 * @generated
 */
public interface OSPFilePersistence extends BasePersistence<OSPFile> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OSPFileUtil} to access the o s p file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o s p file in the entity cache if it is enabled.
	*
	* @param ospFile the o s p file
	*/
	public void cacheResult(com.kisti.osp.model.OSPFile ospFile);

	/**
	* Caches the o s p files in the entity cache if it is enabled.
	*
	* @param ospFiles the o s p files
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.model.OSPFile> ospFiles);

	/**
	* Creates a new o s p file with the primary key. Does not add the o s p file to the database.
	*
	* @param propertyName the primary key for the new o s p file
	* @return the new o s p file
	*/
	public com.kisti.osp.model.OSPFile create(java.lang.String propertyName);

	/**
	* Removes the o s p file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file that was removed
	* @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.OSPFile remove(java.lang.String propertyName)
		throws com.kisti.osp.NoSuchFileException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.model.OSPFile updateImpl(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o s p file with the primary key or throws a {@link com.kisti.osp.NoSuchFileException} if it could not be found.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file
	* @throws com.kisti.osp.NoSuchFileException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.OSPFile findByPrimaryKey(
		java.lang.String propertyName)
		throws com.kisti.osp.NoSuchFileException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o s p file with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file, or <code>null</code> if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.OSPFile fetchByPrimaryKey(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o s p files.
	*
	* @return the o s p files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.OSPFile> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o s p files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o s p files
	* @param end the upper bound of the range of o s p files (not inclusive)
	* @return the range of o s p files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.OSPFile> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o s p files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o s p files
	* @param end the upper bound of the range of o s p files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o s p files
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.OSPFile> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o s p files from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o s p files.
	*
	* @return the number of o s p files
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}