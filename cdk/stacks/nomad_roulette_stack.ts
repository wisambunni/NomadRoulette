import * as cdk from 'aws-cdk-lib/core';
import { Construct } from 'constructs';
import { NomadRouletteGateway } from '../bin/constructs/api/nomad_roulette_gateway';
import { GenerateItineraryLambda } from '../bin/constructs/lambda/generate_itinerary_lambda';

export class NomadRouletteStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const gateway = new NomadRouletteGateway(this, 'NomadRouletteGateway');

    const generateItinieraryLamda = new GenerateItineraryLambda(this, 'GenerateItineraryLambda', {
      definitionName: 'generateItineraryLambda'
    });

    gateway.AddRoute('itineraries', 'POST', generateItinieraryLamda.function);
    gateway.AddRoute('destinations', 'POST', generateItinieraryLamda.function);
  }
}
