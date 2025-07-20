package com.timothy.national_contest_lookup.service;

import com.timothy.national_contest_lookup.dto.response.ReportResponse;
import com.timothy.national_contest_lookup.dto.response.ScoreResponse;
import com.timothy.national_contest_lookup.dto.response.TOP10AResponse;
import com.timothy.national_contest_lookup.entity.Score;
import com.timothy.national_contest_lookup.repository.ScoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService {
    ScoreRepository scoreRepository;

    @Cacheable(value = "allReportsCache", key = "#subject")
    public ReportResponse getAllSubjectsReport(String subject) {
        List<Object[]> result;

        switch (subject.toLowerCase()) {
            case "toan" -> result = scoreRepository.reportToan();
            case "vatli" -> result = scoreRepository.reportVatLi();
            case "hoahoc" -> result = scoreRepository.reportHoaHoc();
            case "nguvan" -> result = scoreRepository.reportNguVan();
            case "ngoainingu" -> result = scoreRepository.reportNgoaiNgu();
            case "sinhhoc" -> result = scoreRepository.reportSinhHoc();
            case "lichsu" -> result = scoreRepository.reportLichSu();
            case "diali" -> result = scoreRepository.reportDiaLi();
            case "gdcd" -> result = scoreRepository.reportGdcd();
            default -> throw new IllegalArgumentException("Môn học không hợp lệ: " + subject);
        }

        Object[] row = result.get(0);
        int lv1 = ((Number) row[0]).intValue();
        int lv2 = ((Number) row[1]).intValue();
        int lv3 = ((Number) row[2]).intValue();
        int lv4 = ((Number) row[3]).intValue();

        return new ReportResponse(subject, lv1, lv2, lv3, lv4, lv1 + lv2 + lv3 + lv4);
    }

    @Cacheable(value = "topStudentsCache", key = "'topKhoiA'")
    public List<TOP10AResponse> getTop10KhoiA() {
        List<TOP10AResponse> all = scoreRepository.findTopBlockA().stream()
                .sorted((a, b) -> b.getBlockAScore().compareTo(a.getBlockAScore()))
                .limit(10)
                .toList();

        int rank = 1;
        for (TOP10AResponse student : all) {
            student.setRank(rank++);
        }
        return all;
    }
}
