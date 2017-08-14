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

package org.kisti.edison.search.service.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.search.service.model.SearchCondition;

/**
 * The persistence interface for the search condition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yjpark
 * @see SearchConditionPersistenceImpl
 * @see SearchConditionUtil
 * @generated
 */
public interface SearchConditionPersistence extends BasePersistence<SearchCondition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SearchConditionUtil} to access the search condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the search condition in the entity cache if it is enabled.
	*
	* @param searchCondition the search condition
	*/
	public void cacheResult(
		org.kisti.edison.search.service.model.SearchCondition searchCondition);

	/**
	* Caches the search conditions in the entity cache if it is enabled.
	*
	* @param searchConditions the search conditions
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.search.service.model.SearchCondition> searchConditions);

	/**
	* Creates a new search condition with the primary key. Does not add the search condition to the database.
	*
	* @param id the primary key for the new search condition
	* @return the new search condition
	*/
	public org.kisti.edison.search.service.model.SearchCondition create(long id);

	/**
	* Removes the search condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the search condition
	* @return the search condition that was removed
	* @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.search.service.model.SearchCondition remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.search.service.NoSuchSearchConditionException;

	public org.kisti.edison.search.service.model.SearchCondition updateImpl(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the search condition with the primary key or throws a {@link org.kisti.edison.search.service.NoSuchSearchConditionException} if it could not be found.
	*
	* @param id the primary key of the search condition
	* @return the search condition
	* @throws org.kisti.edison.search.service.NoSuchSearchConditionException if a search condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.search.service.model.SearchCondition findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.search.service.NoSuchSearchConditionException;

	/**
	* Returns the search condition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the search condition
	* @return the search condition, or <code>null</code> if a search condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.search.service.model.SearchCondition fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the search conditions.
	*
	* @return the search conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.search.service.model.SearchCondition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the search conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search conditions
	* @param end the upper bound of the range of search conditions (not inclusive)
	* @return the range of search conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.search.service.model.SearchCondition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the search conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search conditions
	* @param end the upper bound of the range of search conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of search conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.search.service.model.SearchCondition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the search conditions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of search conditions.
	*
	* @return the number of search conditions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}