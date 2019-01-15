package com.kisti.osp.visualizer.portlet.paraview;

import java.io.IOException;

public interface ProcessReadyCallback {
	void onResponse(String jsonString) throws IOException;
}