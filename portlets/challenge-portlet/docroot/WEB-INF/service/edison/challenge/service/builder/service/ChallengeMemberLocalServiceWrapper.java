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
 * Provides a wrapper for {@link ChallengeMemberLocalService}.
 *
 * @author kyj
 * @see ChallengeMemberLocalService
 * @generated
 */
public class ChallengeMemberLocalServiceWrapper
	implements ChallengeMemberLocalService,
		ServiceWrapper<ChallengeMemberLocalService> {
	public ChallengeMemberLocalServiceWrapper(
		ChallengeMemberLocalService challengeMemberLocalService) {
		_challengeMemberLocalService = challengeMemberLocalService;
	}

	/**
	* Adds the challenge member to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeMember the challenge member
	* @return the challenge member that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember addChallengeMember(
		edison.challenge.service.builder.model.ChallengeMember challengeMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.addChallengeMember(challengeMember);
	}

	/**
	* Creates a new challenge member with the primary key. Does not add the challenge member to the database.
	*
	* @param chMemberid the primary key for the new challenge member
	* @return the new challenge member
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember createChallengeMember(
		long chMemberid) {
		return _challengeMemberLocalService.createChallengeMember(chMemberid);
	}

	/**
	* Deletes the challenge member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chMemberid the primary key of the challenge member
	* @return the challenge member that was removed
	* @throws PortalException if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember deleteChallengeMember(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.deleteChallengeMember(chMemberid);
	}

	/**
	* Deletes the challenge member from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeMember the challenge member
	* @return the challenge member that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember deleteChallengeMember(
		edison.challenge.service.builder.model.ChallengeMember challengeMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.deleteChallengeMember(challengeMember);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _challengeMemberLocalService.dynamicQuery();
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
		return _challengeMemberLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _challengeMemberLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _challengeMemberLocalService.dynamicQuery(dynamicQuery, start,
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
		return _challengeMemberLocalService.dynamicQueryCount(dynamicQuery);
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
		return _challengeMemberLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeMember fetchChallengeMember(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.fetchChallengeMember(chMemberid);
	}

	/**
	* Returns the challenge member with the primary key.
	*
	* @param chMemberid the primary key of the challenge member
	* @return the challenge member
	* @throws PortalException if a challenge member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember getChallengeMember(
		long chMemberid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeMember(chMemberid);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the challenge members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge members
	* @param end the upper bound of the range of challenge members (not inclusive)
	* @return the range of challenge members
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeMember> getChallengeMembers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeMembers(start, end);
	}

	/**
	* Returns the number of challenge members.
	*
	* @return the number of challenge members
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getChallengeMembersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeMembersCount();
	}

	/**
	* Updates the challenge member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeMember the challenge member
	* @return the challenge member that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public edison.challenge.service.builder.model.ChallengeMember updateChallengeMember(
		edison.challenge.service.builder.model.ChallengeMember challengeMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.updateChallengeMember(challengeMember);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeMemberLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeMemberLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeMemberLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeMember> getChallengeMemberByTeam(
		long temaID) throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeMemberByTeam(temaID);
	}

	@Override
	public void addChallengeTeamMember(
		javax.portlet.ActionRequest actionRequest,
		javax.portlet.ActionResponse actionResponse, boolean isLeader,
		long chteamID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_challengeMemberLocalService.addChallengeTeamMember(actionRequest,
			actionResponse, isLeader, chteamID);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeamListByMember(
		java.lang.String screenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeTeamListByMember(screenName);
	}

	@Override
	public java.util.List<edison.challenge.service.builder.model.ChallengeMember> getChallengeTeamMemberByTeamID(
		long teamID) throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getChallengeTeamMemberByTeamID(teamID);
	}

	@Override
	public long getMemberIDbyScreenName(long teamID, java.lang.String screenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getMemberIDbyScreenName(teamID,
			screenName);
	}

	@Override
	public long getTeamLeaderToMemberID(long teamID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getTeamLeaderToMemberID(teamID);
	}

	@Override
	public int countOfTeamMember(long teamID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.countOfTeamMember(teamID);
	}

	@Override
	public edison.challenge.service.builder.model.ChallengeMember getLeaderMemeber(
		long teamID) throws com.liferay.portal.kernel.exception.SystemException {
		return _challengeMemberLocalService.getLeaderMemeber(teamID);
	}

	@Override
	public void deleteTeamMemberByService(
		javax.portlet.ActionRequest actionRequest,
		javax.portlet.ActionResponse actionResponse)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_challengeMemberLocalService.deleteTeamMemberByService(actionRequest,
			actionResponse);
	}

	@Override
	public void deleteMemberByTeam(long teamID)
		throws com.liferay.portal.kernel.exception.SystemException {
		_challengeMemberLocalService.deleteMemberByTeam(teamID);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeMemberLocalService getWrappedChallengeMemberLocalService() {
		return _challengeMemberLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeMemberLocalService(
		ChallengeMemberLocalService challengeMemberLocalService) {
		_challengeMemberLocalService = challengeMemberLocalService;
	}

	@Override
	public ChallengeMemberLocalService getWrappedService() {
		return _challengeMemberLocalService;
	}

	@Override
	public void setWrappedService(
		ChallengeMemberLocalService challengeMemberLocalService) {
		_challengeMemberLocalService = challengeMemberLocalService;
	}

	private ChallengeMemberLocalService _challengeMemberLocalService;
}