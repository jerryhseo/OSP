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

package kisti.edison.challenge.service.base;

import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;

import java.util.Arrays;

/**
 * @author KYJ
 * @generated
 */
public class ChallengeTeamLocalServiceClpInvoker {
	public ChallengeTeamLocalServiceClpInvoker() {
		_methodName0 = "addChallengeTeam";

		_methodParameterTypes0 = new String[] {
				"kisti.edison.challenge.model.ChallengeTeam"
			};

		_methodName1 = "createChallengeTeam";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteChallengeTeam";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteChallengeTeam";

		_methodParameterTypes3 = new String[] {
				"kisti.edison.challenge.model.ChallengeTeam"
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

		_methodName10 = "fetchChallengeTeam";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchChallengeTeamByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchChallengeTeamByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getChallengeTeam";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getChallengeTeamByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getChallengeTeamByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getChallengeTeams";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getChallengeTeamsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateChallengeTeam";

		_methodParameterTypes19 = new String[] {
				"kisti.edison.challenge.model.ChallengeTeam"
			};

		_methodName80 = "getBeanIdentifier";

		_methodParameterTypes80 = new String[] {  };

		_methodName81 = "setBeanIdentifier";

		_methodParameterTypes81 = new String[] { "java.lang.String" };

		_methodName86 = "getChallengeTeames";

		_methodParameterTypes86 = new String[] { "long" };

		_methodName87 = "getChallengeTeames";

		_methodParameterTypes87 = new String[] { "long", "int" };

		_methodName88 = "getChallengeTeams";

		_methodParameterTypes88 = new String[] { "long", "long" };

		_methodName89 = "getChallengeTeamsAndEvaluationOrder";

		_methodParameterTypes89 = new String[] { "long", "long" };

		_methodName90 = "getChallengeTeamsAndEvaluationOrder";

		_methodParameterTypes90 = new String[] { "long", "long", "int", "int" };

		_methodName93 = "getChallengeTeams";

		_methodParameterTypes93 = new String[] { "long", "long", "int", "int" };

		_methodName94 = "getChallengeTeamsCount";

		_methodParameterTypes94 = new String[] { "long", "long" };

		_methodName95 = "addChallengeTeam";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.util.Map",
				"java.util.Map",
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"java.lang.String", "java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName98 = "updateChallengeTeam";

		_methodParameterTypes98 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Map",
				"java.util.Map", "java.util.Map",
				"com.liferay.portal.kernel.upload.UploadPortletRequest",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName99 = "deleteChallengeTeam";

		_methodParameterTypes99 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName100 = "fileUpload";

		_methodParameterTypes100 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.upload.UploadPortletRequest", "long"
			};

		_methodName101 = "fileDownload";

		_methodParameterTypes101 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"javax.portlet.ResourceRequest",
				"javax.portlet.ResourceResponse"
			};

		_methodName102 = "updateStatus";

		_methodParameterTypes102 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName103 = "getChallengeTeamByMemberUser";

		_methodParameterTypes103 = new String[] { "long", "long", "long" };

		_methodName104 = "getChallengeTeamByMemberUser";

		_methodParameterTypes104 = new String[] { "long", "long", "int", "int" };

		_methodName105 = "getTeamAppList";

		_methodParameterTypes105 = new String[] { "long", "long" };

		_methodName106 = "getTeamAppListString";

		_methodParameterTypes106 = new String[] { "long", "long" };

		_methodName107 = "getTeamAppListStringForWeb";

		_methodParameterTypes107 = new String[] { "long", "long" };

		_methodName108 = "getTeamSimulationNumber";

		_methodParameterTypes108 = new String[] { "long", "long" };

		_methodName109 = "getCPUUseage";

		_methodParameterTypes109 = new String[] { "long", "long" };

		_methodName110 = "isChallengeTeamMember";

		_methodParameterTypes110 = new String[] { "long", "long", "long" };

		_methodName111 = "getUserInfo";

		_methodParameterTypes111 = new String[] {
				"long", "javax.portlet.ResourceRequest",
				"javax.portlet.ResourceResponse"
			};

		_methodName112 = "getUserCurrentTeam";

		_methodParameterTypes112 = new String[] { "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.addChallengeTeam((kisti.edison.challenge.model.ChallengeTeam)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.createChallengeTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.deleteChallengeTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.deleteChallengeTeam((kisti.edison.challenge.model.ChallengeTeam)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.fetchChallengeTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.fetchChallengeTeamByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.fetchChallengeTeamByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeams(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.updateChallengeTeam((kisti.edison.challenge.model.ChallengeTeam)arguments[0]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			ChallengeTeamLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeames(((Long)arguments[0]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeames(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeams(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamsAndEvaluationOrder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeams(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.addChallengeTeam(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.updateChallengeTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[5],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[6],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.deleteChallengeTeam(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.fileUpload(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.upload.UploadPortletRequest)arguments[2],
				((Long)arguments[3]).longValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			ChallengeTeamLocalServiceUtil.fileDownload(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(javax.portlet.ResourceRequest)arguments[3],
				(javax.portlet.ResourceResponse)arguments[4]);

			return null;
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamByMemberUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getChallengeTeamByMemberUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getTeamAppList(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getTeamAppListString(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getTeamAppListStringForWeb(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getTeamSimulationNumber(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getCPUUseage(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.isChallengeTeamMember(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			ChallengeTeamLocalServiceUtil.getUserInfo(((Long)arguments[0]).longValue(),
				(javax.portlet.ResourceRequest)arguments[1],
				(javax.portlet.ResourceResponse)arguments[2]);

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return ChallengeTeamLocalServiceUtil.getUserCurrentTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
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
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
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
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
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
}