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
 * Provides a wrapper for {@link ChildChallengeLocalService}.
 *
 * @author kyj
 * @see ChildChallengeLocalService
 * @generated
 */
public class ChildChallengeLocalServiceWrapper
	implements ChildChallengeLocalService,
		ServiceWrapper<ChildChallengeLocalService> {
	public ChildChallengeLocalServiceWrapper(
		ChildChallengeLocalService childChallengeLocalService) {
		_childChallengeLocalService = childChallengeLocalService;
	}

	/**
	* Adds the child challenge to the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge addChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.addChildChallenge(childChallenge);
	}

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childid the primary key for the new child challenge
	* @return the new child challenge
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge createChildChallenge(
		long childid) {
		return _childChallengeLocalService.createChildChallenge(childid);
	}

	/**
	* Deletes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge deleteChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.deleteChildChallenge(childid);
	}

	/**
	* Deletes the child challenge from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge deleteChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.deleteChildChallenge(childChallenge);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _childChallengeLocalService.dynamicQuery();
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
		return _childChallengeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _childChallengeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _childChallengeLocalService.dynamicQuery(dynamicQuery, start,
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
		return _childChallengeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _childChallengeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public edison.challenge.service.builder.model.ChildChallenge fetchChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.fetchChildChallenge(childid);
	}

	/**
	* Returns the child challenge with the primary key.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge getChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getChildChallenge(childid);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> getChildChallenges(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getChildChallenges(start, end);
	}

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getChildChallengesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getChildChallengesCount();
	}

	/**
	* Updates the child challenge in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChildChallenge updateChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.updateChildChallenge(childChallenge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _childChallengeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_childChallengeLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _childChallengeLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> getChildByChallenge(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getChildByChallenge(challengeid);
	}

	@Override
	public int getChildByChallengeCount(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getChildByChallengeCount(challengeid);
	}

	@Override
	public void deleteChildChallengeAllCoverage(long childID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_childChallengeLocalService.deleteChildChallengeAllCoverage(childID);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChildChallenge> getRunningChild()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getRunningChild();
	}

	@Override
	public int getRunningChildCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.getRunningChildCount();
	}

	@Override
	public boolean isMemeberofTeam(long userID, long childID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _childChallengeLocalService.isMemeberofTeam(userID, childID);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChildChallengeLocalService getWrappedChildChallengeLocalService() {
		return _childChallengeLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChildChallengeLocalService(
		ChildChallengeLocalService childChallengeLocalService) {
		_childChallengeLocalService = childChallengeLocalService;
	}

	@Override
	public ChildChallengeLocalService getWrappedService() {
		return _childChallengeLocalService;
	}

	@Override
	public void setWrappedService(
		ChildChallengeLocalService childChallengeLocalService) {
		_childChallengeLocalService = childChallengeLocalService;
	}

	private ChildChallengeLocalService _childChallengeLocalService;
}