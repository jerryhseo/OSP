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

package org.kisti.edison.bestsimulation.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SimulationExeStsMigrationLocalService}.
 *
 * @author EDISON
 * @see SimulationExeStsMigrationLocalService
 * @generated
 */
public class SimulationExeStsMigrationLocalServiceWrapper
	implements SimulationExeStsMigrationLocalService,
		ServiceWrapper<SimulationExeStsMigrationLocalService> {
	public SimulationExeStsMigrationLocalServiceWrapper(
		SimulationExeStsMigrationLocalService simulationExeStsMigrationLocalService) {
		_simulationExeStsMigrationLocalService = simulationExeStsMigrationLocalService;
	}

	/**
	* Adds the simulation exe sts migration to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationExeStsMigration the simulation exe sts migration
	* @return the simulation exe sts migration that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration addSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.model.SimulationExeStsMigration simulationExeStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.addSimulationExeStsMigration(simulationExeStsMigration);
	}

	/**
	* Creates a new simulation exe sts migration with the primary key. Does not add the simulation exe sts migration to the database.
	*
	* @param simulationExeStsMigrationPK the primary key for the new simulation exe sts migration
	* @return the new simulation exe sts migration
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration createSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK simulationExeStsMigrationPK) {
		return _simulationExeStsMigrationLocalService.createSimulationExeStsMigration(simulationExeStsMigrationPK);
	}

	/**
	* Deletes the simulation exe sts migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationExeStsMigrationPK the primary key of the simulation exe sts migration
	* @return the simulation exe sts migration that was removed
	* @throws PortalException if a simulation exe sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration deleteSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK simulationExeStsMigrationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.deleteSimulationExeStsMigration(simulationExeStsMigrationPK);
	}

	/**
	* Deletes the simulation exe sts migration from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationExeStsMigration the simulation exe sts migration
	* @return the simulation exe sts migration that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration deleteSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.model.SimulationExeStsMigration simulationExeStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.deleteSimulationExeStsMigration(simulationExeStsMigration);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _simulationExeStsMigrationLocalService.dynamicQuery();
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
		return _simulationExeStsMigrationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationExeStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationExeStsMigrationLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationExeStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationExeStsMigrationLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _simulationExeStsMigrationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _simulationExeStsMigrationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration fetchSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK simulationExeStsMigrationPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.fetchSimulationExeStsMigration(simulationExeStsMigrationPK);
	}

	/**
	* Returns the simulation exe sts migration with the primary key.
	*
	* @param simulationExeStsMigrationPK the primary key of the simulation exe sts migration
	* @return the simulation exe sts migration
	* @throws PortalException if a simulation exe sts migration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration getSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.service.persistence.SimulationExeStsMigrationPK simulationExeStsMigrationPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.getSimulationExeStsMigration(simulationExeStsMigrationPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the simulation exe sts migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationExeStsMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation exe sts migrations
	* @param end the upper bound of the range of simulation exe sts migrations (not inclusive)
	* @return the range of simulation exe sts migrations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationExeStsMigration> getSimulationExeStsMigrations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.getSimulationExeStsMigrations(start,
			end);
	}

	/**
	* Returns the number of simulation exe sts migrations.
	*
	* @return the number of simulation exe sts migrations
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSimulationExeStsMigrationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.getSimulationExeStsMigrationsCount();
	}

	/**
	* Updates the simulation exe sts migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationExeStsMigration the simulation exe sts migration
	* @return the simulation exe sts migration that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationExeStsMigration updateSimulationExeStsMigration(
		org.kisti.edison.bestsimulation.model.SimulationExeStsMigration simulationExeStsMigration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationExeStsMigrationLocalService.updateSimulationExeStsMigration(simulationExeStsMigration);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationExeStsMigrationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationExeStsMigrationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationExeStsMigrationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationExeStsMigrationLocalService getWrappedSimulationExeStsMigrationLocalService() {
		return _simulationExeStsMigrationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationExeStsMigrationLocalService(
		SimulationExeStsMigrationLocalService simulationExeStsMigrationLocalService) {
		_simulationExeStsMigrationLocalService = simulationExeStsMigrationLocalService;
	}

	@Override
	public SimulationExeStsMigrationLocalService getWrappedService() {
		return _simulationExeStsMigrationLocalService;
	}

	@Override
	public void setWrappedService(
		SimulationExeStsMigrationLocalService simulationExeStsMigrationLocalService) {
		_simulationExeStsMigrationLocalService = simulationExeStsMigrationLocalService;
	}

	private SimulationExeStsMigrationLocalService _simulationExeStsMigrationLocalService;
}