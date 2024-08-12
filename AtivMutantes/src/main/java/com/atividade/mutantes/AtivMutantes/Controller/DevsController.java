package com.atividade.mutantes.AtivMutantes.Controller;


import com.atividade.mutantes.AtivMutantes.Dtos.DevsDto;
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

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/devs")
public class DevsController {

    @Autowired
    DevsRepository devsRepository;

    @Autowired
    MutantRepository mutantRepository;

    @GetMapping
    public ResponseEntity<List<DevsModel>> getAllDevsRecords (){
        return ResponseEntity.status(HttpStatus.OK).body(devsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDevsRecordById (@PathVariable(value = "id") Long id){
        DevsModel devs = devsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        return ResponseEntity.status(HttpStatus.OK).body(devs);
    }

    @GetMapping("/mutant/{name}")
    public ResponseEntity<Object> getDevsRecordByMutantName (@PathVariable(value = "name")String name){
        MutantModel mutant = mutantRepository.findMutantByName(name);
        if (mutant == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutant not found!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(mutant.getDevs());
        }
    }

//    @PostMapping
//    public ResponseEntity<Object> addRecord (@RequestBody @Valid DevsDto devsDto){
//        DevsModel record = new DevsModel();
//        BeanUtils.copyProperties(devsDto, record);
//        record.setMutant(mutantRepository.findMutantByName(devsDto.mutantName()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(devsRepository.save(record));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMutant (@PathVariable(value = "id") Long id, @RequestBody @Valid DevsDto devsDto){
        DevsModel record = devsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        BeanUtils.copyProperties(devsDto, record);
        return ResponseEntity.status(HttpStatus.OK).body(devsRepository.save(record));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteMutant (@PathVariable(value="id") Long id){
        DevsModel record = devsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        devsRepository.deleteById(record.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Record deleted!");
    }

}