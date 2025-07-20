package com.timothy.national_contest_lookup.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TOP10AResponse {
    String sbd;           // Số báo danh
    BigDecimal toan;      // Điểm Toán
    BigDecimal vatLi;     // Điểm Vật lí
    BigDecimal hoaHoc;    // Điểm Hóa học
    BigDecimal tongDiem;  // Tổng điểm khối A
    Integer rank;         // Thứ hạng

    public TOP10AResponse(String sbd, BigDecimal toan, BigDecimal vatLi, BigDecimal hoaHoc) {
        this.sbd = sbd;
        this.toan = toan;
        this.vatLi = vatLi;
        this.hoaHoc = hoaHoc;
        this.tongDiem = sum(toan, vatLi, hoaHoc);
    }

    private BigDecimal sum(BigDecimal a, BigDecimal b, BigDecimal c) {
        return nonNull(a).add(nonNull(b)).add(nonNull(c));
    }

    private BigDecimal nonNull(BigDecimal val) {
        return val != null ? val : BigDecimal.ZERO;
    }

    public BigDecimal getBlockAScore() {
        return getTongDiem();
    }
}
