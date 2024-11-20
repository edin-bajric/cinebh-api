truncate projectiontime;
insert into projectionTime (projectionId, time)
select id, time
from projection, unnest(array['12:00:00', '16:00:00', '18:00:00', '20:00:00']::time[]) as time;