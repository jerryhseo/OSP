package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.service.ChallengeTeamMemberLocalServiceUtil;

public class ChallengeTeamMemberPermission {
	public static void check(PermissionChecker permissionChecker,
			long challengeTeamMemberId, String actionId) 
				throws PrincipalException, PortalException, SystemException{
		if(!contains(permissionChecker, challengeTeamMemberId, actionId)){
			System.out.println("Permission denied : "+ challengeTeamMemberId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	
	public static boolean contains(PermissionChecker permissionChecker,
			long challengeTeamMemberId, String actionId) throws PortalException, SystemException{
		ChallengeTeamMember challengeTeamMember = ChallengeTeamMemberLocalServiceUtil
				.getChallengeTeamMember(challengeTeamMemberId);
		
		return permissionChecker.hasPermission(challengeTeamMember.getGroupId(),
				ChallengeTeamMember.class.getName(), challengeTeamMember.getChallengeTeamMemberId(),
				actionId);
	}
}
