package kisti.edison.challenge.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import kisti.edison.challenge.model.ChallengeEvaluationManagement;
import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.permission.ChallengeEvaluationManagementPermission;
import kisti.edison.challenge.service.permission.ChallengeEvaluationScorePermission;
import kisti.edison.challenge.util.ActionKeys;

public class ChallengeEvaluationScoreAssetRenderer extends BaseAssetRenderer {

	private ChallengeEvaluationScore _challengeEvaluationScore;
	
	public ChallengeEvaluationScoreAssetRenderer(ChallengeEvaluationScore challengeEvaluationScore){
		_challengeEvaluationScore = challengeEvaluationScore;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		
		long challengeEvaluationScoreId = _challengeEvaluationScore.getChallengeEvaluationScoreId();
		
		boolean contains = false;
		
		try {
			contains = ChallengeEvaluationScorePermission.contains(permissionChecker,
					challengeEvaluationScoreId, ActionKeys.UPDATE);
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
		return ChallengeEvaluationScoreAssetRenderer.class.getName();
	}

	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		return _challengeEvaluationScore.getChallengeEvaluationScoreId();
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return _challengeEvaluationScore.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {
		// TODO Auto-generated method stub
		return String.valueOf(_challengeEvaluationScore.getScore());
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return String.valueOf(_challengeEvaluationScore.getScore());
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return _challengeEvaluationScore.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return _challengeEvaluationScore.getUserName();
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return _challengeEvaluationScore.getUuid();
	}

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse, String template) throws Exception {
		// TODO Auto-generated method stub
		if(template.equals(TEMPLATE_FULL_CONTENT)){
			renderRequest.setAttribute("challengeEvaluationScore", _challengeEvaluationScore);
			return "/html/challengeevaluation/challengeevaluationfullcontent.jsp";
		}else{
			return null;			
		}
	}
	private Log _log;

}
