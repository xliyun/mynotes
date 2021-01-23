package com.liyun.DesignPattern.memento.game;

import java.util.ArrayList;
import java.util.HashMap;

//守护者对象，保存游戏角色的状态
public class Caretaker {

	//如果只保存一次状态
	private Memento  memento;
	//对GameRole 保存多次状态
	//private ArrayList<Memento> mementos;
	//对多个游戏角色保存多个状态
	//private HashMap<String, ArrayList<Memento>> rolesMementos;

	public Memento getMemento() {
		return memento;
	}

	//还可以结合原型模式，快速复制一个对象
	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
	
}
