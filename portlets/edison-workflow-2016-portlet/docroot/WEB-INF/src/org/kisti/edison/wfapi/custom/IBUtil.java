package org.kisti.edison.wfapi.custom;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.kisti.edison.model.EdisonExpando;
import org.kisti.edison.model.EdisonRoleConstants;
import org.kisti.edison.model.IcebreakerVcToken;
import org.kisti.edison.util.TokenProviderUtil;
import org.springframework.util.StringUtils;

import com.kisti.osp.constants.OSPPropsUtil;
import com.kisti.osp.service.OSPFileLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;

public class IBUtil{
    
    public static String getFileId(String path) {
        if(!StringUtils.hasText(path)) {
            return null;
        }
        return Base64.encode(path.getBytes());
    }
    
    public static String saveFileContent(String screenName, String target, String content, String repositoryType) 
        throws PortalException, SystemException, IOException {
        Path targetPath = OSPFileLocalServiceUtil.getRepositoryPath(screenName, target, repositoryType);
        Path targetFolder = targetPath.getParent();
        String owner = screenName;
        if(Files.notExists(targetFolder)){
            Files.createDirectories(targetFolder);
        }

        OpenOption[] openOptions = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING};
        Files.write(targetPath, content.getBytes(StandardCharsets.UTF_8), openOptions);
        if(!System.getProperty("os.name").toLowerCase().contains("windows")){
            OSPFileLocalServiceUtil.changeFileOwner(screenName, targetFolder.toString(), owner, repositoryType);
            OSPFileLocalServiceUtil.changeFileMode(screenName, targetFolder.toString(), "g+w", repositoryType);
        }

        return targetPath != null ? targetPath.toString() : null;
    }
    
    public static String copyDLEntryFile(
        long srcDLEntryId, String targetScreenName, String target, String targetRepository, boolean overwrite) 
            throws IOException, PortalException, SystemException{
        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(srcDLEntryId);
        InputStream stream = null;

        stream = fileEntry.getContentStream();
        Path targetPath = OSPFileLocalServiceUtil.getRepositoryPath(targetScreenName, target, targetRepository);
        Path targetFolder = targetPath.getParent();
        String owner = targetScreenName;
        if(!Files.exists(targetFolder)){
            Files.createDirectories(targetFolder);
        }

        Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        if(!System.getProperty("os.name").toLowerCase().contains("windows")){
            OSPFileLocalServiceUtil.changeFileOwner(targetScreenName, targetFolder.toString(), owner, targetRepository);
            OSPFileLocalServiceUtil.changeFileMode(targetScreenName, targetFolder.toString(), "g+w", targetRepository);
        }

        if(Validator.isNotNull(stream))
            stream.close();

        return targetPath != null ? targetPath.toString() : null;
    }

    public static IcebreakerVcToken createExpandoUserVctoken(User user, long thisGroupId, String userScreenName,
        String userPassword)
        throws SystemException, MalformedURLException, PortalException, IOException, ParseException{

        IcebreakerVcToken icebreakerVcToken = new IcebreakerVcToken();

        // icebreaker 가입이 성공한 경우 신규 토큰 발행 및 커스텀 필드 추가
        icebreakerVcToken = TokenProviderUtil.getVcToken(thisGroupId, userScreenName, userPassword);
        icebreakerVcToken.setVcToken(icebreakerVcToken.getVcToken());
        icebreakerVcToken.setVcTokenExpired(icebreakerVcToken.getVcTokenExpired());

        if(!user.getExpandoBridge().hasAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId))){

            user.getExpandoBridge().addAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId), false);
            user.getExpandoBridge().addAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId),
                false);

            ExpandoColumn toKenColumn = ExpandoColumnLocalServiceUtil.getColumn(user.getExpandoBridge().getCompanyId(),
                user.getExpandoBridge().getClassName(), ExpandoTableConstants.DEFAULT_TABLE_NAME,
                EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId));
            setExpandoPermissions(user.getExpandoBridge().getCompanyId(), toKenColumn);

            ExpandoColumn expiredColumn = ExpandoColumnLocalServiceUtil.getColumn(
                user.getExpandoBridge().getCompanyId(), user.getExpandoBridge().getClassName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME,
                EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId));
            setExpandoPermissions(user.getExpandoBridge().getCompanyId(), expiredColumn);
        }

        user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN + String.valueOf(thisGroupId),
            icebreakerVcToken.getVcToken());
        user.getExpandoBridge().setAttribute(EdisonExpando.USER_VC_TOKEN_EXPIRED + String.valueOf(thisGroupId),
            icebreakerVcToken.getVcTokenExpired());
        return icebreakerVcToken;
    }

    public static void setExpandoPermissions(long companyId, ExpandoColumn column) throws SystemException{

        try{

            Role userRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.USER);
            Role adminRole = RoleLocalServiceUtil.getRole(companyId, EdisonRoleConstants.ADMINISTRATOR);

            if(userRole != null && adminRole != null){
                // define actions
                String[] actionIds = new String[]{ActionKeys.VIEW, ActionKeys.UPDATE};

                Map<Long, String[]> map = new HashMap();
                map.put(userRole.getRoleId(), actionIds);
                map.put(adminRole.getRoleId(), actionIds);

                // set the permission
                ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, ExpandoColumn.class.getName(),
                    ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(column.getColumnId()), map);

            }
        }catch (PortalException pe){
        }
    }
}
