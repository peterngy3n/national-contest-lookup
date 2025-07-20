package com.timothy.national_contest_lookup.mapper;

import com.timothy.national_contest_lookup.dto.response.ScoreResponse;
import com.timothy.national_contest_lookup.entity.Score;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-20T23:49:21+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ScoreMapperImpl implements ScoreMapper {

    @Override
    public ScoreResponse toScoreResponse(Score score) {
        if ( score == null ) {
            return null;
        }

        ScoreResponse.ScoreResponseBuilder scoreResponse = ScoreResponse.builder();

        scoreResponse.diaLi( score.getDiaLi() );
        scoreResponse.gdcd( score.getGdcd() );
        scoreResponse.hoaHoc( score.getHoaHoc() );
        scoreResponse.lichSu( score.getLichSu() );
        scoreResponse.maNgoaiNgu( score.getMaNgoaiNgu() );
        scoreResponse.ngoaiNgu( score.getNgoaiNgu() );
        scoreResponse.nguVan( score.getNguVan() );
        scoreResponse.sbd( score.getSbd() );
        scoreResponse.sinhHoc( score.getSinhHoc() );
        scoreResponse.toan( score.getToan() );
        scoreResponse.vatLi( score.getVatLi() );

        return scoreResponse.build();
    }
}
