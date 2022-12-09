package com.intuit.paymantservice.processor;

public interface Producer<T>{
	public void sendMessage(T message);
}
