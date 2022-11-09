CREATE table actions(
    action_id bigint not null PRIMARY KEY,
    member_id bigint not null,
    guild_id bigint not null,
    action_Type varchar(64) not null,
    applied_by bigint not null,
    date_applied timestamp not null DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY(member_id) REFERENCES members(member_id)ON DELETE CASCADE,
    FOREIGN KEY(guild_id) REFERENCES servers(guild_id)
);