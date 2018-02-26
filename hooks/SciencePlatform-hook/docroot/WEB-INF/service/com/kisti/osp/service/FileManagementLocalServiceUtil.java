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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for FileManagement. This utility wraps
 * {@link com.kisti.osp.service.impl.FileManagementLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jerry h. Seo
 * @see FileManagementLocalService
 * @see com.kisti.osp.service.base.FileManagementLocalServiceBaseImpl
 * @see com.kisti.osp.service.impl.FileManagementLocalServiceImpl
 * @generated
 */
public class FileManagementLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.kisti.osp.service.impl.FileManagementLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the file management to the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.FileManagement addFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFileManagement(fileManagement);
	}

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param userId the primary key for the new file management
	* @return the new file management
	*/
	public static com.kisti.osp.model.FileManagement createFileManagement(
		long userId) {
		return getService().createFileManagement(userId);
	}

	/**
	* Deletes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the file management
	* @return the file management that was removed
	* @throws PortalException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.FileManagement deleteFileManagement(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFileManagement(userId);
	}

	/**
	* Deletes the file management from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.FileManagement deleteFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFileManagement(fileManagement);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.kisti.osp.model.FileManagement fetchFileManagement(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFileManagement(userId);
	}

	/**
	* Returns the file management with the primary key.
	*
	* @param userId the primary key of the file management
	* @return the file management
	* @throws PortalException if a file management with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.FileManagement getFileManagement(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFileManagement(userId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.kisti.osp.model.FileManagement> getFileManagements(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFileManagements(start, end);
	}

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	* @throws SystemException if a system exception occurred
	*/
	public static int getFileManagementsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFileManagementsCount();
	}

	/**
	* Updates the file management in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.FileManagement updateFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFileManagement(fileManagement);
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
	* Deprecated.
	*  Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
	public static java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean deleteOnExit,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getLinkedTemporaryFilePath(portletRequest, target, prefix,
			suffix, deleteOnExit, isJobResult);
	}

	/**
	* Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*
	* @throws SystemException
	* @throws PortalException
	*/
	public static java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix, boolean deleteOnExit,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getLinkedTemporaryFilePath(portletRequest, source, prefix,
			suffix, deleteOnExit, repoType);
	}

	/**
	* Deprecated.
	*
	* Copy a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
	public static java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getCopiedTemporaryFilePath(portletRequest, target, prefix,
			suffix, isJobResult);
	}

	/**
	* Copy a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
	public static java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getCopiedTemporaryFilePath(portletRequest, source, prefix,
			suffix, repoType);
	}

	public static com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFolderInformation(portletRequest, folderPath, filter,
			isJobResult);
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
	public static com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFolderInformation(portletRequest, folderPath, filter,
			repositoryType);
	}

	public static com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFileInformation(portletRequest, filePath, isJobResult);
	}

	/**
	* Returns information of a specified file and return JSONObject.
	*  {
	*      name:String [file name],
	*     size: String [file size],
	*     isFile:boolean [ file or not ]
	*  }
	*/
	public static com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFileInformation(portletRequest, filePath, repositoryType);
	}

	public static java.nio.file.Path createFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().createFile(portletRequest, target, repositoryType);
	}

	public static void deleteFile(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().deleteFile(portletRequest, target, repositoryType);
	}

	public static java.lang.String moveFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .moveFile(portletRequest, source, target, overwrite,
			repositoryType);
	}

	public static java.lang.String copyFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .copyFile(portletRequest, source, target, overwrite,
			repositoryType);
	}

	/**
	* Copy an dlentry to the target file
	*
	* @throws IOException
	* @throws SystemException
	* @throws PortalException
	*/
	public static java.lang.String copyDLEntryFile(
		javax.portlet.PortletRequest portletRequest, long srcDLEntryId,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .copyDLEntryFile(portletRequest, srcDLEntryId, target,
			overwrite, repositoryType);
	}

	/**
	* Change owner of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	public static void changeFileOwner(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String owner, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.changeFileOwner(portletRequest, target, owner, repositoryType);
	}

	/**
	* Change mode of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	public static void changeFileMode(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String mode, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().changeFileMode(portletRequest, target, mode, repositoryType);
	}

	public static void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.duplicated(portletRequest, portletResponse, filePath, isJobResult);
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
	public static void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.duplicated(portletRequest, portletResponse, filePath,
			repositoryType);
	}

	public static void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().upload(portletRequest, target, uploadFileName, isJobResult);
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
	public static void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.upload(portletRequest, target, uploadFileName, repositoryType);
	}

	public static void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().download(portletRequest, portletResponse, dlFileEntryId);
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
	public static void downloadDLEntry(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.downloadDLEntry(portletRequest, portletResponse, dlFileEntryId);
	}

	public static void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.download(portletRequest, portletResponse, targetFolder, files,
			isJobResult);
	}

	/**
	* Download a file or files.
	* If count of the files is larger than 1, the files zipped as an file.
	*/
	public static void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.download(portletRequest, portletResponse, targetFolder, files,
			repositoryType);
	}

	public static void downloadFromText(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String fileName, java.lang.String fileContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.downloadFromText(portletRequest, portletResponse, fileName,
			fileContext);
	}

	public static void checkValidFile(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.checkValidFile(portletRequest, portletResponse, filePath,
			isJobResult);
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
	public static void checkValidFile(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.checkValidFile(portletRequest, portletResponse, target,
			repositoryType);
	}

	public static void readFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletRequest, portletResponse, filePath,
			isJobResult);
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
	public static void readFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletRequest, portletResponse, target,
			repositoryType);
	}

	public static void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFile(portletRequest, portletResponse, filePath, isJobResult);
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
	public static void downloadFile(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.downloadFile(portletRequest, portletResponse, target,
			repositoryType);
	}

	public static void readFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletRequest, portletResponse, contentType,
			filePath, isJobResult);
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
	public static void readFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletRequest, portletResponse, contentType,
			target, repositoryType);
	}

	public static void readFirstFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String parentPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFirstFileContent(portletRequest, portletResponse, parentPath,
			filter, isJobResult);
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
	public static void readFirstFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFirstFileContent(portletRequest, portletResponse, target,
			filter, repositoryType);
	}

	public static byte[] readFileContent(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readFileContent(portletRequest, filePath, isJobResult);
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
	public static byte[] readFileContent(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readFileContent(portletRequest, target, repositoryType);
	}

	public static void getFirstFileName(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String parentPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFirstFileName(portletRequest, portletResponse, parentPath,
			filter, isJobResult);
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
	public static void getFirstFileName(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFirstFileName(portletRequest, portletResponse, target, filter,
			repositoryType);
	}

	public static void saveFileContent(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		java.lang.String content, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.saveFileContent(portletRequest, filePath, content, isJobResult);
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
	public static void saveFileContent(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String content, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.saveFileContent(portletRequest, target, content, repositoryType);
	}

	public static void writeTextFile(java.lang.String content,
		java.nio.file.Path target) throws java.io.IOException {
		getService().writeTextFile(content, target);
	}

	public static void readDLAppEntry(
		javax.portlet.PortletResponse portletResponse, long dlEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().readDLAppEntry(portletResponse, dlEntryId);
	}

	public static void writeToClient(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String errorMessage,
		com.liferay.portal.kernel.json.JSONObject data)
		throws java.io.IOException {
		getService().writeToClient(portletResponse, errorMessage, data);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> readOutLogFile(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		long lastPosition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readOutLogFile(portletRequest, simulationUuid, jobUuid,
			lastPosition);
	}

	public static com.liferay.portal.kernel.json.JSONObject readFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readFile(portletRequest, target, startPosition, size,
			repositoryType);
	}

	public static java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException {
		return getService().readTextFile(path);
	}

	public static java.lang.String getAbolutePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAbolutePath(portletRequest, path, isJobResult);
	}

	public static java.lang.String getJobResultPath(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		java.lang.String path) {
		return getService().getJobResultPath(simulationUuid, jobUuid, path);
	}

	public static void clearService() {
		_service = null;
	}

	public static FileManagementLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					FileManagementLocalService.class.getName());

			if (invokableLocalService instanceof FileManagementLocalService) {
				_service = (FileManagementLocalService)invokableLocalService;
			}
			else {
				_service = new FileManagementLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(FileManagementLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(FileManagementLocalService service) {
	}

	private static FileManagementLocalService _service;
}