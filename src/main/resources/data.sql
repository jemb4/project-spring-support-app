-----------------
-- Insert rols --
-----------------
INSERT INTO rols (id, name) VALUES (default, 'support');
INSERT INTO rols (id, name) VALUES (default, 'employee');

------------------
-- Insert users --
------------------
INSERT INTO users (id, name, surname, rol_id) VALUES (default, 'Peter', 'Doe', 1);
INSERT INTO users (id, name, surname, rol_id) VALUES (default, 'Peter', 'Martin', 2);
INSERT INTO users (id, name, surname, rol_id) VALUES (default, 'Miguel', 'Doe', 1);

-------------------
-- Insert topics --
-------------------
INSERT INTO topics (id, name, description) VALUES (default, 'Blue Screen', 'A Blue screen error');

---------------------
-- Insert requests --
---------------------
INSERT INTO requests (id, request_date, description, assisted, user_id, topic_id) 
VALUES (default, '2025-01-15', 'El equipo muestra pantalla azul al iniciar Windows', false, 1, 1);

INSERT INTO requests (id, request_date, description, assisted, user_id, topic_id) 
VALUES (default, '2024-01-10', 'Pantalla azul después de actualizar controladores', true, 1, 1);

INSERT INTO requests (id, request_date, description, assisted, user_id, topic_id) 
VALUES (default, '2024-01-20', 'Error de pantalla azul al ejecutar aplicaciones pesadas', false, 1, 1);

INSERT INTO requests (id, request_date, description, assisted, user_id, topic_id) 
VALUES (default, '2024-01-05', 'BSOD después de instalar nuevo software', true, 1, 1);

INSERT INTO requests (id, request_date, description, assisted, user_id, topic_id) 
VALUES (default, '2024-01-18', 'Pantalla azul aparece aleatoriamente durante el trabajo', false, 1, 1);