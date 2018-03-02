package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;

public class ChallengePermission {
	public static void check(PermissionChecker permissionChecker,
			long challengeId, String actionId) throws PortalException,
			SystemException{
		if (!contains(permissionChecker, challengeId, actionId)) {
			System.out.println("Permission denied : "+ challengeId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	

	
	public static boolean contains(PermissionChecker permissionChecker,
			long challengeId, String actionId) throws PortalException,
	SystemException {
		Challenge challenge = ChallengeLocalServiceUtil.getChallenge(challengeId);

		//permissionChecker.hasOwnerPermission(arg0, arg1, arg2, arg3, arg4)
		
		return permissionChecker.hasPermission(challenge.getGroupId(), 
				Challenge.class.getName(), challenge.getChallengeId(), actionId);
	}
}
