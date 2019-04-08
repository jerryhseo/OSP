package org.kisti.edison.science.portlet.scienceAppstore;

import java.util.Map;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;

public class ScienceAppFriendlyURLMapper extends DefaultFriendlyURLMapper {
	
	@Override
	public boolean isCheckMappingWithPrefix() {
		System.out.println("######");
		return true;
	}
	
	@Override
	public Map<String,String> getDefaultReservedParameters(){
		System.out.println(defaultReservedParameters);
		return defaultReservedParameters;
	}
	
}
