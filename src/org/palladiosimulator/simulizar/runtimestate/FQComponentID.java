package org.palladiosimulator.simulizar.runtimestate;

import java.util.Collections;
import java.util.List;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

public class FQComponentID {

    private final List<AssemblyContext> assembyContextPath;

    public FQComponentID(List<AssemblyContext> assemblyContextPath) {
        super();
        this.assembyContextPath = Collections.unmodifiableList(assemblyContextPath);
    }

    /**
     * @return the assembyContextPath
     */
    public final List<AssemblyContext> getAssembyContextPath() {
        return assembyContextPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((assembyContextPath == null) ? 0 : assembyContextPath.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FQComponentID other = (FQComponentID) obj;
        if (assembyContextPath == null) {
            if (other.assembyContextPath != null) {
                return false;
            }
        } else if (!assembyContextPath.equals(other.assembyContextPath)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder pathBuilder = new StringBuilder();
        for (AssemblyContext assembyContext : assembyContextPath) {
            pathBuilder.append(assembyContext.getEntityName());
            pathBuilder.append("::");
        }
        pathBuilder.delete(pathBuilder.length() - 2, pathBuilder.length());
        return "FQComponentID [assembyContextPath=" + pathBuilder.toString() + "]";
    }

}
