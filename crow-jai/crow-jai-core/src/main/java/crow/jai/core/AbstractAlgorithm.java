package crow.jai.core;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import crow.jai.core.constraint.Constraint;
import crow.jai.core.util.ExcecutionEndListener;
import crow.jai.core.util.AlgorithmEvent;
import crow.jai.core.util.SolutionChangeListener;

/**
 * Abstract class that implements a general methods from {@link Algorithm}.
 *
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.1
 */
public abstract class AbstractAlgorithm implements Algorithm {

    /**
     * List of constraints.
     */
    protected List<Constraint> constraints = new ArrayList<>();
    /**
     * Elapsed time.
     */
    protected long elapsedTime = 0;
    /**
     * List of listeners.
     */
    protected List<EventListener> eventListeners = new ArrayList<>();
    /**
     * To know if the algorithm is currently running.
     */
    protected boolean running = false;

    /**
     * Generic solution.
     */
    protected Solution solution;

    private boolean addAlgorithmListener(EventListener listener) {
        boolean contains = eventListeners.contains(listener);
        if (!contains) {
            eventListeners.add(listener);
        }
        return contains;
    }

    /**
     * Adds new execution end listener.
     *
     * @param listener the execution end listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean addExcecutionEndListener(ExcecutionEndListener listener) {
        return addAlgorithmListener(listener);
    }

    /**
     * Adds new solution change listener.
     *
     * @param listener the solution change listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean addSolutionChangeListener(SolutionChangeListener listener) {
        return addAlgorithmListener(listener);
    }

    /**
     * Removes the specified execution end listener. See {@link List#remove(java.lang.Object)
     * }
     *
     * @param listener the execution end listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean removeExcecutionEndListener(ExcecutionEndListener listener) {
        return eventListeners.remove(listener);
    }

    /**
     * Removes the specified solution change listener. See {@link List#remove(java.lang.Object)
     * }
     *
     * @param listener the solution change listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean removeSolutionChangeListener(SolutionChangeListener listener) {
        return eventListeners.remove(listener);
    }

    @Override
    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    @Override
    public Constraint[] getConstraints() {
        return constraints.toArray(new Constraint[0]);
    }

    @Override
    public long getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public Solution getSolution() {
        return solution;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void removeConstraint(Constraint constraint) {
        constraints.remove(constraint);
    }

    @Override
    public void stop() {
        if (running) {
            running = false;
        }
    }

    @Override
    public boolean testConstraint() {
        int countMandatories = 0;
        int countMandatoriesTrue = 0;
        int countOptionalsTrue = 0;
        for (Constraint constraint : constraints) {
            switch (constraint.getConstraintType()) {
                case MANDATORY:
                    countMandatories++;
                    if (constraint.eval(this)) {
                        countMandatoriesTrue++;
                    }
                    break;
                case OPTIONAL:
                    if (constraint.eval(this)) {
                        countOptionalsTrue++;
                    }
                    break;
            }
        }
        if (countMandatories != countMandatoriesTrue) {
            return false;
        }
        return countMandatories != 0 || countOptionalsTrue != 0;
    }

    /**
     * Used to fire algorithm end execution event.
     */
    protected void fireAlgorithmFinishedEvent() {
        for (EventListener eventListener : eventListeners) {
            if (eventListener instanceof ExcecutionEndListener) {
                ExcecutionEndListener excecutionEndListener = (ExcecutionEndListener) eventListener;
                excecutionEndListener.algorithmFinished(new AlgorithmEvent(
                        this, solution));
            }
        }
    }

    /**
     * Used to fire solution changed event.
     */
    protected void fireBestSolutionUpdatedEvent() {
        fireBestSolutionUpdatedEvent(new AlgorithmEvent(this, solution));
    }

    protected void fireBestSolutionUpdatedEvent(AlgorithmEvent event) {
        for (EventListener eventListener : eventListeners) {
            if (eventListener instanceof SolutionChangeListener) {
                SolutionChangeListener solutionChangeListener = (SolutionChangeListener) eventListener;
                solutionChangeListener.solutionUpdated(event);
            }
        }
    }
}
