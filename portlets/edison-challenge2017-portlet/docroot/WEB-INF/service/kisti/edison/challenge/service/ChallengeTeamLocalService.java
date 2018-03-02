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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for ChallengeTeam. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author KYJ
 * @see ChallengeTeamLocalServiceUtil
 * @see kisti.edison.challenge.service.base.ChallengeTeamLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeTeamLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ChallengeTeamLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChallengeTeamLocalServiceUtil} to access the challenge team local service. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeTeamLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the challenge team to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	*
	* @param challengeTeamId the primary key for the new challenge team
	* @return the new challenge team
	*/
	public kisti.edison.challenge.model.ChallengeTeam createChallengeTeam(
		long challengeTeamId);

	/**
	* Deletes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamId the primary key of the challenge team
	* @return the challenge team that was removed
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the challenge team from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team with the matching UUID and company.
	*
	* @param uuid the challenge team's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeamByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team matching the UUID and group.
	*
	* @param uuid the challenge team's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team, or <code>null</code> if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam fetchChallengeTeamByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team with the primary key.
	*
	* @param challengeTeamId the primary key of the challenge team
	* @return the challenge team
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeam(
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team with the matching UUID and company.
	*
	* @param uuid the challenge team's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team
	* @throws PortalException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeamByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the challenge team matching the UUID and group.
	*
	* @param uuid the challenge team's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team
	* @throws PortalException if a matching challenge team could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam getChallengeTeamByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of challenge teams.
	*
	* @return the number of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the challenge team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public kisti.edison.challenge.model.ChallengeTeam updateChallengeTeam(
		kisti.edison.challenge.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeames(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamsAndEvaluationOrder(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamsAndEvaluationOrder(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeams(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getChallengeTeamsCount(long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeTeam addChallengeTeam(
		long userId, java.lang.String teamName,
		java.util.Map<java.util.Locale, java.lang.String> subject,
		java.util.Map<java.util.Locale, java.lang.String> paperName,
		java.util.Map<java.util.Locale, java.lang.String> paperAbstract,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		java.lang.String grade, java.lang.String phone, long childChallengeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
			com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeTeam deleteChallengeTeam(
		long challengeTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean fileUpload(long childChallengeId, java.lang.String fileType,
		com.liferay.portal.kernel.upload.UploadPortletRequest uploadRequest,
		long challengeTeamId);

	public void fileDownload(long challengeTeamId, java.lang.String fileType,
		java.lang.String fileName,
		javax.portlet.ResourceRequest resourceRequest,
		javax.portlet.ResourceResponse resourceResponse)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public kisti.edison.challenge.model.ChallengeTeam updateStatus(
		long userId, long challengeTeamId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamByMemberUser(
		long groupId, long applyUserId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<kisti.edison.challenge.model.ChallengeTeam> getChallengeTeamByMemberUser(
		long groupId, long applyUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.lang.String> getTeamAppList(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getTeamAppListString(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getTeamAppListStringForWeb(long companyId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTeamSimulationNumber(long companyId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getCPUUseage(long companyId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isChallengeTeamMember(long groupId, long challengeTeamId,
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getUserInfo(long challengeTeamId,
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response) throws java.lang.Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public kisti.edison.challenge.model.ChallengeTeam getUserCurrentTeam(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;
}