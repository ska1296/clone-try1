-- Database: clone

-- DROP DATABASE clone;

## CREATE DATABASE:
```
CREATE DATABASE clone
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
```


## CREATE TABLES:
```
drop table users;
create table users (UUID uuid, username varchar(255), password varchar(255));
drop table AUTH;
create table AUTH (UUID uuid, AUTH_TOKEN varchar(255));
drop table POST;
create table POST (POST_DATA varchar(140), UUID uuid, CREATETIME varchar(100), POST_UUID uuid);
drop table FOLLOWS;
create table FOLLOWS (FOLLOWER_UUID uuid, FOLLOWS_UUID uuid);
drop table LIKES;
create table LIKES (USER_UUID uuid, POST_UUID uuid);
```


## READ FROM TABLES:
```
select * from users;
select * from AUTH;
select * from post;
select * from FOLLOWS;
select * from LIKES;
```