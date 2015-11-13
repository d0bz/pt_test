package com.playtech.testassignment.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("get_numbers")
public interface RandomGeneratorService extends RemoteService {
	int[] getNumbers(int columns, int symbols) throws IllegalArgumentException;
}
