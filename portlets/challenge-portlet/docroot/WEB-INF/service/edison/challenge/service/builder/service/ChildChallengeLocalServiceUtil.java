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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ChildChallenge. This utility wraps
 * {@link edison.challenge.service.builder.service.impl.ChildChallengeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author kyj
 * @see ChildChallengeLocalService
 * @see edison.challenge.service.builder.service.base.ChildChallengeLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.impl.ChildChallengeLocalServiceImpl
 * @generated
 */
public class ChildChallengeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link edison.challenge.service.builder.service.impl.ChildChallengeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the child challenge to the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was added
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge addChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChildChallenge(childChallenge);
	}

	/**
	* Creates a new child challenge with the primary key. Does not add the child challenge to the database.
	*
	* @param childid the primary key for the new child challenge
	* @return the new child challenge
	*/
	public static edison.challenge.service.builder.model.ChildChallenge createChildChallenge(
		long childid) {
		return getService().createChildChallenge(childid);
	}

	/**
	* Deletes the child challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge that was removed
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge deleteChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChildChallenge(childid);
	}

	/**
	* Deletes the child challenge from the database. Also notifies the appropriate model listeners.
	*
	* @param childChallenge the child challenge
	* @return the child challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge deleteChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChildChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static edison.challenge.service.builder.model.ChildChallenge fetchChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChildChallenge(childid);
	}

	/**
	* Returns the child challenge with the primary key.
	*
	* @param childid the primary key of the child challenge
	* @return the child challenge
	* @throws PortalException if a child challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChildChallenge getChildChallenge(
		long childid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildChallenge(childid);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> getChildChallenges(
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
	public static edison.challenge.service.builder.model.ChildChallenge updateChildChallenge(
		edison.challenge.service.builder.model.ChildChallenge childChallenge)
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

	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> getChildByChallenge(
		long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildByChallenge(challengeid);
	}

	public static int getChildByChallengeCount(long challengeid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildByChallengeCount(challengeid);
	}

	public static void deleteChildChallengeAllCoverage(long childID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteChildChallengeAllCoverage(childID);
	}

	public static java.util.List<edison.challenge.service.builder.model.ChildChallenge> getRunningChild()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRunningChild();
	}

	public static int getRunningChildCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRunningChildCount();
	}

	public static boolean isMemeberofTeam(long userID, long childID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().isMemeberofTeam(userID, childID);
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