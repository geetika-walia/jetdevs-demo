#!/bin/bash
apt-get update && apt-get install -y iputils-ping
echo "Starting Docker"
i=0
echo "Checking DB is up ..."
# sleep 120
while true
do
    if [ "$i" -ge 18000 ]
    then
        echo "limit exceeds..."
        break
    fi

    i=$(( i + 10 ))
    ping -c 1 $1
    if ping -c 1 $1 > /dev/null
    then
      echo "$i...Mysql is UP..."
        java -jar ./jetdevs-testtask-1.0-SNAPSHOT.jar
        break;
    else
      echo "$i...Waiting for DB 10 second ..."
      sleep 10
    fi
done


