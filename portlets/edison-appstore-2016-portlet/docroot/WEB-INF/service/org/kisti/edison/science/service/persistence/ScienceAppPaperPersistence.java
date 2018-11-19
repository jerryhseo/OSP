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

package org.kisti.edison.science.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import org.kisti.edison.science.model.ScienceAppPaper;

/**
 * The persistence interface for the science app paper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see ScienceAppPaperPersistenceImpl
 * @see ScienceAppPaperUtil
 * @generated
 */
public interface ScienceAppPaperPersistence extends BasePersistence<ScienceAppPaper> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScienceAppPaperUtil} to access the science app paper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the science app papers where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the matching science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findByScienceAppId(
		long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the science app papers where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app papers
	* @param end the upper bound of the range of science app papers (not inclusive)
	* @return the range of matching science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findByScienceAppId(
		long scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the science app papers where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of science app papers
	* @param end the upper bound of the range of science app papers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findByScienceAppId(
		long scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first science app paper in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app paper
	* @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a matching science app paper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper findByScienceAppId_First(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppPaperException;

	/**
	* Returns the first science app paper in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching science app paper, or <code>null</code> if a matching science app paper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper fetchByScienceAppId_First(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last science app paper in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app paper
	* @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a matching science app paper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper findByScienceAppId_Last(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppPaperException;

	/**
	* Returns the last science app paper in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching science app paper, or <code>null</code> if a matching science app paper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper fetchByScienceAppId_Last(
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the science app papers before and after the current science app paper in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppPaperPK the primary key of the current science app paper
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next science app paper
	* @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper[] findByScienceAppId_PrevAndNext(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK,
		long scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppPaperException;

	/**
	* Removes all the science app papers where scienceAppId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByScienceAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of science app papers where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the number of matching science app papers
	* @throws SystemException if a system exception occurred
	*/
	public int countByScienceAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the science app paper in the entity cache if it is enabled.
	*
	* @param scienceAppPaper the science app paper
	*/
	public void cacheResult(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper);

	/**
	* Caches the science app papers in the entity cache if it is enabled.
	*
	* @param scienceAppPapers the science app papers
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.science.model.ScienceAppPaper> scienceAppPapers);

	/**
	* Creates a new science app paper with the primary key. Does not add the science app paper to the database.
	*
	* @param scienceAppPaperPK the primary key for the new science app paper
	* @return the new science app paper
	*/
	public org.kisti.edison.science.model.ScienceAppPaper create(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK);

	/**
	* Removes the science app paper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppPaperPK the primary key of the science app paper
	* @return the science app paper that was removed
	* @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper remove(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppPaperException;

	public org.kisti.edison.science.model.ScienceAppPaper updateImpl(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the science app paper with the primary key or throws a {@link org.kisti.edison.science.NoSuchScienceAppPaperException} if it could not be found.
	*
	* @param scienceAppPaperPK the primary key of the science app paper
	* @return the science app paper
	* @throws org.kisti.edison.science.NoSuchScienceAppPaperException if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper findByPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.science.NoSuchScienceAppPaperException;

	/**
	* Returns the science app paper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scienceAppPaperPK the primary key of the science app paper
	* @return the science app paper, or <code>null</code> if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.science.model.ScienceAppPaper fetchByPrimaryKey(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the science app papers.
	*
	* @return the science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the science app papers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app papers
	* @param end the upper bound of the range of science app papers (not inclusive)
	* @return the range of science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the science app papers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of science app papers
	* @param end the upper bound of the range of science app papers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of science app papers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the science app papers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of science app papers.
	*
	* @return the number of science app papers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}