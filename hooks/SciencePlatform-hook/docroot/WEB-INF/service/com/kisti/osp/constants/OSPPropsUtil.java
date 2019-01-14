package com.kisti.osp.constants;

import com.liferay.portal.kernel.util.PropsUtil;

public class OSPPropsUtil {
	private static final String OSP_PORTAL_SERVER_ADMIN_ID = "osp.portal.admin.id";
	private static final String OSP_PORTAL_SERVER_USER_GROUP = "osp.portal.server.user.group";
	private static final String OSP_ROOT_DIR_PATH = "osp.root.dir.path";
	private static final String USER_ROOT_DIR_PATH = "osp.user.root.dir.path";
	private static final String SPYGLASS_ROOT_DIR_PATH = "osp.spyglass.root.dir.path";
	private static final String SPYGLASS_APPS_DIR_PATH = "osp.spyglass.apps.dir.path";
	private static final String SPYGLASS_APPS_BIN_DIR = "osp.spyglass.apps.bin.dir";
	private static final String SPYGLASS_APPS_SRC_DIR = "osp.spyglass.apps.src.dir";
	private static final String SPYGLASS_APPS_DATA_DIR = "osp.spyglass.apps.data.dir";
	private static final String PROVENANCE_ROOT_DIR_PATH = "osp.provenance.root.dir.path";
	private static final String ICECAP_ROOT_DIR_PATH = "osp.icecap.root.dir.path";
	private static final String ICEBREAKER_ROOT_DIR_PATH = "osp.icebreaker.root.dir.path";
	private static final String ICEBUG_ROOT_DIR_PATH = "osp.icebug.root.dir.path";
	private static final String MERIDIAN_ROOT_DIR_PATH = "osp.meridian.root.dir.path";
	private static final String IGLOO_ROOT_DIR_PATH = "osp.igloo.root.dir.path";
	
	public static final String OSP_PORTAL_SERVER_ADMIN_ID(){
		return PropsUtil.get(OSP_PORTAL_SERVER_ADMIN_ID);
	}
	
	public static final String OSP_PORTAL_SERVER_USER_GROUP(){
		return PropsUtil.get(OSP_PORTAL_SERVER_USER_GROUP);
	}
	
	public static final String OSP_ROOT_DIR_PATH(){
		return PropsUtil.get(OSP_ROOT_DIR_PATH);
	}
	
	public static final String USER_ROOT_DIR_PATH(){
		return PropsUtil.get(USER_ROOT_DIR_PATH);
	}
	
	public static final String SPYGLASS_ROOT_DIR_PATH(){
		return PropsUtil.get(SPYGLASS_ROOT_DIR_PATH);
	}
	
	public static final String SPYGLASS_APPS_DIR_PATH(){
		return PropsUtil.get(SPYGLASS_APPS_DIR_PATH);
	}
	
	public static final String SPYGLASS_APPS_BIN_DIR(){
		return PropsUtil.get(SPYGLASS_APPS_BIN_DIR);
	}

	public static final String SPYGLASS_APPS_SRC_DIR(){
		return PropsUtil.get(SPYGLASS_APPS_SRC_DIR);
	}
	
	public static final String SPYGLASS_APPS_DATA_DIR(){
		return PropsUtil.get(SPYGLASS_APPS_DATA_DIR);
	}

	public static final String PROVENANCE_ROOT_DIR_PATH(){
		return PropsUtil.get(PROVENANCE_ROOT_DIR_PATH);
	}
	
	public static final String ICECAP_ROOT_DIR_PATH(){
		return PropsUtil.get(ICECAP_ROOT_DIR_PATH);
	}
	
	public static final String ICEBREAKER_ROOT_DIR_PATH(){
		return PropsUtil.get(ICEBREAKER_ROOT_DIR_PATH);
	}
	
	public static final String ICEBUG_ROOT_DIR_PATH(){
		return PropsUtil.get(ICEBUG_ROOT_DIR_PATH);
	}
	
	public static final String MERIDIAN_ROOT_DIR_PATH(){
		return PropsUtil.get(MERIDIAN_ROOT_DIR_PATH);
	}
	
	public static final String IGLOO_ROOT_DIR_PATH(){
		return PropsUtil.get(IGLOO_ROOT_DIR_PATH);
	}
}
