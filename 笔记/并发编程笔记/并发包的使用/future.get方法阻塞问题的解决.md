# 一、Future

Future模式是多线程设计常用的一种设计模式。Future模式可以理解成：我有一个任务，提交给了Future，Future替我完成这个任务。期间我自己可以去做任何想做的事情。一段时间之后，我就便可以从Future那儿取出结果。

Future提供了三种功能：

判断任务是否完成

能够中断任务

能够获取任务执行的结果

向线程池中提交任务的submit方法不是阻塞方法，**而Future.get方法是一个阻塞方法，当submit提交多个任务时，只有所有任务都完成后，才能使用get按照任务的提交顺序得到返回结果，所以一般需要使用future.isDone先判断任务是否全部执行完成，完成后再使用future.get得到结果。（也可以用get (long timeout, TimeUnit unit)方法可以设置超时时间，防止无限时间的等待）**

三段式的编程：1.启动多线程任务2.处理其他事3.收集多线程任务结果，Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作；要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源。

解决方法：CompletionService和CompletableFuture（**按照任务完成的先后顺序获取任务的结果**）

# 二、CompletionService是java1.8之前最好用的方法

能够实现按照任务完成的先后顺序获取任务的结果。

```java
publicclassTestCompletionService{
privatestaticfinalString commandstr01 = "hahah";
privatestaticfinalString commandstr02 = "hahah";
publicstaticvoid main(String[] args) throws InterruptedException, ExecutionException {
//1、创建一个线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
		completionService.submit(new MyThreadt33(commandstr01));
		completionService.submit(new MyThreadt44(commandstr01));
		executorService.shutdown();
		System.out.println(completionService.take().get());
		System.out.println(completionService.take().get());
	}
}
classMyThreadt33implementsCallable<String>{
privateString commandstr;          // 要运行的mingling
public MyThreadt33(String commandstr) {
		this.commandstr = commandstr;
	}
	@Override
publicString call() throws Exception {
int sum = 0;
for (int i = 0; i < 100; i++) {
			Thread.sleep(200);
			sum += i;
			System.out.println("Mythread3: "+i);
		}
returnString.valueOf(sum+300000);
	}
}
classMyThreadt44implementsCallable<String>{
privateString commandstr;          // 要运行的mingling
public MyThreadt44(String commandstr) {
		this.commandstr = commandstr;
	}
	@Override
publicString call() throws Exception {
int sum = 0;
for (int i = 0; i < 50; i++) {
			Thread.sleep(200);
			sum += i;
			System.out.println("Mythread4: "+i);
		}
returnString.valueOf(sum+400000);
	}
}
```

CompletionService方法可以通过completionService.take().get()方法获取最快完成的线程的返回结果（若当前没有线程执行完成则阻塞直到最快的线程执行结束），第二次调用则返回第二快完成的线程的返回结果。

# 三、CompletableFureture接口

所谓异步调用其实就是实现一个可无需等待被调函数的返回值而让操作继续运行的方法。简单的讲就是另启一个线程来完成调用中的部分计算，使调用继续运行或返回，而不需要等待计算结果。但调用者仍需要取线程的计算结果。

JDK1.5新增了Future接口，用于描述一个异步计算的结果。虽然 Future 以及相关使用方法提供了异步执行任务的能力，但是对于结果的获取却是很不方便，只能通过阻塞或者轮询的方式得到任务的结果。

JDK1.8后提出了CompletableFuture接口实现了Future和CompletionStage两个接口，CompletionStage可以看做是一个异步任务执行过程的抽象（CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段，一个阶段的计算执行可以是一个Function，Consumer或者Runnable。比如：

```
stage.thenApply(x-> square(x)).thenAccept(x-> System.out.print(x)).thenRun(() -> System.out.println())）
```

我们可以基于CompletableFuture创建任务和**链式处理多个任务****，并实现**按照任务完成的先后顺序获取任务的结果。

1.创建任务

\##使用runAsync方法新建一个线程来运行Runnable对象(无返回值)；

\##使用supplyAysnc方法新建线程来运行Supplier<T>对象(有返回值)；

\##基于线程池创建

2.任务的异步处理

不论Future.get()方法还是CompletableFuture.get()方法都是阻塞的，为了获取任务的结果同时不阻塞当前线程的执行，我们可以使用CompletionStage提供的方法结合callback来实现任务的异步处理。

\##whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
\##whenCompleteAsync：把 whenCompleteAsync 这个任务继续提交给线程池来进行执行，也就是并行执行。

\##thenApply：当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化

\##thenAccept：thenAccept接收上一阶段的输出作为本阶段的输入，并消费处理，无返回结果。　

\##thenRun：不关心前一阶段的计算结果，因为它不需要输入参数，进行消费处理，无返回结果。

\## thenCombine：会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。

\## applyToEither ：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。

\##acceptEither 方法：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作

```java
public class TestCompletableFuture {
	private static final String commandstr01 = "hahah";
	private static final String commandstr02 = "hahah";
	private static final String commandstr03 = "hahah";
	private static final String commandstr04 = "hahah";
 
	    public static void main(String[] args) throws InterruptedException, ExecutionException{
	        
	    	ExecutorService executorService = Executors.newCachedThreadPool();
	    	
	        CompletableFuture.supplyAsync(new MyThreadt444(commandstr02),executorService).whenComplete((result, e) -> {
	        	//执行线程执行完以后的操作。
	            System.out.println(result + " " + e);
	        }).exceptionally((e) -> {
	            //抛出异常
	        	System.out.println("exception " + e);
	            return "exception";
	        });
	        
	         CompletableFuture.supplyAsync(new MyThreadt333(commandstr02),executorService).whenComplete((result, e) -> {
	        	//执行线程执行完以后的操作。
	        	System.out.println(result + " " + e);
	        }).exceptionally((e) -> {
	            System.out.println("exception " + e);
	            return "exception";
	        });
	    }
}
 
 
 
class MyThreadt333 implements Supplier<String>{
 
	private String commandstr;          // 要运行的mingling
	public MyThreadt333(String commandstr) {
		this.commandstr = commandstr;
	}
	@Override
	public String get() {
		int sum = 0;
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum += i;
			System.out.println("Mythread333: "+i);
		}
		return String.valueOf(sum+300000);
	}
}
 
class MyThreadt444 implements Supplier<String>{
 
	private String commandstr;          // 要运行的mingling
	public MyThreadt444(String commandstr) {
		this.commandstr = commandstr;
	}
	@Override
	public String get() {
		int sum = 0;
		for (int i = 0; i < 40; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum += i;
			System.out.println("Mythread444: "+i);
		}
		return String.valueOf(sum+400000);
	}
}
```

四、集中多线程并发取结果方式的总结

![img](D:\github\mynotes\笔记\并发编程笔记\并发包的使用\future.get方法阻塞问题的解决\d3bc2ea737ba44c3bf811f60d7499169.png)