CREATE table server_entities(
    guild_id bigint NOT NULL,
    entity_name varchar(75) not null,
    entity_id bigint not null,

    FOREIGN KEY(guild_id) REFERENCES servers(guild_id) ON DELETE CASCADE,
    PRIMARY KEY(entity_id,guild_id)
)