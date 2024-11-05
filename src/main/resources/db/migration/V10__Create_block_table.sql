CREATE TABLE post_public.block
(
    id              BIGINT AUTO_INCREMENT,
    user_id         BIGINT    NOT NULL,
    block_user_id BIGINT    NOT NULL,
    time_insert     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (block_user_id) REFERENCES user (id),
    UNIQUE user_id_block_user_id (user_id, block_user_id)
) COLLATE utf8_bin;