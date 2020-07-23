package com.zhizu.akka;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by chengxiao on 15/7/19.
 */
@Slf4j
public class NotifyActor extends AbstractActor
{
	private void onReceive(String str){
		log.info(str);
	}

	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
				.match(String.class, s -> {
					onReceive(s);
				}).matchAny(s -> {
					log.info(s.toString());
		}).build();
	}
}
