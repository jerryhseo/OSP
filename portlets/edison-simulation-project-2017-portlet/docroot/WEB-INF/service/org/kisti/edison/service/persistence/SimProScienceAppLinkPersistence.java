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

import org.kisti.edison.model.SimProScienceAppLink;

/**
 * The persistence interface for the sim pro science app link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see SimProScienceAppLinkPersistenceImpl
 * @see SimProScienceAppLinkUtil
 * @generated
 */
public interface SimProScienceAppLinkPersistence extends BasePersistence<SimProScienceAppLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimProScienceAppLinkUtil} to access the sim pro science app link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sim pro science app links where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the matching sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sim pro science app links where simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of sim pro science app links
	* @param end the upper bound of the range of sim pro science app links (not inclusive)
	* @return the range of matching sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sim pro science app links where simulationProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationProjectId the simulation project ID
	* @param start the lower bound of the range of sim pro science app links
	* @param end the upper bound of the range of sim pro science app links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findBySimulationProjectId(
		long simulationProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first sim pro science app link in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sim pro science app link
	* @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a matching sim pro science app link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink findBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimProScienceAppLinkException;

	/**
	* Returns the first sim pro science app link in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sim pro science app link, or <code>null</code> if a matching sim pro science app link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink fetchBySimulationProjectId_First(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last sim pro science app link in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sim pro science app link
	* @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a matching sim pro science app link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink findBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimProScienceAppLinkException;

	/**
	* Returns the last sim pro science app link in the ordered set where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sim pro science app link, or <code>null</code> if a matching sim pro science app link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink fetchBySimulationProjectId_Last(
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sim pro science app links before and after the current sim pro science app link in the ordered set where simulationProjectId = &#63;.
	*
	* @param simProScienceAppLinkPK the primary key of the current sim pro science app link
	* @param simulationProjectId the simulation project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sim pro science app link
	* @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink[] findBySimulationProjectId_PrevAndNext(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK,
		long simulationProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimProScienceAppLinkException;

	/**
	* Removes all the sim pro science app links where simulationProjectId = &#63; from the database.
	*
	* @param simulationProjectId the simulation project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sim pro science app links where simulationProjectId = &#63;.
	*
	* @param simulationProjectId the simulation project ID
	* @return the number of matching sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public int countBySimulationProjectId(long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the sim pro science app link in the entity cache if it is enabled.
	*
	* @param simProScienceAppLink the sim pro science app link
	*/
	public void cacheResult(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink);

	/**
	* Caches the sim pro science app links in the entity cache if it is enabled.
	*
	* @param simProScienceAppLinks the sim pro science app links
	*/
	public void cacheResult(
		java.util.List<org.kisti.edison.model.SimProScienceAppLink> simProScienceAppLinks);

	/**
	* Creates a new sim pro science app link with the primary key. Does not add the sim pro science app link to the database.
	*
	* @param simProScienceAppLinkPK the primary key for the new sim pro science app link
	* @return the new sim pro science app link
	*/
	public org.kisti.edison.model.SimProScienceAppLink create(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK);

	/**
	* Removes the sim pro science app link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simProScienceAppLinkPK the primary key of the sim pro science app link
	* @return the sim pro science app link that was removed
	* @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink remove(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimProScienceAppLinkException;

	public org.kisti.edison.model.SimProScienceAppLink updateImpl(
		org.kisti.edison.model.SimProScienceAppLink simProScienceAppLink)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sim pro science app link with the primary key or throws a {@link org.kisti.edison.NoSuchSimProScienceAppLinkException} if it could not be found.
	*
	* @param simProScienceAppLinkPK the primary key of the sim pro science app link
	* @return the sim pro science app link
	* @throws org.kisti.edison.NoSuchSimProScienceAppLinkException if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink findByPrimaryKey(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.NoSuchSimProScienceAppLinkException;

	/**
	* Returns the sim pro science app link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simProScienceAppLinkPK the primary key of the sim pro science app link
	* @return the sim pro science app link, or <code>null</code> if a sim pro science app link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public org.kisti.edison.model.SimProScienceAppLink fetchByPrimaryKey(
		org.kisti.edison.service.persistence.SimProScienceAppLinkPK simProScienceAppLinkPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sim pro science app links.
	*
	* @return the sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sim pro science app links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sim pro science app links
	* @param end the upper bound of the range of sim pro science app links (not inclusive)
	* @return the range of sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sim pro science app links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.SimProScienceAppLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sim pro science app links
	* @param end the upper bound of the range of sim pro science app links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<org.kisti.edison.model.SimProScienceAppLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sim pro science app links from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sim pro science app links.
	*
	* @return the number of sim pro science app links
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}