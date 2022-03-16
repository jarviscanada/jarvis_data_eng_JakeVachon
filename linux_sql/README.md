

#INTRODUCTION
### This program leverages bash, git, docker and SQL to initially create a docker container one will use to extract hardware and usage specifications from host computers using bash scripting and SQL queries. Managers can access host_usage database to track individual node usage patterns and host_info database to examine node hardware specifications.
### This program can be downloaded and used by anyone with administrative privileges to get and/or compare real time usage information and hardware specifications of any node in their cluster. Custom SQL queries can also be created by managers to extract any data requires within the database

#Quick Start
### 1. Start a PSQL instance using psql_docker.sh by running psql_docker.sh from terminal 
####./scripts/psql_docker.sh start|stop|create [db_username][db_password]
### 2. Connect to PSQL using ```psql -h localhost -U postgres -d postgres -W```
### 3. Create host usage and host info tables by running ```ddl.sql;```
### 4. Exit PSQL instance with ```\q```
### 5. Add hardware specifications to host_info by running ```host_info.sh``` from terminal
### 6. Add usage information to host_usage by running ```host_usage.sh``` from terminal
### 7. Setup crontab to pull usage information in 5 minute intervals. Run ```crontab -e``` to open crontab editor, then insert ```* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log```

#Implementation
##Diagram
###See - linux_sql/asset/Diagram.png
##Scripts
###psql_docker.sh - Starts/stops/creates psql instance
```bash - ./scripts/psql_docker.sh start|stop|create [db_username][db_password]```
###host_info.sh - Extracts host hardware specification and adds to host_info table
```psql - ./scripts/host_info.sh```
###host_usage.sh - Extracts host usage specifications and adds to host_usage table
```psql - ./scripts/host_usage.sh```
###crontab - Extracts host usage info from host in 1 minute intervals and adds to table
###queries.sql - 3 sql queries extracting various information from database
####Query 1 - Displays total memory for each host organized by number of CPUs
####Query 2 - Displays average memory used in 5 minute period for each host
####Query 3 - Displays possible cronjob execution failures
#Database Modeling
| **Host_info** | Stores host hardware specs          | 
|---------------|-------------------------------------|
| ID            | Unique ID for this host             |
| Hostname      | Name of this host                   |
| CPU#          | Number of cpus in computer          |
| CPU_Arch      | E.G. 'x86_64'                       |
| CPU_Mod       | #Associated with CPU model          |
| CPU_Mhz       | CPU speed                           |
| l2_Cache      | part of CPU memory size             |
| Tot_Mem       | Total memory in host computer       |
| Timestamp     | Time that information was extracted |

| **Host_usage** | Stores host usage information at intervals |
|----------------|--------------------------------------------|
| Timestamp      | Time that this interval ran                |
| Host_id        | FK from 'id' column of host_info table     |
| Memory_free    | Total free memory remaining for this host  |
| CPU_idle       | Time this CPU has been idle for            |
| CPU_Kernel     | Host Operating system                      |
| CPU_IO         | Speed between hard disk and RAM            |
| Disk_available | Amount of disk space totally available     |

#Test
###1. Bash scripts were each ran to confirm the desired result was achieved. PSQL instance was started, stopped and created, tables were created and then filled with the given scripts.
###2. SQL queries were all ran in DBeaver database UI using dummy inputs to ensure they produce the desired output
#Deployment
###This project has been deployed using Github to allow user download, docker for container and PSQL initialization and Crontab to initiate data collection for host_usage
#Future Improvements
###1. Include more ready to run SQL queries for frequently requested datasets
###2. Include additional user hardware and host usage fields
###3. Respond to feedback to improve ease of use and facilitate setup for end users