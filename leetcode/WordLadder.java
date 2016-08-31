/**
No. 127
Given two words(beginWord and endWord), and a dictionary's word list, find the
length of shortest transformation sequence from beginWord to endWord, such that:
    1. Only one letter can be changed at a time
    2. Each intermediate word must exist in the word list
**/
import java.util.Set;
import java.util.HashSet;
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);
        Set<String> reachSet = new HashSet<String>();
        reachSet.add(beginWord);
        int count = 1;
        while(!reachSet.isEmpty()) {
            Set<String> tmpSet = new HashSet<String>();
            for(String bs : reachSet) {
                for(int i = 0; i < bs.length(); ++i) {
                    char[] chars = bs.toCharArray();
                    for(char ch = 'a'; ch <= 'z'; ++ch) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if(wordList.contains(word)) {
                            tmpSet.add(word);
                            wordList.remove(word);
                        }
                    }
                }
            }
            reachSet = tmpSet;
            ++count;
            if(reachSet.contains(endWord))
                return count;
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hot";
        String endWord = "dog";
        Set<String> set = new HashSet<String>();
        set.add("hot"); set.add("dot"); set.add("dog");
        //set.add("lot"); set.add("log");
        WordLadder obj = new WordLadder();
        int ret = obj.ladderLength(beginWord, endWord, set);
        System.out.println(ret);
    }
}
