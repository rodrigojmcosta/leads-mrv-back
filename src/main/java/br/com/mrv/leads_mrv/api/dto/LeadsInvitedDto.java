package br.com.mrv.leads_mrv.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadsInvitedDto {

  private Long id;
  private String contactFirstName;
  private LocalDateTime dateCreated;
  private String suburb;
  private String category;
  private String description;
  private BigDecimal price;

}
