DROP TABLE actor
CREATE TABLE actor (
	id INT PRIMARY KEY IDENTITY,
	name VARCHAR(200) NOT NULL, 
	sex VARCHAR(1) NOT NULL
)

DROP TABLE actor_character
CREATE TABLE actor_character(
	id INT PRIMARY KEY IDENTITY,
	character VARCHAR(100)
)

DROP TABLE director
CREATE TABLE director(
	id INT PRIMARY KEY IDENTITY,
	name VARCHAR(200) NOT NULL,
	addition VARCHAR(150)
)


DROP TABLE movie
CREATE TABLE movie(
	id INT PRIMARY KEY IDENTITY,
	title VARCHAR(200) NOT NULL,
	year INT
)


DROP TABLE language
CREATE TABLE language(
	id INT PRIMARY KEY IDENTITY,
	language VARCHAR (100) NOT NULL
)

DROP TABLE genre
CREATE TABLE genre(
	id INT PRIMARY KEY IDENTITY,
	genre VARCHAR(100) NOT NULL
)

DROP TABLE director_movie
CREATE TABLE director_movie(
	idDirector INT,
	idMovie INT,
	CONSTRAINT fk_director_movie_1 FOREIGN KEY (idDirector) REFERENCES director(id),
	CONSTRAINT fk_director_movie_2 FOREIGN KEY (idMovie) REFERENCES movie(id),
	CONSTRAINT pk_director_movie PRIMARY KEY(idDirector,idMovie)
)

DROP TABLE genre_movie
CREATE TABLE genre_movie(
	idMovie INT,
	idGenre INT,
	CONSTRAINT fk_genre_movie_1 FOREIGN KEY (idMovie) REFERENCES movie(id),
	CONSTRAINT fk_genre_movie_2 FOREIGN KEY (idGenre) REFERENCES genre(id),
	CONSTRAINT pk_genre_movie PRIMARY KEY (idMovie, idGenre)
)

DROP TABLE language_movie
CREATE TABLE language_movie(
	
)
