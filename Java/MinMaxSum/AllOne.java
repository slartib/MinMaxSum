class AllOne 
{
  Map<String, Integer> allOne;

    public AllOne() 
    {
      allOne = new HashMap<>();
    }
    
    public void inc(String key) 
    {
      Integer count = allOne.get(key);
      if (count == null)
      {
        allOne.put(key, 1);
        count = Integer.valueOf(1);
      }
      else 
      {
        count++;
        allOne.put(key, count );
      }
    }
    
    public void dec(String key) 
    {
      Integer count = allOne.get(key);
      count--;
      if (count.intValue() == 0) 
      {
        allOne.remove(key);

      }
      else
      {
        allOne.put(key, count);
      }
    }
    
    public String getMaxKey() 
    {
     int maxC = -1;
      String maxK = "";
     for (Map.Entry<String,Integer> entry : allOne.entrySet()) 
     {
       if (entry.getValue().intValue() > maxC)
       {
         maxC = entry.getValue().intValue();
         maxK = entry.getKey();
       }
     }
      return maxK;
    }
    
    public String getMinKey() 
    {
     int minC = Integer.MAX_VALUE;
      String minK = "";
     for (Map.Entry<String,Integer> entry : allOne.entrySet()) 
     {
       if (entry.getValue().intValue() < minC)
       {
         minC = entry.getValue().intValue();
         minK = entry.getKey();
       }
     }
      return minK;
    }
}
