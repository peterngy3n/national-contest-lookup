package com.timothy.national_contest_lookup.repository;

import com.timothy.national_contest_lookup.dto.response.ReportResponse;
import com.timothy.national_contest_lookup.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ScoreRepository extends JpaRepository<Score, String> {
    @Query("SELECT s FROM Score s")
    Stream<Score> streamAllScores();
}
