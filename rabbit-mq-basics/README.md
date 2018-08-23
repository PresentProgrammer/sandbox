### To run locally
* Install Docker;
* In cmd, run `docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=test -e RABBITMQ_DEFAULT_PASS=test -p 8080:15672 -p 5672:5672 rabbitmq:3-management`
  * You should be able to connect to Message Broker management interface with `localhost:8080`.
* Run the main methods of `Publisher` and `Consumer` classes.