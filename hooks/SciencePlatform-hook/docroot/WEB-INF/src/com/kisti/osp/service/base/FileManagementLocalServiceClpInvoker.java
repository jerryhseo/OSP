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

package com.kisti.osp.service.base;

import com.kisti.osp.service.FileManagementLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Jerry h. Seo
 * @generated
 */
public class FileManagementLocalServiceClpInvoker {
	public FileManagementLocalServiceClpInvoker() {
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

		_methodName40 = "getBeanIdentifier";

		_methodParameterTypes40 = new String[] {  };

		_methodName41 = "setBeanIdentifier";

		_methodParameterTypes41 = new String[] { "java.lang.String" };

		_methodName56 = "getLinkedTemporaryFilePath";

		_methodParameterTypes56 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean"
			};

		_methodName57 = "getLinkedTemporaryFilePath";

		_methodParameterTypes57 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean",
				"java.lang.String"
			};

		_methodName58 = "getCopiedTemporaryFilePath";

		_methodParameterTypes58 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName59 = "getCopiedTemporaryFilePath";

		_methodParameterTypes59 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName60 = "getFolderInformation";

		_methodParameterTypes60 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName61 = "getFolderInformation";

		_methodParameterTypes61 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName62 = "getFileInformation";

		_methodParameterTypes62 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};

		_methodName63 = "getFileInformation";

		_methodParameterTypes63 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName64 = "createFile";

		_methodParameterTypes64 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName65 = "deleteFile";

		_methodParameterTypes65 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName67 = "moveFile";

		_methodParameterTypes67 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName69 = "copyFile";

		_methodParameterTypes69 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName71 = "copyDLEntryFile";

		_methodParameterTypes71 = new String[] {
				"javax.portlet.PortletRequest", "long", "java.lang.String",
				"boolean", "java.lang.String"
			};

		_methodName72 = "changeFileOwner";

		_methodParameterTypes72 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName73 = "changeFileMode";

		_methodParameterTypes73 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName76 = "duplicated";

		_methodParameterTypes76 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName77 = "duplicated";

		_methodParameterTypes77 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName78 = "upload";

		_methodParameterTypes78 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName79 = "upload";

		_methodParameterTypes79 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName80 = "download";

		_methodParameterTypes80 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName81 = "downloadDLEntry";

		_methodParameterTypes81 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName82 = "download";

		_methodParameterTypes82 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "boolean"
			};

		_methodName83 = "download";

		_methodParameterTypes83 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "java.lang.String"
			};

		_methodName84 = "downloadFromText";

		_methodParameterTypes84 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName88 = "checkValidFile";

		_methodParameterTypes88 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName89 = "checkValidFile";

		_methodParameterTypes89 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName90 = "readFileContent";

		_methodParameterTypes90 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName91 = "readFileContent";

		_methodParameterTypes91 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName92 = "getFile";

		_methodParameterTypes92 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName93 = "downloadFile";

		_methodParameterTypes93 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName94 = "readFileContent";

		_methodParameterTypes94 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName95 = "readFileContent";

		_methodParameterTypes95 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName96 = "readFirstFileContent";

		_methodParameterTypes96 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName97 = "readFirstFileContent";

		_methodParameterTypes97 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName98 = "readFileContent";

		_methodParameterTypes98 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};

		_methodName99 = "readFileContent";

		_methodParameterTypes99 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName100 = "getFirstFileName";

		_methodParameterTypes100 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName101 = "getFirstFileName";

		_methodParameterTypes101 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName102 = "saveFileContent";

		_methodParameterTypes102 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName103 = "saveFileContent";

		_methodParameterTypes103 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName104 = "writeTextFile";

		_methodParameterTypes104 = new String[] {
				"java.lang.String", "java.nio.file.Path"
			};

		_methodName105 = "readDLAppEntry";

		_methodParameterTypes105 = new String[] {
				"javax.portlet.PortletResponse", "long"
			};

		_methodName106 = "writeToClient";

		_methodParameterTypes106 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName107 = "readOutLogFile";

		_methodParameterTypes107 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "long"
			};

		_methodName108 = "readFile";

		_methodParameterTypes108 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "long",
				"long", "java.lang.String"
			};

		_methodName109 = "readTextFile";

		_methodParameterTypes109 = new String[] { "java.nio.file.Path" };

		_methodName110 = "getAbolutePath";

		_methodParameterTypes110 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};

		_methodName112 = "getJobResultPath";

		_methodParameterTypes112 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return FileManagementLocalServiceUtil.addFileManagement((com.kisti.osp.model.FileManagement)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return FileManagementLocalServiceUtil.createFileManagement(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return FileManagementLocalServiceUtil.deleteFileManagement(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return FileManagementLocalServiceUtil.deleteFileManagement((com.kisti.osp.model.FileManagement)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return FileManagementLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return FileManagementLocalServiceUtil.fetchFileManagement(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileManagement(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return FileManagementLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileManagements(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileManagementsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return FileManagementLocalServiceUtil.updateFileManagement((com.kisti.osp.model.FileManagement)arguments[0]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return FileManagementLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			FileManagementLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return FileManagementLocalServiceUtil.getLinkedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return FileManagementLocalServiceUtil.getLinkedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				(java.lang.String)arguments[5]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return FileManagementLocalServiceUtil.getCopiedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return FileManagementLocalServiceUtil.getCopiedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFolderInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFolderInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return FileManagementLocalServiceUtil.createFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			FileManagementLocalServiceUtil.deleteFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return FileManagementLocalServiceUtil.moveFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return FileManagementLocalServiceUtil.copyFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return FileManagementLocalServiceUtil.copyDLEntryFile((javax.portlet.PortletRequest)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			FileManagementLocalServiceUtil.changeFileOwner((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			FileManagementLocalServiceUtil.changeFileMode((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			FileManagementLocalServiceUtil.duplicated((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			FileManagementLocalServiceUtil.duplicated((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			FileManagementLocalServiceUtil.upload((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			FileManagementLocalServiceUtil.upload((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			FileManagementLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			FileManagementLocalServiceUtil.downloadDLEntry((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			FileManagementLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			FileManagementLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			FileManagementLocalServiceUtil.downloadFromText((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			FileManagementLocalServiceUtil.checkValidFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			FileManagementLocalServiceUtil.checkValidFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			FileManagementLocalServiceUtil.getFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			FileManagementLocalServiceUtil.downloadFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			FileManagementLocalServiceUtil.readFirstFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			FileManagementLocalServiceUtil.readFirstFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			FileManagementLocalServiceUtil.getFirstFileName((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			FileManagementLocalServiceUtil.getFirstFileName((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			FileManagementLocalServiceUtil.saveFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			FileManagementLocalServiceUtil.saveFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			FileManagementLocalServiceUtil.writeTextFile((java.lang.String)arguments[0],
				(java.nio.file.Path)arguments[1]);

			return null;
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			FileManagementLocalServiceUtil.readDLAppEntry((javax.portlet.PortletResponse)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			FileManagementLocalServiceUtil.writeToClient((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.json.JSONObject)arguments[2]);

			return null;
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return FileManagementLocalServiceUtil.readOutLogFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Long)arguments[3]).longValue());
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return FileManagementLocalServiceUtil.readFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.lang.String)arguments[4]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return FileManagementLocalServiceUtil.readTextFile((java.nio.file.Path)arguments[0]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return FileManagementLocalServiceUtil.getAbolutePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return FileManagementLocalServiceUtil.getJobResultPath((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

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
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
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
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
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
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName112;
	private String[] _methodParameterTypes112;
}