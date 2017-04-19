并发编程代码大全

### 有关线程 com.alabada.con01_Thread
    Calculator01、Calculator02：
        线程创建（实现Runnable接口的类，使用带参数的Thread构造器来创建Thread对象，这个参数就是实现Runnable接口的类的一个对象）。
        线程基本状态获取。
    PrimeGenerator:
        继承Thread类，并且覆盖run()方法的方式来创建线程，并测试中断线程。
    FileSearch:
        线程中的异常处理。
    FileSearch:
        TimeUnit.SECONDS.sleep的使用。
    DataSourceLoader、NetworkConnectionLoader
        join方法的使用：当一个线程对象的join()方法被调用时，调用它的线程将被挂起，直到这个线程对象完成它的任务。
    CleanerTask，WriteTask，Event：
        守护线程的使用：
    ExceptionHandler，Task：
        在线程对象里捕获和处理运行时异常的一种机制
        如果线程没有被与之未捕获异常处理器，JVM将打印堆栈记录到控制台，并退出程序
    UnsafeTask，SafeTask：
        线程局部变量的使用：通过这种方式，每个线程都有它们自己的startDate属性了
    SearchTask：
        线程组的使用:调用线程组的interrupt方法，将该组下其余线程都中断
    MyThreadGroup:
        线程组非捕获异常处理器的使用
    MyThreadFactory:
        使用工厂类来创建线程使用案例
        
### 线程同步基础
    com\alabada\con02_synch\synch 包下：
        一个对象的方法采用synchronized关键字进行申明，只能被一个线程访问。
        如果线程a正在执行一个同步方法syncMethonA(),线程b要执行这个对象的其他同步方法syncMethodB(),线程b将被阻塞，直到线程a访问完。
        但如果线程b访问的是同一个类的不同对象，那么两个线程都不会被阻塞。
        
    com\alabada\con02_synch\synch1 包下：
        使用对象作为synchronized()的参数来保护代码块。
        不同的属性使用不同的对象，通过这种方式，来达到对不同属性的同步控制。
        
    com\alabada\con02_synch\producerconsumer1 包下：
        典型的生产者与消费者问题
        wait(),notify(),notifyAll()方法的使用
        
    com\alabada\con02_synch\lock 包下：
        使用锁实现同步。(设计公平模式与非公平模式)
        ReentrantLock锁的使用：
             首先，创建ReentrantLock对象；
             其次，在临界区的开始，必须通过lock()方法获取对锁的控制，当线程A访问这个方法时，如果没有其他线程获取对这个锁的控制，lock()方法将让线程A获得锁并且允许它立刻执行临界区代码。否则就只能等到其他线程执行完，线程A才可以接着执行。
             最后，在线程离开临界区的时候，必须使用unlock()方法来释放它持有的锁，以让其他线程来访问临界区。如果在离开临界区时没有调用unlock()方法，其他线程将永久等待，从而导致死锁的发生。
        
    com\alabada\con02_synch\readWriteLock 包下：
        使用读写锁实现同步数据访问：ReentrantReadWriteLock
         这个类有两个锁，一个是读操作锁，另一个是写操作锁。使用读操作锁时可以允许多个线程同时访问，但是使用写操作锁时只允许一个线程进行。
         在一个线程执行写操作时，其他线程不能够执行读操作。
        
    com\alabada\con02_synch\producerconsumer2包下：
        当一个线程调用了条件对象的signal()或者signalAll()方法后，一个或者多个在该条件上挂起的线程将被唤醒，但这并不能保证让他们挂起的条件已经满足，
         所以必须在while循环中调用await（），在条件成立之前不能离开这个循环，如果条件不成立，将再次调用await（）。
         案例：解决经典的生产者-消费者问题
              生产者生产数据与消费者消费数据基于不同的条件对象；
              生产者在生成数据时，如果发现缓冲区已满，则将调用其中一个条件对象的await()方法；
              同理，当消费者消费数据时，发现缓冲区没有数据，则调用另一个对象的await()方法；
              缓冲区有数据了，相应条件对象又调用signalAll()方法来告知消费者可以消费了；
              同理，缓冲区有空间了，相应条件对象也调用signalAll()方法来告知生产者可以继续生成数据了。

        

### 线程同步辅助类
    com\alabada\con03_assist\semaphore包下：
        信号量的使用案例。
        可以看到，信号量对一些共享资源的访问控制做的非常好。
        信号量实现临界区必须遵循三个步骤，从而对共享资源的访问：
              首先，必须通过acquire()方法获得信号量；
              其次，使用共享资源执行必要的操作；
              最后，必须通过release方法释放信号量。
        二进制信号量可以保护对单一共享资源，或者单一临界区的访问，从而使得保护的资源在同一个时间内只能被一个线程访问。
        信号量也是可以用来保护一个资源的多个副本，或者被多个线程执行的临界区。

    com\alabada\con03_assist\countDownLatch包下：
        等待多个并发事件完成案例增加，通过辅助类（CountDownLatch）来实现。
        总的说来，该辅助类是用来实现线程间同步的
             比如说：10个人开会，如何做到在10个人都到齐后，会议正式开始。
        
             一个初始值，即定义必须等待的先行完成的操作的数目；
             await()方法，需要等待其他事件先完成的线程调用；
             countDown()方法，每个被等待的时间在完成的时候调用。
             当内部计数器到达0的时候，CountDownLatch对象将唤起所有的await（）方法上等待的线程。

        
    com\alabada\con03_assist\cyclicBarrier包下：
        也是同步辅助类，允许两个或者多个线程在某个点上进行同步。与CountDownLatch类似。
             CyclicBarrier类有一个内部计数器，可以控制指定书目的几个线程必须都达到集合点。
             每一个线程到达集合点就会调用awiat()方法通知CyclicBarrier对象，CyclicBarrier对象会让这个线程休眠直到其他所有的线程到达集合点。

        
    com\alabada\con03_assist\phaser包下：
        演示了phaser对象是如何同步多个线程的。
        解决多个线程之间同步的问题
        比如：对于一件事情，一个线程执行完成后挂起等待所有线程都将这件事情执行完毕后，才处理其他的线程。

    com\alabada\con03_assist\myPhaser包下：
        演示了自定义phaser对象是如何同步多个线程的。
        
    com\alabada\con03_assist\exchange包下：
        演示了两个线程之间是如何通过Exchange辅助类来实现数据交换的。
    

### 线程执行器
    com\alabada\con04_executor\demo01包下：
        缓存线程池的使用。
        使用ThreadPoolExecutor来执行任务；推荐使用Executors工厂来创建ThreadPoolExecutor.
        仅当线程的数量是合理的，或者线程只会运行很短的时间内，适合采用这种方法。

    com\alabada\con04_executor\demo02包下：
        创建固定大小的线程执行器
        使用Executors工厂类的newFixedThreadPool方法来创建执行器。创建了具有线程数量最大值的执行器。

    com\alabada\con04_executor\demo03_callable：
        执行器框架的优势：可以运行并发任务并返回结果。
        Callable：这个接口声明了call（）方法。可以在这个方法里实现任务的具体逻辑操作。
        Future:这个接口声明了一些方法来获取由Callable产生的结果，并管理它们的状态。













