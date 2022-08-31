DROP TABLE IF EXISTS registerKeyword CASCADE;
DROP TABLE IF EXISTS food CASCADE;

CREATE TABLE registerKeyword (
      seq              bigint NOT NULL AUTO_INCREMENT, --등록 지점 PK
      name             varchar(40) NOT NULL,           --등록 지점 이름
      type             varchar(50) NOT NULL,          --등록 지점 타입(정류장, 지하철 역)
      latitude         real NOT NULL,                 --위도
      longitude        real NOT NULL,                 -- 경도
      roadAddress      varchar(255) NOT NULL,          --도로명 주소
      roadZipCode      varchar(10) NOT NULL,           --도로명 우편번호
      PRIMARY KEY (seq)
);

CREATE TABLE food (
    seq              bigint NOT NULL AUTO_INCREMENT, --사업자 PK
    name             varchar(40) NOT NULL,           --사업자 명
    marketType       varchar(50) NOT NULL,           --업태
    isOperating      bool NOT NULL,                   --엉업상태
    startDate        varchar(10) NOT NULL,          --인허가 일자
    latitude         real NOT NULL,                 --위도
    longitude        real NOT NULL,                -- 경도
    roadAddress      varchar(255) NOT NULL,          --도로명 주소
    roadZipCode      varchar(10) NOT NULL,           --도로명 우편번호
    titleImageUrl    varchar(255) NOT NULL,           --title 이미지 주소
    likesNumber      int NOT NULL default 0,                      --좋아요 갯수
    commentsNumber   int NOT NULL default 0,                      --댓글 갯수

    PRIMARY KEY (seq)
);
