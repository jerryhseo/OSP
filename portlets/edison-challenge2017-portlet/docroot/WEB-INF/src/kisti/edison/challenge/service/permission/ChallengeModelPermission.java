package kisti.edison.challenge.service.permission;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class ChallengeModelPermission {
	public static final String RESOURCE_NAME = "kisti.edison.challenge.model";
	
	public static void check(PermissionChecker permissionChecker, long groupId,
			String actionId) throws PortalException{
		if(!contains(permissionChecker, groupId, actionId)) {
			System.out.println("Permission denied : "+ groupId + " : action : " + actionId);
			throw new PrincipalException();
		}
	}
	
	public static boolean contains(PermissionChecker permissionChecker, 
			long groupId, String actionId){
		return permissionChecker.hasPermission(groupId, RESOURCE_NAME, groupId, actionId);
	}
}
