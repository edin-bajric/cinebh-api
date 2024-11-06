insert into projection (venueId, movieId)
values
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Avatar: The Way of Water')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Beetlejuice Beetlejuice')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Joker: Folie à Deux')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Napoleon')),
    ((select id from venue where name = 'Cineplex'),(select id from movie where title = 'Venom: The Last Dance'));