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

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.bestsimulation.model.UniversityExecute;

/**
 * The persistence interface for the university execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see UniversityExecutePersistenceImpl
 * @see UniversityExecuteUtil
 * @generated
 */
public interface UniversityExecutePersistence extends BasePersistence<UniversityExecute> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UniversityExecuteUtil} to access the university execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the university execute in the entity cache if it is enabled.
	*
	* @param universityExecute the university execute
	*/
	public void cacheResult(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute);

	/**
	* Caches the university executes in the entity cache if it is enabled.
	*
	* @param universityExecutes the university executes
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> universityExecutes);

	/**
	* Creates a new university execute with the primary key. Does not add the university execute to the database.
	*
	* @param universityExecutePK the primary key for the new university execute
	* @return the new university execute
	*/
	public org.kisti.edison.bestsimulation.model.UniversityExecute create(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK);

	/**
	* Removes the university execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.UniversityExecute remove(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException;

	public org.kisti.edison.bestsimulation.model.UniversityExecute updateImpl(
		org.kisti.edison.bestsimulation.model.UniversityExecute universityExecute)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the university execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException} if it could not be found.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute
	* @throws org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.UniversityExecute findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchUniversityExecuteException;

	/**
	* Returns the university execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param universityExecutePK the primary key of the university execute
	* @return the university execute, or <code>null</code> if a university execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.UniversityExecute fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.UniversityExecutePK universityExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the university executes.
	*
	* @return the university executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<org.kisti.edison.bestsimulation.model.UniversityExecute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the university executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of university executes.
	*
	* @return the number of university executes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}