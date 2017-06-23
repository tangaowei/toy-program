/**
Leetcode No. 126
**/
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;

public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        LinkedList<String> list = new LinkedList<String>();
        list.add(beginWord);
        List<LinkedList<String>> llist = new LinkedList<LinkedList<String>>();
        llist.add(list);
        wordList.add(endWord);
        boolean hasPath = false;
        while(!llist.isEmpty()) {
            List<LinkedList<String>> tmplList = new LinkedList<LinkedList<String>>();
            Set<String> removeSet = new HashSet<String>();
            for(LinkedList<String> tmpList : llist) {
                String curWord = tmpList.getLast();
                for(int i = 0; i < curWord.length(); ++i) {
                    char[] chars = curWord.toCharArray();
                    for(char c = 'a'; c <= 'z'; ++c) {
                        if(chars[i] == c) continue;
                        chars[i] = c;
                        String word = new String(chars);
                        if(wordList.contains(word)) {
                            LinkedList addList = new LinkedList<String>(tmpList);
                            addList.add(word);
                            tmplList.add(addList);
                            removeSet.add(word);
                        }
                    }
                }
            }
            llist = tmplList;
            if(removeSet.contains(endWord)) {
                hasPath = true;
                break;
            }
            wordList.removeAll(removeSet);
        }
        List<List<String>> retList = new LinkedList<>();
        if(hasPath) {
            for(LinkedList<String> tmpList : llist) {
                if(endWord.equals(tmpList.getLast()))
                    retList.add(tmpList);
            }
        }
        return retList;
    }


    public static void main(String[] args) {
        WordLadder2 obj = new WordLadder2();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log"};
        Set<String> set = new HashSet<String>();
        for(String s : wordList) {
            set.add(s);
        }
        List<List<String>> llist =  obj.findLadders(beginWord, endWord, set);
        for(List<String> list : llist)
            System.out.println(list);
    }
}
