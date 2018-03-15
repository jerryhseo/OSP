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
 * Provides a wrapper for {@link ChallengeTeamLocalService}.
 *
 * @author KYJ
 * @see ChallengeTeamLocalService
 * @generated
 */
public class ChallengeTeamLocalServiceWrapper
	implements ChallengeTeamLocalService,
		ServiceWrapper<ChallengeTeamLocalService> {
	public ChallengeTeamLocalServiceWrapper(
		ChallengeTeamLocalService challengeTeamLocalService) {
		_challengeTeamLocalService = challengeTeamLocalService;
	}

	/**
	* Adds the challenge team to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.addChallengeTeam(challengeTeam);
	}

	/**
	* Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	*
	* @param challengeTeamId the primary key for the new challenge team
	* @return the new challenge team
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam createChallengeTeam(
		long challengeTeamId) {
		return _challengeTeamLocalService.createChallengeTeam(challengeTeamId);
	}

	/**
	* Deletes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamId the primary key of the challenge team
	* @return the challenge team that was removed
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.deleteChallengeTeam(challengeTeamId);
	}

	/**
	* Deletes the challenge team from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.deleteChallengeTeam(challengeTeam);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _challengeTeamLocalService.dynamicQuery();
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
		return _challengeTeamLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _challengeTeamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _challengeTeamLocalService.dynamicQuery(dynamicQuery, start,
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
		return _challengeTeamLocalService.dynamicQueryCount(dynamicQuery);
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
		return _challengeTeamLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.fetchChallengeTeam(challengeTeamId);
	}

	/**
	* Returns the challenge team with the matching UUID and company.
	*
	* @param uuid the challenge team's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeamByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.fetchChallengeTeamByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge team matching the UUID and group.
	*
	* @param uuid the challenge team's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeamByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.fetchChallengeTeamByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the challenge team with the primary key.
	*
	* @param challengeTeamId the primary key of the challenge team
	* @return the challenge team
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeam(challengeTeamId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge team with the matching UUID and company.
	*
	* @param uuid the challenge team's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team
	* @throws PortalException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeamByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge team matching the UUID and group.
	*
	* @param uuid the challenge team's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team
	* @throws PortalException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeamByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the challenge teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @return the range of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeams(start, end);
	}

	/**
	* Returns the number of challenge teams.
	*
	* @return the number of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getChallengeTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamsCount();
	}

	/**
	* Updates the challenge team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public kisti.edison.challenge.model.ChallengeTeam updateChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.updateChallengeTeam(challengeTeam);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeTeamLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeTeamLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeTeamLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeames(groupId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeames(groupId, status);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeams(groupId,
			childChallengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamsAndEvaluationOrder(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamsAndEvaluationOrder(groupId,
			childChallengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamsAndEvaluationOrder(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamsAndEvaluationOrder(groupId,
			childChallengeId, start, end);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeams(groupId,
			childChallengeId, start, end);
	}

	@Override
	public int getChallengeTeamsCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamsCount(groupId,
			childChallengeId);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
		long userId, java.lang.String teamName,
		java.util.Map<java.util.Locale, java.lang.String> subject,
		java.util.Map<java.util.Locale, java.lang.String> paperName,
		java.util.Map<java.util.Locale, java.lang.String> paperAbstract,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone, long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.addChallengeTeam(userId, teamName,
			subject, paperName, paperAbstract, uploadRequest, grade, phone,
			childChallengeId, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam updateChallengeTeam(
		long userId, long childChallengeId, long challengeTeamId,
		java.lang.String teamName,
		java.util.Map<java.util.Locale, java.lang.String> subject,
		java.util.Map<java.util.Locale, java.lang.String> paperName,
		java.util.Map<java.util.Locale, java.lang.String> paperAbstract,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.updateChallengeTeam(userId,
			childChallengeId, challengeTeamId, teamName, subject, paperName,
			paperAbstract, uploadRequest, grade, phone, serviceContext);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.deleteChallengeTeam(challengeTeamId,
			serviceContext);
	}

	@Override
	public boolean fileUpload(long childChallengeId, java.lang.String fileType,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		long challengeTeamId) {
		return _challengeTeamLocalService.fileUpload(childChallengeId,
			fileType, uploadRequest, challengeTeamId);
	}

	@Override
	public void fileDownload(long challengeTeamId, java.lang.String fileType,
		java.lang.String fileName,
		javax.portlet.ResourceRequest resourceRequest,
		javax.portlet.ResourceResponse resourceResponse)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_challengeTeamLocalService.fileDownload(challengeTeamId, fileType,
			fileName, resourceRequest, resourceResponse);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam updateStatus(
		long userId, long challengeTeamId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.updateStatus(userId, challengeTeamId,
			status, serviceContext);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamByMemberUser(
		long groupId, long applyUserId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByMemberUser(groupId,
			applyUserId, childChallengeId);
	}

	@Override
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamByMemberUser(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByMemberUser(groupId,
			applyUserId, start, end);
	}

	@Override
	public java.util.List<java.lang.String> getTeamAppList(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getTeamAppList(companyId,
			challengeTeamId);
	}

	@Override
	public java.lang.String getTeamAppListString(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getTeamAppListString(companyId,
			challengeTeamId);
	}

	@Override
	public java.lang.String getTeamAppListStringForWeb(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getTeamAppListStringForWeb(companyId,
			challengeTeamId);
	}

	@Override
	public int getTeamSimulationNumber(long companyId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getTeamSimulationNumber(companyId,
			challengeTeamId);
	}

	@Override
	public java.lang.String getCPUUseage(long companyId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getCPUUseage(companyId,
			challengeTeamId);
	}

	@Override
	public boolean isChallengeTeamMember(long groupId, long challengeTeamId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.isChallengeTeamMember(groupId,
			challengeTeamId, userId);
	}

	@Override
	public void getUserInfo(long challengeTeamId,
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response) throws java.lang.Exception {
		_challengeTeamLocalService.getUserInfo(challengeTeamId, request,
			response);
	}

	@Override
	public kisti.edison.challenge.model.ChallengeTeam getUserCurrentTeam(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getUserCurrentTeam(groupId, userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeTeamLocalService getWrappedChallengeTeamLocalService() {
		return _challengeTeamLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeTeamLocalService(
		ChallengeTeamLocalService challengeTeamLocalService) {
		_challengeTeamLocalService = challengeTeamLocalService;
	}

	@Override
	public ChallengeTeamLocalService getWrappedService() {
		return _challengeTeamLocalService;
	}

	@Override
	public void setWrappedService(
		ChallengeTeamLocalService challengeTeamLocalService) {
		_challengeTeamLocalService = challengeTeamLocalService;
	}

	private ChallengeTeamLocalService _challengeTeamLocalService;
}