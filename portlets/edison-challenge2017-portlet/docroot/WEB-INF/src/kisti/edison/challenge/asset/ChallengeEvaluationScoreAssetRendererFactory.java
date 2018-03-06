package kisti.edison.challenge.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import kisti.edison.challenge.model.ChallengeEvaluationScore;
import kisti.edison.challenge.service.ChallengeEvaluationScoreLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengeEvaluationScorePermission;


public class ChallengeEvaluationScoreAssetRendererFactory extends BaseAssetRendererFactory{

	public static final String CLASS_NAME = ChallengeEvaluationScore.class.getName();
	public static final String TYPE = "challengeEvaluationScore";
	
	
	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		ChallengeEvaluationScore challengeEvaluationScore = 
				ChallengeEvaluationScoreLocalServiceUtil.getChallengeEvaluationScore(classPK);
		
		return new ChallengeEvaluationScoreAssetRenderer(challengeEvaluationScore);
	}
	

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}
	
	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return ChallengeEvaluationScorePermission.contains(permissionChecker, classPK, actionId);
	}
	
	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}
	
	private static final boolean _LINKABLE = true;

}
