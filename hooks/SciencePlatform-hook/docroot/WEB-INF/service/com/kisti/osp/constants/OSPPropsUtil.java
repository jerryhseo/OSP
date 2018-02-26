package com.kisti.osp.constants;

import com.liferay.portal.kernel.util.PropsUtil;

public class OSPPropsUtil {
	public static final String KEY_SYSTEM_ROOT_DIR_PATH = "osp.system.root.dir.path";
	public static final String KEY_USER_ROOT_DIR_PATH = "osp.user.root.dir.path";
	public static final String KEY_SPYGLASS_ROOT_DIR_PATH = "osp.spyglass.root.dir.path";
	public static final String KEY_SPYGLASS_APPS_DIR_PATH = "osp.spyglass.apps.dir.path";
	public static final String KEY_PROVENANCE_ROOT_DIR_PATH = "osp.provenance.root.dir.path";
	public static final String KEY_ICECAP_ROOT_DIR_PATH = "osp.icecap.root.dir.path";
	public static final String KEY_ICEBREAKER_ROOT_DIR_PATH = "osp.icebreaker.root.dir.path";
	public static final String KEY_ICEBUG_ROOT_DIR_PATH = "osp.icebug.root.dir.path";
	public static final String KEY_MERIDIAN_ROOT_DIR_PATH = "osp.meridian.root.dir.path";
	public static final String KEY_IGLOO_ROOT_DIR_PATH = "osp.igloo.root.dir.path";
	
	public static final String getSystemRootDirPath(){
		return PropsUtil.get(KEY_SYSTEM_ROOT_DIR_PATH);
	}
	
	public static final String getUserRootDirPath(){
		return PropsUtil.get(KEY_USER_ROOT_DIR_PATH);
	}
	
	public static final String getSpyGlassRootDirPath(){
		return PropsUtil.get(KEY_SPYGLASS_ROOT_DIR_PATH);
	}
	
	public static final String getSpyGlassAppsDirPath(){
		return PropsUtil.get(KEY_SPYGLASS_APPS_DIR_PATH);
	}
	
	public static final String getProvenanceRootDirPath(){
		return PropsUtil.get(KEY_PROVENANCE_ROOT_DIR_PATH);
	}
	
	public static final String getIcecapRootDirPath(){
		return PropsUtil.get(KEY_ICECAP_ROOT_DIR_PATH);
	}
	
	public static final String getIceBreakerRootDirPath(){
		return PropsUtil.get(KEY_ICEBREAKER_ROOT_DIR_PATH);
	}
	
	public static final String getIcebugRootDirPath(){
		return PropsUtil.get(KEY_ICEBUG_ROOT_DIR_PATH);
	}
	
	public static final String getMeridianRootDirPath(){
		return PropsUtil.get(KEY_MERIDIAN_ROOT_DIR_PATH);
	}
	
	public static final String getIglooRootDirPath(){
		return PropsUtil.get(KEY_IGLOO_ROOT_DIR_PATH);
	}
}
