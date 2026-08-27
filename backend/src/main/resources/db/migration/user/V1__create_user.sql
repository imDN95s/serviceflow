CREATE TABLE users
(
    id            UUID              NOT NULL,
    email         VARCHAR(255)      NOT NULL,
    password_hash VARCHAR(255)      NOT NULL,
    role          VARCHAR(50)       NOT NULL,
    status        VARCHAR(50)       NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
)