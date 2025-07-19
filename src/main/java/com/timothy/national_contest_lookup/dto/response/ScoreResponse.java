package com.timothy.national_contest_lookup.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoreResponse {
    private String sbd;  // Số báo danh
    private BigDecimal toan;
    private BigDecimal nguVan;
    private BigDecimal ngoaiNgu;
    private BigDecimal vatLi;
    private BigDecimal hoaHoc;
    private BigDecimal sinhHoc;
    private BigDecimal lichSu;
    private BigDecimal diaLi;
    private BigDecimal gdcd;
    private String maNgoaiNgu;
}
