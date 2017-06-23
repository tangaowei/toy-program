/**
leetCode 542
2017/03/25
*/
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
public class Matrix01 {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int row = matrix.size(), col = matrix.get(0).size();
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < col; ++j) {
                if(matrix.get(i).get(j) == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }

        int[] mx = {-1, 0, 1, 0};
        int[] my = {0, 1, 0, -1};
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int i = 0; i < 4; ++i) {
                int nx = cur[0] + mx[i];
                int ny = cur[1] + my[i];
                if(nx < 0 || nx >= row || ny < 0 || ny >= col ||
                    matrix.get(cur[0]).get(cur[1]) <= matrix.get(nx).get(ny) + 1)
                    continue;
                matrix.get(nx).set(ny, matrix.get(cur[0]).get(cur[1]) + 1);
                queue.add(new int[]{nx, ny});
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for(int i = 0; i < 3; ++i) {
            ArrayList<Integer> alist = new ArrayList<Integer>();
            alist.add(0);
            alist.add(1);
            alist.add(1);
            list.add(alist);
        }
        Matrix01 obj = new Matrix01();
        List<List<Integer>> retList = obj.updateMatrix(list);
        System.out.println(retList.toString());
    }
}
