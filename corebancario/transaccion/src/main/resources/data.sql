-- Cuentas
INSERT INTO public.cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
('478758', 'AHORRO', 2000, TRUE, 1),
('225487', 'CORRIENTE', 100, TRUE, 2),
('495878', 'AHORRO', 0, TRUE, 3),
('496825', 'AHORRO', 540, TRUE, 2),
('585545', 'CORRIENTE', 1000, TRUE, 1);


-- Movimientos
INSERT INTO public.movimiento (tipo_movimiento, valor, saldo, cuenta_id, fecha) VALUES
('DEBITO', -575, 1425, 1, NOW()),
('CREDITO', 600, 700, 2, NOW()),
('CREDITO', 150, 150, 3, NOW()),
('DEBITO', -540, 0, 4, NOW());