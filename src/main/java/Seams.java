import java.awt.image.BufferedImage;

public class Seams {
    double[][] horizontalSeamArray;
    double[][] verticalSeamArray;
    int[] removeSeam;

    public Seams(Energy energy, BufferedImage original) {
        createHorizontalSeamArray(energy);
        createVerticalSeamArray(energy);
    }
    private void createHorizontalSeamArray(Energy energy){
        horizontalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int col = 0; col < horizontalSeamArray.length; col++) {
            for (int row = 0; row < horizontalSeamArray[0].length; row++) {
                double value = 0;
                if (col!= 0){
                    value = energy.getEnergyArray()[col-1][row];
                    if (row != 0)
                    {
                        if (value > energy.getEnergyArray()[col-1][row-1])
                        {
                            value = energy.getEnergyArray()[col-1][row-1];
                        }
                    }
                    if (row != horizontalSeamArray[0].length -1)
                    {
                        if (value > energy.getEnergyArray()[col-1][row+1])
                        {
                            value = energy.getEnergyArray()[col-1][row+1];
                        }
                    }
                }
                horizontalSeamArray[col][row] = energy.getEnergyArray()[col][row]+value;
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
        for (int row = 0; row < verticalSeamArray.length; row++) {
            for (int col = 0; col < verticalSeamArray[0].length; col++) {
                double value = 0;
                if (row!= 0){
                    value = energy.getEnergyArray()[col][row-1];
                    if (col != 0)
                    {
                        if (value > energy.getEnergyArray()[col-1][row-1])
                        {
                            value = energy.getEnergyArray()[col-1][row-1];
                        }
                    }
                    if (col != verticalSeamArray[0].length -1)
                    {
                        if (value > energy.getEnergyArray()[col+1][row-1])
                        {
                            value = energy.getEnergyArray()[col+1][row-1];
                        }
                    }
                }
                verticalSeamArray[row][col] = energy.getEnergyArray()[col][row]+value;
            }
        }
    }




  /*  //IS THIS VERTICAL?
    private void createHorizontalSeamArray(Energy energy){
        horizontalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int row = 0; row < horizontalSeamArray.length; row++) {
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
    }*/




/*
    public int[] calcRemoveHorizontalSeam(){
        removeHorizontalSeam = new int[horizontalSeamArray.length];
        double minEnergy = horizontalSeamArray[horizontalSeamArray.length-1][0];
        int minIndex = 0;
        for (int i = 0; i < horizontalSeamArray[0].length; i++) {
            if (horizontalSeamArray[horizontalSeamArray.length-1][i] < minEnergy)
            {
                minEnergy = horizontalSeamArray[horizontalSeamArray.length-1][i];
                minIndex = i;
            }
        }
        removeHorizontalSeam[removeHorizontalSeam.length-1] = minIndex;
        for (int col = horizontalSeamArray.length - 2; col >= 0; col--) {
            if (minIndex == 0)
            {
                if(horizontalSeamArray[col][minIndex]>horizontalSeamArray[col][minIndex+1])
                {
                    minIndex += 1;
                }
            }
            else if (minIndex == horizontalSeamArray.length)
            {
                if(horizontalSeamArray[col][minIndex]>horizontalSeamArray[col][minIndex - 1])
                {
                    minIndex -= 1;
                }
            }
            else
            {
                if(horizontalSeamArray[col][minIndex]<horizontalSeamArray[col][minIndex - 1])
                {
                    if(horizontalSeamArray[col][minIndex+1]<horizontalSeamArray[col][minIndex])
                    {
                        minIndex += 1;
                    }
                }
                else if (horizontalSeamArray[col+1][minIndex]<horizontalSeamArray[col][minIndex - 1])
                {
                    minIndex += 1;
                }
                else {
                    minIndex -= 1;
                }
            }
            removeHorizontalSeam[col] = minIndex;
        }
        return removeHorizontalSeam;
    }*/




    /* almost worked

    private void createHorizontalSeamArray(Energy energy){
        horizontalSeamArray = new double[energy.getEnergyArray().length][energy.getEnergyArray()[0].length];
        for (int col = 0; col < horizontalSeamArray.length; col++) { //should it be -1?
            for (int row = 0; row < horizontalSeamArray[0].length; row++) {
                double value = 0;
                if (row!= 0){
                    value = energy.getEnergyArray()[col][row-1];
                    if (col != 0)
                    {
                        if (value > energy.getEnergyArray()[col-1][row-1])
                        {
                            value = energy.getEnergyArray()[col-1][row-1];
                        }
                    }
                    if (col != horizontalSeamArray.length -1)
                    {
                        if (value > energy.getEnergyArray()[col+1][row-1])
                        {
                            value = energy.getEnergyArray()[col+1][row-1];
                        }
                    }
                }
                horizontalSeamArray[col][row] = energy.getEnergyArray()[col][row]+value;
            }
        }
    }


    removeHorizontalSeam[removeHorizontalSeam.length-1] = minIndex;
        for (int row = horizontalSeamArray[0].length - 2; row >= 0; row--) {
            if (minIndex == 0)
            {
                if(horizontalSeamArray[minIndex][row]>horizontalSeamArray[minIndex+1][row])
                {
                    minIndex += 1;
                }
            }
            else if (minIndex == horizontalSeamArray.length)
            {
                if(horizontalSeamArray[minIndex][row]>horizontalSeamArray[minIndex - 1][row])
                {
                    minIndex -= 1;
                }
            }
            else
            {
                if(horizontalSeamArray[minIndex][row]<horizontalSeamArray[minIndex - 1][row])
                {
                    if(horizontalSeamArray[minIndex+1][row]<horizontalSeamArray[minIndex][row])
                    {
                        minIndex += 1;
                    }
                }
                else if (horizontalSeamArray[minIndex][row+1]<horizontalSeamArray[minIndex - 1][row])
                {
                    minIndex += 1;
                }
                else {
                    minIndex -= 1;
                }
            }
            removeHorizontalSeam[row] = minIndex;
        }
        return removeHorizontalSeam;
    }
     */
}
