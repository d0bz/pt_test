package com.playtech.testassignment.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RandomGeneratorServiceAsync {
	void getNumbers(int columns, int symbols, AsyncCallback<int[]> callback)
			throws IllegalArgumentException;
}
