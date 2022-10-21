public class WordCount implements Comparable<WordCount>{
    public String word;
    public int count;
    
    public WordCount(String word, int count){
        this.word = word;
        this.count = count;
    }
    public int compareTo(WordCount comparable){
        if(this.count > comparable.count){
            return(1);
        } else if (this.count < comparable.count) {
            return(-1);
        } else {
            return(0);
        }
    }

    
    
    
}
