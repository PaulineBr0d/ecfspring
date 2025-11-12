INSERT INTO project_owner (id, name) VALUES
(1, 'Alice Martin'),
(2, 'Bob Durand'),
(3, 'Claire Petit'),
(4, 'David Moreau'),
(5, 'Emma Lefevre');

INSERT INTO developer (id, name, description, technologies, experience) VALUES
(1, 'Jean Dupont', 'Développeur backend', 'Java, Spring', 5),
(2, 'Sophie Bernard', 'Développeuse fullstack', 'JavaScript, React, Spring', 4),
(3, 'Marc Leroy', 'Développeur frontend', 'Angular, HTML, CSS', 3),
(4, 'Julie Simon', 'Développeuse mobile', 'Kotlin, Swift', 6),
(5, 'Paul Richard', 'Développeur data', 'Python, SQL, Spark', 7);


INSERT INTO project (id, title, description, delivery_date, budget, theme, project_owner_id) VALUES
(1, 'SuperFin', 'Application de gestion', '2025-12-01', 15000.0, 'Gestion', 1),
(2, 'Neish', 'Site e-commerce', '2025-11-15', 20000.0, 'Commerce', 2),
(3, 'MyDoctor', 'Application mobile santé', '2026-01-10', 18000.0, 'Santé', 3),
(4, 'FlixNet', 'Plateforme de streaming', '2025-12-20', 25000.0, 'Divertissement', 4),
(5, 'PowerBi','Dashboard analytique', '2026-02-01', 22000.0, 'Data', 5);


INSERT INTO application (id, submission_date, status, developer_id, project_id) VALUES
(1, '2025-10-01', 'Pending', 1, 1),
(2, '2025-10-03', 'Accepted', 2, 2),
(3, '2025-10-05', 'Rejected', 3, 3),
(4, '2025-10-07', 'Pending', 4, 4),
(5, '2025-10-09', 'Accepted', 5, 5);
ALTER TABLE application ALTER COLUMN id RESTART WITH 6;
ALTER TABLE project ALTER COLUMN id RESTART WITH 6;