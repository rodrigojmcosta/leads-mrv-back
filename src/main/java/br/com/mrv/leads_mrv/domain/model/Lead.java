package br.com.mrv.leads_mrv.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.mrv.leads_mrv.domain.enums.LeadStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LEAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTACT_FIRST_NAME", nullable = false)
    private String contactFirstName;

    @Column(name = "CONTACT_LAST_NAME")
    private String contactLastName;

    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;

    @Column(name = "DATE_CREATED", nullable = false, updatable = false  )
    private LocalDateTime dateCreated;

    @Column(name = "SUBURB")
    private String suburb;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private LeadStatusEnum status;

    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.status = LeadStatusEnum.INVITED;
    }

    public void accept() {
        if (this.price.compareTo(new BigDecimal("500")) > 0) {
            this.price = this.price.multiply(new BigDecimal("0.90"));
        }
        this.status = LeadStatusEnum.ACCEPTED;
    }

    public void decline() {
        this.status = LeadStatusEnum.DECLINED;
    }
}