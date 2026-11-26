CREATE TABLE IF NOT EXISTS guest (
    guestNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name CHAR(50) NOT NULL,
    userId CHAR(50) NOT NULL UNIQUE,
    pwd CHAR(50) NOT NULL,
    email CHAR(50),
    tel CHAR(50) NOT NULL,
    joindate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    outdate DATETIME,
    sys INT NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS location (
    locName CHAR(50) NOT NULL PRIMARY KEY,
    address CHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS room (
    roomNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    locName CHAR(50) NOT NULL,
    roomType CHAR(50) NOT NULL,
    roomPrice INT NOT NULL,
    roomImage CHAR(100),
    roomCount INT,
    UNIQUE (locName, roomType),
    FOREIGN KEY (locName) REFERENCES location (locName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS reservation (
    reservNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    guestNo INT NOT NULL,
    roomNo INT NOT NULL,
    adult INT NOT NULL DEFAULT 2,
    kids INT NOT NULL DEFAULT 0,
    ci_date CHAR(50) NOT NULL,
    co_date CHAR(50) NOT NULL,
    totalPrice INT NOT NULL,
    FOREIGN KEY (guestNo) REFERENCES guest (guestNo),
    FOREIGN KEY (roomNo) REFERENCES room (roomNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS facilityinfo (
    facInfoNo INT NOT NULL PRIMARY KEY,
    facName CHAR(50) NOT NULL,
    adultPrice INT NOT NULL DEFAULT 0,
    kidsPrice INT NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS facility (
    facNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reservNo INT,
    facInfoNo INT,
    adultNo INT,
    kidsNo INT,
    FOREIGN KEY (reservNo) REFERENCES reservation (reservNo),
    FOREIGN KEY (facInfoNo) REFERENCES facilityinfo (facInfoNo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS reviewboard (
    reviewNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    guestNo INT NOT NULL,
    r_title CHAR(50) NOT NULL,
    r_content TEXT,
    r_regdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (guestNo) REFERENCES guest (guestNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS askboard (
    askNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    guestNo INT NOT NULL,
    a_title CHAR(50) NOT NULL,
    a_content TEXT NOT NULL,
    a_regdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fileName CHAR(50),
    originalFilename CHAR(50),
    fileSize INT,
    FOREIGN KEY (guestNo) REFERENCES guest (guestNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS noticeboard (
    noticeNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    guestNo INT NOT NULL,
    n_title CHAR(50) NOT NULL,
    n_content TEXT NOT NULL,
    n_regdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    n_count INT NOT NULL DEFAULT 0,
    FOREIGN KEY (guestNo) REFERENCES guest (guestNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS comments (
    no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    askNo INT NOT NULL,
    name CHAR(20) NOT NULL,
    content TEXT,
    regdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (askNo) REFERENCES askboard (askNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

START TRANSACTION;

INSERT INTO location (locName, address) VALUES
    ('Full Moon', '서울특별시 중구 동호로 249'),
    ('Half Moon', '서울특별시 마포구 마포대로 83'),
    ('Crescent Moon', '서울특별시 서초구 효령로 427')
ON DUPLICATE KEY UPDATE locName = locName;

INSERT INTO facilityinfo (facInfoNo, facName, adultPrice, kidsPrice) VALUES
    (1, '조식', 40000, 17000),
    (2, '수영장', 45000, 28000),
    (3, '사우나', 35000, 0),
    (4, '헬스장', 20000, 0)
ON DUPLICATE KEY UPDATE facInfoNo = facInfoNo;

INSERT INTO room (locName, roomType, roomPrice, roomImage, roomCount) VALUES
    ('Full Moon', '디럭스', 240000, 'dex.png', 10),
    ('Full Moon', '슈페리어', 280000, 'spr.png', 8),
    ('Full Moon', '스위트', 400000, 'room-suite.png', 5),
    ('Full Moon', '모노그램스위트', 550000, 'monogram.png', 2),
    ('Half Moon', '디럭스', 240000, 'dex.png', 10),
    ('Half Moon', '슈페리어', 280000, 'spr.png', 8),
    ('Half Moon', '스위트', 400000, 'room-suite.png', 5),
    ('Half Moon', '모노그램스위트', 550000, 'monogram.png', 2),
    ('Crescent Moon', '디럭스', 240000, 'dex.png', 10),
    ('Crescent Moon', '슈페리어', 280000, 'spr.png', 8),
    ('Crescent Moon', '스위트', 400000, 'room-suite.png', 5),
    ('Crescent Moon', '모노그램스위트', 550000, 'monogram.png', 2)
ON DUPLICATE KEY UPDATE roomNo = roomNo;

COMMIT;

CREATE OR REPLACE SQL SECURITY INVOKER VIEW v_fac_sales AS
SELECT m.locName, i.facName, i.adultPrice, i.kidsPrice, r.ci_date, f.kidsNo, f.adultNo
FROM facility f
JOIN reservation r ON f.reservNo = r.reservNo
JOIN facilityinfo i ON f.facInfoNo = i.facInfoNo
JOIN room m ON r.roomNo = m.roomNo;

CREATE OR REPLACE SQL SECURITY INVOKER VIEW v_room_sales AS
SELECT m.locName, m.roomType, m.roomPrice, r.ci_date
FROM reservation r
JOIN room m ON r.roomNo = m.roomNo;
