package com.gsnotes.web.models;

public class NiveauModel {
	
	private Long niveauId;
	private String alias;
	private String titre;
	
	public NiveauModel(Long niveauId, String alias, String titre) {
		this.niveauId = niveauId;
		this.alias = alias;
		this.titre = titre;
	}
	
	public NiveauModel() {}

	public Long getNiveauId() {
		return niveauId;
	}

	public void setNiveauId(Long niveauId) {
		this.niveauId = niveauId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
}
