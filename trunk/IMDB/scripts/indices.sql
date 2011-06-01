create index indiceGenero
on labbd11..genre(genre)
DROP INDEX indiceGenero ON labbd11..genre

create index indiceMovieGenero
on labbd11..genre_movie(idMovie)
DROP INDEX indiceMovieGenero ON labbd11..genre_movie


create index indiceMovieLanguage
on labbd11..language_movie(idMovie)
DROP INDEX indiceMovieLanguage ON labbd11..language_movie


create index indiceActorMovie
on labbd11..movie_actor(idMovie)
DROP INDEX indiceActorMovie ON labbd11..movie_actor

create index indiceLinguagem
on labbd11..language(language)

create index indiceAtores
on labbd11..movie_actor(


-- depois se for ser necessario excluir este indice
--DROP INDEX indiceGenero ON labbd11..genre(genre)


select * from labbd11..language_movie