INSERT INTO CLIENTES (dni, nombre, apellido1, apellido2, fecha_nacimiento) VALUES ('11111111A', 'Juan', 'Pérez', 'López', '1959-09-12');
INSERT INTO CLIENTES (dni, nombre, apellido1, apellido2, fecha_nacimiento) VALUES ('22222222B', 'Raúl', 'Canales', 'Rodriguez', '1985-03-01');
INSERT INTO CLIENTES (dni, nombre, apellido1, apellido2, fecha_nacimiento) VALUES ('33333333C', 'Elena', 'Ruiz', 'Herrera', '2010-05-10');
INSERT INTO CLIENTES (dni, nombre, apellido1, apellido2, fecha_nacimiento) VALUES ('44444444D', 'Raquel', 'Ruiz', 'Herrera', '2002-06-21');
INSERT INTO CLIENTES (dni, nombre, apellido1, apellido2, fecha_nacimiento) VALUES ('55555555E', 'María', 'Sánchez', 'Torres', '1999-08-08');


INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('PREMIUM', 150000, '11111111A');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('NORMAL', 20000, '11111111A');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('NORMAL', 50000, '22222222B');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('JUNIOR', 300, '22222222B');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('JUNIOR', 300, '33333333C');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('NORMAL', 75000, '44444444D');
INSERT INTO CUENTAS_BANCARIAS (tipo_cuenta, total, dni_cliente) VALUES ('PREMIUM', 120000, '55555555E');
