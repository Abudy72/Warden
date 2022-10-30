CREATE table servers(
    guild_id bigint not null PRIMARY KEY ,
    name varchar(255) not null,
    owner varchar(255) not null
);