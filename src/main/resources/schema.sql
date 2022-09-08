DROP TABLE IF EXISTS registerKeyword CASCADE;
DROP TABLE IF EXISTS menu CASCADE;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS comment CASCADE;

CREATE TABLE registerKeyword (
      id              bigint NOT NULL AUTO_INCREMENT, -- 등록 지점 PK
      city             varchar(40) NOT NULL,          -- 등록 지점 PK
      smallCity        varchar(40) NOT NULL,          -- 등록 지점 PK
      name             varchar(40) NOT NULL,           -- 등록 지점 이름
      consonantVowel   varchar(100) NOT NULL,
      type             varchar(50) NOT NULL,          -- 등록 지점 타입(정류장, 지하철 역)
      latitude         real NOT NULL,                 -- 위도
      longitude        real NOT NULL,                 -- 경도
      roadAddress      varchar(255) NOT NULL,          -- 도로명 주소
      roadZipCode      varchar(10) NOT NULL,           -- 도로명 우편번호
      PRIMARY KEY (id),
      key ind_consVow(consonantVowel)
);

CREATE TABLE restaurant (
      id               bigint NOT NULL auto_increment,
      city             varchar(40) NOT NULL,
      smallCity        varchar(40) NOT NULL,
      name             varchar(40) NOT NULL,
      marketType       varchar(50) NOT NULL,
      description      text default '',
      isOperating      bool NOT NULL,
      startDate        varchar(10) NOT NULL,
      latitude         real NOT NULL,
      longitude        real NOT NULL,
      roadAddress      varchar(255) NOT NULL,
      roadZipCode      varchar(10) NOT NULL,
      titleImageUrl    varchar(255) NOT NULL,
      likesNumber      int NOT NULL default 0,
      commentsNumber   int NOT NULL default 0,

      PRIMARY KEY (id),
      KEY idx_city(city, smallCity)
);

CREATE TABLE menu (
      id 					BIGINT NOT NULL AUTO_INCREMENT,
      name                  varchar(40) NOT NULL,
      price                 int NOT NULL,
      likesNumber           int NOT NULL default 0,
      foodId				BIGINT NOT NULL,
      PRIMARY KEY (id),
      key inx_foodId(foodId)
);

CREATE TABLE user (
      userId                varchar(50) NOT NULL,
      password              varchar(50) NOT NULL,
      name                  varchar(50) NOT NULL,
      roadAddress           varchar(100) NOT NULL default 0,
      phoneNumber   		varchar(20) NOT NULL,
      registerDate          datetime NOT NULL,
      lastLoginTime         datetime NOT NULL,
      loginTimes            int NOT NULL default 0,
      PRIMARY KEY (userId)
);

CREATE TABLE comment (
      id                    bigint NOT NULL auto_increment,
      content               text NOT NULL default '',
      likesNumber           int not null default 0,
      registerDate          datetime NOT NULL,
      isDisable             int NOT NULL default 0,
      userId                varchar(50) NOT NULL,
      restaurantId          bigint not null,
      commentId             bigint not null default 0,
      PRIMARY KEY (id),
      KEY idx_user(userId),
      KEY restaurant(restaurantId),
      KEY idx_comment(commentId)

);