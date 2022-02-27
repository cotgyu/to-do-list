mkdir -p ~/docker/mariadb/initdb.d

cp create.sql ~/docker/mariadb/initdb.d/create.sql
cp initData.sql ~/docker/mariadb/initdb.d/initData.sql

docker-compose up