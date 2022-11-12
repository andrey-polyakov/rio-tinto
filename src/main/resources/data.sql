INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (1, 'admin@test.com', 'John Galsworthy', 'admin010101', null, now(), 1, null, null);

INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (2, 'john.doe@test.com', 'John Doe', 'john123', null, now(), 1, null, null);

INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (3, 'jane.doe@test.com', 'Jane Doe', 'jane456', null, now(), 1, null, null);

INSERT INTO DEPARTMENTS (id, name, manager_id, created_at, created_by, updated_at, updated_by)
values (1, 'IT department', 2, now(), 1, null, null);

INSERT INTO DEPARTMENTS (id, name, manager_id, created_at, created_by, updated_at, updated_by)
values (2, 'Sales department', 3, now(), 1, null, null);

INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (4, 'john.galsworthy@test.com', 'John Galsworthy', 'john334456', 1, now(), 1, null, null);

INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (5, 'mark.twain@test.com', 'Mark Twain', 'mark1111', 1, now(), 1, null, null);

INSERT INTO EMPLOYEES (id, email, full_name, password, dep_id, created_at, created_by, updated_at, updated_by)
values (6, 'michael.fox@test.com', 'Michael J. Fox', 'michael000', 2, now(), 1, null, null);