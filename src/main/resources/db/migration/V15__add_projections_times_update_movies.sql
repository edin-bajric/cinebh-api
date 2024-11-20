insert into projection (venueId, movieId)
values
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Terrifier 3')),
    ((select id from venue where city = 'Tuzla'),(select id from movie where title = 'Gladiator II')),
    ((select id from venue where city = 'Tuzla' and city = 'Sarajevo'),(select id from movie where title = 'Hellboy: The Crooked Man')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Alien: Romulus')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'The Wild Robot'));


delete from movierating where rating = '7.2';
delete from movierating where rating = '80%';

INSERT INTO movieRating (movieId, name, rating)
VALUES
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), 'IMDB Rating', '7.2'),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), 'Rotten Tomatoes', '80%');

insert into projectionTime (projectionId, time)
select id, time
from projection, unnest(array['12:00:00', '16:00:00', '18:00:00', '20:00:00']::time[]) as time;