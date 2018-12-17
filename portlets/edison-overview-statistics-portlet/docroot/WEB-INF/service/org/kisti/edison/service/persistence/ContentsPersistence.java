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

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.model.Contents;

/**
 * The persistence interface for the contents service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ContentsPersistenceImpl
 * @see ContentsUtil
 * @generated
 */
public interface ContentsPersistence extends BasePersistence<Contents> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContentsUtil} to access the contents persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the contents in the entity cache if it is enabled.
	*
	* @param contents the contents
	*/
	public void cacheResult(org.kisti.edison.model.Contents contents);

	/**
	* Caches the contentses in the entity cache if it is enabled.
	*
	* @param contentses the contentses
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.Contents> contentses);

	/**
	* Creates a new contents with the primary key. Does not add the contents to the database.
	*
	* @param createDate the primary key for the new contents
	* @return the new contents
	*/
	public org.kisti.edison.model.Contents create(java.lang.String createDate);

	/**
	* Removes the contents with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param createDate the primary key of the contents
	* @return the contents that was removed
	* @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Contents remove(java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchContentsException;

	public org.kisti.edison.model.Contents updateImpl(
		org.kisti.edison.model.Contents contents)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contents with the primary key or throws a {@link org.kisti.edison.NoSuchContentsException} if it could not be found.
	*
	* @param createDate the primary key of the contents
	* @return the contents
	* @throws org.kisti.edison.NoSuchContentsException if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Contents findByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchContentsException;

	/**
	* Returns the contents with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param createDate the primary key of the contents
	* @return the contents, or <code>null</code> if a contents with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Contents fetchByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contentses.
	*
	* @return the contentses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.Contents> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.Contents> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.model.Contents> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contentses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contentses.
	*
	* @return the number of contentses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}