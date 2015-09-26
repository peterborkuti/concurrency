Creating threads
Extend Thread
  thread must write "Start", wait for a second and write "Done".
Implement Runnable
  runnable must write "Start", wait for a second and write "Done".
Implement Runnable and use FutureTask and executor
  runnable must write "Start", wait for a second and write "Done".
  
  Future<?> f = new FutureTask<Void>(runnable, null)
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
