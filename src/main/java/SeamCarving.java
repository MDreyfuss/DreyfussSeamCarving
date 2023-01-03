import java.io.File;

public class SeamCarving {

    public static void main(String[] args) {
        Image picture = new Image("src\\main\\resources\\Broadway_tower_edit.jpg");
        //File newPic = picture.horizontalSeamCarving(100);
        File newPic = picture.verticalSeamCarving(100);
    }

/*
    public class Main
    {
        public static void main(String[] args) {
            int[][] seams = {
                    {1,5,6,11,10},
                    {3,3,8,8,11},
                    {5,5,7,9,9}
            };

            int[][] out = new int[seams.length-1][seams[0].length];
            int[] remove = {0,1,0,1,2};
            for (int i = 0; i < seams.length - 1; i++)
            {
                for (int j=0; j<remove.length; j++)
                {
                    if (i >= remove[j])
                    {
                        out[i][j] = seams[i+1][j];
                    }
                    else{
                        out[i][j] = seams[i][j];
                    }
                }
            }

            for (int[] intArray : out)
            {
                for (int num : intArray)
                {
                    System.out.print(num+", ");
                }
                System.out.println();
            }
        }
    }

*/
}
