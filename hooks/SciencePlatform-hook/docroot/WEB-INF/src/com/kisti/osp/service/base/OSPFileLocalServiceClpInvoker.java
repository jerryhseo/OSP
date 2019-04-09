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

import com.kisti.osp.service.OSPFileLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Jerry h. Seo
 * @generated
 */
public class OSPFileLocalServiceClpInvoker {
	public OSPFileLocalServiceClpInvoker() {
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

		_methodName40 = "getBeanIdentifier";

		_methodParameterTypes40 = new String[] {  };

		_methodName41 = "setBeanIdentifier";

		_methodParameterTypes41 = new String[] { "java.lang.String" };

		_methodName70 = "getLinkedTemporaryFilePath";

		_methodParameterTypes70 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName71 = "getLinkedTemporaryFilePath";

		_methodParameterTypes71 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName72 = "getCopiedTemporaryFilePath";

		_methodParameterTypes72 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName73 = "getCopiedTemporaryFilePath";

		_methodParameterTypes73 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName74 = "getCopiedTempHtmlIndexPath";

		_methodParameterTypes74 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName75 = "getFolderInformation";

		_methodParameterTypes75 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName76 = "getFolderInformation";

		_methodParameterTypes76 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName77 = "getFileInformation";

		_methodParameterTypes77 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName78 = "getFileInformation";

		_methodParameterTypes78 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName79 = "createFile";

		_methodParameterTypes79 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName80 = "createFile";

		_methodParameterTypes80 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName81 = "deleteFile";

		_methodParameterTypes81 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName82 = "deleteFile";

		_methodParameterTypes82 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName83 = "moveFile";

		_methodParameterTypes83 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName84 = "moveFile";

		_methodParameterTypes84 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};

		_methodName85 = "copyFile";

		_methodParameterTypes85 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String"
			};

		_methodName86 = "copyFile";

		_methodParameterTypes86 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};

		_methodName87 = "copyDLEntryFile";

		_methodParameterTypes87 = new String[] {
				"javax.portlet.PortletRequest", "long", "java.lang.String",
				"boolean", "java.lang.String"
			};

		_methodName88 = "copyDLEntryFile";

		_methodParameterTypes88 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName89 = "changeFileOwner";

		_methodParameterTypes89 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName90 = "changeFileOwner";

		_methodParameterTypes90 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName91 = "changeFileMode";

		_methodParameterTypes91 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName92 = "changeFileMode";

		_methodParameterTypes92 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName93 = "duplicated";

		_methodParameterTypes93 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName94 = "duplicated";

		_methodParameterTypes94 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName95 = "upload";

		_methodParameterTypes95 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName96 = "upload";

		_methodParameterTypes96 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.io.File", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName97 = "downloadDLEntry";

		_methodParameterTypes97 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName98 = "download";

		_methodParameterTypes98 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "java.lang.String"
			};

		_methodName99 = "download";

		_methodParameterTypes99 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String[][]",
				"java.lang.String"
			};

		_methodName100 = "downloadFromText";

		_methodParameterTypes100 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName101 = "checkValidFile";

		_methodParameterTypes101 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName102 = "checkValidFile";

		_methodParameterTypes102 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName103 = "readFileContent";

		_methodParameterTypes103 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName104 = "readFileContent";

		_methodParameterTypes104 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName105 = "getFile";

		_methodParameterTypes105 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName106 = "getFile";

		_methodParameterTypes106 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName107 = "downloadFile";

		_methodParameterTypes107 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName108 = "downloadFile";

		_methodParameterTypes108 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName109 = "readFileContent";

		_methodParameterTypes109 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName110 = "readFileContent";

		_methodParameterTypes110 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName111 = "readFirstFileContent";

		_methodParameterTypes111 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName112 = "readFirstFileContent";

		_methodParameterTypes112 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName113 = "readFileContent";

		_methodParameterTypes113 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName114 = "readFileContent";

		_methodParameterTypes114 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName115 = "getFirstFileName";

		_methodParameterTypes115 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName116 = "getFirstFileName";

		_methodParameterTypes116 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName117 = "saveFileContent";

		_methodParameterTypes117 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName118 = "saveFileContent";

		_methodParameterTypes118 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName119 = "writeTextFile";

		_methodParameterTypes119 = new String[] {
				"java.lang.String", "java.nio.file.Path"
			};

		_methodName120 = "readDLAppEntry";

		_methodParameterTypes120 = new String[] {
				"javax.portlet.PortletResponse", "long"
			};

		_methodName121 = "writeToClient";

		_methodParameterTypes121 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName122 = "readTextFile";

		_methodParameterTypes122 = new String[] { "java.nio.file.Path" };

		_methodName123 = "readFileAtPosition";

		_methodParameterTypes123 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "long",
				"long", "java.lang.String"
			};

		_methodName124 = "readFileAtPosition";

		_methodParameterTypes124 = new String[] {
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String"
			};

		_methodName125 = "getRepositoryPath";

		_methodParameterTypes125 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String"
			};

		_methodName126 = "getRepositoryPath";

		_methodParameterTypes126 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName127 = "getFileURL";

		_methodParameterTypes127 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName128 = "getFileURL";

		_methodParameterTypes128 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName129 = "getJobResultPath";

		_methodParameterTypes129 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName130 = "getFileInformation";

		_methodParameterTypes130 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName131 = "processOSPResourceAction";

		_methodParameterTypes131 = new String[] {
				"javax.portlet.ResourceRequest",
				"javax.portlet.ResourceResponse"
			};

		_methodName132 = "setJobDataWithFileFormOutputData";

		_methodParameterTypes132 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject", "java.lang.String"
			};

		_methodName133 = "setJobDataWithFileFormInputData";

		_methodParameterTypes133 = new String[] {
				"java.lang.String", "com.liferay.portal.kernel.json.JSONObject",
				"java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OSPFileLocalServiceUtil.addOSPFile((com.kisti.osp.model.OSPFile)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OSPFileLocalServiceUtil.createOSPFile((java.lang.String)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OSPFileLocalServiceUtil.deleteOSPFile((java.lang.String)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OSPFileLocalServiceUtil.deleteOSPFile((com.kisti.osp.model.OSPFile)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OSPFileLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OSPFileLocalServiceUtil.fetchOSPFile((java.lang.String)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OSPFileLocalServiceUtil.getOSPFile((java.lang.String)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OSPFileLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OSPFileLocalServiceUtil.getOSPFiles(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OSPFileLocalServiceUtil.getOSPFilesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OSPFileLocalServiceUtil.updateOSPFile((com.kisti.osp.model.OSPFile)arguments[0]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return OSPFileLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			OSPFileLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return OSPFileLocalServiceUtil.getLinkedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			OSPFileLocalServiceUtil.getLinkedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return OSPFileLocalServiceUtil.getCopiedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			OSPFileLocalServiceUtil.getCopiedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			OSPFileLocalServiceUtil.getCopiedTempHtmlIndexPath((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return OSPFileLocalServiceUtil.getFolderInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return OSPFileLocalServiceUtil.getFolderInformation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return OSPFileLocalServiceUtil.getFileInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return OSPFileLocalServiceUtil.getFileInformation((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return OSPFileLocalServiceUtil.createFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return OSPFileLocalServiceUtil.createFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			OSPFileLocalServiceUtil.deleteFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			OSPFileLocalServiceUtil.deleteFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return OSPFileLocalServiceUtil.moveFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return OSPFileLocalServiceUtil.moveFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return OSPFileLocalServiceUtil.copyFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return OSPFileLocalServiceUtil.copyFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return OSPFileLocalServiceUtil.copyDLEntryFile((javax.portlet.PortletRequest)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.lang.String)arguments[4]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return OSPFileLocalServiceUtil.copyDLEntryFile(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			OSPFileLocalServiceUtil.changeFileOwner((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			OSPFileLocalServiceUtil.changeFileOwner((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			OSPFileLocalServiceUtil.changeFileMode((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			OSPFileLocalServiceUtil.changeFileMode((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			OSPFileLocalServiceUtil.duplicated((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			OSPFileLocalServiceUtil.duplicated((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			OSPFileLocalServiceUtil.upload((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			OSPFileLocalServiceUtil.upload((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.io.File)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6]);

			return null;
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			OSPFileLocalServiceUtil.downloadDLEntry((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			OSPFileLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			OSPFileLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String[])arguments[4], (java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			OSPFileLocalServiceUtil.downloadFromText((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			OSPFileLocalServiceUtil.checkValidFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			OSPFileLocalServiceUtil.checkValidFile((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			OSPFileLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			OSPFileLocalServiceUtil.readFileContent((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			OSPFileLocalServiceUtil.getFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			OSPFileLocalServiceUtil.getFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			OSPFileLocalServiceUtil.downloadFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			OSPFileLocalServiceUtil.downloadFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			OSPFileLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			OSPFileLocalServiceUtil.readFileContent((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			OSPFileLocalServiceUtil.readFirstFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			OSPFileLocalServiceUtil.readFirstFileContent((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return OSPFileLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return OSPFileLocalServiceUtil.readFileContent((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			OSPFileLocalServiceUtil.getFirstFileName((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			OSPFileLocalServiceUtil.getFirstFileName((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			OSPFileLocalServiceUtil.saveFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			OSPFileLocalServiceUtil.saveFileContent((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			OSPFileLocalServiceUtil.writeTextFile((java.lang.String)arguments[0],
				(java.nio.file.Path)arguments[1]);

			return null;
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			OSPFileLocalServiceUtil.readDLAppEntry((javax.portlet.PortletResponse)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			OSPFileLocalServiceUtil.writeToClient((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.json.JSONObject)arguments[2]);

			return null;
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return OSPFileLocalServiceUtil.readTextFile((java.nio.file.Path)arguments[0]);
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return OSPFileLocalServiceUtil.readFileAtPosition((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.lang.String)arguments[4]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return OSPFileLocalServiceUtil.readFileAtPosition((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.lang.String)arguments[4]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return OSPFileLocalServiceUtil.getRepositoryPath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return OSPFileLocalServiceUtil.getRepositoryPath((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName127.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes127, parameterTypes)) {
			OSPFileLocalServiceUtil.getFileURL((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			OSPFileLocalServiceUtil.getFileURL((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			return OSPFileLocalServiceUtil.getJobResultPath((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			OSPFileLocalServiceUtil.getFileInformation((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName131.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes131, parameterTypes)) {
			OSPFileLocalServiceUtil.processOSPResourceAction((javax.portlet.ResourceRequest)arguments[0],
				(javax.portlet.ResourceResponse)arguments[1]);

			return null;
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			return OSPFileLocalServiceUtil.setJobDataWithFileFormOutputData((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.kernel.json.JSONObject)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			return OSPFileLocalServiceUtil.setJobDataWithFileFormInputData((java.lang.String)arguments[0],
				(com.liferay.portal.kernel.json.JSONObject)arguments[1],
				(java.lang.String)arguments[2]);
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
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
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
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
	private String _methodName116;
	private String[] _methodParameterTypes116;
	private String _methodName117;
	private String[] _methodParameterTypes117;
	private String _methodName118;
	private String[] _methodParameterTypes118;
	private String _methodName119;
	private String[] _methodParameterTypes119;
	private String _methodName120;
	private String[] _methodParameterTypes120;
	private String _methodName121;
	private String[] _methodParameterTypes121;
	private String _methodName122;
	private String[] _methodParameterTypes122;
	private String _methodName123;
	private String[] _methodParameterTypes123;
	private String _methodName124;
	private String[] _methodParameterTypes124;
	private String _methodName125;
	private String[] _methodParameterTypes125;
	private String _methodName126;
	private String[] _methodParameterTypes126;
	private String _methodName127;
	private String[] _methodParameterTypes127;
	private String _methodName128;
	private String[] _methodParameterTypes128;
	private String _methodName129;
	private String[] _methodParameterTypes129;
	private String _methodName130;
	private String[] _methodParameterTypes130;
	private String _methodName131;
	private String[] _methodParameterTypes131;
	private String _methodName132;
	private String[] _methodParameterTypes132;
	private String _methodName133;
	private String[] _methodParameterTypes133;
}