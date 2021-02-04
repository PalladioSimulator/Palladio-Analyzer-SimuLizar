package org.palladiosimulator.simulizar.di.extension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;

import com.google.common.collect.ImmutableSet;

class GenericExtensionComponentTest {

    @Test
    void testComponentWithoutExtension() {
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
        });
        assertThat(underTest.getExtensions(IModelObserver.class).get(), is(empty()));
    }
    
    @Test
    void testSingleExtension() {
        var observer = new IModelObserver() {
        };
        
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public IModelObserver provideObserver() {
                return observer;
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(observer));
    }
    
    @Test
    void testMultipleSingleExtension() {
        var observer = new IModelObserver() {
        };
        var observer2 = new IModelObserver() {
        };
        var observer3 = new IModelObserver() {
        };
        
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public IModelObserver provideObserver1() {
                return observer;
            }
            
            @SuppressWarnings("unused")
            public IModelObserver provideObserver2() {
                return observer2;
            }
            
            @SuppressWarnings("unused")
            public IModelObserver provideObserver3() {
                return observer3;
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(observer, observer2, observer3));
    }
    
    @Test
    void testSetExtension() {
        var observer = new IModelObserver() {
        };
        var observer2 = new IModelObserver() {
        };
        var observer3 = new IModelObserver() {
        };
        
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public Set<IModelObserver> provideObservers() {
                return ImmutableSet.of(observer, observer2, observer3);
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(observer, observer2, observer3));
    }
    

    @Test
    void testCombineSingleAndSetExtension() {
        var observer = new IModelObserver() {
        };
        var observer2 = new IModelObserver() {
        };
        var observer3 = new IModelObserver() {
        };
        var observer4 = new IModelObserver() {
        };
        var observer5 = new IModelObserver() {
        };
        
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public Set<IModelObserver> provideObservers() {
                return ImmutableSet.of(observer, observer2);
            }
            
            @SuppressWarnings("unused")
            public Set<IModelObserver> provideObservers2() {
                return ImmutableSet.of(observer3);
            }
            
            @SuppressWarnings("unused")
            public IModelObserver provideObserver4() {
                return observer4;
            }
            
            @SuppressWarnings("unused")
            public IModelObserver provideObserver5() {
                return observer5;
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(observer, observer2, observer3, observer4, observer5));
    }
    
    static class MergedExtensionType implements IModelObserver, RuntimeStateEntityObserver {
        @Override
        public void initialize() {
        }
    }
    
    @Test
    void testExtentionWithTwoTypesAdheresToStaticTypeSignature() {
        var mergedExtension = new MergedExtensionType();
        
        var underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public MergedExtensionType provide() {
                return mergedExtension;
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(mergedExtension));
        assertThat(underTest.getExtensions(RuntimeStateEntityObserver.class).get(), containsInAnyOrder(mergedExtension));
        
        underTest = new GenericExtensionComponent(new ExtensionComponent() {
            @SuppressWarnings("unused")
            public IModelObserver provide() {
                return mergedExtension;
            }
        });
        
        assertThat(underTest.getExtensions(IModelObserver.class).get(), containsInAnyOrder(mergedExtension));
        assertThat(underTest.getExtensions(RuntimeStateEntityObserver.class).get(), is(empty()));
        
         
    }

}
