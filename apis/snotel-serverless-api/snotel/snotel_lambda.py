"""For some reason the event has different keys for the query parameters depending if the event is coming from sam
local rest api or for the cloud deployment

sam local expects -> event['queryStringParameters']

otherwise the event looks like = event['queryParams']
"""
import boto3

dynamodb_client = boto3.client('dynamodb', region_name='us-west-2')


def query_snotel(location, sdate, edate):
    """
    This function queries the Snotel dynamoDB table w/ partition key location between dates sdate and edate
    :param location:
    :param sdate: Starting date of range to query over
    :param edate: Ending date of range to query over
    :return:
    """

    items = []

    try:
        response = dynamodb_client.query(
            TableName='Snotel',
            KeyConditionExpression='LocationID = :LocationID and SnotelDate BETWEEN :sdate and :edate',
            ExpressionAttributeValues={
                ':LocationID': {'S': location},
                ':sdate': {'S': sdate},
                ':edate': {'S': edate}
            }
        )
        items = response['Items']
    except Exception as e:
        print(e)

    data = []
    for i in range(len(items)):
        data_dictionary = {}
        data_dictionary['snotelDate'] = items[i]['SnotelDate']['S']
        data_dictionary['waterCurrent'] = int(items[i]['WaterCurrent']['N'])
        data_dictionary['waterMedian'] = int(items[i]['WaterCurrentAverage']['N'])
        data_dictionary['waterPctMedian'] = int(items[i]['WaterPctAverage']['N'])
        data_dictionary['snowCurrent'] = int(items[i]['SnowCurrent']['N'])
        data_dictionary['snowMedian'] = int(items[i]['SnowMedian']['N'])
        data_dictionary['snowPctMedian'] = int(items[i]['SnowPctMedian']['N'])
        data.append(data_dictionary)

    return data


def lambda_handler(event, context):
    location = event['queryParams']['location']
    sdate = event['queryParams']['sdate']
    edate = event['queryParams']['edate']

    data = query_snotel(location, sdate, edate)
    return {
        "statusCode": 200,
        "body": data,
    }
