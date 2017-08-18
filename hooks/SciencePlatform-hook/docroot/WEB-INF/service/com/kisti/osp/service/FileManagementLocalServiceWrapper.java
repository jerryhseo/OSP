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

package com.kisti.osp.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FileManagementLocalService}.
 *
 * @author Jerry h. Seo
 * @see FileManagementLocalService
 * @generated
 */
public class FileManagementLocalServiceWrapper
	implements FileManagementLocalService,
		ServiceWrapper<FileManagementLocalService> {
	public FileManagementLocalServiceWrapper(
		FileManagementLocalService fileManagementLocalService) {
		_fileManagementLocalService = fileManagementLocalService;
	}

	/**
	* Adds the file management to the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.model.FileManagement addFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.addFileManagement(fileManagement);
	}

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param userId the primary key for the new file management
	* @return the new file management
	*/
	@Override
	public com.kisti.osp.model.FileManagement createFileManagement(long userId) {
		return _fileManagementLocalService.createFileManagement(userId);
	}

	/**
	* Deletes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the file management
	* @return the file management that was removed
	* @throws PortalException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.model.FileManagement deleteFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.deleteFileManagement(userId);
	}

	/**
	* Deletes the file management from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.model.FileManagement deleteFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.deleteFileManagement(fileManagement);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fileManagementLocalService.dynamicQuery();
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery, start,
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
		return _fileManagementLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fileManagementLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.kisti.osp.model.FileManagement fetchFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.fetchFileManagement(userId);
	}

	/**
	* Returns the file management with the primary key.
	*
	* @param userId the primary key of the file management
	* @return the file management
	* @throws PortalException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.model.FileManagement getFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.getFileManagement(userId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @return the range of file managements
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.kisti.osp.model.FileManagement> getFileManagements(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.getFileManagements(start, end);
	}

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getFileManagementsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.getFileManagementsCount();
	}

	/**
	* Updates the file management in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.kisti.osp.model.FileManagement updateFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.updateFileManagement(fileManagement);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _fileManagementLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_fileManagementLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _fileManagementLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Public APIs Section
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean deleteOnExit,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getLinkedTemporaryFilePath(portletRequest,
			target, prefix, suffix, deleteOnExit, isJobResult);
	}

	@Override
	public java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getCopiedTemporaryFilePath(portletRequest,
			target, prefix, suffix, isJobResult);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getFolderInformation(portletRequest,
			folderPath, filter, isJobResult);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getFileInformation(portletRequest,
			filePath, isJobResult);
	}

	@Override
	public java.nio.file.Path createFile(java.lang.String target)
		throws java.io.IOException {
		return _fileManagementLocalService.createFile(target);
	}

	@Override
	public java.nio.file.Path createFile(java.lang.String target,
		boolean overwrite) throws java.io.IOException {
		return _fileManagementLocalService.createFile(target, overwrite);
	}

	@Override
	public void deleteFile(java.lang.String fileName)
		throws java.io.IOException {
		_fileManagementLocalService.deleteFile(fileName);
	}

	@Override
	public java.nio.file.Path moveFile(java.lang.String srcPath,
		java.lang.String targetPath) throws java.io.IOException {
		return _fileManagementLocalService.moveFile(srcPath, targetPath);
	}

	@Override
	public java.nio.file.Path moveFile(java.lang.String srcPath,
		java.lang.String targetPath, boolean overwrite)
		throws java.io.IOException {
		return _fileManagementLocalService.moveFile(srcPath, targetPath,
			overwrite);
	}

	@Override
	public java.nio.file.Path copyFile(java.lang.String srcPath,
		java.lang.String targetPath) throws java.io.IOException {
		return _fileManagementLocalService.copyFile(srcPath, targetPath);
	}

	@Override
	public java.nio.file.Path copyFile(java.lang.String source,
		java.lang.String target, boolean overwrite) throws java.io.IOException {
		return _fileManagementLocalService.copyFile(source, target, overwrite);
	}

	@Override
	public void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.duplicated(portletRequest, portletResponse,
			filePath, isJobResult);
	}

	@Override
	public void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.upload(portletRequest, target,
			uploadFileName, isJobResult);
	}

	@Override
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.download(portletRequest, portletResponse,
			targetFolder, files, isJobResult);
	}

	@Override
	public void checkValidFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.checkValidFile(portletRequest,
			portletResponse, filePath, isJobResult);
	}

	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFileContent(portletRequest,
			portletResponse, filePath, isJobResult);
	}

	@Override
	public void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.getFile(portletRequest, portletResponse,
			filePath, isJobResult);
	}

	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFileContent(portletRequest,
			portletResponse, contentType, filePath, isJobResult);
	}

	@Override
	public void readFirstFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String parentPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFirstFileContent(portletRequest,
			portletResponse, parentPath, filter, isJobResult);
	}

	@Override
	public void getFirstFileName(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String parentPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.getFirstFileName(portletRequest,
			portletResponse, parentPath, filter, isJobResult);
	}

	@Override
	public void saveFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String filePath, java.lang.String content, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.saveFileContent(portletRequest, filePath,
			content, isJobResult);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject saveInputFile(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String scienceAppName, java.lang.String scienceAppVersion,
		java.lang.String simulationTime, java.lang.String jobNumber,
		java.lang.String fileName, java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.saveInputFile(portletRequest,
			scienceAppName, scienceAppVersion, simulationTime, jobNumber,
			fileName, content);
	}

	@Override
	public void readDLAppEntry(javax.portlet.PortletResponse portletResponse,
		long dlEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readDLAppEntry(portletResponse, dlEntryId);
	}

	@Override
	public void writeToClient(javax.portlet.PortletResponse portletResponse,
		java.lang.String errorMessage,
		com.liferay.portal.kernel.json.JSONObject data)
		throws java.io.IOException {
		_fileManagementLocalService.writeToClient(portletResponse,
			errorMessage, data);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> readOutLogFile(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		long lastPosition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.readOutLogFile(portletRequest,
			simulationUuid, jobUuid, lastPosition);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public FileManagementLocalService getWrappedFileManagementLocalService() {
		return _fileManagementLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedFileManagementLocalService(
		FileManagementLocalService fileManagementLocalService) {
		_fileManagementLocalService = fileManagementLocalService;
	}

	@Override
	public FileManagementLocalService getWrappedService() {
		return _fileManagementLocalService;
	}

	@Override
	public void setWrappedService(
		FileManagementLocalService fileManagementLocalService) {
		_fileManagementLocalService = fileManagementLocalService;
	}

	private FileManagementLocalService _fileManagementLocalService;
}