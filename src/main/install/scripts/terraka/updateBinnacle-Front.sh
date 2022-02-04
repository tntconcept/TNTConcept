#!/bin/bash

service_name=apache

download_artifact(){
    echo "Para conectarnos a Nexus necesitamos un usuario y su contraseña"
    echo -n "username: "
    read username
    echo -n "password: "
    read -s password
    echo
    echo -n "version de binnacle_front: "
    read version

    URL="https://tnt2.autentia.com/nexus/repository/autentia-raw/binnacle-front/binnacle_front_$version.zip"
    echo "Descargando binnacle_front_$version.zip...."
    wget -O binnacle_front.zip --user=$username --password=$password  $URL &> /dev/null
    echo "binnacle_front.zip descargado!"
    echo
}

deploy_artifact(){
    echo "Añadiendo binnacle_front al volumen de $service_name"
    unzip binnacle_front.zip -d binnacle
    rm -rf ~/vagrant/terraka-dockers/tntconcept/services/apache/volumes/binnacle/
    mv binnacle ~/vagrant/terraka-dockers/tntconcept/services/apache/volumes/binnacle/
    rm  binnacle_front.zip
    echo "Ya está la nueva versión de TNT-Binnacle actualizada, para ello dirijase a"
    echo "http://192.168.168.5:38080/"
}

is_success(){
    [[ "$?" == 0 ]] && return
}

main(){
    echo "Proceso de actualización de binnacle_front en el entorno de pre-producción"
    download_artifact

    if is_success; then
      deploy_artifact	
    fi
}

main
