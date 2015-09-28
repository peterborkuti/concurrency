Creating threads

Extend Thread
  thread must write "Start", wait for a second and write "Done".

Implement Runnable
  runnable must write "Start", wait for a second and write "Done".

Implement Runnable and use executor
  runnable must write "Start", wait for a second and write "Done".
  ExecutorService executor = Executors.newFixedThreadPool(8);
  ...
  executor.execute(sleeper);
  Util.sleep(5000,1000);
  Util.shutdownAndAwaitTermination(executor);
Implement runnable, run 10 pieces of them with executor

Let's use latch!
Create two threads. The second should wait the first to finish
Create N threads and start them. Create a thread Q which should wait for the other N to finish.
Create N threads. They should wait for each other to start processing.
Collector
    There is one Collector and arbitrary number of Passengers. The colle

Gardener
  seeds, gardener. Seeds must be watered to grow. After they reach a certain size, harvested.
Dishwashers
  

Authors
  5 authors: sleeping, waiting for a pen, writing. 3 pens.

Automatic Traffic Light
  Light: red. Green for a given time if there is a car in front of it.

Two dependent automatic traffic light
  Green for a given time if there is a car in front of it and the other light is not green

Elevator1
  Two stops (Up and Down). People waiting at the stops.

Elevator2
  N stops. People waiting at the stops and when get in, choose a random Stop.
