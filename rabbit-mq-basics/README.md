### To run locally
##### Docker
* Install Docker;
* In cmd, run `docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=test -e RABBITMQ_DEFAULT_PASS=test -p 8080:15672 -p 5672:5672 rabbitmq:3-management`
  * You should be able to connect to Message Broker management interface with `localhost:8080`.
* In `MqChannelFactory` class, uncomment `factory.setUri`, and comment `factory.setHost` calls.
* Run the main methods of `Publisher` and `Consumer` classes.

##### Windows installation
* Install Windows service (https://www.rabbitmq.com/install-windows.html#run-windows)
* From Start, run 'RabbitMQ Command Prompt (sbin dir)'
* Run `rabbitmq-plugins enable rabbitmq_management`
* Run `rabbitmqctl add_user test test`
* Run `rabbitmqctl set_user_tags test administrator`
  * You should be able to connect to Message Broker management interface with `localhost:15672`.
* In `MqChannelFactory` class, uncomment `factory.setHost`, and comment `factory.setUri` calls.
* Run the main methods of `Publisher` and `Consumer` classes.