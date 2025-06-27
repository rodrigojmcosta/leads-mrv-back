package br.com.mrv.leads_mrv.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LeadStatusEnumConverter implements AttributeConverter<LeadStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(LeadStatusEnum status) {
        return status != null ? String.valueOf(status.getValue()) : null;
    }

    @Override
    public LeadStatusEnum convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        return LeadStatusEnum.getByValue(dbData.charAt(0));
    }
}