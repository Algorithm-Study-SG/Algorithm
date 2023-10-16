select f.flavor
from first_half f 
join (
    select sum(total_order) total_order, flavor 
    from july 
    group by flavor
) j on j.flavor=f.flavor
order by f.total_order+j.total_order desc limit 3;
