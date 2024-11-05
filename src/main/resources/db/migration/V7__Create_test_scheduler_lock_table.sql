CREATE TABLE test_scheduler_lock
(
    instance_name VARCHAR(64) NOT NULL,
    time_insert   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);