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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService {
    ScoreRepository scoreRepository;

    @Cacheable(value = "allReportsCache", key = "'allReports'")
    public List<ReportResponse> getAllSubjectsReport() {
        List<Score> allScores = getAllScoresFromCache();

        // Tạo array cho từng môn: [excellent, good, average, poor, total]
        int[] toanStats = new int[5];
        int[] nguvanStats = new int[5];
        int[] ngoainguStats = new int[5];
        int[] vatliStats = new int[5];
        int[] hoahocStats = new int[5];
        int[] sinhhocStats = new int[5];
        int[] lichsuStats = new int[5];
        int[] dialiStats = new int[5];
        int[] gdcdStats = new int[5];

        // CHỈ 1 VÒNG LẶP DUY NHẤT - O(n) thay vì O(n×m)
        for (Score score : allScores) {
            // Tính trực tiếp cho từng môn - không cần switch case
            processSubjectScore(score.getToan(), toanStats);
            processSubjectScore(score.getNguVan(), nguvanStats);
            processSubjectScore(score.getNgoaiNgu(), ngoainguStats);
            processSubjectScore(score.getVatLi(), vatliStats);
            processSubjectScore(score.getHoaHoc(), hoahocStats);
            processSubjectScore(score.getSinhHoc(), sinhhocStats);
            processSubjectScore(score.getLichSu(), lichsuStats);
            processSubjectScore(score.getDiaLi(), dialiStats);
            processSubjectScore(score.getGdcd(), gdcdStats);
        }

        // Tạo kết quả
        return Arrays.asList(
                createReportResponse("toan", toanStats),
                createReportResponse("nguvan", nguvanStats),
                createReportResponse("ngoaingu", ngoainguStats),
                createReportResponse("vatli", vatliStats),
                createReportResponse("hoahoc", hoahocStats),
                createReportResponse("sinhhoc", sinhhocStats),
                createReportResponse("lichsu", lichsuStats),
                createReportResponse("diali", dialiStats),
                createReportResponse("gdcd", gdcdStats)
        );
    }

    @Cacheable(value = "allScoresCache", key = "'allScores'")
    public List<Score> getAllScoresFromCache() {
        return scoreRepository.findAll();
    }

    private void processSubjectScore(BigDecimal subjectScore, int[] stats) {
        if (subjectScore != null) {
            stats[4]++; // total
            double point = subjectScore.doubleValue();
            if (point >= 8.0) stats[0]++;      // excellent
            else if (point >= 6.0) stats[1]++; // good
            else if (point >= 4.0) stats[2]++; // average
            else stats[3]++;                   // poor
        }
    }

    private ReportResponse createReportResponse(String subject, int[] stats) {
        return ReportResponse.builder()
                .subject(subject)
                .lv4(stats[0])  // excellent
                .lv3(stats[1])  // good
                .lv2(stats[2])  // average
                .lv1(stats[3])  // poor
                .total(stats[4])
                .build();
    }

    @Cacheable(value = "topStudentsCache", key = "'topKhoiA'")
    public List<TOP10AResponse> getTop10KhoiA() {
        long startTime = System.currentTimeMillis();

        List<Score> allScores = getAllScoresFromCache();

        List<TOP10AResponse> topStudents = allScores.stream()
                .filter(this::hasValidKhoiAScores)  // Lọc học sinh có đủ điểm 3 môn
                .map(this::calculateKhoiAScore)     // Tính tổng điểm khối A
                .sorted((s1, s2) -> s2.getTongDiem().compareTo(s1.getTongDiem())) // Sắp xếp giảm dần
                .limit(10)                          // Lấy TOP 10
                .collect(Collectors.toList());

        // Gán thứ hạng
        for (int i = 0; i < topStudents.size(); i++) {
            topStudents.get(i).setRank(i + 1);
        }

        long endTime = System.currentTimeMillis();
        return topStudents;
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
