package kisti.edison.challenge.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.PermissionChecker;

import kisti.edison.challenge.model.Challenge;
import kisti.edison.challenge.model.ChildChallenge;
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.ChildChallengeLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengePermission;
import kisti.edison.challenge.service.persistence.ChallengeActionableDynamicQuery;
import kisti.edison.challenge.service.persistence.ChildChallengeActionableDynamicQuery;
import kisti.edison.challenge.util.ActionKeys;
import kisti.edison.challenge.util.PortletKeys;

public class ChildChallengeIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = { ChildChallenge.class.getName() };
	
	public static final String PORTLET_ID = PortletKeys.CHALLENGE;
	
	public ChildChallengeIndexer(){
		setPermissionAware(true);
	}
	
	@Override
	public String[] getClassNames() {
		// TODO Auto-generated method stub
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, String entryClassName, long entryClassPK,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		return ChallengePermission.contains(permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChildChallenge childChallenge = (ChildChallenge)obj;
		
		deleteDocument(childChallenge.getCompanyId(), childChallenge.getChildChallengeId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChildChallenge childChallenge = (ChildChallenge)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, childChallenge);
		document.addDate(Field.MODIFIED_DATE, childChallenge.getModifiedDate());
		
		String title;
		Challenge challenge = ChallengeLocalServiceUtil.getChallenge(childChallenge.getChallengeId());
		title = challenge.getField()+childChallenge.getNumber();
		document.addText(Field.TITLE, title);
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(childChallenge.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, childChallenge.getGroupId());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL) throws Exception {
		// TODO Auto-generated method stub
		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChildChallenge childChallenge = (ChildChallenge)obj;
		
		Document document = getDocument(childChallenge);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(),  childChallenge.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
		
		reindexChildChallenges(companyId);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		ChildChallenge childChallenge = ChildChallengeLocalServiceUtil.getChildChallenge(classPK);
		
		doReindex(childChallenge);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}
	
	protected void reindexChildChallenges(long companyId)
		throws PortalException, SystemException{
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChildChallengeActionableDynamicQuery(){

			@Override
			protected void performAction(Object obj) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				ChildChallenge childChallenge = (ChildChallenge)obj;
				
				Document document = getDocument(childChallenge);
				
				documents.add(document);
			}
			
		};
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.performActions();
		
		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId,
				documents);
	}

}
