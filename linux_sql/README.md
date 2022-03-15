

#INTRODUCTION
### This program leverages bash, git, docker and SQL to initially create a docker container one will use to extract hardware and usage specifications from host computers using bash scripting and SQL queries. Managers can access host_usage database to track individual node usage patterns and host_info database to examine node hardware specifications.
### This program can be downloaded and used by anyone with administrative privileges to get and/or compare real time usage information and hardware specifications of any node in their cluster. Custom SQL queries can also be created by managers to extract any data requires within the database

#Quick Start
### 1. Start a PSQL instance using pasql_docker.sh by running psql_docker.sh from terminal 
####./scripts/psql_docker.sh start|stop|create [db_username][db_password]
### 2. Connect to PSQL using ```psql -h localhost -U postgres -d postgres -W```
### 3. Create host usage and host info tables by running ```ddl.sql;```
### 4. Exit PSQL instance with ```\q```
### 5. Add hardware specifications to host_info by running ```host_info.sh``` from terminal
### 6. Add usage information to host_usage by running ```host_usage.sh``` from terminal
### 7. Setup crontab to pull usage information in 5 minute intervals. Run ```crontab -e``` to open crontab editor, then insert ```* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log```


