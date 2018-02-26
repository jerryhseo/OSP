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
	* Deprecated.
	*  Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
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

	/**
	* Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix, boolean deleteOnExit,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getLinkedTemporaryFilePath(portletRequest,
			source, prefix, suffix, deleteOnExit, repoType);
	}

	/**
	* Deprecated.
	*
	* Copy a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
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

	/**
	* Copy a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
	@Override
	public java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getCopiedTemporaryFilePath(portletRequest,
			source, prefix, suffix, repoType);
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

	/**
	* List file information in a folder and return JSONArray.
	*  [
	*      {
	*          name:String [file name],
	*         size: String [file size],
	*         isFile:boolean [ file or not ]
	*      },
	*      ......
	*  ]
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getFolderInformation(portletRequest,
			folderPath, filter, repositoryType);
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

	/**
	* Returns information of a specified file and return JSONObject.
	*  {
	*      name:String [file name],
	*     size: String [file size],
	*     isFile:boolean [ file or not ]
	*  }
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.getFileInformation(portletRequest,
			filePath, repositoryType);
	}

	@Override
	public java.nio.file.Path createFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.createFile(portletRequest, target,
			repositoryType);
	}

	@Override
	public void deleteFile(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.deleteFile(portletRequest, target,
			repositoryType);
	}

	@Override
	public java.lang.String moveFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.moveFile(portletRequest, source,
			target, overwrite, repositoryType);
	}

	@Override
	public java.lang.String copyFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.copyFile(portletRequest, source,
			target, overwrite, repositoryType);
	}

	/**
	* Copy an dlentry to the target file
	*
	* @throws IOException
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public java.lang.String copyDLEntryFile(
		javax.portlet.PortletRequest portletRequest, long srcDLEntryId,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.copyDLEntryFile(portletRequest,
			srcDLEntryId, target, overwrite, repositoryType);
	}

	/**
	* Change owner of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public void changeFileOwner(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_fileManagementLocalService.changeFileOwner(portletRequest, target,
			owner, repositoryType);
	}

	/**
	* Change mode of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@Override
	public void changeFileMode(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_fileManagementLocalService.changeFileMode(portletRequest, target,
			mode, repositoryType);
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

	/**
	* Check a file is exist or not.
	*
	* @param portletRequest
	* @param portletResponse
	* @param filePath
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.duplicated(portletRequest, portletResponse,
			filePath, repositoryType);
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

	/**
	* Upload a file from session request as the target.
	*
	* @param portletRequest
	* @param target
	* @param uploadFileName
	* @param repositoryType
	* @throws SystemException
	* @throws PortalException
	* @throws IOException
	*/
	@Override
	public void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.upload(portletRequest, target,
			uploadFileName, repositoryType);
	}

	@Override
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.download(portletRequest, portletResponse,
			dlFileEntryId);
	}

	/**
	* Download a DLEntry file.
	*
	* @param portletRequest
	* @param portletResponse
	* @param dlFileEntryId
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void downloadDLEntry(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.downloadDLEntry(portletRequest,
			portletResponse, dlFileEntryId);
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

	/**
	* Download a file or files.
	* If count of the files is larger than 1, the files zipped as an file.
	*/
	@Override
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.download(portletRequest, portletResponse,
			targetFolder, files, repositoryType);
	}

	@Override
	public void downloadFromText(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String fileName, java.lang.String fileContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.downloadFromText(portletRequest,
			portletResponse, fileName, fileContext);
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

	/**
	* Check a file is regular file or not.
	*
	* @param portletRequest
	* @param portletResponse
	* @param target
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void checkValidFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.checkValidFile(portletRequest,
			portletResponse, target, repositoryType);
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

	/**
	* Read a file content as a character stream.
	*
	* @param portletRequest
	* @param portletResponse
	* @param target
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFileContent(portletRequest,
			portletResponse, target, repositoryType);
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

	/**
	* Download a file.
	*
	* @param portletRequest
	* @param portletResponse
	* @param target
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void downloadFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.downloadFile(portletRequest,
			portletResponse, target, repositoryType);
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

	/**
	* Read a file content with the specified content type.
	*
	* @param portletRequest
	* @param portletResponse
	* @param contentType
	* @param target
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFileContent(portletRequest,
			portletResponse, contentType, target, repositoryType);
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

	/**
	* Read file content or the first file in a folder.
	*
	* @param portletRequest
	* @param portletResponse
	* @param target
	* @param filter
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void readFirstFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.readFirstFileContent(portletRequest,
			portletResponse, target, filter, repositoryType);
	}

	@Override
	public byte[] readFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.readFileContent(portletRequest,
			filePath, isJobResult);
	}

	/**
	* Read a file content
	*
	* @param portletRequest
	* @param target
	* @param repositoryType
	* @return byte[] file content
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public byte[] readFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.readFileContent(portletRequest,
			target, repositoryType);
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

	/**
	* Gets the file name of a path or the first file name of a folder.
	*
	* @param portletRequest
	* @param portletResponse
	* @param target
	* @param filter
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void getFirstFileName(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.getFirstFileName(portletRequest,
			portletResponse, target, filter, repositoryType);
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

	/**
	* Save the specified content to a file.
	*
	* @param portletRequest
	* @param target
	* @param content
	* @param repositoryType
	* @throws PortalException
	* @throws SystemException
	* @throws IOException
	*/
	@Override
	public void saveFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String content,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		_fileManagementLocalService.saveFileContent(portletRequest, target,
			content, repositoryType);
	}

	@Override
	public void writeTextFile(java.lang.String content,
		java.nio.file.Path target) throws java.io.IOException {
		_fileManagementLocalService.writeTextFile(content, target);
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

	@Override
	public com.liferay.portal.kernel.json.JSONObject readFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return _fileManagementLocalService.readFile(portletRequest, target,
			startPosition, size, repositoryType);
	}

	@Override
	public java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException {
		return _fileManagementLocalService.readTextFile(path);
	}

	@Override
	public java.lang.String getAbolutePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fileManagementLocalService.getAbolutePath(portletRequest, path,
			isJobResult);
	}

	@Override
	public java.lang.String getJobResultPath(java.lang.String simulationUuid,
		java.lang.String jobUuid, java.lang.String path) {
		return _fileManagementLocalService.getJobResultPath(simulationUuid,
			jobUuid, path);
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