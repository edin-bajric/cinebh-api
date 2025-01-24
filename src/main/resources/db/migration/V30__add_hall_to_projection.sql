update projection
set hallId = (
    select h.id
    from hall h
    where h.name = 'Hall 1'
)
where movieId = (
    select m.id
    from movie m
    where m.title = 'Avatar: The Way of Water'
);