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

package edison.challenge.service.builder.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChallengeMemberService}.
 *
 * @author kyj
 * @see ChallengeMemberService
 * @generated
 */
public class ChallengeMemberServiceWrapper implements ChallengeMemberService,
	ServiceWrapper<ChallengeMemberService> {
	public ChallengeMemberServiceWrapper(
		ChallengeMemberService challengeMemberService) {
		_challengeMemberService = challengeMemberService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _challengeMemberService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_challengeMemberService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _challengeMemberService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ChallengeMemberService getWrappedChallengeMemberService() {
		return _challengeMemberService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedChallengeMemberService(
		ChallengeMemberService challengeMemberService) {
		_challengeMemberService = challengeMemberService;
	}

	@Override
	public ChallengeMemberService getWrappedService() {
		return _challengeMemberService;
	}

	@Override
	public void setWrappedService(ChallengeMemberService challengeMemberService) {
		_challengeMemberService = challengeMemberService;
	}

	private ChallengeMemberService _challengeMemberService;
}