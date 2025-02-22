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

package org.kisti.edison.virtuallaboratory.service.persistence;

/**
 * @author EDISON
 */
public interface VirtualLabUserFinder {
	public java.util.List<java.lang.Object[]> getVirtualClassStudentList(
		long virtualLabId, long classId, long questionSeqNo,
		java.lang.String search_parameter, long groupId);

	public java.lang.Object[] getCountVirtualClassRegisterUserList(long classId);

	public org.kisti.edison.virtuallaboratory.model.VirtualLabUser getVirtualLabUserInfo(
		long classId, long userId);

	public int getStudentCount(long virtualLabId, long classId);

	public java.util.List<java.lang.Object[]> getUserGroupClassUser(
		long userId, long groupId);

	public java.lang.String getVirtualLabClassUserIds(long virtualLabId,
		long classId);

	public java.lang.String getVirtualLabClassScienceAppIds(long virtualLabId,
		long classId);

	public java.util.List<java.lang.Object[]> getVirtualClassStudentManagementList(
		long virtualLabId, long classId, long questionSeqNo,
		java.lang.String search_parameter, long groupId,
		java.lang.String userIds, java.lang.String scienceAppIds);
}