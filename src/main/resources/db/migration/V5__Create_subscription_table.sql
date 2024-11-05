CREATE TABLE post_public.subscription
(
    id          BIGINT AUTO_INCREMENT,
    sub_user_id BIGINT    NOT NULL,
    pub_user_id BIGINT    NOT NULL,
    time_insert TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (sub_user_id) REFERENCES user (id),
    FOREIGN KEY (pub_user_id) REFERENCES user (id),
    UNIQUE KEY sub_user_id_pub_user_id (sub_user_id, pub_user_id)
) COLLATE utf8_bin;