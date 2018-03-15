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
 * Provides the local service utility for ChallengeEvaluation. This utility wraps
 * {@link kisti.edison.challenge.service.impl.ChallengeEvaluationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author KYJ
 * @see ChallengeEvaluationLocalService
 * @see kisti.edison.challenge.service.base.ChallengeEvaluationLocalServiceBaseImpl
 * @see kisti.edison.challenge.service.impl.ChallengeEvaluationLocalServiceImpl
 * @generated
 */
public class ChallengeEvaluationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link kisti.edison.challenge.service.impl.ChallengeEvaluationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the challenge evaluation to the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluation the challenge evaluation
	* @return the challenge evaluation that was added
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation addChallengeEvaluation(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addChallengeEvaluation(challengeEvaluation);
	}

	/**
	* Creates a new challenge evaluation with the primary key. Does not add the challenge evaluation to the database.
	*
	* @param challengeEvaluationId the primary key for the new challenge evaluation
	* @return the new challenge evaluation
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation createChallengeEvaluation(
		long challengeEvaluationId) {
		return getService().createChallengeEvaluation(challengeEvaluationId);
	}

	/**
	* Deletes the challenge evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation that was removed
	* @throws PortalException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvaluation(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeEvaluation(challengeEvaluationId);
	}

	/**
	* Deletes the challenge evaluation from the database. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluation the challenge evaluation
	* @return the challenge evaluation that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvaluation(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteChallengeEvaluation(challengeEvaluation);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static kisti.edison.challenge.model.ChallengeEvaluation fetchChallengeEvaluation(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchChallengeEvaluation(challengeEvaluationId);
	}

	/**
	* Returns the challenge evaluation with the matching UUID and company.
	*
	* @param uuid the challenge evaluation's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchChallengeEvaluationByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge evaluation matching the UUID and group.
	*
	* @param uuid the challenge evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation, or <code>null</code> if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation fetchChallengeEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchChallengeEvaluationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the challenge evaluation with the primary key.
	*
	* @param challengeEvaluationId the primary key of the challenge evaluation
	* @return the challenge evaluation
	* @throws PortalException if a challenge evaluation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation getChallengeEvaluation(
		long challengeEvaluationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluation(challengeEvaluationId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the challenge evaluation with the matching UUID and company.
	*
	* @param uuid the challenge evaluation's UUID
	* @param companyId the primary key of the company
	* @return the matching challenge evaluation
	* @throws PortalException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation getChallengeEvaluationByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluationByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the challenge evaluation matching the UUID and group.
	*
	* @param uuid the challenge evaluation's UUID
	* @param groupId the primary key of the group
	* @return the matching challenge evaluation
	* @throws PortalException if a matching challenge evaluation could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation getChallengeEvaluationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the challenge evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link kisti.edison.challenge.model.impl.ChallengeEvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of challenge evaluations
	* @param end the upper bound of the range of challenge evaluations (not inclusive)
	* @return the range of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluations(start, end);
	}

	/**
	* Returns the number of challenge evaluations.
	*
	* @return the number of challenge evaluations
	* @throws SystemException if a system exception occurred
	*/
	public static int getChallengeEvaluationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluationsCount();
	}

	/**
	* Updates the challenge evaluation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param challengeEvaluation the challenge evaluation
	* @return the challenge evaluation that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static kisti.edison.challenge.model.ChallengeEvaluation updateChallengeEvaluation(
		kisti.edison.challenge.model.ChallengeEvaluation challengeEvaluation)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateChallengeEvaluation(challengeEvaluation);
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

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluations(groupId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChallengeEvaluations(groupId, challengeTeamId);
	}

	public static java.util.List<kisti.edison.challenge.model.ChallengeEvaluation> getChallengeEvaluations(
		long groupId, long challengeTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getChallengeEvaluations(groupId, challengeTeamId, start, end);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation deleteChallengeEvalution(
		long challengeEvaluationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteChallengeEvalution(challengeEvaluationId,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation addChallengeEvalution(
		long userId, long challengeTeamId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addChallengeEvalution(userId, challengeTeamId,
			assessmentItem, distribution, score, serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation updateChallengeEvalution(
		long userId, long challengeTeamId, long challengeEvaluationId,
		java.util.Map<java.util.Locale, java.lang.String> assessmentItem,
		double distribution, double score,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateChallengeEvalution(userId, challengeTeamId,
			challengeEvaluationId, assessmentItem, distribution, score,
			serviceContext);
	}

	public static kisti.edison.challenge.model.ChallengeEvaluation updateStatus(
		long userId, long challengeEvaluationId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, challengeEvaluationId, status,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ChallengeEvaluationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ChallengeEvaluationLocalService.class.getName());

			if (invokableLocalService instanceof ChallengeEvaluationLocalService) {
				_service = (ChallengeEvaluationLocalService)invokableLocalService;
			}
			else {
				_service = new ChallengeEvaluationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ChallengeEvaluationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ChallengeEvaluationLocalService service) {
	}

	private static ChallengeEvaluationLocalService _service;
}