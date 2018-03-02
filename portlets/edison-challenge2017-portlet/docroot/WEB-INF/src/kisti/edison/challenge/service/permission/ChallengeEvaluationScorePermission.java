package kisti.edison.challenge.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;


public class ChallengeEvaluationScorePermission {
	public static void check(PermissionChecker permissionChecker,
			long challengeEvaluationScoreId, String actionId) throws PortalException,
			SystemException{
		if (!contains(permissionChecker, challengeEvaluationScoreId, actionId)) {
			System.out.println("Permission denied : "+ challengeEvaluationScoreId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	

	
	public static boolean contains(PermissionChecker permissionChecker,
			long challengeEvaluationScoreId, String actionId) throws PortalException,
	SystemException {
		ChallengeEvaluationScore challengeEvaluationScore = 
				ChallengeEvaluationScoreLocalServiceUtil.getChallengeEvaluationScore(challengeEvaluationScoreId);

		//permissionChecker.hasOwnerPermission(arg0, arg1, arg2, arg3, arg4)
		
		return permissionChecker.hasPermission(challengeEvaluationScore.getGroupId(), 
				ChallengeEvaluationScore.class.getName(), challengeEvaluationScore.getChallengeEvaluationScoreId(), actionId);
	}
}
