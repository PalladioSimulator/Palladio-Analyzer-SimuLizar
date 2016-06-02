package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.storydriven.storydiagrams.activities.ActivitiesFactory;
import org.storydriven.storydiagrams.activities.Activity;
import org.storydriven.storydiagrams.activities.OperationExtension;

/**
 * Loader for activities in sdm models. Special thanks to Marie.
 * 
 * @author Joachim Meyer
 * 
 */
public final class ActivityLoader {

    /**
     * Binds parameters to given activity.
     * 
     * @param activity
     *            the activity.
     * @param parameterName
     *            the parameters names.
     * @param parameterTypes
     *            the parameter types.
     * @return the activity with bounded parameters.
     */
    public static Activity createBindings(final Activity activity, final String[] parameterName,
            final EClassifier[] parameterTypes) {

        final EClass clazz = EcoreFactory.eINSTANCE.createEClass();
        clazz.setName("ReconfigurationStrategy");

        final EOperation eOperation = createOperation(activity, "test", parameterName, parameterTypes);

        clazz.getEOperations().add(eOperation);

        final EAnnotation eAnnotation = eOperation.getEAnnotations().get(0);
        final OperationExtension operationExtension = (OperationExtension) eAnnotation.getContents().get(0);
        return operationExtension.getOwnedActivity();

    }

    private static EOperation createOperation(final Activity a, final String methodName, final String[] paramNames,
            final EClassifier[] paramTypes) {

        final EOperation method = EcoreFactory.eINSTANCE.createEOperation();
        method.setName(methodName);
        createParameters(method, paramNames, paramTypes);
        createOperationExtension(a, method);
        // createParameterExpressions(method, paramNames);
        return method;
    }

    private static void createOperationExtension(final Activity activity, final EOperation method) {
        final OperationExtension operation = ActivitiesFactory.eINSTANCE.createOperationExtension();
        operation.setOwnedActivity(EcoreUtil.copy(activity));
        operation.setOperation(method);
    }

    // private ParameterExpression createParameterExpression(final EParameter parameter, final
    // ObjectVariable var)
    // {
    // final ParameterExpression expression =
    // ExpressionsFactory.eINSTANCE.createParameterExpression();
    // final ParameterExtension paramExtension = CallsFactory.eINSTANCE.createParameterExtension();
    // paramExtension.setParameter(parameter);
    // expression.setParameter(paramExtension);
    // return expression;
    // }

    // private void createParameterExpressions(final EOperation method, final String[] paramNames)
    // {
    // final OperationExtension opExt = (OperationExtension) method
    // .getEAnnotation("http://ns.storydriven.org/extension").getContents().get(0);
    // final Activity activity = opExt.getOwnedActivity();
    // for (final ActivityNode node : activity.getOwnedActivityNodes())
    // {
    // if (node instanceof ModifyingStoryNode && node.getName().equals("modify pattern"))
    // {
    // final StoryPattern storyPattern = ((StoryNode) node).getStoryPattern();
    // for (final AbstractVariable var : storyPattern.getVariables())
    // {
    // if (var instanceof ObjectVariable)
    // {
    // setBindingExpressionForObjectVariable(method, paramNames, (ObjectVariable) var);
    // }
    // }
    // break;
    // }
    // }
    // }

    private static void createParameters(final EOperation method, final String[] paramNames,
            final EClassifier[] paramTypes) {
        for (int i = 0; i < paramNames.length; i++) {
            final EParameter parameter = EcoreFactory.eINSTANCE.createEParameter();
            parameter.setName(paramNames[i]);
            parameter.setEType(paramTypes[i]);
            method.getEParameters().add(parameter);
        }
    }

    // private void setBindingExpressionForObjectVariable(final EOperation method, final String[]
    // paramNames,
    // final ObjectVariable var)
    // {
    // for (int i = 0; i < paramNames.length; i++)
    // {
    // if (var.getName().equals(paramNames[i]))
    // {
    // for (final EParameter param : method.getEParameters())
    // {
    // if (param.getName().equals(paramNames[i]))
    // {
    // var.setBindingExpression(createParameterExpression(param, var));
    // }
    // }
    // break;
    // }
    // }
    // }

}
