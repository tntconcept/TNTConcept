#!/bin/bash

service_name=tomcat

download_artifact(){
    echo "Para conectarnos a Nexus necesitamos un usuario y su contraseña"
    echo -n "username: "
    read username
    echo -n "password: "
    read -s password
    echo
    echo -n "version de tntconcept-web: "
    read version

    URL="https://tnt2.autentia.com/nexus/repository/maven-releases/com/autentia/tnt/tntconcept-web/$version/tntconcept-web-$version.war"
    echo "Descargando tntconcept-web-$version.war...."
    wget -O tntconcept.war --user=$username --password=$password  $URL &> /dev/null
    echo "tntconcept.war descargado!"
    echo
}

deploy_artifact(){
    echo "Añadiendo tntconcept.war al volumen del servicio $service_name"
    cp tntconcept.war ~/vagrant/terraka-dockers/tntconcept/services/${service_name}/volumes/webapps/tntconcept.war
    rm tntconcept.war
}

is_success(){
    [[ "$?" == 0 ]] && return
}

main(){
    echo "Proceso de actualización de TNTConcept en el entorno de pre-producción"
    download_artifact

    if is_success; then

	deploy_artifact
        if [ -z `docker ps -q --no-trunc | grep $(docker-compose ps -q ${service_name})` ]; then
            echo "Levantando servicio ${service_name}..."
	    echo	
            docker-compose up -d ${service_name}
        else
            echo "Reiniciando servicio ${service_name}..."
	    echo
            docker-compose restart ${service_name}
	fi
    fi


    echo
    if is_success; then
    	echo "Ya está la nueva versión de TNT actualizada, para ello dirijase a"
    	echo "http://192.168.168.5:18080/tntconcept/"
    else
	echo "Ha ocurrido un error al desplegar la nueva version"
    fi
}

main



