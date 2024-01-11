package com.contactos.sistema.repository;

import com.contactos.sistema.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepositorio extends JpaRepository<Contacto, Integer> {

}
