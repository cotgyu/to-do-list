-- `to-do-list`.label definition

CREATE TABLE IF NOT EXISTS `label`
(
    `label_id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `created_date`  datetime(6) DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `color`         varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `del_flag`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `label_name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `to-do-list`.monthly_user_register_stats definition

CREATE TABLE IF NOT EXISTS `monthly_user_register_stats`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT,
    `count` bigint(20) DEFAULT NULL,
    `month` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `to-do-list`.`user` definition

CREATE TABLE IF NOT EXISTS `user`
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


-- `to-do-list`.user_board_stats definition

CREATE TABLE IF NOT EXISTS `user_board_stats`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `count`   bigint(20) DEFAULT NULL,
    `name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- `to-do-list`.board definition

CREATE TABLE IF NOT EXISTS `board`
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


-- `to-do-list`.topic definition

CREATE TABLE IF NOT EXISTS `topic`
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


-- `to-do-list`.card definition

CREATE TABLE IF NOT EXISTS `card`
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


-- `to-do-list`.card_label definition

CREATE TABLE IF NOT EXISTS `card_label`
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


-- `to-do-list`.check_list definition

CREATE TABLE IF NOT EXISTS `check_list`
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


-- `to-do-list`.check_item definition

CREATE TABLE IF NOT EXISTS `check_item`
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

CREATE TABLE IF NOT EXISTS BATCH_JOB_INSTANCE  (
                                     JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
                                     VERSION BIGINT ,
                                     JOB_NAME VARCHAR(100) NOT NULL,
                                     JOB_KEY VARCHAR(32) NOT NULL,
                                     constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION  (
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

CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION_PARAMS  (
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

CREATE TABLE IF NOT EXISTS BATCH_STEP_EXECUTION  (
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

CREATE TABLE IF NOT EXISTS BATCH_STEP_EXECUTION_CONTEXT  (
                                               STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                               SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                               SERIALIZED_CONTEXT TEXT ,
                                               constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
                                                   references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION_CONTEXT  (
                                              JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                              SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                              SERIALIZED_CONTEXT TEXT ,
                                              constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
                                                  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS BATCH_STEP_EXECUTION_SEQ (
                                          ID BIGINT NOT NULL,
                                          UNIQUE_KEY CHAR(1) NOT NULL,
                                          constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ);

CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION_SEQ (
                                         ID BIGINT NOT NULL,
                                         UNIQUE_KEY CHAR(1) NOT NULL,
                                         constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ);

CREATE TABLE IF NOT EXISTS BATCH_JOB_SEQ (
                               ID BIGINT NOT NULL,
                               UNIQUE_KEY CHAR(1) NOT NULL,
                               constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ);


