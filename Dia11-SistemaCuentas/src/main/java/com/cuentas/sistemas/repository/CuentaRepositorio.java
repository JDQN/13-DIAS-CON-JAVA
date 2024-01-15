package com.cuentas.sistemas.repository;

import com.cuentas.sistemas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepositorio extends JpaRepository<Cuenta, Integer> {
}

