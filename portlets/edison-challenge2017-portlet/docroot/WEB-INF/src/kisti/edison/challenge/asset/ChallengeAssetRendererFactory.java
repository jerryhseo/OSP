package kisti.edison.challenge.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengePermission;

public class ChallengeAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = Challenge.class.getName();
	public static final String TYPE = "challenge";
	
	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		
		Challenge challenge = ChallengeLocalServiceUtil.getChallenge(classPK);
		return new ChallengeAssetRenderer(challenge);
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
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception {
		// TODO Auto-generated method stub
		return ChallengePermission.contains(permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		// TODO Auto-generated method stub
		return _LINKABLE;
	}
	
	
	private static final boolean _LINKABLE = true;

}
