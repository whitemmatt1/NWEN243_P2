# Magic 8 Ball – Java TCP Client-Server Program

This is a simple Java-based TCP client-server application that simulates the classic **Magic 8 Ball** toy. The server randomly selects a response from a list of 20 predefined Magic 8 Ball answers, and the client receives and displays it when a connection is made.
- Originally this was a assignment for NWEN243 where we implemented this into an Amazon ECS server, where I configured with Elastic Load Balancers, Security groups, and Auto Scaling. 
## How It Works

- The **server** (`Magic8BallServer.java`) listens on a specified port for incoming TCP connections.
  - Upon receiving a connection, it selects a random Magic 8 Ball answer and sends it to the client.
  - The response also includes the server's IP address in parentheses.
  
- The **client** (`Magic8BallClient.java`) connects to the server using the given hostname and port.
  - Once connected, it reads and displays the response from the server.

## Usage

### Start The Server

```bash
java Magic8BallServer <port>
```

### Run The Client
 
```bash
java Magic8ballClient <hostname> <port> <question>
```

### Sample Output

```bash
Magic 8 ball says: Most likely(192.168.1.5)
```

### Features

- Simple TCP socket programming in java
- Randomized responses simulating a Magic 8 Ball
- Handles client connections and server responses
 

