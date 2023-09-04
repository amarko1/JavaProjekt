create database JavaProject
go

use JavaProject

go

create table ApplicationUser
(
	IDUser int primary key identity,
	Username nvarchar(50),
	Password nvarchar(50),
	Role nvarchar(40)
)

go

create table Director
(
	IDDirector int primary key identity,
	FirstName nvarchar(50),
	LastName nvarchar(50)
)

go

create table Actor
(
	IDActor int primary key identity,
	FirstName nvarchar(50),
	LastName nvarchar(50)
)

go

create table Movie
(
	IDMovie int primary key identity,
	Title nvarchar(100),
	PublishedDate DateTime,
	Description nvarchar(max),
	OriginalTitle nvarchar(100),
	Duration int,
	YearOfRelease smallint,
	Genre nvarchar(100),
	Poster nvarchar(500),
	Link nvarchar(max),
	Reservation nvarchar(max),
	DisplayDate Datetime,
	Trailer nvarchar(100)
)

go

create table MovieDirector
(
	IDMovieDirector int primary key identity,
	MovieId int not null,
	DirectorId int not null,
	FOREIGN KEY (MovieId) REFERENCES Movie(IDMovie) ON DELETE CASCADE,
    FOREIGN KEY (DirectorId) REFERENCES Director(IDDirector) ON DELETE CASCADE
)

go

create table MovieActor
(
	IDMovieActor int primary key identity,
	MovieId int not null,
	ActorId int not null,
	FOREIGN KEY (MovieId) REFERENCES Movie(IDMovie) ON DELETE CASCADE,
    FOREIGN KEY (ActorId) REFERENCES Actor(IDActor) ON DELETE CASCADE
)

go

insert into ApplicationUser(Username, Password, Role) values('admin', 'admin123', 'Admin')

select * from ApplicationUser