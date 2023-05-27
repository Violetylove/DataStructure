package sequential.sparse;

/**
 * 稀疏矩阵和稀疏数组
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SparseArray {
    public static void main(String[] args) {
        // 用稀疏数组表示一个五子棋棋盘，
        // 创建一个原始的二维稀疏矩阵 11 * 11。0表示没有棋子，1表示黑子，2表示白子。
        int chessRow = 11;
        int chessColumn = 11;
        int[][] chessArr1 = new int[chessRow][chessColumn];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 2;
        // 输出原始数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将原始数组转换到稀疏数组
        // 1. 遍历，得到非零元素的个数sum
        int sum = 0;
        for (int i = 0; i < chessRow; i++) {
            for (int j = 0; j < chessColumn; j++) {
                if (chessArr1[i][j] != 0)
                    sum ++;
            }
        }

        // 2. 创建稀疏数组
        /*
            稀疏数组：
            row  column  value
            11     11      2     第一行存储原始矩阵的行列数和非零元素个数
            1      2       1     第一个非零元素的位置和值
            2      3       2     第二个非零元素的位置和值
            所以稀疏数组的大小为 (sum+1) * 3
         */
        int[][] sparseArr = new int[sum+1][3];

        // 3. 给稀疏数组赋值
        // 第一行，存储原始数组信息
        sparseArr[0][0] = chessRow;
        sparseArr[0][1] = chessColumn;
        sparseArr[0][2] = sum;
        // 遍历其他行，存储非零元素
        int count = 1;// 遍历时用到的协助换行的一个数
        for (int i = 0; i < chessRow; i++) {
            for (int j = 0; j < chessColumn; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count ++;
                }
            }
        }
        // 输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int[] row : sparseArr){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将稀疏数组恢复成原始数组
        // 1. 先读第一行，创建原始数组
        int[][] chessArr2 = new int[ sparseArr[0][0] ][ sparseArr[0][1] ];
        // 2. 读取余下的数据并赋给原始数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[ sparseArr[i][0] ][ sparseArr[i][1] ] = sparseArr[i][2];
        }
        System.out.println();
        System.out.println("恢复后的数组为：");
        for (int[] row : chessArr2){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
