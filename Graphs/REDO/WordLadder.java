package REDO;

// Change one letter at a time; every intermediate word must be in wordList.
// Return the number of words in the SHORTEST sequence from beginWord to
// endWord, counting both ends, or 0 if there is no such sequence.
// beginWord need not be in wordList; endWord must be, or the answer is 0.
// All words are the same length and lowercase.

public static void main(String[]args){test("leetcode example","hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"),5);

// same list, endWord missing -> unreachable
test("end word not in list","hit","cog",Arrays.asList("hot","dot","dog","lot","log"),0);

test("empty word list","hit","cog",Arrays.asList(),0);

// one letter apart already
test("single step","hit","hot",Arrays.asList("hot"),2);

// single-character words
test("one letter words","a","c",Arrays.asList("a","b","c"),2);

// two letters differ and nothing bridges them
test("no bridge word","hot","dog",Arrays.asList("hot","dog"),0);

// same pair, now with the bridge present
test("bridge word present","hot","dog",Arrays.asList("hot","dog","dot"),3);

// two shortest paths of equal length, plus decoy words that lead nowhere
test("two equal shortest paths","red","tax",Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"),4);

// aaa->aba->abc is 3, but aaa->aab->abb->abc is 4; the short one must win
test("short route beats long route","aaa","abc",Arrays.asList("aab","abb","abc","aba","aac"),3);

// beginWord also sits in the list, must not cause a loop or an off-by-one
test("begin word inside list","hot","dog",Arrays.asList("hot","dot","dog"),3);}

// fresh instance each call: solution may use instance fields
static void test(String name,String beginWord,String endWord,List<String>wordList,int expected){int actual=new WordLadder().ladderLength(beginWord,endWord,wordList);boolean pass=actual==expected;System.out.println((pass?"PASS":"FAIL")+" ["+name+"]");if(!pass)System.out.println("  expected: "+expected+"  actual: "+actual);}

HashMap<String,List<String>>adj=new HashMap<>();HashMap<String,Boolean>visited=new HashMap<>();Queue<String>queue=new ArrayDeque<>();

public int ladderLength(String beginWord,String endWord,List<String>wordList){

// create neighbours for first word
for(String toCheck:wordList){createNeighbours(beginWord,toCheck);}visited.put(beginWord,false);

// add all the neighbours of WordList
for(String word:wordList){for(String toCheck:wordList){createNeighbours(word,toCheck);}visited.put(word,false);}

queue.add(beginWord);int ladderLength=0;while(!queue.isEmpty()){int size=queue.size();ladderLength++;for(int i=0;i<size;i++){String cur=queue.poll();if(cur.equals(endWord))return ladderLength;List<String>curNeighbors=adj.get(cur);if(curNeighbors==null)continue;for(String neigbour:curNeighbors){if(!visited.get(neigbour)){queue.add(neigbour);visited.put(neigbour,true);}}}}return 0;

}

private boolean canBeNeighbour(String x,String y){int count=0;for(int i=0;i<x.length();i++){if(x.charAt(i)!=y.charAt(i))count++;if(count>1)return false;}return true;}

private void createNeighbours(String word,String toCheck){if(word.equals(toCheck))return;if(canBeNeighbour(word,toCheck)){List<String>temp=adj.getOrDefault(word,new ArrayList<>());temp.add(toCheck);adj.put(word,temp);}}

import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Step 1: Put all words into a HashSet for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in wordList, transformation is impossible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Step 2: Queue for BFS
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        // Track sequence length (starting with beginWord, so length is 1)
        int level = 1;

        // Step 3: BFS Level-by-Level
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all words at the current transformation depth
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // Base Case: Reached the destination word!
                if (currentWord.equals(endWord)) {
                    return level;
                }

                // Generate all 1-character variations
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // Try replacing character at index 'j' with 'a' through 'z'
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar)
                            continue;

                        wordChars[j] = c;
                        String neighbor = new String(wordChars);

                        // If transformed word is in the dictionary, enqueue it
                        if (wordSet.contains(neighbor)) {
                            queue.add(neighbor);
                            wordSet.remove(neighbor); // Mark as visited by removing it
                        }
                    }

                    // Restore original character for next position
                    wordChars[j] = originalChar;
                }
            }

            // Increment transformation step count for the next level
            level++;
        }

        // Return 0 if endWord was never reached
        return 0;
    }
}}
