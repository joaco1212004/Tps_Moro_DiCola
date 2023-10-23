package nemoDoD;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NemoTest {

	@Test public void test00InitialPosition() {
		checkPosition(new Nemo( new North(), new Point(0, 0 , 0) ), 0, 0, 0);
	}
	
	@Test public void test01InitialDirectionAimingNorth() {
		assertEquals("North", new Nemo( new North(), new Point(0, 0 , 0) ).getDirection());
	}
	
	@Test public void test02InitialDirectionAimingSouth() {
		assertEquals("South", new Nemo( new South(), new Point(0, 0 , 0) ).getDirection());
	}
	
	@Test public void test03InitialDirectionAimingWest() {
		assertEquals("West", new Nemo( new West(), new Point(0, 0 , 0) ).getDirection());
	}
	
	@Test public void test04InitialDirectionAimingEast() {
		assertEquals("East", new Nemo( new East(), new Point(0, 0 , 0) ).getDirection());
	}
	
	@Test public void test05ReceiveAEmptyCommandStringAndDoNothing() {
		checkPosition(createSubmarineAndGiveOrder(""), 0, 0, 0);
	}
	
	@Test public void test06GoDownFromTheSurface() {
		checkPosition(createSubmarineAndGiveOrder('d'), 0, 0, -1);
	}
	
	@Test public void test07GoUpWhileInTheSurface() {
		checkPosition(createSubmarineAndGiveOrder('u'), 0, 0, 0);
	}
	
	@Test public void test08UndoDescendingMovement() {
		checkPosition(createSubmarineAndGiveOrder("du"), 0, 0, 0); 
	}
	
	@Test public void test09GoDownTwice() {
		checkPosition(createSubmarineAndGiveOrder("dd"), 0, 0, -2);
	}
	
	@Test public void test10GoDownTwiceAndGoingUpOnce() {
		checkPosition(createSubmarineAndGiveOrder("ddu"), 0, 0, -1);
	}
	
	@Test public void test11UndoTwoDescendingMoments() {
		checkPosition(createSubmarineAndGiveOrder("dduu"), 0, 0, 0);
	}
	
	@Test public void test12GoDownTwiceAndNotTrespassingTheZLimit() {
		checkPosition(createSubmarineAndGiveOrder("dduuu"), 0, 0, 0);
	}
	
	@Test public void test13Rotate90DegreesToTheLeft() {
		assertEquals("West", createSubmarineAndGiveOrder('l').getDirection());
	}
	
	@Test public void test14Rotate180DegreesToTheLeft() {
		assertEquals("South", createSubmarineAndGiveOrder("ll").getDirection());
	}
	
	@Test public void test15Rotate270DegreesToTheLeft() {
		assertEquals("East", createSubmarineAndGiveOrder("lll").getDirection());
	}
	
	@Test public void test16Rotate90DegreesToTheRight() {
		assertEquals("East", createSubmarineAndGiveOrder('r').getDirection());
	}
	
	@Test public void test17Rotate180DegreesToTheRight() {
		assertEquals("South", createSubmarineAndGiveOrder("rr").getDirection());
	}
	
	@Test public void test18Rotate270DegreesToTheRight() {
		assertEquals("West", createSubmarineAndGiveOrder("rrr").getDirection());
	}
	
	@Test public void test19UndoRightRotation() {
		assertEquals("North", createSubmarineAndGiveOrder("rl").getDirection());
	}
	
	@Test public void test20UndoLeftRotation() {
		assertEquals("North", createSubmarineAndGiveOrder("lr").getDirection());
	}
	
	@Test public void test21GoStraightForward() {
		checkPosition(createSubmarineAndGiveOrder('f'), 0, 1, 0);
	}
	
	@Test public void test22AimEastAndGoForward() {
		checkPosition(createSubmarineAndGiveOrder("rf"), 1, 0, 0);
	}
	
	@Test public void test23AimSouthAndGoForward() {
		checkPosition(createSubmarineAndGiveOrder("rrf"), 0, -1, 0);
	}
	
	@Test public void test24AimWestAndGoForward() {
		checkPosition(createSubmarineAndGiveOrder("lf"), -1, 0, 0);
	}
	
	@Test public void test25FreeCapsule() {
		assertTrue(createSubmarineAndGiveOrder('m').capsule);
	}
	
	@Test public void test26DescendOnceAndFreeCapsule() {
		assertTrue(createSubmarineAndGiveOrder("dm").capsule);
	}
	
	@Test public void test27DescendTwiceAscendAndEjectCapsule() {
		createSubmarineGiveOrderCheckPositionAndEjectCapsule(0, 0, -1, "ddum");
	}
	
	@Test public void test28AssertingLotsOfOrdersGiven() {
		createSubmarineGiveOrderCheckPositionAndEjectCapsule(-2, 1, -1, "lffruudfm");
	}
	
	@Test public void test29CannotFreeCapsule() {
		assertEquals(Deep.MESSAGE_ERROR_CAPSULE, assertThrows(Exception.class, () -> createSubmarineAndGiveOrder("ddm")).getMessage());
	}
	
	private void checkPosition(Nemo submarine, int x, int y, int z) {
		assertEquals(x, submarine.getX());
		assertEquals(y, submarine.getY());
		assertEquals(z, submarine.getZ());
	}
	
	private Nemo createSubmarineAndGiveOrder(String order) {
		Nemo submarine = new Nemo( new North(), new Point(0, 0 , 0) );
		submarine.move(order);
		return submarine;
	}
	
	private Nemo createSubmarineAndGiveOrder(char order) {
		Nemo submarine = new Nemo( new North(), new Point(0, 0 , 0) );
		submarine.move(order);
		return submarine;
	}
	
	private void createSubmarineGiveOrderCheckPositionAndEjectCapsule(int x, int y, int z, String order) {
		Nemo submarine = createSubmarineAndGiveOrder(order);
		checkPosition(submarine, x, y, z);
		assertTrue(submarine.capsule);
	}
}