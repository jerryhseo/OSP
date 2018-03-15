package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.service.permission.ChallengePermission;

public class ChallengeAssetRenderer extends BaseAssetRenderer{

	private Challenge _challenge;
	
	public ChallengeAssetRenderer(Challenge challenge){
		this._challenge = challenge;
	}
		
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		long challengeId = _challenge.getChallengeId();
		
		boolean contains = false;
		
		try{
			contains = ChallengePermission.contains(permissionChecker, 
					challengeId, ActionKeys.UPDATE);
		} catch (PortalException pe){
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se){
			_log.error(se.getLocalizedMessage());
		}
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		long challengeId = _challenge.getChallengeId();
		
		boolean contains = false;
		
		try{
			contains = ChallengePermission.contains(permissionChecker, 
					challengeId, ActionKeys.VIEW);
		} catch (PortalException pe){
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se){
			_log.error(se.getLocalizedMessage());
		}
		return contains;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return Challenge.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _challenge.getChallengeId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _challenge.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		// TODO Auto-generated method stub
		return "Name : "+_challenge.getField(locale);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return _challenge.getField(locale);
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _challenge.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _challenge.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _challenge.getUuid();
	}

	@Override
	public String render(RenderRequest renderRequest, 
			RenderResponse renderResponse, String template) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n\n\nTest Asset Render Method.");
		System.out.println(template);
		System.out.println("/html/challengeadmin/"+template+".jsp");
		System.out.println("====================\n\n\n");
		if(template.equals(TEMPLATE_FULL_CONTENT)){
			renderRequest.setAttribute("challenge_Challenge", _challenge);
			return "/html/challengeadmin/contentview_challenge.jsp";
			//return "/html/challengeadmin/"+template+".jsp";
		}else{
			return null;
		}
		
	}
	
	private Log _log;
}
