

//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
//The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
//A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
//���һ�������Ƿ�valid ����û������
//������һ��9x9�ľ���������9��С�� Ҫ��ÿ�ж��в��ظ���1~9 �������ȱ���ǲ�������
//ÿ�� Ҳ�ǲ��ظ�1~9  �������ȱ���ǲ�������
//ÿ��С��������Ҳ�� 1~9 ����ȱ������

public class ValidSudoku {
//��������������ô��   ����� ����� ���ÿ��С���������

	public boolean isValidSudoku(char[][] board) {
    	return isValidRow(board)&&isValidColumn(board)&&isValidBox(board);     
 	}
	
	//��� ���c�ǲ����Ѿ���flag����� ����ظ����ַ���false���൱��hash������
	private boolean markFlag(boolean[] flag,char c){
		if(c=='.'){
			return true;
		}
		int index= c-'1';
		if(flag[index]){  //���֮ǰ�Ѿ����ֹ��������
			return false;
		}else{
			flag[index]=true; // ��Ӧ������Ϊtrue����ʾ�Ѿ����ֹ���
			return true;
		}
	}
 
   //���ű������� ÿ�μ��ÿ�г䲻�ظ�
	private  boolean  isValidRow(char[][] board) {
		for(int i=0;i<9;i++){
			boolean[] flag= new boolean[9];  // Ҳ����ÿһ�д���һ��hashset  
			for(int j=0;j<9;j++){
				if(!markFlag(flag,board[i][j])){
					return false;
				}
			}
		}
		return true;
	}
	 //���ű������� ÿ�μ��ÿ���ز��ظ�
	private boolean isValidColumn(char[][] board){
		for(int i=0;i<9;i++){
			boolean[] flag= new boolean[9];
			for(int j=0;j<9;j++){    //�ĳ�j i����
				if(!markFlag(flag,board[j][i])){
					return false;
				}
			}
		}
		return true;
	}
	//�������� �������С�Ź���䲻�ظ�
	private boolean isValidBox(char[][] board){
		for(int i=0;i<9;i+=3){
			for(int j=0;j<9;j+=3){  // i*j  �оŸ�С�Ź���
				boolean[] flag=new boolean[9];
				for (int m = 0; m<3;m++) {
					for(int n = 0;n<3;n++){
						if(!markFlag(flag, board[i+m][j+n])){
							return false;
						}
					}
					
				}
			}
		}
		return true;
	}
}



// 01/11/15
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = null;
        for(int i = 0; i < 9; i++){  // each row
            set = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                if(set.contains(board[i][j])) return false;
                set.add(board[i][j]);
            }
        }
        
        for(int i = 0; i < 9; i++){  // each col
            set = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.') continue;
                if(set.contains(board[j][i])) return false;
                set.add(board[j][i]);
            }
        }
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                set = new HashSet<Character>();
                for(int m = 0; m < 3; m++){
                    for(int n = 0; n < 3; n++){
                        int row = i*3+m;
                        int col = j*3+n;
                        if(board[row][col] == '.') continue;
                        if(set.contains(board[row][col])) return false;
                        set.add(board[row][col]);
                    }
                }    
            }    
        }
        return true;
    }
}

// 03/15/15
// ���ÿ��row�����ÿ��col�����ÿ��sub square
// �����'.'��������������ҵ�map��Ӧ��λ�ÿ��Ƿ��Ѿ�����
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++){
            boolean[] map = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int d = board[i][j] - '1';
                if(map[d] == true) return false;
                else map[d] = true;
            }
        }
        for(int i = 0; i < 9; i++){
            boolean[] map = new boolean[9];
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.') continue;
                int d = board[j][i] - '1';
                if(map[d] == true) return false;
                else map[d] = true;
            }
        }
        for(int i = 0; i < 9; i += 3){
            for(int j = 0; j < 9; j += 3){
                boolean[] map = new boolean[9];
                for(int m = 0; m < 3; m++){
                    for(int n = 0; n < 3; n++){
                        if(board[i+m][j+n] == '.') continue;
                        int d = board[i+m][j+n] - '1';
                        if(map[d] == true) return false;
                        else map[d] = true;
                    }
                }
            }
        }
        return true;
    }
}