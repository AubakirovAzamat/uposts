CREATE TABLE post_public.tag
(
    id          BIGINT AUTO_INCREMENT,
    text        VARCHAR(25) NOT NULL,
    time_insert TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE (text)
) COLLATE utf8_bin;
INSERT INTO tag(text) VALUE ('море');