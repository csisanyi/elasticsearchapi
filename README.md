# elasticsearchapi
sample elasticsearchapi

elasticsearch application via docker
resurces/
docker-compose up -d

test with 
curl -X POST -H "Content-Type: application/json" -d '{"firstName": "Test", "lastName": "User","email": "testt@example.com"}' http://localhost:8181/api/elasticsearch/create

curl -X POST -H "Content-Type: application/json" -dte", "lastName": "us"}' http://localhost:8181/api/elasticsearch/search

