package org.kisti.edison.science.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.science.service.persistence.ScienceAppActionableDynamicQuery;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ScienceAppIndexer extends BaseIndexer{

    public static final String[] CLASS_NAMES = {ScienceApp.class.getName()};
    public static final String PORTLET_ID = "edisonappstore2016portlet";

    private static Log log = LogFactoryUtil.getLog(ScienceAppIndexer.class);

    public ScienceAppIndexer(){
        setPermissionAware(false);
    }

    @Override
    public String[] getClassNames(){
        return CLASS_NAMES;
    }

    @Override
    public String getPortletId(){
        return PORTLET_ID;
    }

    @Override
    protected void doDelete(Object obj) throws Exception{
        ScienceApp scienceApp = (ScienceApp) obj;
        deleteDocument(scienceApp.getCompanyId(), scienceApp.getScienceAppId());
    }

    @Override
    protected Document doGetDocument(Object obj) throws Exception{
        log.debug("### ScienceApp INDEXING");
        ScienceApp scienceApp = (ScienceApp) obj;
        Document document = getBaseModelDocument(PORTLET_ID, scienceApp);

        document.addText(Field.ENTRY_CLASS_NAME, ScienceApp.class.getName());
        document.addNumber(Field.CLASS_PK, scienceApp.getScienceAppId());
        document.addText(Field.TITLE, scienceApp.getTitle());

        User user = UserLocalServiceUtil.getUser(scienceApp.getUserId());
        document.addText(Field.USER_NAME, user.getScreenName());

        document.addDate(Field.CREATE_DATE, scienceApp.getCreateDate());
        document.addTextSortable(Field.DESCRIPTION, scienceApp.getName());

        document.addNumber(Field.GROUP_ID, getSiteGroupId(scienceApp.getGroupId()));
        document.addNumber(Field.SCOPE_GROUP_ID, scienceApp.getGroupId());
        document.addNumber(Field.COMPANY_ID, scienceApp.getCompanyId());
        log.debug("### ScienceApp INDEXING DONE");

        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletURL portletURL)
        throws Exception{
        Summary summary = createSummary(document);
        summary.setMaxContentLength(200);
        return summary;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void doReindex(Object obj) throws Exception{
        ScienceApp scienceApp = (ScienceApp) obj;
        Document document = getDocument(scienceApp);
        SearchEngineUtil.updateDocument(getSearchEngineId(), scienceApp.getCompanyId(), document);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception{
        long companyID = GetterUtil.getLong(ids[0]);
        reindexScienceApps(companyID);
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception{
        ScienceApp scienceApp = ScienceAppLocalServiceUtil.getScienceApp(classPK);
        doReindex(scienceApp);
    }

    @Override
    protected String getPortletId(SearchContext searchContext){
        return PORTLET_ID;
    }

    @SuppressWarnings("deprecation")
    protected void reindexScienceApps(long companyId) throws SystemException, PortalException{
        final Collection<Document> documents = new ArrayList<Document>();

        ActionableDynamicQuery actionableDynamicQuery = new ScienceAppActionableDynamicQuery(){
            @Override
            protected void addCriteria(DynamicQuery dynamicQuery){
            }

            @Override
            protected void performAction(Object obj) throws PortalException, SystemException{
                ScienceApp scienceApp = (ScienceApp) obj;
                Document document = getDocument(scienceApp);
                documents.add(document);
            }
        };
        actionableDynamicQuery.setCompanyId(companyId);
        actionableDynamicQuery.performActions();
        SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents);
    }
}
