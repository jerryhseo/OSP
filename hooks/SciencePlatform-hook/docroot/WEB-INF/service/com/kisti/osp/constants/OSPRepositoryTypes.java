package com.kisti.osp.constants;

public enum OSPRepositoryTypes {
	USER_HOME("repository"),
	USER_JOBS("jobs"),
	PROVENANCE("provenance"),
	ICECAP("icecap"),
	SCIENCEAPP("scienceapp"),
	SPYGLASS("spyglass");
	
	private OSPRepositoryTypes(String value) {
		this.value = value;
	}
	
	public boolean equal( String type ){
		return this.value.equalsIgnoreCase(type);
	}
	
	public String value(){
		return this.value;
	}
	
	private String value;
}