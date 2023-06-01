insert into AccountEntry (accountId, accountEntryTypeId, entryDate, entryAmountDate, concept, amount, observations) values (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Test', 0, '');

insert into Idea (userId, creationDate, description, cost, benefits, name) values (1, CURRENT_TIMESTAMP, 'description', 'cost', 'benefits', 'name');

insert into Interaction (creationDate, interest, description, file, fileMime, projectId, userId, interactionTypeId) values (CURRENT_TIMESTAMP, 'LOW', 'test', 'asd', 'jpg', 1, 1, 1);

insert into Inventory (buyDate, cost, serialNumber, type, provider, trademark, model, speed, storage, ram, location, description, asignedToId) values (CURRENT_TIMESTAMP, 10.00, '1234', 'PC', 'provider', 'trademark', 'model', 'spped', 'storage', 'ram', 'location', 'test', 1);

insert into Link (user, link) values ('user', 'link');