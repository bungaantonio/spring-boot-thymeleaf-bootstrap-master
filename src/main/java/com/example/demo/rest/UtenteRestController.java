package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UtenteDTO;
import com.example.demo.exception.CustomErrorType;
import com.example.demo.repository.UtenteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/utente")
public class UtenteRestController {
    public static final Logger logger = LoggerFactory.getLogger(UtenteRestController.class);

    private UtenteRepository utenteRepository;

    @Autowired
    public void setUtenteRepository(UtenteRepository repository) {
        this.utenteRepository = repository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UtenteDTO>> listarTodosUtente() {
        List<UtenteDTO> utentes = utenteRepository.findAll();

        if (utentes.isEmpty()) {
            return new ResponseEntity<List<UtenteDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UtenteDTO>>(utentes, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtenteByNumeroBI(@PathVariable final Long id) {
        Optional<UtenteDTO> utenteOptional = utenteRepository.findById(id);

        if (utenteOptional.isPresent()) {
            return new ResponseEntity<UtenteDTO>(utenteOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Utente com id " + id + ", não foi encontrado."),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtenteDTO> createUtente(@Valid @RequestBody final UtenteDTO utente) {
        logger.info("Criando utente : {}", utente);
        if (utenteRepository.findByNumeroBI(utente.getNumeroBI()) != null) {
            logger.error("Não foi possível criar este utente. Um utente com o número de identificação {} já existe.",
                    utente.getNumeroBI());
            return new ResponseEntity<UtenteDTO>(
                    new CustomErrorType("Não foi possível criar este utente. O utente com o número de identificação"
                            + utente.getNumeroBI() + " já existe."),
                    HttpStatus.CONFLICT);
        }
        utenteRepository.save(utente);
        return new ResponseEntity<UtenteDTO>(utente, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtenteDTO> updateUtente(@PathVariable final Long id,
            @RequestBody UtenteDTO utente) {

        Optional<UtenteDTO> currentOptional = utenteRepository.findById(id);

        if (currentOptional.isEmpty()) {
            return new ResponseEntity<UtenteDTO>(
                    new CustomErrorType("Não foi possível editar este utente. O utente com o id"
                            + id + " não já existe."),
                    HttpStatus.NOT_FOUND);
        }
        UtenteDTO currentUtente = currentOptional.get();

        currentUtente.setNumeroBI(utente.getNumeroBI());
        currentUtente.setNome(utente.getNome());
        currentUtente.setMorada(utente.getMorada());
        currentUtente.setContacto(utente.getContacto());

        utenteRepository.saveAndFlush(currentUtente);

        return new ResponseEntity<UtenteDTO>(currentUtente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UtenteDTO> deleteUtente(@PathVariable final Long id) {
        Optional<UtenteDTO> utente = utenteRepository.findById(id);
        if (utente.isEmpty()) {
            return new ResponseEntity<UtenteDTO>(
                    new CustomErrorType("Não foi possível remover este utente. O utente com o número de identificação"
                            + id + " não já existe."),
                    HttpStatus.NOT_FOUND);
        }
        utenteRepository.deleteById(id);
        return new ResponseEntity<>(new CustomErrorType("Removido o utente com o id" + id + "."),
                HttpStatus.NO_CONTENT);
    }
}
