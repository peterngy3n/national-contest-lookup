package com.timothy.national_contest_lookup.seeder;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.timothy.national_contest_lookup.entity.Score;
import com.timothy.national_contest_lookup.repository.ScoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScoreSeeder implements CommandLineRunner {
    ScoreRepository scoreRepository;

    @Override
    public void run(String... args) throws Exception {
        int batchSize = 1000;

        // Nếu đã có dữ liệu thì không cần import
        if (scoreRepository.count() != 0)
            return;

        // Đọc file CSV từ classpath (bên trong .jar)
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("storage/diem_thi_thpt_2024.csv")) {

            if (inputStream == null) {
                throw new FileNotFoundException("Không tìm thấy file diem_thi_thpt_2024.csv trong classpath.");
            }

            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .withSkipLines(1) // Bỏ qua dòng tiêu đề
                    .build()) {

                String[] line;
                List<Score> batch = new ArrayList<>();
                int count = 0;

                while ((line = reader.readNext()) != null) {
                    Score score = new Score();

                    score.setSbd(line[0]);
                    score.setToan(toDecimal(line[1]));
                    score.setNguVan(toDecimal(line[2]));
                    score.setNgoaiNgu(toDecimal(line[3]));
                    score.setVatLi(toDecimal(line[4]));
                    score.setHoaHoc(toDecimal(line[5]));
                    score.setSinhHoc(toDecimal(line[6]));
                    score.setLichSu(toDecimal(line[7]));
                    score.setDiaLi(toDecimal(line[8]));
                    score.setGdcd(toDecimal(line[9]));
                    score.setMaNgoaiNgu(line.length > 10 ? line[10] : null);

                    batch.add(score);

                    if (batch.size() >= batchSize) {
                        scoreRepository.saveAll(batch);
                        count += batch.size();
                        System.out.println("Đã import: " + count + " bản ghi");
                        batch.clear();
                    }
                }

                // insert phần còn lại nếu có
                if (!batch.isEmpty()) {
                    scoreRepository.saveAll(batch);
                    count += batch.size();
                    System.out.println("Đã import xong tổng cộng: " + count + " bản ghi.");
                }
            }

        } catch (IOException e) {
            System.err.println("Lỗi khi đọc hoặc import file CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private BigDecimal toDecimal(String value) {
        try {
            return (value == null || value.isBlank()) ? null : new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
