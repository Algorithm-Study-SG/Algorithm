SELECT car.CAR_ID, car.CAR_TYPE, round(car.daily_fee*30*(100-plan.discount_rate)/100) FEE
FROM CAR_RENTAL_COMPANY_CAR car
    JOIN (SELECT car_type, discount_rate
          FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
          WHERE car_type in ('세단', 'SUV') AND duration_type = '30일 이상') plan
    ON car.car_type = plan.car_type    
WHERE car.car_id NOT IN (SELECT DISTINCT car_id
                         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY  
                         WHERE end_date >= '2022-11-01' AND start_date <= '2022-11-30') AND
      round(car.daily_fee*30*(100-plan.discount_rate)/100) BETWEEN 500000 AND 1999999
ORDER BY FEE DESC, CAR_TYPE, CAR_ID DESC;