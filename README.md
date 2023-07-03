![uminho](http://www4.di.uminho.pt/~jmf/IMAGES/um_eeng.gif)

## [University of Minho - School of Engineering](https://www.eng.uminho.pt/en) - Integrated Master's, Software Engineering

# Dissertation: Study on the importance of the Principles and  Patterns of Microservices Architectures

**Notice**: The dissertation document will be placed here shortly when it is published.

# Links:
- [Website](https://josepereira1.github.io/)
- [LinkedIn](https://www.linkedin.com/in/joseandrepereira)
- [Github](https://github.com/josepereira1)
- [Docker images repository](https://hub.docker.com/u/josepereira1)

# Index:
- [Microservice List](#microservice-list)
- [How to deploy the case study](#how-to-deploy-the-case-study)
  - [Requirements](#requirements)
  - [Deployment process (Unix)](#deployment-process-unix)
  - [After first deployment](#after-first-deployment) 
- [Use case study application (after deployment)](#use-case-study-application-after-deployment)
  - [Default users](#default-users)
  - [Access monitoring services](#access-monitoring-services)

# Microservice List:

| #ID       | Name                            |
| --------  | -------                         |
| saga      | Saga                            |
| cp        | Command Product                 |
| qp        | Query Product                   |
| cc        | Command Category                |
| qcvp      | Query Category Visible Products |
| qcap      | Query Category All Products     |
| qct       | Query Category Tree             |
| co        | Order                           |
| inventory | Inventory                       |
| sc        | Shopping Cart                   |
| consumer  | Consumer                        |
| manager   | Manager                         |

# How to deploy the case study:

## Requirements:
- Docker and Docker compose;
- Makefile;
- **16+ GB of RAM**;
- the repository folders (src/deployment);

## Deployment process (Unix):

- Download the repository:
```cmd
git clone https://github.com/josepereira1/dissertation.git
```

- Set the docker volumes dir or leave the default (/tmp/dissertation/docker-volumes)
  - open **.env** (src/deployment/.env) and set the **DOCKER_VOLUMES_DIR** var with docker volumes directory.
  - open **makefile** (src/deployment/makefile) and set the **DOCKER_VOLUMES_DIR** var with **same** docker volumes directory defined above.

- Execute deployment:
```cmd
cd src/deployment
make deployment
```
- Wait some time (3 minutes, or check the cpu usage, when it decrease significantly, probably the deployment ended).

- Setup base data and configurations:
```cmd
make setup
```

- Populate data (products, categories and associate them):
```cmd
make populate
```

The **create product** functionality is asynchronous process, than when **populate** process ends, it will take some time until all products are created and associated with categories.

## After first deployment: 

After first deployment, to stop or start the application just need to run **make up** or **make down** and reuse data saved in volumes. There is one more command, **make upf**, it is similar to **make up**, but forces the docker images update.

# Use case study application (after deployment)

After deploy application, access via web browser to [http://localhost:8080](http://localhost:8080/) and use the application. Check the default users section.

## Default users:

- **Consumer**: 
  - username: bob
  - password: password

- **Manager**:
  - username: alice
  - password: password

## Access monitoring services:
- [MongoDB databases](http://localhost:9004/)
- [PostgreSQL databases](http://localhost:9001/?pgsql=postgres&username=root):
  - System: PostgreSQL 
  - Username: root
  - Password: password
  - Servers:
    - postgres-cp
    - postgres-cc
    - postgres-inventory
    - postgres-sc
- [RabbitMQ](http://localhost:15672/):
  - Username: guest
  - Password: guest

## Consult logs:

Can consult logs per service to see command and reply messages used in microservices patterns, using the next command:

```cmd
make <<service_name>>
```
Examples:
```cmd
make cp
```
