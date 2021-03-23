use db;

create table Users(
Email varchar(30) not null,
Password varchar(30) not null,
UserType varchar(10) not null, 
constraint Users_PK primary key (Email),
constraint participants_ibfk CHECK (UserType IN ('Student','Instructor'))
);

create table Course(
CourseID int not null, 
Name varchar(30),
Term varchar(10),
AllowAnonymous bool,
constraint Course_PK primary key (CourseID)
);

create table Folder(
FolderID int not null,
FolderName varchar(30), 
CourseID int not null,
ParentFolderID int,
constraint Folder_PK primary key (FolderID),
constraint Folder_FK1 foreign key (CourseID) references Course(CourseID) on update cascade on delete cascade,
constraint Folder_FK2 foreign key (ParentFolderID) references Folder(FolderID) on update cascade on delete cascade
);

create table Members(
Email varchar(30) not null,
CourseID int not null,
constraint Members_PK primary key (Email, CourseID),
constraint Members_FK1 foreign key (Email) references Users(Email) on update cascade on delete cascade,
constraint Members_FK2 foreign key (CourseID) references Course(CourseID) on update cascade on delete cascade
);

create table Thread(
ThreadID int not null, 
Tag varchar(25),
Title varchar(30),
FolderID int not null,
constraint Thread_PK primary key (ThreadID),
constraint Thread_FK foreign key (FolderID) references Folder(FolderID) on update cascade on delete cascade,
constraint participants_ibfk2 CHECK (Tag IN ('Questions', 'Announcements', 'Homework', 'Homework solutions', 'Lectures notes', 'General announcements'))
);

create table ViewThread(
Email varchar(30) not null,
ThreadID int not null,
constraint ViewThread_PK primary key (Email, ThreadID),
constraint ViewThread_FK1 foreign key (Email) references Users(Email) on update cascade on delete cascade,
constraint ViewThread_FK2 foreign key (ThreadID) references Thread(ThreadID) on update cascade on delete cascade
);

create table Post(
PostID int not null, 
Description varchar(500),
Email varchar(30) not null, 
isAnonymous bool, 
ThreadID int not null, 
constraint Post_PK primary key (PostID, ThreadID),
constraint Post_FK1 foreign key (Email) references Users(Email) on update cascade on delete cascade,
constraint Post_FK2 foreign key (ThreadID) references Thread(ThreadID) on update cascade on delete cascade
);

create table LikePost(
PostID int not null,
Email varchar(30) not null,
constraint LikePost_PK primary key (PostID, Email),
constraint LikePost_FK1 foreign key (PostID) references Post(PostID) on update cascade on delete cascade, 
constraint LikePost_FK2 foreign key (Email) references Users(Email) on update cascade on delete cascade
);

create table Link(
ThreadID int not null, 
SimilarThreadID int not null, 
constraint Link_PK primary key (ThreadID, SimilarThreadID), 
constraint Link_FK1 foreign key (ThreadID) references Thread(ThreadID) on update cascade on delete cascade,
constraint Link_FK2 foreign key (SimilarThreadID) references Thread(ThreadID) on update cascade on delete cascade
);

insert into Users values("stud@example.com", "123", "Student");
insert into Course values(1, "Databaser", "vaar", true);
insert into Folder values(1, "Exam", 1, null);
