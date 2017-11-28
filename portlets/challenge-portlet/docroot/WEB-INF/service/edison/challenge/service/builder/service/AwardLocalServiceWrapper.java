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

package edison.challenge.service.builder.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AwardLocalService}.
 *
 * @author kyj
 * @see AwardLocalService
 * @generated
 */
public class AwardLocalServiceWrapper implements AwardLocalService,
	ServiceWrapper<AwardLocalService> {
	public AwardLocalServiceWrapper(AwardLocalService awardLocalService) {
		_awardLocalService = awardLocalService;
	}

	/**
	* Adds the award to the database. Also notifies the appropriate model listeners.
	*
	* @param award the award
	* @return the award that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.Award addAward(
		edison.challenge.service.builder.model.Award award)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.addAward(award);
	}

	/**
	* Creates a new award with the primary key. Does not add the award to the database.
	*
	* @param awardid the primary key for the new award
	* @return the new award
	*/
	@Override
	public edison.challenge.service.builder.model.Award createAward(
		long awardid) {
		return _awardLocalService.createAward(awardid);
	}

	/**
	* Deletes the award with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param awardid the primary key of the award
	* @return the award that was removed
	* @throws PortalException if a award with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.Award deleteAward(
		long awardid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.deleteAward(awardid);
	}

	/**
	* Deletes the award from the database. Also notifies the appropriate model listeners.
	*
	* @param award the award
	* @return the award that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.Award deleteAward(
		edison.challenge.service.builder.model.Award award)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.deleteAward(award);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _awardLocalService.dynamicQuery();
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
		return _awardLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _awardLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _awardLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _awardLocalService.dynamicQueryCount(dynamicQuery);
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
		return _awardLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public edison.challenge.service.builder.model.Award fetchAward(long awardid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.fetchAward(awardid);
	}

	/**
	* Returns the award with the primary key.
	*
	* @param awardid the primary key of the award
	* @return the award
	* @throws PortalException if a award with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.Award getAward(long awardid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getAward(awardid);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the awards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.AwardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of awards
	* @param end the upper bound of the range of awards (not inclusive)
	* @return the range of awards
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<edison.challenge.service.builder.model.Award> getAwards(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getAwards(start, end);
	}

	/**
	* Returns the number of awards.
	*
	* @return the number of awards
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAwardsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getAwardsCount();
	}

	/**
	* Updates the award in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param award the award
	* @return the award that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.Award updateAward(
		edison.challenge.service.builder.model.Award award)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.updateAward(award);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _awardLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_awardLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _awardLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.Award> getAwardByChildCollet(
		long childChallengeID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getAwardByChildCollet(childChallengeID);
	}

	@Override
	public int getAwardByChildColletCount(long childChallengeID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _awardLocalService.getAwardByChildColletCount(childChallengeID);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AwardLocalService getWrappedAwardLocalService() {
		return _awardLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAwardLocalService(AwardLocalService awardLocalService) {
		_awardLocalService = awardLocalService;
	}

	@Override
	public AwardLocalService getWrappedService() {
		return _awardLocalService;
	}

	@Override
	public void setWrappedService(AwardLocalService awardLocalService) {
		_awardLocalService = awardLocalService;
	}

	private AwardLocalService _awardLocalService;
}