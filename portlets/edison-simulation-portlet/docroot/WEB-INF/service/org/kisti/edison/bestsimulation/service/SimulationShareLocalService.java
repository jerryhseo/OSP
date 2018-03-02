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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for SimulationShare. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author EDISON
 * @see SimulationShareLocalServiceUtil
 * @see org.kisti.edison.bestsimulation.service.base.SimulationShareLocalServiceBaseImpl
 * @see org.kisti.edison.bestsimulation.service.impl.SimulationShareLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SimulationShareLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SimulationShareLocalServiceUtil} to access the simulation share local service. Add custom service methods to {@link org.kisti.edison.bestsimulation.service.impl.SimulationShareLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the simulation share to the database. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.bestsimulation.model.SimulationShare addSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new simulation share with the primary key. Does not add the simulation share to the database.
	*
	* @param simulationSharePK the primary key for the new simulation share
	* @return the new simulation share
	*/
	public org.kisti.edison.bestsimulation.model.SimulationShare createSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK);

	/**
	* Deletes the simulation share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share that was removed
	* @throws PortalException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.bestsimulation.model.SimulationShare deleteSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the simulation share from the database. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public org.kisti.edison.bestsimulation.model.SimulationShare deleteSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.bestsimulation.model.SimulationShare fetchSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the simulation share with the primary key.
	*
	* @param simulationSharePK the primary key of the simulation share
	* @return the simulation share
	* @throws PortalException if a simulation share with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.bestsimulation.model.SimulationShare getSimulationShare(
		org.kisti.edison.bestsimulation.service.persistence.SimulationSharePK simulationSharePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> getSimulationShares(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of simulation shares.
	*
	* @return the number of simulation shares
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSimulationSharesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the simulation share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param simulationShare the simulation share
	* @return the simulation share that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public org.kisti.edison.bestsimulation.model.SimulationShare updateSimulationShare(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public boolean sharingSimulationJob(int classId, int customId,
		int jobSeqNo, java.lang.String jobUuid, java.lang.String simulationUuid);

	public boolean deleteShareSimulationJob(
		org.kisti.edison.bestsimulation.model.SimulationShare simulationShare);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public org.kisti.edison.bestsimulation.model.SimulationShare getSimulationShare(
		int shareSeqNo, int jobSeqNo, java.lang.String jobUuid,
		java.lang.String simulationUuid);

	public java.util.List<org.kisti.edison.bestsimulation.model.SimulationShare> findListByJobUuid(
		java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isExitByJobUUid(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeBySimulationUuid(java.lang.String simulationUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByJobUuid(java.lang.String jobUuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public org.kisti.edison.bestsimulation.model.SimulationShare createByJobUuid(
		java.lang.String jobUuid, long classId, long customId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* JobUUid를 통한 공유 항목 삭제 후 재정의
	*
	* @param jobUuid
	* @param classId
	* @param customIds - 다수 일경우 delimeter는 ,
	* @throws SystemException
	*/
	public void removeAndCreateByJobUUids(java.lang.String jobUuid,
		long classId, java.lang.String customIds)
		throws com.liferay.portal.kernel.exception.SystemException;
}