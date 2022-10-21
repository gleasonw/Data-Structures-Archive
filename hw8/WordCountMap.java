import java.util.ArrayList;

public class WordCountMap<E extends Comparable<E>>{
    Node root;
    Node current;
    
    
    private class Node {
        private String word;
        private int count;
        private Node left;
        private Node right;
        
        private Node(String strKey){
            this.word = strKey;
            this.count = 1;
            this.right = null;
            this.left = null;
        }
    }
        
    public WordCountMap(){
        this.root = null;
    }
    
    private Node add(String item, Node current){
        if(current == null){
            current = new Node(item);
            return(current);
        } else if(current.word.compareTo(item) == 0){
            current.count++;
            return(current);
        } else if(current.word.compareTo(item) > 0){
            current.left = add(item, current.left);
            return(current);
        } else {
            current.right = add(item, current.right);
            return(current);
        }
    }
    
    private Boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }
        
    public void inOrderWord() {
        System.out.println(inOrderWord(root));
    }
    private String inOrderWord(Node n){
        if (n == null) {
            return "";
        }
        if (n.left == null && n.right == null) {
            return((String) n.word);
        } if (n.left != null) {
            return(inOrderWord(n.left) + (String) n.word + inOrderWord(n.right));
        } else {
            return((String) n.word + inOrderWord(n.right));
        }
    }

    public ArrayList<WordCount> inAlphaOrder(Node n, ArrayList objectList){
        if (n == null) {
            return(objectList);
        }
        if (n.left == null && n.right == null) {
            objectList.add(new WordCount(n.word, n.count));
            return(objectList);
        } if (n.left != null) {
            inAlphaOrder(n.left, objectList);
            objectList.add(new WordCount(n.word, n.count));
            inAlphaOrder(n.right, objectList);
            return(objectList);
        } else {
            objectList.add(new WordCount(n.word, n.count));
            inAlphaOrder(n.right, objectList);
            return(objectList);
        }
    }
        
    /**
   * If the specified word is already in this WordCountMap, then its
   * count is increased by one. Otherwise, the word is added to this map
   * with a count of 1.
   */
    public void incrementCount(String word) {
        root = add(word, root);
    }

   /**
   * Returns an array list of WordCount objects, one per word stored in this
   * WordCountMap, sorted in decreasing order by count.
   */
    public ArrayList<WordCount> getWordCountsByCount() {
        ArrayList<WordCount> countList = new ArrayList<WordCount>();
        countList = inAlphaOrder(root, countList);
        sortByCount(countList);
        return countList;
    }
    
    public ArrayList sortByCount(ArrayList<WordCount> List) {
        int j;
        for(int k = 1; k < List.size(); k++){
            WordCount curObject = List.get(k);
            j = k-1;
            while((j >= 0) && (curObject.compareTo(List.get(j)) > 0)){
                WordCount temp = List.get(j);
                List.set(j, List.get(j+1));
                List.set(j+1, temp);
                j--;
            }
        }
        return List;
    }

   /**
   * Returns a list of WordCount objects, one per word stored in this
   * WordCountMap, sorted alphabetically by word.
   */
    public ArrayList<WordCount> getWordCountsByWord() {
        ArrayList<WordCount> alphaList = new ArrayList<WordCount>();
        return inAlphaOrder(root, alphaList);
    }
    
    public static void main(String[] args){
        WordCountMap<String> test = new WordCountMap<String>();
        test.incrementCount("hello");
        test.incrementCount("hello");
        test.incrementCount("hello");
        test.incrementCount("cry");
        test.incrementCount("cry");
        test.incrementCount("helpo");
        test.incrementCount("a");
        test.incrementCount("zzzz");
        test.incrementCount("zzzz");
        test.incrementCount("zzzzz");
        test.inOrderWord();
        ArrayList<WordCount> objectList = new ArrayList<WordCount>();
        objectList = test.getWordCountsByCount();
        for(int i = 0; i < objectList.size(); i++){
            System.out.println(objectList.get(i).word);
        }
        for(int i = 0; i < objectList.size(); i++){
            System.out.println(objectList.get(i).count);
        }
    }

}

