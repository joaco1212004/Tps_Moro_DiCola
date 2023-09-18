package queue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class QueueTest {
	
	public static String firstAddedObject = "First";
	public static String secondAddedObject = "Second";
	public static String addedObject = "Something"; 	
	
  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( new Queue().add( addedObject ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( addedObject, new Queue().add( addedObject ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = new Queue().add( addedObject );
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = new Queue();
    queue.add( addedObject );
    
    assertEquals( addedObject, queue.take());
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = new Queue();

    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = new Queue();

    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = new Queue();
    queue.add( addedObject );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add( firstAddedObject ).add( secondAddedObject ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue(); 
    
    throwableExceptions(queue::take);
  }
 
  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = new Queue();
    queue.add( addedObject );
    queue.take();
    
    throwableExceptions(queue::take);
  }
 
  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    
    throwableExceptions(queue::head);
  }
   
  public void throwableExceptions(Executable function) {
  	Throwable thrown = assertThrows(Throwable.class, function);
      assertEquals(EmptyLocker.QueueIsEmpty, thrown.getMessage());
  }
}