# otrp
online table reservation system (OTRS)

## Prometheus

Install  
https://hub.docker.com/r/prom/prometheus/

Run 
```
docker run -d\
    --name=prometheus \
    -p 9090:9090 \
    -v /path/to/otrs-api.yml:/etc/prometheus/prometheus.yml \
    prom/prometheus
```

UI  
http://localhost:9090

## Graphana

Download  
https://grafana.com/grafana/download

Run
```
docker run -d \
    --name=grafana \
    -p 3000:3000 \
    grafana/grafana:7.3.3-ubuntu
```

UI  
http://localhost:3000  
admin@admin

Import 
OTRS API - Graph-1540540127247.json  
Hystrix Dashboard-1540540111856

## Docker commands
```sh
$ docker-compose up -d
```
> Detached mode: Run containers in the background, print new container names.  

```
$ docker-compose stop
```
> Stops running containers without removing them.  


```
$ docker-compose down
```
> Stops containers and removes containers, networks, volumes, and images created by up.  

Options:
    --rmi type              Remove images. Type must be one of:
                              'all': Remove all images used by any service.
                              'local': Remove only images that don't have a
                              custom tag set by the `image` field.
    -v, --volumes           Remove named volumes declared in the `volumes`
                            section of the Compose file and anonymous volumes
                            attached to containers.
