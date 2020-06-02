#!/bin/bash

CONTAINER_REGISTRY="server:40835"
PODMAN_SERVER="server"

# Alternatively, you could build from the Dockerfile, like so...
#buildah build-using-dockerfile -t balancing-act-api -f Dockerfile target
# But instead we'll use buildah directly.

echo
echo "*** buildah ***"
echo

# Make sure API key is in the environment
if [ -z "$ALPHAVANTAGE_API_KEY" ]; then
	cat <<EOF
ERROR: Make sure your AlphaVantage API key is set in the
ALPHAVANTAGE_API_KEY environment variable.
EOF
	exit 1
fi

container=$(buildah from adoptopenjdk:11-jre-hotspot)
buildah copy $container target/balancing-act-api-0.0.1-SNAPSHOT.jar /opt/app.jar
buildah config --entrypoint '["java", "-jar", "/opt/app.jar"]' $container
buildah config --env ALPHAVANTAGE_API_KEY=$ALPHAVANTAGE_API_KEY $container
buildah unmount $container
buildah commit $container balancing-act-api
buildah rm $container

# Tag the new container and push it to the registry for deployment.
buildah tag balancing-act-api $CONTAINER_REGISTRY/balancing-act-api
buildah push $CONTAINER_REGISTRY/balancing-act-api

if [ -n "$SKIP_DEPLOY" ]; then
	echo "Skipping deployment"
	exit 0
fi

echo
echo "*** restart over ssh ***"
echo

# This assumes that:
# * You have passwordless (e.g., key-based) SSH to $PODMAN_SERVER
# * You have set up balancing-act as a systemd unit in your user on $PODMAN_SERVER
#   (or whatever user is running this script with SSH access to $PODMAN_SERVER)
ssh $PODMAN_SERVER "podman pull $CONTAINER_REGISTRY/balancing-act-api"
ssh $PODMAN_SERVER "systemctl --user restart container-balancing-act-api.service"
ssh $PODMAN_SERVER "systemctl --user status container-balancing-act-api.service" | head -n3




