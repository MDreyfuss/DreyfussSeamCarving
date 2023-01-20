import ImageData.Energy;
import ImageData.Seams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SeamsTest {
    Energy testEnergy;
    Seams testSeams;

    @BeforeEach
    void Setup(){
        this.testEnergy= Mockito.mock(Energy.class);
        double[][] testEnergyArray = {
                {1.0,4.0,3.0,5.0},
                {3.0,2.0,1.0,6.0},
                {2.0,5.0,4.0,1.0},
                {4.0,3.0,2.0,1.0}};
        Mockito.doReturn(testEnergyArray).when(testEnergy).getEnergyArray();
        this.testSeams = new Seams(testEnergy);
    }

    @Test
    void createHorizontalSeamArray() {


        double[][] expectHorizontalSeams = {
                {1.0,4.0,3.0,5.0},
                {4.0,3.0,4.0,9.0},
                {5.0,8.0,7.0,5.0},
                {9.0,8.0,7.0,6.0}};


        assertArrayEquals(expectHorizontalSeams, testSeams.getHorizontalSeamArray());
    }

    @Test
    void createVerticalSeamArray() {
        double[][] expectVerticalSeams = {
                {1.0,3.0,2.0,4.0},
                {5.0,3.0,7.0,5.0},
                {6.0,4.0,7.0,7.0},
                {9.0,10.0,5.0,8.0}};


        assertArrayEquals(expectVerticalSeams,testSeams.getVerticalSeamArray());

    }

    @Test
    void calcRemoveSeam() {

        double[][] testRemove ={
                {1.0,3.0,2.0,4.0},
                {5.0,3.0,7.0,5.0},
                {6.0,4.0,7.0,7.0},
                {9.0,10.0,5.0,8.0}};
        int[] expectRemove = {0,1,1,2};

        assertArrayEquals(expectRemove, testSeams.calcRemoveSeam(testRemove));
    }

}