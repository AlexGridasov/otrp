# otrp
online table reservation system (OTRS)

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
