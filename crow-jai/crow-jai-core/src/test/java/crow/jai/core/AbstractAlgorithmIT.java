package crow.jai.core;

import crow.jai.core.constraint.Constraint;
import crow.jai.core.constraint.ConstraintType;
import crow.jai.core.constraint.MaxIterationsConstraint;
import crow.jai.core.constraint.MinErrorConstraint;
import crow.jai.core.util.AlgorithmEvent;
import crow.jai.core.util.ExecutionEndListener;
import crow.jai.core.util.SolutionChangeListener;
import org.junit.*;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * @author Eng. Ferrás Cecilio, Yeinier
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
     * Test of addExecutionEndListener method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testAddExecutionEndListener() {
        System.out.println("addExecutionEndListener");
        ExecutionEndListener listener = new ExecutionEndListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean result = instance.addExecutionEndListener(listener);
        assertEquals(false, result);
    }

    /**
     * Test of addSolutionChangeListener method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testAddSolutionChangeListener() {
        System.out.println("addSolutionChangeListener");
        SolutionChangeListener listener = new SolutionChangeListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean result = instance.addSolutionChangeListener(listener);
        assertEquals(false, result);
    }

    /**
     * Test of removeExecutionEndListener method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testRemoveExecutionEndListener() {
        System.out.println("removeExecutionEndListener");
        ExecutionEndListener listener = new ExecutionEndListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean result = instance.removeExecutionEndListener(listener);
        assertEquals(false, result);
    }

    /**
     * Test of removeSolutionChangeListener method, of class AbstractAlgorithm.
     */
    @Test
    @SuppressWarnings("rawtypes")
    public void testRemoveSolutionChangeListener() {
        System.out.println("removeSolutionChangeListener");
        SolutionChangeListener listener = new SolutionChangeListenerImpl();
        AbstractAlgorithm instance = new AlgorithmImpl();
        boolean result = instance.removeSolutionChangeListener(listener);
        assertEquals(false, result);
    }

    /**
     * Test of getConstraints method, of class AbstractAlgorithm.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testGetConstraints() {
        System.out.println("getConstraints");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Constraint[] expResult
                = new Constraint[]{
                new MaxIterationsConstraint(ConstraintType.MANDATORY, 100L),
                new MinErrorConstraint(ConstraintType.MANDATORY, 0.0)
        };
        instance.addConstraint(expResult[0]);
        instance.addConstraint(expResult[1]);
        Constraint[] result = instance.getConstraints();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getElapsedTime method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
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
    }

    /**
     * Test of getSolution method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetSolution() {
        System.out.println("getSolution");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Solution result = instance.getSolution();
        assertNotNull(result);
    }

    /**
     * Test of isRunning method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
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
        boolean result = instance.isRunning();
        assertEquals(true, result);
        instance.stop();
    }

    /**
     * Test of removeConstraint method, of class AbstractAlgorithm.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testRemoveConstraint() {
        System.out.println("removeConstraint");
        AbstractAlgorithm instance = new AlgorithmImpl();
        Constraint[] constraints
                = new Constraint[]{
                new MaxIterationsConstraint(ConstraintType.MANDATORY, 100L),
                new MinErrorConstraint(ConstraintType.MANDATORY, 0.0)
        };
        Constraint constraint = constraints[0];
        instance.addConstraint(constraints[0]);
        instance.addConstraint(constraints[1]);
        instance.removeConstraint(constraint);
        assertEquals(1, instance.getConstraints().length);
    }

    /**
     * Test of stop method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
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
        boolean result = instance.isRunning();
        assertEquals(false, result);
    }

    /**
     * Test of fireAlgorithmFinishedEvent method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testFireAlgorithmFinishedEvent() {
        System.out.println("fireAlgorithmFinishedEvent");
        AbstractAlgorithm instance = new AlgorithmImpl();
        instance.addExecutionEndListener(new ExecutionEndListener() {

            @Override
            public void algorithmFinished(AlgorithmEvent event) {
                assertEquals(true, true);
            }
        });
        instance.fireAlgorithmFinishedEvent();
    }

    /**
     * Test of fireBestSolutionUpdatedEvent method, of class AbstractAlgorithm.
     */
    @SuppressWarnings("rawtypes")
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
    }

    static public class AlgorithmImpl
            extends AbstractAlgorithm<SolutionImpl>
            implements ErrorBasedAlgorithm<SolutionImpl>, IterativeAlgorithm<SolutionImpl> {

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
                if (newValue > solution.getData()) {
                    solution = new SolutionImpl(newValue);
                    fireBestSolutionUpdatedEvent();
                }
                error = Math.abs(100 - solution.getData());
            }
            running = false;
            elapsedTime -= System.currentTimeMillis();
            fireAlgorithmFinishedEvent();
        }

        @Override
        public Double getCurrentError() {
            return error;
        }

        @Override
        public Long getIterations() {
            return iterations;
        }

        @Override
        public Double getThreshold() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    static public class ExecutionEndListenerImpl implements ExecutionEndListener {

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
