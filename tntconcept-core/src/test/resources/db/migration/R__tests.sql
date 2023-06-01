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

insert into Objective(userId, projectId, startDate, endDate, state, name) 
values (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PENDING', 'name');

insert into Occupation (projectid, userid, startdate, enddate, description, duration)
values (1,1,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'description', 1);

insert into RequestHoliday (beginDate, finalDate, state, userId, observations, userComment, chargeYear)
values ('2023-04-24', '2023-04-27', 'PENDING', 1, '', 'Test', '2023-04-24');

insert into archimedes_security_subject (principal_name, attributes)
values ('admin@autentia.com', '{"sub": "1"}');

insert into Contact (name, email, phone, mobile, notified, email2, phone2, fax, address, postalCode, city, country)
values ('name', 'test@test.com', '11111111111111', '11111111111111', 1, 'test2@test.com', '22222222222222', '22222222222222', 'address', '28000', 'madrid', 'spain');

insert into Offer (number, title, description, userId, organizationId, contactId, creationDate, offerPotential, offerState, observations, showIvaIntoReport)
values ('123456', 'title1', 'description', 1, 1, 1, CURRENT_TIMESTAMP, 'HIGH', 'ACCEPTED', 'observations', 1);

insert into OfferCost (offerId, name, cost, billable, units)
values (1, 'name', 1.00, 1, 1.00);