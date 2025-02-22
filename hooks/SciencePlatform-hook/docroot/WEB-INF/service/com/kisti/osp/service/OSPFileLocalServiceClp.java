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
public class OSPFileLocalServiceClp implements OSPFileLocalService {
	public OSPFileLocalServiceClp(InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "addOSPFile";

		_methodParameterTypes0 = new String[] { "com.kisti.osp.model.OSPFile" };

		_methodName1 = "createOSPFile";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName2 = "deleteOSPFile";

		_methodParameterTypes2 = new String[] { "java.lang.String" };

		_methodName3 = "deleteOSPFile";

		_methodParameterTypes3 = new String[] { "com.kisti.osp.model.OSPFile" };

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

		_methodName10 = "fetchOSPFile";

		_methodParameterTypes10 = new String[] { "java.lang.String" };

		_methodName11 = "getOSPFile";

		_methodParameterTypes11 = new String[] { "java.lang.String" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getOSPFiles";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getOSPFilesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateOSPFile";

		_methodParameterTypes15 = new String[] { "com.kisti.osp.model.OSPFile" };

		_methodName16 = "getBeanIdentifier";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "setBeanIdentifier";

		_methodParameterTypes17 = new String[] { "java.lang.String" };

		_methodName19 = "getLinkedTemporaryFilePath";

		_methodParameterTypes19 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName20 = "getLinkedTemporaryFilePath";

		_methodParameterTypes20 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName21 = "getCopiedTemporaryFilePath";

		_methodParameterTypes21 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName22 = "getCopiedTemporaryFilePath";

		_methodParameterTypes22 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName23 = "getCopiedTempHtmlIndexPath";

		_methodParameterTypes23 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName24 = "getFolderInformation";

		_methodParameterTypes24 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName25 = "getFolderInformation";

		_methodParameterTypes25 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName26 = "getFileInformation";

		_methodParameterTypes26 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName27 = "getFileInformation";

		_methodParameterTypes27 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName28 = "createFile";

		_methodParameterTypes28 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName29 = "createFile";

		_methodParameterTypes29 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName30 = "deleteFile";

		_methodParameterTypes30 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName31 = "deleteFile";

		_methodParameterTypes31 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName32 = "moveFile";

		_methodParameterTypes32 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName33 = "moveFile";

		_methodParameterTypes33 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};

		_methodName34 = "copyFile";

		_methodParameterTypes34 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName35 = "copyFile";

		_methodParameterTypes35 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};

		_methodName36 = "copyDLEntryFile";

		_methodParameterTypes36 = new String[] {
				"javax.portlet.PortletRequest", "long", "java.lang.String",
				"boolean", "java.lang.String"
			};

		_methodName37 = "copyDLEntryFile";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName38 = "changeFileOwner";

		_methodParameterTypes38 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName39 = "changeFileOwner";

		_methodParameterTypes39 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName40 = "changeFileMode";

		_methodParameterTypes40 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName41 = "changeFileMode";

		_methodParameterTypes41 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName42 = "duplicated";

		_methodParameterTypes42 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName43 = "duplicated";

		_methodParameterTypes43 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName44 = "upload";

		_methodParameterTypes44 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName45 = "upload";

		_methodParameterTypes45 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.io.File", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName46 = "downloadDLEntry";

		_methodParameterTypes46 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName47 = "download";

		_methodParameterTypes47 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "java.lang.String"
			};

		_methodName48 = "download";

		_methodParameterTypes48 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String[][]",
				"java.lang.String"
			};

		_methodName49 = "downloadFromText";

		_methodParameterTypes49 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName50 = "checkValidFile";

		_methodParameterTypes50 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName51 = "checkValidFile";

		_methodParameterTypes51 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName52 = "readFileContent";

		_methodParameterTypes52 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName53 = "readFileContent";

		_methodParameterTypes53 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName54 = "getFile";

		_methodParameterTypes54 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName55 = "getFile";

		_methodParameterTypes55 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName56 = "downloadFile";

		_methodParameterTypes56 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName57 = "downloadFile";

		_methodParameterTypes57 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName58 = "readFileContent";

		_methodParameterTypes58 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName59 = "readFileContent";

		_methodParameterTypes59 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName60 = "readFirstFileContent";

		_methodParameterTypes60 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName61 = "readFirstFileContent";

		_methodParameterTypes61 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName62 = "readFileContent";

		_methodParameterTypes62 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName63 = "readFileContent";

		_methodParameterTypes63 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName64 = "getFirstFileName";

		_methodParameterTypes64 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName65 = "getFirstFileName";

		_methodParameterTypes65 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName66 = "saveFileContent";

		_methodParameterTypes66 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName67 = "saveFileContent";

		_methodParameterTypes67 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName68 = "writeTextFile";

		_methodParameterTypes68 = new String[] {
				"java.lang.String", "java.nio.file.Path"
			};

		_methodName69 = "readDLAppEntry";

		_methodParameterTypes69 = new String[] {
				"javax.portlet.PortletResponse", "long"
			};

		_methodName70 = "writeToClient";

		_methodParameterTypes70 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName71 = "readTextFile";

		_methodParameterTypes71 = new String[] { "java.nio.file.Path" };

		_methodName72 = "readFileAtPosition";

		_methodParameterTypes72 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "long",
				"long", "java.lang.String"
			};

		_methodName73 = "readFileAtPosition";

		_methodParameterTypes73 = new String[] {
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String"
			};

		_methodName74 = "getRepositoryPath";

		_methodParameterTypes74 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName75 = "getRepositoryPath";

		_methodParameterTypes75 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName76 = "getFileURL";

		_methodParameterTypes76 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName77 = "getFileURL";

		_methodParameterTypes77 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName78 = "getJobResultPath";

		_methodParameterTypes78 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName79 = "getFileInformation";

		_methodParameterTypes79 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName80 = "processOSPResourceAction";

		_methodParameterTypes80 = new String[] {
				"javax.portlet.ResourceRequest",
				"javax.portlet.ResourceResponse"
			};

		_methodName81 = "setJobDataWithFileFormOutputData";

		_methodParameterTypes81 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject", "java.lang.String"
			};

		_methodName82 = "setJobDataWithFileFormInputData";

		_methodParameterTypes82 = new String[] {
				"java.lang.String", "com.liferay.portal.kernel.json.JSONObject",
				"java.lang.String"
			};
	}

	@Override
	public com.kisti.osp.model.OSPFile addOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { ClpSerializer.translateInput(ospFile) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.OSPFile createOSPFile(
		java.lang.String propertyName) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { ClpSerializer.translateInput(propertyName) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.OSPFile deleteOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] { ClpSerializer.translateInput(propertyName) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.OSPFile deleteOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] { ClpSerializer.translateInput(ospFile) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
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
	public com.kisti.osp.model.OSPFile fetchOSPFile(
		java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] { ClpSerializer.translateInput(propertyName) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.kisti.osp.model.OSPFile getOSPFile(java.lang.String propertyName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] { ClpSerializer.translateInput(propertyName) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
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
	public java.util.List<com.kisti.osp.model.OSPFile> getOSPFiles(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
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

		return (java.util.List<com.kisti.osp.model.OSPFile>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getOSPFilesCount()
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
	public com.kisti.osp.model.OSPFile updateOSPFile(
		com.kisti.osp.model.OSPFile ospFile)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] { ClpSerializer.translateInput(ospFile) });
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

		return (com.kisti.osp.model.OSPFile)ClpSerializer.translateOutput(returnObj);
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
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(prefix),
						
					ClpSerializer.translateInput(suffix),
						
					ClpSerializer.translateInput(repoType)
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
	public void getLinkedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName20,
				_methodParameterTypes20,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(srcScreenName),
					
				ClpSerializer.translateInput(parent),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(repoType)
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
	public java.lang.String getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String prefix, java.lang.String suffix,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(prefix),
						
					ClpSerializer.translateInput(suffix),
						
					ClpSerializer.translateInput(repoType)
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
	public void getCopiedTemporaryFilePath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String sourceFolder,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName22,
				_methodParameterTypes22,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(srcScreenName),
					
				ClpSerializer.translateInput(sourceFolder),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(repoType)
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
	public void getCopiedTempHtmlIndexPath(
		javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String srcScreenName, java.lang.String parent,
		java.lang.String fileName, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName23,
				_methodParameterTypes23,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(srcScreenName),
					
				ClpSerializer.translateInput(parent),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(repoType)
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
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		javax.portlet.PortletRequest portletRequest,
		java.lang.String folderPath, java.lang.String filter,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(folderPath),
						
					ClpSerializer.translateInput(filter),
						
					ClpSerializer.translateInput(repositoryType)
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
	public com.liferay.portal.kernel.json.JSONArray getFolderInformation(
		java.lang.String screenName, java.lang.String folderPath,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(folderPath),
						
					ClpSerializer.translateInput(filter),
						
					ClpSerializer.translateInput(repositoryType)
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
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(filePath),
						
					ClpSerializer.translateInput(repositoryType)
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
	public com.liferay.portal.kernel.json.JSONObject getFileInformation(
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(filePath),
						
					ClpSerializer.translateInput(repositoryType)
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
	public java.nio.file.Path createFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(repositoryType)
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

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path createFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(repositoryType)
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

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void deleteFile(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName30,
				_methodParameterTypes30,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void deleteFile(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName31,
				_methodParameterTypes31,
				new Object[] {
					ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public java.lang.String moveFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(target),
						
					overwrite,
						
					ClpSerializer.translateInput(repositoryType)
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
	public java.lang.String moveFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName33,
					_methodParameterTypes33,
					new Object[] {
						ClpSerializer.translateInput(srcScreenName),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(srcRepositoryType),
						
					ClpSerializer.translateInput(targetScreenName),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(targetRepositoryType),
						
					overwrite
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
	public java.lang.String copyFile(
		javax.portlet.PortletRequest portletRequest, java.lang.String source,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName34,
					_methodParameterTypes34,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(target),
						
					overwrite,
						
					ClpSerializer.translateInput(repositoryType)
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
	public java.lang.String copyFile(java.lang.String srcScreenName,
		java.lang.String source, java.lang.String srcRepositoryType,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepositoryType, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35,
					new Object[] {
						ClpSerializer.translateInput(srcScreenName),
						
					ClpSerializer.translateInput(source),
						
					ClpSerializer.translateInput(srcRepositoryType),
						
					ClpSerializer.translateInput(targetScreenName),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(targetRepositoryType),
						
					overwrite
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
	public java.lang.String copyDLEntryFile(
		javax.portlet.PortletRequest portletRequest, long srcDLEntryId,
		java.lang.String target, boolean overwrite,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName36,
					_methodParameterTypes36,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					srcDLEntryId,
						
					ClpSerializer.translateInput(target),
						
					overwrite,
						
					ClpSerializer.translateInput(repositoryType)
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
	public java.lang.String copyDLEntryFile(long srcDLEntryId,
		java.lang.String targetScreenName, java.lang.String target,
		java.lang.String targetRepository, boolean overwrite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName37,
					_methodParameterTypes37,
					new Object[] {
						srcDLEntryId,
						
					ClpSerializer.translateInput(targetScreenName),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(targetRepository),
						
					overwrite
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
	public void changeFileOwner(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName38,
				_methodParameterTypes38,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(owner),
					
				ClpSerializer.translateInput(repositoryType)
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
	}

	@Override
	public void changeFileOwner(java.lang.String screenName,
		java.lang.String target, java.lang.String owner,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName39,
				_methodParameterTypes39,
				new Object[] {
					ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(owner),
					
				ClpSerializer.translateInput(repositoryType)
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
	}

	@Override
	public void changeFileMode(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName40,
				_methodParameterTypes40,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(mode),
					
				ClpSerializer.translateInput(repositoryType)
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
	}

	@Override
	public void changeFileMode(java.lang.String screenName,
		java.lang.String target, java.lang.String mode,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			_invokableLocalService.invokeMethod(_methodName41,
				_methodParameterTypes41,
				new Object[] {
					ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(mode),
					
				ClpSerializer.translateInput(repositoryType)
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
	}

	@Override
	public void duplicated(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String filePath, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName42,
				_methodParameterTypes42,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(filePath),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void duplicated(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String filePath,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName43,
				_methodParameterTypes43,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(filePath),
					
				ClpSerializer.translateInput(repositoryType)
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
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName44,
				_methodParameterTypes44,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(uploadFileName),
					
				ClpSerializer.translateInput(repositoryType)
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
		javax.portlet.PortletResponse portletResponse, java.io.File uploadFile,
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String fileName, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName45,
				_methodParameterTypes45,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(uploadFile),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(targetFolder),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void downloadDLEntry(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, long dlFileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName46,
				_methodParameterTypes46,
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
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName47,
				_methodParameterTypes47,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(targetFolder),
					
				ClpSerializer.translateInput(files),
					
				ClpSerializer.translateInput(repositoryType)
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
		java.lang.String screenName, java.lang.String targetFolder,
		java.lang.String[] files, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName48,
				_methodParameterTypes48,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(targetFolder),
					
				ClpSerializer.translateInput(files),
					
				ClpSerializer.translateInput(repositoryType)
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
			_invokableLocalService.invokeMethod(_methodName49,
				_methodParameterTypes49,
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
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName50,
				_methodParameterTypes50,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void checkValidFile(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName51,
				_methodParameterTypes51,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName52,
				_methodParameterTypes52,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void readFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName53,
				_methodParameterTypes53,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName54,
				_methodParameterTypes54,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName55,
				_methodParameterTypes55,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void downloadFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName56,
				_methodParameterTypes56,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void downloadFile(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName57,
				_methodParameterTypes57,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
		java.lang.String contentType, java.lang.String target,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName58,
				_methodParameterTypes58,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(contentType),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void readFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String contentType, java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName59,
				_methodParameterTypes59,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(contentType),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(repositoryType)
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
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName60,
				_methodParameterTypes60,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(filter),
					
				ClpSerializer.translateInput(repositoryType)
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
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName61,
				_methodParameterTypes61,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(filter),
					
				ClpSerializer.translateInput(repositoryType)
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
	public byte[] readFileContent(javax.portlet.PortletRequest portletRequest,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName62,
					_methodParameterTypes62,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(repositoryType)
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

		return (byte[])ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public byte[] readFileContent(java.lang.String screenName,
		java.lang.String target, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName63,
					_methodParameterTypes63,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(target),
						
					ClpSerializer.translateInput(repositoryType)
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

		return (byte[])ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void getFirstFileName(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName64,
				_methodParameterTypes64,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(filter),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void getFirstFileName(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String filter, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName65,
				_methodParameterTypes65,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(filter),
					
				ClpSerializer.translateInput(repositoryType)
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
		java.lang.String target, java.lang.String content,
		java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName66,
				_methodParameterTypes66,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(content),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void saveFileContent(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String target,
		java.lang.String content, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName67,
				_methodParameterTypes67,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(target),
					
				ClpSerializer.translateInput(content),
					
				ClpSerializer.translateInput(repositoryType)
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
	public void writeTextFile(java.lang.String content,
		java.nio.file.Path target) throws java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName68,
				_methodParameterTypes68,
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
			_invokableLocalService.invokeMethod(_methodName69,
				_methodParameterTypes69,
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
			_invokableLocalService.invokeMethod(_methodName70,
				_methodParameterTypes70,
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
	public java.lang.String readTextFile(java.nio.file.Path path)
		throws java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName71,
					_methodParameterTypes71,
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
	public com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		javax.portlet.PortletRequest portletRequest, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName72,
					_methodParameterTypes72,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(target),
						
					startPosition,
						
					size,
						
					ClpSerializer.translateInput(repositoryType)
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
	public com.liferay.portal.kernel.json.JSONObject readFileAtPosition(
		java.lang.String screenName, java.lang.String target,
		long startPosition, long size, java.lang.String repositoryType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName73,
					_methodParameterTypes73,
					new Object[] {
						ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(target),
						
					startPosition,
						
					size,
						
					ClpSerializer.translateInput(repositoryType)
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
	public java.nio.file.Path getRepositoryPath(
		javax.portlet.PortletRequest portletRequest, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName74,
					_methodParameterTypes74,
					new Object[] {
						ClpSerializer.translateInput(portletRequest),
						
					ClpSerializer.translateInput(path),
						
					ClpSerializer.translateInput(repoType)
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

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.nio.file.Path getRepositoryPath(java.lang.String userName,
		java.lang.String path, java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName75,
					_methodParameterTypes75,
					new Object[] {
						ClpSerializer.translateInput(userName),
						
					ClpSerializer.translateInput(path),
						
					ClpSerializer.translateInput(repoType)
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

		return (java.nio.file.Path)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void getFileURL(javax.portlet.PortletRequest portletRequest,
		javax.portlet.PortletResponse portletResponse, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName76,
				_methodParameterTypes76,
				new Object[] {
					ClpSerializer.translateInput(portletRequest),
					
				ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(path),
					
				ClpSerializer.translateInput(repoType)
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
	public void getFileURL(javax.portlet.PortletResponse portletResponse,
		java.lang.String screenName, java.lang.String path,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName77,
				_methodParameterTypes77,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(screenName),
					
				ClpSerializer.translateInput(path),
					
				ClpSerializer.translateInput(repoType)
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
	public java.lang.String getJobResultPath(java.lang.String simulationUuid,
		java.lang.String jobUuid, java.lang.String path) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName78,
					_methodParameterTypes78,
					new Object[] {
						ClpSerializer.translateInput(simulationUuid),
						
					ClpSerializer.translateInput(jobUuid),
						
					ClpSerializer.translateInput(path)
					});
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
	public void getFileInformation(
		javax.portlet.PortletResponse portletResponse,
		java.lang.String userScreenName, java.lang.String pathType,
		java.lang.String folderPath, java.lang.String fileName,
		java.lang.String repoType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		try {
			_invokableLocalService.invokeMethod(_methodName79,
				_methodParameterTypes79,
				new Object[] {
					ClpSerializer.translateInput(portletResponse),
					
				ClpSerializer.translateInput(userScreenName),
					
				ClpSerializer.translateInput(pathType),
					
				ClpSerializer.translateInput(folderPath),
					
				ClpSerializer.translateInput(fileName),
					
				ClpSerializer.translateInput(repoType)
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
	public void processOSPResourceAction(
		javax.portlet.ResourceRequest resourceRequest,
		javax.portlet.ResourceResponse resourceResponse) {
		try {
			_invokableLocalService.invokeMethod(_methodName80,
				_methodParameterTypes80,
				new Object[] {
					ClpSerializer.translateInput(resourceRequest),
					
				ClpSerializer.translateInput(resourceResponse)
				});
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
	public com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormOutputData(
		java.lang.String srcScreenName, java.lang.String srcSimulationUuid,
		java.lang.String srcJobUuid,
		com.liferay.portal.kernel.json.JSONObject outputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName81,
					_methodParameterTypes81,
					new Object[] {
						ClpSerializer.translateInput(srcScreenName),
						
					ClpSerializer.translateInput(srcSimulationUuid),
						
					ClpSerializer.translateInput(srcJobUuid),
						
					ClpSerializer.translateInput(outputData),
						
					ClpSerializer.translateInput(targetScreenName)
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
	public com.liferay.portal.kernel.json.JSONObject setJobDataWithFileFormInputData(
		java.lang.String srcScreenName,
		com.liferay.portal.kernel.json.JSONObject inputData,
		java.lang.String targetScreenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			java.io.IOException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName82,
					_methodParameterTypes82,
					new Object[] {
						ClpSerializer.translateInput(srcScreenName),
						
					ClpSerializer.translateInput(inputData),
						
					ClpSerializer.translateInput(targetScreenName)
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
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
}