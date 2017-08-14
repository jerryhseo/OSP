package org.kisti.edison.bestsimulation.portlet.datacollection.Exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DataCollectionException extends PortalException{

	public static final int EXISTS_NAME_VERSION_DATABASE = 1;
	
	public static final int FAIL_VALIDATION_SCIENCE_APP_NAME = 2;
	
	public DataCollectionException(){
		super();
	}
	
	public DataCollectionException(int type){
		_type = type;
	}
	public int getType() {
		return _type;
	}

	private int _type;

	public DataCollectionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataCollectionException(Throwable cause) {
		super(cause);
	}
}


