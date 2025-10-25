-- EXERCICIOS DA AULA 4
 
 
-- 1 
SELECT * FROM film WHERE rating IN ('PG','PG-13' );
 
-- 2  
SELECT * FROM film WHERE rating NOT IN ('R','NC-17' );
 
-- 3 
 
SELECT * FROM film WHERE replacement_cost BETWEEN 10 AND 20 ;
 
-- 4 
SELECT * FROM film WHERE title LIKE'A%';
 
-- 5  
SELECT * FROM film WHERE title LIKE'%LOVE%';
 
-- 6
SELECT * FROM film WHERE length BETWEEN 90 AND 120;

-- 7
SELECT AVG(rental_rate) FROM film WHERE rating = 'PG-13';

-- 8
SELECT SUM(replacement_cost) FROM film WHERE rating = 'R';

-- 9
SELECT AVG(length) FROM film WHERE title LIKE '%SUPER%';

-- 10
SELECT AVG(length) FROM film WHERE rating = 'R' OR rating = 'PG';