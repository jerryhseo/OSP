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
 * Provides the local service utility for ChallengeTeamMember. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeTeamMemberLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChallengeTeamMemberLocalService
 * @see kisti.edison.challenge.service.base.ChallengeTeamMemberLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeTeamMemberLocalServiceImpl
 * @generated
 */
public class ChallengeTeamMemberLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeTeamMemberLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge team member to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMember the challenge team member
	* @return the challenge team member that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember addChallengeTeamMember(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChallengeTeamMember(challengeTeamMember);
	}

	/**
	* Creates a new challenge team member with the primary key. Does not add the challenge team member to the database.
	*
	* @param challengeTeamMemberId the primary key for the new challenge team member
	* @return the new challenge team member
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember createChallengeTeamMember(
		long challengeTeamMemberId) {
		return getService().createChallengeTeamMember(challengeTeamMemberId);
	}

	/**
	* Deletes the challenge team member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member that was removed
	* @throws PortalException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember deleteChallengeTeamMember(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeTeamMember(challengeTeamMemberId);
	}

	/**
	* Deletes the challenge team member from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMember the challenge team member
	* @return the challenge team member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember deleteChallengeTeamMember(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeTeamMember(challengeTeamMember);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static kisti.edison.challenge.model.ChallengeTeamMember fetchChallengeTeamMember(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallengeTeamMember(challengeTeamMemberId);
	}

	/**
	* Returns the challenge team member with the matching UUID and company.
	*
	* @param uuid the challenge team member's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchChallengeTeamMemberByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeTeamMemberByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge team member matching the UUID and group.
	*
	* @param uuid the challenge team member's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team member, or <code>null</code> if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember fetchChallengeTeamMemberByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeTeamMemberByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the challenge team member with the primary key.
	*
	* @param challengeTeamMemberId the primary key of the challenge team member
	* @return the challenge team member
	* @throws PortalException if a challenge team member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember getChallengeTeamMember(
		long challengeTeamMemberId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMember(challengeTeamMemberId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge team member with the matching UUID and company.
	*
	* @param uuid the challenge team member's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge team member
	* @throws PortalException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember getChallengeTeamMemberByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeTeamMemberByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge team member matching the UUID and group.
	*
	* @param uuid the challenge team member's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge team member
	* @throws PortalException if a matching challenge team member could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember getChallengeTeamMemberByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMemberByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the challenge team members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge team members
	* @param end the upper bound of the range of challenge team members (not inclusive)
	* @return the range of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> getChallengeTeamMembers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMembers(start, end);
	}

	/**
	* Returns the number of challenge team members.
	*
	* @return the number of challenge team members
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengeTeamMembersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMembersCount();
	}

	/**
	* Updates the challenge team member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeTeamMember the challenge team member
	* @return the challenge team member that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeTeamMember updateChallengeTeamMember(
		kisti.edison.challenge.model.ChallengeTeamMember challengeTeamMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateChallengeTeamMember(challengeTeamMember);
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> getChallengeTeamMembers(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMembers(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> getChallengeTeamMembers(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMembers(groupId, status);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> getChallengeTeamMembers(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMembers(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeTeamMember> getChallengeTeamMembers(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeTeamMembers(groupId, challengeTeamId, start, end);
	}

	public static int getChallengeTeamMemberCount(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamMemberCount(groupId, challengeTeamId);
	}

	public static int getCountChallengeTeamMembers(long groupId,
		long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCountChallengeTeamMembers(groupId, challengeTeamId);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember getChallengeTeamMember(
		long groupId, long challengeTeamId, long applyUserId) {
		return getService()
				   .getChallengeTeamMember(groupId, challengeTeamId, applyUserId);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember addChallengeTeamMember(
		long userId, long challengeTeamId, long applyUserId,
		java.lang.String grade, java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallengeTeamMember(userId, challengeTeamId,
			applyUserId, grade, phone, isLeader, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember updateChallengeTeamMember(
		long userId, long challengeTeamMemberId, java.lang.String grade,
		java.lang.String phone, boolean isLeader,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeTeamMember(userId, challengeTeamMemberId,
			grade, phone, isLeader, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember updateStatus(
		long userId, long challengeTeamMemberId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, challengeTeamMemberId, status,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember deleteChallengeTeamMember(
		long challengeTeamMemberId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeTeamMember(challengeTeamMemberId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeTeamMember getChallengeTeamLeaderMember(
		long challengeTeamId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeTeamLeaderMember(challengeTeamId, groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeTeamMemberLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeTeamMemberLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeTeamMemberLocalService) {
				_service = (ChallengeTeamMemberLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeTeamMemberLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeTeamMemberLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeTeamMemberLocalService service) {
	}

	private static ChallengeTeamMemberLocalService _service;
}