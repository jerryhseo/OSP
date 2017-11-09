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

		_methodName57 = "getCopiedTemporaryFilePath";

		_methodParameterTypes57 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName58 = "getFolderInformation";

		_methodParameterTypes58 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName59 = "getFileInformation";

		_methodParameterTypes59 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
			};

		_methodName60 = "createFile";

		_methodParameterTypes60 = new String[] { "java.lang.String" };

		_methodName61 = "createFile";

		_methodParameterTypes61 = new String[] { "java.lang.String", "boolean" };

		_methodName62 = "deleteFile";

		_methodParameterTypes62 = new String[] { "java.lang.String" };

		_methodName63 = "moveFile";

		_methodParameterTypes63 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName64 = "moveFile";

		_methodParameterTypes64 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName65 = "copyFile";

		_methodParameterTypes65 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName66 = "copyFile";

		_methodParameterTypes66 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName67 = "duplicated";

		_methodParameterTypes67 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName68 = "upload";

		_methodParameterTypes68 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName69 = "download";

		_methodParameterTypes69 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"long"
			};

		_methodName70 = "download";

		_methodParameterTypes70 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String[][]", "boolean"
			};

		_methodName71 = "downloadFromText";

		_methodParameterTypes71 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String"
			};

		_methodName75 = "checkValidFile";

		_methodParameterTypes75 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName76 = "readFileContent";

		_methodParameterTypes76 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName77 = "getFile";

		_methodParameterTypes77 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "boolean"
			};

		_methodName78 = "readFileContent";

		_methodParameterTypes78 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName79 = "readFirstFileContent";

		_methodParameterTypes79 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName80 = "getFirstFileName";

		_methodParameterTypes80 = new String[] {
				"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName81 = "saveFileContent";

		_methodParameterTypes81 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName82 = "saveInputFile";

		_methodParameterTypes82 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName83 = "writeTextFile";

		_methodParameterTypes83 = new String[] {
				"java.lang.String", "java.nio.file.Path"
			};

		_methodName84 = "readDLAppEntry";

		_methodParameterTypes84 = new String[] {
				"javax.portlet.PortletResponse", "long"
			};

		_methodName85 = "writeToClient";

		_methodParameterTypes85 = new String[] {
				"javax.portlet.PortletResponse", "java.lang.String",
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName86 = "readOutLogFile";

		_methodParameterTypes86 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String",
				"java.lang.String", "long"
			};

		_methodName87 = "readTextFile";

		_methodParameterTypes87 = new String[] { "java.nio.file.Path" };

		_methodName88 = "getAbolutePath";

		_methodParameterTypes88 = new String[] {
				"javax.portlet.PortletRequest", "java.lang.String", "boolean"
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
			return FileManagementLocalServiceUtil.getCopiedTemporaryFilePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFolderInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return FileManagementLocalServiceUtil.getFileInformation((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return FileManagementLocalServiceUtil.createFile((java.lang.String)arguments[0]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return FileManagementLocalServiceUtil.createFile((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			FileManagementLocalServiceUtil.deleteFile((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return FileManagementLocalServiceUtil.moveFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return FileManagementLocalServiceUtil.moveFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return FileManagementLocalServiceUtil.copyFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return FileManagementLocalServiceUtil.copyFile((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			FileManagementLocalServiceUtil.duplicated((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			FileManagementLocalServiceUtil.upload((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			FileManagementLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			FileManagementLocalServiceUtil.download((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			FileManagementLocalServiceUtil.downloadFromText((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			FileManagementLocalServiceUtil.checkValidFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			FileManagementLocalServiceUtil.getFile((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			FileManagementLocalServiceUtil.readFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			FileManagementLocalServiceUtil.readFirstFileContent((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			FileManagementLocalServiceUtil.getFirstFileName((javax.portlet.PortletRequest)arguments[0],
				(javax.portlet.PortletResponse)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());

			return null;
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			FileManagementLocalServiceUtil.saveFileContent((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return FileManagementLocalServiceUtil.saveInputFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			FileManagementLocalServiceUtil.writeTextFile((java.lang.String)arguments[0],
				(java.nio.file.Path)arguments[1]);

			return null;
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			FileManagementLocalServiceUtil.readDLAppEntry((javax.portlet.PortletResponse)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			FileManagementLocalServiceUtil.writeToClient((javax.portlet.PortletResponse)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.json.JSONObject)arguments[2]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return FileManagementLocalServiceUtil.readOutLogFile((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Long)arguments[3]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return FileManagementLocalServiceUtil.readTextFile((java.nio.file.Path)arguments[0]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return FileManagementLocalServiceUtil.getAbolutePath((javax.portlet.PortletRequest)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
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
}