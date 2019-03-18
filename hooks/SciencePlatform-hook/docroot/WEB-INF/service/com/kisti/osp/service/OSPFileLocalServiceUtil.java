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
 * Provides the local service utility for OSPFile. This utility wraps
 * {@link com.kisti.osp.service.impl.OSPFileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jerry h. Seo
 * @see OSPFileLocalService
 * @see com.kisti.osp.service.base.OSPFileLocalServiceBaseImpl
 * @see com.kisti.osp.service.impl.OSPFileLocalServiceImpl
 * @generated
 */
public class OSPFileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.kisti.osp.service.impl.OSPFileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the o s p file to the database. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.OSPFile addOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOSPFile(ospFile);
	}

	/**
	* Creates a new o s p file with the primary key. Does not add the o s p file to the database.
	*
	* @param propertyName the primary key for the new o s p file
	* @return the new o s p file
	*/
	public static com.kisti.osp.model.OSPFile createOSPFile(
		java.lang.String propertyName) {
		return getService().createOSPFile(propertyName);
	}

	/**
	* Deletes the o s p file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file that was removed
	* @throws PortalException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.OSPFile deleteOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOSPFile(propertyName);
	}

	/**
	* Deletes the o s p file from the database. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.OSPFile deleteOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOSPFile(ospFile);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.kisti.osp.model.OSPFile fetchOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOSPFile(propertyName);
	}

	/**
	* Returns the o s p file with the primary key.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file
	* @throws PortalException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.OSPFile getOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOSPFile(propertyName);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the o s p files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.osp.model.impl.OSPFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o s p files
	* @param end the upper bound of the range of o s p files (not inclusive)
	* @return the range of o s p files
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.osp.model.OSPFile> getOSPFiles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOSPFiles(start, end);
	}

	/**
	* Returns the number of o s p files.
	*
	* @return the number of o s p files
	* @throws SystemException if a system exception occurred
	*/
	public static int getOSPFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOSPFilesCount();
	}

	/**
	* Updates the o s p file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.osp.model.OSPFile updateOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOSPFile(ospFile);
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
	* Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*
	* @throws SystemException
	* @throws PortalException
	*/
	public static java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getLinkedTemporaryFilePath(portletRequest, source, prefix,
			suffix, repoType);
	}

	public static void getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getLinkedTemporaryFilePath(portletRequest, portletResponse,
			srcScreenName, parent, fileName, repoType);
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

	public static void getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getCopiedTemporaryFilePath(portletRequest, portletResponse,
			srcScreenName, parent, fileName, repoType);
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

	public static com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		java.lang.String screenName, java.lang.String folderPath,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFolderInformation(screenName, folderPath, filter,
			repositoryType);
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

	public static com.liferay.portal.kernel.json.JSONObject getFileInformation(
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .getFileInformation(screenName, filePath, repositoryType);
	}

	public static java.nio.file.Path createFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().createFile(portletRequest, target, repositoryType);
	}

	public static java.nio.file.Path createFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().createFile(screenName, target, repositoryType);
	}

	public static void deleteFile(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().deleteFile(portletRequest, target, repositoryType);
	}

	public static void deleteFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().deleteFile(screenName, target, repositoryType);
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

	public static java.lang.String moveFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .moveFile(srcScreenName, source, srcRepositoryType,
			targetScreenName, target, targetRepositoryType, overwrite);
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

	public static java.lang.String copyFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .copyFile(srcScreenName, source, srcRepositoryType,
			targetScreenName, target, targetRepositoryType, overwrite);
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

	public static java.lang.String copyDLEntryFile(long srcDLEntryId,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepository, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .copyDLEntryFile(srcDLEntryId, targetScreenName, target,
			targetRepository, overwrite);
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

	public static void changeFileOwner(java.lang.String screenName,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().changeFileOwner(screenName, target, owner, repositoryType);
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

	public static void changeFileMode(java.lang.String screenName,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().changeFileMode(screenName, target, mode, repositoryType);
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

	public static void duplicated(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.duplicated(portletResponse, screenName, filePath, repositoryType);
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

	public static void upload(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.io.File uploadFile,
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String fileName, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.upload(portletRequest, portletResponse, uploadFile, screenName,
			targetFolder, fileName, repositoryType);
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

	public static void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String[] files, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.download(portletRequest, portletResponse, screenName,
			targetFolder, files, repositoryType);
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

	public static void checkValidFile(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.checkValidFile(portletResponse, screenName, target, repositoryType);
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

	public static void readFileContent(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletResponse, screenName, target, repositoryType);
	}

	public static void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFile(portletRequest, portletResponse, target, repositoryType);
	}

	public static void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFile(portletRequest, portletResponse, screenName, target,
			repositoryType);
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

	public static void downloadFile(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.downloadFile(portletRequest, portletResponse, screenName, target,
			repositoryType);
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

	public static void readFileContent(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFileContent(portletResponse, contentType, screenName, target,
			repositoryType);
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

	public static void readFirstFileContent(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.readFirstFileContent(portletResponse, screenName, target, filter,
			repositoryType);
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

	public static byte[] readFileContent(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService().readFileContent(screenName, target, repositoryType);
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

	public static void getFirstFileName(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFirstFileName(portletResponse, screenName, target, filter,
			repositoryType);
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

	public static void saveFileContent(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String content, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.saveFileContent(portletResponse, screenName, target, content,
			repositoryType);
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

	public static java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException {
		return getService().readTextFile(path);
	}

	public static com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readFileAtPosition(portletRequest, target, startPosition,
			size, repositoryType);
	}

	public static com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		java.lang.String screenName, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .readFileAtPosition(screenName, target, startPosition, size,
			repositoryType);
	}

	/**
	* Get path based on repository type.
	*/
	public static java.nio.file.Path getRepositoryPath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRepositoryPath(portletRequest, path, repoType);
	}

	public static java.nio.file.Path getRepositoryPath(
		java.lang.String userName, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRepositoryPath(userName, path, repoType);
	}

	public static void getFileURL(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().getFileURL(portletRequest, portletResponse, path, repoType);
	}

	public static void getFileURL(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService().getFileURL(portletResponse, screenName, path, repoType);
	}

	public static java.lang.String getJobResultPath(
		java.lang.String simulationUuid, java.lang.String jobUuid,
		java.lang.String path) {
		return getService().getJobResultPath(simulationUuid, jobUuid, path);
	}

	public static void getFileInformation(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String userScreenName, java.lang.String pathType,
		java.lang.String folderPath, java.lang.String fileName,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		getService()
			.getFileInformation(portletResponse, userScreenName, pathType,
			folderPath, fileName, repoType);
	}

	public static void processOSPResourceAction(
		javax.portlet.ResourceRequest resourceRequest,
		javax.portlet.ResourceResponse resourceResponse) {
		getService().processOSPResourceAction(resourceRequest, resourceResponse);
	}

	/**
	* ADD GPLUS
	*      ***********************************************************************
	*/
	public static com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormOutputData(
		java.lang.String srcScreenName, java.lang.String srcSimulationUuid,
		java.lang.String srcJobUuid,
		com.liferay.portal.kernel.json.JSONObject outputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .setJobDataWithFileFormOutputData(srcScreenName,
			srcSimulationUuid, srcJobUuid, outputData, targetScreenName);
	}

	public static com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormInputData(
		java.lang.String srcScreenName,
		com.liferay.portal.kernel.json.JSONObject inputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		return getService()
				   .setJobDataWithFileFormInputData(srcScreenName, inputData,
			targetScreenName);
	}

	public static void clearService() {
		_service = null;
	}

	public static OSPFileLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OSPFileLocalService.class.getName());

			if (invokableLocalService instanceof OSPFileLocalService) {
				_service = (OSPFileLocalService)invokableLocalService;
			}
			else {
				_service = new OSPFileLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OSPFileLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(OSPFileLocalService service) {
	}

	private static OSPFileLocalService _service;
}