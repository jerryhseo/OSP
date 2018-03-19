package com.kisti.osp.constants;

import com.liferay.portal.kernel.util.PropsUtil;

public class OSPPropsUtil {
	public static final String OSP_ROOT_DIR_PATH = "osp.root.dir.path";
	public static final String USER_ROOT_DIR_PATH = "osp.user.root.dir.path";
	public static final String SPYGLASS_ROOT_DIR_PATH = "osp.spyglass.root.dir.path";
	public static final String SPYGLASS_APPS_DIR_PATH = "osp.spyglass.apps.dir.path";
	public static final String SPYGLASS_APPS_BIN_DIR = "osp.spyglass.apps.bin.dir";
	public static final String SPYGLASS_APPS_SRC_DIR = "osp.spyglass.apps.src.dir";
	public static final String SPYGLASS_APPS_DATA_DIR = "osp.spyglass.apps.data.dir";
	public static final String PROVENANCE_ROOT_DIR_PATH = "osp.provenance.root.dir.path";
	public static final String ICECAP_ROOT_DIR_PATH = "osp.icecap.root.dir.path";
	public static final String ICEBREAKER_ROOT_DIR_PATH = "osp.icebreaker.root.dir.path";
	public static final String ICEBUG_ROOT_DIR_PATH = "osp.icebug.root.dir.path";
	public static final String MERIDIAN_ROOT_DIR_PATH = "osp.meridian.root.dir.path";
	public static final String IGLOO_ROOT_DIR_PATH = "osp.igloo.root.dir.path";
	
	public static final String getSystemRootDirPath(){
		return PropsUtil.get(OSP_ROOT_DIR_PATH);
	}
	
	public static final String getUserRootDirPath(){
		return PropsUtil.get(USER_ROOT_DIR_PATH);
	}
	
	public static final String getSpyGlassRootDirPath(){
		return PropsUtil.get(SPYGLASS_ROOT_DIR_PATH);
	}
	
	public static final String getSpyGlassAppsDirPath(){
		return PropsUtil.get(SPYGLASS_APPS_DIR_PATH);
	}
	
	public static final String getSpyGlassAppsBinDir(){
		return PropsUtil.get(SPYGLASS_APPS_BIN_DIR);
	}

	public static final String getSpyGlassAppsSrcDir(){
		return PropsUtil.get(SPYGLASS_APPS_SRC_DIR);
	}
	
	public static final String getSpyGlassAppsDataDir(){
		return PropsUtil.get(SPYGLASS_APPS_DATA_DIR);
	}

	public static final String getProvenanceRootDirPath(){
		return PropsUtil.get(PROVENANCE_ROOT_DIR_PATH);
	}
	
	public static final String getIcecapRootDirPath(){
		return PropsUtil.get(ICECAP_ROOT_DIR_PATH);
	}
	
	public static final String getIceBreakerRootDirPath(){
		return PropsUtil.get(ICEBREAKER_ROOT_DIR_PATH);
	}
	
	public static final String getIcebugRootDirPath(){
		return PropsUtil.get(ICEBUG_ROOT_DIR_PATH);
	}
	
	public static final String getMeridianRootDirPath(){
		return PropsUtil.get(MERIDIAN_ROOT_DIR_PATH);
	}
	
	public static final String getIglooRootDirPath(){
		return PropsUtil.get(IGLOO_ROOT_DIR_PATH);
	}
}
