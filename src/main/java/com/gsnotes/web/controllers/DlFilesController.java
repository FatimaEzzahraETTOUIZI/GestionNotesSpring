package com.gsnotes.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IModuleService;
import com.gsnotes.utils.export.ExcelExporterNotes;
import com.gsnotes.web.models.ModuleModel;

@Controller
@RequestMapping("/prof")
public class DlFilesController {
	

	@Autowired
	private IModuleService moduleService; 
	
	@GetMapping("exportNote")
	public String exportNote(@ModelAttribute("moduleModel") ModuleModel moduleModel, Model model) {

		String[] sessions = new String[] {"Normale", "Rattrapage"};
		model.addAttribute("modulesList", moduleService.getAllModules());
		model.addAttribute("sessions" , sessions);
		return "prof/exportNotes";
		
	}

	
	// export des notes par module
	@PostMapping("exportFileNote")
	public void exportFileNote(@ModelAttribute("moduleModel") ModuleModel moduleModel,HttpServletResponse response) throws IOException {
	
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=modules" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		
		Module m = new Module();
		
		List<Module> listModules = moduleService.getAllModules();
		for(Module module : listModules) {
			
			if(module.getIdModule() == moduleModel.getIdModule()) {
				
				// le module choisi par le prof
				m = module;
			}
		}

		List<InscriptionModule> mds = m.getInscriptionModules();

		ExcelExporterNotes excelExporter = moduleService.prepareModuleExport(mds,moduleModel.getIdModule(), moduleModel.getSession());

		excelExporter.exportNote(response);
	}
	


}
