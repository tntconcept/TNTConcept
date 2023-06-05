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

insert into Link (user, link)
values ('user', 'link');

insert into Magazine (name, description)
values ('name', 'description');

insert into Objective(userId, projectId, startDate, endDate, state, name)
values (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PENDING', 'name');

insert into Occupation (projectid, userid, startdate, enddate, description, duration)
values (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'description', 1);

insert into RequestHoliday (beginDate, finalDate, state, userId, observations, userComment, chargeYear)
values ('2023-04-24', '2023-04-27', 'PENDING', 1, '', 'Test', '2023-04-24');

insert into archimedes_security_subject (principal_name, attributes)
values ('admin@autentia.com', '{"sub": "1"}');

insert into Contact (name, email, phone, mobile, notified, email2, phone2, fax, address, postalCode, city, country)
values ('name', 'test@test.com', '11111111111111', '11111111111111', 1, 'test2@test.com', '22222222222222',
        '22222222222222', 'address', '28000', 'madrid', 'spain');

insert into Offer (number, title, description, userId, organizationId, contactId, creationDate, offerPotential,
                   offerState, observations, showIvaIntoReport)
values ('123456', 'title1', 'description', 1, 1, 1, CURRENT_TIMESTAMP, 'HIGH', 'ACCEPTED', 'observations', 1);

insert into OfferCost (offerId, name, cost, billable, units)
values (1, 'name', 1.00, 1, 1.00);

insert into Bill (creationDate, paymentMode, state, number, name, observations, projectId, startBillDate, endBillDate,
                  billType, orderNumber, contactId, providerId, accountId, submitted, billCategoryId, provideService,
                  billRegimeId, deductibleIVAPercentage, freelanceIRPFPercentage)
values ('2023-03-13', 'MONEY', 'EMITTED', 'Test-001', 'Test', '', 5, '2023-03-13', '2023-03-20', 'ISSUED',
        'Order-Test-001', 1, 1, 1, 1, 1, 1, 1, 0, 15);

insert into BillBreakDown (billId, concept, units, amount, iva)
values (1, 'Test', 1.00, 777.77, 21.00);

insert into OfferRole (offerId, name, costPerHour, expectedHours, iva)
values (1, 'name', 10.00, 2, 10.00);

insert into Book (name, author, ISBN, URL, price)
values ('Test', 'Test', 'Test', 'http://url', 33.33);

insert into BulletinBoard (categoryId, userId, creationDate, message, title, documentPath, documentContentType)
values (1, 1, '2023-03-13', '', 'Test', '', '');

insert into Collaborator (userId, contactId, organizationId)
values (1, 1, 1);

insert into Commissioning (requestDate, name, scope, content, products, deliveryDate, budget, notes, authorSignature,
                           reviserSignature, adminSignature, justifyInformation, developedActivities,
                           difficultiesAppeared, results, conclusions, evaluation, status, projectId)
values (CURRENT_TIMESTAMP, 'Test', '', '', '', CURRENT_TIMESTAMP, 10.20, '', 0, 0, 0, 0, '', '', '', '', '', 'CREATED',
        5);

insert into CompanyState (userId, creationDate, description)
values (1, CURRENT_TIMESTAMP, 'Test');

insert into PeriodicalAccountEntry (id, accountId, accountEntryTypeId, frequencyId, concept, entryDate, amount, rise,
                                    observations, ownerId, departmentId, organizationId, insertDate, updateDate)
values (1, 1, 1, 1, "Concept", CURRENT_TIMESTAMP, 75.0, 5.0, "Observations", 1, 1, 1, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

insert into CreditTitle (number, concept, amount, state, type, issueDate, organizationId, observations)
values ('001', 'Test', 33.33, 'EMITTED', 'RECEIVED', CURRENT_TIMESTAMP, 1, '');

insert into Document (id, creationDate, name, description, ownerId, departmentId, insertDate, updateDate)
values (1, CURRENT_TIMESTAMP, 'Test', '', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into ProjectCost (id, projectId, name, cost, billable, ownerId, departmentId, insertDate, updateDate)
values (1, 1, 'Cost', 2.0, true, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into Holiday (description, date)
values ('Test', CURRENT_TIMESTAMP);

insert into FinancialRatio (title, ratioDate, banksAccounts, customers, stocks, amortizations, infrastructures,
                            shortTermLiability, obligationBond, capital, reserves, incomes, expenses,
                            otherExploitationExpenses, financialExpenses, taxes)
values ('Test', CURRENT_TIMESTAMP, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00);

insert into Publication (name, magazineId, accepted)
values ('publicationname', 1, 1);

insert into Setting (type, name, value)
values ('STRING', 'Test', '');

insert into DocumentVersion (documentPath, creationDate, version, documentid)
values ('', CURRENT_TIMESTAMP, '1', 1);
