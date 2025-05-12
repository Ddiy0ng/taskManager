CREATE TABLE schedule(
    taskId BIGINT AUTO_INCREMENT
        PRIMARY KEY
        COMMENT '일정 고유식별자',
    password VARCHAR(30)
        NOT NULL
        COMMENT '비밀번호',
    userId BIGINT
        COMMENT '작성자 고유식별자',
    tasks VARCHAR(200)
        NOT NULL
        COMMENT '일정',
    postDate TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        COMMENT '작성일시',
    updateDate TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
        COMMENT '최종 수정일시',
    FOREIGN KEY(userId) references taskMangerUser(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE taskMangerUser(
     userId BIGINT AUTO_INCREMENT
         PRIMARY KEY
         COMMENT '작성자 고유식별자',
     userName VARCHAR(20)
         NOT NULL
         COMMENT '이름',
     email TEXT
         NOT NULL
         COMMENT '이메일',
     postDate TIMESTAMP
         DEFAULT CURRENT_TIMESTAMP
         COMMENT '등록일',
     updateDate TIMESTAMP
         DEFAULT CURRENT_TIMESTAMP
         ON UPDATE CURRENT_TIMESTAMP
         COMMENT '수정일'
);



