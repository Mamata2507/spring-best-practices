create sequence student_sequence;

create table student
(
    id            bigint not null
                    constraint student_pkey
                    primary key DEFAULT nextval('student_sequence'),
    date_of_birth date   not null,
    email         text   not null
        constraint student_email_unique unique,
    name          text   not null
);
