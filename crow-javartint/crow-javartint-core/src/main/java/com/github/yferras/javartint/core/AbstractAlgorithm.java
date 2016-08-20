package com.github.yferras.javartint.core;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/*
 * #%L
 * Crow JavArtInt Core
 * %%
 * Copyright (C) 2014 - 2015 Eng. Ferrás Cecilio, Yeinier
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.yferras.javartint.core.constraint.Constraint;
import com.github.yferras.javartint.core.util.AlgorithmEvent;
import com.github.yferras.javartint.core.util.ExecutionEndListener;
import com.github.yferras.javartint.core.util.SolutionChangeListener;

/**
 * Abstract class that implements a general methods from {@link com.github.yferras.javartint.core.Algorithm}.
 *
 * @param <S> Any class derived from {@link com.github.yferras.javartint.core.Solution} interface.
 * @author Eng. Ferrás Cecilio, Yeinier
 * @version 0.0.2
 */
public abstract class AbstractAlgorithm<S extends Solution> implements Algorithm<S> {

    private final List<Constraint<? extends Algorithm<? extends Solution>>> constraints = new ArrayList<>();

    private final List<EventListener> eventListeners = new ArrayList<>();

    private long startTime = 0;
    private long elapsedTime = 0;

    private boolean running = false;

    private S solution;

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
    public boolean addExecutionEndListener(ExecutionEndListener listener) {
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
     * Removes the specified execution end listener. See {@link java.util.List#remove(java.lang.Object)}
     *
     * @param listener the execution end listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean removeExecutionEndListener(ExecutionEndListener listener) {
        return eventListeners.remove(listener);
    }

    /**
     * Removes the specified solution change listener. See {@link java.util.List#remove(java.lang.Object)}
     *
     * @param listener the solution change listener instance.
     * @return Returns {@code true} if this instance is already contained,
     * {@code false} otherwise.
     */
    public boolean removeSolutionChangeListener(SolutionChangeListener listener) {
        return eventListeners.remove(listener);
    }

    /** {@inheritDoc} */
    @Override
    public void addConstraint(Constraint<? extends Algorithm<? extends Solution>> constraint) {
        constraints.add(constraint);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Constraint<Algorithm<Solution>>[] getConstraints() {
        return constraints.toArray(new Constraint[constraints.size()]);
    }

    /** {@inheritDoc} */
    @Override
    public Long getElapsedTime() {
        if (isRunning()) {
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        return elapsedTime;
    }

    /**
     * This method sets the current time and sets true to running attribute.
     * Should be invoked at the beginning of run method implementation
     */
    protected void beginAlgorithm() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    /** {@inheritDoc} */
    @Override
    public S getSolution() {
        return solution;
    }

    /**
     * Sets the solution.
     *
     * @param solution new solution.
     */
    protected void setSolution(S solution) {
        this.solution = solution;
        fireBestSolutionUpdatedEvent();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isRunning() {
        return running;
    }

    /** {@inheritDoc} */
    @Override
    public void removeConstraint(Constraint<? extends Algorithm<? extends Solution>> constraint) {
        constraints.remove(constraint);
    }

    /** {@inheritDoc} */
    @Override
    public void stop() {
        if (running) {
            running = false;
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean testConstraint() {
        int countMandatory = 0;
        int countMandatoryTrue = 0;
        int countOptionalsTrue = 0;
        for (Constraint constraint : constraints) {
            switch (constraint.getConstraintType()) {
                case MANDATORY:
                    countMandatory++;
                    if (constraint.evaluate(this)) {
                        countMandatoryTrue++;
                    }
                    break;
                case OPTIONAL:
                    if (constraint.evaluate(this)) {
                        countOptionalsTrue++;
                    }
                    break;
                default:
                    break;
            }
        }
        return countMandatory == countMandatoryTrue && (countMandatory != 0 || countOptionalsTrue != 0);
    }

    /**
     * Used to fire algorithm end execution event. This method should be invoked an the
     * end of {@link #run()} implementation
     */
    protected void fireAlgorithmFinishedEvent() {
        for (EventListener eventListener : eventListeners) {
            if (eventListener instanceof ExecutionEndListener) {
                ExecutionEndListener executionEndListener = (ExecutionEndListener) eventListener;
                executionEndListener.algorithmFinished(new AlgorithmEvent(
                    this, getSolution()));
            }
        }
    }

    /**
     * Used to fire solution changed event.
     */
    protected void fireBestSolutionUpdatedEvent() {
        fireBestSolutionUpdatedEvent(new AlgorithmEvent(this, getSolution()));
    }

    /**
     * Used to fire solution changed event.
     *
     * @param event a {@link com.github.yferras.javartint.core.util.AlgorithmEvent} object.
     */
    protected void fireBestSolutionUpdatedEvent(AlgorithmEvent event) {
        for (EventListener eventListener : eventListeners) {
            if (eventListener instanceof SolutionChangeListener) {
                SolutionChangeListener solutionChangeListener = (SolutionChangeListener) eventListener;
                solutionChangeListener.solutionUpdated(event);
            }
        }
    }
}
