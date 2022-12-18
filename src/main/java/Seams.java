import java.awt.image.BufferedImage;

public class Seams {
    double[][] horizontalSeamArray;
    double[][] verticalSeamArray;
    int[] removeHorizontalSeam;
    int[] removeVerticalSeam;

    public Seams(Energy energy, BufferedImage original) {
        drawHorizontalSeamArray(energy);
        drawVerticalSeamArray(energy);
    }
    private void drawHorizontalSeamArray(Energy energy){
        horizontalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int row = 0; row < horizontalSeamArray.length; row++) { //should it be -1?
            for (int col = 0; col < horizontalSeamArray[0].length; col++) {
                double value = 0;
                if (col!= 0){
                    value = energy.getEnergyArray()[row][col-1];
                    if (row != 0)
                    {
                        if (value < energy.getEnergyArray()[row-1][col-1])
                        {
                            value = energy.getEnergyArray()[row-1][col-1];
                        }
                    }
                    if (row != horizontalSeamArray.length)
                    {
                        if (value < energy.getEnergyArray()[row+1][col-1])
                        {
                            value = energy.getEnergyArray()[row+1][col-1];
                        }
                    }
                }
                horizontalSeamArray[row][col] = energy.getEnergyArray()[row][col]+value;
            }
        }
    }
    private void calcRemoveHorizontalSeam(){
        removeHorizontalSeam = new int[horizontalSeamArray[0].length];
        double minEnergy = horizontalSeamArray[0][horizontalSeamArray.length];
        int minIndex = 0;
        for (int i = 0; i < horizontalSeamArray.length; i++) {
            if (horizontalSeamArray[i][horizontalSeamArray.length] < minEnergy)
            {
                minEnergy = horizontalSeamArray[i][horizontalSeamArray.length];
                minIndex = i;
            }
        }
        removeHorizontalSeam[removeHorizontalSeam.length] = minIndex;
        for (int col = horizontalSeamArray[0].length - 1; col < 0; col--) {
            if (minIndex != 0 && minIndex != horizontalSeamArray.length)
            {

            }
        }

    }
    private void drawVerticalSeamArray(Energy energy){
        verticalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];

    }
    private void calcRemoveVerticalSeam(){
        removeVerticalSeam = new int[verticalSeamArray.length];

    }
}
