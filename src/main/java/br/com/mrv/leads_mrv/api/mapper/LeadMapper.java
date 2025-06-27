package br.com.mrv.leads_mrv.api.mapper;

import br.com.mrv.leads_mrv.api.dto.LeadsAcceptedDto;
import br.com.mrv.leads_mrv.api.dto.LeadsInvitedDto;
import br.com.mrv.leads_mrv.domain.model.Lead;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class LeadMapper {

  public LeadsInvitedDto toInvitedDto(Lead lead) {
    return LeadsInvitedDto.builder()
        .id(lead.getId())
        .contactFirstName(lead.getContactFirstName())
        .dateCreated(lead.getDateCreated())
        .suburb(lead.getSuburb())
        .category(lead.getCategory())
        .description(lead.getDescription())
        .price(lead.getPrice())
        .build();
  }

  public List<LeadsInvitedDto> toInvitedDtoList(List<Lead> leads) {
    return leads.stream().map(this::toInvitedDto).collect(Collectors.toList());
  }

  public LeadsAcceptedDto toAcceptedDto(Lead lead) {
    String fullName = (lead.getContactFirstName() != null ? lead.getContactFirstName() : "") +
        (lead.getContactLastName() != null ? (" " + lead.getContactLastName()) : "");
    return LeadsAcceptedDto.builder()
        .id(lead.getId())
        .contactFullName(fullName.trim())
        .contactPhone(lead.getContactPhone())
        .contactEmail(lead.getContactEmail())
        .dateCreated(lead.getDateCreated())
        .suburb(lead.getSuburb())
        .category(lead.getCategory())
        .description(lead.getDescription())
        .price(lead.getPrice())
        .build();
  }

  public List<LeadsAcceptedDto> toAcceptedDtoList(List<Lead> leads) {
    return leads.stream().map(this::toAcceptedDto).collect(Collectors.toList());
  }
}
