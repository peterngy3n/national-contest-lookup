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

//    @Cacheable(value = "allReportsCache", key = "'allReports'")
//    public List<ReportResponse> getAllSubjectsReport() {
//        List<Score> allScores = getAllScoresFromCache();
//
//        // Tạo array cho từng môn: [excellent, good, average, poor, total]
//        int[] toanStats = new int[5];
//        int[] nguvanStats = new int[5];
//        int[] ngoainguStats = new int[5];
//        int[] vatliStats = new int[5];
//        int[] hoahocStats = new int[5];
//        int[] sinhhocStats = new int[5];
//        int[] lichsuStats = new int[5];
//        int[] dialiStats = new int[5];
//        int[] gdcdStats = new int[5];
//
//        // CHỈ 1 VÒNG LẶP DUY NHẤT - O(n) thay vì O(n×m)
//        for (Score score : allScores) {
//            // Tính trực tiếp cho từng môn - không cần switch case
//            processSubjectScore(score.getToan(), toanStats);
//            processSubjectScore(score.getNguVan(), nguvanStats);
//            processSubjectScore(score.getNgoaiNgu(), ngoainguStats);
//            processSubjectScore(score.getVatLi(), vatliStats);
//            processSubjectScore(score.getHoaHoc(), hoahocStats);
//            processSubjectScore(score.getSinhHoc(), sinhhocStats);
//            processSubjectScore(score.getLichSu(), lichsuStats);
//            processSubjectScore(score.getDiaLi(), dialiStats);
//            processSubjectScore(score.getGdcd(), gdcdStats);
//        }
//
//        // Tạo kết quả
//        return Arrays.asList(
//                createReportResponse("toan", toanStats),
//                createReportResponse("nguvan", nguvanStats),
//                createReportResponse("ngoaingu", ngoainguStats),
//                createReportResponse("vatli", vatliStats),
//                createReportResponse("hoahoc", hoahocStats),
//                createReportResponse("sinhhoc", sinhhocStats),
//                createReportResponse("lichsu", lichsuStats),
//                createReportResponse("diali", dialiStats),
//                createReportResponse("gdcd", gdcdStats)
//        );
//    }
//
//    @Cacheable(value = "allScoresCache", key = "'allScores'")
//    public List<Score> getAllScoresFromCache() {
//        return scoreRepository.findAll();
//    }
//
//    private void processSubjectScore(BigDecimal subjectScore, int[] stats) {
//        if (subjectScore != null) {
//            stats[4]++; // total
//            double point = subjectScore.doubleValue();
//            if (point >= 8.0) stats[0]++;      // excellent
//            else if (point >= 6.0) stats[1]++; // good
//            else if (point >= 4.0) stats[2]++; // average
//            else stats[3]++;                   // poor
//        }
//    }
//
//    private ReportResponse createReportResponse(String subject, int[] stats) {
//        return ReportResponse.builder()
//                .subject(subject)
//                .lv4(stats[0])  // excellent
//                .lv3(stats[1])  // good
//                .lv2(stats[2])  // average
//                .lv1(stats[3])  // poor
//                .total(stats[4])
//                .build();
//    }

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


    private ReportResponse build(String subject, Object result) {
        Object[] row = (Object[]) result;

        Integer lv1 = ((Number) row[0]).intValue();
        Integer lv2 = ((Number) row[1]).intValue();
        Integer lv3 = ((Number) row[2]).intValue();
        Integer lv4 = ((Number) row[3]).intValue();
        Integer total = lv1 + lv2 + lv3 + lv4;

        return new ReportResponse(subject, lv1, lv2, lv3, lv4, total);
    }


    private void processSubjectScore(BigDecimal subjectScore, int[] stats) {
        if (subjectScore != null) {
            stats[4]++; // total
            double point = subjectScore.doubleValue();
            if (point >= 8.0) stats[0]++;
            else if (point >= 6.0) stats[1]++;
            else if (point >= 4.0) stats[2]++;
            else stats[3]++;
        }
    }

    private ReportResponse createReportResponse(String subject, int[] stats) {
        return ReportResponse.builder()
                .subject(subject)
                .lv4(stats[0]) // excellent
                .lv3(stats[1]) // good
                .lv2(stats[2]) // average
                .lv1(stats[3]) // poor
                .total(stats[4])
                .build();
    }

    @Cacheable(value = "topStudentsCache", key = "'topKhoiA'")
    public List<TOP10AResponse> getTop10KhoiA() {
//        PriorityQueue<TOP10AResponse> topHeap = new PriorityQueue<>(10, Comparator.comparing(TOP10AResponse::getTongDiem));
//
//        try (Stream<Score> stream = scoreRepository.streamAllScores()) {
//            stream.filter(this::hasValidKhoiAScores).forEach(score -> {
//                TOP10AResponse student = calculateKhoiAScore(score);
//
//                if (topHeap.size() < 10) {
//                    topHeap.offer(student);
//                } else if (student.getTongDiem().compareTo(topHeap.peek().getTongDiem()) > 0) {
//                    topHeap.poll();
//                    topHeap.offer(student);
//                }
//            });
//        }
//
//        // Sắp xếp lại giảm dần để set rank
//        List<TOP10AResponse> sorted = new ArrayList<>(topHeap);
//        sorted.sort((s1, s2) -> s2.getTongDiem().compareTo(s1.getTongDiem()));
//
//        for (int i = 0; i < sorted.size(); i++) {
//            sorted.get(i).setRank(i + 1);
//        }
//
//        return sorted;
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


    private boolean hasValidKhoiAScores(Score score) {
        return score.getToan() != null &&
                score.getVatLi() != null &&
                score.getHoaHoc() != null;
    }

    private TOP10AResponse calculateKhoiAScore(Score score) {
        BigDecimal tongDiem = score.getToan()
                .add(score.getVatLi())
                .add(score.getHoaHoc());

        return TOP10AResponse.builder()
                .sbd(score.getSbd())
                .toan(score.getToan())
                .vatLi(score.getVatLi())
                .hoaHoc(score.getHoaHoc())
                .tongDiem(tongDiem)
                .build();
    }
}
