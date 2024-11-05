CREATE TABLE post_public.post_tag
(
    id          BIGINT AUTO_INCREMENT,
    post_id   BIGINT NOT NULL,
    tag_id      BIGINT NOT NULL,
    time_insert TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id),
    UNIQUE `post_id_tag_id` (`post_id`, `tag_id`)
) COLLATE utf8_bin;