CREATE TABLE authorized_servers(
    guild_id bigint not null PRIMARY KEY,
    token varchar(255) not null,
    is_Validated boolean not null DEFAULT FALSE,

    FOREIGN KEY (guild_id) REFERENCES servers(guild_id)
)