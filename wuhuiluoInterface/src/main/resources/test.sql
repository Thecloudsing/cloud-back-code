create table user_data (
                           id int unsigned primary key auto_increment,
                           username char(12) not null,
                           account char(32) not null,
                           password char(32) not null,
                           created_at timestamp not null default current_timestamp
) character set utf8 collate utf8_bin;


drop table user_data;
insert into user_data (username, account, password)
values
    ('root','123456','123456'),
    ('admin','123789','123789');

select * from user_data;


create table student (
                         id int unsigned,
                         studentName varchar(255),
                         birthday date,
                         grade varchar(10),
                         gender char(1),
                         studentId int unsigned primary key auto_increment,
                         school varchar(255),
                         created_at datetime not null default current_timestamp
);

insert into student(id, studentName, birthday, grade, gender, school)
values (1445, '吴辉烙', '2005-6-7', '2022大一', '男', '4455');

update student set id = 123 ,birthday = '2002-9-6' where school = '4455';

drop table teacher;

create table teacher (
                         id int unsigned primary key auto_increment,
                         teacherName varchar(255),
                         subjects varchar(255),
                         callPhoneNumber char(11),
                         onboardingTime date,
                         baseSalary double,
                         created_at datetime not null default current_timestamp
);

insert into teacher (id, teacherName, subjects, callPhoneNumber, onboardingTime, baseSalary)
values (545, 'lan', 'en', '12345678910', '2022-2-9', 14110.45);
