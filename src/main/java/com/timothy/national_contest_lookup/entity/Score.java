package com.timothy.national_contest_lookup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "scores")
public class Score {
    @Id
    @Column(length = 20)
    private String sbd;  // Số báo danh

    @Column(precision = 4, scale = 2)
    private BigDecimal toan;

    @Column(name = "ngu_van", precision = 4, scale = 2)
    private BigDecimal nguVan;

    @Column(name = "ngoai_ngu", precision = 4, scale = 2)
    private BigDecimal ngoaiNgu;

    @Column(name = "vat_li", precision = 4, scale = 2)
    private BigDecimal vatLi;

    @Column(name = "hoa_hoc", precision = 4, scale = 2)
    private BigDecimal hoaHoc;

    @Column(name = "sinh_hoc", precision = 4, scale = 2)
    private BigDecimal sinhHoc;

    @Column(name = "lich_su", precision = 4, scale = 2)
    private BigDecimal lichSu;

    @Column(name = "dia_li", precision = 4, scale = 2)
    private BigDecimal diaLi;

    @Column(precision = 4, scale = 2)
    private BigDecimal gdcd;

    @Column(name = "ma_ngoai_ngu", length = 10)
    private String maNgoaiNgu;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
