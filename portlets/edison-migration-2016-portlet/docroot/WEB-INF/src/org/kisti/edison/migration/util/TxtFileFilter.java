package org.kisti.edison.migration.util;

import java.io.File;
import java.io.FilenameFilter;

public class TxtFileFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		boolean isAccept = false;
		if(name.trim().toUpperCase().endsWith("TXT")==true){
			isAccept = true;
		}else{
			isAccept = false;
		}
		return isAccept;
	}

}
