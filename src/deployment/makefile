DOCKER_VOLUMES_DIR=/tmp/dissertation/docker-volumes

# SERVICES ################################################################################

# deployment services (this command down the services if already running and remove/reset the old volumes and create the new ones before deployment)
deployment: down remove_volumes create_volumes_dirs upf

# update only modified services
up:
	sudo docker-compose up -d --build --remove-orphans

# update all services
upf:
	sudo docker-compose up -d --build --force --remove-orphans

# down all services
down:
	sudo docker-compose down

# docker setup

create_volumes_dirs:
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/mongo
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/postgres
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/postgres/${CP_NAME}
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/postgres/${CC_NAME}
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/postgres/${INVENTORY_NAME}
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/postgres/${SC_NAME}
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/rabbitmq
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/rabbitmq/data
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/rabbitmq/log
	sudo mkdir -p ${DOCKER_VOLUMES_DIR}/kong
	sudo cp services/api-gateway/kong.* ${DOCKER_VOLUMES_DIR}/kong

remove_volumes:
	sudo rm -rf ${DOCKER_VOLUMES_DIR}/mongo
	sudo rm -rf ${DOCKER_VOLUMES_DIR}/postgres
	sudo rm -rf ${DOCKER_VOLUMES_DIR}/rabbitmq
	sudo rm -rf ${DOCKER_VOLUMES_DIR}/kong
	sudo docker system prune

copy_api_gateway_config_to_volumes:
	sudo cp services/api-gateway/kong.* ${DOCKER_VOLUMES_DIR}/kong

# scripts

setup:
	/bin/sh scripts/setup.sh

# populates

populate: populate_categories populate_products populate_products_in_categories

populate_categories:
	/bin/sh scripts/populate/populate_categories.sh

populate_products:
	/bin/sh scripts/populate/populate_products.sh

populate_products_in_categories:
	/bin/sh scripts/populate/populate_products_in_categories.sh

# logs:

tomcat:
	sudo docker logs --follow tomcat

saga:
	sudo docker logs --follow saga

cp:
	sudo docker logs --follow cp

qp:
	sudo docker logs --follow qp

cc:
	sudo docker logs --follow cc

qc:
	sudo docker logs --follow qc

co:
	sudo docker logs --follow co

inventory:
	sudo docker logs --follow inventory

manager:
	sudo docker logs --follow manager

consumer:
	sudo docker logs --follow consumer

sc:
	sudo docker logs --follow sc

mysql:
	sudo docker logs --follow deployment_mysql_1

rmq:
	sudo docker logs --follow rabbitmq

mongo:
	sudo docker logs --follow deployment_mongo_1

kong:
	sudo docker logs --follow kong

frontend:
	sudo docker logs --follow frontend

#populate:
#	sudo docker logs --follow populate

qcvp:
	sudo docker logs --follow qcvp

qcap:
	sudo docker logs --follow qcap

qct:
	sudo docker logs --follow qct
