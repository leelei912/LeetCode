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
	//y �ڼ��� �����꣬x�� ������
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
	//�ݹ�ʵ�� ���������� y x ��ĸ�� ��i����ĸ ���������Թ��㷨
	private static boolean exist(char[][] board, int y, int x, char[] word, int i) {
		if (i == word.length) return true;//��iΪ��ĸ��ĳ���ʱ��i�Ǵ�0��ʼ�� ˵���Ѿ����ʵ�����ĸ����+1�ˣ�Ҳ����ǰ��������ĸ��������
		if (y<0 || x<0 || y == board.length || x == board[y].length) return false;//y<0 x>0Խ�� y=len=x Խ��
		if (board[y][x] != word[i]) return false;//���ǹ涨��ĸ
		//��������һ��˵�� �ҵ�����ĸ
		//λ���( ^ ) 1 0000 0000 ����������1λ���ֲ��䣬0λҲ���� ������ĸ�ǲ��ᳬ��255��Ҳ����˵ 
		//256���λΪ����λ����������û����ʱ���ͽ������ӵ����λ��0->1
		board[y][x] ^= 256;
		boolean exist = exist(board, y, x+1, word, i+1)
			|| exist(board, y, x-1, word, i+1)
			|| exist(board, y+1, x, word, i+1)
			|| exist(board, y-1, x, word, i+1);
		///�������ӷ��ʹ�ʱ�����λ��1���ͽ������ӵ����λ��1^1->0
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
        
        //������Ŷ ������Ķ�Ӧ
        board[i][j] = word[index];
        return res;
    }
}