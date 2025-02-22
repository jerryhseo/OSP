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

package org.kisti.edison.multiboard.service.base;

import org.kisti.edison.multiboard.service.BoardDivLocalServiceUtil;

import java.util.Arrays;

/**
 * @author mhkang
 * @generated
 */
public class BoardDivLocalServiceClpInvoker {
	public BoardDivLocalServiceClpInvoker() {
		_methodName0 = "addBoardDiv";

		_methodParameterTypes0 = new String[] {
				"org.kisti.edison.multiboard.model.BoardDiv"
			};

		_methodName1 = "createBoardDiv";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteBoardDiv";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteBoardDiv";

		_methodParameterTypes3 = new String[] {
				"org.kisti.edison.multiboard.model.BoardDiv"
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

		_methodName10 = "fetchBoardDiv";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getBoardDiv";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getBoardDivs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getBoardDivsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateBoardDiv";

		_methodParameterTypes15 = new String[] {
				"org.kisti.edison.multiboard.model.BoardDiv"
			};

		_methodName16 = "addBoardBoardDiv";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addBoardBoardDiv";

		_methodParameterTypes17 = new String[] {
				"long", "org.kisti.edison.multiboard.model.BoardDiv"
			};

		_methodName18 = "addBoardBoardDivs";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addBoardBoardDivs";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearBoardBoardDivs";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteBoardBoardDiv";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteBoardBoardDiv";

		_methodParameterTypes22 = new String[] {
				"long", "org.kisti.edison.multiboard.model.BoardDiv"
			};

		_methodName23 = "deleteBoardBoardDivs";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteBoardBoardDivs";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getBoardBoardDivs";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getBoardBoardDivs";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getBoardBoardDivs";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getBoardBoardDivsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasBoardBoardDiv";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasBoardBoardDivs";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setBoardBoardDivs";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName58 = "getBeanIdentifier";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "setBeanIdentifier";

		_methodParameterTypes59 = new String[] { "java.lang.String" };

		_methodName64 = "getCustomListBoard";

		_methodParameterTypes64 = new String[] {
				"long", "int", "int", "long", "java.lang.String",
				"java.lang.String", "java.util.Locale", "long", "boolean",
				"java.lang.String", "java.lang.String"
			};

		_methodName65 = "getCustomCountBoard";

		_methodParameterTypes65 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "long",
				"java.lang.String"
			};

		_methodName66 = "getAllBoardDivs";

		_methodParameterTypes66 = new String[] {  };

		_methodName67 = "removeAll";

		_methodParameterTypes67 = new String[] {  };

		_methodName68 = "insertBoardDiv";

		_methodParameterTypes68 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return BoardDivLocalServiceUtil.addBoardDiv((org.kisti.edison.multiboard.model.BoardDiv)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return BoardDivLocalServiceUtil.createBoardDiv(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return BoardDivLocalServiceUtil.deleteBoardDiv(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return BoardDivLocalServiceUtil.deleteBoardDiv((org.kisti.edison.multiboard.model.BoardDiv)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return BoardDivLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return BoardDivLocalServiceUtil.fetchBoardDiv(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardDiv(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return BoardDivLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardDivs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardDivsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return BoardDivLocalServiceUtil.updateBoardDiv((org.kisti.edison.multiboard.model.BoardDiv)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			BoardDivLocalServiceUtil.addBoardBoardDiv(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			BoardDivLocalServiceUtil.addBoardBoardDiv(((Long)arguments[0]).longValue(),
				(org.kisti.edison.multiboard.model.BoardDiv)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			BoardDivLocalServiceUtil.addBoardBoardDivs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			BoardDivLocalServiceUtil.addBoardBoardDivs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.multiboard.model.BoardDiv>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			BoardDivLocalServiceUtil.clearBoardBoardDivs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			BoardDivLocalServiceUtil.deleteBoardBoardDiv(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			BoardDivLocalServiceUtil.deleteBoardBoardDiv(((Long)arguments[0]).longValue(),
				(org.kisti.edison.multiboard.model.BoardDiv)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			BoardDivLocalServiceUtil.deleteBoardBoardDivs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			BoardDivLocalServiceUtil.deleteBoardBoardDivs(((Long)arguments[0]).longValue(),
				(java.util.List<org.kisti.edison.multiboard.model.BoardDiv>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardBoardDivs(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardBoardDivs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardBoardDivs(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBoardBoardDivsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return BoardDivLocalServiceUtil.hasBoardBoardDiv(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return BoardDivLocalServiceUtil.hasBoardBoardDivs(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			BoardDivLocalServiceUtil.setBoardBoardDivs(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return BoardDivLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			BoardDivLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return BoardDivLocalServiceUtil.getCustomListBoard(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.util.Locale)arguments[6],
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(),
				(java.lang.String)arguments[9], (java.lang.String)arguments[10]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return BoardDivLocalServiceUtil.getCustomCountBoard(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(), (java.lang.String)arguments[5]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return BoardDivLocalServiceUtil.getAllBoardDivs();
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			BoardDivLocalServiceUtil.removeAll();

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			BoardDivLocalServiceUtil.insertBoardDiv(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue());

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
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
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
}