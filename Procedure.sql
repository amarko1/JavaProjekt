CREATE PROCEDURE CreateActor
    @FirstName nvarchar(50),
    @LastName nvarchar(50),
	@IDActor int OUTPUT
AS
BEGIN
    INSERT INTO Actor (FirstName, LastName)
    VALUES (@FirstName, @LastName)
	SET @IDActor = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateActor
    @IDActor int,
    @FirstName nvarchar(50),
    @LastName nvarchar(50)
AS 
BEGIN 
    UPDATE Actor
    SET FirstName = @FirstName,
        LastName = @LastName
    WHERE IDActor = @IDActor
END


GO


CREATE PROCEDURE DeleteActor
    @IDActor int
AS 
BEGIN 
    DELETE FROM Actor
    WHERE IDActor = @IDActor
END


GO

CREATE PROCEDURE SelectActor
    @IDActor int
AS 
BEGIN 
    SELECT * FROM Actor
    WHERE IDActor = @IDActor
END


GO


CREATE PROCEDURE SelectActors
AS 
BEGIN 
    SELECT * FROM Actor
END


GO


CREATE PROCEDURE CreateUser
    @Username nvarchar(50),
    @Password nvarchar(50),
    @Role nvarchar(40),
    @IDUser int OUTPUT
AS 
BEGIN 
    INSERT INTO ApplicationUser (Username, Password, Role)
    VALUES (@Username, @Password, @Role)
    SET @IDUser = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateUser
    @IDUser int,
    @Username nvarchar(50),
    @Password nvarchar(50),
    @Role nvarchar(40)
AS 
BEGIN 
    UPDATE ApplicationUser
    SET Username = @Username,
        Password = @Password,
        Role = @Role
    WHERE IDUser = @IDUser
END


GO


CREATE PROCEDURE DeleteUser
    @IDUser int
AS 
BEGIN 
    DELETE FROM ApplicationUser
    WHERE IDUser = @IDUser
END


GO


CREATE PROCEDURE SelectUser
    @IDUser int
AS 
BEGIN 
    SELECT * FROM ApplicationUser
    WHERE IDUser = @IDUser
END


GO


CREATE PROCEDURE SelectUsers
AS 
BEGIN 
    SELECT * FROM ApplicationUser
END


GO



CREATE PROCEDURE CreateDirector
    @FirstName nvarchar(50),
    @LastName nvarchar(50),
    @IDDirector int OUTPUT
AS 
BEGIN 
    INSERT INTO Director (FirstName, LastName)
    VALUES (@FirstName, @LastName)
    SET @IDDirector = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateDirector
    @IDDirector int,
    @FirstName nvarchar(50),
    @LastName nvarchar(50)
AS 
BEGIN 
    UPDATE Director
    SET FirstName = @FirstName,
        LastName = @LastName
    WHERE IDDirector = @IDDirector
END


GO


CREATE PROCEDURE DeleteDirector
    @IDDirector int
AS 
BEGIN 
    DELETE FROM Director
    WHERE IDDirector = @IDDirector
END


GO


CREATE PROCEDURE SelectDirector
    @IDDirector int
AS 
BEGIN 
    SELECT * FROM Director
    WHERE IDDirector = @IDDirector
END


GO


CREATE PROCEDURE SelectDirectors
AS 
BEGIN 
    SELECT * FROM Director
END


GO


CREATE OR ALTER PROCEDURE CreateMovie
    @Title nvarchar(100),
    @PublishedDate DateTime,
    @Description nvarchar(max),
    @OriginalTitle nvarchar(100),
    @Duration int,
    @YearOfRelease smallint,
    @Genre nvarchar(100),
    @Poster nvarchar(500),
    @Link nvarchar(max),
    @Reservation nvarchar(max),
    @DisplayDate Datetime,
    @Trailer nvarchar(100),
	@IDMovie int OUTPUT
AS
BEGIN
    INSERT INTO Movie (Title, PublishedDate, Description, OriginalTitle, Duration, YearOfRelease, Genre, Poster, Link, Reservation, DisplayDate, Trailer)
    VALUES (@Title, @PublishedDate, @Description, @OriginalTitle, @Duration, @YearOfRelease, @Genre, @Poster, @Link, @Reservation, @DisplayDate, @Trailer)
	SET @IDMovie = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateMovie
    @IDMovie int,
    @Title nvarchar(100),
    @PublishedDate DateTime,
    @Description nvarchar(max),
    @OriginalTitle nvarchar(100),
    @Duration int,
    @YearOfRelease smallint,
    @Genre nvarchar(100),
    @Poster nvarchar(500),
    @Link nvarchar(max),
    @Reservation nvarchar(max),
    @DisplayDate Datetime,
    @Trailer nvarchar(100)
AS
BEGIN
    UPDATE Movie
    SET Title = @Title, PublishedDate = @PublishedDate, Description = @Description, OriginalTitle = @OriginalTitle,
        Duration = @Duration, YearOfRelease = @YearOfRelease, Genre = @Genre, Poster = @Poster, Link = @Link,
        Reservation = @Reservation, DisplayDate = @DisplayDate, Trailer = @Trailer
    WHERE IDMovie = @IDMovie
END


GO


CREATE PROCEDURE DeleteMovie
    @IDMovie int
AS
BEGIN
    DELETE FROM Movie
    WHERE IDMovie = @IDMovie
END


GO

CREATE PROCEDURE SelectMovie
    @IDMovie int
AS
BEGIN
    SELECT * FROM Movie
    WHERE IDMovie = @IDMovie
END


GO


CREATE PROCEDURE SelectMovies
AS
BEGIN
    SELECT * FROM Movie
END


GO


CREATE PROCEDURE CreateMovieActor
    @MovieId int,
    @ActorId int,
    @IDMovieActor int OUTPUT
AS 
BEGIN 
    INSERT INTO MovieActor (MovieId, ActorId)
    VALUES (@MovieId, @ActorId)
    SET @IDMovieActor = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateMovieActor
    @IDMovieActor int,
    @MovieId int,
    @ActorId int
AS 
BEGIN 
    UPDATE MovieActor
    SET MovieId = @MovieId,
        ActorId = @ActorId
    WHERE IDMovieActor = @IDMovieActor
END


GO


CREATE PROCEDURE DeleteMovieActor
    @IDMovieActor int
AS 
BEGIN 
    DELETE FROM MovieActor
    WHERE IDMovieActor = @IDMovieActor;
END;


GO


CREATE PROCEDURE SelectMovieActor
    @IDMovieActor int
AS 
BEGIN 
    SELECT * FROM MovieActor
    WHERE IDMovieActor = @IDMovieActor;
END;


GO


CREATE PROCEDURE SelectMovieActors
AS 
BEGIN 
    SELECT * FROM MovieActor;
END;


GO


CREATE OR ALTER PROCEDURE CreateMovieDirector
    @MovieId int,
    @DirectorId int,
	@IDMovieDirector int OUTPUT
AS 
BEGIN 
    INSERT INTO MovieDirector (MovieId, DirectorId)
    VALUES (@MovieId, @DirectorId)
	SET @IDMovieDirector = SCOPE_IDENTITY()
END


GO


CREATE PROCEDURE UpdateMovieDirector
    @IDMovieDirector int,
    @MovieId int,
    @DirectorId int
AS 
BEGIN 
    UPDATE MovieDirector
    SET MovieId = @MovieId,
        DirectorId = @DirectorId
    WHERE IDMovieDirector = @IDMovieDirector
END


GO


CREATE PROCEDURE DeleteMovieDirector
    @IDMovieDirector int
AS 
BEGIN 
    DELETE FROM MovieDirector
    WHERE IDMovieDirector = @IDMovieDirector
END


GO


CREATE PROCEDURE SelectMovieDirector
    @IDMovieDirector int
AS 
BEGIN 
    SELECT * FROM MovieDirector
    WHERE IDMovieDirector = @IDMovieDirector
END


GO


CREATE PROCEDURE SelectMovieDirectors
AS 
BEGIN 
    SELECT * FROM MovieDirector
END


GO


CREATE PROCEDURE DeleteAll
AS 
BEGIN 
	delete from MovieDirector
	delete from MovieActor
	delete from Director
	delete from Actor
	delete from Movie
END


GO


CREATE OR ALTER PROC AuthenticateApplicationUser
	@Username NVARCHAR(50),
	@Password NVARCHAR(50)
AS 
BEGIN 
	select * from ApplicationUser
	where Username = @Username and Password = @Password
END

GO
