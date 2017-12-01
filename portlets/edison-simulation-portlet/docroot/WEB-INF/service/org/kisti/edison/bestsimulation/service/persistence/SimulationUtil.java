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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.bestsimulation.model.Simulation;

import java.util.List;

/**
 * The persistence utility for the simulation service. This utility wraps {@link SimulationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationPersistence
 * @see SimulationPersistenceImpl
 * @generated
 */
public class SimulationUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Simulation simulation) {
		getPersistence().clearCache(simulation);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Simulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Simulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Simulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Simulation update(Simulation simulation)
		throws SystemException {
		return getPersistence().update(simulation);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Simulation update(Simulation simulation,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(simulation, serviceContext);
	}

	/**
	* Returns the simulation where simulationUuid = &#63; or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationException} if it could not be found.
	*
	* @param simulationUuid the simulation uuid
	* @return the matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findBySimulationUuid(simulationUuid);
	}

	/**
	* Returns the simulation where simulationUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param simulationUuid the simulation uuid
	* @return the matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySimulationUuid(simulationUuid);
	}

	/**
	* Returns the simulation where simulationUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param simulationUuid the simulation uuid
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchBySimulationUuid(
		java.lang.String simulationUuid, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationUuid(simulationUuid, retrieveFromCache);
	}

	/**
	* Removes the simulation where simulationUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @return the simulation that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation removeBySimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().removeBySimulationUuid(simulationUuid);
	}

	/**
	* Returns the number of simulations where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySimulationUuid(simulationUuid);
	}

	/**
	* Returns all the simulations where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByAppId(
		java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId(scienceAppId);
	}

	/**
	* Returns a range of all the simulations where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByAppId(
		java.lang.String scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppId(scienceAppId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByAppId(
		java.lang.String scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppId(scienceAppId, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByAppId_First(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByAppId_First(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByAppId_Last(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByAppId_Last(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByAppId_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByAppId_PrevAndNext(simulationPK, scienceAppId,
			orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppId(java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppId(scienceAppId);
	}

	/**
	* Returns the number of simulations where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppId(java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppId(scienceAppId);
	}

	/**
	* Returns all the simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the simulations where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where userId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByUserId_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByUserId_PrevAndNext(simulationPK, userId,
			orderByComparator);
	}

	/**
	* Removes all the simulations where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of simulations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the simulations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId_G(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_G(groupId, userId);
	}

	/**
	* Returns a range of all the simulations where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId_G(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_G(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByUserId_G(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_G(groupId, userId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByUserId_G_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByUserId_G_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByUserId_G_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_G_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByUserId_G_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByUserId_G_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByUserId_G_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserId_G_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByUserId_G_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByUserId_G_PrevAndNext(simulationPK, groupId, userId,
			orderByComparator);
	}

	/**
	* Removes all the simulations where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId_G(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId_G(groupId, userId);
	}

	/**
	* Returns the number of simulations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId_G(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId_G(groupId, userId);
	}

	/**
	* Returns all the simulations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the simulations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where groupId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByGroupId_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(simulationPK, groupId,
			orderByComparator);
	}

	/**
	* Removes all the simulations where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of simulations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the simulations where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppName(
		java.lang.String scienceAppName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppName(scienceAppName);
	}

	/**
	* Returns a range of all the simulations where scienceAppName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppName the science app name
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppName(
		java.lang.String scienceAppName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppName(scienceAppName, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppName the science app name
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppName(
		java.lang.String scienceAppName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppName(scienceAppName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppName_First(
		java.lang.String scienceAppName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppName_First(scienceAppName, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppName_First(
		java.lang.String scienceAppName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppName_First(scienceAppName,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppName_Last(
		java.lang.String scienceAppName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppName_Last(scienceAppName, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppName_Last(
		java.lang.String scienceAppName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppName_Last(scienceAppName, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppName = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppName the science app name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByScienceAppName_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppName_PrevAndNext(simulationPK,
			scienceAppName, orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppName = &#63; from the database.
	*
	* @param scienceAppName the science app name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppName(java.lang.String scienceAppName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppName(scienceAppName);
	}

	/**
	* Returns the number of simulations where scienceAppName = &#63;.
	*
	* @param scienceAppName the science app name
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppName(java.lang.String scienceAppName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScienceAppName(scienceAppName);
	}

	/**
	* Returns all the simulations where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId(
		java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId(scienceAppId);
	}

	/**
	* Returns a range of all the simulations where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId(
		java.lang.String scienceAppId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId(scienceAppId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId(
		java.lang.String scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId(scienceAppId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_First(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_First(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_First(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_Last(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_Last(
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_Last(scienceAppId, orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppId the science app ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByScienceAppId_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_PrevAndNext(simulationPK, scienceAppId,
			orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppId(java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppId(scienceAppId);
	}

	/**
	* Returns the number of simulations where scienceAppId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppId(java.lang.String scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScienceAppId(scienceAppId);
	}

	/**
	* Returns all the simulations where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U(
		java.lang.String scienceAppId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId_U(scienceAppId, userId);
	}

	/**
	* Returns a range of all the simulations where scienceAppId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U(
		java.lang.String scienceAppId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_U(scienceAppId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U(
		java.lang.String scienceAppId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_U(scienceAppId, userId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_U_First(
		java.lang.String scienceAppId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_First(scienceAppId, userId,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_U_First(
		java.lang.String scienceAppId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_U_First(scienceAppId, userId,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_U_Last(
		java.lang.String scienceAppId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_Last(scienceAppId, userId,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_U_Last(
		java.lang.String scienceAppId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_U_Last(scienceAppId, userId,
			orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and userId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByScienceAppId_U_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_PrevAndNext(simulationPK,
			scienceAppId, userId, orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppId = &#63; and userId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppId_U(java.lang.String scienceAppId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppId_U(scienceAppId, userId);
	}

	/**
	* Returns the number of simulations where scienceAppId = &#63; and userId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppId_U(java.lang.String scienceAppId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScienceAppId_U(scienceAppId, userId);
	}

	/**
	* Returns all the simulations where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_G(
		java.lang.String scienceAppId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByScienceAppId_G(scienceAppId, groupId);
	}

	/**
	* Returns a range of all the simulations where scienceAppId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_G(
		java.lang.String scienceAppId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_G(scienceAppId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_G(
		java.lang.String scienceAppId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_G(scienceAppId, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_G_First(
		java.lang.String scienceAppId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_G_First(scienceAppId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_G_First(
		java.lang.String scienceAppId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_G_First(scienceAppId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_G_Last(
		java.lang.String scienceAppId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_G_Last(scienceAppId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_G_Last(
		java.lang.String scienceAppId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_G_Last(scienceAppId, groupId,
			orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByScienceAppId_G_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_G_PrevAndNext(simulationPK,
			scienceAppId, groupId, orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppId = &#63; and groupId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppId_G(java.lang.String scienceAppId,
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppId_G(scienceAppId, groupId);
	}

	/**
	* Returns the number of simulations where scienceAppId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param groupId the group ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppId_G(java.lang.String scienceAppId,
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScienceAppId_G(scienceAppId, groupId);
	}

	/**
	* Returns all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @return the matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U_G(
		java.lang.String scienceAppId, long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_U_G(scienceAppId, userId, groupId);
	}

	/**
	* Returns a range of all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U_G(
		java.lang.String scienceAppId, long userId, long groupId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_U_G(scienceAppId, userId, groupId,
			start, end);
	}

	/**
	* Returns an ordered range of all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findByScienceAppId_U_G(
		java.lang.String scienceAppId, long userId, long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByScienceAppId_U_G(scienceAppId, userId, groupId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_U_G_First(
		java.lang.String scienceAppId, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_G_First(scienceAppId, userId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_U_G_First(
		java.lang.String scienceAppId, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_U_G_First(scienceAppId, userId,
			groupId, orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByScienceAppId_U_G_Last(
		java.lang.String scienceAppId, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_G_Last(scienceAppId, userId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation, or <code>null</code> if a matching simulation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByScienceAppId_U_G_Last(
		java.lang.String scienceAppId, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScienceAppId_U_G_Last(scienceAppId, userId, groupId,
			orderByComparator);
	}

	/**
	* Returns the simulations before and after the current simulation in the ordered set where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param simulationPK the primary key of the current simulation
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation[] findByScienceAppId_U_G_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK,
		java.lang.String scienceAppId, long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence()
				   .findByScienceAppId_U_G_PrevAndNext(simulationPK,
			scienceAppId, userId, groupId, orderByComparator);
	}

	/**
	* Removes all the simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63; from the database.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByScienceAppId_U_G(java.lang.String scienceAppId,
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByScienceAppId_U_G(scienceAppId, userId, groupId);
	}

	/**
	* Returns the number of simulations where scienceAppId = &#63; and userId = &#63; and groupId = &#63;.
	*
	* @param scienceAppId the science app ID
	* @param userId the user ID
	* @param groupId the group ID
	* @return the number of matching simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScienceAppId_U_G(java.lang.String scienceAppId,
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByScienceAppId_U_G(scienceAppId, userId, groupId);
	}

	/**
	* Caches the simulation in the entity cache if it is enabled.
	*
	* @param simulation the simulation
	*/
	public static void cacheResult(
		org.kisti.edison.bestsimulation.model.Simulation simulation) {
		getPersistence().cacheResult(simulation);
	}

	/**
	* Caches the simulations in the entity cache if it is enabled.
	*
	* @param simulations the simulations
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.Simulation> simulations) {
		getPersistence().cacheResult(simulations);
	}

	/**
	* Creates a new simulation with the primary key. Does not add the simulation to the database.
	*
	* @param simulationPK the primary key for the new simulation
	* @return the new simulation
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation create(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK) {
		return getPersistence().create(simulationPK);
	}

	/**
	* Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation remove(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().remove(simulationPK);
	}

	public static org.kisti.edison.bestsimulation.model.Simulation updateImpl(
		org.kisti.edison.bestsimulation.model.Simulation simulation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(simulation);
	}

	/**
	* Returns the simulation with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationException} if it could not be found.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationException if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationException {
		return getPersistence().findByPrimaryKey(simulationPK);
	}

	/**
	* Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationPK the primary key of the simulation
	* @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.Simulation fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationPK simulationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(simulationPK);
	}

	/**
	* Returns all the simulations.
	*
	* @return the simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @return the range of simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the simulations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulations
	* @param end the upper bound of the range of simulations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.Simulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the simulations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of simulations.
	*
	* @return the number of simulations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SimulationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SimulationPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					SimulationPersistence.class.getName());

			ReferenceRegistry.registerReference(SimulationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SimulationPersistence persistence) {
	}

	private static SimulationPersistence _persistence;
}