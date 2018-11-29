package org.kisti.edison.osp.editor.util;

public enum MeshType{
    AERODYNAMICS_2D("Aerodynamics 2D"), 
    AERODYNAMICS_3D("Aerodynamics 3D"), 
    BLADE_VIBRATION_3D("Blade_vibration 3D");

    final private String viewName;

    private MeshType(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName(){
        return viewName;
    }
}
