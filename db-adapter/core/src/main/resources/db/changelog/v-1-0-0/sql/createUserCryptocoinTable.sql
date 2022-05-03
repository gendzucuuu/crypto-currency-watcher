CREATE TABLE user_cryptocoin
(
    id            SERIAL NOT NULL,
    username      VARCHAR(255)          NOT NULL,
    cryptocoin_id BIGINT NOT NULL ,
    CONSTRAINT pk_user_cryptocoin PRIMARY KEY (id)
);

ALTER TABLE user_cryptocoin
    ADD CONSTRAINT FK_USER_CRYPTOCOIN_ON_CRYPTOCOIN FOREIGN KEY (cryptocoin_id) REFERENCES cryptocoin (id);