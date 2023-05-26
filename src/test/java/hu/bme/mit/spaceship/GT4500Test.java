package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary = mock(TorpedoStore.class);
  private TorpedoStore secondary = mock(TorpedoStore.class);


  @BeforeEach
  public void init(){
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fire_test1(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fire_test2(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

  @Test
  public void fire_test3(){
    // Arrange
    when(secondary.fire(1)).thenReturn(true);
    when(primary.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fire_test4(){
    // Arrange
    when(secondary.fire(1)).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fire_test5(){
    // Arrange
    when(secondary.fire(1)).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.ALL);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(3)).fire(1);
    verify(secondary, times(1)).fire(1);
  }

  @Test
  public void fire_test6(){
    // Arrange
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
  }

}
