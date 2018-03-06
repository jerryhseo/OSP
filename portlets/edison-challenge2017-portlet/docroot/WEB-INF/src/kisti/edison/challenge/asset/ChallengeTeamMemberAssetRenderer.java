package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.ChallengeTeamMember;
import kisti.edison.challenge.service.permission.ChallengeTeamMemberPermission;
import kisti.edison.challenge.service.permission.ChildChallengePermission;
import kisti.edison.challenge.util.ActionKeys;

public class ChallengeTeamMemberAssetRenderer extends BaseAssetRenderer {

	private ChallengeTeamMember _challengeTeamMember;
	
	public ChallengeTeamMemberAssetRenderer(ChallengeTeamMember challengeTeamMember){
		_challengeTeamMember = challengeTeamMember;
	}
	
	
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		long challengeTeamMemberId = _challengeTeamMember.getChallengeTeamMemberId();
		
		boolean contains = false;
		try{
			contains = ChallengeTeamMemberPermission
					.contains(permissionChecker, challengeTeamMemberId, ActionKeys.UPDATE);
		}catch(PortalException pe){
			_log.error(pe.getLocalizedMessage());
		}catch(SystemException se){
			_log.error(se.getLocalizedMessage());
		}
		
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		long challengeTeamMemberId = _challengeTeamMember.getChallengeTeamMemberId();
		
		boolean contains = false;
		
		try{
			contains = ChildChallengePermission
					.contains(permissionChecker, challengeTeamMemberId, ActionKeys.VIEW);
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
		return ChallengeTeamMember.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getChallengeTeamMemberId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getApplyUserName() + "<br>" 
					+_challengeTeamMember.getInstitute(locale) + "<br>"
					+_challengeTeamMember.getMajor(locale);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getApplyUserName();
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _challengeTeamMember.getUuid();
	}

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse, 
			String template) throws Exception {
		// TODO Auto-generated method stub
		
		if(template.equals(TEMPLATE_FULL_CONTENT)){
			renderRequest.setAttribute("challengeTeamMember", _challengeTeamMember);
			
			return "/html/challengeteam/challengeteammemberfullcontent.jsp";
		} else{
			return null;
		}
	}
	private Log _log;
}
