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

package org.kisti.edison.virtuallaboratory.service.base;

import org.kisti.edison.virtuallaboratory.service.VirtualLabLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class VirtualLabLocalServiceClpInvoker {
	public VirtualLabLocalServiceClpInvoker() {
		_methodName0 = "addVirtualLab";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName1 = "createVirtualLab";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteVirtualLab";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteVirtualLab";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.VirtualLab"
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

		_methodName10 = "fetchVirtualLab";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getVirtualLab";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getVirtualLabs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getVirtualLabsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateVirtualLab";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName16 = "addVirtualLabClassVirtualLab";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addVirtualLabClassVirtualLab";

		_methodParameterTypes17 = new String[] {
				"long", "org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName18 = "addVirtualLabClassVirtualLabs";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addVirtualLabClassVirtualLabs";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearVirtualLabClassVirtualLabs";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteVirtualLabClassVirtualLab";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteVirtualLabClassVirtualLab";

		_methodParameterTypes22 = new String[] {
				"long", "org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName23 = "deleteVirtualLabClassVirtualLabs";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteVirtualLabClassVirtualLabs";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getVirtualLabClassVirtualLabs";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getVirtualLabClassVirtualLabs";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getVirtualLabClassVirtualLabs";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getVirtualLabClassVirtualLabsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasVirtualLabClassVirtualLab";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasVirtualLabClassVirtualLabs";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setVirtualLabClassVirtualLabs";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName32 = "addSurveyVirtualLab";

		_methodParameterTypes32 = new String[] { "long", "long" };

		_methodName33 = "addSurveyVirtualLab";

		_methodParameterTypes33 = new String[] {
				"long", "org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName34 = "addSurveyVirtualLabs";

		_methodParameterTypes34 = new String[] { "long", "long[][]" };

		_methodName35 = "addSurveyVirtualLabs";

		_methodParameterTypes35 = new String[] { "long", "java.util.List" };

		_methodName36 = "clearSurveyVirtualLabs";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "deleteSurveyVirtualLab";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "deleteSurveyVirtualLab";

		_methodParameterTypes38 = new String[] {
				"long", "org.kisti.edison.virtuallaboratory.model.VirtualLab"
			};

		_methodName39 = "deleteSurveyVirtualLabs";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "deleteSurveyVirtualLabs";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "getSurveyVirtualLabs";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "getSurveyVirtualLabs";

		_methodParameterTypes42 = new String[] { "long", "int", "int" };

		_methodName43 = "getSurveyVirtualLabs";

		_methodParameterTypes43 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName44 = "getSurveyVirtualLabsCount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "hasSurveyVirtualLab";

		_methodParameterTypes45 = new String[] { "long", "long" };

		_methodName46 = "hasSurveyVirtualLabs";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "setSurveyVirtualLabs";

		_methodParameterTypes47 = new String[] { "long", "long[][]" };

		_methodName152 = "getBeanIdentifier";

		_methodParameterTypes152 = new String[] {  };

		_methodName153 = "setBeanIdentifier";

		_methodParameterTypes153 = new String[] { "java.lang.String" };

		_methodName158 = "addVirtualLab";

		_methodParameterTypes158 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long", "java.util.Map",
				"java.util.Locale"
			};

		_methodName159 = "getVirtualLabAuthList";

		_methodParameterTypes159 = new String[] {
				"long", "long", "java.util.Locale"
			};

		_methodName160 = "getVirtualLabClassRegisterList";

		_methodParameterTypes160 = new String[] {
				"long", "long", "long", "long", "java.util.Locale"
			};

		_methodName161 = "getVirtualLabClassRegisterInfo";

		_methodParameterTypes161 = new String[] {
				"long", "long", "long", "java.util.Locale"
			};

		_methodName162 = "getVirtualLabInfomation";

		_methodParameterTypes162 = new String[] { "long", "java.util.Locale" };

		_methodName163 = "updateVirtualLabStatus";

		_methodParameterTypes163 = new String[] { "java.util.Map" };

		_methodName164 = "getListVirtualLab";

		_methodParameterTypes164 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName165 = "getListVirtualLabManagement";

		_methodParameterTypes165 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName166 = "getVirtualLabGroup";

		_methodParameterTypes166 = new String[] {  };

		_methodName167 = "getVirtualLabList";

		_methodParameterTypes167 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName168 = "getVirtualLabTabList";

		_methodParameterTypes168 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName169 = "getCountVirtualLab";

		_methodParameterTypes169 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName170 = "getCountVirtualLabClassRegisterList";

		_methodParameterTypes170 = new String[] {
				"long", "long", "java.util.Locale"
			};

		_methodName171 = "getCountVirtualLabList";

		_methodParameterTypes171 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName172 = "updateVirtualLabInfomation";

		_methodParameterTypes172 = new String[] {
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"javax.portlet.PortletRequest", "long", "long", "java.util.Map",
				"java.util.Locale"
			};

		_methodName173 = "updateVirtualLabDisable";

		_methodParameterTypes173 = new String[] { "long", "java.lang.String" };

		_methodName174 = "transferVirtualLabOwner";

		_methodParameterTypes174 = new String[] { "long", "long", "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return VirtualLabLocalServiceUtil.addVirtualLab((org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return VirtualLabLocalServiceUtil.createVirtualLab(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return VirtualLabLocalServiceUtil.deleteVirtualLab(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return VirtualLabLocalServiceUtil.deleteVirtualLab((org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return VirtualLabLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return VirtualLabLocalServiceUtil.fetchVirtualLab(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLab(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return VirtualLabLocalServiceUtil.updateVirtualLab((org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			VirtualLabLocalServiceUtil.addVirtualLabClassVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			VirtualLabLocalServiceUtil.addVirtualLabClassVirtualLab(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			VirtualLabLocalServiceUtil.addVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			VirtualLabLocalServiceUtil.addVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLab>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			VirtualLabLocalServiceUtil.clearVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteVirtualLabClassVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteVirtualLabClassVirtualLab(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLab>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassVirtualLabsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return VirtualLabLocalServiceUtil.hasVirtualLabClassVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return VirtualLabLocalServiceUtil.hasVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			VirtualLabLocalServiceUtil.setVirtualLabClassVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			VirtualLabLocalServiceUtil.addSurveyVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			VirtualLabLocalServiceUtil.addSurveyVirtualLab(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			VirtualLabLocalServiceUtil.addSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			VirtualLabLocalServiceUtil.addSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLab>)arguments[1]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			VirtualLabLocalServiceUtil.clearSurveyVirtualLabs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteSurveyVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteSurveyVirtualLab(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.VirtualLab)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			VirtualLabLocalServiceUtil.deleteSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.VirtualLab>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getSurveyVirtualLabs(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getSurveyVirtualLabsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return VirtualLabLocalServiceUtil.hasSurveyVirtualLab(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return VirtualLabLocalServiceUtil.hasSurveyVirtualLabs(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			VirtualLabLocalServiceUtil.setSurveyVirtualLabs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName152.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes152, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName153.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes153, parameterTypes)) {
			VirtualLabLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName158.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes158, parameterTypes)) {
			return VirtualLabLocalServiceUtil.addVirtualLab((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.util.Map<java.lang.String, java.lang.String>)arguments[4],
				(java.util.Locale)arguments[5]);
		}

		if (_methodName159.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes159, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabAuthList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName160.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes160, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassRegisterList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.util.Locale)arguments[4]);
		}

		if (_methodName161.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes161, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabClassRegisterInfo(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(), (java.util.Locale)arguments[3]);
		}

		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabInfomation(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName163.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes163, parameterTypes)) {
			return VirtualLabLocalServiceUtil.updateVirtualLabStatus((java.util.Map<java.lang.String, java.lang.String>)arguments[0]);
		}

		if (_methodName164.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes164, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getListVirtualLab((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName165.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes165, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getListVirtualLabManagement((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName166.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes166, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabGroup();
		}

		if (_methodName167.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes167, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName168.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes168, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getVirtualLabTabList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName169.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes169, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getCountVirtualLab((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName170.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes170, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getCountVirtualLabClassRegisterList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		if (_methodName171.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes171, parameterTypes)) {
			return VirtualLabLocalServiceUtil.getCountVirtualLabList((java.util.Map<java.lang.String, java.lang.Object>)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName172.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes172, parameterTypes)) {
			return VirtualLabLocalServiceUtil.updateVirtualLabInfomation((com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[0],
				(javax.portlet.PortletRequest)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.util.Map<java.lang.String, java.lang.String>)arguments[4],
				(java.util.Locale)arguments[5]);
		}

		if (_methodName173.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes173, parameterTypes)) {
			return VirtualLabLocalServiceUtil.updateVirtualLabDisable(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName174.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes174, parameterTypes)) {
			VirtualLabLocalServiceUtil.transferVirtualLabOwner(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());

			return null;
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
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
	private String _methodName152;
	private String[] _methodParameterTypes152;
	private String _methodName153;
	private String[] _methodParameterTypes153;
	private String _methodName158;
	private String[] _methodParameterTypes158;
	private String _methodName159;
	private String[] _methodParameterTypes159;
	private String _methodName160;
	private String[] _methodParameterTypes160;
	private String _methodName161;
	private String[] _methodParameterTypes161;
	private String _methodName162;
	private String[] _methodParameterTypes162;
	private String _methodName163;
	private String[] _methodParameterTypes163;
	private String _methodName164;
	private String[] _methodParameterTypes164;
	private String _methodName165;
	private String[] _methodParameterTypes165;
	private String _methodName166;
	private String[] _methodParameterTypes166;
	private String _methodName167;
	private String[] _methodParameterTypes167;
	private String _methodName168;
	private String[] _methodParameterTypes168;
	private String _methodName169;
	private String[] _methodParameterTypes169;
	private String _methodName170;
	private String[] _methodParameterTypes170;
	private String _methodName171;
	private String[] _methodParameterTypes171;
	private String _methodName172;
	private String[] _methodParameterTypes172;
	private String _methodName173;
	private String[] _methodParameterTypes173;
	private String _methodName174;
	private String[] _methodParameterTypes174;
}