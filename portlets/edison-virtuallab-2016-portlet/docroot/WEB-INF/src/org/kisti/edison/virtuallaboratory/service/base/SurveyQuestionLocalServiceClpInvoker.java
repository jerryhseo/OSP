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

import org.kisti.edison.virtuallaboratory.service.SurveyQuestionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author EDISON
 * @generated
 */
public class SurveyQuestionLocalServiceClpInvoker {
	public SurveyQuestionLocalServiceClpInvoker() {
		_methodName0 = "addSurveyQuestion";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName1 = "createSurveyQuestion";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSurveyQuestion";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSurveyQuestion";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
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

		_methodName10 = "fetchSurveyQuestion";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSurveyQuestion";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSurveyQuestions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSurveyQuestionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSurveyQuestion";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName16 = "addSurveySurveyQuestion";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addSurveySurveyQuestion";

		_methodParameterTypes17 = new String[] {
				"long",
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName18 = "addSurveySurveyQuestions";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addSurveySurveyQuestions";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearSurveySurveyQuestions";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteSurveySurveyQuestion";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteSurveySurveyQuestion";

		_methodParameterTypes22 = new String[] {
				"long",
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName23 = "deleteSurveySurveyQuestions";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteSurveySurveyQuestions";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getSurveySurveyQuestions";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getSurveySurveyQuestions";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getSurveySurveyQuestions";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getSurveySurveyQuestionsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasSurveySurveyQuestion";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasSurveySurveyQuestions";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setSurveySurveyQuestions";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName32 = "addSurveyAnswerSurveyQuestion";

		_methodParameterTypes32 = new String[] { "long", "long" };

		_methodName33 = "addSurveyAnswerSurveyQuestion";

		_methodParameterTypes33 = new String[] {
				"long",
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName34 = "addSurveyAnswerSurveyQuestions";

		_methodParameterTypes34 = new String[] { "long", "long[][]" };

		_methodName35 = "addSurveyAnswerSurveyQuestions";

		_methodParameterTypes35 = new String[] { "long", "java.util.List" };

		_methodName36 = "clearSurveyAnswerSurveyQuestions";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "deleteSurveyAnswerSurveyQuestion";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "deleteSurveyAnswerSurveyQuestion";

		_methodParameterTypes38 = new String[] {
				"long",
				"org.kisti.edison.virtuallaboratory.model.SurveyQuestion"
			};

		_methodName39 = "deleteSurveyAnswerSurveyQuestions";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "deleteSurveyAnswerSurveyQuestions";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "getSurveyAnswerSurveyQuestions";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "getSurveyAnswerSurveyQuestions";

		_methodParameterTypes42 = new String[] { "long", "int", "int" };

		_methodName43 = "getSurveyAnswerSurveyQuestions";

		_methodParameterTypes43 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName44 = "getSurveyAnswerSurveyQuestionsCount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "hasSurveyAnswerSurveyQuestion";

		_methodParameterTypes45 = new String[] { "long", "long" };

		_methodName46 = "hasSurveyAnswerSurveyQuestions";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "setSurveyAnswerSurveyQuestions";

		_methodParameterTypes47 = new String[] { "long", "long[][]" };

		_methodName152 = "getBeanIdentifier";

		_methodParameterTypes152 = new String[] {  };

		_methodName153 = "setBeanIdentifier";

		_methodParameterTypes153 = new String[] { "java.lang.String" };

		_methodName158 = "insertSurveyQuestion";

		_methodParameterTypes158 = new String[] {
				"java.util.Map", "java.util.Locale"
			};

		_methodName159 = "getSurveyQuestionInfomation";

		_methodParameterTypes159 = new String[] { "long", "java.util.Locale" };

		_methodName160 = "getSurveyQuestionResult";

		_methodParameterTypes160 = new String[] {
				"long", "long", "long", "long", "java.util.Locale"
			};

		_methodName161 = "getSurveyQuestionResult";

		_methodParameterTypes161 = new String[] {
				"long", "long", "long", "long", "java.util.Locale",
				"java.lang.String", "java.lang.String"
			};

		_methodName162 = "getSurveyQuestionSubject";

		_methodParameterTypes162 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "long"
			};

		_methodName163 = "deleteSurveyQuestionList";

		_methodParameterTypes163 = new String[] { "long" };

		_methodName164 = "getQuestionSeqList";

		_methodParameterTypes164 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.addSurveyQuestion((org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.createSurveyQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.deleteSurveyQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.deleteSurveyQuestion((org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.fetchSurveyQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.updateSurveyQuestion((org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveySurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveySurveyQuestion(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.SurveyQuestion>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.clearSurveySurveyQuestions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveySurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveySurveyQuestion(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.SurveyQuestion>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveySurveyQuestions(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveySurveyQuestionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.hasSurveySurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.hasSurveySurveyQuestions(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.setSurveySurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveyAnswerSurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveyAnswerSurveyQuestion(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.addSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.SurveyQuestion>)arguments[1]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.clearSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveyAnswerSurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveyAnswerSurveyQuestion(((Long)arguments[0]).longValue(),
				(org.kisti.edison.virtuallaboratory.model.SurveyQuestion)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.virtuallaboratory.model.SurveyQuestion>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyAnswerSurveyQuestionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.hasSurveyAnswerSurveyQuestion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.hasSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.setSurveyAnswerSurveyQuestions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName152.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes152, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName153.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes153, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName158.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes158, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.insertSurveyQuestion((java.util.Map)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName159.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes159, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestionInfomation(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName160.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes160, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestionResult(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(), (java.util.Locale)arguments[4]);
		}

		if (_methodName161.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes161, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestionResult(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.util.Locale)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6]);
		}

		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getSurveyQuestionSubject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue());
		}

		if (_methodName163.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes163, parameterTypes)) {
			SurveyQuestionLocalServiceUtil.deleteSurveyQuestionList(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName164.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes164, parameterTypes)) {
			return SurveyQuestionLocalServiceUtil.getQuestionSeqList(((Long)arguments[0]).longValue());
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
}