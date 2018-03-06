package kisti.edison.challenge.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import kisti.edison.challenge.model.ChallengeTeam;
import kisti.edison.challenge.service.ChallengeTeamLocalServiceUtil;

public class ChallengeTeamAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = ChallengeTeam.class.getName();
	public static final String TYPE = "challengeTeam";
	
	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		ChallengeTeam challengeTeam = ChallengeTeamLocalServiceUtil.getChallengeTeam(classPK);
		return new ChallengeTeamAssetRenderer(challengeTeam);
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
	public boolean isLinkable() {
		// TODO Auto-generated method stub
		return _LINKABLE;
	}

	private static final boolean _LINKABLE = true;
}
