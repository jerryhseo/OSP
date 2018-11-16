package org.kisti.edison.osp.model;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MeshData{
    private int airfoilsCount;
    private List<Long> staggerAngle;
    private List<Long> pitchGap;
    private List<Long> axialGap;
    private List<Airfoil> airfoils;
    
    public int getAirfoilsCount(){
        return airfoilsCount;
    }
}

