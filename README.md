# NomadRoulette
A budget-first travel discovery engine. Instead of users picking a destination and finding out the cost, they input their home airport, travel dates, and maximum budget. The system randomly selects a destination that fits the flight budget, finds available hotels, and curates local points of interest, generating a complete surprise itinerary.

Build backend
docker-compose build backend

Compile a Spring Boot jar
docker run --rm -v "$(pwd)/backend:/app" -w /app gradle:jdk21 gradle clean shadowJar --no-daemon


Spin up localstack
docker-compose up -d localstack

Deploy stack
cd cdk
> ./deploy.sh

Test API

Get destinations within budget (returns list of swipeable destinations)
curl -X POST https://{api-id}.execute-api.localhost.localstack.cloud:4566/prod/destinations \
  -H "Content-Type: application/json" \
  -d '{
    "originAirport": "LAX",
    "startDate": "2026-05-01",
    "endDate": "2026-05-14",
    "budget": 3500
  }'

Generate itinerary for a chosen destination
curl -X POST https://{api-id}.execute-api.localhost.localstack.cloud:4566/prod/itineraries \
  -H "Content-Type: application/json" \
  -d '{
    "originAirport": "LAX",
    "startDate": "2026-05-01",
    "endDate": "2026-05-14",
    "budget": 3500
  }'