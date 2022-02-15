package farias.eugene.patrick.butecoonline.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farias.eugene.patrick.butecoonline.domain.model.Componente;

@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long> {

}
