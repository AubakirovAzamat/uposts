CREATE TABLE post_public.comment
(
    id          BIGINT AUTO_INCREMENT,
    user_id     BIGINT       NOT NULL,
    post_id   BIGINT       NOT NULL,
    text        VARCHAR(140) NOT NULL,
    time_insert TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) COLLATE utf8_bin;