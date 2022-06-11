package com.gsnotes.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.IElementDao;
import com.gsnotes.dao.IInscriptionModuleDao;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IModuleService;
import com.gsnotes.utils.export.ExcelExporterNotes;

@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {
	
	@Autowired
	private IModuleDao moduleDao;
	
	@Autowired
	private IElementDao elementDao;
	
	
	@Autowired
	private IInscriptionModuleDao inscriptionmoduleDao;

	
	public List<Module> getAllModules() {
		// TODO Auto-generated method stub
		return moduleDao.findAll();
	}
	
	@Override
	public List<InscriptionModule> getAllInscriptionModules() {
		// TODO Auto-generated method stub
		return inscriptionmoduleDao.findAll();
	}

	@Override
	public List<Element> getAllelements() {
		// TODO Auto-generated method stub
		return elementDao.findAll();
	}
	
	@Override
	public ExcelExporterNotes prepareModuleExport(List<InscriptionModule> moduleInscriptions, Long idModule, String session) {


		int nElements = moduleInscriptions.get(0).getModule().getElements().size();
		int nDataColumns = nElements+6;		
		String[] columnNames = new String[nDataColumns];	
		String[][] headerData = new String[2][6];	
		
		Module m = new Module();
		
		List<Module> listModules = moduleDao.findAll();
		for(Module module : listModules) {
			if(module.getIdModule() == idModule)
			{
				m = module;
			}
		}
		
		headerData[0][0] = "Module";
		headerData[0][1] = m.getTitre();
		headerData[0][2] = "Semestre";
		headerData[0][3] = "Printemps";			
		headerData[0][4] = "Année";
		int y = moduleInscriptions.get(0).getInscriptionAnnuelle().getAnnee();
		headerData[0][5] = y+" / "+(y+1);
		
		headerData[1][0] = "Enseignant";
		headerData[1][1] = "Boudaa";
		headerData[1][2] = "Session";
		headerData[1][3] = session;		
		headerData[1][4] = "Classe";
		String niv = moduleInscriptions.get(0).getModule().getNiveau().getAlias();
		headerData[1][5] = niv;		
		
		
		columnNames[0] = "ID";
		columnNames[1] = "CNE";
		columnNames[2] = "NOM";
		columnNames[3] = "PRENOM";
		

		for(int j = 0 ; j < nElements ; j++) {

			List<Element> elements = moduleInscriptions.get(0).getModule().getElements();
				columnNames[j+4] = elements.get(j).getNom();
		}
		
		columnNames[nElements+4] = "MOYENNE";
		columnNames[nElements+5] = "VALIDATION";		
		
				
		List<InscriptionModule> moduleR = new ArrayList<InscriptionModule>();
		List<InscriptionModule> moduleV = new ArrayList<InscriptionModule>();

		for (InscriptionModule im : moduleInscriptions) {
			
			if(im.getNoteSN() < 12.0 && im.getNoteSN() > 0.0) {
				
				// liste des étudiants qui ont passé la session rattrapage
				moduleR.add(im);
			}
			else if(im.getNoteSN() >= 12.0 || im.getNoteSN() == 0.0){
				
				// liste des étudiants qui ont passé la session normale

				moduleV.add(im);
			}
		}
		
				
		
		if(session.equals("Rattrapage")) 
		{	
			int nDataRows = moduleR.size();
			String[][] data = new String[nDataRows][nDataColumns];

			int i = 0;
			
			for (InscriptionModule im : moduleR) 
			{		
					
					data[i][0] = String.valueOf(im.getInscriptionAnnuelle().getEtudiant().getIdUtilisateur());
					data[i][1] = im.getInscriptionAnnuelle().getEtudiant().getCne();
					data[i][2] = im.getInscriptionAnnuelle().getEtudiant().getNom();
					data[i][3] = im.getInscriptionAnnuelle().getEtudiant().getPrenom();
					
					for(int k = 4 ; k < nElements+2 ; k++)
					{
						data[i][k] = " ";
					}
					
					i++;
				
			}
			
			return new ExcelExporterNotes(columnNames, data, "modules",headerData);
			
		}
		else {
			
			int nDataRows = moduleV.size();	
			String[][] data = new String[nDataRows][nDataColumns];

			int j=0;
			
			for (InscriptionModule im : moduleV) 
			{					
					data[j][0] = String.valueOf(im.getInscriptionAnnuelle().getEtudiant().getIdUtilisateur());
					data[j][1] = im.getInscriptionAnnuelle().getEtudiant().getCne();
					data[j][2] = im.getInscriptionAnnuelle().getEtudiant().getNom();
					data[j][3] = im.getInscriptionAnnuelle().getEtudiant().getPrenom();
					
					for(int k = 4 ; k < nElements+2 ; k++) 
					{
						data[j][k] = " ";
					}
					
					j++;				
			}		
			
			return new ExcelExporterNotes(columnNames, data, "modules",headerData);

		}
		}
	
}

