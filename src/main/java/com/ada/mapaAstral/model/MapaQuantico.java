package com.ada.mapaAstral.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MapaQuantico {
    private String nome;
    private String signo;
    private String ascendente;
    private String localNascimento;
    private LocalDateTime horaNascimento;
    private Integer idade;

    @Override
    public String toString() {
        return "MapaQuantico\n" +
                "Nome: " + nome +
                "\nSigno :" + signo +
                "\nAnscendente: " + ascendente +
                "\nLocal Nascimento: " + localNascimento +
                "\nHora Nascimento: " + horaNascimento +
                "\nIdade: " + idade ;
    }
}
