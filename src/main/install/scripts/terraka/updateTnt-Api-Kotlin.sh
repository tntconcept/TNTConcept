#!/bin/bash

service_name=tomcat

download_artifact_gh(){
    echo "Para conectarnos a Github necesitamos un usuario y un Personal Access Token (PAT)"
    echo -n "username: "
    read gh_user
    echo -n "token: "
    read -s gh_token
    echo
    echo -n "version de tntconcept-api-rest-kotlin: "
    read version

    echo "Descargando tntconcept-api-rest-kotlin-$version.war...."
    curl -s -S -u $username:$gh_token https://api.github.com/repos/autentia/binnacle-api-kotlin/releases/tags/"$version" |
     jq '.assets[] | select(.name | contains(".war")) .url' |
     xargs curl -s -S -L -H 'Accept: application/octet-stream' -o tntconcept-api-rest-kotlin.war -u $username:$gh_token
    echo "tntconcept-api-rest-kotlin.war descargado!"
    echo
}

download_artifact(){
    echo "Para conectarnos a Nexus necesitamos un usuario y su contraseña"
    echo -n "username: "
    read username
    echo -n "password: "
    read -s password
    echo
    echo -n "version de tntconcept-api-rest-kotlin: "
    read version

    URL="https://tnt2.autentia.com/nexus/repository/maven-releases/com/autentia/tnt/api/tntconcept-api-rest-kotlin/$version/tntconcept-api-rest-kotlin-$version.war"
    echo "Descargando tntconcept-api-rest-kotlin-$version.war...."
    wget -O tntconcept-api-rest-kotlin.war --user=$username --password=$password  $URL &> /dev/null
    echo "tntconcept-api-rest-kotlin.war descargado!"
    echo
}

deploy_artifact(){
    echo "Añadiendo tntconcept-api-rest-kotlin.war al volumen del servicio $service_name"
    cp tntconcept-api-rest-kotlin.war ~/vagrant/terraka-dockers/tntconcept/services/${service_name}/volumes/webapps/tntconcept-api-rest-kotlin.war
    rm tntconcept-api-rest-kotlin.war
}

is_success(){
    [[ "$?" == 0 ]] && return
}

main(){
    echo "Proceso de actualización de TNTConcept-api-rest-kotlin en el entorno de pre-producción"
    download_artifact_gh

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
    	echo "Ya está la nueva versión de TNTConcept-api-rest-kotlin actualizada, para ello dirijase a"
    	echo "http://192.168.168.5:18080/tntconcept-api-rest-kotlin/"
    else
	echo "Ha ocurrido un error al desplegar la nueva version"
    fi
}

main



