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

package com.kisti.osp.icecap.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DataTypeEditor. This utility wraps
 * {@link com.kisti.osp.icecap.service.impl.DataTypeEditorLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Young-K. Suh and Jerry H. Seo
 * @see DataTypeEditorLocalService
 * @see com.kisti.osp.icecap.service.base.DataTypeEditorLocalServiceBaseImpl
 * @see com.kisti.osp.icecap.service.impl.DataTypeEditorLocalServiceImpl
 * @generated
 */
public class DataTypeEditorLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.kisti.osp.icecap.service.impl.DataTypeEditorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the data type editor to the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditor the data type editor
	* @return the data type editor that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor addDataTypeEditor(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDataTypeEditor(dataTypeEditor);
	}

	/**
	* Creates a new data type editor with the primary key. Does not add the data type editor to the database.
	*
	* @param dataTypeEditorPK the primary key for the new data type editor
	* @return the new data type editor
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor createDataTypeEditor(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK) {
		return getService().createDataTypeEditor(dataTypeEditorPK);
	}

	/**
	* Deletes the data type editor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor that was removed
	* @throws PortalException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor deleteDataTypeEditor(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDataTypeEditor(dataTypeEditorPK);
	}

	/**
	* Deletes the data type editor from the database. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditor the data type editor
	* @return the data type editor that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor deleteDataTypeEditor(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDataTypeEditor(dataTypeEditor);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.kisti.osp.icecap.model.DataTypeEditor fetchDataTypeEditor(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDataTypeEditor(dataTypeEditorPK);
	}

	/**
	* Returns the data type editor with the primary key.
	*
	* @param dataTypeEditorPK the primary key of the data type editor
	* @return the data type editor
	* @throws PortalException if a data type editor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor getDataTypeEditor(
		com.kisti.osp.icecap.service.persistence.DataTypeEditorPK dataTypeEditorPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeEditor(dataTypeEditorPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the data type editors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.icecap.model.impl.DataTypeEditorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data type editors
	* @param end the upper bound of the range of data type editors (not inclusive)
	* @return the range of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.icecap.model.DataTypeEditor> getDataTypeEditors(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeEditors(start, end);
	}

	/**
	* Returns the number of data type editors.
	*
	* @return the number of data type editors
	* @throws SystemException if a system exception occurred
	*/
	public static int getDataTypeEditorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataTypeEditorsCount();
	}

	/**
	* Updates the data type editor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataTypeEditor the data type editor
	* @return the data type editor that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.icecap.model.DataTypeEditor updateDataTypeEditor(
		com.kisti.osp.icecap.model.DataTypeEditor dataTypeEditor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDataTypeEditor(dataTypeEditor);
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

	public static boolean createDataTypeEditorObject(
		java.lang.Long dataTypeId, boolean isDefault,
		java.lang.Long dataTypeEditorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createDataTypeEditorObject(dataTypeId, isDefault,
			dataTypeEditorId);
	}

	/**
	* Retrieve a matching DataTypeEditor with a given typeId and a given servicePK
	*
	* @param typeId a given DataType typeId
	* @param servicePK a given editorId
	* @return Map<String, Object> object representing a matching DataTypeEditor object
	* @throws SystemException
	*/
	public static java.util.Map<java.lang.String, java.lang.Object> retrieveDataTypeEditorPK(
		long typeId, long servicePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().retrieveDataTypeEditorPK(typeId, servicePK);
	}

	/**
	* Retrieve a list of DataTypeEditor objects
	*
	* @param typeId typeid
	* @return a list of DataTypeEditor objects
	* @throws SystemException
	*/
	public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> retrieveDataTypeEditorList(
		long typeId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().retrieveDataTypeEditorList(typeId);
	}

	/**
	* Remove a DataTypeEditor object's PK associated with a given typeId and a given servicePK
	*
	* @param typeId typeId
	* @param servicePK editorId
	* @throws SystemException
	* @throws NoSuchDataTypeEditorException
	*/
	public static void removeDataTypeEditorByPK(long typeId, long servicePK)
		throws com.kisti.osp.icecap.NoSuchDataTypeEditorException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().removeDataTypeEditorByPK(typeId, servicePK);
	}

	/**
	* Remove DataTypeEditor by typeId
	*
	* @param typeId typeId
	* @throws SystemException
	* @throws NoSuchDataTypeEditorException
	*/
	public static void removeDataTypeEditorByTypeId(long typeId)
		throws com.kisti.osp.icecap.NoSuchDataTypeAnalyzerException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().removeDataTypeEditorByTypeId(typeId);
	}

	public static void clearService() {
		_service = null;
	}

	public static DataTypeEditorLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DataTypeEditorLocalService.class.getName());

			if (invokableLocalService instanceof DataTypeEditorLocalService) {
				_service = (DataTypeEditorLocalService)invokableLocalService;
			}
			else {
				_service = new DataTypeEditorLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DataTypeEditorLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(DataTypeEditorLocalService service) {
	}

	private static DataTypeEditorLocalService _service;
}