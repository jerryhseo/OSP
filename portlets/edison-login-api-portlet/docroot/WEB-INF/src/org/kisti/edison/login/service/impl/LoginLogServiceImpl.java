/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.kisti.edison.login.service.impl;

import java.util.Map;

import org.kisti.edison.login.service.base.LoginLogServiceBaseImpl;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the login log remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.kisti.edison.login.service.LoginLogService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author yjpark
 * 
 * @see org.kisti.edison.login.service.base.LoginLogServiceBaseImpl
 * @see org.kisti.edison.login.service.LoginLogServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL, value = "users")
public class LoginLogServiceImpl extends LoginLogServiceBaseImpl{
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link
     * org.kisti.edison.login.service.LoginLogServiceUtil} to access the login
     * log remote service.
     */
    
    private final static long USER_COMPANY_ID = 20154;

    @AccessControlled(guestAccessEnabled = true)
    @JSONWebService(method = "POST", value = "user-info")
    public JSONObject loginInfo(String screenName, String password){
        JSONObject userInfo = JSONFactoryUtil.createJSONObject();
        if(!StringUtils.hasText(password)){
            userInfo.put("isValid", false);
            userInfo.put("message", "Please Insert Password");
            return userInfo;
        }
        User user = null;
        String failMessage = "";
        boolean isValidPassword = false;
        try{
            user = UserLocalServiceUtil.getUserByScreenName(USER_COMPANY_ID, screenName);
            int authenticateByScreenName = UserLocalServiceUtil.authenticateByScreenName(USER_COMPANY_ID, screenName,
                password, null, null, null);
            isValidPassword = authenticateByScreenName == 1 ? true : false;
            if(!isValidPassword){
                failMessage = "Invalid password";
            }else{
                userInfo.put("user", 
                    JSONFactoryUtil.createJSONObject(userModelToJsonString(user)) );
            }
        }catch (NoSuchUserException nue){
            isValidPassword = false;
            failMessage = "No such user with screen name " + screenName;
        }catch (PortalException | SystemException e){
            e.printStackTrace();
            isValidPassword = false;
            failMessage = "System failure, Contact your system administrator";
        }

        if(isValidPassword){
            userInfo.put("isValid", true);
            return userInfo;
        }else{
            userInfo.put("isValid", false);
            userInfo.put("message", failMessage);
            return userInfo;
        }
    }

    private String userModelToJsonString(User user){
        Map<String, Object> userMap = user.getModelAttributes();
        userMap.remove("reminderQueryQuestion");
        userMap.remove("reminderQueryAnswer");
        userMap.remove("digest");
        userMap.remove("password");
        userMap.remove("passwordReset");
        userMap.remove("passwordModifiedDate");
        userMap.remove("passwordEncrypted");
        Gson json = new GsonBuilder().create();
        return json.toJson(userMap);
    }
}