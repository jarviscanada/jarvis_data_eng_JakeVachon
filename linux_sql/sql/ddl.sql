DROP TABLE host_info, host_usage;

CREATE TABLE PUBLIC.host_info
  (
     id               SERIAL NOT NULL,
     hostname         VARCHAR NOT NULL,
     cpu_number       INT NOT NULL,
     cpu_architecture VARCHAR NOT NULL,
     cpu_model        VARCHAR NOT NULL,
     cpu_mhz          FLOAT NOT NULL,
     L2_cache         VARCHAR NOT NULL,
     total_mem        INT NOT NULL,
     time_stamp       TIMESTAMP NOT NULL,

     PRIMARY KEY (id),
     UNIQUE (hostname)
  );

CREATE TABLE PUBLIC.host_usage
 (
     "timestamp"    TIMESTAMP NOT NULL,
     host_id        SERIAL NOT NULL,
     memory_free    INT NOT NULL,
     cpu_idle       INT NOT NULL,
     cpu_kernal     INT NOT NULL,
     disk_io        VARCHAR NOT NULL,
     disk_available VARCHAR NOT NULL,

     FOREIGN KEY (host_id)
        REFERENCES host_info(id)
  );

