CREATE table actions(
    action_id bigint not null,
    member_id bigint not null,
    guild_id bigint not null,
    action_Type varchar(64) not null,
    reason varchar(255) not null,
    applied_by bigint not null,
    date_applied timestamp not null DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (action_id,guild_id),
    FOREIGN KEY(member_id) REFERENCES members(member_id)ON DELETE CASCADE,
    FOREIGN KEY(guild_id) REFERENCES servers(guild_id)
);