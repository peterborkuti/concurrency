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
Implement runnable, run 10 pieces of them with executor

  

Gardener
  seeds, gardener. Seeds must be watered to grow. After they reach a certain size, harvested.

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
