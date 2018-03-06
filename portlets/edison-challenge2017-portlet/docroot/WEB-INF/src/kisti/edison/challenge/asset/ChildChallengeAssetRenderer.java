package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChildChallengePermission;
import kisti.edison.challenge.util.ActionKeys;

public class ChildChallengeAssetRenderer extends BaseAssetRenderer {

	private ChildChallenge _childChallenge;
	
	public ChildChallengeAssetRenderer(ChildChallenge childChallenge){
		_childChallenge = childChallenge;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
		long childChallengeId = _childChallenge.getChildChallengeId();
		
		boolean contains = false;
		
		try{
			contains = ChildChallengePermission.contains(permissionChecker, childChallengeId, ActionKeys.UPDATE);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		long childChallengeId = _childChallenge.getChildChallengeId();
		
		boolean contains = false;
		
		try{
			contains = ChildChallengePermission.contains(permissionChecker, childChallengeId, ActionKeys.VIEW);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return ChildChallenge.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _childChallenge.getChildChallengeId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _childChallenge.getGroupId();
	}

	@Override
	public String getSummary(Locale arg0) {
		// TODO Auto-generated method stub
		return "number : "+_childChallenge.getNumber() + "\n Date : "+
				_childChallenge.getChallengeStartDay() + " - " + _childChallenge.getChallengeEndDay();
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		Challenge challenge = null;
		try {
			challenge = ChallengeLocalServiceUtil.getChallenge(_childChallenge.getChallengeId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challenge.getField(locale)+ " : " + _childChallenge.getNumber();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _childChallenge.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _childChallenge.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _childChallenge.getUuid();
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
			renderRequest.setAttribute("challenge_ChildChallenge", _childChallenge);
			return "/html/challengeadmin/contentview_childchallenge.jsp";
			//return "/html/challenge/"+template+".jsp";
		}else{
			return null;
		}
	}

	private Log _log;
}
