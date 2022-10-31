CREATE table warden_tokens(
    token varchar(255) not null PRIMARY KEY,
    is_Claimed boolean not null DEFAULT false,
    claimed_by bigint UNIQUE,

    FOREIGN KEY(claimed_by) REFERENCES servers(guild_id),
    CHECK ((is_Claimed = true AND claimed_by != NULL) OR
           (is_Claimed = false AND claimed_by = NULL))
);