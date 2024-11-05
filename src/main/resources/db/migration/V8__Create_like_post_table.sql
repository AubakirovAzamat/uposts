CREATE TABLE post_public.like_post
(
    id          BIGINT AUTO_INCREMENT,
    post_id   BIGINT    NOT NULL,
    user_id     BIGINT    NOT NULL,
    time_insert TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    UNIQUE `post_id_user_id` (`post_id`, `user_id`)
) COLLATE utf8_bin;