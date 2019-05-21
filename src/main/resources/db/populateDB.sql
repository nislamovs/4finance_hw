
-----------------------------------------------------   Users    -------------------------------------------------------------------------

INSERT INTO users(id, firstname, lastname, address, email, phone, is_blocked, created_date, last_modified_date) VALUES (1,  'Aleksandrs',  'Collins',     'Central Strees 13',                                     'awesomeclient21@gmail.com',    '8000233412'    , false, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO users(id, firstname, lastname, address, email, phone, is_blocked, created_date, last_modified_date) VALUES (2,  'Peteris',     'Klava',       '10 Old Mill Rd, Tomatin, Inverness IV13 7YW, UK',       'TheSickGamer@gmail.com',       '(288) 208-8088', false, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO users(id, firstname, lastname, address, email, phone, is_blocked, created_date, last_modified_date) VALUES (3,  'Vladislavs',  'Strass',      '13 Bathurst Rd, Norwich NR2 2PP, UK',                   'Exciting@gmail.com',           '(552) 691-6319', true, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO users(id, firstname, lastname, address, email, phone, is_blocked, created_date, last_modified_date) VALUES (4,  'Kenny',       'Jones',       '2 Court Rd, Port Glasgow PA14 5PR, UK',                 'Ashamed@gmail.com',            '(516) 789-5429', false, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO users(id, firstname, lastname, address, email, phone, is_blocked, created_date, last_modified_date) VALUES (5,  'Fred',        'Mazur',       '8 Rose Ct, Wickersley, Rotherham S66 1BH, UK',          'Dysfunctional@gmail.com',      '(368) 870-4044', false, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));


-----------------------------------------------------   Loans    -------------------------------------------------------------------------

-- User 1
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(1, 1, '5000', '6000', 100, UPPER('approved'), '146.69.210.250', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(2, 1, '12500', '13500', 60, UPPER('approved') , '240.173.125.253', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- User 2
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(3, 2, '9000', '10000', 15, UPPER('payed_off'), '91.109.195.201', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- User 3
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(4, 3, '3000', '4000',   220, UPPER('pending'), '138.53.233.154', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(5, 3, '7600', '0',   550, UPPER('rejected'), '29.235.195.231', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(6, 3, '40000', '41000',  30, UPPER('approved'), '54.76.99.178', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(7, 3, '1200', '2200',   50, UPPER('sent_to_collection'), '232.138.158.96', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- User 4
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(8, 4, '90000', '91000',  20, UPPER('approved'), '85.89.56.1', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(9, 4, '120000', '121000',  140, UPPER('payed_off'), '174.160.167.240', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(10, 4, '12342334', '0',  39, UPPER('rejected'), '12.94.143.160', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- User 5
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(11, 5, '45000', '46000',  320, UPPER('approved'), '230.119.91.140', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO loans(id, user_pk, loan_amount, debt, loan_term, status, ip_address, created_date, last_modified_date) VALUES(12, 5, '12000', '13000',  12, UPPER('payed_off'), '129.6.190.145', TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

--------------------------------------------------------  Loan extentions    --------------------------------------------------------------------

-- Loan 1
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(1,   1, 10, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 2
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(2,   2, 50, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(3,   2, 12, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 3
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(4,   3, 90, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(5,   3, 30, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(6,   3, 5, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 4
-- No extentions

-- Loan 5
-- No extentions

-- Loan 6
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(7,   6, 45, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 7
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(8,   7, 100, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(9,   7, 15, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 8
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(10,  8, 20, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 9
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(11, 10, 250, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(12, 10, 300, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(13, 10, 120, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 10
-- No extentions

-- Loan 11
INSERT INTO extentions (id, loan_pk, extention_period, created_date, last_modified_date) VALUES(14, 11, 180, TO_DATE('01/12/2017 13:45', 'DD/MM/YYYY HH24:MI'), TO_DATE('21/11/2018 13:45', 'DD/MM/YYYY HH24:MI'));

-- Loan 12
-- No extentions