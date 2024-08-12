package com.atividade.mutantes.AtivMutantes.Repositories;

import com.atividade.mutantes.AtivMutantes.Model.DevsModel;
import com.atividade.mutantes.AtivMutantes.Model.MutantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevsRepository extends JpaRepository<DevsModel, Long> {
}
