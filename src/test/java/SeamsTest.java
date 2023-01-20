import ImageData.Energy;
import ImageData.Seams;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SeamsTest {

    @Test
    void createHorizontalSeamArray() {
        Energy testEnergy = Mockito.mock(Energy.class);
        double[][] testEnergyArray = {
                {1.0,4.0,3.0,5.0},
                {3.0,2.0,1.0,6.0},
                {2.0,5.0,4.0,1.0},
                {4.0,3.0,2.0,1.0}};
        Mockito.doReturn(testEnergyArray).when(testEnergy).getEnergyArray();
        Seams testSeams = new Seams(testEnergy);

        double[][] expectHorizontalSeams = {
                {1.0,4.0,3.0,5.0},
                {4.0,3.0,4.0,9.0},
                {5.0,8.0,7.0,5.0},
                {9.0,8.0,7.0,6.0}};


        assertArrayEquals(expectHorizontalSeams,testSeams.getHorizontalSeamArray());
    }

    @Test
    void createVerticalSeamArray() {
        Energy testEnergy = Mockito.mock(Energy.class);
        double[][] testEnergyArray = {
                {1.0,4.0,3.0,5.0},
                {3.0,2.0,1.0,6.0},
                {2.0,5.0,4.0,1.0},
                {4.0,3.0,2.0,1.0}};
        Mockito.doReturn(testEnergyArray).when(testEnergy).getEnergyArray();
        Seams testSeams = new Seams(testEnergy);

        double[][] expectVerticalSeams = {
                {1.0,3.0,2.0,4.0},
                {5.0,3.0,7.0,5.0},
                {6.0,4.0,7.0,7.0},
                {9.0,10.0,5.0,8.0}};


        assertArrayEquals(expectVerticalSeams,testSeams.getVerticalSeamArray());

    }

    @Test
    void calcRemoveSeam() {

    }

}