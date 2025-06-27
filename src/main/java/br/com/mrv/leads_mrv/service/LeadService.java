package br.com.mrv.leads_mrv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.mrv.leads_mrv.domain.datasource.repository.LeadRepository;
import br.com.mrv.leads_mrv.domain.enums.LeadStatusEnum;
import br.com.mrv.leads_mrv.domain.model.Lead;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadService {

  private final LeadRepository leadRepository;

  public List<Lead> findAllByStatus(LeadStatusEnum status) {
    return leadRepository.findAllByStatus(status);
  }

  public Optional<Lead> findById(Long id) {
    return leadRepository.findById(id);
  }

  public Lead save(Lead lead) {
    return leadRepository.save(lead);
  }

  public Lead acceptLead(Long id) {
    Lead lead = leadRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + id));
    lead.accept();
    return leadRepository.saveAndFlush(lead);
  }

  public Lead declineLead(Long id) {
    Lead lead = leadRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + id));
    lead.decline();
    return leadRepository.saveAndFlush(lead);
  }
}
