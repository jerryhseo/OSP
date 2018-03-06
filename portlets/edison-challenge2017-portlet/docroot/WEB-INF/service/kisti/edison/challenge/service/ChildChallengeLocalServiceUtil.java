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

package kisti.edison.challenge.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ChildChallenge. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChildChallengeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChildChallengeLocalService
 * @see kisti.edison.challenge.service.base.ChildChallengeLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChildChallengeLocalServiceImpl
 * @generated
 */
public class ChildChallengeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChildChallengeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the child challenge to the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge addChildChallenge(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChildChallenge(childChallenge);
	}

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childChallengeId the primary key for the new child challenge
	* @return the new child challenge
	*/
	public static kisti.edison.challenge.model.ChildChallenge createChildChallenge(
		long childChallengeId) {
		return getService().createChildChallenge(childChallengeId);
	}

	/**
	* Deletes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge deleteChildChallenge(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChildChallenge(childChallengeId);
	}

	/**
	* Deletes the child challenge from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge deleteChildChallenge(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChildChallenge(childChallenge);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static kisti.edison.challenge.model.ChildChallenge fetchChildChallenge(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChildChallenge(childChallengeId);
	}

	/**
	* Returns the child challenge with the matching UUID and company.
	*
	* @param uuid the child challenge's UUID
	* @param companyId the primary key of the company
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchChildChallengeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChildChallengeByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the child challenge matching the UUID and group.
	*
	* @param uuid the child challenge's UUID
	* @param groupId the primary key of the group
	* @return the matching child challenge, or <code>null</code> if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge fetchChildChallengeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChildChallengeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the child challenge with the primary key.
	*
	* @param childChallengeId the primary key of the child challenge
	* @return the child challenge
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge getChildChallenge(
		long childChallengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenge(childChallengeId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the child challenge with the matching UUID and company.
	*
	* @param uuid the child challenge's UUID
	* @param companyId the primary key of the company
	* @return the matching child challenge
	* @throws PortalException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge getChildChallengeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengeByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the child challenge matching the UUID and group.
	*
	* @param uuid the child challenge's UUID
	* @param groupId the primary key of the group
	* @return the matching child challenge
	* @throws PortalException if a matching child challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge getChildChallengeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the child challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of child challenges
	* @param end the upper bound of the range of child challenges (not inclusive)
	* @return the range of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(start, end);
	}

	/**
	* Returns the number of child challenges.
	*
	* @return the number of child challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int getChildChallengesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengesCount();
	}

	/**
	* Updates the child challenge in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChildChallenge updateChildChallenge(
		kisti.edison.challenge.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateChildChallenge(childChallenge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, challengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChildChallenges(groupId, challengeId, status, start, end);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChildChallenges(groupId, challengeId, challengeStatus);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, java.lang.String challengeStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, challengeStatus);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, int number)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, number);
	}

	public static java.util.List<kisti.edison.challenge.model.ChildChallenge> getChildChallenges(
		long groupId, long challengeId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenges(groupId, challengeId, status);
	}

	public static int getChildChallengesCount(long groupId, long challengeId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallengesCount(groupId, challengeId, status);
	}

	public static kisti.edison.challenge.model.ChildChallenge deleteChildChallenge(
		long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChildChallenge(childChallengeId, serviceContext);
	}

	public static kisti.edison.challenge.model.ChildChallenge addChildChallenge(
		long userId, long challengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChildChallenge(userId, challengeId, number,
			presentationDay, paperStartDay, paperEndDay, evaluationDay,
			challengeStartDay, challengeEndDay, challengeStatus, serviceContext);
	}

	public static kisti.edison.challenge.model.ChildChallenge updateChildChallenge(
		long userId, long challengeId, long childChallengeId, int number,
		java.util.Date presentationDay, java.util.Date paperStartDay,
		java.util.Date paperEndDay, java.util.Date evaluationDay,
		java.util.Date challengeStartDay, java.util.Date challengeEndDay,
		java.lang.String challengeStatus,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChildChallenge(userId, challengeId, childChallengeId,
			number, presentationDay, paperStartDay, paperEndDay, evaluationDay,
			challengeStartDay, challengeEndDay, challengeStatus, serviceContext);
	}

	public static kisti.edison.challenge.model.ChildChallenge updateStatus(
		long userId, long childChallengeId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, childChallengeId, status,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChildChallengeLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChildChallengeLocalService.class.getName());

			if (invokableLocalService instanceof ChildChallengeLocalService) {
				_service = (ChildChallengeLocalService)invokableLocalService;
			}
			else {
				_service = new ChildChallengeLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChildChallengeLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChildChallengeLocalService service) {
	}

	private static ChildChallengeLocalService _service;
}