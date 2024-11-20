update projection set venueId = (select id from venue where city = 'Tuzla') where movieId = (select id from movie where title = 'Napoleon');

INSERT INTO movie (title, rating, language, length, description, director, startDate, endDate, trailerUrl)
VALUES
    ('Terrifier 3', 'Not Rated', 'English', '125', 'Art the Clown is set to unleash chaos on the unsuspecting residents of Miles County as they peacefully drift off to sleep on Christmas Eve.', 'Damien Leone', '2024-11-19', '2024-12-25', 'https://www.youtube.com/watch?v=zaPcin5knJk'),
    ('Gladiator II', 'R', 'English', '148', 'After his home is conquered by the tyrannical emperors who now lead Rome, Lucius is forced to enter the Colosseum and must look to his past to find strength to return the glory of Rome to its people.', 'Ridley Scott', '2024-11-30', '2025-01-15', 'https://www.youtube.com/watch?v=4rgYUipGJNo'),
    ('Hellboy: The Crooked Man', 'R', 'English', '99', 'Hellboy and a rookie B.P.R.D. agent in the 1950s are sent to the Appalachians, where they discover a remote community dominated by witches and led by the sinister local demon, the Crooked Man.', 'Brian Taylor', '2024-11-30', '2025-01-15', 'https://www.youtube.com/watch?v=4fw2PIpndnM'),
    ('Alien: Romulus', 'R', 'English', '119', 'While scavenging the deep ends of a derelict space station, a group of young space colonists come face to face with the most terrifying life form in the universe.', 'Fede Alvarez', '2024-11-22', '2025-01-15', 'https://youtu.be/OzY2r2JXsDM?si=AvBnhxfzOcHaIP3G'),
    ('The Wild Robot', 'PG', 'English', '102', 'After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island''s animals and cares for an orphaned baby goose.', 'Chris Sanders', '2024-11-30', '2025-01-15', 'https://www.youtube.com/watch?v=67vbA5ZJdKQ');

INSERT INTO movieImage (movieId, isCoverImage, url)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), TRUE, 'https://i.postimg.cc/zfC8smhn/terrifier.jpg'),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), TRUE, 'https://i.postimg.cc/pr8wFzBK/gladiator.webp'),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), TRUE, 'https://i.postimg.cc/667sG268/hellboy.webp'),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), TRUE, 'https://i.postimg.cc/jqBp6xCZ/alien.jpg'),
    ((SELECT id FROM movie WHERE title = 'The Count of Monte-Cristo'), TRUE, 'https://i.postimg.cc/MHqLnT9G/count.jpg');

INSERT INTO genre (name) VALUES ('Animation');

INSERT INTO movieGenre (movieId, genreId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM genre WHERE name = 'Horror')),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM genre WHERE name = 'Comedy')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM genre WHERE name = 'Action')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM genre WHERE name = 'Epic')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM genre WHERE name = 'Adventure')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM genre WHERE name = 'Drama')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM genre WHERE name = 'Action')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM genre WHERE name = 'Horror')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM genre WHERE name = 'Superhero')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM genre WHERE name = 'Horror')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM genre WHERE name = 'Sci-Fi')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM genre WHERE name = 'Thriller')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM genre WHERE name = 'Sci-Fi')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM genre WHERE name = 'Animation'));

INSERT INTO writer (name) VALUES ('Damien Leone'), ('Peter Craig'), ('David Franzoni'), ('Christopher Golden'), ('Mike Mignola'), ('Brian Taylor'),
                                 ('Dan O''Bannon'), ('Ronald Shusett'), ('Fede Alvarez'), ('Chris Sanders'), ('Peter Brown');

INSERT INTO movieWriter (movieId, writerId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM writer WHERE name = 'Damien Leone')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM writer WHERE name = 'David Scarpa')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM writer WHERE name = 'Peter Craig')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM writer WHERE name = 'David Franzioni')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM writer WHERE name = 'Christopher Golden')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM writer WHERE name = 'Mike Mignola')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM writer WHERE name = 'Brian Taylor')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM writer WHERE name = 'Dan O''Bannon')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM writer WHERE name = 'Ronald Shusett')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM writer WHERE name = 'Fede Alvarez')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM writer WHERE name = 'Chris Sanders')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM writer WHERE name = 'Peter Brown'));

INSERT INTO performer (name, role) values ('Lauren LaVera', 'Sienna'), ('David Howard Thornton', 'Art the Clown'),
                                          ('Antonella Rose', 'Gabbie'), ('Connie Nielsen', 'Lucilla'), ('Paul Mescal', 'Lucius'), ('Pedro Pascal', 'Marcus Acacius'),
                                          ('Jack Kesy', 'Hellboy'), ('Jefferson White', 'Tom Ferrell'), ('Adeline Rudolph', 'Bobbie Jo Song'),
                                          ('Cailee Spaeny', 'Rain'), ('David Jonsson', 'Andy'), ('Archie Renaux', 'Tyler'), ('Lupita Nyong''o', 'Roz'), ('Pedro Pascal', 'Fink'), ('Kit Connor', 'Brightbill');

INSERT INTO moviePerformer (movieId, performerId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM performer WHERE name = 'Lauren LaVera')),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM performer WHERE name = 'David Howard Thornton')),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), (SELECT id FROM performer WHERE name = 'Antonella Rose')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM performer WHERE name = 'Connie Nielsen')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM performer WHERE name = 'Paul Mescal')),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), (SELECT id FROM performer WHERE role = 'Marcus Acacius')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM performer WHERE name = 'Jack Kesy')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM performer WHERE name = 'Jefferson White')),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), (SELECT id FROM performer WHERE name = 'Adeline Rudolph')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM performer WHERE name = 'Cailee Spaeny')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM performer WHERE name = 'David Jonsson')),
    ((SELECT id FROM movie WHERE title = 'Alien: Romulus'), (SELECT id FROM performer WHERE name = 'Archie Renaux')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM performer WHERE name = 'Lupita Nyong''o')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM performer WHERE role = 'Fink')),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), (SELECT id FROM performer WHERE name = 'Kit Connor'));

INSERT INTO movieRating (movieId, name, rating)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), 'IMDB Rating', '6.5'),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), 'Rotten Tomatoes', '77%'),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), 'IMDB Rating', '7.0'),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), 'Rotten Tomatoes', '76%'),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), 'IMDB Rating', '4.5'),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), 'Rotten Tomatoes', '37%'),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), 'IMDB Rating', '7.2'),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), 'Rotten Tomatoes', '80%'),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), 'IMDB Rating', '8.3'),
    ((SELECT id FROM movie WHERE title = 'The Wild Robot'), 'Rotten Tomatoes', '98%');

