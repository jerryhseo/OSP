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
 * Provides the local service utility for ChallengeTeam. This utility wraps
 * {@link edison.challenge.service.builder.service.impl.ChallengeTeamLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author kyj
 * @see ChallengeTeamLocalService
 * @see edison.challenge.service.builder.service.base.ChallengeTeamLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.impl.ChallengeTeamLocalServiceImpl
 * @generated
 */
public class ChallengeTeamLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link edison.challenge.service.builder.service.impl.ChallengeTeamLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge team to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was added
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam addChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChallengeTeam(challengeTeam);
	}

	/**
	* Creates a new challenge team with the primary key. Does not add the challenge team to the database.
	*
	* @param chTeamid the primary key for the new challenge team
	* @return the new challenge team
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam createChallengeTeam(
		long chTeamid) {
		return getService().createChallengeTeam(chTeamid);
	}

	/**
	* Deletes the challenge team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team that was removed
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam deleteChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeTeam(chTeamid);
	}

	/**
	* Deletes the challenge team from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam deleteChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeTeam(challengeTeam);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edison.challenge.service.builder.model.impl.ChallengeTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static edison.challenge.service.builder.model.ChallengeTeam fetchChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallengeTeam(chTeamid);
	}

	/**
	* Returns the challenge team with the primary key.
	*
	* @param chTeamid the primary key of the challenge team
	* @return the challenge team
	* @throws PortalException if a challenge team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam getChallengeTeam(
		long chTeamid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeam(chTeamid);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeams(start, end);
	}

	/**
	* Returns the number of challenge teams.
	*
	* @return the number of challenge teams
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengeTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamsCount();
	}

	/**
	* Updates the challenge team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeTeam the challenge team
	* @return the challenge team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static edison.challenge.service.builder.model.ChallengeTeam updateChallengeTeam(
		edison.challenge.service.builder.model.ChallengeTeam challengeTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateChallengeTeam(challengeTeam);
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

	public static java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeamByChild(
		long childID)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamByChild(childID);
	}

	public static java.util.List<edison.challenge.service.builder.model.ChallengeTeam> getChallengeTeamByUserScreenName(
		java.lang.String memberScreenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeTeamByUserScreenName(memberScreenName);
	}

	public static boolean fileUpload(long childID, java.lang.String inputName,
		javax.portlet.ActionRequest actionRequest, long teamID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fileUpload(childID, inputName, actionRequest, teamID);
	}

	public static java.lang.String getCreateFilePath() {
		return getService().getCreateFilePath();
	}

	public static void teamFileDownLoad(long chTeamID,
		java.lang.String inpuName, java.lang.String fileName,
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.teamFileDownLoad(chTeamID, inpuName, fileName, request, response);
	}

	public static void deleteTeamandMember(
		javax.portlet.ActionRequest actionRequest,
		javax.portlet.ActionResponse actionResponse)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTeamandMember(actionRequest, actionResponse);
	}

	public static java.lang.String getTeamAppList(long companyID, long teamID) {
		return getService().getTeamAppList(companyID, teamID);
	}

	public static java.lang.String getTeamCPUUseage(long companyID, long teamID) {
		return getService().getTeamCPUUseage(companyID, teamID);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeTeamLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeTeamLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeTeamLocalService) {
				_service = (ChallengeTeamLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeTeamLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeTeamLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeTeamLocalService service) {
	}

	private static ChallengeTeamLocalService _service;
}