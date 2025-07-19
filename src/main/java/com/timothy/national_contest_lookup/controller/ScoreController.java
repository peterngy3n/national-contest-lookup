package com.timothy.national_contest_lookup.controller;

import com.timothy.national_contest_lookup.dto.response.ReportResponse;
import com.timothy.national_contest_lookup.dto.response.ScoreResponse;
import com.timothy.national_contest_lookup.dto.response.ApiResponse;
import com.timothy.national_contest_lookup.dto.response.TOP10AResponse;
import com.timothy.national_contest_lookup.service.ReportService;
import com.timothy.national_contest_lookup.service.ScoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreController {
    ScoreService scoreService;
    ReportService reportService;


    //Lấy số báo danh
    @GetMapping("/{sdb}")
    ApiResponse<ScoreResponse>getScoreBySBD (@PathVariable("sdb") String sdb) {
        return ApiResponse.<ScoreResponse>builder()
                .result(scoreService.getScoreBySBD(sdb))
                .build();
    }

    @GetMapping("/report/{subject}")
    ApiResponse<ReportResponse>getReport (@PathVariable("subject") String subject) {
        return ApiResponse.<ReportResponse>builder()
                .result(scoreService.getReportBySubject(subject)).build();
    }

    @GetMapping("/report/top10")
    ApiResponse<List<TOP10AResponse>> getTop10 () {
        return ApiResponse.<List<TOP10AResponse>>builder()
                .result(reportService.getTop10KhoiA())
                .build();
    }

}
