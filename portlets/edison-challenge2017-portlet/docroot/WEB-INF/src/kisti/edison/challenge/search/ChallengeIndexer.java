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
import kisti.edison.challenge.service.ChallengeLocalServiceUtil;
import kisti.edison.challenge.service.permission.ChallengePermission;
import kisti.edison.challenge.service.persistence.ChallengeActionableDynamicQuery;
import kisti.edison.challenge.util.ActionKeys;
import kisti.edison.challenge.util.PortletKeys;

public class ChallengeIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = { Challenge.class.getName() };
	
	public static final String PORTLET_ID = PortletKeys.CHALLENGE_ADMIN;
	
	public ChallengeIndexer(){
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
		Challenge challenge = (Challenge)obj;
		
		deleteDocument(challenge.getCompanyId(), challenge.getChallengeId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Challenge challenge = (Challenge)obj;
		
		Document document = getBaseModelDocument(PORTLET_ID, challenge);
		document.addDate(Field.MODIFIED_DATE, challenge.getModifiedDate());
		document.addText(Field.TITLE, challenge.getField());
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(challenge.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, challenge.getGroupId());
		
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
		Challenge challenge = (Challenge)obj;
		
		Document document = getDocument(challenge);
		
		SearchEngineUtil.updateDocument(getSearchEngineId(), 
				challenge.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		long companyId = GetterUtil.getLong(ids[0]);
		
		reindexChildChallenges(companyId);
	}
	
	protected void reindexChildChallenges(long companyId) 
			throws PortalException, SystemException {
		final Collection<Document> documents = new ArrayList<Document>();
		
		ActionableDynamicQuery actionableDynamicQuery = new ChallengeActionableDynamicQuery(){

			@Override
			protected void performAction(Object obj) throws PortalException, SystemException {
				// TODO Auto-generated method stub
				Challenge challenge = (Challenge)obj;
				
				Document document = getDocument(challenge);
				
				documents.add(document);
			}
			
		};
		
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.performActions();
		
		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		// TODO Auto-generated method stub
		Challenge challenge = ChallengeLocalServiceUtil.getChallenge(classPK);
		
		doReindex(challenge);
	}

	@Override
	protected String getPortletId(SearchContext arg0) {
		// TODO Auto-generated method stub
		return PORTLET_ID;
	}

}
