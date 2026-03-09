import { Construct } from "constructs";
import * as apigateway from "aws-cdk-lib/aws-apigateway";
import * as lambda from "aws-cdk-lib/aws-lambda";

export class NomadRouletteGateway extends Construct {
  public readonly api: apigateway.RestApi; 
    constructor(scope: Construct, id: string) {
        super(scope, id);

        this.api = new apigateway.RestApi(this, id, {
          restApiName: 'NomadRouletteAPI',
          description: 'API for NomadRoulette',

          defaultCorsPreflightOptions: {
            allowOrigins: apigateway.Cors.ALL_ORIGINS,
            allowMethods: apigateway.Cors.ALL_METHODS,
            allowHeaders: ['Content-Type', 'Authorization'],
          }
        });
    }

    public AddRoute(path: string, method: string, lambdaFunction: lambda.Function) {
      const integration = new apigateway.LambdaIntegration(lambdaFunction);
      const resource = this.api.root.addResource(path);

      resource.addMethod(method, integration);
    }
}