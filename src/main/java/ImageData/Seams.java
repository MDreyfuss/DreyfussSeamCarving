package ImageData;

public class Seams {
    double[][] horizontalSeamArray;
    double[][] verticalSeamArray;
    int[] removeSeam;

    public Seams(Energy energy) {
        createHorizontalSeamArray(energy);
        createVerticalSeamArray(energy);
    }
    private void createHorizontalSeamArray(Energy energy){
        horizontalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int col = 0; col < horizontalSeamArray.length; col++) {
            for (int row = 0; row < horizontalSeamArray[0].length; row++) {
                double value = 0;
                if (col!= 0){
                    value = horizontalSeamArray[col-1][row];
                    if (row != 0)
                    {
                        if (value > horizontalSeamArray[col-1][row-1])
                        {
                            value = horizontalSeamArray[col-1][row-1];
                        }
                    }
                    if (row != horizontalSeamArray[0].length -1)
                    {
                        if (value > horizontalSeamArray[col-1][row+1])
                        {
                            value = horizontalSeamArray[col-1][row+1];
                        }
                    }
                }
                horizontalSeamArray[col][row] = energy.getEnergyArray()[col][row] + value;
            }
        }
    }

    public int[] calcRemoveSeam(double[][] seamArray){
        removeSeam = new int[seamArray.length];
        double minEnergy = seamArray[seamArray.length-1][0];
        int minIndex = 0;
        for (int i = 0; i < seamArray[0].length; i++) {
            if (seamArray[seamArray.length-1][i] < minEnergy)
            {
                minEnergy = seamArray[seamArray.length-1][i];
                minIndex = i;
            }
        }
        removeSeam[removeSeam.length-1] = minIndex;
        for (int col = seamArray.length - 2; col >= 0; col--) {
            if (minIndex == 0)
            {
                if(seamArray[col][minIndex]>seamArray[col][minIndex+1])
                {
                    minIndex += 1;
                }
            }
            else if (minIndex == seamArray[0].length)
            {
                if(seamArray[col][minIndex]>seamArray[col][minIndex - 1])
                {
                    minIndex -= 1;
                }
            }
            else
            {
                if(seamArray[col][minIndex]<seamArray[col][minIndex - 1])
                {
                    if(seamArray[col][minIndex+1]<seamArray[col][minIndex])
                    {
                        minIndex += 1;
                    }
                }
                else if (seamArray[col][minIndex+1]<seamArray[col][minIndex - 1])
                {
                    minIndex += 1;
                }
                else {
                    minIndex -= 1;
                }
            }
            removeSeam[col] = minIndex;
        }
        return removeSeam;
    }
    private void createVerticalSeamArray(Energy energy){
        verticalSeamArray = new double[energy.getEnergyArray()[0].length][energy.getEnergyArray().length];
        for (int col = 0; col < energy.getEnergyArray()[0].length; col++) {
            for (int row = 0; row < energy.getEnergyArray().length; row++) {
                double value = 0;
                if (col!= 0){
                    value = verticalSeamArray[col-1][row];
                    if (row != 0)
                    {
                        if (value > verticalSeamArray[col-1][row-1])
                        {
                            value = verticalSeamArray[col-1][row-1];
                        }
                    }
                    if (row != verticalSeamArray[0].length -1)
                    {
                        if (value > verticalSeamArray[col-1][row+1])
                        {
                            value = verticalSeamArray[col-1][row+1];
                        }
                    }
                }
                verticalSeamArray[col][row] = energy.getEnergyArray()[row][col]+value;
            }
        }
    }

    public double[][] getHorizontalSeamArray() {
        return horizontalSeamArray;
    }

    public double[][] getVerticalSeamArray() {
        return verticalSeamArray;
    }
}
