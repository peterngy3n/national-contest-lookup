package com.timothy.national_contest_lookup.mapper;

import com.timothy.national_contest_lookup.dto.response.ScoreResponse;
import com.timothy.national_contest_lookup.entity.Score;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-20T21:22:00+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class ScoreMapperImpl implements ScoreMapper {

    @Override
    public ScoreResponse toScoreResponse(Score score) {
        if ( score == null ) {
            return null;
        }

        ScoreResponse.ScoreResponseBuilder scoreResponse = ScoreResponse.builder();

        scoreResponse.sbd( score.getSbd() );
        scoreResponse.toan( score.getToan() );
        scoreResponse.nguVan( score.getNguVan() );
        scoreResponse.ngoaiNgu( score.getNgoaiNgu() );
        scoreResponse.vatLi( score.getVatLi() );
        scoreResponse.hoaHoc( score.getHoaHoc() );
        scoreResponse.sinhHoc( score.getSinhHoc() );
        scoreResponse.lichSu( score.getLichSu() );
        scoreResponse.diaLi( score.getDiaLi() );
        scoreResponse.gdcd( score.getGdcd() );
        scoreResponse.maNgoaiNgu( score.getMaNgoaiNgu() );

        return scoreResponse.build();
    }
}
