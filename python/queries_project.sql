SELECT * 
FROM retail
LIMIT 10;

SELECT COUNT(*) AS TOTAL_RECORDS
FROM retail;

SELECT COUNT(DISTINCT customer_id) AS unique_customers
FROM retail;

SELECT MAX(invoice_date) AS oldest_invoice,
	MIN(invoice_date) AS newst_invoice
FROM retail;

SELECT COUNT(DISTINCT stock_code) AS unique_items
FROM retail;

WITH table_1 AS(SELECT invoice_no, quantity * unit_price AS price
				FROM retail),
			table_2 AS(SELECT invoice_no, SUM(price) AS total_price
				FROM table_1
				GROUP BY invoice_no)
SELECT ROUND( AVG(total_price)::numeric, 2)
FROM table_2
WHERE total_price>=0;

SELECT ROUND( SUM(unit_price * quantity)::numeric, 2) as tot_rev
FROM retail;

SELECT to_char(invoice_date, 'yyyy-mm') AS "year_month",
	ROUND (SUM(unit_price *quantity)::numeric, 2) AS monthly_rev
FROM retail
GROUP BY "year_month"
ORDER BY "year_month";
