SELECT cpu_number, id, total_mem FROM host_info
order by cpu_number, total_mem

SELECT host_usage.host_id, host_info.hostname,
date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min' as time_rnd,
avg((((host_info.total_mem / 10000) - host_usage.memory_free) * 100) / (total_mem / 10000)) as avg_mem_used_pct
FROM host_usage
JOIN host_info on host_usage.host_id = host_info.id
GROUP BY time_rnd, host_id, host_info.hostname
ORDER BY host_id

SELECT * FROM (
	SELECT host_id, hostname, date_trunc('hour', host_usage.timestamp) + date_part('minute', host_usage.timestamp):: int / 5 * interval '5 min' as timenew,
	    count(*) as num_data_points
    FROM host_info
    JOIN host_usage on host_info.id  = host_usage.host_id
    GROUP BY host_id, hostname, timenew, total_mem
    ORDER BY timenew) AS times_executed
WHERE num_data_points <3;

