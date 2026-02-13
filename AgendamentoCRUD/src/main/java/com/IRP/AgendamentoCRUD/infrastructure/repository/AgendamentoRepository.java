package com.IRP.AgendamentoCRUD.infrastructure.repository;

import com.IRP.AgendamentoCRUD.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);


    @Transactional
    void deleteByDataHoraAgendametoAndCliente(LocalDateTime dataHoraAgendameto, String cliente);

    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    Agendamento findByDataHoraAgendametoAndCliente(LocalDateTime dataHoraAgendameto, String cliente);

}