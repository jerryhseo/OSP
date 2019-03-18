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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for OSPFile. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Jerry h. Seo
 * @see OSPFileLocalServiceUtil
 * @see com.kisti.osp.service.base.OSPFileLocalServiceBaseImpl
 * @see com.kisti.osp.service.impl.OSPFileLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OSPFileLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OSPFileLocalServiceUtil} to access the o s p file local service. Add custom service methods to {@link com.kisti.osp.service.impl.OSPFileLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the o s p file to the database. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was added
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.model.OSPFile addOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new o s p file with the primary key. Does not add the o s p file to the database.
	*
	* @param propertyName the primary key for the new o s p file
	* @return the new o s p file
	*/
	public com.kisti.osp.model.OSPFile createOSPFile(
		java.lang.String propertyName);

	/**
	* Deletes the o s p file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file that was removed
	* @throws PortalException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.model.OSPFile deleteOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the o s p file from the database. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was removed
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.kisti.osp.model.OSPFile deleteOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.model.OSPFile fetchOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o s p file with the primary key.
	*
	* @param propertyName the primary key of the o s p file
	* @return the o s p file
	* @throws PortalException if a o s p file with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.kisti.osp.model.OSPFile getOSPFile(java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.kisti.osp.model.OSPFile> getOSPFiles(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o s p files.
	*
	* @return the number of o s p files
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOSPFilesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the o s p file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ospFile the o s p file
	* @return the o s p file that was updated
	* @throws SystemException if a system exception occurred
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.kisti.osp.model.OSPFile updateOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Link a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Copy a file or a folder to servlet container template folder based on
	*  of the servlet context path.
	*  Returns the template path based on of the servlet context path.
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		java.lang.String screenName, java.lang.String folderPath,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Returns information of a specified file and return JSONObject.
	*  {
	*      name:String [file name],
	*     size: String [file size],
	*     isFile:boolean [ file or not ]
	*  }
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@java.lang.Deprecated
	public java.nio.file.Path createFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public java.nio.file.Path createFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@java.lang.Deprecated
	public void deleteFile(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void deleteFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@java.lang.Deprecated
	public java.lang.String moveFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public java.lang.String moveFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@java.lang.Deprecated
	public java.lang.String copyFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public java.lang.String copyFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Copy an dlentry to the target file
	*
	* @throws IOException
	* @throws SystemException
	* @throws PortalException
	*/
	@java.lang.Deprecated
	public java.lang.String copyDLEntryFile(
		javax.portlet.PortletRequest portletRequest, long srcDLEntryId,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public java.lang.String copyDLEntryFile(long srcDLEntryId,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepository, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Change owner of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@java.lang.Deprecated
	public void changeFileOwner(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void changeFileOwner(java.lang.String screenName,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Change mode of a file
	*
	* @throws SystemException
	* @throws PortalException
	*/
	@java.lang.Deprecated
	public void changeFileMode(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void changeFileMode(java.lang.String screenName,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@java.lang.Deprecated
	public void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void duplicated(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void upload(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.io.File uploadFile,
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String fileName, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	public void downloadDLEntry(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Download a file or files.
	* If count of the files is larger than 1, the files zipped as an file.
	*/
	@java.lang.Deprecated
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String[] files, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void downloadFromText(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String fileName, java.lang.String fileContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void checkValidFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void checkValidFile(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void readFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void downloadFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void downloadFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void readFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void readFirstFileContent(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void readFirstFileContent(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public byte[] readFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public byte[] readFileContent(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFirstFileName(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFirstFileName(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

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
	@java.lang.Deprecated
	public void saveFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String content,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void saveFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String content, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void writeTextFile(java.lang.String content,
		java.nio.file.Path target) throws java.io.IOException;

	public void readDLAppEntry(javax.portlet.PortletResponse portletResponse,
		long dlEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void writeToClient(javax.portlet.PortletResponse portletResponse,
		java.lang.String errorMessage,
		com.liferay.portal.kernel.json.JSONObject data)
		throws java.io.IOException;

	public java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException;

	@java.lang.Deprecated
	public com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		java.lang.String screenName, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	/**
	* Get path based on repository type.
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.nio.file.Path getRepositoryPath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.nio.file.Path getRepositoryPath(java.lang.String userName,
		java.lang.String path, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFileURL(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFileURL(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getJobResultPath(java.lang.String simulationUuid,
		java.lang.String jobUuid, java.lang.String path);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void getFileInformation(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String userScreenName, java.lang.String pathType,
		java.lang.String folderPath, java.lang.String fileName,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public void processOSPResourceAction(
		javax.portlet.ResourceRequest resourceRequest,
		javax.portlet.ResourceResponse resourceResponse);

	/**
	* ADD GPLUS
	*      ***********************************************************************
	*/
	public com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormOutputData(
		java.lang.String srcScreenName, java.lang.String srcSimulationUuid,
		java.lang.String srcJobUuid,
		com.liferay.portal.kernel.json.JSONObject outputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;

	public com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormInputData(
		java.lang.String srcScreenName,
		com.liferay.portal.kernel.json.JSONObject inputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException;
}