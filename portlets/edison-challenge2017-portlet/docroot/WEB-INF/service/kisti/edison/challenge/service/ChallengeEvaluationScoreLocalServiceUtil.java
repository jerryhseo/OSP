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
 * Provides the local service utility for ChallengeEvaluationScore. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationScoreLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreLocalService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationScoreLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationScoreLocalServiceImpl
 * @generated
 */
public class ChallengeEvaluationScoreLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationScoreLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge evaluation score to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChallengeEvaluationScore(challengeEvaluationScore);
	}

	/**
	* Creates a new challenge evaluation score with the primary key. Does not add the challenge evaluation score to the database.
	*
	* @param challengeEvaluationScoreId the primary key for the new challenge evaluation score
	* @return the new challenge evaluation score
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore createChallengeEvaluationScore(
		long challengeEvaluationScoreId) {
		return getService()
				   .createChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Deletes the challenge evaluation score with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score that was removed
	* @throws PortalException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Deletes the challenge evaluation score from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvaluationScore(challengeEvaluationScore);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Returns the challenge evaluation score with the matching UUID and company.
	*
	* @param uuid the challenge evaluation score's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScoreByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationScoreByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge evaluation score matching the UUID and group.
	*
	* @param uuid the challenge evaluation score's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScoreByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationScoreByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation score with the primary key.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score
	* @throws PortalException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge evaluation score with the matching UUID and company.
	*
	* @param uuid the challenge evaluation score's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation score
	* @throws PortalException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoreByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge evaluation score matching the UUID and group.
	*
	* @param uuid the challenge evaluation score's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation score
	* @throws PortalException if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoreByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the challenge evaluation scores.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationScoreModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation scores
	* @param end the upper bound of the range of challenge evaluation scores (not inclusive)
	* @return the range of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationScores(start, end);
	}

	/**
	* Returns the number of challenge evaluation scores.
	*
	* @return the number of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengeEvaluationScoresCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationScoresCount();
	}

	/**
	* Updates the challenge evaluation score in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeEvaluationScore(challengeEvaluationScore);
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationScores(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScores(groupId, challengeTeamId);
	}

	public static java.util.List<java.lang.Long> getChallengeEvaluatiorCount(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluatiorCount(groupId, challengeTeamId);
	}

	public static int countChallengeEvaluationScore(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countChallengeEvaluationScore(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScores(groupId, challengeTeamId,
			start, end);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoresByChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoresByChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	public static int countChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .countChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByGroupIdAndChallengeEvaluationManagementIdAndUserId(
		long userId, long challengeEvaluationScoreId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationScoreByGroupIdAndChallengeEvaluationManagementIdAndUserId(userId,
			challengeEvaluationScoreId, groupId);
	}

	public static double getChallengeTeamTotalScore(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamTotalScore(groupId, challengeTeamId);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvaluationScore(challengeEvaluationScoreId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallengeEvaluationScore(userId, challengeTeamId,
			challengeEvaluationManagementId, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		long challengeEvaluationScoreId, long userId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeEvaluationScore(challengeEvaluationScoreId,
			userId, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationScore updateStatus(
		long userId, long challengeEvaluationScoreId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, challengeEvaluationScoreId, status,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationScoreLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationScoreLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeEvaluationScoreLocalService) {
				_service = (ChallengeEvaluationScoreLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeEvaluationScoreLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationScoreLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationScoreLocalService service) {
	}

	private static ChallengeEvaluationScoreLocalService _service;
}