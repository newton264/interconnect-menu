#!/bin/bash

NUM_FOOD=`cf ic group list | grep food | wc -l`
NUM_DRINKS=`cf ic group list | grep drinks | wc -l`

if [ "$NUM_FOOD" -eq 0 -o "$NUM_DRINKS" -eq 0 ]; then

	echo "[ERROR]: Either food container group or drinks container group does not exit"
	echo "$LIST"
	echo "[ERROR]: Please review previous steps."
	exit 1
fi
if [ "$NUM_FOOD" -gt 1 -o "$NUM_DRINKS" -gt 1 ]; then

	echo "[WARNING]: There is either more than one food or drinks container group"
	echo "[WARNING]: Menu will point to one of them only."
fi


FOOD_ID=`cf ic group list | grep food | awk '{print $1}' | head -n 1`
DRINKS_ID=`cf ic group list | grep drinks | awk '{print $1}' | head -n 1`

#echo "Getting IP address for food container group $FOOD_ID"
FOOD_LOAD_BALANCER_IP=`cf ic group inspect $FOOD_ID | grep private_ip_address | sed 's/.*: "\(.*\)"$/\1/g'`
#echo "Loadbalancer IP: ${FOOD_LOAD_BALANCER_IP}"
#echo
#echo "Getting IP address for drinks container group $DRINKS_ID"
DRINKS_LOAD_BALANCER_IP=`cf ic group inspect $DRINKS_ID | grep private_ip_address | sed 's/.*: "\(.*\)"$/\1/g'`
#echo "Loadbalancer IP: ${DRINKS_LOAD_BALANCER_IP}"
#echo
echo "--env DRINKS_URL=${DRINKS_LOAD_BALANCER_IP} --env FOOD_URL=${FOOD_LOAD_BALANCER_IP}"

exit 0
