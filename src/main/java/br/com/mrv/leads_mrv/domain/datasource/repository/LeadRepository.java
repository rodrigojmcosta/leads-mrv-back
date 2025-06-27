package br.com.mrv.leads_mrv.domain.datasource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrv.leads_mrv.domain.enums.LeadStatusEnum;
import br.com.mrv.leads_mrv.domain.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

  List<Lead> findAllByStatus(LeadStatusEnum status);

  Optional<Lead> findById(Long id);

}