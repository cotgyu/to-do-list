-- label definition

CREATE TABLE `label`
(
    `label_id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `color`         varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `label_name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- monthly_user_register_stats definition

CREATE TABLE `monthly_user_register_stats`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT,
    `count` bigint(20) DEFAULT NULL,
    `month` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `user` definition

CREATE TABLE `user`
(
    `user_id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `email`         varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `name`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `picture`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `role`          varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- user_board_stats definition

CREATE TABLE `user_board_stats`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `count`   bigint(20) DEFAULT NULL,
    `name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- board definition

CREATE TABLE `board`
(
    `board_id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `board_name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `user_id`       bigint(20) DEFAULT NULL,
    PRIMARY KEY (`board_id`),
    KEY             `FKfyf1fchnby6hndhlfaidier1r` (`user_id`),
    CONSTRAINT `FKfyf1fchnby6hndhlfaidier1r` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- topic definition

CREATE TABLE `topic`
(
    `topic_id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `topic_name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `board_id`      bigint(20) DEFAULT NULL,
    PRIMARY KEY (`topic_id`),
    KEY             `FK4w4k5byft2kgtxu6g78gd8pvy` (`board_id`),
    CONSTRAINT `FK4w4k5byft2kgtxu6g78gd8pvy` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- card definition

CREATE TABLE `card`
(
    `card_id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `card_name`     varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `description`   varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `topic_id`      bigint(20) DEFAULT NULL,
    PRIMARY KEY (`card_id`),
    KEY             `FKkk1up2xsfcv4t21pyk345m9cu` (`topic_id`),
    CONSTRAINT `FKkk1up2xsfcv4t21pyk345m9cu` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- card_label definition

CREATE TABLE `card_label`
(
    `card_label_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `card_id`       bigint(20) DEFAULT NULL,
    `label_id`      bigint(20) DEFAULT NULL,
    PRIMARY KEY (`card_label_id`),
    KEY             `FK6hd640wdc120cw7py2mxj6wb7` (`card_id`),
    KEY             `FKmlw7eklhh1w7r627k334hdcoa` (`label_id`),
    CONSTRAINT `FK6hd640wdc120cw7py2mxj6wb7` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`),
    CONSTRAINT `FKmlw7eklhh1w7r627k334hdcoa` FOREIGN KEY (`label_id`) REFERENCES `label` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- check_list definition

CREATE TABLE `check_list`
(
    `check_list_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`    datetime(6) DEFAULT NULL,
    `modified_date`   datetime(6) DEFAULT NULL,
    `check_list_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `del_flag`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `card_id`         bigint(20) DEFAULT NULL,
    PRIMARY KEY (`check_list_id`),
    KEY               `FKbya6dprtlgb9ygidmp03t4wed` (`card_id`),
    CONSTRAINT `FKbya6dprtlgb9ygidmp03t4wed` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- check_item definition

CREATE TABLE `check_item`
(
    `check_item_id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`    datetime(6) DEFAULT NULL,
    `modified_date`   datetime(6) DEFAULT NULL,
    `check_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `check_item_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `del_flag`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `check_list_id`   bigint(20) DEFAULT NULL,
    PRIMARY KEY (`check_item_id`),
    KEY               `FK6q49kls5iiu2y75wa9qd2hb64` (`check_list_id`),
    CONSTRAINT `FK6q49kls5iiu2y75wa9qd2hb64` FOREIGN KEY (`check_list_id`) REFERENCES `check_list` (`check_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



-- Autogenerated: do not edit this file

CREATE TABLE BATCH_JOB_INSTANCE  (
                                     JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
                                     VERSION BIGINT ,
                                     JOB_NAME VARCHAR(100) NOT NULL,
                                     JOB_KEY VARCHAR(32) NOT NULL,
                                     constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
                                      JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                      VERSION BIGINT  ,
                                      JOB_INSTANCE_ID BIGINT NOT NULL,
                                      CREATE_TIME DATETIME NOT NULL,
                                      START_TIME DATETIME DEFAULT NULL ,
                                      END_TIME DATETIME DEFAULT NULL ,
                                      STATUS VARCHAR(10) ,
                                      EXIT_CODE VARCHAR(2500) ,
                                      EXIT_MESSAGE VARCHAR(2500) ,
                                      LAST_UPDATED DATETIME,
                                      JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
                                      constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
                                          references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
                                             JOB_EXECUTION_ID BIGINT NOT NULL ,
                                             TYPE_CD VARCHAR(6) NOT NULL ,
                                             KEY_NAME VARCHAR(100) NOT NULL ,
                                             STRING_VAL VARCHAR(250) ,
                                             DATE_VAL DATETIME DEFAULT NULL ,
                                             LONG_VAL BIGINT ,
                                             DOUBLE_VAL DOUBLE PRECISION ,
                                             IDENTIFYING CHAR(1) NOT NULL ,
                                             constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
                                                 references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
                                       STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                       VERSION BIGINT NOT NULL,
                                       STEP_NAME VARCHAR(100) NOT NULL,
                                       JOB_EXECUTION_ID BIGINT NOT NULL,
                                       START_TIME DATETIME NOT NULL ,
                                       END_TIME DATETIME DEFAULT NULL ,
                                       STATUS VARCHAR(10) ,
                                       COMMIT_COUNT BIGINT ,
                                       READ_COUNT BIGINT ,
                                       FILTER_COUNT BIGINT ,
                                       WRITE_COUNT BIGINT ,
                                       READ_SKIP_COUNT BIGINT ,
                                       WRITE_SKIP_COUNT BIGINT ,
                                       PROCESS_SKIP_COUNT BIGINT ,
                                       ROLLBACK_COUNT BIGINT ,
                                       EXIT_CODE VARCHAR(2500) ,
                                       EXIT_MESSAGE VARCHAR(2500) ,
                                       LAST_UPDATED DATETIME,
                                       constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
                                           references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
                                               STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                               SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                               SERIALIZED_CONTEXT TEXT ,
                                               constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
                                                   references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
                                              JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                              SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                              SERIALIZED_CONTEXT TEXT ,
                                              constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
                                                  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
                                          ID BIGINT NOT NULL,
                                          UNIQUE_KEY CHAR(1) NOT NULL,
                                          constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
                                         ID BIGINT NOT NULL,
                                         UNIQUE_KEY CHAR(1) NOT NULL,
                                         constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE BATCH_JOB_SEQ (
                               ID BIGINT NOT NULL,
                               UNIQUE_KEY CHAR(1) NOT NULL,
                               constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);





INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2022-02-27 21:31:52.968023', '2022-02-27 21:31:52.968023', 'N', 'v123v123s@gmail.com', 'testUser',
        'https://lh6.googleusercontent.com/-XRdI0_dL6cQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm6Uc0lgVjtVlZQFcP1U69RETkOfA/s96-c/photo.jpg',
        'ADMIN'),
       ('2022-02-27 21:31:53.014282', '2022-02-27 21:31:53.014282', 'N', 'testUser2@gmail.com', 'testUser2', '2.jpg',
        'USER'),
       ('2022-02-27 21:31:53.016252', '2022-02-27 21:31:53.016252', 'N', 'testUser3@gmail.com', 'testUser3', '3.jpg',
        'USER'),
       ('2022-02-27 21:31:53.018365', '2022-02-27 21:31:53.018365', 'N', 'testUser4@gmail.com', 'testUser4', '4.jpg',
        'USER'),
       ('2022-02-27 21:31:53.020145', '2022-02-27 21:31:53.020145', 'N', 'testUser5@gmail.com', 'testUser5', '5.jpg',
        'USER'),
       ('2022-01-27 21:31:53.090879', '2022-02-27 21:31:53.220864', 'N', 'testUser6@gmail.com', 'testUser6', '6.jpg',
        'USER'),
       ('2022-01-27 21:31:53.090883', '2022-02-27 21:31:53.221339', 'N', 'testUser7@gmail.com', 'testUser7', '7.jpg',
        'USER'),
       ('2021-12-27 21:31:53.090886', '2022-02-27 21:31:53.221405', 'N', 'testUser8@gmail.com', 'testUser8', '8.jpg',
        'USER'),
       ('2021-11-27 21:31:53.030009', '2022-02-27 21:31:53.22146', 'N', 'initUser0@gmail.com', 'initUser0', '0.jpg',
        'USER'),
       ('2021-11-27 21:31:53.031602', '2022-02-27 21:31:53.221514', 'N', 'initUser1@gmail.com', 'initUser1', '1.jpg',
        'USER');
INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2021-11-27 21:31:53.033065', '2022-02-27 21:31:53.221572', 'N', 'initUser2@gmail.com', 'initUser2', '2.jpg',
        'USER'),
       ('2021-11-27 21:31:53.034887', '2022-02-27 21:31:53.221629', 'N', 'initUser3@gmail.com', 'initUser3', '3.jpg',
        'USER'),
       ('2021-11-27 21:31:53.036337', '2022-02-27 21:31:53.221683', 'N', 'initUser4@gmail.com', 'initUser4', '4.jpg',
        'USER'),
       ('2021-11-27 21:31:53.037719', '2022-02-27 21:31:53.221742', 'N', 'initUser5@gmail.com', 'initUser5', '5.jpg',
        'USER'),
       ('2021-11-27 21:31:53.039177', '2022-02-27 21:31:53.221796', 'N', 'initUser6@gmail.com', 'initUser6', '6.jpg',
        'USER'),
       ('2021-11-27 21:31:53.041094', '2022-02-27 21:31:53.221853', 'N', 'initUser7@gmail.com', 'initUser7', '7.jpg',
        'USER'),
       ('2021-11-27 21:31:53.042522', '2022-02-27 21:31:53.221913', 'N', 'initUser8@gmail.com', 'initUser8', '8.jpg',
        'USER'),
       ('2021-11-27 21:31:53.043909', '2022-02-27 21:31:53.221968', 'N', 'initUser9@gmail.com', 'initUser9', '9.jpg',
        'USER'),
       ('2021-11-27 21:31:53.0452', '2022-02-27 21:31:53.222027', 'N', 'initUser10@gmail.com', 'initUser10', '10.jpg',
        'USER'),
       ('2021-11-27 21:31:53.046785', '2022-02-27 21:31:53.222174', 'N', 'initUser11@gmail.com', 'initUser11', '11.jpg',
        'USER');
INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2021-11-27 21:31:53.048215', '2022-02-27 21:31:53.222483', 'N', 'initUser12@gmail.com', 'initUser12', '12.jpg',
        'USER'),
       ('2021-11-27 21:31:53.049452', '2022-02-27 21:31:53.222559', 'N', 'initUser13@gmail.com', 'initUser13', '13.jpg',
        'USER'),
       ('2021-11-27 21:31:53.050756', '2022-02-27 21:31:53.222617', 'N', 'initUser14@gmail.com', 'initUser14', '14.jpg',
        'USER'),
       ('2021-11-27 21:31:53.052173', '2022-02-27 21:31:53.222675', 'N', 'initUser15@gmail.com', 'initUser15', '15.jpg',
        'USER'),
       ('2021-11-27 21:31:53.053572', '2022-02-27 21:31:53.222729', 'N', 'initUser16@gmail.com', 'initUser16', '16.jpg',
        'USER'),
       ('2021-11-27 21:31:53.054916', '2022-02-27 21:31:53.222782', 'N', 'initUser17@gmail.com', 'initUser17', '17.jpg',
        'USER'),
       ('2021-11-27 21:31:53.056223', '2022-02-27 21:31:53.222835', 'N', 'initUser18@gmail.com', 'initUser18', '18.jpg',
        'USER'),
       ('2021-11-27 21:31:53.057455', '2022-02-27 21:31:53.222902', 'N', 'initUser19@gmail.com', 'initUser19', '19.jpg',
        'USER'),
       ('2021-11-27 21:31:53.058985', '2022-02-27 21:31:53.222965', 'N', 'initUser20@gmail.com', 'initUser20', '20.jpg',
        'USER'),
       ('2021-11-27 21:31:53.060425', '2022-02-27 21:31:53.223017', 'N', 'initUser21@gmail.com', 'initUser21', '21.jpg',
        'USER');
INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2021-11-27 21:31:53.061697', '2022-02-27 21:31:53.223069', 'N', 'initUser22@gmail.com', 'initUser22', '22.jpg',
        'USER'),
       ('2021-11-27 21:31:53.063059', '2022-02-27 21:31:53.223121', 'N', 'initUser23@gmail.com', 'initUser23', '23.jpg',
        'USER'),
       ('2021-11-27 21:31:53.064246', '2022-02-27 21:31:53.223174', 'N', 'initUser24@gmail.com', 'initUser24', '24.jpg',
        'USER'),
       ('2021-11-27 21:31:53.065386', '2022-02-27 21:31:53.223225', 'N', 'initUser25@gmail.com', 'initUser25', '25.jpg',
        'USER'),
       ('2021-11-27 21:31:53.066761', '2022-02-27 21:31:53.223279', 'N', 'initUser26@gmail.com', 'initUser26', '26.jpg',
        'USER'),
       ('2021-11-27 21:31:53.068186', '2022-02-27 21:31:53.223471', 'N', 'initUser27@gmail.com', 'initUser27', '27.jpg',
        'USER'),
       ('2021-11-27 21:31:53.069685', '2022-02-27 21:31:53.223542', 'N', 'initUser28@gmail.com', 'initUser28', '28.jpg',
        'USER'),
       ('2021-11-27 21:31:53.070874', '2022-02-27 21:31:53.223596', 'N', 'initUser29@gmail.com', 'initUser29', '29.jpg',
        'USER'),
       ('2021-11-27 21:31:53.072197', '2022-02-27 21:31:53.223647', 'N', 'initUser30@gmail.com', 'initUser30', '30.jpg',
        'USER'),
       ('2021-11-27 21:31:53.07364', '2022-02-27 21:31:53.2237', 'N', 'initUser31@gmail.com', 'initUser31', '31.jpg',
        'USER');
INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2021-11-27 21:31:53.074986', '2022-02-27 21:31:53.22375', 'N', 'initUser32@gmail.com', 'initUser32', '32.jpg',
        'USER'),
       ('2021-11-27 21:31:53.076623', '2022-02-27 21:31:53.223799', 'N', 'initUser33@gmail.com', 'initUser33', '33.jpg',
        'USER'),
       ('2021-11-27 21:31:53.077986', '2022-02-27 21:31:53.223854', 'N', 'initUser34@gmail.com', 'initUser34', '34.jpg',
        'USER'),
       ('2021-11-27 21:31:53.079469', '2022-02-27 21:31:53.223908', 'N', 'initUser35@gmail.com', 'initUser35', '35.jpg',
        'USER'),
       ('2021-11-27 21:31:53.080622', '2022-02-27 21:31:53.223957', 'N', 'initUser36@gmail.com', 'initUser36', '36.jpg',
        'USER'),
       ('2021-11-27 21:31:53.081822', '2022-02-27 21:31:53.224008', 'N', 'initUser37@gmail.com', 'initUser37', '37.jpg',
        'USER'),
       ('2021-11-27 21:31:53.083115', '2022-02-27 21:31:53.224153', 'N', 'initUser38@gmail.com', 'initUser38', '38.jpg',
        'USER'),
       ('2021-11-27 21:31:53.084348', '2022-02-27 21:31:53.224222', 'N', 'initUser39@gmail.com', 'initUser39', '39.jpg',
        'USER'),
       ('2021-11-27 21:31:53.085623', '2022-02-27 21:31:53.224274', 'N', 'initUser40@gmail.com', 'initUser40', '40.jpg',
        'USER'),
       ('2021-11-27 21:31:53.086788', '2022-02-27 21:31:53.224327', 'N', 'initUser41@gmail.com', 'initUser41', '41.jpg',
        'USER');
INSERT INTO `user` (created_date, modified_date, del_flag, email, name, picture, `role`)
VALUES ('2021-11-27 21:31:53.088147', '2022-02-27 21:31:53.224383', 'N', 'initUser42@gmail.com', 'initUser42', '42.jpg',
        'USER'),
       ('2021-11-27 21:31:53.089592', '2022-02-27 21:31:53.224441', 'N', 'initUser43@gmail.com', 'initUser43', '43.jpg',
        'USER'),
       ('2021-11-27 21:31:53.090873', '2022-02-27 21:31:53.224489', 'N', 'initUser44@gmail.com', 'initUser44', '44.jpg',
        'USER');
INSERT INTO board (created_date, modified_date, board_name, del_flag, user_id)
VALUES ('2022-02-27 21:31:53.091335', '2022-02-27 21:31:53.091335', 'board1', 'N', 1),
       ('2022-02-27 21:31:53.101556', '2022-02-27 21:31:53.101556', 'board2', 'N', 1),
       ('2022-02-27 21:31:53.103093', '2022-02-27 21:31:53.103093', 'board3', 'N', 1),
       ('2022-02-27 21:31:53.104372', '2022-02-27 21:31:53.104372', 'board4', 'N', 2),
       ('2022-02-27 21:31:53.105611', '2022-02-27 21:31:53.105611', 'board5', 'N', 3),
       ('2022-02-27 21:31:53.107207', '2022-02-27 21:31:53.107207', 'board6', 'N', 3),
       ('2022-02-27 21:31:53.108726', '2022-02-27 21:31:53.108726', 'testBoard10', 'N', 4),
       ('2022-02-27 21:31:53.110236', '2022-02-27 21:31:53.110236', 'testBoard11', 'N', 4),
       ('2022-02-27 21:31:53.111774', '2022-02-27 21:31:53.111774', 'testBoard12', 'N', 4),
       ('2022-02-27 21:31:53.112877', '2022-02-27 21:31:53.112877', 'testBoard13', 'N', 4);
INSERT INTO board (created_date, modified_date, board_name, del_flag, user_id)
VALUES ('2022-02-27 21:31:53.114048', '2022-02-27 21:31:53.114048', 'testBoard14', 'N', 4),
       ('2022-02-27 21:31:53.115452', '2022-02-27 21:31:53.115452', 'testBoard15', 'N', 4),
       ('2022-02-27 21:31:53.116847', '2022-02-27 21:31:53.116847', 'testBoard16', 'N', 4),
       ('2022-02-27 21:31:53.118068', '2022-02-27 21:31:53.118068', 'testBoard17', 'N', 4),
       ('2022-02-27 21:31:53.119213', '2022-02-27 21:31:53.119213', 'testBoard18', 'N', 4),
       ('2022-02-27 21:31:53.120447', '2022-02-27 21:31:53.120447', 'testBoard19', 'N', 4),
       ('2022-02-27 21:31:53.121492', '2022-02-27 21:31:53.121492', 'testBoard20', 'N', 4),
       ('2022-02-27 21:31:53.122674', '2022-02-27 21:31:53.122674', 'testBoard21', 'N', 4),
       ('2022-02-27 21:31:53.123859', '2022-02-27 21:31:53.123859', 'testBoard22', 'N', 4),
       ('2022-02-27 21:31:53.125121', '2022-02-27 21:31:53.125121', 'testBoard23', 'N', 4);
INSERT INTO board (created_date, modified_date, board_name, del_flag, user_id)
VALUES ('2022-02-27 21:31:53.126245', '2022-02-27 21:31:53.126245', 'testBoard24', 'N', 4),
       ('2022-02-27 21:31:53.127381', '2022-02-27 21:31:53.127381', 'testBoard25', 'N', 4),
       ('2022-02-27 21:31:53.128464', '2022-02-27 21:31:53.128464', 'testBoard26', 'N', 4),
       ('2022-02-27 21:31:53.129604', '2022-02-27 21:31:53.129604', 'testBoard27', 'N', 4),
       ('2022-02-27 21:31:53.130589', '2022-02-27 21:31:53.130589', 'testBoard28', 'N', 4),
       ('2022-02-27 21:31:53.131594', '2022-02-27 21:31:53.131594', 'testBoard29', 'N', 4);


INSERT INTO topic (created_date, modified_date, del_flag, topic_name, board_id)
VALUES ('2022-02-27 21:31:53.132961', '2022-02-27 21:31:53.132961', 'N', 'topic1', 1),
       ('2022-02-27 21:31:53.14284', '2022-02-27 21:31:53.14284', 'N', 'topic2', 1),
       ('2022-02-27 21:31:53.144338', '2022-02-27 21:31:53.144338', 'N', 'topic3', 4);


INSERT INTO card (created_date, modified_date, card_name, del_flag, description, topic_id)
VALUES ('2022-02-27 21:31:53.145993', '2022-02-27 21:31:53.145993', 'card1', 'N', 'des1', 1),
       ('2022-02-27 21:31:53.153662', '2022-02-27 21:31:53.153662', 'card2', 'N', 'des2', 1),
       ('2022-02-27 21:31:53.155471', '2022-02-27 21:31:53.155471', 'card3', 'N', 'des3', 2),
       ('2022-02-27 21:31:53.156861', '2022-02-27 21:31:53.156861', 'card4', 'N', 'des4', 2),
       ('2022-02-27 21:31:53.158406', '2022-02-27 21:31:53.158406', 'card5', 'N', 'des5', 2),
       ('2022-02-27 21:31:53.159653', '2022-02-27 21:31:53.159653', 'card6', 'N', 'des6', 2),
       ('2022-02-27 21:31:53.160789', '2022-02-27 21:31:53.160789', 'card7', 'N', 'des7', 3);


INSERT INTO label (created_date, modified_date, color, del_flag, label_name)
VALUES ('2022-02-27 21:31:53.188355', '2022-02-27 21:31:53.188355', 'green', 'N', 'label1'),
       ('2022-02-27 21:31:53.196732', '2022-02-27 21:31:53.196732', 'red', 'N', 'label2'),
       ('2022-02-27 21:31:53.198438', '2022-02-27 21:31:53.198438', 'blue', 'N', 'label3'),
       ('2022-02-27 21:31:53.199909', '2022-02-27 21:31:53.199909', 'yellow', 'N', 'label4');



INSERT INTO card_label (created_date, modified_date, del_flag, card_id, label_id)
VALUES ('2022-02-27 21:31:53.203338', '2022-02-27 21:31:53.203338', 'N', 1, 1),
       ('2022-02-27 21:31:53.213432', '2022-02-27 21:31:53.213432', 'N', 1, 3),
       ('2022-02-27 21:31:53.214719', '2022-02-27 21:31:53.214719', 'N', 2, 2),
       ('2022-02-27 21:31:53.215848', '2022-02-27 21:31:53.215848', 'N', 2, 3),
       ('2022-02-27 21:31:53.217215', '2022-02-27 21:31:53.217215', 'N', 4, 3);



INSERT INTO check_list (created_date, modified_date, check_list_name, del_flag, card_id)
VALUES ('2022-02-27 21:31:53.162353', '2022-02-27 21:31:53.162353', 'checkList1', 'N', 1),
       ('2022-02-27 21:31:53.171756', '2022-02-27 21:31:53.171756', 'checkList2', 'N', 1);



INSERT INTO check_item (created_date, modified_date, check_flag, check_item_name, del_flag, check_list_id)
VALUES ('2022-02-27 21:31:53.173205', '2022-02-27 21:31:53.173205', 'Y', 'checkItem1', 'N', 1),
       ('2022-02-27 21:31:53.182185', '2022-02-27 21:31:53.182185', 'Y', 'checkItem2', 'N', 1),
       ('2022-02-27 21:31:53.18375', '2022-02-27 21:31:53.18375', 'N', 'checkItem3', 'N', 1),
       ('2022-02-27 21:31:53.184896', '2022-02-27 21:31:53.184896', 'N', 'checkItem4', 'N', 2),
       ('2022-02-27 21:31:53.18661', '2022-02-27 21:31:53.18661', 'N', 'checkItem5', 'N', 2);
