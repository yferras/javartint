/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crow.jai.core;

import crow.jai.core.constraint.Constraint;
import crow.jai.core.constraint.ConstraintType;
import crow.jai.core.constraint.MaxIterationsConstraint;
import crow.jai.core.constraint.MinErrorConstraint;
import crow.jai.core.util.AlgorithmEvent;
import crow.jai.core.util.ExcecutionEndListener;
import crow.jai.core.util.SolutionChangeListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yey
 */
public class AbstractAlgorithmIT {

    public AbstractAlgorithmIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addExcecutionEndListener method, of class AbstractAlgorithm.
     */
    @Test
    public void testAddExcecutionEndListener() {
        System.out.println("addExcecutionEndListener");
        ExcecutionEndListener listener = new ExcecutionEndListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean expResult = false;
        boolean result = instance.addExcecutionEndListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of addSolutionChangeListener method, of class AbstractAlgorithm.
     */
    @Test
    public void testAddSolutionChangeListener() {
        System.out.println("addSolutionChangeListener");
        SolutionChangeListener listener = new SolutionChangeListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean expResult = false;
        boolean result = instance.addSolutionChangeListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of removeExcecutionEndListener method, of class AbstractAlgorithm.
     */
    @Test
    public void testRemoveExcecutionEndListener() {
        System.out.println("removeExcecutionEndListener");
        ExcecutionEndListener listener = new ExcecutionEndListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean expResult = false;
        boolean result = instance.removeExcecutionEndListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of removeSolutionChangeListener method, of class AbstractAlgorithm.
     */
    @Test
    public void testRemoveSolutionChangeListener() {
        System.out.println("removeSolutionChangeListener");
        SolutionChangeListener listener = new SolutionChangeListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean expResult = false;
        boolean result = instance.removeSolutionChangeListener(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getConstraints method, of class AbstractAlgorithm.
     */
    @Test
    public void testGetConstraints() {
        System.out.println("getConstraints");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Constraint[] expResult = 
                new Constraint[]{
                    new MaxIterationsConstraint(ConstraintType.MANDATORY, 100L),
                    new MinErrorConstraint(ConstraintType.MANDATORY, 0.0)
                };
        instance.addConstraint(expResult[0]);        
        instance.addConstraint(expResult[1]);
        Constraint[] result = instance.getConstraints();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getElapsedTime method, of class AbstractAlgorithm.
     */
    @Test
    public void testGetElapsedTime() {
        System.out.println("getElapsedTime");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Thread thread = new Thread(instance);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractAlgorithmIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.stop();
        long result = instance.getElapsedTime();
        assertTrue(result != 0L);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getSolution method, of class AbstractAlgorithm.
     */
    @Test
    public void testGetSolution() {
        System.out.println("getSolution");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Solution result = instance.getSolution();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of isRunning method, of class AbstractAlgorithm.
     */
    @Test
    public void testIsRunning() {
        System.out.println("isRunning");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Thread thread = new Thread(instance);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractAlgorithmIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean expResult = true;
        boolean result = instance.isRunning();
        assertEquals(expResult, result);
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of removeConstraint method, of class AbstractAlgorithm.
     */
    @Test
    public void testRemoveConstraint() {
        System.out.println("removeConstraint");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Constraint[] constraints = 
                new Constraint[]{
                    new MaxIterationsConstraint(ConstraintType.MANDATORY, 100L),
                    new MinErrorConstraint(ConstraintType.MANDATORY, 0.0)
                };
        Constraint constraint = constraints[0];
        instance.addConstraint(constraints[0]);        
        instance.addConstraint(constraints[1]);
        instance.removeConstraint(constraint);
        assertEquals(1, instance.getConstraints().length);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class AbstractAlgorithm.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Thread thread = new Thread(instance);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractAlgorithmIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.stop();
        boolean expResult = false;
        boolean result = instance.isRunning();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of fireAlgorithmFinishedEvent method, of class AbstractAlgorithm.
     */
    @Test
    public void testFireAlgorithmFinishedEvent() {
        System.out.println("fireAlgorithmFinishedEvent");
        AbstractAlgorithm instance = new AlgorithmImpl();
        instance.addExcecutionEndListener(new ExcecutionEndListener() {

            @Override
            public void algorithmFinished(AlgorithmEvent event) {
                assertEquals(true, true);
            }
        });
        instance.fireAlgorithmFinishedEvent();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of fireBestSolutionUpdatedEvent method, of class AbstractAlgorithm.
     */
    @Test
    public void testFireBestSolutionUpdatedEvent_0args() {
        System.out.println("fireBestSolutionUpdatedEvent");
        AbstractAlgorithm instance = new AlgorithmImpl();
        instance.addSolutionChangeListener(new SolutionChangeListener() {

            @Override
            public void solutionUpdated(AlgorithmEvent event) {
                assertEquals(true, true);
            }
        });
        instance.fireBestSolutionUpdatedEvent();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    static public class AlgorithmImpl extends AbstractAlgorithm implements ErrorBasedAlgorithm, IterativeAlgorithm {

        private double error;
        private long iterations = 0L;

        public AlgorithmImpl() {
            solution = new SolutionImpl(0);
        }

        @Override
        public void run() {
            elapsedTime = System.currentTimeMillis();
            running = true;
            while (running && !testConstraint()) {
                iterations++;
                int newValue = new Random().nextInt(200);
                if (newValue > ((SolutionImpl) solution).getData()) {
                    solution = new SolutionImpl(newValue);
                    fireBestSolutionUpdatedEvent();
                }
                error = Math.abs(100 - ((SolutionImpl) solution).getData());
            }
            running = false;
            elapsedTime -= System.currentTimeMillis();
            fireAlgorithmFinishedEvent();
        }

        @Override
        public double getCurrentError() {
            return error;
        }

        @Override
        public long getIterations() {
            return iterations;
        }
    }

    static public class SolutionImpl implements Solution {

        private int data;

        public SolutionImpl(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

    }

    static public class ExcecutionEndListenerImpl implements ExcecutionEndListener {

        @Override
        public void algorithmFinished(AlgorithmEvent event) {
            System.out.println("Algorithm Finished");
        }

    }

    static public class SolutionChangeListenerImpl implements SolutionChangeListener {

        @Override
        public void solutionUpdated(AlgorithmEvent event) {
            System.out.println("Solution Updated");
        }

    }
}
