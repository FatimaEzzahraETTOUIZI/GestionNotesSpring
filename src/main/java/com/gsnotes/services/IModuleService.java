package com.gsnotes.services;

import java.util.List;


import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.bo.Module;
import com.gsnotes.utils.export.ExcelExporterNotes;

public interface IModuleService {
	
	public List<Module> getAllModules();
	
	public List<InscriptionModule> getAllInscriptionModules();
	
	public List<Element> getAllelements();
		
	public ExcelExporterNotes prepareModuleExport(List<InscriptionModule> moduleInscriptions, Long idModule, String session) ;

}
