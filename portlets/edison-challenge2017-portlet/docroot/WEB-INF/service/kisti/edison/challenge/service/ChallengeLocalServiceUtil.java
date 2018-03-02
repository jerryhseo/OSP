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
 * Provides the local service utility for Challenge. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChallengeLocalService
 * @see kisti.edison.challenge.service.base.ChallengeLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeLocalServiceImpl
 * @generated
 */
public class ChallengeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge to the database. Also notifies the appropriate model listeners.
	*
	* @param challenge the challenge
	* @return the challenge that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge addChallenge(
		kisti.edison.challenge.model.Challenge challenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChallenge(challenge);
	}

	/**
	* Creates a new challenge with the primary key. Does not add the challenge to the database.
	*
	* @param challengeId the primary key for the new challenge
	* @return the new challenge
	*/
	public static kisti.edison.challenge.model.Challenge createChallenge(
		long challengeId) {
		return getService().createChallenge(challengeId);
	}

	/**
	* Deletes the challenge with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeId the primary key of the challenge
	* @return the challenge that was removed
	* @throws PortalException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge deleteChallenge(
		long challengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallenge(challengeId);
	}

	/**
	* Deletes the challenge from the database. Also notifies the appropriate model listeners.
	*
	* @param challenge the challenge
	* @return the challenge that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge deleteChallenge(
		kisti.edison.challenge.model.Challenge challenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallenge(challenge);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static kisti.edison.challenge.model.Challenge fetchChallenge(
		long challengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallenge(challengeId);
	}

	/**
	* Returns the challenge with the matching UUID and company.
	*
	* @param uuid the challenge's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge fetchChallengeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallengeByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge matching the UUID and group.
	*
	* @param uuid the challenge's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge, or <code>null</code> if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge fetchChallengeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallengeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the challenge with the primary key.
	*
	* @param challengeId the primary key of the challenge
	* @return the challenge
	* @throws PortalException if a challenge with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge getChallenge(
		long challengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenge(challengeId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge with the matching UUID and company.
	*
	* @param uuid the challenge's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge
	* @throws PortalException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge getChallengeByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge matching the UUID and group.
	*
	* @param uuid the challenge's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge
	* @throws PortalException if a matching challenge could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge getChallengeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the challenges.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenges
	* @param end the upper bound of the range of challenges (not inclusive)
	* @return the range of challenges
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(start, end);
	}

	/**
	* Returns the number of challenges.
	*
	* @return the number of challenges
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengesCount();
	}

	/**
	* Updates the challenge in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challenge the challenge
	* @return the challenge that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.Challenge updateChallenge(
		kisti.edison.challenge.model.Challenge challenge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateChallenge(challenge);
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

	public static kisti.edison.challenge.model.Challenge getChallengeByGroupAndField(
		long groupId, java.lang.String field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			kisti.edison.challenge.NoSuchChallengeException {
		return getService()
				   .getChallengeByGroupAndField(groupId, field,
			orderByComparator);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(groupId);
	}

	public static boolean hasManagerRole(long groupId, long userId,
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasManagerRole(groupId, userId, companyId);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallengesByManagerRole(
		long groupId, long userId, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengesByManagerRole(groupId, userId, companyId);
	}

	public static boolean hasEvaluationRole(long groupId, long userId,
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasEvaluationRole(groupId, userId, companyId);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallengesByEvaluationRole(
		long groupId, long userId, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengesByEvaluationRole(groupId, userId, companyId);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(groupId, status);
	}

	public static java.util.List<kisti.edison.challenge.model.Challenge> getChallenges(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallenges(groupId, start, end);
	}

	public static int getChallengesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengesCount(groupId);
	}

	public static kisti.edison.challenge.model.Challenge addChallenge(
		long userId, java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallenge(userId, name, field, description,
			serviceContext);
	}

	public static kisti.edison.challenge.model.Challenge updateChallenge(
		long userId, long challengeId,
		java.util.Map<java.util.Locale, java.lang.String> name,
		java.util.Map<java.util.Locale, java.lang.String> field,
		java.util.Map<java.util.Locale, java.lang.String> description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallenge(userId, challengeId, name, field,
			description, serviceContext);
	}

	public static kisti.edison.challenge.model.Challenge deleteChallenge(
		long challengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallenge(challengeId, serviceContext);
	}

	public static kisti.edison.challenge.model.Challenge updateStatus(
		long userId, long challengeId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, challengeId, status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeLocalService) {
				_service = (ChallengeLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeLocalService service) {
	}

	private static ChallengeLocalService _service;
}