package com.atividade.mutantes.AtivMutantes.Controller;

import com.atividade.mutantes.AtivMutantes.Dtos.MutantAtSchoolDto;
import com.atividade.mutantes.AtivMutantes.Dtos.MutantDto;
import com.atividade.mutantes.AtivMutantes.Dtos.MutantInvitedDto;
import com.atividade.mutantes.AtivMutantes.Model.DevsModel;
import com.atividade.mutantes.AtivMutantes.Model.MutantModel;
import com.atividade.mutantes.AtivMutantes.Repositories.DevsRepository;
import com.atividade.mutantes.AtivMutantes.Repositories.MutantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    MutantRepository mutantRepository;

    @Autowired
    DevsRepository devsRepository;

    @GetMapping
    public ResponseEntity<List<MutantModel>> getAllMutants(){
        return ResponseEntity.status(HttpStatus.OK).body(mutantRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMutantById (@PathVariable(value = "id") UUID id){
        MutantModel mutant = mutantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mutant not found!"));
        return ResponseEntity.status(HttpStatus.OK).body(mutant);
    }

    @GetMapping("/atSchool")
    public ResponseEntity<List<MutantModel>> getMutantAtSchool (){
        return ResponseEntity.status(HttpStatus.OK).body(mutantRepository.atSchoolMutant());
    }

    @GetMapping("/atEspada")
    public ResponseEntity<List<MutantModel>> getMutantAtEspada (){
        return ResponseEntity.status(HttpStatus.OK).body(mutantRepository.atEspadaMutant());
    }

    @PostMapping
    public ResponseEntity<Object> addMutant (@RequestBody @Valid MutantDto mutantDto){
        MutantModel mutant = new MutantModel();
        BeanUtils.copyProperties(mutantDto, mutant);

        //Verifica se o Mutante postado matou mais de 20 Aliens e retorna uma DTO indicando isso
        if (mutant.getAliensDefeated()>20) {
            mutant.setAtEspada(true);
            mutantRepository.save(mutant);
            MutantInvitedDto mInvited = new MutantInvitedDto();
            BeanUtils.copyProperties(mutant, mInvited);

            return ResponseEntity.status(HttpStatus.CREATED).body(mInvited);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(mutantRepository.save(mutant));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMutant (@PathVariable(value = "id") UUID id, @RequestBody @Valid MutantDto mutantDto){
        MutantModel mutant = mutantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mutant not found!"));
        BeanUtils.copyProperties(mutantDto, mutant);
        //Verifica se o Mutante postado matou mais de 20 Aliens e retorna uma DTO indicando isso
        if (mutant.getAliensDefeated()>20) {
            mutant.setAtEspada(true);
            mutantRepository.save(mutant);
            MutantInvitedDto mInvited = new MutantInvitedDto();
            BeanUtils.copyProperties(mutant, mInvited);

            return ResponseEntity.status(HttpStatus.CREATED).body(mInvited);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(mutantRepository.save(mutant));
        }
    }

    //Cria/atualiza um registro sempre que o estado de "atSchool" for alterado
    @PutMapping("/atSchool/{name}")
    public ResponseEntity<Object> updateAtSchool(@PathVariable(value = "name")String name, @RequestBody @Valid MutantAtSchoolDto mutantAtSchoolDto){
        MutantModel mutant = mutantRepository.findMutantByName(name);
        if (mutant == null)                                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found!");

        else if (mutant.getPassword() != "Apocalipse")      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Wrong password!");

        else if (!mutant.getAtSchool() && mutantAtSchoolDto.atSchool()){
                DevsModel record = new DevsModel();
                record.setMutant(mutantRepository.findMutantByName(name));
                record.setEntry(LocalDateTime.now());
                devsRepository.save(record);
                BeanUtils.copyProperties(mutantAtSchoolDto, mutant);
                mutantRepository.save(mutant);
                return ResponseEntity.status(HttpStatus.OK).body("Mutante " + name + " arrived!");

            } else if (mutant.getAtSchool() && !mutantAtSchoolDto.atSchool()) {
                DevsModel record = mutant.getDevs()
                        .stream().max(Comparator.comparingLong(DevsModel::getId))
                        .orElseThrow(() -> new RuntimeException("ERROR"));
                record.setDeparture(LocalDateTime.now());
                devsRepository.save(record);
                BeanUtils.copyProperties(mutantAtSchoolDto, mutant);
                mutantRepository.save(mutant);
                return ResponseEntity.status(HttpStatus.OK).body("Mutant " + name + " left!");

            }else{
            return ResponseEntity.status(HttpStatus.OK).body("Status not changed!");
        }
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteMutant (@PathVariable(value="id") UUID id){
        MutantModel mutant = mutantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mutant not found!"));
        mutantRepository.deleteById(mutant.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Mutant deleted!");
    }

}
