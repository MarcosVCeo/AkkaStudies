import akka.actor.{Actor, ActorSystem, Props}

object ActorCapabilities extends App {

  import CounterActor.*

  val actorSystem = ActorSystem("capabilities")


  /**
   * Exercises
   *
   * 1. A counter acotr
   *   - Increment
   *   - Decrement
   *   - Print
   */


  private class CounterActor extends Actor {

    /* This is a private atribute of the instânce because the actor neds to have a private intern state */
    private var total = 0

    override def receive: Receive = {
      case IncrementMessage(quantity) =>
        println(s"[$self] Incrementing $quantity")
        total += quantity

      case DecrementMessage(quantity) =>
        println(s"[$self] Decrementing $quantity")
        total -= quantity

      case ConsultTotal() => println(s"[$self] Total is $total")
    }
  }

  object CounterActor {
    case class IncrementMessage(quantity: Int)

    case class DecrementMessage(quantity: Int)

    case class ConsultTotal()
  }

  val counterActor1 = actorSystem.actorOf(Props[CounterActor](), "counter1")
  val counterActor2 = actorSystem.actorOf(Props[CounterActor](), "counter2")

  counterActor1 ! IncrementMessage(2)
  counterActor1 ! IncrementMessage(6)
  counterActor1 ! IncrementMessage(7)
  counterActor1 ! DecrementMessage(7)
  counterActor1 ! ConsultTotal()
}


