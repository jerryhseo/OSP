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

package org.kisti.edison.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for RequestMember. This utility wraps
 * {@link org.kisti.edison.service.impl.RequestMemberLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author edison
 * @see RequestMemberLocalService
 * @see org.kisti.edison.service.base.RequestMemberLocalServiceBaseImpl
 * @see org.kisti.edison.service.impl.RequestMemberLocalServiceImpl
 * @generated
 */
public class RequestMemberLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.kisti.edison.service.impl.RequestMemberLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the request member to the database. Also notifies the appropriate model listeners.
	*
	* @param requestMember the request member
	* @return the request member that was added
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember addRequestMember(
		org.kisti.edison.model.RequestMember requestMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addRequestMember(requestMember);
	}

	/**
	* Creates a new request member with the primary key. Does not add the request member to the database.
	*
	* @param requestMemberPK the primary key for the new request member
	* @return the new request member
	*/
	public static org.kisti.edison.model.RequestMember createRequestMember(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK) {
		return getService().createRequestMember(requestMemberPK);
	}

	/**
	* Deletes the request member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member that was removed
	* @throws PortalException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember deleteRequestMember(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteRequestMember(requestMemberPK);
	}

	/**
	* Deletes the request member from the database. Also notifies the appropriate model listeners.
	*
	* @param requestMember the request member
	* @return the request member that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember deleteRequestMember(
		org.kisti.edison.model.RequestMember requestMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteRequestMember(requestMember);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.kisti.edison.model.RequestMember fetchRequestMember(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchRequestMember(requestMemberPK);
	}

	/**
	* Returns the request member with the primary key.
	*
	* @param requestMemberPK the primary key of the request member
	* @return the request member
	* @throws PortalException if a request member with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember getRequestMember(
		org.kisti.edison.service.persistence.RequestMemberPK requestMemberPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRequestMember(requestMemberPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the request members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.model.impl.RequestMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of request members
	* @param end the upper bound of the range of request members (not inclusive)
	* @return the range of request members
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<org.kisti.edison.model.RequestMember> getRequestMembers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRequestMembers(start, end);
	}

	/**
	* Returns the number of request members.
	*
	* @return the number of request members
	* @throws SystemException if a system exception occurred
	*/
	public static int getRequestMembersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRequestMembersCount();
	}

	/**
	* Updates the request member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param requestMember the request member
	* @return the request member that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static org.kisti.edison.model.RequestMember updateRequestMember(
		org.kisti.edison.model.RequestMember requestMember)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateRequestMember(requestMember);
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

	/**
	* 시뮬레이션 프로젝트 멤버 가입 신청
	*
	* @param user - 신청 유저 정보
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @param requestState - 신청 상태
	* @return RequestMember - 생성한 신청정보
	* @throws SystemException
	*/
	public static org.kisti.edison.model.RequestMember insertCustomSimulationProjectMemberRequest(
		com.liferay.portal.model.User user, long simulationProjectId,
		java.lang.String requestState)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .insertCustomSimulationProjectMemberRequest(user,
			simulationProjectId, requestState);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 신청 정보 수정
	*
	* @param requestSeq - 신청 seq 번호
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @param requestState - 신청 상태
	* @return RequestMember - 수정한 신청정보
	* @throws SystemException
	*/
	public static org.kisti.edison.model.RequestMember updateCustomSimulationProjectMemberRequest(
		long requestSeq, long simulationProjectId, long userId,
		java.lang.String requestState)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCustomSimulationProjectMemberRequest(requestSeq,
			simulationProjectId, userId, requestState);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 신청 정보 삭제
	*
	* @param requestSeq - 신청 seq
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @throws SystemException
	*/
	public static void deleteCustomSimulationProjectMemberRequest(
		long requestSeq, long simulationProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteCustomSimulationProjectMemberRequest(requestSeq,
			simulationProjectId);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 신청 정보 리스트
	*
	* @param begin
	* @param end
	* @param searchText - 검색어
	* @param searchRequestState - 절차
	* @return List<Map<String, Object>> - 시뮬레이션 프로젝트 멤버 신청 정보 리스트
	* @throws SystemException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCustomRequestMemberList(
		long simulationProjectId, int begin, int end,
		java.lang.String searchText, java.lang.String searchRequestState,
		java.util.Locale locale, long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCustomRequestMemberList(simulationProjectId, begin, end,
			searchText, searchRequestState, locale, columnId);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 신청 정보 카운트
	*
	* @param searchText - 검색어
	* @param searchRequestState - 절차
	* @return int - 시뮬레이션 프로젝트 멤버 신청 정보 카운트
	* @throws SystemException
	*/
	public static int getCustomRequestMemberCount(long simulationProjectId,
		java.lang.String searchText, java.lang.String searchRequestState,
		long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCustomRequestMemberCount(simulationProjectId,
			searchText, searchRequestState, columnId);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 체크
	*
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @param userId - 유저 아이디
	* @param portalGroupId - 포탈 그룹 아이디
	* @return 프로젝트 소속 여부
	* @throws SystemException
	*/
	public static boolean isSimulationProjectMember(long simulationProjectId,
		long userId, long portalGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .isSimulationProjectMember(simulationProjectId, userId,
			portalGroupId);
	}

	/**
	* 시뮬레이션 프로젝트 멤버 신청중인지 체크
	*
	* @param simulationProjectId - 시뮬레이션 프로젝트 아이디
	* @param userId - 유저 아이디
	* @return 프로젝트 멤버 신청 여부
	* @throws SystemException
	*/
	public static boolean isSimulationProjectMemberRequest(
		long simulationProjectId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .isSimulationProjectMemberRequest(simulationProjectId, userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static RequestMemberLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					RequestMemberLocalService.class.getName());

			if (invokableLocalService instanceof RequestMemberLocalService) {
				_service = (RequestMemberLocalService)invokableLocalService;
			}
			else {
				_service = new RequestMemberLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(RequestMemberLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(RequestMemberLocalService service) {
	}

	private static RequestMemberLocalService _service;
}