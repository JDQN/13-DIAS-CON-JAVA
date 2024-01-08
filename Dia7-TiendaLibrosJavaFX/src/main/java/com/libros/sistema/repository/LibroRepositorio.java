package com.libros.sistema.repository;

import com.libros.sistema.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {

}
