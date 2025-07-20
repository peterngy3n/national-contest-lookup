package com.timothy.national_contest_lookup.service;

import com.timothy.national_contest_lookup.dto.response.ReportResponse;
import com.timothy.national_contest_lookup.dto.response.ScoreResponse;
import com.timothy.national_contest_lookup.entity.Score;
import com.timothy.national_contest_lookup.exception.CustomException;
import com.timothy.national_contest_lookup.exception.ErrorCode;
import com.timothy.national_contest_lookup.mapper.ScoreMapper;
import com.timothy.national_contest_lookup.repository.ScoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreService {
    ScoreRepository scoreRepository;
    ScoreMapper scoreMapper;
    ReportService reportService;

    public ScoreResponse getScoreBySBD(String sdb) {
        Score score = scoreRepository.findById(sdb).orElseThrow(
                () -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
        return scoreMapper.toScoreResponse(score);
    }



//    public ReportResponse getReportBySubject(String subject) {
//        log.info("In score service");
//        List<ReportResponse> allReports = reportService.getAllSubjectsReport();
//        return allReports.stream()
//                .filter(report -> report.getSubject().equals(subject.toLowerCase()))
//                .findFirst()
//                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
//    }

    // Method tối ưu - chỉ 4 phép so sánh


    // public ReportResponse getReportBySubject(String subject) {
    //     // Sử dụng ORM: lấy tất cả entity từ database
    //     List<Score> allScores = scoreRepository.findAll();
        
    //     int excellent = 0; // >= 8
    //     int good = 0;      // 6 <= score < 8
    //     int average = 0;   // 4 <= score < 6
    //     int poor = 0;      // < 4
    //     int total = 0;

    //     for (Score score : allScores) {
    //         BigDecimal subjectScore = getScoreBySubjectName(score, subject);
            
    //         if (subjectScore != null) {
    //             total++;
    //             double point = subjectScore.doubleValue();
                
    //             if (point >= 8.0) {
    //                 excellent++;
    //             } else if (point >= 6.0) {
    //                 good++;
    //             } else if (point >= 4.0) {
    //                 average++;
    //             } else {
    //                 poor++;
    //             }
    //         }
    //     }
        
    //     return ReportResponse.builder()
    //             .subject(subject)
    //             .lv4(excellent)
    //             .lv3(good)
    //             .lv2(average)
    //             .lv1(poor)
    //             .total(total)
    //             .build();
    // }
}

