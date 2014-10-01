drop table teacher;
drop table student;
drop table assistentteacher;
drop table roleschool;
drop table person;

create table person(
    id integer primary key,
    firstName varchar(15),
    lastName varchar(30),
    phone varchar(8),
    email varchar(30)
);

create table roleschool(
    id integer primary key,
    "role" varchar(15),
    constraint FK_ROLE_ID foreign key (id) references person(id)
);

create table teacher(
    id integer primary key,
    degree varchar(20),
    constraint FK_TEACHER_ID foreign key (id) references roleschool(id)
);

create table student(
    id integer primary key,
    semester varchar(15),
    constraint FK_STUDENT_ID foreign key (id) references roleschool(id)
);

create table assistentteacher(
    id integer primary key,
    constraint FK_ASSISTENTTEACHER_ID foreign key (id) references roleschool(id)
);
