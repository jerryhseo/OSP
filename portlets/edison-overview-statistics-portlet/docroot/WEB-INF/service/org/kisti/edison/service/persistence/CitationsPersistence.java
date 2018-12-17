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

import org.kisti.edison.model.Citations;

/**
 * The persistence interface for the citations service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see CitationsPersistenceImpl
 * @see CitationsUtil
 * @generated
 */
public interface CitationsPersistence extends BasePersistence<Citations> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CitationsUtil} to access the citations persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the citations in the entity cache if it is enabled.
	*
	* @param citations the citations
	*/
	public void cacheResult(org.kisti.edison.model.Citations citations);

	/**
	* Caches the citationses in the entity cache if it is enabled.
	*
	* @param citationses the citationses
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.Citations> citationses);

	/**
	* Creates a new citations with the primary key. Does not add the citations to the database.
	*
	* @param createDate the primary key for the new citations
	* @return the new citations
	*/
	public org.kisti.edison.model.Citations create(java.lang.String createDate);

	/**
	* Removes the citations with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param createDate the primary key of the citations
	* @return the citations that was removed
	* @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Citations remove(java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchCitationsException;

	public org.kisti.edison.model.Citations updateImpl(
		org.kisti.edison.model.Citations citations)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the citations with the primary key or throws a {@link org.kisti.edison.NoSuchCitationsException} if it could not be found.
	*
	* @param createDate the primary key of the citations
	* @return the citations
	* @throws org.kisti.edison.NoSuchCitationsException if a citations with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Citations findByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchCitationsException;

	/**
	* Returns the citations with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param createDate the primary key of the citations
	* @return the citations, or <code>null</code> if a citations with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.Citations fetchByPrimaryKey(
		java.lang.String createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the citationses.
	*
	* @return the citationses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.Citations> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the citationses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of citationses
	* @param end the upper bound of the range of citationses (not inclusive)
	* @return the range of citationses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.Citations> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the citationses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.CitationsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of citationses
	* @param end the upper bound of the range of citationses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of citationses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.Citations> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the citationses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of citationses.
	*
	* @return the number of citationses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}