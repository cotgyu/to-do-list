mkdir -p ~/docker/mariadb/initdb.d
chmod 755 ~/docker

mkdir -p ~/docker/mariadb/var/lib/mysql
chmod 755 ~/docker/mariadb/var/lib/mysql

mkdir -p ~/docker/mariadb/var/log/maria
chmod 755 ~/docker/mariadb/var/log/maria

mkdir -p ~/docker/redis/data
chmod 755 ~/docker/redis/data

cp create.sql ~/docker/mariadb/initdb.d/create.sql
chmod 755 ~/docker/mariadb/initdb.d/create.sql
cp initData.sql ~/docker/mariadb/initdb.d/initData.sql
chmod 755 ~/docker/mariadb/initdb.d/initData.sql

docker-compose up