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
        
### 同步 com.alabada.con02_synch
    Account，Bank，Company：
        一个对象的方法采用synchronized关键字进行申明，只能被一个线程访问。
        如果线程a正在执行一个同步方法syncMethonA(),线程b要执行这个对象的其他同步方法syncMethodB(),线程b将被阻塞，直到线程a访问完。但如果线程b访问的是同一个类的不同对象，那么两个线程都不会被阻塞。
    Cinema,TicketOffice1,TicketOffice2：
        使用对象作为synchronized()的参数来保护代码块。不同的属性使用不同的对象，通过这种方式，来达到对不同属性的同步控制。
    EventStorage,Producer,Consumer：
        典型的生产者与消费者问题
        wait(),notify(),notifyAll()方法的使用
    PrintQueue,Job：
        使用锁实现同步。















