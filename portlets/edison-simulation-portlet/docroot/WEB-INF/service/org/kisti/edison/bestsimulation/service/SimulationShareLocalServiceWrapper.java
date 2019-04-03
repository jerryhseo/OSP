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
 * Provides a wrapper for {@link SimulationShareLocalService}.
 *
 * @author EDISON
 * @see SimulationShareLocalService
 * @generated
 */
public class SimulationShareLocalServiceWrapper
	implements SimulationShareLocalService,
		ServiceWrapper<SimulationShareLocalService> {
	public SimulationShareLocalServiceWrapper(
		SimulationShareLocalService simulationShareLocalService) {
		_simulationShareLocalService = simulationShareLocalService;
	}

	/**
	* Adds the simulation share to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare addSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.addSimulationShare(simulationShare);
	}

	/**
	* Creates a new simulation share with the primary key. Does not add the simulation share to the database.
	*
	* @param simulationSharePK the primary key for the new simulation share
	* @return the new simulation share
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare createSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK) {
		return _simulationShareLocalService.createSimulationShare(simulationSharePK);
	}

	/**
	* Deletes the simulation share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share that was removed
	* @throws PortalException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare deleteSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.deleteSimulationShare(simulationSharePK);
	}

	/**
	* Deletes the simulation share from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare deleteSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.deleteSimulationShare(simulationShare);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _simulationShareLocalService.dynamicQuery();
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
		return _simulationShareLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationShareLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _simulationShareLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _simulationShareLocalService.dynamicQueryCount(dynamicQuery);
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
		return _simulationShareLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare fetchSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.fetchSimulationShare(simulationSharePK);
	}

	/**
	* Returns the simulation share with the primary key.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share
	* @throws PortalException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare getSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.getSimulationShare(simulationSharePK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the simulation shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.bestsimulation.model.impl.SimulationShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of simulation shares
	* @param end the upper bound of the range of simulation shares (not inclusive)
	* @return the range of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> getSimulationShares(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.getSimulationShares(start, end);
	}

	/**
	* Returns the number of simulation shares.
	*
	* @return the number of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSimulationSharesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.getSimulationSharesCount();
	}

	/**
	* Updates the simulation share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare updateSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.updateSimulationShare(simulationShare);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _simulationShareLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_simulationShareLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _simulationShareLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public boolean sharingSimulationJob(int classId, int customId,
		int jobSeqNo, java.lang.String jobUuid, java.lang.String simulationUuid) {
		return _simulationShareLocalService.sharingSimulationJob(classId,
			customId, jobSeqNo, jobUuid, simulationUuid);
	}

	@Override
	public boolean deleteShareSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare) {
		return _simulationShareLocalService.deleteShareSimulationJob(simulationShare);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare getSimulationShare(
		int shareSeqNo, int jobSeqNo, java.lang.String jobUuid,
		java.lang.String simulationUuid) {
		return _simulationShareLocalService.getSimulationShare(shareSeqNo,
			jobSeqNo, jobUuid, simulationUuid);
	}

	@Override
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> findListByJobUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.findListByJobUuid(jobUuid);
	}

	@Override
	public boolean isExitByJobUUid(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.isExitByJobUUid(jobUuid);
	}

	@Override
	public void removeBySimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationShareLocalService.removeBySimulationUuid(simulationUuid);
	}

	@Override
	public void removeByJobUuid(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationShareLocalService.removeByJobUuid(jobUuid);
	}

	@Override
	public org.kisti.edison.bestsimulation.model.SimulationShare createByJobUuid(
		java.lang.String jobUuid, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _simulationShareLocalService.createByJobUuid(jobUuid, classId,
			customId);
	}

	/**
	* JobUUid를 통한 공유 항목 삭제 후 재정의
	*
	* @param jobUuid
	* @param classId
	* @param customIds - 다수 일경우 delimeter는 ,
	* @throws SystemException
	*/
	@Override
	public void removeAndCreateByJobUUids(java.lang.String jobUuid,
		long classId, java.lang.String customIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_simulationShareLocalService.removeAndCreateByJobUUids(jobUuid,
			classId, customIds);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SimulationShareLocalService getWrappedSimulationShareLocalService() {
		return _simulationShareLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSimulationShareLocalService(
		SimulationShareLocalService simulationShareLocalService) {
		_simulationShareLocalService = simulationShareLocalService;
	}

	@Override
	public SimulationShareLocalService getWrappedService() {
		return _simulationShareLocalService;
	}

	@Override
	public void setWrappedService(
		SimulationShareLocalService simulationShareLocalService) {
		_simulationShareLocalService = simulationShareLocalService;
	}

	private SimulationShareLocalService _simulationShareLocalService;
}