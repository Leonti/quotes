version=$1
docker pull leonti/quotes
docker stop $(docker ps -a -q)
docker run -p 80:8080 -d leonti/quotes:$version
