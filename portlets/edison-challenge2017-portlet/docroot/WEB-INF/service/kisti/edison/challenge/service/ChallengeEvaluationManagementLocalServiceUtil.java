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
 * Provides the local service utility for ChallengeEvaluationManagement. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationManagementLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChallengeEvaluationManagementLocalService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationManagementLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationManagementLocalServiceImpl
 * @generated
 */
public class ChallengeEvaluationManagementLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationManagementLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge evaluation management to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagement the challenge evaluation management
	* @return the challenge evaluation management that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement addChallengeEvaluationManagement(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallengeEvaluationManagement(challengeEvaluationManagement);
	}

	/**
	* Creates a new challenge evaluation management with the primary key. Does not add the challenge evaluation management to the database.
	*
	* @param challengeEvaluationManagementId the primary key for the new challenge evaluation management
	* @return the new challenge evaluation management
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement createChallengeEvaluationManagement(
		long challengeEvaluationManagementId) {
		return getService()
				   .createChallengeEvaluationManagement(challengeEvaluationManagementId);
	}

	/**
	* Deletes the challenge evaluation management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management that was removed
	* @throws PortalException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvaluationManagement(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvaluationManagement(challengeEvaluationManagementId);
	}

	/**
	* Deletes the challenge evaluation management from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagement the challenge evaluation management
	* @return the challenge evaluation management that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvaluationManagement(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvaluationManagement(challengeEvaluationManagement);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchChallengeEvaluationManagement(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationManagement(challengeEvaluationManagementId);
	}

	/**
	* Returns the challenge evaluation management with the matching UUID and company.
	*
	* @param uuid the challenge evaluation management's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchChallengeEvaluationManagementByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationManagementByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge evaluation management matching the UUID and group.
	*
	* @param uuid the challenge evaluation management's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation management, or <code>null</code> if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement fetchChallengeEvaluationManagementByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationManagementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the challenge evaluation management with the primary key.
	*
	* @param challengeEvaluationManagementId the primary key of the challenge evaluation management
	* @return the challenge evaluation management
	* @throws PortalException if a challenge evaluation management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement getChallengeEvaluationManagement(
		long challengeEvaluationManagementId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagement(challengeEvaluationManagementId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge evaluation management with the matching UUID and company.
	*
	* @param uuid the challenge evaluation management's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation management
	* @throws PortalException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement getChallengeEvaluationManagementByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the challenge evaluation management matching the UUID and group.
	*
	* @param uuid the challenge evaluation management's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation management
	* @throws PortalException if a matching challenge evaluation management could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement getChallengeEvaluationManagementByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the challenge evaluation managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluation managements
	* @param end the upper bound of the range of challenge evaluation managements (not inclusive)
	* @return the range of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagements(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationManagements(start, end);
	}

	/**
	* Returns the number of challenge evaluation managements.
	*
	* @return the number of challenge evaluation managements
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengeEvaluationManagementsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationManagementsCount();
	}

	/**
	* Updates the challenge evaluation management in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationManagement the challenge evaluation management
	* @return the challenge evaluation management that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluationManagement updateChallengeEvaluationManagement(
		kisti.edison.challenge.model.ChallengeEvaluationManagement challengeEvaluationManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeEvaluationManagement(challengeEvaluationManagement);
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationManagementes(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long childChallengeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementes(groupId, childChallengeId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluationManagement> getChallengeEvaluationManagementes(
		long groupId, long childChallengeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationManagementes(groupId,
			childChallengeId, start, end);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement deleteChallengeEvalutionManagement(
		long challengeEvaluationManagementId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvalutionManagement(challengeEvaluationManagementId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement addChallengeEvalutionManagement(
		long userId, long childChallengeId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallengeEvalutionManagement(userId, childChallengeId,
			assessmentItem, distribution, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement updateChallengeEvalutionManagement(
		long userId, long childChallengeId,
		long challengeEvaluationManagementId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeEvalutionManagement(userId,
			childChallengeId, challengeEvaluationManagementId, assessmentItem,
			distribution, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluationManagement updateStatus(
		long userId, long challengeEvaluationManagementId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, challengeEvaluationManagementId,
			status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationManagementLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationManagementLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeEvaluationManagementLocalService) {
				_service = (ChallengeEvaluationManagementLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeEvaluationManagementLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationManagementLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationManagementLocalService service) {
	}

	private static ChallengeEvaluationManagementLocalService _service;
}