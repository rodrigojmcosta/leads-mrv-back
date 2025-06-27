package br.com.mrv.leads_mrv.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mrv.leads_mrv.api.dto.LeadsAcceptedDto;
import br.com.mrv.leads_mrv.api.dto.LeadsInvitedDto;
import br.com.mrv.leads_mrv.api.mapper.LeadMapper;
import br.com.mrv.leads_mrv.domain.enums.LeadStatusEnum;
import br.com.mrv.leads_mrv.domain.model.Lead;
import br.com.mrv.leads_mrv.service.LeadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "${app.api.base}/leads", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LeadController {

  private final LeadService leadService;
  private final LeadMapper leadMapper;

  @GetMapping("/invited")
  public ResponseEntity<List<LeadsInvitedDto>> getInvitedLeads() {
    List<Lead> leads = leadService.findAllByStatus(LeadStatusEnum.INVITED);
    return ResponseEntity.ok(leadMapper.toInvitedDtoList(leads));
  }

  @GetMapping("/accepted")
  public ResponseEntity<List<LeadsAcceptedDto>> getAcceptedLeads() {
    List<Lead> leads = leadService.findAllByStatus(LeadStatusEnum.ACCEPTED);
    return ResponseEntity.ok(leadMapper.toAcceptedDtoList(leads));
  }

  @PutMapping("/{id}/accept")
  public ResponseEntity<LeadsAcceptedDto> acceptLead(@PathVariable Long id) {
    Lead lead = leadService.acceptLead(id);
    return ResponseEntity.ok(leadMapper.toAcceptedDto(lead));
  }

  @PutMapping("/{id}/decline")
  public ResponseEntity<Void> declineLead(@PathVariable Long id) {
    leadService.declineLead(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<LeadsInvitedDto> getLeadById(@PathVariable Long id) {
    return leadService.findById(id)
        .map(leadMapper::toInvitedDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
