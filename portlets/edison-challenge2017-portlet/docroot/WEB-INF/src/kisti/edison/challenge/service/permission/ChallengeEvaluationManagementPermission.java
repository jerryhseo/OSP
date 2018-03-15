package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.service.ChallengeEvaluationManagementLocalServiceUtil;

public class ChallengeEvaluationManagementPermission {
	public static void check(PermissionChecker permissionChecker,
			long challengeEvaluationId, String actionId) throws PortalException,
			SystemException{
		if (!contains(permissionChecker, challengeEvaluationId, actionId)) {
			System.out.println("Permission denied : "+ challengeEvaluationId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	

	
	public static boolean contains(PermissionChecker permissionChecker,
			long challengeEvaluationId, String actionId) throws PortalException,
	SystemException {
		ChallengeEvaluationManagement challengeEvaluationManagement = 
				ChallengeEvaluationManagementLocalServiceUtil.getChallengeEvaluationManagement(challengeEvaluationId);

		//permissionChecker.hasOwnerPermission(arg0, arg1, arg2, arg3, arg4)
		
		return permissionChecker.hasPermission(challengeEvaluationManagement.getGroupId(), 
				ChallengeEvaluationManagement.class.getName(), challengeEvaluationManagement.getChallengeEvaluationManagementId(), actionId);
	}
}
