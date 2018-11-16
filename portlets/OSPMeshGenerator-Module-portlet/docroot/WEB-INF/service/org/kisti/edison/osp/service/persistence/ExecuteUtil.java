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

package org.kisti.edison.osp.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import org.kisti.edison.osp.model.Execute;

import java.util.List;

/**
 * The persistence utility for the execute service. This utility wraps {@link ExecutePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author edison
 * @see ExecutePersistence
 * @see ExecutePersistenceImpl
 * @generated
 */
public class ExecuteUtil {
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
	public static void clearCache(Execute execute) {
		getPersistence().clearCache(execute);
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
	public static List<Execute> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Execute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Execute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Execute update(Execute execute) throws SystemException {
		return getPersistence().update(execute);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Execute update(Execute execute, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(execute, serviceContext);
	}

	/**
	* Returns all the executes where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId);
	}

	/**
	* Returns a range of all the executes where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @return the range of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId, start, end);
	}

	/**
	* Returns an ordered range of all the executes where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

	/**
	* Returns the first execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the first execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching execute, or <code>null</code> if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute fetchByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the last execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the last execute in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching execute, or <code>null</code> if a matching execute could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute fetchByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the executes before and after the current execute in the ordered set where projectId = &#63;.
	*
	* @param executePK the primary key of the current execute
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute[] findByProjectId_PrevAndNext(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(executePK, projectId,
			orderByComparator);
	}

	/**
	* Removes all the executes where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Returns the number of executes where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching executes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Caches the execute in the entity cache if it is enabled.
	*
	* @param execute the execute
	*/
	public static void cacheResult(org.kisti.edison.osp.model.Execute execute) {
		getPersistence().cacheResult(execute);
	}

	/**
	* Caches the executes in the entity cache if it is enabled.
	*
	* @param executes the executes
	*/
	public static void cacheResult(
		java.util.List<org.kisti.edison.osp.model.Execute> executes) {
		getPersistence().cacheResult(executes);
	}

	/**
	* Creates a new execute with the primary key. Does not add the execute to the database.
	*
	* @param executePK the primary key for the new execute
	* @return the new execute
	*/
	public static org.kisti.edison.osp.model.Execute create(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK) {
		return getPersistence().create(executePK);
	}

	/**
	* Removes the execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param executePK the primary key of the execute
	* @return the execute that was removed
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute remove(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException {
		return getPersistence().remove(executePK);
	}

	public static org.kisti.edison.osp.model.Execute updateImpl(
		org.kisti.edison.osp.model.Execute execute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(execute);
	}

	/**
	* Returns the execute with the primary key or throws a {@link org.kisti.edison.osp.NoSuchExecuteException} if it could not be found.
	*
	* @param executePK the primary key of the execute
	* @return the execute
	* @throws org.kisti.edison.osp.NoSuchExecuteException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute findByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			org.kisti.edison.osp.NoSuchExecuteException {
		return getPersistence().findByPrimaryKey(executePK);
	}

	/**
	* Returns the execute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param executePK the primary key of the execute
	* @return the execute, or <code>null</code> if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.osp.model.Execute fetchByPrimaryKey(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(executePK);
	}

	/**
	* Returns all the executes.
	*
	* @return the executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @return the range of executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the executes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of executes
	* @param end the upper bound of the range of executes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of executes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.osp.model.Execute> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the executes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of executes.
	*
	* @return the number of executes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExecutePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExecutePersistence)PortletBeanLocatorUtil.locate(org.kisti.edison.osp.service.ClpSerializer.getServletContextName(),
					ExecutePersistence.class.getName());

			ReferenceRegistry.registerReference(ExecuteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ExecutePersistence persistence) {
	}

	private static ExecutePersistence _persistence;
}