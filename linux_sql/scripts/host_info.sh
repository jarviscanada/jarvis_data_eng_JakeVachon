#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check # of args
if [ $# -ne  5 ]; then
  echo 'Not enough information'
  exit 1
fi


psql_host=$(hostname -f)

lscpu_out=`lscpu`
memory=`cat /proc/meminfo`


cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
model=$(echo "$lscpu_out"  | egrep "Model:" | awk '{print $2}' | xargs)
mhz=$(echo "$lscpu_out"  | egrep "CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "L2 cache:" | awk '{print $3}' | xargs)
total_mem=$(echo "$memory"  | egrep "MemTotal:" | awk '{print $2}' | xargs)
time_stamp=$(date "+%Y-%m-%d %H:%M:%S")

insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, time_stamp)
VALUES('$psql_host', '$cpu_number', '$architecture', '$model', '$mhz', '$l2_cache', '$total_mem', '$time_stamp')"

psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0;
;;

