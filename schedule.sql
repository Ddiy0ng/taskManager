CREATE TABLE task(

    id BIGINT AUTO_INCREMENT
        PRIMARY KEY
        COMMENT '고유식별자',
    password VARCHAR(30)
        NOT NULL
        COMMENT '비밀번호',
    tasks TEXT
        NOT NULL
        COMMENT '일정',
    name VARCHAR(20)
        NOT NULL
        COMMENT '작성자',
    postTime TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
        COMMENT '작성일시',
    CONSTRAINT schedule_pk PRIMARY KEY(id)
);

