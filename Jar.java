import java.util.Random;

public class Jar {
  private String mItemName;
  private int mMaxCapacity;
  private int mItemCount;
  
  public Jar (String itemName, int maxCapacity) {
    mItemName = itemName;
    mMaxCapacity = maxCapacity;
    mItemCount = 0;
  }
  
  public String getItemName() {
    return mItemName;
  }
  
  public int getMaxCapacity() {
    return mMaxCapacity;
  }
  
  public int getItemCount() {
    return mItemCount;
  }
  
  public void fill() {
    Random random = new Random();
    mItemCount = random.nextInt(mMaxCapacity) + 1; // incremented because the jar will never be emtpy, always between (inclusive) 1 and the maximum
    System.out.println("The jar is being filled...\n");
  }
}