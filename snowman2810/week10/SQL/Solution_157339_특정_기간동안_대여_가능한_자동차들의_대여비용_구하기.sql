SELECT car.CAR_ID, car.CAR_TYPE, round((30*car.DAILY_FEE)*(100-discount.DISCOUNT_RATE)/100) AS FEE
FROM car_rental_company_car AS car LEFT JOIN (
    SELECT car_id , MAX(end_date) AS end_date 
    FROM car_rental_company_rental_history 
    GROUP BY car_id) AS history 
ON car.CAR_ID=history.CAR_ID JOIN (
    SELECT car_type, discount_rate 
    FROM car_rental_company_discount_plan 
    WHERE car_type IN ("세단","SUV") AND duration_type="30일 이상") AS discount 
ON car.CAR_TYPE = discount.CAR_TYPE
WHERE car.CAR_TYPE IN ("세단","SUV") AND history.END_DATE < 20221101 
AND round((30*car.DAILY_FEE)*(100-discount.DISCOUNT_RATE)/100) BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC;


SELECT car.CAR_ID, car.CAR_TYPE, round((30*car.DAILY_FEE)*(100-discount.DISCOUNT_RATE)/100) AS FEE
FROM car_rental_company_car AS car JOIN 
    (SELECT car_type, discount_rate 
     FROM car_rental_company_discount_plan 
     WHERE car_type IN ("세단","SUV") AND duration_type="30일 이상") AS discount 
     ON car.CAR_TYPE=discount.CAR_TYPE
WHERE car.CAR_ID IN 
    (SELECT car_id 
     FROM car_rental_company_rental_history 
     GROUP BY car_id HAVING MAX(end_date)<20221101)
AND round((30*car.DAILY_FEE)*(100-discount.DISCOUNT_RATE)/100) BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC;
