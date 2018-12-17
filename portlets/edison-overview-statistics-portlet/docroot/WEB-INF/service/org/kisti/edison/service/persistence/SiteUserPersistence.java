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

import org.kisti.edison.model.SiteUser;

/**
 * The persistence interface for the site user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SiteUserPersistenceImpl
 * @see SiteUserUtil
 * @generated
 */
public interface SiteUserPersistence extends BasePersistence<SiteUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SiteUserUtil} to access the site user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the site users where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findBygroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the site users where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of site users
	* @param end the upper bound of the range of site users (not inclusive)
	* @return the range of matching site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findBygroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the site users where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of site users
	* @param end the upper bound of the range of site users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first site user in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching site user
	* @throws org.kisti.edison.NoSuchSiteUserException if a matching site user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser findBygroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSiteUserException;

	/**
	* Returns the first site user in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching site user, or <code>null</code> if a matching site user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser fetchBygroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last site user in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching site user
	* @throws org.kisti.edison.NoSuchSiteUserException if a matching site user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser findBygroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSiteUserException;

	/**
	* Returns the last site user in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching site user, or <code>null</code> if a matching site user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser fetchBygroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the site users before and after the current site user in the ordered set where groupId = &#63;.
	*
	* @param siteUserPK the primary key of the current site user
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next site user
	* @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser[] findBygroupId_PrevAndNext(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSiteUserException;

	/**
	* Removes all the site users where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBygroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of site users where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching site users
	* @throws SystemException if a system exception occurred
	*/
	public int countBygroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the site user in the entity cache if it is enabled.
	*
	* @param siteUser the site user
	*/
	public void cacheResult(org.kisti.edison.model.SiteUser siteUser);

	/**
	* Caches the site users in the entity cache if it is enabled.
	*
	* @param siteUsers the site users
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.SiteUser> siteUsers);

	/**
	* Creates a new site user with the primary key. Does not add the site user to the database.
	*
	* @param siteUserPK the primary key for the new site user
	* @return the new site user
	*/
	public org.kisti.edison.model.SiteUser create(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK);

	/**
	* Removes the site user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user that was removed
	* @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser remove(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSiteUserException;

	public org.kisti.edison.model.SiteUser updateImpl(
		org.kisti.edison.model.SiteUser siteUser)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the site user with the primary key or throws a {@link org.kisti.edison.NoSuchSiteUserException} if it could not be found.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user
	* @throws org.kisti.edison.NoSuchSiteUserException if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser findByPrimaryKey(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSiteUserException;

	/**
	* Returns the site user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param siteUserPK the primary key of the site user
	* @return the site user, or <code>null</code> if a site user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SiteUser fetchByPrimaryKey(
		org.kisti.edison.service.persistence.SiteUserPK siteUserPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the site users.
	*
	* @return the site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the site users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of site users
	* @param end the upper bound of the range of site users (not inclusive)
	* @return the range of site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the site users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SiteUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of site users
	* @param end the upper bound of the range of site users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of site users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SiteUser> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the site users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of site users.
	*
	* @return the number of site users
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}