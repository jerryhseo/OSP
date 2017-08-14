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

import org.kisti.edison.bestsimulation.model.SimulationJob;

import java.util.List;

/**
 * The persistence utility for the simulation job service. This utility wraps {@link SimulationJobPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author EDISON
 * @see SimulationJobPersistence
 * @see SimulationJobPersistenceImpl
 * @generated
 */
public class SimulationJobUtil {
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
	public static void clearCache(SimulationJob simulationJob) {
		getPersistence().clearCache(simulationJob);
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
	public static List<SimulationJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SimulationJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SimulationJob> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SimulationJob update(SimulationJob simulationJob)
		throws SystemException {
		return getPersistence().update(simulationJob);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SimulationJob update(SimulationJob simulationJob,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(simulationJob, serviceContext);
	}

	/**
	* Returns all the simulation jobs where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBysimulationUuid(simulationUuid);
	}

	/**
	* Returns a range of all the simulation jobs where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBysimulationUuid(simulationUuid, start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs where simulationUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBysimulationUuid(
		java.lang.String simulationUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBysimulationUuid(simulationUuid, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBysimulationUuid_First(simulationUuid, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBysimulationUuid_First(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBysimulationUuid_First(simulationUuid,
			orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBysimulationUuid_Last(simulationUuid, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBysimulationUuid_Last(
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBysimulationUuid_Last(simulationUuid, orderByComparator);
	}

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param simulationUuid the simulation uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob[] findBysimulationUuid_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		java.lang.String simulationUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBysimulationUuid_PrevAndNext(simulationJobPK,
			simulationUuid, orderByComparator);
	}

	/**
	* Removes all the simulation jobs where simulationUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBysimulationUuid(simulationUuid);
	}

	/**
	* Returns the number of simulation jobs where simulationUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBysimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBysimulationUuid(simulationUuid);
	}

	/**
	* Returns all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByjobUuid(simulationUuid, jobUuid);
	}

	/**
	* Returns a range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByjobUuid(simulationUuid, jobUuid, start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByjobUuid(
		java.lang.String simulationUuid, java.lang.String jobUuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByjobUuid(simulationUuid, jobUuid, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByjobUuid_First(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByjobUuid_First(simulationUuid, jobUuid,
			orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByjobUuid_First(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByjobUuid_First(simulationUuid, jobUuid,
			orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByjobUuid_Last(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByjobUuid_Last(simulationUuid, jobUuid,
			orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByjobUuid_Last(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByjobUuid_Last(simulationUuid, jobUuid,
			orderByComparator);
	}

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob[] findByjobUuid_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByjobUuid_PrevAndNext(simulationJobPK, simulationUuid,
			jobUuid, orderByComparator);
	}

	/**
	* Removes all the simulation jobs where simulationUuid = &#63; and jobUuid = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByjobUuid(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByjobUuid(simulationUuid, jobUuid);
	}

	/**
	* Returns the number of simulation jobs where simulationUuid = &#63; and jobUuid = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobUuid the job uuid
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByjobUuid(java.lang.String simulationUuid,
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByjobUuid(simulationUuid, jobUuid);
	}

	/**
	* Returns the simulation job where jobUuid = &#63; or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationJobException} if it could not be found.
	*
	* @param jobUuid the job uuid
	* @return the matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence().findByUuid(jobUuid);
	}

	/**
	* Returns the simulation job where jobUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jobUuid the job uuid
	* @return the matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid(jobUuid);
	}

	/**
	* Returns the simulation job where jobUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jobUuid the job uuid
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByUuid(
		java.lang.String jobUuid, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid(jobUuid, retrieveFromCache);
	}

	/**
	* Removes the simulation job where jobUuid = &#63; from the database.
	*
	* @param jobUuid the job uuid
	* @return the simulation job that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob removeByUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence().removeByUuid(jobUuid);
	}

	/**
	* Returns the number of simulation jobs where jobUuid = &#63;.
	*
	* @param jobUuid the job uuid
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(jobUuid);
	}

	/**
	* Returns all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByStatus(
		boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(jobSubmit, jobStatus);
	}

	/**
	* Returns a range of all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByStatus(
		boolean jobSubmit, long jobStatus, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(jobSubmit, jobStatus, start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findByStatus(
		boolean jobSubmit, long jobStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(jobSubmit, jobStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByStatus_First(
		boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByStatus_First(jobSubmit, jobStatus, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByStatus_First(
		boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByStatus_First(jobSubmit, jobStatus, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByStatus_Last(
		boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByStatus_Last(jobSubmit, jobStatus, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByStatus_Last(
		boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByStatus_Last(jobSubmit, jobStatus, orderByComparator);
	}

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob[] findByStatus_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findByStatus_PrevAndNext(simulationJobPK, jobSubmit,
			jobStatus, orderByComparator);
	}

	/**
	* Removes all the simulation jobs where jobSubmit = &#63; and jobStatus = &#63; from the database.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(jobSubmit, jobStatus);
	}

	/**
	* Returns the number of simulation jobs where jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(jobSubmit, jobStatus);
	}

	/**
	* Returns all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySimulationUuid_S(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus);
	}

	/**
	* Returns a range of all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySimulationUuid_S(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationUuid_S(simulationUuid, jobSubmit,
			jobStatus, start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySimulationUuid_S(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySimulationUuid_S(simulationUuid, jobSubmit,
			jobStatus, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBySimulationUuid_S_First(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySimulationUuid_S_First(simulationUuid, jobSubmit,
			jobStatus, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBySimulationUuid_S_First(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationUuid_S_First(simulationUuid, jobSubmit,
			jobStatus, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBySimulationUuid_S_Last(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySimulationUuid_S_Last(simulationUuid, jobSubmit,
			jobStatus, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBySimulationUuid_S_Last(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySimulationUuid_S_Last(simulationUuid, jobSubmit,
			jobStatus, orderByComparator);
	}

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob[] findBySimulationUuid_S_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySimulationUuid_S_PrevAndNext(simulationJobPK,
			simulationUuid, jobSubmit, jobStatus, orderByComparator);
	}

	/**
	* Removes all the simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63; from the database.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySimulationUuid_S(
		java.lang.String simulationUuid, boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus);
	}

	/**
	* Returns the number of simulation jobs where simulationUuid = &#63; and jobSubmit = &#63; and jobStatus = &#63;.
	*
	* @param simulationUuid the simulation uuid
	* @param jobSubmit the job submit
	* @param jobStatus the job status
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySimulationUuid_S(java.lang.String simulationUuid,
		boolean jobSubmit, long jobStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySimulationUuid_S(simulationUuid, jobSubmit, jobStatus);
	}

	/**
	* Returns all the simulation jobs where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @return the matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySubmitStatus(
		boolean jobSubmit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySubmitStatus(jobSubmit);
	}

	/**
	* Returns a range of all the simulation jobs where jobSubmit = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jobSubmit the job submit
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySubmitStatus(
		boolean jobSubmit, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySubmitStatus(jobSubmit, start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs where jobSubmit = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jobSubmit the job submit
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findBySubmitStatus(
		boolean jobSubmit, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySubmitStatus(jobSubmit, start, end, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBySubmitStatus_First(
		boolean jobSubmit,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySubmitStatus_First(jobSubmit, orderByComparator);
	}

	/**
	* Returns the first simulation job in the ordered set where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBySubmitStatus_First(
		boolean jobSubmit,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySubmitStatus_First(jobSubmit, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findBySubmitStatus_Last(
		boolean jobSubmit,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySubmitStatus_Last(jobSubmit, orderByComparator);
	}

	/**
	* Returns the last simulation job in the ordered set where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching simulation job, or <code>null</code> if a matching simulation job could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchBySubmitStatus_Last(
		boolean jobSubmit,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySubmitStatus_Last(jobSubmit, orderByComparator);
	}

	/**
	* Returns the simulation jobs before and after the current simulation job in the ordered set where jobSubmit = &#63;.
	*
	* @param simulationJobPK the primary key of the current simulation job
	* @param jobSubmit the job submit
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob[] findBySubmitStatus_PrevAndNext(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK,
		boolean jobSubmit,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence()
				   .findBySubmitStatus_PrevAndNext(simulationJobPK, jobSubmit,
			orderByComparator);
	}

	/**
	* Removes all the simulation jobs where jobSubmit = &#63; from the database.
	*
	* @param jobSubmit the job submit
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySubmitStatus(boolean jobSubmit)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySubmitStatus(jobSubmit);
	}

	/**
	* Returns the number of simulation jobs where jobSubmit = &#63;.
	*
	* @param jobSubmit the job submit
	* @return the number of matching simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySubmitStatus(boolean jobSubmit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySubmitStatus(jobSubmit);
	}

	/**
	* Caches the simulation job in the entity cache if it is enabled.
	*
	* @param simulationJob the simulation job
	*/
	public static void cacheResult(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob) {
		getPersistence().cacheResult(simulationJob);
	}

	/**
	* Caches the simulation jobs in the entity cache if it is enabled.
	*
	* @param simulationJobs the simulation jobs
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> simulationJobs) {
		getPersistence().cacheResult(simulationJobs);
	}

	/**
	* Creates a new simulation job with the primary key. Does not add the simulation job to the database.
	*
	* @param simulationJobPK the primary key for the new simulation job
	* @return the new simulation job
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob create(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK) {
		return getPersistence().create(simulationJobPK);
	}

	/**
	* Removes the simulation job with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job that was removed
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob remove(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence().remove(simulationJobPK);
	}

	public static org.kisti.edison.bestsimulation.model.SimulationJob updateImpl(
		org.kisti.edison.bestsimulation.model.SimulationJob simulationJob)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(simulationJob);
	}

	/**
	* Returns the simulation job with the primary key or throws a {@link org.kisti.edison.bestsimulation.NoSuchSimulationJobException} if it could not be found.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job
	* @throws org.kisti.edison.bestsimulation.NoSuchSimulationJobException if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob findByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.bestsimulation.NoSuchSimulationJobException {
		return getPersistence().findByPrimaryKey(simulationJobPK);
	}

	/**
	* Returns the simulation job with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param simulationJobPK the primary key of the simulation job
	* @return the simulation job, or <code>null</code> if a simulation job with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.bestsimulation.model.SimulationJob fetchByPrimaryKey(
		org.kisti.edison.bestsimulation.service.persistence.SimulationJobPK simulationJobPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(simulationJobPK);
	}

	/**
	* Returns all the simulation jobs.
	*
	* @return the simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @return the range of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the simulation jobs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation jobs
	* @param end the upper bound of the range of simulation jobs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.bestsimulation.model.SimulationJob> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the simulation jobs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of simulation jobs.
	*
	* @return the number of simulation jobs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SimulationJobPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SimulationJobPersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.bestsimulation.service.ClpSerializer.getServletContextName(),
					SimulationJobPersistence.class.getName());

			ReferenceRegistry.registerReference(SimulationJobUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SimulationJobPersistence persistence) {
	}

	private static SimulationJobPersistence _persistence;
}