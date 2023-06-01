insert into AccountEntry (accountId, accountEntryTypeId, entryDate, entryAmountDate, concept, amount, observations)
values (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Test', 0, '');

insert into Idea (userId, creationDate, description, cost, benefits, name)
values (1, CURRENT_TIMESTAMP, 'description', 'cost', 'benefits', 'name');

insert into Interaction (creationDate, interest, description, file, fileMime, projectId, userId, interactionTypeId)
values (CURRENT_TIMESTAMP, 'LOW', 'test', 'asd', 'jpg', 1, 1, 1);

insert into Inventory (buyDate, cost, serialNumber, type, provider, trademark, model, speed, storage, ram, location,
                       description, asignedToId)
values (CURRENT_TIMESTAMP, 10.00, '1234', 'PC', 'provider', 'trademark', 'model', 'spped', 'storage', 'ram', 'location',
        'test', 1);

insert into Activity (userId, start, duration, billable, roleId, description, hasEvidences, end, approvalState)
values (1, '2023-05-24 09:00:00', 30, 1, 1, 'Test', 0, '2023-05-24 09:30:00', 'NA');

insert into Link (user, link) values ('user', 'link');

insert into Magazine (name, description) values ('name', 'description');
