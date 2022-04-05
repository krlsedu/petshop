create table if not exists oauth_access_token
(
    token_id          varchar(256) not null
    constraint oauth_access_token_pkey
    primary key,
    token             bytea,
    authentication_id varchar(256) not null,
    user_name         varchar(256),
    client_id         varchar(256),
    authentication    bytea,
    refresh_token     varchar(256)
    );

alter table oauth_access_token
    owner to postgres;

create index if not exists oauth_access_token_authentication_id_index
    on oauth_access_token (authentication_id);

create table if not exists oauth_approvals
(
    userid         varchar(256),
    clientid       varchar(256),
    scope          varchar(256),
    status         varchar(10),
    expiresat      timestamp,
    lastmodifiedat timestamp
    );

alter table oauth_approvals
    owner to postgres;

create table if not exists oauth_client_details
(
    client_id               varchar(256) not null
    constraint oauth_client_details_pkey
    primary key,
    resource_ids            varchar(256),
    client_secret           varchar(256),
    scope                   varchar(256),
    authorized_grant_types  varchar(256),
    web_server_redirect_uri varchar(256),
    authorities             varchar(256),
    access_token_validity   integer,
    refresh_token_validity  integer,
    additional_information  varchar(4096),
    autoapprove             varchar(256)
    );

alter table oauth_client_details
    owner to postgres;

create table if not exists oauth_client_token
(
    token_id          varchar(256),
    token             bytea,
    authentication_id varchar(256) not null
    constraint oauth_client_token_pkey
    primary key,
    user_name         varchar(256),
    client_id         varchar(256)
    );

alter table oauth_client_token
    owner to postgres;

create table if not exists oauth_code
(
    code           varchar(256),
    authentication bytea
    );

alter table oauth_code
    owner to postgres;

create table if not exists oauth_refresh_token
(
    token_id       varchar(256),
    token          bytea,
    authentication bytea
    );

alter table oauth_refresh_token
    owner to postgres;

INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES ('pet-shop', 'pet-shop', 'foo,read,write','password,authorization_code,refresh_token', null, null, 36000, 36000,
        null, true)