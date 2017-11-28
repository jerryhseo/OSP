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
 * Provides a wrapper for {@link ChallengeTeamLocalService}.
 *
 * @author kyj
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
	public edison.challenge.service.builder.model.ChallengeTeam addChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.addChallengeTeam(challengeTeam);
	}

	/**
	* Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	*
	* @param chTeamid the primary key for the new challenge team
	* @return the new challenge team
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeTeam createChallengeTeam(
		long chTeamid) {
		return _challengeTeamLocalService.createChallengeTeam(chTeamid);
	}

	/**
	* Deletes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team that was removed
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeTeam deleteChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.deleteChallengeTeam(chTeamid);
	}

	/**
	* Deletes the challenge team from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeTeam deleteChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public edison.challenge.service.builder.model.ChallengeTeam fetchChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.fetchChallengeTeam(chTeamid);
	}

	/**
	* Returns the challenge team with the primary key.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeTeam getChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeam(chTeamid);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the challenge teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge teams
	* @param end the upper bound of the range of challenge teams (not inclusive)
	* @return the range of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeams(
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
	public edison.challenge.service.builder.model.ChallengeTeam updateChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
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
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeamByChild(
		long childID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByChild(childID);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeamByUserScreenName(
		java.lang.String memberScreenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.getChallengeTeamByUserScreenName(memberScreenName);
	}

	@Override
	public boolean fileUpload(long childID, java.lang.String inputName,
		javax.portlet.ActionRequest actionRequest, long teamID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeTeamLocalService.fileUpload(childID, inputName,
			actionRequest, teamID);
	}

	@Override
	public java.lang.String getCreateFilePath() {
		return _challengeTeamLocalService.getCreateFilePath();
	}

	@Override
	public void teamFileDownLoad(long chTeamID, java.lang.String inpuName,
		java.lang.String fileName, javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_challengeTeamLocalService.teamFileDownLoad(chTeamID, inpuName,
			fileName, request, response);
	}

	@Override
	public void deleteTeamandMember(javax.portlet.ActionRequest actionRequest,
		javax.portlet.ActionResponse actionResponse)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_challengeTeamLocalService.deleteTeamandMember(actionRequest,
			actionResponse);
	}

	@Override
	public java.lang.String getTeamAppList(long companyID, long teamID) {
		return _challengeTeamLocalService.getTeamAppList(companyID, teamID);
	}

	@Override
	public java.lang.String getTeamCPUUseage(long companyID, long teamID) {
		return _challengeTeamLocalService.getTeamCPUUseage(companyID, teamID);
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