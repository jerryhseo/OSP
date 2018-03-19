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

import com.kisti.osp.model.FileManagement;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the file management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jerry h. Seo
 * @see FileManagementPersistenceImpl
 * @see FileManagementUtil
 * @generated
 */
public interface FileManagementPersistence extends BasePersistence<FileManagement> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileManagementUtil} to access the file management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the file management in the entity cache if it is enabled.
	*
	* @param fileManagement the file management
	*/
	public void cacheResult(com.kisti.osp.model.FileManagement fileManagement);

	/**
	* Caches the file managements in the entity cache if it is enabled.
	*
	* @param fileManagements the file managements
	*/
	public void cacheResult(
		java.util.List<com.kisti.osp.model.FileManagement> fileManagements);

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param userId the primary key for the new file management
	* @return the new file management
	*/
	public com.kisti.osp.model.FileManagement create(long userId);

	/**
	* Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the file management
	* @return the file management that was removed
	* @throws com.kisti.osp.NoSuchFileManagementException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.FileManagement remove(long userId)
		throws com.kisti.osp.NoSuchFileManagementException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.osp.model.FileManagement updateImpl(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the file management with the primary key or throws a {@link com.kisti.osp.NoSuchFileManagementException} if it could not be found.
	*
	* @param userId the primary key of the file management
	* @return the file management
	* @throws com.kisti.osp.NoSuchFileManagementException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.FileManagement findByPrimaryKey(long userId)
		throws com.kisti.osp.NoSuchFileManagementException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the file management with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the file management
	* @return the file management, or <code>null</code> if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.osp.model.FileManagement fetchByPrimaryKey(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the file managements.
	*
	* @return the file managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.FileManagement> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @return the range of file managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.FileManagement> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file managements
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.osp.model.FileManagement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the file managements from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}