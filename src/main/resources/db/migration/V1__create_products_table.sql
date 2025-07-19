CREATE TABLE exam_scores (
    sbd VARCHAR(20) PRIMARY KEY,

    toan DECIMAL(4,2),
    ngu_van DECIMAL(4,2),
    ngoai_ngu DECIMAL(4,2),

    vat_li DECIMAL(4,2),
    hoa_hoc DECIMAL(4,2),
    sinh_hoc DECIMAL(4,2),

    lich_su DECIMAL(4,2),
    dia_li DECIMAL(4,2),
    gdcd DECIMAL(4,2),

    ma_ngoai_ngu VARCHAR(10),

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
