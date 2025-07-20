package com.timothy.national_contest_lookup.repository;

import com.timothy.national_contest_lookup.dto.response.ReportResponse;
import com.timothy.national_contest_lookup.dto.response.TOP10AResponse;
import com.timothy.national_contest_lookup.entity.Score;
import org.springframework.data.domain.Pageable;
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

    @Query("""
    SELECT new com.timothy.national_contest_lookup.dto.response.TOP10AResponse(
        s.sbd,
        s.toan,
        s.vatLi,
        s.hoaHoc
    )
    FROM Score s
    WHERE s.toan IS NOT NULL AND s.vatLi IS NOT NULL AND s.hoaHoc IS NOT NULL
""")
    List<TOP10AResponse> findTopBlockA();

    // JPQL không có UNION ALL => ta viết 9 query riêng
    @Query("""
        SELECT 
            COUNT(CASE WHEN s.toan >= 8 THEN 1 END),
            COUNT(CASE WHEN s.toan >= 6 AND s.toan < 8 THEN 1 END),
            COUNT(CASE WHEN s.toan >= 4 AND s.toan < 6 THEN 1 END),
            COUNT(CASE WHEN s.toan < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportToan();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.nguVan >= 8 THEN 1 END),
            COUNT(CASE WHEN s.nguVan >= 6 AND s.nguVan < 8 THEN 1 END),
            COUNT(CASE WHEN s.nguVan >= 4 AND s.nguVan < 6 THEN 1 END),
            COUNT(CASE WHEN s.nguVan < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportNguVan();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.ngoaiNgu >= 8 THEN 1 END),
            COUNT(CASE WHEN s.ngoaiNgu >= 6 AND s.ngoaiNgu < 8 THEN 1 END),
            COUNT(CASE WHEN s.ngoaiNgu >= 4 AND s.ngoaiNgu < 6 THEN 1 END),
            COUNT(CASE WHEN s.ngoaiNgu < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportNgoaiNgu();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.vatLi >= 8 THEN 1 END),
            COUNT(CASE WHEN s.vatLi >= 6 AND s.vatLi < 8 THEN 1 END),
            COUNT(CASE WHEN s.vatLi >= 4 AND s.vatLi < 6 THEN 1 END),
            COUNT(CASE WHEN s.vatLi < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportVatLi();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.hoaHoc >= 8 THEN 1 END),
            COUNT(CASE WHEN s.hoaHoc >= 6 AND s.hoaHoc < 8 THEN 1 END),
            COUNT(CASE WHEN s.hoaHoc >= 4 AND s.hoaHoc < 6 THEN 1 END),
            COUNT(CASE WHEN s.hoaHoc < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportHoaHoc();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.sinhHoc >= 8 THEN 1 END),
            COUNT(CASE WHEN s.sinhHoc >= 6 AND s.sinhHoc < 8 THEN 1 END),
            COUNT(CASE WHEN s.sinhHoc >= 4 AND s.sinhHoc < 6 THEN 1 END),
            COUNT(CASE WHEN s.sinhHoc < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportSinhHoc();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.lichSu >= 8 THEN 1 END),
            COUNT(CASE WHEN s.lichSu >= 6 AND s.lichSu < 8 THEN 1 END),
            COUNT(CASE WHEN s.lichSu >= 4 AND s.lichSu < 6 THEN 1 END),
            COUNT(CASE WHEN s.lichSu < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportLichSu();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.diaLi >= 8 THEN 1 END),
            COUNT(CASE WHEN s.diaLi >= 6 AND s.diaLi < 8 THEN 1 END),
            COUNT(CASE WHEN s.diaLi >= 4 AND s.diaLi < 6 THEN 1 END),
            COUNT(CASE WHEN s.diaLi < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportDiaLi();

    @Query("""
        SELECT 
            COUNT(CASE WHEN s.gdcd >= 8 THEN 1 END),
            COUNT(CASE WHEN s.gdcd >= 6 AND s.gdcd < 8 THEN 1 END),
            COUNT(CASE WHEN s.gdcd >= 4 AND s.gdcd < 6 THEN 1 END),
            COUNT(CASE WHEN s.gdcd < 4 THEN 1 END)
        FROM Score s
    """)
    List<Object[]> reportGdcd();
}
