package test;

 class SafePoint {
    private int x;
    private int y;

    public SafePoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public SafePoint(SafePoint safePoint){
        this(safePoint.x, safePoint.y);
    }

    public synchronized int[] getXY(){
        return new int[]{x,y};
    }

    public synchronized void setXY(int x, int y){
    	System.out.println("~~~~~");
        this.x = x;
        //Simulate some resource intensive work that starts EXACTLY at this point, causing a small delay
        try {
            Thread.sleep(10 * 100);
        } catch (InterruptedException e) {
         e.printStackTrace();
        }
        this.y = y;
    }

    public String toString(){
      return x+""+y;
    }
}

public class SafePointMain {
public static void main(String[] args) throws Exception {
    final SafePoint originalSafePoint = new SafePoint(1,1);

    //One Thread is trying to change this SafePoint
    new Thread(new Runnable() {
        @Override
        public void run() {
            originalSafePoint.setXY(2, 2);
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