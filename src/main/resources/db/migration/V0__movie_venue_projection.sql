CREATE TABLE movie (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                       title VARCHAR(45) NOT NULL,
                       rating VARCHAR(45),
                       language VARCHAR(45),
                       length VARCHAR(45),
                       description TEXT,
                       director VARCHAR(255),
                       startDate DATE,
                       endDate DATE,
                       trailerUrl VARCHAR(45)
);

CREATE TABLE writer (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE performer (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                           name VARCHAR(255) NOT NULL,
                           role VARCHAR(255)
);

CREATE TABLE genre (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                       name VARCHAR(45) NOT NULL
);

CREATE TABLE movieWriter (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                             movieId UUID,
                             writerId UUID,
                             FOREIGN KEY (movieId) REFERENCES movie(id),
                             FOREIGN KEY (writerId) REFERENCES writer(id)
);

CREATE TABLE moviePerformer (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                                movieId UUID,
                                performerId UUID,
                                FOREIGN KEY (movieId) REFERENCES movie(id),
                                FOREIGN KEY (performerId) REFERENCES performer(id)
);

CREATE TABLE movieGenre (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                            movieId UUID,
                            genreId UUID,
                            FOREIGN KEY (movieId) REFERENCES movie(id),
                            FOREIGN KEY (genreId) REFERENCES genre(id)
);

CREATE TABLE movieRating (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                             name VARCHAR(45) NOT NULL,
                             rating VARCHAR(45),
                             movieId UUID,
                             FOREIGN KEY (movieId) REFERENCES movie(id)
);

CREATE TABLE movieImage (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                            movieId UUID,
                            isCoverImage BOOLEAN,
                            url VARCHAR(255),
                            FOREIGN KEY (movieId) REFERENCES movie(id)
);

CREATE TABLE venue (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                       name VARCHAR(45) NOT NULL,
                       phone VARCHAR(45),
                       street VARCHAR(255),
                       streetNumber VARCHAR(45),
                       city VARCHAR(45),
                       imageURL VARCHAR(255)
);

CREATE TABLE projection (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
                            venueId UUID,
                            movieId UUID,
                            projectionTime TIME,
                            FOREIGN KEY (venueId) REFERENCES venue(id),
                            FOREIGN KEY (movieId) REFERENCES movie(id)
);
