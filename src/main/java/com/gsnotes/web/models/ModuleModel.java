package com.gsnotes.web.models;


public class ModuleModel {
	
	
	private Long idModule;
	private String titre;
	private String session;
	
	public ModuleModel() {}
	
	public ModuleModel(Long idModule, String titre, String session) {
		super();
		this.idModule = idModule;
		this.titre = titre;
		this.session = session;
	}
	
	public Long getIdModule() {
		return idModule;
	}
	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
	
	

}
