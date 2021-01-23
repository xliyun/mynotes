package com.liyun.DesignPattern.mediator;

public class ClientTest {

	public static void main(String[] args) {
		//创建一个中介者对象
		Mediator mediator = new ConcreteMediator();
		
		//创建Alarm并且加入到ConcreteMediator对象的HashMap
		Alarm alarm = new Alarm(mediator, "闹钟");
		
		//创建了CoffeeMachine对象，并且加入到ConcreteMediator对象的HashMap
		CoffeeMachine coffeeMachine = new CoffeeMachine(mediator,
				"咖啡机");

		//创建Curtains对象，并且加入到ConcreteMediator对象的HashMap
		Curtains curtains = new Curtains(mediator, "窗帘");
		TV tV = new TV(mediator, "TV");
		
		//让闹钟发出消息
		alarm.SendAlarm(0);
		coffeeMachine.FinishCoffee();
		alarm.SendAlarm(1);
	}

}
