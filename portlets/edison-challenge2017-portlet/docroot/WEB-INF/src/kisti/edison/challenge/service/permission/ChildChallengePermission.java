package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;

public class ChildChallengePermission {
	public static void check(PermissionChecker permissionChecker,
			long childChallengeId, String actionId) throws PortalException,
	SystemException {
		if(!contains(permissionChecker, childChallengeId, actionId)){
			System.out.println("Permission denied : "+ childChallengeId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	
	public static boolean contains(PermissionChecker permissionChecker, 
			long childChallengeId, String actionId)
					throws PortalException, SystemException {
		
		ChildChallenge childChallenge = ChildChallengeLocalServiceUtil
				.getChildChallenge(childChallengeId);
		
		return permissionChecker.hasPermission(childChallenge.getGroupId(), 
				ChildChallenge.class.getName(), 
				childChallenge.getChildChallengeId(), actionId);	
	}

}
