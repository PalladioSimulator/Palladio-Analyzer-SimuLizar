package org.palladiosimulator.simulizar.ui.configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.runconfig.AbstractConfigurationTab;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.workflow.launchconfig.ImageRegistryHelper;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;

/**
 * File name input tab for SimuLizar. Uses middleware and eventmiddle ware input fields for Monitor
 * Repository models and SDM models.
 */
public class LinkingResourceSimulationConfigurationTab extends AbstractLaunchConfigurationTab {

    // Label content
    
    private static final String LABEL_GROUP_LINKING_RESOURCE_SIMULATION = "Linking Resource Simulation";
    private static final String LABEL_SIMULATE_NETWORK = "Simulate full middleware marshalling / demarshalling of remote calls.";
    private static final String LABEL_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCE = "Simulate throughput of remote calls.";
    private static final String LABEL_SIMULATE_NO_THROUGHPUT_OF_LINKING_RESOURCE = "Do not simulate throughput of remote calls.";
    private static final String[] COMBO_SIMULATE_ITEMS = { LABEL_SIMULATE_NETWORK,
            LABEL_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCE, LABEL_SIMULATE_NO_THROUGHPUT_OF_LINKING_RESOURCE };
    private static final String MIDDELWARE_REPOSITORY_LABEL = "Middleware Repository";
    private static final String FEATURE_CONFIGURATION_FILE_LABEL = "Feature Configuration File";
    private static final String SIMULATE_NETWORK_EXPLANATION = "Latency is always simulated if linking resources connect containers. The linking resource is treated as a FCFS resource.";

    // Default values
    private static final Boolean DEFAULT_SIMULATE_LINKING_RESOURCES = false;
    private static final Boolean DEFAULT_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES = true;

    // UI elements
    private Combo simulateLinkingResourceCombo;
    private Text textMiddlewareRepository;
    private Text textFeatureConfig;
    protected List<Control> visibleForNetworkCompletion = new LinkedList<>();

    /**
     * {@inheritDoc}
     */
    public void createControl(Composite parent) {

        final ModifyListener modifyListener = ev -> setDirtyAndUpdate();

        final SelectionListener selectionListener = new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                setDirtyAndUpdate();
            }

            public void widgetSelected(SelectionEvent e) {
                setDirtyAndUpdate();
            }
        };

        // Create the top-level container:
        Composite container = new Composite(parent, SWT.NONE);
        setControl(container);
        container.setLayout(new GridLayout());

        // Create networking section:
        final Group networkingGroup = new Group(container, SWT.NONE);
        networkingGroup.setText(LABEL_GROUP_LINKING_RESOURCE_SIMULATION);
        final GridData gd_networkingGroup = new GridData(SWT.FILL, SWT.CENTER, true, false);
        networkingGroup.setLayoutData(gd_networkingGroup);
        networkingGroup.setLayout(new GridLayout());

        simulateLinkingResourceCombo = new Combo(networkingGroup, SWT.READ_ONLY);
        final GridData gd_simulateLinkingResourcesButton = new GridData(SWT.FILL, SWT.CENTER, true, false);
        simulateLinkingResourceCombo.setLayoutData(gd_simulateLinkingResourcesButton);
        simulateLinkingResourceCombo.setItems(COMBO_SIMULATE_ITEMS);
        simulateLinkingResourceCombo.addSelectionListener(selectionListener);

        Label simulateLinkingResourceLabel = new Label(networkingGroup, SWT.NONE);
        simulateLinkingResourceLabel.setText(SIMULATE_NETWORK_EXPLANATION);
        simulateLinkingResourceLabel.setEnabled(true);

        textMiddlewareRepository = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, MIDDELWARE_REPOSITORY_LABEL,
                ConstantsContainer.REPOSITORY_EXTENSION, textMiddlewareRepository, getShell(),
                ConstantsContainer.DEFAULT_RMI_MIDDLEWARE_REPOSITORY_FILE);
        visibleForNetworkCompletion.add(textMiddlewareRepository.getParent());

        textFeatureConfig = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, FEATURE_CONFIGURATION_FILE_LABEL,
                ConstantsContainer.FEATURECONFIG_EXTENSION, textFeatureConfig, getShell(),
                ConstantsContainer.DEFAULT_FEATURE_CONFIGURATION_FILE);
        visibleForNetworkCompletion.add(textFeatureConfig.getParent());
    }

    private void updateVisibilityOfSubgroups() {
        var vis = LABEL_SIMULATE_NETWORK.equals(simulateLinkingResourceCombo.getText());
        visibleForNetworkCompletion.forEach(c -> c.setVisible(vis));
    }

    private void setDirtyAndUpdate() {
        updateVisibilityOfSubgroups();
        LinkingResourceSimulationConfigurationTab.this.setDirty(true);
        LinkingResourceSimulationConfigurationTab.this.updateLaunchConfigurationDialog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
        setErrorMessage(null);

        if (LABEL_SIMULATE_NETWORK.equals(simulateLinkingResourceCombo.getText())) {
            if (!TabHelper.validateFilenameExtension(textFeatureConfig.getText(),
                    ConstantsContainer.FEATURECONFIG_EXTENSION)) {
                setErrorMessage("Feature config file is missing!");
                return false;
            }
            if (!TabHelper.validateFilenameExtension(textMiddlewareRepository.getText(),
                    ConstantsContainer.REPOSITORY_EXTENSION)) {
                setErrorMessage("Middleware repository model is missing!");
                return false;
            }
        }

        return super.isValid(launchConfig);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Link Simulation";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        return ImageRegistryHelper.getTabImage(AbstractConfigurationTab.PLUGIN_ID,
                AbstractConfigurationTab.CONFIGURATION_TAB_IMAGE_PATH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeFrom(ILaunchConfiguration configuration) {
        Map<String, Object> attrs;
        try {
            attrs = configuration.getAttributes();
        } catch (CoreException e) {
            throw new RuntimeException("Error parsing launch configuration", e);
        }

        var simulateLinkingResources = Optional.ofNullable(attrs.get(SimuComConfig.SIMULATE_LINKING_RESOURCES))
            .map(a -> Boolean.valueOf(a.toString()))
            .orElse(DEFAULT_SIMULATE_LINKING_RESOURCES);
        var simulateLinkThroughput = Optional
            .ofNullable(attrs.get(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES))
            .map(a -> Boolean.valueOf(a.toString()))
            .orElse(DEFAULT_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES);

        var mode = simulateLinkingResources ? LABEL_SIMULATE_NETWORK
                : simulateLinkThroughput ? LABEL_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCE
                        : LABEL_SIMULATE_NO_THROUGHPUT_OF_LINKING_RESOURCE;
        var idx = Arrays.asList(simulateLinkingResourceCombo.getItems())
            .indexOf(mode);
        simulateLinkingResourceCombo.select(idx);

        textFeatureConfig.setText(Optional.ofNullable(attrs.get(ConstantsContainer.FEATURE_CONFIG))
            .map(Object::toString)
            .orElse(ConstantsContainer.DEFAULT_FEATURE_CONFIGURATION_FILE));

        textMiddlewareRepository
            .setText(Optional.ofNullable(attrs.get(ConstantsContainer.RMI_MIDDLEWARE_REPOSITORY_FILE))
                .map(Object::toString)
                .orElse(ConstantsContainer.DEFAULT_RMI_MIDDLEWARE_REPOSITORY_FILE));

        updateVisibilityOfSubgroups();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
        switch (this.simulateLinkingResourceCombo.getText()) {
        case LABEL_SIMULATE_NO_THROUGHPUT_OF_LINKING_RESOURCE:
            configuration.setAttribute(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
            configuration.setAttribute(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES, false);
            break;
        case LABEL_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCE:
            configuration.setAttribute(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
            configuration.setAttribute(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES, true);
            break;
        case LABEL_SIMULATE_NETWORK:
            configuration.setAttribute(SimuComConfig.SIMULATE_LINKING_RESOURCES, true);
            configuration.setAttribute(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES, true);
            break;
        }

        configuration.setAttribute(ConstantsContainer.RMI_MIDDLEWARE_REPOSITORY_FILE,
                textMiddlewareRepository.getText());
        configuration.setAttribute(ConstantsContainer.FEATURE_CONFIG, textFeatureConfig.getText());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(SimuComConfig.SIMULATE_LINKING_RESOURCES, DEFAULT_SIMULATE_LINKING_RESOURCES);
        configuration.setAttribute(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES,
                DEFAULT_SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES);
        configuration.setAttribute(ConstantsContainer.RMI_MIDDLEWARE_REPOSITORY_FILE,
                ConstantsContainer.DEFAULT_RMI_MIDDLEWARE_REPOSITORY_FILE);
        configuration.setAttribute(ConstantsContainer.FEATURE_CONFIG,
                ConstantsContainer.DEFAULT_FEATURE_CONFIGURATION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return "org.palladiosimulator.simulizar.ui.configuration.linkingresource";
    }

}
