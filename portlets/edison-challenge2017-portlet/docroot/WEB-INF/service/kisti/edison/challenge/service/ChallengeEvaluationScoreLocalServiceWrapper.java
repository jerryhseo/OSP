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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChallengeEvaluationScoreLocalService}.
 *
 * @author KYJ
 * @see ChallengeEvaluationScoreLocalService
 * @generated
 */
public class ChallengeEvaluationScoreLocalServiceWrapper
	implements ChallengeEvaluationScoreLocalService,
		ServiceWrapper<ChallengeEvaluationScoreLocalService> {
	public ChallengeEvaluationScoreLocalServiceWrapper(
		ChallengeEvaluationScoreLocalService challengeEvaluationScoreLocalService) {
		_challengeEvaluationScoreLocalService = challengeEvaluationScoreLocalService;
	}

	/**
	* Adds the challenge evaluation score to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.addChallengeEvaluationScore(challengeEvaluationScore);
	}

	/**
	* Creates a new challenge evaluation score with the primary key. Does not add the challenge evaluation score to the database.
	*
	* @param challengeEvaluationScoreId the primary key for the new challenge evaluation score
	* @return the new challenge evaluation score
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore createChallengeEvaluationScore(
		long challengeEvaluationScoreId) {
		return _challengeEvaluationScoreLocalService.createChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Deletes the challenge evaluation score with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score that was removed
	* @throws PortalException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.deleteChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Deletes the challenge evaluation score from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.deleteChallengeEvaluationScore(challengeEvaluationScore);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _challengeEvaluationScoreLocalService.dynamicQuery();
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
		return _challengeEvaluationScoreLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.dynamicQuery(dynamicQuery,
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
		return _challengeEvaluationScoreLocalService.dynamicQueryCount(dynamicQuery);
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
		return _challengeEvaluationScoreLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.fetchChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	/**
	* Returns the challenge evaluation score with the matching UUID and company.
	*
	* @param uuid the challenge evaluation score's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation score, or <code>null</code> if a matching challenge evaluation score could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScoreByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.fetchChallengeEvaluationScoreByUuidAndCompanyId(uuid,
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
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore fetchChallengeEvaluationScoreByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.fetchChallengeEvaluationScoreByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the challenge evaluation score with the primary key.
	*
	* @param challengeEvaluationScoreId the primary key of the challenge evaluation score
	* @return the challenge evaluation score
	* @throws PortalException if a challenge evaluation score with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScore(
		long challengeEvaluationScoreId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScore(challengeEvaluationScoreId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoreByUuidAndCompanyId(uuid,
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
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoreByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScores(start,
			end);
	}

	/**
	* Returns the number of challenge evaluation scores.
	*
	* @return the number of challenge evaluation scores
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getChallengeEvaluationScoresCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoresCount();
	}

	/**
	* Updates the challenge evaluation score in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationScore the challenge evaluation score
	* @return the challenge evaluation score that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		kisti.edison.challenge.model.ChallengeEvaluationScore challengeEvaluationScore)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.updateChallengeEvaluationScore(challengeEvaluationScore);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeEvaluationScoreLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeEvaluationScoreLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeEvaluationScoreLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScores(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScores(groupId,
			challengeTeamId);
	}

	@Override
	public java.util.List<java.lang.Long> getChallengeEvaluatiorCount(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluatiorCount(groupId,
			challengeTeamId);
	}

	@Override
	public int countChallengeEvaluationScore(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.countChallengeEvaluationScore(groupId,
			challengeTeamId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScores(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScores(groupId,
			challengeTeamId, start, end);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoresByChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoresByChallengeEvaluationManagementId(
		long groupId, long challengeEvaluationManagementId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoresByChallengeEvaluationManagementId(groupId,
			challengeEvaluationManagementId, start, end);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeEvaluationScore> getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	@Override
	public int countChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(
		long groupId, long challengeTeamId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.countChallengeEvaluationScoreByGroupIdAndChallengeTeamIdAndUserId(groupId,
			challengeTeamId, userId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore getChallengeEvaluationScoreByGroupIdAndChallengeEvaluationManagementIdAndUserId(
		long userId, long challengeEvaluationScoreId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeEvaluationScoreByGroupIdAndChallengeEvaluationManagementIdAndUserId(userId,
			challengeEvaluationScoreId, groupId);
	}

	@Override
	public double getChallengeTeamTotalScore(long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.getChallengeTeamTotalScore(groupId,
			challengeTeamId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore deleteChallengeEvaluationScore(
		long challengeEvaluationScoreId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.deleteChallengeEvaluationScore(challengeEvaluationScoreId,
			serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore addChallengeEvaluationScore(
		long userId, long challengeTeamId,
		long challengeEvaluationManagementId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.addChallengeEvaluationScore(userId,
			challengeTeamId, challengeEvaluationManagementId, score,
			serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore updateChallengeEvaluationScore(
		long challengeEvaluationScoreId, long userId, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.updateChallengeEvaluationScore(challengeEvaluationScoreId,
			userId, score, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeEvaluationScore updateStatus(
		long userId, long challengeEvaluationScoreId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeEvaluationScoreLocalService.updateStatus(userId,
			challengeEvaluationScoreId, status, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeEvaluationScoreLocalService getWrappedChallengeEvaluationScoreLocalService() {
		return _challengeEvaluationScoreLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeEvaluationScoreLocalService(
		ChallengeEvaluationScoreLocalService challengeEvaluationScoreLocalService) {
		_challengeEvaluationScoreLocalService = challengeEvaluationScoreLocalService;
	}

	@Override
	public ChallengeEvaluationScoreLocalService getWrappedService() {
		return _challengeEvaluationScoreLocalService;
	}

	@Override
	public void setWrappedService(
		ChallengeEvaluationScoreLocalService challengeEvaluationScoreLocalService) {
		_challengeEvaluationScoreLocalService = challengeEvaluationScoreLocalService;
	}

	private ChallengeEvaluationScoreLocalService _challengeEvaluationScoreLocalService;
}