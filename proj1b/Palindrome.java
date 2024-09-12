import java.util.LinkedList;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        if(word.isEmpty()){
            return new ArrayDeque<Character>();
        }
        Deque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word){
        Deque<Character>word_deque = wordToDeque(word);
        return isPalindrome(word_deque);
    }
    private boolean isPalindrome(Deque<Character> word_deque){
        if(word_deque.isEmpty()){
            return true;
        }
        if(word_deque.size() == 1){
            return true;
        }
        if(word_deque.removeFirst()!=word_deque.removeLast()){
            return false;
        }
        else{
            return isPalindrome(word_deque);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character>word_deque = wordToDeque(word);
        return isPalindrome(word_deque, cc);
    }

    private boolean isPalindrome(Deque<Character> word_deque, CharacterComparator cc){
        if(word_deque.isEmpty()){
            return true;
        }
        if(word_deque.size() == 1){
            return true;
        }
        if(cc.equalChars(word_deque.removeFirst(), word_deque.removeLast())){
            return isPalindrome(word_deque, cc);
        }
        else{
            return false;
        }
    }
}
