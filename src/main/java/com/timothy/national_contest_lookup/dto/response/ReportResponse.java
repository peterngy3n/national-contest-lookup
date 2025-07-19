package com.timothy.national_contest_lookup.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
    String subject;
    Integer lv1;
    Integer lv2;
    Integer lv3;
    Integer lv4;
    Integer total;
}
