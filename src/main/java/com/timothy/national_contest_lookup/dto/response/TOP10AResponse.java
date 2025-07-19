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
}
