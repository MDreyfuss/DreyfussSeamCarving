import java.io.File;

public class SeamCarving {

    public static void main(String[] args) {
        Image picture = new Image("src\\main\\resources\\Broadway_tower_edit.jpg");
        //File newPic = picture.horizontalSeamCarving(100);
        File newPic = picture.verticalSeamCarving(100);
    }
}
