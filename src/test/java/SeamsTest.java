import imageData.Energy;
import imageData.Seams;
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
                {2.0,5.0,4.0,1.0}};
        Mockito.doReturn(testEnergyArray).when(testEnergy).getEnergyArray();
        this.testSeams = new Seams(testEnergy);
    }

    @Test
    void createVerticalSeamArray() {

        double[][] expectVerticalEnergies = {
                {1.0,4.0,3.0,5.0},
                {4.0,3.0,4.0,9.0},
                {5.0,8.0,7.0,5.0}};

        assertArrayEquals(expectVerticalEnergies, testSeams.getVerticalEnergyArray());
    }

    @Test
    void createHorizontalSeamArray() {
        double[][] expectHorizontalEnergies = {
                {1.0,3.0,2.0},
                {5.0,3.0,7.0},
                {6.0,4.0,7.0},
                {9.0,10.0,5.0}};

        assertArrayEquals(expectHorizontalEnergies,testSeams.getHorizontalEnergyArray());

    }

    @Test
    void calcRemoveSeam() {

        double[][] testRemove ={
                {1.0,3.0,2.0,4.0},
                {5.0,3.0,7.0,5.0},
                {6.0,4.0,7.0,7.0}};
        int[] expectRemove = {0,1,1};

        assertArrayEquals(expectRemove, testSeams.calcRemoveSeam(testRemove));
    }

}