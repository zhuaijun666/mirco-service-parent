package com.zhizu.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ActorSystemGate {
	private final static Logger LOGGER = LogManager.getLogger(ActorSystemGate.class);
	
	private final ActorSystem actorsystem = ActorSystem.apply("notify");

	private ActorRef notifyRef = null;

	private static ActorSystemGate instance = new ActorSystemGate();
	
	private ActorSystemGate(){
		this.init();
	}

	public static ActorSystemGate getInstance() {
		return instance;
	}
	
	public void init() {

		notifyRef = actorsystem.actorOf(
				new RoundRobinPool(8).props(Props.create(NotifyActor.class)),
				"notify");

	}

	public void putMessage(Object obj) {
		notifyRef.tell(obj, null);
	}


}
