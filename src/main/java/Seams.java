import java.awt.image.BufferedImage;

public class Seams {
    double[][] horizontalSeamArray;
    double[][] verticalSeamArray;
    int[] removeHorizontalSeam;
    int[] removeVerticalSeam;

    public Seams(Energy energy, BufferedImage original) {
        createHorizontalSeamArray(energy);
        createVerticalSeamArray(energy);
    }
    private void createHorizontalSeamArray(Energy energy){
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
                    if (row != horizontalSeamArray.length -1)
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
        for (int col = horizontalSeamArray[0].length - 1; col > 0; col--) {
            if (minIndex == 0)
            {
                if(horizontalSeamArray[minIndex][col]>horizontalSeamArray[minIndex+1][col])
                {
                    minIndex += 1;
                }
            }
            else if (minIndex == horizontalSeamArray.length)
            {
                if(horizontalSeamArray[minIndex][col]>horizontalSeamArray[minIndex - 1][col])
                {
                    minIndex -= 1;
                }
            }
            else
            {
                if(horizontalSeamArray[minIndex][col]<horizontalSeamArray[minIndex - 1][col])
                {
                    if(horizontalSeamArray[minIndex+1][col]<horizontalSeamArray[minIndex][col])
                    {
                        minIndex += 1;
                    }
                }
                else if (horizontalSeamArray[minIndex][col+1]<horizontalSeamArray[minIndex - 1][col])
                {
                    minIndex += 1;
                }
                else {
                    minIndex -= 1;
                }
            }
            removeHorizontalSeam[col] = minIndex;
        }

    }
    private void createVerticalSeamArray(Energy energy){
        verticalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];

    }
    private void calcRemoveVerticalSeam(){
        removeVerticalSeam = new int[verticalSeamArray.length];

    }
}
