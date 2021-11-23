# Docker Basics

Docker commands and notes bases on the course [Docker: Criando containers sem dor de cabeça](https://cursos.alura.com.br/course/docker-e-docker-compose) by Alura.

# Installation

## Linux

- `sudo apt remove docker docker-engine docker.io`

- `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`

- `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu\$(lsb_release-cs)\stable"
`

- `sudo apt update`

- `sudo apt-get -y install docker-ce docker-compose`

- `sudo usermod -aG docker $USER`

- [`sudo chmod 666 /var/run/docker.sock`](https://stackoverflow.com/questions/48957195/how-to-fix-docker-got-permission-denied-issue)

- `sudo systemctl restart docker`

# Commands

- `docker version` or `docker --version`

- List active containers: `docker ps`

- List all containers: `docker ps -a`

- `docker start CONTAINER-ID`

- `docker stop CONTAINER-ID`

- Remove a specific container: `docker rm CONTAINER-ID`

- Remove all inactive containers: `docker container prune`

- List all images: `docker images`

- Remove an image: `docker rmi image-name`

- Check ports: `docker port CONTAINER-ID`

- Minimal Working Example: [`docker run hello-world`](https://hub.docker.com/_/hello-world)

- `docker rmi hello-world`

- List only the containers id: `docker ps -q`

- `docker stop -t 0 $(docker ps -q)`

- `docker inspect CONTAINER-ID`

## Ubuntu Example

- `docker run ubuntu`

- `docker run ubuntu echo "Hello World"`

- `docker run -it ubuntu`

- `docker start -a -i CONTEINER-ID`


- `docker run -v "/var/www" ubuntu`

- `docker run -it -v "/home/USERNAME/Desktop:/var/www" ubuntu`

## Static Site Example

- `docker run dockersamples/static-site`

- `docker run -d dockersamples/static-site`

- `docker run -d -P dockersamples/static-site`

- `docker run -d -P --name um-nome-qualquer dockersamples/static-site`

- `docker run -d -p 9696:80 dockersamples/static-site`

- `docker run -d -P -e AUTHOR="Caro" dockersamples/static-site`

## Code Example

- `docker run -p 8080:3000 -v "/home/USERNAME/Desktop/volume-exemplo:/var/www" -w "/var/www" node npm start`

- `docker run -d -p 8080:3000 -v "$(pwd):/var/www" -w "/var/www" node npm start`

## Creating our own images

- `docker build -f Dockerfile -t diascaro/node .`

- `docker run -d -p 8080:3000 diascaro/node`

- `docker login`

- `docker push diascaro/node`

## Networking with Docker

- `docker network create --driver bridge minha-rede`

- `docker network ls`

- `docker run -it --name meu-ubuntu --network minha-rede ubuntu`

## Docker Compose

To be added...
