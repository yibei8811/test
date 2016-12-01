package net.jcip.examples;

 class ThreadB extends Thread{
    int total;
    public void run(){
        synchronized(this){
            for(int i=0;i<101;i++){
                total+=i;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("~~~~~~~~");
            notify();
        }
        for(;;);
    }
    public int getTotal(){
        return this.total;
    }

}



/*   第二个类*/
public class cccc extends Thread{
    ThreadB c;
    public cccc(ThreadB c){
        this.c=c;
    }
    public void run(){
        synchronized (c) {
            try{
//            	while(true){
            		System.out.println(Thread.currentThread()+"等待计算结果。。。。");
            		c.wait();
//            	}
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+"计算结果为："+c.getTotal());
        }
    }
    public static void main(String[] args) {
        ThreadB calculator = new ThreadB();

        cccc reader1=new cccc(calculator);      
        cccc reader2=new cccc(calculator);
        cccc reader3=new cccc(calculator);
        reader1.start();
        reader2.start();
        reader3.start();
        calculator.start();

        for(;;);
    }
}