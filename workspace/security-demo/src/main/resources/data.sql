insert into users(username, password, enabled) values ('hema', '$2a$12$O2kltwQyR.vgN6yFO9pDTur3z.BUvDAjcx0Lwfx3WDGMSpctDpm1K', 1);

insert into users(username, password, enabled) values ('smitha', '$2a$12$7Qsq0XkmJU.v8G4SLdSlSeU4F4KFrbYio12zbVqO6CbJVnMyBjtgG', 1);

insert into authorities (username, authority) values ('hema', 'ROLE_USER');

insert into authorities (username, authority) values ('smitha', 'ROLE_USER');
insert into authorities (username, authority) values ('smitha', 'ROLE_ADMIN');
