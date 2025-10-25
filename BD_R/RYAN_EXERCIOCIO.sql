--Liste todos os títulos dos filmes cadastrados--
SELECT * FROM film

--Selecione o título e o ano de lançamento dosfilmes--

SELECT title, release_year FROM film;

--Selecione o título e o ano de lançamento dos filmes--

SELECT title || ' - ' || release_year AS Filme_Ano FROM film;

--Exiba os filmes cuja classificação (rating) seja PG-13--

SELECT title,rating  FROM film WHERE  rating  = 'PG-13';

--Liste os filmes com duração de aluguel (rental_duration) maior
que 5 dias.--

SELECT title , rental_duration FROM film WHERE rental_duration >= 5;

-- Liste todos os filmes com valor de aluguel (rental_rate) igual a
0.99. --

SELECT title,rental_rate FROM film WHERE rental_rate =  0.99;

-- Liste os filmes em ordem alfabética, considerando o título. --

SELECT title FROM film ORDER BY title ASC;