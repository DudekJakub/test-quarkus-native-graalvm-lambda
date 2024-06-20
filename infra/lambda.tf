module "lambda" {
  source  = "source.tui/customer/gco-lambda/aws"
  version = "~> 1.0"
  context = local.context

  vpc_id        = local.team_state.vpc.id
  kms           = local.service_state.profile.kms.arn
  subnets_ids   = local.team_state.private_subnets.ids
  alarm_sns_arn = local.team_state.sns.alarm_sns_arn

  config = {
    timeout     = 900
    memory_size = 256
    name        = local.lambda.name
    egress_rules = [
      {
        from_port       = 443
        to_port         = 443
        protocol        = "TCP"
        security_groups = [local.team_state.vpc_endpoints.sns.security_group_id]
      }
    ]
    policies = [
      {
        name = local.lambda.name
        json = data.aws_iam_policy_document.lambda_policies.json
      }
    ]
  }
}

data "aws_iam_policy_document" "lambda_policies" {}
