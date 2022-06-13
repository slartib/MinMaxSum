package com.rkd;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import com.rkd.Node;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SinglyLinkedListNode
{
  int data;
  SinglyLinkedListNode next;

  SinglyLinkedListNode(int dataIn)
  {
    data = dataIn;
  }
}

class GraphNode
{
  public int val;
  public List<GraphNode> neighbors;
  public GraphNode() {
    val = 0;
    neighbors = new ArrayList<GraphNode>();
  }
  public GraphNode(int _val) {
    val = _val;
    neighbors = new ArrayList<GraphNode>();
  }
  public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

class NtreeNode {
  public int val;
  public List<NtreeNode> children;


  public NtreeNode() {
    children = new ArrayList<NtreeNode>();
  }

  public NtreeNode(int _val) {
    val = _val;
    children = new ArrayList<NtreeNode>();
  }

  public NtreeNode(int _val,ArrayList<NtreeNode> _children) {
    val = _val;
    children = _children;
  }
};

class AllOne
{
  Map<String, Integer> allOne;
  TreeMap<Integer, List<String>> countMap;
  LinkedList<String> minMaxList;
  int maxC;
  int minC;

  public AllOne()
  {
    allOne = new HashMap<>();
    countMap = new TreeMap<>();
    minMaxList = new LinkedList<>();
    maxC = Integer.MIN_VALUE;
    minC = Integer.MAX_VALUE;
  }
  private void removeFromCountMap(Integer count, String key)
  {
    if (!countMap.isEmpty() && countMap.containsKey(count))
    {
      if (countMap.get(count).size() == 1 )
      {
        if (countMap.get(count).get(0).equals(key))
        {
          countMap.remove(count);
        }
      }
      else
      {
        countMap.get(count).remove(key);
      }
    }
  }
  private void addToCountMap(Integer count, String key)
  {
    if (countMap.containsKey(count))
    {
      countMap.get(count).add(key);
    }
    else
    {
      ArrayList<String> temp = new ArrayList<>();
      temp.add(key);
      countMap.put(count, temp);
    }
  }

  public void inc(String key)
  {
    Integer count = allOne.get(key);
    if (count == null)
    {
      allOne.put(key, 1);
      count = Integer.valueOf(1);

      addToCountMap(count, key);
    }
    else
    {
      removeFromCountMap(count, key);
      count++;
      allOne.put(key, count );
      addToCountMap(count, key);
    }

  }

  public void dec(String key)
  {
    Integer count = allOne.get(key);
    removeFromCountMap(count, key);

    count--;
    if (count.intValue() == 0)
    {
      allOne.remove(key);
    }
    else
    {
      allOne.put(key, count);
      addToCountMap(count, key);
    }
  }

  public String getMaxKey()
  {
    String maxK = "";
    if (!countMap.isEmpty())
    {
      maxK = countMap.lastEntry().getValue().get(0);
    }

    return maxK;
  }

  public String getMinKey()
  {
    String minK = "";
    if (!countMap.isEmpty())
    {
      minK = countMap.firstEntry().getValue().get(0);
    }

    return minK;
  }
}

public class Main
{

  public static List<Integer> evenOddInput = new ArrayList<>();
  public static List<List<Integer>> evenOddQueries = new ArrayList<>();
  public static List<List<Integer>> prisonerQueries = new ArrayList<>();


  public static void main(String[] args)
  {
  /*  List<Integer> input = new ArrayList<Integer>();
    input.add(7);
    input.add(69);
    input.add(2);
    input.add(221);
    input.add(8974);
    Main.miniMaxSum(input);
    System.out.println(Main.timeConversion("11:21:00PM"));
    System.out.println(Main.timeConversion("12:21:00AM"));

    System.out.println(Main.camelCase("S;M;plasticCup()"));
    System.out.println(Main.camelCase("C;V;mobile phone"));
    System.out.println(Main.camelCase("C;C;coffee machine"));
    System.out.println(Main.camelCase("S;C;LargeSoftwareBook"));
    System.out.println(Main.camelCase("C;M;white sheet of paper"));
    System.out.println(Main.camelCase("S;V;pictureFrame"));

    System.out.println(Main.divisibleSumPairs(6, 3, List.of(new Integer[]{1, 3, 2, 6, 1, 2})));

    System.out.println(lightCombinations(1000));

    System.out.println(summingSeries(1));
    System.out.println(summingSeries(2));
    System.out.println(summingSeries(5));
    System.out.println(summingSeries(100));
    System.out.println(summingSeries(1000000));

    Main.find(new int[]{2, 3, 5, 8, 2}, 1, 4);
*/

        
/*
        List<Integer> evenOddInput = IntStream.of(6, 5, 6, 9, 3, 7, 4, 5, 2, 5, 4, 7, 4, 4, 3, 0, 8, 8, 6, 8, 8, 4, 3, 1, 4, 9, 2, 0, 9, 8, 9, 2, 6, 6, 4, 9, 5, 0, 3, 8, 7, 1, 7, 2, 7, 2, 2, 6, 1, 0, 9, 1, 5, 9, 4, 9, 0, 7, 1, 7, 7, 1, 1, 5, 9, 7, 7, 6, 7, 3, 6, 5, 6, 3, 9, 4, 8, 1, 2, 9, 3, 9, 0, 1, 8, 5, 0, 4, 6, 3, 8, 5, 6, 1, 1, 5, 9, 8, 4, 8).boxed().toList();
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(new ArrayList<Integer>(Arrays.asList(42, 51)));
        queries.add(new ArrayList<Integer>(Arrays.asList(1, 94)));
        queries.add(new ArrayList<Integer>(Arrays.asList(35, 65)));
        queries.add(new ArrayList<Integer>(Arrays.asList(15, 25)));
        queries.add(new ArrayList<Integer>(Arrays.asList(57, 80)));
        queries.add(new ArrayList<Integer>(Arrays.asList(44, 92)));
        queries.add(new ArrayList<Integer>(Arrays.asList(28, 66)));
        queries.add(new ArrayList<Integer>(Arrays.asList(37, 60)));
        queries.add(new ArrayList<Integer>(Arrays.asList(33, 52)));
        queries.add(new ArrayList<Integer>(Arrays.asList(29, 30)));
        queries.add(new ArrayList<Integer>(Arrays.asList(8, 76)));
        queries.add(new ArrayList<Integer>(Arrays.asList(22, 75)));
        queries.add(new ArrayList<Integer>(Arrays.asList(59, 96)));
        queries.add(new ArrayList<Integer>(Arrays.asList(30, 38)));
        queries.add(new ArrayList<Integer>(Arrays.asList(36, 94)));
        queries.add(new ArrayList<Integer>(Arrays.asList(19, 29)));
        queries.add(new ArrayList<Integer>(Arrays.asList(12, 44)));
        queries.add(new ArrayList<Integer>(Arrays.asList(29, 30)));
        queries.add(new ArrayList<Integer>(Arrays.asList(5, 77)));
        queries.add(new ArrayList<Integer>(Arrays.asList(44, 64)));
        queries.add(new ArrayList<Integer>(Arrays.asList(14, 39)));
        queries.add(new ArrayList<Integer>(Arrays.asList(7, 41)));
        queries.add(new ArrayList<Integer>(Arrays.asList(5, 19)));
        queries.add(new ArrayList<Integer>(Arrays.asList(29, 89)));
        queries.add(new ArrayList<Integer>(Arrays.asList(18, 70)));
        queries.add(new ArrayList<Integer>(Arrays.asList(18, 97)));
        queries.add(new ArrayList<Integer>(Arrays.asList(16, 25)));
*/
        /*
        Main.readEvenOddInputs("Z:\\Development\\Projects\\Java\\MinMaxSum\\evenodd_input3.txt");
        List<String> evenOddOutput = Main.evenOdd(Main.evenOddInput, Main.evenOddQueries);
        int i = 1;
        for (String output : evenOddOutput)
        {
            //System.out.println("Result " + i++ + " = " + output);
            System.out.println(output);
        } */
     /*   List<Integer> arr = new ArrayList<>(Arrays.asList(1, 3, 4, 3, 1, 3, 1, 4, 5));
        int lonelyint = Main.lonelyinteger(arr);
        System.out.println("lonelyint: " + lonelyint);

        System.out.println(Main.divisors(1));
        System.out.println(Main.divisors(2));
        System.out.println(Main.divisors(3));
        System.out.println(Main.divisors(4));
        System.out.println(Main.divisors(5));
        System.out.println(Main.divisors(6));
        System.out.println(Main.divisors(7));
        System.out.println(Main.divisors(8));
        System.out.println(Main.divisors(9));
        System.out.println(Main.divisors(10));
        System.out.println(Main.divisors(11));
        System.out.println(Main.divisors(12));
        System.out.println(Main.divisors(13));
        System.out.println(Main.divisors(14));
        System.out.println(Main.divisors(15));
        System.out.println(Main.divisors(16));
        System.out.println(Main.divisors(17));
        System.out.println(Main.divisors(18));
        System.out.println(Main.divisors(19));
        System.out.println(Main.divisors(20));

    */
        /*
        Main.readPrisonerInputs("Z:\\Development\\Projects\\Java\\MinMaxSum\\prisoners_input.txt");
        for (List<Integer> list : prisonerQueries)
        {
            System.out.println(Main.saveThePrisoner(list.get(0), list.get(1), list.get(2)));
        }
         */

    //System.out.println(Main.countSubstrings("aabbcdefghijklmnop", 0, 10));
    //System.out.println(Main.countSubstrings2("aabbcdefghijklmnop", 0, 10));

    /*System.out.println("Permutations: " + sherlockPermutations(2, 3));
    System.out.println("Permutations: " + sherlockPermutations(10, 10));
    System.out.println("Permutations: " + sherlockPermutations(50, 50));
    System.out.println("Permutations: " + sherlockPermutations(100, 100));

    System.out.println("Permutations: " + sherlockPermutations2(2, 3));
    System.out.println("Permutations: " + sherlockPermutations2(10, 10));
    System.out.println("Permutations: " + sherlockPermutations2(50, 50));
    System.out.println("Permutations: " + sherlockPermutations2(100, 100));
*/
    //Main.findZigZagSequence(new int[]{1,2,3,4,5,6,7}, 7);
/*
    System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,1}));
    System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));

    System.out.println(generalGCD(new int[]{2, 4, 12, 18, 36}));
    System.out.println(generalGCD(new int[]{6, 9, 12, 18, 36}));
    System.out.println(generalGCD(new int[]{2, 5, 8, 18, 36}));

    int k = 3;    // total number of linked lists
*/
    // an array to store the head nodes of the linked lists
   /* Node[] lists = new Node[k];

    lists[0] = new Node(1);
    lists[0].next = new Node(5);
    lists[0].next.next = new Node(7);

    lists[1] = new Node(2);
    lists[1].next = new Node(3);
    lists[1].next.next = new Node(6);
    lists[1].next.next.next = new Node(9);

    lists[2] = new Node(4);
    lists[2].next = new Node(8);
    lists[2].next.next = new Node(10);

    // Merge all lists into one
    Node head = mergeKLists(lists);

    printList(head);

    */
    /*
    List<String> codeList = new ArrayList<>();
    codeList.add("apple apple");
    codeList.add("anything orange grapes");
    codeList.add("pear");

    List<String> shoppingList = new ArrayList<>();
    shoppingList.add("apple");
    shoppingList.add("apple");
    shoppingList.add("grapes");
    shoppingList.add("banana");
    shoppingList.add("banana");
    shoppingList.add("orange");
    shoppingList.add("banana");
    shoppingList.add("pear");
    shoppingList.add("pineapple");


    System.out.println("Shopping List matches promotion: " + shoppingListMatchesPromotion2(codeList, shoppingList));


    List<String> codeList2 = new ArrayList<>();
    codeList.add("apple pear");
    codeList.add("banana anything banana");
    codeList.add("pear");

    List<String> repos = new ArrayList<>();
    repos.add("bags");
    repos.add("banner");
    repos.add("ball");
    repos.add("bat");
    repos.add("baggage");
    repos.add("battery");


    List<List<String>> recommends = recomendations(repos, "battery");

    System.out.println(recommends.toString());
    */
    //System.out.println(Main.birthday(new ArrayList<>(Arrays.asList(2, 5, 1, 3, 4, 4, 3, 5, 1, 1, 2, 1, 4, 1, 3, 3, 4, 2, 1)), 18, 7));
    //System.out.println(NthFib(1000));
    /*
    List<Integer> sticks = new ArrayList<>();
    sticks.add(1000000000);
    sticks.add(1000000000);
    sticks.add(1000000000);
    sticks.add(1000000000);
    sticks.add(1000000000);
    sticks.add(1000000000);

    List<Integer> triangle = maximumPerimeterTriangle(sticks);

    for (Integer side : triangle)
    {
      System.out.println(side);
    }
    */
    //String s = "ACDAAFFAA";
    //System.out.println(uniqueLetterString2(s));
    //System.out.println(squares(17, 880000000));

   /* int[] nums = new int[]{3,5,-1, -2, 6, 1, 2, 9, 7, -5, 11};
    int[] maxValues = maxSlidingWindow3(nums, 4);
    System.out.print("[");
    for (int i = 0; i < maxValues.length; i++)
    {
      if (i > 0)
      {
        System.out.print(",");
      }
      System.out.print(maxValues[i]);
    }
    System.out.print("]\n");

    */
    /*System.out.println(strangeCounter(2));
    System.out.println(strangeCounter(4));
    System.out.println(strangeCounter(7));
    System.out.println(strangeCounter(15));
    System.out.println(strangeCounter(19));
    System.out.println(strangeCounter(10));
    System.out.println(strangeCounter(20));
    System.out.println(strangeCounter(21));
    System.out.println(strangeCounter(22));

     */

    /*List<String> G = new ArrayList<>();
    G.add("111111111111111");
    G.add("111111111111111");
    G.add("111111011111111");
    G.add("111111111111111");
    G.add("111111111111111");

    List<String> P = new ArrayList<>();
    P.add("11111");
    P.add("11111");
    P.add("11110");
    System.out.println(gridSearch(G, P));

    G = new ArrayList<>();
    G.add("123412");
    G.add("561212");
    G.add("123634");
    G.add("781288");

    P = new ArrayList<>();
    P.add("12");
    P.add("34");

    System.out.println(gridSearch(G, P));

     */
    int[] result = longestSubArray(new int[]{1, 5, -1, 3, 3, 5, 4, 6, 8, 9, 5});
    for (int i = 0; i < result.length; i++)
    {
      System.out.println(result[i]);
    }
  }

  public NtreeNode cloneTree(NtreeNode root)
  {
    if (root == null)
    {
      return null;
    }
    if (root.children.isEmpty())
    {
      return new NtreeNode(root.val);
    }
    NtreeNode newNode = new NtreeNode(root.val);
    for (NtreeNode child : root.children)
    {
      newNode.children.add(cloneTree(child));
    }
    return newNode;
  }

  public static GraphNode cloneGraph(GraphNode node)
  {
    if (node == null)
    {
      return node;
    }
    if (node.neighbors.isEmpty())
    {
      return new GraphNode(node.val);
    }
    Queue<GraphNode> nodeQueue = new LinkedList<>();
    nodeQueue.add(node);
    Map<GraphNode, GraphNode> visited = new HashMap<>();

    visited.put(node, new GraphNode(node.val));
    while (!nodeQueue.isEmpty())
    {
      GraphNode current = nodeQueue.poll();
      GraphNode currentClone = visited.get(current);
      for (GraphNode neighbor : current.neighbors)
      {
        GraphNode clonedNeighbor = visited.get(neighbor);
        if (clonedNeighbor == null)
        {
          nodeQueue.add(neighbor);
          clonedNeighbor = new GraphNode(neighbor.val);
          visited.put(neighbor, clonedNeighbor);
        }
        currentClone.neighbors.add(clonedNeighbor);
      }
    }
    return visited.get(node);
  }

  public static int numTrees(int n)
  {
    // Calculates the number of trees by determining the
    // nth Catalan number
    long result = 1;

    for (long i = 1; i <= n; i++)
    {
      result *= (4L * i - 2);
      result /= (i + 1);
    }
    return (int) result;

  }


  public static int[] countBits(int n)
  {
    int[] ans = new int[n + 1];
    for (int i = 0; i <= n; i++)
    {
      int num = i;
      while (num != 0)
      {
        if (num % 2 == 1)
        {
          ans[i]++;
        }
        num = num >> 1;
      }
    }
    return ans;
  }

  public static int maxArea(int[] height)
  {
    int maxArea = 0;
    int left = 0;
    int right = height.length - 1;

    while (left < right)
    {
      int width = right - left;
      int area = Math.min(height[left], height[right]) * width;
      maxArea = Math.max(maxArea, area);
      if (height[left] < height[right])
      {
        left++;
      }
      else
      {
        right--;
      }
    }
    return maxArea;
  }

  private static void shiftLeft(int[] nums, int index, int shiftNum)
  {
    if (index < nums.length && index - shiftNum >= 0)
    {
      for (int i = index; i < nums.length; i++)
      {
        nums[i - shiftNum] = nums[i];
      }
      for (int j = nums.length - 1; j > nums.length - shiftNum - 1; j--)
      {
        nums[j] = Integer.MIN_VALUE;
      }
    }
  }

  public static int removeDuplicates(int[] nums)
  {
    if (nums.length == 0 || nums.length == 1)
    {
      return nums.length;
    }

    int k = nums.length;

    for (int i = 1; i < k; i++)
    {
      int shiftNum = 0;
      int iTemp = i;
      while (i < nums.length && nums[i] == nums[i - 1])
      {
        shiftNum++;
        i++;
        k--;
      }
      if (shiftNum > 0)
      {
        shiftLeft(nums, i, shiftNum);
      }
      i = iTemp;

    }
    return k;
  }

/*
7
2
0
7
3
1
6
3
1
6
2
0
9
3
1
12
4
1
1
1
1
2
1
1
100000009
33
17
5
2
1
5
2
2
5
2
0
 */
  public static double champagneTower(int poured, int query_row, int query_glass)
  {
    if (query_row == 0 )
    {
      if (poured > 1)
      {
        return 1.0;
      }
      return poured;
    }
    double dRow = (double) query_row;
    double rowMax = ((dRow + 1.0) * (dRow + 2.0)) / 2.0;
    double priorRowMax = query_row > 1 ? (dRow * (dRow + 1.0)) / 2.0 : 1.0;
    if (poured >= rowMax)
    {
      return 1.0;
    }
    if (poured <= (int) priorRowMax)
    {
      return 0.0;
    }
    double partialPour = (double) poured - priorRowMax;
    boolean isExterior = query_row == 1 || query_glass == 0 || query_glass == query_row;
    double levelPerGlass = partialPour / dRow;
    if (isExterior)
    {
      levelPerGlass /= 2.0;
    }
    return levelPerGlass;
  }

  public static boolean isSubsequence(String s, String t)
  {
    boolean isSubsequence = true;
    int currentIndex = 0;
    int i = 0;
    while (isSubsequence && i < s.length())
    {
      currentIndex = t.indexOf(s.charAt(i), currentIndex);
      if (currentIndex < 0)
      {
        isSubsequence = false;
      }
      else
      {
        i++;
        currentIndex++;
      }
    }
    return isSubsequence;
  }

  public static int[] longestSubArray(int[] arr)
  {
    if (arr == null)
    {
      return null;
    }
    if (arr.length == 1)
    {
      return arr;
    }
    //LinkedList<Integer> uniqueNums = new LinkedList<>();
    Map<Integer, Integer> nums = new HashMap<>();
    int start = 0;
    int maxStart = 0;

    nums.put(arr[start], start);

    int end = 1;
    int maxEnd = 0;
    while (start < end && start < arr.length && end < arr.length)
    {
      if (!nums.containsKey(arr[end]))
      {
        nums.put(arr[end], end);


        if (end - start > maxEnd - maxStart)
        {
          maxEnd = end;
          maxStart = start;
        }
        end++;
      }
      else
      {

        start = Math.max(nums.get(arr[end]) + 1, start);
        nums.put(arr[end], end);
        end++;

      }
    }
    if (end - start > maxEnd - maxStart)
    {
      maxEnd = end;
      maxStart = start;
    }

    int[] result = new int[maxEnd - maxStart + 1];

    int j = 0;
    for (int i = maxStart; i <= maxEnd; i++, j++)
    {
      result[j] = arr[i];
    }
    return result;
  }

  public static String longestCommonPrefix(String[] strs)
  {
    StringBuilder sb = new StringBuilder();
    if(strs.length > 0)
    {
      sb.append(strs[0]);
      for (int i = 1; i < strs.length; i++)
      {
        int j = 0;
        boolean commonPrefixComplete = false;
        if (sb.length() > strs[i].length())
        {
          sb.delete(strs[i].length(), sb.length());
        }
        while (j < strs[i].length() && j < sb.length() && !commonPrefixComplete)
        {

          if (strs[i].charAt(j) != sb.charAt(j))
          {
            commonPrefixComplete = true;
            sb.delete(j, sb.length());
          }
          j++;
        }
      }
    }
    return sb.toString();
  }

  public static String encryption(String s)
  {

    StringBuilder sb = new StringBuilder();
    //List<List<String>> grid = new ArrayList<List<String>>();
    String sNoSpaces = s.replaceAll(" ", "");
    int sLen = sNoSpaces.length();
    if (sLen <= 1)
    {
      return sNoSpaces;
    }
    int rows = (int) Math.floor(Math.sqrt(sLen));
    int columns = (int) Math.ceil(Math.sqrt(sLen));
    if (rows * columns < sLen)
    {
      rows++;
    }

    int row = 0;
    int col = 0;
    char[][] grid = new char[rows][columns];
    for (int i = 0; i < sNoSpaces.length(); i++)
    {
      if (col == columns)
      {
        col = 0;
        row++;
      }
      grid[row][col] = sNoSpaces.charAt(i);
      col++;
    }
    for (int j = 0; j < columns; j++)
    {
      if (j > 0)
      {
        sb.append(" ");
      }
      for (int k = 0; k < rows; k++)
      {
        if (grid[k][j] != 0)
        {
          sb.append(grid[k][j]);
        }
      }
    }

    return sb.toString();
  }

  class MedianFinder {
    List<Integer> medianStore;
    int midpoint;
    int count;
    boolean isEven;
    public MedianFinder()
    {
      medianStore = new ArrayList<>();
    }
    public static int titleToNumber(String columnTitle)
    {
      Map<Character, Integer> charMap = new HashMap<>();
      int i = 1;
      for (char c = 'A'; c <= 'Z'; c++)
      {
        charMap.put(c, i);
        i++;
      }
      int num = 0;
      int len = columnTitle.length();
      for (int j = len - 1; j >= 0; j--)
      {
        int charIndex = (len - 1) - j;
        Integer charNum = charMap.get(columnTitle.charAt(charIndex));
        num += charNum * Math.pow(26, j);
      }
      return num;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target)
    {
      List<List<Integer>> quadruplets = new ArrayList<>();
      if (nums.length >= 4)
      {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++)
        {
          if (i > 0 && nums[i] == nums[i - 1])
          {
            continue;
          }
          for (int j = i + 1; j < nums.length - 2; j++)
          {
            if (j != i + 1 && nums[j] == nums[j - 1])
            {
              continue;
            }
            int left = j + 1;
            int right = nums.length - 1;
            while (left < right)
            {
              int sum = nums[i] + nums[j] + nums[left] + nums[right];
              if (sum > target)
              {
                right--;
              }
              else if (sum < target)
              {
                left++;
              }
              else
              {
                List<Integer> quadruplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                quadruplets.add(quadruplet);
                left++;
                right--;
                while (nums[left] == nums[left - 1] && left < right)
                {
                  left++;
                }
                while (nums[right] == nums[right + 1] && left < right)
                {
                  right--;
                }
              }
            }
          }
        }
      }
      return quadruplets;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n)
    {
      if (n == 0)
      {
        return;
      }
      if (m == 0)
      {
        if (n >= 0)
        {
          System.arraycopy(nums2, 0, nums1, 0, n);
        }
        return;
      }
      int[] merged = new int[m+n];
      int i = 0;
      int j = 0;
      int mergedIndex = 0;

      while (i < m && j < n)
      {
        if (nums1[i] <= nums2[j])
        {
          merged[mergedIndex] = nums1[i];
          i++;
        }
        else
        {
          merged[mergedIndex] = nums2[j];
          j++;
        }
        mergedIndex++;
      }
      if (i == m)
      {
        for (; j < n; j++)
        {
          merged[mergedIndex] = nums2[j];
          mergedIndex++;
        }
      }
      else if (j == n)
      {
        for (; i < m; i++)
        {
          merged[mergedIndex] = nums1[i];
          mergedIndex++;
        }
      }
      if (m + n >= 0)
      {
        System.arraycopy(merged, 0, nums1, 0, m + n);
      }
    }

    public static List<List<Integer>> fourSum2(int[] nums, int target)
    {
      List<List<Integer>> quadruplets = new ArrayList<>();
      if (nums.length >= 4)
      {
        for (int i = 0; i < nums.length - 3; i++)
        {
          for (int j = i + 1; j < nums.length - 2; j++)
          {
            for (int k = j + 1; k < nums.length - 1 ; k++)
            {
              for (int m = k + 1; m < nums.length; m++)
              {
                int sum = nums[i] + nums[j] + nums[k] + nums[m];
                if (sum == target)
                {
                  List<Integer> quadruplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                  Collections.sort(quadruplet);
                  if (!quadruplets.contains(quadruplet))
                  {
                    quadruplets.add(quadruplet);
                  }
                }
              }
            }
          }
        }
      }
      return quadruplets;
    }
    
    public static List<List<Integer>> threeSum(int[] nums)
    {
      List<List<Integer>> triplets = new ArrayList<>();
      if (nums.length >= 3)
      {
        for (int i = 0; i < nums.length - 2; i++)
        {
          for (int j = i + 1; j < nums.length - 1; j++)
          {
            for (int k = j + 1; k < nums.length; k++)
            {
              int sum = nums[i] + nums[j] + nums[k];
              if (sum == 0)
              {
                List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                Collections.sort(triplet);
                if (!triplets.contains(triplet))
                {
                  triplets.add(triplet);
                }
              }
            }
          }
        }
      }
      return triplets;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2)
    {
      ListNode mergedHead = null;
      ListNode mergedCurrent = null;
      ListNode current1 = list1;
      ListNode current2 = list2;

      while (current1 != null && current2 != null)
      {
        ListNode nextNode = null;
        if (current1.val < current2.val)
        {
          nextNode = current1;
          current1 = current1.next;
        }
        else
        {
          nextNode = current2;
          current2 = current2.next;
        }
        if (mergedHead == null)
        {
          mergedHead = nextNode;
          mergedCurrent = nextNode;
        }
        else
        {
          mergedCurrent.next = nextNode;
          mergedCurrent = mergedCurrent.next;
        }
      }
      if (current1 != null)
      {
        if (mergedCurrent != null)
        {
          mergedCurrent.next = current1;
        }
        else
        {
          mergedHead = current1;
        }
      }
      if (current2 != null)
      {
        if (mergedCurrent != null)
        {
          mergedCurrent.next = current2;
        }
        else
        {
          mergedHead = current2;
        }
      }
      return mergedHead;
    }

    public static List<List<Integer>> threeSum2(int[] nums)
    {
      List<List<Integer>> triplets = new ArrayList<>();
      Arrays.sort(nums);

      for (int i = 0; i < nums.length - 2; i++)
      {
        int j = i + 1;
        int k = nums.length - 1;
        if (i > 0 && nums[i] == nums[i - 1])
        {
          continue;
        }
        while (j < k)
        {
          if (k != nums.length - 1 && nums[k] == nums[k + 1])
          {
            k--;
            continue;
          }
          int sum = nums[i] + nums[j] + nums[k];
          if (sum > 0)
          {
            k--;
          }
          else if (sum < 0)
          {
            j++;
          }
          else
          {
            List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
            triplets.add(triplet);
            j++;
            k--;
          }
        }
      }
      return triplets;
    }

    public boolean isPalindrome(int x)
    {
      if (x < 0)
      {
        return false;
      }
      Deque<Integer> forward = new LinkedList<>();
      Deque<Integer> reverse = new LinkedList<>();
      int xLocal = x;
      int digit;
      while (xLocal > 0)
      {
        digit = xLocal % 10;
        forward.push(digit);
        reverse.offer(digit);
        xLocal /= 10;
      }
      while (!forward.isEmpty())
      {
        if (forward.pop() != reverse.poll())
        {
          return false;
        }
      }
      return true;
    }


    public int[] searchRange(int[] nums, int target)
    {
      int[] output = new int[2];
      if (nums.length == 0 || nums.length == 1 && nums[0] != target)
      {
        return new int[]{-1,-1};
      }
      int index = Arrays.binarySearch(nums, target);
      if ( index < 0)
      {
        return new int[]{-1,-1};
      }
      int start = index;
      int end = index;
      while (start >= 0 && nums[start] == target)
      {
        output[0] = start;
        start--;
      }
      while (end < nums.length && nums[end] == target)
      {
        output[1] = end;
        end++;
      }
      return output;
    }

    public void addNum(int num)
    {
      int index = Collections.binarySearch(medianStore, num);
      if (index < 0)
      {
        index = -index - 1;
      }
      if (medianStore.isEmpty())
      {
        midpoint = 0;
        count = 1;
        isEven = false;
      }
      else
      {
        count++;
        midpoint = count / 2;
        isEven = !isEven;
      }
      medianStore.add(index, num);
    }

    public  double findMedian()
    {
      double median = (double) medianStore.get(midpoint);

      if (isEven)
      {
        median = (median + (double) medianStore.get(midpoint - 1)) / 2.0;
      }
      return median;
    }
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    List<ListNode> nodes = new ArrayList<>();
    ListNode current = head;
    ListNode returnHead = head;

    while (current != null)
    {
      nodes.add(current);
      current = current.next;
    }
    int numNodes = nodes.size();
    int indexToRemove = numNodes - n;
    if (indexToRemove == 0)
    {
      returnHead = head.next;
    }
    if (indexToRemove == numNodes - 1)
    {
      if (numNodes >= 2)
      {
        nodes.get(numNodes - 2 ).next = null;
      }
    }
    if (indexToRemove > 0 && indexToRemove < numNodes - 1)
    {
      nodes.get(indexToRemove - 1).next = nodes.get(indexToRemove + 1);
    }
    return returnHead;
  }

  public static void rotate(int[][] matrix)
  {
    int temp = 0;
    if (matrix.length == 0 || matrix[0].length == 0)
    {
      return;
    }

    int rows = matrix.length;
    int columns = matrix[0].length;
    int endRow = rows - 1;
    int endCol = columns - 1;
    int startRow = 0;
    int startCol = 0;
    int numMatrixPaths = rows / 2;
    for (int i = 0; i < numMatrixPaths; i++)
    {
      for (int j = 0; j < endCol - startCol; j++)
      {
        temp = matrix[startRow + j][endCol];
        matrix[startRow + j][endCol] = matrix[startRow][startCol + j];
        matrix[startRow][startCol + j] = matrix[endRow - j][startCol];
        matrix[endRow - j][startCol] = matrix[endRow][endCol - j];
        matrix[endRow][endCol - j] = temp;
      }
      startRow++;
      startCol++;
      endRow--;
      endCol--;
    }
  }

  public static long strangeCounter(long t) {
    long[] countSum = new long[39];
    //long[] startValue = new long[39];
    long value = 0;
    for (int i = 0; i < countSum.length; i++)
    {
      //startValue[i] = (long) (Math.pow(2, i) * 3);
      countSum[i] = (long) ((Math.pow(2, i + 1) - 1) * 3);
    }
    int index = Arrays.binarySearch(countSum, t);
    if (index < 0)
    {
      index = Math.abs(index) - 1;
      value = countSum[index] - t + 1;
    }
    else
    {
      value = 1;
    }
    return value;
  }

  public static int maximumGap(int[] nums) {
    int numLen = nums.length;
    if (numLen < 2)
    {
      return 0;
    }
    if (numLen == 2)
    {
      return Math.abs(nums[1] - nums[0]);
    }
    int maxDiff = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < numLen; i++)
    {
      pq.offer(nums[i]);
    }
    Integer n0 = pq.poll();
    while (!pq.isEmpty())
    {
      Integer n1 = pq.peek();
      maxDiff = Math.max(maxDiff, n1 - n0);
      n0 = pq.poll();
    }
    return maxDiff;
  }

  public static boolean canWin(int leap, int[] game) {
    int len = game.length;
    int endIndex = len -1;
    boolean canWin = false;
    int locationIndex = 0;
    int previousIndex = -1;
    while (!canWin && locationIndex < endIndex && locationIndex != previousIndex)
    {
      if (locationIndex < previousIndex)
      {
        break;
      }
      previousIndex = locationIndex;
      if (locationIndex + leap > endIndex)
      {
        canWin = true;
      }
      else if (game[locationIndex + leap] == 0)
      {
        locationIndex += leap;
      }
      else if (game[locationIndex + 1] == 0)
      {
        locationIndex++;
      }
      else if (locationIndex != 0 && game[locationIndex - 1] == 0)
      {
        int startIndex = locationIndex;
        while (locationIndex != 0 && game[locationIndex - 1] == 0)
        {
          locationIndex--;
          //boolean leaped = false;
          if (locationIndex + leap < endIndex && game[locationIndex + leap] == 0)
          {
            locationIndex += leap;
            if (locationIndex <= startIndex)
            {
              return false;
            }
          }
        }
      }
    }
    return canWin;
  }

  class Interval implements Comparable<Interval>
  {
    Integer start;
    Integer end;
    public Interval()
    {
      start = Integer.MIN_VALUE;
      end = Integer.MIN_VALUE;
    }

    public Interval(Integer start, Integer end)
    {
      this.start = start;
      this.end = end;
    }

    public Interval combine(Interval interval)
    {
      Integer newStart = Math.min(start, interval.start);
      Integer newEnd = Math.max(end, interval.end);
      return new Interval(newStart, newEnd);
    }

    int[] getArray()
    {
      return new int[]{start, end};
    }

    public boolean isOverlapping(Interval interval)
    {
      boolean overlaps = false;
      if (this.equals(interval))
      {
        overlaps = true;
      }
      if ((this.start <= interval.start && this.end >= interval.start) ||
          (interval.start <= this.start && interval.end >= this.start))
      {
        overlaps = true;
      }
      return overlaps;
    }

    @Override
    public boolean equals(Object o)
    {
      if (this == o)
      {
        return true;
      }
      if (o == null || getClass() != o.getClass())
      {
        return false;
      }
      Interval interval = (Interval) o;
      return start.equals(interval.start) && end.equals(interval.end);
    }

    @Override
    public int compareTo(Interval interval)
    {
      int result = Integer.compare(start, interval.start);
      if (result == 0)
      {
        result = Integer.compare(end, interval.end);
      }
      return result;
    }
  }

  public int[][] merge(int[][] intervals)
  {
    List<Interval> mergedIntervals = new ArrayList<>();
    PriorityQueue<Interval> pq = new PriorityQueue<>();
    for (int i = 0; i < intervals.length; i++)
    {
      pq.offer(new Interval(intervals[i][0], intervals[i][1]));
    }

    Interval workingInterval = new Interval();
    while (!pq.isEmpty())
    {
      Interval nextInterval = pq.poll();
      if (workingInterval.isOverlapping(nextInterval))
      {
        workingInterval = workingInterval.combine(nextInterval);
      }
      else
      {
        if (workingInterval.start != Integer.MIN_VALUE)
        {
          mergedIntervals.add(workingInterval);
        }
        workingInterval = nextInterval;
      }
    }
    if (mergedIntervals.isEmpty() ||   !workingInterval.equals(mergedIntervals.get(mergedIntervals.size() - 1)))
    {
      mergedIntervals.add(workingInterval);
    }
    int[][] result = new int[mergedIntervals.size()][2];
    for (int j = 0; j < mergedIntervals.size(); j++)
    {
      result[j] = mergedIntervals.get(j).getArray();
    }
    return result;
  }
  public static int maxForRange2(int[] arr, int start, int end)
  {
    int max = arr[start];
    if (start == end)
    {
      max = arr[start];
    }
    else
    {
      for (int i = end; i > start; i--)
      {
        max = Math.max(max, arr[i]);
      }
    }
    return max;
  }

  public static int[] maxSlidingWindow3(int[] nums, int k)
  {
    if (nums.length == 1 || k == 1)
    {
      return nums;
    }
    Deque<Integer> deque = new LinkedList<>();
    List<Integer> windowMax = new ArrayList<>();

    for (int i = 0; i < nums.length; i++)
    {
      while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i])
      {
        deque.removeLast();
      }
      while (!deque.isEmpty() && deque.getFirst() <= i - k)
      {
        deque.removeFirst();
      }
      deque.addLast(i);
      if (i >= k - 1)
      {
        windowMax.add(nums[deque.getFirst()]);
      }
    }
    
    return windowMax.stream().mapToInt(i -> i).toArray();
  }

  public static int[] maxSlidingWindow2(int[] nums, int k)
  {
    if (nums.length == 1 || k == 1)
    {
      return nums;
    }
    List<Integer> windowMax = new ArrayList<>();

    int max = Integer.MIN_VALUE;
    int maxLeft = nums[0];
    int maxRight = nums[k - 1];
    int maxMid = Integer.MIN_VALUE + 2;
    int prevRight = Integer.MIN_VALUE + 1;
    int prevLeft = Integer.MIN_VALUE + 3;

    for (int i = 0; i <= nums.length - k; i++)
    {
      if (k == 2)
      {
        max = Math.max(nums[i], nums[i + 1]);
      }
      else if (k == 3)
      {
        max = Math.max(nums[i],Math.max(nums[i + 1], nums[i + 2]));
      }
      else
      {
        maxLeft = nums[i];
        maxRight = nums[i + k - 1];

        if (max == maxLeft)
        {
          max = Math.max(maxLeft, maxRight);
          maxMid = maxForRange(nums, i + 1, i + k - 2);
        }
        else
        {
          if (prevRight != Integer.MIN_VALUE && (max == prevRight || prevRight > maxMid))
          {
            maxMid = prevRight;
          }
          else if (max != maxMid)
          {
            maxMid = maxForRange(nums, i + 1, i + k - 2);
          }
          max = Math.max(maxLeft, Math.max(maxMid, maxRight));
        }
      }
      prevRight = maxRight;
      prevLeft = maxLeft;
      windowMax.add(max);
    }
    return windowMax.stream().mapToInt(i -> i).toArray();
  }

  public static int maxForRange(int[] arr, int start, int end)
  {
    int max = arr[start];
    if (start == end)
    {
      max = arr[start];
    }
    else
    {
      for (int i = end; i > start; i--)
      {
        max = Math.max(max, arr[i]);
      }
    }
    return max;
  }

  public static int[] maxSlidingWindow(int[] nums, int k)
  {
    if (nums.length == 1 || k == 1)
    {
      return nums;
    }
    //List<Integer> windowMax = new ArrayList<>();

      int max = Integer.MIN_VALUE; // Integer.MIN_VALUE;
      int maxLeft = Integer.MIN_VALUE;
      int maxRight = Integer.MIN_VALUE;
      int maxMid = Integer.MIN_VALUE;
      List<Integer> windowMax = new ArrayList<>();

      for (int i = 0; i <= nums.length - k; i++)
      {
        maxLeft = nums[i];
        maxRight = nums[i + k];
        if (max == maxLeft)
        {
          max = Math.max(maxLeft, maxRight);
        }
        else
        {
          maxMid = maxForRange(nums, i + 1, i + k - 1);
          max = Math.max(maxLeft, Math.max(maxMid, maxRight));
        }
        windowMax.add(max);
       // max = Integer.MIN_VALUE;
      }
      return windowMax.stream().mapToInt(i -> i).toArray();

    /*
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>(k, Collections.reverseOrder());
    for (int i = 0; i < nums.length; i++)
    {
      maxQueue.offer(nums[i]);
      if (maxQueue.size() % k == 0)
      {
        int max = maxQueue.peek();
        windowMax.add(max);
      }
    } */
   // return windowMax.stream().mapToInt(i -> i).toArray();
  }
  public static int squares(int a, int b) {
    int numSquares = 0;
    int aSqrt = (int) Math.ceil(Math.sqrt(a));
    int bSqrt = (int) Math.floor(Math.sqrt(b));
    if (aSqrt <= bSqrt)
    {
      numSquares = bSqrt - aSqrt + 1;
    }
    return numSquares;
  }

  public static String isValid(String s) {
    int[] frequencies = new int[26];
    String result = "NO";
    Map<Integer, Integer> counts = new HashMap<>();
    List<Integer> countList = new ArrayList<>();
    for (int i = 0; i < s.length(); i++)
    {
      frequencies[s.charAt(i) - 'a']++;
    }

    for (int j = 0; j < frequencies.length; j++)
    {
      //System.out.println("j = " + j + " frequencies[j] = " + frequencies[j]);
      if (frequencies[j] != 0)
      {
        if (counts.computeIfPresent(frequencies[j], (k, v) -> v + 1) == null )
        {
          counts.put(frequencies[j], 1);
          // System.out.println("Counts.get(frequencies[j]) = " + counts.get(frequencies[j]));
        }
        //System.out.println("Counts.get(frequencies[j]) = " + counts.get(frequencies[j]));
      }
    }
    if (counts.size() < 3)
    {
      if (counts.size() == 1)
      {
        result = "YES";
      }
      else
      {
        int keyDiff = 0;
        int keyOne = 0;
        int keyTwo = 0;
        int oneCount = 0;
        int twoCount = 0;
        int iteration = 0;

        for (Map.Entry<Integer, Integer> entry  : counts.entrySet())
        {
          iteration++;
          if (iteration == 1)
          {
            keyOne = entry.getKey();
            oneCount = entry.getValue();
          }
          else
          {
            keyTwo = entry.getKey();
            twoCount = entry.getValue();
          }
        }
        if (((Math.abs(keyOne - keyTwo) == 1) || (keyOne == 1 && oneCount == 1) || (keyTwo == 1 && twoCount == 1)) && (oneCount == 1 || twoCount == 1))
        {
          result = "YES";
        }
        //System.out.println("keyOne = " + keyOne + " keyTwo = " + keyTwo + " oneCount = " + oneCount + " twoCount = " + twoCount);
      }
    }

    return result;
  }

  public static ListNode rotateRight(ListNode head, int k)
  {
    if (head == null || head.next == null)
    {
      return head;
    }
    LinkedList<ListNode> nodeList = new LinkedList<>();
    ListNode curr = head;
    while (curr != null)
    {
      nodeList.add(curr);
      curr = curr.next;
    }
    int rotation = k % nodeList.size();
    ListNode newHead = head;
    if (rotation != 0)
    {
      int size = nodeList.size();
      int lastIndex = size - 1;
      int newHeadIndex = size - rotation;
      int newTailIndex = newHeadIndex - 1;
      nodeList.get(newTailIndex).next = null;
      newHead = nodeList.get(newHeadIndex);
      nodeList.get(lastIndex).next = head;
    }
    return newHead;
  }

  public static List<Integer> cutTheSticks(List<Integer> arr) {
    List<Integer> result = new ArrayList<>();
    SortedMap<Integer, List<Integer>> sticks = new TreeMap<>();
    Map<Integer, List<Integer>> tempSticks = new HashMap<>();
    int numSticks = arr.size();
    for (int i = 0; i< arr.size(); i++)
    {
      if (!sticks.containsKey(arr.get(i)))
      {
        List<Integer> indexList = new ArrayList<>();
        indexList.add(i);
        sticks.put(arr.get(i), indexList);
      }
      else
      {
        sticks.get(arr.get(i)).add(i);
      }
    }

    while (!sticks.isEmpty())
    {
      tempSticks.clear();
      int shortest = sticks.firstKey();
      result.add(numSticks);
      numSticks -= sticks.get(shortest).size();
      sticks.remove(shortest);

      for (Iterator<Map.Entry<Integer, List<Integer>>> iterator = sticks.entrySet().iterator(); iterator.hasNext(); )
      {
        Map.Entry<Integer, List<Integer>> entry = iterator.next();
        Integer len = entry.getKey();
        List<Integer> indexes = entry.getValue();
        iterator.remove();
        tempSticks.put(len - shortest, indexes);
      }
      sticks.clear();
      for (Map.Entry<Integer, List<Integer>> tempEntry : tempSticks.entrySet())
      {
        sticks.put(tempEntry.getKey(), tempEntry.getValue());
      }
    }
    return result;
  }

  public static BigInteger NthFib(int n)
  {
    //BigInteger result = BigInteger.ZERO;
    BigDecimal phi = BigDecimal.valueOf(0.5).multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(Math.sqrt(5.0))));
    BigDecimal Phi = BigDecimal.valueOf(0.5).multiply(BigDecimal.ONE.add(BigDecimal.valueOf(Math.sqrt(5.0))));
    phi = phi.pow(n);
    Phi = Phi.pow(n);
    BigDecimal Fn = (Phi.subtract(phi)).multiply(BigDecimal.valueOf(1.0 / Math.sqrt(5.0)));
    return Fn.toBigInteger();
  }

  public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2)
  {
    SinglyLinkedListNode merged = null;
    if (head1 == null)
    {
      return head2;
    }
    if (head2 == null)
    {
      return head1;
    }
    SinglyLinkedListNode current1 = head1;
    SinglyLinkedListNode current2 = head2;
    SinglyLinkedListNode currentMerged = null;
    while (current1 != null && current2 != null)
    {
      int data = Math.min(current1.data, current2.data);
      if (currentMerged == null)
      {
        merged = currentMerged = new SinglyLinkedListNode(data);
      }
      else
      {
        currentMerged.next = new SinglyLinkedListNode(data);
        currentMerged = currentMerged.next;
      }
      if (current1.data < current2.data)
      {
        //currentMerged.data = current1.data;
        current1 = current1.next;
      }
      else
      {
        //currentMerged.data = current2.data;
        current2 = current2.next;
      }
    }
    if (current1 != null)
    {
      currentMerged.next = current1;
    }
    else
    {
      currentMerged.next = current2;
    }
    return merged;
  }

  public static String isBalanced(String s)
  {
    Stack<String> stack = new Stack<>();
    Map<String, String> bracketMates = new HashMap<>();
    bracketMates.put("}", "{");
    bracketMates.put("]", "[");
    bracketMates.put(")", "(");
    String opening = "{[(";
    for (int i = 0; i < s.length(); i++)
    {
      String b = String.valueOf(s.charAt(i));
      if (opening.contains(b))
      {
        stack.push(b);
      }
      else
      {
        String mate = bracketMates.get(b);
        String popped = "";
        if (!stack.isEmpty())
        {
          popped = stack.pop();
        }
        if (!popped.equals(mate))
        {
          return "NO";
        }
      }
    }
    if (stack.isEmpty())
    {
      return "YES";
    }
    return "NO";
  }

  class LRUCache
  {
    private final int CAPACITY;
    MyLinkedHashMap cache;


    public LRUCache(int capacity)
    {
      CAPACITY = capacity;
      //super(CAPACITY, 0.75f, true);
      cache = new MyLinkedHashMap(CAPACITY);
    }

    public int get(int key)
    {
      //Integer value = super.get(key);
      //return value == null ? -1 : value;
      return cache.get(key);
    }

    public void put(int key, int value)
    {
      //super.put(key, value);
      cache.put(key, value);
    }

/*
    class MyLinkedHashMap extends LinkedHashMap<Integer, Integer>
    {
        //private final int CAPACITY;
        public MyLinkedHashMap(int capacity)
        {
            //CAPACITY = capacity;
            super(CAPACITY, 0.75f, true);
        }

        public int get(int key)
        {
            Integer value = super.get(key);
            return value == null ? -1 : value;
        }

        public void put(int key, int value)
        {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest)
        {
            return this.size() > CAPACITY;
        }

    }
  */

  }

  public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks)
  {
    List<Integer> result = new ArrayList<>();
    long maxPerimeter = 0;
    for (int i = 0; i < sticks.size() - 2; i++)
    {
      for (int j = i + 1; j < sticks.size() - 1; j++)
      {
        for (int k = j + 1; k < sticks.size(); k++)
        {
          long a = sticks.get(i);
          long b = sticks.get(j);
          long c = sticks.get(k);
          long perimeter = a + b + c;
          long maxSide = Math.max(c, Math.max(a, b));
          long legsSum = perimeter - maxSide;
          if (legsSum - maxSide > 0)
          {
            if (perimeter > maxPerimeter)
            {
              maxPerimeter = perimeter;
              result.clear();
              result.add((int) a);
              result.add((int) b);
              result.add((int) c);
              Collections.sort(result);
            }
            if (perimeter == maxPerimeter)
            {
              if (result.get(0) < Math.min(a, Math.min(b, c)))
              {
                result.clear();
                result.add((int) a);
                result.add((int) b);
                result.add((int) c);
                Collections.sort(result);
              }
            }
          }
        }
      }
    }
    if (result.isEmpty())
    {
      result.add(-1);
    }
    return result;
  }

  public int evalRPN(String[] tokens)
  {
    Stack<Integer> stack = new Stack<>();
    //int result = 0;
    Map<String, String> operators = new HashMap<>();
    operators.put("+", "a");
    operators.put("-", "s");
    operators.put("*", "m");
    operators.put("/", "d");
    for (String token : tokens)
    {
      if (!operators.containsKey(token))
      {
        stack.push(Integer.parseInt(token));
      }
      else
      {
        int x = stack.pop();
        int y = stack.pop();
        switch (token)
        {
          case "+":
            stack.push(y + x);
            break;
          case "-":
            stack.push(y - x);
            break;
          case "*":
            stack.push(y * x);
            break;
          case "/":
            stack.push(y / x);
        }
        //stack.push(result);
      }
    }
    return stack.pop();
  }

  class MyLinkedHashMap extends LinkedHashMap<Integer, Integer>
  {
    private final int CAPACITY;

    public MyLinkedHashMap(int capacity)
    {
      super(capacity, 0.75f, true);
      CAPACITY = capacity;
    }

    public int get(int key)
    {
      Integer value = super.get(key);
      return value == null ? -1 : value;
    }

    public void put(int key, int value)
    {
      super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest)
    {
      return this.size() > CAPACITY;
    }
  }


  public static void minimumBribes(List<Integer> q)
  {
    int bribes = 0;
     /*   int maxIndividualBribes = 0;
        for (int i = 0; i < q.size(); i++)
        {
            int rider = q.get(i);
            if (i+ 1 < rider)
            {
                int riderBribes = rider - (i + 1);
                maxIndividualBribes = Math.max(maxIndividualBribes, riderBribes);
                bribes += riderBribes;
            }
        }

        if (maxIndividualBribes < 3)
        {
            System.out.println(bribes);
        }
        else
        {
            System.out.println("Too chaotic");
        }
       */
    int numRiders = q.size();
    for (int position = numRiders - 1; position >= 0; position--)
    {
      if (q.get(position) != (position + 1))
      {
        if (((position - 1) >= 0) && q.get(position - 1) == (position + 1))
        {
          bribes++;
          Collections.swap(q, position, position - 1);
        }
        else if (((position - 2) >= 0) && q.get(position - 2) == (position + 1))
        {
          bribes += 2;
          //Collections.swap(q, position - 2, position - 1);
          //Collections.swap(q, position - 1, position);
          q.set(position - 2, q.get(position - 1));
          q.set(position - 1, q.get(position));
          q.set(position, position + 1);
        }
        else
        {
          System.out.println("Too chaotic");
          return;
        }
      }
    }
    System.out.println(bribes);
  }


  public static int superDigit(String n, int k)
  {
    if (n.length() == 1)
    {
      return Integer.parseInt(n);
    }
    long sum = 0;
    for (int i = 0; i < n.length(); i++)
    {
      sum += Long.parseLong(n.substring(i, i + 1));
    }
    sum *= k;
    return superDigit(Long.toString(sum), 1);
  }


  public static final Comparator<String> LETTER_LOG = new Comparator<String>()
  {
    @Override
    public int compare(String log1, String log2)
    {
      String log1sub = log1.substring(log1.indexOf(' '));
      String log2sub = log2.substring(log2.indexOf(' '));
      int comp = log1sub.compareTo(log2sub);
      return comp == 0 ? log1.compareTo(log2) : comp;
    }
  };

  public static String[] reorderLogFiles(String[] logs)
  {
    List<String> letterLogs = new ArrayList<>();
    List<String> digitLogs = new ArrayList<>();
    for (String log : logs)
    {
      String[] logSplit = log.split(" ");

      if (logSplit[1].matches("[0-9]+"))
      {
        digitLogs.add(log);
      }
      else
      {
        letterLogs.add(log);
      }
    }
    Collections.sort(letterLogs, Main.LETTER_LOG);
    letterLogs.addAll(digitLogs);
    return letterLogs.toArray(new String[0]);
  }

  public static int[] getModifiedArray(int length, int[][] updates)
  {
    int[] result = new int[length];
    for (int i = 0; i < updates.length; i++)
    {
      int startIndex = updates[i][0];
      int endIndex = updates[i][1];
      int increment = updates[i][2];
      for (int j = startIndex; j <= endIndex; j++)
      {
        result[j] += increment;
      }
    }
    return result;
  }

  public static int kthFactor(int n, int k)
  {
    List<Integer> factors = new ArrayList<>();
    Map<Integer, Integer> factorsMap = new HashMap<>();
    int result = -1;
    for (int i = 1; i <= Math.ceil(Math.sqrt(n)); i++)
    {
      if (n % i == 0)
      {
        if (!factorsMap.containsKey(i))
        {
          factors.add(i);
          factorsMap.put(i, i);
          int secondFactor = n / i;
          Integer sf = factorsMap.putIfAbsent(secondFactor, secondFactor);
          if (sf == null)
          {
            factors.add(secondFactor);
          }
        }
      }
    }
    Collections.sort(factors);
    if (factors.size() >= k)
    {
      result = factors.get(k - 1);
    }
    return result;
  }

  private static List<List<String>> recomendations(List<String> repository, String query)
  {
    List<List<String>> recommendedItems = new ArrayList<>();
    Collections.sort(repository);
    for (int i = 0; i < query.length(); i++)
    {
      List<String> intermediateItems = new ArrayList<>();
      for (String item : repository)
      {
        String matchString = query.substring(0, i + 1);
        if (item.startsWith(matchString))
        {
          intermediateItems.add(item);
        }
      }
      if (intermediateItems.size() > 3)
      {
        intermediateItems.subList(3, intermediateItems.size()).clear();
      }
      recommendedItems.add(intermediateItems);
    }
    return recommendedItems;
  }

  public static int flippingMatrix(List<List<Integer>> matrix)
  {
    int sum = 0;
    int rows = matrix.size();
    int columns = matrix.get(0).size();
    for (int row = 0; row < rows / 2; row++)
    {
      for (int column = 0; column < columns / 2; column++)
      {
        int row1 = row;
        int row2 = rows - row - 1;
        int column1 = column;
        int column2 = columns - column - 1;

        sum += Math.max(Math.max(matrix.get(row1).get(column1),
                matrix.get(row1).get(column2)),
            Math.max(matrix.get(row2).get(column1),
                matrix.get(row2).get(column2)));
      }
    }
    return sum;
  }


// Java program to find maximum value of top N/2 x N/2
// matrix using row and column reverse operations


//  static int maxSum(int mat[][])
//  {
//    int sum = 0;
//
//    for (int i = 0; i < R / 2; i++)
//    {
//      for (int j = 0; j < C / 2; j++)
//      {
//        int r1 = i;
//        int r2 = R - i - 1;
//        int c1 = j;
//        int c2 = C - j - 1;
//
//        // We can replace current cell [i, j]
//        // with 4 cells without changing affecting
//        // other elements.
//        sum += Math.max(Math.max(mat[r1][c1], mat[r1][c2]),
//            Math.max(mat[r2][c1], mat[r2][c2]));
//      }
//    }
//
//    return sum;
//    }

  public static int amazonShoppingCartPromo(List<String> codeList, List<String> shoppingCart)
  {
    int result = 0;
    int groupCount = codeList.size();
    int matchingGroups = 0;
    int currentGroupIndex = 0;
    int currentGroupItemIndex = 0;
    List<List<String>> groups = new ArrayList<>();
    for (String item : codeList)
    {
      String[] group = item.split(" ");
      groups.add(new ArrayList<>(Arrays.asList(group)));
      System.out.println(item);
    }
    List<String> currentGroup = groups.get(currentGroupIndex);
    int currentGroupElements = currentGroup.size();
    boolean partialGroupMatch = false;
    for (int i = 0; i < shoppingCart.size(); i++)
    {
      if (!partialGroupMatch)
      {
        if (shoppingCart.get(i).toLowerCase().equals(currentGroup.get(currentGroupItemIndex)) || currentGroup.get(currentGroupItemIndex).equals("anything"))
        {
          if (currentGroup.size() == 1)
          {
            currentGroupIndex++;
          }
          else
          {
            currentGroupItemIndex++;
            partialGroupMatch = true;
          }
        }
      }
    }
    return result;
  }

  public static int shoppingListMatchesPromotion2(List<String> codeList, List<String> shoppingCart)
  {
    int result = 0;
    String shoppingCartString = shoppingCart.stream().collect(Collectors.joining(" ")).toLowerCase();
    List<String> patterns = new ArrayList<>();

    for (String group : codeList)
    {
      String pattern = group;
      if (pattern.contains("anything"))
      {
        pattern = pattern.replace("anything", "[a-z]+");
      }
      patterns.add(pattern);
    }
    int lastMatchIndex = 0;
    int groupsMatched = 0;
    for (String regex : patterns)
    {
      Pattern p = Pattern.compile(regex);
      Matcher m = p.matcher(shoppingCartString);
      if (m.find(lastMatchIndex))
      {
        groupsMatched++;
        lastMatchIndex = m.end();
      }
    }
    if (groupsMatched == codeList.size())
    {
      result = 1;
    }
    return result;
  }

  public static double findMedianSortedArrays(int[] nums1, int[] nums2)
  {
    double median = 0.0;

    int totalElements = nums1.length + nums2.length;
    int halfElements = Math.floorDiv(totalElements, 2);
    int[] merged = new int[halfElements + 1];
    int medianIndex1 = halfElements;
    int medianIndex2 = 0;
    if (totalElements % 2 == 0)
    {
      medianIndex1 = halfElements - 1;
      medianIndex2 = halfElements;
    }

    int mergedIndex = 0;
    int num1Index = 0;
    int num2Index = 0;

    while (mergedIndex < merged.length && num1Index < nums1.length && num2Index < nums2.length)
    {
      if (nums1[num1Index] < nums2[num2Index])
      {
        merged[mergedIndex++] = nums1[num1Index++];
      }
      else
      {
        merged[mergedIndex++] = nums2[num2Index++];
      }
    }

    while (mergedIndex < merged.length && num1Index < nums1.length)
    {
      merged[mergedIndex++] = nums1[num1Index++];
    }
    while (mergedIndex < merged.length && num2Index < nums2.length)
    {
      merged[mergedIndex++] = nums2[num2Index++];
    }

    median = (double) merged[medianIndex1];
    if (medianIndex2 != 0)
    {
      median = (double) (merged[medianIndex1] + merged[medianIndex2]) / 2.0;
    }
    return median;
  }

  public static int shoppingListMatchesPromotion(List<String> codeList, List<String> shoppingCart)
  {
    int result = 0;
    int numberOfGroups = codeList.size();
    int currentGroupIndex = 0;
    int currentGroupItemIndex = 0;
    List<List<String>> groups = new ArrayList<>();
    for (String item : codeList)
    {
      System.out.println("Code group " + item);
      String[] group = item.split(" ");
      List<String> groupItems = new ArrayList<>();
      for (int i = 0; i < group.length; i++)
      {
        groupItems.add(group[i].toLowerCase());
      }
      groups.add(groupItems);
      System.out.println(item);
    }
    List<String> currentGroup = groups.get(currentGroupIndex);
    int currentGroupElementCount = currentGroup.size();
    boolean allGroupsPassed = false;
    //boolean partialGroupMatch = false;
    for (int i = 0; i < shoppingCart.size() && !allGroupsPassed; i++)
    {
      System.out.println("ShoppingCart item " + i + ": " + shoppingCart.get(i));
      if (shoppingCart.get(i).toLowerCase().equals(currentGroup.get(currentGroupItemIndex)) || currentGroup.get(currentGroupItemIndex).equals("anything"))
      {
        System.out.println("ShoppingCart Item " + shoppingCart.get(i) + " matches group number " + currentGroupIndex + " item " + currentGroup.get(currentGroupItemIndex));
        if (currentGroup.size() == 1)
        {
          currentGroupIndex++;
        }
        else
        {
          currentGroupItemIndex++;
          i++;
          while (currentGroupItemIndex < currentGroup.size() && i < shoppingCart.size())
          {
            if (shoppingCart.get(i).toLowerCase().equals(currentGroup.get(currentGroupItemIndex)) || currentGroup.get(currentGroupItemIndex).equals("anything"))
            {
              System.out.println("ShoppingCart Item " + shoppingCart.get(i) + " matches group number " + currentGroupIndex + " item " + currentGroup.get(currentGroupItemIndex));

              currentGroupItemIndex++;
              i++;
            }
            else
            {
              break;
            }
          }
        }
        if (currentGroupItemIndex == currentGroup.size())
        {
          currentGroupIndex++;
          if (currentGroup.size() == currentGroupIndex)
          {
            allGroupsPassed = true;
            result = 1;
          }
          else
          {
            currentGroup = groups.get(currentGroupIndex);
            currentGroupItemIndex = 0;
          }
        }
        else
        {
          currentGroupItemIndex = 0;
          if (shoppingCart.get(i).toLowerCase().equals(currentGroup.get(currentGroupItemIndex)) || currentGroup.get(currentGroupItemIndex).equals("anything"))
          {
            currentGroupItemIndex++;
          }
        }
      }
    }
    return result;
  }


  public static int birthday(List<Integer> s, int d, int m)
  {
    int count = 0;
    System.out.println("d = " + d + " m = " + m);
    System.out.println("s.size() = " + s.size() + " s[0] = " + s.get(0));

    //for ( int i = 0; i < (s.size() > m ? s.size() - m : s.size()); i++)
    for (int i = 0; i <= s.size() - m; i++)
    {
      int sum = 0;

      if (m <= s.size())
      {
        for (int j = 0; j < m; j++)
        {
          System.out.println("i = " + i + " j = " + j);
          System.out.println("s[j + i] = " + s.get(j + i));
          sum += s.get(j + i);
        }
      }
      if (sum == d)
      {
        count++;
      }
    }
    return count;
  }


  // Utility function to print contents of a linked list
  public static void printList(Node node)
  {
    while (node != null)
    {
      System.out.print(node.data + " —> ");
      node = node.next;
    }
    System.out.print("null");
  }

  // Takes two lists sorted in increasing order and merges their nodes
  // to make one big sorted list returned
  public static Node sortedMerge(Node a, Node b)
  {
    // base cases
    if (a == null)
    {
      return b;
    }
    else if (b == null)
    {
      return a;
    }

    Node result;

    // pick either `a` or `b`, and recur
    if (a.data <= b.data)
    {
      result = a;
      result.next = sortedMerge(a.next, b);
    }
    else
    {
      result = b;
      result.next = sortedMerge(a, b.next);
    }
    return result;
  }

  // The main function to merge given `k` sorted linked lists.
  // It takes array `lists` of size `k` and generates the sorted output
  public static Node mergeKLists(Node[] lists)
  {
    // base case
    if (lists == null || lists.length == 0)
    {
      return null;
    }

    int last = lists.length - 1;

    // repeat until only one list is left
    while (last != 0)
    {
      int i = 0, j = last;

      // `(i, j)` forms a pair
      while (i < j)
      {
        // merge list `j` with `i`
        lists[i] = sortedMerge(lists[i], lists[j]);

        // consider the next pair
        i++;
        j--;

        // if all pairs are merged, update last
        if (i >= j)
        {
          last = j;
        }
      }
    }

    return lists[0];
  }

  public static int greatestCommonDivisor(int a, int b)
  {
    if (b == 0)
    {
      return a;
    }
    return greatestCommonDivisor(b, a % b);
  }

  public static int generalGCD(int[] numbers)
  {
    int[] workingNumbers = numbers;
    Arrays.sort(workingNumbers);
    int gcd = workingNumbers[0];
    for (int i = 1; i < workingNumbers.length; i++)
    {
      gcd = greatestCommonDivisor(gcd, workingNumbers[i]);
    }
    return gcd;
  }

  public static int canCompleteCircuit(int[] gas, int[] cost)
  {
    int gasAmount = 0;
    int totalGas = 0;
    int startStation = 0;

    for (int i = 0; i < gas.length; i++)
    {
      totalGas += gas[i] - cost[i];
      gasAmount += gas[i] - cost[i];
      if (gasAmount < 0)
      {
        gasAmount = 0;
        startStation = i + 1;
      }
    }
    return (totalGas < 0) ? -1 : startStation;
  }

  public static int truckTour(List<List<Integer>> petrolpumps)
  {
    int start = 0;
    int gas = 0;
    int totalFuel = 0;
    int totalStations = petrolpumps.size();
    for (int station = 0; station < totalStations; station++)
    {
      int currentGas = petrolpumps.get(station).get(0);
      int currentDistance = petrolpumps.get(station).get(1);
      System.out.println("currentGas = " + currentGas + " currentDistance = " + currentDistance);
      totalFuel += currentGas - currentDistance;
      gas += currentGas - currentDistance;
      System.out.println("totalFuel = " + currentGas + " gas = " + gas);
      if (gas < 0)
      {
        gas = 0;
        start = station + 1;
        System.out.println("start = " + start);
      }
    }
    return start;
  }

  public static int canCompleteCircuit3(int[] gas, int[] cost)
  {
    int gasAmount = 0;
    int startStation = -1;
    int i = 0;
    int j = 0;

    while (i < gas.length)
    {
      while (j < gas.length && gasAmount >= 0)
      {
        int index = j + i < gas.length ? j + i : j + i - gas.length;
        gasAmount += gas[index] - cost[index];
        j++;
      }
      if (j == gas.length && gasAmount >= 0)
      {
        startStation = i;
        break;
      }
      gasAmount = 0;
      j = 0;
      i++;

    }
    return startStation;
  }

  public static int canCompleteCircuit2(int[] gas, int[] cost)
  {
    int gasAmount = 0;
    int startStation = -1;
    int i = 0;
    while (i < gas.length && gas[i] < cost[i])
    {
      i++;
    }

    int j = 0;

    while (j < gas.length)
    {
      int index = j + i < gas.length ? j + i : j + i - gas.length;
      gasAmount += gas[index] - cost[index];
      j++;
    }
    if (gasAmount >= 0)
    {
      startStation = i;
    }
    return startStation;
  }

  public static void findZigZagSequence(int[] a, int n)
  {
    Arrays.sort(a);
    int mid = ((n - 1) / 2);
    int temp = a[mid];
    a[mid] = a[n - 1];
    a[n - 1] = temp;

    int st = mid + 1;
    int ed = n - 1;
    while (st <= ed)
    {
      temp = a[st];
      a[st] = a[ed];
      a[ed] = temp;
      st = st + 1;
      ed = ed - 1;
    }
    for (int i = 0; i < n; i++)
    {
      if (i > 0)
      {
        System.out.print(" ");
      }
      System.out.print(a[i]);
    }
    System.out.println();
  }

  public static void miniMaxSum(List<Integer> arr)
  {
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;
    if (arr.size() >= 4)
    {
      for (int i = 0; i < arr.size() - 3; i++)
      {
        for (int j = i + 1; j < arr.size() - 2; j++)
        {
          for (int k = j + 1; k < arr.size() - 1; k++)
          {
            for (int l = k + 1; l < arr.size(); l++)
            {
              long sum = (long) arr.get(i) + (long) arr.get(j) + (long) arr.get(k) + (long) arr.get(l);
              if (sum < min)
              {
                min = sum;
              }
              if (sum > max)
              {
                max = sum;
              }
            }
          }
        }
      }
      System.out.println(String.format("%d %d", min, max));
    }
  }

  public static String timeConversion(String s)
  {
    String amPm = s.substring(s.length() - 2);
    String time = s.substring(0, s.length() - 2);
    String[] timeComponents = time.split(":");
    boolean addTwelve = false;
    int hour = Integer.parseInt(timeComponents[0]);
    if (amPm.toUpperCase().equals("PM") && hour < 12)
    {
      hour += 12;
    }
    if (amPm.toUpperCase().equals("AM") && hour == 12)
    {
      hour = 0;
    }
    return String.format("%02d:%s:%s", hour, timeComponents[1], timeComponents[2]);
  }

  public static List<Integer> breakingRecords(List<Integer> scores)
  {
    int min = scores.get(0);
    int max = scores.get(0);
    Integer[] maxMinRecordCounts = {0, 0};

    for (Integer score : scores)
    {
      if (score > max)
      {
        max = score;
        maxMinRecordCounts[0]++;
      }
      if (score < min)
      {
        min = score;
        maxMinRecordCounts[1]++;
      }
    }
    return new ArrayList<Integer>(List.of(maxMinRecordCounts));
  }

  public static String camelCase(String input)
  {
    StringBuilder sb = new StringBuilder();
    if (input != null)
    {
      String[] splitInput = input.split(";");
      if (splitInput.length == 3)
      {
        String previousC = null;
        for (int i = 0; i < splitInput[2].length(); i++)
        {
          char c = splitInput[2].charAt(i);

          if (splitInput[0].equals("C"))
          {
            if (splitInput[1].equals("C"))
            {
              if (i == 0 || previousC.equals(" "))
              {
                sb.append(Character.toUpperCase(c));
              }
              else
              {
                if (c != ' ')
                {
                  sb.append(c);
                }
              }
            }

            if (splitInput[1].equals("M") || splitInput[1].equals("V"))
            {
              if (i == 0)
              {
                sb.append(Character.toLowerCase(c));
              }
              else if (previousC.equals(" "))
              {
                sb.append(Character.toUpperCase(c));
              }
              else
              {
                if (c != ' ')
                {
                  sb.append(c);
                }
              }
            }
          }
          else if (splitInput[0].equals("S"))
          {
            if (Character.isUpperCase(c))
            {
              if (i != 0)
              {
                sb.append(" ");
              }
              sb.append(Character.toLowerCase(c));
            }
            else if (c != '(' && c != ')')
            {
              sb.append(c);
            }
          }
          previousC = String.valueOf(c);
        }
        if (splitInput[0].equals("C") && splitInput[1].equals("M"))
        {
          sb.append("()");
        }
      }
    }
    return sb.toString();
  }


  public static int divisibleSumPairs(int n, int k, List<Integer> ar)
  {
    int pairs = 0;
    for (int i = 0; i < ar.size() - 1; i++)
    {
      for (int j = i + 1; j < ar.size(); j++)
      {
        if ((ar.get(i) + ar.get(j)) % k == 0)
        {
          pairs++;
        }
      }
    }
    return pairs;
  }

  public static long lightCombinations(int n)
  {
    BigInteger two = new BigInteger("2");
    BigInteger twoPow = two.pow(n).subtract(new BigInteger("1")).mod(new BigInteger("100000"));
    ;
    return Long.parseLong(twoPow.toString());
  }

  public static List<Integer> matchingStrings(List<String> strings, List<String> queries)
  {
    Map<String, Integer> frequencyMap = new HashMap<>();
    List<Integer> result = new ArrayList<>();
    for (String query : queries)
    {
      frequencyMap.put(query, 0);
    }
    for (String str : strings)
    {
      frequencyMap.computeIfPresent(str, (k, v) -> v + 1);
    }
    for (String query : queries)
    {
      result.add(frequencyMap.get(query));
    }
    return result;
  }

  public static int summingSeries(long n)
  {
    // Write your code here
    BigInteger bigIntN = BigInteger.valueOf(n);
    BigInteger bigIntResult = bigIntN.modPow(BigInteger.valueOf(2), BigInteger.valueOf((long) (10e9 - 7)));
    return Integer.parseInt(bigIntResult.toString());
  }

  public static int find(int[] arr, int x, int y)
  {
    System.out.println("x = " + x + " y = " + y);
    System.out.println(("arr[x - 1] = " + arr[x - 1]));
    if (x > y)
    {
      System.out.println("ans = 1");
      return 1;
    }
    int ans = (int) Math.pow(arr[x - 1], Main.find(arr, x + 1, y));
    System.out.println("ans = " + ans);
    return ans;
  }

  public static List<String> evenOdd(List<Integer> arr, List<List<Integer>> queries)
  {
    List<String> evenOdd = new ArrayList<>();
    int inputNumber = 1;
    for (List<Integer> query : queries)
    {
      int x = query.get(0);
      int y = query.get(1);
      System.out.println("InputNumber = " + inputNumber++ + " x = " + x + " y = " + y);
      System.out.println("arr(x - 1) = " + arr.get(x - 1));

      if (x != y && arr.get(x) == 0)
      {
        System.out.println("Found 0 adjacent, Odd");
        evenOdd.add("Odd");
      }
      else
      {
        if (arr.get(x - 1) % 2 == 0)
        {
          System.out.println("arr.get(x - 1): " + arr.get(query.get(0) - 1) + " - Even");
          evenOdd.add("Even");
        }
        else
        {
          System.out.println("arr.get(x - 1): " + arr.get(query.get(0) - 1) + " - Odd");
          evenOdd.add("Odd");
        }
      }
    }
    return evenOdd;
  }

  public static void readEvenOddInputs(String inputPath)
  {
    BufferedReader reader;
    try
    {
      reader = new BufferedReader(new FileReader(inputPath));
      int lineNum = 1;
      String line = reader.readLine();
      while (line != null)
      {
        if (lineNum == 2)
        {
          Main.populateInputArray(line);
        }
        if (lineNum > 3)
        {
          List<Integer> query = new ArrayList<>();
          String[] queryItems = line.split(" ");
          if (queryItems.length > 1)
          {
            query.add(Integer.parseInt(queryItems[0]));
            query.add(Integer.parseInt(queryItems[1]));
            Main.evenOddQueries.add(query);
          }
        }
        lineNum++;
        line = reader.readLine();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void readPrisonerInputs(String inputPath)
  {
    BufferedReader reader;
    try
    {
      reader = new BufferedReader(new FileReader(inputPath));

      String line = reader.readLine();
      while (line != null)
      {
        List<Integer> query = new ArrayList<>();
        String[] queryItems = line.split(" ");
        if (queryItems.length == 3)
        {
          query.add(Integer.parseInt(queryItems[0]));
          query.add(Integer.parseInt(queryItems[1]));
          query.add(Integer.parseInt(queryItems[2]));
          Main.prisonerQueries.add(query);
        }
        line = reader.readLine();
      }
    }


    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  private static void populateInputArray(String line)
  {
    if (line != null)
    {
      String[] values = line.split(" ");
      for (String value : values)
      {
        Main.evenOddInput.add(Integer.parseInt(value));
      }
    }
  }

  public static List<Integer> getPatternIndexes(String row, String pattern)
  {
    List<Integer> indexes = new ArrayList<>();
    int index = row.indexOf(pattern);
    while (index != -1)
    {
      indexes.add(index);
      //index = row.indexOf(pattern, index + pattern.length());
      index = row.indexOf(pattern, index + 1);
    }
    return indexes;
  }

  public static List<Integer> getCommonIndexes(List<Integer> list1, List<Integer> list2 )
  {
    if (list1.isEmpty() || list2.isEmpty())
    {
      return new ArrayList<Integer>();
    }
    List<Integer> commonList = new ArrayList<>();
    for (int i = 0; i < list1.size(); i++)
    {
      if (list2.contains(list1.get(i)))
      {
        commonList.add(list1.get(i));
      }
    }
    return commonList;
  }

  private static int resetPatternCheck(List<String> g, List<String> p, List<List<Integer>> pCols, int i)
  {
    int pRow;
    pRow = 0;
    pCols.clear();
    List<Integer> temp = getPatternIndexes(g.get(i), p.get(pRow));
    if (!temp.isEmpty())
    {
      pCols.add(temp);
    }
    if (pCols.size() > 0)
    {
      pRow++;
    }
    return pRow;
  }


  public static String gridSearch(List<String> G, List<String> P)
  {
    String patternFound = "NO";
    int pRow = 0;
    List<List<Integer>> pCols = new ArrayList<>();
    int pCol;
    int index = 0;
    for (int i = 0; i < G.size() && pRow < P.size(); i++)
    {
      index = G.get(i).indexOf(P.get(pRow));
      if (index >= 0)
      {
        if (pRow == 0)
        {
          pCols.add(getPatternIndexes(G.get(i), P.get(pRow)));

          pRow++;
        }
        else
        {
          if (pCols.get(0).contains(index))
          {
            pCols.add(getPatternIndexes(G.get(i), P.get(pRow)));
            List<Integer> commonList = getCommonIndexes(pCols.get(0), pCols.get(1));
            if (!commonList.isEmpty())
            {
              pRow++;
              pCols.clear();
              pCols.add(commonList);
            }
            else
            {
              pRow = resetPatternCheck(G, P, pCols, i);

            }

          }
          else
          {
            pRow = resetPatternCheck(G, P, pCols, i);
          }
        }
      }
      else
      {
        pRow = resetPatternCheck(G, P, pCols, i);
      }
    }


    if (pRow == P.size())
    {
      patternFound = "YES";
    }
    return patternFound;
  }


  public static int singleNumber(int[] nums)
  {
    Map<Integer,Integer> visited = new HashMap<>();
    for (int num : nums)
    {
      if (visited.remove(num) == null)
      {
        visited.put(num, 1);
      }
    }
    int singleInt = 0;
    for (Map.Entry<Integer, Integer> single : visited.entrySet())
    {
      return single.getKey();
    }
    return singleInt;
  }



  public static int lonelyinteger(List<Integer> a)
  {
    int lonelyint = 0;

    Map<Integer, Integer> counts = new HashMap<>();
    Map<Integer, Integer> ones = new HashMap<>();
    for (Integer i : a)
    {
      System.out.println("i = " + i);
      if (counts.computeIfPresent(i, (k, v) -> v + 1) == null)
      {
        counts.put(i, 1);
        ones.put(i, 1);
      }
      else
      {
        if (ones.containsKey(i))
        {
          System.out.println("Removing " + i);
          ones.remove(i);
        }
      }
    }
    if (!ones.isEmpty())
    {
      System.out.println("Ones size: " + ones.size());
      Optional<Integer> first = ones.keySet().stream().findFirst();
      if (first.isPresent())
      {
        lonelyint = first.get();
      }
    }
    System.out.println("LonelyInt = " + lonelyint);
    return lonelyint;
  }

  public static int divisors(int n)
  {
    Set<Integer> factors = new HashSet<>();
    if (n % 2 == 0)
    {
      factors.add(2);
      if (n > 2)
      {
        factors.add(n);
        if ((n / 2) % 2 == 0)
        {
          factors.add(n / 2);
        }
        for (int i = 3; i <= n / i; i++)
        {
          if (n % i == 0)
          {
            if (i % 2 == 0)
            {
              factors.add(i);
            }
            if (n / i % 2 == 0)
            {
              factors.add(n / i);
            }
          }
        }
      }
    }

    return factors.size();
  }
  public static void rotateMatrix(int[][] matrix)
  {
    int temp = 0;
    if (matrix.length == 0 || matrix[0].length == 0)
    {
      return;
    }

    int rows = matrix.length;
    int columns = matrix[0].length;
    int endRow = rows - 1;
    int endCol = columns - 1;
    int startRow = 0;
    int startCol = 0;
    int numMatrixPaths = rows / 2;
    for (int i = 0; i < numMatrixPaths; i++)
    {
      for (int j = 0; j < endCol - startCol; j++)
      {
        temp = matrix[startRow + j][endCol];
        matrix[startRow + j][endCol] = matrix[startRow][startCol + j];
        matrix[startRow][startCol + j] = matrix[endRow - j][startCol];
        matrix[endRow - j][startCol] = matrix[endRow][endCol - j];
        matrix[endRow][endCol - j] = temp;
      }
      startRow++;
      startCol++;
      endRow--;
      endCol--;
    }

  }

  public static void rotate(int[] nums, int k)
  {
    if (k == 0 || nums.length < 2 || k == nums.length)
    {
      return;
    }
    int arrLen = nums.length;
    int k1 = k < arrLen ? k : k % arrLen;
    int rotateIndex = k1;
    int tempIndex = 0;
    int temp1 = 0;

    for (int i = 0; i < arrLen - k1; i++)
    {
      rotateIndex = i + k1;
      if (i % k1 == 0)
      {
        tempIndex = 0;
      }
      temp1 = nums[rotateIndex];
      nums[rotateIndex] = nums[tempIndex];
      nums[tempIndex] = temp1;
      tempIndex++;
    }

    int modK =arrLen % k1;

    if (modK != 0)
    {
      int[] modKTemp = new int[modK];
      for (int m = 0; m < modK; m++)
      {
        modKTemp[m] = nums[m];
      }
      int shift = k1 - modK;

      for (int j = modK; j < k1; j++ )
      {
        nums[j - modK] = nums[j];
      }
      for (int p = 0; p < modK; p++)
      {
        nums[p + shift] = modKTemp[p];
      }
    }
  }

  public static String nSpaces(int n)
  {
    if (n == 0)
    {
      return "";
    }
    char[] spaces = new char[n];
    Arrays.fill(spaces, ' ');
    return String.valueOf(spaces);
  }

  public static List<String> fullJustify(String[] words, int maxWidth) {

    List<String> lines = new ArrayList<>();
    List<String> tempLine = new ArrayList<>();

    int tempLineLength = 0;
    for (String word : words)
    {
      if ((tempLineLength + word.length() + tempLine.size()) <= maxWidth)
      {
        tempLine.add(word);
        tempLineLength += word.length();
      }
      else
      {
        StringBuilder line = new StringBuilder();
        if (tempLine.size() == 1)
        {
          line.append(tempLine.get(0));
          line.append(nSpaces(maxWidth - tempLineLength));
          //lines.add(line.toString());
        }
        else
        {
          //StringBuilder line = new StringBuilder();
          int totalSpaces = maxWidth - tempLineLength;
          int minSpacesBetweenWords = totalSpaces / (tempLine.size() - 1);
          int extraSpaces = totalSpaces % (tempLine.size() - 1);
          for (int i = 0; i < tempLine.size(); i++)
          {
            if (i != 0)
            {
              int numSpaces = minSpacesBetweenWords;
              if (extraSpaces > 0)
              {
                numSpaces++;
                extraSpaces--;
              }
              line.append(nSpaces(numSpaces));
            }
            line.append(tempLine.get(i));
          }
        }
        tempLine.clear();
        tempLine.add(word);
        tempLineLength = word.length();
        lines.add(line.toString());
      }
    }
    if (!tempLine.isEmpty())
    {
      StringBuilder line = new StringBuilder();
      for (int i = 0; i < tempLine.size(); i++)
      {
        if (i != 0)
        {
          line.append(" ");
          tempLineLength++;
        }
        line.append(tempLine.get(i));
      }
      line.append(nSpaces(maxWidth - tempLineLength));
      lines.add(line.toString());
    }
    return lines;
  }

  public static boolean isNumber(String s) {
    Pattern p = Pattern.compile("^[+-]?(\\d*\\.?\\d+)([eE][-+]?\\d+)?");
    Pattern p2 = Pattern.compile("^[+-]?(\\d+\\.?\\d*)([eE][-+]?\\d+)?");
    Matcher m = p.matcher(s);
    Matcher m2 = p2.matcher(s);
    return (m.matches() || m2.matches());
  }

  public static int saveThePrisoner(int n, int m, int s)
  {
    int prisonerToWarn = m % n + s - 1;
    if (prisonerToWarn > n)
    {
      prisonerToWarn -= n;
    }
    if (prisonerToWarn == 0)
    {
      prisonerToWarn = n;
    }
    return prisonerToWarn;
  }

  public static String reverseWords(String s)
  {
    String[] splitArray = s.trim().split("[ ]+");
    StringBuilder sb = new StringBuilder();
    for (int j = splitArray.length - 1; j >= 0; j--)
    {
      sb.append(splitArray[j].trim());
      if (j != 0)
      {
        sb.append(" ");
      }
    }
    return sb.toString();
  }

  public static int reverse2(int x) {
    int signMultiplier = x > 0 ? 1 : -1;
    int forwardX = x * signMultiplier;
    int reverseX = 0;
    boolean possibleExceeds32Bit = forwardX > 1E9 ;

    if (possibleExceeds32Bit && forwardX % 10 > 2)
    {
      return 0;
    }
    while (forwardX > 0)
    {
      int digit = forwardX % 10;
      reverseX = reverseX * 10 + digit;
      forwardX /= 10;
      if (possibleExceeds32Bit && forwardX > 0 && forwardX < 10)
      {
        if ((reverseX > 214748364) || (reverseX == 214748364 && forwardX > 8))
        {
          return 0;
        }
      }
    }
    return reverseX * signMultiplier;
  }

  public static int reverseDigits(int x) {
    LinkedList<Integer> digits = new LinkedList<>();
    int signMultiplier = x > 0 ? 1 : -1;
    int forwardX = Math.abs(x);
    while (forwardX > 0)
    {
      digits.add(forwardX % 10);
      forwardX /= 10;
    }
    boolean possibleExceeds32Bit = digits.size() == 10;
    if (possibleExceeds32Bit && digits.peek() > 2)
    {
      return 0;
    }
    int reverseX = 0;
    while (!digits.isEmpty() )
    {
      reverseX *= 10;
      reverseX += digits.removeFirst();
      if (possibleExceeds32Bit && digits.size() == 1 )
      {
        if ((reverseX > 214748364) || (reverseX == 214748364 && digits.peek() > 8))
        {
          return 0;
        }
      }
    }
    return reverseX * signMultiplier;
  }

  public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> differences = new HashMap<>();
    int[] result = new int[2];
    for (int i = 0; i < nums.length; i++)
    {
      if (!differences.containsKey(nums[i]))
      {
        differences.put(target - nums[i], i);
      }
      else
      {
        Integer index = differences.get(nums[i]);
        result[0] = index;
        result[1] = i;
        break;
      }
    }
    return result;
  }

  public static int lengthOfLongestSubstring(String s)
  {
    Map<Character, Integer> currentSubstringChars = new HashMap<>();
    LinkedList<Integer> indexQueue = new LinkedList<>();

    int maxSubstringLength = 0;
    int currentSubstringLength = 0;
    int startIndex = 0;
    for (int i = 0; i < s.length(); i++)
    {
      Integer existingIndex = 0;
      if ((existingIndex = currentSubstringChars.putIfAbsent(s.charAt(i), i)) != null)
      {
        startIndex = existingIndex + 1;

        currentSubstringLength = i - existingIndex;
        Integer indexToRemove = indexQueue.peekFirst();
        while (!indexQueue.isEmpty() &&indexToRemove != null && indexToRemove < startIndex)
        {
          indexQueue.removeFirst();
          currentSubstringChars.remove(s.charAt(indexToRemove));
          if (!indexQueue.isEmpty())
          {
            indexToRemove = indexQueue.peek();
          }
        }
        currentSubstringChars.put(s.charAt(i), i);
        indexQueue.add(i);
      }
      else
      {
        currentSubstringLength++;
        indexQueue.add(i);
        maxSubstringLength = Math.max(maxSubstringLength, currentSubstringLength);
      }
    }
    return maxSubstringLength;
  }

  public static int countSubstrings(String s, int start, int end)
  {
    Set<String> substrings = new HashSet<>();
    StringBuilder substring = new StringBuilder();
    int stepCounter = 0;
    for (int i = start; i <= end; i++)
    {
      substring.setLength(0);
      substring.append(s.charAt(i));
      substrings.add(substring.toString());
      for (int j = i + 1; j <= end; j++)
      {
        stepCounter++;
        substring.append(s.charAt(j));
        substrings.add(substring.toString());
      }
    }
    System.out.println("Steps: " + stepCounter);
    for (String str : substrings)
    {
      System.out.println(str);
    }
    return substrings.size();
  }

  public static List<List<String>> suggestedProducts(String[] products, String searchWord)
  {
    List<List<String>> recommendedItems = new ArrayList<>();
    //Arrays.sort(products);
    for (int i = 0; i < searchWord.length(); i++)
    {
      List<String> intermediateItems = new ArrayList<>();
      for (String item : products)
      {
        String matchString = searchWord.substring(0, i + 1);
        if (item.startsWith(matchString))
        {
          intermediateItems.add(item);
        }
      }
      Collections.sort(intermediateItems);
      if (intermediateItems.size() > 3)
      {
        intermediateItems.subList(3, intermediateItems.size()).clear();
      }
      recommendedItems.add(intermediateItems);
    }
    return recommendedItems;
  }

 // public static int[][] merge(int[][] intervals)
 // {

 // }

  public static Map<Character, List<Integer>> populateCharacterMap(String s)
  {
    Map<Character, List<Integer>> characterMap = new HashMap<>();

    for (int i = 0; i < s.length(); i++)
    {
      char charAt = s.charAt(i);
      List<Integer> indexList = characterMap.computeIfAbsent(charAt, key -> new ArrayList<>());
      indexList.add(i);
    }
    return characterMap;
  }

  public static int uniqueLetterString6(String s)
  {
    int result = 0;
    Map<Character, List<Integer>> characterIndexes = populateCharacterMap(s);

    for (Map.Entry<Character, List<Integer>> entry : characterIndexes.entrySet())
    {
      List<Integer> charIndexList = entry.getValue();
      for (int index = 0; index < charIndexList.size(); index++)
      {
        int currentCharIndexInString = charIndexList.get(index);

        int countLeftOfIndex = currentCharIndexInString;
        if (index != 0)
        {
          int previousCharIndexInString = charIndexList.get(index - 1);
          countLeftOfIndex = currentCharIndexInString - previousCharIndexInString - 1;
        }

        int lastIndex = charIndexList.size() - 1;
        int countRightOfIndex =s.length() - currentCharIndexInString - 1;
        if (index != lastIndex)
        {
          int nextIndex = charIndexList.get(index + 1);
          countRightOfIndex = nextIndex - currentCharIndexInString - 1;
        }
        result += countLeftOfIndex + (countRightOfIndex * (countLeftOfIndex + 1)) + 1;
      }
    }
    return result;
  }


  public static int uniqueLetterString5(String s)
  {
    Map<Character, ArrayList<Integer>> indexMap = new HashMap<>();

    //collect all the indices of each character and add it to a list
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      indexMap.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
    }

    int res = 0;

    for (Map.Entry<Character, ArrayList<Integer>> kv : indexMap.entrySet())
    {

      //each character's occurence in String s
      var allOccurrences = kv.getValue();
      for (int i = 0; i < allOccurrences.size(); i++)
      {
        int currentIndex = allOccurrences.get(i);

        //if this is the first occurence, then the number of characters on the left is just it's current Index
        //otherwise if it is not the first occurence, the number of characters on the left between this and its duplicate
        //is just the difference between the current index and the previous index - 1
        int left = i == 0 ? currentIndex : currentIndex - allOccurrences.get(i - 1) - 1;

        //if this is the last occurence, then the number of characters on the right is just the length of the string minus the current index - 1
        //otherwise, if it is not the first occurence, then it will just be the difference between this and the index of its duplicate - 1
        int right = i == allOccurrences.size() - 1 ? s.length() - currentIndex - 1 : allOccurrences.get(i + 1) - currentIndex - 1;

        //the number that this character can contribute to the final result
        //is the number of characters that it can contribute to all the substrings built from the left, excluding the substrings that includes it's duplicate, if any
        //plus
        //it can contribute the same number of characters to all the substrings built from here to it's next duplicate, if any
        res += 1 + left + (right * (left + 1));
      }
    }
    return res;
  }

  /*
  The Key of solving this problem in linear time is find the relationship between sum of countUniqueChars() for substrings ending index i and sum of countUniqueChars() for substrings ending index i-1. This article aims to leave leave no room for ambiguity​ of how this algorithm works.

  For each index i, consider all substrings ending at index i:

  s[0 : i] , ... , s[i-1 : i]  , s[i : i];

  Comparing with all substrings ending at index i-1:

  s[0: i-1], ... , s[i-1, i-1]

  We know the result for s[i : i] which only contains 1 character:

  countUniqueChars(s[i: i]) = 1

  Then we want to find the relationship between:

  countUniqueChars(s[0: i])    v.s.  countUniqueChars(s[0: i-1])
  countUniqueChars(s[1: i])    v.s.  countUniqueChars(s[1: i-1])
        ...                               ...
  countUniqueChars(s[i-1: i])  v.s.  countUniqueChars(s[i-1: i-1])

  Denote c = s.charAt(i), then for all substrings ending at index i-1, there could be 3 cases:

  // denote char c = s.charAt(i);
  Case 1. s[k : i-1] contains 0 c              ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1]) + 1
  Case 2. s[k : i-1] contains 1 c              ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1]) - 1
  Case 3. s[k : i-1] contains at least 2 c     ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1])

  The reasoning is pretty starightforward:

   Case 1.  s[k : i] has one more unique char (c itself) comparing to s[k : i-1]
   Case 2.  s[k : i] has one less unique char (c itself) comparing to s[k : i-1]
   Case 3.  any unique char in s[k : i-1] is still unique in s[k : i];
            c itself is not an unique char for either s[k : i-1] or s[k : i]

  Thus we find the relation between the sum of countUniqueChars() for substrings ending index i and sum of countUniqueChars() for substrings ending index i-1.

          sum of countUniqueChars() for index i
       =  sum of countUniqueChars() for index i-1  +  #(case 1)  -  #(case 2)  +  1

  where the last 1 stands for the substring s[i : i]. The remaining things are just calculating the number of substrings in Case 1 & Case 2. We denote:

  // p : index of last appearance of c;     or -1 if not exist (c never appears)
  // q : index of second last appearance of c;    or -1 if not exist (c never appears twice)

  Then for s[k : i-1] whose starting point is k:

  Case 1. k in [p+1, i-1]   <==>  c appears 0 time in s[k : i-1]
  Case 2. k in [q+1, p]     <==>  c appears 1 time in s[k : i-1]
  Case 3. k in [0, q]       <==>  c appears at least 2 times in s[k : i-1]

  Thus we have:

  #(Case 1)  =  (i-1) - (p+1) + 1
             =  i - p - 1
  #(Case 2)  =  p - (q+1) + 1
             =  p - q

  which leads to

      sum of countUniqueChars() for index i
   =  sum of countUniqueChars() for index i-1  +  (i - p - 1)  -  (p - q)  +  1
   =  sum of countUniqueChars() for index i-1  +  (i - p - p + q)

  Notice we set the default value of p & q to -1 to handles the cases with invalid intervals.
  Now we finish the reasoning. Getting the solution is very straightforward.
   */
  public static int uniqueLetterString4(String s)
  {
    int[] lastSeen = new int[26];
    Arrays.fill(lastSeen, -1);

    int[] secLastSeen = new int[26];
    Arrays.fill(secLastSeen, -1);

    int count = 0, res = 0;

    for (int i = 0; i < s.length(); i++)
    {
      int idx = s.charAt(i) - 'A';
      int p = lastSeen[idx];
      int q = secLastSeen[idx];

      count += i - p - p + q;
      res += count;

      secLastSeen[idx] = lastSeen[idx];
      lastSeen[idx] = i;
    }
    return res;
  }

  public int uniqueLetterString3(String s)
  {
    int n = s.length();
    int res = 0;
    for (int i = 0; i < n; i += 1)
    {
      int l = i - 1;
      while (l >= 0)
      {
        if (s.charAt(l) == s.charAt(i))
        {
          break;
        }
        l -= 1;
      }
      int r = i + 1;
      while (r < n)
      {
        if (s.charAt(r) == s.charAt(i))
        {
          break;
        }
        r += 1;
      }
      res += (r - i) * (i - l);
    }
    return res;
  }

  public static int uniqueLetterString2(String s)
  {
    int res = 0;

    int[][] occurences = new int[26][2];
    for (int[] occurence : occurences)
    {
      Arrays.fill(occurence, -1);
    }

    for (int i = 0; i < s.length(); i++)
    {
      int index = s.charAt(i) - 'A';
      // update result
      // occurences[i][0], occurences[i][1], i
      res += (i - occurences[index][1]) * (occurences[index][1] - occurences[index][0]);
      occurences[index][0] = occurences[index][1];
      occurences[index][1] = i;
    }

    for (int[] occurence : occurences)
    {
      res += (s.length() - occurence[1]) * (occurence[1] - occurence[0]);
    }
    return res;
  }

  public static int uniqueLetterString(String s)
  {
    int[] left = new int[s.length()];
    int[] right = new int[s.length()];

    Map<Character, Integer> m = new HashMap<>();
    for (int i = 0; i < s.length(); ++i)
    {
      var c = s.charAt(i);
      left[i] = m.getOrDefault(c, -1);
      m.put(c, i);
    }

    m.clear();

    for (int i = s.length() - 1; i >= 0; --i)
    {
      var c = s.charAt(i);
      right[i] = m.getOrDefault(c, s.length());
      m.put(c, i);
    }

    int result = 0;
    for (int i = 0; i < s.length(); ++i)
    {
      result += (i - left[i]) * (right[i] - i);
    }
    return result;
  }


  public static int countSubstrings2(String s, int start, int end)
  {
    int characterCounts[] = new int[26];
    int substringCount = 0;
    int stepCounter = 0;
    int i = start, j = start;
    while (i <= end)
    {
      System.out.println("i = " + i + " j = " + j);
      if (j <= end && (characterCounts[s.charAt(j) - 'a'] == 0))
      {
        characterCounts[s.charAt(j) - 'a']++;
        System.out.println(characterCounts);
        substringCount += (j - i + 1);
        System.out.println("substringCount = " + substringCount);
        j++;
      }
      else
      {
        characterCounts[s.charAt(j) - 'a']--;
        System.out.println("Charactercounts = " + characterCounts);
        i++;
        j = i;
      }
    }
    return substringCount;
  }


  private static int sherlockPermutations(int m, int n)
  {   // (L-1)! / ((n-1)!*(m)!)
    int result = 0;
    int totalLength = n + m;
    BigInteger totalLengthMinusOneFact = factorial(totalLength - 1);
    System.out.println("L - 1 fact: " + totalLengthMinusOneFact);
    BigInteger nMinusOneFact = factorial(n - 1);
    System.out.println("n - 1 fact: " + nMinusOneFact);
    BigInteger mFact = factorial(m);
    System.out.println("m fact: " + mFact);
    BigInteger denominator = mFact.multiply(nMinusOneFact);
    System.out.println("Denominator: " + denominator);
    BigInteger quotient = totalLengthMinusOneFact.divide(denominator);
    System.out.println("quotient: " + quotient);
    result = quotient.mod(BigInteger.valueOf(1000000007)).intValue();
    return result;
  }

  private static int sherlockPermutations2(int m, int n)
  {   // (L-1)! / ((n-1)!*(m)!)
    int result = 0;
    int totalLength = n + m;
    int totalLengthStart = Math.max(m, n - 1) + 1;
    System.out.println("Total Length: " + totalLength + " Total length start: " + totalLengthStart);
    BigInteger totalLengthMinusOneFact = partialFactorial(totalLengthStart, totalLength - 1);
    System.out.println("L - 1 fact: " + totalLengthMinusOneFact);

    BigInteger denominator = factorial(Math.min(m, n - 1));
    System.out.println("Denominator: " + denominator);
    BigInteger quotient = totalLengthMinusOneFact.divide(denominator);
    System.out.println("quotient: " + quotient);
    result = quotient.mod(BigInteger.valueOf(1000000007)).intValue();
    return result;
  }

  public static BigInteger factorial(int number)
  {
    BigInteger factorial = BigInteger.ONE;

    for (int i = number; i > 0; i--)
    {
      factorial = factorial.multiply(BigInteger.valueOf(i));
    }
    return factorial;
  }
  public static int firstMissingPositive(int[] nums)
  {
    int maxContinuousFromOne = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++)
    {
      if (maxContinuousFromOne + 1 == nums[i])
      {
        maxContinuousFromOne++;
      }
      else if (nums[i] > 0 && nums[i] != maxContinuousFromOne)
      {
        if (pq.isEmpty() || ( nums[i] != pq.peek() && nums[i] > maxContinuousFromOne))
        {
          pq.add(nums[i]);
        }
      }
      while(!pq.isEmpty() && (pq.peek() == maxContinuousFromOne + 1))
      {
        maxContinuousFromOne = pq.poll();
        while (!pq.isEmpty() && pq.peek() == maxContinuousFromOne)
        {
          pq.poll();
        }
      }
    }
    return maxContinuousFromOne + 1;
  }

  public static ListNode deleteDuplicates(ListNode head)
  {
    if (head == null || head.next == null)
    {
      return head;
    }
    ListNode tempHead = head;
    ListNode curr = head;
    ListNode tail = head;
    int lastVal = curr.val;
    while (curr != null)
    {
      ListNode next = curr.next;
      if (next != null)
      {
        if (next.val == lastVal)
        {
          if (curr == tempHead)
          {
            tempHead = next.next;
            tail = next.next;
          }
          else
          {
            tail.next = next.next;
            //tail =
          }
          curr = next.next;
        }
        else
        {
          tail.next = curr;
          curr = curr.next;
          tail = curr;
          lastVal = curr.val;
        }
      }
    }

    return tempHead;
  }

  public static BigInteger partialFactorial(int start, int end)
  {
    BigInteger factorial = BigInteger.ONE;

    for (int i = start; i <= end; i++)
    {
      factorial = factorial.multiply(BigInteger.valueOf(i));
    }
    return factorial;
  }

  class ListNode
  {
    int val;
    ListNode next;

    ListNode()
    {
    }

    ListNode(int val)
    {
      this.val = val;
    }

    ListNode(int val, ListNode next)
    {
      this.val = val;
      this.next = next;
    }
  }

  public static ListNode reverseKGroup(ListNode head, int k)
  {
    ListNode kthNode = null;
    ListNode listHead = head;
    ListNode workingHead = head;
    ListNode kthNodeNext = null;
    ListNode temp = null;
    ListNode whTemp = null;
    ListNode whTempNext = null;
    ListNode current = head;
    ListNode loopNode = head;
    ListNode previousWhNode = head;
    int iterationCount = 0;
    while (loopNode != null)
    {
      iterationCount++;
      int i = 0;
      for (i = 0; i < k - 1 && current != null; i++)
      {
        current = current.next;
      }
      if (current != null && (i == k - 1))
      {
        kthNode = current;
        kthNodeNext = current.next;
        if (iterationCount == 1)
        {
          listHead = kthNode;
        }

        while (workingHead != kthNode)
        {
          whTemp = workingHead;
          whTempNext = workingHead.next;
          workingHead.next = null;
          temp = kthNode.next;
          kthNode.next = whTemp;
          workingHead = whTempNext;
          kthNode.next.next = temp;
        }
        workingHead = kthNodeNext;
        if (iterationCount > 1)
        {
          previousWhNode.next = kthNode;
          previousWhNode = loopNode;
        }
        current = kthNodeNext;
        loopNode = kthNodeNext;
      }
      else
      {
        break;
      }
    }
    return listHead;
  }

  public static int longestValidParentheses(String s)
  {
    Stack<Integer> openStack = new Stack<>();
    Stack<Integer[]> countStack = new Stack<>();
    Stack<Integer> contiguousGroupStack = new Stack<>();

    int maxLen = 0, currentCount = 0;
    int startIndex = s.indexOf('(');
    int currentStackDepth = 0;

    int endIndex = s.lastIndexOf(')');

    if (endIndex != -1)
    {
      int i = startIndex != -1 ? startIndex : endIndex + 1;
      while (i <= endIndex)
      {
        if (s.charAt(i) == '(')
        {
          openStack.push(i);
          currentStackDepth = openStack.size();
          if (currentCount != 0)
          {
            countStack.push(new Integer[]{currentCount, openStack.size()});
          }

          currentCount = 0;
          i++;
        }
        if (s.charAt(i) == ')')
        {
          if (!openStack.isEmpty())
          {

            if (openStack.size() <= currentStackDepth)
            {
              currentStackDepth = openStack.size();
              Integer[] countValue = null;
              while (!countStack.isEmpty())
              {
                countValue = countStack.peek();
                if (countValue[1] < currentStackDepth)
                {
                  break;
                }
                currentCount += countValue[0];
                countStack.pop();
              }
            }
            openStack.pop();

            currentCount += 2;
            maxLen = Math.max(maxLen, currentCount);
            if (openStack.empty())
            {

              while (!contiguousGroupStack.isEmpty())
              {
                currentCount += contiguousGroupStack.pop();
              }
              contiguousGroupStack.empty();


              maxLen = Math.max(maxLen, currentCount);
              startIndex = s.indexOf('(', i);
              if (startIndex != -1)
              {

                if (startIndex == i + 1)
                {
                  contiguousGroupStack.push(currentCount);
                }
                maxLen = Math.max(maxLen, currentCount);

                currentCount = 0;
                i = startIndex;
              }
              else
              {
                i = endIndex + 1;
              }

            }

            else
            {
              i++;
            }
          }
          else
          {
            i++;
          }
        }
      }
    }
    return maxLen;
  }

}
