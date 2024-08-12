package com.atividade.mutantes.AtivMutantes.Repositories;

import com.atividade.mutantes.AtivMutantes.Model.MutantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MutantRepository extends JpaRepository<MutantModel, UUID> {

    @Query(value = "SELECT * FROM mutant m WHERE m.name ilike :name", nativeQuery = true)
    MutantModel findMutantByName (@Param("name") String name);

    @Query(value = "SELECT * FROM mutant m WHERE m.at_school = true", nativeQuery = true)
    List<MutantModel> atSchoolMutant();

    @Query(value = "SELECT * FROM mutant m WHERE m.at_espada = true", nativeQuery = true)
    List<MutantModel> atEspadaMutant();
}
