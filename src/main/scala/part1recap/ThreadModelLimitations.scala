package part1recap

object ThreadModelLimitations extends App {

  /*
  Daniel's rants
   */

  /**
   * DR #1: OOP encapsulation is only valid in the SINGLE THREADED MODEL.
   */
  class BankAccount(private var amount: Int) {
    override def toString: String = "" + amount

    def withdraw(money: Int) = this.synchronized {
      this.amount -= money
    }

    def deposit(money: Int) = this.synchronized {
      this.amount += money
    }

    def getAmount = amount
  }

  val account = new BankAccount(2000)
  for(_ <- 1 to 1000) {
    new Thread(() => account.withdraw(1)).start()
  }

  for(_ <- 1 to 1000) {
    new Thread(() => account.deposit(1)).start()
  }
  println(account.getAmount)

  // OOP encapsulation is broken in a multithreaded env
  // synchronization! Locks to the rescue

  // deadocks, livelocks

}
