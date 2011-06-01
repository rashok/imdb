
SELECT DISTINCT TOP 1000  movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111'

SELECT DISTINCT movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111'

SELECT * FROM disciplinabd..movies WHERE movieid = '220825'

SELECT * FROM language WHERE idLanguage = 25613615

SELECT DISTINCT TOP 1000  movieid,languages FROM disciplinabd..moremovies WHERE languages IS NOT NULL AND movieid <> '11111111111111'

SELECT * FROM language

UPDATE language SET language = 'Ancient' WHERE language = ', Ancient  '

SELECT DISTINCT TOP 1000 idLanguage, language FROM language

SELECT * FROM disciplinabd..movies WHERE movieid = '1744815'


SELECT DISTINCT TOP 1000 languages FROM disciplinabd..movies WHERE languages IS NOT NULL

SELECT * FROM disciplinabd..movies WHERE movieid = '1014161'

SELECT * FROM disciplinabd..moremovies WHERE movieid = '1014161'

SELECT * FROM language WHERE idLanguage  = 25613419

SELECT * FROM movie WHERE id = 1014161

SELECT * FROM language_movie

DELETE FROM language_movie
INSERT INTO language_movie (idLanguage, idMovie, infAdd) VALUES (25613579, 643061, 'oi');


SELECT top 1000 movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages

SELECT TOP 1000  movieid,languages FROM disciplinabd..movies WHERE languages IS NOT NULL AND movieid <> '11111111111111' GROUP BY movieid, languages

SELECT idLanguage, language FROM language GROUP BY idLanguage, language


DELETE FROM language_movie

UPDATE language SET language = 'Serbo-Croatian' WHERE language = 'Serbo'

SELECT * FROM language_movie


DELETE FROM labbd11..movie

