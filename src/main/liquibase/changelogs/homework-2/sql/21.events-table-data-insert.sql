insert into events (
                    id,
                    title,
                    artists_id,
                    locations_id,
                    description,
                    occupied_places,
                    age_limit,
                    date,
                    events_program_id )
values (
        1,
        'Death Race For Love',
        1,
        1,
        'Dj Khaled a hundred racks, i made another one (uh-huh)' ||
        'Dj Khaled with the tats, i got another one (uh-huh)',
        15,
        18,
        '2021-12-16',
        1 )