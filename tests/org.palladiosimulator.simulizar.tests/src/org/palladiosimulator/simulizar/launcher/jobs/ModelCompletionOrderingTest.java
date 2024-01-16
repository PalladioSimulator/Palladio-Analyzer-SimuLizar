package org.palladiosimulator.simulizar.launcher.jobs;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableSet;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

class ModelCompletionOrderingTest {
    public static class MockJob implements IBlackboardInteractingJob<MDSDBlackboard> {
        @Override
        public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        }

        @Override
        public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        }

        @Override
        public String getName() {
            return "MockJob";
        }

        @Override
        public void setBlackboard(MDSDBlackboard blackboard) {
        }
    }
    
    public static class MockJobWithOrdering extends MockJob implements Comparable<IJob> {
        @Override
        public int compareTo(IJob o) {
            return 0;
        }
    }
    
    public static class MockContributer implements ModelCompletionJobContributor {
        private IBlackboardInteractingJob<MDSDBlackboard> job;

        public MockContributer(IBlackboardInteractingJob<MDSDBlackboard> job) {
            this.job = job;
        }
        
        @Override
        public void contribute(Facade delegate) {
            delegate.contribute(job);
        }
        
    }

    @Test
    void testNoOrdering() throws JobFailedException, UserCanceledException {
        var mockJob1Executed = new AtomicBoolean(false);
        var mockJob2Executed = new AtomicBoolean(false);
        
        var mockJob1 = new MockJob() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                mockJob1Executed.set(true);
            };
        };
        
        var mockJob2 = new MockJob() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                mockJob2Executed.set(true);
            };
        };
        
        var jobUnderTest = new ModelCompletionsJob(() -> ImmutableSet.of(new MockContributer(mockJob1), new MockContributer(mockJob2)));
        jobUnderTest.setBlackboard(new MDSDBlackboard());
        jobUnderTest.execute(new NullProgressMonitor());
        
        assertTrue(mockJob1Executed.get());
        assertTrue(mockJob2Executed.get());
    }
    
    @Test
    void testSimpleOrdering() throws JobFailedException, UserCanceledException {
        var executionOrder = new ConcurrentLinkedDeque<Integer>();
        
        var mockJob1 = new MockJob() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                executionOrder.add(1);
            };
        };
        
        var mockJob2 = new MockJobWithOrdering() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                executionOrder.add(2);
            };
            @Override
            public int compareTo(IJob o) {
                if  (o == mockJob1) return -1;                
                return super.compareTo(o);
            }
        };
        
        var jobUnderTest = new ModelCompletionsJob(() -> ImmutableSet.of(new MockContributer(mockJob1), new MockContributer(mockJob2)));
        jobUnderTest.setBlackboard(new MDSDBlackboard());
        jobUnderTest.execute(new NullProgressMonitor());
        assertThat(executionOrder, contains(2, 1));
    }
    
    @Test
    void testBothJobsProvideOrdering() throws JobFailedException, UserCanceledException {
        var executionOrder = new ConcurrentLinkedDeque<Integer>();
        
        var mockJob1 = new MockJobWithOrdering() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                executionOrder.add(1);
            };
            @Override
            public int compareTo(IJob o) {
                return 1;
            }
        };
        
        var mockJob2 = new MockJobWithOrdering() {
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                executionOrder.add(2);
            };
            @Override
            public int compareTo(IJob o) {
                if  (o == mockJob1) return -1;                
                return super.compareTo(o);
            }
        };
        
        var jobUnderTest = new ModelCompletionsJob(() -> ImmutableSet.of(new MockContributer(mockJob1), new MockContributer(mockJob2)));
        jobUnderTest.setBlackboard(new MDSDBlackboard());
        jobUnderTest.execute(new NullProgressMonitor());
        assertThat(executionOrder, contains(2, 1));
    }
    
    @Test
    void testJobsProvideConflictingOrder() throws JobFailedException, UserCanceledException {
        var mockJob1 = new MockJobWithOrdering() {
            @Override
            public int compareTo(IJob o) {
                return -1;
            }
        };
        
        var mockJob2 = new MockJobWithOrdering() {
            @Override
            public int compareTo(IJob o) {
                if  (o == mockJob1) return -1;                
                return super.compareTo(o);
            }
        };
        
        var jobUnderTest = new ModelCompletionsJob(() -> ImmutableSet.of(new MockContributer(mockJob1), new MockContributer(mockJob2)));
        jobUnderTest.setBlackboard(new MDSDBlackboard());
        assertThrows(IllegalStateException.class, () -> jobUnderTest.execute(new NullProgressMonitor()));
    }

}
