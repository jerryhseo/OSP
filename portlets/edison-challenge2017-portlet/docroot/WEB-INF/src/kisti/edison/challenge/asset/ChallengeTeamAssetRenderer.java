package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.service.permission.ChallengeTeamPermission;
import kisti.edison.challenge.util.ActionKeys;

public class ChallengeTeamAssetRenderer extends BaseAssetRenderer{

	private ChallengeTeam _challengeTeam;
	
	public ChallengeTeamAssetRenderer(ChallengeTeam challengeTeam){
		_challengeTeam = challengeTeam;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		
		long challengeTeamId = _challengeTeam.getChallengeTeamId();
		
		boolean contains = false;
		
		try {
			contains = ChallengeTeamPermission.contains(permissionChecker,
					challengeTeamId, ActionKeys.UPDATE);
		} catch (PortalException pe) {
			_log.error(pe.getLocalizedMessage());
		} catch (SystemException se) {
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		
		long challengeTeamId = _challengeTeam.getChallengeTeamId();
		
		boolean contains = false;
		
		try {
			contains = ChallengeTeamPermission.contains(permissionChecker,
					challengeTeamId, ActionKeys.VIEW);
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
		return ChallengeTeamAssetRenderer.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _challengeTeam.getChallengeTeamId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _challengeTeam.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeTeam.getTeamName() + "<br>" + _challengeTeam.getPaperName(locale);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeTeam.getTeamName();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _challengeTeam.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _challengeTeam.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _challengeTeam.getUuid();
	}

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse, 
			String template) throws Exception {
		// TODO Auto-generated method stub
		if(template.equals(TEMPLATE_FULL_CONTENT)){
			renderRequest.setAttribute("challengeTeam", _challengeTeam);
			
			return "/html/challengeteam/challengeteamfullcontent.jsp";
		}else{
			return null;			
		}
	}
	
	private Log _log;

}
