package br.com.mrv.leads_mrv.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailFileResponse {
    private String mailFileB64;
}
