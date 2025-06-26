package br.com.mrv.leads_mrv.domain.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LeadStatusEnum {

  INVITED('I', "Invited"),
  ACCEPTED('A', "Accepted"),
  DECLINED('D', "Declined");

  private final char value;
  private final String description;

  public static LeadStatusEnum getByValue(Character value) {
    return Arrays.stream(LeadStatusEnum.values())
        .filter(leadStatusEnum -> value.equals(leadStatusEnum.getValue()))
        .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid LeadStatusEnum value: " + value));
  }
}
