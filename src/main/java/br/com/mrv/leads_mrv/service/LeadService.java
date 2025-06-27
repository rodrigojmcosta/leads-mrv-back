package br.com.mrv.leads_mrv.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.mrv.leads_mrv.api.dto.MailFileResponse;
import br.com.mrv.leads_mrv.domain.datasource.repository.LeadRepository;
import br.com.mrv.leads_mrv.domain.enums.LeadStatusEnum;
import br.com.mrv.leads_mrv.domain.model.Lead;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadService {

  private final LeadRepository leadRepository;
  private final EmailService emailService;

  public List<Lead> findAllByStatus(LeadStatusEnum status) {
    return leadRepository.findAllByStatus(status);
  }

  public Optional<Lead> findById(Long id) {
    return leadRepository.findById(id);
  }

  public Lead save(Lead lead) {
    return leadRepository.save(lead);
  }

  public MailFileResponse acceptLead(Long id) {
    Lead lead = leadRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + id));
    lead.accept();
    Lead saved = leadRepository.saveAndFlush(lead);
    emailService.sendLeadAcceptedNotification(
      "vendas@test.com",
      "Lead aceito: " + saved.getId(),
      "Lead aceito: " + saved.getContactFirstName() + " " + (saved.getContactLastName() != null ? saved.getContactLastName() : "")
    );
    try {
      byte[] fileBytes = Files.readAllBytes(Paths.get("vendas@test.txt"));
      String b64 = Base64.getEncoder().encodeToString(fileBytes);
      return new MailFileResponse(b64);
    } catch (Exception e) {
      return new MailFileResponse("");
    }
  }

  public Lead declineLead(Long id) {
    Lead lead = leadRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Lead not found: " + id));
    lead.decline();
    return leadRepository.saveAndFlush(lead);
  }
}
