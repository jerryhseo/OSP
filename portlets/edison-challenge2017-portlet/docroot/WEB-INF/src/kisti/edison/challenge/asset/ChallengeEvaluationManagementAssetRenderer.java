package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.ChallengeEvaluation;
import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission;
import kisti.edison.challenge.util.ActionKeys;

public class ChallengeEvaluationManagementAssetRenderer extends BaseAssetRenderer {

	private ChallengeEvaluationManagement _challengeEvaluationManagement;
	
	public ChallengeEvaluationManagementAssetRenderer(ChallengeEvaluationManagement challengeEvaluationManagement){
		_challengeEvaluationManagement = challengeEvaluationManagement;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		
		long challengeEvaluationManagementId = _challengeEvaluationManagement.getChallengeEvaluationManagementId();
		
		boolean contains = false;
		
		try {
			contains = ChallengeEvaluationManagementPermission.contains(permissionChecker,
					challengeEvaluationManagementId, ActionKeys.UPDATE);
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
		return ChallengeEvaluationManagementAssetRenderer.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getChallengeEvaluationManagementId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getAssessmentItem(locale) + "<br> Distribution" + _challengeEvaluationManagement.getDistribution();
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getAssessmentItem(locale);
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _challengeEvaluationManagement.getUuid();
	}

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse, String template) throws Exception {
		// TODO Auto-generated method stub
		if(template.equals(TEMPLATE_FULL_CONTENT)){
			renderRequest.setAttribute("challengeEvaluationManagement", _challengeEvaluationManagement);
	
			return "/html/challengeEvaluationManagement/challengeevaluationfullcontent.jsp";
		}else{
			return null;			
		}
	}

	private Log _log;
}
