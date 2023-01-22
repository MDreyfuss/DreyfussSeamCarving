package imageEnergiesAndSeams;

public class Seams {
    double[][] horizontalEnergyArray;
    double[][] verticalEnergyArray;
    int[] removeSeam;

    public Seams(Energy energy) {
        createVerticalEnergyArray(energy);
        createHorizontalEnergyArray(energy);
    }
    private void createVerticalEnergyArray(Energy energy){
        verticalEnergyArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int col = 0; col < verticalEnergyArray.length; col++) {
            for (int row = 0; row < verticalEnergyArray[0].length; row++) {
                double value = 0;
                if (col!= 0){
                    value = verticalEnergyArray[col-1][row];
                    if (row != 0)
                    {
                        if (value > verticalEnergyArray[col-1][row-1])
                        {
                            value = verticalEnergyArray[col-1][row-1];
                        }
                    }
                    if (row != verticalEnergyArray[0].length -1)
                    {
                        if (value > verticalEnergyArray[col-1][row+1])
                        {
                            value = verticalEnergyArray[col-1][row+1];
                        }
                    }
                }
                verticalEnergyArray[col][row] = energy.getEnergyArray()[col][row] + value;
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
    private void createHorizontalEnergyArray(Energy energy){
        horizontalEnergyArray = new double[energy.getEnergyArray()[0].length][energy.getEnergyArray().length];
        for (int col = 0; col < energy.getEnergyArray()[0].length; col++) {
            for (int row = 0; row < energy.getEnergyArray().length; row++) {
                double value = 0;
                if (col!= 0){
                    value = horizontalEnergyArray[col-1][row];
                    if (row != 0)
                    {
                        if (value > horizontalEnergyArray[col-1][row-1])
                        {
                            value = horizontalEnergyArray[col-1][row-1];
                        }
                    }
                    if (row != horizontalEnergyArray[0].length -1)
                    {
                        if (value > horizontalEnergyArray[col-1][row+1])
                        {
                            value = horizontalEnergyArray[col-1][row+1];
                        }
                    }
                }
                horizontalEnergyArray[col][row] = energy.getEnergyArray()[row][col]+value;
            }
        }
    }

    public double[][] getHorizontalEnergyArray() {
        return horizontalEnergyArray;
    }

    public double[][] getVerticalEnergyArray() {
        return verticalEnergyArray;
    }
}
