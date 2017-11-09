package org.kisti.eturb.dbservice.model;

import java.util.List;

@SuppressWarnings("unused")
public class MeshData{
    private int airfoilsCount;
    private List<Long> staggerAngle;
    private List<Long> pitchGap;
    private List<Long> axialGap;
    private List<Airfoil> airfoils;
}

