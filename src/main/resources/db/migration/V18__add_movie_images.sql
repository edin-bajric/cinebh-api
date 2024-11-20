INSERT INTO movieImage (movieId, isCoverImage, url)
VALUES
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), FALSE, 'https://i.postimg.cc/8PsztgkP/avatar1.webp'),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), FALSE, 'https://i.postimg.cc/Y0DqMbRH/avatar2.jpg'),
    ((SELECT id FROM movie WHERE title = 'Avatar: The Way of Water'), FALSE, 'https://i.postimg.cc/Kc0YP82H/avatar3.jpg');