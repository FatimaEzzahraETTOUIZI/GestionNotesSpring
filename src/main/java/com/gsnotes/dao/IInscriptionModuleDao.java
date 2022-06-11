package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsnotes.bo.InscriptionModule;

public interface IInscriptionModuleDao extends JpaRepository<InscriptionModule, Long> {

}
