--Personas
INSERT INTO public.persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
('Jose Lema', 'Masculino', 35, '172001', 'Otavalo sn y principal', '098254785'),
('Marianela Montalvo', 'Femenino', 28, '172002', 'Amazonas y NNUU', '097548965'),
('Juan Osorio', 'Masculino', 40, '172003', '13 junio y Equinoccial', '098874587');

-- Clientes
INSERT INTO public.cliente (id, contrasena, estado) VALUES
(1, '1234', TRUE),
(2, '5678', TRUE),
(3, '1245', TRUE);