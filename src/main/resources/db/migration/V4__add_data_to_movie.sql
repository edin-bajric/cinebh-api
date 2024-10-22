INSERT INTO movie (title, rating, language, length, description, director, startDate, endDate, trailerUrl)
VALUES
    ('Avatar: The Way of Water', 'PG-13', 'English', '192', 'Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na''vi race to protect their home.', 'James Cameron', '2024-10-22', '2024-10-29', 'https://youtu.be/d9MyW72ELq0?si=l07St8tpn_ttMKf_'),
    ('Beetlejuice Beetlejuice', 'PG-13', 'English', '105', 'After a family tragedy, three generations of the Deetz family return home to Winter River. Still haunted by Beetlejuice, Lydia''s life is turned upside down when her teenage daughter, Astrid, accidentally opens the portal to the Afterlife.', 'Tim Burton', '2024-10-22', '2024-10-29', 'https://youtu.be/CoZqL9N6Rx4?si=-TUbti_7ZUfPGRc0'),
    ('Joker: Folie à Deux', 'R', 'English', '138', 'Struggling with his dual identity, failed comedian Arthur Fleck meets the love of his life, Harley Quinn, while incarcerated at Arkham State Hospital.', 'Todd Phillips', '2024-10-22', '2024-10-29', 'https://youtu.be/_OKAwz2MsJs?si=6qhRayNn_BIWMBLQ'),
    ('Napoleon', 'R', 'English', '158', 'An epic that details the chequered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.', 'Ridley Scott', '2024-10-22', '2024-10-29', 'https://youtu.be/OAZWXUkrjPc?si=2WyJnIuZWtMI0jHu'),
    ('Venom: The Last Dance', 'PG-13', 'English', '109 minutes', 'Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie''s last dance.', 'Kelly Marcel', '2024-10-22', '2024-10-29', 'https://youtu.be/__2bjWbetsA?si=3CTFmkNKZA-w_RQ7');

INSERT INTO movieImage (movieId, isCoverImage, url)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), TRUE, 'https://i.postimg.cc/Vkb2YxCD/avatar.jpg'),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), TRUE, 'https://i.postimg.cc/90MNsK2P/beetlejuice.jpg'),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), TRUE, 'https://i.postimg.cc/qRpFxgZ5/joker.jpg'),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), TRUE, 'https://i.postimg.cc/mrfJhm3R/napoleon.jpg'),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), TRUE, 'https://i.postimg.cc/cHTb9Zp4/venom.jpg');

INSERT INTO genre (name) VALUES ('Sci-Fi'), ('Comedy'), ('Thriller'), ('Historical'), ('Action'), ('Superhero'), ('Adventure'), ('Fantasy'), ('Horror'), ('Crime'), ('Musical'), ('Drama'), ('Biography'), ('Epic');

INSERT INTO movieGenre (movieId, genreId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM genre WHERE name = 'Sci-Fi')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM genre WHERE name = 'Action')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM genre WHERE name = 'Adventure')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM genre WHERE name = 'Fantasy')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM genre WHERE name = 'Comedy')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM genre WHERE name = 'Horror')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM genre WHERE name = 'Fantasy')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM genre WHERE name = 'Thriller')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM genre WHERE name = 'Musical')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM genre WHERE name = 'Drama')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM genre WHERE name = 'Thriller')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM genre WHERE name = 'Crime')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM genre WHERE name = 'Historical')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM genre WHERE name = 'Epic')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM genre WHERE name = 'Action')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM genre WHERE name = 'Adventure')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM genre WHERE name = 'Biography')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM genre WHERE name = 'Action')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM genre WHERE name = 'Superhero')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM genre WHERE name = 'Adventure')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM genre WHERE name = 'Sci-Fi')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM genre WHERE name = 'Thriller'));

INSERT INTO writer (name) VALUES ('James Cameron'), ('Todd Phillips'), ('Kelly Marcel'), ('Tom Hardy'), ('Rick Jaffa'), ('Amanda Silver'), ('Michael McDowell'), ('Larry Wilson'), ('Alfred Gough'), ('Scott Silver'), ('Bob Kane'), ('David Scarpa');

INSERT INTO movieWriter (movieId, writerId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM writer WHERE name = 'James Cameron')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM writer WHERE name = 'Rick Jaffa')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM writer WHERE name = 'Amanda Silver')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM writer WHERE name = 'Michael McDowell')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM writer WHERE name = 'Larry Wilson')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM writer WHERE name = 'Alfred Gough')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM writer WHERE name = 'Todd Phillips')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM writer WHERE name = 'Scott Silver')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM writer WHERE name = 'Bob Kane')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM writer WHERE name = 'David Scarpa')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM writer WHERE name = 'Kelly Marcel')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM writer WHERE name = 'Tom Hardy'));

INSERT INTO performer (name, role) VALUES ('Sam Worthington', 'Jake Sully'), ('Zoe Saldana', 'Neytiri'), ('Sigourney Weaver', 'Kiri'), ('Michael Keaton', 'Beetlejuice'), ('Winona Ryder', 'Lydia Deetz'), ('Catherine O''Hara', 'Delia Deetz'), ('Joaquin Phoenix', 'Arthur Fleck'), ('Lady Gaga', 'Lee Quinzel'), ('Joaquin Phoenix', 'Napoleon Bonaparte'), ('Venessa Kirby', 'Josephine Bonaparte'), ('Tahar Rahim', 'Paul Barras'), ('Tom Hardy', 'Eddie Brock'), ('Juno Temple', 'Dr. Payne');

INSERT INTO moviePerformer (movieId, performerId)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM performer WHERE name = 'Sam Worthington')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM performer WHERE name = 'Zoe Saldana')),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), (SELECT id FROM performer WHERE name = 'Sigourney Weaver')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM performer WHERE name = 'Michael Keaton')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM performer WHERE name = 'Winona Ryder')),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), (SELECT id FROM performer WHERE name = 'Catherine O''Hara')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM performer WHERE role = 'Arthur Fleck')),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), (SELECT id FROM performer WHERE name = 'Lady Gaga')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM performer WHERE role = 'Napoleon Bonaparte')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM performer WHERE name = 'Vanessa Kirby')),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), (SELECT id FROM performer WHERE name = 'Tahar Rahim')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM performer WHERE name = 'Tom Hardy')),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), (SELECT id FROM performer WHERE name = 'Juno Temple'));

INSERT INTO movieRating (movieId, name, rating)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), 'IMDB Rating', '7.5'),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), 'Rotten Tomatoes', '76%'),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), 'IMDB Rating', '6.9'),
    ((SELECT id FROM movie WHERE title = 'Beetlejuice Beetlejuice'), 'Rotten Tomatoes', '77%'),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), 'IMDB Rating', '5.3'),
    ((SELECT id FROM movie WHERE title = 'Joker: Folie à Deux'), 'Rotten Tomatoes', '32%'),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), 'IMDB Rating', '6.4'),
    ((SELECT id FROM movie WHERE title = 'Napoleon'), 'Rotten Tomatoes', '58%'),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), 'IMDB Rating', '6.0'),
    ((SELECT id FROM movie WHERE title = 'Venom: The Last Dance'), 'Rotten Tomatoes', '42%');