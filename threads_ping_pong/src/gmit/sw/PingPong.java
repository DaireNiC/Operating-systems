package gmit.sw;

public class PingPong implements Runnable{
    private boolean ping = false;
	public static void main(String[] args)
	{
	 Ping t1 = new Ping();
	 Pong t2 = new Pong();
	 
	 t1.start();
	 t2.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized void ping(int value) {
		while ( ping == false) {//make sure two pings don't happen twice in a row
	    	//false mean space in the contents , space to be written 
	        try {
	        	System.out.println("Ping");
	            wait();
	        } catch (InterruptedException e) { }
	    }
	    ping = false;
	    notifyAll();
	}
	
	public synchronized void pong(int value) {
	    while (ping == true) {
	        try {
	        	System.out.println("pong");
	            wait();
	        } catch (InterruptedException e) { }
	    }
	    ping = true;
	    notifyAll();
	}
	

}
 class Ping extends Thread{
	 
	public void run()
	{
		for (int i=0; i<=20; i++){
			  ping(20);
		}
	}
}
 class Pong extends Thread{
	 
	public void run()
	{
		for (int i=0; i<=20; i++){
			  System.out.print("Pong");
		}
	}
}

