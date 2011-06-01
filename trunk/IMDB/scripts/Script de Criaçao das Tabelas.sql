DROP TABLE actor
CREATE TABLE actor (
	id INT PRIMARY KEY,
	name VARCHAR(250) NOT NULL, 
	sex CHAR(1) NOT NULL
)

/* não será mais necessária
DROP TABLE characters
CREATE TABLE characters(
	id INT PRIMARY KEY IDENTITY,
	character VARCHAR(100) UNIQUE NOT NULL
)*/

DROP TABLE director
CREATE TABLE director(
	id INT PRIMARY KEY,
	name VARCHAR(500) NOT NULL,
)

DROP TABLE director_movie
CREATE TABLE director_movie(
	idMovie INT,
	idDirector INT,
	addition VARCHAR(500),
	CONSTRAINT pk_director_addition PRIMARY KEY (idMovie, idDirector, addition)
)


DROP TABLE movie
CREATE TABLE movie(
	id BIGINT PRIMARY KEY,
	title VARCHAR(400) NOT NULL,
	year INT
)


DROP TABLE language
CREATE TABLE language(
	id INT PRIMARY KEY IDENTITY,
	language VARCHAR (100) UNIQUE
)


DROP TABLE genre
CREATE TABLE genre(
	id INT PRIMARY KEY IDENTITY,
	genre VARCHAR(100) NOT NULL UNIQUE
)

DROP TABLE director_movie
CREATE TABLE director_movie(
	idDirector INT,
	idMovie INT,
	CONSTRAINT fk_director_movie_1 FOREIGN KEY (idDirector) REFERENCES director(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_director_movie_2 FOREIGN KEY (idMovie) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pk_director_movie PRIMARY KEY(idDirector,idMovie)
)

DROP TABLE genre_movie
CREATE TABLE genre_movie(
	idGenre INT,
	idMovie INT,
	CONSTRAINT fk_genre_movie_1 FOREIGN KEY (idMovie) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_genre_movie_2 FOREIGN KEY (idGenre) REFERENCES genre(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pk_genre_movie PRIMARY KEY (idMovie, idGenre)
)

DROP TABLE language_movie
CREATE TABLE language_movie(
	idLanguage INT,
	idMovie INT,
	infAdd VARCHAR (100),
	CONSTRAINT fk_language_movie_1 FOREIGN KEY (idLanguage) REFERENCES language(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_language_movie_2 FOREIGN KEY (idMovie) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pk_language_movie PRIMARY KEY (idLanguage, idMovie, infAdd)	
)

DROP TABLE movie_actor
CREATE TABLE movie_actor(
	idMovie INT,
	idActor INT,
	character VARCHAR(100),
	CONSTRAINT fk_movie_actor_1 FOREIGN KEY (idMovie) REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_movie_actor_2 FOREIGN KEY (idActor) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pk_movie_actor PRIMARY KEY (idMovie,idActor, character)
)




