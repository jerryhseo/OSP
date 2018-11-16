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

package org.kisti.edison.osp.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExecuteLocalService}.
 *
 * @author edison
 * @see ExecuteLocalService
 * @generated
 */
public class ExecuteLocalServiceWrapper implements ExecuteLocalService,
	ServiceWrapper<ExecuteLocalService> {
	public ExecuteLocalServiceWrapper(ExecuteLocalService executeLocalService) {
		_executeLocalService = executeLocalService;
	}

	/**
	* Adds the execute to the database. Also notifies the appropriate model listeners.
	*
	* @param execute the execute
	* @return the execute that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.osp.model.Execute addExecute(
		org.kisti.edison.osp.model.Execute execute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.addExecute(execute);
	}

	/**
	* Creates a new execute with the primary key. Does not add the execute to the database.
	*
	* @param executePK the primary key for the new execute
	* @return the new execute
	*/
	@Override
	public org.kisti.edison.osp.model.Execute createExecute(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK) {
		return _executeLocalService.createExecute(executePK);
	}

	/**
	* Deletes the execute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param executePK the primary key of the execute
	* @return the execute that was removed
	* @throws PortalException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.osp.model.Execute deleteExecute(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.deleteExecute(executePK);
	}

	/**
	* Deletes the execute from the database. Also notifies the appropriate model listeners.
	*
	* @param execute the execute
	* @return the execute that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.osp.model.Execute deleteExecute(
		org.kisti.edison.osp.model.Execute execute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.deleteExecute(execute);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _executeLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.osp.model.impl.ExecuteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.osp.model.Execute fetchExecute(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.fetchExecute(executePK);
	}

	/**
	* Returns the execute with the primary key.
	*
	* @param executePK the primary key of the execute
	* @return the execute
	* @throws PortalException if a execute with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.osp.model.Execute getExecute(
		org.kisti.edison.osp.service.persistence.ExecutePK executePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.getExecute(executePK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<org.kisti.edison.osp.model.Execute> getExecutes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.getExecutes(start, end);
	}

	/**
	* Returns the number of executes.
	*
	* @return the number of executes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getExecutesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.getExecutesCount();
	}

	/**
	* Updates the execute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param execute the execute
	* @return the execute that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.osp.model.Execute updateExecute(
		org.kisti.edison.osp.model.Execute execute)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _executeLocalService.updateExecute(execute);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _executeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_executeLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _executeLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void removeExecuteByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_executeLocalService.removeExecuteByProjectId(projectId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ExecuteLocalService getWrappedExecuteLocalService() {
		return _executeLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedExecuteLocalService(
		ExecuteLocalService executeLocalService) {
		_executeLocalService = executeLocalService;
	}

	@Override
	public ExecuteLocalService getWrappedService() {
		return _executeLocalService;
	}

	@Override
	public void setWrappedService(ExecuteLocalService executeLocalService) {
		_executeLocalService = executeLocalService;
	}

	private ExecuteLocalService _executeLocalService;
}