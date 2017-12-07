package leetcode;
/**
 * 	Input:
		[
		  ['A','B','C','E'],
		  ['S','F','C','S'],
		  ['A','D','E','E']
		]
	Output:
		word = "ABCCED", -> returns true,
		word = "SEE", -> returns true,
		word = "ABCB", -> returns false.
 * */
public class WordSearch {
	
	public static boolean exist(char[][] board, String word) {
		char[] characters = word.toCharArray();
		for(int i = 0 ; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(exist(board, j, i, characters, 0)) return true;
			}
		}
        return false;
    }
	//y 第几行 纵坐标，x列 横坐标
	public static boolean exist(char[][] board, int x, int y, char[] word, int index) {
		if(word.length == index) return true;
		if(y < 0 || x < 0 || y == board.length  || x == board[y].length) return false;
		if(board[y][x] != word[index]) return false;
		board[y][x] ^= 256;
		boolean isExist = exist(board, x + 1, y, word, index+1) 
				|| exist(board,x - 1, y,  word, index+1)
				|| exist(board, x, y + 1, word, index+1) 
				|| exist(board, x, y - 1, word, index+1);
		board[y][x] ^= 256;
		return isExist;
	}
	
	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCED";
		if(exist(board, word))
			System.out.println("true");
		else
			System.out.println("false");
	}
}

class Solution2 {
	
	public static boolean exist(char[][] board, String word) {
	    char[] w = word.toCharArray();
	    for (int y=0; y<board.length; y++) {
	    	for (int x=0; x<board[y].length; x++) {
	    		if (exist(board, y, x, w, 0)) return true;
	    	}
	    }
	    return false;
	}
	//递归实现 参数：棋盘 y x 字母表 第i个字母 类似于走迷宫算法
	private static boolean exist(char[][] board, int y, int x, char[] word, int i) {
		if (i == word.length) return true;//当i为字母表的长度时，i是从0开始的 说明已经访问到了字母表长度+1了，也就是前面所有字母都存在了
		if (y<0 || x<0 || y == board.length || x == board[y].length) return false;//y<0 x>0越界 y=len=x 越界
		if (board[y][x] != word[i]) return false;//不是规定字母
		//到下面这一步说明 找到了字母
		//位异或( ^ ) 1 0000 0000 异或的作用是1位保持不变，0位也不变 但是字母是不会超过255的也就是说 
		//256最高位为控制位，当该棋子没访问时，就将该棋子的最高位由0->1
		board[y][x] ^= 256;
		boolean exist = exist(board, y, x+1, word, i+1)
			|| exist(board, y, x-1, word, i+1)
			|| exist(board, y+1, x, word, i+1)
			|| exist(board, y-1, x, word, i+1);
		///当该棋子访问过时，最高位是1，就将该棋子的最高位由1^1->0
		board[y][x] ^= 256;
		return exist;
	}
}
class MaxSpeedSolution {
    
    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || board[0].length == 0)   return false;
        char[] array = word.toCharArray();
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == array[0]){
                    if(dfs(board, i, j, 0, array))     return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, int index, char[] word){
        if(word.length == index)    return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[index])     return false;//board[i][j] != word[index]
        
        board[i][j] = '#';  //each letter could only be used once
        
        boolean res = dfs(board, i+1, j, index + 1, word) || dfs(board, i-1, j, index + 1, word) || dfs(board, i, j+1, index + 1, word) || dfs(board, i, j-1, index + 1, word);
        
        //看不懂哦 和上面的对应
        board[i][j] = word[index];
        return res;
    }
}