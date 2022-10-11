package com.ada.mapaAstral.service;

import com.ada.mapaAstral.enums.Signo;
import com.ada.mapaAstral.model.MapaQuantico;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MapaAstralService {

    public String buscaSignoPorEnun(LocalDateTime datanascimento) {
        MonthDay monthDayNascimento = MonthDay.of(datanascimento.getMonth(), datanascimento.getDayOfMonth());

        for (Signo signo : Signo.values()) {
            if (isWithinRange(monthDayNascimento, signo.getDataComeco(), signo.getDataFim())) {
                System.out.println(signo.getNome());
                return signo.toString();
            }

        }
        System.out.println("Não tem signo");
        return "Não tem signo!!";

    }

    public String buscaPorSigno(LocalDate datanascimento) {
        MonthDay monthDayNascimento = MonthDay.of(datanascimento.getMonth(), datanascimento.getDayOfMonth());

        MonthDay ariesStartDate = MonthDay.of(3, 21);
        MonthDay ariesEndDate = MonthDay.of(4, 20);

        MonthDay sagitarioStartDate = MonthDay.of(11, 22);
        MonthDay sagitarioEndDate = MonthDay.of(12, 21);

        if (isWithinRange(monthDayNascimento, ariesStartDate, ariesEndDate)) {
            return "Aries";
        } else if (isWithinRange(monthDayNascimento, sagitarioStartDate, sagitarioEndDate)) {
            return "Sagitario";
        }

        return "Não tem signo!!";

    }

    private boolean isWithinRange(MonthDay dataNascimento, MonthDay startDate, MonthDay endDate) {
        return !(dataNascimento.isBefore(startDate) || dataNascimento.isAfter(endDate));
    }

    private boolean isWithinRange(LocalTime horarioDeNascimento, LocalTime startTime, LocalTime endTime) {
        return !(horarioDeNascimento.isBefore(startTime) || horarioDeNascimento.isAfter(endTime));
    }

    public String procurarAscendente(String signo, LocalTime horarioDeNascimento) {
        if ("Aries".equalsIgnoreCase(signo)) {
            if (isWithinRange(horarioDeNascimento, LocalTime.of(18, 31), LocalTime.of(20, 30))) {
                return "escorpião";
            }
        } else if ("sagitario".equalsIgnoreCase(signo)) {
            if (isWithinRange(horarioDeNascimento, LocalTime.of(10, 31), LocalTime.of(12, 30))) {
                return "Peixes";
            }

        }

        return "Ufa, não tem ascendente";
    }

    public String mapaAstral(LocalDateTime dataHoraNascimento, String localDenascimento, String nome) {

            return MapaQuantico.builder()
                .nome(nome)
                .signo(buscaPorSigno(dataHoraNascimento.toLocalDate()))
                .ascendente(buscaPorAcendente(dataHoraNascimento))
                .localNascimento(localDenascimento)
                .horaNascimento(dataHoraNascimento)
                .idade(localizarIdade(dataHoraNascimento))
                .build().toString();
    }

    private Integer localizarIdade(LocalDateTime dataHoraNascimento) {
        Period idade = Period.between(dataHoraNascimento.toLocalDate(), LocalDate.now());

        return idade.getYears();
    }

    private String buscaPorZona(String localDenascimento) {
        if(!ZoneId.getAvailableZoneIds().contains(localDenascimento)){
            return ZoneId.of("America/Sao_Paulo").toString();

       }
        return ZoneId.of(localDenascimento).toString();

    }

    private String formarDataDeNascimento(LocalDateTime dataHoraNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        return formatter.format(dataHoraNascimento);
    }

    private String buscaPorAcendente(LocalDateTime dataHoraNascimento) {

        if (dataHoraNascimento.getYear() > 1976) {
            return procurarAscendente(buscaPorSigno(dataHoraNascimento.toLocalDate()), dataHoraNascimento.toLocalTime().minusHours(2));
        } else if (dataHoraNascimento.getYear() > 1946 && dataHoraNascimento.getYear() < 1975) {
            return procurarAscendente(buscaPorSigno(dataHoraNascimento.toLocalDate()), dataHoraNascimento.toLocalTime().minusHours(2));
        } else {
            return procurarAscendente(buscaPorSigno(dataHoraNascimento.toLocalDate()), dataHoraNascimento.toLocalTime());

        }

    }


    }

