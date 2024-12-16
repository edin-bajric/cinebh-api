INSERT INTO movieImage (movieId, isCoverImage, url)
VALUES
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), FALSE, 'https://i.postimg.cc/rpRyr65c/terrifier1.jpg'),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), FALSE, 'https://i.postimg.cc/g2vNkjTP/terrifier2.avif'),
    ((SELECT id FROM movie WHERE title = 'Terrifier 3'), FALSE, 'https://i.postimg.cc/02HbM0hY/terrifier3.webp'),

    ((SELECT id FROM movie WHERE title = 'Gladiator II'), FALSE, 'https://i.postimg.cc/y6DdqZDW/gladiator1.webp'),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), FALSE, 'https://i.postimg.cc/bwg8SyZK/gladiator2.avif'),
    ((SELECT id FROM movie WHERE title = 'Gladiator II'), FALSE, 'https://i.postimg.cc/g00pvCRr/gladiator3.webp'),

    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), FALSE, 'https://i.postimg.cc/Bvw0Nmcp/hellboy1.jpg'),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), FALSE, 'https://i.postimg.cc/1z4xgphq/hellboy2.avif'),
    ((SELECT id FROM movie WHERE title = 'Hellboy: The Crooked Man'), FALSE, 'https://i.postimg.cc/RF9csTS3/hellboy3.jpg');