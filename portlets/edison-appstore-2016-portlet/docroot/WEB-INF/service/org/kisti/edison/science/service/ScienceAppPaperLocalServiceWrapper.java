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
 * Provides a wrapper for {@link ScienceAppPaperLocalService}.
 *
 * @author EDISON
 * @see ScienceAppPaperLocalService
 * @generated
 */
public class ScienceAppPaperLocalServiceWrapper
	implements ScienceAppPaperLocalService,
		ServiceWrapper<ScienceAppPaperLocalService> {
	public ScienceAppPaperLocalServiceWrapper(
		ScienceAppPaperLocalService scienceAppPaperLocalService) {
		_scienceAppPaperLocalService = scienceAppPaperLocalService;
	}

	/**
	* Adds the science app paper to the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppPaper the science app paper
	* @return the science app paper that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper addScienceAppPaper(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.addScienceAppPaper(scienceAppPaper);
	}

	/**
	* Creates a new science app paper with the primary key. Does not add the science app paper to the database.
	*
	* @param scienceAppPaperPK the primary key for the new science app paper
	* @return the new science app paper
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper createScienceAppPaper(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK) {
		return _scienceAppPaperLocalService.createScienceAppPaper(scienceAppPaperPK);
	}

	/**
	* Deletes the science app paper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppPaperPK the primary key of the science app paper
	* @return the science app paper that was removed
	* @throws PortalException if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper deleteScienceAppPaper(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.deleteScienceAppPaper(scienceAppPaperPK);
	}

	/**
	* Deletes the science app paper from the database. Also notifies the appropriate model listeners.
	*
	* @param scienceAppPaper the science app paper
	* @return the science app paper that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper deleteScienceAppPaper(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.deleteScienceAppPaper(scienceAppPaper);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scienceAppPaperLocalService.dynamicQuery();
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
		return _scienceAppPaperLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppPaperLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.science.model.impl.ScienceAppPaperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scienceAppPaperLocalService.dynamicQuery(dynamicQuery, start,
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
		return _scienceAppPaperLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scienceAppPaperLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.kisti.edison.science.model.ScienceAppPaper fetchScienceAppPaper(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.fetchScienceAppPaper(scienceAppPaperPK);
	}

	/**
	* Returns the science app paper with the primary key.
	*
	* @param scienceAppPaperPK the primary key of the science app paper
	* @return the science app paper
	* @throws PortalException if a science app paper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper getScienceAppPaper(
		org.kisti.edison.science.service.persistence.ScienceAppPaperPK scienceAppPaperPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.getScienceAppPaper(scienceAppPaperPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.getPersistedModel(primaryKeyObj);
	}

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
	@Override
	public java.util.List<org.kisti.edison.science.model.ScienceAppPaper> getScienceAppPapers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.getScienceAppPapers(start, end);
	}

	/**
	* Returns the number of science app papers.
	*
	* @return the number of science app papers
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getScienceAppPapersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.getScienceAppPapersCount();
	}

	/**
	* Updates the science app paper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scienceAppPaper the science app paper
	* @return the science app paper that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.science.model.ScienceAppPaper updateScienceAppPaper(
		org.kisti.edison.science.model.ScienceAppPaper scienceAppPaper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _scienceAppPaperLocalService.updateScienceAppPaper(scienceAppPaper);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _scienceAppPaperLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scienceAppPaperLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _scienceAppPaperLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScienceAppPaperLocalService getWrappedScienceAppPaperLocalService() {
		return _scienceAppPaperLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScienceAppPaperLocalService(
		ScienceAppPaperLocalService scienceAppPaperLocalService) {
		_scienceAppPaperLocalService = scienceAppPaperLocalService;
	}

	@Override
	public ScienceAppPaperLocalService getWrappedService() {
		return _scienceAppPaperLocalService;
	}

	@Override
	public void setWrappedService(
		ScienceAppPaperLocalService scienceAppPaperLocalService) {
		_scienceAppPaperLocalService = scienceAppPaperLocalService;
	}

	private ScienceAppPaperLocalService _scienceAppPaperLocalService;
}