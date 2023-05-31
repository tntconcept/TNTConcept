insert into Idea (userId, creationDate, description, cost, benefits, name) values (1, CURRENT_TIMESTAMP, 'description', 'cost', 'benefits', 'name');

insert into Interaction (creationDate, interest, description, file, fileMime, projectId, userId, interactionTypeId) values (CURRENT_TIMESTAMP, 'LOW', 'test', 'asd', 'jpg', 1, 1, 1);