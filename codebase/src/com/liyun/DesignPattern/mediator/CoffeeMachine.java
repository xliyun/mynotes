package com.liyun.DesignPattern.mediator;

public class CoffeeMachine extends Colleague {

	public CoffeeMachine(Mediator mediator, String name) {
		super(mediator, name);
		mediator.Register(name, this);
	}

	@Override
	public void SendMessage(int stateChange) {
		this.GetMediator().GetMessage(stateChange, this.name);
	}

	public void StartCoffee() {
		System.out.println("该煮咖啡了!");
	}

	public void FinishCoffee() {

		System.out.println("5分钟之后!");
		System.out.println("咖啡煮好了!");
		SendMessage(0);
	}
}
