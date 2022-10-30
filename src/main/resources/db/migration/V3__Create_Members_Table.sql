CREATE TABLE members(
    member_id bigint not null PRIMARY KEY,
    strikes int not null DEFAULT 0,
    warnings int not null DEFAULT 0,
    bans int not null DEFAULT 0
)