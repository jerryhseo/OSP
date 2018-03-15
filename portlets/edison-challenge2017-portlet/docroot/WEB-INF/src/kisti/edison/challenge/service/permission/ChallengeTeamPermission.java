package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;

public class ChallengeTeamPermission {

	public static void check(PermissionChecker permissionChecker,
			long challengeTeamId, String actionId)throws PortalException,
			SystemException {
		if(!contains(permissionChecker, challengeTeamId, actionId)){
			System.out.println("Permission denied : "+ challengeTeamId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	
	public static boolean contains(PermissionChecker permissionChecker, 
			long challengeTeamId, String actionId) 
			throws PortalException, SystemException{
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(challengeTeamId);
		
		
		
		return permissionChecker.hasPermission(challengeTeam.getGroupId(), 
				ChallengeTeam.class.getName(),
				challengeTeam.getChallengeTeamId(), actionId);
	}
	
	
}
