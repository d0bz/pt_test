package com.playtech.testassignment.server;

import java.util.Random;

import com.playtech.testassignment.client.RandomGeneratorService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RandomGeneratorServiceImpl extends RemoteServiceServlet implements
		RandomGeneratorService {

	/**
	 * Returns random generated numbers in int array
	 * @param columns Number on reels
	 * @param symbols Number of total symbols
	 * @return int array of random numbers
	 */
	public int[] getNumbers(int columns, int symbols) throws IllegalArgumentException {
		
		Random generator = new Random();
		
		int[] randNumbers = new int[columns];
		for(int col = 0; col < columns; col++){
			//randNumbers[col] = symbols-1; //If you want to win
			randNumbers[col] = generator.nextInt(symbols);
		}

		return randNumbers;
	}

	
}
