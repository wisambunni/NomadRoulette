import { Construct } from 'constructs';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import { LambdaHandlerNames } from '../../resources/constants/constants';

export interface GenerateItineraryLambdaProps {
    definitionName: string;
}

export class GenerateItineraryLambda extends Construct {
    public readonly function: lambda.Function;
    constructor(scope: Construct, id: string, props: GenerateItineraryLambdaProps) {
        super(scope, id);
        this.function = new lambda.Function(this, LambdaHandlerNames.GENERATE_ITINERARY_LAMBDA, {
          runtime: lambda.Runtime.JAVA_21,
          handler: 'org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest',
          code: lambda.Code.fromAsset('../backend/build/libs/nomad-roulette-0.0.1-SNAPSHOT-aws.jar'),
          environment:
            {
              'SPRING_CLOUD_FUNCTION_DEFINITION': props.definitionName,
              'JAVA_TOOL_OPTIONS': '-XX:+TieredCompilation -XX:TieredStopAtLevel=1',
              'MAIN_CLASS': 'com.sambie.nomad_roulette.NomadRouletteApplication'
            }
        })
    }
}