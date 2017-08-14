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

import org.kisti.edison.bestsimulation.model.ScienceAppExecute;

/**
 * The persistence interface for the science app execute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppExecutePersistenceImpl
 * @see ScienceAppExecuteUtil
 * @generated
 */
public interface ScienceAppExecutePersistence extends BasePersistence<ScienceAppExecute> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScienceAppExecuteUtil} to access the science app execute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the science app execute in the entity cache if it is enabled.
	*
	* @param scienceAppExecute the science app execute
	*/
	public void cacheResult(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute);

	/**
	* Caches the science app executes in the entity cache if it is enabled.
	*
	* @param scienceAppExecutes the science app executes
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> scienceAppExecutes);

	/**
	* Creates a new science app execute with the primary key. Does not add the science app execute to the database.
	*
	* @param scienceAppExecutePK the primary key for the new science app execute
	* @return the new science app execute
	*/
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute create(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK);

	/**
	* Removes the science app execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute remove(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException;

	public org.kisti.edison.bestsimulation.model.ScienceAppExecute updateImpl(
		org.kisti.edison.bestsimulation.model.ScienceAppExecute scienceAppExecute)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the science app execute with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException} if it could not be found.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute
	* @throws org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchScienceAppExecuteException;

	/**
	* Returns the science app execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppExecutePK the primary key of the science app execute
	* @return the science app execute, or <code>null</code> if a science app execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.bestsimulation.model.ScienceAppExecute fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.ScienceAppExecutePK scienceAppExecutePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the science app executes.
	*
	* @return the science app executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the science app executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app executes
	* @param end the upper bound of the range of science app executes (not inclusive)
	* @return the range of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the science app executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.ScienceAppExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app executes
	* @param end the upper bound of the range of science app executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.bestsimulation.model.ScienceAppExecute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the science app executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of science app executes.
	*
	* @return the number of science app executes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}