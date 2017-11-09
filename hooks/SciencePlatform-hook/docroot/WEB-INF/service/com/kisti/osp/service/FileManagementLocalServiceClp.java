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

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author Jerry h. Seo
 * @generated
 */
public class FileManagementLocalServiceClp implements FileManagementLocalService {
	public FileManagementLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "addFileManagement";

		_methodParameterTypes0 = new String[] {
				"com.kisti.osp.model.FileManagement"
			};

		_methodName1 = "createFileManagement";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteFileManagement";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteFileManagement";

		_methodParameterTypes3 = new String[] {
				"com.kisti.osp.model.FileManagement"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchFileManagement";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getFileManagement";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getFileManagements";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getFileManagementsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateFileManagement";

		_methodParameterTypes15 = new String[] {
				"com.kisti.osp.model.FileManagement"
			};

		_methodName16 = "getBeanIdentifier";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "setBeanIdentifier";

		_methodParameterTypes17 = new String[] { "java.lang.String" };

		_methodName19 = "getLinkedTemporaryFilePath";

		_methodParameterTypes19 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean"
			};

		_methodName20 = "getCopiedTemporaryFilePath";

		_methodParameterTypes20 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName21 = "getFolderInformation";

		_methodParameterTypes21 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName22 = "getFileInformation";

		_methodParameterTypes22 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};

		_methodName23 = "createFile";

		_methodParameterTypes23 = new String[] { "java.lang.String" };

		_methodName24 = "createFile";

		_methodParameterTypes24 = new String[] { "java.lang.String", "boolean" };

		_methodName25 = "deleteFile";

		_methodParameterTypes25 = new String[] { "java.lang.String" };

		_methodName26 = "moveFile";

		_methodParameterTypes26 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName27 = "moveFile";

		_methodParameterTypes27 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName28 = "copyFile";

		_methodParameterTypes28 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName29 = "copyFile";

		_methodParameterTypes29 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName30 = "duplicated";

		_methodParameterTypes30 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName31 = "upload";

		_methodParameterTypes31 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName32 = "download";

		_methodParameterTypes32 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName33 = "download";

		_methodParameterTypes33 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "boolean"
			};

		_methodName34 = "downloadFromText";

		_methodParameterTypes34 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName35 = "checkValidFile";

		_methodParameterTypes35 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName36 = "readFileContent";

		_methodParameterTypes36 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName37 = "getFile";

		_methodParameterTypes37 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName38 = "readFileContent";

		_methodParameterTypes38 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName39 = "readFirstFileContent";

		_methodParameterTypes39 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName40 = "getFirstFileName";

		_methodParameterTypes40 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName41 = "saveFileContent";

		_methodParameterTypes41 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName42 = "saveInputFile";

		_methodParameterTypes42 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName43 = "writeTextFile";

		_methodParameterTypes43 = new String[] {
				"java.lang.String", "java.nio.file.Path"
			};

		_methodName44 = "readDLAppEntry";

		_methodParameterTypes44 = new String[] {
				"javax.portlet.PortletResponse", "long"
			};

		_methodName45 = "writeToClient";

		_methodParameterTypes45 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName46 = "readOutLogFile";

		_methodParameterTypes46 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "long"
			};

		_methodName47 = "readTextFile";

		_methodParameterTypes47 = new String[] { "java.nio.file.Path" };

		_methodName48 = "getAbolutePath";

		_methodParameterTypes48 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};
	}

	@Override
	public com.kisti.osp.model.FileManagement addFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { ClpSerializer.translateInput(fileManagement) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.FileManagement createFileManagement(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.FileManagement deleteFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.FileManagement deleteFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] { ClpSerializer.translateInput(fileManagement) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.DynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					ClpSerializer.translateInput(projection)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public com.kisti.osp.model.FileManagement fetchFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.FileManagement getFileManagement(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.kisti.osp.model.FileManagement> getFileManagements(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13, new Object[] { start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.kisti.osp.model.FileManagement>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getFileManagementsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public com.kisti.osp.model.FileManagement updateFileManagement(
		com.kisti.osp.model.FileManagement fileManagement)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] { ClpSerializer.translateInput(fileManagement) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.kisti.osp.model.FileManagement)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableLocalService.invokeMethod(_methodName17,
				_methodParameterTypes17,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public java.lang.String getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean deleteOnExit,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(prefix),
						
					ClpSerializer.translateInput(suffix),
						
					deleteOnExit,
						
					isJobResult
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String prefix, java.lang.String suffix, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName20,
					_methodParameterTypes20,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(prefix),
						
					ClpSerializer.translateInput(suffix),
						
					isJobResult
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(folderPath),
						
					ClpSerializer.translateInput(filter),
						
					isJobResult
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.json.JSONArray)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		javax.portlet.PortletRequest portletRequest, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(filePath),
						
					isJobResult
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.json.JSONObject)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path createFile(java.lang.String target)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
					new Object[] { ClpSerializer.translateInput(target) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path createFile(java.lang.String target,
		boolean overwrite) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24,
					new Object[] { ClpSerializer.translateInput(target), overwrite });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void deleteFile(java.lang.String fileName)
		throws java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName25,
				_methodParameterTypes25,
				new Object[] { ClpSerializer.translateInput(fileName) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.nio.file.Path moveFile(java.lang.String srcPath,
		java.lang.String targetPath) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26,
					new Object[] {
						ClpSerializer.translateInput(srcPath),
						
					ClpSerializer.translateInput(targetPath)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path moveFile(java.lang.String srcPath,
		java.lang.String targetPath, boolean overwrite)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						ClpSerializer.translateInput(srcPath),
						
					ClpSerializer.translateInput(targetPath),
						
					overwrite
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path copyFile(java.lang.String srcPath,
		java.lang.String targetPath) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] {
						ClpSerializer.translateInput(srcPath),
						
					ClpSerializer.translateInput(targetPath)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path copyFile(java.lang.String source,
		java.lang.String target, boolean overwrite) throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29,
					new Object[] {
						ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(target),
						
					overwrite
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName30,
				_methodParameterTypes30,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(filePath),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void upload(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String uploadFileName,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName31,
				_methodParameterTypes31,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(uploadFileName),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName32,
				_methodParameterTypes32,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				dlFileEntryId
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void download(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String targetFolder, java.lang.String[] files,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName33,
				_methodParameterTypes33,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(targetFolder),
					
				ClpSerializer.translateInput(files),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void downloadFromText(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String fileName, java.lang.String fileContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName34,
				_methodParameterTypes34,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(fileContext)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void checkValidFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName35,
				_methodParameterTypes35,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(filePath),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName36,
				_methodParameterTypes36,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(filePath),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void getFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName37,
				_methodParameterTypes37,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(filePath),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void readFileContent(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String filePath,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName38,
				_methodParameterTypes38,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(contentType),
					
				ClpSerializer.translateInput(filePath),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
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
		try {
			_invokableLocalService.invokeMethod(_methodName39,
				_methodParameterTypes39,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(parentPath),
					
				ClpSerializer.translateInput(filter),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void getFirstFileName(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String parentPath, java.lang.String filter,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName40,
				_methodParameterTypes40,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(parentPath),
					
				ClpSerializer.translateInput(filter),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void saveFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String filePath, java.lang.String content, boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName41,
				_methodParameterTypes41,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(filePath),
					
				ClpSerializer.translateInput(content),
					
				isJobResult
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
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
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName42,
					_methodParameterTypes42,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(scienceAppName),
						
					ClpSerializer.translateInput(scienceAppVersion),
						
					ClpSerializer.translateInput(simulationTime),
						
					ClpSerializer.translateInput(jobNumber),
						
					ClpSerializer.translateInput(fileName),
						
					ClpSerializer.translateInput(content)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.json.JSONObject)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void writeTextFile(java.lang.String content,
		java.nio.file.Path target) throws java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName43,
				_methodParameterTypes43,
				new Object[] {
					ClpSerializer.translateInput(content),
					
				ClpSerializer.translateInput(target)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void readDLAppEntry(javax.portlet.PortletResponse portletResponse,
		long dlEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName44,
				_methodParameterTypes44,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				dlEntryId
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void writeToClient(javax.portlet.PortletResponse portletResponse,
		java.lang.String errorMessage,
		com.liferay.portal.kernel.json.JSONObject data)
		throws java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName45,
				_methodParameterTypes45,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(errorMessage),
					
				ClpSerializer.translateInput(data)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> readOutLogFile(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String simulationUuid, java.lang.String jobUuid,
		long lastPosition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName46,
					_methodParameterTypes46,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(jobUuid),
						
					lastPosition
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.Map<java.lang.String, java.lang.Object>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName47,
					_methodParameterTypes47,
					new Object[] { ClpSerializer.translateInput(path) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.io.IOException) {
				throw (java.io.IOException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.String getAbolutePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		boolean isJobResult)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName48,
					_methodParameterTypes48,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(path),
						
					isJobResult
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	private InvokableLocalService _invokableLocalService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
}