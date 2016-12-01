package net.jcip.examples;

public class TestSafePoint {
	public static void main(String[] args) {
		final SafePoint originalSafePoint = new SafePoint(1,1);

	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	        	try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            originalSafePoint.set(2, 2);
	            System.out.println("Original : " + originalSafePoint.toString());
	        }
	    }).start();

	    //The other Thread is trying to create a copy. The copy, depending on the JVM, MUST be either (1,1) or (2,2)
	    //depending on which Thread starts first, but it can not be (1,2) or (2,1) for example.
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            SafePoint copySafePoint = new SafePoint(originalSafePoint);
	            System.out.println("Copy : " + copySafePoint.toString());
	        }
	    }).start();
	
		
	}
}
