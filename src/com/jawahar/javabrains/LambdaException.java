package com.jawahar.javabrains;

import java.util.function.BiConsumer;

public class LambdaException {
	//// ---- NEW ----
	public static void main(String[] args) {
		int[] someNumbers = {1, 2, 4, 6, 7};
		int key = 0;
		// step 0 -- process(someNumbers, key, (i, k) -> System.out.println(i / k));
		
		// step 1 -- 
		// process(someNumbers, key, step1WrapperLambda( (i, k) -> System.out.println(i / k) ));
		
		//process(someNumbers, key, step2WrapperLambda( (i, k) -> System.out.println(i / k) ));
		
		process(someNumbers, key, step4WrapperLambda( (i, k) -> System.out.println(i / k) ));
		

	}

	private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i: someNumbers) {
			//System.out.println(i + key);
			consumer.accept(i, key);
		}
		
	}
	// pass thru lambda
	private static BiConsumer<Integer, Integer> step1WrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return consumer;
	}
	
	// hard codes the lambda to always do addition
	private static BiConsumer<Integer, Integer> step2WrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (i, key) -> System.out.println(i + key);
	}
	
	// does what the consumer does with exceptions
	private static BiConsumer<Integer, Integer> step3WrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (i, key) -> { 
			try {
				System.out.println(i + ", " + key + " -->");
				consumer.accept(i, key);
			} 
			catch (ArithmeticException ae) {
				System.out.println("Exception in wrapper lambda");
			}
		};
	}
	
	private static <X, Y> BiConsumer<X, Y> step4WrapperLambda(BiConsumer<X, Y> consumer) {
		return (X, Y) -> { 
			try {
				System.out.println(X + ", " + Y + " -->");
				consumer.accept(X, Y);
			} 
			catch (ArithmeticException ae) {
				System.out.println("Exception in wrapper lambda");
			}
		};
	}

}
