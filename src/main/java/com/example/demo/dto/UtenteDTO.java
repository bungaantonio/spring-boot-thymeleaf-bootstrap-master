package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Utentes")
@Getter
@Setter
public class UtenteDTO {

    @Id
    @GeneratedValue
    @Column(name = "UTENTE_ID")
    private Long id;

    @NotEmpty(message = "error.numeroBI.empty")
    @Length(max = 15, message = "error.numeroBI.length")
    @Column(name = "NUMERO_BI")
    private String numeroBI;

    @NotEmpty(message = "error.nome.empty")
    @Length(max = 50, message = "error.nome.length")
    @Column(name = "NOME")
    private String nome;

    @NotEmpty(message = "error.morada.empty")
    @Length(max = 150, message = "error.morada.length")
    @Column(name = "MORADA")
    private String morada;

    @NotEmpty(message = "error.contacto.empty")
    @Length(max = 9, message = "error.contacto.length")
    @Column(name = "CONTACTO")
    private String contacto;

    public String getErrorMessage() {
        return "";
    }
}
