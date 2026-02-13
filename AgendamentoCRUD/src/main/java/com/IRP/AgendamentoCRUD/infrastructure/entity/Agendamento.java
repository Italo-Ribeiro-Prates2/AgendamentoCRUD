package com.IRP.AgendamentoCRUD.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")


public class Agendamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String servico;
        private LocalDateTime dataHoraAgendamento;
        private String profissional;
        private String cliente;
        private String telefoneCliente;
        private LocalDate dataAgendamento = LocalDate.now();

}
