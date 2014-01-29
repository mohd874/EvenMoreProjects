# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table booking (
  id                        bigint not null,
  date_created              timestamp,
  date_modified             timestamp,
  time_from                 timestamp,
  time_to                   timestamp,
  type                      varchar(5),
  constraint ck_booking_type check (type in ('Class','Event')),
  constraint pk_booking primary key (id))
;

create table course (
  id                        bigint not null,
  date_created              timestamp,
  date_modified             timestamp,
  label                     varchar(255),
  description               varchar(255),
  constraint pk_course primary key (id))
;

create table room (
  id                        bigint not null,
  date_created              timestamp,
  date_modified             timestamp,
  label                     varchar(255),
  capacity                  integer,
  constraint pk_room primary key (id))
;

create table teacher (
  id                        bigint not null,
  date_created              timestamp,
  date_modified             timestamp,
  name                      varchar(255),
  constraint pk_teacher primary key (id))
;

create table user (
  id                        bigint not null,
  date_created              timestamp,
  date_modified             timestamp,
  username                  varchar(255),
  password_hash             varchar(255),
  constraint pk_user primary key (id))
;


create table teacher_course (
  teacher_id                     bigint not null,
  course_id                      bigint not null,
  constraint pk_teacher_course primary key (teacher_id, course_id))
;
create sequence booking_seq;

create sequence course_seq;

create sequence room_seq;

create sequence teacher_seq;

create sequence user_seq;




alter table teacher_course add constraint fk_teacher_course_teacher_01 foreign key (teacher_id) references teacher (id) on delete restrict on update restrict;

alter table teacher_course add constraint fk_teacher_course_course_02 foreign key (course_id) references course (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists booking;

drop table if exists course;

drop table if exists teacher_course;

drop table if exists room;

drop table if exists teacher;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists booking_seq;

drop sequence if exists course_seq;

drop sequence if exists room_seq;

drop sequence if exists teacher_seq;

drop sequence if exists user_seq;

