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

package org.kisti.edison.science.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RequiredLibConfirmLocalService}.
 *
 * @author EDISON
 * @see RequiredLibConfirmLocalService
 * @generated
 */
public class RequiredLibConfirmLocalServiceWrapper
	implements RequiredLibConfirmLocalService,
		ServiceWrapper<RequiredLibConfirmLocalService> {
	public RequiredLibConfirmLocalServiceWrapper(
		RequiredLibConfirmLocalService requiredLibConfirmLocalService) {
		_requiredLibConfirmLocalService = requiredLibConfirmLocalService;
	}

	/**
	* Adds the required lib confirm to the database. Also notifies the appropriate model listeners.
	*
	* @param requiredLibConfirm the required lib confirm
	* @return the required lib confirm that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm addRequiredLibConfirm(
		org.kisti.edison.science.model.RequiredLibConfirm requiredLibConfirm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.addRequiredLibConfirm(requiredLibConfirm);
	}

	/**
	* Creates a new required lib confirm with the primary key. Does not add the required lib confirm to the database.
	*
	* @param requiredLibConfirmPK the primary key for the new required lib confirm
	* @return the new required lib confirm
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm createRequiredLibConfirm(
		org.kisti.edison.science.service.persistence.RequiredLibConfirmPK requiredLibConfirmPK) {
		return _requiredLibConfirmLocalService.createRequiredLibConfirm(requiredLibConfirmPK);
	}

	/**
	* Deletes the required lib confirm with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param requiredLibConfirmPK the primary key of the required lib confirm
	* @return the required lib confirm that was removed
	* @throws PortalException if a required lib confirm with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm deleteRequiredLibConfirm(
		org.kisti.edison.science.service.persistence.RequiredLibConfirmPK requiredLibConfirmPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.deleteRequiredLibConfirm(requiredLibConfirmPK);
	}

	/**
	* Deletes the required lib confirm from the database. Also notifies the appropriate model listeners.
	*
	* @param requiredLibConfirm the required lib confirm
	* @return the required lib confirm that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm deleteRequiredLibConfirm(
		org.kisti.edison.science.model.RequiredLibConfirm requiredLibConfirm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.deleteRequiredLibConfirm(requiredLibConfirm);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _requiredLibConfirmLocalService.dynamicQuery();
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
		return _requiredLibConfirmLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.RequiredLibConfirmModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _requiredLibConfirmLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.RequiredLibConfirmModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _requiredLibConfirmLocalService.dynamicQuery(dynamicQuery,
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
		return _requiredLibConfirmLocalService.dynamicQueryCount(dynamicQuery);
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
		return _requiredLibConfirmLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm fetchRequiredLibConfirm(
		org.kisti.edison.science.service.persistence.RequiredLibConfirmPK requiredLibConfirmPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.fetchRequiredLibConfirm(requiredLibConfirmPK);
	}

	/**
	* Returns the required lib confirm with the primary key.
	*
	* @param requiredLibConfirmPK the primary key of the required lib confirm
	* @return the required lib confirm
	* @throws PortalException if a required lib confirm with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm getRequiredLibConfirm(
		org.kisti.edison.science.service.persistence.RequiredLibConfirmPK requiredLibConfirmPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirm(requiredLibConfirmPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the required lib confirms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.RequiredLibConfirmModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of required lib confirms
	* @param end the upper bound of the range of required lib confirms (not inclusive)
	* @return the range of required lib confirms
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.science.model.RequiredLibConfirm> getRequiredLibConfirms(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirms(start, end);
	}

	/**
	* Returns the number of required lib confirms.
	*
	* @return the number of required lib confirms
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getRequiredLibConfirmsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirmsCount();
	}

	/**
	* Updates the required lib confirm in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param requiredLibConfirm the required lib confirm
	* @return the required lib confirm that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm updateRequiredLibConfirm(
		org.kisti.edison.science.model.RequiredLibConfirm requiredLibConfirm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.updateRequiredLibConfirm(requiredLibConfirm);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _requiredLibConfirmLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_requiredLibConfirmLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _requiredLibConfirmLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public int countByScienceAppId(long scienceAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.countByScienceAppId(scienceAppId);
	}

	@Override
	public java.util.List<org.kisti.edison.science.model.RequiredLibConfirm> findByScienceAppId(
		long scienceAppId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.findByScienceAppId(scienceAppId,
			start, end, orderByComparator);
	}

	@Override
	public int getCountRequiredConfirm(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getCountRequiredConfirm(searchParam);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getRequiredLibConfirmList(
		java.util.Map<java.lang.String, java.lang.Object> searchParam,
		int begin, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirmList(searchParam,
			begin, end);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> getRequiredLibConfirmMap(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirmMap(searchParam);
	}

	@Override
	public org.kisti.edison.science.model.RequiredLibConfirm getRequiredLibConfirmObject(
		java.util.Map<java.lang.String, java.lang.Object> searchParam)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _requiredLibConfirmLocalService.getRequiredLibConfirmObject(searchParam);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public RequiredLibConfirmLocalService getWrappedRequiredLibConfirmLocalService() {
		return _requiredLibConfirmLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedRequiredLibConfirmLocalService(
		RequiredLibConfirmLocalService requiredLibConfirmLocalService) {
		_requiredLibConfirmLocalService = requiredLibConfirmLocalService;
	}

	@Override
	public RequiredLibConfirmLocalService getWrappedService() {
		return _requiredLibConfirmLocalService;
	}

	@Override
	public void setWrappedService(
		RequiredLibConfirmLocalService requiredLibConfirmLocalService) {
		_requiredLibConfirmLocalService = requiredLibConfirmLocalService;
	}

	private RequiredLibConfirmLocalService _requiredLibConfirmLocalService;
}