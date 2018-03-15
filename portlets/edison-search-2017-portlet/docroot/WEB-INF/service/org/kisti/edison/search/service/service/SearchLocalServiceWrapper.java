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

package org.kisti.edison.search.service.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SearchLocalService}.
 *
 * @author yjpark
 * @see SearchLocalService
 * @generated
 */
public class SearchLocalServiceWrapper implements SearchLocalService,
	ServiceWrapper<SearchLocalService> {
	public SearchLocalServiceWrapper(SearchLocalService searchLocalService) {
		_searchLocalService = searchLocalService;
	}

	/**
	* Adds the search to the database. Also notifies the appropriate model listeners.
	*
	* @param search the search
	* @return the search that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.search.service.model.Search addSearch(
		org.kisti.edison.search.service.model.Search search)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.addSearch(search);
	}

	/**
	* Creates a new search with the primary key. Does not add the search to the database.
	*
	* @param id the primary key for the new search
	* @return the new search
	*/
	@Override
	public org.kisti.edison.search.service.model.Search createSearch(long id) {
		return _searchLocalService.createSearch(id);
	}

	/**
	* Deletes the search with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the search
	* @return the search that was removed
	* @throws PortalException if a search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.search.service.model.Search deleteSearch(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.deleteSearch(id);
	}

	/**
	* Deletes the search from the database. Also notifies the appropriate model listeners.
	*
	* @param search the search
	* @return the search that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.search.service.model.Search deleteSearch(
		org.kisti.edison.search.service.model.Search search)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.deleteSearch(search);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _searchLocalService.dynamicQuery();
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
		return _searchLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _searchLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _searchLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _searchLocalService.dynamicQueryCount(dynamicQuery);
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
		return _searchLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.kisti.edison.search.service.model.Search fetchSearch(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.fetchSearch(id);
	}

	/**
	* Returns the search with the primary key.
	*
	* @param id the primary key of the search
	* @return the search
	* @throws PortalException if a search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.search.service.model.Search getSearch(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getSearch(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the searchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.kisti.edison.search.service.model.impl.SearchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of searchs
	* @param end the upper bound of the range of searchs (not inclusive)
	* @return the range of searchs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<org.kisti.edison.search.service.model.Search> getSearchs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getSearchs(start, end);
	}

	/**
	* Returns the number of searchs.
	*
	* @return the number of searchs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSearchsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getSearchsCount();
	}

	/**
	* Updates the search in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param search the search
	* @return the search that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public org.kisti.edison.search.service.model.Search updateSearch(
		org.kisti.edison.search.service.model.Search search)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.updateSearch(search);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _searchLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_searchLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _searchLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getRootSiteAssetCategries(
		long companyGroupId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getRootSiteAssetCategries(companyGroupId,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getSiteAssetCategoriesByParentId(
		long companyGroupId, long groupId, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getSiteAssetCategoriesByParentId(companyGroupId,
			groupId, parentCategoryId);
	}

	/**
	* 占싼딆뵠占쏙옙�브쑴鍮�燁삳똾�믤�醫듼봺 鈺곌퀬��     */
	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> getLv1Categories(
		long companyGroupId, long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getLv1Categories(companyGroupId, groupId,
			locale);
	}

	/**
	* 燁삳똾�믤�醫듼봺 JSTree 占쎄퀣�좑옙占썼�怨좎돳
	*/
	@Override
	public java.lang.String getCategoriesJsonString(long companyGroupId,
		long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getCategoriesJsonString(companyGroupId,
			groupId, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> getCategories(
		long companyGroupId, long groupId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getCategories(companyGroupId, groupId, locale);
	}

	@Override
	public java.util.List<java.util.Map<java.lang.String, java.lang.Object>> assetCategoryToJstreeModel(
		java.util.List<com.liferay.portlet.asset.model.AssetCategory> assetCategories,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.assetCategoryToJstreeModel(assetCategories,
			locale);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> assetCategoryToJstreeModel(
		com.liferay.portlet.asset.model.AssetCategory assetCategory,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.assetCategoryToJstreeModel(assetCategory,
			locale);
	}

	@Override
	public org.kisti.edison.search.service.model.Search appSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.appSearch(searchCondition);
	}

	@Override
	public org.kisti.edison.search.service.model.Search appSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		org.kisti.edison.search.service.model.Search searchResults)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.appSearch(searchCondition, searchResults);
	}

	@Override
	public org.kisti.edison.search.service.model.Search contentSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.contentSearch(searchCondition);
	}

	@Override
	public org.kisti.edison.search.service.model.Search contentSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		org.kisti.edison.search.service.model.Search searchResults)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.contentSearch(searchCondition, searchResults);
	}

	@Override
	public org.kisti.edison.search.service.model.Search projectSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.projectSearch(searchCondition);
	}

	@Override
	public org.kisti.edison.search.service.model.Search projectSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		org.kisti.edison.search.service.model.Search searchResults)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.projectSearch(searchCondition, searchResults);
	}

	@Override
	public org.kisti.edison.search.service.model.Search dataSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.dataSearch(searchCondition);
	}

	@Override
	public org.kisti.edison.search.service.model.Search dataSearch(
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response,
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.dataSearch(request, response, searchCondition);
	}

	@Override
	public org.kisti.edison.search.service.model.Search dataSearch(
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response,
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		org.kisti.edison.search.service.model.Search searchResults)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.dataSearch(request, response,
			searchCondition, searchResults);
	}

	@Override
	public org.kisti.edison.search.service.model.Search dataSearch(
		org.kisti.edison.search.service.model.SearchCondition searchCondition,
		org.kisti.edison.search.service.model.Search searchResults)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.dataSearch(searchCondition, searchResults);
	}

	@Override
	public org.kisti.edison.search.service.model.Search totalSearch(
		javax.portlet.ResourceRequest request,
		javax.portlet.ResourceResponse response,
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws java.lang.Exception {
		return _searchLocalService.totalSearch(request, response,
			searchCondition);
	}

	@Override
	public long[] getCategoryIdArrays(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getCategoryIdArrays(searchCondition);
	}

	@Override
	public java.util.List<java.lang.Long> getCategoryIds(
		org.kisti.edison.search.service.model.SearchCondition searchCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _searchLocalService.getCategoryIds(searchCondition);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SearchLocalService getWrappedSearchLocalService() {
		return _searchLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSearchLocalService(
		SearchLocalService searchLocalService) {
		_searchLocalService = searchLocalService;
	}

	@Override
	public SearchLocalService getWrappedService() {
		return _searchLocalService;
	}

	@Override
	public void setWrappedService(SearchLocalService searchLocalService) {
		_searchLocalService = searchLocalService;
	}

	private SearchLocalService _searchLocalService;
}