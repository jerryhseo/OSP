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

package edison.challenge.service.builder.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;

import edison.challenge.service.builder.model.Agency;
import edison.challenge.service.builder.model.ChildChallenge;
import edison.challenge.service.builder.service.base.AgencyLocalServiceBaseImpl;

/**
 * The implementation of the agency local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link edison.challenge.service.builder.service.AgencyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author kyj
 * @see edison.challenge.service.builder.service.base.AgencyLocalServiceBaseImpl
 * @see edison.challenge.service.builder.service.AgencyLocalServiceUtil
 */
public class AgencyLocalServiceImpl extends AgencyLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link edison.challenge.service.builder.service.AgencyLocalServiceUtil} to access the agency local service.
	 */
	

	@Override
	public List<Agency> getAgencyByChild(long childID) throws SystemException {
		// TODO Auto-generated method stub
		return this.agencyPersistence.findBychildCollect(childID);
	}

	@Override
	public int getAgencyByChildCount(long childID) throws SystemException {
		// TODO Auto-generated method stub
		return this.agencyPersistence.findBychildCollect(childID).size();
	}
}