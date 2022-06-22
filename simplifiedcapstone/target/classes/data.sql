--USERS
INSERT INTO `users` (`user_id`, `email`, `first_name`, `last_name`, `password`) VALUES (1, 'admin@admin.com', 'Joe', 'admin', '$2a$10$BQqez4boveJWubiZEGlRHelPggrfIHoe3DbsMTJ9Lxilwl62DSSCm');
INSERT INTO `users` (`user_id`, `email`, `first_name`, `last_name`, `password`) VALUES (2, 'user@user.com', 'user', 'user', '$2a$10$cItSwgDGLyZhBIAI6lMuLeuQ8MUD4tlpIBkVVRxS8zwoVaOBKiULO');
INSERT INTO `users` (`user_id`, `email`, `first_name`, `last_name`, `password`) VALUES (3, 'organizer@organizer.com', 'organizer', 'Dave', '$2a$10$FaKMxOTH77B/saI56Ru75.LReGLLAXloOEUrOwhOoL1O6tR5E7AhW');
INSERT INTO `users` (`user_id`, `email`, `first_name`, `last_name`, `password`) VALUES (4, 'follower@follower.com', 'Jane', 'Follower', '$2a$10$FDOe9ewRtCV.LDHukEcZ9efIrfqpOQhkrRoC.eQ/HDV5DBN1zCnDu');

--EVENTS
INSERT INTO `events` (`event_id`, `date`, `description`, `location`, `event_name`, `time`) VALUES (1, '2022-06-30', 'Event', '555 Event Drive, Event Eventon', 'Event', '00:45:00');
INSERT INTO `events` (`event_id`, `date`, `description`, `location`, `event_name`, `time`) VALUES (2, '2022-06-23', 'weqrwea', 'ZXCZXCZ', 'sadfasdf', '00:00:00');
INSERT INTO `events` (`event_id`, `date`, `description`, `location`, `event_name`, `time`) VALUES (3, '2022-07-06', 'This is a really cool event', '101 101 Drive Street', 'Awesome Event', '17:30:00');
INSERT INTO `events` (`event_id`, `date`, `description`, `location`, `event_name`, `time`) VALUES (4, '2022-07-14', 'Hello! You are probably wondering where I am right now. Hey guys Im in New York City just hanging ou-', 'New York City, Minecraft', 'The Best Event', '12:00:00');


--USERTYPES
INSERT INTO `usertypes` (`type_id`, `usertype_name`, `wheelchair`) VALUES (1, 'Autism', b'0');
INSERT INTO `usertypes` (`type_id`, `usertype_name`, `wheelchair`) VALUES (2, 'TBI', b'1');
INSERT INTO `usertypes` (`type_id`, `usertype_name`, `wheelchair`) VALUES (3, 'Blindness', b'0');
INSERT INTO `usertypes` (`type_id`, `usertype_name`, `wheelchair`) VALUES (4, 'Deafness', b'0');
INSERT INTO `usertypes` (`type_id`, `usertype_name`, `wheelchair`) VALUES (5, 'Paraplegic', b'1');

--ROLES
INSERT INTO `roles` (`role_id`, `user_role`) VALUES (1, 'Organizer');
INSERT INTO `roles` (`role_id`, `user_role`) VALUES (2, 'Attendee');

--USER_TYPES
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (1, 1);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (1, 2);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (1, 3);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (3, 1);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (3, 2);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (3, 3);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (2, 3);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (2, 4);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (2, 5);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (4, 2);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (4, 3);
INSERT INTO `user_types` (`user_user_id`, `types_type_id`) VALUES (4, 4);

--USER_ROLES
INSERT INTO `user_roles` (`user_user_id`, `roles_role_id`) VALUES (1, 1);
INSERT INTO `user_roles` (`user_user_id`, `roles_role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_user_id`, `roles_role_id`) VALUES (3, 1);
INSERT INTO `user_roles` (`user_user_id`, `roles_role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_user_id`, `roles_role_id`) VALUES (4, 2);

--USER_EVENTS
INSERT INTO `user_events` (`user_user_id`, `event_id`) VALUES (1, 1);
INSERT INTO `user_events` (`user_user_id`, `event_id`) VALUES (1, 2);
INSERT INTO `user_events` (`user_user_id`, `event_id`) VALUES (3, 3);
INSERT INTO `user_events` (`user_user_id`, `event_id`) VALUES (3, 4);

--USER_FOLLOWED_EVENTS
INSERT INTO `user_followed_events` (`user_user_id`, `followed_events_event_id`) VALUES (1, 4);
INSERT INTO `user_followed_events` (`user_user_id`, `followed_events_event_id`) VALUES (2, 4);
INSERT INTO `user_followed_events` (`user_user_id`, `followed_events_event_id`) VALUES (2, 3);

--EVENT_TYPES
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (1, 1);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (4, 3);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (4, 1);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (3, 4);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (3, 5);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (3, 2);
INSERT INTO `event_types` (`event_event_id`, `types_type_id`) VALUES (2, 3);


