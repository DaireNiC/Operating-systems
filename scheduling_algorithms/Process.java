package ie.gmit;

public class Process
  implements Comparable<Process>
{
  private int p_id;
  private int p_burst;
  private int p_wait_time;
  
  public Process(int p_id, int p_burst, int p_wait_time)
  {
    this.p_id = p_id;
    this.p_burst = p_burst;
    this.p_wait_time = p_wait_time;
  }

  public Process() {
	  //null constructor
  }
  
  public int compareTo(Process arg0)
  {
    int burst_time_compare = p_burst;
    
    return p_burst - burst_time_compare;
  }
  
  public int getP_id() {
    return p_id;
  }
  
  public void setP_id(int p_id) {
    this.p_id = p_id;
  }
  
  public int getP_burst()
  {
    return p_burst;
  }
  
  public void setP_burst(int p_burst)
  {
    this.p_burst = p_burst;
  }
  
  public int getP_wait_time()
  {
    return p_wait_time;
  }
  
  public void setP_wait_time(int p_wait_time)
  {
    this.p_wait_time = p_wait_time;
  }
  
  public String toString() {
    String s = "\n\nPID: " + getP_id() + "\nBurst Time " + getP_burst() + " \nWait Time: " + getP_wait_time();
    return s;
  }
}