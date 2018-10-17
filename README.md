# analytics-operator-temp-conv

Converts temperature values in between scales.

VAR | Default | Desc
------------- | ------------- | -------------
CONFIG_APPLICATION_ID  | operator-temp-conv | Application ID
CONFIG_BOOTSTRAP_SERVERS  | gets set by querying zookeeper | List of kafka brokers
ZK_QUORUM  | localhost:2181 | zookeeper instances
KAFKA_TOPIC  | input-stream | inputstream name
KAFKA_OUTPUT  | output-stream | outputstream name
INPUT_1  | temp | Input Temperature value
OUTPUT_1  | temp | Output Temperature value
