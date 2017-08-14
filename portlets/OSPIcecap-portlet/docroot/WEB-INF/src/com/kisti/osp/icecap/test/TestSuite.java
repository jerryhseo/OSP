package com.kisti.osp.icecap.test;

import java.util.Map;

import javax.portlet.ActionRequest;

import com.kisti.osp.icecap.NoSuchDataTypeException;
import com.kisti.osp.icecap.model.DataType;
import com.kisti.osp.icecap.service.DataTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;

public class TestSuite {

	public static void modifyDataTypeTest(){
		// TODO Auto-generated method stub
		try {
			ServiceContext sc = null;
			ActionRequest actionRequest = null;
			try {
				sc = ServiceContextFactory.getInstance(DataType.class.getName(), actionRequest);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<String, Object> resMap = DataTypeLocalServiceUtil.modifyDataTypeObject(21202, null, null, null, null, null, null, null, null, sc);
			if(resMap != null){
				System.out.println("size: " + resMap.size());
			}
			
		} catch (NoSuchDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		modifyDataTypeTest();
	}

}
