#!/bin/bash
# deploy-local.sh
#
# Builds the Spring Boot JAR and hot-deploys it to the Lambda running in LocalStack.
#
# Use this script when you've made Java code changes but haven't changed any CDK
# infrastructure. CDK won't detect JAR content changes on its own, so this script
# bypasses that by pushing the new JAR directly to the running Lambda container.
#
# Prerequisites:
#   - LocalStack container must be running (docker-compose up -d localstack)
#   - CDK stack must already be deployed (cd cdk && ./deploy.sh)
#
# Usage (run from backend/ directory):
#   ./deploy-local.sh

set -e  # Exit immediately if any command fails

LAMBDA_FUNCTION_NAME="NomadRouletteStack-GenerateItineraryLambdaG-b72c2978"
JAR_PATH="build/libs/nomad-roulette-0.0.1-SNAPSHOT-aws.jar"
LOCALSTACK_CONTAINER="nomadroulette-localstack-1"
REMOTE_JAR_PATH="/tmp/nomad-roulette.jar"

echo "==> Building JAR..."
docker run --rm \
  -v "$(pwd):/app" \
  -w /app \
  gradle:jdk21 gradle clean shadowJar --no-daemon

echo "==> Copying JAR into LocalStack container..."
docker cp "$JAR_PATH" "$LOCALSTACK_CONTAINER:$REMOTE_JAR_PATH"

echo "==> Updating Lambda function code..."
docker exec "$LOCALSTACK_CONTAINER" awslocal lambda update-function-code \
  --function-name "$LAMBDA_FUNCTION_NAME" \
  --zip-file "fileb://$REMOTE_JAR_PATH"

echo "==> Done. Lambda updated successfully."
