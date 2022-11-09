CREATE SEQUENCE token_sequence
    START WITH 100003421
    INCREMENT BY 105332;
CREATE table warden_tokens(
    id bigint DEFAULT (nextval('token_sequence')),
    token varchar(255) not null UNIQUE,
    is_Claimed boolean not null DEFAULT false,
    claimed_by bigint UNIQUE DEFAULT NULL,

    FOREIGN KEY(claimed_by) REFERENCES servers(guild_id) ON DELETE CASCADE ,
    PRIMARY KEY (id,token),
    CHECK ((is_Claimed = true AND claimed_by IS NOT NULL) OR
           (is_Claimed = false AND claimed_by IS NULL))
);