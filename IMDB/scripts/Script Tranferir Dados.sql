-- inserindo o diretor
INSERT INTO labbd11..director(id,name) SELECT directorid,dname FROM disciplinabd..directorsmovies GROUP BY directorid, dname
	
-- inserindo os filmes dos diretores	
INSERT INTO labbd11..director_movie (idMovie, idDirector,addition) SELECT movieid, directorid,addition FROM disciplinabd..directorsmovies WHERE movieid > 0 GROUP BY movieid, directorid, addition


-- inserindo os gêneros
INSERT INTO genre (genre) SELECT dbo.Trim(s.s) FROM disciplinabd..movies m CROSS APPLY dbo.Split(';', m.genres) S  WHERE m.genres IS NOT NULL 

-- criando uma tabela virtual com somente os genêros não repetidos
CREATE TABLE #temp_genre(
	id INT PRIMARY KEY IDENTITY,
	genre VARCHAR(100) NOT NULL
)

-- inserindo os generos não repetidos nesta tabela temporária
INSERT INTO #temp_genre SELECT genre FROM genre GROUP BY genre

-- apagando os generos 
DELETE FROM genre

-- passando os generos não repetidos para a tabela correta
INSERT INTO genre SELECT genre FROM #temp_genre


-- criando tabela temporária para manter as linguas diferentes pelo distinct
CREATE TABLE #temp_language(
	id INT PRIMARY KEY IDENTITY,
	language VARCHAR (100) NOT NULL
)

-- inserindo as línguas, garantindo a unicidade 
INSERT INTO #temp_language SELECT DISTINCT lang.language FROM language lang

-- apagando as linguas que foram inseridas pelo java
DELETE FROM language

-- movendo as linguas para a tabela correta
INSERT INTO language(language) SELECT t.language FROM #temp_language t


-- copiando os dados do filme para a tabela movie
INSERT INTO labbd11..movie(id, title, year) SELECT CASE WHEN IsNumeric(movies.movieid+ '.0e0') <> 1  THEN NULL ELSE CAST (movies.movieid AS INT) END , movies.title, CASE WHEN IsNumeric(movies.mvyear+ '.0e0') <> 1  THEN NULL ELSE CAST (movies.mvyear AS INT) END FROM disciplinabd..movies WHERE movieid <> '11111111111111' GROUP BY movieid, movies.title, movies.mvyear

INSERT INTO labbd11..movie(id, title, year) SELECT CASE WHEN IsNumeric(m.movieid+ '.0e0') <> 1  THEN NULL ELSE CAST (m.movieid AS INT) END , m.title, CASE WHEN IsNumeric(m.mvyear+ '.0e0') <> 1  THEN NULL ELSE CAST (m.mvyear AS INT) END FROM disciplinabd..moremovies m WHERE m.movieid <> '11111111111111' GROUP BY m.movieid, m.title, m.mvyear

INSERT INTO labbd11..actor(id, name, sex) SELECT DISTINCT CASE WHEN IsNumeric(movies.actorid+ '.0e0') <> 1  THEN NULL ELSE CAST (movies.actorid AS INT) END, movies.actorname, movies.sex FROM disciplinabd..movies

SELECT TOP (1000) CAST (movies.actorid AS INT), movies.actorname, movies.sex FROM disciplinabd..movies movies

SELECT TOP (1000) * FROM  disciplinabd..movies


INSERT INTO labbd11..actor_character(character) SELECT DISTINCT m.as_character FROM disciplinabd..movies m 

SELECT s FROM dbo.Split(' ', 'I hate bunnies')

SELECT dbo.TRIM(' leading trailing ')

INSERT TOP(10000) INTO labbd11..language(language) SELECT DISTINCT s.s FROM disciplinabd..movies m CROSS APPLY dbo.Split(';', m.languages) S


CREATE TABLE teste(
	id INT PRIMARY KEY
	)


SELECT TOP (1000) character FROM actor_character


DELETE FROM actor_character

-- inserindo os personagens dos atores
INSERT INTO labbd11..actor_character (character) 
SELECT DISTINCT
  case
    -- Test for not valid positions
    when Start.Pos = 1 or Stop.Pos = 0
    then m.as_character
    else substring(m.as_character, Start.Pos, Stop.Pos - Start.Pos)
  end
from disciplinabd..moremovies m 
  cross apply (select charindex('[', m.as_character)+1) as Start(Pos)
  cross apply (select charindex(']', m.as_character, Start.Pos)) as Stop(Pos)

SELECT TOP (1000) * FROM director

SELECT dname FROM disciplinabd..directorsmovies GROUP BY dname

SELECT * FROM director_movie

SELECT TOP (1000) languages FROM movies WHERE languages IS NOT NULL







































